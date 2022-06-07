package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService service;

    @Mock
    Map<String, Object> model;

    List<Vet> vetList = new ArrayList<>();

    @InjectMocks
    VetController controller;

    @BeforeEach
    void setUp(){
        //when
        Vet vet = new Vet();

        vetList.add(vet);

        given(service.findVets()).willReturn(vetList);
    }

    @Test
    void showVetList_returnView() {

        //given call controller
        String view = controller.showVetList(model);
        //then
        then(service).should().findVets();
        verify(model, times(1)).put(anyString(), any(Vets.class));
        assertEquals("vets/vetList", view);
        verifyNoMoreInteractions(model);

    }

    @Test
    void showResourcesVetList_returnVets() {
        //given call controller
        Vets vets = controller.showResourcesVetList();
        //then
        then(service).should().findVets();
        assertThat(vets.getVetList()).hasSize(1);
    }
}