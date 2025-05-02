package com.example.springsession.controller;
import com.example.springsession.user.User;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class Controller {
    private final List<User> users = new ArrayList<>(); //데이터 반환 리스트 선언
    //ArrayList를 사용한 이유는 데이터 읽기 탐색에 용이해서이다.
    @GetMapping
    public List<User> getAllUsers()
    {
        return users; //등록된 유저 정보를 다 불러오기
    }

    //id 데이터를 기준으로 데이터 불러오기
    @GetMapping("/{id}")
    public User get_data(@PathVariable("id") String id) //pathvariable 값과 변수 이름이 동일하면 생략가능하지만 여기서 생략X
    {
        //stream 대체 형식
        //for (User user : users){ System.out.println(user.getId());}
        return users.stream()
                .filter(user -> user.getId().equals(id))//매개변수에서 입력한 id 기준으로 분리
                //람다식이 사용되는 과정에서 users안의 데이터를 분리
                //분리하는 기준
                .findFirst()
                .orElseThrow(() -> new RuntimeException("id not found!!!"));
    }

    @PostMapping //데이터를 생성하므로  RequestBody
    public User generate_data(@RequestBody User user) //User 클래스 데이터를 입력
    {
        users.add(user); //ArrayList 항목에 데이터를 추가한다.
        return user; //데이터를 추가 후 입력한 데이터를 반환
    }

    @PutMapping("/{id}") //Put은 전체 데이터를 수정
    public User update_Alldata(@PathVariable String id, @RequestBody User data)
    {
        User user = users.stream()
                .filter( split_user -> split_user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("id not found!!!"));
        user.setName(data.getName());
        user.setIntro(data.getIntro());
        //users.remove(user); -> Test
        //users.add(user);
        return user;
    }

    @PatchMapping("/{id}") //Patch는 일부 데이터만을 수정
    public User updatae_pdata(@PathVariable String id, @RequestBody User data)
    {
        User user = users.stream()
                .filter( split_user -> split_user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("id not found!!!"));
        if(data.getName()!=null) //새로 입력한 데이터 중에 이름이 있다면
        {
            user.setName(data.getName()); //새로운 이름으로 등록
        }
        if(data.getIntro()!=null) //새로 입력한 데이터 중에 자기소개가 있다면
        {
            user.setIntro(data.getIntro()); //새로운 자기소개 등록
        }
        return user; //확인용 user 데이터 반환
    }

    @DeleteMapping("/{id}")
    public String delete_data(@PathVariable String id)
    {
        User user = get_data(id);
        users.remove(user); //ArrayList 데이터 삭제
        return "user data deleted = "+ id; //user 데이터 삭제 문구 출력
    }

}
