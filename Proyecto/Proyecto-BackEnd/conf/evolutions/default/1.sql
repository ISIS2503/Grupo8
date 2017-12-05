# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actuador (
  id                        bigint not null,
  activo                    boolean,
  inicio                    timestamp,
  area_id                   bigint,
  constraint pk_actuador primary key (id))
;

create table alerta (
  id                        bigint not null,
  tipo                      integer,
  area_id                   bigint,
  constraint pk_alerta primary key (id))
;

create table area (
  id                        bigint not null,
  tipo                      integer,
  nivel_id                  bigint,
  constraint pk_area primary key (id))
;

create table dato (
  id                        bigint not null,
  valor                     float,
  time_stamp                timestamp,
  constraint pk_dato primary key (id))
;

create table microcontrolador (
  id                        bigint not null,
  area_id                   bigint,
  constraint pk_microcontrolador primary key (id))
;

create table nivel (
  id                        bigint not null,
  nivel                     integer,
  constraint uq_nivel_nivel unique (nivel),
  constraint pk_nivel primary key (id))
;

create table reporte (
  id                        bigint not null,
  fecha                     timestamp,
  nivel_id                  bigint,
  constraint pk_reporte primary key (id))
;

create table sensor (
  id                        bigint not null,
  minimo                    float,
  maximo                    float,
  microcontrolador_id       bigint,
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

alter table actuador add constraint fk_actuador_area_1 foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_actuador_area_1 on actuador (area_id);
alter table alerta add constraint fk_alerta_area_2 foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_alerta_area_2 on alerta (area_id);
alter table area add constraint fk_area_nivel_3 foreign key (nivel_id) references nivel (id) on delete restrict on update restrict;
create index ix_area_nivel_3 on area (nivel_id);
alter table microcontrolador add constraint fk_microcontrolador_area_4 foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_microcontrolador_area_4 on microcontrolador (area_id);
alter table reporte add constraint fk_reporte_nivel_5 foreign key (nivel_id) references nivel (id) on delete restrict on update restrict;
create index ix_reporte_nivel_5 on reporte (nivel_id);
alter table sensor add constraint fk_sensor_microcontrolador_6 foreign key (microcontrolador_id) references microcontrolador (id) on delete restrict on update restrict;
create index ix_sensor_microcontrolador_6 on sensor (microcontrolador_id);



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

