package com.ll.exam.sbb;

import com.ll.exam.sbb.AnswerRepositoryTest;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.question.QuestionRepository;
import com.ll.exam.sbb.user.UserRepository;
import com.ll.exam.sbb.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;


    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    public static void createSampleData(UserService userService) {
        userService.create("admin", "admin@test.com", "1234");
        userService.create("user1", "user1@test.com", "1234");
    }

    private void createSampleData() {
        createSampleData(userService);
    }

    public static void clearData(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        AnswerRepositoryTest.clearData(answerRepository, questionRepository);
        QuestionRepositoryTest.clearData(questionRepository);
        userRepository.deleteAll();     // DELETE FROM site_user;
        userRepository.truncate();
    }

    private void clearData() {
        clearData(userRepository, answerRepository, questionRepository);
    }

    @Test
    @DisplayName("회원가입이 가능하다.")
    public void t1() {
        userService.create("user2", "user2@email.com", "1234");
    }
}