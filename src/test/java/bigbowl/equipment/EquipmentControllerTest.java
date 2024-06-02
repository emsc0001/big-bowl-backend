package bigbowl.equipment;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(EquipmentController.class)
class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentService equipmentService;

    private ObjectMapper objectMapper;
    private Equipment equipment;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        equipment = new Equipment();
        equipment.setId(1);
        equipment.setName("Ball");
        equipment.setType("Sports");
        equipment.setStatus("Operational");
        equipment.setAdditionalDetails("Standard size");
    }

    @Test
    void updateEquipmentStatus() throws Exception {
        // Arrange
        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setStatus("Defective");

        Mockito.when(equipmentService.findEquipmentById(1L)).thenReturn(equipment);
        Mockito.when(equipmentService.saveEquipment(Mockito.any(Equipment.class))).thenReturn(updatedEquipment);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/equipment/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEquipment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("Defective")));

        Mockito.verify(equipmentService, Mockito.times(1)).findEquipmentById(1L);
        Mockito.verify(equipmentService, Mockito.times(1)).saveEquipment(Mockito.any(Equipment.class));
    }
}
