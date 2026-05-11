# Rapport d'etat actuel - Application Assurance

## 1. Introduction

Ce rapport presente l'etat actuel de l'application de gestion des contrats d'assurance developpee avec Spring Boot. Il compare le projet avec le cahier de charge de l'examen "Architecture Distribuee et Middleware - ENSET 2026".

Etat technique verifie le 11/05/2026 :

- Compilation Maven : OK
- Tests Maven : OK, 1 test execute, 0 erreur
- Base de donnees : H2 en memoire
- Backend : Spring Boot, Spring Data JPA, Spring MVC REST, Spring Security JWT
- Documentation API : Swagger / OpenAPI ajoute
- Frontend Angular : pas encore realise

## 2. Rappel du cahier de charge

L'application doit permettre de gerer des contrats d'assurance avec les regles suivantes :

- Un client peut souscrire plusieurs contrats d'assurance.
- Il existe trois types de contrats : automobile, habitation et sante.
- Un contrat peut avoir plusieurs paiements.
- Les donnees doivent etre stockees dans H2 ou MySQL.
- L'application doit contenir une couche DAO, une couche service, une couche web REST.
- La securite doit etre basee sur Spring Security et JWT.
- Un frontend Angular doit etre propose.

## 3. Architecture technique du projet

L'architecture actuelle est organisee en couches :

- Couche entities : classes JPA representant le modele metier.
- Couche repositories : interfaces Spring Data JPA.
- Couche DTOs : objets de transfert utilises par l'API.
- Couche mappers : conversion entre entites et DTOs avec MapStruct.
- Couche services : interface metier et implementation transactionnelle.
- Couche web : REST Controllers.
- Couche security : JWT, filtre d'authentification, service utilisateur et configuration Spring Security.

Technologies utilisees :

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Spring Web MVC
- Spring Security
- JWT avec jjwt
- Lombok
- MapStruct
- Swagger / OpenAPI avec springdoc

## 4. Diagramme de classes textuel

Client :

- id
- nom
- email
- contrats

ContratAssurance :

- id
- dateSouscription
- statut
- dateValidation
- montantCotisation
- dureeContrat
- tauxCouverture
- client
- paiements

ContratAutomobile herite de ContratAssurance :

- numeroImmatriculation
- marqueVehicule
- modeleVehicule

ContratHabitation herite de ContratAssurance :

- typeLogement
- adresse
- superficie

ContratSante herite de ContratAssurance :

- niveauCouverture
- nombrePersonnesCouvertes

Paiement :

- id
- date
- montant
- type
- contrat

AppUser :

- id
- username
- password
- roles

AppRole :

- id
- roleName

Relations principales :

- Client 1..* ContratAssurance
- ContratAssurance 1..* Paiement
- AppUser *..* AppRole
- ContratAutomobile, ContratHabitation et ContratSante heritent de ContratAssurance

## 5. Reponses aux questions du cahier de charge

### A. Livrables

1. Creer un repository Github avec le nom demande.

Etat actuel : partiellement conforme.

Le projet a un remote GitHub :

`https://github.com/majda-22/AitLamouden-Majda-Exam-JEE.git`

Le nom semble conforme a l'esprit du sujet. Il faut verifier que c'est bien le repository final a deposer.

2. Deposer le lien dans Classroom.

Etat actuel : non verifiable depuis le code.

Action restante : deposer le lien GitHub dans Classroom.

3. Commit et push chaque 20 minutes.

Etat actuel : non verifiable uniquement par le code.

Action restante : continuer a faire des commits reguliers. Il existe actuellement des fichiers modifies et non commit.

4. Rendre un rapport PDF.

Etat actuel : en cours.

Ce fichier Markdown peut servir de base au rapport PDF final. Il faudra le convertir en PDF avant le depot.

### B. Conception

5. Etablir une architecture technique du projet.

Etat actuel : conforme.

L'architecture est organisee selon les couches demandees : DAO, service, web REST et securite.

6. Etablir un diagramme de classes.

Etat actuel : partiellement conforme.

Le modele de classes existe dans le code. Le diagramme UML graphique n'est pas encore fourni. Le diagramme textuel est presente dans ce rapport, mais il faudra idealement ajouter un vrai diagramme UML dans le PDF final.

### C. Implementation

1. Creer un projet Spring Boot avec les dependances requises.

Etat actuel : globalement conforme.

Dependances presentes :

- Spring Web
- Spring Data JPA
- Spring Security
- H2
- Lombok
- MapStruct
- Swagger / OpenAPI
- JWT

Point d'attention :

- Le package actuel est `ma.enset.tonnom.assurance`.
- Le cahier de charge demande que le GroupId, ArtifactId et package de base contiennent le nom et le prenom. Le repository contient le nom, mais le package et le groupId semblent encore generiques.

2.a. Creer les entites JPA.

Etat actuel : conforme.

Entites creees :

- Client
- ContratAssurance
- ContratAutomobile
- ContratHabitation
- ContratSante
- Paiement
- AppUser
- AppRole

Les enumerations metier sont aussi presentes :

- StatutContrat
- TypeLogement
- NiveauCouverture
- TypePaiement

2.b. Creer les interfaces JPA Repository.

Etat actuel : conforme.

