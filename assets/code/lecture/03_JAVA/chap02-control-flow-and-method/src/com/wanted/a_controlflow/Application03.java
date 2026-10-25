package com.wanted.a_controlflow;

public class Application03 {

    public static void main(String[] args) {

        /* comment.
        *   A && B
        *   - && 연산자는 좌항/우항이 모두 true 이여야지만 true를 반환한다.
        *   - 둘 중 하나라도 false 이면 무조건 false 이다.
        *   - <회원가입 조건>
        *   - A : 아이디 중복 여부 판단 (1분 걸리는 작업)
        *   - B : 비밀번호 8글자 미만 여부 판단 (0.5초 걸리는 작업)
        *   - 회원가입 시 if (A && B) { 회원가입 성공!! } else { 회원가입 실패! }
        *   - 만약 사용자가 A, B 둘 중 하나의 작업을 실패하게 된다면??
        *   - 성공시점? 실패시점?
        *   - B 를 좌항에 두게 된다면 0.5초 만에 회원가입 실패를 알 수 있게 된다.
        *   - A 를 좌항에 두게 된다면 1분 만에 회원가입 실패를 알 수 있게 된다.
        * */

        /* comment.
        *   단축 평가 (short-circuit evaluation)
        *   AND 연산 (&&)
        *   - 두 피연산자가 모두 참일때만 참이다.
        *   - 첫 번째 피연산자가 false 인 경우, 두 번째 피연산자는 실행하지 않는다.
        *   - false 확률이 높은 조건을 좌항에 작성하는 것이 대규모 트레픽, 메모리 최적화 관점에서 유리하다.
        *   OR 연산 (||)
        *   - 두 피연산자 중 1개라도 참이면 참이다.
        *   - true 확률이 높은 조건을 좌항에 작성하는 것이 유리하다.
        * */

        int age = 25; // 테스트용 변수 선언 및 초기화
        String discount; // 변수 선언

        // 비효율적 조건 순서 테스트 : 발생 확률이 드문 조건 먼저
        // age 가 19와 같은 지 비교 후 else 검사
        System.out.println("=========== 비효율적 조건 순서 ===========");
        long startTime = System.nanoTime(); // 시작 시간 체크

        if(age <= 19) { // 드문 조건
            discount = "학생 할인 가능";
        } else {
            discount = "할인 불가";
        }

        long endTime = System.nanoTime(); // 끝나는 시간 체크
        System.out.println("결과 : " + discount + ", 시간 : " + (endTime - startTime) + "(ns)");

        System.out.println("=========== 효율적 조건 순서 ===========");
        long startTime2 = System.nanoTime(); // 시작 시간 체크

        if(age > 19) { // 자주 발생 조건
            discount = "학생 할인 가능";
        } else {
            discount = "할인 불가";
        }

        long endTime2 = System.nanoTime(); // 끝나는 시간 체크
        System.out.println("결과 : " + discount + ", 시간 : " + (endTime2 - startTime2) + "(ns)");




    }
}
