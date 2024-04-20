/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oddbird.bitsnbobs;

import java.util.Random;

/**
 *
 * @author wirri
 */
public class CustomTown {
    static Random rand = new Random();

    public static String gen() {
        String name = "";
        // Prefix
        name = cus_prefix[rand.nextInt(cus_prefix.length)];
        
        // Main cluster
        name = name + cluster();
        
        // Secondary Optional Cluster
        // if the cluster is .x.x.x or shorter, add another very short cluster?
        if ((rand.nextInt(10)>8)&&(CountHas(name, '.')<3)) {
            // optional clitic
            name = name + "-" + clitic[rand.nextInt(clitic.length)];
            name = name + "-" + cus_cluster_core[rand.nextInt(cus_cluster_core.length)];
            if ((rand.nextInt(10)>9)) {name = name +  "." + cus_cluster_tail[rand.nextInt(cus_cluster_tail.length)];};
            name = name + "." + cus_sitetype[rand.nextInt(cus_sitetype.length)];
            };   
            
        // Suffix
        name = name + "." + cus_suffix[rand.nextInt(cus_suffix.length)];
        
        // Fix
        name = ReplaceEnglishWords(name);
        return name;
    };

    public static String cluster() {
        String name = "";
        boolean moretail = true;
        // Core 
        name = name + "." + cus_cluster_core[rand.nextInt(cus_cluster_core.length)];
        // only want a cluster tail some of the time.  These checks are cumulative.
        if ((rand.nextInt(100)>40)) {moretail=false;};  // Continue 40% of the time
        if (moretail) {name = name +  "." + cus_cluster_tail[rand.nextInt(cus_cluster_tail.length)];};
        if ((rand.nextInt(100)>10)) {moretail=false;};  // Continue 10% of the time
        if (moretail) {name = name +  "." + cus_cluster_tail[rand.nextInt(cus_cluster_tail.length)];};
        // ...but you can have up to two if you're Really lucky.  Three gets silly.
        name = name + "." + cus_sitetype[rand.nextInt(cus_sitetype.length)];
        return name;
    };

    static String[] clitic = {
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "in", "under", "upon", "on", "over", "by"
        };
    
    static String[] cus_prefix = { 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        // Common ones
        "Great ", "Greater ", "High ", "New ", "Little ", "Lesser ", "Low ", "Old ",
        "Great ", "Greater ", "High ", "New ", "Little ", "Lesser ", "Low ", "Old ",
        "Great ", "Greater ", "High ", "New ", "Little ", "Lesser ", "Low ", "Old ",
        "Great ", "Greater ", "High ", "New ", "Little ", "Lesser ", "Low ", "Old ",
        "North ", "South ", "East ", "West ", "Central ", "Grand ", "Middle ",
        "North ", "South ", "East ", "West ", "Central ", "Grand ", "Middle ",
        // Rare ones
        "Fort ", "Saint ", "San ", "Chipping ", "Bright ", "Broad ", "Long ",
        "Short ", "Dappled ", "Narrow ", "Grim ", "Deep ", "Shallow ", "Lost ", "Last ", 
        
        // "King's ", "Prince's ", "Queen's ", "Bishop's ", 
    };

