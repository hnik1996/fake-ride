
package ir.carpino.fakeRide.model.polygon;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated("net.hexar.json2pojo")
public class Feature {

    @Expose
    @NotNull
    private Geometry geometry;
    @Expose
    private Properties properties;
    @Expose
    @Size(min = 2)
    private String type;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
