package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(schema = "public", name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<FileEntity> fileEntityList;

    public List<FileEntity> getFileEntityList() {
        return fileEntityList;
    }

    public void setFileEntityList(List<FileEntity> fileEntityList) {
        this.fileEntityList = fileEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
