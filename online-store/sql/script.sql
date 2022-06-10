create table category_ref
(
    category_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    category_name varchar(256) not null
);

create table chat
(
    chat_id    int auto_increment
        constraint `PRIMARY`
        primary key,
    start_date timestamp(6) not null
);

create table city_ref
(
    city_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    city_name varchar(256) not null
);

create table message_status_ref
(
    message_status_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    message_status_name varchar(45) not null
);

create table product_level_ref
(
    product_level_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    product_level_name varchar(45) not null
);

create table product_status_ref
(
    product_status_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    product_status_name varchar(45) not null
);

create table rating_ref
(
    rating_id     int auto_increment
        constraint `PRIMARY`
        primary key,
    rating_number int not null
);

create table role_ref
(
    role_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    role_name varchar(45) not null
);

create table user_status_ref
(
    user_status_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    user_status_name varchar(45) not null
);

create table user
(
    user_id        int auto_increment
        constraint `PRIMARY`
        primary key,
    user_email     varchar(256) not null,
    user_hash_pass varchar(255) null,
    actual_date    timestamp(6) not null,
    user_phone     varchar(255) null,
    user_google_id varchar(50)  null,
    role_id        int          not null,
    user_status_id int          not null,
    constraint fk_users_role_ref1
        foreign key (role_id) references role_ref (role_id),
    constraint fk_users_user_status_ref
        foreign key (user_status_id) references user_status_ref (user_status_id)
);

create table chat_user
(
    chat_id int not null,
    user_id int not null,
    constraint `PRIMARY`
        primary key (chat_id, user_id),
    constraint fk_chat_has_user_chat1
        foreign key (chat_id) references chat (chat_id),
    constraint fk_chat_has_user_user1
        foreign key (user_id) references user (user_id)
);

create index fk_chat_has_user_chat1_idx
    on chat_user (chat_id);

create index fk_chat_has_user_user1_idx
    on chat_user (user_id);

create table message
(
    message_id        int auto_increment
        constraint `PRIMARY`
        primary key,
    text              text         not null,
    actual_date       timestamp(6) not null,
    sender_id         int          not null,
    chat_id           int          not null,
    message_status_id int          not null,
    constraint fk_message_chat1
        foreign key (chat_id) references chat (chat_id),
    constraint fk_message_message_status_ref1
        foreign key (message_status_id) references message_status_ref (message_status_id),
    constraint fk_message_user1
        foreign key (sender_id) references user (user_id)
);

create index fk_message_chat1_idx
    on message (chat_id);

create index fk_message_message_status_ref1_idx
    on message (message_status_id);

create index fk_message_user1_idx
    on message (sender_id);

create table product
(
    product_id        int auto_increment
        constraint `PRIMARY`
        primary key,
    product_name      varchar(256)   not null,
    description       text           null,
    product_price     decimal(10, 2) null,
    actual_date       timestamp(6)   not null,
    product_status_id int            not null,
    category_id       int            not null,
    city_id           int            not null,
    user_id           int            not null,
    product_level_id  int            not null,
    constraint fk_product_category_ref1
        foreign key (category_id) references category_ref (category_id),
    constraint fk_product_city_ref1
        foreign key (city_id) references city_ref (city_id),
    constraint fk_product_product_level_ref1
        foreign key (product_level_id) references product_level_ref (product_level_id),
    constraint fk_product_product_status_ref1
        foreign key (product_status_id) references product_status_ref (product_status_id),
    constraint fk_product_users1
        foreign key (user_id) references user (user_id)
);

create table auction
(
    auction_id    int auto_increment
        constraint `PRIMARY`
        primary key,
    product_id    int            not null,
    user_id       int            null,
    end_date      timestamp      null,
    is_active     tinyint(1)     null,
    current_price decimal(10, 2) null,
    constraint FK_auction_product
        foreign key (product_id) references product (product_id),
    constraint FK_auction_user
        foreign key (user_id) references user (user_id)
);

create table basket
(
    user_id    int not null,
    product_id int not null,
    constraint `PRIMARY`
        primary key (user_id, product_id),
    constraint fk_users_has_product_product1
        foreign key (product_id) references product (product_id),
    constraint fk_users_has_product_users1
        foreign key (user_id) references user (user_id)
);

create index fk_users_has_product_product1_idx
    on basket (product_id);

create index fk_users_has_product_users1_idx
    on basket (user_id);

create index fk_product_category_ref1_idx
    on product (category_id);

create index fk_product_city_ref1_idx
    on product (city_id);

create index fk_product_product_level_ref1_idx
    on product (product_level_id);

create index fk_product_product_status_ref1_idx
    on product (product_status_id);

create index fk_product_users1_idx
    on product (user_id);

create table product_images
(
    product_image_id int auto_increment
        constraint `PRIMARY`
        primary key,
    image_url        varchar(256) not null,
    product_id       int          not null,
    constraint fk_product_images_product1
        foreign key (product_id) references product (product_id)
);

create index fk_product_images_product1_idx
    on product_images (product_id);

create table token
(
    token    varchar(255) not null
        constraint `PRIMARY`
        primary key,
    end_date timestamp(6) not null,
    user_id  int          not null,
    constraint fk_Token_user1
        foreign key (user_id) references user (user_id)
);

create index fk_Token_user1_idx
    on token (user_id);

create index fk_users_role_ref1_idx
    on user (role_id);

create index fk_users_user_status_ref_idx
    on user (user_status_id);

create table user_rating
(
    set_user_id      int not null,
    get_user_id      int not null,
    rating_number_id int not null,
    constraint `PRIMARY`
        primary key (set_user_id, get_user_id),
    constraint fk_user_rating_rating_ref1
        foreign key (rating_number_id) references rating_ref (rating_id),
    constraint fk_user_rating_user1
        foreign key (set_user_id) references user (user_id),
    constraint fk_user_rating_user2
        foreign key (get_user_id) references user (user_id)
);

