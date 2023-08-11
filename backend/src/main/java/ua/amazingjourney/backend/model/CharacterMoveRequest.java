package ua.amazingjourney.backend.model;

import lombok.Data;

@Data
public class CharacterMoveRequest {
    private MoveRequest moveRequest;
    private Character character;
}
