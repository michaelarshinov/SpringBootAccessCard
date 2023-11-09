-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."permission_navigationItem"
(
    "PermissionId" integer NOT NULL,
    "NavigationItemId" integer NOT NULL,
    CONSTRAINT "FK_NavigationItemId" FOREIGN KEY ("NavigationItemId")
        REFERENCES public."NavigationItem" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_PermissionId" FOREIGN KEY ("PermissionId")
        REFERENCES public."Permission" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- changeset liquibase:2
INSERT INTO public."permission_navigationItem" ("PermissionId", "NavigationItemId") VALUES (1, 1);