package ua.mibal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ua.mibal.test.annotation.Postgres;

@Postgres
@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }
}
