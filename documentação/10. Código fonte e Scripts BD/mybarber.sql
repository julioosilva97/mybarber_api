--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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
-- Name: agendamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agendamento (
    id integer NOT NULL,
    datahorainicio timestamp without time zone NOT NULL,
    datahoratermino timestamp without time zone NOT NULL,
    observacao character varying(200),
    status character varying(100) NOT NULL,
    valor numeric(10,2) NOT NULL,
    id_cliente integer,
    id_barbeiro integer NOT NULL,
    nome_cliente character varying(100) NOT NULL,
    notificado boolean
);


ALTER TABLE public.agendamento OWNER TO postgres;

--
-- Name: agendamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.agendamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.agendamento_id_seq OWNER TO postgres;

--
-- Name: agendamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.agendamento_id_seq OWNED BY public.agendamento.id;


--
-- Name: agendamento_servico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agendamento_servico (
    id_agendamento integer NOT NULL,
    id_servico integer NOT NULL
);


ALTER TABLE public.agendamento_servico OWNER TO postgres;

--
-- Name: barbearia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.barbearia (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    descricao character varying(100),
    id_endereco integer NOT NULL,
    qtdfuncionario integer,
    qtdcliente integer,
    qtdservico integer
);


ALTER TABLE public.barbearia OWNER TO postgres;

--
-- Name: barbearia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.barbearia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.barbearia_id_seq OWNER TO postgres;

--
-- Name: barbearia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.barbearia_id_seq OWNED BY public.barbearia.id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    nome character varying(100),
    sobrenome character varying(100),
    telefone character varying(100),
    data_nascimento date,
    id_endereco integer
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_barbearia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente_barbearia (
    id_cliente integer NOT NULL,
    id_barbearia integer NOT NULL
);


ALTER TABLE public.cliente_barbearia OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id integer NOT NULL,
    logradouro character varying(200),
    bairro character varying(100),
    numero integer,
    cep character varying(100),
    cidade character varying(100),
    uf character varying(2)
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.endereco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_seq OWNER TO postgres;

--
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    id integer NOT NULL,
    nome character varying(100),
    sobrenome character varying(100),
    telefone character varying(100),
    data_nascimento date,
    id_endereco integer,
    cargo character varying(100) NOT NULL,
    id_barbearia integer NOT NULL
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- Name: funcionario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.funcionario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionario_id_seq OWNER TO postgres;

--
-- Name: funcionario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.funcionario_id_seq OWNED BY public.funcionario.id;


--
-- Name: gerenciar_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gerenciar_usuario (
    id_usuario integer,
    id_cliente integer,
    id_funcionario integer
);


ALTER TABLE public.gerenciar_usuario OWNER TO postgres;

--
-- Name: horario_atendimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.horario_atendimento (
    id integer NOT NULL,
    dia character varying(100) NOT NULL,
    aberto boolean NOT NULL,
    entrada time without time zone,
    saida time without time zone,
    id_funcionario integer NOT NULL,
    saida_almoco time without time zone,
    entrada_almoco time without time zone,
    almoco boolean
);


ALTER TABLE public.horario_atendimento OWNER TO postgres;

--
-- Name: horario_atendimento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.horario_atendimento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.horario_atendimento_id_seq OWNER TO postgres;

--
-- Name: horario_atendimento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.horario_atendimento_id_seq OWNED BY public.horario_atendimento.id;


--
-- Name: perfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perfil (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL
);


ALTER TABLE public.perfil OWNER TO postgres;

--
-- Name: perfil_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.perfil_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.perfil_id_seq OWNER TO postgres;

--
-- Name: perfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.perfil_id_seq OWNED BY public.perfil.id;


--
-- Name: perfil_permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perfil_permissao (
    id_perfil integer NOT NULL,
    id_permissao integer NOT NULL
);


ALTER TABLE public.perfil_permissao OWNER TO postgres;

--
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissao (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL
);


ALTER TABLE public.permissao OWNER TO postgres;

--
-- Name: permissao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permissao_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permissao_id_seq OWNER TO postgres;

--
-- Name: permissao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permissao_id_seq OWNED BY public.permissao.id;


--
-- Name: promocao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.promocao (
    id integer NOT NULL,
    datainicio date NOT NULL,
    datafim date NOT NULL,
    descricao character varying(50),
    id_servico integer NOT NULL,
    status boolean,
    valor numeric(10,2)
);


ALTER TABLE public.promocao OWNER TO postgres;

