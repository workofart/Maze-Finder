# Maze Finder
Maze Finder implements a undirected graph using an adjacency matrix

This application was developed for an data structure and algorithms course at Western. It will read a specified input file, draw out the map and find the path from the source to the destination given the maximum number of tolls allowed.
<br/>
If no path is found, an alert will be shown.

# Install

To install, simply pull the master branch to download the application. Then, use the command `javac Solve.java` to compile the program.

# Run

After the program is compiled, use the command `java Solve mapFile` to run. Here, the `mapFile` is a text file, the map files are labeled `map1.txt` to `map7.txt`, formatted as follows:

1. First line contains the scaling factor,the program doens't use this value, but it can be adjusted to fit your computer screen (large value, bigger screen, vice versa)
2. Second line contains the width of the map, arranged in grids - this value indicates the number of vertical roads in each row
3. Third line contains the length of the map - this value indicates the number of horizontal raods in each column of the grid
4. Fourth line contains the number of toll roads the program is alowed to use in the path
5. The following lines contains the map path created using the following symbols:

```
  * ’s’: starting point **_only one_**
  * ’e’: destination **_only one_**
  * ’o’: intersection of two roads
  * ’h’: horizontal toll road
  * ’v’: vertical toll road
  * ’-’: horizontal free road
  * ’|’: vertical free road
  * ’ ’: block of houses
```

# Documentation
The entire program is documented in Javadoc format embedded in the java files
