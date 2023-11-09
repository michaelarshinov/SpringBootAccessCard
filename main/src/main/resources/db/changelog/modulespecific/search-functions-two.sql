-- liquibase formatted sql
--подключение модулей postgres

-- changeset liquibase:1 splitStatements:false
create index if not exists info_gist_idx on "RFIDCard"
using gist (fullvisiting("ReadableCardNumber", "SerialCardNumber", "CardNumberWithoutConversion") gist_trgm_ops);

-- changeset liquibase:2 splitStatements:false
create index if not exists info_gist_eng_idx_kb on "RFIDCard"
using gist (replace_unaccent_eng(fullvisitingpage("ReadableCardNumber", "SerialCardNumber", "CardNumberWithoutConversion")) gist_trgm_ops);

-- changeset liquibase:3 splitStatements:false
create index if not exists info_gist_idx_eng_translit on "RFIDCard"
using gist (replace_unaccent_eng_translit(fullvisitingpage("ReadableCardNumber", "SerialCardNumber", "CardNumberWithoutConversion")) gist_trgm_ops);

-- changeset liquibase:4 splitStatements:false
create index if not exists info_gist_rus_idx_kb on "RFIDCard"
using gist (replace_unaccent_eng(fullvisitingpage("ReadableCardNumber", "SerialCardNumber", "CardNumberWithoutConversion")) gist_trgm_ops);

-- changeset liquibase:5 splitStatements:false
create index if not exists info_gist_idx_rus_translit on "RFIDCard"
using gist (replace_unaccent_eng_translit(fullvisitingpage("ReadableCardNumber", "SerialCardNumber", "CardNumberWithoutConversion")) gist_trgm_ops);
