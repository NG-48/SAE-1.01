public class test {
    public static void main(String[] args) {
        int[][] mat = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        affiche(mat);
        for (int tour=0;tour<2;tour++){
            Extensions.melangeGrille(mat,2);
            System.out.println();
            affiche(mat);

        }
        for(int ligne=0;ligne< mat.length;ligne++){
            System.out.print(Extensions.testTricheLigne(mat[ligne]) + " ");
        }
        System.out.println();
        for(int colonne=0;colonne< mat.length;colonne++){
            System.out.print(Extensions.testTricheColonne(mat,colonne) + " ");
        }
        System.out.println();
        int ligne=0;
        int colonne=0;
        while (ligne<9){
            System.out.print(Extensions.testTricheCarre(mat,ligne,colonne) + " ");
            colonne +=3;
            if(colonne>8){
                colonne=0;
                ligne+=3;
            }
        }
        System.out.println();

    }
    public static void affiche(int[][] mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
