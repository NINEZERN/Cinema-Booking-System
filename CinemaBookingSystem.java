import java.util.*;

public class CinemaBookingSystem {

    private static Map<Integer, MovieSession> sessions = new HashMap<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        initializeSessions(); 
        runBookingSystem();
    }

    private static void initializeSessions() {
        String[] movies = {"Avatar", "Inception", "Titanic", "Matrix", "Interstellar"};
        String[] dates = {"2024-12-12", "2024-12-13", "2024-12-14"};
        for (int i = 1; i <= 5; i++) {
            String movie = movies[random.nextInt(movies.length)];
            String date = dates[random.nextInt(dates.length)];
            double price = 10 + random.nextInt(11);
            sessions.put(i, new MovieSession(movie, date, price, 5, 5));
        }
    }

    private static void runBookingSystem() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Cinema Booking System ===");
            System.out.println("1. Show available sessions");
            System.out.println("2. Book a seat");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> showSessions();
                case 2 -> bookSeat(scanner);
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showSessions() {
        System.out.println("\nAvailable Movie Sessions:");
        for (Map.Entry<Integer, MovieSession> entry : sessions.entrySet()) {
            int id = entry.getKey();
            MovieSession session = entry.getValue();
            System.out.printf("ID: %d | Movie: %s | Date: %s | Price: $%.2f\n",
                    id, session.movieName, session.date, session.price);
        }
    }

    private static void bookSeat(Scanner scanner) {
        System.out.print("\nEnter the session ID: ");
        String sessionIdInput = scanner.nextLine();

        if (!sessionIdInput.matches("\\d+")) {
            System.out.println("Invalid session ID. Please enter a number.");
            return;
        }

        int sessionId = Integer.parseInt(sessionIdInput);
        MovieSession session = sessions.get(sessionId);

        if (session == null) {
            System.out.println("Invalid session ID.");
            return;
        }

        System.out.printf("Selected session: %s on %s ($%.2f)\n", session.movieName, session.date, session.price);
        displaySeatGrid(session.seats);

        System.out.print("Enter seat (row-column, e.g., 1-3): ");
        String seatInput = scanner.nextLine();

        if (!seatInput.matches("\\d+-\\d+")) {
            System.out.println("Invalid seat format. Use row-column (e.g., 1-3).\n");
            return;
        }

        String[] parts = seatInput.split("-");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        if (row < 1 || row > session.seats.length || col < 1 || col > session.seats[0].length) {
            System.out.println("Invalid seat position.\n");
            return;
        }

        if (session.seats[row - 1][col - 1]) {
            System.out.println("Seat is already booked.\n");
        } else {
            session.seats[row - 1][col - 1] = true;
            System.out.println("Seat booked successfully!\n");
        }
    }

    private static void displaySeatGrid(boolean[][] seats) {
        System.out.println("\nSeat Layout (O = Available, X = Taken):");
        System.out.print("   ");
        for (int col = 1; col <= seats[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < seats.length; row++) {
            System.out.print((row + 1) + "  ");
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print((seats[row][col] ? "X" : "O") + " ");
            }
            System.out.println();
        }
    }
}
