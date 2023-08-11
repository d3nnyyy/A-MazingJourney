package ua.amazingjourney.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MazeInitializer {

    private int size;
    private int difficulty;

}
