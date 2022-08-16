package com.example.demo.Entities;

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

    @ManyToOne
    @JoinColumn(name = "id_user_main")
    private UserEntity dialog_user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dialog_message")
    private List<MessageEntity> messages;

    private Long id_user_interlocutor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_user_interlocutor() {
        return id_user_interlocutor;
    }

    public void setId_user_interlocutor(Long id_user_interlocutor) {
        this.id_user_interlocutor = id_user_interlocutor;
    }

    public UserEntity getDialog_user() {
        return dialog_user;
    }

    public void setDialog_user(UserEntity dialog_user) {
        this.dialog_user = dialog_user;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

}
