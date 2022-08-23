package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Enumerated;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(defaultValue = "0") int page) {

        Object o = session.getAttribute("SPRING_SECURITY_CONTEXT");
        System.out.println(o);

//        Page<Question> paging = questionService.getList(page);
//
//        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name = "id") Long id,
                         Model model) {

        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);

        return "question_detail";
    }

    @ResponseBody
    @GetMapping("/list2")
    public String list2() {
        return "하하111777";
    }
}
