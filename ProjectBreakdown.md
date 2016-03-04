Johanna Jan
Will Weber

								Project Breakdown
								  March 3, 2016

The project system will be divided into 3 components (blackboxes): FileParser, Transformations, and Output. The FileParser will take a cvs as input and output a STREAM (collections will be made as an intermediate state). Transformations will take a stream as input and out a STREAM. The Output blackbox will take a stream as input and output a CSV. 

We will also have a Command interface that interacts with the 3 blackboxes above. This class will be connected to the command line. It will probably have a CommandParser class that searches for the input cvs and transformation keywords.

4 parts in total:
	⁃	FileParser
	⁃	Transformations
	⁃	Output
	⁃	CommandParser
