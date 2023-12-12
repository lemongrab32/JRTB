package com.github.javarushcommunity.javarushtelegrambot.service;

import com.github.javarushcommunity.javarushtelegrambot.dto.GroupStatDTO;
import com.github.javarushcommunity.javarushtelegrambot.dto.StatisticDTO;
import com.github.javarushcommunity.javarushtelegrambot.repository.entity.GroupSub;
import com.github.javarushcommunity.javarushtelegrambot.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

@DisplayName("Unit-level testing for StatisticsService")
public class StatisticsServiceTest {

    private GroupSubService groupSubService;
    private TelegramUserService telegramUserService;

    private StatisticsService statisticsService;

    @BeforeEach
    public void init() {
        groupSubService = Mockito.mock(GroupSubService.class);
        telegramUserService = Mockito.mock(TelegramUserService.class);
        statisticsService = new StatisticsServiceImpl(groupSubService, telegramUserService);
    }

    @Test
    public void shouldProperlySendStatDTO() {
        //given
        Mockito.when(telegramUserService.retrieveAllInActiveUsers())
                .thenReturn(Collections.singletonList(new TelegramUser()));
        TelegramUser activeUser = new TelegramUser();
        activeUser.setGroupSubs(Collections.singletonList(new GroupSub()));
        Mockito.when(telegramUserService.retrieveAllActiveUsers())
                .thenReturn(Collections.singletonList(activeUser));
        GroupSub groupSub = new GroupSub();
        groupSub.setTitle("group");
        groupSub.setId(1L);
        groupSub.setUsers(Collections.singletonList(new TelegramUser()));
        Mockito.when(groupSubService.findAll()).thenReturn(Collections.singletonList(groupSub));

        //when
        StatisticDTO statisticDTO = statisticsService.countBotStatistic();

        //then
        Assertions.assertNotNull(statisticDTO);
        Assertions.assertEquals(1, statisticDTO.getActiveUserCount());
        Assertions.assertEquals(1, statisticDTO.getInactiveUserCount());
        Assertions.assertEquals(1.0, statisticDTO.getAverageGroupCountByUser());
        Assertions.assertEquals(Collections.singletonList(new GroupStatDTO(groupSub.getId(),
                groupSub.getTitle(), groupSub.getUsers().size())), statisticDTO.getGroupStatDTOs());
    }

}
