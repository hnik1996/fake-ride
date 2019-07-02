
package ir.carpino.fakeRide.model.polygon;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Generated("net.hexar.json2pojo")
public class Geometry {

    @Expose
    @NotNull
    private List<List<List<Double>>> coordinates;
    @Expose
    @Size(min = 2)
    private String type;

    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
