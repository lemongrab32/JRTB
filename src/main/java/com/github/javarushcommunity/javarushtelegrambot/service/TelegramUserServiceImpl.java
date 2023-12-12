package com.github.javarushcommunity.javarushtelegrambot.service;

import com.github.javarushcommunity.javarushtelegrambot.repository.TelegramUserRepository;
import com.github.javarushcommunity.javarushtelegrambot.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService{

    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public List<TelegramUser> retrieveAllInActiveUsers() {
        return telegramUserRepository.findAllByActiveFalse();
    }

    @Override
    public Optional<TelegramUser> findByChatId(Long chatId) {
        return telegramUserRepository.findById(chatId);
    }
}
