-- liquibase formatted sql

-- changeset liquibase:1
INSERT INTO public."AccessPoint" ("Id", "Name", "AccessZone1Id", "AccessZone2Id", "ACSId") VALUES ('d7d5baf7-6d84-4016-8228-4159b4410a3e', 'Точка Доступа 1', '68baac98-fdb5-480c-b71a-b615dc25eb25', 'f2a3d99e-612b-4705-8107-cd508b48680e', 'b5c6e805-968e-41f2-820e-c397ff63ceb9');
INSERT INTO public."AccessPoint" ("Id", "Name", "AccessZone1Id", "AccessZone2Id", "ACSId") VALUES ('290688bb-a450-4c88-91de-d3093ec539ee', 'Дверь между 1 и 2', 'f2a3d99e-612b-4705-8107-cd508b48680e', 'd1ad50c8-482a-42bb-a270-aabf0f8f575b', 'b5c6e805-968e-41f2-820e-c397ff63ceb9');
