-- =====================================================
-- JAVAZON E-COMMERCE DATABASE SCHEMA (Oracle 11g XE)
-- =====================================================

-- Drop sequences first (safe if they exist)
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.USERS_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.CART_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.CART_ITEM_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.ORDER_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.ORDER_ITEM_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.PRODUCT_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.CATEGORY_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.PRODUCER_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.FAVOURITES_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.RELATED_PRODUCT_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.IMAGES_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE JAVAZON.DISCOUNTS_SEQ';
EXCEPTION WHEN OTHERS THEN NULL;
END;


-- CREATE sequences
CREATE SEQUENCE JAVAZON.USERS_SEQ           START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.CART_SEQ           START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.CART_ITEM_SEQ       START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.ORDER_SEQ           START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.ORDER_ITEM_SEQ      START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.PRODUCT_SEQ         START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.CATEGORY_SEQ        START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.PRODUCER_SEQ        START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.FAVOURITES_SEQ      START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.RELATED_PRODUCT_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.IMAGES_SEQ          START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE JAVAZON.DISCOUNTS_SEQ       START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- =====================================================
-- 1. USERS
-- =====================================================
CREATE TABLE JAVAZON.USERS (
    user_id       NUMBER PRIMARY KEY,
    username      VARCHAR2(50)  NOT NULL UNIQUE,
    email         VARCHAR2(100) NOT NULL UNIQUE,
    password      VARCHAR2(255) NOT NULL,
    first_name    VARCHAR2(50),
    last_name     VARCHAR2(50),

    -- SharedEntity audit fields
    created_on    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    VARCHAR2(100),
    updated_on    TIMESTAMP,
    updated_by    VARCHAR2(100)
);

CREATE TRIGGER JAVAZON.trg_users_pk
BEFORE INSERT ON JAVAZON.USERS
FOR EACH ROW
BEGIN
    IF :NEW.user_id IS NULL THEN
        :NEW.user_id := JAVAZON.USERS_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 2. CART
-- =====================================================
CREATE TABLE JAVAZON.CART (
    cart_id       NUMBER PRIMARY KEY,
    user_id       NUMBER NOT NULL,
    total_amount NUMBER,
    total_price NUMBER,

    created_on    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    VARCHAR2(100),
    updated_on    TIMESTAMP,
    updated_by    VARCHAR2(100),

    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES JAVAZON.USERS(user_id)
);

CREATE TRIGGER JAVAZON.trg_cart_pk
BEFORE INSERT ON JAVAZON.CART
FOR EACH ROW
BEGIN
    IF :NEW.cart_id IS NULL THEN
        :NEW.cart_id := JAVAZON.CART_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 3. CART_ITEM
-- =====================================================
CREATE TABLE JAVAZON.CART_ITEM (
    cart_item_id NUMBER        PRIMARY KEY,
    cart_id      NUMBER        NOT NULL,
    product_id   NUMBER        NOT NULL,
    quantity     NUMBER        DEFAULT 1,
    total_price  NUMBER(12,2),

    created_on   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR2(100),
    updated_on   TIMESTAMP,
    updated_by   VARCHAR2(100),

    CONSTRAINT fk_cartitem_cart    FOREIGN KEY (cart_id)    REFERENCES JAVAZON.CART(cart_id),
    CONSTRAINT fk_cartitem_product FOREIGN KEY (product_id) REFERENCES JAVAZON.PRODUCT(product_id),
    CONSTRAINT chk_cartitem_qty    CHECK (quantity > 0)
);

CREATE TRIGGER JAVAZON.trg_cart_item_pk
BEFORE INSERT ON JAVAZON.CART_ITEM
FOR EACH ROW
BEGIN
    IF :NEW.cart_item_id IS NULL THEN
        :NEW.cart_item_id := JAVAZON.CART_ITEM_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 4. CATEGORY
-- =====================================================
CREATE TABLE JAVAZON.CATEGORY (
    category_id   NUMBER PRIMARY KEY,
    category_name          VARCHAR2(100) NOT NULL,
    category_description   VARCHAR2(500),
    parent_id     NUMBER, -- for sub-categories
    active        VARCHAR2(1) DEFAULT 'Y',
    sort_order    NUMBER DEFAULT 0,
    main_iamge BLOB,

    created_on    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    VARCHAR2(100),
    updated_on    TIMESTAMP,
    updated_by    VARCHAR2(100),

    CONSTRAINT fk_category_parent FOREIGN KEY (parent_id) REFERENCES JAVAZON.CATEGORY(category_id)
);

