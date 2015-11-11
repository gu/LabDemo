package wigwam.labdemo.demofragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wigwam.labdemo.R;
import wigwam.labdemo.connection.CommandUpdateListener;

/**
 * Created by freddy on 11/10/15.
 */
public class FragmentFlorescentSegmentation extends Fragment{
    private final static String TAG = "FragmentFlorescentSegmentation";

    private Activity activity;
    CommandUpdateListener.onCommandUpdateListener commandUpdateListener;

    private ListView lv;
    List<String> srcList = new ArrayList<>();

    private Button btnOriginalSlide;
    private Button btnCellsUnsplit;
    private Button btnCellsSplit;
    private Button btnFinalImage;
    private Button btnGroundTruth;

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity();
        commandUpdateListener = (CommandUpdateListener.onCommandUpdateListener) activity;

        View view = inflator.inflate(R.layout.fragment_florescent_segmentation, container, false);

        lv = (ListView) view.findViewById(R.id.lv);
        populateList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                srcList
        );
        lv.setAdapter(arrayAdapter);

        btnOriginalSlide = (Button) view.findViewById(R.id.btn_original_slide);
        btnCellsUnsplit = (Button) view.findViewById(R.id.btn_cells_unsplit);
        btnCellsSplit = (Button) view.findViewById(R.id.btn_cells_split);
        btnFinalImage = (Button) view.findViewById(R.id.btn_final_image);
        btnGroundTruth = (Button) view.findViewById(R.id.btn_ground_truth);

        setListeners();

        return view;
    }

    public void populateList() {
        srcList.add("Slide 1");
        srcList.add("Slide 2");
        srcList.add("Slide 3");
        srcList.add("Slide 4");
    }

    public void setListeners() {
        btnOriginalSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandUpdateListener.commandUpdate("flor_seg_orig_slide");
            }
        });

        btnCellsUnsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandUpdateListener.commandUpdate("flor_seg_cell_unsplit");
            }
        });

        btnCellsSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandUpdateListener.commandUpdate("flor_seg_cell_split");
            }
        });

        btnFinalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandUpdateListener.commandUpdate("flor_seg_final_image");
            }
        });

        btnGroundTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandUpdateListener.commandUpdate("flor_seg_ground_truth");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = srcList.get(position);
                commandUpdateListener.commandUpdate("flor_seg_"+name);
            }
        });
    }

}
