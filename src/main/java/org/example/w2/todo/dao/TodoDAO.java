package org.example.w2.todo.dao;

import lombok.extern.log4j.Log4j2;
import org.example.w2.todo.TodoVO;

import java.util.List;

@Log4j2
public enum TodoDAO {
    INSTANCE;

    TodoDAO() {

    }

    public List<TodoVO> list(){

        log.info("list");

        return null;
    }
}
