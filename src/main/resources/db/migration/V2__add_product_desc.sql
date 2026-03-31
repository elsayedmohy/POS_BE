alter table products
    add active bool default true not null;

alter table products
    add description text null;