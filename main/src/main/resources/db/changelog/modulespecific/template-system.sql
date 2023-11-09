-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."TemplateSystem"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "Version" text COLLATE pg_catalog."default" NOT NULL,
    "Enabled" boolean DEFAULT true,
    CONSTRAINT "PhysicalTemplateSystem_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "NameVersion_U" UNIQUE ("Name", "Version")
)

-- changeset liquibase:2
alter table public."TemplateSystem" add column IF NOT EXISTS "Logo" text;

-- changeset liquibase:3
alter table public."TemplateSystem" add column IF NOT EXISTS "Brand" text;