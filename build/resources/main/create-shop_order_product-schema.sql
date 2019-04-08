CREATE TABLE public.shop_order_product (
	shop_order uuid NOT NULL,
	product uuid NOT NULL,
	CONSTRAINT order_product_order_fk FOREIGN KEY (shop_order) REFERENCES shop_order(id),
	CONSTRAINT order_product_product_id_fkey FOREIGN KEY (product) REFERENCES product(id) ON DELETE CASCADE
);