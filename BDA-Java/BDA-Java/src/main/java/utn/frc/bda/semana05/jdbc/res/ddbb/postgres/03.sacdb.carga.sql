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
--        D B M S :   P o s t g r e S Q L
--
--    A U T O R :    S c a r a f i a
--
-- =============================================================================

-- =============================================================================
-- PERSONAS, DOCENTES, ALUMNOS... (directo y/o con asociación/es)
-- =============================================================================
SELECT fn_savepersona('1642-12-25/1727-03-20', 'Newton', 'Isaac');
SELECT pr_savedocente(1, 'LD-10.001');
SELECT pr_savealumno(1, 'LA-10.001');

SELECT fn_savealumno('1629-04-14/1695-07-08', 'Huygens', 'Christiaan', 'LA-10.002');
SELECT fn_savealumno('1646-07-01/1716-11-14', 'Leibniz', 'Gottfried Wilhelm', 'LA-10.003');
SELECT fn_savealumno('1736-06-14/1806-08-23', 'de Coulomb', 'Charles-Augustin', 'LA-10.004');
SELECT fn_savealumno('1736-01-19/1819-08-25', 'Watt', 'James', 'LA-10.005');
SELECT fn_savealumno('1745-02-18/1827-03-05', 'Volta', 'Alessandro Giuseppe Antonio Anastasio', 'LA-10.006');
SELECT fn_savealumno('1775-01-20/1836-06-10', 'Ampère', 'André-Marie', 'LA-10.007');
SELECT fn_savealumno('1789-03-16/1854-07-06', 'Ohm', 'Georg Simon', 'LA-10.008');
SELECT fn_savealumno('1791-09-22/1867-08-25', 'Faraday', 'Michael', 'LA-10.009');
SELECT fn_savealumno('1818-12-24/1889-10-11', 'Joule', 'James Prescott', 'LA-10.010');
SELECT fn_savealumno('1831-06-13/1879-11-05', 'Maxwell', 'James Clerk', 'LA-10.011');
SELECT fn_savealumno('1856-07-10/1943-01-07', 'Tesla', 'Nikola', 'LA-10.012');
SELECT fn_savealumno('1857-02-22/1894-01-01', 'Hertz', 'Heinrich Rudolf', 'LA-10.013');
SELECT fn_savealumno('1867-11-07/1934-07-04', 'Skłodowska-Curie', 'Maria Salomea', 'LA-10.014');
SELECT fn_savealumno('1879-03-14/1955-04-18', 'Einstein', 'Albert', 'LA-10.015');
SELECT fn_savealumno('1885-10-07/1962-11-18', 'Bohr', 'Niels', 'LA-10.016');

SELECT fn_savedocente('1685-03-31/1750-07-28', 'Bach', 'Johann Sebastian', 'LD-20.001');
SELECT pr_savealumno(17, 'LA-20.001');

SELECT fn_savealumno('1678-03-04/1741-07-28', 'Vivaldi', 'Antonio Lucio', 'LA-20.002');
SELECT fn_savealumno('1756-01-27/1791-12-05', 'Mozart', 'Wolfgang Amadeus', 'LA-20.003');
SELECT fn_savealumno('1770-12-??/1827-03-26', 'van Beethoven', 'Ludwig', 'LA-20.004');
SELECT fn_savealumno('1810-03-01/1849-10-17', 'Chopin', 'Frédéric François', 'LA-20.005');
SELECT fn_savealumno('1811-10-22/1886-07-31', 'Liszt', 'Franz', 'LA-20.006');
SELECT fn_savealumno('1813-05-22/1883-02-13', 'Wagner', 'Wilhelm Richard', 'LA-20.007');
SELECT fn_savealumno('1825-10-25/1899-06-03', 'Strauss', 'Johann Baptist II', 'LA-20.008');
SELECT fn_savealumno('1833-05-07/1897-04-03', 'Brahms', 'Johannes', 'LA-20.009');
SELECT fn_savealumno('1840-05-07/1893-11-06', 'Tchaikovsky', 'Pyotr Ilyich', 'LA-20.010');
SELECT fn_savealumno('1928-11-10/2020-07-06', 'Morricone', 'Enio', 'LA-20.011');
SELECT fn_savealumno('1932-02-08/', 'Williams', 'John Towner', 'LA-20.012');
-- SELECT fn_savedocente('nnnn-02-07/', 'Mastropiero / Mastropiano', 'Johann Sebastian / Johann Severo / Peter Illich / Wolfgang Amadeus / Etcétera', 'LD-nnn');

SELECT fn_savealumno('1596-03-31/1650-02-11', 'Descartes', 'René', 'LA-30.001');
SELECT pr_savedocente(29, 'LD-30.001');

