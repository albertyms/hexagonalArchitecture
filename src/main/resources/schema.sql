DROP TABLE IF EXISTS BRAND CASCADE;

CREATE TABLE BRAND (
        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
        NAME VARCHAR NOT NULL,
        START_DATE TIMESTAMP NOT NULL,
        END_DATE TIMESTAMP NOT NULL
);


DROP TABLE IF EXISTS PRICE CASCADE;

CREATE TABLE PRICE (
        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
        BRAND_ID BIGINT NOT NULL,
        PRODUCT_ID BIGINT NOT NULL,
        PRICE_LIST BIGINT NOT NULL,
        PRIORITY INT NOT NULL,
        START_DATE TIMESTAMP NOT NULL,
        END_DATE TIMESTAMP NOT NULL,
        PRICE NUMERIC(20, 2) NOT NULL,
        CURR VARCHAR(3) NOT NULL
);



