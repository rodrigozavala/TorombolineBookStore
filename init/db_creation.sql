CREATE DATABASE IF NOT EXISTS TOROMBOLINE_BOOK_STORE;

CREATE TABLE TOROMBOLINE_BOOK_STORE.BOOKS (
    book_id INT AUTO_INCREMENT,
    title VARCHAR(255),
    author_id INT,
    category_id INT,
    price DECIMAL(10, 2),
    description VARCHAR(255),
    isbn VARCHAR(13),
    publication_date DATE,
    cover_image_url VARCHAR(255),
    stock_quantity INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT PK_books PRIMARY KEY (book_id)
);


CREATE TABLE TOROMBOLINE_BOOK_STORE.AUTHOR_PROFILES (
    author_id INT AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    biography TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT PK_author_profiles PRIMARY KEY (author_id)
);


CREATE TABLE TOROMBOLINE_BOOK_STORE.BOOK_CATEGORIES (
    category_id INT AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    CONSTRAINT PK_categories PRIMARY KEY (category_id)
);


CREATE TABLE TOROMBOLINE_BOOK_STORE.USERS (
    user_id BINARY(16) default (UUID_TO_BIN(UUID())),
    email VARCHAR(255) UNIQUE NOT NULL,
    salt VARCHAR(24) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_users PRIMARY KEY (user_id)
);

CREATE TABLE TOROMBOLINE_BOOK_STORE.CUSTOMERS (
    customer_id INT AUTO_INCREMENT,
    user_id BINARY(16),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone_number VARCHAR(20),
    shipping_address TEXT,
    billing_address TEXT,
    CONSTRAINT PK_customers PRIMARY KEY (customer_id),
    FOREIGN KEY (user_id) REFERENCES TOROMBOLINE_BOOK_STORE.USERS(user_id)
);


CREATE TABLE TOROMBOLINE_BOOK_STORE.ORDERS (
    order_id INT AUTO_INCREMENT,
    customer_id INT,
    order_date TIMESTAMP,
    status VARCHAR(100),
    total_price DECIMAL(10, 2),
    shipping_address TEXT,
    billing_address TEXT,
    payment_method VARCHAR(100),
    payment_status VARCHAR(100),
    created_at TIMESTAMP,
    CONSTRAINT PK_orders PRIMARY KEY (order_id),
    FOREIGN KEY (customer_id) REFERENCES TOROMBOLINE_BOOK_STORE.CUSTOMERS(customer_id)
);


CREATE TABLE TOROMBOLINE_BOOK_STORE.ORDER_ITEMS (
    order_item_id INT AUTO_INCREMENT,
    order_id INT,
    book_id INT,
    quantity INT,
    price_at_time DECIMAL(10, 2),
    CONSTRAINT PK_order_items PRIMARY KEY (order_item_id),
    FOREIGN KEY (order_id) REFERENCES TOROMBOLINE_BOOK_STORE.ORDERS(order_id),
    FOREIGN KEY (book_id) REFERENCES TOROMBOLINE_BOOK_STORE.BOOKS(book_id)
);


ALTER TABLE TOROMBOLINE_BOOK_STORE.BOOKS ADD FOREIGN KEY (category_id)
REFERENCES TOROMBOLINE_BOOK_STORE.BOOK_CATEGORIES(category_id);

ALTER TABLE TOROMBOLINE_BOOK_STORE.BOOKS ADD FOREIGN KEY (author_id)
REFERENCES TOROMBOLINE_BOOK_STORE.AUTHOR_PROFILES(author_id);


INSERT INTO TOROMBOLINE_BOOK_STORE.AUTHOR_PROFILES
(first_name, last_name, biography, created_at, updated_at)
VALUES
('William', 'Shakespeare', 'English playwright, poet, and actor widely regarded as the greatest writer in the English language.', NOW(), NOW()),
('Jane', 'Austen', 'English novelist known primarily for her six major novels, including Pride and Prejudice.', NOW(), NOW()),
('Charles', 'Dickens', 'English writer and social critic, author of Oliver Twist and A Tale of Two Cities.', NOW(), NOW()),
('Leo', 'Tolstoy', 'Russian author of War and Peace and Anna Karenina, considered one of the greatest novelists.', NOW(), NOW()),
('Fyodor', 'Dostoevsky', 'Russian novelist and philosopher best known for Crime and Punishment and The Brothers Karamazov.', NOW(), NOW()),
('Homer', '', 'Ancient Greek epic poet traditionally said to be the author of the Iliad and the Odyssey.', NOW(), NOW()),
('Victor', 'Hugo', 'French poet, novelist, and dramatist of the Romantic movement, author of Les Misérables.', NOW(), NOW()),
('Mark', 'Twain', 'American writer and humorist best known for The Adventures of Tom Sawyer and Huckleberry Finn.', NOW(), NOW()),
('Herman', 'Melville', 'American novelist and short story writer known for Moby-Dick.', NOW(), NOW()),
('Emily', 'Bronte', 'English novelist and poet who wrote Wuthering Heights.', NOW(), NOW()),
('Mary', 'Shelley', 'English novelist best known for writing Frankenstein.', NOW(), NOW()),
('Miguel', 'de Cervantes', 'Spanish writer best known for Don Quixote, one of the foundational works of Western literature.', NOW(), NOW()),
('Johann Wolfgang', 'von Goethe', 'German writer and statesman, famous for Faust and The Sorrows of Young Werther.', NOW(), NOW()),
('Franz', 'Kafka', 'German-speaking Bohemian writer known for The Metamorphosis and The Trial.', NOW(), NOW()),
('Gustave', 'Flaubert', 'French novelist known for his debut novel Madame Bovary.', NOW(), NOW()),
('Thomas', 'Hardy', 'English novelist and poet known for Tess of the d’Urbervilles and Jude the Obscure.', NOW(), NOW()),
('Nathaniel', 'Hawthorne', 'American novelist and short story writer known for The Scarlet Letter.', NOW(), NOW()),
('Dante', 'Alighieri', 'Italian poet famous for The Divine Comedy, an epic poem of the afterlife.', NOW(), NOW()),
('Edgar Allan', 'Poe', 'American writer, poet, and literary critic best known for his tales of mystery and the macabre.', NOW(), NOW()),
('James', 'Joyce', 'Irish novelist and poet best known for Ulysses and A Portrait of the Artist as a Young Man.', NOW(), NOW());

