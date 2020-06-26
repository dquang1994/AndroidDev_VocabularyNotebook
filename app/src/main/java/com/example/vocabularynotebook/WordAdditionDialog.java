package com.example.vocabularynotebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class WordAdditionDialog extends DialogFragment {
    WordAdditionDialogCallback callback;
    DatabaseWrapper databaseWrapper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        databaseWrapper = new DatabaseWrapper(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.word_addition_dialog, null);

        final EditText etxtNewWord = dialogView.findViewById(R.id.word_addition_dialog_input_new_word);
        final EditText etxtMeaning = dialogView.findViewById(R.id.word_addition_dialog_input_meaning);
        Button btnOk = dialogView.findViewById(R.id.word_addition_dialog_btn_ok);
        Button btnCancel = dialogView.findViewById(R.id.word_addition_dialog_btn_cancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eng = etxtNewWord.getText().toString();
                String vn = etxtMeaning.getText().toString();
                databaseWrapper.insertWord(eng, vn);
                callback.onOkClick();
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(dialogView);

        return builder.create();
    }

    @Override
    public void onStop() {
        super.onStop();
        databaseWrapper.closeDatabase();
    }

    public void setCallback(WordAdditionDialogCallback callback){
        this.callback = callback;
    }

    public interface WordAdditionDialogCallback {
        public void onOkClick();
    }
}
