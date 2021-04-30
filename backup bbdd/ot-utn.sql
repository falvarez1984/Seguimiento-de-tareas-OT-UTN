PGDMP         *    
            y            ot-utn    12.3    12.3 V    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16393    ot-utn    DATABASE     �   CREATE DATABASE "ot-utn" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Argentina.1252' LC_CTYPE = 'Spanish_Argentina.1252';
    DROP DATABASE "ot-utn";
                postgres    false                        3079    16394 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    1            �            1259    16403    aa1instituto    TABLE     n   CREATE TABLE public.aa1instituto (
    id_inst integer NOT NULL,
    n_inst character varying(20) NOT NULL
);
     DROP TABLE public.aa1instituto;
       public         heap    postgres    false            �            1259    16406    aa1instituto_id_inst_seq    SEQUENCE     �   CREATE SEQUENCE public.aa1instituto_id_inst_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.aa1instituto_id_inst_seq;
       public          postgres    false    203            �           0    0    aa1instituto_id_inst_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.aa1instituto_id_inst_seq OWNED BY public.aa1instituto.id_inst;
          public          postgres    false    204            �            1259    16408    aa2depto    TABLE     �   CREATE TABLE public.aa2depto (
    id_depto integer NOT NULL,
    id_inst1 integer NOT NULL,
    n_depto character varying(30) NOT NULL
);
    DROP TABLE public.aa2depto;
       public         heap    postgres    false            �            1259    16411    aa2depto_id_depto_seq    SEQUENCE     �   CREATE SEQUENCE public.aa2depto_id_depto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.aa2depto_id_depto_seq;
       public          postgres    false    205            �           0    0    aa2depto_id_depto_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.aa2depto_id_depto_seq OWNED BY public.aa2depto.id_depto;
          public          postgres    false    206            �            1259    16413    aa3servicio    TABLE     �   CREATE TABLE public.aa3servicio (
    id_serv integer NOT NULL,
    id_depto1 integer NOT NULL,
    n_servicio character varying(50) NOT NULL
);
    DROP TABLE public.aa3servicio;
       public         heap    postgres    false            �            1259    16416    aa3servicio_id_serv_seq    SEQUENCE     �   CREATE SEQUENCE public.aa3servicio_id_serv_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.aa3servicio_id_serv_seq;
       public          postgres    false    207            �           0    0    aa3servicio_id_serv_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.aa3servicio_id_serv_seq OWNED BY public.aa3servicio.id_serv;
          public          postgres    false    208            �            1259    16418    aa4tipoequipo    TABLE     u   CREATE TABLE public.aa4tipoequipo (
    id_tequipo integer NOT NULL,
    n_tequipo character varying(50) NOT NULL
);
 !   DROP TABLE public.aa4tipoequipo;
       public         heap    postgres    false            �            1259    16421    aa4tipoequipo_id_tequipo_seq    SEQUENCE     �   CREATE SEQUENCE public.aa4tipoequipo_id_tequipo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.aa4tipoequipo_id_tequipo_seq;
       public          postgres    false    209            �           0    0    aa4tipoequipo_id_tequipo_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.aa4tipoequipo_id_tequipo_seq OWNED BY public.aa4tipoequipo.id_tequipo;
          public          postgres    false    210            �            1259    16423 	   aa5equipo    TABLE     �   CREATE TABLE public.aa5equipo (
    id_equipo integer NOT NULL,
    id_tequipo1 integer NOT NULL,
    n_equipo character varying(50) NOT NULL
);
    DROP TABLE public.aa5equipo;
       public         heap    postgres    false            �            1259    16426    aa5equipo_id_equipo_seq    SEQUENCE     �   CREATE SEQUENCE public.aa5equipo_id_equipo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.aa5equipo_id_equipo_seq;
       public          postgres    false    211            �           0    0    aa5equipo_id_equipo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.aa5equipo_id_equipo_seq OWNED BY public.aa5equipo.id_equipo;
          public          postgres    false    212            �            1259    16428    aa6personal    TABLE     S   CREATE TABLE public.aa6personal (
    n_personal character varying(50) NOT NULL
);
    DROP TABLE public.aa6personal;
       public         heap    postgres    false            �            1259    16431    cierre    TABLE     �   CREATE TABLE public.cierre (
    id_pedido5 integer NOT NULL,
    equip_rep boolean,
    reg_sal boolean,
    fecha_cierre character varying(15),
    informe_h boolean,
    informe_f boolean,
    obs_f character varying(200)
);
    DROP TABLE public.cierre;
       public         heap    postgres    false            �            1259    16434    comentarios    TABLE     �   CREATE TABLE public.comentarios (
    id_pedido8 integer,
    comentario character varying(1000),
    informe_temp boolean,
    desc_insitu boolean,
    ok_desc_insitu boolean,
    tipo_informe integer
);
    DROP TABLE public.comentarios;
       public         heap    postgres    false            �            1259    16440    datos_iniciales    TABLE     �  CREATE TABLE public.datos_iniciales (
    id_pedido integer NOT NULL,
    nombre character varying(70) NOT NULL,
    instituto character varying(70) NOT NULL,
    depto character varying(70) NOT NULL,
    servicio character varying(70) NOT NULL,
    equipo character varying(70) NOT NULL,
    n_utn character varying(10) NOT NULL,
    obs character varying(100),
    fecha character varying(15) NOT NULL,
    cierre character varying(5),
    refrigerado boolean
);
 #   DROP TABLE public.datos_iniciales;
       public         heap    postgres    false            �            1259    16443    datos_iniciales_id_pedido_seq    SEQUENCE     �   CREATE SEQUENCE public.datos_iniciales_id_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.datos_iniciales_id_pedido_seq;
       public          postgres    false    216            �           0    0    datos_iniciales_id_pedido_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.datos_iniciales_id_pedido_seq OWNED BY public.datos_iniciales.id_pedido;
          public          postgres    false    217            �            1259    16445    datos_pedido    TABLE     �   CREATE TABLE public.datos_pedido (
    id_pedido1 integer NOT NULL,
    nombre_usuario character varying(70) NOT NULL,
    medio character varying(70) NOT NULL,
    motivo character varying(100) NOT NULL,
    traslado boolean
);
     DROP TABLE public.datos_pedido;
       public         heap    postgres    false            �            1259    16448    datos_pedido_id_pedido1_seq    SEQUENCE     �   CREATE SEQUENCE public.datos_pedido_id_pedido1_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.datos_pedido_id_pedido1_seq;
       public          postgres    false    218            �           0    0    datos_pedido_id_pedido1_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.datos_pedido_id_pedido1_seq OWNED BY public.datos_pedido.id_pedido1;
          public          postgres    false    219            �            1259    16450 	   guardados    TABLE     �   CREATE TABLE public.guardados (
    id_pedido6 integer NOT NULL,
    g_pedido boolean,
    g_traslado boolean,
    g_revision boolean,
    g_cotizacion boolean,
    g_repuesto boolean,
    g_cierre boolean
);
    DROP TABLE public.guardados;
       public         heap    postgres    false            �            1259    16453    personal    TABLE     a   CREATE TABLE public.personal (
    id integer NOT NULL,
    nombre character varying NOT NULL
);
    DROP TABLE public.personal;
       public         heap    postgres    false            �            1259    16459    repuesto    TABLE     ^  CREATE TABLE public.repuesto (
    id_pedido4 integer NOT NULL,
    nombre character varying(50) NOT NULL,
    cantidad integer NOT NULL,
    fecha_pedido character varying(15) NOT NULL,
    fecha_resp_prov character varying(15) NOT NULL,
    nombre_prov character varying(70) NOT NULL,
    fecha_envio_serv character varying(15) NOT NULL,
    fecha_resp_serv character varying(15) NOT NULL,
    fecha_adq character varying(15) NOT NULL,
    obs_repuesto character varying(120),
    cot_si_no boolean,
    envio_si_no boolean,
    adq_si_no boolean,
    id_repuesto integer,
    precio integer NOT NULL
);
    DROP TABLE public.repuesto;
       public         heap    postgres    false            �            1259    16462 	   revisión    TABLE     |   CREATE TABLE public."revisión" (
    id_pedido3 integer NOT NULL,
    diag character varying(150),
    repuesto boolean
);
    DROP TABLE public."revisión";
       public         heap    postgres    false            �            1259    16553    tabla_temporal    TABLE     
  CREATE TABLE public.tabla_temporal (
    id_pedido4 integer,
    nombre character varying(50),
    cantidad integer,
    fecha_pedido character varying(15),
    fecha_resp_prov character varying(15),
    nombre_prov character varying(70),
    fecha_envio_serv character varying(15),
    fecha_resp_serv character varying(15),
    fecha_adq character varying(15),
    obs_repuesto character varying(120),
    cot_si_no boolean,
    envio_si_no boolean,
    adq_si_no boolean,
    id_repuesto integer,
    precio integer
);
 "   DROP TABLE public.tabla_temporal;
       public         heap    postgres    false            �            1259    16465    traslado    TABLE     �   CREATE TABLE public.traslado (
    id_pedido2 integer NOT NULL,
    fecha_traslado character varying(15) NOT NULL,
    hoja_desc boolean NOT NULL,
    hoja_archivada boolean NOT NULL,
    planilla boolean NOT NULL,
    n_planilla integer NOT NULL
);
    DROP TABLE public.traslado;
       public         heap    postgres    false            �
           2604    16546    aa1instituto id_inst    DEFAULT     |   ALTER TABLE ONLY public.aa1instituto ALTER COLUMN id_inst SET DEFAULT nextval('public.aa1instituto_id_inst_seq'::regclass);
 C   ALTER TABLE public.aa1instituto ALTER COLUMN id_inst DROP DEFAULT;
       public          postgres    false    204    203            �
           2604    16547    aa2depto id_depto    DEFAULT     v   ALTER TABLE ONLY public.aa2depto ALTER COLUMN id_depto SET DEFAULT nextval('public.aa2depto_id_depto_seq'::regclass);
 @   ALTER TABLE public.aa2depto ALTER COLUMN id_depto DROP DEFAULT;
       public          postgres    false    206    205            �
           2604    16548    aa3servicio id_serv    DEFAULT     z   ALTER TABLE ONLY public.aa3servicio ALTER COLUMN id_serv SET DEFAULT nextval('public.aa3servicio_id_serv_seq'::regclass);
 B   ALTER TABLE public.aa3servicio ALTER COLUMN id_serv DROP DEFAULT;
       public          postgres    false    208    207            �
           2604    16549    aa4tipoequipo id_tequipo    DEFAULT     �   ALTER TABLE ONLY public.aa4tipoequipo ALTER COLUMN id_tequipo SET DEFAULT nextval('public.aa4tipoequipo_id_tequipo_seq'::regclass);
 G   ALTER TABLE public.aa4tipoequipo ALTER COLUMN id_tequipo DROP DEFAULT;
       public          postgres    false    210    209            �
           2604    16550    aa5equipo id_equipo    DEFAULT     z   ALTER TABLE ONLY public.aa5equipo ALTER COLUMN id_equipo SET DEFAULT nextval('public.aa5equipo_id_equipo_seq'::regclass);
 B   ALTER TABLE public.aa5equipo ALTER COLUMN id_equipo DROP DEFAULT;
       public          postgres    false    212    211            �
           2604    16551    datos_iniciales id_pedido    DEFAULT     �   ALTER TABLE ONLY public.datos_iniciales ALTER COLUMN id_pedido SET DEFAULT nextval('public.datos_iniciales_id_pedido_seq'::regclass);
 H   ALTER TABLE public.datos_iniciales ALTER COLUMN id_pedido DROP DEFAULT;
       public          postgres    false    217    216            �
           2604    16552    datos_pedido id_pedido1    DEFAULT     �   ALTER TABLE ONLY public.datos_pedido ALTER COLUMN id_pedido1 SET DEFAULT nextval('public.datos_pedido_id_pedido1_seq'::regclass);
 F   ALTER TABLE public.datos_pedido ALTER COLUMN id_pedido1 DROP DEFAULT;
       public          postgres    false    219    218            o          0    16403    aa1instituto 
   TABLE DATA           7   COPY public.aa1instituto (id_inst, n_inst) FROM stdin;
    public          postgres    false    203   Ui       q          0    16408    aa2depto 
   TABLE DATA           ?   COPY public.aa2depto (id_depto, id_inst1, n_depto) FROM stdin;
    public          postgres    false    205   �i       s          0    16413    aa3servicio 
   TABLE DATA           E   COPY public.aa3servicio (id_serv, id_depto1, n_servicio) FROM stdin;
    public          postgres    false    207   �j       u          0    16418    aa4tipoequipo 
   TABLE DATA           >   COPY public.aa4tipoequipo (id_tequipo, n_tequipo) FROM stdin;
    public          postgres    false    209   �m       w          0    16423 	   aa5equipo 
   TABLE DATA           E   COPY public.aa5equipo (id_equipo, id_tequipo1, n_equipo) FROM stdin;
    public          postgres    false    211   0o       y          0    16428    aa6personal 
   TABLE DATA           1   COPY public.aa6personal (n_personal) FROM stdin;
    public          postgres    false    213   Hy       z          0    16431    cierre 
   TABLE DATA           k   COPY public.cierre (id_pedido5, equip_rep, reg_sal, fecha_cierre, informe_h, informe_f, obs_f) FROM stdin;
    public          postgres    false    214   %z       {          0    16434    comentarios 
   TABLE DATA           v   COPY public.comentarios (id_pedido8, comentario, informe_temp, desc_insitu, ok_desc_insitu, tipo_informe) FROM stdin;
    public          postgres    false    215   �       |          0    16440    datos_iniciales 
   TABLE DATA           �   COPY public.datos_iniciales (id_pedido, nombre, instituto, depto, servicio, equipo, n_utn, obs, fecha, cierre, refrigerado) FROM stdin;
    public          postgres    false    216   �       ~          0    16445    datos_pedido 
   TABLE DATA           [   COPY public.datos_pedido (id_pedido1, nombre_usuario, medio, motivo, traslado) FROM stdin;
    public          postgres    false    218   D�       �          0    16450 	   guardados 
   TABLE DATA           u   COPY public.guardados (id_pedido6, g_pedido, g_traslado, g_revision, g_cotizacion, g_repuesto, g_cierre) FROM stdin;
    public          postgres    false    220   �       �          0    16453    personal 
   TABLE DATA           .   COPY public.personal (id, nombre) FROM stdin;
    public          postgres    false    221   �       �          0    16459    repuesto 
   TABLE DATA           �   COPY public.repuesto (id_pedido4, nombre, cantidad, fecha_pedido, fecha_resp_prov, nombre_prov, fecha_envio_serv, fecha_resp_serv, fecha_adq, obs_repuesto, cot_si_no, envio_si_no, adq_si_no, id_repuesto, precio) FROM stdin;
    public          postgres    false    222   +�       �          0    16462 	   revisión 
   TABLE DATA           A   COPY public."revisión" (id_pedido3, diag, repuesto) FROM stdin;
    public          postgres    false    223   :�       �          0    16553    tabla_temporal 
   TABLE DATA           �   COPY public.tabla_temporal (id_pedido4, nombre, cantidad, fecha_pedido, fecha_resp_prov, nombre_prov, fecha_envio_serv, fecha_resp_serv, fecha_adq, obs_repuesto, cot_si_no, envio_si_no, adq_si_no, id_repuesto, precio) FROM stdin;
    public          postgres    false    225   H�       �          0    16465    traslado 
   TABLE DATA           o   COPY public.traslado (id_pedido2, fecha_traslado, hoja_desc, hoja_archivada, planilla, n_planilla) FROM stdin;
    public          postgres    false    224   r�       �           0    0    aa1instituto_id_inst_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.aa1instituto_id_inst_seq', 9, true);
          public          postgres    false    204            �           0    0    aa2depto_id_depto_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.aa2depto_id_depto_seq', 20, true);
          public          postgres    false    206            �           0    0    aa3servicio_id_serv_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.aa3servicio_id_serv_seq', 69, true);
          public          postgres    false    208            �           0    0    aa4tipoequipo_id_tequipo_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.aa4tipoequipo_id_tequipo_seq', 30, true);
          public          postgres    false    210            �           0    0    aa5equipo_id_equipo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.aa5equipo_id_equipo_seq', 230, true);
          public          postgres    false    212            �           0    0    datos_iniciales_id_pedido_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.datos_iniciales_id_pedido_seq', 80, true);
          public          postgres    false    217            �           0    0    datos_pedido_id_pedido1_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.datos_pedido_id_pedido1_seq', 1, false);
          public          postgres    false    219            �
           2606    16476    aa1instituto aa1instituto_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.aa1instituto
    ADD CONSTRAINT aa1instituto_pkey PRIMARY KEY (id_inst);
 H   ALTER TABLE ONLY public.aa1instituto DROP CONSTRAINT aa1instituto_pkey;
       public            postgres    false    203            �
           2606    16478    aa2depto aa2depto_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.aa2depto
    ADD CONSTRAINT aa2depto_pkey PRIMARY KEY (id_depto);
 @   ALTER TABLE ONLY public.aa2depto DROP CONSTRAINT aa2depto_pkey;
       public            postgres    false    205            �
           2606    16480    aa3servicio aa3servicio_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.aa3servicio
    ADD CONSTRAINT aa3servicio_pkey PRIMARY KEY (id_serv);
 F   ALTER TABLE ONLY public.aa3servicio DROP CONSTRAINT aa3servicio_pkey;
       public            postgres    false    207            �
           2606    16482     aa4tipoequipo aa4tipoequipo_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.aa4tipoequipo
    ADD CONSTRAINT aa4tipoequipo_pkey PRIMARY KEY (id_tequipo);
 J   ALTER TABLE ONLY public.aa4tipoequipo DROP CONSTRAINT aa4tipoequipo_pkey;
       public            postgres    false    209            �
           2606    16484    aa5equipo aa5equipo_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.aa5equipo
    ADD CONSTRAINT aa5equipo_pkey PRIMARY KEY (id_equipo);
 B   ALTER TABLE ONLY public.aa5equipo DROP CONSTRAINT aa5equipo_pkey;
       public            postgres    false    211            �
           2606    16486    cierre cierre_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cierre
    ADD CONSTRAINT cierre_pkey PRIMARY KEY (id_pedido5);
 <   ALTER TABLE ONLY public.cierre DROP CONSTRAINT cierre_pkey;
       public            postgres    false    214            �
           2606    16488 $   datos_iniciales datos_iniciales_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.datos_iniciales
    ADD CONSTRAINT datos_iniciales_pkey PRIMARY KEY (id_pedido);
 N   ALTER TABLE ONLY public.datos_iniciales DROP CONSTRAINT datos_iniciales_pkey;
       public            postgres    false    216            �
           2606    16490    datos_pedido datos_pedido_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.datos_pedido
    ADD CONSTRAINT datos_pedido_pkey PRIMARY KEY (id_pedido1);
 H   ALTER TABLE ONLY public.datos_pedido DROP CONSTRAINT datos_pedido_pkey;
       public            postgres    false    218            �
           2606    16492    guardados guardados_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.guardados
    ADD CONSTRAINT guardados_pkey PRIMARY KEY (id_pedido6);
 B   ALTER TABLE ONLY public.guardados DROP CONSTRAINT guardados_pkey;
       public            postgres    false    220            �
           2606    16494    personal personal_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.personal
    ADD CONSTRAINT personal_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.personal DROP CONSTRAINT personal_pkey;
       public            postgres    false    221            �
           2606    16496    revisión revisión_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."revisión"
    ADD CONSTRAINT "revisión_pkey" PRIMARY KEY (id_pedido3);
 F   ALTER TABLE ONLY public."revisión" DROP CONSTRAINT "revisión_pkey";
       public            postgres    false    223            �
           2606    16498    traslado traslado_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.traslado
    ADD CONSTRAINT traslado_pkey PRIMARY KEY (id_pedido2);
 @   ALTER TABLE ONLY public.traslado DROP CONSTRAINT traslado_pkey;
       public            postgres    false    224            �
           2606    16499    aa2depto aa2depto_id_inst1_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.aa2depto
    ADD CONSTRAINT aa2depto_id_inst1_fkey FOREIGN KEY (id_inst1) REFERENCES public.aa1instituto(id_inst);
 I   ALTER TABLE ONLY public.aa2depto DROP CONSTRAINT aa2depto_id_inst1_fkey;
       public          postgres    false    203    2770    205            �
           2606    16504 &   aa3servicio aa3servicio_id_depto1_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.aa3servicio
    ADD CONSTRAINT aa3servicio_id_depto1_fkey FOREIGN KEY (id_depto1) REFERENCES public.aa2depto(id_depto);
 P   ALTER TABLE ONLY public.aa3servicio DROP CONSTRAINT aa3servicio_id_depto1_fkey;
       public          postgres    false    207    2772    205            �
           2606    16509 $   aa5equipo aa5equipo_id_tequipo1_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.aa5equipo
    ADD CONSTRAINT aa5equipo_id_tequipo1_fkey FOREIGN KEY (id_tequipo1) REFERENCES public.aa4tipoequipo(id_tequipo);
 N   ALTER TABLE ONLY public.aa5equipo DROP CONSTRAINT aa5equipo_id_tequipo1_fkey;
       public          postgres    false    211    2776    209            �
           2606    16514    cierre cierre_id_pedido5_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cierre
    ADD CONSTRAINT cierre_id_pedido5_fkey FOREIGN KEY (id_pedido5) REFERENCES public.datos_iniciales(id_pedido);
 G   ALTER TABLE ONLY public.cierre DROP CONSTRAINT cierre_id_pedido5_fkey;
       public          postgres    false    216    214    2782            �
           2606    16519 )   datos_pedido datos_pedido_id_pedido1_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.datos_pedido
    ADD CONSTRAINT datos_pedido_id_pedido1_fkey FOREIGN KEY (id_pedido1) REFERENCES public.datos_iniciales(id_pedido);
 S   ALTER TABLE ONLY public.datos_pedido DROP CONSTRAINT datos_pedido_id_pedido1_fkey;
       public          postgres    false    2782    216    218            �
           2606    16524 #   guardados guardados_id_pedido6_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.guardados
    ADD CONSTRAINT guardados_id_pedido6_fkey FOREIGN KEY (id_pedido6) REFERENCES public.datos_iniciales(id_pedido);
 M   ALTER TABLE ONLY public.guardados DROP CONSTRAINT guardados_id_pedido6_fkey;
       public          postgres    false    220    2782    216            �
           2606    16529 #   revisión revisión_id_pedido3_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."revisión"
    ADD CONSTRAINT "revisión_id_pedido3_fkey" FOREIGN KEY (id_pedido3) REFERENCES public.datos_iniciales(id_pedido);
 Q   ALTER TABLE ONLY public."revisión" DROP CONSTRAINT "revisión_id_pedido3_fkey";
       public          postgres    false    2782    216    223            �
           2606    16534 !   traslado traslado_id_pedido2_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.traslado
    ADD CONSTRAINT traslado_id_pedido2_fkey FOREIGN KEY (id_pedido2) REFERENCES public.datos_iniciales(id_pedido);
 K   ALTER TABLE ONLY public.traslado DROP CONSTRAINT traslado_id_pedido2_fkey;
       public          postgres    false    216    224    2782            o   H   x�3���s��2RN\Ɯ����N\&��~ �H��r��d��9}}��7�qYp:��xsY�ts��qqq A��      q   �   x�u���P���)xc�c"H4:�\��� `���qprs����&$J�Ԟ�MOsB�uy#�.�]��(�Y/-��hD+�ٌ���&̓4�	X�YFQ�.�%�����E�m�W�Y^�ۧ�^�}��H��t�i�/E&�J�H㟥�|�w�y���7���0��!Y�B,�&H6x$���y̾R �� s��+���l9n�      s   �  x�mTMo1={~�� Q�3ɱMJQ�H鉋�h����w���C%$n���n
