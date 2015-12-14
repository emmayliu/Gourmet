DROP SCHEMA IF EXISTS pm4;
CREATE SCHEMA IF NOT EXISTS pm4;
USE pm4;

DROP TABLE IF EXISTS Reservations;
DROP TABLE IF EXISTS CheckIn;
DROP TABLE IF EXISTS CheckInDisplay;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Promotions;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS GoodFor;
DROP TABLE IF EXISTS DeliveryRestaurant;
DROP TABLE IF EXISTS TakeOutRestaurant;
DROP TABLE IF EXISTS ReservationRestaurant;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Users;


CREATE TABLE Users (
    UserId INT AUTO_INCREMENT,
    Name VARCHAR(255),
    password varchar(255) not null,
    CreatedSince DATE,
    ReviewCount INT,
    Gender BOOLEAN,
    CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId)
);

CREATE TABLE Restaurants (
    RestaurantId VARCHAR(255),
    RestaurantName VARCHAR(255),
    AcceptsCreditCard BOOLEAN,
    WIFI BOOLEAN,
    PriceRange INT,
    OpenTime TIME,
    CloseTime TIME,
    NoiseLevel INT,
    Neighborhood VARCHAR(255),
    Star DECIMAL(2 , 1 ),
    Parking INT,
    Street VARCHAR(1024),
    City VARCHAR(255),
    State VARCHAR(22),
    Zipcode INT,
    CONSTRAINT pk_Restaurants_RestaurantId PRIMARY KEY (RestaurantId)
);


