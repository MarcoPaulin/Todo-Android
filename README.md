Utilisation :
Pour utiliser l'application, dans un premier temps, créer un compte grace au bouton s'inscrire, une fois le compte fait vos accéder à la page principale où vous retrouverez en haut à droite un icon outils permettant de définir la couleur de la barre en haut.
Vous retrouvez également en bas à droite un bouton permettant de créer une Todo liste, Définir le nom pour accéder à la page de la Todo, vous arriverez ainsi sur une page ou vous retrouverez en en haut au centre le nom de votre Todo, appuyer sur le bouton en bas pour définir vos premières taches.
Vous retrouverez sur votre page principale toutes vos Todo reconnaissable par leurs noms vous avez juste à cliquer dessus pour retrouver et ajouter vos taches, une connexion est demander au début de chaque lancement

Liste des fonctionnalités implémentées :
<br>
[V] Utilisation de l'Api Preference :  bouton outils dans l'item bar (homeActivity) permettant de changer la couleur de l'AppBar qui est sauvegarder avec l'api <br> préférence et récupérer dans les autre vus même quand l'application est fermé
<br>
[V] Ecriture/lecture dans un Fichier : fichier json ( fichier Todo_l, homeActivity, TodoActivity)
<br>
[ ] Utilisation de SQLite
<br>
[ ] Utilisation de Room
<br>
[V] Utilisation de Firebase : (fichier connexionPageActivity and CreateAccount) systeme de connexion 
<br>
[V] Nombre d'activités ou fragment supérieur ou égal à 3
<br>
[V] Gestion du bouton Back (message pour confirmer que l'on veut réellement quitter l'application) : message demandant si on veut se déconnecter (home activity) et message demandant
<br>
si on veut quitter l'application a la page de connexion (1 er page, connexionPageActivity)
<br>
[V] L'affichage d'une liste avec son adapter (HomeActivity avec le onclick quui ouvre une page)
<br>
[V] L'affichage d'une liste avec un custom adapter (avec gestion d’événement) : (Todo Activity avec checkboxe lier à une list)
<br>
[V] La pertinence d'utilisation des layouts (L'application doit être responsive et supporter: portrait/paysage et tablette) : (pas de bug en rotation, 
<br>l'application marche sans casse)<br>
[V] L'utilisation de d’événement améliorant l'ux (pex: swipe). Préciser : checkBoxe(TodoActivity dans la list custom<br>
, Bouton flottant(TodoActivity, HomeActivity, AppBar avec un menu <br>
[ ] La réalisation de composant graphique custom (Paint 2D, Calendrier,...) Préciser :<br>
[ ] Les taches en background (codage du démarrage d'un thread)<br>
[V] Le codage d'un menu (contextuel ou non, utilisation d'un menu en resource XML): le bouton outils sur l'AppBar apres la connexion ( homeactivity ouvre un menu (par un menu.xml dans le dossier menu )<br>
[V] L'application de pattern (Reactive programming, singleton, MVC,...) Liste : Singleton<br>
