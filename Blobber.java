package oddbird.bitsnbobs;
import java.util.Random;
import javax.swing.*;     // For the GUI
import java.awt.event.*;  // more GUI
import java.awt.image.*;  // more gui
import java.awt.*;        // more GUI
import java.util.Arrays;
import java.util.ArrayList;
import com.google.gson.Gson; // java obj <-> JSON string

@SuppressWarnings("unused")


/**
 * This sections out a grid into aesthetic blobs
 * @author wirri
 * 
 * Note to self : Refactor entire code into proper object, now that you know what's needed in each bit
 * - Data object for a LOT (contains name, list of coordinates, list of neighbors)
 * - Data object for a parcel (contains name, list of lots, list of neighboring parcels)
 * - Data object for a nation ()
 * - Data object for the whole shebang (contains grid, maxX maxY, which namegen(s) to use, )
 */
public class Blobber {
    static Random rand = new Random();
        // Gives a 16x16 grid, initializes in constructor with "BlankGrid(claim, maxx, maxy);"
    public int minx = 0;    public int maxx = 16;
    public int miny = 0;    public int maxy = 16;
    public String[][] claim = new String[maxx][maxy];
    
    // Set these to generated names eventually
    public String[] names = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "&", "$", "%"};
       
    public Blobber() {
        // initialize blank grid, Start near the center; hard-coded (7,7).
        claim = BlankGrid(claim, maxx, maxy);
        int x = 7; int y = 7;
        
        // set current point to the first name in our array, and spread its lot
        claim[x][y] = names[0];  
        Spreadlot(x,y);
        
        // Find Empty nearby point  ( Add fail condition for when x+y>9001)
        int[] coord = FindNearbyEmpty(x,y);
        x = coord[0]; y = coord[1];
        // Number of lots to generate in the clump: 
        int i = 0;
        int imax = 5;

        // Trail a random shape of lots around the grid
        while ((coord[0]+coord[1]<9001)&&(i<imax)) {
            // set current point to next name, and spread.
            claim[x][y] = names[i];  
            Spreadlot(x,y);
            // find next point, and increment cointer
            coord = FindNearbyEmpty(x,y); // point near last used point
            x = coord[0]; y = coord[1];
            i++;
            };
        
        // Next Shape; this is separate from the first one to create better overall geometry.
        // Functionally it's literally just a repeat of the previous step and could be put in a function.
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

        // Next Shape, same as the last two.
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
        // This is the same aside from where findnearbyempty is centered
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

    // Display a grid in console format when the names are single letters
    public void print() {
        String temp = "";
        for (int j = 0; j < 16; j++) {
            temp = "";
            for (int i = 0; i < 16; i++) {temp = temp + claim[j][i];}
            System.out.println(temp);
            }
    }
    
    // Functions used to create and populate a grid
    private String[][] BlankGrid(String[][] grid, int x, int y) {
        for (int j = 0; j < x; j++) {
            for (int i = 0; i < y; i++) {
                grid[i][j] = ".";
                }//i
            }//j
        return grid;
    }
    
    private void Spreadlot(int xx, int yy) {
        // blocklimit is the maximum size in blocks of the area
        int blocklimit = 2 + rand.nextInt(3); // 4 max
        if (rand.nextBoolean()) {
            blocklimit = blocklimit + rand.nextInt(2);  // 50% chance of up to 5 max
            if (rand.nextBoolean()) {
                blocklimit = blocklimit + rand.nextInt(3); // 25% chance of up to 7 max
                }; // end 8
            }; // end 6
        int blocksclaimed = CountClaimed(xx,yy);
        int neighbors = CountLotNeighbors(xx,yy);
        int i = 0;
        int numloops = 1;  // works fine when this is 2

        String[] spots = {"", "N", "S", "E", "W", "NE", "NW", "SE", "SW"};
        spots = Shuffle(spots);
        int[] coord = ConvertCoord(xx, yy, spots[0]);
        int tempx = coord[0]; int tempy = coord[1];
        int limit = blocklimit-blocksclaimed;
        
        while ((limit>0)&&(neighbors>0)&&(i<spots.length)&&(numloops>0)) {
            // Get a coordinate inside the first ring around the starting point
            coord = ConvertCoord(xx, yy, spots[i]);
            tempx = coord[0]; tempy = coord[1];
            // If that coordinate is claimed by the lot, 
            if (Equals(tempx, tempy, xx, yy))   {
                // attempt to spread from there and update blockclaim
                Spread(tempx, tempy, limit); 
                blocksclaimed = CountClaimed(xx,yy);
                limit = blocklimit-blocksclaimed;
                neighbors = CountLotNeighbors(xx,yy);
                }; 
            // adjust counters, and go to next option
            i++; // counting up through the entries in spots[]
            if (i==spots.length) {numloops--; i=0;}; 
            }// end while
    }
    
    private void Spread(int xx, int yy, int blocksleft) {
        // Only call this on non-null (not ".") blocks.
        String name = claim[xx][yy];  // set our current blob name from the coords given
        boolean cont = true; boolean hasclaimedsomething = false;
        // Put the spots in a random order.
        String[] spots = {"N", "S", "E", "W"};
        spots = Shuffle(spots);
        // Initialize the tempx tempy to the first place in Spots
        int[] coord = ConvertCoord(xx, yy, spots[0]);
        int tempx = coord[0]; int tempy = coord[1];
        // Enter the conditional loop, which breaks immediately when a condition fails
        while ((HasFreeNeighbors(xx,yy))&&(blocksleft>0)&&(cont)) {
            // BLOCK 1 / 4
            // If the first spot is empty, claim it.
            if (Empty(tempx, tempy)) {claim[tempx][tempy]=name; hasclaimedsomething=true; blocksleft--;};
            // If something has been claimed, consider stopping.
            if ((hasclaimedsomething)&&(rand.nextInt(10)>5)) {cont=false;};
            
            // BLOCK 2 / 4
            coord = ConvertCoord(xx, yy, spots[1]);
            tempx = coord[0]; tempy = coord[1];
            if (Empty(tempx, tempy)) {claim[tempx][tempy]=name; hasclaimedsomething=true; blocksleft--;};
            if ((hasclaimedsomething)&&(rand.nextInt(10)>4)) {cont=false;};

            // BLOCK 3 / 4
            coord = ConvertCoord(xx, yy, spots[2]);
            tempx = coord[0]; tempy = coord[1];
            if (Empty(tempx, tempy)) {claim[tempx][tempy]=name; hasclaimedsomething=true; blocksleft--;};
            if ((hasclaimedsomething)&&(rand.nextInt(10)>2)) {cont=false;};
            
            // BLOCK 4 / 4
            coord = ConvertCoord(xx, yy, spots[3]);
            tempx = coord[0]; tempy = coord[1];
            if (Empty(tempx, tempy)) {claim[tempx][tempy]=name; hasclaimedsomething=true; blocksleft--;};
            // Stop now, you've tried all four possibilities.
            cont = false; // break out of the conditional loop after attempting all four spots.
            }
        
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
        if (dir.contains("N")) {tempy = yy - CountHas(dir, 'N');};
        if (dir.contains("S")) {tempy = yy + CountHas(dir, 'S');};
        if (dir.contains("W")) {tempx = xx - CountHas(dir, 'W');};
        if (dir.contains("E")) {tempx = xx + CountHas(dir, 'E');};
        // if dir = "" or contains no NSEW, do nothing, return original input coords.
        int[] coords = {tempx, tempy};
        return coords;
    }
        
    // Basic information-gathering functions, during grid creation
    // Typically involves individual selected input squares or an input string
    private int CountClaimed(int xx, int yy) {
        int num = 0;
        for (int j = 0; j < maxx; j++) {
            for (int i = 0; i < maxy; i++) {
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
    
    public int CountHas(String instr, char letter) {
        int c = 0;
        for (int x = 0; x<instr.length(); x++) {
            if (instr.charAt(x)==letter) {c++;};};
        return c;
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
        if ((xx<minx)||(xx>maxx-1)) {check=true;};  // if out of bounds, True.
        if ((yy<miny)||(yy>maxy-1)) {check=true;};  // If out of bounds, True.
        return check;
    }
    
    // This section is for meta-analysis on a completed claim[][] grid.
    
    // Produce a full list of the area-names in use
    private ArrayList<String> ListLots() {
        ArrayList<String> lots = new ArrayList<String>();
        boolean onthelist = false;
        // Check every square
        for (int j = 0; j < maxx; j++) {
            for (int i = 0; i < maxy; i++) {
                // If unclaimed,
                if (!(claim[i][j].equals('.'))) {
                    // Check if it's on the list
                    onthelist = false;
                    for (String s : lots) {
                        if ((claim[i][j].equals(s))) {onthelist=true;};
                        };  // end for loop
                    if (Empty(i, j)) {onthelist=true;};
                    // if still not on the list, add it.
                    if (!onthelist) {lots.add(claim[i][j]);};  
                    };// end unclaimedcheck
                }//i
            }//j
        return lots;
    }

    // Produce a full list of the coordinates claimed by each area
    private ArrayList<Tuple>[] ListClaims() {
        // need list of names
        ArrayList<String> lots = new ArrayList<String>();
        lots = ListLots();
        // We now have a full list of all used lot names
        // lots.size() tells us how many lists we need in our arraylist
        ArrayList<Tuple>[] coords = new ArrayList[lots.size()];
        // initialize that many lists in our array of array lists
        for (int i=0; i<lots.size(); i++) {
            coords[i] = new ArrayList<Tuple>();
            }
        // Create a generic tuple to override
        Tuple temp = new Tuple(0,0);
        
        // Begin primary logic
        // Check every square
        for (int b = 0; b < maxx; b++) {
            for (int a = 0; a < maxy; a++) {
                // If the square is not claimed
                if (!(Empty(a,b))) {
                    // Check its name against every name in lots
                    for (int c = 0; c<lots.size(); c++) {
                        // When they match,
                        if (claim[a][b].equals(lots.get(c))) {
                            // add this tuple to that list
                            temp = new Tuple(a,b);
                            coords[c].add(temp);                            
                            };
                        }
                    };                
                }//a
            }//b
        return coords;
    }

    // Convert the coord[][] listarray into a string
    public String ListClaimsToString() {
        String output = "";
        ArrayList<String> names = ListLots();
        ArrayList<Tuple>[] coord = ListClaims();
        
        for (int i=0; i<names.size(); i++) {
            output = output + names.get(i) + " (";
            for (int j=0; j<coord[i].size(); j++) {
                output = output + coord[i].get(j).ToStr() + ", ";
                }// j
            output = output + " ), \n";
            } //i
        
        output = output.replaceAll("\\,  \\)", ")");

        return output;
    }
    
    // TODO: create data object containing all lots on the grid  and their associated info
    // ---- The arraylist of touples is a good start, but not what is needed.
    // TODO: return list of all neighbors of the given lot (and their directions?)
    // ---- If a N S E W (uppercase) or nw ne sw se (lowercase) direction is given for every 
    // ---- connection point, allowing repeats (ie, internal corners), that gives a strength / size
    // ---- for every border.  Count number of capitals (each=1) and lowercase (each=0.25, so "sw" = 0.5) and sum
    // ---- can give plaintext direction of lot too - if it contains N or n add "north", if it contains E or e add "east", etc.
    // ---- include error text for if neighbor is both east and west or north and south of an outjutting segment
    // TODO: 
    
/*
	public static void maingui() {
            // Rename this to main if you want to run blobber without calling it from another file.
            // Copy it to another file if you want to call blobber from there
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Blobber.createAndShowGUI();				
                    }
                });               
            }
*/
    
	public static void createAndShowGUI() {
		// make window
		JFrame frame = new JFrame("Blobber");
		frame.setSize(900, 500);
		Dimension boxsize = new Dimension(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// define window
		JPanel panel = new JPanel();
		// DisplayTroll readout1 = new DisplayTroll();
		// readout1.setPreferredSize(boxsize);
		frame.add(panel);
		// frame.add(readout1);

		// menu
		var menuBar = new JMenuBar();
		var generalMenu = new JMenu("General");
		generalMenu.setMnemonic(KeyEvent.VK_F);
		
		//general menu
		var gennamesMenuItem = new JMenuItem(" 150 names to file");
		gennamesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {buttonGenNames();}
			});
                
		var exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		exitMenuItem.setToolTipText("Exit");
		exitMenuItem.addActionListener((event) -> System.exit(0));
				
		// assemble menubar
		generalMenu.add(gennamesMenuItem);
		generalMenu.add(exitMenuItem);
		// add menus to main bar
		menuBar.add(generalMenu);
		// add menubar to frame
		frame.setJMenuBar(menuBar);		
		// Place toolbar and components
		Container contentPane = frame.getContentPane();
		// contentPane.add(readout1, BorderLayout.WEST); // .WEST, .CENTER, .EAST
		
		// display window
		frame.setVisible(true);
	}
		
	private static void buttonGenNames() {
		Gson gson = new Gson();
		String txt = new String("");
		fio fileinterface = new fio();
				
		int x = 0;
		int numbertogenerate = 150;
		while (x<numbertogenerate) {
			txt = txt + CustomTown.gen();
			txt = txt + ", ";
			x++;
			}
		fileinterface.save("names.txt",txt);
	}
    
    
}
