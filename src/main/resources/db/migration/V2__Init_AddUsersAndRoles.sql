insert into role (id, name)
    values (1, 'ADMIN'), (2, 'USER');
insert into users (id, username, password)
    values (1, 'user', '$2a$12$yX3z8McyZLz3myl5JjgEx.nBdrHa7Gi4H3ubjzKjp3D7F8J1ewM1.'), (2, 'admin', '$2a$12$3aa80MIvaVZ8ZgpaNESeLe98LAiNtyp6hr9Ordn8oIriA.2tGVBn6');
insert into users_roles (user_id, roles_id)
    values (1, 2), (2,1);