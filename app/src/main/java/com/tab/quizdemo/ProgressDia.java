package com.tab.quizdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ProgressDia extends AppCompatDialogFragment {
    AlertDialog dialog;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater=getActivity ().getLayoutInflater ();
        View rootView=inflater.inflate (R.layout.progress_dia,null,false);
        dialog=new AlertDialog.Builder(getActivity()).setView(rootView)
                .show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
