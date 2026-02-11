package ru.bot.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class KeyboardFactory {

    public static InlineKeyboardMarkup setStartInlineKeyboard() {
        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        inlineButton("Start", "/start"),
                        inlineButton("Help", "/help"),
                        inlineButton("Inline", "/inline")
                ))
                .build();
    }

    public static ReplyKeyboardMarkup setStartReplyKeyboard() {
        return ReplyKeyboardMarkup.builder()
                .keyboardRow(new KeyboardRow(List.of(
                        replyButton("Start"),
                        replyButton("Help"),
                        replyButton("Reply")
                )))
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .selective(false)
                .build();
    }

    private static InlineKeyboardButton inlineButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }

    private static KeyboardButton replyButton(String text) {
        return new KeyboardButton(text);
    }
}