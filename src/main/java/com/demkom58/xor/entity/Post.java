package com.demkom58.xor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Post cannot be empty")
    private String content;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User wallOwner;

    Post() {
    }

    public Post(String content, User sender, User wallOwner) {
        this.content = content;
        if (content.isEmpty())
            throw new IllegalArgumentException();

        this.sender = sender;
        Objects.requireNonNull(sender);

        this.wallOwner = wallOwner;
        Objects.requireNonNull(wallOwner);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User author) {
        this.sender = author;
    }

    public User getWallOwner() {
        return wallOwner;
    }

    public void setWallOwner(User receiver) {
        this.wallOwner = receiver;
    }
}
