package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList();

        model.addAttribute("questionList", questionList);
        return "/question_list";
    }

    @GetMapping("/question/detail/{id}")
    public String detail(@PathVariable(name = "id") Long id,
                         Model model) {

        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);

        return "/question_detail";
    }

    @ResponseBody
    @GetMapping("/question/list2")
    public String list2() {
        return "하하111777";
    }
}
