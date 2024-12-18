import java.util.Random;

public class MovieSession {
    String movieName;
    String date;
    double price;
    boolean[][] seats;

    public MovieSession(String movieName, String date, double price, int rows, int cols) {
        this.movieName = movieName;
        this.date = date;
        this.price = price;
        this.seats = new boolean[rows][cols]; 
        generateRandomTakenSeats();
    }

    private void generateRandomTakenSeats() {
        int totalSeats = seats.length * seats[0].length;
        int takenSeats = totalSeats / 4;

        Random random = new Random();
        for (int i = 0; i < takenSeats; i++) {
            int row, col;
            do {
                row = random.nextInt(seats.length);
                col = random.nextInt(seats[0].length);
            } while (seats[row][col]);

            seats[row][col] = true;
        }
    }
}

