package com.op.restdatalibrary.services;

import com.op.restdatalibrary.entities.Book;

public class BookPriceCalculator {
    public Double calcularPrice(Book book){
        Double precio=book.getPrice();
        if(book.getPages()>300){
            precio+=5;
        }
        precio+=2.99;
        return precio;
    }
}
