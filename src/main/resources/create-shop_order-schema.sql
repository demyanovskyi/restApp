CREATE TABLE shop_order (
                id uuid NOT NULL,
                user_id uuid NOT NULL,
                CONSTRAINT order_pkey PRIMARY KEY (id),CONSTRAINT order_users_fk FOREIGN KEY (user_id) REFERENCES users(id))
                ;
