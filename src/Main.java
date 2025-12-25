import model.*;
import model.dto.BookCreateDto;
import repository.UserRepository;
import service.BookingService;
import service.implementation.BookingServiceImpl;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Booking Process");
        User customer = new Customer();
        customer.setId(1);
        customer.setUserName("jame");
        customer.setEmail("jame123@gmail.com");
        customer.setPassword("@#$#%");
        // book
        Book book = new Book();
        book.setId(1);
        book.setUuid(UUID.randomUUID().toString());
        book.setBookedDate(Date.from(Instant.now()));
        book.setBookingName("Pass App Booking");
        book.setBookingStatus(BookingStatus.PENDING);
        //
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setVehicleName("Pass App");
        vehicle.setUuid(UUID.randomUUID().toString());
        book.setVehicle(vehicle);
        // booking process started
        BookingService bookingService = new BookingServiceImpl();
        BookCreateDto bookCreateDto = new BookCreateDto(
                (Customer) customer,
                book
        );
        bookingService.book(bookCreateDto);
        System.out.println(
                UserRepository.users
        );
    }
}
