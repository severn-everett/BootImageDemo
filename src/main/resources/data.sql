INSERT INTO author(first_name, last_name) VALUES('John', 'Doe'), ('Jane', 'Doe');

INSERT INTO book(title, price, author_id)
VALUES('John First Book', 22.50, (SELECT id FROM author WHERE first_name = 'John')),
      ('Jane First Book', 25.50, (SELECT id FROM author WHERE first_name = 'Jane')),
      ('Jane Second Book', 19.25, (SELECT id FROM author WHERE first_name = 'Jane'));