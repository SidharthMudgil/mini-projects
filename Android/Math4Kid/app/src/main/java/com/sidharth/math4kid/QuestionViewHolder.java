package com.sidharth.math4kid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionViewHolder extends RecyclerView.ViewHolder {
    private final TextView num1TV;
    private final TextView num2TV;
    private final TextView operationTV;

    public QuestionViewHolder(@NonNull View itemView) {
        super(itemView);

        num1TV = itemView.findViewById(R.id.num1);
        num2TV = itemView.findViewById(R.id.num2);
        operationTV = itemView.findViewById(R.id.operation);
    }

    public void bind(int num1, int num2, char operation) {
        num1TV.setText(String.valueOf(num1));
        num2TV.setText(String.valueOf(num2));
        operationTV.setText(String.valueOf(operation));
    }
}
