package com.boot;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Long>   {
	
	@Query(value = "SELECT s FROM Subject s WHERE s.subtitle LIKE '%' || :keyword || '%'"
			+ " OR s.durationinhours LIKE '%' || :keyword || '%'")
    public List<Subject> search(@Param("keyword") String keyword);
	
	public List<Subject> findBySubtitle(String subTitle);

}
