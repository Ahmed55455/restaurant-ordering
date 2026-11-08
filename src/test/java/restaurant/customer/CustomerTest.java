package restaurant.customer;

import org.junit.jupiter.api.Test;
import restaurant.exception.RestaurantException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void createCustomer_withValidData_setsIdAndName() {
        Customer customer = new Customer("C1", "Ahmed");

        assertEquals("C1", customer.getId());
        assertEquals("Ahmed", customer.getName());
    }

    @Test
    void createCustomer_withEmptyId_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Customer(" ", "Ahmed"));
    }

    @Test
    void createCustomer_withNullId_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Customer(null, "Ahmed"));
    }

    @Test
    void createCustomer_withEmptyName_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Customer("C1", ""));
    }

    @Test
    void createCustomer_withNullName_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Customer("C1", null));
    }
}