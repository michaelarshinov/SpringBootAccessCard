-- liquibase formatted sql
--подключение модулей postgres

-- changeset liquibase:1 splitStatements:false
create extension if not exists pg_trgm;

-- Создание функции объединения полей для поиска 

-- changeset liquibase:2 splitStatements:false
create or replace function fullvisiting(ReadableCardNumber text, SerialCardNumber text, CardNumberWithoutConversion text)
returns text
language plpgsql
immutable
as $$
begin
  return regexp_replace(
    lower(
      trim(
	ReadableCardNumber::text || ' ' || SerialCardNumber::text || ' ' || CardNumberWithoutConversion::text
      )
    ),
    'ё',
    'е',
    'g'
   );
exception
  when others then raise exception '%', sqlerrm;
end;
$$; 

-- changeset liquibase:3 splitStatements:false
create or replace function fullvisitingpage(ReadableCardNumber text, SerialCardNumber text, CardNumberWithoutConversion text)
returns text
language plpgsql
immutable
as $$
begin
  return regexp_replace(
    lower(
      trim(
	ReadableCardNumber::text || ' ' || SerialCardNumber::text || ' ' || CardNumberWithoutConversion::text
      )
    ),
    'ё',
    'е',
    'g'
   );
exception
  when others then raise exception '%', sqlerrm;
end;
$$; 

-- Создание функции для перевода текста на другую раскладку (анг-рус)
-- changeset liquibase:4 splitStatements:false
CREATE OR REPLACE FUNCTION public.replace_unaccent_eng(text)
RETURNS text
LANGUAGE plpgsql
immutable
AS $function$
DECLARE
    input_string text := $1;
