# BitsnBobs
Town name generator, maybe evolving into something more

To Do: 
- Give blobs names from the generator
- Create data structure to record and track information about each blob as their features grow in complexity
- Create function to list all neighbors of a given blob
- Create function to test whether two arbitrary blobs are neighbors or not.
- Begin implementing nobility, population units, guilds/families of note, nested land claims, and fealty

Changelog : 

2024-4-20 : 
- Added TODO to file itself
- Added my file input output thing stolen from the dancestry project
- Created a Tuple primitive
- Updated main bitsnbobs to have a console and GUI version, GUI currently only a stub but will be useful later
- Continued tweaking custom town names
- Increased efficiency of blobber (less loops, more optimized single-use randomization).  Tend to check every neighboring square -once-, just in a random order.
- Learned how to use ListArrays
- ListLots() lists all the lot names currently in use
- ListClaims() lists all the coordinates claimed by each area
- ListclaimsToString() dumps a summary of that info to a string, example below

Penimine, Stanesgaydale, Hailsrutcup Springs, Lytchmesby, Beckaeth Garden, Fowcup, Sittingersminster Cross, High Peterans, Windthoughspa, Easchelylade, Skegoldsnell, Lytstowe-Westerway, Brelingcombe, Wedborne, Fort Bodumbcaster, Tentershot, Rostone, Arundelencote, Faringor, Falalmzance City, Gainsnell, Bingtistone, Forchurch, Rutburgh, Clunkerne, Widlinglisle, Blandbridgeryn, Clevetondal, Haverbingess, Grayslee, West Loostable, Wincanrow Stoke, Yarruthspa, Burotsesans, Auking, Huntingey-Calworth, Didcotzance, Hexdean, Rasbrough-Dudstead, Low Thetalmport, Chelmsside, Battlessop, Cleegrange, East Worthingstaple, Yorkditch, Framgate, Halergayley, Shilend, Teloldsminster, Hemanstoke, Cambearers, Retenstol, Lymingspa, Hailerissea, Harrowaey, Peastairs, Rothering, Wandsens End, Ilkesingruth, Burnleigh, Auborough, Cuckney, Irtcaster, Drondown-by-Eshgrave Marsh, South Trebach, Leostell-Oakstow End, New Derewenhills, Totbridgeleys, Yateanstowe, Maidenthoughoft, Queenall, Ellesshaw, Staplevil, Fowehamsteinett, Haylecup, Ening, Hinckleys, Millsericester, Thornastol-Macmere, Wivenfield, Halmantrell, Olfract, Chorhampditch, Cuckerscot, Olmouth-Saxmundshot, Little Merness, Pocklesbech Rivers, Pruerizance, Saltmore, Godalmmowbach, Haltwhistlefirth, Ambleotsrowcorn, Kidderdal-Rushwade, Leighring, Cotlingt, Bedlake, Rasbernor, Fakeneth, Seathoughmoor, Newbigford, 

........H..J....
.......HHHIJJ...
.......GHDII....
......FGDDC.....
.....FFAEBCC....
......AAABB.....
.......AAB......

F ((6, 5), (5, 6), (6, 6)), 
A ((7, 6), (6, 7), (7, 7), (8, 7), (7, 8), (8, 8)), 
H ((3, 7), (2, 8), (3, 8), (4, 8), (3, 9)), 
G ((4, 7), (5, 7)), 
D ((5, 8), (4, 9), (5, 9)), 
E ((6, 8)), 
B ((6, 9), (7, 9), (8, 9), (7, 10)), 
I ((3, 10), (4, 10), (4, 11)), 
C ((5, 10), (6, 10), (6, 11)), 
J ((2, 11), (3, 11), (3, 12)), 


Initial Upload: 
- Reviewed Transport Tycoon's english town name generator, compared it with wikipedia's list of english towns and cities, did some analysis, and bodged something together.  Also spent a good while tweaking the quality-control function and percentage chances, for optimal results.
- Created a (very inefficient?) blob-generator, which will lay out irregularly shaped and sized counties / land parcels on a loose grid.  All copies of a given letter belong to the same blob, "." is unclaimed.
- Assorted small blob-related functions that will be useful later

Example namedump and blob dump: (unedited) (use a monospaced font for the blob dump)

Tillysomstairs, Wimblethorpes, High Halifaxton, High Lymingmoor, Alfold, Malgrove, Conersgate, Tiltiburn, Fleetlingdal, Oswesham, Cirenwood, Dewshallerfirth, Tickhurch-Littlehampvern, Tynelade, Meloft-Hythey, East Hitchstow, Hadstell, Ponmine, Tewkeserwade, Grimsash, Goolegate, Heconsers, Romhills, Rothson Bridge, Shepanlisle, Wigcove, Heldean, Denholmewards, Skegumbdeoft, Dagennelish, Liskelingford, Cockkirk, Sleadore-Beamamby, Eyebury, Droithamtry, Ketbersomerlish, Hartlehaven, Bilnock, Dappled Gillstead, Middle Torringsea, Cockererway, Burmborne-Lednall, Chormorshed, Sedgemersingoaks, Okealmens, Pouleafract, Kemborough, Staplecaster, Old Cartton-Covley, Blacklinking, Riskestowe, Lytchmansted, Osweshamphills, Hytheditch, Shefkemoor, Dukinlow, Verstock, Todburn, Torringangrave, Yorkpool, Lost Cleveterstor, Shankhaven, Hudton, Shieldseterlade, Thirsksay, Leyker, Clunnyport, Thorncayheroe, Stalwold, Middle Letchmerset, Invernesing, Hexstell, Hemeltestor, Wokingfastby, Telesash, Fort Tottenall, Whitedechester Market, Shieldzance, Manslingrow, Bolfastlish, Redrutheseth, Swadlincester Forum, Taviworthy Royal, Grand Worththoughlisle, Maczance, Ottespenpoint, Woodforth, Colwell, Hitchryn, Barnthorpes, Stainfastes, Evesdenthorpe, Sherchelwyn, Shallow Norcayney, Lostwithson, Bolsmowstow, Wotdean, Bromystow, Rugescombe, Rickhead, 
. . . . . . . . . . . . . . . .
. . . . . . C . L . . . . . . .
. . . . . C C E L . . . . . . .
. . . . B B B D D D I . . . . .
. . . . . A A A A H I J K K . .
. . . . . . F F G H H J . K . .
. . . . . . F . . . J J . . . .
. . . . . . . . . . . . . . . .
