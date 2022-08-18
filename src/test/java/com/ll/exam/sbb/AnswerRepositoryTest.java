package com.ll.exam.sbb;

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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    private int lastSampleDataId;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    private void clearData() {
        QuestionRepositoryTest.clearData(questionRepository);

        answerRepository.deleteAll();
        answerRepository.truncateTable();
    }

    private void createSampleData() {
        QuestionRepositoryTest.createSampleData(questionRepository);

        Question q = questionRepository.findById(1L).get();

        Answer a1 = new Answer();
        a1.setContent("sbb는 질문답변 게시판 입니다.");
        a1.setQuestion(q);
        a1.setCreateDate(LocalDateTime.now());
        answerRepository.save(a1);

        Answer a2 = new Answer();
        a2.setContent("sbb에서는 주로 스프링부트 관련 내용을 다룹니다.");
        a2.setQuestion(q);
        a2.setCreateDate(LocalDateTime.now());
        answerRepository.save(a2);

    }

    @Test
    void 저장() {
        Optional<Question> oq = questionRepository.findById(2L);
        assertThat(oq.isPresent()).isTrue();
        Question question = oq.get();

        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answerRepository.save(answer);
    }

    @Test
    void 조회() {
        Question q = questionRepository.findById(1L).get();

        Optional<Answer> oa = this.answerRepository.findById(1L);
        assertThat(oa.isPresent()).isTrue();
        Answer a = oa.get();

        Assertions.assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
    }

    @Test
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