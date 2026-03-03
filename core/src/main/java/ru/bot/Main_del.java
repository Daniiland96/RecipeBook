package ru.bot;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.bot.model.Chat;
import ru.bot.model.User;
import ru.bot.repository.ChatRepository;
import ru.bot.repository.RecipeRepository;
import ru.bot.repository.UserRepository;

import java.util.HashSet;

@SpringBootApplication
@Slf4j
public class Main_del {
    static void main(String[] args) {
        SpringApplication.run(Main_del.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner run(UserRepository userRep,
                          ChatRepository chatRep,
                          RecipeRepository recipeRep
    ) {
        return args -> {
            Chat chat = chatRep.findByIdWithMembers("Chat_00_01").orElseThrow();
            log.info("\n" + chat.getMembers().size() + "\n");

            User user = createUser();
//            user = userRep.save(user);

            chat.addMember(user);
            chat = chatRep.save(chat);
            log.info("\n" + chat.toString() + "\n");
        };
    }

    User createUser() {
        return User.builder()
                .id("Dan-4445_6")
                .name("DanDan")
                .chats(new HashSet<>())
                .build();
    }

    Chat createChat() {
        return Chat.builder()
                .id("Chat_00_01")
                .name("Kitchen")
                .members(new HashSet<>())
                .build();
    }
}