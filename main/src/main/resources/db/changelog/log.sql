-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."Log"
(
    "Id" Serial NOT NULL,
    "Level" character varying(20) COLLATE pg_catalog."default",
    "Date" timestamp with time zone,
    "Logger" character varying(200) COLLATE pg_catalog."default",
    "MethodName" character varying(200) COLLATE pg_catalog."default",
    "Message" character varying(1000) COLLATE pg_catalog."default",
    "ExecTimeInMilliseconds" integer,
    "Exception" character varying(1000) COLLATE pg_catalog."default",
    "Meta" text COLLATE pg_catalog."default",
    CONSTRAINT "PK_Log" PRIMARY KEY ("Id")
);