Repositories presents :

- ClientRepository
- ContratAssuranceRepository
- PaiementRepository
- AppUserRepository
- AppRoleRepository

2.c. Tester la couche DAO avec des donnees de test.

Etat actuel : conforme.

La classe principale alimente la base H2 avec :

- deux clients
- un contrat automobile
- un contrat habitation
- un paiement
- trois roles de securite
- un utilisateur admin par defaut

Compte admin cree :

- username : `admin`
- password : `admin123`

3. Creer une couche service avec DTOs et Mappers.

Etat actuel : conforme mais ameliorable.

Presents :

- AssuranceService
- AssuranceServiceImpl
- ClientDTO
- ContratAssuranceDTO
- PaiementDTO
- ClientMapper
- ContratMapper
- PaiementMapper

Point d'attention :

- Les DTOs de contrat couvrent seulement les attributs communs de `ContratAssurance`.
- Les champs specifiques de `ContratAutomobile`, `ContratHabitation` et `ContratSante` ne sont pas encore exposes dans les DTOs REST.

4. Creer les Web Services REST et tester avec Swagger.

Etat actuel : partiellement conforme.

Controllers presents :

- AuthController : `/api/auth/login`
- ClientController : `/api/clients`
- ContratController : `/api/contrats`
- PaiementController : `/api/paiements`

Swagger est ajoute via springdoc :

- URL prevue : `/swagger-ui.html`
- API docs : `/v3/api-docs`

Point d'attention :

- Il manque des endpoints de modification `PUT` ou `PATCH`.
- Il manque des endpoints specialises pour creer un contrat automobile, habitation ou sante avec leurs champs specifiques.
- Les endpoints de lecture sont proteges par JWT, ce qui est coherent, mais il faut tester manuellement avec un token.

5. Proposer une application frontend Angular.

Etat actuel : non realise.

Action restante :

- Creer le projet Angular.
- Ajouter login JWT.
- Ajouter pages clients, contrats, paiements.
- Ajouter gestion des roles cote interface.

6. Securiser backend et frontend avec Spring Security et JWT avec trois roles.

Etat actuel : backend partiellement conforme.

Backend :

- JWT service present.
- Filtre JWT present.
- AuthController present.
- Roles presents : `ROLE_CLIENT`, `ROLE_EMPLOYE`, `ROLE_ADMIN`.
- Admin par defaut cree automatiquement.

Autorisations actuelles :

- `/api/auth/**` public
- `/swagger-ui/**` et `/v3/api-docs/**` publics
- `/h2-console/**` public
- DELETE `/api/**` : `ROLE_ADMIN`
- POST `/api/**` : `ROLE_EMPLOYE` ou `ROLE_ADMIN`
- autres requetes : utilisateur authentifie

Point d'attention :

- `ROLE_CLIENT` n'a pas encore de regle metier specifique.
- Il manque probablement une regle explicite pour `PUT` si on ajoute la modification.
- Le frontend n'existe pas encore, donc la securite frontend n'est pas encore realisee.

7. Apporter des ameliorations additionnelles.

Etat actuel : quelques ameliorations deja presentes.

Ameliorations existantes :

- Swagger / OpenAPI.
- DTOs + MapStruct.
- JWT.
- Initialisation automatique des donnees.
- H2 Console.

Ameliorations recommandees avant le frontend :

- Ajouter des DTOs specialises pour les trois types de contrats.
- Ajouter les endpoints `PUT` pour modifier clients, contrats et paiements.
- Ajouter une gestion propre des erreurs avec `@ControllerAdvice`.
- Ajouter des validations avec `jakarta.validation`.
- Ajouter des tests REST avec MockMvc.
- Ajouter des roles de test `employe` et `client`, pas seulement admin.
- Remplacer la version Spring Boot snapshot par une version stable si possible.

## 6. Etat de compatibilite global

Le backend est bien avance et compatible avec une grande partie du cahier de charge.

Points conformes :

- Projet Spring Boot cree.
- Entites JPA principales creees.
- Heritage des contrats implemente.
- Repositories Spring Data crees.
- Initialisation DAO avec donnees de test.
- Couche service creee.
- DTOs et mappers crees.
- REST Controllers crees.
- Swagger ajoute.
- Securite JWT ajoutee.
- Trois roles crees.
- Tests Maven passent.

Points incomplets ou a corriger avant le frontend :

- Frontend Angular absent.
- DTOs et APIs des sous-types de contrats incomplets.
- Pas de vrais endpoints de modification.
- `ROLE_CLIENT` pas encore exploite fonctionnellement.
- Rapport PDF final non genere.
- Diagramme UML graphique non genere.
- Package/groupId encore generiques par rapport a l'exigence nom/prenom.

## 7. Conclusion

L'application est actuellement dans un etat backend fonctionnel et compile correctement. Elle couvre les couches principales demandees par le sujet : DAO, service, REST, securite JWT et documentation Swagger.

Avant de commencer le frontend Angular, il est recommande de finaliser les endpoints REST, surtout la gestion complete des trois types de contrats d'assurance, puis de tester l'authentification JWT avec les roles `ROLE_ADMIN`, `ROLE_EMPLOYE` et `ROLE_CLIENT`.
