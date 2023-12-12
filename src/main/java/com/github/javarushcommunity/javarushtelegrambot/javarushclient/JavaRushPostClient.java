package com.github.javarushcommunity.javarushtelegrambot.javarushclient;

import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.PostInfo;

import java.util.List;

public interface JavaRushPostClient {

    List<PostInfo> findNewPosts(Long groupId, Long lastPostId);

}
