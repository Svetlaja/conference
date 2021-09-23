DELETE FROM schedule;
DELETE FROM users_talks;
DELETE FROM talk;
DELETE FROM room;
DELETE FROM user;

INSERT INTO room(room_name)
VALUES ('Room 100'),
       ('Room 120'),
       ('Room 122'),
       ('Room 240');


INSERT INTO user(username, password, role)
VALUES ('admin', '$2a$10$HCb6ztK0nbmRWMGrbKPa4O29ZcLOaTX/8Zj4PI9TDI6qMZXdcUSke', 'ADMIN'),
       ('simple user', '$2a$10$/gdApep2tA.Y9zxA72Jccuv.283oYkB7hjzGP59GvyqylEzdmTY0C', 'LISTENER'),
       ('speaker 1', '$2a$10$jn0IVlTchodyTfvb/.Plv.4Yr3jsFt7aDCXkIvetUg7choHVyH6Na', 'SPEAKER'),
       ('speaker 2', '$2a$10$J2NS7hfAzL2yTy6ZO9kEM.j1aZexItzIj0IPFCUcwMOzU69RSdBIO', 'SPEAKER');


INSERT INTO talk(talk_name)
VALUES ('Talk 1'),
       ('Talk 2 from data sql'),
       ('Talk about something');

INSERT INTO users_talks(user_id,talk_id)
VALUES (3, 1),
       (3, 3),
       (4, 1),
       (4, 2);

INSERT INTO schedule(date_start, date_end, room_id, talk_id)
VALUES ('2021-09-23 10:00:00', '2021-09-23 11:30:00', 2, 1),
       ('2021-09-23 13:00:00', '2021-09-23 14:00:00', 1, 2),
       ('2021-09-23 10:30:00', '2021-09-23 15:00:00', 4, 3);
