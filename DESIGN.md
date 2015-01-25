Cell Society Design
===================
Intro/Overview - Sid
User Interface/Design Considerations - Sunjeev
Design Details - Janan
Team Responsibilities - All

#Introduction
The goal of this project is to create a program that can run any of four CA simulations, with the possibility of more being added. This will be done by reading in an XML file that contains which CA to run and all of the parameters required for the specific CA simluation. 

We want it to make it extendable enough that any number of CA simulations can be added, each with their own parameters. Therefore, the "view" pat of the program should be very flexible. The controller part, where the transitions between different levels will be handled, should also be flexible.

We want to make it so that the basic structure and foundation, so to speak, of the program is closed and doesn't need to be altered. We want to make it flexible in the way that the program handles new simulations being added. 

We plan to use the model-view-controller architecture to structure our program.

#Overview
The program will be divided up into three sections, in standard model-view-controller ideology. The controller will handle the highest level functions, making sure that everything is running and "delegating" smaller tasks to other classes. The view will deal with the actual simulations and what the user sees. The model package will deal with all of the very specific graphics, such as the red, green, and yellow squares in the fire simulation. I will describe each section in more detail below.

##Controller
The controller is where the highest level functions of the program will be handled. The classes in here will deal with the general back-end of the program. In our current design, there are four classes.

* Main
* CellSocietyController
* FileLoader
* XMLParser

###Main
The main will handle the actual running of the program. It will initialize the keyframe/timeline and start the program by calling the largest class in the program, CellSocietyController.

###CellSocietyController
This class will handle a lot of the large functions and operations of the program. It will start by initializing the splash screen. Then, it will continue to poll different parts of the program to test for transitions between the splash screen and the simulations. This class will also check if a simulation has ended or if the user has asked for the program to stop or load a new file.

###FileLoader
This class is included in the controller because it deals with back-end operations of the program. This will likely be called from CellSocietyController once the load button has been pressed on the Splash Screen. It will load up a new screen that allows the user to choose an XML file to load.

###XMLParser
The XMLParser class will take the information it receives from the FileLoader and read through the XML to make it readable for the rest of the program, specifically the simulations. The Parser will do this by creating two objects. The first will be a HashMap that contains all parameter titles as the keys and parameter values as the values. Because HashMaps are mutable, this will allow for extra parameters to be added. The second object will be a 2D Array or a "Grid" object that we create. This object will contain all of the values and statuses of each cell in a grid. It will then be able to check the status of neighbors by using indexing. These are the two main data structures that will be used.


##View
The view will contain a Simulation superclass, as well as a few smaller classes that deal with specific "screens" that need to be displayed to users.

* SplashScreen
* FileLoaderScreen
* Simulation Superclass
* Simulations

###SplashScreen
The SplashScreen view will have the buttons and text required to introduce the user to the program. They will get instructions and be able to click the "Load File" button, which will take them to the FileLoaderScreen and initiate the FileLoader and XMLParser controller classes. 

###FileLoaderScreen
The FileLoaderScreen will be a simple view screen that allows for the user to choose an XML file from a list. This will then call the XMLParser to create the primary structures that are used by the program.

###Simulation Superclass
This will be the main class that will be extended by all of the individual simulations. It will have methods that create the grid and deal wth updating each individual cell.

###Simulations
Several extended versions of the Simulation Superclass will be included here. Each one of these will have individual methods that deal with how that specific simulation is updated.

##Model
The Model package of the program will contain at least one superclass titled "Square," which will be extended to different colored squares that are used to populate the grids. It might also include other graphics that we choose to include.

##Other Information
We are thinking of this design as a "funnel" of sorts. The controller will call down to the view classes which will call down to the model classes. Each class will only deal with exactly what it needs to. So, for example, the sprites/graphics will not know anything more than how to return its node to any class that calls it.