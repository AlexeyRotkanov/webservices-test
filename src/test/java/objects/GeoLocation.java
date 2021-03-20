package objects;

public class GeoLocation {

    private String lat = "";
    private String lng = "";

    public GeoLocation() {
    }

    public GeoLocation(String latitude, String longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }

    public void setLat(String latitude) {
        this.lat = latitude;
    }

    public String getLat() {
        return lat;
    }

    public void setLng(String longitude) {
        this.lng = longitude;
    }

    public String getLng() {
        return lng;
    }
}
