/**
 *
 * @author Jose Samayoa
 */
 
import java.util.Random;
import java.util.Scanner;

public class theunluckyrobot {
    public static void main(String[] args) {
	int totalScore = 0;
	int itrCount;
	int reward;
	char direction;
	int x = 0;
	int y = 0;
	
	for (itrCount = 1; itrCount <=20; itrCount++) {
	    if (!isGameOver(x, y, totalScore, itrCount)) {
		displayInfo(x, y, itrCount, totalScore);
		direction = inputDirection();
		if (doesExceed(x, y, direction)) {
		System.out.println("Exceed boundary, -2000 damage applied");
		totalScore += -2000;
		} else {
		    switch (direction){
			case 'u':
			    y++;
			    totalScore += punishOrMercy(direction, reward())-10;
			    break;
			case 'd':
			    y--;
			    totalScore += punishOrMercy(direction, reward()) -50;
			    break;
			case 'l':
			    x--;
			    totalScore += punishOrMercy(direction, reward()) -50;
			    break;
			case 'r':
			    totalScore += punishOrMercy(direction, reward()) -50;
			    x++;
			    break;
		    }
		}
	    } else {
		evaluation(totalScore);
		break;
	    }
	    
	}
    }
    public static void displayInfo(int x, int y, int itrCount, int totalScore){
	System.out.printf("\nFor point (X=%d, Y=%d) at iteration: %d the total"
		+ " score is: %d\n", x, y, itrCount, totalScore);
    }
    public static boolean doesExceed(int x, int y, char direction) {
	switch (direction){
	    case 'u':
		return (y == 4);
	    case 'd':
		return (y == 0);
	    case 'l':
		return (x == 0);
	    case 'r':
		return (x == 4);
	    default:
		return true;
	}
    }
    public static int reward() {
	Random rand = new Random();
	int dice = rand.nextInt(6) + 1;
	switch (dice){
	    case 1:
		System.out.printf("Dice: 1, reward: -100");
		System.out.println();
		return -100;
	    case 2:
		System.out.printf("Dice: 2, reward: -200");
		System.out.println();
		return -200;
	    case 3:
		System.out.printf("Dice: 3, reward: -300");
		System.out.println();
		return -300;
	    case 4:
		System.out.printf("Dice: 4, reward: 300");
		System.out.println();
		return 300;
	    case 5:
		System.out.printf("Dice: 5, reward: 400");
		System.out.println();
		return 400;
	    default:
		System.out.printf("Dice: 6, reward: 500");
		System.out.println();
		return 600;
	}
    }
    public static int punishOrMercy(char direction, int reward) {
	Random rand = new Random();
	if (reward < 0 && direction == 'u') {
	    int coinFlip = rand.nextInt(2);
	    if (coinFlip == 0) {
		reward = 0;
		System.out.println("Coin: tail | Mercy, the negative was removed");
	    } else {
		System.out.println("Coin: heads | No mercy, the negative was applied");
	    }
	}
	return reward;
    }
    public static String toTitleCase(String str) {
	return (Character.toUpperCase(str.charAt(0))+
		str.substring(1,str.lastIndexOf(" ")).toLowerCase()
		+ " " +
		Character.toUpperCase(str.charAt(str.indexOf(" ")+1)) 
		+ str.substring(str.lastIndexOf(" ")+2).toLowerCase());
    }
    public static void evaluation(int totalScore) {
	Scanner console = new Scanner(System.in);
	System.out.print("Please enter your name (First and Last name): ");
	String firstLastNameString = console.nextLine();
	if (totalScore >= 2000){
	    System.out.printf("Victory! %s, your score is %d",
		    toTitleCase(firstLastNameString), totalScore);
	} else {
	    System.out.printf("Mission failed! %s, your score is %d",
		    toTitleCase(firstLastNameString), totalScore);
	}
    }
    public static char inputDirection() {
	Scanner console = new Scanner(System.in);
	char dir;
	do {
	    System.out.print("Please input a valid direction: ");
	    dir = console.next().charAt(0);
	} while (!
		(dir == 'u' | 
		dir == 'd' |
		dir == 'l' |
		dir == 'r' )
		);
	return dir;
    }
    public static boolean isGameOver(int x, int y, int totalScore, int itrCount){
    if (itrCount > 20) {
	    return true;
	} else if (totalScore > 2000 ) {
	    return true;
	} else if (totalScore < -1000 ) {
	    return true;
	} else if (( y == 0 || y == 4) && x == 4) {
	    return true;
	} else {
	    return false;
	}    
    }
}
