# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actuador (
  id                        bigint not null,
  activo                    boolean,
  inicio                    timestamp,
  id_area                   bigint,
  constraint pk_actuador primary key (id))
;

create table alerta (
  id                        bigint not null,
  tipo                      integer,
  id_area                   bigint,
  constraint pk_alerta primary key (id))
;

create table area (
  id                        bigint not null,
  tipo                      integer,
  id_nivel                  bigint,
  constraint pk_area primary key (id))
;

create table dato (
  id                        bigint not null,
  valor                     float,
  time_stamp                timestamp,
  id_sensor                 bigint,
  constraint pk_dato primary key (id))
;

create table microcontrolador (
  id                        bigint not null,
  id_area                   bigint,
  constraint pk_microcontrolador primary key (id))
;

create table nivel (
  id                        bigint not null,
  nivel                     integer,
  constraint pk_nivel primary key (id))
;

create table reporte (
  id                        bigint not null,
  fecha                     timestamp,
  id_nivel                  bigint,
  constraint pk_reporte primary key (id))
;

create table sensor (
  id                        bigint not null,
  minimo                    float,
  maximo                    float,
  id_microcontrolador       bigint,
  constraint pk_sensor primary key (id))
;

create table variable_ambiental (
  id                        bigint not null,
  valor_maximo              float,
  valor_minimo              float,
  variacion                 float,
  uniadad_de_medida         varchar(255),
  precision                 float,
  frecuencia                float,
  constraint pk_variable_ambiental primary key (id))
;

create sequence actuador_seq;

create sequence alerta_seq;

create sequence area_seq;

create sequence dato_seq;

create sequence microcontrolador_seq;

create sequence nivel_seq;

create sequence reporte_seq;

create sequence sensor_seq;

create sequence variable_ambiental_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists actuador;

drop table if exists alerta;

drop table if exists area;

drop table if exists dato;

drop table if exists microcontrolador;

drop table if exists nivel;

drop table if exists reporte;

drop table if exists sensor;

drop table if exists variable_ambiental;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists actuador_seq;

drop sequence if exists alerta_seq;

drop sequence if exists area_seq;

drop sequence if exists dato_seq;

drop sequence if exists microcontrolador_seq;

drop sequence if exists nivel_seq;

drop sequence if exists reporte_seq;

drop sequence if exists sensor_seq;

drop sequence if exists variable_ambiental_seq;

