-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."NavigationItem"
(
    "Id" Serial NOT NULL,
    "ParentId" integer,
    "IsMenuLeaf" boolean NOT NULL,
    "TextResourceKey" character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "LanguageId" integer,
    "MenuIcon" character varying(100) COLLATE pg_catalog."default",
    "PageUrl" text COLLATE pg_catalog."default",
    CONSTRAINT "PK_NavigationItem" PRIMARY KEY ("Id"),
    CONSTRAINT "FK_ParentId" FOREIGN KEY ("ParentId")
        REFERENCES public."NavigationItem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_TextResources" FOREIGN KEY ("LanguageId", "TextResourceKey")
        REFERENCES public."TextResources" ("LanguageId", "ResourceKey") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- changeset liquibase:2
INSERT INTO public."NavigationItem" ("Id", "ParentId", "IsMenuLeaf", "TextResourceKey", "LanguageId", "MenuIcon", "PageUrl") VALUES (1, NULL, true, 'TemplateSystem_Menu_Root', 1, 'TemplateSystem', '/module_TemplateSystem_index');