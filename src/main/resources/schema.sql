CREATE TABLE Author (
    Id INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(70) NOT NULL,
    Country VARCHAR(100) NOT NULL,
    PRIMARY KEY(Id)
);

CREATE TABLE Book (
    Id INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(50) NOT NULL,
    PRIMARY KEY(Id)
);

CREATE TABLE Books_Authors (
    Author_Id INT NOT NULL,
    Book_Id  INT NOT NULL,
    FOREIGN KEY (Author_Id) REFERENCES AUTHOR(Id),
    FOREIGN KEY (Book_Id) REFERENCES BOOK(Id)
);