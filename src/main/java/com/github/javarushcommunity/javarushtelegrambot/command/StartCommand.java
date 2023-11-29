package com.github.javarushcommunity.javarushtelegrambot.command;

import com.github.javarushcommunity.javarushtelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет, я Javarush Telegram Bot." +
            "Я помогу тебе быть в курсе последних статей тех авторов, которые тебе" +
            "интересны. Я ещё маленький и только учусь.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                START_MESSAGE);
    }
}
