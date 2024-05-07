import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class Entry {
    /* Variables */
    private final static JaroWinklerSimilarity similarityFormula = new JaroWinklerSimilarity();
    private final static String[] masterSchoolList = new String[]
            {"AandMConsolidated", "AbrahamLincoln", "ADL", "AdvancedTechnologies",
            "Albuquerque", "AliceDeal", "AliefTaylor", "Alpharetta",
            "AmericanHeritageBroward", "Andover", "AndoverCentral", "Andrews",
            "ArchbishopMitty", "Argyle", "ArizonaCollegePrep", "ASPECAcademy", "Athens",
            "Aubrey", "AustinHighSchoo", "AustinPeaceAcademy", "AveryCoonley", "Baldwin",
            "BaldwinCity", "BaltimoreCityCollege", "BarackObamaMaleLeadershipAcademy",
            "BarbersHill", "Barstow", "BASISDC", "BASISPeoria", "BASISSanAntonioShavano",
            "Bellarmine", "Bellingham", "Benedictine", "BenjaminBanneker",
            "BerkeleyPreparatorySchool", "Berkley", "BigSky", "Blackfoot",
            "BlueSpringsSouth", "BlueValley", "BlueValleyNorth", "BlueValleyNorthwest",
            "BlueValleySouthwest", "BlueValleyWest", "BonitaVista", "Borger",
            "BostonCollegiateCharterSchool", "BostonLatin", "Bozeman", "Branson",
            "BravoMedicalMagnet", "BreakDebate", "Brock", "BronxScience", "BrookfieldEast",
            "BrooklynTechnical", "BrophyCollegePrep", "Bruni", "BSAcademy", "CKMcClatchy",
            "Cabot", "Caddo", "Caldwell", "CalvertHall", "Campbell", "Campus", "Canyon",
            "CanyonCrest", "CanyonCrestAcademy", "Carlsbad", "CarrolltonSacredHeart",
            "Casady", "CathrynGormanIndependent", "CaveSpring", "CedarPark", "CedarRidge",
            "Century", "CHAMPS", "Channelview", "Chantilly", "Chanute",
            "CharlesHerbertFlowers", "CharlesPage", "CharlotteLatin", "Chattahoochee",
            "CherokeeTrail", "CherryCreek", "Chesterton", "ChicagoAG",
            "ChicagoBullsCollegePrep", "Clackamas", "ClaudiaTaylor", "ClearSprings",
            "ClovisNorth", "CMITNorth", "Colgan", "CollegePark", "CollegePrep",
            "Collegiate", "Columbia", "Coppell", "Coppell9th", "Corn", "CornerCanyon",
            "CRNorth", "CRDLSDebate", "CrimsonCliffs", "CRN", "CrossingsChristianSchool",
            "CulverCity", "CyWoods", "CypressWoods", "DallasHighlandPark",
            "DamienHSStLucyPriory", "DCPElPrimero", "DeSoto", "DebateGo", "Decatur",
            "DeerfieldAcademy", "Denmark", "DenverNorth", "DesertVista", "DodgeCity",
            "Dominion", "DoughertyValley", "Dow", "DowlingCatholic", "DowntownMagnets",
            "DrippingSprings", "DruidHills", "DukeEllington", "Dulles", "DuVal", "Eagan",
            "East", "Edcouch-Elsa", "Edina", "Edison", "EdisonIndependent",
            "EdisonPreparatory", "EdmondNorth", "EdwardMurrow", "ElDorado",
            "EleanorRoosevelt", "EliotHine", "Elite", "ElizabethLearningCenter", "Elkins",
            "Elko", "Emery", "Emporia", "Fairview", "Farmington", "Fenway",
            "FireflyAcademy", "FlowerMound", "ForestPark", "FortCollins", "FoxChapelArea",
            "Franklin", "FranklinOR", "FujianIndependent", "Galena", "Galloway",
            "Garfield", "GBS", "GeorgeBannermanDealeyMontessori", "GeorgeWashington",
            "GeorgetownDay", "GhidottiEarly", "Girard", "GlenRose", "GlenbrookNorth",
            "GlenbrookSouth", "GlendaDawson", "Glendale", "GreatBend", "GreenCanyon",
            "GreenValley", "GreenhillSchool", "GuangdongTigersIndependent", "Gunn",
            "Hallsville", "Hardy", "HARTS", "HarvardWestlake", "Hawken", "Hayden",
            "Head-Royce", "Hebron", "Heights", "Hendrickson", "HeritageHallSchool",
            "Hickman", "Highland", "HighlandPark", "Hightower", "Hillcrest",
            "HolyTrinityCatholic", "Hoover", "HoustonMemorial", "HSCSD", "Hume-Fogg",
            "HuntersLane", "HuntingtonPark", "Hutchinson", "HutchinsonKS", "IdahoFalls",
            "IDEATRESLAGOS", "IndianHills", "Interlake", "IowaCity", "IowaCityWest",
            "IowaColonyHighSchool", "IsidoreNewmanSchool", "IVA", "JamesLogan", "Jasper",
            "JeffersonCity", "Jenks", "JesuitDallas", "JohnPaulII", "JohnsCreek",
            "Johnson", "Jones", "JonesCollegePrep", "Joplin", "Joshua", "JPG",
            "JunctionCity", "KatyTaylor", "KentDenverSchool", "KenwoodAcademy",
            "KetteringMiddle", "KingCollegePrep", "KippIndyLegacy", "KippKingCollegiate",
            "KippOK", "KuwiwiDebateAcademy", "LaCrosseCentral", "LaReina",
            "LadueHortonWatkins", "LakeBalboaCollegePrep", "LakeTravis", "LakeCity",
            "LakevilleNorth", "LaneTechCollegePrep", "LanghamCreek", "Lansing",
            "LarchmontCharterSchool", "Laurel", "LawMagnet", "Lawrence",
            "LawrenceFreeState", "LCAnderson", "Leander", "LeesSummitWest", "Legacy",
            "Leland", "LeonMGoldstein", "Lexington", "LiberalArtsAndScienceAcademy",
            "Liberty", "LibertyHill", "LibertyIA", "Lincoln", "LincolnEast", "LincolnOR",
            "LincolnPark", "LincolnPrep", "LittleRockCentral", "LOGYC", "LongBranch",
            "LosOsos", "LouisDBrandeis", "LouisaCounty", "Lovejoy", "Lowell", "Lubbock",
            "Madison", "MadisonCentral", "Magnolia", "MaineEast", "Maize", "Mamaroneck",
            "Manhattan", "Manvel", "Maplewood-RichmondHgts", "MaristSchool", "McClintock",
            "McDonogh", "McDowell", "McKinleyTechnology", "McPherson", "Meadows",
            "Melissa", "Midway", "MillValley", "MillardNorth", "MillardWest",
            "MinneapolisSouth", "MiraLoma", "ModernBrain", "Monett", "Monterey",
            "MontgomeryBellAcademy", "MontgomeryBellLM", "MountainHomeHSID", "Munster",
            "NewDesignsCharterSchool-UniversityPark", "NewMission", "NewTrier",
            "NewarkScience", "NewarkTech", "Newton", "NilesNorth", "NilesWest", "NLD",
            "NorthAllegheny", "NorthAtlanta", "NorthBrowardPrep", "NorthLakeEarlyCollege",
            "Northfield", "Northridge", "NorthsideCollegePreparatory",
            "NorthstarAcademyHighSchool-Newark", "Northview", "Northwood", "NotreDame",
            "Novi", "NSUUniversitySchool", "OakGrove", "OaklandTech", "Oakton", "OBryant",
            "OceanLakes", "Odessa", "OES", "Okemos", "OlatheEast", "OlatheNorth",
            "OlatheNorthwest", "OlatheWest", "Opus", "OreCity", "OSM", "OverlandParkWest",
            "PaceAcademy", "Paola", "Paradise", "ParkHillSouth", "Parkrose",
            "PembrokeHill", "Peninsula", "Penn", "PennHig", "Pennsbury",
            "PeppyPilotsIndependent", "Perry", "Phelps", "PineCrestSchool", "PineTree",
            "Pittsburg", "PittsburghCentralCatholic", "Pocatello", "PolytechnicSchool",
            "PortOfLosAngeles", "ProspectHillAcademyCharterSchool", "Prosper",
            "QuarryLaneKW", "QuestAcademy", "Reagan", "Rigby", "RockBridge", "RockHill",
            "RonaldReaganCollegePrep", "Roosevelt", "RosemountMN", "RowlandHall",
            "RufusKing", "SacramentoUrbanDebateLeague", "SacredHeartCathedralPrep",
            "SageHill", "Salado", "SaladoSW", "SalinaSouth", "SamBarlow",
            "SanAngeloCentral", "SanMarino", "SandraDayOConnor", "SarahEGoode", "Seaman",
            "SFLincoln", "Shadyside", "ShawneeHeightsHighSchoo", "ShawneeMissionEast",
            "ShawneeMissionSouth", "ShawneeMissionWest", "Shelley", "Sidwell",
            "SilverCreek", "SLCIndependent", "SmithsonValley", "SMNW", "Solon", "Solorio",
            "Somerville", "SonomaAcademy", "SouthEugene", "SouthGate", "SouthShore",
            "SouthHills", "SPASH", "SpringHill", "SpringHillISD", "SpringfieldCatholic",
            "SpringfieldCentral", "StCroixPrep", "StFrancis", "StIgnatius", "StJames",
            "StJohns", "StLukesSchool", "StMarks", "StMarys", "StPaulCentral",
            "STEAMLegacy", "StephenFAustin", "STISDWorldScholars", "Stockdale",
            "StrakeJesuitCollegePreparatory", "StuartHobson", "Stuyvesant",
            "SuccessAcademyHS-LiberalArts", "SummerCreekTX", "SummerTX", "SumnerKS",
            "Sunnyvale", "SWW", "TAGMagnet", "TaipeiAmerican", "Talkington", "Tartan",
            "TCRobersonNC", "TexasAcademyOfMathematicsAndScience", "TheAltamontSchool",
            "TheAwtyInternationalSchool", "TheKinkaidSchool", "TheQuarryLaneSchool",
            "TheWoodlandsCollegePark", "TheodoreRoosevelt", "ThomasJeffersonHSST",
            "ThomasKellyCollegePrep", "TitanDebate", "Tomball", "TomballMemorial",
            "Tonganoxie", "Topeka", "TopekaSeaman", "TravisBBryan",
            "TrinidadGarzaEarlyCollege", "Truman", "TrumanInd",
            "TulsaSchoolOfArtsAndSciences", "TwolvesIndependent", "UnionHighschool",
            "UniversityHighSchoolFresnoState", "UniversityOfChicagoLab",
            "UniversitySchool", "USN", "VladovicHarborTeacherPreparationAcademy",
            "VonSteuben", "WalterPaytonCollegePrep", "Washburn", "WashburnRural",
            "Washington", "WashingtonPrep", "WashingtonTechnicalMagnetSchool", "Wayzata",
            "Weatherford", "Wellington", "WestAnchorage", "WestCampus", "WestELPSLC",
            "WestHighSchoolSLC", "WestTexas", "Westlake", "Westminster", "Westmoore",
            "Westview", "Westwood", "WhitneyYoung", "WichitaEast", "WichitaNorthwest",
            "WichitaSoutheast", "WillRogers", "Willamette", "Wimberley",
            "WinstonChurchill", "WinstonChurchillMiddle", "Woodnorth", "WoodwardAcademy",
            "Wooster", "Worland", "WylieEGroves", "WyomingVirtualAcademy", "YerbaBuena"};

    /* Parsing Methods */
    public static String parseCode(String inputCode) {
        String school;
        double maxSimiliarity = 0.0;
        double tempSimilarity;

        // simplifies the input code
        inputCode = inputCode.replaceAll("[^A-Za-z]", "");

        // separates the school and the code
        school = inputCode.substring(0, inputCode.length() - 2);

        // loops through every possible school name
        for (String tempSchool : masterSchoolList) {
            // applies the similarity formula
            tempSimilarity = similarityFormula.apply(inputCode, tempSchool);

            // if the school is more similar, replace it
            if (tempSimilarity > maxSimiliarity) {
                maxSimiliarity = tempSimilarity;
                school = tempSchool;
            }
        }

        return school;
    }

    public static String parseEntry(String inputEntry) {
        // simplifies the input entry
        inputEntry = inputEntry.replaceAll("[^A-Za-z&-]", "");

        // finds the first two letters of each debater's name
        String debater1 = inputEntry.substring(0, 2);
        String debater2 = inputEntry.substring(inputEntry.indexOf("&") + 1, inputEntry.indexOf("&") + 3);

        return debater1 + debater2;
    }
}