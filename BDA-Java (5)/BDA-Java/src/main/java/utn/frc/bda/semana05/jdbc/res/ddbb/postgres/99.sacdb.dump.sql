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

ALTER TABLE ONLY public.materia_docentes DROP CONSTRAINT materia_docentes_pid_fkey;
ALTER TABLE ONLY public.materia_docentes DROP CONSTRAINT materia_docentes_mid_fkey;
ALTER TABLE ONLY public.inscripcion DROP CONSTRAINT inscripcion_pid_fkey;
ALTER TABLE ONLY public.inscripcion DROP CONSTRAINT inscripcion_cid_fkey;
ALTER TABLE ONLY public.docente DROP CONSTRAINT docente_pid_fkey;
ALTER TABLE ONLY public.curso DROP CONSTRAINT curso_mid_fkey;
ALTER TABLE ONLY public.alumno DROP CONSTRAINT alumno_pid_fkey;
DROP INDEX public.idx_persona_apenom;
DROP INDEX public.idx_curso_finicio;
DROP INDEX public.i_crs_mns_element;
DROP INDEX public.i_crs_mns_cid;
ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_dni_key;
ALTER TABLE ONLY public.materia DROP CONSTRAINT materia_pkey;
ALTER TABLE ONLY public.materia DROP CONSTRAINT materia_nombre_key;
ALTER TABLE ONLY public.materia_docentes DROP CONSTRAINT materia_docentes_pkey;
ALTER TABLE ONLY public.inscripcion DROP CONSTRAINT inscripcion_pkey;
ALTER TABLE ONLY public.docente DROP CONSTRAINT docente_pkey;
ALTER TABLE ONLY public.docente DROP CONSTRAINT docente_legajo_key;
ALTER TABLE ONLY public.curso DROP CONSTRAINT curso_pkey;
ALTER TABLE ONLY public.curso DROP CONSTRAINT curso_nombre_key;
ALTER TABLE ONLY public.alumno DROP CONSTRAINT alumno_pkey;
ALTER TABLE ONLY public.alumno DROP CONSTRAINT alumno_legajo_key;
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

COPY public.alumno (pid, legajo) FROM stdin;
1	LA-10.001
2	LA-10.002
3	LA-10.003
4	LA-10.004
5	LA-10.005
6	LA-10.006
7	LA-10.007
8	LA-10.008
9	LA-10.009
10	LA-10.010
11	LA-10.011
12	LA-10.012
13	LA-10.013
14	LA-10.014
15	LA-10.015
16	LA-10.016
17	LA-20.001
18	LA-20.002
19	LA-20.003
20	LA-20.004
21	LA-20.005
22	LA-20.006
23	LA-20.007
24	LA-20.008
25	LA-20.009
26	LA-20.010
27	LA-20.011
28	LA-20.012
29	LA-30.001
30	LA-30.002
31	LA-30.003
32	LA-30.004
33	LA-30.005
34	LA-30.006
35	LA-30.007
36	LA-30.008
37	LA-30.009
38	LA-30.010
39	LA-30.011
40	LA-30.012
\.


--
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.curso (cid, mid, pid, nombre, descripcion, cupo, finicio, ffin) FROM stdin;
1	1	1	Física Teórica	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.	24	2016-03-01 13:45:10	\N
2	2	17	Historia de la Música	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.	24	2016-03-01 13:47:18	\N
3	3	29	Introducción a la Filosofía	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.	24	2016-03-01 13:52:22	\N
\.


--
-- Data for Name: curso_alumnos; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.curso_alumnos (cid, pid) FROM stdin;
\.


--
-- Data for Name: docente; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.docente (pid, legajo) FROM stdin;
1	LD-10.001
17	LD-20.001
29	LD-30.001
\.


