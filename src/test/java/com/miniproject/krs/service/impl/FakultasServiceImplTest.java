package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.FakultasEntity;
import com.miniproject.krs.model.FakultasModel;
import com.miniproject.krs.repository.FakultasRepo;
import com.miniproject.krs.service.FakultasService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FakultasServiceImplTest {
    @Autowired
    @InjectMocks
    private FakultasService service;

    @Mock
    private FakultasRepo repo;

    private static List<FakultasEntity> fakultasEntityList;

    @BeforeEach
    void setUp() {
        fakultasEntityList = Arrays.asList(
                new FakultasEntity("FK", "Fakultas Kedokteran", "Jakarta"),
                new FakultasEntity("FT", "Fakultas Teknik", "Jakarta"),
                new FakultasEntity("FE", "Fakultas Ekonomi", "Jakarta")
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        //method repo.findALl panggil fakultasEntityList
        //mocking
        when(repo.findAll()).thenReturn(fakultasEntityList);

        //test method get
        List<FakultasModel> result = service.getAll();

        //check 1
        assertNotNull(result);

        //check 2
        assertEquals(3, result.size()); // cek apakah isi fakultasEnity mock berisi 3?

        //check 3 data pada index 0
        assertEquals("FK", result.get(0).getCode());
        assertEquals("Fakultas Kedokteran", result.get(0).getName());
    }

    @Test
    void getById() {
        //skenario 1
        FakultasModel result = service.getById("");//null

        assertNotNull(result);
        assertNull(result.getCode());

        //skenario 2
        Optional<FakultasEntity> entity = Optional.of(fakultasEntityList.get(0));
        //mocking
        when(repo.findById(any(String.class))).thenReturn(entity);

        result = service.getById("FK");
        assertNotNull(result);
        assertEquals("FK", result.getCode());
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