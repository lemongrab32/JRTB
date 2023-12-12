package com.github.javarushcommunity.javarushtelegrambot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"title", "activeUserCount"})
public class GroupStatDTO {

    private final Long id;
    private final String title;
    private final Integer activeUserCount;

}
