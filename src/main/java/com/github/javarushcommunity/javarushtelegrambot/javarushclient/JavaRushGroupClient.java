package com.github.javarushcommunity.javarushtelegrambot.javarushclient;

import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupCountRequestArgs;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupInfo;
import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupRequestArgs;

import java.util.List;

public interface JavaRushGroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    Long getGroupCount(GroupCountRequestArgs countRequestArgs);

    GroupDiscussionInfo getGroupById(Long id);

    Long findLastPostId(Long groupSubId);

}
