-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE IF NOT EXISTS public."TextResources"
(
    "ResourceKey" character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "LanguageId" integer NOT NULL,
    "TextValue" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "PK_TextResources" PRIMARY KEY ("ResourceKey", "LanguageId"),
    CONSTRAINT "FK_Language" FOREIGN KEY ("LanguageId")
        REFERENCES public."Language" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- changeset liquibase:2
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_OperandsDataSourceType', 1, 'Тип подключения операнда');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_OperandsDataSourceType', 2, 'Connection type operands');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('ThemeTemplateSystem_Name_Light', 1, 'Светлая тема');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('ThemeTemplateSystem_Name_Dark', 1, 'Темная тема');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('ThemeTemplateSystem_Name_Light', 2, 'Light theme');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('ThemeTemplateSystem_Name_Dark', 2, 'Dark theme');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_Dictionary', 1, 'Словарь');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_Dictionary', 2, 'Dictionary');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Menu_Root', 1, 'Корень Меню');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Menu_Root', 2, 'Menu Root');

-- changeset liquibase:3
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType', 1, 'Тип Идентификатора доступа (Тип ИДД)');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType', 2, 'Access Id Type');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_RFIDCard', 1, 'RFID Карта');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_RFIDCard', 2, 'RFID Card');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_NFCLabel', 1, 'NFC label');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_NFCLabel', 2, 'NFC Метка');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_1xBarcode', 1, 'Бумажный + 1xШК');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_1xBarcode', 2, '1xBarcode');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_2xBarcode', 1, 'Бумажный + 2xШК');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_2xBarcode', 2, '2xBarcode');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_PinCode', 1, 'Пин код');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_PinCode', 2, 'Pin code');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_StateNumber', 1, 'Гос номер');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_StateNumber', 2, 'State number');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_Face', 1, 'Лицо');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DictionaryStatic_AccessIdType_Face', 2, 'Face');

-- changeset liquibase:4
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Title_MainForm', 1, 'Системы контроля доступа');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Title_MainForm', 2, 'Access control system');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_ACS', 1, 'СКУД');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_ACS', 2, 'ACS');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_TypesOfACSID', 1, 'Виды ИД СКУД');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_TypesOfACSID', 2, 'Types of ACS IDs');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Type', 1, 'Вид');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Type', 2, 'Type');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Conversion', 1, 'Преобразование');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Conversion', 2, 'Conversion');
--INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessZones_MainForm', 1, 'Зона доступа');
--INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessZones_MainForm', 2, 'Access zone');
--INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessZones_Name', 1, 'Название');
--INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessZones_Name', 2, 'Name');
--INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessZones_Objects_To_Visit', 1, 'Объекты посещения');
--INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessZones_Objects_To_Visit', 2, 'Attendance Objects');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessPoints_MainForm', 1, 'Точка доступа');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessPoints_MainForm', 2, 'Access point');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessPoints_Name', 1, 'Название');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('AccessPoints_Name', 2, 'Name');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Title_AttendanceObjects', 1, 'Объекты');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Title_AttendanceObjects', 2, 'Attendance objects');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Field_AddObjectsToVisit', 1, 'Добавить объект посещения');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Field_AddObjectsToVisit', 2, 'Add attendance object');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_AddAttendanceObjects_ButtonText_CreateButton', 1, 'Создать');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_AddAttendanceObjects_ButtonText_CreateButton', 2, 'Create');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_AddAttendanceObjects_ButtonText_CancellButton', 1, 'Отмена');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_AddAttendanceObjects_ButtonText_CancellButton', 2, 'Cancel');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Conversion_DeleteTypeDialog_AttendanceObjects', 1, 'Вы действительно хотите удалить тип объекта посещения?');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_Conversion_DeleteTypeDialog_AttendanceObjects', 2, 'Are you sure you want to delete the attendance objects?');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DeleteTypeDialog_ButtonText_OKButton', 1, 'ОК');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DeleteTypeDialog_ButtonText_OKButton', 2, 'OK');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DeleteTypeDialog_ButtonText_CancelButton', 1, 'Отмена');
INSERT INTO public."TextResources" ("ResourceKey", "LanguageId", "TextValue") VALUES ('TemplateSystem_DeleteTypeDialog_ButtonText_CancelButton', 2, 'Cancel');