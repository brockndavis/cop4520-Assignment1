How to compile the program:

1. cd into the project's src directory
2. ensure java 8 or higher is installed on your machine
3. run the command 'javac Main.java PrimeFinder.java CommandLineArgsProcessor.java'

How to run the program:
1. Complete the steps above for compiling the program
2. The program has 2 optional arguments which are specified with the flags --lower <value> and --upper <value>
   which correspond to the upper and lower bounds to find primes for. If unspecified, the lower bound is 1 and the upper bound is 1e8
3. Execute the program by running the command `java Main` or `java Main --lower <value> --upper <value>`
4. The program will finish execute by listing the execution time, number of primes found, the sum of the prime founds, and the 10 greatest primes found.