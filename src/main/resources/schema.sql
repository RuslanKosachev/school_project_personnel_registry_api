DROP TABLE IF EXISTS employee_office;
DROP TABLE IF EXISTS employee_document;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS office;
DROP TABLE IF EXISTS organization;
DROP TABLE IF EXISTS country_catalog;
DROP TABLE IF EXISTS identification_document_catalog;
-- -----------------------------------------------------------------------------------------------------
-- справочник стран 
CREATE TABLE IF NOT EXISTS country_catalog
(
  id      SMALLINT     NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  code    VARCHAR(3)   NOT NULL                COMMENT 'Цифровой код страны по ISO 3166-1',
  name    VARCHAR(150) NOT NULL                COMMENT 'Название страны',
  version INTEGER      NOT NULL                COMMENT 'Служебное поле hibernate',

  CONSTRAINT PK_COUNTRIES_CATALG_ID PRIMARY KEY (id)
);
COMMENT ON TABLE  country_catalog IS 'Справочник стран';

CREATE UNIQUE INDEX UX_COUNTRIES_CATALOG_CODE ON country_catalog(code);
CREATE UNIQUE INDEX UX_COUNTRIES_CATALOG_NAME ON country_catalog(name);
-- ------------------------------------------------------------------------------------------------------
-- справочник видов документов, удостоверяющих личность физического лица
CREATE TABLE IF NOT EXISTS identification_document_catalog
(
  id      SMALLINT     NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  code    VARCHAR(3)   NOT NULL                COMMENT 'Код документа',
  name    VARCHAR(250) NOT NULL                COMMENT 'Наименование документа',
  version INTEGER      NOT NULL                COMMENT 'Служебное поле hibernate',

  CONSTRAINT PK_IDENTIFICATION_DOCUMENT_CATALOG_ID PRIMARY KEY (id)
);
COMMENT ON TABLE identification_document_catalog IS 'Справочник видов документов, удостоверяющих личность физического лица согласно приложению №3 приказа ФНС России от 25.01.2012 N ММВ-7-6/25@ (ред. от 25.05.2016)';

CREATE UNIQUE INDEX UX_IDENTIFICATION_DOCUMENT_CATALOG_CODE ON identification_document_catalog(code);
CREATE UNIQUE INDEX UX_IDENTIFICATION_DOCUMENT_CATALOG_NAME ON identification_document_catalog(name);
--  -----------------------------------------------------------------------------------------------------
-- Организация
CREATE TABLE IF NOT EXISTS organization
(
  id        INTEGER      NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  name      VARCHAR(100) NOT NULL                COMMENT 'Сокращенное название',
  full_name VARCHAR(350) NOT NULL                COMMENT 'Полное название',
  inn       CHAR(12)     NOT NULL                COMMENT 'Идентификационный номер налогоплательщика',
  kpp       CHAR(9)      NOT NULL                COMMENT 'Код причины постановки на учет в налоговых органах',
  address   VARCHAR(350) NOT NULL                COMMENT 'Регистрационный адрес',
  phone     CHAR(20)                             COMMENT 'Номер телефона',
  is_active BOOLEAN      DEFAULT FALSE           COMMENT 'Действующая организация, если дейтвует - true',
  version   INTEGER      NOT NULL                COMMENT 'Служебное поле hibernate',

  CONSTRAINT PK_ORGANIZATION_ID PRIMARY KEY (ID)
);
COMMENT ON TABLE organization IS 'Организация';

CREATE INDEX IX_ORGANIZATION_NAME ON organization(name);

CREATE UNIQUE INDEX UX_ORGANIZATION_FULL_NAME ON organization(full_name);
CREATE UNIQUE INDEX UX_ORGANIZATION_INN       ON organization(inn);
CREATE UNIQUE INDEX UX_ORGANIZATION_KPP       ON organization(kpp);
-- -------------------------------------------------------------------------------------------
-- подразделение организации
CREATE TABLE IF NOT EXISTS office
(
  id                INTEGER      NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  name              VARCHAR(100) NOT NULL                COMMENT 'Название',
  address           VARCHAR(350) NOT NULL                COMMENT 'Регистрационный адрес',
  phone             VARCHAR(20)                          COMMENT 'Номер телефона',
  is_active         BOOLEAN      DEFAULT FALSE           COMMENT 'Действующее подразделение, если дейтвует - true',
  organization_id   INTEGER                              COMMENT 'Уникальный идентификатор организации',
  version           INTEGER      NOT NULL                COMMENT 'Служебное поле hibernate',

  CONSTRAINT PK_OFFICE_ID PRIMARY KEY (id)
);
COMMENT ON TABLE office IS 'Подразделение организации';

CREATE INDEX IX_OFFICE_NAME            ON office(name);
CREATE INDEX IX_OFFICE_PHONE           ON office(phone);
CREATE INDEX IX_OFFICE_ORGANIZATION_ID ON office(organization_id);

