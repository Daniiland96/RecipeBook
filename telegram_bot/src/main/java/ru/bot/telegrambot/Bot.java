package ru.bot.telegrambot;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import ru.bot.config.BotConfig;
import ru.bot.telegrambot.button.KeyboardFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot implements BotCommands {
    private final BotConfig config;

    public Bot(BotConfig config) {
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        Message message;
        Long chatId;
        Long userId;
        String userName;
        String receivedMessage;

        if (update.hasMessage() && update.getMessage().hasText()) {
            message = update.getMessage();
            chatId = message.getChatId();
            userId = message.getFrom().getId();
            userName = message.getFrom().getFirstName();
            receivedMessage = message.getText();
            List<MessageEntity> entities = message.getEntities();

            if (entities != null && !entities.isEmpty()) {
                log.info(message.getEntities().toString());
            receivedMessage = getBotCommandText(receivedMessage, entities);
            }

            log.info("\n Message -> chatId: {}\n userId: {}\n userName: {}\n receivedMessage: {}",
                    chatId, userId, userName, receivedMessage);

            botAnswerUtils(receivedMessage, chatId, userName);
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            log.info("\n Callback -> chatId: {}\n userId: {}\n userName: {}\n receivedMessage: {}",
                    chatId, userId, userName, receivedMessage);

            botAnswerUtils(receivedMessage, chatId, userName);
        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage) {
            case "/start", "Start":
                startBot(chatId, userName);
                break;
            case "/help", "Help":
                sendHelpText(chatId, HELP_TEXT);
                break;
            default:
                break;
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hi, " + userName + "! I'm a Telegram bot.'");
        message.setReplyMarkup(KeyboardFactory.setStartReplyKeyboard());
        message.setReplyMarkup(KeyboardFactory.setStartInlineKeyboard());

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("help sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private String getBotCommandText(String fullText, List<MessageEntity> entities) {

//        String myUsername = getBotUsername().toLowerCase();
//
//        for (MessageEntity entity : entities) {
//            if ("bot_command".equals(entity.getType())) {
//                String command = fullText.substring(entity.getOffset(),
//                        entity.getOffset() + entity.getLength());
////                нужен ли стринг билдер
//                if (command.endsWith("@" + myUsername)) {
//                    return command.
//                }
//            }
//        }
        return fullText;
    }
}