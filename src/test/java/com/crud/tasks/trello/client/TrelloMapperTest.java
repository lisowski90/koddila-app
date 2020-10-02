package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    TrelloCard trelloCard = new TrelloCard("test", "test description", "test top", "test id");
    TrelloCardDto trelloCardDto = new TrelloCardDto("test dto", "test dto description", "test dto top", "test dto id");
    List<TrelloList> trelloLists = new ArrayList<>();
    List<TrelloListDto> trelloListDtoList = new ArrayList<>();
    TrelloList trelloList = new TrelloList("test list id", "test list name", false);
    TrelloListDto trelloListDto = new TrelloListDto("test dto list id", "test dto list name", true);
    List<TrelloBoard> trelloBoards = new ArrayList<>();
    List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
    TrelloBoard trelloBoard = new TrelloBoard("board test id", "board test name", trelloLists);
    TrelloBoardDto trelloBoardDto = new TrelloBoardDto("board dto test id", "board dto test name", trelloListDtoList);

    @Test
    public void testMapToBoards() {
        //Given
        trelloBoards.add(trelloBoard);
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> testedBoard = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(trelloBoardDto.getId(), testedBoard.get(0).getId());
        System.out.println("Compared id:" + trelloBoardDto.getId() + " and " + testedBoard.get(0).getId());
        assertEquals(trelloBoardDto.getName(), testedBoard.get(0).getName());

    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        trelloBoards.add(trelloBoard);
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoardDto> testedBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloBoard.getId(), testedBoardDto.get(0).getId());
        System.out.println("Compared id:" + trelloBoard.getId() + " and " + testedBoardDto.get(0).getId());
        assertEquals(trelloBoard.getName(), testedBoardDto.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        trelloLists.add(trelloList);
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> testedList = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(trelloListDto.getId(), testedList.get(0).getId());
        assertEquals(trelloListDto.getName(), testedList.get(0).getName());
        assertEquals(trelloListDto.isClosed(), testedList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        trelloLists.add(trelloList);
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloListDto> testedListDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloList.getId(), testedListDto.get(0).getId());
        assertEquals(trelloList.getName(), testedListDto.get(0).getName());
        assertEquals(trelloList.isClosed(), testedListDto.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {

        //Given

        //When
        TrelloCard testedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getDescription(), testedCard.getDescription());
        assertEquals(trelloCardDto.getIdList(), testedCard.getIdList());
        assertEquals(trelloCardDto.getName(), testedCard.getName());
        assertEquals(trelloCardDto.getPos(), testedCard.getPos());
    }

    @Test
    public void testMapToCardDto() {
        //Given

        //When
        TrelloCardDto testedCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getDescription(), testedCardDto.getDescription());
        assertEquals(trelloCard.getIdList(), testedCardDto.getIdList());
        assertEquals(trelloCard.getName(), testedCardDto.getName());
        assertEquals(trelloCard.getPos(), testedCardDto.getPos());
    }
}
