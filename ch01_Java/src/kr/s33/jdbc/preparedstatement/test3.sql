CREATE TABLE test3(
 num NUMBER PRIMARY KEY,
 title VARCHAR2(60) NOT NULL,
 name VARCHAR2(30) NOT NULL,
 memo VARCHAR2(4000) NOT NULL,
 email VARCHAR2(30),
 reg_date DATE NOT NULL
 );
 CREATE SEQUENCE test3_seq;--num에 사용하려고 만듦