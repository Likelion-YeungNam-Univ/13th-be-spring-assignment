package com.example.springsession.user;

public class User {
    private String id;
    private String name;
    private String intro;
    //스프링에서 기본생성자 생성
    public User(){}
    //사용하는 데이터의 종류는 학번/이름/자기소개 3가지
    public User(String id, String name, String intro) { //생성자
        this.id = id;
        this.name = name;
        this.intro = intro;
    }
    //getter start part
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntro() {
        return intro;
    }

    ///////getter end & setter start part
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
