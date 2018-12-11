-- справочник видов документов, удостоверяющих личность физического лица
INSERT INTO identification_document_catalog(id, code, name)
  VALUES
  (1, 3, 'Свидетельство о рождении'),
  (2, 7, 'Военный билет'),
  (3, 8, 'Временное удостоверение, выданное взамен военного билета'),
  (4, 10, 'Паспорт иностранного гражданина'),
  (5, 11, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу'),
  (6, 12, 'Вид на жительство в Российской Федерации'),
  (7, 13, 'Удостоверение беженца'),
  (8, 15, 'Разрешение на временное проживание в Российской Федерации'),
  (9, 18, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации'),
  (10, 21, 'Паспорт гражданина Российской Федерации'),
  (11, 23, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства'),
  (12, 24, 'Удостоверение личности военнослужащего Российской Федерации'),
  (13, 91, 'Иные документы');

-- справочник стран
INSERT INTO country_catalog(id, code, name)
  VALUES
  (1, 643, 'Российская Федерация'),
  (2, 36, 'Австралия'),
  (3, 40, 'Австрия'),
  (5, 51, 'Армения'),
  (6, 112, 'Белоруссия'),
  (8, 76, 'Бразилия'),
  (9, 348, 'Венгрия'),
  (11, 300, 'Греция'),
  (12, 208, 'Дания'),
  (14, 376, 'Израиль'),
  (15, 356, 'Индия'),
  (16, 352, 'Исландия'),
  (17, 398, 'Казахстан'),
  (18, 124, 'Канада'),
  (26, 840, 'США'),
  (28, 804, 'Украина'),
  (29, 276, 'Германия'),
  (30, 372, 'Ирландия'),
  (31, 380, 'Италия'),
  (32, 442, 'Люксембург'),
  (34, 203, 'Чехия'),
  (35, 246, 'Финляндия'),
  (36, 250, 'Франция'),
  (37, 756, 'Швейцария'),
  (38, 752, 'Швеция'),
  (39, 392, 'Япония');

-- Организация
INSERT INTO organization(id, name, full_name, inn, kpp, address, is_active)
  VALUES
  (
    1,
    'СГТУ имени Гагарина Ю.А.',
    'Федеральное государственное бюджетное образовательное учреждение высшего образования «Саратовский государственный технический университет имени Гагарина Ю.А.»',
    '8894103143',
    '794561321',
    'г.Саратов, ул.Политехническая, 77',
    true
  );

-- подразделение организации
INSERT INTO office(id, name, is_active, organization_id)
  VALUES
  (1, 'Институт энергетики и транспортных систем', true, 1),
  (2, 'Институт электронной техники и машиностроения', true, 1),
  (3, 'Институт прикладных информационных технологий и коммуникаций', true, 1),
  (4, 'Физико-технический институт', true, 1),
  (5, 'Институт урбанистики, архитектуры и строительства', true, 1),
  (6, 'Институт социального и производственного менеджмента', true, 1);

-- работник
SET @countryCatalogId = SELECT TOP 1 id FROM country_catalog WHERE code = 643;
INSERT INTO employee(id, first_name, second_name, position, is_identified, country_catalog_id)
  VALUES
  (1, 'Виктор','Прокопенко','Ассистент', true, @countryCatalogId),
  (2, 'Антон','Крук','Доцент', true, @countryCatalogId),
  (3, 'Оксана','Десятова','Доцент', true, @countryCatalogId),
  (4, 'Антонина','Шевченко','Профессор', true, @countryCatalogId),
  (5, 'Анатолий','Дмитров','Заведующий кафедрой ИнЭТМ', true, @countryCatalogId),
  (6, 'Иван','Кобзар','Директор', true, @countriesCatalogId),
  (7, 'Виктор','Грачь','Заместитель директора по научно-исследовательской работе', true, @countryCatalogId),
  (8, 'Ольга','Буткова','Заместитель директора по воспитательной работе', true, @countryCatalogId),
  (9, 'Алина','Мелова', 'Заместитель директора по учебной работе', true, @countryCatalogId),
  (10, 'Михаил','Савицкий','Ассистент', true, @countryCatalogId),
  (11, 'Артем','Крава','Ассистент', true, @countryCatalogId);

-- документ удостоверяющих личность работника
SET @identificationDocumentCatalogId = SELECT id FROM identification_document_catalog WHERE code = 21;
INSERT INTO employee_document(id, name, number, date, identification_document_catalog_id, employee_id)
  VALUES
  (1, 'паспорт','78946', '1985-12-04', @identificationDocumentCatalogId, 1);

-- связь employee - office
INSERT INTO employee_office(employee_id, office_id)
  VALUES
  (1, 2),
  (2, 2),
  (3, 2),
  (4, 2),
  (5, 2),
  (6, 2),
  (7, 2),
  (8, 2),
  (9, 2),
  (10, 2),
  (11, 2),
  (1, 3),
  (1, 4);