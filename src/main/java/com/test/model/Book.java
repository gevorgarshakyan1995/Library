package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String authot;//author

    @Column
    private int haort;

    @Column
    private  int value;

    @Column(name = "value_rent")
    private  int valueRent;

    @Column(name = "tream_rent")
    private String tremRent;

    @Enumerated(EnumType.STRING)
    @Column(name = "Book_status")
    private StatusBook status;

    @Column(name = "reseved_Book_token", unique = true)
    private String ResevedBook ;

    @Column(name = "status_timeMillis")
    private Long StatusTime;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    // Who is it with
    private User user;

    public Long getStatusTime() {
        return StatusTime;
    }

    public void setStatusTime(Long statusTime) {
        StatusTime = statusTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthot() {
        return authot;
    }

    public void setAuthot(String authot) {
        this.authot = authot;
    }

    public int getHaort() {
        return haort;
    }

    public void setHaort(int haort) {
        this.haort = haort;
    }

    public int getValue() {
        return value;
    }

    public String getResevedBook() {
        return ResevedBook;
    }

    public void setResevedBook(String resevedBook) {
        ResevedBook = resevedBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValueRent() {
        return valueRent;
    }

    public void setValueRent(int valueRent) {
        this.valueRent = valueRent;
    }

    public String getTremRent() {
        return tremRent;
    }

    public void setTremRent(String tremRent) {
        this.tremRent = tremRent;
    }

    public StatusBook getStatus() {
        return status;
    }

    public void setStatus(StatusBook status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authot='" + authot + '\'' +
                ", haort=" + haort +
                ", value=" + value +
                ", valueRent=" + valueRent +
                ", tremRent='" + tremRent + '\'' +
                '}';
    }
}
