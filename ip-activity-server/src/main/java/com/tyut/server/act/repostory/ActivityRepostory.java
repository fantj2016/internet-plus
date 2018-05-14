package com.tyut.server.act.repostory;

import com.tyut.server.act.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Fant.J.
 * 2018/4/23 22:04
 */
public interface ActivityRepostory extends JpaRepository<Activity,Integer> {
}
