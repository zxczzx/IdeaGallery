package migatotech.ideagallery.category;

import migatotech.ideagallery.jwt.JwtInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtInterceptor jwtInterceptor;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getAllCategories() throws Exception {
        var categories = List.of(new Category("name1"), new Category("name2"));
        var categoriesString = "[{\"name\":\"name1\"},{\"name\":\"name2\"}]";

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/category/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(categoriesString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(categoryService).getAllCategories();
    }
}