
package ir.carpino.fakeRide.model.polygon;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Generated("net.hexar.json2pojo")
public class Polygon {

    @Expose
    @NotNull
    private List<Feature> features;
    @Expose
    @Size(min = 5)
    private String type;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
