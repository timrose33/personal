#include <stdio.h>
#include "compute.h"
 static bool expected (int N, int actual, int expected ) {
	if (actual != expected) {
		printf("Failure Expected: %d Actual:%d For:%d/n",expected, actual, N);
								}
	    return (actual == expected);

	}


int main(int argc, char *argv[]) {
		
		  expected(11, compute(1,1), 1); expected(53, compute(5,3), 2);
		  expected(66, compute(6,6), 3); expected(88, compute(8,8), 4);
		  expected(122, compute(1,22), 22); expected(235, compute(2,35), 8);
		  expected(22, compute(2,2), 2); expected(33, compute(3,3), 2);
		 
		expected(315, compute(3, 13), 4);
		expected(315, compute(3, 15), 5);
		expected(330, compute(3, 30), 6);
		expected(430, compute(4, 30), 5);
		expected(350, compute(3, 50), 7);
		expected(350, compute(5, 45), 6);
		expected(950, compute(9, 50), 6);
		expected(6100, compute(6, 100), 7);
		expected(8300, compute(8,300),9);
		expected(71000, compute(7,1000),11);
		expected(4500, compute(4,500),11);
		
		
		expected(0, TwoEggCalculation(35), 8);
		expected(0, TwoEggCalculation(10), 4);
		expected(0, TwoEggCalculation(11), 5);
		expected(0, TwoEggCalculation(50), 10);
		expected(0, TwoEggCalculation(1), 1);
		expected(0, TwoEggCalculation(2), 2);
		expected(0, TwoEggCalculation(3), 2);
		expected(0, TwoEggCalculation(500), 32);
		
		expected(1, BinarySearchDepth(1),1);
		expected(2, BinarySearchDepth(2),2);
		expected(3, BinarySearchDepth(3),2);
		expected(4, BinarySearchDepth(4),3);
		expected(7, BinarySearchDepth(7),3);
		expected(8, BinarySearchDepth(8),4);
		expected(15, BinarySearchDepth(15),4);
		expected(16, BinarySearchDepth(16),5);
		expected(30, BinarySearchDepth(30),5);
		expected(100, BinarySearchDepth(100),7);
		expected(300, BinarySearchDepth(300),9);
		expected(1000, BinarySearchDepth(1000),10);
}
