package com.example.GHand;

import com.example.GHand.document.usuario.Usuario;
import com.example.GHand.dto.usuario.UsuarioDto;
import com.example.GHand.dto.usuario.UsuarioLoginRequestDto;
import com.example.GHand.repository.UsuarioRepository;
import com.example.GHand.service.UsuarioService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable((SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
        ReflectionTestUtils.setField(usuarioService, "objectmapper", objectMapper);
    }


}
