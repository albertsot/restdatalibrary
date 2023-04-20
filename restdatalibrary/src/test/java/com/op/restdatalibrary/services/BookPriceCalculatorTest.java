package com.op.restdatalibrary.services;

import com.op.restdatalibrary.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calcularPriceTest() {
        BookPriceCalculator bookPriceCalculator=new BookPriceCalculator();

        Book book=new Book(1L,"Harry Pooter","Yuval",450,29.99, LocalDate.of(2020,04,14),true);
        Double precio=bookPriceCalculator.calcularPrice(book);
        System.out.println(precio);
        assertTrue(precio>0);
        assertEquals(37.98,precio);

    }
}