    static String[] cus_suffix = {
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
        " Abbey",      " Bay",        " Beeches",    " Bridge",     " Cross",      " Common",
        " Castle",     " City",       " Downs",      " End",        " Falls",
        " Forum",      " Garden",     " Green",      " Heath",      " Hill",       " Marsh",
        " Major",      " Minor",      " Market",     " Park",       " Rivers",     " Ridge",
        " Springs",    " Royal",      " Stoke"
        /* 
            (-st-)
            edmunds, annes, mary
            
            // on-the-wold, on-sea, on-the-naze,  
        */ 
    };
   
    
    static String[] cus_cluster_core = {
        "Ab", "Aber", "Acc", "Ad", "Al", "Alb", "Ald", "Alf", "Alfre", 
        "Ann", "Am", "Amble", "Amers", "Ames", "Ampt", "And", "Apple", "Arundel", "As", 
        "Ash", "Ashbur", "Ashing", "Ath", "Ather", "Attle", "Au", "Auck", "Ax", 
        "Avon", "Ayles", "Ayls", "Ba", "Bake", "Bal", "Ban", "Bar", "Barn", "Barns", 
        "Barnt", "Bas", "Basil", "Bat", "Bath", "Battle", "Baw", "Be", "Bea", "Beck", "Bed", 
        "Bee", "Bel", "Bent", "Ber", "Berk", "Bev", "Bewd", "Bex", "Bi", "Big", 
        "Bil", "Bing", "Birm", "Black", "Bland", "Blaz", "Bletch", "Blyth", "Bo",
        "Bod", "Bog", "Bol", "Bols", "Bor", "Bot", "Bourne", "Brack", "Brad", 
        "Brain", "Brent", "Bri", "Brid", "Bridg", "Brier", "Brig", "Brigg", "Brigh", 
        "Bright", "Brix", "Broad", "Brom", "Broms", "Bromy", "Brown", "Bre", "Buck", 
        "Buckfast", "Bucking", "Bud", "Bude", "Bun", "Bunt", "Bur", "Burg", "Burn", 
        "Burnt", "Bux", "Cai", "Cal", "Calne", "Cam", "Camber", "Camp", "Can", "Canter", 
        "Car", "Carn", "Cart", "Carter", "Cary", "Chadder", "Chag", "Charl", 
        "Chat", "Chelms", "Chel", "Chelten", "Ches", "Chester", "Chi", "Chip", "Chippen", 
        "Chor", "Chris", "Cinder", "Ciren", "Clac", "Cleck", "Cleckhea", "Clee", 
        "Cleve", "Clit", "Clun", "Coal", "Cock", "Cocker", "Cog", "Cogges", "Col",
        "Cole", "Colne", "Con", "Congle", "Conis", "Cor", "Corn", "Cot", "Cov", "Cove",
        "Coven", "Cow", "Cram", "Cran", "Craw", "Cray", "Cred", "Credi", "Crew", "Crewe", 
        "Crom", "Crow", "Crowle", "Croy", "Cuck", "Cull", "Cullomp", "Cum",
        "Dag", "Dagen", "Dal", "Dar", "Darl", "Darling", "Dart", "Darwen", "Dav", 
        "Daven", "Daw", "Deal", "Deep", "Den", "Denholme", "Der", "Dere", "Des",
        "Devil", "Deviz", "Dews", "Did", "Didcot", "Din", "Dinning", "Diss", "Don", "Dor",
        "Down", "Drif", "Droit", "Dron", "Droyls", "Dud", "Dukin", "Dun", "Dunmow", 
        "Dur", "Durs", "Eal", "Ear", "Eas", "Easing", "Eg", "El", "Elles", "Ely",
        "En", "Ep", "Epp", "Esh", "Eves", "Ex", "Exeter", "Eye", "Fails", "Fair", 
        "Fak", "Faken", "Fal", "Far", "Fare", "Faring", "Farn", "Fav", "Favers", 
        "Feather", "Fel", "Felix", "Fen", "Fenny", "Fern", "Fer", "Ferry", "Fi", 
        "Fil", "Fin", "Fle", "Fleet", "Flit", "Fol", "Folk", "Folke", "For", "Ford", 
        "Fow", "Fox", "Fram", "Frin", "Frods", "Frome", "Gains", "Gates", "Gill",
        "Gilling", "Glas", "Glaston", "Glos", "Glou", "Go", "God", "Godalm",
        "Godman", "Goole", "Gos", "Grange", "Grant", "Grav", "Grays", "Grim", "Grims", 
        "Grin", "Guild", "Guis", "Hack", "Had", "Hail", "Hails", "Hal", "Hale", "Hales", 
        "Hali", "Halifax", "Hallis", "Halt", "Haltwhistle", "Hand", "Har", "Harpen", 
        "Harrow", "Hartle", "Hasle", "Hast", "Hat", "Hav", "Havant", "Haver", 
        "Haw", "Hay", "Hayle", "He", "Hea", "Heath", "Heb", "Hel", "Hem", "Hemel", 
        "Hemp", "Hems", "Hen", "Here", "Herne", "Hert", "Hes", "Hessle", "Het", 
        "Hex", "Hey", "Hinck", "Hitch", "Hoddes", "Holm", "Hols", "Honi", "Hor", 
        "Horn", "Horne", "Hors", "Hough", "Houns", "Hove", "Hoy", "Huck", "Hud",
        "Hudders", "Hugh", "Hull", "Hun", "Hunger", "Hunstan", "Hunt", "Hunting", 
        "Hyde", "Hythe", "Il", "Ilfra", "Ilk", "Ilke", "Inver", "Ips", "Irth", "Irthing", 
        "Iv", "Ivy", "Jar", "Keigh", "Kemp", "Ken", "Kenil", "Kes", "Ket", "Ketter", 
        "Keyns", "Kid", "Kidder", "Kids", "Kill", "Killing", "Kim", "Kimber", "Kin", "King",
        "Kir", "Kirk", "Knares", "Knot", "Knotting", "Knuts", "Lan", "Launce", 
        "Leam", "Leaming", "Leather", "Lech", "Led", "Leed", "Leek", "Lei", "Leigh", 
        "Leo", "Letch", "Lew", "Lewis", "Ley", "Lich", "Lin", "Liske", "Littlehamp", 
        "Liver", "Liz", "Lo", "Loft", "Lon", "Long", "Loo", "Lostwith", "Lough", 
        "Louth", "Lu", "Lud", "Lutter", "Lyd", "Lydd", "Lyme", "Lyming", "Lyn", "Lynn", 
        "Lyt", "Lytch", "Mable", "Mac", "Maccles", "Mag", "Mai", "Maid", "Maiden", "Mal", 
        "Malmes", "Malt", "Man", "Manning", "Mans", "Mar", "March", "Mark", "Marl", 
        "Mary", "Mat", "Mel", "Melks", "Mer", "Mex", "Mid", "Mil", "Mills", "Mine", 
        "Mol", "Mon", "Mor", "More", "Moreton", "Morp", "Nail", "Nails", "Nan", 
        "Nant", "Ne", "Need", "Nel", "Neots", "New", "Newbig", "Nor", "Norman", 
        "Northaller", "Notting", "Nunea", "Oak", "Oke", "Ol", "Old", "Oll", "Oller", 
        "Ong", "Ongar", "Orms", "Orp", "Orping", "Oss", "Oswes", "Ot", "Ott", 
        "Oundle", "Out", "Ox", "Pad", "Paign", "Pains", "Pea", "Peace", "Pen", 
        "Peni", "Per", "Pet", "Peter", "Peters", "Pick", "Picker", "Pit", "Ply", "Pock", 
        "Pockling", "Pol", "Pole", "Polti", "Pon", "Ponte", "Pool", "Poole", "Port", 
        "Portis", "Ports", "Pot", "Pott", "Poul", "Pre", "Prud", "Pru", "Pud", 
        "Queen", "Quin", "Ram", "Rams", "Ras", "Rasen", "Raunds", "Ray", "Read", 
        "Red", "Redcar", "Redruth", "Rei", "Ret", "Rich", "Rick", "Rid", "Ride", "Ring", "Rip", 
        "Ripon", "Ris", "Ro", "Roch", "Rom", "Ross", "Roth", "Rother", "Row", "Roy",
        "Rug", "Ruge", "Rig", "Run", "Rush", "Rut", "Ryde", "Rye", "Sal", "Sale", 
        "Salis", "Salt", "San", "Sand", "Sandy", "Saw", "Sax", "Saxmund", "Scar", 
        "Scun", "Sea", "Sedge", "Sel", "Set", "Settle", "Shaf", "Shaft", "Shank", 
        "Sheer", "Shef", "Shep", "Sher", "Shield", "Shields", "Shil", "Ship", "Shore", 
        "Shrews", "Shrop", "Sid", "Sit", "Sitting", "Skeg", "Skel", "Skelmers", 
        "Skip", "Slea", "Slough", "Smeth", "Snod", "So", "Sod", "Somer", "Sout", 
        "Spald", "Spenny", "Spils", "Staf", "Stain", "Stal", "Staly", "Stam", 
        "Stan", "Staple", "Staun", "Stave", "Steven", "Stock", "Stocks", "Stony",
        "Stort", "Stot", "Stour", "Strat", "Strath", "Stre", "Stret", "Stroud", "Sud", 
        "Sunder", "Sut", "Swad", "Swadlin", "Swald", "Swaff", "Swan", "Swin", "Tad", 
        "Tam", "Taun", "Tavi", "Teign", "Tel", "Ten", "Tender", "Tenter", "Tet",
        "Tewkes", "Thame", "Thatch", "Thax", "Thet", "Thirsk", "Thong", "Thorn", 
        "Thorna", "Tick", "Til", "Tilly", "Tip", "Tiver", "Tod", "Todmor", "Tor",
        "Torring", "Tot", "Totten", "Tow", "Tre", "Tru", "Tun", "Twicken",
        "Tyne", "Uck", "Ulver", "Up", "Upping", "Utto", "Ux", "Vent", "Ver", 
        "Wad", "Wake", "Wal", "Walla", "Walling", "Walm", "Wals", "Walt", "Waltham",
        "Wands", "Want", "War", "Ware", "Wash", "Washing", "Wat", "Watch", "Wath", 
        "We", "Wed", "Wednes", "Weigh", "Wel", "Welling", "Wells", "Wem", "Wen", "Wend",
        "Wester", "Wes", "Westhough", "Wether", "Wey", "Wha", "Whi", "whit",
        "White", "Wick", "Wid", "Wig", "Wigan", "Willen", "Wim", "Wimble", "Win",
        "Wincan", "Winch", "Winchel", "Wind", "Winds", "Winder", "Wins", "Wis", "Wit",
        "With", "Withern", "Wiven", "Wo", "Woking", "Wol", "Wolver", "Womb", "Wood", 
        "Wool", "Woot", "Wor", "Work", "Worth", "Worm", "Wot", "Wy", "Wymond", 
        "Yacht", "Yar", "Yarm", "Yate", "Yea", "Yeo", "York", "Yox", 

    };
    static String[] cus_cluster_tail = {
        "alm", "an", "ber", "ber", "bing", "ble", "bridge", "bridge", "car", "cay", 
        "chel", "cons", "cot", "de", "den", "dulph", "ea", "eham", "el", "en", "en", "en",
        "er", "er", "er", "er", "er", "er", "er", "eri", "eris", "ern", "ers", "ers",
        "es", "es", "es", "eter", "fast", "fax", "fra", "gay", "haller",
        "ham", "hamp", "hamp", "hea", "holme",
        "i", "il", "il", "ing", "ing", "ing", "ing", "is",
        "ke", "la", "le", "le", "le", "les", "les",
        "lin", "ling", "ling", "ling", "ling", "ling",
        "man", "man", "mans", "mers", "mes", "meth", "mond", "mor", "mow", "mund",
        "n", "ne", "nea", "ninch", "ny", "olds", "on", "ots", "oxet",
        "patria", "pen", "ping", "ring", "ro", "row", "ruth", "som", "som", "somer",
        "stan", "stein", "te", "ten", "ten", "though", "ti", "tle", "ton",
        "umb", "wat", "wen", "with", "y"
    };

