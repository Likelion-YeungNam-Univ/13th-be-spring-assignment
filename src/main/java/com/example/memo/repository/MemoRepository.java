package com.example.memo.repository;

import com.example.memo.model.Memo;
import java.util.*;

// JPA 사용 지양으로 인해 데이터를 프로그램안에서만 임시로 저장하기위한 클래스
public class MemoRepository {
    private static final Map<Long, Memo> store = new HashMap<>();
    private static long sequence = 0L;

    // 모든 메모 조회
    public List<Memo> findAll() {
        return new ArrayList<>(store.values()); // Map 내부가 바뀔 위험성으로 인해 복사본을 리턴
    }

    // ID로 메모 조회
    public Memo findById(Long id) {
        return store.get(id);
    }

    // 새 메모저장하기
    public Memo save(Memo memo) {
        memo.setId(++sequence); // ID는 자동으로 증가하게 만들어줌
        store.put(memo.getId(), memo);
        return memo;
    }

    // 메모 수정
    public void update(Long id, Memo memo) {
        memo.setId(id);
        store.put(id, memo);
    }

    // 메모 삭제
    public void delete(Long id) {
        store.remove(id);
    }
}
