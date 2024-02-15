#include "compute.h"

	int compute(int nb_item, int height) {
		// defend against bad input
		if (nb_item < 1 || height < 1)
			return 0;

		int total = 0;

		int result = compute2(nb_item, height, total);
		return total;
	}

	int compute2(int nb_item, int height, int& total) {
		int result = 0;
		
		// handle bad input
		if (nb_item < 1 || height < 1) return 0;
		
	    	// if there are enough eggs binary search gives the best result. Determine max number of 
		// binary search iterations for the height 
		int binSearchDepth = BinarySearchDepth(height);
	
		if (nb_item == 1) {
			// only 1 egg left, linear search from lowest floor remaining section
			result = height;
			total+=result;
		} 
		
        	else if (height == 1 || height == 2) { 
        		result = height; 
			total+=result;
       		}
		
		else if (nb_item == 2) {
			// for 2 egss use formula given by https://datagenetics.com/blog/july22012/index.html
			result = TwoEggCalculation(height);
			total+=result;
		}
		
        	else if ( binSearchDepth <=  nb_item) {

            		result = binSearchDepth;
			total+=result;
        	}

		else /* if (nb_item >= 3) */ {
			// All other cases the optimal height (least worst minimum) will be something in between
			// to 2 egg height and the halfway point used by the binary search method.
			// These ranges are represented by variables left and right.
			//
			// The idea is to traverse the different options within that range. However, because of
			// all the backtracking and recursion involved the linear search is not scalable for larger
			// values. Therefore, a binary search method is used.
			//
			// The values k, n, and e are used to align with the example in https://datagenetics.com/blog/july22012/index.html
			//
			// The search is split into 2 parts around the value of n.
			// The 2 parts can be represented by 2 (mathematical) functions (formula unknown:
			// - the left side of the "split" will always increase or remain the same as n increases.
			// - the right side of the "split' will always decrease or remain the same as n increases.
			// --> Therefore, if the 2 values are equal the minimum has been reached.
			
	       		int twoEggHeight = TwoEggCalculation( height );
	       		int k = height;
	       		int minValue = 1000000;
			int e = nb_item;
			
	     		int left = twoEggHeight; 
	     		int right = height/2;
	     		int n = left;
			while (left <= right) {
			
		       	   n = (left + right ) / 2;
        	   
			   int throwaway = 0;
			   int throwaway2 = 0;
        	   	   int EggBreaksNValue = compute2( e-1,  n-1, throwaway );
        	   	   int EggDoesntBreakNValue = compute2 (e, k-n, throwaway2);
        	   
        	   	   if (EggBreaksNValue > EggDoesntBreakNValue) {
         		      if (minValue > EggBreaksNValue) {
         			   minValue = EggBreaksNValue;
         		      }
         		      if (right == n) break;
         		      right = n;
        		   
        	   	   } else if (EggBreaksNValue < EggDoesntBreakNValue) {
           		   	if (minValue > EggDoesntBreakNValue) {
         			   minValue = EggDoesntBreakNValue;
           		        }
           		   	// Special handling because of the integer divide by 2
           		   	// to not miss a value when left and right are very close
         		   	if (left == n) { 
         			   if ((right - left) > 1)  break;
         		           else left = n+1;
         		   	} else {
         			   left = n;
             		       }
        	          } else {
        		     minValue = EggBreaksNValue;
        		     break;
        		 
        	          }  //else
       	   		} // while
           		if (minValue < 100000) {
			  total++;
			  total+=minValue;
			}
       		 } // else nb_items > 3

		return total;
	}

	int TwoEggCalculation(int height) {

		for (int n = 1; n <= height; n++) {
			if ((n * (n + 1) / 2 >= height))
				return n;
		}
		return 0;
	}

	int BinarySearchDepth(int value) {
		int count = 1;

		while (value > 1) {
			value = value / 2 + value % 1;
			count++;
		}
		return count;
	}


