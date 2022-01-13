package Startup;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of Startup
 */
public class Startup {

    /**
     * List of views associated with Startup model
     */
    private List<StartupView> views;

    /**
     * Default Constructor
     */
    public Startup() {
        views = new ArrayList<>();
    }

    /**
     * Adds a view to list of views associated with Startup model
     *
     * @param newView       StartupView, the new view associated with Startup
     */
    public void addView(StartupView newView)
    {
        views.add(newView);
    }

    /**
     * removes a view to list of views associated with Startup model
     *
     * @param oldView       StartupView, the view to be removed
     */
    public void removeView(StartupView oldView)
    {
        for (int i = 0; i < views.size(); i++)
        {
            if(views.get(i).equals(oldView))
            {
                views.remove(i);
                break;
            }
        }
    }

    /**
     * calls register method for all views in the list
     */
    public void register()
    {
        for (StartupView v:views)
        {
            v.register();
        }
    }

    /**
     * calls login method for all views in the list
     */
    public void login()
    {
        for (StartupView v:views)
        {
            v.login();
        }
    }
}
