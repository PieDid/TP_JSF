/********************************************************************/
/******************* TP GESTION COMPTES *****************************/
/********************************************************************/


# création BDD comptes
create database if not exists bdd_gestion_comptes;
drop table comptes;
drop table clients;
drop table conseillers;

# création de la table conseiller
create table if not exists conseillers( id_conseiller integer primary key auto_increment,
									   nom varchar(100),
                                       prenom varchar (100),
                                       mail varchar(100),
                                       mot_de_passe varchar(100)
									); 

# création de la table clients
create table if not exists clients(
									id_client integer auto_increment,
									nom varchar(100),
                                    prenom varchar(100),
                                    adresse varchar(300),
                                    codePostal varchar(100),
                                    ville varchar(100),
                                    telephone varchar(100),
                                    conseiller_id integer,
                                    constraint pk_client primary key (id_client),
                                    constraint fk_clients_conseillers foreign key (conseiller_id) references conseillers(id_conseiller)
								);
                                
# création de la table comptes
create table if not exists comptes(
									id_compte integer auto_increment,
                                    typeCompte varchar(100),
                                    solde double,
                                    taux double,
                                    decouvert double,
                                    client_id integer,
                                    constraint pk_compte primary key (id_compte),
                                    constraint fk_comptes_clients foreign key (client_id) references clients(id_client)
								);
                                

# insertion de conseillers
insert into conseillers (nom, prenom, mail, mot_de_passe)
values ('Juste','Leblanc','leblanc.juste@gmail.com','1234!'),
	   ('Seize','Louis','louisxvi.leroicestmoi@gmail.com','1789!');                          
                                    
# insertion de quelques clients
insert into clients (nom, prenom, adresse, codePostal, ville, telephone, conseiller_id)
values ('Jeanne','Jolicoeur','71, Rue St Ferreol','92360','MEUDON-LA-FORET','01 47 42 62 22','1'),
	   ('Vick','Giguare','17, rue Pierre De Coubertin','31300','TOULOUSE','05 90 11 36 36','1'),
	   ('Obi-Wan','Kenobi','1, rue du seul espoir','05040','TATOOINE','22 44 66 88 00','2');

# insertion de quelques comptes 
insert into comptes(typeCompte, solde, taux, decouvert, client_id)
values ('Courant',1200,null,500,1),('Epargne',3000,0.03,null,1),
	   ('Courant',400,null,150,2),('Epargne',6200,0.01,null,2),
       ('Courant',2400,null,50,3),('Epargne',15000,0.06,null,3);
       


