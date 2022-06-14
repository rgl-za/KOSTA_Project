
-- 데이터베이스 생성 --
create user cobuy identified by cobuy;
grant resource, connect to cobuy;
commit;

-- 테이블 생성 --
DROP TABLE users;

create table users(
    userId varchar2(50) not null, -- 아이디 --
    userName varchar2(50) not null, -- 이름 --
    password varchar2(50) not null, -- 비밀번호 --
    phone varchar2(50) not null, -- 핸드폰 번호 --
    postNum varchar2(10) not null, -- 우편 번호 --
    address varchar2(100) not null, -- 주소 --
    nickName varchar2(50) not null, -- 닉네임 --
    gender varchar(10) not null, -- 성별 --
    birth date not null, -- 생년월일 --
    
    constraint pk_userId primary key(userId) -- 아이디 pk 제약조건 --
);

DROP TABLE userAccount;

create table userAccount(
    historyNum number not null, -- 거래 내역 번호 --
    userId varchar2(50) not null, -- 아이디 --
    pnum number, -- 글 고유 번호 --
    dealName varchar2(100) not null, -- 거래 내역 이름 --
    money number not null, -- 거래 금액 --
    content varchar2(100) not null, -- 거래 내용 --
    totalPoint number, -- 잔여 포인트 --
    regDate date not null, -- 날짜 --
    
    constraint pk_historyNum primary key(historyNum), -- 거래 내역 번호 pk 설정 -- 
    constraint fk_userId foreign key(userId) references users(userId) -- 아이디 fk 설정 --
);

DROP TABLE chatRoom;

create table chatRoom(
    pnum number not null, -- 글 고유 번호 --
    cnum number not null, -- 채팅 고유 번호 --
    
    constraint pk_cnum primary key(cnum) -- 채팅 고유 번호 pk 설정 --
);

DROP TABLE chat;

create table chat(
    cnum number not null, -- 채팅 고유 번호 --
    userId varchar2(50) not null, -- 아이디 --
    chatContent varchar2(500), -- 채팅 내용 --
    chatDate date, -- 채팅 날짜 --
    
    constraint fk_cnum foreign key(cnum) references chatRoom(cnum) -- 채팅 고유 번호 fk 설정 --
);ㅂ

DROP TABLE category;

create table category(
    catNum number not null, -- 카테고리 고유 번호 --
    catIndex varchar2(50) not null, -- 카테고리 인덱스 --
    
    constraint pk_catNum primary key(catNum) -- 카테고리 고유 번호 pk 설정 --
);

DROP TABLE post;

create table post(
    pnum number not null, -- 게시글 고유 번호 --
    catNum number not null, -- 카테고리 고유 번호 --
    leaderId varchar2(50) not null, -- 방장 아이디 --
    title varchar2(100) not null, -- 게시글 제목 --
    content varchar2(3000) not null, -- 게시글 내용 -- 
    photo varchar2(500) not null, -- 제품 사진 --
    link varchar2(1000) not null, -- 제품 링크 --
    price number not null, -- 제품 원가 --
    postNum number not null, -- 우편번호 --
    dealAddress varchar2(500) not null, -- 거래 주소 --
    maxPeople number, -- 최대 인원 --
    minPeople number, -- 최소 인원 --
    accountPost varchar2(50), -- 게시글 전용 계좌 --
    uploadDate date, -- 게시글 업로드 날짜 --
    endDate date not null, -- 거래 마감 날짜 --
    delete_yn varchar2(1)
    finaldate date
    constraint pk_pnum primary key(pnum), -- 게시글 고유 번호 pk 설정 --
    constraint fk_catNum foreign key(catNum) references category(catNum) -- 카테고리 고유 번호 fk 설정 --
    
);

DROP TABLE heart;

create table heart(
    lnum number not null, -- 찜 고유 번호 --
    pnum number not null, -- 게시글 고유 번호 --
    userId varchar2(50) not null, -- 아이디 --
    
    constraint pk_lnum primary key(lnum), -- 찜 고유 번호 pk 설정 --
    constraint fk_pnum foreign key(pnum) references post(pnum) -- 게시글 고유 번호 fk 설정 --
);

DROP TABLE teamMember;

create table teamMember(
    memberNum number not null,
    pnum number not null, -- 게시글 고유 번호 --
    userId varchar2(50) not null, -- 아이디 --
    
    constraint pk_memberNum primary key(memberNum),
    constraint pnum_fk foreign key(pnum) references post(pnum) -- 게시글 고유 번호 fk 설정 --
);

-- 시퀀스 객체 생성 --
DROP SEQUENCE seq_history_no;

CREATE SEQUENCE seq_history_no
INCREMENT BY 1
START WITH 1 ;

drop sequence seq_like_no;

create sequence seq_like_no
increment by 1
start with 1;

drop sequence seq_chat_no;

create sequence seq_chat_no
increment by 1
start with 1;

drop sequence seq_post_no;

create sequence seq_post_no
increment by 1
start with 1;

drop sequence seq_member_no;

create sequence seq_member_no
increment by 1
start with 1;


--수정 쿼리--
alter table post add finalDate date not null;
alter table post modify finalDate null;

create table comments(
    cnum number not null,
    pnum number not null,
    comments varchar2(1000) not null,
    writer varchar2(50) not null,
    regDate date not null,
    
    constraint pk_cnum primary key(cnum),
    constraint pnum_fk2 foreign key(pnum) references post(pnum)
);

drop sequence seq_comments_no;

create sequence seq_comments_no
increment by 1
start with 1;