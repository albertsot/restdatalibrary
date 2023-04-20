package com.op.restdatalibrary.controller;

import com.op.restdatalibrary.entities.Book;
import com.op.restdatalibrary.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;
    public static final Logger logger= LoggerFactory.getLogger(BookController.class);
    @Value("${app.message}")
    private String mensaje;
    /**
     * http://localhost:8080/api/books
     * @return
     */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        System.out.println(mensaje);
        return bookRepository.findAll();

    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> finById(@PathVariable Long id){
         Optional<Book> optionBooks=bookRepository.findById(id);
         if (optionBooks.isPresent()){
             return new ResponseEntity<>(optionBooks.get(),HttpStatus.OK);
         }
         else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        if(book.getId()!=null){
            logger.warn("Tryin to create a  existent book");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookRepository.save(book),HttpStatus.CREATED);
    }
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody  Book book){
        if(book.getId()==null){
            logger.warn("Tryin to update a no existent book");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!bookRepository.existsById(book.getId())){
            logger.warn("Tryin to update a no existent book");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(bookRepository.save(book),HttpStatus.OK);
    }
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        if(bookRepository.findById(id).isPresent()){
            logger.warn("Trying deleting id of book");
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            logger.warn("Id of book not found for deleting");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