�p���x<��ƙ�ԉ�y�6��l|H�[����Pck<�&���Y�e�R%����M��KN:Ӈ��8� t��I��i�kC�D+�s��l.�ڰ;<�2���媐�P�z����R6����(�`�sC�ǤY-ep/��:���^z�`h{�	��`��6�n+́������1I���R�ݡH�s ׸�Xn^�Z}|�^�R^��Z���Z���V+Y@�\VАɊ�suC3Xˇ�^/~���b.�k�R�;3y�ѓ���t>b�`6;��6��S*��"���򖣧���=�xN�={%GW��\� )�u�8��D%*��ܺ��1�v���yG�J<oLlP��)I�fI��y|�2S�zk>�#�r��R��� 5�Ӣ�4@�8X��wA_�d��v:[��gTV��0�M����.�(�:�����=�M���3�;�~�Ov1`83ʼ\>��x�d}�����1�
�g��$����x�wz㒉h�T	%ƕ*a`�b'�+��x�S%lG6����;��Hj%<\�[�FX�.p#W'���G�`O�в6�.��B_��T	g���i�򟢛'E�A�܏�������B��������?�dux��'�ja�� ~�=����'-��}���s)���hkZQ%����XC�2�5$-�Z/U.�ZC�%�O3"��G��      u   �  x�]Q;�1��S�YK����,�Flv�4̌f�@#M��·�2E*a.���HG���H�u&QノG}���F��<�#7sؓ%w���B?��IG�h�LC��o�sgj�%TduKum�c��+��Ka���A�p����(��.n�`u��o�q{_�?-�z���l:��-%�Q0�R���A�x� Fos?�I�8�G�ya�7���4LЂj�s#�r	�_�j�rG]��١\p�/3G(7���E$�[8N�����H���Ys�0Z/�������[��������T
ަw;fK"�����X4�
=5�'��1�\��*�v���ɢZ³����U�Z����)�ZC�")q��Z6Qm8�~ օj�4�d�g��
�&�O��Õ�R      w   
  x�}X�r۸]�_���O�\*���^kF�ȩY�� 	Ih@ҕ��d�E��������IQ�3UZݍFw��'�d�S��h�bW�5*�N�:��E!�"ϕ��r±�6k�+�hL�Hw-
Q�R;1�u�w�R_��LǢ�i�g'�A7�cnx�dX���R8���c�\W';�?*��!zS7��^�'��U�
�)�'Z#��?bJQHbǯ8��!�I0�����,]��D���Q�oyc(�~z!�,p��&!|�'_���J4�(�ވjq��� ��7jǶ�������?�bj4�&��ZU���Z�Z�6b㮕.�?v,Ob�=�O��4����W�<���ZՍ,id��^�`�]��DÙ� ���-�Jl2�@�t����9��g��k���;�-p� ��+D��B��kSiҴWkrԞF`}`ݜb;Y��	�3���ȓ��/���t��7SRDBro~�Z
#(m�����)W&o�^)��^�`I
.s/�΢�,��w�D~�k-�kD��ۢQ�l��q'
��,E�k'"���ϰ�6���dwC� �[��p���q�5,#ߣ��L��/�L��q(�{ֽ�hD��ld�`�Ů������!3h�+��l�3����c�����o?��LV�9���Q���d:��a�߷�N��7�/��C��S�*7:	�5��"����MU�^���k��td:����G��sU�w�I��X�6�`�L�[e�4�^�lՐ%�
�L#+Xr�}_�A���V6���I�\Xۚ.$"���H4g��w�Re�����4q�~V���V{���>~t���ɲ��=*�2A#ϊ:	i:h���mH�V��$)�ҽ��$�Zw^�jK%�$�$�Ѿ�)���d
ҭ]�W������I��1�$)C�GV({As߸��RR}s�₅���#�N. aC(N�;�'� �l�չ��N�
m�-]YαqҘD�<� �u�+��N�%K��1��4!�p1;�O>L�4卽K ;s��eV��Z��3A°��2�X�B6�T�V��'��)_X��H ���N\�jS�kc��[�FV�Na츬���81q>��֊�Mi}��z����N��~�����o�LlN�Q�1%��n���x�2ȳz�m�]�j�7R��BTKTaR��8Tئ�~�U�a�=��=0bÃ�x��]�&i�8H��ܴ�5�*��|� o�]bfMeoo� 17e�VN�>���F��ap�����ʍһ�7��g���,�z�d�~��!aGX2a4��s��!����� ��]�J焦���mk��-P��-%�~OJ�kU��6�� �T�M9�&��vAɋ$���ܽFQV�G��5�:�S ��áP�բ5,uԥ���J�{�;"��v����J@Jz}�����x�^?�W���u�W�7| 5Ds)��]�nw{�|��R�4��_��ۇD�$B�x�w��Ot�C\�qt��4B�T��㗜���s�>.f������
�ח�+?4����P���O% ��0�ӳe�1����E��B��x�j�!���O�����s%�,t���T�#�J���~��`(�p9�_�\4b�O����%�0�����0��i���Y'�\��U��mi�!����Gk(���sc�wUl�0�B����]����Cѿ��zm߈��b����[dil��)�MIr�]���(�׉�f����c��� ����vq���ڵ8��� ��ܗ}K��75����6����� �j������B".�.oh�蟞1���+�nG �3�۠RT�c��Z z~s0��Xo������c)�k[���]a����~.�B���%ᠼ�'%��+�	�#D�O ���~ݷ�$육�B_L����Ҥ�&K_��y�DMW�L�<�+#�=|Co�������Y�%�R&6�}�#�E��� n7h�7Ct���9��'���p�O(Z�
ƃ��'�jF�oi<��#��U6�6�m��A�Ē��,�r�O�l)}�FBj�dq�j������>��f�D���B@�'��x7rS#�6����Ɣ4Ľ�+핝�=�~���1�vD��Y�vo�!�.t�	�x�y;��w�V 80PӺQ�>� '/�UU�������^H���Z�Q�˶�_s��[�9 I�;ߏv���{q�M�j�Z0�FlPC���2P��h1L��q ����>M��p^����Z�NnG�`=<.?�oJya��t�KS�K�	���%�����NƟC`$pV8i��{%X<4l��d9A���iڡg�8@���Gx�6�m'%.��X�A2܎&"������uN�҄��u��"/+�hJHJ�{��j�0��-�Q�9$�Ho=`�� 6��.�������%An3�(�q�r� R�$z
Rq�98j�Nh���yكr�����1�q�( ���]��� 	��@8��8����:�      y   �   x���iA��b*H�� �$��C���X`�kv��=�
7m�C��m4��ޓV�vKkN]�m���R��g�ó�tUh�\X�f��}� *(��`�5�v��SL6I>�C��N/-����7�j�a^�5������x���M>#��0⯹d��dE�[%^��E��-B�n�i���}��f�K[vxnKr����>����6�      z   �  x��W���6��O�0��,�޴��������h��ԑ��ݷIy�A����R�%[	� �]X;&���oF����㻇�1yLWiB��}g����O�rJ:k�(Ke��K��7���t��V�~�R�Қ�Y-*�x%y+�F:�vN,��Iqc��NV8��)���XW]K�J�=���9j���^q�3�kU7J�
2R�ӯҝ����O��C넇8��O���񴭃�F8�߷O�
~���Tou�b��\�W� &����<����a�q�8bp��x���(�PfD��(q��"ĸ��63�� K�c!� ���Y$O(��"�pY�;���
�K�(�8�rh�x��e�Z8�ZMT(ϒM0�dO���4����#.���B���<ڐ9�uK���U����6���w�lX���TmY�E_�I�"�P�Nj$��p
�?[gP2O�j\'w��p�T*3J�Gi��i����������+$��k��S�5�Zb�z���މ� ���k��ȥ!����H�f��!�=R'8t���%�i��k*��@!�?~xǢ�����]h]�����!�1{|�N(ϱw�ȵڶ�K�r
�t�������#�Qh�a���ŕ�X�R�ƫ����{�"�����e)���yD�� 8�@���QU�,�;�4��۷�m�S��\��u��c:�#UN���!n@�n(��DG�^���NhjT9�N>�K�Ѽ��AE���*�%���(A�OH3%��O�Jb�c�uvPH�>
+�sp�:���@W���yK�0�
�4\܉h�:�4 ��V���3�Ȧ�~��^�=�`�HN���d�<&$�Qڵ(Ŋ!���HPZ�d�Љ�4��&>�UAS���ն#�8��X�l��63ҷ6�R5�T�����W��S��Uax��Bc�/'�������s�"
���FTמ>�F�qx��8��=��J0�2���G�"Ezo�;����.g��#2{9>G��P3�C��g� �����G̵���e����H��yg�,�v��7@�<��3G#��_�X��`y>#,b_>�V؎<�?0�j#�,z����
���� 2��:��3��Q::�R�^����b�j%b��ƚ*gD90���ɢRB[5�TP?��t5�h�Ń�����ߨ�;�y���R9��t@R#INxt�Q!��C��?S��F�҇���aW\�Ա���5�݂B/��(���ҥ�/���$�޽Zڻ���K��yl��	��/(H�\�7`@\)F�dÛD����N�s2�0�J�͸I��_�b裿��^h|�\&�T
�
��c3����g<�����bX o���L���d�|�^L�_4�fP=Q2};���Z]{}9:ٌ�|_�p X����QK�q���z�0      {   (  x��V���6=k�b>`cؒe{s��@7M�MN{�$�˔"�2���s�[�������%EE��k�"g8o޼�Ev���.,��ta5]XO���y��HVϋ��b3]��yb9ϓ�$�<���w��&������3����]�^:K^R#}m��%IM{gR6֍�&��I�yf
i1��j�^$�Ɏ��=�Y�ܬHn�ȳCAh-݌^�Z� �ۮ�W�I����O��.�]'���������SUwP���k��;���R8~u��-�
W��t�R�K�>>5jg_���j1�r16��Y�<�Ӆ���R�ϕ���t'����#A������[/��_ҩ����B�\��OT8~��S�����W'�7�,E��4Cq:�
�pYЭ}hq�\z ����Z������<~F���{�mP�a�6����BW�w�;�]K��ĪpN�z��#2�VJ����6�	6���/��S<q�4	d�͵�����`kQ)#�f�̇���q{��4W+�;�����r8�A	�P�g�����{k��3c��u��ס��5��aL���2�e"6�9�zP^��2��Y�0�̓ԶfG�g��Vc����k�Tac�ɱ1/ �Va`_ )9ݏdl���'рy0�_�]ڟ][&j�(�2Q�e�d�5W<"��P�t���^P
�C��W"H�g�k'H�'1��p���U��ft����I��5�N"�����$���O���^����=>E�2����Bx����y�N|&X�	��$Se:J$�*�L��2���J�n��g^o��!��F�	�_�h#j�u�*	f��~jb���aw0L�|Fo����a���C$�m��F�������o� �����Bc+-{y|VνS0-f�jPV��OW?'$Q��-ז��RiC����q�^��0�b3���[�GыU9	<A����:rO�v�����/�l���t�e��%��Y�$ 7���:Հ���h:rN����Ķp��F9
��y�o�{Tq~�;�8�����O�G�"����gU�晷\�P�آN� ����$�zF�J�+
�ӓBZ-3�o ���r+j4U���3�gIH#P���J|�#*�Ή�V��g�q�0LT�>���+�p��*��obQC*ǢѪA@7Z�;� ����8Dl;�����ǰ��D�b��q����D%։&�f+�c���?�f�K�Cqp��@��	>�A����U��x�ɪ:m�[��ǜ
��ǝS���N1M=-�s$B�>~i9h����Q�����...�7:F      |   ,  x��Y�r�8]�_�ewUb� ���d�NT�Ī��bj6�HQ�$])�M�Y��+�^�~l(?J�(wf���<��s�=�Ʌ4��g��{a�<�*ՙ^���-��-�tn��$��'�c>���u�����F=!�R-Uj�T�\�sV�vn�|���8	�� �_�̼�mp��BJ�D�2W�����I������ށ��
�b��y��A��
�2O��d�R�8�[%1�|_}�����v|�/j���FR�i�4����盏���<���o�Ľ��3�)�:�T/W�&<�"B�� �0��vX�:�Ľ���b�� �n��G]F�rzj�Tf�PEWf��iiPL���wi8?J~��5ME�d^
9�~~��Q��3W)� %3��� �g�ޝp���<�Z&YydҼ\e�Aә�3]��B�R�q!
�G��g��+�*���B� ����+N���*O���]��
�?�����r��;��4�
���y�y�/p���L�1��-/@@�9_�RR��A���K���{�̺"נ�{���J�O��w'��"h�:��)J��JQVS�|�����D��Į�qPmM���L��n����������7AnI�V5�J[㼫<ś�:�~#�R�*��FօMҪ�Hs�m/�+̷�-m����)T_z��ʰ�}˚����x��J�M���\�N�g⻝�u�ߟ�?�,c\��/�6�OL�Y�{V��h���<d�A������9t>C�����x�=��s�����&̚Ms�Ud�G����T��q5�[���nR���AW�'�V�*_�p)�k([��Ԗ[Dv�ONE�� ��T�:��ןu��4:��q�H1w0��cȭ	��������0��a���{Ξ�kJ�����]<r��R|a`	yj���|/�#��9gV�=n�!`ޏo���zʦPp|UItq�"���l�oK<{a���gf�[�K���NN�C�|�ڎ��I�p t@�Ų��7���aG,�ꋦ�Xb,z����ΌD�j���vj�Ϸs�I��R�?�2S���f�8)����-��mN\h�PW��Ԧ�:&�u�S&���������`���F�,l�Vwr�Э��خu-� �ܵ��]��p@�	�{ G:�9Y"�{m������"#��7�Q��D��}ʆ{q}}���`�{��n)K�1����p~��mɝ�*�1���p�b�����ͮur.Һfg��pz\W#P�&\fR�5V6��A`w�^��XD�-t��й%[胃�/*Ь�������q6�v�����4��dQ����<i�l��G���=j��a��_��������G���t��/�3l��'{�1�\�qD�+Ld�͹@��چ��Ju�p)�B��VF`�:ﬧ��� ;��C�����G�����Y4y�'����
���Tlb�#l�'�T�АBȫF�1�V�*JM�!� ��x�X�Wڮ2OD[�F�;x��,�	f�ߌ�8� �]�o��M�&���S�kjY�Ͽ�ji/�R���7�?�W�M�朳����ا�K���_������ӌ�f��,�T��0��76��M|R�88t�����>��'����(������Iu(��]��~�H��� !x�H�����{���k��~]7#ﱉ!�x8��X,��O�1R�߮n(����6�~Ƣ���cr��\e���,�\L\���所IM@�o��J^ޣ���}c.MvO�}4�y}���L�̦k&_�ע��(�=!�[����?'�/��M~Jx��������� ����      ~   �  x��W�r�8<S_���z�GG�]N�^U����!	�H @HU���1��r�q�c��2e�[���eJ��<�{���-m�t�4�TF��tt!?�~�L
ir��	�����U|DjM�܊2+�։�����H����`�����\§�#���r���EEo-�d���W�Z]�G�W����4�Vx�*#>H˒���DA�s)�!�.�\ZqI)©�~�_�F\�e�V��b!���ɍ�H?Zad*�*Hx�^����zgNI��^�����w�]���(�����!�7�΃W�|��	婬�H�b$
^a�.�+G�/ٗ7�U�Bq	I��ϟFoK2�L�`�˭4��=+���.�M��`g�x<�]�&�6F3Me�6ݐ�}v� ?�Gi��Rnͥ�֐^ᰌ`�$a5�D^U���%U	���:�r\�	�qt<���+�'ќ
�R�.)}��^���Za�Hl�~z�FF���i�ѡdv�hE��M�/�d�
�T�r��eD�KT�=B�K�\�N��Yw���";i$#��5���g�q�[W OːR|͜�I�a ��*����H�M�9�"�B�P�At\�3N�O� �G��3�R��ڝ"Bvv��U�!0e����HM�
�_�vߐ�AR�!FM\vX���X%V�}�$es/�g�B�s�ݚ�p����8zg=�Fo����lz�'27D�v�R�2���hÐ��/�xS����� :4��g����"8����EW��Z��&�}�k ��\y| >��!W��L�� ����R�m�h�vOLEQHt�;c=y#w`�ь'%��+�t��}�����������莘%�Kij�މ�����h8�E�1r'�CBe"�����y.JCLaܩe�d�J��h|�����$�cD�c�f��b�4}��� ��G"��/
@vK�ݨ����B�2�>�H�����
�/� B�t,K�>�m
2f�pA}�#!�v�0�>3��Eל�GAЏ���a%��������E���YU���p��t&-B��O��c�5�I�eT��*6*�ڰ�i׀�6���x-ZD>tځ�G�������<�ƅ<�[t~[���{؎O�m���ui��BJs&�A4���<���X�ӕ5[�M1QB6��x���/|ϜJ�� X�8�0רI�ʦ+ָ�M_<��G����o���Y,^Z~���L����{�O��,�Kl-�''ѭ�n�[v��}�����j�VrK����]�q��a�B=�p3��J̲�M�Y\�m�f�����Bf���P/:�f�s��hQ�I7��t�M��j��Q��P��A�pmCs���l�X��|��O�a�@�C���?ڇi[Q^򼮷���q�@���梹�
S@�s���eS`2mh�W^�<�/�u�B(n`{�a�$� ���"r��� A�l7���s�v3H9�[�W��1W��N���掰�8Y4/N�Nɂ�Ls{86zq��2�a��x8�Qa�e��#�^�8T�҇=h7�^ސ�����We�[���0w�T���bp�L�$��h~u#Y󠷤3���5/��Q� T���v�B�>z�D�{[q!������R���1D��Jڂp�B�|5��ޟ�^���&fF      �      x������ � �      �      x������ � �      �   �  x��U�n�0=S_1��6I��MV��Iu�"F�S��Pr��z�'��:�B;������{o���ɹj�����b�4a���S�"Y�Z�D��S]�K-����&|�b]&?�o�	���i^dq���7�GNܳ)��8�c��7%�x��,�L�%��rU��Ӣ;)���*ZHd#����n/]�`�挻�}����{��c�c��@� ���|� J��UE](@�U(�D�PhC���e;$RՃ�͜�v�m*�jj,cۄ9�c��X��9�-Ǧ��0�}�j�`!��xi������2=�8aY�{d}x7[ߧ��s�� �^�i���bPT�G���sk���e���q�Bt��9ˀ3p�RcX��'a.L���J�:���&�p���ɻFn�Z#�kH�b��3f68����p>ژۅ�>����9���4�����&�����E�� ��}�>!�6S��ͬ;�6]o��n٫R�������4>��z��8��t �ja.2�l���/TR�.>u������Ouw���w���.^a��di���������瓑h�~�+���1	���"�HEݪ�ߙ�Z��n�.u��{�)/��/䢔S���2Q����Ը�34��F��F�E^��/Й3{�ɦU�`M
$*!WZK�~�|�
���AL~��V�o��S�E����x�XY�D�>���+� ���E�\نz\�l�w��WZ݉�>�y�o2|V���K3��a	��|��۾eY� ��8      �   �  x��V�r�6�����*i_�B?�;ו���	Į� �,��B�.s�s�`w)�\�h�$����������hUk���m�cI6�q�����\�0}ku�;�j>[e��t��TKLjӣn�Տ�OOY�:i��貛�������[��'÷8l�|Ъ��S�Z�BT�٧�;���_�G���Zr���VY{�ßQ���Zq�_�&B,�w��l�%T�z���t2ZAٔb���M��d��rH�q���]���N��6W��8�I�<n�����@Up�t�M9�m>�M.��V�}k�@��q1�l�n>E�S�-���{ �O7�&;d���L�5'��}4@	����v�v���?��g��
��W�\�N1(@O`�mwo�-�?�'?^�&+)���CL�{�h�{4��ˮ��'W��5,�/;�m�\P�������x/x�"���<����d5�A��BpI��>i��L���~h[�����K�k��67V�/��\�9�]r��ĭ�zaԅ�xM���-yw8+��8���0="�6�Z�p"��*��3��C5"���MOۄݲ�v��*zH�X�����4����%�`U9[^6���Sp|�j�.�V�m�vK�s��Jɻ�����+;r=ڿRFB\f�z!���u*#sg����Űs{%IOO�-)��K���9�v����sx��?��D�O>��GH��G-����V��{��
w=��X>jr��h��D���q�q���7�ax�Ϛ�Α]9�J��S�+�V�9��=�@��1�L�HSß�`�ޖH��6d��ޒY��!&�cL�`���o7:Ȱ��S�:oG�I�Q����B�ƏpUH�nz��X�.Bp��8cvwt��&c��x�"{e��A��ne�|KsS�0p,��A c�<��#�ʛPO!k�'9 EUETՋv؍lӈ>�1��S ~Uz�<�
�iB���> �jQ��v~�`HU��I'��a�6ӹVKֹ�Դ�C+z�2v�)S���H?G�>i�6f����~A<�I��U#?��s)t jgl5WIq,��Hv�C_ܶ�@����������Pو��~z6��V�Ɉ9��j���d� ?�Cmghے�����[;��;9�G��.��:�a�?��@!d�J�U���ā���̪Vh���-K�^���u�y�C�D�ЁHG�y}+��+�7�u �Y=�K�4	����*�z���Le6+xC���	~��mLuFx^�N2���6N,D�۹=A,~��)N3DF6����YS�ם�`��6���ʢ"&~.p����&&�胱T�[b�x�ٛ�V3F��Yw�`<���2�^P�F�I&�PrQs�������V�^/i��p���fR�%��\�u���yKcƽH���p�QxO��.p5S���Z�x/���>K�Z=�P��x{������7}$nv�~���7FŹ�-����q��n�����n��?\�К7u�>ƻa��8;;��E      �     x��T�r�0}��b�;�^I6��q��2��N_#���02ɴ��|B�r A:~9�ϞsveFɨY5
��-f�"HЦ6u��d�R�$(9W�B*�^kB�'��8�~'��Ix��W�a��<�1���(u&d��9���.Z�L�����d��D-�JJ���XA"[)T�}����F�����h��+��:��Z6���(!��� �窨��۪m�D�,��.�'QS-�lߤ�Ja��!�p3��zx���h���iq��-�$ꩩe����Ҡ�/����s�p²���m4����ca@�.���&�ٸ�bPT��ڣ�J�t�d1�0S���I��wPt��{��c������,t��񫄁�sY7�S��[���|X�����^�����h>�F�;v�jǐ��[
h�Ho�, ��d�z����&�>w��م)��~�#y���K8Ol�Y�M�%�,���������0���@�Ӯ���!2���׮/k�����{}���[�]�ns�]Y���z�      �   �   x�m�Kn1�5u��� �uN��+�Z��[^�	c�����h?����}�ht#+O0x�2�0�F���|��ްȄ������v�i�C�k;��2�-��}�sxo.�f�
���Ҍ��D�I���x`!e~��:��ȝ�j"�����aYu ���Mώs?i+��Ч͚`���[�`a�s@��_ � W$�(     