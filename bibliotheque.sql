--
-- PostgreSQL database dump
--

-- Dumped from database version 16.6 (Ubuntu 16.6-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.6 (Ubuntu 16.6-0ubuntu0.24.04.1)

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
-- Name: emprunts; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.emprunts (
    id_emprunts integer NOT NULL,
    livre_id integer,
    membre_id integer,
    date_emprunt date,
    date_retour_prevue date,
    date_retour_effective date
);


ALTER TABLE public.emprunts OWNER TO admin;

--
-- Name: emprunts_id_emprunts_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.emprunts_id_emprunts_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprunts_id_emprunts_seq OWNER TO admin;

--
-- Name: emprunts_id_emprunts_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.emprunts_id_emprunts_seq OWNED BY public.emprunts.id_emprunts;


--
-- Name: livres; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.livres (
    id integer NOT NULL,
    titre character varying(255),
    auteur character varying(255),
    categorie character varying(100),
    nombre_exemplaires integer
);


ALTER TABLE public.livres OWNER TO admin;

--
-- Name: livres_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.livres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livres_id_seq OWNER TO admin;

--
-- Name: livres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.livres_id_seq OWNED BY public.livres.id;


--
-- Name: membres; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.membres (
    id integer NOT NULL,
    nom character varying(255),
    prenom character varying(255),
    email character varying(255),
    adhesion_date date
);


ALTER TABLE public.membres OWNER TO admin;

--
-- Name: membres_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.membres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.membres_id_seq OWNER TO admin;

--
-- Name: membres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.membres_id_seq OWNED BY public.membres.id;


--
-- Name: emprunts id_emprunts; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.emprunts ALTER COLUMN id_emprunts SET DEFAULT nextval('public.emprunts_id_emprunts_seq'::regclass);


--
-- Name: livres id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.livres ALTER COLUMN id SET DEFAULT nextval('public.livres_id_seq'::regclass);


--
-- Name: membres id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.membres ALTER COLUMN id SET DEFAULT nextval('public.membres_id_seq'::regclass);


--
-- Data for Name: emprunts; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.emprunts (id_emprunts, livre_id, membre_id, date_emprunt, date_retour_prevue, date_retour_effective) FROM stdin;
1	1	1	2025-01-15	2025-01-29	2025-01-15
4	3	4	2025-01-15	2025-01-29	2025-01-10
2	5	2	2025-01-15	2025-01-29	2025-02-01
\.


--
-- Data for Name: livres; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.livres (id, titre, auteur, categorie, nombre_exemplaires) FROM stdin;
1	Les Bimanes 	Sévérin Cécile Abega	nouvelles	6
2	Balafon	Engelbert Mveng	poésie	3
3	La croix du Sud	Joseph Ngoué	théâtre	2
4	Une Saison Blanche et Sèche	André Brink	roman	2
5	Le Barbier de Séville	Beaumarchais	théâtre	4
\.


--
-- Data for Name: membres; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.membres (id, nom, prenom, email, adhesion_date) FROM stdin;
1	Solo	Béton	solobeton@example.com	2025-01-15
2	fifion	ribana	fifion123@example.com	2025-01-15
4	Abena	jean	jeanabena@example.com	2025-01-15
\.


--
-- Name: emprunts_id_emprunts_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.emprunts_id_emprunts_seq', 4, true);


--
-- Name: livres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.livres_id_seq', 5, true);


--
-- Name: membres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.membres_id_seq', 4, true);


--
-- Name: emprunts emprunts_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_pkey PRIMARY KEY (id_emprunts);


--
-- Name: livres livres_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT livres_pkey PRIMARY KEY (id);


--
-- Name: membres membres_email_key; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.membres
    ADD CONSTRAINT membres_email_key UNIQUE (email);


--
-- Name: membres membres_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.membres
    ADD CONSTRAINT membres_pkey PRIMARY KEY (id);


--
-- Name: emprunts emprunts_livre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_livre_id_fkey FOREIGN KEY (livre_id) REFERENCES public.livres(id);


--
-- Name: emprunts emprunts_membre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_membre_id_fkey FOREIGN KEY (membre_id) REFERENCES public.membres(id);


--
-- PostgreSQL database dump complete
--

