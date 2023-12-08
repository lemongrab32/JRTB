package com.github.javarushcommunity.javarushtelegrambot.repository;

import com.github.javarushcommunity.javarushtelegrambot.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
