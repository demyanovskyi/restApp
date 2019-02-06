CREATE TABLE public.shop_order (
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	CONSTRAINT shop_order_pkey PRIMARY KEY (id),
	CONSTRAINT shop_order_users_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
