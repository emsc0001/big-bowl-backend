package bigbowl.bowlinglane;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BowlingLaneController.class)
class BowlingLaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BowlingLaneService bowlingLaneService;

    private ObjectMapper objectMapper;
    private BowlingLane bowlingLane;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        bowlingLane = new BowlingLane(1L, 5, true, false);
    }

    @Test
    void createBowlingLane() throws Exception {
        // Arrange
        Mockito.when(bowlingLaneService.saveOrUpdate(Mockito.any(BowlingLane.class))).thenReturn(bowlingLane);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/BowlingLanes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bowlingLane)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(100)))
                .andExpect(jsonPath("$.laneNumber", is(100)))
                .andExpect(jsonPath("$.isForKids", is(true)))
                .andExpect(jsonPath("$.underMaintenance", is(false)));

        Mockito.verify(bowlingLaneService, Mockito.times(1)).saveOrUpdate(Mockito.any(BowlingLane.class));
    }

    @Test
    void deleteBowlingLane() throws Exception {
        // Arrange
        Long laneId = 1L;
        Mockito.doNothing().when(bowlingLaneService).deleteById(laneId);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/BowlingLanes/{id}", laneId))
                .andExpect(status().isOk());

        Mockito.verify(bowlingLaneService, Mockito.times(1)).deleteById(laneId);
    }
}
