/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import InsertionSort;
import SelectionSort;
import Sort;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

import org.junit.rules.Stopwatch;

/**
 * @param <T> The generic type T is that of the input to the function f which you will pass in to the constructor.
 */
public class Benchmark<T> {

    /**
     * Constructor for a Benchmark.
     * @param f a function of T => Void.
     * Function f is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     * When you create a lambda defining f, you must return "null."
     */
    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     * @param t the value that will in turn be passed to function f.
     * @param m the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    public double run(T t, int m) {
    	double time=0;
    	double startTime=0;
    	double endTime=0;
    	for(int i=0;i<m;i++) {
    		startTime=System.nanoTime();
        	this.f.apply(t);
        	endTime=System.nanoTime();
        	time+= (endTime-startTime)/Math.pow(10,6);
    	}    	
        return  time/m; // TODO
    }
    

    private final Function<T, Void> f;

    /**
     * Everything below this point has to do with a particular example of running a Benchmark.
     * In this case, we time three types of simple sort on a randome integer array of length 1000.
     * Each test is run 200 times.
     * @param args the command-line arguments, of which none are significant.
     */
    public static void main(String[] args) {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        // TODO You need to apply doubling to n
        int n = 1000; // This is the size of the array
        for (int k = 0; k< 6; k++) {
            Integer[] array = new Integer[n];
            Integer[] sorted = new Integer[n];
            Integer[] partialSortedArray=new Integer[n];
            Integer[] reverseSortedArray=new Integer[n];
            for(int i=0;i<n;i++) {
            	sorted[i]=i;
            }
            System.out.println("\nFor N="+n);
            benchmarkSort(sorted, "InsertionSort - Sorted Array", new InsertionSort<>(), m);
            benchmarkSort(sorted, "SelectionSort - Sorted Array", new SelectionSort<>(), m);
            for (int i = 0; i < n; i++) array[i] = random.nextInt();
            benchmarkSort(array, "InsertionSort - Random Array", new InsertionSort<>(), m);
            benchmarkSort(array, "SelectionSort - Random Array", new SelectionSort<>(), m);
            
            //Partial Sorted Array i.e generating some random integers and inserting them to a sorted list anywhere in the array.
    		ArrayList<Integer> randomList=new ArrayList();
    		for(int i=0;i<n/10;i++) {
    			randomList.add((int)(Math.random()*n));
    		}
    		for(int i=0;i<n;i++) {
    			partialSortedArray[i]=i;
    			if(randomList.contains(partialSortedArray[i])) {
    				partialSortedArray[i]=random.nextInt(n);
    			}
    		}
            benchmarkSort(partialSortedArray, "InsertionSort - Partial Sorted Array", new InsertionSort<>(), m);
            benchmarkSort(partialSortedArray, "SelectionSort - Partial Sorted Array", new SelectionSort<>(), m);

            //Reverse Sorted Array i.e 1-n/2 sorted and n/2 to n random
            for(int i=0;i<n;i++) reverseSortedArray[i] = n-i;
            benchmarkSort(reverseSortedArray, "InsertionSort - Reverse Sorted Array", new InsertionSort<>(), m);
            benchmarkSort(reverseSortedArray, "SelectionSort - Reverse Sorted Array", new SelectionSort<>(), m);
            n = n * 2;
        }
    }

    private static void benchmarkSort(Integer[] array, String name, Sort<Integer> sorter, int m) {
        Function<Integer[], Void> sortFunction = (xs) -> {
            sorter.sort(xs);
            return null;
        };
        Benchmark<Integer[]> bm = new Benchmark<>(sortFunction);
        double x = bm.run(array, m);
        System.out.println(name + ": " + x + " millisecs");
    }
}