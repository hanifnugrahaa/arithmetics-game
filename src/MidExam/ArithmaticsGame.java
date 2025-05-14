package MidExam;
import java.util.Random;
import java.util.Scanner;

class Player {
    String name;
    int score = 0;
    int lives = 3;

    Player(String name) {
        this.name = name;
    }

    void addScore(int points) {
        score += points;
    }

    void subtractScore(int points) {
        score -= points;
    }

    void decreasedLives() {
        lives--;
    }

    boolean alive() {
        return lives > 0;
    }

    boolean reachedScore(int minScore) {
        return score >= minScore;
    }
}

abstract class ArithmaticsOperation {
    String name;

    ArithmaticsOperation(String name) {
        this.name = name;
    }

    abstract int calculate(int a, int b);
}

class Addition extends ArithmaticsOperation {

    Addition() {
        super("Addition");
    }

    int calculate(int a, int b) {
        return a + b;
    }
}

class Subtraction extends ArithmaticsOperation {

    Subtraction() {
        super("Subtraction");
    }

    int calculate(int a, int b) {
        return a - b;
    }
}

public class ArithmaticsGame {
    static final int winScore = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************");
        System.out.println("Arithmatics Game");
        System.out.println("**********************");
        System.out.print("Enter your name: ");
        Player player = new Player(scanner.nextLine());
        ArithmaticsOperation[] operations = {new Addition(), new Subtraction()};
        Random random = new Random();

        while (player.alive() && !player.reachedScore(winScore)) {
            System.out.println("**********************");
            System.out.println("Arithmatics Game");
            System.out.println("**********************");
            System.out.println("Main Menu: ");
            for (int i = 0; i < operations.length; i++) {
                System.out.println((i + 1) + ". " + operations[i].name);
            }
            System.out.println((operations.length + 1) + ". Exit");
            System.out.print("Enter Menu: ");
            int choice = scanner.nextInt();
            if (choice == operations.length + 1) {
                break;
            }
            ArithmaticsOperation operation = operations[choice - 1];
            int a = random.nextInt(20);
            int b = random.nextInt(20);
            if (choice == 1) {
                System.out.println("");
                System.out.println("What is the result of " + a + " + " + b + "? ");
                System.out.print("Answer: ");
            } else {
                System.out.println("What is the result of " + a + " - " + b + "? ");
                System.out.print("Answer: ");
            }
            int answer = scanner.nextInt();
            int correctAnswer = operation.calculate(a, b);
            if (answer == correctAnswer) {
                System.out.println("Yes " + player.name + ", you're right");
                player.addScore(5);
            } else {
                System.out.println("Sorry " + player.name + ", wrong answer");
                player.subtractScore(2);
                player.decreasedLives();
            }
            System.out.println("Score: " + player.score + ", Lives: " + player.lives);
            System.out.println("");
        }
        if (player.reachedScore(winScore)) {
            System.out.println("Congratulations " + player.name + ", You have finished this game. You won with a score of " + player.score + ". Please try another menu.");
        } else {
            System.out.println("Hi " + player.name + ", Don’t give up. I’m sure you can do better");
        }
    }
}