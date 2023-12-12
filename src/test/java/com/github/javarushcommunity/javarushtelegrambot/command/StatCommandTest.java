package com.github.javarushcommunity.javarushtelegrambot.command;

import com.github.javarushcommunity.javarushtelegrambot.dto.GroupStatDTO;
import com.github.javarushcommunity.javarushtelegrambot.dto.StatisticDTO;
import com.github.javarushcommunity.javarushtelegrambot.service.SendBotMessageService;
import com.github.javarushcommunity.javarushtelegrambot.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static com.github.javarushcommunity.javarushtelegrambot.command.CommandName.STAT;
import static com.github.javarushcommunity.javarushtelegrambot.command.StatCommand.STAT_MESSAGE;

@DisplayName("Unit-level testing for StatCommand")
public class StatCommandTest extends AbstractCommandTest{

    private SendBotMessageService sendBotMessageService;
    private StatisticsService statisticsService;
    private Command statCommand;

    @BeforeEach
    public void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        statisticsService = Mockito.mock(StatisticsService.class);
        statCommand = new StatCommand(sendBotMessageService, statisticsService);
    }

    @Test
    public void shouldProperlySendMessage() {
        //given
        Long chatId = 1234567L;
        GroupStatDTO groupDto = new GroupStatDTO(1L, "group", 1);
        StatisticDTO statisticDTO = new StatisticDTO(1, 1,
                Collections.singletonList(groupDto), 2.5);
        Mockito.when(statisticsService.countBotStatistic())
                .thenReturn(statisticDTO);

        //when
        statCommand.execute(prepareUpdate(chatId, STAT.getCommandName()));

        //then
        Mockito.verify(sendBotMessageService).sendMessage(chatId,
                String.format(STAT_MESSAGE, statisticDTO.getActiveUserCount(),
                        statisticDTO.getInactiveUserCount(),
                        statisticDTO.getAverageGroupCountByUser(),
                        String.format("%s (id = %s) - %s подписчиков",
                                groupDto.getTitle(), groupDto.getId(),
                                groupDto.getActiveUserCount())));
    }

    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendBotMessageService, statisticsService);
    }
}
