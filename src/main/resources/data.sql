
INSERT INTO Book
    (Title)
VALUES
    ('The Catcher in the Rye'),
    ('Nine Stories'),
    ('Franny and Zooey'),
    ('The Great Gatsby'),
    ('Tender id the Night'),
    ('Pride and Prejudice'),
    ('Professional ASP.NET 4.5 in C# and VB')
;

INSERT INTO Author
    (Name, Country)
VALUES
    ('J.D. Salinger', 'USA'),
    ('F. Scott. Fitzgerald', 'USA'),
    ('Jane Austen', 'UK'),
    ('Scott Hanselman', 'USA'),
    ('Jason N. Gaylord', 'USA'),
    ('Pranav Rastogi', 'India'),
    ('Todd Miranda', 'USA'),
    ('Christian Wenz', 'USA')
;

INSERT INTO Books_Authors
    (Book_Id, Author_Id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 3),
    (7, 4),
    (7, 5),
    (7, 6),
    (7, 7),
    (7, 8)
;

