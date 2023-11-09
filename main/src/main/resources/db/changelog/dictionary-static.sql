-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."DictionaryStatic"
(
    "Id" Serial NOT NULL,
    "ParentId" integer,
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "TextResourceKey" character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "LanguageId" integer,
    "ImageValue" bytea,
    "MaterialUIIconName" text COLLATE pg_catalog."default",
    CONSTRAINT "PK_dictionaryStatic" PRIMARY KEY ("Id"),
    CONSTRAINT "FK_ParentId" FOREIGN KEY ("ParentId")
        REFERENCES public."DictionaryStatic" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_TextResources" FOREIGN KEY ("LanguageId", "TextResourceKey")
        REFERENCES public."TextResources" ("LanguageId", "ResourceKey") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
-- changeset liquibase:2
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (1, NULL, 'Dictionaries', 'TemplateSystem_DictionaryStatic_Dictionary', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (2, 1, 'OperandsDataSourceType', 'TemplateSystem_DictionaryStatic_OperandsDataSourceType', 1, NULL, NULL);

-- changeset liquibase:3
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (3, 1, 'AccessIdType', 'TemplateSystem_DictionaryStatic_AccessIdType', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (4, 3, 'AccessIdType_RFIDCard', 'TemplateSystem_DictionaryStatic_AccessIdType_RFIDCard', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (5, 3, 'AccessIdType_NFCLabel', 'TemplateSystem_DictionaryStatic_AccessIdType_NFCLabel', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (6, 3, 'AccessIdType_1xBarcode', 'TemplateSystem_DictionaryStatic_AccessIdType_1xBarcode', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (7, 3, 'AccessIdType_2xBarcode', 'TemplateSystem_DictionaryStatic_AccessIdType_2xBarcode',1, NULL, NULL); 
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (8, 3, 'AccessIdType_PinCode', 'TemplateSystem_DictionaryStatic_AccessIdType_PinCode', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (9, 3, 'AccessIdType_StateNumber', 'TemplateSystem_DictionaryStatic_AccessIdType_StateNumber', 1, NULL, NULL);
INSERT INTO public."DictionaryStatic" ("Id", "ParentId", "Name", "TextResourceKey", "LanguageId", "ImageValue", "MaterialUIIconName") VALUES (10, 3, 'AccessIdType_Face', 'TemplateSystem_DictionaryStatic_AccessIdType_Face', 1, NULL, NULL);