--
-- Data for Name: inscripcion; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.inscripcion (cid, pid, cfinal) FROM stdin;
1	2	\N
1	3	\N
1	4	\N
1	5	\N
1	6	\N
1	7	\N
1	8	\N
1	9	\N
1	10	\N
1	11	\N
1	12	\N
1	13	\N
1	14	\N
1	15	\N
1	16	\N
2	18	\N
2	19	\N
2	20	\N
2	21	\N
2	22	\N
2	23	\N
2	24	\N
2	25	\N
2	26	\N
2	27	\N
2	28	\N
3	30	\N
3	31	\N
3	32	\N
3	33	\N
3	34	\N
3	35	\N
3	36	\N
3	37	\N
3	38	\N
3	39	\N
3	40	\N
\.


--
-- Data for Name: materia; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.materia (mid, nombre, descripcion) FROM stdin;
1	Física	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.
2	Música	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.
3	Filosofía	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.
\.


--
-- Data for Name: materia_docentes; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.materia_docentes (mid, pid) FROM stdin;
1	1
2	17
3	29
\.


--
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: sacusr
--

COPY public.persona (pid, dni, apellido, nombre) FROM stdin;
1	1642-12-25/1727-03-20	Newton	Isaac
2	1629-04-14/1695-07-08	Huygens	Christiaan
3	1646-07-01/1716-11-14	Leibniz	Gottfried Wilhelm
4	1736-06-14/1806-08-23	de Coulomb	Charles-Augustin
5	1736-01-19/1819-08-25	Watt	James
6	1745-02-18/1827-03-05	Volta	Alessandro Giuseppe Antonio Anastasio
7	1775-01-20/1836-06-10	Ampère	André-Marie
8	1789-03-16/1854-07-06	Ohm	Georg Simon
9	1791-09-22/1867-08-25	Faraday	Michael
10	1818-12-24/1889-10-11	Joule	James Prescott
11	1831-06-13/1879-11-05	Maxwell	James Clerk
12	1856-07-10/1943-01-07	Tesla	Nikola
13	1857-02-22/1894-01-01	Hertz	Heinrich Rudolf
14	1867-11-07/1934-07-04	Skłodowska-Curie	Maria Salomea
15	1879-03-14/1955-04-18	Einstein	Albert
16	1885-10-07/1962-11-18	Bohr	Niels
17	1685-03-31/1750-07-28	Bach	Johann Sebastian
18	1678-03-04/1741-07-28	Vivaldi	Antonio Lucio
19	1756-01-27/1791-12-05	Mozart	Wolfgang Amadeus
20	1770-12-??/1827-03-26	van Beethoven	Ludwig
21	1810-03-01/1849-10-17	Chopin	Frédéric François
22	1811-10-22/1886-07-31	Liszt	Franz
23	1813-05-22/1883-02-13	Wagner	Wilhelm Richard
24	1825-10-25/1899-06-03	Strauss	Johann Baptist II
25	1833-05-07/1897-04-03	Brahms	Johannes
26	1840-05-07/1893-11-06	Tchaikovsky	Pyotr Ilyich
27	1928-11-10/2020-07-06	Morricone	Enio
28	1932-02-08/	Williams	John Towner
29	1596-03-31/1650-02-11	Descartes	René
30	1632-08-29/1704-10-28	Locke	John
31	1724-04-22/1804-02-12	Kant	Immanuel
32	1770-08-27/1831-11-14	Hegel	Georg Wilhelm Friedrich
33	1788-02-22/1860-09-21	Schopenhauer	Arthur
34	1798-01-19/1857-09-05	Comte	Isidore Marie Auguste François Xavier
35	1844-10-15/1900-08-25	Nietzsche	Friedrich Wilhelm
36	1864-04-21/1920-06-14	Weber	Maximilian Karl Emil
37	1889-09-26/1976-05-26	Heidegger	Martin
38	1872-05-18/1970-02-02	Russell	Bertrand Arthur William
39	1883-05-09/1955-10-18	Ortega y Gasset	José
40	1905-06-21/1980-04-15	Sartre	Jean-Paul Charles Aymard
\.


--
-- Name: curso_cid_seq; Type: SEQUENCE SET; Schema: public; Owner: sacusr
--

