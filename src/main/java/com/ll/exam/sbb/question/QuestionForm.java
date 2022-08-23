package com.ll.exam.sbb.question;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class QuestionForm {

    private String subject;
    private String content;
}
