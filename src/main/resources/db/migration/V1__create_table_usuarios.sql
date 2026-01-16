create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(120) not null,
    email varchar(130) not null unique,
    contrasena varchar(255) not null,

    primary key(id)
);