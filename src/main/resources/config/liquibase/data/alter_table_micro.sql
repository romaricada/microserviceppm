alter table microserviceppm.besoin add column nature_prestation_id bigint;
alter table microserviceppm.ppm add column id_exercice bigint;
alter table microserviceppm.ppm_activite add column niveau varchar(255);
alter table microserviceppm.activite add column reported boolean;
alter table microserviceppm.activite add column etat_marche varchar(255);
alter table microserviceppm.mode_passation add column abreviation varchar(255);
alter table microserviceppm.exercice_budgetaire add column active boolean;
alter table microserviceppm.besoin add column used boolean;
alter table microserviceppm.etape add column ordre int unique ;
alter table microserviceppm.ligne_budgetaire add column exercice_id bigint;
alter table microserviceppm.besoin add column nombre_lot int ;
alter table microserviceppm.ppm_activite add column report boolean;
INSERT INTO microserviceppm.unite_administrative (id, libelle, abbreviation, adresse, contact, identite_responsable, deleted, created_by, last_modified_by) VALUES (
                      '1'::bigint, 'Direction Marché public'::character varying(255), 'DMP'::character varying(255),
                      'BOPDKSALA'::character varying(255), '3456'::character varying(255),
                      'COMPARORE ADDEL'::character varying(255), false::boolean,
                      'admin'::character varying(50),
                      'admin'::character varying(50)
                    )


/*pour inserer la valeur par defaut dans la table unite administrative*/
INSERT INTO microserviceppm.unite_administrative (
id, libelle, abbreviation, adresse, contact, identite_responsable, deleted, created_by, last_modified_by) VALUES (
'1'::bigint, 'Direction Marché public'::character varying(255), 'DMP'::character varying(255),
 'BOPDKSALA'::character varying(255), '3456'::character varying(255),
  'COMPARORE ADDEL'::character varying(255), false::boolean,
   'admin'::character varying(50),
    'admin'::character varying(50)
    );

alter table microserviceppm.etape_activite_ppm
	add column debut timestamp without time zone,
	add column fin timestamp without time zone;

alter table microserviceppm.besoin add column montantEstime double precision;
alter table microserviceppm.exercice_budgetaire add column elaborer boolean;
alter table microserviceppm.activite add column nom_activite varchar(255) not null ;

create table microserviceppm.user_notification (
	id bigint primary key,
	visited boolean,
	jour timestamp without time zone,
	etape_activite_ppm_id bigint references microserviceppm.etape_activite_ppm(id),
	user_id bigint,
	tache_etape_id bigint,
	etape_execution_id bigint,
	deleted boolean,
	created_by character varying,
	created_date timestamp without time zone,
	last_modified_by character varying,
	last_modified_date timestamp without time zone
);

alter table microserviceppm.calcul_delai add column mode_passation_id bigint;


