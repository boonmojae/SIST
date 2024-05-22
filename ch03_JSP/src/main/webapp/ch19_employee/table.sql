--사원관리
create table semployee(
 num number primary key,
 id varchar2(12) unique not null,--story에 있는 데이터를 안지우면 탈퇴를 못함
 name varchar2(30) not null,
 passwd varchar2(12) not null,
 salary number(8) not null,
 job varchar2(30) not null,
 reg_date date default sysdate not null
);
create sequence semployee_seq;
--사원게시판(story가 semployee정보를 읽어오니까 sem을 먼저 만들어놔야됨)
create table story(
 snum number not null,--story의 프라이머리키
 title varchar2(150) not null,
 content clob not null,
 ip varchar2(30) not null,
 num number not null, --사원번호에 포링키제약조건
 reg_date date default sysdate not null,
 constraint story_pk primary key (snum),
 constraint story_fk foreign key (num) references semployee (num)
);
create sequence story_seq;
