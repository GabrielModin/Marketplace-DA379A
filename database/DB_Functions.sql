create or replace function f_getAllProducts()
    returns setof products as
    $$
    begin
    return query (select * from products);
    end;
    $$

language plpgsql;

create or replace function f_addproducts(f_price double precision, f_date date, f_type varchar, f_color varchar, f_condition varchar, f_status varchar, f_seller varchar, f_name varchar)
    returns int as
    $$
    declare ok int;
    begin
        insert into products(price, date, type, color, condition, status, seller, name)
        values (f_price, f_date, f_type, f_color, f_condition, f_status, f_seller, f_name);
        ok = 1;
        return ok;
    EXCEPTION when too_many_arguments then
        ok = -1;
        return ok;
    end;
    $$

language plpgsql;

CREATE OR REPLACE FUNCTION f_register_user(
    username VARCHAR,
    email VARCHAR,
    password VARCHAR,
    birth_date DATE,
    first_name VARCHAR,
    last_name VARCHAR)
    RETURNS BOOLEAN AS $$
    BEGIN
        INSERT INTO users VALUES (username, email, password, birth_date, first_name, last_name);
        RETURN true;
    EXCEPTION WHEN unique_violation THEN
        RETURN false;
    END;
    $$ LANGUAGE plpgsql;
	

CREATE OR REPLACE FUNCTION f_delete_user(uname VARCHAR)
    AS $$
    BEGIN
        DELETE FROM users WHERE uname = users.username;
    END;
    $$ LANGUAGE plpgsql;