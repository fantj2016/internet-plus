package com.tyut.school.repository;

import com.tyut.core.pojo.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/4/24 18:48
 */
@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {

//    @Query(value = "select s from School s where s.schoolName like %:likeName%")
    @Query("select s.id,s.schoolName from School s where s.schoolName like %?1%")
    List<School> getSchoolBySchoolNameLike(@Param("likeName") String likeName);

}
