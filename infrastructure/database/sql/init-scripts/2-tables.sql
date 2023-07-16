create table if not exists management.snack_type(
       id smallserial not null primary key,
       name text not null
);
create table if not exists management.snacks(
       id smallserial not null primary key,
       name text not null,
       snack_type_id integer references management.snack_type(id),
       quantity integer not null,
       price money not null
);
create table if not exists management.member_card_type(
       member_card_type_id smallserial not null primary key,
       title text not null,
       member_card_image text not null
);
create table if not exists management.chair_type(
       chair_type_id smallserial not null primary key,
       description text not null
);
create table if not exists management.screen_type(
       screen_type_id smallserial not null primary key,
       name text not null,
       description text
);
create table if not exists management.auditorium(
       auditorium_id smallserial not null primary key,
       name text not null,
       doors integer not null,
       state character(1) not null,
       rows integer not null,
       screen_type integer references management.screen_type(screen_type_id) on delete cascade
);
create table if not exists management.chair_auditorium(
       chair_auditorium_id serial not null primary key,
       row integer not null,
       state character(1) not null,
       auditorium integer references management.auditorium(auditorium_id) on delete cascade,
       chair_type integer references management.chair_type(chair_type_id) on delete cascade
);
create table if not exists management.cast_type(
       cast_type_id smallserial not null primary key,
       role_name text not null
);
create table if not exists management.film(
       film_id serial not null primary key,
       title text not null,
       duration integer not null,
       year integer not null
);
create table if not exists management.actor(
       actor_id serial not null primary key,
       first_name text not null,
       last_name text not null,
       gender char(1) not null,
       profile_picture text
);
create table if not exists management.cast(
       cast_id smallserial not null primary key,
       cast_type integer references management.cast_type(cast_type_id) on delete cascade,
       film integer references management.film(film_id) on delete cascade,
       actor integer references management.actor(actor_id) on delete cascade
);
create table if not exists management.schedule(
       schedule_id serial not null primary key,
       film integer references management.film(film_id),
       auditorium integer references management.auditorium(auditorium_id) on delete cascade,
       date date not null,
       time time not null
);