    static String[] cus_sitetype = {
        "age", "all", "ans", "ard", "ark", "ash", "bach", "bar", "bech", "berg",
        "borough", "borne", "bourne", "bridge", "brook", "brough",
        "burg", "burgh", "burn", "bury", "by", "cambe", "caster",
        "chester", "cester", "church", "coln", "combe", "corn", "cote", "cove", "cup",
        "dal", "dale", "dean", "del", "den", "ditch", "don", "dock", "dore", "down",
        "end", "ens", "ent", "er", "ers", "ery", "et", "eth", "ett", "es", "ess", "ey", 
        "field", "firth", "fold", "ford", "forth", "fract", "gate", "grange", "grave", "grove",
        "hall", "ham", "hattan", "haven", "head", "heroe", "hill", "hills",
        "hoe", "house", "hull", "hunt", "hurst", "iel", "in", "ing", "ings", 
        "kern", "kerne", "king", "kirk", "lace", "lade", "land", "lake", "lee", "leigh",
        "ley", "leys", "lish", "lisle", "lock", "low",
        "manby", "mere", "mine", "minster", "mond", "moor", "more", "mouth",
        "nall", "nell", "ness", "ney", "nock", "nor",
        "oaks", "oft", "over", "pool", "point", "port", "quay",
        "ring", "rith", "row", "ruth", "ryn", "say", "shaw", "scot", "sea", "sey", "shed",
        "shire", "shore", "shot", "side", "son", "sop", "spa", "stable",
        "stairs", "staple", "stead", "sted", "stell", "stock", "stoke",
        "stol", "ston", "stone", "stor", "stow", "stowe", "thorne", "thorpe", "thorpes",
        "thwaite", "ton", "town", "tree", "trell", "try", "vern", "vil", "ville",
        "wade", "wall", "wards", "way", "well", "went",
        "wich", "wick", "wold", "wood", "whistle", "worth", "worthy", "wyn", "zance", 
    };
    

