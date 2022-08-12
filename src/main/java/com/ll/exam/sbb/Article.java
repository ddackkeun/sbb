package com.ll.exam.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Article {
    private static int lastId = 0;
    private int id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this(++lastId, title, body);
    }


}
