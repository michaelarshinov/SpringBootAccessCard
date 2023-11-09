-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."AccessEvent"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "AccessIdType" integer NOT NULL,
    "AccessId" text COLLATE pg_catalog."default" NOT NULL,
    "ACSId" uuid NOT NULL,
    "Date" timestamp with time zone NOT NULL,
    "AccessPointId" uuid NOT NULL,
    CONSTRAINT "AccessEvent_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "ACSId_FK" FOREIGN KEY ("ACSId")
        REFERENCES public."TemplateSystem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "AccessPointId_FK" FOREIGN KEY ("AccessPointId")
        REFERENCES public."AccessPoint" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