--
-- Name: promocao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.promocao_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.promocao_id_seq OWNER TO postgres;

--
-- Name: promocao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.promocao_id_seq OWNED BY public.promocao.id;


--
-- Name: servico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servico (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL,
    valor numeric(10,2) NOT NULL,
    tempo time without time zone NOT NULL,
    id_barbearia integer NOT NULL
);


ALTER TABLE public.servico OWNER TO postgres;

--
-- Name: servico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servico_id_seq OWNER TO postgres;

--
-- Name: servico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servico_id_seq OWNED BY public.servico.id;


--
-- Name: token_de_verificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.token_de_verificacao (
    id integer NOT NULL,
    token character varying(100) NOT NULL,
    data_hora_emissao timestamp without time zone NOT NULL,
    data_hora_expiracao timestamp without time zone NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.token_de_verificacao OWNER TO postgres;

--
-- Name: token_de_verificacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.token_de_verificacao_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.token_de_verificacao_id_seq OWNER TO postgres;

--
-- Name: token_de_verificacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.token_de_verificacao_id_seq OWNED BY public.token_de_verificacao.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    login character varying(100),
    senha character varying(300),
    ativo boolean NOT NULL,
    email character varying(100)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_perfil (
    id_usuario integer,
    id_perfil integer
);


ALTER TABLE public.usuario_perfil OWNER TO postgres;

--
-- Name: agendamento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento ALTER COLUMN id SET DEFAULT nextval('public.agendamento_id_seq'::regclass);


--
-- Name: barbearia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.barbearia ALTER COLUMN id SET DEFAULT nextval('public.barbearia_id_seq'::regclass);


--
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- Name: endereco id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);


--
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.funcionario_id_seq'::regclass);


--
-- Name: horario_atendimento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horario_atendimento ALTER COLUMN id SET DEFAULT nextval('public.horario_atendimento_id_seq'::regclass);


--
-- Name: perfil id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil ALTER COLUMN id SET DEFAULT nextval('public.perfil_id_seq'::regclass);


--
-- Name: permissao id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao ALTER COLUMN id SET DEFAULT nextval('public.permissao_id_seq'::regclass);


--
-- Name: promocao id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promocao ALTER COLUMN id SET DEFAULT nextval('public.promocao_id_seq'::regclass);


--
-- Name: servico id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servico ALTER COLUMN id SET DEFAULT nextval('public.servico_id_seq'::regclass);


--
-- Name: token_de_verificacao id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token_de_verificacao ALTER COLUMN id SET DEFAULT nextval('public.token_de_verificacao_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Data for Name: agendamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agendamento (id, datahorainicio, datahoratermino, observacao, status, valor, id_cliente, id_barbeiro, nome_cliente, notificado) FROM stdin;
1	2020-11-10 08:00:00	2020-11-10 09:00:00	\N	CONCLUIDO	20.00	\N	1	ribamar	\N
2	2020-11-11 08:00:00	2020-11-11 09:00:00	\N	CONCLUIDO	20.00	\N	1	neymar	\N
3	2020-11-09 08:00:00	2020-11-09 08:30:00	\N	CONCLUIDO	10.00	\N	2	neymar	\N
4	2020-11-11 09:30:00	2020-11-11 10:00:00	\N	CONCLUIDO	10.00	\N	1	brenner	\N
5	2020-12-01 08:00:00	2020-12-01 08:30:00	\N	AGENDADO	10.00	\N	1	brenner	\N
7	2020-11-11 10:30:00	2020-11-11 11:30:00	\N	CONCLUIDO	20.00	\N	1	trellez	\N
8	2020-11-16 08:00:00	2020-11-16 08:30:00	\N	CANCELADO	10.00	\N	2	neymar	\N
6	2020-11-09 08:00:00	2020-11-09 09:30:00	\N	CONCLUIDO	30.00	\N	1	ribamar	\N
9	2020-11-16 08:30:00	2020-11-16 10:00:00	\N	CANCELADO	30.00	\N	2	brenner	\N
10	2020-11-16 08:00:00	2020-11-16 08:30:00	\N	AGENDADO	10.00	\N	2	ney	\N
\.


--
-- Data for Name: agendamento_servico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agendamento_servico (id_agendamento, id_servico) FROM stdin;
1	1
2	1
3	2
4	2
5	2
6	2
6	1
7	1
8	2
9	2
9	1
10	2
\.


--
-- Data for Name: barbearia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.barbearia (id, nome, descricao, id_endereco, qtdfuncionario, qtdcliente, qtdservico) FROM stdin;
1	barber	show	1	2	0	2
2	barbearia do riba	\N	3	1	0	0
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome, sobrenome, telefone, data_nascimento, id_endereco) FROM stdin;
\.


