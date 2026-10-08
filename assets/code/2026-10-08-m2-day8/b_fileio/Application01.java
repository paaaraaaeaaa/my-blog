
package com.wanted.b_fileio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Application01 {
    public static void main(String[] args) {

        // 파일 쓰기
        try {
            // File IO 관련 클래스들은 객체 생성 시 예외를 반드시 처리하게 설정이 되어 있다.
            FileWriter writer = new FileWriter("output.txt");
            writer.write("Hello, File IO!!!");
            writer.write("File Test");

            // 버퍼(연결통로)에 있는 데이터를 밀어서 디스크에 저장한다.
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 파일 읽기 작업
        try {
            FileReader reader = new FileReader("output.txt");

            int data;
            // read() : 파일에서 한 문자씩 읽고, 파일 끝에 도달하면 -1 을 반환한다.
            while ((data = reader.read()) != -1) {
                System.out.println((char)data);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
