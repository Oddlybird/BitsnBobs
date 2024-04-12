package oddbird.bitsnbobs;
import java.util.Random;
@SuppressWarnings("unused")

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This sections out a grid into aesthetic blobs
 * @author wirri
 */
public class Blobber {
    static Random rand = new Random();
        // Gives a 16x16 grid, increase later and build via a pair of for loops rather than manually
    public int minx = 0;    public int maxx = 15;
    public int miny = 0;    public int maxy = 15;
    public String[][] claim = {
        //0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 0
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 1
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 2
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 3
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 4
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 5
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 6
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 7
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 8
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 9
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 10
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 11
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 12
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 13
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 14
        {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}, // 15
        };
    // Set these to generated names eventually
    public String[] names = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
       
    public Blobber() {
        // Current location we are adding to
        int x = 7;
        int y = 7;
        
        // set current point to first name, and spread.
        claim[x][y] = names[0];  
        Spreadlot(x,y);
        
        // Find Empty nearby point  ( Add fail condition for when x+y>9001)
        int[] coord = FindNearbyEmpty(x,y);
        x = coord[0]; y = coord[1];
        // Number of lots to generate in the clump: 
        int i = 0;
        int imax = 5;

        // Trail a random shape around
        while ((coord[0]+coord[1]<9001)&&(i<imax)) {
            // set current point to next name, and spread.
            claim[x][y] = names[i];  
            Spreadlot(x,y);
            // find next point, and increment cointer
            coord = FindNearbyEmpty(x,y); // point near last used point
            x = coord[0]; y = coord[1];
            i++;
            };
        
        // Next Shape
        coord = FindNearbyEmpty(7,7); // point near center
        x = coord[0]; y = coord[1];
        imax = imax + rand.nextInt(5);
        while ((coord[0]+coord[1]<9001)&&(i<imax)) {
            // set current point to next name, and spread.
            claim[x][y] = names[i];  
            Spreadlot(x,y);
            // find next point, and increment cointer
            coord = FindNearbyEmpty(x,y); // point near last used point
            x = coord[0]; y = coord[1];
            i++;
            };       

        // Next Shape
        coord = FindNearbyEmpty(7,7); // point near center
        x = coord[0]; y = coord[1];
        imax = imax + rand.nextInt(5);
        while ((coord[0]+coord[1]<9001)&&(i<imax)) {
            // set current point to next name, and spread.
            claim[x][y] = names[i];  
            Spreadlot(x,y);
            // find next point, and increment cointer
            coord = FindNearbyEmpty(x,y); // point near last used point
            x = coord[0]; y = coord[1];
            i++;
            };       
            
        // Fill in the blob around the center a bit
        imax = imax + rand.nextInt(5);
        while ((coord[0]+coord[1]<9001)&&(i<imax)) {
            // set current point to next name, and spread.
            claim[x][y] = names[i];  
            Spreadlot(x,y);
            // find next point, and increment cointer
            coord = FindNearbyEmpty(7,7); // point near center
            x = coord[0]; y = coord[1];
            i++;
            };
            
            
    }
    
    
    private void Spreadlot(int xx, int yy) {
        int blocklimit = 2 + rand.nextInt(3) + rand.nextInt(3); // max 6
        int blocksclaimed = CountClaimed(xx,yy);
        int neighbors = CountLotNeighbors(xx,yy);
        int i = 0;
        int numloops = 2;

        String[] spots = {"", "", "N", "S", "E", "W", "NE", "NW", "SE", "SW"};
        spots = Shuffle(spots);
        int[] coord = ConvertCoord(xx, yy, spots[0]);
        int tempx = coord[0]; int tempy = coord[1];
        int limit = blocklimit-blocksclaimed;
        
        while ((blocksclaimed<blocklimit)&&(neighbors>0)&&(i<spots.length)&&(numloops>0)) {
            // Get a coordinate inside the first ring
            coord = ConvertCoord(xx, yy, spots[i]);
            tempx = coord[0]; tempy = coord[1];
            // If that coordinate is claimed by the lot, attempt to spread from there and update blockclaim
            if (Equals(tempx, tempy, xx, yy))   {
                Spread(tempx, tempy, limit); 
                blocksclaimed = CountClaimed(xx,yy);
                limit = blocklimit-blocksclaimed;
                }; 
            // Decrement counter, and go to next option
            i++;
            if (i==spots.length) {numloops--; i=0;};
            }// end while
    }
    
