package com.sidharth.math4kid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OutputFragment extends Fragment {
    private int l1;
    private int u1;
    private int l2;
    private int u2;
    private int max;
    private int rows;
    private int cols;
    private int opr;
    private int format;

    public static OutputFragment newInstance(int l1, int u1, int l2, int u2, int rows, int cols, int max, int opr, int format) {
        OutputFragment fragment = new OutputFragment();
        Bundle args = new Bundle();
        args.putInt("l1", l1);
        args.putInt("u1", u1);
        args.putInt("l2", l2);
        args.putInt("u2", u2);
        args.putInt("max", max);
        args.putInt("rows", rows);
        args.putInt("cols", cols);
        args.putInt("opr", opr);
        args.putInt("format", format);
        fragment.setArguments(args);
        return fragment;
    }

    public OutputFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            l1 = getArguments().getInt("l1");
            u1 = getArguments().getInt("u1");
            l2 = getArguments().getInt("l2");
            u2 = getArguments().getInt("u2");
            max = getArguments().getInt("max");
            rows = getArguments().getInt("rows");
            cols = getArguments().getInt("cols");
            opr = getArguments().getInt("opr");
            format = getArguments().getInt("format");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_output, container, false);

        QuestionAdapter adapter = new QuestionAdapter(getQuestions(), opr, format);
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), cols));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private ArrayList<ArrayList<Integer>> getQuestions() {
        Random random = new Random();
        ArrayList<ArrayList<Integer>> questions = new ArrayList<>();
        int count = rows * cols * 3;

        for (int i = 0; i < rows * cols; i++) {
            int num1 = random.nextInt(u1 - l1 + 1) + l1;
            int num2 = random.nextInt(u2 - l2 + 1) + l2;

            if (opr == 0 && count > 0) {
                while (num1 + num2 > max || questions.contains(new ArrayList<>(Arrays.asList(num1, num2)))) {
                    num1 = random.nextInt(u1 - l1 + 1) + l1;
                    num2 = random.nextInt(u2 - l2 + 1) + l2;
                    count--;

                    if (count <= 0) {
                        return questions;
                    }
                }
            }

            if (num1 + num2 < max && !questions.contains(new ArrayList<>(Arrays.asList(num1, num2)))){
                questions.add(new ArrayList<>(Arrays.asList(num1, num2)));
            }
        }

        return questions;
    }
}