--
-- Data for Name: cliente_barbearia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente_barbearia (id_cliente, id_barbearia) FROM stdin;
\.


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.endereco (id, logradouro, bairro, numero, cep, cidade, uf) FROM stdin;
1	rua 10	souza	145	0000	itaqua	SP
2	\N	Pinheiros	526	05432-000	S├úo Paulo	SP
3	\N	Pinheiros	526	05432-000	S├úo Paulo	SP
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	criacao-tabelas	SQL	V1__criacao-tabelas.sql	-1478670440	postgres	2020-10-30 21:08:59.081223	701	t
2	2	inserindo-dados-necessarios-login	SQL	V2__inserindo-dados-necessarios-login.sql	-675915306	postgres	2020-10-30 21:08:59.835875	34	t
3	3	criacao-tabela-gereciar-usuario	SQL	V3__criacao-tabela-gereciar-usuario.sql	-401456003	postgres	2020-10-30 21:08:59.884443	288	t
4	4	deletar coluna usuario de cliente funcionario	SQL	V4__deletar_coluna_usuario_de_cliente_funcionario.sql	-811457585	postgres	2020-10-30 21:09:00.217202	21	t
5	5	adicionando perfil cliente	SQL	V5__adicionando_perfil_cliente.sql	948192702	postgres	2020-10-30 21:09:00.2622	2	t
6	6	inserindo tabela promocao	SQL	V6__inserindo_tabela_promocao.sql	-1319901711	postgres	2020-10-30 21:09:00.274599	41	t
7	7	add-colunas-almoco-horario-atendimento	SQL	V7__add-colunas-almoco-horario-atendimento.sql	-678158881	postgres	2020-10-30 21:09:00.335664	4	t
8	8	alterando-tipo-nome-coluna-dia-agendamento	SQL	V8__alterando-tipo-nome-coluna-dia-agendamento.sql	1687018873	postgres	2020-10-30 21:09:00.354165	129	t
9	9	tirando-not-null-entrada-saida-horario-atendimento	SQL	V9__tirando-not-null-entrada-saida-horario-atendimento.sql	448053146	postgres	2020-10-30 21:09:00.520928	2	t
10	10	inserindo coluna status tabela promocao	SQL	V10__inserindo_coluna_status_tabela_promocao.sql	-351234076	postgres	2020-10-30 21:09:00.532579	97	t
11	11	add-coluna-notificado-agendamento	SQL	V11__add-coluna-notificado-agendamento.sql	-2027611436	postgres	2020-10-30 21:09:00.636973	2	t
12	12	add-coluna-qtd-barbearia	SQL	V12__add-coluna-qtd-barbearia.sql	-446280722	postgres	2020-10-30 21:09:00.646647	3	t
13	13	adicionar coluna valor tabela promocao	SQL	V13__adicionar_coluna_valor_tabela_promocao.sql	1581256300	postgres	2020-10-30 21:09:00.660217	2	t
14	14	add-flag-almoco-horario-atendimento	SQL	V14__add-flag-almoco-horario-atendimento.sql	-175822304	postgres	2020-10-30 21:09:00.670881	1	t
15	15	add-on-delete-cascade-em-usuario	SQL	V15__add-on-delete-cascade-em-usuario.sql	309113605	postgres	2020-10-30 21:09:00.680102	15	t
\.


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id, nome, sobrenome, telefone, data_nascimento, id_endereco, cargo, id_barbearia) FROM stdin;
1	Julio		4644	\N	\N	BARBEIRO	1
2	vitin	marc	(11) 1111-1111	1997-03-19	\N	BARBEIRO	1
3	ribamar	\N	111111111	\N	\N	BARBEIRO	2
\.


--
-- Data for Name: gerenciar_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gerenciar_usuario (id_usuario, id_cliente, id_funcionario) FROM stdin;
1	\N	1
2	\N	2
3	\N	3
\.


