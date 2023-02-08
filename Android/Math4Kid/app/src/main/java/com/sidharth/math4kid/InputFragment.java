package com.sidharth.math4kid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

public class InputFragment extends Fragment {
    private RangeSlider firstNumSlider;
    private RangeSlider secondNumSlider;
    private Slider maxTotalSlider;
    private Slider totalColsSlider;
    private Slider totalRowsSlider;


    private RadioGroup operationGroup;
    private RadioGroup formatGroup;

    public InputFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        firstNumSlider = view.findViewById(R.id.first_num_slider);
        secondNumSlider = view.findViewById(R.id.second_num_slider);
        maxTotalSlider = view.findViewById(R.id.max_slider);
        totalRowsSlider = view.findViewById(R.id.total_rows_slider);
        operationGroup = view.findViewById(R.id.operation_group);
        formatGroup = view.findViewById(R.id.format_group);
        totalColsSlider = view.findViewById(R.id.total_cols_slider);
        TextView maxTV = view.findViewById(R.id.maxTV);
        Button showButton = view.findViewById(R.id.show_button);

        firstNumSlider.setValues(15.0f, 45.0f);
        secondNumSlider.setValues(15.0f, 45.0f);

        maxTotalSlider.setValueFrom(getInclusiveLowerBound());
        maxTotalSlider.setValueTo(getInclusiveUpperBound());
        maxTotalSlider.setValue(getInclusiveUpperBound());

        formatGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.horizontal) {
                totalColsSlider.setValueTo(2);
                totalColsSlider.setValue(2);
            } else {
                totalColsSlider.setValueTo(3);
                totalColsSlider.setValue(2);
            }
        });

        operationGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.add) {
                maxTotalSlider.setVisibility(View.VISIBLE);
                maxTV.setVisibility(View.VISIBLE);
            } else {
                maxTotalSlider.setVisibility(View.GONE);
                maxTV.setVisibility(View.GONE);
            }
        });

        firstNumSlider.setMinSeparationValue(10.0f);
        secondNumSlider.setMinSeparationValue(10.0f);

        firstNumSlider.addOnChangeListener((slider, value, fromUser) -> {
            maxTotalSlider.setValueFrom(getInclusiveLowerBound());
            maxTotalSlider.setValueTo(getInclusiveUpperBound());
            maxTotalSlider.setValue(getInclusiveUpperBound());
        });

        secondNumSlider.addOnChangeListener((slider, value, fromUser) -> {
            maxTotalSlider.setValueFrom(getInclusiveLowerBound());
            maxTotalSlider.setValueTo(getInclusiveUpperBound());
            maxTotalSlider.setValue(getInclusiveUpperBound());
        });

        showButton.setOnClickListener(v -> {
            int opr;
            int format = (formatGroup.getCheckedRadioButtonId() == R.id.vertical) ? 0 : 1;

            if (operationGroup.getCheckedRadioButtonId() == R.id.add) {
                opr = 0;
            } else if (operationGroup.getCheckedRadioButtonId() == R.id.sub) {
                opr = 1;
            } else {
                opr = 2;
            }


            OutputFragment fragment = OutputFragment.newInstance(
                    firstNumSlider.getValues().get(0).intValue(),
                    firstNumSlider.getValues().get(1).intValue(),
                    secondNumSlider.getValues().get(0).intValue(),
                    secondNumSlider.getValues().get(1).intValue(),
                    (int) totalRowsSlider.getValue(),
                    (int) totalColsSlider.getValue(),
                    (int) maxTotalSlider.getValue(),
                    opr,
                    format);

            if (getActivity() != null) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack("input_fragment")
                        .commit();
            }
        });

        return view;
    }

    private float getInclusiveLowerBound() {
        return firstNumSlider.getValues().get(0) + secondNumSlider.getValues().get(1);
    }

    private float getInclusiveUpperBound() {
        return firstNumSlider.getValues().get(1) + secondNumSlider.getValues().get(1);
    }
}