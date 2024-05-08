# Tournament Simulator

This program will return statistics and expected results of a debate tournament given certain input information. It will simulate preliminary & elimination rounds and generate elimination brackets based off of tournament size and characteristics, as well as create OpenCaselist wiki links given a list of teams.

This program was written in the context of a TOC-division policy debate tournament and may not apply to other forms of debate.

## Installation

The program is written in Java and can run in any Java compiler. It was written in and currently configured for IntellJ IDEA. Download the ZIP file and open it where you would like to.

## Usage

Simply run the 'Main.java' and the program will provide you with a directory in the terminal. Input numbers to select the class you would like to run.

The directory:
```
—————————————————————————————————————————————————————————
Tournament Simulator
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Starting guide:
0 --> Help Menu
1 --> Preliminary Round Statistics
2 --> Elimination Round Statistics
3 --> Elimination Bracket Generator
4 --> OpenCaselist Wiki Generator
5 --> Reset Tournament
-1 -> Terminate Program
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Enter directory:
```

The help menu:
```
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Help menu:
0 --> Regenerates this menu in its entirety.
1 --> Receives tournament values and calculates expected 
      statistics for all preliminary rounds. These stats 
      include records and pullups.
2 --> Receives tournament values and calculates expected 
      statistics for all elimination rounds. These stats 
      include the size of the first elimination round 
      and teams breaking, screwed, debating and byed.
3 --> Receives tournament values and calculates the full 
      elimination bracket and expected elimination size.
4 --> Receives team and entry names and will return 
      OpenCaselist links to all team wikis, by 
      automatically matching school aliases and debater 
      names by initials.
5 --> Resets the current tournament values previously 
      inputted and saved in the program. Run this 
      before entering new data. Automatically ran 
      when booting the program.
-1 -> Ends the current program immediately. Your data 
      is still viewable and copyable in the terminal.
Note: Type 'n/a' when prompted to input team data to 
      auto-fill team codes, entry names, and skills.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
```

## Contributing

If you would like to, please feel free to submit a pull request to improve this code. It was written with limited knowledge of Java and quite likely has errors or and opaque operations that can be clarified.
This program was written to be consistent across directories. Accuracy is appreciated, but not the sole purpose of the code.

## Examples

The following are example outputs based on data from the 2024 Tournament of Champions.

Preliminary & elimination round statistics:
```
—————————————————————————————————————————————————————————
New tournament values:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Number of teams debating: 92
Number of prelim rounds: 7
Number of elim rounds: 5
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
List of team codes: 
n/a
List of entry names: 
n/a
List of team elos: 
n/a
—————————————————————————————————————————————————————————
Preliminary round statistics:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 0-7: 1.3
Expected teams 1-6: 5.5
Expected teams 2-5: 14.9
Expected teams 3-4: 24.3
Expected teams 4-3: 24.2
Expected teams 5-2: 14.9
Expected teams 6-1: 5.6
Expected teams 7-0: 1.3
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected pullups round 3:
From 0-2 to 1-1: 1
From 1-1 to 2-0: 1
Expected pullups round 4:
From 0-3 to 1-2: 1.9
From 1-2 to 2-1: 2.9
From 2-1 to 3-0: 1.8
Expected pullups round 5:
From 0-4 to 1-3: 0.5
From 1-3 to 2-2: 0.6
From 2-2 to 3-1: 0.6
From 3-1 to 4-0: 0.4
Expected pullups round 6:
From 0-5 to 1-4: 1.5
From 1-4 to 2-3: 2.8
From 2-3 to 3-2: 3.5
From 3-2 to 4-1: 2.8
From 4-1 to 5-0: 1.5
Expected pullups round 7:
From 0-6 to 1-5: 0.4
From 1-5 to 2-4: 0.6
From 2-4 to 3-3: 0.5
From 3-3 to 4-2: 0.6
From 4-2 to 5-1: 0.6
From 5-1 to 6-0: 0.3
—————————————————————————————————————————————————————————
Enter directory: 2
—————————————————————————————————————————————————————————
Elimination round statistics:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
The tournament will break to a partial Doubleoctofinals.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams positive: 21.7
Expected teams negative: 70.4
Expected teams breaking: 21.7
Expected teams screwed: 0
Expected teams debating: 11.4
Expected teams byed: 10.3
Expected number of elim debates: 6
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 5-2 that break: 14.8
Expected teams 6-1 that break: 5.6
Expected teams 7-0 that break: 1.3
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 5-2 that are debating: 11.4
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 5-2 that are byed: 3.5
Expected teams 6-1 that are byed: 5.6
Expected teams 7-0 that are byed: 1.3
—————————————————————————————————————————————————————————
```

