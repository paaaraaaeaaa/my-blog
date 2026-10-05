package com.wanted.oop.b_oop.d_abstraction.lab;

/* comment. 학생(행위자)
 *   사용자 명령을 받아 두 교수님(꼼꼼한 교수, 온화한 교수) 중 고른 분에게 전달하고, 자기 체력·지식·일차를 바꾼다.
 *   안내 문구와 체력 바는 Student 만 출력한다. (Professor 는 출력하지 않는다.)
 *   클리어(추천서 획득), 번아웃, 기한 초과 판정도 Student 가 한다.
 *   Application 은 Professor 에 접근하면 안 된다. 교수님 번호(1, 2)만 Student 에게 넘긴다. 그래서 캡슐화 씀
 *  */

public class Student {

    // 변하는 상태 후보군
    // 체력(%), 지식, 일차(1일차에서 시작), 게임 종료 여부
    private int stamina = 100;
    private int knowledge;
    private int day = 1;
    private boolean finished;

    // 클래스도 자료형이다. Professor 는 Student 만 접근해야 한다.
    // 같은 Professor 클래스로 객체를 두 개 만든다. 생성자에 넘기는 값만 다르다.
    private Professor strictProfessor = new Professor("꼼꼼한 교수", 30, 2);
    private Professor kindProfessor = new Professor("온화한 교수", 10, 1);

    // 변하지 않는 값 (static 을 배우기 전이므로 private final 필드로 둔다.)
    // MAX_DAY : 기한 5일 / STUDY_COST : 공부 소모량 / REST_AMOUNT : 휴식 회복량
    // BONUS_KNOWLEDGE : 면담 소모량이 절반이 되는 지식 / MIN_COST : 면담 소모량 최솟값
    private final int MAX_DAY = 5;
    private final int MAX_STAMINA = 100;
    private final int STUDY_COST = 10;
    private final int REST_AMOUNT = 30;
    private final int BONUS_KNOWLEDGE = 5;
    private final int MIN_COST = 10;

    // 면담해라 (교수님 번호와 함께)
    // 1 : 꼼꼼한 교수, 2 : 온화한 교수, 그 외 : 잘못된 번호
    // 잘못된 번호는 안내 문구만 출력한다. (체력 바 없음, 상태 변화 없음)
    public void talk(int no) {
        if (no == 1) {
            talkWith(strictProfessor);
        } else if (no == 2) {
            talkWith(kindProfessor);
        } else {
            System.out.println("잘못된 번호 입력!");
        }
    }

    // 고른 교수님과 면담 (두 교수님 공통 처리)
    // 순서: 면담 횟수 초과 검사(교수님마다 하루 3번) → 체력 부족 검사 → 면담(호감도 + 증가량) → 체력 감소
    //       → 결과 문구·체력 바 → 클리어(호감도 10 이상, 번아웃보다 우선) → 번아웃
    // 실패(횟수 초과, 체력 부족)하면 안내 문구와 체력 바만 출력하고 상태는 바꾸지 않는다.
    private void talkWith(Professor professor) {
        if (professor.canTalk()) {
            int cost = calcCost(professor);

            if (stamina < cost) {
                System.out.println("너무 지쳤습니다");
                printStaminaBar();
            } else {
                professor.talk();
                stamina -= cost;
                System.out.println(professor.getName() + "님과 면담했습니다. 호감도가 " + professor.getGain() + " 올랐습니다.");
                printStaminaBar();

                if (professor.isFavorFull()) {
                    System.out.println("추천서 획득!");
                    this.finished = true;
                } else {
                    checkBurnout();
                }
            }
        } else {
            System.out.println("오늘 면담 시간이 끝났습니다. 휴식하면 다음 날 다시 면담할 수 있습니다");
            printStaminaBar();
        }
    }

    // 공부해라
    // 순서: 체력 부족 검사 → 지식 +1, 체력 감소 → 결과 문구·체력 바 → 번아웃
    public void study() {
        if (this.stamina < STUDY_COST) {
            System.out.println("너무 지쳤습니다");
            printStaminaBar();
        } else {
            // 지식 +1, 체력 감소 → 결과 문구 → 체력 바 → 번아웃 검사
            knowledge += 1;
            stamina -= STUDY_COST;
            System.out.println("공부했습니다. 지식이 1 올랐습니다.");
            printStaminaBar();
            checkBurnout();
        }

    }

