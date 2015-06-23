package model;

/**
 * Created by voskart on 23.06.15.
 */
// A class which should be filled by someone (not me) with useful data
// This data will be displayed by the HotOrNot view
public class Data {
    private String _abstract;
    private Coordinates coordinates;
    private String url;

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String get_abstract() {
        return _abstract;
    }

    public String getUrl() {
        return url;
    }
}
