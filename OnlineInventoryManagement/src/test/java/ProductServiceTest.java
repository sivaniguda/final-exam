import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", BigDecimal.valueOf(10), 100, null);
        Product product2 = new Product(2L, "Product2", BigDecimal.valueOf(20), 200, null);
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }
    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Product1", BigDecimal.valueOf(10), 100, null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(1L);
        assertEquals("Product1", result.getName());
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        Product result = productService.getProductById(1L);
        assertNull(result);
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    public void testSaveProduct() {
        Product product = new Product(1L, "Product1", BigDecimal.valueOf(10), 100, null);
        when(productRepository.save(product)).thenReturn(product);
        Product result = productService.saveProduct(product);
        assertEquals("Product1", result.getName());
        verify(productRepository, times(1)).save(product);
    }
    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
    @Test
    public void testGetProductsByCategory() {
        Product product1 = new Product(1L, "Product1", BigDecimal.valueOf(10), 100, null);
        List<Product> products = Arrays.asList(product1);
        when(productRepository.findByCategory_Id(1L)).thenReturn(products);
        List<Product> result = productService.getProductsByCategory(1L);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findByCategory_Id(1L);
    }
}
