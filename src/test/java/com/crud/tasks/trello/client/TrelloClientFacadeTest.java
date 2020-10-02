package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyBoard() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();

        trelloLists.add(new TrelloListDto("1","s",false));
        trelloBoards.add(new TrelloBoardDto("1","s",trelloLists));
        mappedTrelloLists.add(new TrelloList("1","s",false));
        mappedTrelloBoards.add(new TrelloBoard("1","s",mappedTrelloLists));

        when(trelloService.fetchTrelloBoard()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();

        trelloLists.add(new TrelloListDto("1","s",false));
        trelloBoards.add(new TrelloBoardDto("1","s",trelloLists));
        mappedTrelloLists.add(new TrelloList("1","s",false));
        mappedTrelloBoards.add(new TrelloBoard("1","s",mappedTrelloLists));

        when(trelloService.fetchTrelloBoard()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtoList);
        assertEquals(1,trelloBoardDtoList.size());

        trelloBoardDtoList.forEach
                (trelloBoardDto -> {
                    assertEquals("1",trelloBoardDto.getId());
                    assertEquals("s",trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach
                    (trelloListDto -> {
                assertEquals("1",trelloListDto.getId());
                assertEquals("s",trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldCreatedCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task dto",
                "Test Description dto",
                "top dto",
                "test_id dto"
        );
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "Oko",
                "Oko",
                "www"
        );

        when(trelloService.createTrelloCard(any())).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto newCard = trelloFacade.createCard(trelloCardDto);

        //Then
        assertEquals("Oko", newCard.getId());
        assertEquals("Oko", newCard.getName());
        assertEquals("www", newCard.getShortUrl());
    }
}
