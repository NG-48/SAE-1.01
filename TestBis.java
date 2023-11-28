


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
    public static void main(String[] args) {
        int gagnant=partie_bis_Auto();
        if (gagnant==0){
            Ut.afficherSL("\nC'est un match nul !");
        }
        else if(gagnant==1){
            Ut.afficherSL("\nLe gagnant est le joueur humain !");
        }
        else{
            Ut.afficherSL("\nLe gagnant est l'ordinateur !");
        }
    }
}
