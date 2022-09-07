package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(schema = "public", name = "Dialogs")
public class DialogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dialog_message")
    private List<MessageEntity> messages;

    @ManyToOne
    @JoinColumn(name = "id_user_main")
    private UserEntity dialog_user;
}
