Create table sup_manage (sup_id int primary key,sup_name varchar(40),sup_addr varchar(40),sup_cont_no varchar(10));

 insert into sup_manage values(101,'SHARAD SHINDE','64/55 HADAPSAR',8651472569);

postgres=# \d sup_manage;
                      Table "public.sup_manage"
   Column    |         Type          | Collation | Nullable | Default 
-------------+-----------------------+-----------+----------+---------
 sup_id      | integer               |           | not null | 
 sup_name    | character varying(40) |           |          | 
 sup_addr    | character varying(40) |           |          | 
 sup_cont_no | character(10)         |           |          | 
Indexes:
    "sup_manage_pkey" PRIMARY KEY, btree (sup_id)
Referenced by:
    TABLE "daily_collection_details" CONSTRAINT "daily_collection_details_sup_id_fkey" FOREIGN KEY (sup_id) REFERENCES sup_manage(sup_id)

postgres=# select * from sup_manage;
 sup_id |    sup_name    |       sup_addr        | sup_cont_no 
--------+----------------+-----------------------+-------------
    102 | VIVEK JAGTAP   | PALAKHITAL SASWAD     | 7536985412
    103 | MAHESH PATIL   | PATIL NAGAR PURANDAR  | 8574965214
    101 | SHARAD NIKAM   | AMBEGAV PUNE          | 9604528768
    104 | RAJESH SHELAR  | SHELAR NAGAR PURANDAR | 8745857458
    105 | RAM TOPE       | KHADAKWASLA PUNE      | 7755859685

(5 rows)

======================================================================================================


Create table prod_manage (prod_id int primary key,prod_name varchar(40),prod_type varchar(40),prod_rate float,prod_exp_date char(10));

insert into prod_manage values(201,'BUFFALO MILK','MILK',65,'30/05/2021');

postgres=# \d prod_manage;
                       Table "public.prod_manage"
    Column     |         Type          | Collation | Nullable | Default 
---------------+-----------------------+-----------+----------+---------
 prod_id       | integer               |           | not null | 
 prod_name     | character varying(40) |           |          | 
 prod_type     | character varying(40) |           |          | 
 prod_rate     | double precision      |           |          | 
 prod_exp_date | character(10)         |           |          | 
Indexes:
    "prod_manage_pkey" PRIMARY KEY, btree (prod_id)

postgres=# select * from prod_manage;
 prod_id |     prod_name      | prod_type | prod_rate | prod_exp_date 
---------+--------------------+-----------+-----------+---------------
     201 | BUFFALO MILK 1LTR  | MILK      |        58 | 16-06-2021
     202 | BUFFALO MILK 500ML | MILK      |        29 | 16-06-2021
     203 | COW MILK 1LTR      | MILK      |        54 | 16-06-2021
     204 | COW MILK 500ML     | MILK      |        27 | 16-06-2021
     205 | A2 MILK 1LTR       | MILK      |       100 | 16-06-2021
     206 | A2 MILK 500ML      | MILK      |        50 | 16-06-2021
(6 rows)

======================================================================================================

Create table add_user (username varchar(20) primary key,password varchar(10));

insert into add_user values('admin','admin123');

postgres=# \d add_user;
                      Table "public.add_user"
  Column  |         Type          | Collation | Nullable | Default 
----------+-----------------------+-----------+----------+---------
 username | character varying(20) |           | not null | 
 password | character varying(10) |           |          | 
Indexes:
    "add_user_pkey" PRIMARY KEY, btree (username)


postgres=# select * from add_user;
  username  |  password  
------------+------------
 admin1     | admin123
 admin      | admin12345
 prathamesh | 12345
(3 rows)

======================================================================================================


Create table milktype(type_id int primary key,type_name varchar(30),milk_rate float);
insert into milktype values(1,'BUFFALO MILK',60);

 \d milktype;
                      Table "public.milktype"
  Column   |         Type          | Collation | Nullable | Default 
-----------+-----------------------+-----------+----------+---------
 type_id   | integer               |           | not null | 
 type_name | character varying(30) |           |          | 
 milk_rate | double precision      |           |          | 
Indexes:
    "milktype_pkey" PRIMARY KEY, btree (type_id)
Referenced by:
    TABLE "daily_collection_details" CONSTRAINT "daily_collection_details_type_id_fkey" FOREIGN KEY (type_id) REFERENCES milktype(type_id)


postgres=# select * from milktype;
 type_id |  type_name   | milk_rate 
---------+--------------+-----------
       3 | A2           |        80
       1 | BUFFALO MILK |        55
       2 | COW MILK     |        48
       4 | SHEAP MILK   |        75
(4 rows)



======================================================================================================

create table daily_collection_details(recid int primary key,cdate date,sup_id int references sup_manage(sup_id),type_id int references milktype(type_id),fat_per int,quantity int);


postgres=# \d daily_collection_details
       Table "public.daily_collection_details"
  Column  |  Type   | Collation | Nullable | Default 
----------+---------+-----------+----------+---------
 recid    | integer |           | not null | 
 cdate    | date    |           |          | 
 sup_id   | integer |           |          | 
 type_id  | integer |           |          | 
 fat_per  | integer |           |          | 
 quantity | integer |           |          | 
Indexes:
    "daily_collection_details_pkey" PRIMARY KEY, btree (recid)
Foreign-key constraints:
    "daily_collection_details_sup_id_fkey" FOREIGN KEY (sup_id) REFERENCES sup_manage(sup_id)
    "daily_collection_details_type_id_fkey" FOREIGN KEY (type_id) REFERENCES milktype(type_id)


postgres=# select * from daily_collection_details;
 recid |   cdate    | sup_id | type_id | fat_per | quantity 
-------+------------+--------+---------+---------+----------
     1 | 2021-06-10 |    108 |       3 |      50 |       45
     2 | 2021-06-11 |    108 |       2 |      50 |     1111
     3 | 2021-06-11 |    107 |       4 |      76 |      500
     4 | 2021-06-12 |    108 |       3 |      50 |      123
     5 | 2021-06-12 |    101 |       3 |     100 |      500
     6 | 2021-06-12 |    101 |       1 |      90 |      545
     7 | 2021-06-12 |    101 |       4 |      95 |       54
     8 | 2021-06-12 |    101 |       2 |      95 |       54
     9 | 2021-06-12 |    110 |       2 |      70 |      400
    10 | 2021-06-12 |    103 |       4 |      95 |      780

(10 rows)

