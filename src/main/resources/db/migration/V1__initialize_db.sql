CREATE TABLE `idea`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `creator_id`   INT          NOT NULL,
    `creator_name` VARCHAR(255) NOT NULL,
    `title`        VARCHAR(255) NOT NULL,
    `description`  VARCHAR(255) NOT NULL,
    `image_id`     BIGINT       NOT NULL,
    `likes`        BIGINT       NOT NULL DEFAULT 0,

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci;

CREATE TABLE `category`
(
    `id`   INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci;

CREATE TABLE `idea_category`
(
    `idea_id`     BIGINT,
    `category_id` INT,
    PRIMARY KEY (idea_id, category_id),
    FOREIGN KEY (idea_id) REFERENCES idea (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci;

CREATE TABLE `like`
(
    `id`      BIGINT AUTO_INCREMENT PRIMARY KEY,
    `idea_id` BIGINT NOT NULL,
    `user_id` INT    NOT NULL,
    UNIQUE (idea_id, user_id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci;

CREATE TABLE `user`
(
    `id`    VARCHAR(255) PRIMARY KEY,
    `name`  VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci;

CREATE TABLE `comment`
(
    `id`         BIGINT AUTO_INCREMENT PRIMARY KEY,
    `text`       TEXT         NOT NULL,
    `user_id`    VARCHAR(255) NOT NULL,
    `idea_id`    BIGINT       NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci;
