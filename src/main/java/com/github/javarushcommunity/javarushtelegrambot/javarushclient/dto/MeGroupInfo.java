package com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto;

import lombok.Data;

@Data
public class MeGroupInfo {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
