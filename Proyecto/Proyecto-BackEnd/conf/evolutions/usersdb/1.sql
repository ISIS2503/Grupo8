# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table rol (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_rol primary key (id))
;

create table usuario (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  token                     varchar(255),
  constraint uq_usuario_login unique (login),
  constraint pk_usuario primary key (id))
;


create table usuario_rol (
  usuario_id                     bigint not null,
  rol_id                         bigint not null,
  constraint pk_usuario_rol primary key (usuario_id, rol_id))
;
create sequence rol_seq;

create sequence usuario_seq;




alter table usuario_rol add constraint fk_usuario_rol_usuario_01 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table usuario_rol add constraint fk_usuario_rol_rol_02 foreign key (rol_id) references rol (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists rol;

drop table if exists usuario;

drop table if exists usuario_rol;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists rol_seq;

drop sequence if exists usuario_seq;

