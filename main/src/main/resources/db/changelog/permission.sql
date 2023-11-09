-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."Permission"
(
    "Id" Serial NOT NULL,
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "PK_Permission" PRIMARY KEY ("Id")
);

-- changeset liquibase:2
INSERT INTO public."Permission" ("Id", "Name") VALUES (1, 'api/Module/test');