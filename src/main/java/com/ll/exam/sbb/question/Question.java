package com.ll.exam.sbb.question;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser author;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    @ManyToMany
    Set<SiteUser> voter;

    private Long hitCount = 0L;
    
    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        answerList.add(answer);
    }
}
