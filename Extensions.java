import java.util.Random;

public class Extensions {


    //.....................................................................
    //          extension 3.2
    //.....................................................................

    /*
    * actions: regarde si il ya plusières fois le meme nombre dans la ligne de gOrdi,
    * revoie vrai si il y en au moins 2 identiques sinon revoie faux
    */
    public static boolean testTricheLigne(int[] gOrdi){
        int nbvaleur;
        for (int tour=0; tour<gOrdi.length-1;tour++){
            nbvaleur = 0;
            for (int colonne = tour; colonne<gOrdi.length; colonne++ ){
                if (gOrdi[tour] == gOrdi[colonne]){
                    nbvaleur++;
                }
            }
            if (nbvaleur>1){
                return true;
            }
        }
        return false;
    }


    /*
     * actions: regarde si il ya plusières fois le meme nombre dans la colonne de gOrdi,
     * revoie vrai si il y en au moins 2 identiques sinon revoie faux
     */
    public static boolean testTricheColonne(int[][] gOrdi,int colonne){
        int nbvaleur;
        for (int tour=0; tour<gOrdi.length-1;tour++){
            nbvaleur = 0;
            for (int ligne = tour; ligne<gOrdi.length; ligne++ ){
                if (gOrdi[tour][colonne] == gOrdi[ligne][colonne]){
                    nbvaleur++;
                }
            }
            if (nbvaleur>1){
                return true;
            }
        }
        return false;
    }

    /*
     * Stratégie : mettre la grille 3*3 dans un 1*9 puis appeler testTricheLigne
     * actions: regarde si il ya plusières fois le meme nombre dans le carre 3*3,
     * contenant les coordonées entrées en paramètres, de gOrdi,
     * revoie vrai si il y en au moins 2 identiques sinon revoie faux
     */
    public static boolean testTricheCarre(int[][] gOrdi,int ligne,int colonne){
        int rang=0;
        int[] tab = new int[9];

        int[] coorCarre = SudokuBase.debCarre(3,ligne,colonne);
        for (int i = coorCarre[0]; i < coorCarre[0] + 3; i++) {
            for (int j = coorCarre[1]; j < coorCarre[1] + 3; j++) {
                tab[rang] = gOrdi[i][j];
                rang++;
            }
        }
        return testTricheLigne(tab);
    }

    /*
     * met fin a la partie en disant que l'humain a tricher
     */
        public static void finDePartieTriche(){
            System.out.println("Vous avez trichée, le gagnant est l'ordinateur");
            System.exit(0);
        }
    //.....................................................................
    //          fin extension 3.2
    //.....................................................................

    //.....................................................................
    //          extension 3.4
    //.....................................................................


    /*
     * action : modifie les 2 grilles carré IDENTIQUES en leur fesant unr rotation de 90°
     * dans le sens anti-horaire
     */
    public static void rotationGrille(int[][] base,int[][] modif){
            for (int ligne = 0; ligne<modif.length;ligne++){
                for (int colonne=0; colonne<modif[ligne].length;colonne++){
                    modif[modif.length-colonne-1][ligne] = base[ligne][colonne];
                }
            }
            SudokuBase.copieMatrice(modif,base);
    }


    /*
     *action : modifie les 2 grilles carré IDENTIQUES par rapport a leur symétrie centrale de l'axe horizontale
     */
    public static void symetrieCentrale(int[][] base,int[][] modif){
        for (int ligne = 0; ligne<modif.length;ligne++){
            System.arraycopy(base[ligne], 0, modif[modif.length - ligne - 1], 0, modif[ligne].length);
        }
        SudokuBase.copieMatrice(modif,base);
    }

    /*
     *action : modifie les 2 grilles carré IDENTIQUES par rapport a leur diagonale principale
     */
    public static void symetrieDiagonale(int[][] base,int[][] modif){
        for (int ligne = 0; ligne<modif.length;ligne++){
            for (int colonne=0; colonne<modif[ligne].length;colonne++){
                modif[colonne][ligne] = base[ligne][colonne];
            }
        }
        SudokuBase.copieMatrice(modif,base);
    }


