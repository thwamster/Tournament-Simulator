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

If you would like to, please feel free to submit a pull request to improve this code.It was written with limited knowledge of Java and quite likely has errors or and opaque operations that can be clarified.
This program was written to be consistent across directories. Accuracy is appreciated, but not the sole purpose of the code.


