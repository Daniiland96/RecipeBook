package ru.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
public class TelegramBotMain {
    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(TelegramBotMain.class, args);
    }
}
