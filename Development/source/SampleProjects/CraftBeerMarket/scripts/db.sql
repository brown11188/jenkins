-- Create table users
CREATE TABLE users
(
  username character varying(45) NOT NULL,
  password character varying(253),
  enabled boolean,
  CONSTRAINT users_pkey PRIMARY KEY (username )
)


-- Create table user_roles
CREATE TABLE user_roles
(
  user_role_id serial NOT NULL,
  username character varying(45) NOT NULL,
  role character varying(45),
  CONSTRAINT user_roles_pkey PRIMARY KEY (user_role_id ),
  CONSTRAINT user_roles_username_fkey FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_roles_role_username_key UNIQUE (role , username )
)

-- Create table user_info
CREATE TABLE user_info
(
  username character varying(253) NOT NULL,
  first_name character varying(253),
  last_name character varying(253),
  email character varying(253),
  address character varying(253),
  CONSTRAINT user_info_pkey PRIMARY KEY (username ),
  CONSTRAINT user_info_username_fkey FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

-- Create table customers
CREATE TABLE customers
(
  username character varying(253) NOT NULL,
  first_name character varying(253),
  last_name character varying(253),
  email character varying(253),
  address character varying(253),
  CONSTRAINT customers_pkey PRIMARY KEY (username )
)

-- Insert data for table users
INSERT INTO users(username,password,enabled)
VALUES ('varick','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

-- Insert data for table user_roles
INSERT INTO user_roles (username, role)
VALUES ('varick', 'ROLE_ADMIN');

-- Create table category
CREATE TABLE category
(
  id serial NOT NULL,	
  name character varying(45) NOT NULL,
  description text,
  CONSTRAINT category_pkey PRIMARY KEY (id )
)

-- Create table beer
CREATE TABLE beer
(
  id serial NOT NULL,
  name character varying(45),
  manufacturer character varying(100),
  country character varying(45),
  price numeric,
  description text,
  archived boolean,
  category_id bigint NOT NULL,
  CONSTRAINT beer_pkey PRIMARY KEY (id),
  CONSTRAINT beer_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

---Passport table
CREATE TABLE passport
(
  id serial NOT NULL,
  username character varying(45) NOT NULL,
  "beer_id" integer,
  CONSTRAINT "Passport_pkey" PRIMARY KEY (id ),
  CONSTRAINT "Passport_beerID_fkey" FOREIGN KEY ("beer_id")
      REFERENCES beer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Passport_username_fkey" FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
----Access token table
CREATE TABLE access_token
(
  id serial NOT NULL,
  token character varying(253),
  username character varying(45),
  token_type character varying(45),
  expires_time timestamp without time zone,
  CONSTRAINT access_token_pkey PRIMARY KEY (id ),
  CONSTRAINT access_token_username_fkey FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
