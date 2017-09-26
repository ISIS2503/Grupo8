# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actuador (
  id                        bigint,
  activo                    boolean,
  inicio                    timestamp,
  id_area                   bigint)
;

create table alerta (
  id                        bigint,
  tipo                      integer,
  id_area                   bigint)
;

create table area (
  id                        bigint,
  tipo                      integer,
  id_nivel                  bigint)
;

create table dato (
  id                        bigint,
  valor                     float,
  time_stamp                timestamp,
  id_sensor                 bigint)
;

create table microcontrolador (
  id                        bigint,
  id_area                   bigint)
;

create table nivel (
  id                        bigint,
  nivel                     integer)
;

create table reporte (
  id                        bigint,
  fecha                     timestamp,
  id_nivel                  bigint)
;

create table sensor (
  id                        bigint,
  minimo                    float,
  maximo                    float,
  id_microcontrolador       bigint)
;

create table usuario (
  id                        bigint,
  rol                       varchar(255),
  login                     varchar(255),
  password                  varchar(255))
;

create table variable_ambiental (
  id                        bigint,
  valor_maximo              float,
  valor_minimo              float,
  variacion                 float,
  uniadad_de_medida         varchar(255),
  precision                 float,
  frecuencia                float)
;




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

drop table if exists usuario;

drop table if exists variable_ambiental;

SET REFERENTIAL_INTEGRITY TRUE;

