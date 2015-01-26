Cell Society Design
===================
Intro/Overview - Sid
User Interface/Design Considerations - Sunjeev
Design Details - Janan
Team Responsibilities - All

#Introduction
The goal of this project is to create a program that can run any of four CA simulations, with the possibility of more being added. This will be done by reading in an XML file that contains which CA to run and all of the parameters required for the specific CA simulation. 

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
The XMLParser class will take the information it receives from the FileLoader and read through the XML to make it readable for the rest of the program, specifically the simulations. The Parser will do this by creating two objects. The first will be a HashMap that contains all parameter titles as the keys and parameter values as the values. Because HashMaps are mutable, this will allow for extra parameters to be added. The second object will be a 2D ArrayList or a "Grid" object that we create. This object will contain all of the values and statuses of each cell in a grid. It will then be able to check the status of neighbors by using indexing. These are the two main data structures that will be used.

##Model
The Model package of the program will contain the Simulation superclass wand each specific simulation will be a subclass of Simulation. All the computation involved in updating the grid is done by this class, and the Model will then pass the state of grid on to View class, which updates the display for the User.

###Simulation Superclass
This will be the main class that will be extended by all of the individual simulations. It will have methods that create the grid and deal wth updating each individual cell.

###Simulations
Several extended versions of the Simulation Superclass will be included here. Each one of these will have individual methods that deal with how that specific simulation is updated.

##View
The view will contain the specific "screens" that need to be displayed to users.

* SplashScreen
* FileLoaderScreen
* SimulationScreen

###SplashScreen
The SplashScreen view will have the buttons and text required to introduce the user to the program. They will get instructions and be able to click the "Load File" button, which will take them to the FileLoaderScreen and initiate the FileLoader and XMLParser controller classes. 

###FileLoaderScreen
The FileLoaderScreen will be a simple view screen that allows for the user to choose an XML file from a list. This will then call the XMLParser to create the primary structures that are used by the program.

###SimulationScreen
The Simulation Screen will be a view that shows a grid of cells as defined by the user. It will contain a 2D array of Square Nodes to be displayed on the JavaFX Scene. The state of the Squares on the screen are updated by the active Simulation class in the model. It will also buttons that will call commands in the Controller and allow the user to control the simulation as necessary, like pausing, changing speed and stepping forward.

##Other Information
We are thinking of this design as a "funnel" of sorts. The controller will call down to the model classes which will provide the necessary state of the program to the view classes. Each class will only deal with exactly what it needs to. So, for example, the sprites/graphics in the View will not know anything more than how to return its node to any class that calls it.

#User Interface 

The user interface for this program will be relatively simple. When the program runs, the splash screen will open, and the user will have the option to click on a button which will allow the user to load an xml file. As of right now, the splash screen is going to look very simple. It will have a title, maybe Cellular Automata, and a button titled "Load File". 

Add Image Here

When "Load File" is clicked, the explorer will appear which will allow the user to select an xml file that they wish to read in. If they select a file that is incomplete or has errors, the program will return to the splash screen with a new text that says "Error Loading File". 

If the file is successfully loaded, the explorer will close and the new simulation screen will appear. The simulation will automatically run. It will look different depending on which model the xml file chose. For example, if the fire starter simulation was indicated in the xml file, the colors will be green, red, and yellow. The other models might have different colors to better indicate what they are modeling. At the top left of the screen will be a button titled "Load". This button will open up the file loader and allow the user to load in a new file. At the top center there will be two buttons, one titled "step" which will allow the user to step through the simulation, and another titled "speed up" which speeds up the simulation while the button is held down. At the top right will be another button titled "Stop/Start" which either stops or starts the animation depending if the animation is in a stopped state or a start state.

Add Image here

#Design Details

##Controller

###Main
As described above, the sole purpose of this class is to initialize the JavaFX Scene and Timeline and delegates the logic of the program to CellSocietyController.

###CellSocietyController
CellSocietyController serves as the connecting centerpiece between all the inputs and outputs of the program. It calls the SplashScreen View at the initialization of the program, which brings up a splash screen and waits until the user clicks a button to call the fileLoader, which handles the reading of the simulation parameters. CellSocietyController will pass a 2D ArrayList of the initial grid state, a HashMap of simulation parameters, and a simulation name String to fileLoader, which is supposed to set them according to the XML file that the user chose. CellSociety then looks at the simulation name, picks the appropriate Simulation Model to call and initializes that Model with the appropriate parameters. At the same time, it initializes a SimulationScreen view that is ready to display the grid to the user and take in user input. Any further user input will result in calls to methods in CellSocietyController that in turn call methods in the Simulation to alter its behavior. Another possibility is for the user to reload the simulation, in which case the CellSocietyController will start the loop again and call the fileLoader.

