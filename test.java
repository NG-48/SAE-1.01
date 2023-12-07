public class test {
    public static void main(String[] args) {
        int[][] mat = {
                {6, 0, 0, 0, 0, 1, 0, 4, 0},
                {0, 0, 0, 9, 6, 5, 0, 1, 2},
                {8, 1, 0, 0, 4, 0, 0, 0, 0},
                {0, 5, 0, 3, 0, 2, 0, 7, 0},
                {7, 0, 0, 0, 0, 0, 1, 8, 9},
                {0, 0, 0, 0, 7, 0, 0, 0, 3},
                {3, 0, 0, 0, 2, 0, 9, 0, 4},
                {0, 9, 0, 0, 0, 0, 7, 2, 0},
                {2, 4, 0, 6, 9, 0, 0, 0, 0}
        };

        int[][] mat2 = new int[9][9];
        affiche(mat);
        System.out.println(compterZeros(mat));
        System.out.println();
        SudokuBase.saisirGrilleIncompleteFichier(compterZeros(mat)-1,mat2,"grille1.txt");
        affiche(mat2);
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
}
