package exam.management.be.repository;

import exam.management.be.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, Integer> {
    @Query(value = "SELECT NEW map(m.id as id, m.name as name, m.code as code) FROM modules m", nativeQuery = true)
    List<Map<String, Object>> listModules();

    @Modifying
    @Query(value = "SELECT m.name FROM Modules m WHERE m.id IN ?1")
    List<String> listModuleNamesById(List<Integer> idList);

    @Modifying
    @Query("SELECT m.name FROM Modules m " +
            "LEFT JOIN Speciality s ON s.moduleId = m.id " +
            "LEFT JOIN Lecturers l ON l.id = s.lecturerId " +
            "WHERE l.id = ?1")
    List<String> listModuleNamesByLecturerId(Integer lecturerId);
}
