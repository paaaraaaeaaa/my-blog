package com.wanted.oop.b_oop.d_abstraction.lab;

/* comment. 교수님(대상)
 *   자기 호감도와 면담 횟수를 관리하고, 자기 정보를 문자열로 알려준다.
 *   같은 Professor 클래스로 객체를 두 개(꼼꼼한 교수, 온화한 교수) 만든다. 생성자에 넘기는 값만 다르다.
 *   호감도와 면담 횟수는 객체마다 따로 쌓인다.
 *   묻는 메서드(canTalk, isFavorFull, getName, getCost, getGain)와 toString() 은 출력하지 않는다.
 *   (canTalk, isFavorFull 은 true/false 만 돌려준다.)
 *   안내 문구는 Student 가 출력한다.
 *   Student 가 먼저 검사하지만, 대상 객체도 자기 상태를 스스로 지킨다.
 *  */

public class Professor {

    // 변하지 않는 값 : 생성자로 받은 뒤 바뀌지 않는다.
    // 이름, 체력 소모량(%), 호감도 증가량 (꼼꼼한 교수 30, 2 / 온화한 교수 10, 1)
    private final String name;
    private final int cost;
    private final int gain;

    // 변하는 상태 : 호감도, 오늘 면담 횟수 (교수님마다 따로 쌓인다.)
    private int favor;
    private int talkCount;

    // 변하지 않는 값 (static 을 배우기 전이므로 private final 필드로 둔다.)
    // (상수 없이 isFavorFull() 에서 호감도 목표 10, canTalk() 에서 하루 최대 면담 횟수 3 을 숫자로 직접 쓴다.)

    // 교수님은 이름, 체력 소모량(%), 호감도 증가량을 생성자로 받는다.
    public Professor(String name, int cost, int gain) {
        this.name = name;
        this.cost = cost;
        this.gain = gain;
    }

    // 면담 가능한지 알려줘라 (하루 최대 3번, 출력 없음)
    public boolean canTalk() {
        if (talkCount < 3) {
            return true;
        } else {
            return false;
        }
    }

    // 이름을 알려줘라
    public String getName() {
        return name;
    }

    // 소모량을 알려줘라
    public int getCost() {
        return cost;
    }

    // 호감도 증가량을 알려줘라
    public int getGain() {
        return gain;
    }

    // 면담받아라
    // 안에서 canTalk() 를 한 번 더 확인하고, false 면 아무것도 바꾸지 않는다.
    // 호감도 + 증가량, 면담 횟수 +1
    public void talk() {
        if (canTalk()) {
            this.favor += gain;
            this.talkCount++;
        }
    }

    // 호감도가 가득 찼는지 알려줘라 (favor >= 10, 출력 없음)
    public boolean isFavorFull() {
        if (favor >= 10) {
            return true;
        } else {
            return false;
        }
    }

    // 하루 넘겨라 : 면담 횟수 0 (호감도는 유지)
    // Student.rest() 가 두 교수님 모두에게 부른다.
    public void newDay() {
        this.talkCount = 0;
    }

    // 정보를 문자열로 알려줘라. 예) 꼼꼼한 교수 | 호감도 4/10 | 오늘 면담 2/3
    // (출력은 하지 않는다. Student 가 System.out.println(professor) 로 출력한다.)
    @Override
    public String toString() {
        return name + " | 호감도 " + favor + "/10 | 오늘 면담 " + talkCount + "/3";
    }

}
