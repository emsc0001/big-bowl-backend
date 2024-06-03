
package bigbowl.bowlinglane;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(BowlingLaneController.class)
public class BowlingLaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BowlingLaneService bowlingLaneService;

    private ObjectMapper objectMapper;
    private BowlingLane bowlingLane;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        bowlingLane = new BowlingLane();
        bowlingLane.setLaneNumber(1);
        bowlingLane.setIsForKids(true);
        bowlingLane.setUnderMaintenance(false);
    }

    @Test
    void createBowlingLane() throws Exception {
        // Arrange
        Mockito.when(bowlingLaneService.saveOrUpdate(Mockito.any(BowlingLane.class))).thenReturn(bowlingLane);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/BowlingLanes")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bowlingLane)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.laneNumber", is(bowlingLane.getLaneNumber())))
                .andExpect(jsonPath("$.forKids", is(bowlingLane.isForKids())))
                .andExpect(jsonPath("$.underMaintenance", is(bowlingLane.isUnderMaintenance())));
    }
}