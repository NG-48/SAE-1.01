public class TestBis {

    public static int initPartie_Bis(int [][] gSecret, int [][] gHumain, int [][] gOrdi,
                                 boolean[][][] valPossibles, int [][]nbValPoss){

        System.out.println("veuillez saisir le nombre de trous que vous souhaiter dans votre grille de sudoku");
        int nbTrous =SudokuBase.saisirEntierMinMax(0,81);
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
            pointOrdi+=SudokuBase.tourOrdinateur(gOrdi,valPossibles,nbValPoss);
        }
        if(pointHumain<pointOrdi){
            return 2;
        } else if (pointHumain==pointOrdi) {
            return 0;
        }else {
            return 1;
        }
    }  // fin partie

    public static void main(String[] args) {
        int gagnant=partie_bis();
        if (gagnant==0){
            Ut.afficherSL("C'est un match nul !");
        }
        else if(gagnant==1){
            Ut.afficherSL("Le gagnant est le joueur humain !");
        }
        else{
            Ut.afficherSL("Le gagnant est l'ordinateur !");
        }
    }
}