--
-- Data for Name: horario_atendimento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.horario_atendimento (id, dia, aberto, entrada, saida, id_funcionario, saida_almoco, entrada_almoco, almoco) FROM stdin;
1	SEGUNDA	t	08:00:00	18:00:00	1	\N	\N	f
2	TERCA	t	08:00:00	18:00:00	1	\N	\N	f
3	QUARTA	t	08:00:00	18:00:00	1	\N	\N	f
4	QUINTA	f	\N	\N	1	\N	\N	f
5	SEXTA	f	\N	\N	1	\N	\N	f
6	SABADO	f	\N	\N	1	\N	\N	f
7	DOMINGO	f	\N	\N	1	\N	\N	f
8	SEGUNDA	t	08:00:00	23:00:00	2	\N	\N	f
9	TERCA	f	\N	\N	2	\N	\N	f
10	QUARTA	f	\N	\N	2	\N	\N	f
11	QUINTA	f	\N	\N	2	\N	\N	f
12	SEXTA	f	\N	\N	2	\N	\N	f
13	SABADO	f	\N	\N	2	\N	\N	f
14	DOMINGO	f	\N	\N	2	\N	\N	f
\.


--
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.perfil (id, descricao) FROM stdin;
1	ADMINISTRADOR
2	CLIENTE
\.


--
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.perfil_permissao (id_perfil, id_permissao) FROM stdin;
1	1
1	2
1	3
1	4
\.


--
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissao (id, descricao) FROM stdin;
1	CADASTRAR_SERVICO
2	LISTAR_SERVICO
3	EXCLUIR_SERVICO
4	EDITAR_SERVICO
\.


--
-- Data for Name: promocao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.promocao (id, datainicio, datafim, descricao, id_servico, status, valor) FROM stdin;
\.


--
-- Data for Name: servico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.servico (id, descricao, valor, tempo, id_barbearia) FROM stdin;
1	corte	20.00	01:00:00	1
2	barba	10.00	00:30:00	1
\.


--
-- Data for Name: token_de_verificacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.token_de_verificacao (id, token, data_hora_emissao, data_hora_expiracao, id_usuario) FROM stdin;
8	84a6c278-5944-447d-848d-e7cec8d99379	2020-11-03 19:50:22.439739	2020-11-04 19:50:22.439739	1
9	5790af2c-38d8-4cf5-861d-f5805f09f8cb	2020-11-06 19:43:10.50032	2020-11-07 19:43:10.50032	2
10	4ecf70a3-4248-40c9-895d-ceb8bfd3bc9d	2020-11-11 20:45:39.1895	2020-11-12 20:45:39.1895	3
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, login, senha, ativo, email) FROM stdin;
2	vitin	\N	f	teste@email.com
3	riba	\N	f	victormarcantonio16@gmail.com
1	teste	$2a$10$4zADxyBgo3IGwCXt9YW0jeSmzOlyFHc40SN5Ps55b7PEJHkJV/pGm	t	victor_marcantonio@hotmail.com
\.


--
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_perfil (id_usuario, id_perfil) FROM stdin;
1	1
2	1
3	1
\.


--
-- Name: agendamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.agendamento_id_seq', 10, true);


--
-- Name: barbearia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.barbearia_id_seq', 2, true);


--
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 1, false);


--
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.endereco_id_seq', 3, true);


--
-- Name: funcionario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.funcionario_id_seq', 3, true);


--
-- Name: horario_atendimento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.horario_atendimento_id_seq', 14, true);


--
-- Name: perfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.perfil_id_seq', 2, true);


--
-- Name: permissao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permissao_id_seq', 4, true);


--
-- Name: promocao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.promocao_id_seq', 1, false);


--
-- Name: servico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.servico_id_seq', 2, true);


--
-- Name: token_de_verificacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.token_de_verificacao_id_seq', 10, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 3, true);


--
-- Name: agendamento agendamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_pkey PRIMARY KEY (id);


--
-- Name: barbearia barbearia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.barbearia
    ADD CONSTRAINT barbearia_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);


--
-- Name: gerenciar_usuario gerenciar_usuario_id_cliente_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerenciar_usuario
    ADD CONSTRAINT gerenciar_usuario_id_cliente_key UNIQUE (id_cliente);


--
-- Name: gerenciar_usuario gerenciar_usuario_id_funcionario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerenciar_usuario
    ADD CONSTRAINT gerenciar_usuario_id_funcionario_key UNIQUE (id_funcionario);


--
-- Name: gerenciar_usuario gerenciar_usuario_id_usuario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerenciar_usuario
    ADD CONSTRAINT gerenciar_usuario_id_usuario_key UNIQUE (id_usuario);


--
-- Name: horario_atendimento horario_atendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horario_atendimento
    ADD CONSTRAINT horario_atendimento_pkey PRIMARY KEY (id);


--
-- Name: perfil perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id);