    /*
     *action : echange les 2 lignes entrées en paramètres des matrices IDENTIQUES
     *
     * en echangera en premier dans modif puis copie des resultats dans base
     */
    public static void echangeLigne(int[][] base,int[][] modif,int l1,int l2){
        System.arraycopy(base[l1], 0, modif[l2], 0, modif.length);
        System.arraycopy(base[l2], 0, modif[l1], 0, modif.length);


        System.arraycopy(modif[l1], 0, base[l1], 0, modif.length);
        System.arraycopy(modif[l2], 0, base[l2], 0, modif.length);
    }


    /*
     *action : mélange la grille passée en paramettre en appelant au hasard les autres fonctions
     * le int demander en paramètre definit le nombre minimal de fonctions appelée (le max etant de 3 suplémentaire)
     *
     * on decide que l'on ne peut pas appelée la même fonction de maniere consécutive
     */
    public static void melangeGrille(int[][] grille,int min){
        Random random = new Random();
        int[][] grille2=new int[9][9];
        SudokuBase.copieMatrice(grille,grille2);

        int actuelle;
        int precedant=-1; // valeur bidon

        int ligne1;
        int ligne2;
        int carre;

        int tour = random.nextInt(3) +min + 1;

        do{
            // gestion du choix aléatoire de la fonction

            do{
                actuelle= random.nextInt(4);
            }while (actuelle==precedant);
            precedant=actuelle;

            //appel des différentes fonctions

            if(actuelle==0){
                rotationGrille(grille2,grille);
            }
            else if(actuelle==1){
                symetrieCentrale(grille2,grille);
            }
            else if(actuelle==2){
                symetrieDiagonale(grille2,grille);
            }
            else{
                // selection de 2 lignes au hasard qui sont dans le meme carre

                carre = random.nextInt(3);
                ligne1 = random.nextInt(3);
                ligne2=  (random.nextInt(2) +1 +ligne1)%3;
                ligne1 += carre*3;
                ligne2 += carre*3;

                echangeLigne(grille2,grille,ligne1,ligne2);

            }

            tour--;
        }while (tour>0);
    }
    //.....................................................................
    //          fin extension 3.4
    //.....................................................................

    //.....................................................................
    //          extension 3.5
    //.....................................................................


    /*
     *action: demande en entrée une grille de sudoku complete0
     * qu'il va rendre incomplete avec un niveau de difficultées facile . Retourne le nombre de trou final
     */
    public static int grilleFacile(int[][] grille){
        int nbTrous=1;
        boolean aTrouver;
        boolean[][][] valPoss= new boolean[9][9][10];
        int[][] mat= new int[9][9], nbValPoss=new int[9][9];

        SudokuBase.copieMatrice(grille,mat);
        SudokuBase.afficheGrille(3,grille);
        test.affiche(nbValPoss);
        System.out.println();

        initPossible(grille,valPoss,nbValPoss);
        test.affiche2(valPoss);
        test.affiche(nbValPoss);
        SudokuBase.initGrilleIncomplete(nbTrous,mat,grille);


        do{
            aTrouver  = false;
            for (int ligne = 0; ligne < 9 && !aTrouver; ligne++) {
                for (int colonne = 0; colonne < 9 && !aTrouver; colonne++) {
                    if (grille[ligne][colonne] != 0 && nbValPoss[ligne][colonne] == 1) {
                        actuValPoss(grille,ligne,colonne,valPoss,nbValPoss);
                        aTrouver=true;
                        nbTrous++;
                    }
                }
            }

        }while (aTrouver);
        System.out.println();
        test.affiche(nbValPoss);
        System.out.println("grille finale");
        test.affiche(grille);
        return nbTrous;
    }


