
public class InsertionSort<X extends Comparable<X>> implements Sort<X> {
    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement insertionSort
    	for(int i=from;i<to;i++) {
    		for(int j=i;j>from;j--) {
    			if(less(xs[j],xs[j-1])){
    				swap(xs, from, to, j, j-1);
    			}
    			else break;
    		}
    	}
    }
}
