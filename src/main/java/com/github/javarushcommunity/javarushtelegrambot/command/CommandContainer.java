package com.github.javarushcommunity.javarushtelegrambot.command;

import com.github.javarushcommunity.javarushtelegrambot.command.annotation.AdminCommand;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.JavaRushGroupClientImpl;
import com.github.javarushcommunity.javarushtelegrambot.service.GroupSubService;
import com.github.javarushcommunity.javarushtelegrambot.service.SendBotMessageService;
import com.github.javarushcommunity.javarushtelegrambot.service.StatisticsService;
import com.github.javarushcommunity.javarushtelegrambot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import java.util.List;

import static com.github.javarushcommunity.javarushtelegrambot.command.CommandName.*;
import static java.util.Objects.nonNull;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            JavaRushGroupClient javaRushGroupClient,
                            GroupSubService groupSubService,
                            List<String> admins,
                            StatisticsService statisticsService) {

        this.admins = admins;

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, statisticsService))
                .put(ADD_GROUP_SUB.getCommandName(),
                        new AddGroupSubCommand(sendBotMessageService, javaRushGroupClient, groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService,
                        telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService))
                .put(ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }

}
