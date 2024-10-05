--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.24
-- Dumped by pg_dump version 14.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE public.persona ALTER COLUMN pid DROP DEFAULT;
ALTER TABLE public.materia ALTER COLUMN mid DROP DEFAULT;
ALTER TABLE public.curso ALTER COLUMN cid DROP DEFAULT;
DROP VIEW public.v_persona;
DROP VIEW public.v_docente;
DROP VIEW public.v_curso;
DROP VIEW public.v_alumno;
DROP SEQUENCE public.persona_pid_seq;
DROP TABLE public.persona;
DROP SEQUENCE public.materia_mid_seq;
DROP TABLE public.materia_docentes;
DROP TABLE public.materia;
DROP TABLE public.inscripcion;
DROP TABLE public.docente;
DROP SEQUENCE public.curso_cid_seq;
DROP TABLE public.curso_alumnos;
DROP TABLE public.curso;
DROP TABLE public.alumno;
DROP FUNCTION public.pr_saveinscripcion(pin_cid integer, pin_pid integer, pin_cfinal smallint);
DROP FUNCTION public.pr_saveinscripcion(pin_cid integer, pin_pid integer);
DROP FUNCTION public.pr_savedocente(pin_pid integer, pin_legajo character varying);
DROP FUNCTION public.pr_saveasociacion(pin_mid integer, pin_pid integer);
DROP FUNCTION public.pr_savealumno(pin_pid integer, pin_legajo character varying);
DROP FUNCTION public.pr_deletepersona(pin_pid integer);
DROP FUNCTION public.pr_deletemateria(pin_mid integer);
DROP FUNCTION public.pr_deleteinscripcion(pin_cid integer, pin_pid integer);
DROP FUNCTION public.pr_deletedocente(pin_pid integer);
DROP FUNCTION public.pr_deletecurso(pin_cid integer);
DROP FUNCTION public.pr_deleteasociacion(pin_mid integer, pin_pid integer);
DROP FUNCTION public.pr_deletealumno(pin_pid integer);
DROP FUNCTION public.fn_savepersona(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying);
DROP FUNCTION public.fn_savepersona(pin_dni character varying, pin_apellido character varying, pin_nombre character varying);
DROP FUNCTION public.fn_savemateria(pin_mid integer, pin_nombre character varying, pin_descripcion text);
DROP FUNCTION public.fn_savemateria(pin_nombre character varying, pin_descripcion text);
DROP FUNCTION public.fn_savedocente(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying);
DROP FUNCTION public.fn_savedocente(pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying);
DROP FUNCTION public.fn_savecurso(pin_cid integer, pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone, pin_ffin timestamp without time zone);
DROP FUNCTION public.fn_savecurso(pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone, pin_ffin timestamp without time zone);
DROP FUNCTION public.fn_savecurso(pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone);
DROP FUNCTION public.fn_savealumno(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying);
DROP FUNCTION public.fn_savealumno(pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying);
--
-- Name: fn_savealumno(character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savealumno(pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  BEGIN
    RETURN fn_savealumno(NULL, pin_dni, pin_apellido, pin_nombre, pin_legajo);
  END;

$$;


ALTER FUNCTION public.fn_savealumno(pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) OWNER TO sacusr;

--
-- Name: fn_savealumno(integer, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savealumno(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_pid                         INTEGER;
    var_dni                         VARCHAR         := TRIM(pin_dni);
    var_apellido                    VARCHAR         := TRIM(pin_apellido);
    var_nombre                      VARCHAR         := TRIM(pin_nombre);
    var_legajo                      VARCHAR         := TRIM(pin_legajo);

  BEGIN
    
    var_pid = fn_savepersona(pin_pid, var_dni, var_apellido, var_nombre);

    PERFORM pr_savealumno(var_pid, var_legajo);
    
    RETURN var_pid;
  END;

$$;


ALTER FUNCTION public.fn_savealumno(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) OWNER TO sacusr;

--
-- Name: fn_savecurso(integer, integer, character varying, text, smallint, timestamp without time zone); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savecurso(pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  BEGIN
    RETURN fn_savecurso(pin_mid, pin_pid, pin_nombre, pin_descripcion, pin_cupo, pin_finicio, NULL);
  END;

$$;


ALTER FUNCTION public.fn_savecurso(pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone) OWNER TO sacusr;

--
-- Name: fn_savecurso(integer, integer, character varying, text, smallint, timestamp without time zone, timestamp without time zone); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savecurso(pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone, pin_ffin timestamp without time zone) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  BEGIN
    RETURN fn_savecurso(NULL, pin_mid, pin_pid, pin_nombre, pin_descripcion, pin_cupo, pin_finicio, pin_ffin);
  END;

$$;


ALTER FUNCTION public.fn_savecurso(pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone, pin_ffin timestamp without time zone) OWNER TO sacusr;

--
-- Name: fn_savecurso(integer, integer, integer, character varying, text, smallint, timestamp without time zone, timestamp without time zone); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savecurso(pin_cid integer, pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone, pin_ffin timestamp without time zone) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_cid                         INTEGER         := pin_cid;
    var_nombre                      VARCHAR         := TRIM(pin_nombre);

  BEGIN
    IF (var_cid IS NULL) THEN
      -- no existe ==> insert
      INSERT INTO curso(mid, pid, nombre, descripcion, cupo, finicio, ffin)
        VALUES (pin_mid, pin_pid, var_nombre, pin_descripcion, pin_cupo, pin_finicio, pin_ffin)
        RETURNING cid INTO var_cid;
    ELSE
      -- sí existe ==> update
      UPDATE curso SET
        mid = pin_mid,
        pid = pin_pid,
        nombre = var_nombre,
        descripcion = pin_descripcion,
        finicio = pin_finicio,
        ffin = pin_ffin
      WHERE cid = var_cid;
    END IF;

    RETURN var_cid;
  END;

$$;


ALTER FUNCTION public.fn_savecurso(pin_cid integer, pin_mid integer, pin_pid integer, pin_nombre character varying, pin_descripcion text, pin_cupo smallint, pin_finicio timestamp without time zone, pin_ffin timestamp without time zone) OWNER TO sacusr;

--
-- Name: fn_savedocente(character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savedocente(pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  BEGIN
    RETURN fn_savedocente(NULL, pin_dni, pin_apellido, pin_nombre, pin_legajo);
  END;

$$;


ALTER FUNCTION public.fn_savedocente(pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) OWNER TO sacusr;

--
-- Name: fn_savedocente(integer, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savedocente(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_pid                         INTEGER;
    var_dni                         VARCHAR         := TRIM(pin_dni);
    var_apellido                    VARCHAR         := TRIM(pin_apellido);
    var_nombre                      VARCHAR         := TRIM(pin_nombre);
    var_legajo                      VARCHAR         := TRIM(pin_legajo);

  BEGIN
    
    var_pid = fn_savepersona(pin_pid, var_dni, var_apellido, var_nombre);

    PERFORM pr_savedocente(var_pid, var_legajo);
    
    RETURN var_pid;
  END;

$$;


ALTER FUNCTION public.fn_savedocente(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying, pin_legajo character varying) OWNER TO sacusr;

--
-- Name: fn_savemateria(character varying, text); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savemateria(pin_nombre character varying, pin_descripcion text) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  BEGIN
    RETURN fn_savemateria(NULL, pin_nombre, pin_descripcion);
  END;

$$;


ALTER FUNCTION public.fn_savemateria(pin_nombre character varying, pin_descripcion text) OWNER TO sacusr;

--
-- Name: fn_savemateria(integer, character varying, text); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savemateria(pin_mid integer, pin_nombre character varying, pin_descripcion text) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_mid                         INTEGER         := pin_mid;
    var_nombre                      VARCHAR         := TRIM(pin_nombre);

  BEGIN
    IF (var_mid IS NULL) THEN
      -- no existe ==> insert
      INSERT INTO materia(nombre, descripcion)
        VALUES (var_nombre, pin_descripcion)
        RETURNING mid INTO var_mid;
    ELSE
      -- sí existe ==> update
      UPDATE materia SET
        nombre = var_nombre,
        descripcion = pin_descripcion
      WHERE mid = var_mid;
    END IF;

    RETURN var_mid;
  END;

$$;


ALTER FUNCTION public.fn_savemateria(pin_mid integer, pin_nombre character varying, pin_descripcion text) OWNER TO sacusr;

--
-- Name: fn_savepersona(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savepersona(pin_dni character varying, pin_apellido character varying, pin_nombre character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  BEGIN
    RETURN fn_savepersona(NULL, pin_dni, pin_apellido, pin_nombre);
  END;

$$;


ALTER FUNCTION public.fn_savepersona(pin_dni character varying, pin_apellido character varying, pin_nombre character varying) OWNER TO sacusr;

--
-- Name: fn_savepersona(integer, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.fn_savepersona(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_pid                         INTEGER         := pin_pid;
    var_dni                         VARCHAR         := TRIM(pin_dni);
    var_apellido                    VARCHAR         := TRIM(pin_apellido);
    var_nombre                      VARCHAR         := TRIM(pin_nombre);

  BEGIN
    IF (var_pid IS NULL) THEN
      -- no existe ==> insert
      INSERT INTO persona(dni, apellido, nombre)
        VALUES (var_dni, var_apellido, var_nombre)
        RETURNING pid INTO var_pid;
    ELSE
      -- sí existe ==> update
      UPDATE persona SET
        dni = var_dni,
        apellido = var_apellido,
        nombre = var_nombre
      WHERE pid = var_pid;
    END IF;

    RETURN var_pid;
  END;

$$;


ALTER FUNCTION public.fn_savepersona(pin_pid integer, pin_dni character varying, pin_apellido character varying, pin_nombre character varying) OWNER TO sacusr;

--
-- Name: pr_deletealumno(integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deletealumno(pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM alumno WHERE pid = pin_pid;

    RETURN;
  END;

$$;


ALTER FUNCTION public.pr_deletealumno(pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_deleteasociacion(integer, integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deleteasociacion(pin_mid integer, pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM materia_docentes
      WHERE mid = pin_mid
      AND pid = pin_pid;
  END;

$$;


ALTER FUNCTION public.pr_deleteasociacion(pin_mid integer, pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_deletecurso(integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deletecurso(pin_cid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM curso WHERE cid = pin_cid;
  END;

$$;


ALTER FUNCTION public.pr_deletecurso(pin_cid integer) OWNER TO sacusr;

--
-- Name: pr_deletedocente(integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deletedocente(pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM docente WHERE pid = pin_pid;

    RETURN;
  END;

$$;


ALTER FUNCTION public.pr_deletedocente(pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_deleteinscripcion(integer, integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deleteinscripcion(pin_cid integer, pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM inscripcion
      WHERE cid = pin_cid
      AND pid = pin_pid;
  END;

$$;


ALTER FUNCTION public.pr_deleteinscripcion(pin_cid integer, pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_deletemateria(integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deletemateria(pin_mid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM materia WHERE mid = pin_mid;
  END;

$$;


ALTER FUNCTION public.pr_deletemateria(pin_mid integer) OWNER TO sacusr;

--
-- Name: pr_deletepersona(integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_deletepersona(pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    DELETE FROM persona WHERE pid = pin_pid;

    RETURN;
  END;

$$;


ALTER FUNCTION public.pr_deletepersona(pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_savealumno(integer, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_savealumno(pin_pid integer, pin_legajo character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_legajo                      VARCHAR         := TRIM(pin_legajo);
    var_count                       INTEGER         := 0;

  BEGIN

    SELECT COUNT(*)
      INTO var_count
      FROM alumno
      WHERE pid = pin_pid;

    IF (var_count = 0) THEN
      -- no existe ==> insert
      INSERT INTO alumno(pid, legajo) VALUES (pin_pid, var_legajo);

    ELSE
      -- sí existe ==> update
      UPDATE alumno SET legajo = var_legajo WHERE pid = pin_pid;
    END IF;

  END;

$$;


ALTER FUNCTION public.pr_savealumno(pin_pid integer, pin_legajo character varying) OWNER TO sacusr;

--
-- Name: pr_saveasociacion(integer, integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_saveasociacion(pin_mid integer, pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    INSERT INTO materia_docentes(mid, pid)
      VALUES (pin_mid, pin_pid);
  END;

$$;


ALTER FUNCTION public.pr_saveasociacion(pin_mid integer, pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_savedocente(integer, character varying); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_savedocente(pin_pid integer, pin_legajo character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_legajo                      VARCHAR         := TRIM(pin_legajo);
    var_count                       INTEGER         := 0;

  BEGIN

    SELECT COUNT(*)
      INTO var_count
      FROM docente
      WHERE pid = pin_pid;

    IF (var_count = 0) THEN
      -- no existe ==> insert
      INSERT INTO docente(pid, legajo) VALUES (pin_pid, var_legajo);

    ELSE
      -- sí existe ==> update
      UPDATE docente SET legajo = var_legajo WHERE pid = pin_pid;
    END IF;

  END;

$$;


ALTER FUNCTION public.pr_savedocente(pin_pid integer, pin_legajo character varying) OWNER TO sacusr;

--
-- Name: pr_saveinscripcion(integer, integer); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_saveinscripcion(pin_cid integer, pin_pid integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

  BEGIN
    PERFORM pr_saveinscripcion(pin_cid, pin_pid, NULL);
  END;

$$;


ALTER FUNCTION public.pr_saveinscripcion(pin_cid integer, pin_pid integer) OWNER TO sacusr;

--
-- Name: pr_saveinscripcion(integer, integer, smallint); Type: FUNCTION; Schema: public; Owner: sacusr
--

CREATE FUNCTION public.pr_saveinscripcion(pin_cid integer, pin_pid integer, pin_cfinal smallint) RETURNS void
    LANGUAGE plpgsql
    AS $$

  DECLARE
    var_count                       INTEGER := 0;

  BEGIN
    SELECT COUNT(*)
      INTO var_count
      FROM inscripcion
      WHERE cid = pin_cid
      AND pid = pin_pid;

    IF (var_count = 0) THEN
      -- no existe ==> insert
      INSERT INTO inscripcion(cid, pid, cfinal) VALUES (pin_cid, pin_pid, pin_cfinal);
    ELSE
      -- sí existe ==> update
      UPDATE inscripcion SET cfinal = pin_cfinal WHERE cid = pin_cid AND pid = pin_pid;
    END IF;
  END;

$$;


ALTER FUNCTION public.pr_saveinscripcion(pin_cid integer, pin_pid integer, pin_cfinal smallint) OWNER TO sacusr;

SET default_tablespace = '';

--
-- Name: alumno; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.alumno (
    pid integer NOT NULL,
    legajo character varying(16) NOT NULL
);


ALTER TABLE public.alumno OWNER TO sacusr;

--
-- Name: curso; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.curso (
    cid integer NOT NULL,
    mid integer NOT NULL,
    pid integer NOT NULL,
    nombre character varying(64) NOT NULL,
    descripcion text,
    cupo smallint DEFAULT 0 NOT NULL,
    finicio timestamp without time zone NOT NULL,
    ffin timestamp without time zone,
    CONSTRAINT curso_check CHECK (((ffin IS NULL) OR (finicio < ffin))),
    CONSTRAINT curso_cupo_check CHECK ((cupo > 0))
);


ALTER TABLE public.curso OWNER TO sacusr;

--
-- Name: curso_alumnos; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.curso_alumnos (
    cid integer,
    pid integer
);


ALTER TABLE public.curso_alumnos OWNER TO sacusr;

--
-- Name: curso_cid_seq; Type: SEQUENCE; Schema: public; Owner: sacusr
--

CREATE SEQUENCE public.curso_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.curso_cid_seq OWNER TO sacusr;

--
-- Name: curso_cid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sacusr
--

ALTER SEQUENCE public.curso_cid_seq OWNED BY public.curso.cid;


--
-- Name: docente; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.docente (
    pid integer NOT NULL,
    legajo character varying(16) NOT NULL
);


ALTER TABLE public.docente OWNER TO sacusr;

--
-- Name: inscripcion; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.inscripcion (
    cid integer NOT NULL,
    pid integer NOT NULL,
    cfinal smallint,
    CONSTRAINT inscripcion_cfinal_check CHECK (((cfinal IS NULL) OR ((cfinal >= 0) AND (cfinal <= 10))))
);


ALTER TABLE public.inscripcion OWNER TO sacusr;

--
-- Name: materia; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.materia (
    mid integer NOT NULL,
    nombre character varying(64) NOT NULL,
    descripcion text
);


ALTER TABLE public.materia OWNER TO sacusr;

--
-- Name: materia_docentes; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.materia_docentes (
    mid integer NOT NULL,
    pid integer NOT NULL
);


ALTER TABLE public.materia_docentes OWNER TO sacusr;

--
-- Name: materia_mid_seq; Type: SEQUENCE; Schema: public; Owner: sacusr
--

CREATE SEQUENCE public.materia_mid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.materia_mid_seq OWNER TO sacusr;

--
-- Name: materia_mid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sacusr
--

ALTER SEQUENCE public.materia_mid_seq OWNED BY public.materia.mid;


--
-- Name: persona; Type: TABLE; Schema: public; Owner: sacusr
--

CREATE TABLE public.persona (
    pid integer NOT NULL,
    dni character varying(32) NOT NULL,
    apellido character varying(64) NOT NULL,
    nombre character varying(64) NOT NULL
);


ALTER TABLE public.persona OWNER TO sacusr;

--
-- Name: persona_pid_seq; Type: SEQUENCE; Schema: public; Owner: sacusr
--

CREATE SEQUENCE public.persona_pid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.persona_pid_seq OWNER TO sacusr;

--
-- Name: persona_pid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sacusr
--

ALTER SEQUENCE public.persona_pid_seq OWNED BY public.persona.pid;


--
-- Name: v_alumno; Type: VIEW; Schema: public; Owner: sacusr
--

CREATE VIEW public.v_alumno AS
 SELECT p.pid,
    p.dni,
    p.apellido,
    p.nombre,
    a.legajo
   FROM (public.alumno a
     JOIN public.persona p ON ((a.pid = p.pid)));


ALTER TABLE public.v_alumno OWNER TO sacusr;

--
-- Name: v_curso; Type: VIEW; Schema: public; Owner: sacusr
--

CREATE VIEW public.v_curso AS
 SELECT c.cid,
    c.nombre AS curso,
    m.mid,
    m.nombre AS materia,
    c.cupo,
    c.pid AS did,
    ((((('('::text || (d.legajo)::text) || ') '::text) || (pd.apellido)::text) || ', '::text) || (pd.nombre)::text) AS docente,
    c.descripcion,
    c.finicio,
    c.ffin,
    a.legajo,
    pa.pid,
    pa.dni,
    pa.apellido,
    pa.nombre,
    i.cfinal
   FROM ((((((public.curso c
     JOIN public.materia m ON ((c.mid = m.mid)))
     JOIN public.docente d ON ((c.pid = d.pid)))
     JOIN public.persona pd ON ((c.pid = pd.pid)))
     LEFT JOIN public.inscripcion i ON ((c.cid = i.cid)))
     LEFT JOIN public.alumno a ON ((i.pid = a.pid)))
     LEFT JOIN public.persona pa ON ((a.pid = pa.pid)));


ALTER TABLE public.v_curso OWNER TO sacusr;

--
-- Name: v_docente; Type: VIEW; Schema: public; Owner: sacusr
--

CREATE VIEW public.v_docente AS
 SELECT p.pid,
    p.dni,
    p.apellido,
    p.nombre,
    d.legajo
   FROM (public.docente d
     JOIN public.persona p ON ((d.pid = p.pid)));


ALTER TABLE public.v_docente OWNER TO sacusr;

--
-- Name: v_persona; Type: VIEW; Schema: public; Owner: sacusr
--

CREATE VIEW public.v_persona AS
 SELECT p.pid,
    p.dni,
    p.apellido,
    p.nombre,
    d.legajo AS legajo_docente,
    a.legajo AS legajo_alumno
   FROM ((public.persona p
     LEFT JOIN public.docente d ON ((p.pid = d.pid)))
     LEFT JOIN public.alumno a ON ((p.pid = a.pid)));


ALTER TABLE public.v_persona OWNER TO sacusr;

--
-- Name: curso cid; Type: DEFAULT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.curso ALTER COLUMN cid SET DEFAULT nextval('public.curso_cid_seq'::regclass);


--
-- Name: materia mid; Type: DEFAULT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.materia ALTER COLUMN mid SET DEFAULT nextval('public.materia_mid_seq'::regclass);


--
-- Name: persona pid; Type: DEFAULT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.persona ALTER COLUMN pid SET DEFAULT nextval('public.persona_pid_seq'::regclass);


--
-- Data for Name: alumno; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: curso_alumnos; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: docente; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: inscripcion; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: materia; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: materia_docentes; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: sacusr
--



--
-- Name: curso_cid_seq; Type: SEQUENCE SET; Schema: public; Owner: sacusr
--

SELECT pg_catalog.setval('public.curso_cid_seq', 1, false);


--
-- Name: materia_mid_seq; Type: SEQUENCE SET; Schema: public; Owner: sacusr
--

SELECT pg_catalog.setval('public.materia_mid_seq', 1, false);


--
-- Name: persona_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: sacusr
--

SELECT pg_catalog.setval('public.persona_pid_seq', 1, false);


--
-- PostgreSQL database dump complete
--

