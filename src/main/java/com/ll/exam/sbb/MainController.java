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

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @ResponseBody
    @GetMapping("/plus")
    public int showPlus(int a, int b) {
        return a + b;
    }

    @GetMapping("/minus")
    @ResponseBody
    public int showMinus(int a, int b) {
        return a - b;
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit) {

        if (limit == null) {
            limit = 9;
        }

        if (dan == null) {
            dan = 9;
        }

        Integer finalDan = dan;
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
                .collect(Collectors.joining("<br>"));
    }

    @ResponseBody
    @GetMapping("/mbti/{name}")
    public String showMbti(@PathVariable String name) {
        String result = switch (name) {
            case "홍길동" -> "INFP";
            case "임꺽정" -> "ENFP";
            case "김철수" -> "ISTJ";
            default -> "모름";
        };
        return result;
    }

    @ResponseBody
    @GetMapping("/saveSession/{name}/{value}")
    public String saveSession(@PathVariable String name,
                              @PathVariable String value,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);

        return "세션 변수 %s의 값이 %s로 설정되었습니다.".formatted(name, value);
    }

    @ResponseBody
    @GetMapping("/getSession/{name}")
    public String getSession(@PathVariable String name, HttpSession session) {
        String value = (String) session.getAttribute(name);
        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
    }


    private List<Article> articles = new ArrayList<>(
            Arrays.asList(new Article("제목", "내용"), new Article("제목", "내용"))
    );
    // asList의 결과 값은 immutable(수정할 수 없는) 리스트이다.
    // ArrayList 로 감싸면 mutable로 바뀐다.

    @ResponseBody
    @GetMapping("/addArticle")
    public String addArticle(String title, String body) {
        Article article = new Article(title, body);
        articles.add(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }

    @ResponseBody
    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable int id) {
        Article article = articles
                    .stream()
                    .filter(a -> a.getId() == id)
                    .findFirst()
                    .orElse(null);

        return article;
    }

    @ResponseBody
    @GetMapping("/modifyArticle/{id}")
    public String modifyArticle(@PathVariable int id, String title, String body) {
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null) {
            return "%d번 게시물은 존재하지 않습니다.".formatted(id);
        }

        article.setTitle(title);
        article.setBody(body);

        return "%d번 게시물을 수정하였습니다.".formatted(id);
    }
}
