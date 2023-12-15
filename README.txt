Mattéo Benhalima a participé à environ 60% du projet
Oussama Daoudi a produit 40% du projet

Le fichier SudokuBase contient la version de base du jeu , pour y jouer il suffit d'executé ce fichier
Le fichier Extensions contient le version avec extention du jeu , poour y jouer il suffit d'executée ce fichier
pour chaque fonction ajoutée dans ce fichier des indications sur celles-ci sont indiquée
le fichier grille1.txt qui contient une grille de sudoku complete

Nous allons maintenant entrer plus en détail dans la partie extensions du projet:

Nous avons réalisée un total de 7 extensions, la manquante étant la 3.6

Extension 3.1 : 
Pour cette extension nous avons calculé que l’ordinateur devait tenter ce qui lui donner plus de chance de gagner. À partir de 3 valeurs possibles la question se pose car selon les calculs l’ordinateur aura autant de chance de prendre une pénalité que de trouver la bonne réponse. Donc à partir de ce moment il faut simplement décider si l’ordinateur est « joueur » ou pas et nous avons décidé qui le sera donc il va tenter sa chance lorsque il y a au maximum 3 valeurs possibles. Pour cela il suffisait de modifier un peu la fonction tourOrdi et aussi de vérifier si la valeur qu’a joué l’ordinateur est bien juste.

Extension 3.2 :
pour l'Extension 3.2 la stratégie globale est:
	pour la partie initGrille, de faire des fonctions qui verifie qu'il n'y a pas de doublons sur les lignes colonnes et carrée, en raison de la non-utilisations de initGrilleIncomplette, nous avons décider de faire les vérifications anti-doublon sur son homologue Fichier.
	pour la partie en cours de partie, de crée une fonction qui arrête la partie et qui est appélée des qu'un cas de tricherie selon les consignes est détectée.

Extension 3.3 :
Ici nous avons décidé de décomposer le problème et donc de créer comme un système de pile donc avec le principe LIFO ( Last In First Out ). Nous avons donc créer une fonction empiler qui consiste à mettre dans tabTrous les trous évidents pour les stocker à la manière d’une pile et nous avons ensuite créer une fonction depiler qui elle va servir à retirer les coordonnées du dernier trou évident qui est entré dans le tableau tabTrous. Par la suite nous avons créer une fonction InitTrous qui va servir à remplir une première fois tabTrous avec tout les trous évidents qui sont disponibles pour le moment. Ensuite l’actualisation des trous évidents se fait dans suppValPoss.

Extension 3.4 :
Pour l'extension 3.4 la stratégie globale est d'avoir 4 fonctions pour les différents mélanges la grille et finalement une 5eme qui de maniere aléatoires appliques ces mélanges

Extension 3.5:
pour l'extension 3.5 la stratégie globale est de prendre une grille pleine et d'initialisée une matrice valPossibles et nbValposs a la maniere de base a une exception près : on retire pas a une case la possibilitée d'être elle même , ensuite on regarde les cases qui peuvent devenir des trous évident et on les transforme 1 par 1 de telle manière que les autres cases de la meme lignes ,colonnes , carré voyent leurs valeurs possibles augmentée si nécessaires. 

Extension 3.7 :
Pour cette extension nous avons pensé que sur une même ligne i, si les colonnes j1 et j2 ont la même pair de valeur possibles cela signifie que ce sont les seules cases de la ligne qui peuvent contenir ces deux valeurs en particuliers. Donc nous pouvons éliminer ces valeurs là pour toute la ligne i ( sauf du coup sur les colonnes j1 et j2). Cela va réduire les possibilités des autres cases et donc l’ordinateur facilite grandement sa résolution du Sudoku. Ce raisonnement s’applique également sur les colonnes et les carrés comme cela est dit dans l’énoncé c’est pourquoi nous avons fait trois fonctions qui vont enlever les valeurs possibles en suivant ce raisonnement dans les lignes, colonnes et carrés. 
Justification du raisonnement : Commençons par le cas de la ligne. Supposons donc une ligne i et trois cases j1,j2 et j3. Si j1 et j2 ont la même pair de valeur commune cela veut dire que j3 ne pourra pas avoir ces valeurs car cela va à l’encontre du principe même du Sudoku. Si par exemple j1 et j2 ont pour valeur commune { 4,5 } alors j3 ne pourrait avoir aucune de ces deux valeurs car sinon il y aurait une répétition d’un même chiffre et cela est interdit dans le Sudoku. Par conséquent ce principe va s’appliquer pour les carrés et pour les colonnes avec la même logique.

Extension 3.8 : 
Pour l'extension 3.8 la stratégie globale est celles décrite dans l'énoncée; la seule différence avec celle-ci et que en cas d'echec en ne regarde pas que le trou renvoyer par la fonction de recherche mais bien toute la grille a la recherche d'un trou avec 0 valeurs possibles




Mode d'emploi : 
suivre les instructions apparaissants à l'écran

petit détail à prendre en note : la methode saisirGrilleIncompleteFichier est celle qui est sélectionnée pour jouer une partie peut importe la version.
