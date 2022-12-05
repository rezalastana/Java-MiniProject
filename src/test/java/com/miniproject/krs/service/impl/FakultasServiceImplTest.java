package com.miniproject.krs.service.impl;

import com.miniproject.krs.entity.FakultasEntity;
import com.miniproject.krs.model.FakultasModel;
import com.miniproject.krs.repository.FakultasRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class FakultasServiceImplTest {
    @Autowired
    @InjectMocks
    private FakultasServiceImpl service;

    @Mock
    private FakultasRepo repo;

    private static List<FakultasEntity> fakultasEntityList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fakultasEntityList = Arrays.asList(
                new FakultasEntity("FK", "Fakultas Kedokteran", "Yogyakarta"),
                new FakultasEntity("FT", "Fakultas Teknik", "Yogyakarta"),
                new FakultasEntity("FE", "Fakultas Ekonomi", "Yogyakarta")
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

        //check jika data salah
        assertNotEquals("FK", result.get(2).getCode());
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
    void save_Check_Code() {
        FakultasModel request = new FakultasModel("FE", "Fakultas Ekonomi", "Yogyakarta");

        //check code
        when(repo.findByCode(request.getCode())).thenReturn(Arrays.asList(fakultasEntityList.get(2)));

        Optional<FakultasModel> result = service.save(request);
        assertNotNull(result);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void save_Check_Name(){
        FakultasModel request = new FakultasModel("FE", "Fakultas Ekonomi", "Yogyakarta");

        //check name
        when(repo.findByCode("FE")).thenReturn(Collections.emptyList());
        when(repo.findByName("Fakultas Ekonomi")).thenReturn(Collections.emptyList());

        //mocking
        when(repo.save(any(FakultasEntity.class))).thenReturn(fakultasEntityList.get(2));

        //skenario
        Optional<FakultasModel> result = service.save(request);
        assertNotNull(result);
        assertEquals("FE", result.get().getCode());
        assertEquals("Fakultas Ekonomi", result.get().getName());
        assertEquals("Yogyakarta", result.get().getAlamat());
    }

    @Test
    void update() {
        //Data
        FakultasModel request = new FakultasModel("FK", "Fakultas Kedokteran", "Bantul");//data diubah
        Optional<FakultasEntity> entity = Optional.of(fakultasEntityList.get(0)); //diambil pada data index 0

        //Skenario 1
        Optional<FakultasModel> result1 = service.update("", null);//percobaan data kosong
        assertNotNull(result1);
        assertEquals(Optional.empty(), result1); //cek Equals

        //Mocking
        when(repo.findById(any(String.class))).thenReturn(entity);
        when(repo.save(any(FakultasEntity.class))).thenReturn(fakultasEntityList.get(0));

        //Skenario 2
        Optional<FakultasModel> result2 = service.update("123", request);
        assertNotNull(result2);
        assertNotEquals(Optional.empty(), result2);
        assertTrue(result2.isPresent());

        assertEquals("FK", result2.get().getCode());
        assertEquals("Fakultas Kedokteran", result2.get().getName());
        assertEquals("Bantul", result2.get().getAlamat());


    }

    @Test
    void delete() {
        Optional<FakultasEntity> entity = Optional.of(fakultasEntityList.get(0));

        //Skenario 1
        Optional<FakultasModel> result1 = service.delete("");//delete data kosong
        assertNotNull(result1);
        assertEquals(Optional.empty(), result1);

        //Mocking
        when(repo.findById(any(String.class))).thenReturn(entity);

        //Skenario 2
        Optional<FakultasModel> result2 = service.delete("123");
        assertNotNull(result2);
        assertNotEquals(Optional.empty(), result2);
        assertTrue(result2.isPresent());
    }
}