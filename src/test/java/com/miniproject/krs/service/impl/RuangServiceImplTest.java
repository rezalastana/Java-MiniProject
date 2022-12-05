package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.RuangEntity;
import com.miniproject.krs.model.RuangModel;
import com.miniproject.krs.repository.RuangRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RuangServiceImplTest {

    @Autowired
    @InjectMocks
    private RuangServiceImpl service;

    @Mock
    private RuangRepo repo;

    private static List<RuangEntity> ruangEntityList;
    @BeforeEach
    void setUp() {
        ruangEntityList = Arrays.asList(
                new RuangEntity("R001", "Ruang Patt1", "2"),
                new RuangEntity("R002", "Ruang Patt2", "3"),
                new RuangEntity("R003", "Ruang Patt3", "1")
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        //mocking
        when(repo.findAll()).thenReturn(ruangEntityList);

        //check method get
        List<RuangModel> result = service.getAll();
        //check 1
        assertNotNull(result);
        //check 2
        assertEquals(3, result.size());
        //check 3 data index 0
        assertEquals("R001", result.get(0).getCode());
        assertEquals("Ruang Patt1",result.get(0).getName());
        assertEquals("2", result.get(0).getLantaiKe());

        //check 4 data salah
        assertNotEquals("ROO2", result.get(2).getCode());
    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}