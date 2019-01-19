select * from v$instance
select * from database_properties
select * from nls_database_parameters
select userenv('language') from dual
select * from V$NLS_PARAMETERS

-- 配置oracle客户端
NLS_LANG
AMERICAN_AMERICA.AL32UTF8
TNS_ADMIN
D:\ProgramFiles\instantclient_11_2
path添加D:\ProgramFiles\instantclient_11_2;


select performance_price_sequence.nextval as nextvalue from dual
update campaign_price_code cpc set cpc.id=campaign_price_code_sequence.nextval where cpc.id=-1


update contact c set c.last_name='O''Brien' where c.contact_id=50367754 and field like ?
-- setParameter(1, "%" + parameter + "%");


select to_date('2013-07-20 23:59:59','yyyy-MM-dd hh24:mi:ss') from dual
select sysdate - interval '1' hour from dual
select sysdate - interval '1' day from dual -- select sysdate - 0.5 from dual 默认是减去day
select sysdate - interval '1' month from dual 
select sysdate - interval '1' year from dual

performance_datetime > (sysdate + time_zone_offset/60/24) -- performance.time_zone_offset是分钟，除的结果是相对服务器的偏移天数

select sum(case when t.flag='x' then t.value else 0 end) as revenue, count(case when t.flag='x' then t.id end) as revenueCount,
to_char(t.tranDate, 'yyyy-MM-dd') from t group by to_char(t.tranDate, 'yyyy-MM-dd') -- 按天统计总和与个数


COUNTRY_ISO3 CHAR(3)
使用JPA时：select trim(country_iso3) from dual

-- Create table
create table performance_price_range
(
  id number(19,0) not null,
  perf_sales_schedule_id   number(19,0) not null,
  sales_channel_id         number(19,0) default 1 not null,
  max_price_id             number(19,0) not null,
  min_price_id             number(19,0) not null
);

-- Create/Recreate primary, unique and foreign key constraints
alter table performance_price_range
  add constraint perf_price_code_range_pk primary key (ID);
alter table performance_price_range
  add constraint perf_sales_schedule_fk foreign key (PERF_SALES_SCHEDULE_ID)
  references performance_sales_schedule (SCHEDULE_ID);
alter table performance_price_range
  add constraint sales_channel_fk foreign key (SALES_CHANNEL_ID)
  references sales_channel (CHANNEL_ID);
alter table performance_price_range
  add constraint max_price_id_fk foreign key (MAX_PRICE_ID)
  references performance_price (PERFORMANCE_PRICE_ID);
alter table performance_price_range
  add constraint min_price_id_fk foreign key (MIN_PRICE_ID)
  references performance_price (PERFORMANCE_PRICE_ID);

create sequence performance_price_range_seq
minvalue 1
maxvalue 9223372036854775807
start with 1
increment by 1
nocache;

create or replace procedure hl_test_lob(tname varchar2, val out number)
as
cnt number;
begin
select count(*) into cnt from user_tables ut where ut.TABLE_NAME=tname;
if cnt > 0 then
    execute immediate 'drop table basic_lob_table';
end if;
val := 7;
end;

1、一个表中只能包含一个 LONG 类型的列
2、不能索引LONG类型列
3、不能将含有LONG类型列的表作聚簇
4、不能在SQL*Plus中将LONG类型列的数值插入到另一个表格中,如 insert into ...select
5、不能在SQL*Plus中通过查询其他表的方式来创建LONG类型列,如 create table as select
6、不能对LONG类型列加约束条件（NULL, NOT NULL, DEFAULT 除外），如：关键字列(PRIMARY KEY)不能是 LONG 数据类型
7、LONG类型列不能用在Select的以下子句中：where, group by, order by，以及带有distinct的select语句中
8、LONG类型列不能用于分布查询


select price_code_id,
       price_code_name,
       price,
       count(ticket_id) as num_tix,
       iso_code,
       round(sum(price), 2) as price_revenue,
       round(sum(fee_paid), 2) as fee_revenue
  from ticket t, currency c
 where t.creation_date > to_date('2013-01-01', 'yyyy-MM-dd')
   and t.creation_date < to_date('2013-09-30', 'yyyy-MM-dd')
   and t.venue_id_ref = 5072
   and t.currency_id = c.currency_id
   and t.status in ('ISSUED', 'REDEEMED')
 group by price_code_id, price_code_name, price, iso_code
union all
-- 以price_code_id, price_code_name小计总数
select price_code_id,
       (price_code_name || 'z') as price_code_name,
       null,
       count(ticket_id) as num_tix,
       '',
       round(sum(price), 2) as price_revenue,
       round(sum(fee_paid), 2) as fee_revenue
  from ticket t, currency c
 where t.creation_date > to_date('2013-01-01', 'yyyy-MM-dd')
   and t.creation_date < to_date('2013-09-30', 'yyyy-MM-dd')
   and t.venue_id_ref = 5072
   and t.currency_id = c.currency_id
   and t.status in ('ISSUED', 'REDEEMED')
 group by price_code_id, price_code_name
 order by price_code_name, num_tix   -- union all完成后统一order by
