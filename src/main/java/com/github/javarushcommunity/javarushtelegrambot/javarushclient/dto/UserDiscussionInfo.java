package com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto;

import lombok.Data;

@Data
public class UserDiscussionInfo {

    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;

}
