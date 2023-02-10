--==============================
-- mybatis 
--==============================
-- web계정 사용.

create table student (
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date,
    deleted_at date,
    constraint pk_student_no primary key(no)
);

create sequence seq_student_no;

insert into 
    student(no, name, tel)
values(
    seq_student_no.nextval, '홍길동', '01012341234'
);
  
insert into 
    student(no, name, tel)
values(
    seq_student_no.nextval, '신사임당', '01055553333'
);    

insert into 
    student(no, name, tel)
values(
    seq_student_no.nextval, '이순신', '01077773333'
);

select * from student;

-- 수정
update
    student
set
    tel = '01033334444',
    updated_at = sysdate
where
    no = 1;
    
-- 삭제
update
    student
set 
    deleted_at = sysdate
where
    no = 3;

commit;

-- 일반회원 조회
select * from student where deleted_at is null;
select * from student where deleted_at is null and tel like '%33%';
select * from student where deleted_at is null and name = '홍길동';


select * from student;
delete from student where no = 21;

select * from student order by no asc;
update student set deleted_at = null where no = 1;

commit;


--=================================================
-- sh계정 employee | department | job | sal_grade
--=================================================
-- sh계정에서 web에 테이블 권한 부여
grant all on sh.employee to web;
grant all on sh.department to web;
grant all on sh.job to web;
grant all on sh.sal_grade to web;

select * from sh.employee;
select * from sh.department;
select * from sh.job;
select * from sh.sal_grade;

-- 관리자가 web에 create synonym 권한 부여 (system)
grant create synonym to web;

-- 동의어(synonym) 객체 지정 : 특정 사용자, 특정 테이블에 대한 별칭 지정
create synonym emp for sh.employee;
create synonym dept for sh.department;
create synonym job for sh.job;
create synonym sal_grade for sh.sal_grade;

select * from emp;
select * from dept;
select * from job;
select * from sal_grade;

-- 사번, 사원명, 이메일, 전화번호 검색 && 성별검색
select
    *
from
    (
        select
            e.*,
            decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender
        from
            emp e
    )
where
    quit_yn = 'N'
    and
    emp_name like '%이%'
    and
    gender = '남';

select * from emp where hire_date > '2000/01/01';