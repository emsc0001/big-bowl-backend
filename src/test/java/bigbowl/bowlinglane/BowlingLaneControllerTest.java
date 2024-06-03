
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
    void setUpCreate() {
        objectMapper = new ObjectMapper();
        bowlingLane = new BowlingLane();
        bowlingLane.setLaneNumber(1);
        bowlingLane.setIsForKids(true);
        bowlingLane.setUnderMaintenance(false);
    }

    @Test
    void getBowlingLaneById() throws Exception {
        Long laneId = 1L;
        BowlingLane expectedLane = new BowlingLane(laneId, 1, true, false);

        // Arrange
        Mockito.when(bowlingLaneService.findById(laneId)).thenReturn(expectedLane);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/BowlingLanes/" + laneId)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expectedLane.getId().intValue())))
                .andExpect(jsonPath("$.laneNumber", is(expectedLane.getLaneNumber())))
                .andExpect(jsonPath("$.forKids", is(expectedLane.isForKids())))
                .andExpect(jsonPath("$.underMaintenance", is(expectedLane.isUnderMaintenance())));
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

    @BeforeEach
    void setUpUpdate() {
        objectMapper = new ObjectMapper();
        bowlingLane = new BowlingLane(1L, 1, true, false);
    }

    @Test
    void updateBowlingLane() throws Exception {
        // Arrange
        Long laneId = 1L;
        bowlingLane.setLaneNumber(2);
        bowlingLane.setUnderMaintenance(true);

        Mockito.when(bowlingLaneService.saveOrUpdate(Mockito.any(BowlingLane.class))).thenReturn(bowlingLane);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/BowlingLanes/" + laneId)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))  // Mock JWT token with authority
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bowlingLane)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bowlingLane.getId().intValue())))
                .andExpect(jsonPath("$.laneNumber", is(bowlingLane.getLaneNumber())))
                .andExpect(jsonPath("$.forKids", is(bowlingLane.isForKids())))
                .andExpect(jsonPath("$.underMaintenance", is(bowlingLane.isUnderMaintenance())));
    }

    @Test
    void deleteBowlingLane() throws Exception {
        Long laneId = 1L;

        Mockito.doNothing().when(bowlingLaneService).deleteById(laneId);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/BowlingLanes/" + laneId)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isOk());


        Mockito.verify(bowlingLaneService, Mockito.times(1)).deleteById(laneId);
    }

}
