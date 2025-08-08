---- Database: mld
--
---- DROP DATABASE IF EXISTS mld;
--
--CREATE DATABASE mld
--    WITH
--    OWNER = xaq
--    ENCODING = 'UTF8'
--    LC_COLLATE = 'C'
--    LC_CTYPE = 'C'
--    LOCALE_PROVIDER = 'libc'
--    TABLESPACE = pg_default
--    CONNECTION LIMIT = -1
--    IS_TEMPLATE = False;

CREATE TABLE transactions (
    id uuid DEFAULT gen_random_uuid(),
    transaction_time VARCHAR NOT NULL, -- can be "TIMESTAMP" or "TIMESTAMPTZ" with time zone
    from_bank VARCHAR NOT NULL,
    from_account VARCHAR NOT NULL,
    to_bank VARCHAR NOT NULL,
	to_account VARCHAR NOT NULL,
	received_amount NUMERIC NOT NULL,
	received_currency VARCHAR NOT NULL,
	pay_amount NUMERIC NOT NULL,
	pay_currency VARCHAR NOT NULL,
	pay_format VARCHAR NOT NULL,
	is_laundering BOOLEAN DEFAULT NULL,
    PRIMARY KEY (id)
);

---- Insert a single data row
--insert into transactions(transaction_time, from_bank, from_account, to_bank, to_account, received_amount, received_currency,
--pay_amount, pay_currency, pay_format, is_laundering) values ('2022-09-01 00:20','010','8000EBD30','010',
--'8000EBD30',3697.34,'US Dollar',3697.34,'US Dollar','Reinvestment',TRUE)
--
---- update a data row
--update transactions set is_laundering=FALSE where id='44d506b7-a866-4bff-8da5-0fe8ccc75897';
