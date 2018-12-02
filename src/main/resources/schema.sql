DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS employee_document;
DROP TABLE IF EXISTS office;
DROP TABLE IF EXISTS organization;
DROP TABLE IF EXISTS countries_catalog;
DROP TABLE IF EXISTS identification_document_catalog;

-- -----------------------------------------------------------------------------------------------------
-- справочник стран 
CREATE TABLE IF NOT EXISTS countries_catalog
(
  id   SMALLINT     AUTO_INCREMENT,
  code SMALLINT     NOT NULL,
  name VARCHAR(150) NOT NULL,

  CONSTRAINT PK_COUNTRIES_CATALG_ID PRIMARY KEY (id)
);
COMMENT ON TABLE  countries_catalog      IS 'справочник стран';
COMMENT ON COLUMN countries_catalog.name IS 'название страны';
COMMENT ON COLUMN countries_catalog.code IS 'цифровой код страны по ISO 3166-1';

CREATE UNIQUE INDEX UX_COUNTRIES_CATALOG_CODE ON countries_catalog(code);
CREATE UNIQUE INDEX UX_COUNTRIES_CATALOG_NAME ON countries_catalog(name);

-- ------------------------------------------------------------------------------------------------------
-- справочник видов документов, удостоверяющих личность физического лица
CREATE TABLE IF NOT EXISTS identification_document_catalog
(
  id   SMALLINT     NOT NULL AUTO_INCREMENT,
  code VARCHAR(3)   NOT NULL,
  name VARCHAR(250) NOT NULL,

  CONSTRAINT PK_IDENTIFICATION_DOCUMENT_CATALOG_ID PRIMARY KEY (id)
);
COMMENT ON TABLE  identification_document_catalog      IS 'справочник видов документов, удостоверяющих личность физического лица';
COMMENT ON COLUMN identification_document_catalog.code IS 'код документа';
COMMENT ON COLUMN identification_document_catalog.name IS 'наименование документа';

CREATE UNIQUE INDEX UX_IDENTIFICATION_DOCUMENT_CATALOG_CODE ON identification_document_catalog(code);
CREATE UNIQUE INDEX UX_IDENTIFICATION_DOCUMENT_CATALOG_NAME ON identification_document_catalog(name);

--  -----------------------------------------------------------------------------------------------------
-- Организация
CREATE TABLE IF NOT EXISTS organization
(
  id        INTEGER      NOT NULL AUTO_INCREMENT,
  name      VARCHAR(100) NOT NULL,
  full_name VARCHAR(350) NOT NULL,
  inn       CHAR(10)     NOT NULL,
  kpp       CHAR(9)      NOT NULL,
  address   VARCHAR(250) NOT NULL,
  phone     CHAR(20),
  is_active BOOLEAN      DEFAULT FALSE,

  CONSTRAINT PK_ORGANIZATION_ID PRIMARY KEY (ID)
);

COMMENT ON TABLE  organization     			 IS 'Организация';
COMMENT ON COLUMN organization.name 	   IS 'сокращенное название';
COMMENT ON COLUMN organization.full_name IS 'полное название';
COMMENT ON COLUMN organization.inn 			 IS 'идентификационный номер налогоплательщика';
COMMENT ON COLUMN organization.kpp 			 IS 'код причины постановки на учет в налоговых органах';
COMMENT ON COLUMN organization.address   IS 'регистрационный адрес';
COMMENT ON COLUMN organization.phone     IS 'телефон';
COMMENT ON COLUMN organization.is_active IS 'действующая организация, если дейтвует - true';

CREATE INDEX IX_ORGANIZATION_NAME ON organization(name);

CREATE UNIQUE INDEX UX_ORGANIZATION_FULL_NAME ON organization(full_name);
CREATE UNIQUE INDEX UX_ORGANIZATION_INN       ON organization(inn);
CREATE UNIQUE INDEX UX_ORGANIZATION_KPP       ON organization(kpp);

-- -------------------------------------------------------------------------------------------
-- подразделение организации
CREATE TABLE IF NOT EXISTS office
(
  id                INTEGER       NOT NULL AUTO_INCREMENT,
  name              VARCHAR(100),
  address           VARCHAR(255),
  phone             VARCHAR(20),
  is_active         BOOLEAN       DEFAULT FALSE,
  organization_id   INTEGER,

  CONSTRAINT PK_OFFICE_ID PRIMARY KEY (id)
);
COMMENT ON TABLE  office           IS 'подразделение организации';
COMMENT ON COLUMN office.name      IS 'название';
COMMENT ON COLUMN office.address   IS 'регистрационный адрес';
COMMENT ON COLUMN office.phone 		 IS 'номер';
COMMENT ON COLUMN office.is_active IS 'действующее подразделение, если дейтвует - true';

