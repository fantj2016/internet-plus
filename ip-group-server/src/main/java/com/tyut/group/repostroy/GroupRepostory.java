package com.tyut.group.repostroy;

import com.tyut.core.pojo.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fant.J.
 * 2018/5/21 20:43
 */
@Repository
public interface GroupRepostory extends JpaRepository<Group,Integer> {

}
