
CREATE TABLE public.product (
	id uuid NOT NULL,
	product_name varchar(100) NOT NULL,
	price numeric NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);
