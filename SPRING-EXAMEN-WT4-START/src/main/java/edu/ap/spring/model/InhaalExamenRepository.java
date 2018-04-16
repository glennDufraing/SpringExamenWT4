package edu.ap.spring.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InhaalExamenRepository extends CrudRepository<InhaalExamen,Long>{

	List<InhaalExamen> findByStudentOrderByReason (String student);
}
