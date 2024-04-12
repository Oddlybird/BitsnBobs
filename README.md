# BitsnBobs
Town name generator, maybe evolving into something more

To Do: 
- Give blobs names from the generator
- Create data structure to record and track information about each blob as their features grow in complexity
- Create function to list all neighbors of a given blob
- Create function to test whether two arbitrary blobs are neighbors or not.
- Begin implementing nobility, population units, guilds/families of note, nested land claims, and fealty

Changelog : 


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
