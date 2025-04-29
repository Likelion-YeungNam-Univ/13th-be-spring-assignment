package com.example.springsession.service;

import com.example.springsession.dto.MemoDto;
import com.example.springsession.entity.Memo;
import com.example.springsession.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoService {
    @Autowired
    private MemoRepository memoRepository;


    public Memo add(MemoDto dto){
        Memo memo = dto.toEntity();
        return memoRepository.save(memo);
    }

    public List<Memo> getAll(){
        return memoRepository.findAll();
    }


    public Memo find(Long id) {
        return memoRepository.findById(id).orElse(null);
    }

    @Transactional
    public MemoDto putUpdate(Long id, MemoDto memoDto) {
        Memo target = find(id);
        if (memoDto.checkNull() || target == null) return null; // put 방식에서 완전한 JSON 형태로 도착해야함
        target.put(memoDto);
        Memo edited = memoRepository.save(target);
        return edited.toDto();
    }

    @Transactional
    public MemoDto patchUpdate(Long id, MemoDto memoDto){
        Memo target = find(id);
        if(target == null) return null;
        target.patch(memoDto);
        Memo edited = memoRepository.save(target);
        return edited.toDto();
    }

    @Transactional
    public Memo delete(Long id) {
        Memo memo = find(id);
        if(memo == null) return null;
        memoRepository.delete(memo);
        return memo;
    }


}
