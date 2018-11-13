

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BSTClient {
	private static Writer writer;
	private static HashMap<Integer,Integer> hm;
	public static void main(String args[]) throws IOException {
		int N=100;
		int M=200;
		int X=10000;
		BSTSimple bst=new BSTSimple();
		Random r=new Random();
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<N;i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		for(int i=0;i<N;i++) {
			int x=list.get(i);
			bst.put(x, i);
		}
		hm=new HashMap<>();
		hm.put(bst.countNodes(),bst.height());
		for(int k=0;k<X;k++) {
			boolean b=r.nextBoolean();
			int initCount=bst.countNodes();
			String operation;
			if(b) {
				operation="Insert";
				bst.put(r.nextInt(M), r.nextInt(M));
			}else {
				operation="Delete";
				bst.delete(r.nextInt(M));
			}
			int finalCount=bst.countNodes();
			if(initCount!=finalCount) {
				hm.put(bst.countNodes(),bst.height());
				String s=operation+","+"  Size:"+bst.countNodes()+","+"  Depth: "+bst.height();
				System.out.println(s);
			}
		}
		generateFile();
	}
	
	private static void generateColumns() throws IOException{
		for(Map.Entry<Integer, Integer> entry: hm.entrySet()) {
			String s=entry.getKey()+","+entry.getValue()+","+Math.log(entry.getKey())+","+Math.log(entry.getValue());
			writer.append(s);
			writer.append("\n");
		}
	}
	
	private static void generateFile() throws IOException{
        try {
            File file = new File("./BST.csv");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            System.out.println("New BST File Created");
            writer = new FileWriter(file);
            writer.append("Size,Depth,Log(Size), Log(Depth)");
            writer.append("\n");
            generateColumns();
        }finally{
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }      
    }
}
