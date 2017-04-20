import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by olwin on 2/6/17.
 */
public class main {
    static int min = 0, max = 0, lastIndex = 0;
    static int[] BucketAry;

    public static void main(String[] args) {
        try {
            File infile = new File(args[0]);
            String outfile = args[1];

            PrintWriter output = new PrintWriter(outfile, "UTF-8");
            findMinMax(infile, output);
            constructor(min, max);
            printSortData(infile,output);
            output.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void findMinMax(File infile, PrintWriter output) {
        int num = 0;
        boolean firstTime = true;
        try {
            Scanner scan = new Scanner(infile);
            while (scan.hasNext()) {
                num = Integer.parseInt(scan.next());
                if (num < 0) output.println("Negative number in input");
                if (num > max) max = num;
                if (firstTime) {
                    min = num;
                    firstTime = false;
                } //first time to set the minimum number
                if (num < min) min = num;
            }
            scan.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void printSortData(File infile, PrintWriter output){
        int num,index,value,count;
        try{
            Scanner scan=new Scanner(infile);
            while(scan.hasNext()){
                num=Integer.parseInt(scan.next());
                index= getIndex(num);
                BucketAry[index]++;
            }

            for(int i=0;i<lastIndex;i++){
                if(BucketAry[i]!=0){
                    count = BucketAry[i];
                    for(int j=0;j<count;j++){
                        value=i+min;
                        output.write(value+" ");
                    }
                }
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }
    public static int getIndex(int data) {
        return data - min;
    }

    public static void constructor(int min, int max) {
        BucketAry = new int[max - min + 1];
        lastIndex = max - min + 1;
    }
}
