package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapperTestSuite {

    TaskMapper taskMapper = new TaskMapper();

    Task task = new Task(1L, "test name", "test description");
    TaskDto taskDto = new TaskDto(9L, "test dto name", "test dto description");
    List<Task> taskList = new ArrayList<>();
    List<TaskDto> taskDtoList = new ArrayList<>();

    @Test
    public void testMapToTask() {
        //Given

        //When
        Task testedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), testedTask.getId());
        assertEquals(taskDto.getTitle(), testedTask.getTitle());
        assertEquals(taskDto.getContent(), testedTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given

        //When
        TaskDto testedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), testedTaskDto.getId());
        assertEquals(task.getTitle(), testedTaskDto.getTitle());
        assertEquals(task.getContent(), testedTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        taskList.add(task);
        taskDtoList.add(taskDto);

        //When
        List<TaskDto> testedList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(taskList.get(0).getId(), testedList.get(0).getId());
        assertEquals(taskList.get(0).getContent(), testedList.get(0).getContent());
        assertEquals(taskList.get(0).getTitle(), testedList.get(0).getTitle());
    }
}
