CREATE DATABASE IF NOT EXISTS myhotel;
USE myhotel;

CREATE TABLE IF NOT EXISTS menu_item (
    id INTEGER NOT NULL PRIMARY KEY,
    name TEXT,
    price NUMERIC(4,2),
    active BOOLEAN,
    dateOfLaunch DATE,
    category TEXT,
    freeDelivery BOOLEAN
);

CREATE TABLE IF NOT EXISTS cart (
    id INTEGER NOT NULL PRIMARY KEY,
    total NUMERIC(4,2)
);

CREATE TABLE IF NOT EXISTS cart_items (
    cartId INTEGER NOT NULL,
    itemId INTEGER NOT NULL,
    PRIMARY KEY (cartId, itemId),
    CONSTRAINT cart_id_fk FOREIGN KEY (cartId) REFERENCES cart(id) ON DELETE CASCADE,
    CONSTRAINT item_id_fk FOREIGN KEY (itemId) REFERENCES menu_item(id) ON DELETE CASCADE
);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (0, 'Water', 1.50, true, STR_TO_DATE('01/01/2018', '%d/%m/%Y'), 'Drink', true);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (1, 'Soda', 3.00, true, STR_TO_DATE('08/01/2018', '%d/%m/%Y'), 'Drink', true);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (2, 'Chips', 3.50, true, STR_TO_DATE('03/04/2019', '%d/%m/%Y'), 'Food', true);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (3, 'Salad', 8.00, true, STR_TO_DATE('08/04/2019', '%d/%m/%Y'), 'Food', false);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (4, 'Pizza', 10.00, true, STR_TO_DATE('19/08/2020', '%d/%m/%Y'), 'Food', false);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (5, 'Juice', 4.00, true, STR_TO_DATE('02/02/2021', '%d/%m/%Y'), 'Drink', false);

INSERT INTO menu_item(id, name, price, active, dateOfLaunch, category, freeDelivery) 
VALUES (6, 'Cookies', 7.00, false, STR_TO_DATE('31/12/2023', '%d/%m/%Y'), 'Food', false);

INSERT INTO cart(id, total) VALUES (0, 0);
INSERT INTO cart(id, total) VALUES (1, 0);
INSERT INTO cart(id, total) VALUES (2, 0);

INSERT INTO cart_items(cartId, itemId) VALUES (0, 0);
INSERT INTO cart_items(cartId, itemId) VALUES (0, 1);
INSERT INTO cart_items(cartId, itemId) VALUES (0, 2);

INSERT INTO cart_items(cartId, itemId) VALUES (1, 0);
INSERT INTO cart_items(cartId, itemId) VALUES (1, 3);

INSERT INTO cart_items(cartId, itemId) VALUES (2, 1);
INSERT INTO cart_items(cartId, itemId) VALUES (2, 2);
INSERT INTO cart_items(cartId, itemId) VALUES (2, 4);