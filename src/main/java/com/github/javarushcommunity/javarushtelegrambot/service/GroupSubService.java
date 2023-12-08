package com.github.javarushcommunity.javarushtelegrambot.service;

import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.javarushtelegrambot.repository.entity.GroupSub;

public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);

}
