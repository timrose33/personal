This folder contains my solution for a coding challenge - the Two Egg Problem. 
The challenge is outlined here: http://www.datagenetics.com/blog/july22012/index.html
       (note - the site does not use https)

The solution was all my own work. No cheats or ChatGPT were used. The solution uses:

- multi-threaded Java test harness driving concurrent executions of the program.
- the core algorithm in C++ using recursion
- JNI to access C++ from Java        
- build and executions steps including a Makefile

The challenge instructions were laid out as follows:

Description
You have a mission to find at which floor an egg would break. You also have a limited number of eggs 
(nb_egg). You know the number of total floors (nb_floor). We want to know how many tries it will take 
in the worst-case scenario to find at which floor the eggs are breaking. Remember, you have a limited 
number of eggs. You are asked to create a strategy that would minimize the worst-case scenario and give a systematic 
answer. Such a function can be refereed to "Minimization of Maximum Regret" and is explained here.

Specific Challenge

Low level implementation

Implement an algorithm in C or C++ that find the maximum number of tries 
(int compute(int nb_item, int height)).
This C/C++ implementation should not include any third parties and the code should be using only native 
base types like int, short etc.

Higher abstraction language

Write a java program that test the C/C++ implementation. The java program should print at the console 
the following table by calling the C/C++ function. Each call to the C/C++ library should be done in parallel 
using threads, however the printing should still match this table.
Building Height / Eggs 1 2 3 4 5 6 7 8 9 10
1 floor                1 1 1 1 1 1 1 1 1  1
2 floors               2 2 2 2 2 2 2 2 2  2
3 floors               3 2 2 2 2 2 2 2 2  2
4 floors               4 3 3 3 3 3 3 3 3  3
5 floors               5 3 3 3 3 3 3 3 3  3
6 floors               6 3 3 3 3 3 3 3 3  3
7 floors               7 4 3 3 3 3 3 3 3  3
8 floors               8 4 4 4 4 4 4 4 4  4
9 floors               9 4 4 4 4 4 4 4 4  4
10 floors             10 4 4 4 4 4 4 4 4  4
11 floors             11 5 4 4 4 4 4 4 4  4
12 floors             12 5 4 4 4 4 4 4 4  4
13 floors             13 5 4 4 4 4 4 4 4  4
14 floors             14 5 4 4 4 4 4 4 4  4
15 floors             15 5 5 4 4 4 4 4 4  4
16 floors             16 6 5 5 5 5 5 5 5  5
17 floors             17 6 5 5 5 5 5 5 5  5
18 floors             18 6 5 5 5 5 5 5 5  5
19 floors             19 6 5 5 5 5 5 5 5  5
20 floors             20 6 5 5 5 5 5 5 5  5
21 floors             21 6 5 5 5 5 5 5 5  5
22 floors             22 7 5 5 5 5 5 5 5  5
23 floors             23 7 5 5 5 5 5 5 5  5
24 floors             24 7 5 5 5 5 5 5 5  5
25 floors             25 7 5 5 5 5 5 5 5  5
30 floors             30 8 6 5 5 5 5 5 5  5
35 floors             35 8 6 6 6 6 6 6 6  6
40 floors             40 9 6 6 6 6 6 6 6  6
45 floors             45 9 7 6 6 6 6 6 6  6
50 floors             50 10 7 6 6 6 6 6 6 6
100 floors           100 14 9 8 7 7 7 7 7 7
200 floors           200 20 11 9 8 8 8 8 8 8
300 floors           300 24 13 10 9 9 9 9 9 9
400 floors           400 28 14 11 10 9 9 9 9 9
500 floors           500 32 15 11 10 10 9 9 9 9
1,000 floors        1000 45 19 13 11 11 11 10 10 10

Technical limitations
• We recommend you use Java Native Interface (JNI) in your C/C++ code to make it available to 
your java program.
• You are free to manage the java threads like you see fit.
• You can use a static table with a fixed size in the C/C++ implementation that supports a 
maximum of 10 eggs and 100 floors to make your life easier.
• CLI is good enough. No need for a GUI.
• You can do it in Windows or Linux.
• You should provide all steps to compile both programs and how to run the java part. The 
program will be tested in a virgin environment that only have gcc 9.3.0 and jdk 17.
• Recursive strategy is recommended for the C/C++ implementation. Stopping conditions should 
be carefully thought through.