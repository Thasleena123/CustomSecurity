package com.example.library.sevice;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;
import com.example.library.utility.CustomToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    public String borrowBook(String token,long bookId){
        if(!CustomToken.validateToken(token)){
            throw  new RuntimeException("Invalid token");
        }
        String userDepartment=CustomToken.extractDepartment(token);
        Optional<BookEntity> bookEntityOptional=bookRepository.findById(bookId);
        if(bookEntityOptional.isEmpty()){
            throw  new RuntimeException("Book not found");
        }
        BookEntity book=bookEntityOptional.get();
        if (!userDepartment.equals(book.getDepartment())) {
            throw new RuntimeException("Access denied: You cannot borrow this book");
        }
        return  "book borrowed successfully";
    }
}
