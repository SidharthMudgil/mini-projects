package com.example.martialarts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.martialarts.model.MartialArt;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class UpdateDialog extends DialogFragment {
    public interface UpdateDialogListener {
        void onUpdateButtonClick(int id, MartialArt martialArt);
    }

    private UpdateDialogListener updateDialogListener;

    private int id;
    private int flag;
    private MartialArt martialArt;
    private Context context;

    public static UpdateDialog newInstance(int flag, int id, MartialArt martialArt) {
        UpdateDialog fragment = new UpdateDialog();

        Bundle args = new Bundle();
        args.putInt("flag", flag);
        args.putInt("id", id);
        args.putSerializable("martialArt", martialArt);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flag = getArguments().getInt("flag");
            id = getArguments().getInt("id");
            martialArt = (MartialArt) getArguments().getSerializable("martialArt");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            updateDialogListener = (UpdateDialogListener) context;
            this.context = context;
        } catch (ClassCastException e) {
            throw new ClassCastException("MainActivity must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        assert getActivity() != null;
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.update_martial_art, null);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.update, null)
                .setNegativeButton(R.string.cancel, null)
                .create();

        TextInputEditText nameET = dialogView.findViewById(R.id.editText_name_new);
        TextInputEditText colorET = dialogView.findViewById(R.id.editText_color_new);
        TextInputEditText priceET = dialogView.findViewById(R.id.editText_price_new);

        nameET.setText(martialArt.getMartialArtName());
        colorET.setText(martialArt.getMartialArtColor());
        priceET.setText(String.valueOf(martialArt.getMartialArtPrice()));

        alertDialog.setOnShowListener(dialog -> {
            Button positiveBtn = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
            Button negativeBtn = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);

            positiveBtn.setOnClickListener(v -> {

                TextInputEditText[] allFields = {
                        nameET,
                        colorET,
                        priceET
                };

                boolean isValid = checkForm(allFields);

                if (isValid) {
                    dialog.dismiss();
                }
            });

            negativeBtn.setOnClickListener(v -> dialog.dismiss());
        });

        return alertDialog;
    }

    private boolean checkForm(TextInputEditText[] allFields) {
        List<TextInputEditText> errorFields = new ArrayList<>();

        for (TextInputEditText inputField : allFields) {

            if (TextUtils.isEmpty(inputField.getText())) {

                errorFields.add(inputField);

                for (int i = errorFields.size() - 1; i >= 0; i--) {
                    TextInputEditText currentField = errorFields.get(i);
                    currentField.setError(context.getString(R.string.error_message));
                    currentField.requestFocus();
                    showKeyboard();
                }
            }
        }

        if (errorFields.isEmpty()) {
            closeKeyboard();
            MartialArt martialArt = new MartialArt(
                    id,
                    String.valueOf(allFields[0].getText()),
                    String.valueOf(allFields[1].getText()),
                    Double.parseDouble(String.valueOf(allFields[2].getText()))
            );
            updateDialogListener.onUpdateButtonClick(flag, martialArt);
            return true;
        }

        return false;
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
