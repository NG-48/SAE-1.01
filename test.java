public class test {
    public static void main(String[] args) {
       int[][] mat=new int[9][9];
       SudokuBase.initGrilleComplete(mat);
       Extensions.melangeGrille(mat,14);
       affiche(mat);
       SudokuBase.initGrilleIncomplete(81,mat,mat);
       System.out.println();
       affiche(mat);
    }
    public static void affiche(int[][] mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int compterZeros(int[][] matrice) {
        int nombreZeros = 0;

        // Parcourir la matrice et compter les zéros
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == 0) {
                    nombreZeros++;
                }
            }
        }

        return nombreZeros;
    }
    public static void affiche2(boolean[][][] boo){
        for (int i = 0; i < boo.length; i++) {
            for (int j = 0; j < boo[i].length; j++) {
                TestBis.afficherTabBoolean(boo[i][j]);
            }
            System.out.println();
        }
    }
}
