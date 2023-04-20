package com.op.restdatalibrary;

import com.op.restdatalibrary.entities.Book;
import com.op.restdatalibrary.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class RestdatabooksApplication {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(RestdatabooksApplication.class, args);
		BookRepository bookRepository=context.getBean(BookRepository.class);

		Book book1=new Book(null,"Homo","Yuval",450,29.99, LocalDate.of(2020,04,14),true);
		Book book2=new Book(null,"Titanic","Harry",7650,99.99, LocalDate.of(2017,04,14),true);
		bookRepository.save(book1);
		bookRepository.save(book2);

		bookRepository.findAll();

	}

}
