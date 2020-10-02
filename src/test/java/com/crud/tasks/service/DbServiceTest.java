package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task = new Task(1L, "test task", "test description");
        Task task2 = new Task(2L, "test2 task", "test2 description");
        List<Task> taskList = new ArrayList<>();

        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        taskList.add(task);
        taskList.add(task2);
        List<Task> testedList = dbService.getAllTask();

        //Then
        assertEquals(2, testedList.size());
        assertTrue(testedList.contains(task));
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "test task", "test description");

        when(taskRepository.save(task)).thenReturn(task);

        //When
        Task testedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), testedTask.getId());
        assertEquals(task.getTitle(), testedTask.getTitle());
        assertEquals(task.getContent(), testedTask.getContent());
    }


    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "test task", "test description");

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        //When
        Optional<Task> testedTask = dbService.getTask(1L);

        //Then
        assertEquals(task.getId(), testedTask.get().getId());
        assertEquals(task.getTitle(), testedTask.get().getTitle());
        assertEquals(task.getContent(), testedTask.get().getContent());

    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(100L, "test100 task", "test100 description");

        //When
        dbService.deleteTask(task.getId());

        //Then
        verify(taskRepository, times(1)).deleteById(100L);
    }}
