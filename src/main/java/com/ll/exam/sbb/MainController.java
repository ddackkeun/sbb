package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @ResponseBody
    @GetMapping("/sbb")
    public String showMain() {
        return "안녕하세요";
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
//
//    @ResponseBody
//    @GetMapping("/saveSession/{name}/{value}")
//    public String saveSession(@PathVariable String name,
//                              @PathVariable String value,
//                              HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.setAttribute(name, value);
//
//        return "세션 변수 %s의 값이 %s로 설정되었습니다.".formatted(name, value);
//    }
//
//    @ResponseBody
//    @GetMapping("/getSession/{name}")
//    public String getSession(@PathVariable String name, HttpSession session) {
//        String value = (String) session.getAttribute(name);
//        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
//    }
//
//
//    private List<Article> articles = new ArrayList<>(
//            Arrays.asList(new Article("제목", "내용"), new Article("제목", "내용"))
//    );
//    // asList의 결과 값은 immutable(수정할 수 없는) 리스트이다.
//    // ArrayList 로 감싸면 mutable로 바뀐다.
//
//    @ResponseBody
//    @GetMapping("/addArticle")
//    public String addArticle(String title, String body) {
//        Article article = new Article(title, body);
//        articles.add(article);
//
//        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
//    }
//
//    @ResponseBody
//    @GetMapping("/article/{id}")
//    public Article getArticle(@PathVariable int id) {
//        Article article = articles
//                .stream()
//                .filter(a -> a.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        return article;
//    }
//
//    @ResponseBody
//    @GetMapping("/modifyArticle/{id}")
//    public String modifyArticle(@PathVariable int id, String title, String body) {
//        Article article = articles
//                .stream()
//                .filter(a -> a.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        if (article == null) {
//            return "%d번 게시물은 존재하지 않습니다.".formatted(id);
//        }
//
//        article.setTitle(title);
//        article.setBody(body);
//
//        return "%d번 게시물을 수정하였습니다.".formatted(id);
//    }
//
//    @ResponseBody
//    @GetMapping("/deleteArticle/{id}")
//    public String deleteArticle(@PathVariable int id) {
//        Article article = articles
//                .stream()
//                .filter(a -> a.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        if (article == null) {
//            return "%d번 게시물은 존재하지 않습니다.".formatted(id);
//        }
//
//        articles.remove(article);
//
//        return "%d번 게시물을 삭제하였습니다.".formatted(article.getId());
//    }
//
//    @ResponseBody
//    @GetMapping("/addPerson/{id}")
//    public Person addPerson(Person person) {
//        // Param 값으로 들어온 것을
//        // @ModelAttribute 로 객체를 자동 매핑해준다.
//        // PathVariable의 값도 쓰지 않아도 자동으로 매핑해서 넣어준다.
//        return person;
//    }
}