    /*
     * action :  demande a l'utilisateur la difficultées de sa partie puis transforme ghumain en grille incomplete
     * pouvant être complété en gOrdi. renvoit le nombre de trous de la partie
     *
     */
    public static int partieFacile(int[][] gsecret,int[][] gHumain){
        int nbTrous;
        System.out.println("veuillez choisir un niveau de difficultées :");
        System.out.println("si vous souhaitez un grille de niveaux facile entrez \"0\" sinon entrez \"1\"");
        if(SudokuBase.saisirEntierMinMax(0,1)==0){
            //grille de niveaux facile
            SudokuBase.copieMatrice(gsecret,gHumain);
            nbTrous = grilleFacile(gHumain);
            System.out.println("le nombre de trous de la partie est "+nbTrous);
        }else {
            // grille classique
            System.out.println("veuillez saisir le nombre de Trous pour votre partie");
            nbTrous = SudokuBase.saisirEntierMinMax(0,81);
            SudokuBase.initGrilleIncomplete(nbTrous,gsecret,gHumain);
        }
        return nbTrous;
    }


    /*
     * objectif : faire en sorte de savoir si valPossibles contient val
     * apres le passage a 0 d'une case qui vaut val
     *
     * action : de la meme maniere que suppValPoss, elle parcourd gOrdi afin de regarder si un val est présent sur
     * la ligne colollone et le carrée. elle renvoit true si c'est un val possible ( elle ne l'a pas trouver) sinon false
     */
    public static boolean ajoutValPoss(int[][] gOrdi, int i, int j,int val) {
        // on commence par la ligne, puis la colonne puis le carre
        for (int colonne = 0; colonne < gOrdi[i].length; colonne++) {
            if(gOrdi[i][colonne]==val) return false;
        }
        // il n'y a pas val sur la meme ligne que le coor
        for (int ligne = 0; ligne < gOrdi[i].length; ligne++) {
            if(gOrdi[ligne][j]==val) return false;
        }
        // il n'y a pas val sur la même colonne que les coor
        int[] tabCarre = SudokuBase.debCarre(3, i, j);
        int ligneCarre = tabCarre[0];
        int colonneCarre = tabCarre[1];
        for (int ligne = ligneCarre; ligne < ligneCarre + 3; ligne++) {
            for (int colonne = colonneCarre; colonne < colonneCarre + 3; colonne++) {
                if(gOrdi[ligne][colonne]==val) return false;
            }
        }
        //il n'y a pas val dans le même carré que les coor
        return true;


    }  // fin suppValPoss

