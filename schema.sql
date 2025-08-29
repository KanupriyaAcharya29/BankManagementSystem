CREATE DATABASE bankdb;

USE bankdb;

CREATE TABLE accounts (
    account_no INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    balance DOUBLE DEFAULT 0,
    pin_hash VARCHAR(256)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_no INT,
    type VARCHAR(50),
    amount DOUBLE,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_no) REFERENCES accounts(account_no)
);