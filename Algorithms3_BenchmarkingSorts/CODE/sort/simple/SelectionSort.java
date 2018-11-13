
public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement selection sort
    	for(int i=from;i<to;i++) {
    		int min=i;
    		for(int j=i+1;j<to;j++) {
    			if(less(xs[j],xs[min])) {
    				min=j;
    			}
    		}
    		swap(xs,from, to, i, min);
    	}
    }
}
