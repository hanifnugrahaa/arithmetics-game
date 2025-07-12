package midexam;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ArithmeticGame {
    
    private static final int WINNING_SCORE = 100;
    private static final int POINTS_CORRECT = 5;
    private static final int POINTS_INCORRECT = 2;
    private static final int MAX_RANDOM_NUMBER = 20;

    private final Player player;
    private final ArithmeticOperation[] operations;
    private final Random random;
    private final Scanner scanner;

    public ArithmeticGame(Scanner scanner) {
        this.scanner = scanner;
        this.random = new Random();
        this.operations = new ArithmeticOperation[]{new Addition(), new Subtraction()};

        clearScreen();
        displayWelcomeMessage();
        System.out.print(">> Boleh kenalan? Siapa namamu?: ");
        this.player = new Player(scanner.nextLine());
        System.out.println("\nAsyik! Halo, " + player.getName() + "! Ayo kita mulai bermain.");
        pressEnterToContinue();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArithmeticGame game = new ArithmeticGame(scanner);
            game.run();
        }
    }

    public void run() {
        while (player.isAlive() && !player.hasReachedScore(WINNING_SCORE)) {
            clearScreen(); 
            displayMenu();
            int choice = getPlayerChoice();

            if (choice == operations.length) { 
                break;
            }
            playRound(operations[choice]);
        }
        clearScreen();
        displayFinalMessage();
    }

    private void playRound(ArithmeticOperation operation) {
        clearScreen(); 
        int a = random.nextInt(MAX_RANDOM_NUMBER);
        int b = random.nextInt(MAX_RANDOM_NUMBER);

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    Tantangan " + operation.getOperationName() + " untukmu!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n" + operation.getQuestion(a, b));
        System.out.print("Jawab di sini -> ");

        try {
            int answer = Integer.parseInt(scanner.nextLine());
            int correctAnswer = operation.calculate(a, b);

            if (answer == correctAnswer) {
                System.out.println("\n     HORE, BENAR! KAMU HEBAT! 🎉");
                System.out.println("     + " + POINTS_CORRECT + " poin untukmu!");
                player.addScore(POINTS_CORRECT);
            } else {
                System.out.println("\n     Oops, sedikit lagi! Jangan khawatir ya.");
                System.out.println("     Jawaban yang benar adalah: " + correctAnswer);
                System.out.println("     Kamu kehilangan 1 nyawa ♥");
                player.subtractScore(POINTS_INCORRECT);
                player.loseLife();
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     Aduh, sepertinya itu bukan angka. Hati-hati ya.");
            System.out.println("     Kamu kehilangan 1 nyawa ♥");
            player.loseLife();
        }

        System.out.printf("\n[ Skor: %d ★ | Nyawa: %d ♥ ]\n", player.getScore(), player.getLives());
        System.out.println("=====================================");

        pressEnterToContinue(); 
    }


    private void pressEnterToContinue() {
        System.out.print("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    
    private int getPlayerChoice() {
        while (true) {
            System.out.print("\nKetik pilihanmu [1, 2, atau 3] -> ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= operations.length + 1) {
                    return choice - 1;
                }
                System.out.println("Pilihan tidak ada di menu. Coba lagi ya!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Coba lagi ya!");
            }
        }
    }

    private void displayWelcomeMessage() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("  ★ SELAMAT DATANG DI GAME HITUNG ★  ");
        System.out.println("=====================================");
        System.out.println();
    }

    private void displayMenu() {
        System.out.println("\n\n-------------------------------------");
        System.out.println("   Mau main yang mana nih, " + player.getName() + "?");
        System.out.println("-------------------------------------");
        for (int i = 0; i < operations.length; i++) {
            System.out.printf("  [ %d ] %s\n", (i + 1), operations[i].getOperationName());
        }
        System.out.printf("  [ %d ] Keluar Dulu\n", (operations.length + 1));
        System.out.println("-------------------------------------");
    }

    private void displayFinalMessage() {
        System.out.println("\n=====================================");
        if (player.hasReachedScore(WINNING_SCORE)) {
            System.out.println("    🏆 KAMU MENANG, " + player.getName() + "! 🏆");
            System.out.println("      Skor akhirmu: " + player.getScore());
            System.out.println("      Luar biasa! Pertahankan ya!");
        } else if (!player.isAlive()){
             System.out.println("      GAME OVER, " + player.getName() + "...");
             System.out.println("      Jangan sedih, kamu sudah berusaha keras! 😊");
             System.out.println("      Ayo main lagi kapan-kapan!");
        } else {
            System.out.println("      Sampai jumpa lagi, " + player.getName() + "!");
            System.out.println("      Terima kasih sudah bermain! 👋");
        }
        System.out.println("=====================================");
    }
}