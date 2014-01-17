TopUV
=====

========================
Projet IF26 - TopUVs

Projet réalisé dans le cadre de l'UV IF26 (Conception sécurisée d'applications : Web Mobile et Smartphones).
L'objectif de celui-ci a été la création d'une application Androïd pour la consultation et "l'évaluation" des UVs enseignées au sein de l'UTT.
Les principales fonctionnalités et les différents étapes de conception sont détaillées au sein du Wiki.


Installer Android Studio
========================
http://developer.android.com/sdk/installing/studio.html

Une fois installé, rechercher et appliquer les dernière mises à jour.

Vérifier si il y a des mises à jour disponibles à chaque démarrage.

Installer un émulateur
======================
L'émulateur fournit par android studio étant gourmant en ressource et super lent, il existe un outil nommé genymotion qui émule facilement et beaucoup plus rapidement les supports android.

1. Télécharger et installer VirtualBox
   dispobile à cette adresse  https://www.virtualbox.org/wiki/Downloads

2. Télécharger et installer genymotion
   disponible à cette adresse https://shop.genymotion.com/index.php?controller=order-opc
   Il est par ailleurs nécessaire de s'enregistrer pour pouvoir le télécharger.

3. Configurer genymotion
   Une fois installé, lancer genymotion. Lors de la première utilisation, il va vous demandr de télécharger un ou plusieurs modèles de terminaux virtuels selon vos besoins. téléchargez-en un ou plusieurs. Puis une fois terminé, lancer l'émulation. Lors du premier lancment, genymotion va demander le chemin du sdk android sur votre système, indiquez-lui et le tour est joué. Si une erreur survient, vérifiez bien que vous avec correctement installé virtualBox et si nécessaire le démarrer une fois.

4. Lancer son appli android sur genymotion.
   Télécharger et installer le plugin genymotion sur android studio dans preferences, plugins. Une fois le plugin installé, relancer android studio. Ensuite lancer un émulateur avec genymotion, et lorsque vous exécutez votre projet sur android studio, l'émultauer est automatiquement repéré par android studio.
