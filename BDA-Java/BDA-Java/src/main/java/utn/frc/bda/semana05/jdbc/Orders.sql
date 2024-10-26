-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    B A C K E N D   D E   A P L I C A C I O N E S   ( B D A )
--    E J E M P L O   T U T O R:
--        O R D E R S
--
--    E S T R U C T U R A   B B D D   ( o r d e r s d b )
--        D B M S :   H 2   D a t a b a s e   E n g i n e
--
--    A U T O R :    S c a r a f i a
--
-- =============================================================================
drop table if exists city cascade;
create table city (
  cid     integer       not null,
  cname   varchar(64)   not null,
  primary key (cid),
  unique (cname)
);

drop table if exists customer cascade;
create table customer (
  cid     integer       not null,
  cname   varchar(128)  not null,
  city_id integer       not null,
  primary key (cid),
  foreign key (city_id)
    references city(cid)
);

drop table if exists product cascade ;
create table product (
  pid     integer       not null,
  pname   varchar(64)   not null,
  price   decimal       not null,
  primary key (pid)
);

drop table if exists orders cascade ;
create table orders(
  oid     integer       not null,
  cid     integer       not null,
  odate   date          not null,
  primary key (oid),
  foreign key (cid)
    references customer(cid)
);

drop table if exists order_details cascade ;
create table order_details(
  oid     integer       not null,
  pid     integer       not null,
  cnt     integer       not null,
  primary key (oid, pid),
  foreign key (oid)
    references orders(oid),
  foreign key (pid)
    references product(pid)
);