BEGIN
input_string := translate(input_string, 'f', 'а');
input_string := translate(input_string, ',', 'б');
input_string := translate(input_string, 'd', 'в');
input_string := translate(input_string, 'u', 'г');
input_string := translate(input_string, 'l', 'д');
input_string := translate(input_string, 't', 'е');
input_string := translate(input_string, '`', 'ё');
input_string := translate(input_string, ';', 'ж');
input_string := translate(input_string, 'p', 'з');
input_string := translate(input_string, 'b', 'и');
input_string := translate(input_string, 'q', 'й');
input_string := translate(input_string, 'r', 'к');
input_string := translate(input_string, 'k', 'л');
input_string := translate(input_string, 'v', 'м');
input_string := translate(input_string, 'y', 'н');
input_string := translate(input_string, 'j', 'о');
input_string := translate(input_string, 'g', 'п');
input_string := translate(input_string, 'h', 'р');
input_string := translate(input_string, 'c', 'с');
input_string := translate(input_string, 'n', 'т');
input_string := translate(input_string, 'e', 'у');
input_string := translate(input_string, 'a', 'ф');
input_string := translate(input_string, '[', 'х');
input_string := translate(input_string, 'w', 'ц');
input_string := translate(input_string, 'x', 'ч');
input_string := translate(input_string, 'i', 'ш');
input_string := translate(input_string, 'o', 'щ');
input_string := translate(input_string, ']', 'ъ');
input_string := translate(input_string, 's', 'ы');
input_string := translate(input_string, 'm', 'ь');
input_string := translate(input_string, '''', 'э');
input_string := translate(input_string, '.', 'ю');
input_string := translate(input_string, 'z', 'я');

input_string := translate(input_string, 'F', 'А');
input_string := translate(input_string, ',', 'Б');
input_string := translate(input_string, 'D', 'В');
input_string := translate(input_string, 'U', 'Г');
input_string := translate(input_string, 'L', 'Д');
input_string := translate(input_string, 'T', 'Е');
input_string := translate(input_string, '`', 'Ё');
input_string := translate(input_string, ';', 'Ж');
input_string := translate(input_string, 'P', 'З');
input_string := translate(input_string, 'B', 'И');
input_string := translate(input_string, 'Q', 'Й');
input_string := translate(input_string, 'R', 'К');
input_string := translate(input_string, 'K', 'Л');
input_string := translate(input_string, 'V', 'М');
input_string := translate(input_string, 'Y', 'Н');
input_string := translate(input_string, 'J', 'О');
input_string := translate(input_string, 'G', 'П');
input_string := translate(input_string, 'H', 'Р');
input_string := translate(input_string, 'C', 'С');
input_string := translate(input_string, 'N', 'Т');
input_string := translate(input_string, 'E', 'У');
input_string := translate(input_string, 'A', 'Ф');
input_string := translate(input_string, '[', 'Х');
input_string := translate(input_string, 'W', 'Ц');
input_string := translate(input_string, 'X', 'Ч');
input_string := translate(input_string, 'I', 'Ш');
input_string := translate(input_string, 'O', 'Щ');
input_string := translate(input_string, ']', 'Ъ');
input_string := translate(input_string, 'S', 'Ы');
input_string := translate(input_string, 'M', 'Ь');
input_string := translate(input_string, '''', 'Э');
input_string := translate(input_string, '.', 'Ю');
input_string := translate(input_string, 'Z', 'Я');
return input_string;
END;
$function$ ;

-- Создание функции для перевода текста на другую раскладку (рус-англ)
-- changeset liquibase:5 splitStatements:false
CREATE OR REPLACE FUNCTION public.replace_unaccent_rus(text)
RETURNS text
LANGUAGE plpgsql
immutable
AS $function$
DECLARE
    input_string text := $1;
BEGIN
input_string := translate(input_string, 'а', 'f');
input_string := translate(input_string, 'б', ',');
input_string := translate(input_string, 'в', 'd');
input_string := translate(input_string, 'г', 'u');
input_string := translate(input_string, 'д', 'l');
input_string := translate(input_string, 'е', 't');
input_string := translate(input_string, 'ё', '`');
input_string := translate(input_string, 'ж', ';');
input_string := translate(input_string, 'з', 'p');
input_string := translate(input_string, 'и', 'b');
input_string := translate(input_string, 'й', 'q');
input_string := translate(input_string, 'к', 'r');
input_string := translate(input_string, 'л', 'k');
input_string := translate(input_string, 'м', 'v');
input_string := translate(input_string, 'н', 'y');
input_string := translate(input_string, 'о', 'j');
input_string := translate(input_string, 'п', 'g');
input_string := translate(input_string, 'р', 'h');
input_string := translate(input_string, 'с', 'c');
input_string := translate(input_string, 'т', 'n');
input_string := translate(input_string, 'у', 'e');
input_string := translate(input_string, 'ф', 'a');
input_string := translate(input_string, 'х', '[');
input_string := translate(input_string, 'ц', 'w');
input_string := translate(input_string, 'ч', 'x');
input_string := translate(input_string, 'ш', 'i');
input_string := translate(input_string, 'щ', 'o');
input_string := translate(input_string, 'ъ', ']');
input_string := translate(input_string, 'ы', 's');
input_string := translate(input_string, 'ь', 'm');
input_string := translate(input_string, 'э', '''');
input_string := translate(input_string, 'ю', '.');
input_string := translate(input_string, 'я', 'z');


input_string := translate(input_string, 'А', 'F');
input_string := translate(input_string, 'Б', ',');
input_string := translate(input_string, 'В', 'D');
input_string := translate(input_string, 'Г', 'U');
input_string := translate(input_string, 'Д', 'L');
input_string := translate(input_string, 'Е', 'T');
input_string := translate(input_string, 'Ё', '`');
input_string := translate(input_string, 'Ж', ';');
input_string := translate(input_string, 'З', 'P');
input_string := translate(input_string, 'И', 'B');
input_string := translate(input_string, 'Й', 'Q');
input_string := translate(input_string, 'К', 'R');
input_string := translate(input_string, 'Л', 'K');
input_string := translate(input_string, 'М', 'V');
input_string := translate(input_string, 'Н', 'Y');
input_string := translate(input_string, 'О', 'J');
input_string := translate(input_string, 'П', 'G');
input_string := translate(input_string, 'Р', 'H');
input_string := translate(input_string, 'С', 'C');
input_string := translate(input_string, 'Т', 'N');
input_string := translate(input_string, 'У', 'E');
input_string := translate(input_string, 'Ф', 'A');
input_string := translate(input_string, 'Х', '[');
input_string := translate(input_string, 'Ц', 'W');
input_string := translate(input_string, 'Ч', 'X');
input_string := translate(input_string, 'Ш', 'I');
input_string := translate(input_string, 'Щ', 'O');
input_string := translate(input_string, 'Ъ', ']');
input_string := translate(input_string, 'Ы', 'S');
input_string := translate(input_string, 'Ь', 'M');
input_string := translate(input_string, 'Э', '''');
input_string := translate(input_string, 'Ю', '.');
input_string := translate(input_string, 'Я', 'Z');

return input_string;
END;
$function$ ;

-- Создание функций для перевода текста на другой язык транслитом (анг-рус)
-- changeset liquibase:6 splitStatements:false
create or replace function public.replace_unaccent_eng_translit_first(text)
returns text
language plpgsql
immutable
as $function$
declare
	input_string text := $1;
begin
input_string := regexp_replace(input_string, 'shh', 'щ', 'g');
input_string := regexp_replace(input_string, 'Shh', 'Щ', 'g');
input_string := regexp_replace(input_string, 'SHH', 'Щ', 'g');
return input_string;
end;
$function$;

-- changeset liquibase:7 splitStatements:false
create or replace function public.replace_unaccent_eng_translit_second(text)
returns text
language plpgsql
immutable
as $function$
declare
	input_string text := $1;
begin
input_string := regexp_replace(input_string, 'ya', 'я', 'g');
input_string := regexp_replace(input_string, 'yo', 'ё', 'g');
input_string := regexp_replace(input_string, 'yu', 'ю', 'g');
input_string := regexp_replace(input_string, 'zh', 'ж', 'g');
input_string := regexp_replace(input_string, 'ch', 'ч', 'g');
input_string := regexp_replace(input_string, 'sh', 'ш', 'g');
input_string := regexp_replace(input_string, 'kh', 'х', 'g');
input_string := regexp_replace(input_string, 'e`', 'э', 'g');
input_string := regexp_replace(input_string, '``', 'ъ', 'g');
input_string := regexp_replace(input_string, 'cz', 'ц', 'g');

input_string := regexp_replace(input_string, 'Ya', 'Я', 'g');
input_string := regexp_replace(input_string, 'YA', 'Я', 'g');
input_string := regexp_replace(input_string, 'Yo', 'Ё', 'g');
input_string := regexp_replace(input_string, 'YO', 'Ё', 'g');
input_string := regexp_replace(input_string, 'Yu', 'Ю', 'g');
input_string := regexp_replace(input_string, 'YU', 'Ю', 'g');
input_string := regexp_replace(input_string, 'Zh', 'Ж', 'g');
input_string := regexp_replace(input_string, 'ZH', 'Ж', 'g');
input_string := regexp_replace(input_string, 'Ch', 'Ч', 'g');
input_string := regexp_replace(input_string, 'CH', 'Ч', 'g');
input_string := regexp_replace(input_string, 'Sh', 'Ш', 'g');
input_string := regexp_replace(input_string, 'SH', 'Ш', 'g');
input_string := regexp_replace(input_string, 'Kh', 'Х', 'g');
input_string := regexp_replace(input_string, 'KH', 'Х', 'g');
input_string := regexp_replace(input_string, 'E`', 'Э', 'g');
input_string := regexp_replace(input_string, 'Cz', 'Ц', 'g');
input_string := regexp_replace(input_string, 'CZ', 'Ц', 'g');
return input_string;
end;
$function$;

-- changeset liquibase:8 splitStatements:false
create or replace function public.replace_unaccent_eng_translit_third(text)
returns text
language plpgsql
immutable
as $function$
declare
	input_string text := $1;
begin
input_string := translate(input_string, 'a', 'а');
input_string := translate(input_string, 'b', 'б');
input_string := translate(input_string, 'c', 'ц');
input_string := translate(input_string, 'd', 'д');
input_string := translate(input_string, 'e', 'е');
input_string := translate(input_string, 'f', 'ф');
input_string := translate(input_string, 'g', 'г');
input_string := translate(input_string, 'h', 'х');
input_string := translate(input_string, 'i', 'и');
input_string := translate(input_string, 'j', 'й');
input_string := translate(input_string, 'k', 'к');
input_string := translate(input_string, 'l', 'л');
input_string := translate(input_string, 'm', 'м');
input_string := translate(input_string, 'n', 'н');
input_string := translate(input_string, 'o', 'о');
input_string := translate(input_string, 'p', 'п');
input_string := translate(input_string, 'q', 'щ');
input_string := translate(input_string, 'r', 'р');
input_string := translate(input_string, 's', 'с');
input_string := translate(input_string, 't', 'т');
input_string := translate(input_string, 'u', 'у');
input_string := translate(input_string, 'v', 'в');
input_string := translate(input_string, 'w', 'ы');
input_string := translate(input_string, 'x', 'х');
input_string := translate(input_string, 'y', 'ы');
input_string := translate(input_string, 'z', 'з');

input_string := translate(input_string, 'A', 'А');
input_string := translate(input_string, 'B', 'Б');
input_string := translate(input_string, 'C', 'Ц');
input_string := translate(input_string, 'D', 'Д');
input_string := translate(input_string, 'E', 'Е');
input_string := translate(input_string, 'F', 'Ф');
input_string := translate(input_string, 'G', 'Г');
input_string := translate(input_string, 'H', 'Х');
input_string := translate(input_string, 'I', 'И');
input_string := translate(input_string, 'J', 'Й');
input_string := translate(input_string, 'K', 'К');
input_string := translate(input_string, 'L', 'Л');
input_string := translate(input_string, 'M', 'М');
input_string := translate(input_string, 'N', 'Н');
input_string := translate(input_string, 'O', 'О');
input_string := translate(input_string, 'P', 'П');
input_string := translate(input_string, 'Q', 'Щ');
input_string := translate(input_string, 'R', 'Р');
input_string := translate(input_string, 'S', 'С');
input_string := translate(input_string, 'T', 'Т');
input_string := translate(input_string, 'U', 'У');
input_string := translate(input_string, 'V', 'В');
input_string := translate(input_string, 'W', 'Ы');
input_string := translate(input_string, 'X', 'Х');
input_string := translate(input_string, 'Y', 'Ы');
input_string := translate(input_string, 'Z', 'З');
return input_string;
end;
$function$;

-- changeset liquibase:9 splitStatements:false
CREATE OR REPLACE FUNCTION public.replace_unaccent_eng_translit(text)
RETURNS text
LANGUAGE plpgsql
immutable
AS $function$
BEGIN
return replace_unaccent_eng_translit_third(
	replace_unaccent_eng_translit_second(
		replace_unaccent_eng_translit_first($1)
	)
);
END;
$function$ ;


-- Создание функции для перевода текста на другой язык транслитом (рус-англ)
-- changeset liquibase:10 splitStatements:false
CREATE OR REPLACE FUNCTION public.replace_unaccent_rus_translit(text)
RETURNS text
LANGUAGE plpgsql
immutable
AS $function$
DECLARE
    input_string text := $1;
BEGIN
input_string := translate(input_string, 'а', 'a');
input_string := translate(input_string, 'б', 'b');
input_string := translate(input_string, 'в', 'v');
input_string := translate(input_string, 'г', 'g');
input_string := translate(input_string, 'д', 'd');
input_string := translate(input_string, 'е', 'e');
input_string := regexp_replace(input_string, 'ё', 'yo', 'g');
input_string := regexp_replace(input_string, 'ж', 'zh', 'g');
input_string := translate(input_string, 'з', 'z');
input_string := translate(input_string, 'и', 'i');
input_string := translate(input_string, 'й', 'j');
input_string := translate(input_string, 'к', 'k');
input_string := translate(input_string, 'л', 'l');
input_string := translate(input_string, 'м', 'm');
input_string := translate(input_string, 'н', 'n');
input_string := translate(input_string, 'о', 'o');
input_string := translate(input_string, 'п', 'p');
input_string := translate(input_string, 'р', 'r');
input_string := translate(input_string, 'с', 's');
input_string := translate(input_string, 'т', 't');
input_string := translate(input_string, 'у', 'u');
input_string := translate(input_string, 'ф', 'f');
input_string := translate(input_string, 'х', 'x');
input_string := translate(input_string, 'ц', 'c');
input_string := regexp_replace(input_string, 'ч', 'ch', 'g');
input_string := regexp_replace(input_string, 'ш', 'sh', 'g');
input_string := regexp_replace(input_string, 'щ', 'shh', 'g');
input_string := regexp_replace(input_string, 'ъ', '``', 'g');
input_string := regexp_replace(input_string, 'ы', 'y`', 'g');
input_string := translate(input_string, 'ь', '`');
input_string := regexp_replace(input_string, 'э', 'e`', 'g');
input_string := regexp_replace(input_string, 'ю', 'yu', 'g');
input_string := regexp_replace(input_string, 'я', 'ya', 'g');


input_string := translate(input_string, 'А', 'A'); 
input_string := translate(input_string, 'Б', 'B'); 
input_string := translate(input_string, 'В', 'V'); 
input_string := translate(input_string, 'Г', 'G'); 
input_string := translate(input_string, 'Д', 'D'); 
input_string := translate(input_string, 'Е', 'E'); 
input_string := regexp_replace(input_string, 'Ё', 'Yo', 'g'); 
input_string := regexp_replace(input_string, 'Ж', 'Zh', 'g'); 
input_string := translate(input_string, 'З', 'Z'); 
input_string := translate(input_string, 'И', 'I'); 
input_string := translate(input_string, 'Й', 'J'); 
input_string := translate(input_string, 'К', 'K'); 
input_string := translate(input_string, 'Л', 'L'); 
input_string := translate(input_string, 'М', 'M'); 
input_string := translate(input_string, 'Н', 'N'); 
input_string := translate(input_string, 'О', 'O'); 
input_string := translate(input_string, 'П', 'P'); 
input_string := translate(input_string, 'Р', 'R'); 
input_string := translate(input_string, 'С', 'S'); 
input_string := translate(input_string, 'Т', 'T'); 
input_string := translate(input_string, 'У', 'U'); 
input_string := translate(input_string, 'Ф', 'F'); 
input_string := translate(input_string, 'Х', 'X'); 
input_string := translate(input_string, 'Ц', 'C'); 
input_string := regexp_replace(input_string, 'Ч', 'Ch', 'g'); 
input_string := regexp_replace(input_string, 'Ш', 'Sh', 'g'); 
input_string := regexp_replace(input_string, 'Щ', 'Shh', 'g'); 
input_string := regexp_replace(input_string, 'Ъ', '``', 'g'); 
input_string := regexp_replace(input_string, 'Ы', 'Y`', 'g'); 
input_string := translate(input_string, 'Ь', '`'); 
input_string := regexp_replace(input_string, 'Э', 'E`', 'g'); 
input_string := regexp_replace(input_string, 'Ю', 'Yu', 'g'); 
input_string := regexp_replace(input_string, 'Я', 'Ya', 'g'); 
return input_string;
END;
$function$ ;
