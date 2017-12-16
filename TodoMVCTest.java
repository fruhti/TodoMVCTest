package com.github;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;

import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class TodoMVCTest {

    @Test //start test
    public void testTask() {
        open("http://todomvc4tasj.herokuapp.com/"); //open page in browser
        $("#new-todo").setValue("Task 1").pressEnter(); //create Task 1
        $("#new-todo").setValue("Task 2").pressEnter(); //create Task 2
        $("#new-todo").setValue("Task 3").pressEnter(); //create Task 3
        $("#new-todo").setValue("Task 4").pressEnter(); //create Task 4
        $$("#todo-list li").shouldHave(exactTexts("Task 1","Task 2", "Task 3","Task 4")); //check: tasks 1..4 created?

        $$("#todo-list li").find(exactText("Task 2")).hover().find(".destroy").click();//delete task 2
        $$("#todo-list li").shouldHave(exactTexts("Task 1", "Task 3", "Task 4")); // check: task 2 deleted?

        $$("#todo-list li").find(exactText("Task 4")).find(".toggle").click();// mark task 4 completed
        $$("#todo-list li").find(exactText("Task 4")).shouldHave(cssClass("completed"));// check: task 4 marked?

        $("#clear-completed").click();// clear completed tasks
        $$("#todo-list li").shouldHave(exactTexts("Task 1", "Task 3")); // check: task 4 removed as completed?
        $("#clear-completed").shouldBe(hidden);// check: the button invisible?

        $("#toggle-all").click();// mark all as completed
        $("#toggle-all").shouldBe(checked);// check: mark all marked?
        $$("#todo-list li").find(exactText("Task 1")).shouldHave(cssClass("completed"));// check: task 1 marked?
        $$("#todo-list li").find(exactText("Task 3")).find(".toggle").shouldBe(checked);// check: task 3 marked?

        $("#clear-completed").click();// clear completed tasks
        $$("#todo-list li").shouldBe(empty); //check: all tasks removed?
        $("#clear-completed").shouldBe(hidden);// check: the button invisible?
    }
}