CREATE TRIGGER JAVAZON.trg_category_pk
BEFORE INSERT ON JAVAZON.CATEGORY
FOR EACH ROW
BEGIN
    IF :NEW.category_id IS NULL THEN
        :NEW.category_id := JAVAZON.CATEGORY_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 5. PRODUCER
-- =====================================================
CREATE TABLE JAVAZON.PRODUCER (
    producer_id   NUMBER PRIMARY KEY,
    producer_name          VARCHAR2(100) NOT NULL,
    producer_description   VARCHAR2(1000),
    producer_country       VARCHAR2(100),
    producer_website       VARCHAR2(255),
    rating        NUMBER(3,1) DEFAULT 0,
    logo_image    VARCHAR2(255),

    created_on    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    VARCHAR2(100),
    updated_on    TIMESTAMP,
    updated_by    VARCHAR2(100)
);

CREATE TRIGGER JAVAZON.trg_producer_pk
BEFORE INSERT ON JAVAZON.PRODUCER
FOR EACH ROW
BEGIN
    IF :NEW.producer_id IS NULL THEN
        :NEW.producer_id := JAVAZON.PRODUCER_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 6. PRODUCT
-- =====================================================
CREATE TABLE JAVAZON.PRODUCT (
    product_id      NUMBER PRIMARY KEY,
    product_name            VARCHAR2(200) NOT NULL,
    product_description     CLOB,
    product_price           NUMBER(12,2) NOT NULL,
    stock_quantity  NUMBER DEFAULT 0,
    category_id     NUMBER,
    producer_id     NUMBER,
    rating          NUMBER(3,1)  DEFAULT 0,
    active          VARCHAR2(20) DEFAULT 'Y',
	main_image BLOB,

    created_on    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    VARCHAR2(100),
    updated_on    TIMESTAMP,
    updated_by    VARCHAR2(100),

    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES JAVAZON.CATEGORY(category_id),
    CONSTRAINT fk_product_producer FOREIGN KEY (producer_id) REFERENCES JAVAZON.PRODUCER(producer_id)
);

CREATE TRIGGER JAVAZON.trg_product_pk
BEFORE INSERT ON JAVAZON.PRODUCT
FOR EACH ROW
BEGIN
    IF :NEW.product_id IS NULL THEN
        :NEW.product_id := JAVAZON.PRODUCT_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 7. ORDERS
-- =====================================================
CREATE TABLE JAVAZON.ORDERS (
    order_id       NUMBER PRIMARY KEY,
    user_id        NUMBER NOT NULL,
    order_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price   NUMBER(12,2) NOT NULL,
    status         VARCHAR2(50) DEFAULT 'PENDING',
    voucher_discount_amount NUMBER ,
    cart_id NUMBER,

    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     VARCHAR2(100),
    updated_on     TIMESTAMP,
    updated_by     VARCHAR2(100),

    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES JAVAZON.USERS(user_id),
    CONSTRAINT fk_ORDERS_cart FOREIGN KEY (cart_id) REFERENCES JAVAZON.CART(cart_id)
);

CREATE TRIGGER JAVAZON.trg_order_pk
BEFORE INSERT ON JAVAZON.ORDERS
FOR EACH ROW
BEGIN
    IF :NEW.order_id IS NULL THEN
        :NEW.order_id := JAVAZON.ORDER_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 8. FAVOURITES
-- =====================================================
CREATE TABLE JAVAZON.FAVOURITES (
    favourite_id   NUMBER PRIMARY KEY,
    user_id        NUMBER NOT NULL,
    product_id     NUMBER NOT NULL,

    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     VARCHAR2(100),
    updated_on     TIMESTAMP,
    updated_by     VARCHAR2(100),

    CONSTRAINT fk_fav_user    FOREIGN KEY (user_id)    REFERENCES JAVAZON.USERS(user_id),
    CONSTRAINT fk_fav_product FOREIGN KEY (product_id) REFERENCES JAVAZON.PRODUCT(product_id),
    CONSTRAINT uq_fav_user_product UNIQUE (user_id, product_id)
);

