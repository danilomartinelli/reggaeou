CREATE TYPE event_info_vendor AS ENUM ('IBahia', 'Sympla');

CREATE TABLE Categories
(
    id_category SERIAL PRIMARY KEY,
    name varchar(60) NOT NULL
);

CREATE TABLE Events
(
	href varchar(240) NOT NULL UNIQUE PRIMARY KEY,

	title varchar(240) NOT NULL,
	description text NOT NULL,
	local varchar(120) NOT NULL,
	date DATE NOT NULL,
	time varchar(10) NOT NULL,
	folder varchar(240) NOT NULL,

    id_category INT REFERENCES Categories(id_category),
	vendor event_info_vendor NOT NULL
);

CREATE TABLE Prices
(
    id_price SERIAL PRIMARY KEY,

    id_event varchar(240) REFERENCES Events(href),

    description varchar(60) NOT NULL,
    value MONEY NOT NULL
);


CREATE TABLE Users
(
    id_user SERIAL PRIMARY KEY,
    email varchar(60) NOT NULL UNIQUE,
    tel varchar(11) NOT NULL
);

CREATE TABLE User_Category
(
    id_user INT REFERENCES Users(id_user),
    id_category INT REFERENCES Categories(id_category),

    PRIMARY KEY(id_user, id_category)
);