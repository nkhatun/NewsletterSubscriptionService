drop table SUBSCRIPTIONS if exists;
create table Subscriptions (Id serial, Status Integer (10) NOT NULL, user_id Long NOT NULL);