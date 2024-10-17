-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    B A C K E N D   D E   A P L I C A C I O N E S   ( B D A )
--    P R Á C T I C O   T U T O R:
--        S I S T E M A   D E
--        A D M I N I S T R A C I Ó N   D E   C U R S O S   ( S A C )
--
--    C A R G A   B B D D   ( s a c d b )
--        D B M S :   H 2   D a t a b a s e   E n g i n e
--
--    A U T O R :    S c a r a f i a
--
-- =============================================================================

-- =============================================================================
-- PERSONAS
-- =============================================================================
INSERT INTO persona (dni, apellido, nombre) VALUES
  -- Física
  ('1642-12-25/1727-03-20', 'Newton', 'Isaac'),
  ('1629-04-14/1695-07-08', 'Huygens', 'Christiaan'),
  ('1646-07-01/1716-11-14', 'Leibniz', 'Gottfried Wilhelm'),
  ('1736-06-14/1806-08-23', 'de Coulomb', 'Charles-Augustin'),
  ('1736-01-19/1819-08-25', 'Watt', 'James'),
  ('1745-02-18/1827-03-05', 'Volta', 'Alessandro Giuseppe Antonio Anastasio'),
  ('1775-01-20/1836-06-10', 'Ampère', 'André-Marie'),
  ('1789-03-16/1854-07-06', 'Ohm', 'Georg Simon'),
  ('1791-09-22/1867-08-25', 'Faraday', 'Michael'),
  ('1818-12-24/1889-10-11', 'Joule', 'James Prescott'),
  ('1831-06-13/1879-11-05', 'Maxwell', 'James Clerk'),
  ('1856-07-10/1943-01-07', 'Tesla', 'Nikola'),
  ('1857-02-22/1894-01-01', 'Hertz', 'Heinrich Rudolf'),
  ('1867-11-07/1934-07-04', 'Skłodowska-Curie', 'Maria Salomea'),
  ('1879-03-14/1955-04-18', 'Einstein', 'Albert'),
  ('1885-10-07/1962-11-18', 'Bohr', 'Niels'),
  -- Música
  ('1685-03-31/1750-07-28', 'Bach', 'Johann Sebastian'),
  ('1678-03-04/1741-07-28', 'Vivaldi', 'Antonio Lucio'),
  ('1756-01-27/1791-12-05', 'Mozart', 'Wolfgang Amadeus'),
  ('1770-12-??/1827-03-26', 'van Beethoven', 'Ludwig'),
  ('1810-03-01/1849-10-17', 'Chopin', 'Frédéric François'),
  ('1811-10-22/1886-07-31', 'Liszt', 'Franz'),
  ('1813-05-22/1883-02-13', 'Wagner', 'Wilhelm Richard'),
  ('1825-10-25/1899-06-03', 'Strauss', 'Johann Baptist II'),
  ('1833-05-07/1897-04-03', 'Brahms', 'Johannes'),
  ('1840-05-07/1893-11-06', 'Tchaikovsky', 'Pyotr Ilyich'),
  ('1928-11-10/2020-07-06', 'Morricone', 'Enio'),
  ('1932-02-08/', 'Williams', 'John Towner'),
  -- Filosofía
  ('1596-03-31/1650-02-11', 'Descartes', 'René'),
  ('1632-08-29/1704-10-28', 'Locke', 'John'),
  ('1724-04-22/1804-02-12', 'Kant', 'Immanuel'),
  ('1770-08-27/1831-11-14', 'Hegel', 'Georg Wilhelm Friedrich'),
  ('1788-02-22/1860-09-21', 'Schopenhauer', 'Arthur'),
  ('1798-01-19/1857-09-05', 'Comte', 'Isidore Marie Auguste François Xavier'),
  ('1844-10-15/1900-08-25', 'Nietzsche', 'Friedrich Wilhelm'),
  ('1864-04-21/1920-06-14', 'Weber', 'Maximilian Karl Emil'),
  ('1889-09-26/1976-05-26', 'Heidegger', 'Martin'),
  ('1872-05-18/1970-02-02', 'Russell', 'Bertrand Arthur William'),
  ('1883-05-09/1955-10-18', 'Ortega y Gasset', 'José'),
  ('1905-06-21/1980-04-15', 'Sartre', 'Jean-Paul Charles Aymard')
;

-- =============================================================================
-- DOCENTES
-- =============================================================================
INSERT INTO docente (pid, legajo) VALUES
  (1, 'LD-10.001'), (17, 'LD-20.001'), (29, 'LD-30.001')
;

-- =============================================================================
-- ALUMNOS
-- =============================================================================
INSERT INTO alumno (pid, legajo) VALUES
  -- Física
   (1, 'LA-10.001'),  (2, 'LA-10.002'),  (3, 'LA-10.003'),  (4, 'LA-10.004'),
   (5, 'LA-10.005'),  (6, 'LA-10.006'),  (7, 'LA-10.007'),  (8, 'LA-10.008'),
   (9, 'LA-10.009'), (10, 'LA-10.010'), (11, 'LA-10.011'), (12, 'LA-10.012'),
  (13, 'LA-10.013'), (14, 'LA-10.014'), (15, 'LA-10.015'), (16, 'LA-10.016'),
  -- Música
  (17, 'LA-20.001'), (18, 'LA-20.002'), (19, 'LA-20.003'), (20, 'LA-20.004'),
  (21, 'LA-20.005'), (22, 'LA-20.006'), (23, 'LA-20.007'), (24, 'LA-20.008'),
  (25, 'LA-20.009'), (26, 'LA-20.010'), (27, 'LA-20.011'), (28, 'LA-20.012'),
  -- Filosofía
  (29, 'LA-30.001'), (30, 'LA-30.002'), (31, 'LA-30.003'), (32, 'LA-30.004'),
  (33, 'LA-30.005'), (34, 'LA-30.006'), (35, 'LA-30.007'), (36, 'LA-30.008'),
  (37, 'LA-30.009'), (38, 'LA-30.010'), (39, 'LA-30.011'), (40, 'LA-30.012')
;

-- =============================================================================
-- MATERIAS (y ASOCIACIONES)
-- =============================================================================
INSERT INTO materia (nombre, descripcion) VALUES
  ('Física', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'),
  ('Música', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'),
  ('Filosofía', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.')
;

INSERT INTO materia_docentes (mid, pid) VALUES
  (1, 1), (2, 17), (3, 29)
;

-- =============================================================================
-- CURSOS (e INSCRIPCIONES)
-- =============================================================================
INSERT INTO curso (nombre, descripcion, cupo, finicio, mid, pid) VALUES
  ('Física Teórica', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.', 24, '2016-03-01 13:45:10', 1, 1),
  ('Historia de la Música', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.', 24, '2016-03-01 13:47:18', 2, 17),
  ('Introducción a la Filosofía', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.', 24, '2016-03-01 13:52:22', 3, 29)
;

INSERT INTO inscripcion (cid, pid) VALUES
  -- Física
  (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
  (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16),
  -- Música
  (2, 18), (2, 19), (2, 20), (2, 21), (2, 22), (2, 23),
  (2, 24), (2, 25), (2, 26), (2, 27), (2, 28),
  -- Filosofía
  (3, 30), (3, 31), (3, 32), (3, 33), (3, 34), (3, 35),
  (3, 36), (3, 37), (3, 38), (3, 39), (3, 40)
;

-- =============================================================================
-- COMMIT;
-- =============================================================================
