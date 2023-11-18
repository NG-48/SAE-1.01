import java.util.*;
import java.lang.*;
import java.util.Scanner;

public class SudokuBase {
    //test
    //.........................................................................
    // Fonctions utiles
    //.........................................................................


    /** pré-requis : min <= max
     *  résultat :   un entier saisi compris entre min et max, avec re-saisie éventuelle jusqu'à ce qu'il le soit
     */
    public static int saisirEntierMinMax(int min, int max){
        int saisi;
        saisi = Ut.saisirEntier();
        while (saisi<min || saisi>max){
            System.err.println("veuillez resaisir un entier valide compris entre "+min+" et "+max);
            saisi = Ut.saisirEntier();
        }
        return saisi;
    }  // fin saisirEntierMinMax
    //.........................................................................


    /** MODIFICI
     *  pré-requis : mat1 et mat2 ont les mêmes dimensions
     *  action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2 soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2){
        for(int i=0;i<mat1.length;i++){
            for (int j=0;j<mat1[0].length;j++){
                mat2[i][j]=mat1[i][j];
            }
        }
    }  // fin copieMatrice

    //.........................................................................


    /** pré-requis :  n >= 0
     *  résultat : un tableau de booléens représentant le sous-ensemble de l'ensemble des entiers
     *             de 1 à n égal à lui-même
     */
    public static boolean[] ensPlein(int n){
      boolean[] tab = new boolean[n];
      for (int i=0;i<tab.length;i++){
          tab[i]=true;
      }
      return tab;
    }  // fin ensPlein

    //.........................................................................


    /** pré-requis : "1 "<= val < ens.length
     *  action :     supprime la valeur val de l'ensemble représenté par ens, s'il y est
     *  résultat :   vrai ssi val était dans cet ensemble
     */
    public static boolean supprime(boolean[] ens, int val){
        if(ens[val]){
            ens[val]=false;
            return true;
        }
        return false;
    }  // fin supprime

    //.........................................................................


    /** pré-requis : l'ensemble représenté par ens n'est pas vide
     *  résultat :   un élément de cet ensemble
     */
    public static int uneValeur(boolean[] ens){
        int i=1;
        while (!ens[i]){
            i++;
        }
        return i;
    }  // fin uneValeur

    //.........................................................................

    /**

     1 2 3 4 5 6 7 8 9
     -------------------
     1 |6 2 9|7 8 1|3 4 5|
     2 |4 7 3|9 6 5|8 1 2|
     3 |8 1 5|2 4 3|6 9 7|
     -------------------
     4 |9 5 8|3 1 2|4 7 6|
     5 |7 3 2|4 5 6|1 8 9|
     6| 1 6 4|8 7 9|2 5 3|
     -------------------
     7 3 8 1|5 2 7|9 6 4
     8 |5 9 6|1 3 4|7 2 8|
     9 |2 4 7|6 9 8|5 3 1|
     -------------------


     1 2 3 4 5 6 7 8 9
     -------------------
     1 |6 0 0|0 0 1|0 4 0|
     2 |0 0 0|9 6 5|0 1 2|
     3 |8 1 0|0 4 0|0 0 0|
     -------------------
     4 |0 5 0|3 0 2|0 7 0|
     5 |7 0 0|0 0 0|1 8 9|
     6||0 0 0|0 7 0|0 0 3|
     -------------------
     7 |3 0 0|0 2 0|9 0 4|
     8 |0 9 0|0 0 0|7 2 0|
     9 |2 4 0|6 9 0|0 0 0|
     -------------------


     * pré-requis : 0<=k<=3 et g est une grille k^2xk^2 dont les valeurs sont comprises
     *              entre 0 et k^2 et qui est partitionnée en k sous-carrés kxk
     * action : affiche la  grille g avec ses sous-carrés et avec les numéros des lignes
     *          et des colonnes de 1 à k^2.
     * Par exemple, pour k = 3, on obtient le dessin d'une grille de Sudoku
     *
     */
    public static void afficheGrille(int k,int[][] g){
        System.out.print("   ");
        for(int a=1; a<=k*k;a++){
                System.out.print(a+" ");
        }
        System.out.println();
        String separateur ="";
        for(int b=0 ; b<2*k*k+3 ; b++ ){
            separateur = separateur + "-";
        }
        System.out.println(separateur);
        // affichage des 2 premieres lignes finit
        for(int ligne=0;ligne<k*k;ligne++){
            System.out.print((ligne+1)+" |");
            for (int colonne=0;colonne<k*k;colonne++){
                if((colonne+1)%k==0){
                    System.out.print(g[ligne][colonne]+"|");
                }else{
                    System.out.print(g[ligne][colonne]+" ");
                }
            }
            System.out.println();
            if((ligne+1)%k==0){
                System.out.println(separateur);
            }
        }

    }
    // fin afficheGrille
    //.........................................................................

