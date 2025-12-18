package cz.cvut.fit.cola.colaserver.system;

import cz.cvut.fit.cola.colaserver.controller.dto.ConsumptionCreateDto;
import cz.cvut.fit.cola.colaserver.controller.dto.UserCreateDto;
import cz.cvut.fit.cola.colaserver.controller.dto.UserDto;
import cz.cvut.fit.cola.colaserver.controller.dto.UserStatsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void fullUserLifecycleTest() {
        UserCreateDto userCreate = new UserCreateDto();
        userCreate.setUsername("system_user");
        userCreate.setEmail("sys@test.com");

        ResponseEntity<UserDto> createResponse = restTemplate.postForEntity("/users", userCreate, UserDto.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        UserDto createdUser = createResponse.getBody();
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());

        ConsumptionCreateDto consumption = new ConsumptionCreateDto();
        consumption.setUserId(createdUser.getId());
        consumption.setAmountMl(500);

        restTemplate.postForEntity("/consumptions", consumption, Object.class);

        ResponseEntity<UserStatsDto[]> leaderboardResponse = restTemplate.getForEntity("/consumptions/stats/leaderboard", UserStatsDto[].class);
        assertEquals(HttpStatus.OK, leaderboardResponse.getStatusCode());
        
        UserStatsDto[] stats = leaderboardResponse.getBody();
        boolean found = false;
        if (stats != null) {
            for (UserStatsDto stat : stats) {
                if (stat.getUsername().equals("system_user") && stat.getTotalAmountMl() == 500) {
                    found = true;
                    break;
                }
            }
        }
        assertTrue(found, "User should be in leaderboard with 500ml");
    }
}
