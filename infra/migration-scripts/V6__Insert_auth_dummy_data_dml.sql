INSERT INTO AUTH_ROLE (ID, ROLE) VALUES ('b7134137-7cea-4b10-af4a-ce90b78bac16', 'SIMPLE_USER');

INSERT INTO AUTH_ROLE_TO_AUTHORITIES(ROLE, ROLE_ID, AUTHORITIES)
VALUES ('SIMPLE_USER', 'b7134137-7cea-4b10-af4a-ce90b78bac16', 'REFRESH_TOKEN_DELETE REFRESH_TOKEN_ALL_DELETE INSERT_REVIEW RETRIEVE_UNITS');

INSERT INTO AUTH_ROLE_TO_USERNAME(USERNAME, ROLE, USER_ID, ROLE_ID)
VALUES ('unclebob', 'SIMPLE_USER', 'b5d46ba0-b0e0-4f0e-a399-e796c3969d3b', 'b7134137-7cea-4b10-af4a-ce90b78bac16');
