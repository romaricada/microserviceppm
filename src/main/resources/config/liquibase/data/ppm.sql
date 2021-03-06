PGDMP         %                y            sgimp    9.6.15    9.6.15 f    .           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            /           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            0           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            	            2615    92858    microserviceppm    SCHEMA        CREATE SCHEMA microserviceppm;
    DROP SCHEMA microserviceppm;
             sgimp    false            ?            1259    92999    acteur    TABLE     ?  CREATE TABLE microserviceppm.acteur (
    id bigint NOT NULL,
    libelle character varying(255) NOT NULL,
    deleted boolean,
    userid bigint,
    mail character varying(255),
    contact character varying(255),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 #   DROP TABLE microserviceppm.acteur;
       microserviceppm         sgimp    false    9            ?            1259    92983    activite    TABLE     :  CREATE TABLE microserviceppm.activite (
    id bigint NOT NULL,
    code_ligne_plan character varying(255) NOT NULL,
    nom_activite character varying(255) NOT NULL,
    gestionnaire_credit character varying(255),
    etat_marche character varying(255),
    deleted boolean,
    reported boolean,
    passation_id bigint,
    nature_prestation_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 %   DROP TABLE microserviceppm.activite;
       microserviceppm         sgimp    false    9            ?            1259    92905    besoin    TABLE     ?  CREATE TABLE microserviceppm.besoin (
    id bigint NOT NULL,
    libelle character varying(255) NOT NULL,
    quantite integer,
    date_debut date,
    date_fin date,
    deleted boolean,
    nombre_lot integer,
    used boolean,
    montant_estime double precision,
    exercice_id bigint,
    unite_administrative_id bigint,
    nature_prestation_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    montant_inscrit double precision,
    montant_restant double precision
);
 #   DROP TABLE microserviceppm.besoin;
       microserviceppm         sgimp    false    9            ?            1259    92915    besoin_ligne_budgetaire    TABLE     ?  CREATE TABLE microserviceppm.besoin_ligne_budgetaire (
    id bigint NOT NULL,
    aecp boolean NOT NULL,
    montant_estime double precision,
    deleted boolean,
    ligne_budget_id bigint,
    besoin_id bigint,
    activite_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 4   DROP TABLE microserviceppm.besoin_ligne_budgetaire;
       microserviceppm         sgimp    false    9            ?            1259    93133    calcul_delai    TABLE     ?  CREATE TABLE microserviceppm.calcul_delai (
    id bigint NOT NULL,
    libelle character varying(255) NOT NULL,
    deleted boolean,
    etape_id bigint,
    mode_passation_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 )   DROP TABLE microserviceppm.calcul_delai;
       microserviceppm         sgimp    false    9            ?            1259    92864    databasechangelog    TABLE     b  CREATE TABLE microserviceppm.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);
 .   DROP TABLE microserviceppm.databasechangelog;
       microserviceppm         sgimp    false    9            ?            1259    92859    databasechangeloglock    TABLE     ?   CREATE TABLE microserviceppm.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 2   DROP TABLE microserviceppm.databasechangeloglock;
       microserviceppm         sgimp    false    9            ?            1259    93041    etape    TABLE     ?  CREATE TABLE microserviceppm.etape (
    id bigint NOT NULL,
    libelle character varying(255) NOT NULL,
    ordre integer NOT NULL,
    deleted boolean,
    mode_passation_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 "   DROP TABLE microserviceppm.etape;
       microserviceppm         sgimp    false    9            ?            1259    93061    etape_activite_ppm    TABLE     ?  CREATE TABLE microserviceppm.etape_activite_ppm (
    id bigint NOT NULL,
    date_etape date,
    date_reelle date,
    deleted boolean,
    visited boolean,
    etape_id bigint,
    ppm_activite_id bigint,
    debut timestamp without time zone,
    fin timestamp without time zone,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 /   DROP TABLE microserviceppm.etape_activite_ppm;
       microserviceppm         sgimp    false    9            ?            1259    92925    exercice_budgetaire    TABLE     ?  CREATE TABLE microserviceppm.exercice_budgetaire (
    id bigint NOT NULL,
    annee integer NOT NULL,
    deleted boolean,
    active boolean NOT NULL,
    elaborer boolean NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 0   DROP TABLE microserviceppm.exercice_budgetaire;
       microserviceppm         sgimp    false    9            ?            1259    92872    jhi_persistent_audit_event    TABLE     ?   CREATE TABLE microserviceppm.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);
 7   DROP TABLE microserviceppm.jhi_persistent_audit_event;
       microserviceppm         sgimp    false    9            ?            1259    92877    jhi_persistent_audit_evt_data    TABLE     ?   CREATE TABLE microserviceppm.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);
 :   DROP TABLE microserviceppm.jhi_persistent_audit_evt_data;
       microserviceppm         sgimp    false    9            ?            1259    93113    jour_ferier    TABLE     ?  CREATE TABLE microserviceppm.jour_ferier (
    id bigint NOT NULL,
    libelle character varying(255) NOT NULL,
    jour date,
    deleted boolean,
    exercice_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 (   DROP TABLE microserviceppm.jour_ferier;
       microserviceppm         sgimp    false    9            ?            1259    92889    ligne_budgetaire    TABLE     8  CREATE TABLE microserviceppm.ligne_budgetaire (
    id bigint NOT NULL,
    budget character varying(255) NOT NULL,
    ligne_credit character varying(255),
    section character varying(255) NOT NULL,
    programme character varying(255) NOT NULL,
    action character varying(255) NOT NULL,
    chapitre character varying(255) NOT NULL,
    activite character varying(255) NOT NULL,
    article character varying(255) NOT NULL,
    paragraphe character varying(255) NOT NULL,
    dot_init_ae double precision,
    dot_init_cp double precision,
    dot_cor_ae double precision,
    dot_cor_cp double precision,
    engage double precision,
    engage_cf double precision,
    liquide double precision,
    ordonne double precision,
    vbp double precision,
    ecp double precision,
    deleted boolean,
    exercice_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    unite_administrative_id bigint
);
 -   DROP TABLE microserviceppm.ligne_budgetaire;
       microserviceppm         sgimp    false    9            ?            1259    92951    mode_passation    TABLE     ?  CREATE TABLE microserviceppm.mode_passation (
    id bigint NOT NULL,
    libelle_passation character varying(255) NOT NULL,
    abreviation character varying(255),
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 +   DROP TABLE microserviceppm.mode_passation;
       microserviceppm         sgimp    false    9            ?            1259    93071    nature_prestation    TABLE     ?  CREATE TABLE microserviceppm.nature_prestation (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 .   DROP TABLE microserviceppm.nature_prestation;
       microserviceppm         sgimp    false    9            ?            1259    93103     nature_prestation_mode_passation    TABLE     ?  CREATE TABLE microserviceppm.nature_prestation_mode_passation (
    id bigint NOT NULL,
    montant_min double precision NOT NULL,
    montant_max double precision NOT NULL,
    mode_passation_id bigint,
    nature_prestation_id bigint,
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 =   DROP TABLE microserviceppm.nature_prestation_mode_passation;
       microserviceppm         sgimp    false    9            ?            1259    93123    norme_reference    TABLE       CREATE TABLE microserviceppm.norme_reference (
    id bigint NOT NULL,
    norme integer,
    referentiel integer,
    norme_min integer,
    referentiel_min integer,
    norme_max integer,
    referentiel_max integer,
    norme_ouvrable boolean,
    referentiel_ouvrable boolean,
    intervalle boolean,
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 ,   DROP TABLE microserviceppm.norme_reference;
       microserviceppm         sgimp    false    9            ?            1259    92967    ppm    TABLE     ?  CREATE TABLE microserviceppm.ppm (
    id bigint NOT NULL,
    libelle_ppm character varying(255) NOT NULL,
    reference_plan character varying(255) NOT NULL,
    valid boolean,
    id_exercice bigint,
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    montant_estime double precision
);
     DROP TABLE microserviceppm.ppm;
       microserviceppm         sgimp    false    9            ?            1259    93051    ppm_activite    TABLE     L  CREATE TABLE microserviceppm.ppm_activite (
    id bigint NOT NULL,
    montant_depense_engage_non_liquide double precision NOT NULL,
    credit_disponible double precision NOT NULL,
    periode_lancement_appel date NOT NULL,
    periode_remise_offre date NOT NULL,
    temps_necessaire_evaluation_offre integer NOT NULL,
    date_problable_demarage_prestation date NOT NULL,
    delai_execution_prevu integer NOT NULL,
    date_buttoire date NOT NULL,
    niveau character varying(255),
    deleted boolean,
    report boolean,
    ppm_id bigint,
    id_exercice bigint,
    activite_id bigint,
    source_financement_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 )   DROP TABLE microserviceppm.ppm_activite;
       microserviceppm         sgimp    false    9            ?            1259    93015    referentiel_delai    TABLE     ?  CREATE TABLE microserviceppm.referentiel_delai (
    id bigint NOT NULL,
    observation character varying(255),
    deleted boolean,
    etape_id bigint,
    acteur_id bigint,
    mode_passation_id bigint,
    norme_reference_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 .   DROP TABLE microserviceppm.referentiel_delai;
       microserviceppm         sgimp    false    9            ?            1259    92870    sequence_generator    SEQUENCE     ?   CREATE SEQUENCE microserviceppm.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE microserviceppm.sequence_generator;
       microserviceppm       sgimp    false    9            ?            1259    93025    source_financement    TABLE       CREATE TABLE microserviceppm.source_financement (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    libelle character varying(255) NOT NULL,
    code_pays character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 /   DROP TABLE microserviceppm.source_financement;
       microserviceppm         sgimp    false    9            ?            1259    93087    timbre    TABLE     V  CREATE TABLE microserviceppm.timbre (
    id bigint NOT NULL,
    code character varying(255),
    sigle character varying(255),
    libelle character varying(255),
    pays character varying(255),
    devise character varying(255),
    logo bytea,
    logo_content_type character varying(255),
    identite_ministre character varying(255),
    titre_ministre character varying(255),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 #   DROP TABLE microserviceppm.timbre;
       microserviceppm         sgimp    false    9            ?            1259    92935    unite_administrative    TABLE       CREATE TABLE microserviceppm.unite_administrative (
    id bigint NOT NULL,
    libelle character varying(255) NOT NULL,
    abbreviation character varying(255) NOT NULL,
    adresse character varying(255),
    contact character varying(255),
    identite_responsable character varying(255),
    deleted boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 1   DROP TABLE microserviceppm.unite_administrative;
       microserviceppm         sgimp    false    9            ?            1259    93143    user_notification    TABLE     ?  CREATE TABLE microserviceppm.user_notification (
    id bigint NOT NULL,
    visited boolean,
    jour timestamp without time zone,
    user_id bigint NOT NULL,
    tache_etape_id bigint NOT NULL,
    type_tache character varying(50) NOT NULL,
    deleted boolean NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
 .   DROP TABLE microserviceppm.user_notification;
       microserviceppm         sgimp    false    9                      0    92999    acteur 
   TABLE DATA               ?   COPY microserviceppm.acteur (id, libelle, deleted, userid, mail, contact, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    223   س                 0    92983    activite 
   TABLE DATA               ?   COPY microserviceppm.activite (id, code_ligne_plan, nom_activite, gestionnaire_credit, etat_marche, deleted, reported, passation_id, nature_prestation_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    222    ?                 0    92905    besoin 
   TABLE DATA                 COPY microserviceppm.besoin (id, libelle, quantite, date_debut, date_fin, deleted, nombre_lot, used, montant_estime, exercice_id, unite_administrative_id, nature_prestation_id, created_by, created_date, last_modified_by, last_modified_date, montant_inscrit, montant_restant) FROM stdin;
    microserviceppm       sgimp    false    216   ??                 0    92915    besoin_ligne_budgetaire 
   TABLE DATA               ?   COPY microserviceppm.besoin_ligne_budgetaire (id, aecp, montant_estime, deleted, ligne_budget_id, besoin_id, activite_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    217   ?       *          0    93133    calcul_delai 
   TABLE DATA               ?   COPY microserviceppm.calcul_delai (id, libelle, deleted, etape_id, mode_passation_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    234   ?                 0    92864    databasechangelog 
   TABLE DATA               ?   COPY microserviceppm.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
    microserviceppm       sgimp    false    211   8?                 0    92859    databasechangeloglock 
   TABLE DATA               [   COPY microserviceppm.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
    microserviceppm       sgimp    false    210   )?       "          0    93041    etape 
   TABLE DATA               ?   COPY microserviceppm.etape (id, libelle, ordre, deleted, mode_passation_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    226   N?       $          0    93061    etape_activite_ppm 
   TABLE DATA               ?   COPY microserviceppm.etape_activite_ppm (id, date_etape, date_reelle, deleted, visited, etape_id, ppm_activite_id, debut, fin, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    228   ??                 0    92925    exercice_budgetaire 
   TABLE DATA               ?   COPY microserviceppm.exercice_budgetaire (id, annee, deleted, active, elaborer, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    218   ??                 0    92872    jhi_persistent_audit_event 
   TABLE DATA               j   COPY microserviceppm.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
    microserviceppm       sgimp    false    213   @?                 0    92877    jhi_persistent_audit_evt_data 
   TABLE DATA               W   COPY microserviceppm.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
    microserviceppm       sgimp    false    214   ]?       (          0    93113    jour_ferier 
   TABLE DATA               ?   COPY microserviceppm.jour_ferier (id, libelle, jour, deleted, exercice_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    232   z?                 0    92889    ligne_budgetaire 
   TABLE DATA               b  COPY microserviceppm.ligne_budgetaire (id, budget, ligne_credit, section, programme, action, chapitre, activite, article, paragraphe, dot_init_ae, dot_init_cp, dot_cor_ae, dot_cor_cp, engage, engage_cf, liquide, ordonne, vbp, ecp, deleted, exercice_id, created_by, created_date, last_modified_by, last_modified_date, unite_administrative_id) FROM stdin;
    microserviceppm       sgimp    false    215   ??                 0    92951    mode_passation 
   TABLE DATA               ?   COPY microserviceppm.mode_passation (id, libelle_passation, abreviation, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    220   ?       %          0    93071    nature_prestation 
   TABLE DATA               ?   COPY microserviceppm.nature_prestation (id, code, libelle, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    229   [?       '          0    93103     nature_prestation_mode_passation 
   TABLE DATA               ?   COPY microserviceppm.nature_prestation_mode_passation (id, montant_min, montant_max, mode_passation_id, nature_prestation_id, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    231   ?       )          0    93123    norme_reference 
   TABLE DATA               ?   COPY microserviceppm.norme_reference (id, norme, referentiel, norme_min, referentiel_min, norme_max, referentiel_max, norme_ouvrable, referentiel_ouvrable, intervalle, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    233   2?                 0    92967    ppm 
   TABLE DATA               ?   COPY microserviceppm.ppm (id, libelle_ppm, reference_plan, valid, id_exercice, deleted, created_by, created_date, last_modified_by, last_modified_date, montant_estime) FROM stdin;
    microserviceppm       sgimp    false    221   O?       #          0    93051    ppm_activite 
   TABLE DATA               ?  COPY microserviceppm.ppm_activite (id, montant_depense_engage_non_liquide, credit_disponible, periode_lancement_appel, periode_remise_offre, temps_necessaire_evaluation_offre, date_problable_demarage_prestation, delai_execution_prevu, date_buttoire, niveau, deleted, report, ppm_id, id_exercice, activite_id, source_financement_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    227   ??                  0    93015    referentiel_delai 
   TABLE DATA               ?   COPY microserviceppm.referentiel_delai (id, observation, deleted, etape_id, acteur_id, mode_passation_id, norme_reference_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    224   r?       1           0    0    sequence_generator    SEQUENCE SET     L   SELECT pg_catalog.setval('microserviceppm.sequence_generator', 1850, true);
            microserviceppm       sgimp    false    212            !          0    93025    source_financement 
   TABLE DATA               ?   COPY microserviceppm.source_financement (id, code, libelle, code_pays, description, type, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    225   ??       &          0    93087    timbre 
   TABLE DATA               ?   COPY microserviceppm.timbre (id, code, sigle, libelle, pays, devise, logo, logo_content_type, identite_ministre, titre_ministre, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    230   ??                 0    92935    unite_administrative 
   TABLE DATA               ?   COPY microserviceppm.unite_administrative (id, libelle, abbreviation, adresse, contact, identite_responsable, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    219   ??       +          0    93143    user_notification 
   TABLE DATA               ?   COPY microserviceppm.user_notification (id, visited, jour, user_id, tache_etape_id, type_tache, deleted, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
    microserviceppm       sgimp    false    235   ?       p
           2606    93006    acteur acteur_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY microserviceppm.acteur
    ADD CONSTRAINT acteur_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY microserviceppm.acteur DROP CONSTRAINT acteur_pkey;
       microserviceppm         sgimp    false    223    223            n
           2606    92990    activite activite_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY microserviceppm.activite
    ADD CONSTRAINT activite_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY microserviceppm.activite DROP CONSTRAINT activite_pkey;
       microserviceppm         sgimp    false    222    222            d
           2606    92919 4   besoin_ligne_budgetaire besoin_ligne_budgetaire_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire
    ADD CONSTRAINT besoin_ligne_budgetaire_pkey PRIMARY KEY (id);
 g   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire DROP CONSTRAINT besoin_ligne_budgetaire_pkey;
       microserviceppm         sgimp    false    217    217            b
           2606    92909    besoin besoin_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY microserviceppm.besoin
    ADD CONSTRAINT besoin_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY microserviceppm.besoin DROP CONSTRAINT besoin_pkey;
       microserviceppm         sgimp    false    216    216            ?
           2606    93137    calcul_delai calcul_delai_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY microserviceppm.calcul_delai
    ADD CONSTRAINT calcul_delai_pkey PRIMARY KEY (id);
 Q   ALTER TABLE ONLY microserviceppm.calcul_delai DROP CONSTRAINT calcul_delai_pkey;
       microserviceppm         sgimp    false    234    234            X
           2606    92863 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY microserviceppm.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 c   ALTER TABLE ONLY microserviceppm.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       microserviceppm         sgimp    false    210    210            z
           2606    93065 *   etape_activite_ppm etape_activite_ppm_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY microserviceppm.etape_activite_ppm
    ADD CONSTRAINT etape_activite_ppm_pkey PRIMARY KEY (id);
 ]   ALTER TABLE ONLY microserviceppm.etape_activite_ppm DROP CONSTRAINT etape_activite_ppm_pkey;
       microserviceppm         sgimp    false    228    228            v
           2606    93045    etape etape_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY microserviceppm.etape
    ADD CONSTRAINT etape_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY microserviceppm.etape DROP CONSTRAINT etape_pkey;
       microserviceppm         sgimp    false    226    226            f
           2606    92929 ,   exercice_budgetaire exercice_budgetaire_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY microserviceppm.exercice_budgetaire
    ADD CONSTRAINT exercice_budgetaire_pkey PRIMARY KEY (id);
 _   ALTER TABLE ONLY microserviceppm.exercice_budgetaire DROP CONSTRAINT exercice_budgetaire_pkey;
       microserviceppm         sgimp    false    218    218            [
           2606    92876 :   jhi_persistent_audit_event jhi_persistent_audit_event_pkey 
   CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);
 m   ALTER TABLE ONLY microserviceppm.jhi_persistent_audit_event DROP CONSTRAINT jhi_persistent_audit_event_pkey;
       microserviceppm         sgimp    false    213    213            ^
           2606    92881 @   jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey 
   CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);
 s   ALTER TABLE ONLY microserviceppm.jhi_persistent_audit_evt_data DROP CONSTRAINT jhi_persistent_audit_evt_data_pkey;
       microserviceppm         sgimp    false    214    214    214            ?
           2606    93117    jour_ferier jour_ferier_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY microserviceppm.jour_ferier
    ADD CONSTRAINT jour_ferier_pkey PRIMARY KEY (id);
 O   ALTER TABLE ONLY microserviceppm.jour_ferier DROP CONSTRAINT jour_ferier_pkey;
       microserviceppm         sgimp    false    232    232            `
           2606    92896 &   ligne_budgetaire ligne_budgetaire_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY microserviceppm.ligne_budgetaire
    ADD CONSTRAINT ligne_budgetaire_pkey PRIMARY KEY (id);
 Y   ALTER TABLE ONLY microserviceppm.ligne_budgetaire DROP CONSTRAINT ligne_budgetaire_pkey;
       microserviceppm         sgimp    false    215    215            j
           2606    92958 "   mode_passation mode_passation_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY microserviceppm.mode_passation
    ADD CONSTRAINT mode_passation_pkey PRIMARY KEY (id);
 U   ALTER TABLE ONLY microserviceppm.mode_passation DROP CONSTRAINT mode_passation_pkey;
       microserviceppm         sgimp    false    220    220            ?
           2606    93107 F   nature_prestation_mode_passation nature_prestation_mode_passation_pkey 
   CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.nature_prestation_mode_passation
    ADD CONSTRAINT nature_prestation_mode_passation_pkey PRIMARY KEY (id);
 y   ALTER TABLE ONLY microserviceppm.nature_prestation_mode_passation DROP CONSTRAINT nature_prestation_mode_passation_pkey;
       microserviceppm         sgimp    false    231    231            |
           2606    93078 (   nature_prestation nature_prestation_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY microserviceppm.nature_prestation
    ADD CONSTRAINT nature_prestation_pkey PRIMARY KEY (id);
 [   ALTER TABLE ONLY microserviceppm.nature_prestation DROP CONSTRAINT nature_prestation_pkey;
       microserviceppm         sgimp    false    229    229            ?
           2606    93127 $   norme_reference norme_reference_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY microserviceppm.norme_reference
    ADD CONSTRAINT norme_reference_pkey PRIMARY KEY (id);
 W   ALTER TABLE ONLY microserviceppm.norme_reference DROP CONSTRAINT norme_reference_pkey;
       microserviceppm         sgimp    false    233    233            x
           2606    93055    ppm_activite ppm_activite_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY microserviceppm.ppm_activite
    ADD CONSTRAINT ppm_activite_pkey PRIMARY KEY (id);
 Q   ALTER TABLE ONLY microserviceppm.ppm_activite DROP CONSTRAINT ppm_activite_pkey;
       microserviceppm         sgimp    false    227    227            l
           2606    92974    ppm ppm_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY microserviceppm.ppm
    ADD CONSTRAINT ppm_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY microserviceppm.ppm DROP CONSTRAINT ppm_pkey;
       microserviceppm         sgimp    false    221    221            r
           2606    93019 (   referentiel_delai referentiel_delai_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY microserviceppm.referentiel_delai
    ADD CONSTRAINT referentiel_delai_pkey PRIMARY KEY (id);
 [   ALTER TABLE ONLY microserviceppm.referentiel_delai DROP CONSTRAINT referentiel_delai_pkey;
       microserviceppm         sgimp    false    224    224            t
           2606    93032 *   source_financement source_financement_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY microserviceppm.source_financement
    ADD CONSTRAINT source_financement_pkey PRIMARY KEY (id);
 ]   ALTER TABLE ONLY microserviceppm.source_financement DROP CONSTRAINT source_financement_pkey;
       microserviceppm         sgimp    false    225    225            ~
           2606    93094    timbre timbre_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY microserviceppm.timbre
    ADD CONSTRAINT timbre_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY microserviceppm.timbre DROP CONSTRAINT timbre_pkey;
       microserviceppm         sgimp    false    230    230            h
           2606    92942 .   unite_administrative unite_administrative_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY microserviceppm.unite_administrative
    ADD CONSTRAINT unite_administrative_pkey PRIMARY KEY (id);
 a   ALTER TABLE ONLY microserviceppm.unite_administrative DROP CONSTRAINT unite_administrative_pkey;
       microserviceppm         sgimp    false    219    219            ?
           2606    93147 (   user_notification user_notification_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY microserviceppm.user_notification
    ADD CONSTRAINT user_notification_pkey PRIMARY KEY (id);
 [   ALTER TABLE ONLY microserviceppm.user_notification DROP CONSTRAINT user_notification_pkey;
       microserviceppm         sgimp    false    235    235            Y
           1259    92882    idx_persistent_audit_event    INDEX     {   CREATE INDEX idx_persistent_audit_event ON microserviceppm.jhi_persistent_audit_event USING btree (principal, event_date);
 7   DROP INDEX microserviceppm.idx_persistent_audit_event;
       microserviceppm         sgimp    false    213    213            \
           1259    92883    idx_persistent_audit_evt_data    INDEX     t   CREATE INDEX idx_persistent_audit_evt_data ON microserviceppm.jhi_persistent_audit_evt_data USING btree (event_id);
 :   DROP INDEX microserviceppm.idx_persistent_audit_evt_data;
       microserviceppm         sgimp    false    214            ?
           2606    93183 )   activite fk_activite_nature_prestation_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.activite
    ADD CONSTRAINT fk_activite_nature_prestation_id FOREIGN KEY (nature_prestation_id) REFERENCES microserviceppm.nature_prestation(id);
 \   ALTER TABLE ONLY microserviceppm.activite DROP CONSTRAINT fk_activite_nature_prestation_id;
       microserviceppm       sgimp    false    2684    222    229            ?
           2606    93178 !   activite fk_activite_passation_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.activite
    ADD CONSTRAINT fk_activite_passation_id FOREIGN KEY (passation_id) REFERENCES microserviceppm.mode_passation(id);
 T   ALTER TABLE ONLY microserviceppm.activite DROP CONSTRAINT fk_activite_passation_id;
       microserviceppm       sgimp    false    220    222    2666            ?
           2606    93153    besoin fk_besoin_exercice_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.besoin
    ADD CONSTRAINT fk_besoin_exercice_id FOREIGN KEY (exercice_id) REFERENCES microserviceppm.exercice_budgetaire(id);
 O   ALTER TABLE ONLY microserviceppm.besoin DROP CONSTRAINT fk_besoin_exercice_id;
       microserviceppm       sgimp    false    216    218    2662            ?
           2606    93173 >   besoin_ligne_budgetaire fk_besoin_ligne_budgetaire_activite_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire
    ADD CONSTRAINT fk_besoin_ligne_budgetaire_activite_id FOREIGN KEY (activite_id) REFERENCES microserviceppm.activite(id);
 q   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire DROP CONSTRAINT fk_besoin_ligne_budgetaire_activite_id;
       microserviceppm       sgimp    false    222    217    2670            ?
           2606    93168 <   besoin_ligne_budgetaire fk_besoin_ligne_budgetaire_besoin_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire
    ADD CONSTRAINT fk_besoin_ligne_budgetaire_besoin_id FOREIGN KEY (besoin_id) REFERENCES microserviceppm.besoin(id);
 o   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire DROP CONSTRAINT fk_besoin_ligne_budgetaire_besoin_id;
       microserviceppm       sgimp    false    216    2658    217            ?
           2606    93163 B   besoin_ligne_budgetaire fk_besoin_ligne_budgetaire_ligne_budget_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire
    ADD CONSTRAINT fk_besoin_ligne_budgetaire_ligne_budget_id FOREIGN KEY (ligne_budget_id) REFERENCES microserviceppm.ligne_budgetaire(id);
 u   ALTER TABLE ONLY microserviceppm.besoin_ligne_budgetaire DROP CONSTRAINT fk_besoin_ligne_budgetaire_ligne_budget_id;
       microserviceppm       sgimp    false    217    215    2656            ?
           2606    93158 (   besoin fk_besoin_unite_administrative_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.besoin
    ADD CONSTRAINT fk_besoin_unite_administrative_id FOREIGN KEY (unite_administrative_id) REFERENCES microserviceppm.unite_administrative(id);
 [   ALTER TABLE ONLY microserviceppm.besoin DROP CONSTRAINT fk_besoin_unite_administrative_id;
       microserviceppm       sgimp    false    2664    219    216            ?
           2606    93223 1   etape_activite_ppm fk_etape_activite_ppm_etape_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.etape_activite_ppm
    ADD CONSTRAINT fk_etape_activite_ppm_etape_id FOREIGN KEY (etape_id) REFERENCES microserviceppm.etape(id);
 d   ALTER TABLE ONLY microserviceppm.etape_activite_ppm DROP CONSTRAINT fk_etape_activite_ppm_etape_id;
       microserviceppm       sgimp    false    2678    226    228            ?
           2606    93228 8   etape_activite_ppm fk_etape_activite_ppm_ppm_activite_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.etape_activite_ppm
    ADD CONSTRAINT fk_etape_activite_ppm_ppm_activite_id FOREIGN KEY (ppm_activite_id) REFERENCES microserviceppm.ppm_activite(id);
 k   ALTER TABLE ONLY microserviceppm.etape_activite_ppm DROP CONSTRAINT fk_etape_activite_ppm_ppm_activite_id;
       microserviceppm       sgimp    false    2680    227    228            ?
           2606    92884 8   jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES microserviceppm.jhi_persistent_audit_event(event_id);
 k   ALTER TABLE ONLY microserviceppm.jhi_persistent_audit_evt_data DROP CONSTRAINT fk_evt_pers_audit_evt_data;
       microserviceppm       sgimp    false    214    213    2651            ?
           2606    93243 &   jour_ferier fk_jour_ferier_exercice_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.jour_ferier
    ADD CONSTRAINT fk_jour_ferier_exercice_id FOREIGN KEY (exercice_id) REFERENCES microserviceppm.exercice_budgetaire(id);
 Y   ALTER TABLE ONLY microserviceppm.jour_ferier DROP CONSTRAINT fk_jour_ferier_exercice_id;
       microserviceppm       sgimp    false    2662    232    218            ?
           2606    93233 5   nature_prestation_mode_passation fk_mode_passation_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.nature_prestation_mode_passation
    ADD CONSTRAINT fk_mode_passation_id FOREIGN KEY (mode_passation_id) REFERENCES microserviceppm.mode_passation(id);
 h   ALTER TABLE ONLY microserviceppm.nature_prestation_mode_passation DROP CONSTRAINT fk_mode_passation_id;
       microserviceppm       sgimp    false    231    220    2666            ?
           2606    93238 8   nature_prestation_mode_passation fk_nature_prestation_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.nature_prestation_mode_passation
    ADD CONSTRAINT fk_nature_prestation_id FOREIGN KEY (nature_prestation_id) REFERENCES microserviceppm.nature_prestation(id);
 k   ALTER TABLE ONLY microserviceppm.nature_prestation_mode_passation DROP CONSTRAINT fk_nature_prestation_id;
       microserviceppm       sgimp    false    229    2684    231            ?
           2606    93213 (   ppm_activite fk_ppm_activite_activite_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.ppm_activite
    ADD CONSTRAINT fk_ppm_activite_activite_id FOREIGN KEY (activite_id) REFERENCES microserviceppm.activite(id);
 [   ALTER TABLE ONLY microserviceppm.ppm_activite DROP CONSTRAINT fk_ppm_activite_activite_id;
       microserviceppm       sgimp    false    2670    222    227            ?
           2606    93208 (   ppm_activite fk_ppm_activite_id_exercice    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.ppm_activite
    ADD CONSTRAINT fk_ppm_activite_id_exercice FOREIGN KEY (id_exercice) REFERENCES microserviceppm.exercice_budgetaire(id);
 [   ALTER TABLE ONLY microserviceppm.ppm_activite DROP CONSTRAINT fk_ppm_activite_id_exercice;
       microserviceppm       sgimp    false    227    2662    218            ?
           2606    93203 #   ppm_activite fk_ppm_activite_ppm_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.ppm_activite
    ADD CONSTRAINT fk_ppm_activite_ppm_id FOREIGN KEY (ppm_id) REFERENCES microserviceppm.ppm(id);
 V   ALTER TABLE ONLY microserviceppm.ppm_activite DROP CONSTRAINT fk_ppm_activite_ppm_id;
       microserviceppm       sgimp    false    227    2668    221            ?
           2606    93218 2   ppm_activite fk_ppm_activite_source_financement_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.ppm_activite
    ADD CONSTRAINT fk_ppm_activite_source_financement_id FOREIGN KEY (source_financement_id) REFERENCES microserviceppm.source_financement(id);
 e   ALTER TABLE ONLY microserviceppm.ppm_activite DROP CONSTRAINT fk_ppm_activite_source_financement_id;
       microserviceppm       sgimp    false    225    2676    227            ?
           2606    93193 0   referentiel_delai fk_referentiel_delai_acteur_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.referentiel_delai
    ADD CONSTRAINT fk_referentiel_delai_acteur_id FOREIGN KEY (acteur_id) REFERENCES microserviceppm.acteur(id);
 c   ALTER TABLE ONLY microserviceppm.referentiel_delai DROP CONSTRAINT fk_referentiel_delai_acteur_id;
       microserviceppm       sgimp    false    223    2672    224            ?
           2606    93188 /   referentiel_delai fk_referentiel_delai_etape_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.referentiel_delai
    ADD CONSTRAINT fk_referentiel_delai_etape_id FOREIGN KEY (etape_id) REFERENCES microserviceppm.etape(id);
 b   ALTER TABLE ONLY microserviceppm.referentiel_delai DROP CONSTRAINT fk_referentiel_delai_etape_id;
       microserviceppm       sgimp    false    2678    226    224            ?
           2606    93198 8   referentiel_delai fk_referentiel_delai_mode_passation_id    FK CONSTRAINT     ?   ALTER TABLE ONLY microserviceppm.referentiel_delai
    ADD CONSTRAINT fk_referentiel_delai_mode_passation_id FOREIGN KEY (mode_passation_id) REFERENCES microserviceppm.mode_passation(id);
 k   ALTER TABLE ONLY microserviceppm.referentiel_delai DROP CONSTRAINT fk_referentiel_delai_mode_passation_id;
       microserviceppm       sgimp    false    220    2666    224                 x??U;N?0?_N?x??fw;???,?8???	!Q????$?DBQ?H?,????	X?O?????????
???z.{&?e.?:H5?}?H?73??)0?J[_???,.?ӡM"?:?????????T$????uV?ȣϳ??-?v?"??`???5??:=?Pѕ??W*<Q'?"??lf?l'?J??0aL_.0?????%2C?<?????5????T=$??}?
rر-??ե?Ö????4T?Z????۳??C*Kk?n???[?}v;4M?	\a??         ?   x???=N1Fk??@63c{?[? Q@??f?:?R???A?\???pNB+v?????=͠K???^U???c??Ѝ??j???ӈ????ߊ?y???-
n????X?[ )?5??h?iW??%j?d@????1????w???@
?:X?<<Oq?c<u????-f?Id?/d[8????6???pǇ8??V???{?I=?F?BA??Hh??{	??????ߗ+?T           x???;N?0?????F3?G??=?6^b???,??(?-W?&??? ??)?d?\|??I36????w?nz?Z_??^?p??8???u??E ?%?A?ŵ????
iKf??6h?i???I????ίp??????Adp??^??Afphl?g?T??`]\?<Lac8u???Q?6?Y??n?+[? F? g???u??^???/?M??it??O?ȥ??fa?	???*c?X??h?)C?i~D`?*CV6i?e(??B1?y0p???eY~ (?         ?   x???;n?0?Y:E/?o?9K?E???????#???'[?P ?o??P?Y? ???????????o@Hw?w??IS?(@?%??S??e?:??????s???́z_???/?ߕH?@?z?5|M}GdO?????k???\7 _??????????#x??A???^???????M?=?G??h??pn?A?????????|?s_??'l§????Y__???????3??      *      x?????? ? ?         ?  x??[?nG}?> v???o	?!q`7??8?> ?RmsW??
???ږ9"??i?Y?,?U?>??Ou?(???????????]?^?囯/???-S????۸z×?o?\???r????????????.;P????T???ߐ???rJ??t???/???????&????}I?'Sػ\?U???M]^s??/???2_?~??e???}?a??W????u?????g??뿑?A?Ӂ??Oc?_gP:؅?j?
VY?(??[?S? ?uL?|???n?7??]?@W?E?+?͂??�?u?fQ?&~{Ky?^^????????????????????e?sdt_M???ٳc????d??+?tiË??B?-7??\?Y??w?o?r?G(? ??.?`u??8?M $UY넿!??G8??C.?}?9x???2n?׫ۿ?U:?E^H?K?U??8ȯ?s???q?W7?wǁ頵?K)??V;t~x?-_?|y?Z?#ݙ]??xt16?r?X2??k??4?Dz~}yw?:??Т????|{?\i?????6hg?%?f??1Z??ǒP?F??]??a:?U??^???iY?????:?`??%?M?i???%	???P5(????ɫQ#?.???jq> ?Xd?!Z?p?j?tT???U?{?pO?B??V?.?Ӂz?????JV?j ?$'[l?A?G??J?n??~??ş???<	8h???[Xr??&;?"&?IVsTX????<?t??v??O? ?p???~HT?????@28??pF%??;?!k!?w;??"mx?W?u??r%5??Fj???P??2?6?ʮZW?Ɏ%??!G??3? ?#?h???+C?<x??O?#?????}LRD???p??uP??5??FIhU?&+#?d頾O?[?a?|]?U??݊?`tR????O?N^??C??ʀ?lB?^I?#Ҿ?????!??mu3l?(?:???[???[!???M?g[g)??`)9c?Vr??"az?ի??Z&t????]1???E.?VMA.RᖬL????\?j???jC??kt,??q?R:J??2?$1???(A???X?AeN?ʹ??<D?]?$.Y?4??????и???*?h?7 N??FE???d?l?va?`??l???ـ??v??????)??׽?K?k3?
R????ɢmѩ=????G?-?????,Ȧ`k?U?|M?R`???!?q?J?9??5ڀ3aџ?Bg#"C?`?^jS٩ T??IhtR??$?c??ΟkQ?{??_\y?ϗ???GP"JՅY?RXz?>Zc%	ҟ??r> ??t?)???Q?@n??)?I?(????ǡQ?I???g?;??-?_^o?u??;S?h????w`Nb?+G?[vPk`????R?]vF?Ţ\???mi'???\߭3??\?U?+yq?U?-??.Fg@???EF?XM??XC?vuQ????}?????_?'?tNIR?	4c?B?i??TV?Y?R????f?[z??כz??7???OoZw8p=ɿ?-??Y???+?\K=??#??֮??(AIƳo-?f,a??G*o<??+x?f-? ???8rD=?<ji-?^?????g?ТBɴC??Ϲ?ɤ????5R??ĈU?b??c1?D??????????ξ{?P??h`?2???`?:k?"5???	??b<.7?li?~?_*?
?#XAG0<?ʶB5Jd)B?M5ɮ??ة?vy??]L>j??????Ct|?=0뱣a?Sa%?˳??ʖ?)To?M????鷅ߖ!???^??ݚ_??v????a??q?j\G#??`?P????f0<v???]??G??]ҧ?G+E:?}_?$?22!x`??????m????u?z??ut???J???ti?s?zy?????C?H~?{kN??I,?0SJ
???J?Ԙ??lC?A?.??8?5?}?????e??'%@mel?HU??ʸ$-Q
??xP??3ګ??>?????/?i]N0????????BP?6d? ?u?YrK??LN???????h??#O?Ȱ3??8|d??uf?R?A(?K?@Z?U???fũ??&N?8y5??????d???n?#??<?	?o&???ܖ??^??	?G?`?.?-B??Ɛp?????o?>???C?Go;?;U??謼ڙa??1/??V???}Ȉ^~????gs9[?`EV{_^????1y>?Q?f$??8????W?X?Ϊ??XY???[Wu?:??????ǘ_??s*p#&?3?ܓ<??M?3w?????C?ŐO?Jz*0D??#f???9t:?????2?}??#W:?;;p[F??Y\G(?????c??)0c"??xMk?$?Z2?}??䣪?tR?Qg?əP??R?آ=k???R?Ge?U???f?y?P???ӻ?`-vv`?2?"'K???<?:??8g?c??N?.V;Qg@>???g??`:;pYa?TJJ9?d?2D??곸????Ξ?O
N~?L.??1^?6??{??????TN?d?w?HҲ?`?V???[??????oV??????.?1?~???W???}??w??a?????嫋??^>??Ol?}???????O~|w?x?\??5??q?sG?䮁?R9?????|?j?Kv?[?ZϢu?I?#S????F???U???1?鴚Z?n8ܥ??ݠ?(?XDKb?m4??]?9˲˖?Ld??a????t????%jXO?iR??|l?ONgҵ0?g#v??|7*mA????1)?X????{L???Yk???????A?fw?C?????,ܿ;??????p?????ߠ?etJ?????3?+?*P??b?Ƙ\?Qij????????????'?MeoV?e???'m?ӹ?-\?5)w??ܰD?Ca7(j??f???J??r??*?T?9??i?0??-?Cs????\??cx@?v(????v6?H?t7(??;T!1??"啷?r6z???~????D???[lˀv??B??S?ǭ[&??6!?R?NUǥ???&YWJ-?f??J*?d'r?sbzh?v?n?b?M?oϞ<y????[O            x?3?L???"?=... U?      "   M  x??]?nG>???o??2???? e??pc#r$O@??!?۾??|?r?w?d?d??????!?9??DQ????????m??????)۽,??u\???????=[g??Wמ??5?
#M?B???a2a?3?w?wis1??WB??Q?
??o??+)?"`˙???8L
av_??g7?\\?}?wM?/?!??`D?r?=.?????Ə??u1?q????a?26?pA??OO1}???<??????ci??h???E??գHj?g?Ww?u?06??W4`	??`.???????nv/I??"Yg???EH???(?%?럮??aI????۠????4?|?\?>?q??As!iG?4"?Y??jPGs??;zP??A??9??x=???\?,???-fkE??2LZ'?>?|A?*H1?R???SG?R;??????v/????o??}?Yh??ah w???????l??????p??2q ?q??N??BIt??? P???I???p?[??x?Y'a1??????4??ڗ?0Y?"V???^??ĨJ?,????>?^
??F? X??v?!Q??Hc?g_?H?J[??Δ{t???U?@O??vIAzH?e??ʺ ????)>???@mc[.P x?^?%塏iq?;R?܏???y?>?I??/A<?BK??^?_?,|??????8*?R?Z8y89k6?<?ir??u? P  ??? yH?t??vX??`K?2>c?n4????JI?z%??I?Qra?e T??;?_(?F??~?s??;??hN`?0:k(?G{9??$Q%X?Fs? ????`V?R9)M ?X????????L???/>?MZ?E??D?DM ?YX*????h???B??p}???o?Ӌ/??t??T ,v??B????j	f??0`??~??p_?vdPiz~4?e??91??6????^??IG???S??<??X??Ԟ& (.\??8~\?i??????b?Yǩ?9)?H?p??????$?qU????i??U^?W?5?DF???@?????_?G>?d??q?KVy?E P?v??S ??????ceC]?m?)?8)?9?W??֡t?	?!2t??u>?ao??t?FѬKA??U? ??U3 ????*???`?1?c??W5LB?a?-sqr?ֻC3 v??-@?ӌ? ????0hȳW?f ???W?(?3??z???K????",??????1L?S?HCW}???Ś?????؋? ?cX?C?J;?OGUCMXY???? Sd??? 7`}?vT??C??Ͷř^4??1\?? ?????CE໭? ???{+????E!k?ې?=`?0S??A??6q????]?e`Lo???5?25y?3-F??h?{?oW"%$Y?_?ϖd???
aL?vӜa?F0???]5???-?=?4! ???? ?=9?0چi1??$T?dI?\\???:(?????h&???ZM?1!MM{18??4??????s???????3? P??LnG?.W?o?ͥC? W-?T??}?R<????_iy?#k?ç+?k???
B???????"?W˜F|?ۺDVi?U?B?Z???( ~-????)?h!o-??U??azA?Z.? ????r?| ?????\AM~o2-? ??%????Q pۼw[M?ES5k?K?#A?l׬5?E??c?_?$?!BM!B?T" ׶?&B?4&`.l?/̈́l?? ?P'k?x&`ulO??+???e?`B???m&??????&???q?5v߆?$?8??!?)L$C_Wjz?dK?3?P?Ap?Q64???߅O????y?????S????M?Oz????h??]?Ͽ??W?:?X?h5l^0??`??4?b?W?u?<W?-J.o?]?eɬ?#?T????^???"???b?ୢ??)?爨35??S???!?l?%??XX8}
k%g{???? /ȵ??~?<????{?d??-??u?Re?J?AfG?9??l?"?
??K)URI! ??e~?`???????PJ4b?:????炋*???H?a8=???d`??.4p??gz?㥴??2	;$w?b2=??{d) ????Q&??U?e???R??Z?%?zŻߒҨ723[?~?/6  ?N????9?[ۤA??~+?s?(??fA??Q%鵳y?C??B??yy??RK? D??v?c??clz'?R??+lyC
i??T"???????HG?v ?!??a? B?q????y????u??ї????%?:9?C??????u(EuR?????_d=1G????? sx?,??>????JT?('??}ҽn??2<??????>?I???Kx?? ??q
x?6????pg?????ow???????Z??(i?ܜd7\?Z??X?E?z?"?1?Oq}%d?Q?[R???8?N???)?|(???ERV?%n??hpj?????D??h??-NPX4?gձ??Å ?F??`?$????s???GH??0???m??6??0ʜ	΄?lc,?HS?????(E@??QX?1?hXic????s?C???'?~?AB?yu ?Ma(<T[R
?su}bI????E6?(??ԢH?=ŉIMȔJO?)??8d#?v?jZ ???µ??wMQ?1?d??L9Er?
?)u??Sdw^??#?????0?)?o?e????O?n?Gg?6M??բym?dB?ܬ???&o?Q?v;Is???( ??)u
??S?3?I?3??4'wd?X!??o?P#-?\ _j?^?FWÆ?[.N??Ed?Gu]?Q??4??S9?6???O??^?fT"?:?I#4?𛖮-eyN?0̦??I?ٔ?e ĥuV#L,d#Bh#E?kF??.m=.??????8??{?i9??N??4??v|??J??n?? ?A0Û=Dju?Xh? ??GPk"W?Iu??N???㶆?&?/???iU;2p????1?&Æ???????3%fK?0Ħmk?1D????K?1Wb:??Cx=?m??%?y?!?lob?Z3?kYD??}r]<??s?%?'U?! ?Er4/?????nN???????-Y?????{N1=??s??????#?ϙpC/?ys?????q????B??F7?b?9??fީe}??m_??|??5z????8? T?[7??</W??\?@?z?A??ktE4?"?l???@2ʝT?m???	?B"??ڪ?W;???܇F?#????"p?u???h9????4???s]?????z^:2?6??VK???=????????X[1qI)?R?B??4`u?'j???N@@?'j:???=???\3??N3??T3??z?????#??6?C?>O??o&{gR?y?1O?"????!???^???Q??Ŧ?.??ӕc?W?C?Fi?W??#d+W?9?u2?f?A??׾??3?"w?YZ?g?
]?f?L9׹? 9??xd??ջ??,?M[????'"
??-???k????/??#oV??.???I?c?UxfS?m?6`?'T?!?@??????6]Ǧ??????o??X???z??u???/??z??xݞ?v??!E?wMF??Z???7AU??S?Xӝ.?ㄺE]?G^???fa9t?I?<E????c?Xr???w ?P????i?4?A??'\P?y?&W?]?W?"??wRn?/'fc?C??C?}??Đ'????`'?O??h?????? ??rx????>sG1??B?۞ \? l?ܲ=?&#?YL??(?C0?BX?NnVY\??R?`ma?_?~^,Ȭ U?????M?M09?R???cq??0Fo)??W?b?ӞVF?Ƣ6? ?)?s\?k"ٖ?n?5???o?]ӫ??D6?O???X???W?^???au      $      x?????? ? ?         h   x?}?1?0??zE>`??d??[?
th??M:uI.$q
h"??H?#??z?E??࢜lӬDm????????i>ً?F?(?s?C;?ѭ0l8oE?Ve-"??#p            x?????? ? ?            x?????? ? ?      (      x?????? ? ?         i  x???]n?@???{???'?پ???6L?}gwilR#M???&???iK?>?]/K??j????w???B=?է??h5?Z{?X???,?A???p??X?!?6?[?"AU?n??\!.9?dޛ??f5#m?????j_???}???.ƀ41??ap??X?(????@6??1??I#B??k􁭟d??j$v?(?Ϭ?????$#?????mˁ?ؽ?`??QMϳ?>B?(?"?I???$s?????y?9?V?$??W?u??ܵ}???m?y?[?f[74	յu?X]??B????Z?u A?e??R??g?u?LQ??? ?4??9!]?+	ְ??5?f1?;jW:?         ;   x?3420?t	? ?4?Ĕ??<N##]K]#C#+#+C=Ccs#?
?b???? A!*      %   ?   x?????0Ek{
/??=B??%?1?R???A??A?*l?$?")Nwt?t??H?+?7?8Q???w.ĉ?5?L??lR??dY??K(?ެ
?ȟ?#??r?e?\R@????????????U??U!k?????O??E????b?#խ[(?P??G_?r????d:      '      x?????? ? ?      )      x?????? ? ?         K   x?3440???5202?,(?U 3J8?i??)??y? 1]K]#C#+#+C=ssCSC?
b??b???? ??c      #   ?   x??ҿ
?0????@?}????&?&ı? ????hC? ^?@??%?H5?K!a?Ȉ??&,???f??@?c9?Ot+???'???ޞ?׼5G?????V95???Iѵ_tȳ.?u??:qt?xxvE?ܻӵN??]????Y3???A??h2?@љ????.myZ?xMN?CBx?S??             x?????? ? ?      !      x?????? ? ?      &      x?????? ? ?         ?   x?3405?t? ??(J?LL????4202?5??50R04?2??24?373361&??+F??? ??@      +      x?????? ? ?     