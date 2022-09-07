package com.example.demo.entity;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "public", name = "Friends")
public class FriendsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Long id_interlocutor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public Long getId_interlocutor() {
        return id_interlocutor;
    }

    public void setId_interlocutor(Long id_interlocutor) {
        this.id_interlocutor = id_interlocutor;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