CREATE TABLE CheckIn (
    CheckInId Int AUTO_INCREMENT,
    RestaurantId VARCHAR(255),
    SundaySum INT,
    MondaySum INT,
    TuesdaySum INT,
    WednesdaySum INT,
    ThursdaySum INT,
    FridaySum INT,
    SaturdaySum INT,
    CONSTRAINT pk_CheckIn_CheckInId PRIMARY KEY (CheckInId),
    CONSTRAINT fk_CheckIn_RestaurantId FOREIGN KEY (RestaurantId)
        REFERENCES Restaurants (RestaurantId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CheckInDisplay (
	CheckInId INT AUTO_INCREMENT,
    SundaySum INT,
    MondaySum INT,
    TuesdaySum INT,
    WednesdaySum INT,
    ThursdaySum INT,
    FridaySum INT,
    SaturdaySum INT,
    CONSTRAINT pk_CheckInDisplay_CheckInId PRIMARY KEY (CheckInId)
);

CREATE TABLE Promotions (
    PromotionId INT AUTO_INCREMENT,
    RestaurantId VARCHAR(255),
    StartTime TIMESTAMP,
    EndTime TIMESTAMP,
    Discount DOUBLE,
    CONSTRAINT pk_Promotions_PromotionId PRIMARY KEY (PromotionId),
    CONSTRAINT fk_Promotions_RestaurantId FOREIGN KEY (RestaurantId)
        REFERENCES Restaurants (RestaurantId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE GoodFor (
    RestaurantId VARCHAR(255),
    GoodForBrunch BOOLEAN,
    GoodForDinner BOOLEAN,
    GoodForBreakfast BOOLEAN,
    GoodForLunch BOOLEAN,
    GoodForDessert BOOLEAN,
    GoodForLateNight BOOLEAN,
    GoodForKids BOOLEAN,
    GoodForGroups BOOLEAN,
    CONSTRAINT pk_GoodFor_RestaurantId PRIMARY KEY (RestaurantId)
);

CREATE TABLE Categories (
    RestaurantId VARCHAR(255),
    Bars BOOLEAN,
    Mexican BOOLEAN,
    Chinese BOOLEAN,
    AmericanNew BOOLEAN,
    AmericanTraditional BOOLEAN,
    Sandwiches BOOLEAN,
    Seafood BOOLEAN,
    Buffets BOOLEAN,
    Greek BOOLEAN,
    Indian BOOLEAN,
    SteakHouse BOOLEAN,
    Nightlife BOOLEAN,
    Korean BOOLEAN,
    Pizza BOOLEAN,
    Thai BOOLEAN,
    Italian BOOLEAN,
    CONSTRAINT pk_Categories_RestaurantId PRIMARY KEY (RestaurantId)
);

CREATE TABLE TakeOutRestaurant (
    RestaurantId VARCHAR(255),
    MaxWaitTime INT,
    CONSTRAINT pk_TakeOutRestaurant_RestaurantId PRIMARY KEY (RestaurantId),
    CONSTRAINT fk_TakeOutRestaurant_RestaurantId FOREIGN KEY (RestaurantId)
        REFERENCES Restaurants (RestaurantId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DeliveryRestaurant (
    RestaurantId VARCHAR(255),
    MaxDeliverTime INT,
    CONSTRAINT pk_DeliveryRestaurant_RestaurantId PRIMARY KEY (RestaurantId),
    CONSTRAINT fk_DeliveryRestaurant_RestaurantId FOREIGN KEY (RestaurantId)
        REFERENCES Restaurants (RestaurantId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ReservationRestaurant (
    RestaurantId VARCHAR(255),
    MaxRoomSize INT,
    CONSTRAINT pk_ReservationRestaurant_RestaurantId PRIMARY KEY (RestaurantId),
    CONSTRAINT fk_ReservationRestaurant_RestaurantId FOREIGN KEY (RestaurantId)
        REFERENCES Restaurants (RestaurantId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reservations (
    reservationid INT AUTO_INCREMENT,
    userid INT,
    restaurantid VARCHAR(255),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    size INT,
    CONSTRAINT pk_reservation_reservationid PRIMARY KEY (Reservationid),
    CONSTRAINT fk_reservation_userid FOREIGN KEY (userid)
        REFERENCES Users (userid)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_reservation_restaurantid FOREIGN KEY (restaurantid)
        REFERENCES Restaurants (restaurantid)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reviews (
    ReviewId int auto_increment,
    UserId INT,
    RestaurantId VARCHAR(255),
    Review VARCHAR(1024),
    Created TIMESTAMP,
    Rating DECIMAL,
    CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
    CONSTRAINT fk_Reviews_UserId FOREIGN KEY (UserId)
        REFERENCES Users (UserId)
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_Reviews_RestaurantId FOREIGN KEY (RestaurantId)
        REFERENCES Restaurants (RestaurantId)
        ON UPDATE CASCADE ON DELETE SET NULL
);


load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/restaurant_with_full_addr.csv'
into TABLE Restaurants
FIELDS TERMINATED BY ','  enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;

load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/checking.csv'
into TABLE CheckIn
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;

load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/users.csv'
into TABLE Users
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;

load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/categories.csv'
into TABLE Categories
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;

load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/gf.csv'
into TABLE Goodfor
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;

load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/reviews.csv'
into TABLE Reviews
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
ignore 1 lines
(UserId, RestaurantId, Review, Created, Rating);

/*
load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/take-o.csv'
into TABLE TakeOutRestaurant
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;

load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/reserve.csv'
into TABLE ReservationRestaurant
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;


load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/discount.csv'
into TABLE Promotions
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;


load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/delivery_restaurant.csv'
into TABLE DeliveryRestaurant
FIELDS TERMINATED BY ',' enclosed by '"'
LINES TERMINATED BY '\n'
ignore 1 lines;



load data infile '/Users/entingwu/Desktop/DatabaseManagement/PROJECT/DATA/valid_datasets/reservations.csv'
into TABLE Reservations
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r'
ignore 1 lines
(userid, restaurantid, start_time, end_time, size);



*/
select * from Restaurants;
select * from Reviews;
select * from Users;
select * from Categories;

SELECT * 
FROM Restaurants INNER JOIN Categories
ON Restaurants.RestaurantId = Categories.RestaurantId
WHERE Categories.Korean = 1; 