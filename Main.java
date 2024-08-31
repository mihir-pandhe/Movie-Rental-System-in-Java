import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie {
    private String title;
    private String genre;
    private int releaseYear;

    public Movie(String title, String genre, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + releaseYear + ")";
    }
}

class Customer {
    private String name;
    private String contactInfo;

    public Customer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return name + " (" + contactInfo + ")";
    }
}

class MovieRentalSystem {
    private List<Movie> movies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void listMovies() {
        System.out.println("Movies:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public void listCustomers() {
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieRentalSystem system = new MovieRentalSystem();

        system.addMovie(new Movie("The Matrix", "Sci-Fi", 1999));
        system.addMovie(new Movie("Inception", "Sci-Fi", 2010));
        system.addCustomer(new Customer("Jane Smith", "098-765-4321"));
        system.addCustomer(new Customer("John Doe", "123-456-7890"));

        while (true) {
            System.out.println("\nMovie Rental System");
            System.out.println("1. List Movies");
            System.out.println("2. List Customers");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    system.listMovies();
                    break;
                case 2:
                    system.listCustomers();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
