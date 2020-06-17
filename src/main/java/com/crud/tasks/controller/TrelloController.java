package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() throws TaskNotFoundException {
        List<TrelloBoardDto> trelloBoards1 = trelloClient.getTrelloBoards();

        trelloBoards1.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .filter(trelloBoardDto -> trelloBoardDto.getId() != null & trelloBoardDto.getName() != null)
                .map(trelloBoardDto -> trelloBoardDto.getName() + " " + trelloBoardDto.getId())
                .forEach(System.out::println);
       }
}

