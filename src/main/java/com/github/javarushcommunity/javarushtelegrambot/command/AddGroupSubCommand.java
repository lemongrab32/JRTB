package com.github.javarushcommunity.javarushtelegrambot.command;

import com.github.javarushcommunity.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.JavaRushGroupClientImpl;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupRequestArgs;
import com.github.javarushcommunity.javarushtelegrambot.repository.entity.GroupSub;
import com.github.javarushcommunity.javarushtelegrambot.service.GroupSubService;
import com.github.javarushcommunity.javarushtelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.javarushcommunity.javarushtelegrambot.command.CommandName.ADD_GROUP_SUB;
import static com.github.javarushcommunity.javarushtelegrambot.command.CommandUtils.*;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class AddGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JavaRushGroupClient javaRushGroupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService,
                              JavaRushGroupClient javaRushGroupClient,
                              GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.javaRushGroupClient = javaRushGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        Long chatId = getChatId(update);
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = javaRushGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(String.valueOf(chatId), groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(String.valueOf(chatId), groupById);
            sendBotMessageService.sendMessage(String.valueOf(chatId),
                    "Подписал на группу " + savedGroupSub.getTitle());
        }
        else {
            sendNotValidGroupID(chatId, groupId);
        }
    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendNotValidGroupID(Long chatId, String groupId) {
        String groupNotFoundMessage = "Неправильный ID группы = \"%s\"";
        sendBotMessageService.sendMessage(String.valueOf(chatId), String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(Long chatId) {
        String groupIds = javaRushGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передайте команду вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "Я подготовил список всех групп: \n\n" +
                "Имя группы - ID группы \n\n" +
                "%s";

        sendBotMessageService.sendMessage(String.valueOf(chatId), String.format(message, groupIds));
    }

}
