package Startup;


import Login.LoginView;

import java.util.ArrayList;
import java.util.List;

public class Startup {

    private List<StartupView> views;

    public Startup() {
        views = new ArrayList<>();
    }

    public void addView(StartupView newView)
    {
        views.add(newView);
    }

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

    public void register()
    {
        for (StartupView v:views)
        {
            v.register();
        }
    }

    public void login()
    {
        for (StartupView v:views)
        {
            v.login();
        }
    }
}
