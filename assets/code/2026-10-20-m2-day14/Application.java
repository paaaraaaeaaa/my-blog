package com.eulji;

import com.eulji.book.controller.BookController;
import com.eulji.book.model.service.BookService;
import com.eulji.book.view.input.BookInputView;
import com.eulji.book.view.output.BookOutputView;
import com.eulji.category.library.controller.CategoryController;
import com.eulji.category.library.model.service.CategoryService;
import com.eulji.category.library.view.input.CategoryInputView;
import com.eulji.category.library.view.output.CategoryOutputView;
import com.eulji.member.controller.MemberController;
import com.eulji.member.model.service.MemberService;
import com.eulji.member.view.input.MemberInputView;
import com.eulji.member.view.output.MemberOutputView;
import com.eulji.rental.rental.library.controller.RentalController;
import com.eulji.stats.controller.StatsController;
import com.eulji.stats.model.service.StatsService;
import com.eulji.stats.view.input.StatsInputView;
import com.eulji.stats.view.output.StatsOutputView;

public class Application {

    public static void main(String[] args) {

        // 객체 조립
        // B 도서
        BookService bookService = new BookService();
        BookController bookController = new BookController(bookService);
        BookOutputView bookOutputView = new BookOutputView();
        BookInputView bookInputView = new BookInputView(bookController, bookOutputView);

        // C 대여
        RentalController rentalController = new RentalController();

        // D 카테고리
        CategoryService categoryService = new CategoryService();
        CategoryController categoryController = new CategoryController(categoryService);
        CategoryOutputView categoryOutputView = new CategoryOutputView();
        CategoryInputView categoryInputView = new CategoryInputView(categoryController, categoryOutputView);

        // D 통계
        StatsService statsService = new StatsService();
        StatsController statsController = new StatsController(statsService);
        StatsOutputView statsOutputView = new StatsOutputView();
        StatsInputView statsInputView = new StatsInputView(statsController, statsOutputView);

        // A 회원 (메인 화면)
        MemberService memberService = new MemberService();
        MemberController memberController = new MemberController(memberService);
        MemberOutputView memberOutputView = new MemberOutputView();
        MemberInputView memberInputView = new MemberInputView(memberController, memberOutputView,
                bookInputView, categoryInputView, rentalController, statsInputView);

        memberInputView.displayMainView();
    }
}
