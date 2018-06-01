package fr.symbolit.todolist.services;

import fr.symbolit.todolist.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodolistService {

    private List<Todo> todoList;

    public List<Todo> getTodoList() {
        if (todoList == null) {
            this.setTodoList(new ArrayList<>());
        }
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    // Methods


    public List<Todo> getAllTodos() {
        return this.getTodoList();
    }

    public Todo getTodo(int todoIndex) {
        if (givenTodoIsInTheList(todoIndex)) {
            return this.getTodoList().get(todoIndex);
        }
        return null;
    }

    public void addTodo(Todo todo) {
        this.getTodoList().add(todo);
    }

    public void deleteTodo(int todoIndex) {
        if (givenTodoIsInTheList(todoIndex)) {
            this.getTodoList().remove(todoIndex);
        }
    }

    public void editTodo(int todoIndex, Todo newTodo) {
        if (givenTodoIsInTheList(todoIndex)) {
            this.getTodoList().set(todoIndex, newTodo);
        }
    }

    private boolean givenTodoIsInTheList(int todoIndex) {
        return this.getTodoList().size() > todoIndex;
    }
}
