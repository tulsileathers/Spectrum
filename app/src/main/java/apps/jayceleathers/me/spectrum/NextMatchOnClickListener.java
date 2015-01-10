package apps.jayceleathers.me.spectrum;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jayce on 12/15/14.
 */
public abstract class NextMatchOnClickListener implements View.OnClickListener
{

    ViewGroup container;

    public NextMatchOnClickListener(ViewGroup container) {
        this.container = container;
    }



};
