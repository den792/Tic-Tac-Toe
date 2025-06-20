import java.util.Scanner;

public class Game {
    private static char[][] board = new char[3][3]; // Ігрове поле 3x3
    private static char currentPlayer = 'X';        // Поточний гравець

    public static void startGame(){
        initBoard();  // Ініціалізація пустого поля
        playGame();   // Запуск гри
    }
    private static void initBoard() {
        // заміна \u0000 на ' ' в полі board (очищення поля)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // основний цикл
    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        int moves = 0;             // Кількість зроблених ходів
        boolean gameEnded = false; // Ознака завершення гри

        // максимум 9 ходів або до перемоги
        while (!gameEnded && moves < 9) {
            printBoard(); // Вивід поля
            System.out.println("Гравець " + currentPlayer + ", введіть рядок і стовпець (через пробіл): ");
            // Зчитування координат
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            //якщо рядок стовпець не виходять за межі і на вільній клітинці
            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer; // Робимо хід
                moves++; // Збільшуємо лічильник ходів

                //перевірка
                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println("Гравець " + currentPlayer + " переміг!");
                    gameEnded = true;
                } else if (moves == 9) {
                    printBoard();
                    System.out.println("Нічия!");
                } else {
                    switchPlayer(); // Зміна гравця
                }
            } else {
                System.out.println("Недійсний хід. Спробуйте ще раз.");
            }
        }
    }
    private static void printBoard() {
        // Виведення заголовку зі стовпцями
        System.out.println("  0 1 2");

        // Виведення кожного рядка
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // Номер рядка
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]); // Символ у клітинці
                if (j < 2) System.out.print("|"); // Роздільник між клітинками
            }
            System.out.println();
            if (i < 2) System.out.println("  -+-+-"); // Горизонтальні роздільники
        }
    }

    private static boolean isValidMove(int row, int col) {
        // Перевірка координатии
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    // Зміна гравця X → O або O → X
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWin(char player) {
        // Перевірка рядків і стовпців
        for (int i = 0; i < 3; i++) {
            // Якийсь з рядків належить одному гравцю
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player)
                return true;

            // Якийсь із стовпців  належить одному гравцю
            if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player)
                return true;
        }

        // діагональ \
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player)
            return true;

        // діагональ /
        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player)
            return true;

        return false; // Якщо нічого не знайшлося — перемоги нема
    }

}
