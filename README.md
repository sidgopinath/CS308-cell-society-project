cellsociety
===========

by Janan Zhu(jz113), Sid Gopinath(sdg23), Sunjeev Devulapalli(svd12)

* **Date Started:** January 23
* **Date Finished:** February 10 (Sprint 3)
* **Number of Hours:** 25 hours (Sprint 1 + 2) + 20 hours (Sprint 3)

###Group Roles
* Janan: In charge of the Predator simulation and design of the Cells. Also worked heavily on inheritance hierarchy, especially with simulations and cells.
* Sid: In charge of Controller, XML Parsing/Writing, error handling, and nearly all configuration. Additionally, helped implement Simulation rules in Simulations and Cells.
* Sunjeev: In charge of View classes and View issues in Sprint 3. Also worked on non-Predator simulations and Cells.

###Resources
* StackOverflow (commented within code)
* [Website for guidance on XML Parser design](http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/)

###File Information
* **Files Used to Start the Project:** Main.java starts the project
* **Files Used to Test the Project:** All XML files in the testFiles folder and stylefiles folder.
* **Data or Resource Files Required by Project:** XML File format specified in "xmlFormat.xml" for simulation. You must load in a style file before you load the XML file with the simulation data.

###Known Bugs, Crashes, or Problems
* If you press load and then don't choose a file or hit cancel, you will get an error.
* The step button works, but it lags a little bit when pressed.
* The predator simulation is buggy at times. 
* If you don't load in a style file before loading in the simulation, the program crashes.

###Extra Features
N/A

###Assignment Impressions
This assignment taught us a lot about inheritance hierarchy, which was good. In particular, the second sprint really made us think about laying our program in a smart manner. However, the third sprint had an excessive number of suggested features to add. While it helped us see what was lacking in our design, it was tough to sift through these to figure out what should actually be implemented. It was even tougher to know how we would be graded, particularly since we haven't been able to get feedback back from our Game project.

Overall, it was very useful to learn about file error cehcking, inheritance hierarchy, and the idea of model-view-controller. It was just a lot of work, which seemed to have diminishing returns towards the end.

