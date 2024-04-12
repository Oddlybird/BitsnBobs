/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package oddbird.bitsnbobs;
import java.util.Random;
@SuppressWarnings("unused")

/**
 *
 * @author wirri
 */
public class BitsnBobs {
    static Random rand = new Random();
    public static void main(String[] args) {
        //String temp = gen("cus", 10);
        //System.out.println(temp);
        //
        Blobber blobs = new Blobber();
        blobs.print();
        
    }// end main
    
    public static String gen(String code, int num) {
        String var = "";
        if (num<1)    {num=1;};
        if (num>10000) {num=10000;};

        while (num>0) {
            if (code.equals("cus")) {var = var + CustomTown.gen();};
            var = var + ", ";
            num--;
        }
        return var;
    }//end gen
    
    public static String gennum(int num) {
        String var = "";
        int temp = 0;
        if (num<1)    {num=1;};
        if (num>1000) {num=1000;};

        while (num>0) {
            temp = rand.nextInt(3) + rand.nextInt(3) + 2;
            var = var + temp + ", ";
            num--;
        }
        return var;
    }
    
}// end class