SELECT pg_catalog.setval('public.curso_cid_seq', 3, true);


--
-- Name: materia_mid_seq; Type: SEQUENCE SET; Schema: public; Owner: sacusr
--

SELECT pg_catalog.setval('public.materia_mid_seq', 3, true);


--
-- Name: persona_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: sacusr
--

SELECT pg_catalog.setval('public.persona_pid_seq', 40, true);


--
-- Name: alumno alumno_legajo_key; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.alumno
    ADD CONSTRAINT alumno_legajo_key UNIQUE (legajo);


--
-- Name: alumno alumno_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.alumno
    ADD CONSTRAINT alumno_pkey PRIMARY KEY (pid);


--
-- Name: curso curso_nombre_key; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_nombre_key UNIQUE (nombre);


--
-- Name: curso curso_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (cid);


--
-- Name: docente docente_legajo_key; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.docente
    ADD CONSTRAINT docente_legajo_key UNIQUE (legajo);


--
-- Name: docente docente_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.docente
    ADD CONSTRAINT docente_pkey PRIMARY KEY (pid);


--
-- Name: inscripcion inscripcion_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.inscripcion
    ADD CONSTRAINT inscripcion_pkey PRIMARY KEY (cid, pid);


--
-- Name: materia_docentes materia_docentes_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.materia_docentes
    ADD CONSTRAINT materia_docentes_pkey PRIMARY KEY (mid, pid);


--
-- Name: materia materia_nombre_key; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT materia_nombre_key UNIQUE (nombre);


--
-- Name: materia materia_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT materia_pkey PRIMARY KEY (mid);


--
-- Name: persona persona_dni_key; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_dni_key UNIQUE (dni);


--
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (pid);


--
-- Name: i_crs_mns_cid; Type: INDEX; Schema: public; Owner: sacusr
--

CREATE INDEX i_crs_mns_cid ON public.curso_alumnos USING btree (cid);


--
-- Name: i_crs_mns_element; Type: INDEX; Schema: public; Owner: sacusr
--

CREATE INDEX i_crs_mns_element ON public.curso_alumnos USING btree (pid);


--
-- Name: idx_curso_finicio; Type: INDEX; Schema: public; Owner: sacusr
--

CREATE INDEX idx_curso_finicio ON public.curso USING btree (finicio);


--
-- Name: idx_persona_apenom; Type: INDEX; Schema: public; Owner: sacusr
--

CREATE INDEX idx_persona_apenom ON public.persona USING btree (apellido, nombre);


--
-- Name: alumno alumno_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.alumno
    ADD CONSTRAINT alumno_pid_fkey FOREIGN KEY (pid) REFERENCES public.persona(pid);


--
-- Name: curso curso_mid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_mid_fkey FOREIGN KEY (mid, pid) REFERENCES public.materia_docentes(mid, pid);


--
-- Name: docente docente_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.docente
    ADD CONSTRAINT docente_pid_fkey FOREIGN KEY (pid) REFERENCES public.persona(pid);


--
-- Name: inscripcion inscripcion_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.inscripcion
    ADD CONSTRAINT inscripcion_cid_fkey FOREIGN KEY (cid) REFERENCES public.curso(cid);


--
-- Name: inscripcion inscripcion_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.inscripcion
    ADD CONSTRAINT inscripcion_pid_fkey FOREIGN KEY (pid) REFERENCES public.alumno(pid);


--
-- Name: materia_docentes materia_docentes_mid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.materia_docentes
    ADD CONSTRAINT materia_docentes_mid_fkey FOREIGN KEY (mid) REFERENCES public.materia(mid);


--
-- Name: materia_docentes materia_docentes_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sacusr
--

ALTER TABLE ONLY public.materia_docentes
    ADD CONSTRAINT materia_docentes_pid_fkey FOREIGN KEY (pid) REFERENCES public.docente(pid);


--
-- PostgreSQL database dump complete
--

