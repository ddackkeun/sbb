package com.ll.exam.sbb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;
    private static Long lastSampleDataId;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    public static Long createSampleData(QuestionRepository questionRepository) {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);    // 두번째 질문 저장

        return q2.getId();
    }

    private void createSampleData() {
        lastSampleDataId = createSampleData(questionRepository);
    }

    public static void clearData(QuestionRepository questionRepository) {
        questionRepository.deleteAll();
        questionRepository.truncateTable();
    }

    private void clearData() {
        clearData(questionRepository);
    }

    @Test
    void 저장() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);  // 두번째 질문 저장

        assertThat(q1.getId()).isGreaterThan(lastSampleDataId + 1);
        assertThat(q2.getId()).isGreaterThan(lastSampleDataId + 2);
    }

    @Test
    void 삭제() {
        assertThat(questionRepository.count()).isEqualTo(lastSampleDataId);

        Optional<Question> oq = questionRepository.findById(1L);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        questionRepository.delete(q);
        assertThat(questionRepository.count()).isEqualTo(lastSampleDataId - 1);
    }

    @Test
    void 수정() {
        Optional<Question> oq = questionRepository.findById(1L);
        assertThat(oq.isPresent()).isTrue();
        Question question = oq.get();

        question.setSubject("수정된 제목");
        questionRepository.save(question);

        Question findQuestion = questionRepository.findById(1L).get();
        assertThat(findQuestion.getSubject()).isEqualTo("수정된 제목");
    }

    @Test
    void findAll() {
        List<Question> all = questionRepository.findAll();
        assertThat(Long.valueOf(all.size())).isEqualTo(lastSampleDataId);

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

	@Test
	void findSubject() {
        Optional<Question> oq = questionRepository.findBySubject("sbb가 무엇인가요?");
        assertThat(oq.isPresent()).isTrue();
        Question question = oq.get();

        assertThat(question.getId()).isEqualTo(1);
	}

	@Test
	void findBySubjectAndContent() {
		Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertThat(q.getId()).isEqualTo(1);
	}

	@Test
	void findBySubjectLike() {
		List<Question> qList = questionRepository.findBySubjectLike("sbb%");
        Question question = qList.get(0);

		assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}

}