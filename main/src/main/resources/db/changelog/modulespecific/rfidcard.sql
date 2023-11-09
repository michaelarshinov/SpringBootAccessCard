-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."RFIDCard"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Status" smallint NOT NULL,
    "ACSName" text COLLATE pg_catalog."default" NOT NULL,
    "ACSId" uuid NOT NULL,
    "ReadableCardNumber" text COLLATE pg_catalog."default" NOT NULL,
    "SerialCardNumber" text COLLATE pg_catalog."default" NOT NULL,
    "CardNumberWithoutConversion" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "RFIDCard_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "CardNumbers_U" UNIQUE ("ReadableCardNumber", "SerialCardNumber"),
    CONSTRAINT "TemplateSystemId_FK" FOREIGN KEY ("ACSId")
        REFERENCES public."TemplateSystem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);