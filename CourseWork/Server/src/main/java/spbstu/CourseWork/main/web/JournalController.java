package spbstu.CourseWork.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spbstu.CourseWork.main.dto.JournalDto;

import spbstu.CourseWork.main.entity.Journal;
import spbstu.CourseWork.main.exception.BookNotFoundException;
import spbstu.CourseWork.main.exception.JournalNotFoundException;
import spbstu.CourseWork.main.service.JournalService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Journal> findById(@PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(journalService.findById(id), HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-data-beg/{dataBeg}")
    public ResponseEntity<List<Journal>> findByDataBeg(@PathVariable("dataBeg") Timestamp dataBeg){
        try {
            List<Journal> journalList = journalService.findByDataBeg(dataBeg);
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-data-end/{dataEnd}")
    public ResponseEntity<List<Journal>> findByDataEnd(@PathVariable("dataEnd") Timestamp dataEnd){
        try {
            List<Journal> journalList = journalService.findByDataEnd(dataEnd);
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-data-ret/{dataRet}")
    public ResponseEntity<List<Journal>> findByDataRet(@PathVariable("dataRet") Timestamp dataRet){
        try {
            List<Journal> journalList = journalService.findByDataRet(dataRet);
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Journal>> findAll(){
        try {
            List<Journal> journalList = journalService.findAll();
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-journal-by-book-name/{bookName}")
    public ResponseEntity<List<Journal>> findJournalByBookName(@PathVariable("bookName") String bookName){
        try {
            List<Journal> journalList = journalService.findJournalByBookName(bookName);
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-journal-by-client-initials/{firstName} {lastName} {fatherName}")
    public ResponseEntity<List<Journal>> findJournalByClientInitials(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @PathVariable("fatherName") String fatherName){
        try {
            List<Journal> journalList = journalService.findJournalByClientInitials(firstName, lastName, fatherName);
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-journal-by-passport-seria-and-passport-num/{passportSeria} {passportNum}")
    public ResponseEntity<List<Journal>> findJournalByPassportSeriaAndPassportNum(
            @PathVariable("passportSeria") String passportSeria,
            @PathVariable("passportNum") String passportNum){
        try {
            List<Journal> journalList = journalService.findJournalByPassportSeriaAndPassportNum(passportSeria, passportNum);
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Journal> addJournal(@RequestBody JournalDto journalDto)
    {
        try {
            Journal journal = journalService.add(journalDto);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (JournalNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Journal> deleteBookByName(@PathVariable("id") Integer id){
        try {
            journalService.deleteJournalById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (BookNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}