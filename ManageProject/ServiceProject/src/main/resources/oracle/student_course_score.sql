select round(123.456), round(123.456,2), round(123.456,-1), ceil(123.456), floor(123.456) from dual;

--取余,次方,平方根
select mod(5,2), power(2,3), sqrt(9) from dual;

select upper('a'), lower('B'), initcap('ab'), substr('abcde',2,3), substr('abcde',-3), length('abc') from dual;

select 'a'||'b', concat('a','b'), trim('a' from 'aabaa'), ltrim('aabaa','a'), rtrim('aabaa','a') from dual;

--第一次出现bc的位置
select replace('abcabc','b',''), instr('abcabc','bc') from dual;

select to_date('25/12/2016 18:16:00','dd/mm/yyyy hh24:mi:ss'), to_char(sysdate,'hh24:mi') from dual;

--转成字符串会四舍五入
select to_char(1234.567,'$999,999.99'), to_number('$1,234.456','$9,999.999') from dual;

create table test_data
(
name varchar(10),
course varchar(20),
score number(3)
);
insert into test_data (name, course, score)values ('aaa', '语文', 60);
insert into test_data (name, course, score)values ('aaa', '语文', 70);
insert into test_data (name, course, score)values ('aaa', '数学', 80);
insert into test_data (name, course, score)values ('bbb', '外语', 77);
insert into test_data (name, course, score)values ('bbb', '数学', 80);
insert into test_data (name, course, score)values ('bbb', '数学', 85);
insert into test_data (name, course, score)values ('ccc', '语文', 77);
insert into test_data (name, course, score)values ('ccc', '数学', 80);
insert into test_data (name, course, score)values ('ccc', '外语', 80);
insert into test_data (name, course, score)values ('ddd', '外语', 60);
insert into test_data (name, course, score)values ('ddd', '外语', 70);
insert into test_data (name, course, score)values ('ddd', '外语', 80);

select course 课程, to_char(wm_concat(distinct name)) 考试的学生 from test_data group by course;

--rownum只能用小于和小于等于
select score, tmp from
(
select score, rownum tmp from
(
select score, rownum from test_data order by score desc --其他情况不要在子查询中使用排序
)
where rownum <=3
)
where tmp > 1

select name, count(distinct course) from test_data group by name having count(distinct course) = 3;

select t.* from test_data t,
(
select max(score) score, course from test_data group by course
) tt
where t.course = tt.course and t.score = tt.score;

/*
非相关子查询:是独立于外部查询的子查询,子查询总共执行一次执行完毕后将值传递给外部查询（自内而外）
相关子查询:执行依赖于外部查询的数据,外部查询每扫描一行都要执行一次子查询（自外而内）
一般情况子查询换成表连接效率更高
*/
select * from test_data t where score = (select max(score) from test_data where course = t.course);
select * from test_data t where not exists(select * from test_data where course = t.course and score > t.score);
--不加条件的数据查询按照 n*n 计算,本例12*12(笛卡尔积)
select * from test_data t, test_data tt where t.course = tt.course order by t.course, t.name, t.score;

select course, avg(score) from test_data where course='语文' group by course; --效率高
select course, avg(score) from test_data group by course having course='语文';
select name, course, sum(score) from test_data group by rollup(name, course); --统计报表

/*
拼接sql:
where 1=1 and ...
where 1=2 or ...

查询id包含连续3个0以上的记录:
select * from departure_list_import where REGEXP_LIKE(id,'0{3,}')

CHAR   NCHAR 使用unicode存储中文,相对长度是前者一半
VARCHAR2 NVARCHAR2 使用unicode存储中文,相对长度是前者一半
NUMBER(有效数字位数,小数位数)

truncate删除表数据比delete效率高

alter table onetable modify column_name datatype not null;
alter table onetable add new_column varchar2(10) default 'abc'; 会刷新存量数据并产生表级锁
分两步执行:
alter table onetable add new_column varchar2(10);
alter table onetable modify new_column varchar2(10) default 'abc' ;

创建表级: constraint 自定约束名 约束关键字(列名或表达式)
外键可为null,唯一约束只能有一个null
查看约束表user_constraints
SQL不等符: username <> 'aaa', not(username = 'aaa'), is not null
val not in (10, 20, null)相当于val!=10 and val!=20 and val!=null(错误永远为假)
case column_name when value then result,...[else result] end
case when column_name >= value then result,...[else result] end
decode(column_name, value, result,...[default_value])

分组函数会忽略空值, nvl(null, 0)
> any(select t.value from t)相当于>(select min(value) from t)
> all(select t.value from t)相当于>(select max(value) from t)
*/

--打开命令窗口
set serveroutput on
desc dbms_output.put_line
accept input prompt('input a number');
declare
    var1 varchar2(500);
    var2 boolean := true;
    var3 number(5,2);
    var4 date;
    var5 test_data.name%type;
    var6 test_data%rowtype;
    var7 number := &input;
begin
    if var7 = 0 then
        dbms_output.put_line('退出');
    elsif var7 = 1 then --注意elsif写法
        var3 := 1.23;
        var4 := sysdate;
        select name into var5 from test_data where score=85;
        select * into var6 from test_data where score=85;
        while var7 < 3 loop
            var7 := var7 + 1;
        end loop;
        for var7 in 101..103 loop
            dbms_output.put_line(var7);
        end loop;
        var1 := var3  || ' ' || var4  || ' ' || var5 || ' ' || var6.course;
        dbms_output.put_line(var1 || ' ' || var7);
    end if;
end;
/

/*
光标属性: %found, %notfound, %isopen, %rowcount已获取的行数非总行数
默认只能打开300个光标
*/
declare
    cursor namescore is select name,avg(score) score from test_data group by name;
    eachName test_data.name%type;
    avgScore test_data.score%type;
begin
    open namescore;
        loop
            fetch namescore into eachName,avgScore;
            exit when namescore%notfound;
            if avgScore < 76 then
                update test_data set score = score + 5 where name = eachName;
            end if;
            dbms_output.put_line(eachName || ' ' || avgScore);
        end loop;
    close namescore;
    --commit; --如果有操作数据的语句一定要提交
end;
/

declare
    cursor courseinfo(course_name varchar2) is select * from test_data where course = course_name;
    onerow test_data%rowtype;
begin
    open courseinfo('外语');
        loop
            fetch courseinfo into onerow;
            exit when courseinfo%notfound;
            dbms_output.put_line(courseinfo%rowcount);
        end loop;
    close courseinfo;
end;
/

select * from user_sequences where sequence_name='SEQ_V2ID';
