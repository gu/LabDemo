package wigwam.labdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by freddy on 11/10/15.
 */
public class FragmentDemo2 extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflator.inflate(R.layout.fragment_demo2, container, false);
    }
}
