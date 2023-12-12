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

        int[] coorCarre = Sudoku.debCarre(3,ligne,colonne);
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
            for (int colonne=0; colonne<modif[ligne].length;colonne++){
                modif[modif.length-ligne-1][colonne] = base[ligne][colonne];
            }
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
        for (int c1 = 0; c1<modif.length;c1++){
            modif[l2][c1] = base[l1][c1];
        }
        for (int c2 = 0; c2<modif.length;c2++) {
            modif[l1][c2] = base[l2][c2];
        }


        for (int c3 = 0; c3<modif.length;c3++){
            base[l1][c3] = modif[l1][c3];
        }
        for (int c4 = 0; c4<modif.length;c4++) {
            base[l2][c4] = modif[l2][c4];
        }
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
     *action: demande en entrée un nombre de trou minimal et une grille de sudoku complete
     * qu'il va rendre incomplete avec un niveau de difficultées facile . Retourne le nombre de trou final
     */
    public static int grilleFacile(int[][] grille, int nbTrous){
        boolean aTrouver;
        boolean[][][] valPoss= new boolean[9][9][10];
        int[][] mat= new int[9][9], nbValPoss=new int[9][9];
        SudokuBase.copieMatrice(grille,mat);
        SudokuBase.initGrilleIncomplete(nbTrous,mat,grille);
        SudokuBase.initPleines(grille,valPoss,nbValPoss);
        SudokuBase.initPossibles(grille,valPoss,nbValPoss);
        do{
            aTrouver  = false;
            for (int ligne = 0; ligne < 9 && !aTrouver; ligne++) {
                for (int colonne = 0; colonne < 9 && !aTrouver; colonne++) {
                    if (grille[ligne][colonne] != 0 && nbValPoss[ligne][colonne] == 1) {
                        SudokuBase.suppValPoss(grille,ligne,colonne,valPoss,nbValPoss);
                        grille[ligne][colonne]=0;
                        aTrouver=true;
                        nbTrous++;
                        nbValPoss[ligne][colonne]--;
                    }
                }
            }

        }while (aTrouver);
        return nbTrous;
    }





    //.....................................................................
    //          fin extension 3.5
    //.....................................................................

    //.....................................................................
    //          Début extension 3.1
    //.....................................................................
    public static int tourOrdi(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss,int[][] tabTrous) {
        int malus = 0;
        int[] coor;
        coor = rechercheTrou(gOrdi, nbValPoss,tabTrous);
        int ligne = coor[0];
        int colonne = coor[1];
        System.out.println("\nc'est le tour de l'ordinateur!");


        if (nbValPoss[ligne][colonne] == 1 || nbValPoss[ligne][colonne]==2 || nbValPoss[ligne][colonne]==3) {
            boolean doitTrouver = true;
            do {
                int valeurTentative = Sudoku.uneValeur(valPossibles[ligne][colonne]);
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
                }
            }while(doitTrouver);

            //extention 3.2
        }else if(nbValPoss[ligne][colonne]==0){
            finDePartieTriche();
        }
        else {
            Ut.afficherSL("J'utilise un joker");
            Ut.afficherSL("Donne moi le résultat pour le point de coordonnées " + (ligne + 1) + " " + (colonne + 1));
            int reponse = Sudoku.saisirEntierMinMax(1, 9);

            //extention 3.2
            if(!valPossibles[ligne][colonne][reponse]){
                finDePartieTriche();
            }
            gOrdi[ligne][colonne] = reponse;
            malus++;

        }

        Ut.afficherSL("l'ordinateur met " + gOrdi[ligne][colonne] + " aux coordonnées suivante : " + (ligne + 1) + " " + (colonne + 1) + "\nvoici sa grille après avoir jouée");
        Sudoku.afficheGrille(3, gOrdi);
        Sudoku.suppValPoss(gOrdi, ligne, colonne, valPossibles, nbValPoss);
        return malus;
    }

    public static boolean confirmationValeur(int[] coor, int valeur){
        System.out.println("l'ordinateur veut jouer la valeur "+valeur+" aux coordonée suivantes : "+coor[0]+" "+coor[1]);
        System.out.println("si la valeur proposer est correcte veuillez saisir 0 sinon saisir 1");
        return SudokuBase.saisirEntierMinMax(0,1)==0?true:false;
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

    public static int[][] gestionTrous(int[][] gOrdi, int[][] nbValPoss){
        int[][] tabTrous=new int[2][81];
        int[] coor=new int[2];
        for(int ligne=0;ligne<9;ligne++){
            for(int colonne=0;colonne<9;colonne++){
                if(gOrdi[ligne][colonne]==0 && nbValPoss[ligne][colonne]==1){
                    empiler(tabTrous, ligne, colonne);
                }
            }
        }
        return tabTrous;
    }

    public static int[] rechercheTrou(int[][] gOrdi,int[][] nbValPoss, int[][] tabTrous){
        int[] coor = new int[2];
        boolean trouver=true;
        if(tabTrous[0][0]>0){
            coor=depiler(tabTrous);
        }
        else{
            tabTrous=gestionTrous(gOrdi,nbValPoss);
            if(tabTrous[0][0]<1){
                for(int ligne=0; ligne<9 && trouver;ligne++){
                    for(int colonne=0; colonne<9 && trouver;colonne++){
                        if(gOrdi[ligne][colonne]==0){
                            coor[0]=ligne;
                            coor[1]=colonne;
                            trouver=false;
                        }
                    }
                }
            }
            else{
                coor=depiler(tabTrous);
            }
        }
        return coor;
    }
    

    //.....................................................................
    //          fin extension 3.3
    //.....................................................................


    public static void main(String[] args) {

    }
}
