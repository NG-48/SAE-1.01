import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Test {
    public static void main(String[] args) {
        int[][] sudoku=new int[9][9];
        SudokuBase.initGrilleComplete(sudoku);
        int[][] trous=new int[9][9];
        SudokuBase.initGrilleIncomplete(Ut.randomMinMax(5,25),sudoku,trous);
        boolean[] tabBool= {false,false,false,true,true,false};
        SudokuBase.afficheGrille(3,sudoku);
        SudokuBase.afficheGrille(3,trous);
        System.out.println("");
        int[][] matrice = new int[25][25];
        System.out.println(SudokuBase.tourHumain(sudoku,trous));
    }
}