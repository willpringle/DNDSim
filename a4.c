/*****************************
 *	name:	Will Pringle
 *	id:		1056636
 ****************************/
#include "a4.h"

int numify(char* str, int max) {
	int size = strlen(str);
	int i = 0;
	int flag = 0;
	int new = hash1(str, max); /* the new to return */
	int multi = 1000000;
	
	for(i = 0; i < size; i++) {
		
		if(!flag && (str[i] == '-' || isdigit(str[i]))) {
			flag = 1;
		}
		
		if(flag) {
			new = new + str[i] * multi;
			multi = multi / 10;
			new = new + str[i] * multi;
		}
		
	}
	
	
	if(new > max) {
		
		new = new - 2000000;
		
		if(new > max) {
			
			new = new % max;
			
		}
		
		else if (new < 0) {
			
			new = hash1(str, max);
			puts("hello");
			
		}
		
		
	}
	
	
	
/*	printf("%d\t\tnumber = %c\n", new, str[i-1]);*/
	
	return new;
}

void dater(char* str, int *month, int *day, int *year) {
	int multi = 10;
	int numSlash = 0;
	
	int size = strlen(str);
	int i = 0;
	int j = 0;

	for(i = 0; i < size; i++) {
		
		if(isdigit(str[i]) && !numSlash) {
			*month = *month + multi * (str[i] - '0');
			multi = multi / 10;
			
		} else if (isdigit(str[i]) && numSlash == 1) {
			*day = *day + multi * (str[i] - '0');
			multi = multi / 10;
			
		} else if (isdigit(str[i]) && numSlash == 2) {
			*year = *year + multi * (str[i] - '0');
			multi = multi / 10;
			
		}
		
		if(str[i] == '/') {
			numSlash++;
			multi = 10;
			
			if(numSlash == 2) {
				multi = 1000;
			}
			
		}
		
	}
	
/*	printf("%d\t%d\t%d\t\t\t%s\n", *month, *day, *year, str);*/
}

int hash1(char* str, int max) {
	int i = 0;
	int j = str[0];
	int k = 0;
	int l = 0;
	int sum = 0;
	int longerCharacter = 0;
	unsigned long new = 0;
	
	for(i = 0; i < strlen(str); i++) {
		j = j + str[i] ^ sum;
		sum += str[i];
		l = ~((l << str[i]) - 7);
	}

	new = sum * (int)str[0] * (int)str[0]/13;
	
	k = sum * sum ^ str[0];
	
	new = new ^ max ^ str[0] ^ j ^ l;
	
	j = k >> j;
	j = k >> j;
	
	new = new ^ j;

	if(new > max) {
		
		new = new % max;
		
	}

    return (int)new;
}

int hash2(char* str, int max) {
	int size = strlen(str);
	int i = 0;
	int flag = 0;
	int new = hash1(str, max); /* the new to return */
	int multi = 1000000;
	
	for(i = 0; i < size; i++) {
		
		if(!flag && (str[i] == '-' || isdigit(str[i]))) {
			flag = 1;
		}
		
		if(flag) {
			new = new + str[i] * multi;
			multi = multi / 10;
			new = new + str[i] * multi;
		}
	}
	
	if(new > max) {
		new = new - 2000000;
		
		if(new > max) {
			new = new % max;
		}
		
		else if (new < 0) {
			
			new = hash1(str, max);
			puts("hello");
			
		}
	}
	
	return new;
}

int hash3(char* str, int max) {
	int new = 0;
	int month = 0;
	int day = 0;
	int year = 0;
	int temp = 0;
	dater(str, &month, &day, &year);
/*	printf("\nday = %d\tmonth = %d\t year = %d\n%s\n\n", day, month, year, str);*/
	
	/* if the value isn't in the correct format */
	if((!month || day) && !year) {
		return hash1(str, max);
	}
	
	/* add the number of days */
	new = month * 31 + day;
	
	if(year >= 2000){
		new = new * 10 + 2;
		
	}
	else {
		new = new * 10 + 9;
		
	}
	
	temp = year / 100;
	year = year - temp;
	new = new + year * 10000;
	
	if(new > max) {
		
		new = new % max;
		
	}

	return new;
}