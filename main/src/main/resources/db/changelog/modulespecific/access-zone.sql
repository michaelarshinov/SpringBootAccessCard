-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."AccessZone"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "ACSId" uuid,
    CONSTRAINT "AccessZone_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "NameACSId_U" UNIQUE ("Name", "ACSId"),
    CONSTRAINT "PACSId_FK" FOREIGN KEY ("ACSId")
        REFERENCES public."TemplateSystem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)