import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie {
    private String title;
    private String genre;
    private int releaseYear;
    private boolean isAvailable;

    public Movie(String title, String genre, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.isAvailable = true;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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

class Rental {
    private Movie movie;
    private Customer customer;
    private String rentalDate;
    private String dueDate;

    public Rental(Movie movie, Customer customer, String rentalDate, String dueDate) {
        this.movie = movie;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return movie + " rented by " + customer + " (Due: " + dueDate + ")";
    }
}

class MovieRentalSystem {
    private List<Movie> movies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

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

    public void rentMovie(String title, String customerName, String rentalDate, String dueDate) {
        Movie movie = findMovieByTitle(title);
        Customer customer = findCustomerByName(customerName);

        if (movie != null && customer != null) {
            if (movie.isAvailable()) {
                movie.setAvailable(false);
                Rental rental = new Rental(movie, customer, rentalDate, dueDate);
                rentals.add(rental);
                System.out.println("Movie rented successfully.");
            } else {
                System.out.println("Movie is not available.");
            }
        } else {
            System.out.println("Invalid movie or customer.");
        }
    }

    public void returnMovie(String title, String customerName) {
        Rental rental = findRental(title, customerName);

        if (rental != null) {
            Movie movie = rental.getMovie();
            movie.setAvailable(true);
            rentals.remove(rental);
            System.out.println("Movie returned successfully.");
        } else {
            System.out.println("No rental record found.");
        }
    }

    public void listRentals() {
        System.out.println("Rentals:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    private Movie findMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    private Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    private Rental findRental(String title, String customerName) {
        for (Rental rental : rentals) {
            if (rental.getMovie().getTitle().equalsIgnoreCase(title) &&
                    rental.getCustomer().getName().equalsIgnoreCase(customerName)) {
                return rental;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieRentalSystem system = new MovieRentalSystem();

        system.addMovie(new Movie("Inception", "Sci-Fi", 2010));
        system.addMovie(new Movie("The Matrix", "Sci-Fi", 1999));
        system.addCustomer(new Customer("John Doe", "123-456-7890"));
        system.addCustomer(new Customer("Jane Smith", "098-765-4321"));

        while (true) {
            System.out.println("\nMovie Rental System");
            System.out.println("1. List Movies");
            System.out.println("2. List Customers");
            System.out.println("3. Rent Movie");
            System.out.println("4. Return Movie");
            System.out.println("5. List Rentals");
            System.out.println("6. Exit");

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
                    System.out.print("Enter movie title: ");
                    String rentTitle = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String rentCustomer = scanner.nextLine();
                    System.out.print("Enter rental date (YYYY-MM-DD): ");
                    String rentDate = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    system.rentMovie(rentTitle, rentCustomer, rentDate, dueDate);
                    break;
                case 4:
                    System.out.print("Enter movie title: ");
                    String returnTitle = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String returnCustomer = scanner.nextLine();
                    system.returnMovie(returnTitle, returnCustomer);
                    break;
                case 5:
                    system.listRentals();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
