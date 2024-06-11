import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
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
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllOrders() {
        Order order1 = new Order(1L, "John Doe", "john@example.com", null, BigDecimal.ZERO);
        Order order2 = new Order(2L, "Jane Doe", "jane@example.com", null, BigDecimal.ZERO);
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> result = orderService.getAllOrders();
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }
    @Test
    public void testGetOrderById() {
        Order order = new Order(1L, "John Doe", "john@example.com", null, BigDecimal.ZERO);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Order result = orderService.getOrderById(1L);
        assertEquals("John Doe", result.getCustomerName());
        verify(orderRepository, times(1)).findById(1L);
    }
    @Test
    public void testGetOrderByIdNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        Order result = orderService.getOrderById(1L);
        assertNull(result);
        verify(orderRepository, times(1)).findById(1L);
    }
    @Test
    public void testSaveOrder() {
        Order order = new Order(1L, "John Doe", "john@example.com", Arrays.asList(
                new OrderItem(1L, null, 2, BigDecimal.valueOf(20), null)
        ), BigDecimal.ZERO);
        when(orderRepository.save(order)).thenReturn(order);
        Order result = orderService.saveOrder(order);
        assertEquals("John Doe", result.getCustomerName());
        assertEquals(BigDecimal.valueOf(20), result.getTotalAmount());
        verify(orderRepository, times(1)).save(order);
    }
    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }
    @Test
    public void testGetOrdersByCustomerEmail() {
        Order order1 = new Order(1L, "John Doe", "john@example.com", null, BigDecimal.ZERO);
        List<Order> orders = Arrays.asList(order1);
        when(orderRepository.findByCustomerEmail("john@example.com")).thenReturn(orders);
        List<Order> result = orderService.getOrdersByCustomerEmail("john@example.com");
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByCustomerEmail("john@example.com");
    }
}