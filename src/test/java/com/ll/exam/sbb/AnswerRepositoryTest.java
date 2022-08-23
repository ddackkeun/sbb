package com.ll.exam.sbb;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionRepository;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AnswerRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;


    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    public static void clearData(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        QuestionRepositoryTest.clearData(questionRepository);

        answerRepository.deleteAll();
        answerRepository.truncate();
    }

    private void clearData() {
        clearData(answerRepository, questionRepository);
    }

    private void createSampleData() {
        QuestionRepositoryTest.createSampleData(questionRepository);

        Question q = questionRepository.findById(1L).get();

        Answer a1 = new Answer();
        a1.setContent("sbb는 질문답변 게시판 입니다.");
        a1.setCreateDate(LocalDateTime.now());
        a1.setAuthor(new SiteUser(1L));
        q.addAnswer(a1);

        Answer a2 = new Answer();
        a2.setContent("sbb에서는 주로 스프링부트 관련 내용을 다룹니다.");
        a2.setCreateDate(LocalDateTime.now());
        a2.setAuthor(new SiteUser(2L));
        q.addAnswer(a2);

        questionRepository.save(q);     // answer 반영 내용 저장을 위해 사용
    }

    @Test
    @Transactional
    @Rollback(false)
    void 저장() {
        Question q = questionRepository.findById(2L).get();

        Answer a1 = new Answer();
        a1.setContent("네 자동으로 생성됩니다.");
        a1.setCreateDate(LocalDateTime.now());
        a1.setAuthor(new SiteUser(2L));
        q.addAnswer(a1);

        Answer a2 = new Answer();
        a2.setContent("네 맞습니다.");
        a2.setCreateDate(LocalDateTime.now());
        a2.setAuthor(new SiteUser(2L));
        q.addAnswer(a2);

        questionRepository.save(q);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 조회() {
        Answer a = answerRepository.findById(1L).get();

        Assertions.assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
    }

    @Test
    @Transactional
    @Rollback(false)
    void 관련된_question_조회() {
        Answer a = answerRepository.findById(1L).get();
        Question question = a.getQuestion();

        assertThat(question.getId()).isEqualTo(1L);
    }

    @Test
    @Transactional
    @Rollback(false)
    void question으로_관련된_answer_조회() {
        // Transactional이 없으면 question 가져오면서 DB연결이 끊어짐
        Question q = questionRepository.findById(1L).get();
        // SELECT * FROM question WHERE id = 1

        List<Answer> answerList = q.getAnswerList();
        // SELECT * FROM answer WHERE question_id = 1

        assertThat(answerList.size()).isEqualTo(2);
        assertThat(answerList.get(0).getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
    }
}