    /** pré-requis : k > 0, 0 <= i< k^2 et 0 <= j < k^2
     *  résultat : (i,j) étant les coordonnées d'une case d'une grille k^2xk^2 partitionnée
     *             en k sous-carrés kxk, retourne les coordonnées de la case du haut à gauche
     *             du sous-carré de la grille contenant cette case.
     *  Par exemple, si k=3, i=5 et j=7, la fonction retourne (3,6).
     */
    public static int[] debCarre(int k,int i,int j){
        int[] tab=new int[2];
        tab[0]=i-i%k;
        tab[1]=j-j%k;
        return tab;
    }// fin debCarre


    //.........................................................................

    // Initialisation
    //.........................................................................


    /** MODIFICI
     *  pré-requis : gComplete est une matrice 9X9
     *  action   :   remplit gComplete pour que la grille de Sudoku correspondante soit complète
     *  stratégie :  les valeurs sont données directement dans le code et on peut utiliser copieMatrice pour mettre à jour gComplete
     */
    public static void initGrilleComplete(int [][] gComplete){
        int[][] mat= {
                {6, 2, 9, 7, 8, 1, 3, 4, 5},
                {4, 7, 3, 9, 6, 5, 8, 1, 2},
                {8, 1, 5, 2, 4, 3, 6, 9, 7},
                {9, 5, 8, 3, 1, 2, 4, 7, 6},
                {7, 3, 2, 4, 5, 6, 1, 8, 9},
                {1, 6, 4, 8, 7, 9, 2, 5, 3},
                {3, 8, 1, 5, 2, 7, 9, 6, 4},
                {5, 9, 6, 1, 3, 4, 7, 2, 8},
                {2, 4, 7, 6, 9, 8, 5, 3, 1}
        };
        copieMatrice(mat,gComplete);

    } // fin initGrilleComplete

    //.........................................................................


    /** MODIFICI
     *  pré-requis : gSecret est une grille de Sudoku complète de mêmes dimensions que gIncomplete et 0 <= nbTrous <= 81
     *  action :     modifie gIncomplete pour qu'elle corresponde à une version incomplète de la grille de Sudoku gSecret (gIncomplete peut être complétée en gSecret),
     *               avec nbTrous trous à des positions aléatoires
     */
    public static void initGrilleIncomplete(int nbTrous, int [][] gSecret, int[][] gIncomplete){
        copieMatrice(gSecret,gIncomplete);
        while (nbTrous!=0){
            int ligne = Ut.randomMinMax(0,8);
            int colonne = Ut.randomMinMax(0,8);
            if (gIncomplete[ligne][colonne]!=0){
                gIncomplete[ligne][colonne]=0;
                nbTrous--;
            }
        }

    } // fin initGrilleIncomplete

    //.........................................................................