-- 输出结果参考unionall_rollup.csv
select price_code_id,
       price_code_name,
       price,
       count(ticket_id) as num_tix,
       iso_code,
       round(sum(price), 2) as price_revenue,
       round(sum(fee_paid), 2) as fee_revenue
  from ticket t, currency c
 where t.creation_date > to_date('2013-01-01', 'yyyy-MM-dd')
   and t.creation_date < to_date('2013-09-30', 'yyyy-MM-dd')
   and t.venue_id_ref = 5072
   and t.currency_id = c.currency_id
   and t.status in ('ISSUED', 'REDEEMED')
 group by rollup(price_code_name, (price_code_id, price, iso_code))
 order by price_code_name, num_tix -- 如果price_code_name和num_tix相同，排序添加", price"修正统计项排序问题
-- 输出结果参考unionall_rollup.csv

create table test_calculate
(
    label_name varchar2(50),
    order_id number(19,0),
    amount number(19,0)
);
insert into test_calculate values('Base Ticket Price',10001,1);
insert into test_calculate values('Conveinance Fee %',10001,2);
insert into test_calculate values('Promoter Fee',10001,3);
insert into test_calculate values('Base Ticket Price',10002,4);
insert into test_calculate values('Conveinance Fee %',10002,5);
insert into test_calculate values('Promoter Fee',10002,6);
insert into test_calculate values('_NonComponent Ticket Fee',10002,7);
insert into test_calculate values('Artist Fee',10002,8);
insert into test_calculate values('Base Ticket Price',10003,9);
insert into test_calculate values('Conveinance Fee %',10003,10);
-- 把行数据转换成列数据
select order_id,sum(amount),sum(ticket_price) as ticket_price,sum(convenience_fee_per) as convenience_fee_per,sum(promoter_fee) as promoter_fee,
sum(noncomponent_ticket_fee) as noncomponent_ticket_fee,sum(artist_fee) as artist_fee from
(select order_id,label_name,sum(amount) as amount,sum(decode(trim(label_name),'Base Ticket Price',amount,0)) as ticket_price,sum(decode(trim(label_name),'Conveinance Fee %',amount,0)) as convenience_fee_per,
sum(decode(trim(label_name),'Promoter Fee',amount,0)) as promoter_fee,sum(decode(trim(label_name),'_NonComponent Ticket Fee',amount,0)) as noncomponent_ticket_fee,
sum(decode(trim(label_name),'Artist Fee',amount,0)) as artist_fee from test_calculate group by order_id, label_name order by order_id, label_name) group by order_id;

--测试外连接
create table table1
(
    id number(19,0),
    name varchar2(50)
);
create table table2
(
    id number(19,0),
    name varchar2(50),
    typeName varchar2(50)
);
create table table3
(
    id number(19,0),
    typeName varchar2(50)
);
insert into table1 values(1,'a');
insert into table1 values(2,'b');
insert into table1 values(3,'c');
insert into table1 values(4,'d');
insert into table1 values(5,'e');
insert into table2 values(1,'a','T-1');
insert into table2 values(2,'b','T-2');
insert into table2 values(3,'c','T-3');
insert into table3 values(1,'T-3');
1. (+)操作符只能出现在where子句中，并且不能与outer join语法同时使用
2. 当使用(+)操作符执行外连接时，如果在where子句中包含有多个条件，则必须在所有条件中都包含(+)操作符
3. (+)操作符只适用于列，而不能用在表达式上
4. (+)操作符不能与or和in操作符一起使用
5. (+)操作符只能用于实现左外连接和右外连接，而不能用于实现完全外连接
select a.id,b.id,c.id from table1 a left join table2 b on a.name=b.name left join table3 c on b.typeName=c.typeName order by a.id;
select a.id,b.id,c.id from table1 a,table2 b,table3 c where TRIM(UPPER(a.name))=TRIM(UPPER(b.name(+))) and b.typeName=c.typeName(+) and a.id in(1,2,3,4,5) order by a.id;


-- 在多台服务器情况下只能用数据库控制并发访问：
-- (1)使用sequence每次获取+1的数据行
-- (2)取出数据行的状态和更新时的状态要一致
select t.ticket_id, t.status from ticket t where t.status='OPEN';
update ticket t set t.status='ISSUED' where t.status='OPEN' and t.ticket_id=12345;


-- for big table the following would lock up the db
-- alter table performance_price_code add enable_custom_barcode number(1,0) default 0;
-- 1添加空字段，2用query设置字段的值，3给字段添加默认值


find available price codes 对于数据量大的表替换Sql:
select sum(case when status=0 then 1 else 0 end), perf_price_code_id from perf_custom_barcode where 
perf_price_code_id in (7133611,7158334,7164708) group by perf_price_code_id having sum(case when status=0 then 1 else 0 end) > 0;

select 7133611 from dual where exists (select 1 from perf_custom_barcode where perf_price_code_id = 7133611 and status = 0)
union all
select 7158334 from dual where exists (select 1 from perf_custom_barcode where perf_price_code_id = 7158334 and status = 0)
union all
select 7164708 from dual where exists (select 1 from perf_custom_barcode where perf_price_code_id = 7164708 and status = 0);

convert number to double:
cast(sum(soldprice - refundedprice) as number(19,2)) as totalprice