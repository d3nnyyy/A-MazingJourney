package ua.amazingjourney.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.amazingjourney.backend.exception.MazeGenerationException;
import ua.amazingjourney.backend.exception.ShortestPathCalculationException;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.service.MazeService;

import java.util.LinkedList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for MazeController.
 * Tests the controller's methods.
 */
@WebMvcTest(MazeController.class)
class MazeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MazeService mazeService;

    /**
     * Tests the generateMaze method.
     * Checks if the response contains the generated maze and the shortest path.
     *
     * @throws Exception If an error occurs.
     */
    @Test
    public void generateMaze() throws Exception {

        int size = 5;
        int difficulty = 2;
        int capacity = size * 2 - 1;

        MazeInitializer mazeInitializer = new MazeInitializer(size, difficulty);
        boolean[][] generatedMaze = new boolean[capacity][capacity];
        LinkedList<LinkedList<Integer>> shortestPath = new LinkedList<>();
        when(mazeService.generateMaze(mazeInitializer)).thenReturn(generatedMaze);
        when(mazeService.getShortestPath()).thenReturn(shortestPath);

        mockMvc.perform(post("/api/maze/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mazeInitializer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maze").exists())
                .andExpect(jsonPath("$.shortestPath").exists());

    }

    /**
     * Tests the generateMaze method.
     * Checks if the response contains the error message.
     *
     * @throws Exception If an error occurs.
     */
    @Test
    public void getStats() throws Exception {

        TraveledPathRequest traveledPathRequest = new TraveledPathRequest();
        Double stats = 50.0;
        when(mazeService.getStats(traveledPathRequest)).thenReturn(stats);

        mockMvc.perform(post("/api/maze/stats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traveledPathRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(stats.toString()));

    }

    /**
     * Test for generating a maze with a simulated MazeGenerationException.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void generateMaze_MazeGenerationException() throws Exception {

        // Simulate MazeGenerationException being thrown
        MazeInitializer mazeInitializer = new MazeInitializer(5, 2);

        when(mazeService.generateMaze(mazeInitializer))
                .thenThrow(new MazeGenerationException("Maze generation failed", new RuntimeException()));

        mockMvc.perform(post("/api/maze/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mazeInitializer)))
                .andExpect(status().isInternalServerError())  // Expect HTTP 500 status
                .andExpect(content().string("Maze generation failed"));  // Expect the exception message
    }

    /**
     * Test for generating a maze with a simulated ShortestPathCalculationException.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void generateMaze_ShortestPathCalculationException() throws Exception {
        MazeInitializer mazeInitializer = new MazeInitializer(5, 2);

        // Simulate ShortestPathCalculationException being thrown
        when(mazeService.generateMaze(mazeInitializer))
                .thenThrow(new ShortestPathCalculationException("Shortest path calculation failed", new RuntimeException()));

        mockMvc.perform(post("/api/maze/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mazeInitializer)))
                .andExpect(status().isInternalServerError())  // Expect HTTP 500 status
                .andExpect(content().string("Shortest path calculation failed"));  // Expect the exception message
    }

    /**
     * Test for generating a maze with a simulated RuntimeException.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void generateMaze_RuntimeException() throws Exception {
        MazeInitializer mazeInitializer = new MazeInitializer(5, 2);

        // Simulate RuntimeException being thrown
        when(mazeService.generateMaze(mazeInitializer))
                .thenThrow(new RuntimeException("Exception"));

        mockMvc.perform(post("/api/maze/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mazeInitializer)))
                .andExpect(status().isInternalServerError())  // Expect HTTP 500 status
                .andExpect(content().string("Exception"));  // Expect the exception message
    }

    /**
     * Test for getting stats with a simulated ShortestPathCalculationException.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void getStats_ShortestPathCalculationException() throws Exception {
        TraveledPathRequest traveledPathRequest = new TraveledPathRequest();

        // Simulate ShortestPathCalculationException being thrown
        Mockito.when(mazeService.getStats(traveledPathRequest))
                .thenThrow(new ShortestPathCalculationException("Shortest path calculation failed", new RuntimeException()));

        mockMvc.perform(post("/api/maze/stats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traveledPathRequest)))
                .andExpect(status().isInternalServerError())  // Expect HTTP 500 status
                .andExpect(content().string("Shortest path calculation failed"));  // Expect the exception message
    }

    /**
     * Test for getting stats with a simulated RuntimeException.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void getStats_RuntimeException() throws Exception {
        TraveledPathRequest traveledPathRequest = new TraveledPathRequest();

        // Simulate ShortestPathCalculationException being thrown
        Mockito.when(mazeService.getStats(traveledPathRequest))
                .thenThrow(new RuntimeException("Exception"));

        mockMvc.perform(post("/api/maze/stats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traveledPathRequest)))
                .andExpect(status().isInternalServerError())  // Expect HTTP 500 status
                .andExpect(content().string("Exception"));  // Expect the exception message
    }


}