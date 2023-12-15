Mattéo Benhalima à participer a environ 50% du projet
Oussama Daoudi a produit 50% du projet

Le fichier SudokuBase contient la version de base du jeu , pour y jouer il suffit d'executé ce fichier
Le fichier Extensions contient le version avec extention du jeu , poour y jouer il suffit d'executée ce fichier
pour chaque fonction ajoutée dans ce fichier des indications sur celles-ci sont indiquée
le fichier grille1.txt qui contient une grille de sudoku complete

Nous allons maintenant entré plus en détail dans la partie extentions du projet:

Nous avons réalisée un total de 7 extentions, la manquante étant la 3.6

pour l'extention 3.2 la stratégie globale est:
	pour la partie initGrille, de faire des fonctions qui verifie qu'il n'y a pas de doublons sur les lignes colonnes et carrée, en raison de la non-utilisations de initGrilleIncomplette, nous avons décider de faire les vérifications anti-doublon sur son homologue Fichier.
	pour la partie en cours de partie, de crée une fonction qui arrête la partie et qui est appélée des qu'un cas de tricherie selon les consignes est détectée.

Pour l'extention 3.4 la stratégie globale est d'avoir 4 fonctions pour les différents mélanges la grille et finalement une 5eme qui de maniere aléatoires appliques ces mélanges

pour l'extention 3.5 la stratégie globale est de prendre une grille pleine et d'initialisée une matrice valPossibles et nbValposs a la maniere de base a une exception près : on retire pas a une case la possibilitée d'être elle même , ensuite on regarde les cases qui peuvent devenir des trous évident et on les transforme 1 par 1 de telle manière que les autres cases de la meme lignes ,colonnes , carré voyent leurs valeurs possibles augmentée si nécessaires. 

Pour l'extentions 3.8 la stratégie globale est celles décrite dans l'énoncée; la seule différence avec celle-ci et que en cas d'echec en ne regarde pas que le trou renvoyer par la fonction de recherche mais bien toute la grille a la recherche d'un trou avec 0 valeurs possibles




Mode d'emploi : 
suivre les instructions apparaissants a l'écran

petit détail à prendre en note : la methode saisirGrilleIncompleteFichier est celle qui est sélectionnée pour jouer une partie peut importe la version.
