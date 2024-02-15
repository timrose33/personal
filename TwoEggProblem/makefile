# C+= part
testcompute: main.o compute.o
		gcc -O main.o compute.o -o testcompute 

main.o: main.cpp # test harness for compute function
		gcc -c main.cpp -o main.o 

compute.o: compute.cpp
		gcc -O -c compute.cpp -o compute.o 

eggjni.o: eggjni.cpp EggProblemTestThread.h
		gcc -O -I$(JNI_INCLUDE) -I$(JNI_INCLUDE)/linux -o eggjni.o -c eggjni.cpp
		#gcc -O -I/usr/lib/jvm/java-8-openjdk-amd64/include -I/usr/lib/jvm/java-8-openjdk-amd64/include/linux -o eggjni.o -c eggjni.cpp

eggjni.so: eggjni.o compute.o
		gcc  -shared -o eggjni.so eggjni.o compute.o 

# java part

Mainline.class: Mainline.java
		javac  Mainline.java

EggProblemTestThread.class: EggProblemTestThread.java
		javac EggProblemTestThread.java

EggProblemTestThread.h: EggProblemTestThread.java # Generate JNI header
		javac -h . EggProblemTestThread.java

all: testcompute eggjni.so Mainline.class EggProblemTestThread.class EggProblemTestThread.h

clean: 
	rm *.o  *.so EggProblemTestThread.h *.class testcompute

