package com.gfa.foxbook.foxbook.unitTests;

import com.gfa.foxbook.foxbook.models.nonusermodels.Technology;
import com.gfa.foxbook.foxbook.repositories.TechnologyRepository;
import com.gfa.foxbook.foxbook.services.TechnologyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TechnologyServiceImplTest {

    @Mock
    private TechnologyRepository technologyRepository;
    private TechnologyServiceImpl technologyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        technologyService = new TechnologyServiceImpl(technologyRepository);
    }

    @Test
    public void getAllTechnologies() {
        //arange
        Technology html = new Technology("HTML");
        Technology css = new Technology("CSS");
        Technology javascript = new Technology("JavaScript");
        List<Technology> technologies = List.of(html, css, javascript);

        when(technologyRepository.findAll()).thenReturn(technologies);

        //act
        List<Technology> result = technologyService.getAllTechnologies();

        //assert
        assertEquals(technologies, result);
        assertEquals(3, result.size());
    }
}
