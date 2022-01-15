INSERT INTO USERS VALUES('b5d46ba0-b0e0-4f0e-a399-e796c3969d3b', 'Robert', 'Martin', 'unclebob', '$2a$12$meMe5iToZsEAN0WZCHtPdO2LwQKqa..jEHt5ZsQzwrsomi3.BOFDu');

INSERT INTO UNITS VALUES('b5d46ba0-b0e0-4f0e-a399-e796d3569d3a', 'image/url', 'Bay Area Majesty', 'San Fransisco', 'a description', FALSE, 300);
INSERT INTO UNITS VALUES('b5d46ba0-b0e0-4f0e-a399-e796c3969d3a', 'image/url/2', 'Cupertino Lovers', 'San Fransisco', 'another description', FALSE, 400);

UPDATE RENTAPLACE.UNITS SET SEARCH_TOKENS = TO_TSVECTOR('san fransisco bay area majesty') WHERE UNIT_ID = 'b5d46ba0-b0e0-4f0e-a399-e796d3569d3a';
UPDATE RENTAPLACE.UNITS SET SEARCH_TOKENS = TO_TSVECTOR('san fransisco cupertino lovers') WHERE UNIT_ID = 'b5d46ba0-b0e0-4f0e-a399-e796c3969d3a';