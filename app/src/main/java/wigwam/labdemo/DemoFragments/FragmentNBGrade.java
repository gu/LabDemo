package wigwam.labdemo.demofragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import wigwam.labdemo.R;
import wigwam.labdemo.connection.CommandUpdateListener;

/**
 * Created by freddy on 11/10/15.
 */
public class FragmentNBGrade extends Fragment {
    private static final String TAG = "FragmentNBGrade";

    private Activity activity;

    private ListView lv;
    List<String> imgSourceList = new ArrayList<>();

    private Button btnSegmentation;
    private Button btnNuclei;
    private Button btnCytoplasm;
    private Button btnBackground;
    private Button btnClassification;

    private RadioGroup rgMethod;
//    private RadioButton rbKMEANS, rbFCM;

    private EditText etClusterNumber;
    private int clusterNumber = 4;

    private CheckBox cbDisplayOptions;

    CommandUpdateListener.onCommandUpdateListener commandUpdateListener;

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container,
        Bundle savedInstanceState) {

        activity = getActivity();
        commandUpdateListener = (CommandUpdateListener.onCommandUpdateListener) activity;

        View view = inflator.inflate(R.layout.fragment_nb_grade, container, false);

        lv = (ListView) view.findViewById(R.id.nb_grade_lv);
        populateList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                imgSourceList );
        lv.setAdapter(arrayAdapter);

        btnSegmentation = (Button) view.findViewById(R.id.btn_segementation);
        btnNuclei = (Button) view.findViewById(R.id.btn_nuclei);
        btnCytoplasm = (Button) view.findViewById(R.id.btn_cytoplasm);
        btnBackground = (Button) view.findViewById(R.id.btn_background);
        btnClassification = (Button) view.findViewById(R.id.btn_classification);

        btnSegmentation.setEnabled(true);
        btnNuclei.setEnabled(false);
        btnCytoplasm.setEnabled(false);
        btnBackground.setEnabled(false);
        btnClassification.setEnabled(false);

        rgMethod = (RadioGroup) view.findViewById(R.id.rg_method);

        etClusterNumber = (EditText) view.findViewById(R.id.et_cluster_number);

        cbDisplayOptions = (CheckBox) view.findViewById(R.id.cb_display_option);

        setListeners();

        return view;

    }

    public void populateList() {
        imgSourceList.add("D1.mat");
        imgSourceList.add("D2.mat");
        imgSourceList.add("D3.mat");
        imgSourceList.add("D4.mat");
        imgSourceList.add("D5.mat");
        imgSourceList.add("D6.mat");
        imgSourceList.add("D7.mat");
        imgSourceList.add("D8.mat");
        imgSourceList.add("D9.mat");
        imgSourceList.add("D10.mat");
        imgSourceList.add("D11.mat");
        imgSourceList.add("D12.mat");
        imgSourceList.add("PD1.mat");
        imgSourceList.add("PD2.mat");
        imgSourceList.add("PD3.mat");
        imgSourceList.add("PD4.mat");
        imgSourceList.add("PD5.mat");
        imgSourceList.add("PD6.mat");
        imgSourceList.add("PD7.mat");
        imgSourceList.add("PD8.mat");
        imgSourceList.add("PD9.mat");
        imgSourceList.add("PD10.mat");
        imgSourceList.add("PD11.mat");
        imgSourceList.add("PD12.mat");
        imgSourceList.add("UD1.mat");
        imgSourceList.add("UD2.mat");
        imgSourceList.add("UD3.mat");
        imgSourceList.add("UD4.mat");
        imgSourceList.add("UD5.mat");
        imgSourceList.add("UD6.mat");
        imgSourceList.add("UD7.mat");
        imgSourceList.add("UD8.mat");
        imgSourceList.add("UD9.mat");
        imgSourceList.add("UD10.mat");
        imgSourceList.add("UD11.mat");
        imgSourceList.add("UD12.mat");
    }

    public void setListeners() {

        btnSegmentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SEND COMMAND TO CLIENT

                commandUpdateListener.commandUpdate("nb_grade_segmentation");

                btnNuclei.setEnabled(true);
                btnCytoplasm.setEnabled(true);
                btnBackground.setEnabled(true);
                btnClassification.setEnabled(true);
            }
        });

        btnNuclei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SEND COMMAND TO CLIENT
                commandUpdateListener.commandUpdate("nb_grade_nuclei");
            }
        });

        btnCytoplasm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SEND COMMAND TO CLIENT
                commandUpdateListener.commandUpdate("nb_grade_cytoplasm");
            }
        });

        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SEND COMMAND TO CLIENT
                commandUpdateListener.commandUpdate("nb_grade_background");
            }
        });

        btnClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SEND COMMAND TO CLIENT
                commandUpdateListener.commandUpdate("nb_grade_classification");
            }
        });

        rgMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbKMEANS) {
                    //TODO:SEND COMMAND
                    commandUpdateListener.commandUpdate("nb_grade_kmeans");
                } else if (checkedId == R.id.rbFCM) {
                    //TODO: SEND COMMAND
                    commandUpdateListener.commandUpdate("nb_grade_fcm");
                }
            }
        });

        etClusterNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO: do stuff, probably not.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cbDisplayOptions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: do stuff
                if (isChecked) {
                    commandUpdateListener.commandUpdate("nb_grade_display_checked");
                } else {
                    commandUpdateListener.commandUpdate("nb_grade_display_unchecked");
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = imgSourceList.get(position);
                commandUpdateListener.commandUpdate("nb_grade_item_"+name);
            }
        });
    }
}
