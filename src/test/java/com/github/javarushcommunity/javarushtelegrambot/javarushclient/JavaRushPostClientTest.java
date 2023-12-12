package com.github.javarushcommunity.javarushtelegrambot.javarushclient;

import com.github.javarushcommunity.javarushtelegrambot.javarushclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Integration-level testing for JavaRushPostClient")
public class JavaRushPostClientTest {

    private final JavaRushPostClient postClient =
            new JavaRushPostClientImpl("https://javarush.com/api/1.0/rest");

    @Test
    public void shouldProperlyGetNew15Posts() {
        //when
        List<PostInfo> newPosts = postClient.findNewPosts(30L, 2935L);

        //then
        Assertions.assertEquals(15, newPosts.size());
    }

}
