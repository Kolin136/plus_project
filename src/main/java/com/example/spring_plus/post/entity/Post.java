package com.example.spring_plus.post.entity;

import com.example.spring_plus.global.auditing.Timestamped;
import com.example.spring_plus.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 800)
    private String contents;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updatePost(String title,String contents) {
        this.title = title;
        this.contents = contents;
    }






}
