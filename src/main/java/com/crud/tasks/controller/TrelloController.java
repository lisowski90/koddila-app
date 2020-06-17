package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() throws TaskNotFoundException {
        List<Optional<List<TrelloBoardDto>>> trelloBoards1 = trelloClient.getTrelloBoards();

        List<List<TrelloBoardDto>> trelloBoards = trelloBoards1.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        trelloBoards.stream()
                .flatMap(trelloBoardDtos -> trelloBoardDtos.stream())
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .filter(trelloBoardDto -> trelloBoardDto.getId() != null & trelloBoardDto.getName() != null)
                .map(trelloBoardDto -> trelloBoardDto.getName() + " " + trelloBoardDto.getId())
                .forEach(System.out::println);
       }
}


