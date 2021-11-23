package com.example.blog.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

@Entity
public class Post implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;
    private String anons;
    private String full_text;



    //@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime published_at;


    public LocalDateTime getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDateTime published_at) {
        this.published_at = published_at;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.modules(new JavaTimeModule());

        return builder;
    }


    public Integer priority;

    public void priorityValue(Integer priority){
        if ((priority >= 5)||(priority <= 0)){
            System.out.println("Введите значение от 0 до 5");
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Post() {}

    public Post(String title, String anons, String full_text, LocalDateTime published_at, Integer priority) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.published_at = published_at;
        this.priority = priority;
    }

    public static final Comparator<Post> COMPARE_BY_PRIORITY = new Comparator<Post>() {
        @Override
        public int compare(Post lhs, Post rhs) {
            return rhs.getPriority() - lhs.getPriority();
        }
    };
}
