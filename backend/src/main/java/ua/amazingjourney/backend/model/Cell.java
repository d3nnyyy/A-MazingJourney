package ua.amazingjourney.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * A single cell in a maze
 * Contains its i-coordinate and j-coordinate
 */
@Data
@AllArgsConstructor
public class Cell {

    /**
     * I-coordinate or Y-coordinate
     */
    @JsonProperty("iCoordinate")
    private int iCoordinate;

    /**
     * J-coordinate or X-coordinate
     */
    @JsonProperty("jCoordinate")
    private int jCoordinate;

    /**
     * Copy constructor
     * @param another cell to copy from
     */
    public Cell(Cell another) {
        this.iCoordinate = another.getICoordinate();
        this.jCoordinate = another.getJCoordinate();
    }

    /**
     * Returns if two cells are the same. Compares their coordinates
     * @param another cell to compare with
     * @return true if two cells are the same, false if not
     */
    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another == null || getClass() != another.getClass()) return false;
        Cell cell = (Cell) another;
        return iCoordinate == cell.iCoordinate && jCoordinate == cell.jCoordinate;
    }

    /**
     * Overrode hashCode method
     * @return hash code of a cell
     */
    @Override
    public int hashCode() {
        return Objects.hash(iCoordinate, jCoordinate);
    }

    /**
     * Makes cell readable
     * @return string of cell
     */
    @Override
    public String toString() {
        return "Cell{" +
                "iCoordinate=" + iCoordinate +
                ", jCoordinate=" + jCoordinate +
                '}';
    }
}
