create table if not exists btc_price
(
    id          bigint      not null auto_increment,
    external_id bigint      not null,
    symbol      varchar(45) not null,
    save_date   timestamp   not null,
    coin_price  double      not null,
    primary key (id)
);

create table if not exists eth_price
(
    id          bigint      not null auto_increment,
    external_id bigint      not null,
    symbol      varchar(45) not null,
    save_date   timestamp   not null,
    coin_price  double      not null,
    primary key (id)
);

create table if not exists sol_price
(
    id          bigint      not null auto_increment,
    external_id bigint      not null,
    symbol      varchar(45) not null,
    save_date   timestamp   not null,
    coin_price  double      not null,
    primary key (id)
);

create table if not exists coins
(
    id          bigint      not null auto_increment,
    external_id bigint      not null,
    symbol      varchar(45) not null,
    primary key (id)
);

create table if not exists user_coin_price
(
    id           bigint      not null auto_increment,
    user_name    varchar(45) not null,
    symbol       varchar(45) not null,
    date         timestamp   not null,
    member_price double      not null,
    primary key (id)
);
