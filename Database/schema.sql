create table users(
  id serial primary key,
  username varchar(30) unique not null,
  password varchar(255) not null,
  role varchar(20) not null
);

create table teams(
  id serial primary key,
  name varchar(50) unique not null,
  city varchar(20) not null
);

create table matches(
  id serial primary key,
  home_team_id int not null,
  guest_team_id int not null,
  
  home_team_score int default 0,
  guest_team_score int default 0,
  
  match_date timestamp not null,
  status varchar(20) default 'SCHEDULED',
  
  constraint fk_home_team foreign key (home_team_id) references teams(id) on delete cascade,
  constraint fk_guest_team foreign key (guest_team_id) references teams(id) on delete cascade
);