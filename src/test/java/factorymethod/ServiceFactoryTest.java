package factorymethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceFactoryTest {
    @Test
    void shouldReturnAService() {
        try {
            IService service = ServiceFactory.getService("ProductService");
            assertTrue(service instanceof IService<?>);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldNotFindAService() {
        try {
            ServiceFactory.getService("ZZZZZZ");
            fail();
        } catch (Exception e) {
            assertEquals("Could not find service", e.getMessage());
        }
    }

    @Test
    void shouldGetInvalidService() {
        try {
            ServiceFactory.getService("UserService");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid service", e.getMessage());
        }
    }
}