insert into TOROMBOLINE_BOOK_STORE.BOOK_CATEGORIES
(name, description) values ('NO_CLASSIFIED_YET','Classification is pending');

INSERT INTO TOROMBOLINE_BOOK_STORE.BOOKS
(title, author_id, category_id, price, description, isbn, publication_date, cover_image_url, stock_quantity, created_at, updated_at)
VALUES
('Hamlet', 1, 1, 9.99, 'A tragedy by William Shakespeare.', '9780141396507', '1603-01-01', 'https://example.com/hamlet.jpg', 50, NOW(), NOW()),
('Pride and Prejudice', 2, 1, 8.99, 'A romantic novel by Jane Austen.', '9780141439518', '1813-01-28', 'https://example.com/pride.jpg', 40, NOW(), NOW()),
('A Tale of Two Cities', 3, 1, 10.99, 'A novel by Charles Dickens set in London and Paris before and during the French Revolution.', '9780141439600', '1859-04-30', 'https://example.com/tale.jpg', 60, NOW(), NOW()),
('War and Peace', 4, 1, 14.99, 'A novel by Leo Tolstoy detailing Napoleon’s invasion of Russia.', '9780199232765', '1869-01-01', 'https://example.com/warpeace.jpg', 35, NOW(), NOW()),
('Crime and Punishment', 5, 1, 11.50, 'A psychological novel by Fyodor Dostoevsky.', '9780140449136', '1866-01-01', 'https://example.com/crime.jpg', 45, NOW(), NOW()),
('The Odyssey', 6, 1, 12.00, 'An epic Greek poem attributed to Homer.', '9780140268867', '0800-01-01', 'https://example.com/odyssey.jpg', 70, NOW(), NOW()),
('Les Misérables', 7, 1, 13.75, 'A novel by Victor Hugo about social injustice in France.', '9780451419439', '1862-01-01', 'https://example.com/lesmis.jpg', 50, NOW(), NOW()),
('Adventures of Huckleberry Finn', 8, 1, 9.99, 'A novel by Mark Twain about a boy\'s journey on the Mississippi River.', '9780486280615', '1884-12-10', 'https://example.com/huckfinn.jpg', 55, NOW(), NOW()),
('Moby-Dick', 9, 1, 11.25, 'A novel by Herman Melville about the voyage of the whaling ship Pequod.', '9781503280786', '1851-10-18', 'https://example.com/mobydick.jpg', 38, NOW(), NOW()),
('Wuthering Heights', 10, 1, 9.50, 'A gothic novel by Emily Brontë.', '9780141439556', '1847-12-01', 'https://example.com/wuthering.jpg', 42, NOW(), NOW()),
('Frankenstein', 11, 1, 10.25, 'A novel by Mary Shelley that tells the story of Victor Frankenstein.', '9780141439471', '1818-01-01', 'https://example.com/frankenstein.jpg', 47, NOW(), NOW()),
('Don Quixote', 12, 1, 13.99, 'A Spanish novel by Miguel de Cervantes.', '9780060934347', '1605-01-16', 'https://example.com/donquixote.jpg', 48, NOW(), NOW()),
('Faust', 13, 1, 8.99, 'A tragic play by Johann Wolfgang von Goethe.', '9780140449013', '1808-01-01', 'https://example.com/faust.jpg', 32, NOW(), NOW()),
('The Metamorphosis', 14, 1, 7.50, 'A novella by Franz Kafka.', '9780553213690', '1915-10-01', 'https://example.com/metamorphosis.jpg', 44, NOW(), NOW()),
('Madame Bovary', 15, 1, 10.99, 'A novel by Gustave Flaubert.', '9780140449129', '1856-01-01', 'https://example.com/bovary.jpg', 39, NOW(), NOW()),
('Tess of the d’Urbervilles', 16, 1, 9.75, 'A novel by Thomas Hardy.', '9780141439594', '1891-01-01', 'https://example.com/tess.jpg', 36, NOW(), NOW()),
('The Scarlet Letter', 17, 1, 8.49, 'A novel by Nathaniel Hawthorne.', '9780142437261', '1850-01-01', 'https://example.com/scarlet.jpg', 41, NOW(), NOW()),
('The Divine Comedy', 18, 1, 15.00, 'An epic poem by Dante Alighieri.', '9780142437223', '1320-01-01', 'https://example.com/divine.jpg', 29, NOW(), NOW()),
('The Raven and Other Poems', 19, 1, 7.99, 'A collection by Edgar Allan Poe.', '9780486278050', '1845-01-01', 'https://example.com/raven.jpg', 52, NOW(), NOW()),
('Ulysses', 20, 1, 14.50, 'A novel by James Joyce that parallels Homer’s Odyssey.', '9780199535675', '1922-02-02', 'https://example.com/ulysses.jpg', 30, NOW(), NOW());
