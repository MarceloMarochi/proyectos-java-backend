drop table if exists usr cascade;
create table usr (
  uname         varchar(64)   not null,
  primary key (uname)
);

drop table if exists language cascade;
create table language (
  language      varchar(32)   not null,
  primary key (language)
);

drop table if exists tag cascade;
create table tag (
  tag           varchar(32)   not null,
  primary key (tag)
);

drop table if exists repository cascade;
create table repository (
  rid           integer       not null,
  uname         varchar(64)   not null,
  rname         varchar(64)   not null,
  description   varchar(255),
  last_update   date,
  stars         decimal,
  url           varchar(255),
  primary key (rid),
  foreign key (uname)
    references usr(uname),
  unique (rname)
);

drop table if exists repository_languages cascade;
create table repository_languages (
  rid           integer       not null,
  language      varchar(32)   not null,
  primary key (rid, language),
  foreign key (rid)
    references repository(rid),
  foreign key (language)
    references language(language)
);

drop table if exists repository_tags cascade;
create table repository_tags (
  rid           integer       not null,
  tag           varchar(32)   not null,
  primary key (rid, tag),
  foreign key (rid)
    references repository(rid),
  foreign key (tag)
    references tag(tag)
);

select * from repository;
