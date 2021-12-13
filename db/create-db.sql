CREATE DATABASE my-hotel;

CREATE TABLE menu_item (
    id INTEGER NOT NULL PRIMARY KEY,
    name TEXT,
    price NUMERIC(4,2),
    active BOOLEAN,
    dateOfLaunch DATE,
    category TEXT,
    freeDelivery BOOLEAN
);

CREATE TABLE cart {
    id INTEGER NOT NULL PRIMARY KEY,
    total NUMERIC(4,2)
};

CREATE TABLE cart_items {
    cartId INTEGER NOT NULL,
    itemId INTEGER NOT NULL,
    PRIMARY KEY (cartId, itemId),
    CONSTRAINT cart_id_fk FOREIGN KEY cartId REFERENCES cart(id),
    CONSTRAINT item_id_fk FOREIGN KEY itemId REFERENCES menu_item(id)
};