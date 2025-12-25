package service.implementation;

import model.Book;
import model.BookingStatus;
import model.Customer;
import model.History;
import model.dto.BookCreateDto;
import repository.UserRepository;
import service.BookingService;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class BookingServiceImpl implements BookingService {
    @Override
    public Book book(BookCreateDto book) {
        // booking logic
        // dependency relationship
        History history = new History();
        history.setId(1);
        history.setUuid(UUID.randomUUID().toString());
        history.setHistoryDate(Date.from(Instant.now()));
        history.getBooks().add(book.book());
        // save booking history for customer
        Customer customer = book.customer();
        customer.getHistories().add(history);
        UserRepository.users.add(customer);
        System.out.println("Booking is on the process");
        return book.book();
    }

    @Override
    public Boolean cancelBook(BookingStatus status,
                              String bookingUuid) {
        return null;
    }
}