ALTER TABLE office
  ADD CONSTRAINT FK_OFFICE_ORGANIZATION_ID
    FOREIGN KEY (organization_id)
      REFERENCES organization(id)
      ON DELETE RESTRICT
      ON UPDATE CASCADE;
-- --------------------------------------------------------------------------------------------------
-- сотрудник
CREATE TABLE IF NOT EXISTS employee
(
  id                 INTEGER      NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  first_name         VARCHAR(50)  NOT NULL                COMMENT 'Имя',
  second_name        VARCHAR(50)                          COMMENT 'Фамилия',
  middle_name        VARCHAR(50)                          COMMENT 'Отчество',
  position           VARCHAR(100) NOT NULL                COMMENT 'Должность',
  phone              CHAR(20)                             COMMENT 'Телефон',
  is_identified      BOOLEAN      DEFAULT FALSE           COMMENT 'Работающий или не работающий, если работающий - true',
  country_catalog_id SMALLINT                             COMMENT 'Уникальный идентификатор страны',
  version            INTEGER      NOT NULL                COMMENT 'Служебное поле hibernate',

  CONSTRAINT PK_EMPLOYEE_ID PRIMARY KEY (id)
);
COMMENT ON TABLE employee IS 'Сотрудник';

CREATE INDEX IX_EMPLOYEE_FIRST_NAME         ON employee(first_name);
CREATE INDEX IX_EMPLOYEE_SECOND_NAME        ON employee(second_name);
CREATE INDEX IX_EMPLOYEE_MIDDLE_NAME        ON employee(middle_name);
CREATE INDEX IX_EMPLOYEE_COUNTRY_CATALOG_ID ON employee(country_catalog_id);

ALTER TABLE employee
  ADD CONSTRAINT FK_EMPLOYEE_COUNTRIES_CATALOG_ID
    FOREIGN KEY (country_catalog_id)
      REFERENCES country_catalog(id)
      ON DELETE SET NULL
      ON UPDATE CASCADE;
-- --------------------------------------------------------------------------------------------------
-- документ удостоверяющих личность работника
CREATE TABLE IF NOT EXISTS employee_document
(
  id                                 INTEGER       NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  name                               VARCHAR(250)                          COMMENT 'Наименование документа',
  number                             VARCHAR(20)                           COMMENT 'Номер документа',
  date                               DATE                                  COMMENT 'Дата выдачи документа',
  identification_document_catalog_id SMALLINT                              COMMENT 'Уникальный идентификатор типа документа удостоверяющих личность физического лица',
  employee_id                        INTEGER                               COMMENT 'Уникальный идентификатор сотрудниа',
  version                            INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',

  CONSTRAINT PK_EMPLOYEE_DOCUMENT_ID PRIMARY KEY (id)
);
COMMENT ON TABLE employee_document IS 'Документ удостоверяющих личность сотрудника';

CREATE INDEX IX_EMPLOYEE_DOCUMENT_IDENTIFICATION_DOCUMENT_CATALOG_ID ON employee_document(identification_document_catalog_id);
CREATE INDEX IX_EMPLOYEE_DOCUMENT_EMPLOYEE_ID                        ON employee_document(employee_id);

ALTER TABLE employee_document
  ADD CONSTRAINT IF NOT EXISTS FK_EMPLOYEE_DOCUMENT_IDENTIFICATION_DOCUMENT_CATALOG_ID
    FOREIGN KEY (identification_document_catalog_id)
      REFERENCES identification_document_catalog(id)
      ON DELETE SET NULL
      ON UPDATE CASCADE;

ALTER TABLE employee_document
  ADD CONSTRAINT IF NOT EXISTS FK_EMPLOYEE_DOCUMENT_EMPLOYEE_ID
    FOREIGN KEY (employee_id)
      REFERENCES employee(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE;
-- --------------------------------------------------------------------------------------------------
-- join-таблица для связи сотрудник - подразделение
CREATE TABLE IF NOT EXISTS employee_office
(
  employee_id INTEGER COMMENT 'Уникальный идентификатор сотрудниа',
  office_id   INTEGER COMMENT 'Уникальный идентификатор подразделения',

  CONSTRAINT PK_EMPLOYEE_OFFICE PRIMARY KEY (employee_id, office_id)
);
COMMENT ON TABLE employee_office IS 'Join-таблица для связи сотрудник - подразделение';

CREATE INDEX IX_EMPLOYEE_OFFICE_EMPLOYEE_ID ON employee_office(employee_id);
CREATE INDEX IX_EMPLOYEE_OFFICE_OFFICE_ID   ON employee_office(office_id);

ALTER TABLE employee_office
  ADD CONSTRAINT FK_EMPLOYEE_OFFICE_EMPLOYEE_ID
    FOREIGN KEY (employee_id)
      REFERENCES employee(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE;

ALTER TABLE employee_office
  ADD CONSTRAINT FK_EMPLOYEE_OFFICE_OFFICE_ID
    FOREIGN KEY (office_id)
      REFERENCES office(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE;