create schema orion

    create table comparison
    (
        id         serial not null unique,
        created_by varchar,
        created_on timestamp
    )

    create table comparison_element
    (
        id         serial not null unique,
        latitude   varchar,
        longitude  varchar,
        altitude   varchar,
        accuracy   varchar,
        times      varchar,
        comparison bigint,
        foreign key (comparison) references orion.comparison (id)
    )