    private void Spread(int xx, int yy, int blocksleft) {
        // Only call this on non-null (not ".") blocks.
        String name = claim[xx][yy];  // set our current blob name from the coords given
        boolean cont = true;          // Consider using a counter of some kind for number of loops?  Eh.
        int chancesuccess = 25;
        if (CountFreeNeighbors(xx,yy)==3) {chancesuccess=50;};
        if (CountFreeNeighbors(xx,yy)==2) {chancesuccess=75;};
        if (CountFreeNeighbors(xx,yy)==1) {chancesuccess=100;};
        String[] spots = {"N", "S", "E", "W"};
        spots = Shuffle(spots);
        int[] coord = ConvertCoord(xx, yy, spots[0]);
        int tempx = coord[0]; int tempy = coord[1];
        
        while ((HasFreeNeighbors(xx,yy))&&(blocksleft>0)&&(cont)) {
            // attempt claim
            if (CountFreeNeighbors(xx,yy)>1) {
                if ((rand.nextInt(100)<chancesuccess)) {
                    if (Empty(tempx, tempy)) {blocksleft--; claim[tempx][tempy]=name;};};    
                };
            // Consider exiting the loop early.
            if ((rand.nextInt(100)>80)) {cont = false;};
            }
        // Some attempts have been made.  Return from whence you came.        
        // Consider adding a successfully claimed location to some kind of list here?
    };

    private int[] FindNearbyEmpty(int xx, int yy) {
        String[] spots = {"N", "S", "E", "W", "NE", "NW", "SE", "SW"};
        spots = Shuffle(spots);
        
        int xwin = 9001;        int ywin = 9001;
        int i = 0;
        int[] coord = ConvertCoord(xx, yy, spots[i]);
        int tempx = coord[0]; int tempy = coord[1];
        
        // First attempt, ring 1, distance 1.
        while ((xwin+ywin>9001)&&(i<spots.length)) { // OVER NINE THOUSAND?!
            // Get a coordinate within 1 step of current location
            coord = ConvertCoord(xx, yy, spots[i]);
            tempx = coord[0]; tempy = coord[1];
            // If that coordinate is empty, use it.
            if (Empty(tempx, tempy))   {xwin=tempx; ywin=tempy;}; 
            // if it is not empty, increment the index, check the next spot
            i++;       
            }// end-while

        // Next attempt, ring 2.
        // Note that loop will not even start if xwin and ywin are already valid.
        String[] spots2 = { "NNW", "NN", "NNE", "EEN", "EE", "EES", "SSW", "SSE", "SS", "WW", "WWN", "WWS"};
        if (xwin+ywin<9001) {spots2 = Shuffle(spots2); i=0;};
        while ((xwin+ywin>9001)&&(i<spots2.length)) { // OVER NINE THOUSAND?!
            // Get a coordinate within 1 step of current location
            coord = ConvertCoord(xx, yy, spots2[i]);
            tempx = coord[0]; tempy = coord[1];
            // If that coordinate is empty, use it.
            if (Empty(tempx, tempy))   {xwin=tempx; ywin=tempy;}; 
            // if it is not empty, increment the index, check the next spot
            i++;       
            }// end-while

        // Next attempt, same as the last, ring3.
        String[] spots3 = { "NNWW", "NNEE", "SSEE", "SSWW",
            "NNN", "NNNE", "NNNW", "SSSE", "SSS", "SSSW",
            "NEEE", "SEEE", "EEE", "WWW", "NWWW", "SWWW"};
        if (xwin+ywin<9001) {spots3 = Shuffle(spots3); i=0;};
        while ((xwin+ywin>9001)&&(i<spots3.length)) { // OVER NINE THOUSAND?!
            // Get a coordinate within 1 step of current location
            coord = ConvertCoord(xx, yy, spots3[i]);
            tempx = coord[0]; tempy = coord[1];
            // If that coordinate is empty, use it.
            if (Empty(tempx, tempy))   {xwin=tempx; ywin=tempy;}; 
            // if it is not empty, increment the index, check the next spot
            i++;       
            }// end-while

        // Next attempt, ring4.
        String[] spots4 = { 
            "NNNN", "NNNNE", "NNNNW", "SSSSE", "SSSS", "SSSSW",
            "NEEEE", "SEEEE", "EEEE", "WWWW", "NWWWW", "SWWWW",
            "NNWWW", "NNNWW", "NNEEE", "NNNEE", "SSEEE", "SSSEE", "SSWWW", "SSSWW"};
        if (xwin+ywin<9001) {spots4 = Shuffle(spots4); i=0;};
        while ((xwin+ywin>9001)&&(i<spots4.length)) { // OVER NINE THOUSAND?!
            // Get a coordinate within 1 step of current location
            coord = ConvertCoord(xx, yy, spots4[i]);
            tempx = coord[0]; tempy = coord[1];
            // If that coordinate is empty, use it.
            if (Empty(tempx, tempy))   {xwin=tempx; ywin=tempy;}; 
            // if it is not empty, increment the index, check the next spot
            i++;       
            }// end-while
        
        // If xwin and ywin are still 9001, there is no nearby empty point.
        int[] coords = {xwin, ywin};
        return coords;
    };
    
