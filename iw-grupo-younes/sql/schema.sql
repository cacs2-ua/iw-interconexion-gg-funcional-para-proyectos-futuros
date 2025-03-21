--
-- PostgreSQL database dump
--

-- Dumped from database version 13.16 (Debian 13.16-1.pgdg120+1)
-- Dumped by pg_dump version 13.16 (Debian 13.16-1.pgdg120+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: iweb
--

CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    administrador boolean NOT NULL,
    bloqueado boolean NOT NULL,
    email character varying(255) NOT NULL,
    fecha_nacimiento date,
    nombre character varying(255),
    password character varying(255)
);


ALTER TABLE public.usuarios OWNER TO iweb;

--
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: iweb
--

CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO iweb;

--
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iweb
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: iweb
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: iweb
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

-- Tabla `pedido`
CREATE TABLE pedido (
                        id SERIAL PRIMARY KEY,
                        numero_pedido VARCHAR(255) UNIQUE NOT NULL,
                        fecha_pedido TIMESTAMP NOT NULL,
                        estado VARCHAR(255) NOT NULL,
                        total NUMERIC(10, 2) NOT NULL,
                        tipo_entrega VARCHAR(255),
                        detalles TEXT,
                        id_usuario BIGINT NOT NULL,
                        FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Tabla `producto_pedido`
CREATE TABLE producto_pedido (
                                 id SERIAL PRIMARY KEY,
                                 id_pedido BIGINT NOT NULL,
                                 id_producto BIGINT NOT NULL,
                                 cantidad INT NOT NULL,
                                 precio_unitario NUMERIC(10, 2) NOT NULL,
                                 subtotal NUMERIC(10, 2) NOT NULL,
                                 FOREIGN KEY (id_pedido) REFERENCES pedido(id) ON DELETE CASCADE,
                                 FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE CASCADE
);

