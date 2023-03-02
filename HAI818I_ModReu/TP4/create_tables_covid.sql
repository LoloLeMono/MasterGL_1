CREATE TABLE users(
    user_id serial NOT NULL PRIMARY KEY,
    first_name varchar(30) NOT NULL,
    last_name varchar(30) NOT NULL,
    email varchar(80) NOT NULL,
    phone_number varchar(20) NOT NULL,
    password varchar(100) NOT NULL
);

CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date timestamp without time zone NOT NULL
);

CREATE TABLE user_locations
(
    user_id integer NOT NULL REFERENCES users (user_id),
    location_id integer NOT NULL REFERENCES locations (location_id)
);

INSERT INTO users(first_name,last_name,email,phone_number,password) VALUES('Tom','Robinson','tom.rob@yopmail.com','+15103754657','123456');
INSERT INTO locations(latitude,longitude,location_date) VALUES(43.63746472422702,3.8409670228559136,now());