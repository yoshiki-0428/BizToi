create table book
(
	ID int auto_increment comment '本ID'
		primary key,
	ISBN varchar(13) null comment 'ISBN番号',
	PICTURE_URL varchar(255) null comment '写真参照URL',
	TITLE varchar(255) null comment '本タイトル',
	DETAIL varchar(255) null comment '本詳細',
	LINK_URL varchar(255) null comment 'リンクURL'
)
charset=utf8;

create table user
(
	ID varchar(128) not null comment 'USER ID'
		primary key,
	ID_TOKEN text not null comment '一時的アクセスのためのトークン',
	ACCESS_TOKEN text not null comment '一時的アクセスのためのトークン',
	REFRESH_TOKEN text not null comment 'アクセストークン更新のためのトークン',
	INSERTED timestamp default current_timestamp,
    MODIFIED timestamp default current_timestamp not null
)
charset=utf8;

create table toi
(
	ID int auto_increment comment '問題(Toi)のID'
		primary key,
	USER_ID varchar(128) not null comment '所有者',
	BOOK_ID int null comment '参考本',
	PICTURE_URL varchar(255) null comment '問題に画像を付ける場合',
	TITLE varchar(255) null comment '問題集タイトル',
	DETAIL varchar(255) null comment '問題集詳細文',
	CONTENT varchar(255) null comment '問題集内容',
	PUBLIC_FLG varchar(1) default '1' null comment '公開フラグ 0:非公開, 1:公開',
    INSERTED timestamp default current_timestamp,
    MODIFIED timestamp default current_timestamp not null,
	constraint toi_user_ID_fk
		foreign key (USER_ID) references user (ID),
    constraint toi_book_ID_fk
        foreign key (BOOK_ID) references book (ID)
)
charset=utf8;

create table question
(
	ID int auto_increment comment '質問ID'
		primary key,
	TOI_ID int not null comment '問題集と紐づくID',
	TITLE varchar(255) null comment '質問タイトル',
	DETAIL varchar(255) null comment '詳細',
	REQUIRED varchar(1) default '0' null comment '必須フラグ 0:非必須, 1:必須',
	ORDER_ID int null comment '順番ID',
    INSERTED timestamp default current_timestamp,
    MODIFIED timestamp default current_timestamp not null,
	constraint question_toi_ID_fk
		foreign key (TOI_ID) references toi (ID)
)
charset=utf8;

create table answer_head
(
    ID int auto_increment comment '回答ID'
        primary key,
    USER_ID varchar(128) not null comment '所有者',
    TOI_ID int not null comment '問題集と紐づくID',
    PUBLISH_FLG bit(1) not null default b'1' comment '公開フラグ',
    INSERTED timestamp default current_timestamp,
    MODIFIED timestamp default current_timestamp not null,
    constraint answer_user_ID_fk
        foreign key (USER_ID) references user (ID),
    constraint answer_head_toi_ID_fk
        foreign key (TOI_ID) references toi (ID)
)
charset=utf8;

create table answer
(
	ID int auto_increment comment '回答ID'
		primary key,
    ANSWER_HEAD_ID int not null,
	QUESTION_ID int not null comment '質問と紐づくID',
    PICTURE_URL varchar(255) null comment '問題に画像を付ける場合',
	ANSWER text null comment '回答内容',
    INSERTED timestamp default current_timestamp,
    MODIFIED timestamp default current_timestamp not null,
    constraint answer_head_question_ID_fk
        foreign key (ANSWER_HEAD_ID) references answer_head (ID),
    constraint answer_question_ID_fk
		foreign key (QUESTION_ID) references question (ID)
)
charset=utf8;

create table talk
(
	ID int auto_increment comment '本ID'
		primary key,
	TOI_ID int not null comment '問題集と紐づくID',
	USER_ID varchar(128) not null comment '所有者',
	TALK_ID int null comment '返信した場合、紐付けをする',
	COMMENT varchar(255) null comment 'コメント',
    INSERTED timestamp default current_timestamp,
    MODIFIED timestamp default current_timestamp not null,
	constraint talk_talk_ID_fk
		foreign key (TALK_ID) references talk (ID),
	constraint talk_toi_ID_fk
		foreign key (TOI_ID) references toi (ID)
)
charset=utf8;
