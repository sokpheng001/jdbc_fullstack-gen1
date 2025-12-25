package service;

import model.Book;
import model.BookingStatus;
import model.dto.BookCreateDto;

public interface BookingService {
    Book book(BookCreateDto book);
    Boolean cancelBook(BookingStatus status
            , String bookingUuid);
}
