CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table TASK (
  id uuid default uuid_generate_v1 (),
  status varchar(50),
  date timestamp,
  primary key (id)
);