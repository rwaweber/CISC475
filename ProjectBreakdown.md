Johanna Jan
Will Weber

								Project Breakdown
								  March 3, 2016

The project system will be divided into 3 components (black boxes): FileParser, Transformations, and Output. The FileParser will take a CSV as input and output a STREAM (collections will be made as an intermediate state). Transformations will handle all of our stream operations and manipulations. If it starts to get too complex, we will add another class and organize the transformations. These classes will take a stream as input and output a STREAM. The Output black box will take a stream as input and output a CSV. 

We will also have a Command interface that interacts with the 3 black boxes above. This class will be connected to the command line. It will probably have a CommandParser class that searches for the input CSV and transformation keywords.

4 parts in total:
	⁃	FileParser
	⁃	Transformations
	⁃	Output
	⁃	CommandParser