CREATE TRIGGER JAVAZON.trg_favourites_pk
BEFORE INSERT ON JAVAZON.FAVOURITES
FOR EACH ROW
BEGIN
    IF :NEW.favourite_id IS NULL THEN
        :NEW.favourite_id := JAVAZON.FAVOURITES_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 9. RELATED_PRODUCT
-- =====================================================
CREATE TABLE JAVAZON.RELATED_PRODUCT (
    related_id     NUMBER PRIMARY KEY,
    parent_id      NUMBER NOT NULL,  -- main product
    child_id       NUMBER NOT NULL,  -- related product
    relation_type  VARCHAR2(50) DEFAULT 'CROSS_SELL',
    sort_order     NUMBER DEFAULT 0,

    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     VARCHAR2(100),
    updated_on     TIMESTAMP,
    updated_by     VARCHAR2(100),

    CONSTRAINT fk_rel_parent FOREIGN KEY (parent_id) REFERENCES JAVAZON.PRODUCT(product_id),
    CONSTRAINT fk_rel_child  FOREIGN KEY (child_id)  REFERENCES JAVAZON.PRODUCT(product_id),
    CONSTRAINT uq_related UNIQUE (parent_id, child_id)
);

CREATE TRIGGER JAVAZON.trg_related_product_pk
BEFORE INSERT ON JAVAZON.RELATED_PRODUCT
FOR EACH ROW
BEGIN
    IF :NEW.related_id IS NULL THEN
        :NEW.related_id := JAVAZON.RELATED_PRODUCT_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 10 . Shared_Images (polymorphic: product, category, producer, etc.)
-- =====================================================
CREATE TABLE JAVAZON.Shared_Images    (
    image_id       NUMBER PRIMARY KEY,
    images_name           VARCHAR2(200),
    images_path           VARCHAR2(500) NOT NULL,
    alt_text       VARCHAR2(255),
    parent_id      NUMBER NOT NULL,
    parent_type    VARCHAR2(50) NOT NULL, -- 'PRODUCT', 'CATEGORY', 'PRODUCER', etc.
    sort_order     NUMBER DEFAULT 0,

    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     VARCHAR2(100),
    updated_on     TIMESTAMP,
    updated_by     VARCHAR2(100)
);

CREATE TRIGGER JAVAZON.trg_images_pk
BEFORE INSERT ON JAVAZON.Shared_Images
FOR EACH ROW
BEGIN
    IF :NEW.image_id IS NULL THEN
        :NEW.image_id := JAVAZON.IMAGES_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- 12. DISCOUNTS (polymorphic too)
-- =====================================================
CREATE TABLE JAVAZON.DISCOUNTS (
    discount_id    NUMBER PRIMARY KEY,
    title          VARCHAR2(200) NOT NULL,
    description    VARCHAR2(1000),
    discount_percent NUMBER(5,2),
    start_date     TIMESTAMP,
    end_date       TIMESTAMP,
    parent_id      NUMBER NOT NULL,
    parent_type    VARCHAR2(50) NOT NULL, -- 'PRODUCT', 'CATEGORY', etc.
    active      CHAR(1) DEFAULT 'Y' CHECK (active IN ('Y','N')),

    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     VARCHAR2(100),
    updated_on     TIMESTAMP,
    updated_by     VARCHAR2(100)
);

CREATE TRIGGER JAVAZON.trg_discounts_pk
BEFORE INSERT ON JAVAZON.DISCOUNTS
FOR EACH ROW
BEGIN
    IF :NEW.discount_id IS NULL THEN
        :NEW.discount_id := JAVAZON.DISCOUNTS_SEQ.NEXTVAL;
    END IF;
END;


-- =====================================================
-- END OF SCRIPT
-- =====================================================

COMMIT;

-- Optional: insert a test user
-- INSERT INTO JAVAZON.USERS (username, email, password, first_name, last_name)
-- VALUES ('admin', 'admin@javazon.com', 'hashed_password_here', 'Admin', 'User');