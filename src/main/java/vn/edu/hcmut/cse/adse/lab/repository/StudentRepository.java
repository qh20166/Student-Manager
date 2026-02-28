package vn.edu.hcmut.cse.adse.lab.repository;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, String> {

    List<Student> findByNameContainingIgnoreCase(String name);
    // Spring sẽ tự hiểu là: SELECT * FROM students ORDER BY id ASC
    List<Student> findAllByOrderByIdAsc();

    // Nếu muốn tìm kiếm cũng trả về kết quả đã sắp xếp:
    List<Student> findByNameContainingIgnoreCaseOrderByIdAsc(String name);
}