    public static void actuValPoss(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        int valeur = gOrdi[i][j];
        gOrdi[i][j]=0;
        // on commence par la ligne, puis la colonne puis le carre
        for (int colonne = 0; colonne < gOrdi[i].length; colonne++) {
            if (ajoutValPoss(gOrdi,i,colonne,valeur)) {
                valPossibles[i][colonne][valeur]=true;
                nbValPoss[i][colonne]++;
            }
        }
        for (int ligne = 0; ligne < gOrdi[i].length; ligne++) {
            if (ajoutValPoss(gOrdi,ligne,j,valeur)) {
                valPossibles[ligne][j][valeur]=true;
                nbValPoss[ligne][j]++;
            }
        }
        int[] tabCarre = SudokuBase.debCarre(3, i, j);
        int ligneCarre = tabCarre[0];
        int colonneCarre = tabCarre[1];
        for (int ligne = ligneCarre; ligne < ligneCarre + 3; ligne++) {
            for (int colonne = colonneCarre; colonne < colonneCarre + 3; colonne++) {
                if (ajoutValPoss(gOrdi,ligne,colonne,valeur)) {
                    valPossibles[ligne][colonne][valeur]=true;
                    nbValPoss[ligne][colonne]++;
                }
            }
        }


    }  // fin suppValPoss
    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action :      met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     * et leur nombre dans nbValPoss
     */
    public static void initPossible(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[i].length; j++) {
                if(gOrdi[i][j]!=0){
                    valPossibles[i][j][gOrdi[i][j]]= false;
                }
                nbValPoss[i][j]=1;
                valPossibles[i][j]=SudokuBase.ensPlein(9);
            }
        }
    }
    // fin initPossibles
    //.....................................................................
    //          fin extension 3.5
    //.....................................................................

    //.....................................................................
    //          Début extension 3.1
    //.....................................................................
    public static int tourOrdi(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss,int[][] tabTrous) {
        int malus = 0;
        int[] coor;
        coor = rechercheTrou(gOrdi,tabTrous);
        int ligne = coor[0];
        int colonne = coor[1];
        System.out.println("\nc'est le tour de l'ordinateur!");


        if (nbValPoss[ligne][colonne] == 1 || nbValPoss[ligne][colonne]==2 || nbValPoss[ligne][colonne]==3) {
            boolean doitTrouver = true;
            do {
                int valeurTentative = SudokuBase.uneValeur(valPossibles[ligne][colonne]);
                if(confirmationValeur(coor,valeurTentative)){
                    gOrdi[ligne][colonne] = valeurTentative;
                    doitTrouver=false;
                }else {
                    valPossibles[ligne][colonne][valeurTentative] = false;
                    malus++;
                    
                    //extension 3.2
                    if(nbValPoss[ligne][colonne] == 1){
                        finDePartieTriche();
                    }
                    nbValPoss[ligne][colonne]--;
                }
            }while(doitTrouver);

            //extention 3.2
        }else if(nbValPoss[ligne][colonne]==0){
            finDePartieTriche();
        }
        else {
            Ut.afficherSL("J'utilise un joker");
            Ut.afficherSL("Donne moi le résultat pour le point de coordonnées " + (ligne + 1) + " " + (colonne + 1));
            int reponse = SudokuBase.saisirEntierMinMax(1, 9);

            //extention 3.2
            if(!valPossibles[ligne][colonne][reponse]){
                finDePartieTriche();
            }
            gOrdi[ligne][colonne] = reponse;
            malus++;

        }

        Ut.afficherSL("l'ordinateur met " + gOrdi[ligne][colonne] + " aux coordonnées suivante : " + (ligne + 1) + " " + (colonne + 1) + "\nvoici sa grille après avoir jouée");
        SudokuBase.afficheGrille(3, gOrdi);
        suppValPoss(gOrdi, ligne, colonne, valPossibles, nbValPoss,tabTrous);
        return malus;
    }

    public static boolean confirmationValeur(int[] coor, int valeur){
        System.out.println("l'ordinateur veut jouer la valeur "+valeur+" aux coordonée suivantes : "+(coor[0]+1)+" "+(coor[1]+1));
        System.out.println("si la valeur proposer est correcte veuillez saisir 0 sinon saisir 1");
        return SudokuBase.saisirEntierMinMax(0, 1) == 0;
    }
    //.....................................................................
    //          fin extension 3.1
    //.....................................................................

    //.....................................................................
    //          Début extension 3.3
    //.....................................................................
    public static void empiler(int[][] tabTrous, int x, int y){
        tabTrous[0][0]++;
        int card=tabTrous[0][0];
        tabTrous[card-1][0]=x;
        tabTrous[card-1][1]=y;
    }

    public static int[] depiler(int[][] tabTrous){
        int card=tabTrous[0][0];
        int[] coor=new int[2];
        coor[0]=tabTrous[card-1][0];
        coor[1]=tabTrous[card-1][1];
        tabTrous[0][0]--;
        tabTrous[card-1][0]=0;
        tabTrous[card-1][1]=0;
        return coor;
    }

    public static void initTrous(int[][] gOrdi, int[][] nbValPoss, int[][] tabTrous){
        int[] coor=new int[2];
        for(int ligne=0;ligne<9;ligne++){
            for(int colonne=0;colonne<9;colonne++){
                if(gOrdi[ligne][colonne]==0 && nbValPoss[ligne][colonne]==1){
                    empiler(tabTrous, ligne, colonne);
                }
            }
        }
    }

    public static int[] rechercheTrou(int[][] gOrdi, int[][] tabTrous){
        int[] coor = new int[2];
        boolean trouver=true;
        if(tabTrous[0][0]>0){
            return depiler(tabTrous);
        }
        else{
            for(int ligne=0; ligne<9 && trouver;ligne++){
                for(int colonne=0; colonne<9 && trouver;colonne++){
                    if(gOrdi[ligne][colonne]==0){
                        coor[0]=ligne;
                        coor[1]=colonne;
                        return coor;
                    }
                }
            }
        }
        return coor;
    }
    

    //.....................................................................
    //          fin extension 3.3
    //.....................................................................
    //.....................................................................
    //          fonction générales
    //.....................................................................



    public static int initPartie(int[][] gSecret, int[][] gHumain, int[][] gOrdi,
                                 boolean[][][] valPossibles, int[][] nbValPoss,int[][] tabTrous) {
        SudokuBase.initGrilleComplete(gSecret);
        melangeGrille(gSecret,14);
        int nbTrous = partieFacile(gSecret,gHumain);
        initTrous(gHumain,nbValPoss,tabTrous);
        System.out.println("veuillez saisir le nom de votre fichier contenant votre grille");
        String fic = Ut.saisirChaine();

        SudokuBase.saisirGrilleIncompleteFichier(nbTrous, gOrdi,fic);
        SudokuBase.initPossibles(gOrdi, valPossibles, nbValPoss);

        return nbTrous;
    } // fin initPartie
    public static int partie() {
        int[][] gSecret = new int[9][9];
        int[][] gHumain = new int[9][9];
        int[][] gOrdi = new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];
        int[][] nbValPoss = new int[9][9];
        int[][] tabTrous=new int[2][82];


        int nbTrous = initPartie(gSecret, gHumain, gOrdi, valPossibles, nbValPoss,tabTrous);

        int pointHumain = 0;
        int pointOrdi = 0;
        for (; nbTrous > 0; nbTrous--) {
            pointHumain += SudokuBase.tourHumain(gSecret, gHumain);
            System.out.println("Voici le nombre de point que vous possédez : " + pointHumain);

            pointOrdi += tourOrdi(gOrdi, valPossibles, nbValPoss,tabTrous);
            System.out.println("Voici le nombre de point que l'ordinateur possède : " + pointOrdi);

        }
        if (pointHumain < pointOrdi) {
            return 1;
        } else if (pointHumain == pointOrdi) {
            return 0;
        } else {
            return 2;
        }
    }  // fin partie

    public static void suppValPoss(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss, int[][] tabTrous) {
        // on commence par la ligne, puis la colonne puis le carre
        for (int colonne = 0; colonne < gOrdi[i].length; colonne++) {
            if (SudokuBase.supprime(valPossibles[i][colonne], gOrdi[i][j])) {
                nbValPoss[i][colonne]--;
                if(gOrdi[i][colonne]==0 && nbValPoss[i][colonne]==1){
                    empiler(tabTrous,i,colonne);
                }
            }
        }
        for (int ligne = 0; ligne < gOrdi[i].length; ligne++) {
            if (SudokuBase.supprime(valPossibles[ligne][j], gOrdi[i][j])) {
                nbValPoss[ligne][j]--;
                if(gOrdi[ligne][j]==0 && nbValPoss[ligne][j]==1){
                    empiler(tabTrous,ligne,j);
                }
            }
        }
        int[] tabCarre = SudokuBase.debCarre(3, i, j);
        int ligneCarre = tabCarre[0];
        int colonneCarre = tabCarre[1];
        for (int ligne = ligneCarre; ligne < ligneCarre + 3; ligne++) {
            for (int colonne = colonneCarre; colonne < colonneCarre + 3; colonne++) {
                if (SudokuBase.supprime(valPossibles[ligne][colonne], gOrdi[i][j])) {
                    nbValPoss[ligne][colonne]--;
                    if(gOrdi[ligne][colonne]==0 && nbValPoss[ligne][colonne]==1){
                        empiler(tabTrous,ligne,colonne);
                    }
                }
            }
        }


    }  // fin suppValPoss

    //.....................................................................
    //          fin fonction générales
    //.....................................................................


    public static void main(String[] args) {
        int gagnant = partie();
        if (gagnant == 0) {
            Ut.afficherSL("\nC'est un match nul !");
        } else if (gagnant == 1) {
            Ut.afficherSL("\nLe gagnant est le joueur humain !");
        } else {
            Ut.afficherSL("\nLe gagnant est l'ordinateur !");
        }


    }
}