    public void print() {
        String temp = "";
        for (int j = 0; j < 16; j++) {
            temp = "";
            for (int i = 0; i < 16; i++) {temp = temp + claim[j][i];}
            System.out.println(temp);
            }
    }
    
    private int CountClaimed(int xx, int yy) {
        int num = 0;
        for (int j = 0; j < 16; j++) {
            for (int i = 0; i < 16; i++) {
                if (Equals(xx,yy,i,j)) {
                    num++;
                    };// if the square shares the same name as our reference point
                }//i
            }//j
        return num;
    }
    
    private int CountFreeNeighbors(int xx, int yy) {
        int neighbors = 0;
        if (Empty(xx-1, yy))   {neighbors++;};  // W
        if (Empty(xx,   yy+1)) {neighbors++;};  // N
        if (Empty(xx+1, yy))   {neighbors++;};  // E
        if (Empty(xx,   yy-1)) {neighbors++;};  // S
        // consider whether to add diagonals in or not.. it will make more weird shapes if you do
        // if (Empty(xx-1, yy+1)) {neighbors++;};  // NW
        // if (Empty(xx+1, yy+1)) {neighbors++;};  // NE
        // if (Empty(xx+1, yy-1)) {neighbors++;};  // SE
        // if (Empty(xx-1, yy-1)) {neighbors++;};  // SW
        return neighbors;
    }

    private int CountLotNeighbors(int xx, int yy) {
        int neighb = 0;
        for (int j = 0; j < 16; j++) {
            for (int i = 0; i < 16; i++) {
                if (Equals(xx,yy,i,j)) {
                    neighb = neighb + CountFreeNeighbors(i,j);
                    };// if the square shares the same name as our reference point
                }//i
            }//j
        return neighb;
    }
    
    private Boolean HasFreeNeighbors(int xx, int yy) {
        boolean check = false;
        if (CountFreeNeighbors(xx,yy)>0) {check = true;};
        return check;
    }
    
    private Boolean Empty(int xx, int yy) {
        boolean check = false;  // False by default.
        if (OutOfBounds(xx,yy)) {return false;};
        if (claim[xx][yy].equals(".")) {check=true;};  // Only if it is ".", True.
        return check;
    };
    
    private Boolean Equals(int aa, int bb, int ii, int jj) {
        boolean check = false;
        if (OutOfBounds(aa,bb)) {return false;};
        if (OutOfBounds(ii,jj)) {return false;};
        if (claim[aa][bb].equals(claim[ii][jj])) {check=true;}; 
        if (Empty(aa, bb)) {check=false;};
        if (Empty(ii, jj)) {check=false;};
        // If they are both valid and claimed by the same non-null blob, true
        // If either is null, false.
        // If either is out of bounds, false.
        // Else, false.
        return check;
    }
    
    private Boolean OutOfBounds(int xx, int yy) {
        boolean check = false;  // most things are probably in bounds
        if ((xx<minx)||(xx>maxx)) {check=true;};  // if out of bounds, True.
        if ((yy<miny)||(yy>maxy)) {check=true;};  // If out of bounds, True.
        return check;
    }

    private String[] Shuffle(String[] input) {
        for (int i = 0; i < input.length; i++) {
            int randomIndexToSwap = rand.nextInt(input.length);
            String temp = input[randomIndexToSwap];
            input[randomIndexToSwap] = input[i];
            input[i] = temp;
            }//for
        return input;
    }
    
    private int[] ConvertCoord(int xx, int yy, String dir) {
        int tempx = xx; int tempy = yy;
        if (dir.contains("N")) {tempy = yy - counthas(dir, 'N');};
        if (dir.contains("S")) {tempy = yy + counthas(dir, 'S');};
        if (dir.contains("W")) {tempx = xx - counthas(dir, 'W');};
        if (dir.contains("E")) {tempx = xx + counthas(dir, 'E');};        
        int[] coords = {tempx, tempy};
        return coords;
    }
    
    public static int counthas(String instr, char letter) {
        int c = 0;
        for (int x = 0; x<instr.length(); x++) {
            if (instr.charAt(x)==letter) {c++;};};
        return c;
        }

}