    /** pré-requis : 0 <= nbTrous <= 81
     *  résultat :   une grille  9x9 saisie dont les valeurs sont comprises ente 0 et 9
     *               avec exactement  nbTrous valeurs nulles
     *               et avec re-saisie jusqu'à ce que ces conditions soient vérifiées.
     *               On suppose dans la version de base que la grille saisie est bien une grille de Sudoku incomplète.
     *  stratégie : utilise la fonction saisirEntierMinMax
     */
    public static int [][] saisirGrilleIncomplete(int nbTrous){
        int[][] grille= new int[9][9];
        int nbTrousSaisi;
        System.out.println("veuillez saisir la grille");
        do{
           nbTrousSaisi = 0;
            for (int ligne=0;ligne<9;ligne++){
                for (int colonne=0;colonne<9;colonne++){
                    int saisi = saisirEntierMinMax(0,9);
                    grille[ligne][colonne] = saisi;
                    if(saisi==0){
                        nbTrousSaisi++;
                    }
                }
            }
            if (nbTrousSaisi!=nbTrous){
                System.err.println("un mauvais nombre de trous a été saisi, veuillez ressaisir la grille.");
            }

        }while (nbTrous!=nbTrousSaisi);
        return grille;
    } // fin saisirGrilleIncomplete

    //.........................................................................



    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque trou de gOrdi
     *           et leur nombre dans nbValPoss
     */
    public static void initPleines(int [][] gOrdi, boolean[][][] valPossibles, int [][] nbValPoss){
        for (int ligne=0;ligne<gOrdi.length;ligne++){
            for (int colonne=0;colonne<gOrdi[0].length;colonne++){
                valPossibles[ligne][colonne] = ensPlein(10);
                nbValPoss[ligne][colonne] = 9;
            }
        }
    }  // fin initPleines

