package com.wanted.b_collection.a_list.dto;

public class BookDTO {

    // DTO(Data Transfer Object)
    // 행위(==메서드) 에 집중하는 클래스가 아닌 단순 데이터 운반을 위한 클래스이다.
    // 메서드가 아닌 필드들로만 이루어져 있다.
    // DTO 에 포함되어 있는 값.
    // 1. 필드, 2. 기본생성자, 3. 모든 필드를 초기화하는 생성자 4. getter, 5. setter, 6. toString

    // 1. 필드
    private int no; // 책 번호
    private String title; // 책 제목
    private String author; // 책 저자
    private int price; // 책 가격

    // 2. 기본생성자
    public BookDTO() {}

    // 3. 모든 필드를 초기화하는 생성자
    public BookDTO(int no, String title, String author, int price) {
        this.no = no;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // 4. getter 5. setter
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // 6. toString
    @Override
    public String toString() {
        return "BookDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
