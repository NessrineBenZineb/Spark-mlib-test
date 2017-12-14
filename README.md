# Analyse des données avec Spark Mlib (Machine learning : Approche supervisée)


#Installation

Spark
Cloudera
Jdk 1.8
Eclipse ou autre ide

# Architecture du projet

Voici l'architecture du notre projet:

 
- Classe du traitement contenant le code java de chaque fonctionalité demandée.
- GettingStarted :  Une classe qui contient le web service à exécuter selon l'id du tratement.


# Importation des données sur mlab 

Il suffit juste d'exécuter cette commande:
  &nbsp;    
   ```sh
   
mongoimport -h dchemin de la base de données -d database -c collection -u <user> -p <password> --file <input file>

   ```
   
# Base de connaissances

On fait une base de connaissances sur laquelle va se faire l'analyse.

# Implémentation et exécution

On écrit les classes du traitement selon nos besoins.
On fait quelques configuration nécessaires.
On lance la classe GettingStarted.