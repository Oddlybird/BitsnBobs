/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oddbird.bitsnbobs;

/**
 * I'm tired of not having a convenient primitive for this
 * @author wirri
 */
public class Tuple {
    int x;
    int y;
    
    public Tuple(int inx, int iny) {
        this.x = inx;
        this.y = iny;
    }

    public Tuple(int[] input) {
        this.x = input[0];
        this.y = input[1];
    }

    // Who knows if this works
    public Tuple(String input) {
        // Trim ()
        input = input.replace('(', ' ');
        input = input.replace(')', ' ');
        input = input.trim();
        // Attempt to split it in half at the , and get rid of the comma and space
        String regex = "[,]";
        String[] myarr = input.split(regex);
        myarr[0] = myarr[0].replace(',', ' ');
        myarr[0] = myarr[0].trim();
        myarr[1] = myarr[1].replace(',', ' ');
        myarr[1] = myarr[1].trim();        
        // Now try to parse the substrings
        this.x = Integer.parseInt(myarr[0]);
        this.y = Integer.parseInt(myarr[1]);
    }   
    
    public String ToStr() {
        String a = "(" + x + ", " + y + ")";
        return a;
    }

    public int[] ToArray() {
        int[] output = {x, y};
        return output;
    }

    // Create and populate a grid.
    // Probably don't call this, copy-paste-edit when needed
    // You can refer to grid[a][b].x to get the x coord of a grid square
    private Tuple[][] BlankGrid(int x, int y) {
        Tuple[][] grid = new Tuple[x][y]; 
        for (int j = 0; j < x; j++) {
            for (int i = 0; i < y; i++) {
                grid[i][j] = new Tuple(i,j);
                }//i
            }//j
        return grid;
    }

    
}

