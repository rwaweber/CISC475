[![Stories in Ready](https://badge.waffle.io/rwaweber/CISC475.png?label=ready&title=Ready)](https://waffle.io/rwaweber/CISC475)
[![Build Status](https://travis-ci.org/rwaweber/CISC475.svg?branch=master)](https://travis-ci.org/rwaweber/CISC475)
# CISC475 - Project 5 Data Wrangling
# Product of the Lords of the Underfish

#Underfish Lords:
Teague Forren (Potato)
Greg Mohler
Joie Jan (Banana)
Will Weber (Webs)
Ben Rodd (b-rodd) 

# To use the REPL
## Setting the working environment
* In order to work on a data set, you must set the `source` and the `destination` files
`start sourcefile.csv destination.csv`
## Observing the data
* If you would like to view the data that you are currently working on, it will visualize a spreadsheet format in a JFrame
 * [NOTE!] If using this application on a remote server, must have X11 forwarding enabled on the server so that the JFrame window shows up on the client. 
`view fileyouwouldliketosee.csv`
## Exporting Files
* *feature under heavy development!*
* For those looking to automate transformations on large data sets, you may want to automate transformations
 * We suggest running an interactive session and exporting the session history as a script so that the munging process can be replicated
`> history export script.bcl`
## Munging!
* The feature everyone has been waiting for...
* There are two primary modes to consider when performing the munging task
 1. Interactive Mode
  * Performing transformations in our repl so that the data analyst can play
  * viewing resulting transformations
  * exporting sessions as scripts
 2. Automated(Scripted) Mode
  * Running exported session scripts
  * Running transformations over massive data sets (inset bigdata tagline)
* Available Transformations(verbs):
 * Mean
 * Max
 * Min
 * Sum
 * normalize_local_extrema
 * stand_dev
* **Usage**
`transform mean col 2`
`transform verb (col/row transformation) (column-name, column-index, row-index)`