    // 휴식해라
    // 체력 회복(최대 100%), 두 교수님에게 하루 넘겨라(면담 횟수 0, 호감도 유지), 일차 +1, 결과 문구·체력 바, 기한 검사
    // 5일차에 휴식해서 일차가 5를 넘으면 기한 초과로 게임이 끝난다.
    public void rest() {
        stamina = (stamina + REST_AMOUNT > MAX_STAMINA) ? MAX_STAMINA : stamina + REST_AMOUNT;
        strictProfessor.newDay();
        kindProfessor.newDay();
        day++;

        // 일차에 따라 결과 문구를 고른다. 기한 초과 → 마지막 날 → 그 외 순서로 나눈다.
        if (day > MAX_DAY) {
            System.out.println("휴식했습니다. 하루가 지났습니다.");
        } else if (day == MAX_DAY) {
            System.out.println("휴식했습니다. " + day + "일차가 되었습니다. 오늘이 마지막 날입니다. (기한 " + MAX_DAY + "일)");
        } else {
            System.out.println("휴식했습니다. " + day + "일차가 되었습니다. (기한 " + MAX_DAY + "일)");
        }
        printStaminaBar();

        if (day > MAX_DAY) {
            System.out.println("기한이 지났습니다. 추천서를 받지 못했습니다");
            this.finished = true;
        }
    }

    // 상태를 보여줘라
    // 체력 바(한 번만), 지식, 일차, 두 교수님 정보
    public void showStatus() {
        printStaminaBar();
        System.out.println("지식 " + knowledge);
        System.out.println(day + "일차 (기한 " + MAX_DAY + "일)");
        System.out.println(strictProfessor);
        System.out.println(kindProfessor);
    }

    // 규칙을 알려줘라 (게임 시작 때 메뉴가 나오기 전에 한 번만)
    // 현재 체력과 일차는 보여주지 않는다. 교수님마다 하루 3번, 호감도 10 은 Professor 의 숫자와 같아야 한다.
    // 면담·공부·휴식의 차이와 두 교수님의 소모량·증가량도 함께 안내한다. (교수님 값은 getter 로 가져온다.)
    public void showRules() {
        System.out.println("===========연구실 생존기 규칙===========");
        System.out.println("기한은 " + MAX_DAY + "일입니다. 휴식하면 하루가 지납니다.");
        System.out.println("교수님마다 하루에 3번까지 면담할 수 있습니다.");
        System.out.println("교수님의 호감도가 10 이상이 되면 추천서를 받습니다.");
        System.out.println("체력이 0%가 되면 번아웃으로 휴학합니다.");
        System.out.println("면담 : 교수님의 호감도를 올립니다. 체력을 씁니다.");
        System.out.println("  " + strictProfessor.getName() + " : 체력 " + strictProfessor.getCost() + "% 소모, 호감도 +" + strictProfessor.getGain());
        System.out.println("  " + kindProfessor.getName() + " : 체력 " + kindProfessor.getCost() + "% 소모, 호감도 +" + kindProfessor.getGain());
        System.out.println("공부 : 지식을 올립니다. 체력을 " + STUDY_COST + "% 씁니다. 지식이 " + BONUS_KNOWLEDGE + " 이상이면 꼼꼼한 교수와의 면담 체력 소모가 절반이 됩니다.");
        System.out.println("휴식 : 체력을 " + REST_AMOUNT + "% 회복하고 하루가 지납니다.");
        System.out.println("=======================================");
    }

    // 게임이 끝났는지 알려줘라
    public boolean isFinished() {
        return finished;
    }

    // 체력 바 출력. 예) [███████░░░] 70%
    private void printStaminaBar() {
        int filled = stamina / 10;

        System.out.print("[");
        for (int i = 0; i < filled ; i++) {
            System.out.print("█");
        }
        for (int i = 0; i < (10 - filled) ; i++) {
            System.out.print("░");
        }
        System.out.println("] " + stamina + "%");
    }

    // 면담 소모량 계산. 지식이 5 이상이면 절반(소수점 버림, 최소 10%). 지식은 하루가 지나도 유지된다.
    // 온화한 교수(소모량 10%)는 절반이 5% 라서 최소값 10% 가 적용되어 지식 보너스가 없다. (의도한 규칙)
    private int calcCost(Professor professor) {
        if (knowledge >= BONUS_KNOWLEDGE) {
            return Math.max(MIN_COST, professor.getCost() / 2);
        } else {
            return professor.getCost();
        }
    }

    // 체력이 0%이면 번아웃 처리 (면담에서는 클리어가 아닐 때만 호출한다.)
    private void checkBurnout() {
        if (stamina == 0) {
            System.out.println("번아웃으로 휴학합니다");
            this.finished = true;
        }
    }

}
