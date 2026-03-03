package ru.bot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "members")
    private Set<Chat> chats = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recipe> recipes = new HashSet<>();

    public void joinChat(Chat chat) {
        chats.add(chat);
        chat.getMembers().add(this);
    }

    public void leaveChat(Chat chat) {
        chats.remove(chat);
        chat.getMembers().remove(this);
    }
}