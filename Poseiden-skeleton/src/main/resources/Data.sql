-- create database prod;
-- use prod;
CREATE TABLE Bid (
    BidListId INT PRIMARY KEY,
    account VARCHAR(255),
    type VARCHAR(255),
    bidQuantity DOUBLE,
    askQuantity DOUBLE,
    bid DOUBLE,
    ask DOUBLE,
    benchmark VARCHAR(255),
    bidListDate TIMESTAMP,
    commentary TEXT,
    security VARCHAR(255),
    status VARCHAR(255),
    trader VARCHAR(255),
    book VARCHAR(255),
    creationName VARCHAR(255),
    creationDate TIMESTAMP,
    revisionName VARCHAR(255),
    revisionDate TIMESTAMP,
    dealName VARCHAR(255),
    dealType VARCHAR(255),
    sourceListId VARCHAR(255),
    side VARCHAR(255)
);

CREATE TABLE CurvePoint (
    id INT PRIMARY KEY,
    curveId INT,
    asOfDate TIMESTAMP,
    term DOUBLE,
    value DOUBLE,
    creationDate TIMESTAMP
);

CREATE TABLE Rating (
    id INT PRIMARY KEY,
    moodysRating VARCHAR(255),
    sandPRating VARCHAR(255),
    fitchRating VARCHAR(255),
    orderNumber INT
);

CREATE TABLE Rule (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    json TEXT,
    template TEXT,
    sqlStr TEXT,
    sqlPart TEXT
);

CREATE TABLE Trade (
    tradeId INT PRIMARY KEY,
    account VARCHAR(255),
    type VARCHAR(255),
    buyQuantity DOUBLE,
    sellQuantity DOUBLE,
    buyPrice DOUBLE,
    sellPrice DOUBLE,
    benchmark VARCHAR(255),
    tradeDate TIMESTAMP,
    security VARCHAR(255),
    status VARCHAR(255),
    trader VARCHAR(255),
    book VARCHAR(255),
    creationName VARCHAR(255),
    creationDate TIMESTAMP,
    revisionName VARCHAR(255),
    revisionDate TIMESTAMP,
    dealName VARCHAR(255),
    dealType VARCHAR(255),
    sourceListId VARCHAR(255),
    side VARCHAR(255)
);

CREATE TABLE Users (
    id INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    fullname VARCHAR(255),
    role VARCHAR(255)
);

-- Insert some sample data
INSERT INTO Bid VALUES (1, 'account1', 'type1', 100.0, 200.0, 1.0, 2.0, 'benchmark1', NOW(), 'commentary1', 'security1', 'status1', 'trader1', 'book1', 'creationName1', NOW(), 'revisionName1', NOW(), 'dealName1', 'dealType1', 'sourceListId1', 'side1');
INSERT INTO CurvePoint VALUES (1, 1, NOW(), 1.0, 2.0, NOW());
INSERT INTO Rating VALUES (1, 'Aaa', 'AAA', 'AAA', 1);
INSERT INTO Rule VALUES (1, 'rule1', 'description1', '{}', 'template1', 'sqlStr1', 'sqlPart1');
INSERT INTO Trade VALUES (1, 'account1', 'type1', 100.0, 200.0, 1.0, 2.0, 'benchmark1', NOW(), 'security1', 'status1', 'trader1', 'book1', 'creationName1', NOW(), 'revisionName1', NOW(), 'dealName1', 'dealType1', 'sourceListId1', 'side1');
INSERT INTO Users (id, username, password, fullname, role) VALUES 
(1, 'john.doe@example.com', '$2a$10$QUcOkrgiQAH4zoKuKPd0Re9BbFJd85JsBxejaI7nTqfQx4OmV1MTS', 'John Doe', 'ADMIN'),
(2, 'jane.doe@example.com', '$2a$10$QUcOkrgiQAH4zoKuKPd0Re9BbFJd85JsBxejaI7nTqfQx4OmV1MTS', 'Jane Doe', 'ADMIN'),
(3, 'jean.valjean@example.com', '$2a$10$QUcOkrgiQAH4zoKuKPd0Re9BbFJd85JsBxejaI7nTqfQx4OmV1MTS', 'Jean Valjean', 'ADMIN'),
(4, 'peter.parker@example.com', '$2a$10$QUcOkrgiQAH4zoKuKPd0Re9BbFJd85JsBxejaI7nTqfQx4OmV1MTS', 'Peter Parker', 'ADMIN'),
(5, 'gwen.stacy@example.com', 'password5', 'Gwen Stacy', 'USER');