Elimination bracket:
```
—————————————————————————————————————————————————————————
New tournament values:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Number of teams breaking: 22
Number of elim rounds: 5
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
List of team codes: 
Liberal Arts and Science BD
Peninsula LL
[...]                                                     // removed for readability
Lowell CL
Liberal Arts and Science LF
List of entry names: 
Barrett & Dollinger
Lai & Lai
[...]                                                     // removed for readability
Chen & Liu
Low & Faisal
List of team elos: 
22
21
[...]                                                     // removed for readability
2
1
—————————————————————————————————————————————————————————
The tournament will break to a partial Doubleoctofinals.  // obviously the following isn't correct because of upsets, but this is what it would look like determined by numerical seeds.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Doubleoctofinals:                	Octofinals:                      	Quarterfinals:                   	Semifinals:                      	Finals:                          	Champions:                       
LiberalArtsAndScienceAcademyBaDo 	LiberalArtsAndScienceAcademyBaDo 	LiberalArtsAndScienceAcademyBaDo 	LiberalArtsAndScienceAcademyBaDo 	LiberalArtsAndScienceAcademyBaDo 	LiberalArtsAndScienceAcademyBaDo 
BYE                             
NotreDameChKa                    	PeninsulaLiYu                    
PeninsulaLiYu                    
BaltimoreCityCollegeSeEr         	BaltimoreCityCollegeSeEr         	MontgomeryBellLMLeMa             
BYE                             
BYE                             	MontgomeryBellLMLeMa             
MontgomeryBellLMLeMa             
MontgomeryBellLMGeHo             	MontgomeryBellLMGeHo             	MontgomeryBellLMGeHo             	WestwoodShTr                     
BYE                             
LowellChLi                       	MeadowsBoPa                      
MeadowsBoPa                      
NorthviewChPo                    	NorthviewChPo                    	WestwoodShTr                     
LiberalArtsAndScienceAcademyBoSo 
BYE                             	WestwoodShTr                     
WestwoodShTr                     
USNShEs                          	USNShEs                          	USNShEs                          	USNShEs                          	PeninsulaLaLa                    
BYE                             
PeninsulaLiQi                    	LittleRockCentralMiBa            
LittleRockCentralMiBa            
MontgomeryBellLMMaTu             	MontgomeryBellLMMaTu             	BellarmineKrLi                   
LiberalArtsAndScienceAcademyLoFa 
BYE                             	BellarmineKrLi                   
BellarmineKrLi                   
BerkeleyPreparatorySchoolVaRo    	BerkeleyPreparatorySchoolVaRo    	BerkeleyPreparatorySchoolVaRo    	PeninsulaLaLa                    
BYE                             
BYE                             	GreenhillSchoolLiCh              
GreenhillSchoolLiCh              
NewTrierLeSh                     	NewTrierLeSh                     	PeninsulaLaLa                    
WestwoodJaAn                     
BYE                             	PeninsulaLaLa                    
PeninsulaLaLa                    
—————————————————————————————————————————————————————————
```

OpenCaselist wiki links:
```
————————————————————————————————————————————————————————— // note: these have a chance to be inaccurate based on which name is first on tabroom.
Expected team wikis:                                      // it's usually right because coaches like to input the names the same on tabroom and the wiki.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
https://opencaselist.com/hspolicy23/Alpharetta/KoMa
https://opencaselist.com/hspolicy23/EleanorRoosevelt/DeSt
[...]                                                     // removed for readability
https://opencaselist.com/hspolicy23/WichitaEast/HaRa
https://opencaselist.com/hspolicy23/WylieEGroves/XiCh
—————————————————————————————————————————————————————————
```

The following are example outputs based on made-up data.

Preliminary & elimination statistics:
```
—————————————————————————————————————————————————————————
New tournament values:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Number of teams debating: 10
Number of prelim rounds: 3
Number of elim rounds: 3
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
List of team codes: 
n/a
List of entry names: 
n/a
List of team elos: 
n/a
—————————————————————————————————————————————————————————
Preliminary round statistics:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 0-3: 1.7
Expected teams 1-2: 3.3
Expected teams 2-1: 3.3
Expected teams 3-0: 1.7
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected pullups round 2:
From 0-1 to 1-0: 1
Expected pullups round 3:
From 0-2 to 1-1: 0.9
From 1-1 to 2-0: 0.8
—————————————————————————————————————————————————————————
Enter directory: 2
—————————————————————————————————————————————————————————
Elimination round statistics:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
The tournament will break to a partial Quarterfinals.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams positive: 5
Expected teams negative: 5
Expected teams breaking: 5
Expected teams screwed: 0
Expected teams debating: 2
Expected teams byed: 3
Expected number of elim debates: 1
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 2-1 that break: 3.3
Expected teams 3-0 that break: 1.7
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 2-1 that are debating: 2
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Expected teams 2-1 that are byed: 1.3
Expected teams 3-0 that are byed: 1.7
—————————————————————————————————————————————————————————
```

Elimination bracket:
```
—————————————————————————————————————————————————————————
New tournament values:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Number of teams breaking: 10
Number of elim rounds: 3
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
List of team codes: 
n/a
List of entry names: 
n/a
List of team elos: 
n/a
—————————————————————————————————————————————————————————
The tournament will break to a full Quarterfinals.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Quarterfinals: 	Semifinals:    	Finals:        	Champions:     
School0Entry0  	School0Entry0  	School0Entry0  	School0Entry0  
School8Entry8  
School4Entry4  	School3Entry3  
School3Entry3  
School2Entry2  	School2Entry2  	School1Entry1  
School5Entry5  
School9Entry9  	School1Entry1  
School1Entry1  
—————————————————————————————————————————————————————————
```
