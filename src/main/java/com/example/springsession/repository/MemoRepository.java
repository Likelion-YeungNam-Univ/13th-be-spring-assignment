package com.example.springsession.repository;

import com.example.springsession.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Repository
public class MemoRepository {
    private final TreeMap<Long, Memo> memoDB;

    public MemoRepository() {
        memoDB = new TreeMap<Long, Memo>();
    }

    public List<Memo> findAll() {
        List<Memo> memos = new ArrayList<Memo>();
        for (Long id : memoDB.keySet()) {
            memos.add(memoDB.get(id));
        }
        return memos;
    }

    public Memo findById(Long id) {
        return memoDB.get(id);
    }

    public Memo save(Memo memo) {
        memoDB.put(memo.getId(), memo);
        return memoDB.get(memo.getId());
    }

    public void delete(Memo memo) {
        memoDB.remove(memo.getId());
    }
}
