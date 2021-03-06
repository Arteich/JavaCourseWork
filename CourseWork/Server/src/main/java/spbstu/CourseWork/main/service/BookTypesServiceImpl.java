package spbstu.CourseWork.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spbstu.CourseWork.main.dto.BookTypesDto;
import spbstu.CourseWork.main.entity.BookTypes;
import spbstu.CourseWork.main.exception.BookTypeNotFoundException;
import spbstu.CourseWork.main.repository.BookTypesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookTypesServiceImpl implements BookTypesService {

    @Autowired
    private BookTypesRepository bookTypesRepository;

    @Override
    public BookTypes findById(Integer id) {
        Optional<BookTypes> ap = bookTypesRepository.findById(id);
        if(ap.isPresent()){
            return ap.get();
        }else{
            throw new BookTypeNotFoundException("Book type not found");
        }
    }

    @Override
    public BookTypes findByName(String name) {
        Optional<BookTypes> ap = bookTypesRepository.findByName(name);
        if(ap.isPresent()){
            return ap.get();
        }else{
            throw new BookTypeNotFoundException("Book type not found");
        }
    }

    @Override
    public List<BookTypes> findByCnt(Integer cnt) {
        return (List<BookTypes>) bookTypesRepository.findByCnt(cnt);
    }

    @Override
    public List<BookTypes> findAll() {
        return (List<BookTypes>) bookTypesRepository.findAll();
    }

    @Override
    public List<BookTypes> findByDayCnt(Integer dayCnt) {
        return (List<BookTypes>) bookTypesRepository.findByDayCount(dayCnt);
    }

    @Override
    public BookTypes add(BookTypesDto bookTypeDTO) {
        String name = bookTypeDTO.getName();
        int cnt = bookTypeDTO.getCnt();
        int fine = bookTypeDTO.getFine();
        int dayCount = bookTypeDTO.getDayCount();
        BookTypes bookType = new BookTypes(name, cnt, fine, dayCount);
        bookTypesRepository.save(bookType);
        return bookType;
    }

    @Override
    public void deleteBookTypeByName(String name) {
        bookTypesRepository.deleteBookTypeByName(name);
    }
}
