package ru.netology.javacore;

import org.junit.jupiter.api.*;

public class TodosTests {

    Todos todos = new Todos();

    @BeforeAll
    public static void beforeAllMethod() {
        System.out.println("Запуск тестирования");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Старт теста");
        todos = new Todos();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Тест выполнен");
    }

    @AfterAll
    public static void afterAllMethod() {
        System.out.println("Тестирование завершено");
    }

    @Test
    public void testAddTask() {
        todos.addTask("Java");
        Assertions.assertTrue(todos.tasks.contains("Java"));
    }

    @Test
    public void testRemoveTask() {
        todos.addTask("Java");
        todos.addTask("Test");
        todos.removeTask("Test");
        Assertions.assertFalse(todos.tasks.contains("Test"));
    }

    @Test
    public void testGetAllTasks() {
        todos.addTask("Test");
        todos.addTask("Java");
        Assertions.assertEquals("Java Test", todos.getAllTasks());
    }
}