    private static String ReplaceEnglishWords(String name) {      
        name = name.replace("whistle.ing", "whistling");
        name = name.replace("bridge.ing", "bridging");
        name = name.replace("o.i", "ori");
        name = name.replace("i.i", "i");
        name = name.replace("a.i", "ari");
        name = name.replace("u.i", "uti");
        name = name.replace("e.i", "esi");
        name = name.replace("e.et", "et");
        name = name.replace("e.e", "e");
        name = name.replace("a.a", "a");
        name = name.replace("o.o", "o");
        name = name.replace("u.u", "u");
        name = name.replace("ake.ker", "aker");
        name = name.replace("th.c", "tc");
        name = name.replace("th.k", "tk");
        name = name.replace("th.q", "tq");
        name = name.replace("ne.ne", "ne");
        name = name.replace("cl.k", "cle");
        name = name.replace("ing.ing", "ing");
        name = name.replace("ing.ling", "illing");
        name = name.replace("ing.bing", "ing");
        name = name.replace("ing.ring", "irring");
        name = name.replace("ing.y", "inge");
        name = name.replace("ith.le", "it.le");
        // Remove section barriers here
        name = name.replace(".", "");
        name = name.replace("--", "-");
        // Syllable tweaking and slurring
        name = name.replace("yy", "y");
        name = name.replace("armring", "arming");
        name = name.replace("eend", "end");
        name = name.replace("eer", "er");
        name = name.replace("eey", "ey");
        name = name.replace("einingent", "eining");
        name = name.replace("ingent", "ingt");
        name = name.replace("ingn", "ing");
        name = name.replace("stanstan", "stain");
        name = name.replace("ccc", "cc");
        name = name.replace("sss", "ss");
        name = name.replace("ttt", "tt");
        name = name.replace("lll", "ll");
        name = name.replace("ppp", "pp");
        name = name.replace("mpp", "mp");
        name = name.replace("ggg", "gg");
        name = name.replace("tlele", "tle");
        name = name.replace("blele", "ble");
        name = name.replace("clele", "cle");
        name = name.replace("ereren", "erren");
        name = name.replace("rlling", "rling");
        name = name.replace("aa", "a");
        name = name.replace("nbl", "mbl");
        name = name.replace("mdl", "ndl");
        name = name.replace("rmt", "rnt");
        name = name.replace("rnp", "rmp");
        name = name.replace("edmes", "ednes");
        name = name.replace("astti", "asti");
        name = name.replace("ww", "w");
        name = name.replace("cotwat", "cothwait");
        name = name.replace("ledulph", "dulph");
        name = name.replace("heakehoe", "heak");
        name = name.replace("olalm", "olm");
        name = name.replace("tss", "ss");
        name = name.replace("sst", "st");
        name = name.replace("ssp", "sp");
        name = name.replace("ckesdal", "cksdal");
        name = name.replace("etping", "etting");
        name = name.replace("yss", "ys");
        name = name.replace("linglish", "lisnes");
        name = name.replace("frafirth", "frith");
        name = name.replace("ckc", "ck");
        name = name.replace("ckaya", "cka");
        name = name.replace("tchth", "ckth");
        name = name.replace("eern", "ern");
        name = name.replace("cotshot", "cossot");
        name = name.replace("ingdet", "ingt");
        name = name.replace("erera", "erra");
        name = name.replace("mormouth", "mort");
        name = name.replace("sersing", "serring");
        name = name.replace("eerth", "erth");
        name = name.replace("angees", "anges");
        name = name.replace("erser", "ers");
        name = name.replace("somsop", "somp");
        name = name.replace("hamhamp", "ham");
        name = name.replace("hamphamp", "hamp");
        name = name.replace("hamphamp", "hamp"); // "Littlehamphamphampdore"
        name = name.replace("outn", "utn");
        name = name.replace("mowman", "mown");
        name = name.replace("ppd", "pd");
        name = name.replace("eae", "ae");
        name = name.replace("eage", "edge");
        name = name.replace("Abeb", "Abb");
        name = name.replace("bbridge", "bbedge");
        name = name.replace("hamcoln", "hancoin");
        name = name.replace("beldulph", "bdolph");
        name = name.replace("deldulph", "ddolph");
        name = name.replace("beleys", "bleys");
        name = name.replace("erark", "ark");
        name = name.replace("erern", "erren");
        name = name.replace("Aberi", "Berri");
        name = name.replace("enern", "enner");
        name = name.replace("Abill", "Bill");
        name = name.replace("withlingess", "withlings");
        name = name.replace("singess", "sings");
        name = name.replace("bingping", "ping");
        name = name.replace("ccb", "cb");
        name = name.replace("ccd", "cd");
        name = name.replace("ccf", "ckf");
        name = name.replace("ccg", "cg");
        name = name.replace("ccl", "cl");
        name = name.replace("ccm", "cm");
        name = name.replace("ccn", "cn");
        name = name.replace("ccp", "cc");
        name = name.replace("ccsh", "csh");
        name = name.replace("ccs", "cs");
        name = name.replace("cct", "ct");
        name = name.replace("ccw", "cw");
        name = name.replace("hamern", "hammen");
        name = name.replace("neano", "nenno");
        name = name.replace("lbb", "lb");
        name = name.replace("bingage", "binage");
        name = name.replace("lbden", "lben");
        name = name.replace("lbking", "lking");
        name = name.replace("lbny", "lny");
        name = name.replace("berteber", "berter");
        name = name.replace("lbte", "bbe");
        name = name.replace("bbea", "bbe");
        name = name.replace("ldbe", "bbe");
        name = name.replace("ldcol", "ldcoi");
        name = name.replace("ldling", "lling");
        name = name.replace("chch", "ch");
        name = name.replace("nb", "mb");
        name = name.replace("yea", "i");
        name = name.replace("berer", "bearer");
        name = name.replace("ereri", "ari");
        name = name.replace("arard", "arrad");
        name = name.replace("thh", "th");
        name = name.replace("ingber", "eber");
        name = name.replace("berbar", "barb");
        name = name.replace("dnm", "dn");
        name = name.replace("ssb", "sb");
        name = name.replace("phh", "ph");
        name = name.replace("artping", "arping");
        name = name.replace("erery", "erry");
        name = name.replace("silsted", "sild");
        name = name.replace("mbcamb", "mb");
        name = name.replace("ssc", "sc");
        name = name.replace("fff", "ff");
        name = name.replace("uper", "upper");
        name = name.replace("aiea", "ars");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
        name = name.replace("", "");
          return name;
    }
    
    
    private static int CountHas(String instr, char letter) {
            int c = 0;
            for (int x = 0; x<instr.length(); x++) {
                if (instr.charAt(x)==letter) {c++;};};
            return c;
    };
    
}
