
CREATE TABLE group_sub (
    id BIGINT,
    title VARCHAR(100),
    last_article_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE group_x_user (
    group_sub_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tg_user(chat_id),
    FOREIGN KEY (group_sub_id) REFERENCES group_sub(id),
    UNIQUE (user_id, group_sub_id)
);