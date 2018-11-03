package com.tyut.user.repostory;

import com.tyut.core.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepostory extends JpaRepository<Teacher,Integer> {
    Teacher findByGroupId(Integer groupId);
}
