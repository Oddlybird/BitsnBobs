/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package oddbird.bitsnbobs;
import java.util.Random;
/*
import javax.swing.*;     // For the GUI
import java.awt.event.*;  // more GUI
import java.awt.image.*;  // more gui
import java.awt.*;        // more GUI
import java.util.Arrays;
import com.google.gson.Gson; // java obj <-> JSON string
*/
@SuppressWarnings("unused")

/**
 *
 * @author wirri
 */
public class BitsnBobs {
    static Random rand = new Random();
    
    public static void main(String[] args) {
        mainconsole();
        // maingui(); // alternate version
    }// end main

	public static void maingui() {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Blobber.createAndShowGUI();				
                    }
                });               
            }
    
    public static void mainconsole() {
        String temp = gen("cus", 100);
        System.out.println(temp);
        //
        Blobber blobs = new Blobber();
        blobs.print();
        System.out.println(blobs.ListClaimsToString());
    }// end console version of main
    
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
