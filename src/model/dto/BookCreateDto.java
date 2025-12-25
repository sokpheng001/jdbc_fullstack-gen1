package model.dto;

import model.Book;
import model.Customer;

public record BookCreateDto(
        Customer customer,
        Book book
) {
}
