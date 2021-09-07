package guru.springframework.sfgpetclinic.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import guru.springframework.sfgpetclinic.SfgPetClinicApplication;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = SfgPetClinicApplication.class)
@AutoConfigureMockMvc
public class OwnerControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    public static Stream<Arguments> providePathList() {
        return Stream.of(
            Arguments.of("/owners"),
            Arguments.of("/owners/"),
            Arguments.of("/owners/index"),
            Arguments.of("/owners/index.html")
        );
    }

    @ParameterizedTest
    @MethodSource("providePathList")
    public void givenOwners_whenGetOwners_thenStatus200(String path)
    throws Exception {
        mvc.perform(get(path)
                        .contentType(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(content()
                           .contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

}