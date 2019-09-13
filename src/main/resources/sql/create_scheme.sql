/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     28.08.2019 13:24:23                          */
/*==============================================================*/


alter table Expos
    drop foreign key FK_EXPOS_RELATIONS_THEMES;

alter table Expos
    drop foreign key FK_EXPOS_RELATIONS_SHOWROOM;

alter table Payments
    drop foreign key FK_PAYMENTS_RELATIONS_TICKETS;

alter table Tickets
    drop foreign key FK_TICKETS_RELATIONS_USERS;

alter table Tickets
    drop foreign key FK_TICKETS_RELATIONS_EXPOS;

alter table Tickets
    drop foreign key FK_TICKETS_RELATIONS_PAYMENTS;

alter table Users
    drop foreign key FK_USERS_RELATIONS_ROLES;


alter table Expos
    drop foreign key FK_EXPOS_RELATIONS_THEMES;

alter table Expos
    drop foreign key FK_EXPOS_RELATIONS_SHOWROOM;

drop table if exists Expos;


alter table Payments
    drop foreign key FK_PAYMENTS_RELATIONS_TICKETS;

drop table if exists Payments;

drop table if exists Roles;

drop table if exists Showrooms;

drop table if exists Themes;


alter table Tickets
    drop foreign key FK_TICKETS_RELATIONS_USERS;

alter table Tickets
    drop foreign key FK_TICKETS_RELATIONS_EXPOS;

alter table Tickets
    drop foreign key FK_TICKETS_RELATIONS_PAYMENTS;

drop table if exists Tickets;


alter table Users
    drop foreign key FK_USERS_RELATIONS_ROLES;

drop table if exists Users;

/*==============================================================*/
/* Table: Expos                                                 */
/*==============================================================*/
create table Expos
(
    expo_id                   int(11)      not null auto_increment comment '',
    showroom_id               int(11) comment '',
    theme_id                  int(11) comment '',
    expo_showroom_id          int          not null comment '',
    expo_theme_id             char(32)     not null comment '',
    expo_date_start           datetime     not null comment '',
    expo_date_end             datetime     not null comment '',
    expo_ticket_price         float(11, 3) not null comment '',
    expo_total_tickets_amount int          not null comment '',
    expo_info                 text         not null comment '',
    primary key (expo_id)
);

/*==============================================================*/
/* Table: Payments                                              */
/*==============================================================*/
create table Payments
(
    payment_id     int(11)      not null auto_increment comment '',
    ticket_id      int(11) comment '',
    payment_value  float(11, 3) not null comment '',
    payment_method char(32)     not null comment '',
    payment_status char(32)     not null comment '',
    primary key (payment_id)
);

/*==============================================================*/
/* Table: Roles                                                 */
/*==============================================================*/
create table Roles
(
    role_id   int(11)  not null auto_increment comment '',
    role_name char(32) not null comment '',
    primary key (role_id)
);

/*==============================================================*/
/* Table: Showrooms                                             */
/*==============================================================*/
create table Showrooms
(
    showroom_id   int(11)  not null auto_increment comment '',
    showroom_name char(32) not null comment '',
    showroom_info text     not null comment '',
    primary key (showroom_id)
);

/*==============================================================*/
/* Table: Themes                                                */
/*==============================================================*/
create table Themes
(
    theme_id   int(11)  not null auto_increment comment '',
    theme_name char(32) not null comment '',
    primary key (theme_id)
);

/*==============================================================*/
/* Table: Tickets                                               */
/*==============================================================*/
create table Tickets
(
    ticket_id         int(11)  not null auto_increment comment '',
    expo_id           int(11) comment '',
    user_id           int(11) comment '',
    payment_id        int(11) comment '',
    ticket_expo_id    int      not null comment '',
    ticket_user_id    int      not null comment '',
    ticket_payment_id int      not null comment '',
    ticket_date_time  datetime not null comment '',
    ticket_amount     int      not null comment '',
    ticket_info       text     not null comment '',
    primary key (ticket_id)
);

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
    user_id       int(11)  not null auto_increment comment '',
    role_id       int(11) comment '',
    user_role_id  int      not null comment '',
    user_name     char(32) not null comment '',
    user_email    char(32) not null comment '',
    user_password char(32) not null comment '',
    primary key (user_id)
);

alter table Expos
    add constraint FK_EXPOS_RELATIONS_THEMES foreign key (theme_id)
        references Themes (theme_id) on delete restrict on update restrict;

alter table Expos
    add constraint FK_EXPOS_RELATIONS_SHOWROOM foreign key (showroom_id)
        references Showrooms (showroom_id) on delete restrict on update restrict;

alter table Payments
    add constraint FK_PAYMENTS_RELATIONS_TICKETS foreign key (ticket_id)
        references Tickets (ticket_id) on delete restrict on update restrict;

alter table Tickets
    add constraint FK_TICKETS_RELATIONS_USERS foreign key (user_id)
        references Users (user_id) on delete restrict on update restrict;

alter table Tickets
    add constraint FK_TICKETS_RELATIONS_EXPOS foreign key (expo_id)
        references Expos (expo_id) on delete restrict on update restrict;

alter table Tickets
    add constraint FK_TICKETS_RELATIONS_PAYMENTS foreign key (payment_id)
        references Payments (payment_id) on delete restrict on update restrict;

alter table Users
    add constraint FK_USERS_RELATIONS_ROLES foreign key (role_id)
        references Roles (role_id) on delete restrict on update restrict;


