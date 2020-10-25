-- Table: public.cases

-- DROP TABLE public.cases;

CREATE TABLE public.cases
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( CYCLE INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    case_id integer,
    customer_id integer,
    provider_id integer,
    created_error_code integer,
    status character(50) COLLATE pg_catalog."default",
    ticket_creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    product_name character(50) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE public.cases
    OWNER to postgres;
	
	
	

--*******************************
--- Insert new row for test ---
--*******************************
/*
insert into cases(case_id, 
				  customer_id, 
				  provider_id, 
				  created_error_code, 
				  status, 
				  ticket_creation_date, 
				  last_modified_date, 
				  product_name) 
values(1, 818591, 6111, 324, 'Closed', '3/14/2019 16:30', '3/17/2019 03:41', 'BLUE')
*/	