CREATE INDEX IX_OFFICE_NAME            ON office(name);
CREATE INDEX IX_OFFICE_PHONE           ON office(phone);
CREATE INDEX IX_OFFICE_ORGANIZATION_ID ON office(organization_id);

ALTER TABLE office
  ADD CONSTRAINT FK_OFFICE_ORGANIZATION_ID
    FOREIGN KEY (organization_id)
      REFERENCES organization(id)
      ON DELETE RESTRICT;
			
-- --------------------------------------------------------------------------------------------------
-- документ удостоверяющих личность работника
CREATE TABLE IF NOT EXISTS employee_document
(
  id                                 INTEGER       NOT NULL AUTO_INCREMENT,
  name                               VARCHAR(250),
  number                             VARCHAR(20),
  date                               DATE,
  identification_document_catalog_id SMALLINT,

  CONSTRAINT PK_EMPLOYEE_DOCUMENT_ID PRIMARY KEY (id)
);
COMMENT ON TABLE 	employee_document 			 IS 'документ удостоверяющих личность работника';
COMMENT ON COLUMN employee_document.name 	 IS 'наименование документа';
COMMENT ON COLUMN employee_document.number IS 'номер документа';
COMMENT ON COLUMN employee_document.date 	 IS 'дата выдачи документа';

CREATE INDEX IX_EMPLOYEE_DOCUMENT_IDENTIFICATION_DOCUMENT_CATALOG_ID ON employee_document(identification_document_catalog_id);

ALTER TABLE employee_document
  ADD CONSTRAINT IF NOT EXISTS FK_EMPLOYEE_DOCUMENT_IDENTIFICATION_DOCUMENT_CATALOG_ID
    FOREIGN KEY (identification_document_catalog_id)
      REFERENCES identification_document_catalog(id)
      ON DELETE SET NULL;
			
-- --------------------------------------------------------------------------------------------------
-- работник
CREATE TABLE IF NOT EXISTS employee
(
  id                   INTEGER      NOT NULL AUTO_INCREMENT, 
  first_name           VARCHAR(50)  NOT NULL,
  second_name          VARCHAR(50),
  middle_name          VARCHAR(50),
  position             VARCHAR(100) NOT NULL,
  phone                CHAR(20),
  is_identified        BOOLEAN      DEFAULT FALSE,
  employee_document_id INTEGER,
  countries_catalog_id SMALLINT,
  office_id            INTEGER,

  CONSTRAINT PK_EMPLOYEE_ID PRIMARY KEY (id)
);
COMMENT ON TABLE employee 							 IS 'работник';
COMMENT ON COLUMN employee.first_name 	 IS 'имя';
COMMENT ON COLUMN employee.second_name 	 IS 'фамилия';
COMMENT ON COLUMN employee.middle_name 	 IS 'отчество';
COMMENT ON COLUMN employee.phone 				 IS 'телефон';
COMMENT ON COLUMN employee.is_identified IS 'работающий или не работающий, если работающий - true';

CREATE INDEX IX_EMPLOYEE_FIRST_NAME           ON employee(first_name);
CREATE INDEX IX_EMPLOYEE_SECOND_NAME          ON employee(second_name);
CREATE INDEX IX_EMPLOYEE_MIDDLE_NAME          ON employee(middle_name);
CREATE INDEX IX_EMPLOYEE_COUNTRIES_CATALOG_ID ON employee(countries_catalog_id);
CREATE INDEX IX_EMPLOYEE_OFFICE_ID            ON employee(office_id);

CREATE UNIQUE INDEX UX_EMPLOYEE_DOCUMENT_ID ON employee(employee_document_id);

ALTER TABLE employee
  ADD CONSTRAINT FK_EMPLOYEE_DOCUMENT_ID
    FOREIGN KEY (employee_document_id)
      REFERENCES employee_document(id)
      ON DELETE RESTRICT;

ALTER TABLE employee
  ADD CONSTRAINT FK_EMPLOYEE_COUNTRIES_CATALOG_ID
    FOREIGN KEY (countries_catalog_id)
      REFERENCES countries_catalog(id)
      ON DELETE SET NULL;

ALTER TABLE employee
  ADD CONSTRAINT FK_EMPLOYEE_OFFICE_ID
    FOREIGN KEY (office_id)
      REFERENCES office(id)
      ON DELETE RESTRICT;