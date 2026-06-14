import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CycleTest {

    @Test
    void acceptValidOrder() {
        Shop shop = new Shop();

        Order order = mock(Order.class);

        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Max Mustermann");

        assertTrue(shop.accept(order));
    }

    @Test
    void rejectGravelBike() {
        Shop shop = new Shop();

        Order order = mock(Order.class);

        when(order.getBicycleType()).thenReturn(Type.GRAVEL);

        assertFalse(shop.accept(order));
    }

    @Test
    void rejectEBike() {
        Shop shop = new Shop();

        Order order = mock(Order.class);

        when(order.getBicycleType()).thenReturn(Type.EBIKE);

        assertFalse(shop.accept(order));
    }

    @Test
    void rejectSecondOrderFromSameCustomer() {
        Shop shop = new Shop();

        Order order1 = mock(Order.class);
        when(order1.getBicycleType()).thenReturn(Type.RACE);
        when(order1.getCustomer()).thenReturn("Max");

        shop.accept(order1);

        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.RACE);
        when(order2.getCustomer()).thenReturn("Max");

        assertFalse(shop.accept(order2));
    }

    @Test
    void AcceptOrderOnLimit(){
        Shop shop = new Shop();

        Order order1 = mock(Order.class);
        when(order1.getBicycleType()).thenReturn(Type.RACE);
        when(order1.getCustomer()).thenReturn("Max");

        shop.accept(order1);

        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.FIXIE);
        when(order2.getCustomer()).thenReturn("Adrian");

        shop.accept(order2);

        Order order3 = mock(Order.class);
        when(order3.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(order3.getCustomer()).thenReturn("Klaus");

        shop.accept(order3);

        Order order4 = mock(Order.class);
        when(order4.getBicycleType()).thenReturn(Type.RACE);
        when(order4.getCustomer()).thenReturn("Peter");

        shop.accept(order4);

        Order order5 = mock(Order.class);
        when(order5.getBicycleType()).thenReturn(Type.RACE);
        when(order5.getCustomer()).thenReturn("Ben");

        assertTrue(shop.accept(order5));

    }

    @Test
    void RejectOrderOverLimit(){
        Shop shop = new Shop();

        Order order1 = mock(Order.class);
        when(order1.getBicycleType()).thenReturn(Type.RACE);
        when(order1.getCustomer()).thenReturn("Max");

        shop.accept(order1);

        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.FIXIE);
        when(order2.getCustomer()).thenReturn("Adrian");

        shop.accept(order2);

        Order order3 = mock(Order.class);
        when(order3.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(order3.getCustomer()).thenReturn("Klaus");

        shop.accept(order3);

        Order order4 = mock(Order.class);
        when(order4.getBicycleType()).thenReturn(Type.RACE);
        when(order4.getCustomer()).thenReturn("Peter");

        shop.accept(order4);

        Order order5 = mock(Order.class);
        when(order5.getBicycleType()).thenReturn(Type.RACE);
        when(order5.getCustomer()).thenReturn("Ben");

        shop.accept(order5);

        Order order6 = mock(Order.class);
        when(order6.getBicycleType()).thenReturn(Type.RACE);
        when(order6.getCustomer()).thenReturn("Kim");

        assertFalse(shop.accept(order6));
    }
}
