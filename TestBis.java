


public class TestBis {

    public static int initPartie_Bis(int [][] gSecret, int [][] gHumain, int [][] gOrdi,
                                 boolean[][][] valPossibles, int [][]nbValPoss){

        System.out.println("veuillez saisir le nombre de trous que vous souhaiter dans votre grille de sudoku");
        int nbTrous =SudokuBase.saisirEntierMinMax(0,81);
        SudokuBase.initPleines(gOrdi,valPossibles,nbValPoss);
        SudokuBase.initGrilleComplete(gSecret); /* Met dans gSecret une grille de Sudoku complète */
        SudokuBase.initGrilleIncomplete(nbTrous,gSecret,gHumain);/* Met dans gHumain une grille de Sudoku incomplète mais qui peut etre compléter en gSecret avec nbTrous*/
        SudokuBase.initGrilleIncomplete(nbTrous,gSecret,gOrdi);
        SudokuBase.initPossibles(gOrdi,valPossibles,nbValPoss);

        return nbTrous;
    } // fin initPartie

    public static int partie_bis(){
        int[][] gSecret=new int[9][9];
        int[][] gHumain=new int[9][9];
        int[][] gOrdi=new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];
        int [][]nbValPoss = new int[9][9];
        int nbTrous=initPartie_Bis(gSecret,gHumain,gOrdi,valPossibles,nbValPoss);
        int pointHumain=0;
        int pointOrdi=0;
        for(;nbTrous>0;nbTrous--){

            pointHumain+= SudokuBase.tourHumain(gSecret,gHumain);
            System.out.println("Vous avez "+pointHumain+" points");


            pointOrdi+=SudokuBase.tourOrdinateur(gOrdi,valPossibles,nbValPoss);
            System.out.println("L'ordinateur a "+pointOrdi+" points");

        }
        if(pointHumain<pointOrdi){
            return 1;
        } else if (pointHumain==pointOrdi) {
            return 0;
        }else {
            return 2;
        }
    }  // fin partie

    public static void afficherTabBoolean(boolean[] tab){
        System.out.print("["+tab[0]);
        for (int i=1;i<tab.length;i++){
            System.out.print(", "+tab[i]);
        }
        System.out.println("]");
    }

    public static int partie_bis_Auto(){
        int[][] gSecret=new int[9][9];
        int[][] gHumain=new int[9][9];
        int[][] gOrdi=new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];
        boolean[][][] valPossiblesHumain = new boolean[9][9][10];
        int [][]nbValPoss = new int[9][9];
        int [][]nbValPossHumain = new int[9][9];
        int nbTrous=initPartie_Bis(gSecret,gHumain,gOrdi,valPossibles,nbValPoss);
        SudokuBase.initPleines(gHumain,valPossiblesHumain,nbValPossHumain);
        SudokuBase.initPossibles(gHumain,valPossiblesHumain,nbValPossHumain);
        int pointHumain=0;
        int pointOrdi=0;
        for(;nbTrous>0;nbTrous--){

            pointHumain+= SudokuBase.tourOrdinateur(gHumain,valPossiblesHumain,nbValPossHumain);
            System.out.println("Vous avez "+pointHumain+" points");


            pointOrdi+=SudokuBase.tourOrdinateur(gOrdi,valPossibles,nbValPoss);
            System.out.println("L'ordinateur a "+pointOrdi+" points");

        }
        if(pointHumain<pointOrdi){
            return 1;
        } else if (pointHumain==pointOrdi) {
            return 0;
        }else {
            return 2;
        }
    }  // fin partie


    public static int tourOrdi(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss,int[][] tabTrous) {
        int malus = 0;
        int[] coor;
        coor = Extensions.rechercheTrou(gOrdi, tabTrous);
        int ligne = coor[0];
        int colonne = coor[1];
        System.out.println("\nc'est le tour de l'ordinateur!");

        if (gOrdi[ligne][colonne] == 0 && nbValPoss[ligne][colonne] == 1) {

            gOrdi[ligne][colonne] = SudokuBase.uneValeur(valPossibles[ligne][colonne]);
            Ut.afficherSL("l'ordinateur met " + gOrdi[ligne][colonne] + " aux coordonnées suivante : " + (ligne + 1) + " " + (colonne + 1) + "\nvoici sa grille après avoir jouée");
            SudokuBase.afficheGrille(3, gOrdi);
        } else {
            Ut.afficherSL("J'utilise un joker");
            Ut.afficherSL("Donne moi le résultat pour le point de coordonnées " + (ligne + 1) + " " + (colonne + 1));
            int reponse = SudokuBase.saisirEntierMinMax(1, 9);
            gOrdi[ligne][colonne] = reponse;
            malus++;
            Ut.afficherSL("l'ordinateur met " + reponse + " aux coordonnées suivante : " + (ligne + 1) + " " + (colonne + 1) + "\nvoici sa grille après avoir jouée");
            SudokuBase.afficheGrille(3, gOrdi);
        }
        Extensions.suppValPoss(gOrdi, ligne, colonne, valPossibles, nbValPoss,tabTrous);
        return malus;
    } // fin tourOrdinateur



    public static void main(String[] args) {
        int[][] gOrdi=new int[9][9], nbValposs=new int[9][9], tabTrous = new int[82][2];
        boolean[][][] valposs=new boolean[9][9][10];
        int nbTrous, compteur=0;

        while (true){
            SudokuBase.initGrilleComplete(gOrdi);
            Extensions.melangeGrille(gOrdi,14);
            nbTrous = Extensions.grilleFacile(gOrdi);
            SudokuBase.initPossibles(gOrdi, valposs, nbValposs);
            Extensions.initTrous(gOrdi,nbValposs,tabTrous);
            for (;nbTrous>0;nbTrous--){
                tourOrdi(gOrdi,valposs,nbValposs,tabTrous);
            }
            compteur++;
            System.out.println();
            System.err.println("le nombre de test est de : "+compteur);
            System.out.println();
        }

    }
}
