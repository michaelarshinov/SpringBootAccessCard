-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."AccessPoint"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "AttendanceObjectForExitId" uuid,
    "AttendanceObjectForEntranceId" uuid,
    "ACSId" uuid NOT NULL,
    CONSTRAINT "AccessPoint_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "AttendanceObjectForExitId_FK" FOREIGN KEY ("AttendanceObjectForExitId")
        REFERENCES public."AttendanceObject" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "AttendanceObjectForEntranceId_FK" FOREIGN KEY ("AttendanceObjectForEntranceId")
        REFERENCES public."AttendanceObject" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "ACSId_FK" FOREIGN KEY ("ACSId")
        REFERENCES public."TemplateSystem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)