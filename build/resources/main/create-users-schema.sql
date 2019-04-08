

CREATE TABLE public.users (
	id uuid NOT NULL,
	name varchar NOT NULL,
	"password" varchar NOT NULL,
	"role" varchar(100) NOT NULL,
	salt varchar(100) NOT NULL,
	email varchar(100) NULL,
	restore_hash varchar(100) NULL,
	validity_period timestamp NULL,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);