import play.api.mvc.EssentialFilter;
import play.http.HttpFilters;

/**
 * Created by enda on 17/02/16.
 */
public class Filters implements HttpFilters {

    @Override
    public EssentialFilter[] filters() {
        return new EssentialFilter[0];
    }
}