    //.........................................................................


    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               0<=i<9, 0<=j<9,g[i][j]>0,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : supprime dans les matrices valPossibles et nbValPoss la valeur gOrdi[i][j] pour chaque case de la ligne,
     *           de la colonne et du carré contenant la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int [][] gOrdi, int i, int j, boolean[][][] valPossibles, int [][]nbValPoss){
        // on commence par la ligne, puis la colonne puis le carre
        for(int colonne=0; colonne<gOrdi[i].length;colonne++){
            if(supprime(valPossibles[i][colonne],gOrdi[i][j])){
                nbValPoss[i][colonne]--;
            }
        }
        for(int ligne=0; ligne<gOrdi[i].length;ligne++){
            if(supprime(valPossibles[ligne][j],gOrdi[i][j])){
                nbValPoss[ligne][j]--;
            }
        }
        int[] tabCarre= debCarre(3,i,j);
        int ligneCarre= tabCarre[0];
        int colonneCarre= tabCarre[1];
        for(int ligne=ligneCarre;ligne<ligneCarre+3;ligne++){
            for (int colonne=colonneCarre; colonne<colonneCarre+3;colonne++){
                if(supprime(valPossibles[ligne][colonne],gOrdi[i][j])){
                    nbValPoss[ligne][colonne]--;
                }
            }
        }



    }  // fin suppValPoss


    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoju incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     * action :      met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     *               et leur nombre dans nbValPoss
     */
    public static void initPossibles(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
       for(int i=0;i<gOrdi.length;i++){
	        for(int j=0;j<gOrdi[i].length;j++){
		        if(gOrdi[i][j]>0){
		            suppValPoss(gOrdi,i,j,valPossibles,nbValPoss);
		        }
	        }
	}

    }  // fin initPossibles

    //.........................................................................


    /** pré-requis : gSecret, gHumain et gOrdi sont des grilles 9x9
     *  action :     demande au joueur humain de saisir le nombre nbTrous compris entre 0 et 81,
     *               met dans gSecret une grille de Sudoku complète,
     *               met dans gHumain une grille de Sudoku incomplète, pouvant être complétée en gSecret
     *               et ayant exactement nbTrous trous de positions aléatoires,
     *               met dans gOrdi une grille de Sudoku incomplète saisie par le joueur humain
     *               ayant  nbTrous trous,
     *               met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     *               et leur nombre dans nbValPoss.
     * retour : la valeur de nbTrous
     */
    public static int initPartie(int [][] gSecret, int [][] gHumain, int [][] gOrdi,
                                 boolean[][][] valPossibles, int [][]nbValPoss){

        System.out.println("veuillez saisir le nombre de trous que vous souhaiter dans votre grille de sudoku");
        int nbTrous =saisirEntierMinMax(0,81);
	initGrilleComplete(gSecret); /* Met dans gSecret une grille de Sudoku complète */
	initGrilleIncomplete(nbTrous,gSecret,gHumain); /* Met dans gHumain une grille de Sudoku incomplète mais qui peut etre compléter en gSecret avec nbTrous*/
	copieMatrice(saisirGrilleIncomplete(nbTrous),gOrdi);
	initPleines(gOrdi,valPossibles,nbValPoss);
        initPossibles(gOrdi,valPossibles,nbValPoss);
	
        return nbTrous;
    } // fin initPartie

    //...........................................................
    // Tour du joueur humain
    //...........................................................

    /** pré-requis : gHumain est une grille de Sudoju incomplète pouvant se compléter en
     *               la  grille de Sudoku complète gSecret
     *  résultat :   le nombre de points de pénalité pris par le joueur humain pendant le tour de jeu
     *  action :     effectue un tour du joueur humain
     */
    public static int tourHumain(int [][] gSecret, int [][] gHumain){
        int malus=0;
        int valeurSaisi;
        System.out.println("\nc'est votre tour!");
        afficheGrille(3,gHumain);
            System.out.println("veuillez choisir la ligne du trou que vous souhaitez remplir");
            int ligneSaisi = saisirEntierMinMax(1, 9) - 1;
            System.out.println("veuillez choisir la colonne du trou que vous souhaitez remplir");
            int colonneSaisi = saisirEntierMinMax(1, 9) - 1;
        while(gHumain[ligneSaisi][colonneSaisi]!=0){
            System.err.println("l'emplacement selectionnée n'est pas un trou! veuillez choisir un nouveau emplacement");
            System.out.println("veuillez choisir la ligne du trou que vous souhaitez remplir");
            ligneSaisi = saisirEntierMinMax(1, 9) - 1;
            System.out.println("veuillez choisir la colonne du trou que vous souhaitez remplir");
            colonneSaisi = saisirEntierMinMax(1, 9) - 1;
        }
        do{
            System.out.println("\nveuillez saisir la valeur avec laquelle vous souhaitez remplacer le trou\nsinon saisissez le nombre 10 si vous souhaitez prendre un joker");
            valeurSaisi=saisirEntierMinMax(1,10);
            if(valeurSaisi==10){
                gHumain[ligneSaisi][colonneSaisi]=gSecret[ligneSaisi][colonneSaisi];
                System.out.println("la réponse correcte était : "+gSecret[ligneSaisi][colonneSaisi]);
                return malus+1;
            } else if (valeurSaisi==gSecret[ligneSaisi][colonneSaisi]){
                gHumain[ligneSaisi][colonneSaisi]=gSecret[ligneSaisi][colonneSaisi];
                System.out.println("c'est la réponse correcte!");
                return malus;
            }else {
                System.out.println("mauvaise réponse , essaye encore");
                malus++;
            }
        }while(valeurSaisi!=gSecret[ligneSaisi][colonneSaisi]);


        return 0;
    } // fin  tourHumain

    //......................................................

    // Tour de l'ordinateur
    //.........................................................................

    /** pré-requis : gOrdi et nbValPoss sont des matrices 9x9
     *  résultat :   le premier trou (i,j) de gOrdi (c'est-à-dire tel que gOrdi[i][j]==0)
     *               évident (c'est-à-dire tel que nbValPoss[i][j]==1) dans l'ordre des lignes,
     *                s'il y en a, sinon le premier trou de gOrdi dans l'ordre des lignes
     *
     */
    public static int[] chercheTrou(int[][] gOrdi,int [][]nbValPoss){
        int[] coor=new int[2];
        boolean trouver=true;
        for(int ligne=0;ligne<9 && trouver;ligne++){
            for (int colonne=0; colonne<9 && trouver;colonne++){
                if(gOrdi[ligne][colonne]==0 && nbValPoss[ligne][colonne]==1){
                    coor[0]=ligne;
                    coor[1]=colonne;
                    trouver=false;
                }
            }
        }

        for(int ligne=0;ligne<9 && trouver;ligne++){
            for (int colonne=0; colonne<9 && trouver ;colonne++){
                if(gOrdi[ligne][colonne]==0){
                    coor[0]=ligne;
                    coor[1]=colonne;
                    trouver=false;
                }
            }
        }
        return coor;
    } // fin chercheTrou

    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoju incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action :     effectue un tour de l'ordinateur
     */
    public static int tourOrdinateur(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
        int malus=0;
        int [] coor;
        coor=chercheTrou(gOrdi,nbValPoss);
        int ligne=coor[0];
        int colonne=coor[1];
        System.out.println("\nc'est le tour de l'ordinateur!");

        System.out.println("valeur coordonées: "+(ligne+1)+" "+(colonne+1)+" nbValPoss = "+nbValPoss[ligne][colonne]);
        TestBis.afficherTabBoolean(valPossibles[ligne][colonne]);
        Ut.afficherSL("Voici la grille de l'ordinateur : ");
        afficheGrille(3,gOrdi);

        if (gOrdi[ligne][colonne]==0 && nbValPoss[ligne][colonne]==1){
            gOrdi[ligne][colonne]=uneValeur(valPossibles[ligne][colonne]);
            Ut.afficherSL("Voici la grille de l'ordinateur après avoir jouer son coup aux coordonnées :" +(ligne+1)+" "+(colonne+1));
            afficheGrille(3,gOrdi);
        }
        else{
            Ut.afficherSL("J'utilise un joker");
            Ut.afficherSL("Donne moi le résultat pour le point de coordonné " + (ligne+1)+" "+(colonne+1));
            int reponse=Ut.saisirEntier();
            gOrdi[ligne][colonne]=reponse;
            malus++;
            Ut.afficherSL("Voici la grille de l'ordinateur après avoir rempli le trou aux coordonnées :"+(ligne+1)+" "+(colonne+1+"grâce au joker"));
            afficheGrille(3,gOrdi);
        }
        suppValPoss(gOrdi,ligne,colonne,valPossibles,nbValPoss);
        if (malus>0){
            Ut.afficherSL("Voici le nombre de malus que l'ordinateur possède :"+malus);
        }
        return malus;
    } // fin tourOrdinateur

    //.........................................................................

    // Partie
    //.........................................................................



    /** pré-requis : aucun
     *  action :     effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     *  résultat :   0 s'il y a match nul, 1 si c'est le joueur humain qui gagne et 2 sinon
     *  */
    public static int partie(){
        int[][] gSecret=new int[9][9];
        int[][] gHumain=new int[9][9];
        int[][] gOrdi=new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];
        int [][]nbValPoss = new int[9][9];
        int nbTrous=initPartie(gSecret,gHumain,gOrdi,valPossibles,nbValPoss);
        int pointHumain=0;
        int pointOrdi=0;
        for(;nbTrous>0;nbTrous--){
            pointHumain+= tourHumain(gSecret,gHumain);
            pointOrdi+=tourOrdinateur(gOrdi,valPossibles,nbValPoss);
        }
        if(pointHumain<pointOrdi){
            return 2;
        } else if (pointHumain==pointOrdi) {
            return 0;
        }else {
            return 1;
        }
    }  // fin partie

    //.........................................................................


    /** pré-requis : aucun
     *  action :     effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     *               et affiche qui a gagné
     */
    public static void main(String[] args){
	int gagnant=partie();
	if (gagnant==0){
	    Ut.afficherSL("C'est un match nul !");
	}
	else if(gagnant==1){
	    Ut.afficherSL("Le gagnant est le joueur humain !");
	}
	else{
	    Ut.afficherSL("Le gagnant est l'ordinateur !");
	}

    }  // fin main
} // fin SudokuBase



