create database javalon3blog;
use javalon3blog;

CREATE TABLE `users` (
                         `id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         `login` VARCHAR(50) NOT NULL UNIQUE,
                         `password` VARCHAR(250) NOT NULL,
                         `token` VARCHAR(50),
                         `active` BOOLEAN NOT NULL DEFAULT false
);

insert into `users` (`login`, `password`, `active`)
values ('user1', '$2a$12$fOKsMr93FPQhDnTEyZcXj.CHsssUDHZtCNy1e1RUsdEV/GTbzAc5W', true),
       ('user2', '$2a$12$PBNTmg0JxqZw.6rwyttkXOumi1XME0EuU2hsGutnzcC./cMfObjSO', true);
