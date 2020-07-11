package mng.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mng.model.Document;

public interface FilesRepository extends CrudRepository<Document, Long> {
	
	
	List<Document> findByProperty(Long id);

}