--
-- Name: permissao permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


--
-- Name: promocao promocao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promocao
    ADD CONSTRAINT promocao_pkey PRIMARY KEY (id);


--
-- Name: servico servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servico
    ADD CONSTRAINT servico_pkey PRIMARY KEY (id);


--
-- Name: token_de_verificacao token_de_verificacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token_de_verificacao
    ADD CONSTRAINT token_de_verificacao_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- Name: usuario usuario_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_login_key UNIQUE (login);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: agendamento agendamento_id_barbeiro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_id_barbeiro_fkey FOREIGN KEY (id_barbeiro) REFERENCES public.funcionario(id);


--
-- Name: agendamento agendamento_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);


--
-- Name: agendamento_servico agendamento_servico_id_agendamento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento_servico
    ADD CONSTRAINT agendamento_servico_id_agendamento_fkey FOREIGN KEY (id_agendamento) REFERENCES public.agendamento(id) ON DELETE CASCADE;


--
-- Name: agendamento_servico agendamento_servico_id_servico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento_servico
    ADD CONSTRAINT agendamento_servico_id_servico_fkey FOREIGN KEY (id_servico) REFERENCES public.servico(id) ON DELETE CASCADE;


--
-- Name: barbearia barbearia_id_endereco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.barbearia
    ADD CONSTRAINT barbearia_id_endereco_fkey FOREIGN KEY (id_endereco) REFERENCES public.endereco(id);


--
-- Name: cliente_barbearia cliente_barbearia_id_barbearia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_barbearia
    ADD CONSTRAINT cliente_barbearia_id_barbearia_fkey FOREIGN KEY (id_barbearia) REFERENCES public.barbearia(id) ON DELETE CASCADE;


--
-- Name: cliente_barbearia cliente_barbearia_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_barbearia
    ADD CONSTRAINT cliente_barbearia_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id) ON DELETE CASCADE;


--
-- Name: cliente cliente_id_endereco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_endereco_fkey FOREIGN KEY (id_endereco) REFERENCES public.endereco(id);


--
-- Name: funcionario funcionario_id_barbearia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_id_barbearia_fkey FOREIGN KEY (id_barbearia) REFERENCES public.barbearia(id);


--
-- Name: funcionario funcionario_id_endereco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_id_endereco_fkey FOREIGN KEY (id_endereco) REFERENCES public.endereco(id);


--
-- Name: gerenciar_usuario gerenciar_usuario_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerenciar_usuario
    ADD CONSTRAINT gerenciar_usuario_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);


--
-- Name: gerenciar_usuario gerenciar_usuario_id_funcionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerenciar_usuario
    ADD CONSTRAINT gerenciar_usuario_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(id);


--
-- Name: gerenciar_usuario gerenciar_usuario_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gerenciar_usuario
    ADD CONSTRAINT gerenciar_usuario_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id) ON DELETE CASCADE;


--
-- Name: horario_atendimento horario_atendimento_id_funcionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horario_atendimento
    ADD CONSTRAINT horario_atendimento_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(id) ON DELETE CASCADE;


--
-- Name: perfil_permissao perfil_permissao_id_perfil_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil_permissao
    ADD CONSTRAINT perfil_permissao_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES public.perfil(id) ON DELETE CASCADE;


--
-- Name: perfil_permissao perfil_permissao_id_permissao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil_permissao
    ADD CONSTRAINT perfil_permissao_id_permissao_fkey FOREIGN KEY (id_permissao) REFERENCES public.permissao(id) ON DELETE CASCADE;


--
-- Name: promocao promocao_id_servico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promocao
    ADD CONSTRAINT promocao_id_servico_fkey FOREIGN KEY (id_servico) REFERENCES public.servico(id) ON DELETE CASCADE;


--
-- Name: servico servico_id_barbearia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servico
    ADD CONSTRAINT servico_id_barbearia_fkey FOREIGN KEY (id_barbearia) REFERENCES public.barbearia(id) ON DELETE CASCADE;


--
-- Name: token_de_verificacao token_de_verificacao_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token_de_verificacao
    ADD CONSTRAINT token_de_verificacao_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id) ON DELETE CASCADE;


--
-- Name: usuario_perfil usuario_perfil_id_perfil_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_perfil
    ADD CONSTRAINT usuario_perfil_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES public.perfil(id) ON DELETE CASCADE;


--
-- Name: usuario_perfil usuario_perfil_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_perfil
    ADD CONSTRAINT usuario_perfil_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

