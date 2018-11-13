
import java.util.Random;

public class RandomWalk {
    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    private void move(int dx, int dy) {
        // TODO you need to implement this
    	this.x=this.x+dx;
    	this.y=this.y+dy;
    }

    /**
     * Perform a random walk of m steps
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        for (int i = 0; i < m; i++)
            randomMove();
    }

    private void randomMove() {
    	int l=4; //can have dynamic l too for showing the relationship between l,d,n
    	int min=1;
    	int max=4;
    	int rand=min+random.nextInt(max-min+1);
    	//if he moves towards East then + x axis (1,0)
    	//if he moves towards West then - x axis (-1,0)
    	//if he moves towards North then + y axis (0,1)
    	//if he moves towards South then - y axis (0,-1)
    	//choosing random numbers between 1,2,3,4 and moving l(dynamic) times for a move.
    	switch(rand){
    		case 1:
    			move(l,0);
    			break;
    		case 2:
    			move(-l,0);
    			break;
    		case 3:
    			move(0,l);
    			break;
    		case 4:
    			move(0,-l);
    			break;	
    	}
        // TODO you need to implement this
    }

    public double distance() {
    	double result=0;
    	result=Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
    	return result; // TODO you need to implement this
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++){
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance/n ;
    }
    
    public static void main(String[] args) {
        if (args.length==0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);
        double meanDistance = randomWalkMulti(m, n);
        System.out.println(m + " steps: " + meanDistance + " over "+ n + " experiments");
    }

}
