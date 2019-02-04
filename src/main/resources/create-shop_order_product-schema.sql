CREATE TABLE order_product (
                order_id uuid NULL,
                product_id uuid NULL,
                CONSTRAINT order_product_order_fk FOREIGN KEY (order_id) REFERENCES shop_order(id) ON DELETE CASCADE,
                CONSTRAINT order_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES tproducts(id)
                ON DELETE CASCADE
                );
