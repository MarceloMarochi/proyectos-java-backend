drop table if exists hospital cascade;
create table hospital (
  hid         integer       not null,
  hname       varchar(64)   not null,
  primary key (hid),
  unique (hname)
);

drop table if exists doctor cascade;
create table doctor(
  did         integer       not null,
  dname       varchar(64)   not null,
  hid         integer       not null,
  primary key (did),
  foreign key (hid)
    references hospital(hid)
);

drop table if exists pathology cascade;
create table pathology(
  pid         integer       not null,
  pname       varchar(64)   not null,
  primary key (pid)
);

drop table if exists report cascade;
create table report(
  rid         integer       not null,
  did         integer       not null,
  rdate       date          not null,
  primary key (rid),
  foreign key (did)
    references doctor (did)
);

drop table if exists report_details cascade;
create table report_details(
  rdid        integer       not null,  --surrogate key
  rid         integer       not null,
  pid         integer       not null,
  cases       integer       not null,
  primary key (rdid),
  foreign key (rid)
    references report(rid),
  foreign key (pid)
    references pathology(pid)
);
--select * from hospital;
-- select * from doctor;
-- select * from pathology;
-- select * from report;
-- select * from report_details;
