package com.ll.exam.sbb.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class AnswerForm {
    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;
}
