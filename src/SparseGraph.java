import java.util.ArrayList;

/**
 * Created by ASUS on 15/10/2017.
 */
public class SparseGraph {
    public SparseGraph(Term[] arr, int clos, int rows, int terms) {
        this.termsArray = arr.clone();
        this.cols = clos;
        this.rows = rows;
        this.terms = terms;
    }

    static class Term {
        int col;
        int row;
        int value;

        public Term(int row, int col, int value) {
            this.col = col;
            this.row = row;
            this.value = value;
        }
    }

    Term[] termsArray;
    int cols;
    int rows;
    int terms;

    public static SparseGraph getFromMatrix(Matrix matrix) {

        ArrayList<Term> terms = new ArrayList<>();
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                terms.add(new Term(i, j, matrix.grid[i][j]));
            }
        }

        return new SparseGraph(terms.toArray(new Term[terms.size()]), matrix.cols, matrix.rows, terms.size());

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < termsArray.length; i++) {
            Term t = termsArray[i];
            sb.append("col : " + t.col + " -- row : " + t.row + " -- value : " + t.value);
            sb.append("\n");
        }
        return sb.toString();
    }

    public SparseGraph transpose() {
        int[] counts = new int[cols];// cols  = rows of transposed
        Term[] newTerms = new Term[termsArray.length];
        for (int i = 0; i < termsArray.length; i++) {
            Term thisTerm = termsArray[i];
            counts[thisTerm.col]++;
            newTerms[i] = new Term(thisTerm.col, thisTerm.row, thisTerm.value);
        }

        // do counting sort
        // 0<= rows < int rows
        int[] places = new int[cols];
        places[0] = 0;
        for (int i = 1; i < counts.length; i++) {
            places[i] = places[i - 1] + counts[i - 1];
        }
        Term[] termssorted = new Term[termsArray.length];
        for (int i = 0; i < termsArray.length; i++) {
            Term currterm = newTerms[i];
            int place = places[currterm.row];
            termssorted[place] = currterm;
            places[currterm.row]++;
        }


        return new SparseGraph(termssorted, rows, cols, terms);
    }


}
