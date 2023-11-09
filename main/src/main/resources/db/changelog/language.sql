-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."Language"
(
    "Id" Serial NOT NULL,
    "Languages" character varying(100) COLLATE pg_catalog."default" NOT NULL,
    "Culture" character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "PK_Language" PRIMARY KEY ("Id")
);

-- changeset liquibase:2
INSERT INTO public."Language" ("Id", "Languages", "Culture") VALUES (1, 'Русский', 'RU-ru');
INSERT INTO public."Language" ("Id", "Languages", "Culture") VALUES (2, 'Еnglish', 'EN-en');