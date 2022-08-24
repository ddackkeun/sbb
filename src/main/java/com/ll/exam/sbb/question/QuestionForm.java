package com.ll.exam.sbb.question;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class QuestionForm {

    private String subject;
    private String content;
}
