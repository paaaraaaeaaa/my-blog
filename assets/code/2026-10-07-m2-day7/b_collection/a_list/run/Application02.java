package com.wanted.b_collection.a_list.run;

import com.wanted.b_collection.a_list.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class Application02 {
    public static void main(String[] args) {

        /* comment. ArrayList 활용!
        *   - 책은 책번호, 제목, 저자, 가격이 있다.
        *   - 5권의 책을 하나의 변수에 저장을 한다.
        *   - 가격 오름차순으로 정렬을 해본다.
        * */

//        BookDTO book1 = new BookDTO(1, "홍길동전", "허균", 50000);
//        BookDTO book2 = new BookDTO(2, "목민심서", "정약용", 45000);
//        BookDTO book3 = new BookDTO(3, "삼국지", "유비", 30000);
//        BookDTO book4 = new BookDTO(4, "마법천자문", "손오공", 20000);
//        BookDTO book5 = new BookDTO(5, "삼국유사", "일연", 58000);

        // BookDTO 타입의 객체를 저장할 수 있는 List
        List<BookDTO> bookList = new ArrayList<>();
        bookList.add(new BookDTO(1, "홍길동전", "허균", 50000));
        bookList.add(new BookDTO(2, "목민심서", "정약용", 45000));
        bookList.add(new BookDTO(3, "삼국지", "유비", 30000));
        bookList.add(new BookDTO(4, "마법천자문", "손오공", 20000));
        bookList.add(new BookDTO(5, "삼국유사", "일연", 58000));

        System.out.println("bookList = " + bookList);

        // 반복문을 활용해서 책 1개씩 출력
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println((i+1) + "번째 책 : " + bookList.get(i));
        }

        // 향상된 for 문
        // for (컬렉션 반복 시 1개의 값을 담을 변수: 컬렉션 객체)
        for (BookDTO book : bookList) {
            System.out.println(book.getNo() + "번째 책 : " + book);
        }


    }

}
