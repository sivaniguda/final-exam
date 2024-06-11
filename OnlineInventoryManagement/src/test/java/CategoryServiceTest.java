import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllCategories() {
        Category category1 = new Category(1L, "Electronics", null);
        Category category2 = new Category(2L, "Books", null);
        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> result = categoryService.getAllCategories();
        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
    }
    @Test
    public void testGetCategoryById() {
        Category category = new Category(1L, "Electronics", null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Category result = categoryService.getCategoryById(1L);
        assertEquals("Electronics", result.getName());
        verify(categoryRepository, times(1)).findById(1L);
    }
    @Test
    public void testGetCategoryByIdNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        Category result = categoryService.getCategoryById(1L);
        assertNull(result);
        verify(categoryRepository, times(1)).findById(1L);
    }
    @Test
    public void testSaveCategory() {
        Category category = new Category(1L, "Electronics", null);
        when(categoryRepository.save(category)).thenReturn(category);
        Category result = categoryService.saveCategory(category);
        assertEquals("Electronics", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }
    @Test
    public void testDeleteCategory() {
        doNothing().when(categoryRepository).deleteById(1L);
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
