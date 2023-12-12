package com.github.javarushcommunity.javarushtelegrambot.service;

import com.github.javarushcommunity.javarushtelegrambot.bot.JavarushTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{

    private final JavarushTelegramBot javarushBot;

    @Autowired
    public SendBotMessageServiceImpl(JavarushTelegramBot javarushBot) {
        this.javarushBot = javarushBot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            javarushBot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(Long chatId, List<String> messages) {
        if (messages.isEmpty()) return;

        messages.forEach(m -> sendMessage(chatId, m));
    }
}
