CREATE TABLE public.users (
	id uuid NOT NULL,
	"name" varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);