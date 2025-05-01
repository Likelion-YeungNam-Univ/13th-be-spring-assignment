package com.example.springsession.service;

import com.example.springsession.dto.MemoDto;
import com.example.springsession.entity.Memo;
import com.example.springsession.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoService {
    private final MemoRepository memoRepository;
    private Long addId = 1L; // 추가할 때 id가 1씩 증가

    public Memo add(MemoDto dto){
        if(dto.checkNull()) return null;
        dto.setId(addId++);
        log.info(dto.getId() + " " + dto.getTitle() + " " + dto.getContent() + " " + dto.getAuthor());
        Memo memo = dto.toEntity();
        return memoRepository.save(memo);
    }

    public List<Memo> getAll(){
        return memoRepository.findAll();
    }

    public Memo find(Long id) {
        return memoRepository.findById(id);
    }

    public MemoDto putUpdate(Long id, MemoDto memoDto) {
        Memo target = find(id);
        if (memoDto.checkNull() || target == null) return null; // put 방식에서 완전한 JSON 형태로 도착해야함
        target.put(memoDto);
        Memo edited = memoRepository.save(target);
        return edited.toDto();
    }

    public MemoDto patchUpdate(Long id, MemoDto memoDto){
        Memo target = find(id);
        if(target == null) return null;
        target.patch(memoDto);
        Memo edited = memoRepository.save(target);
        return edited.toDto();
    }

    public MemoDto delete(Long id) {
        Memo memo = find(id);
        if(memo == null) return null;
        MemoDto delete = memo.toDto();
        memoRepository.delete(memo);
        return delete;
    }
}
