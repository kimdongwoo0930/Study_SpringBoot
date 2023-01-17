CREATE TABLE `account`
(
    `user_id`       varchar(50)  NOT NULL COMMENT '사용자 ID',
    `user_password` varchar(255) NOT NULL COMMENT '사용자 비밀번호',
    `user_name`     varchar(100) NOT NULL COMMENT '사용자 이름',
    `created_at`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`   timestamp             DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';