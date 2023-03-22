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
}
