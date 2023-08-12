package ua.amazingjourney.backend.tools;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Cell {

    @JsonProperty("iCoordinate")
    private int iCoordinate;
    @JsonProperty("jCoordinate")
    private int jCoordinate;

    public Cell(Cell another) {
        this.iCoordinate = another.getICoordinate();
        this.jCoordinate = another.getJCoordinate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return iCoordinate == cell.iCoordinate && jCoordinate == cell.jCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iCoordinate, jCoordinate);
    }
}
