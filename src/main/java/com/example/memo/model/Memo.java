// Memo user
package com.example.memo.model;

public class Memo {
    private Long id;
    private String title;
    private String content;

    public Memo() {} // Memo 생성자

    public Memo(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter & Setter -> 필드에 접근하기 위함
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
