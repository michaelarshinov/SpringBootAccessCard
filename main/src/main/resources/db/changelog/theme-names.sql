-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."ThemeNames"
(
    "ThemeId" integer NOT NULL,
    "NameResourceKey" character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "LanguageId" integer NOT NULL,
    CONSTRAINT "PK_ThemeNames" PRIMARY KEY ("ThemeId"),
    CONSTRAINT "FK_TextResources" FOREIGN KEY ("LanguageId", "NameResourceKey")
        REFERENCES public."TextResources" ("LanguageId", "ResourceKey") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_Theme" FOREIGN KEY ("ThemeId")
        REFERENCES public."Theme" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- changeset liquibase:2
INSERT INTO public."ThemeNames" ("ThemeId", "NameResourceKey", "LanguageId") VALUES (1, 'ThemeTemplateSystem_Name_Light', 1);
INSERT INTO public."ThemeNames" ("ThemeId", "NameResourceKey", "LanguageId") VALUES (2, 'ThemeTemplateSystem_Name_Dark', 1);