SELECT fn_savealumno('1632-08-29/1704-10-28', 'Locke', 'John', 'LA-30.002');
SELECT fn_savealumno('1724-04-22/1804-02-12', 'Kant', 'Immanuel', 'LA-30.003');
SELECT fn_savealumno('1770-08-27/1831-11-14', 'Hegel', 'Georg Wilhelm Friedrich', 'LA-30.004');
SELECT fn_savealumno('1788-02-22/1860-09-21', 'Schopenhauer', 'Arthur', 'LA-30.005');
SELECT fn_savealumno('1798-01-19/1857-09-05', 'Comte', 'Isidore Marie Auguste François Xavier', 'LA-30.006');
SELECT fn_savealumno('1844-10-15/1900-08-25', 'Nietzsche', 'Friedrich Wilhelm', 'LA-30.007');
SELECT fn_savealumno('1864-04-21/1920-06-14', 'Weber', 'Maximilian Karl Emil', 'LA-30.008');
SELECT fn_savealumno('1889-09-26/1976-05-26', 'Heidegger', 'Martin', 'LA-30.009');
SELECT fn_savealumno('1872-05-18/1970-02-02', 'Russell', 'Bertrand Arthur William', 'LA-30.010');
SELECT fn_savealumno('1883-05-09/1955-10-18', 'Ortega y Gasset', 'José', 'LA-30.011');
SELECT fn_savealumno('1905-06-21/1980-04-15', 'Sartre', 'Jean-Paul Charles Aymard', 'LA-30.012');

-- =============================================================================
-- MATERIAS (y ASOCIACIONES)
-- =============================================================================
SELECT fn_savemateria('Física', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.');
SELECT fn_savemateria('Musica', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.');
SELECT fn_savemateria(2, 'Música', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.');
SELECT fn_savemateria('Filosofía', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.');

SELECT pr_saveasociacion(1, 1);
SELECT pr_saveasociacion(2, 17);
SELECT pr_saveasociacion(3, 29);

-- =============================================================================
-- CURSOS (e INSCRIPCIONES)
-- =============================================================================
SELECT fn_savecurso(1, 1, 'Física Teórica'::VARCHAR, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'::VARCHAR, 24::SMALLINT, '2016-03-01 13:45:10'::TIMESTAMP);
SELECT fn_savecurso(2, 17, 'Historia de la Música'::VARCHAR, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'::VARCHAR, 24::SMALLINT, '2016-03-01 13:47:18'::TIMESTAMP);
SELECT fn_savecurso(3, 29, 'Introducción a la Filosofía'::VARCHAR, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'::VARCHAR, 24::SMALLINT, '2016-03-01 13:52:22'::TIMESTAMP);
-- Física
SELECT pr_saveinscripcion(1, 2);
SELECT pr_saveinscripcion(1, 3);
SELECT pr_saveinscripcion(1, 4);
SELECT pr_saveinscripcion(1, 5);
SELECT pr_saveinscripcion(1, 6);
SELECT pr_saveinscripcion(1, 7);
SELECT pr_saveinscripcion(1, 8);
SELECT pr_saveinscripcion(1, 9);
SELECT pr_saveinscripcion(1, 10);
SELECT pr_saveinscripcion(1, 11);
SELECT pr_saveinscripcion(1, 12);
SELECT pr_saveinscripcion(1, 13);
SELECT pr_saveinscripcion(1, 14);
SELECT pr_saveinscripcion(1, 15);
SELECT pr_saveinscripcion(1, 16);
-- Música
SELECT pr_saveinscripcion(2, 18);
SELECT pr_saveinscripcion(2, 19);
SELECT pr_saveinscripcion(2, 20);
SELECT pr_saveinscripcion(2, 21);
SELECT pr_saveinscripcion(2, 22);
SELECT pr_saveinscripcion(2, 23);
SELECT pr_saveinscripcion(2, 24);
SELECT pr_saveinscripcion(2, 25);
SELECT pr_saveinscripcion(2, 26);
SELECT pr_saveinscripcion(2, 27);
SELECT pr_saveinscripcion(2, 28);
-- Filosofía
SELECT pr_saveinscripcion(3, 30);
SELECT pr_saveinscripcion(3, 31);
SELECT pr_saveinscripcion(3, 32);
SELECT pr_saveinscripcion(3, 33);
SELECT pr_saveinscripcion(3, 34);
SELECT pr_saveinscripcion(3, 35);
SELECT pr_saveinscripcion(3, 36);
SELECT pr_saveinscripcion(3, 37);
SELECT pr_saveinscripcion(3, 38);
SELECT pr_saveinscripcion(3, 39);
SELECT pr_saveinscripcion(3, 40);

-- =============================================================================
-- COMMIT;
-- =============================================================================
