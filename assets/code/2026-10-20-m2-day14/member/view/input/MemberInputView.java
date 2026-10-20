package com.eulji.member.view.input;

import com.eulji.book.view.input.BookInputView;
import com.eulji.category.library.view.input.CategoryInputView;
import com.eulji.global.common.InputUtil;
import com.eulji.global.common.Session;
import com.eulji.member.controller.MemberController;
import com.eulji.member.model.dto.Member;
import com.eulji.member.view.output.MemberOutputView;
import com.eulji.rental.rental.library.controller.RentalController;
import com.eulji.stats.view.input.StatsInputView;

public class MemberInputView {

    private final MemberController controller;
    private final MemberOutputView outputView;

    // 다른 담당자 화면 (학생·관리자 메뉴에서 호출)
    private final BookInputView bookInputView;
    private final CategoryInputView categoryInputView;
    private final RentalController rentalController;
    private final StatsInputView statsInputView;

    public MemberInputView(MemberController controller, MemberOutputView outputView,
                           BookInputView bookInputView, CategoryInputView categoryInputView,
                           RentalController rentalController, StatsInputView statsInputView) {
        this.controller = controller;
        this.outputView = outputView;
        this.bookInputView = bookInputView;
        this.categoryInputView = categoryInputView;
        this.rentalController = rentalController;
        this.statsInputView = statsInputView;
    }

    /* 프로그램 메인 화면: 로그인 전 → 학생 메뉴 / 관리자 메뉴 */
    public void displayMainView() {
        boolean running = true;
        while (running) {
            if (!Session.isLoggedIn()) {
                running = displayStartMenu();
            } else if (Session.isAdmin()) {
                displayAdminMenu();
            } else {
                displayStudentMenu();
            }
        }
        outputView.printExit();
    }

    /* 로그인 전 메뉴. 종료를 고르면 false */
    private boolean displayStartMenu() {
        outputView.printStartMenu();
        switch (inputMenu()) {
            case 1 -> login();
            case 2 -> signUp();
            case 3 -> {
                return false;
            }
            case -1 -> { }
            default -> outputView.printInvalidMenu();
        }
        return true;
    }

    private void displayStudentMenu() {
        outputView.printStudentMenu();
        switch (inputMenu()) {
            case 1 -> bookInputView.displayBookMenu();
            case 2 -> categoryInputView.displayStudentMenu();
            case 3 -> rentalController.run();
            case 4 -> displayMyInfoMenu();
            case 5 -> logout();
            case -1 -> { }
            default -> outputView.printInvalidMenu();
        }
    }

    private void displayAdminMenu() {
        outputView.printAdminMenu();
        switch (inputMenu()) {
            case 1 -> bookInputView.displayBookAdminMenu();
            case 2 -> categoryInputView.displayAdminMenu();
            case 4 -> rentalController.showOverdueList();
            case 5 -> statsInputView.displayMenu();
            case 3 -> displayMemberAdminMenu();
            case 6 -> logout();
            case -1 -> { }
            default -> outputView.printInvalidMenu();
        }
    }

    /* F-02 로그인 */
    private void login() {
        String loginId = inputString("아이디(학번): ");
        String password = inputString("비밀번호: ");

        try {
            Member member = controller.login(loginId, password);
            outputView.printLoginSuccess(member);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    /* F-02 로그아웃 */
    private void logout() {
        controller.logout();
        outputView.printLogout();
    }

    /* F-01 회원 가입 */
    private void signUp() {
        outputView.printDepartments(controller.getDepartments());

        Member member = new Member();
        member.setLoginId(inputString("아이디(학번): "));
        member.setPassword(inputString("비밀번호: "));
        member.setName(inputString("이름: "));
        member.setPhone(inputString("전화번호: "));
        member.setDeptId(inputDeptId());

        try {
            controller.signUp(member);
            outputView.printSignUpSuccess();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    /* 학생 메뉴 > 내 정보 (F-03, F-04). 탈퇴하면 로그아웃 상태로 돌아간다 */
    private void displayMyInfoMenu() {
        while (Session.isLoggedIn()) {
            outputView.printMyInfoMenu();
            switch (inputMenu()) {
                case 1 -> showAndEditMyInfo();
                case 2 -> withdraw();
                case 0 -> {
                    return;
                }
                case -1 -> { }
                default -> outputView.printInvalidMenu();
            }
        }
    }

    /* F-03 내 정보 조회·수정 */
    private void showAndEditMyInfo() {
        outputView.printMyInfo(controller.getMyInfo());
        outputView.printEditMenu();

        try {
            switch (inputMenu()) {
                case 1 -> {
                    String current = inputString("현재 비밀번호: ");
                    String next = inputString("새 비밀번호: ");
                    controller.changePassword(current, next);
                    outputView.printEditSuccess();
                }
                case 2 -> {
                    controller.changePhone(inputString("새 전화번호: "));
                    outputView.printEditSuccess();
                }
                case 0, -1 -> { }
                default -> outputView.printInvalidMenu();
            }
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    /* F-04 회원 탈퇴 */
    private void withdraw() {
        String password = inputString("비밀번호: ");
        if (!inputConfirm("정말 탈퇴하시겠습니까?")) {
            outputView.printWithdrawCancel();
            return;
        }

        try {
            controller.withdraw(password);
            outputView.printWithdrawSuccess();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    /* 관리자 메뉴 > 회원 관리 (F-05, F-06) */
    private void displayMemberAdminMenu() {
        while (true) {
            outputView.printMemberAdminMenu();
            switch (inputMenu()) {
                case 1 -> searchMembers();
                case 2 -> displayDepartmentMenu();
                case 0 -> {
                    return;
                }
                case -1 -> { }
                default -> outputView.printInvalidMenu();
            }
        }
    }

    /* F-05 회원 목록·검색 */
    private void searchMembers() {
        String keyword = inputString("검색어(이름 또는 학과명, 엔터면 전체): ");
        outputView.printMemberList(controller.searchStudents(keyword));
    }

    /* F-06 학과 관리 */
    private void displayDepartmentMenu() {
        while (true) {
            outputView.printDepartmentMenu();
            switch (inputMenu()) {
                case 1 -> outputView.printDepartments(controller.getDepartments());
                case 2 -> registerDepartment();
                case 0 -> {
                    return;
                }
                case -1 -> { }
                default -> outputView.printInvalidMenu();
            }
        }
    }

    private void registerDepartment() {
        try {
            controller.registerDepartment(inputString("학과명: "));
            outputView.printDepartmentRegistered();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    /* 메뉴 번호 입력. 숫자가 아니면 안내 후 -1 */
    private int inputMenu() {
        System.out.print("메뉴 선택: ");
        try {
            return Integer.parseInt(InputUtil.SC.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요.");
            return -1;
        }
    }

    /* 학과 번호는 숫자가 들어올 때까지 다시 입력받는다 */
    private int inputDeptId() {
        while (true) {
            System.out.print("학과 번호: ");
            try {
                return Integer.parseInt(InputUtil.SC.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            }
        }
    }

    /* Y면 true, 그 외는 false */
    private boolean inputConfirm(String message) {
        System.out.print(message + " (Y/N): ");
        return InputUtil.SC.nextLine().trim().equalsIgnoreCase("Y");
    }

    private String inputString(String prompt) {
        System.out.print(prompt);
        return InputUtil.SC.nextLine().trim();
    }
}