This design pattern for CellSocietyController reflects the most basic, high-level requirements for our CA simulation program. The main functionalities of the class are:
*   Getting an XML file from the user and parsing it for parameters
*   Initializing a SimulationScreen and the appropriate Simulation Model
*   Processing input from the user to control the model

In the MVC design pattern, the purpose of the controller is to convert user input into appropriate commands. Our design for the controller satisfies this purpose while working directly only with data structures that we know will be common to all CAs (Grid pattern and a set of configuration parameters) and so it should be flexible and extendable to any new simulations we may want to add.


###FileLoader
The purpose of this class is to get an XML file from the user containing parameters. Once initialized, it will set the Scene using a call from the FileLoaderScreen View. When the user loads a file, fileLoader will get the pointer to that file and pass it to XMLParser, who will return the 2D arrayList of initial grid states and a HashMap of parameters. If an error occurs then it will restart the FileLoaderScreen View and prompt for another file.

This class is fairly straightforward; it manages the FileLoaderScreen and the XMLParser and the relationship between them without dealing with the presentation of the file prompt in FileLoaderScreen nor the implementation of the data parsing in XMLParser, following the principle of "telling the other guy."

###XMLParser
This class is initialized with a file pointer from FileLoader and will contain methods for extracting the data we need from the XML file and storing them in the data structures used by the models; namely the 2D arrayList of initial grid states, the name of the simulation and the HashMap of simulation parameters. The XMLParser represents a low-level class in our program design; it doesn't know what the data is being used for, and it doesn't call methods from other classes but simply returns this data to the FileLoader that asked for it. The XMLParser knows exactly as much as it needs to know.

##Model

###Simulation Superclass
The Simulation class is the superclass of individual simulation classes and should define methods that are shared in common among simulations. It will be an abstract class and will define methods for creating and initializing a grid as a List of Cell objects, parsing through the HashMap of parameters and setting the parameters as appropriate, and a method for making a step of the model, with helper methods for updating individual Cells. One such helper method would be a method for defining the neighborhood of a cell, which by default should be the 8,6, or 3 cells surrounding that cell depending on its location in the grid.

These will all be abstract methods to be implemented by the subclasses according to the types of cells in the simulation and the rules for updating the model. Lastly there should be a concrete method for generating a map of cell coordinates to colors based on the state of every cell and passing this map to a method in the associated Simulation View so that the view is updated.

The data structures that all Simulations should contain at minimum are the List of Cell objects in the grid and HashMap of Cell types to Node colors, as read from the HashMap of parameters.

In our design, the Simulation class is meant as the manager of the state of the cell grid. It is the only class that directly works with the cells in the grid, and its duties are to update the cells according to the rules of the simulation and to pass the colors of each cell in the grid at any given time to the SimulationScreen. CellSocietyController is the class that will call the Simulation's update methods, so it will have control over pausing,resuming and stepping through the simulation, as required by the assignment specification.

###Simulations
The specific simulation classes will have whatever data structures necessary to compute the state of the cell according to the rules of the simulation. In general, the process for updating a cell will involve evaluating the neighborhood of the cell and deciding on a next state based on the properties of the neighborhood. This is intended to be as flexible as possible to accomodate different kinds of simulations; each simulation subclass is meant to have a different implementation of updateCell with no restrictions on the kind of rules it can implement and it would even be possible to override the superclass method for determining the neighborhood of the cell if necessary.


###Cell
The Cell object is the lowest level object, representing cells in the grid. The cells themselves don't do any computation and simply return their color/state to the Simulation and allow their state to be modified by the Simulation. The Cells are meant to be containers of information for the Simulation, separate from the logic contained in the Simulation itself.

##View

###SplashScreen
This Scene will contain information about the program and a button that directs the CellSocietyController to load the FileLoaderScreen once pressed by the user.

###FileLoaderScreen
This scene will contain a JavaFX FileChooser object to enable to user to load a XML file containing information about the simulation they want to play. The file is then passed on to the XMLParser, which formats the data into data structures to be read by the Simulation objects.

###SimulationScreen
There will be buttons that allow the user to start/pause, step through, speed up and load a new simulation. ALl of these commands call methods in the CellSocietyController. When initializing the SimulationScreen, the class will have a method to get information about the size of the grid and create Square node objects of an appropriate size to tile the screen according to the number of squares in the grid. It will also have a method that will take in a map of cell coordinates to colors from the Simulation class and update the colors of the Square nodes in the Scene accordingly. These functions of the SimulationScreen are completely independent and abstracted from the model, so they should be easily extendable regardless of new features added in the simulation.

