package ru.sleed.mvc.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import lombok.ToString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.sleed.mvc.entity.User;
import ru.sleed.mvc.model.dto.CreateUserDto;
import ru.sleed.mvc.model.dto.UserDto;
import ru.sleed.mvc.repository.UserRepository;
import ru.sleed.mvc.service.IUserService;
import ru.sleed.mvc.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}"))
                .build();
    }


    @Test
    public void getUserByID_whenUserExists_thenReturnUser() throws Exception {

        UserDto expectedUserDto = new UserDto(4L, "anton",
                LocalDate.of(2002, Month.MARCH, 02));

        MvcResult result = mockMvc.perform(RestDocumentationRequestBuilders.get("/user/{id}",4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(document("getUserByID",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )))
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        UserDto actualUserDto = objectMapper.readValue(contentAsString, UserDto.class);

        assertEquals(expectedUserDto, actualUserDto);

    }

    @Test
    public void getUsers_whenUsersExists_thenReturnUsers() throws Exception{
        List<UserDto> expectedUserDtoList = new ArrayList<>();
        expectedUserDtoList.add(new UserDto(1L, "test", LocalDate.of(2001, Month.JANUARY, 01)));
        expectedUserDtoList.add(new UserDto(2L, "sleed", LocalDate.of(2017, Month.JULY, 05)));
        expectedUserDtoList.add(new UserDto(3L, "sleed", LocalDate.of(2030, Month.FEBRUARY, 11)));
        expectedUserDtoList.add(new UserDto(4L, "anton", LocalDate.of(2002, Month.MARCH, 02)));

        MvcResult result = mockMvc.perform(RestDocumentationRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(document("getUserList"))
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        List<UserDto> actualUserDtoList = objectMapper.readValue(contentAsString,
                new TypeReference<List<UserDto>>(){});

        assertEquals(expectedUserDtoList, actualUserDtoList);
    }

    @Test
    public void createUser_whenUser_thenCreate() throws Exception{
        UserDto expectedCreatedUserDto = new UserDto(1L,
            "posted", LocalDate.of(2011, Month.NOVEMBER, 11));

        MvcResult posted = mockMvc.perform(RestDocumentationRequestBuilders.post("/user")
                        .content(objectMapper.writeValueAsString(
                                new UserDto(1L, "posted", LocalDate.of(2011, Month.NOVEMBER, 11))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("create-user"))
                .andReturn();

        String contentAsString = posted.getResponse().getContentAsString();

        UserDto actualCreatedUserDto = objectMapper.readValue(contentAsString, UserDto.class);

        assertEquals(actualCreatedUserDto, expectedCreatedUserDto);
    }

    @Test
    public void updateUser_whenUser_thenUpdate() throws Exception {
        UserDto expectedUpdatedUserDto = new UserDto(
                4L, "updated", LocalDate.of(2012, Month.DECEMBER, 12));

        MvcResult updated = mockMvc.perform(RestDocumentationRequestBuilders
                .put("/user/{id}", 4)
                        .content(objectMapper.writeValueAsString(
                                new UserDto(4L, "updated", LocalDate.of(2012, Month.DECEMBER, 12))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("update-user",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )))
                .andReturn();

        String contentAsString = updated.getResponse().getContentAsString();

        UserDto actualUpdatedUserDto = objectMapper.readValue(contentAsString, UserDto.class);

        assertEquals(expectedUpdatedUserDto,actualUpdatedUserDto);
    }

    @Test
    public void patchUser_whenUser_thenPatch() throws Exception {
        UserDto expectedPatchedUserDto = new UserDto(
                2L, "sleed", LocalDate.of(2012, Month.DECEMBER, 12));

        MvcResult updated = mockMvc.perform(RestDocumentationRequestBuilders
                        .patch("/user/{id}", 2)
                        .content(objectMapper.writeValueAsString(
                                new UserDto(2L, null, LocalDate.of(2012, Month.DECEMBER, 12))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("patch-user",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )))
                .andReturn();

        String contentAsString = updated.getResponse().getContentAsString();

        UserDto actualPatchedUserDto = objectMapper.readValue(contentAsString, UserDto.class);

        assertEquals(expectedPatchedUserDto,actualPatchedUserDto);
    }

    @Test
    public void deleteUser_whenUser_thanDelete() throws Exception
    {
        ResultActions actualResult = mockMvc.perform(RestDocumentationRequestBuilders.delete("/user/{id}", 1))
                .andExpect(status().isOk())
                .andDo(document("crud-delete-example",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )));

        Optional<User> userById = repository.findUserById(1L);

        assertTrue(userById.isEmpty());
    }

    @Test
    public void findUsersBySurname_whenUsers_thanFind() throws Exception {
        List<UserDto> expectedUserDtoList = new ArrayList<>();
        expectedUserDtoList.add(new UserDto(2L, "sleed", LocalDate.of(2017, Month.JULY, 05)));
        expectedUserDtoList.add(new UserDto(3L, "sleed", LocalDate.of(2030, Month.FEBRUARY, 11)));

        MvcResult actualList = mockMvc.perform(get("/user").param("surname","sleed").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();


        String contentAsString = actualList.getResponse().getContentAsString();

        List<UserDto> actualUserDtoListBySurname = objectMapper.readValue(contentAsString,
                new TypeReference<List<UserDto>>(){});


        assertEquals(expectedUserDtoList,actualUserDtoListBySurname);
    }


}



