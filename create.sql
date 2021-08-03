
    create table bank_account (
       id bigint not null,
        balance double precision,
        user_id_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table contact (
       id bigint not null,
        user_related_id_id bigint,
        user_relating_id_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM

    insert into hibernate_sequence values ( 1 )

    create table transaction (
       id bigint not null,
        amount double precision,
        description varchar(255),
        fees double precision,
        bank_receiver_id_id bigint,
        bank_sender_id_id bigint,
        user_receiver_id_id bigint,
        user_sender_id_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table users (
       id bigint not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=MyISAM

    alter table bank_account 
       add constraint FKns7fd6y71y4k138x14et50tmq 
       foreign key (user_id_id) 
       references users (id)

    alter table contact 
       add constraint FKtjdg14hqc34yujbc2hcn3dnxh 
       foreign key (user_related_id_id) 
       references users (id)

    alter table contact 
       add constraint FKlfkk3yaaaa44fwxncxcgc16b8 
       foreign key (user_relating_id_id) 
       references users (id)

    alter table transaction 
       add constraint FKn1qhav1h3gruot3bg9cqopa56 
       foreign key (bank_receiver_id_id) 
       references bank_account (id)

    alter table transaction 
       add constraint FKff94rc9vv978t12tb2amafwfl 
       foreign key (bank_sender_id_id) 
       references bank_account (id)

    alter table transaction 
       add constraint FKa2hjdnbtjclfs2mxe2972323r 
       foreign key (user_receiver_id_id) 
       references users (id)

    alter table transaction 
       add constraint FKbri710te8huqeu59872ts23gn 
       foreign key (user_sender_id_id) 
       references users (id)

    create table bank_account (
       id bigint not null,
        balance double precision,
        user_id_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table contact (
       id bigint not null,
        user_related_id_id bigint,
        user_relating_id_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM

    insert into hibernate_sequence values ( 1 )

    create table transaction (
       id bigint not null,
        amount double precision,
        description varchar(255),
        fees double precision,
        bank_receiver_id_id bigint,
        bank_sender_id_id bigint,
        user_receiver_id_id bigint,
        user_sender_id_id bigint,
        primary key (id)
    ) engine=MyISAM

    create table users (
       id bigint not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=MyISAM

    alter table bank_account 
       add constraint FKns7fd6y71y4k138x14et50tmq 
       foreign key (user_id_id) 
       references users (id)

    alter table contact 
       add constraint FKtjdg14hqc34yujbc2hcn3dnxh 
       foreign key (user_related_id_id) 
       references users (id)

    alter table contact 
       add constraint FKlfkk3yaaaa44fwxncxcgc16b8 
       foreign key (user_relating_id_id) 
       references users (id)

    alter table transaction 
       add constraint FKn1qhav1h3gruot3bg9cqopa56 
       foreign key (bank_receiver_id_id) 
       references bank_account (id)

    alter table transaction 
       add constraint FKff94rc9vv978t12tb2amafwfl 
       foreign key (bank_sender_id_id) 
       references bank_account (id)

    alter table transaction 
       add constraint FKa2hjdnbtjclfs2mxe2972323r 
       foreign key (user_receiver_id_id) 
       references users (id)

    alter table transaction 
       add constraint FKbri710te8huqeu59872ts23gn 
       foreign key (user_sender_id_id) 
       references users (id)
