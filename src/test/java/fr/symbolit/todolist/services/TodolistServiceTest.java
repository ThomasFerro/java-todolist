package fr.symbolit.todolist.services;

import fr.symbolit.todolist.models.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodolistServiceTest {

    private TodolistService todolistService;
    private static final String FIRST_TODO_TEXT = "FIRST_TODO_TEXT";
    private static final String EDITED_FIRST_TODO_TEXT = "EDITED_FIRST_TODO_TEXT";
    private static final String SECOND_TODO_TEXT = "SECOND_TODO_TEXT";
    private static final String THIRD_TODO_TEXT = "THIRD_TODO_TEXT";
    @BeforeEach
    public void init() {
        todolistService = new TodolistService();
    }

    @Test
    public void shouldInitiateATodoServiceWithNoElement() {
        // Then
        assertEquals(todolistService.getTodoList().size(), 0);
    }

    private Todo createTodo(String text) {
        return new Todo() {{
            setText(text);
        }};
    }

    @Test
    public void shouldGetNoTodoWhenTheListIsEmpty() {
        // Then
        assertEquals(todolistService.getAllTodos(), new ArrayList<Todo>());
    }

    @Test
    public void shouldGetAllTodosWhenTheListIsFilled() {
        // When
        Todo firstTodo = createTodo(FIRST_TODO_TEXT);
        ArrayList<Todo> todoList = new ArrayList<>();
        todoList.add(firstTodo);
        todolistService.addTodo(firstTodo);

        // Then
        assertEquals(todolistService.getAllTodos(), todoList);
    }

    @Test
    public void shouldGetNoTodoWhenSearchingForAnIncorrectElement() {
        // Then
        assertEquals(todolistService.getTodo(0), null);
    }

    @Test
    public void shouldGetATodoWhenSearchingForACorrectElement() {
        // When
        Todo firstTodo = createTodo(FIRST_TODO_TEXT);
        todolistService.addTodo(firstTodo);

        // Then
        assertEquals(todolistService.getTodo(0), firstTodo);
    }

    @Test
    public void shouldAddFirstTodo() {
        // Given
        Todo todo = createTodo(FIRST_TODO_TEXT);

        // When
        todolistService.addTodo(todo);

        // Then
        assertEquals(todolistService.getTodoList().size(), 1);
        assertEquals(todolistService.getTodoList().get(0), todo);
    }

    @Test
    public void shouldAddNextTodo() {
        // Given
        Todo secondTodo = createTodo(SECOND_TODO_TEXT);

        // When
        todolistService.addTodo(createTodo(FIRST_TODO_TEXT));
        todolistService.addTodo(secondTodo);

        // Then
        assertEquals(todolistService.getTodoList().size(), 2);
        assertEquals(todolistService.getTodoList().get(1), secondTodo);
    }

    @Test
    public void shouldntDeleteWhenProvidingIncorrectIndex() {
        // Given
        todolistService.addTodo(createTodo(FIRST_TODO_TEXT));
        todolistService.addTodo(createTodo(SECOND_TODO_TEXT));
        todolistService.addTodo(createTodo(THIRD_TODO_TEXT));

        // When
        todolistService.deleteTodo(4);

        // Then
        assertEquals(todolistService.getTodoList().size(), 3);
    }

    @Test
    public void shouldDeleteTodo() {
        // Given
        todolistService.addTodo(createTodo(FIRST_TODO_TEXT));

        // When
        todolistService.deleteTodo(0);

        // Then
        assertEquals(todolistService.getTodoList().size(), 0);
    }

    @Test
    public void shouldEditTodo() {
        // Given
        Todo unmodifiedFirstTodo = createTodo(FIRST_TODO_TEXT);
        Todo modifiedFirstTodo = createTodo(EDITED_FIRST_TODO_TEXT);
        todolistService.addTodo(unmodifiedFirstTodo);

        // When
        todolistService.editTodo(0, modifiedFirstTodo);

        // Then
        assertEquals(todolistService.getTodoList().get(0).getText(), EDITED_FIRST_TODO_TEXT);
    }

    @Test
    public void shouldntEditTodoWhenProvidingIncorrectIndex() {
        // Given
        Todo unmodifiedFirstTodo = createTodo(FIRST_TODO_TEXT);
        todolistService.addTodo(unmodifiedFirstTodo);

        // When
        todolistService.editTodo(1, createTodo(EDITED_FIRST_TODO_TEXT));

        // Then
        assertEquals(todolistService.getTodoList().get(0), unmodifiedFirstTodo);
    }

}