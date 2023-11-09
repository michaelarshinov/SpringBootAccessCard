-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."AttendanceObject"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "Path" text COLLATE pg_catalog."default" NOT NULL,
    "ACSId" uuid NOT NULL,
    CONSTRAINT "AttendanceObject_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "ACSId_FK" FOREIGN KEY ("ACSId")
        REFERENCES public."TemplateSystem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)