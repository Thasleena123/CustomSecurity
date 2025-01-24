package com.example.library.controller;

import com.example.library.sevice.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestHeader("Authorization") String token, @RequestParam Long bookId) {
        String message = libraryService.borrowBook(token, bookId);
        return ResponseEntity.ok(message);
    }
}
