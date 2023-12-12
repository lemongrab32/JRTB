package com.github.javarushcommunity.javarushtelegrambot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class StatisticDTO {

    private final int activeUserCount;
    private final int inactiveUserCount;
    private final List<GroupStatDTO> groupStatDTOs;
    private final double averageGroupCountByUser;

}
