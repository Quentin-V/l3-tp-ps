package AlgLin;

import java.io.*;
import java.util.*;

/**
 * Classe définissant une matrice
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class Matrice {

    final static double EPSILON = 0.000001;

    /* Définir ici les attributs de la classe */

    /**
     * Coefficients de la matrice
     */
    protected double[][] coefficient;

    /* Définir ici les constructeur de la classe */

    /**
     * Constructeur qui initialise le tableau de coefficients avec un tableau vide
     *
     * @param nbligne   Le nombre de lignes de la matrice
     * @param nbcolonne Le nombre de colonnes de la matrice
     */
    public Matrice(int nbligne, int nbcolonne) {
        this.coefficient = new double[nbligne][nbcolonne];
    }

    /**
     * Constructeur qui prend en paramètre un tableau à 2 dimensions en tant que coefficients de la matrice
     *
     * @param tableau Le tableau de coefficients donné
     */
    public Matrice(double[][] tableau) {
        coefficient = tableau;
    }

    /**
     * Constructeur qui prend en paramètre un nom de fichier et qui recopie le contenu du fichier dans une matrice
     * Renvoie une exception si le fichier n'existe pas
     *
     * @param fichier Le nom du fichier
     */
    public Matrice(String fichier) {
        try {
            Scanner sc = new Scanner(new File(fichier));
            int ligne = sc.nextInt();
            int colonne = sc.nextInt();
            this.coefficient = new double[ligne][colonne];
            for (int i = 0; i < ligne; i++)
                for (int j = 0; j < colonne; j++)
                    this.coefficient[i][j] = sc.nextDouble();
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Fichier absent");
        }
    }

    /* Definir ici les autres methodes */

    /**
     * Méthode de recopie d'une matrice
     *
     * @param arecopier La matrice à recopier
     */
    public void recopie(Matrice arecopier) {
        int ligne, colonne;
        ligne = arecopier.nbLigne();
        colonne = arecopier.nbColonne();
        this.coefficient = new double[ligne][colonne];
        for (int i = 0; i < ligne; i++)
            if (colonne >= 0) System.arraycopy(arecopier.coefficient[i], 0, this.coefficient[i], 0, colonne);
    }

    /**
     * Méthode permettant de connaitre le nombre de lignes de la matrice
     *
     * @return Le nombre de lignes de la matrice
     */
    public int nbLigne() {
        return this.coefficient.length;
    }

    /**
     * Méthode permettant de connaitre le nombre de colonnes de la matrice
     *
     * @return Le nombre de colonnes de la matrice
     */
    public int nbColonne() {
        return this.coefficient[0].length;
    }

    /**
     * Méthode retournant un coefficient en fonction des paramètres donnés
     *
     * @param ligne   La ligne de la matrice du coefficient voulu
     * @param colonne La colonne de la matrice du coefficient voulu
     * @return Le coefficient à la ligne et la colonne données
     */
    public double getCoef(int ligne, int colonne) {
        return this.coefficient[ligne][colonne];
    }

    public Vecteur getColonne(int colonne) {
        Vecteur toReturn = new Vecteur(nbLigne());

        for (int i = 0; i < nbLigne(); i++) {
            toReturn.remplacecoef(i, getCoef(i, colonne));
        }

        return toReturn;
    }

    /**
     * Méthode permettant de remplacer un coefficient de la matrice en fonction des paramètres donnés
     *
     * @param ligne   La ligne du coefficient à remplacer
     * @param colonne La colonne du coefficient à remoplacer
     * @param value   La valeur de remplacement du coefficient
     */
    public void remplacecoef(int ligne, int colonne, double value) {
        this.coefficient[ligne][colonne] = value;
    }

    /**
     * Méthode retournant sous forme de chaine de caractères la matrice en séparant les coefficients avec des espaces
     *
     * @return La chaine de caractères qui représente la matrice
     */
    public String toString() {
        int ligne = this.nbLigne();
        int colonne = this.nbColonne();
        StringBuilder matr = new StringBuilder();
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (j == 0) {
                    matr.append(this.getCoef(i, j));
                } else {
                    matr.append(" ").append(this.getCoef(i, j));
                }
            }
            matr.append("\n");
        }
        return matr.toString();
    }

    /**
     * Méthode permettant de faire le produit par un scalaire de la matrice
     *
     * @param scalaire Le scalaire
     * @return Le produit entre la matrice et le scalaire donné
     */
    public Matrice produit(double scalaire) {
        int ligne = this.nbLigne();
        int colonne = this.nbColonne();
        for (int i = 0; i < ligne; i++)
            for (int j = 0; j < colonne; j++)
                this.coefficient[i][j] *= scalaire;
        return this;
    }

    /**
     * Méthode permettant d'ajouter 2 matrices entre elles
     *
     * @param a Une matrice
     * @param b Une autre matrice
     * @return L'addition des matrices a et b
     */
    static Matrice addition(Matrice a, Matrice b) {
        int ligne = a.nbLigne();
        int colonne = a.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for (int i = 0; i < ligne; i++)
            for (int j = 0; j < colonne; j++)
                mat.coefficient[i][j] = a.coefficient[i][j] + b.coefficient[i][j];
        return mat;
    }

    /**
     * Méthode permettant d'additioner 2 matrices en vérifiant qu'on peut les additioner au préalable
     * Si ce n'est pas le cas, lève une exception
     *
     * @param a Une matrice
     * @param b Une autre matrice
     * @return L'addition de la matrice a et b si elle est possible
     * @throws Exception Si les deux matrices n'ont pas les mêmes dimensions
     */
    static Matrice verif_addition(Matrice a, Matrice b) throws Exception {
        if ((a.nbLigne() == b.nbLigne()) && (a.nbColonne() == b.nbColonne())) {
            return addition(a, b);
        } else {
            throw new Exception("Les deux matrices n'ont pas les mêmes dimensions !!!");
        }
    }

    /**
     * Méthode permettant de faire le produit de 2 matrices entre elles
     *
     * @param a Une matrice
     * @param b Une autre matrice
     * @return Le produit des matrices a et b
     */
    static Matrice produit(Matrice a, Matrice b) {
        int ligne, colonne;
        ligne = a.nbLigne();
        colonne = b.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                mat.coefficient[i][j] = 0;
                for (int k = 0; k < a.nbColonne(); k++)
                    mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
            }
        }
        return mat;
    }

    /**
     * Méthode permettant de faire le produit de 2 matrices entre elles en vérifiant qu'on peut faire le produit au préalable
     * Si ce n'est pas le cas, lève une exception
     *
     * @param a Une matrice
     * @param b Une autre matrice
     * @return Le produit des la matrices a et b si possible
     * @throws Exception Si les deux matrices n'ont pas de dimensions compatibles
     */
    static Matrice verif_produit(Matrice a, Matrice b) throws Exception {
        if (a.nbColonne() != b.nbLigne())
            throw new Exception("Dimensions des matrices à multiplier incorrectes");
        return (produit(a, b));
    }

    public static Matrice reduce(Matrice x, Matrice y, int r, int c, int n) {
        for (int h = 0, j = 0; h < n; h++) {
            if (h == r) continue;
            for (int i = 0, k = 0; i < n; i++) {
                if (i == c) continue;
                y.remplacecoef(j, k, x.getCoef(h, i));
                k++;
            }
            j++;
        }
        return y;
    }

    public Matrice inverse() throws IllegalOperationException, IrregularSysLinException {
        //TODO vérification du déterminant != 0 sinon throw exception
        if (nbLigne() != nbColonne()) throw new IllegalOperationException();
        Matrice toReturn = new Matrice(nbLigne(), nbColonne()),
                identite = new Matrice(nbLigne(), nbColonne());

        for (int i = 0; i < nbLigne(); i++) {
            identite.remplacecoef(i, i, 1);
        }

        for (int i = 0; i < nbColonne(); i++) {
            Vecteur secondMembre = identite.getColonne(i);
            Helder helder = new Helder(this, secondMembre);
            helder.factorLDR();
            Vecteur res = helder.resolutionPartielle();

            for (int j = 0; j < res.nbLigne(); j++) {
                toReturn.remplacecoef(j, i, res.getCoef(j));
            }
        }

        return toReturn;
    }


    public static double getDeterminant(Matrice matrice) throws IllegalOperationException {
        if(matrice.nbColonne() != matrice.nbLigne()) throw new IllegalOperationException();
        if(matrice.nbLigne() == 2) return matrice.getCoef(0,0) * matrice.getCoef(1,1) - (matrice.getCoef(0,1) * matrice.getCoef(1,0));

        double det = 0;
        for(int i = 0; i < matrice.nbColonne(); ++i) {
            det += Math.pow(-1, i) * matrice.getCoef(0,i) * getDeterminant(matrice.cofactor(0,i));
        }
        return det;
    }

    private Matrice cofactor(int ligne, int colonne) {
        Matrice cofact = new Matrice(nbLigne()-1, nbColonne()-1);

        for(int i = 0; i < nbLigne(); ++i) {
            if(i == ligne) continue;
            for(int j = 0; j < nbColonne(); ++j) {
                if(j == colonne) continue;
                if(i > ligne) {
                    if(j > colonne) {
                        cofact.remplacecoef(i-1, j-1, getCoef(i, j));
                    }else {
                        cofact.remplacecoef(i-1, j, getCoef(i, j));
                    }
                }else {
                    if(j > colonne) {
                        cofact.remplacecoef(i, j-1, getCoef(i, j));
                    }else {
                        cofact.remplacecoef(i, j, getCoef(i, j));
                    }
                }

            }
        }

        return cofact;
    }

    public double norme_1() {
        return 0.0;
    }

    public double norme_inf() {
        return 0.0;
    }

    public void conditionnement() {

    }

    /**
     * Main permettant de tester le classe
     */
    public static void main(String[] args) throws IrregularSysLinException, IllegalOperationException, Exception {
//        double[][] mat = {{2, 1}, {0, 1}};
//        Matrice a = new Matrice(mat);
//        System.out.println("construction d'une matrice par affectation d'un tableau :\n" + a);
//        Matrice b = new Matrice("./ressources/matrice.txt");
//        System.out.println("Construction d'une matrice par lecture d'un fichier :\n" + b);
//        Matrice c = new Matrice(2, 2);
//        c.recopie(b);
//        System.out.println("Recopie de la matrice b :\n" + c);
//        System.out.println("Nombre de lignes et colonnes de la matrice c : " + c.nbLigne() +
//                ", " + c.nbColonne());
//        System.out.println("Coefficient (2,2) de la matrice b : " + b.getCoef(1, 1));
//        System.out.println("Nouvelle valeur de ce coefficient : 8");
//        b.remplacecoef(1, 1, 8);
//        System.out.println("Vérification de la modification du coefficient");
//        System.out.println("Coefficient (2,2) de la matrice b : " + b.getCoef(1, 1));
//        System.out.println("Addition de 2 matrices : affichage des 2 matrices " +
//                "puis de leur addition");
//        System.out.println("matrice 1 :\n" + a + "matrice 2 :\n" + b + "somme :\n" +
//                Matrice.addition(a, b));
//        System.out.println("Produit de 2 matrices : affichage des 2 matrices " +
//                "puis de leur produit");
//        System.out.println("matrice 1 :\n" + a + "matrice 2 :\n" + b + "produit :\n" +
//                produit(a, b));

        /*
        Matrice matrice = new Matrice(new double[][]{{4, -20, -12, 63}, {-8, 45, 44, 1}, {20, -105, -79, 8}, {-15, 7, -32, 45}});

        Matrice inverse = matrice.inverse();

        System.out.println("inverse = " + inverse);

        System.out.println(Matrice.produit(matrice, inverse));

        System.out.println("det(3, matrice) = " + det(4, matrice));

         */

        Matrice matrice = new Matrice(new double[][]{{1,2,3,4}, {3,2,1,4}, {5,3,5,6}, {4,1,2,4}});
        //Matrice matrice = new Matrice(new double[][]{{4,-1,1}, {4,5,3}, {-2,0,0}});
        //Matrice matrice = new Matrice(new double[][]{{1,2}, {3,2}});
        System.out.println("Matrice : \n" + matrice);

        //System.out.println("Cofactor : " + matrice.cofactor(1,1));
        System.out.println("Determinant : " + getDeterminant(matrice));

    }
}
