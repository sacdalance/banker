	#include <stdio.h>
	
	#define P 5 // 
	#define R 5 // column
	
	int main() {
		
		int available[P];
		int max[P][R] = {    
			{1, 2, 3, 4, 5},
    		{0, 1, 2, 3, 4},
    		{0, 0, 0, 0, 0},
    		{0, 0, 0, 0, 0},
    		{0, 0, 0, 0, 0}
    	};
		int allocation[P][R] = {0};
		int need[P][R] = {0}; 
		
		int i = 0;
		int j = 0;
			
	    for (i = 0; i < P; i++) {
			for (j = 0; j < R; j++) {
				printf("%d", max[i][j]);
			}
			printf("\n");
		}
		return 0;
	}
	
	void safetyAlgorithm() {
	}

