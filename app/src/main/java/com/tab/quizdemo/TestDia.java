package com.tab.quizdemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestDia extends AppCompatDialogFragment {
    EditText link,id;
    Button attempt;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.test_dia, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(rootView)
                .show();
        dialog.setCanceledOnTouchOutside(false);
        link=rootView.findViewById(R.id.linkEt);
        id=rootView.findViewById(R.id.idEt);
        attempt=rootView.findViewById(R.id.att_btn);
        attempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(link.getText().toString().isEmpty()){
                    showErrorMessage("fill fields");
                }
               else if(id.getText().toString().isEmpty()){
                    showErrorMessage("fill fields");
                }
                else{
                    Intent i= new Intent(getActivity(),QuestionsActivity.class);
                    i.putExtra("link",link.getText().toString().trim());
                    i.putExtra("qid",id.getText().toString().trim());
                    dismiss();
                    startActivity(i);
            }}
        });
        return dialog;
    }

    private void showSuccessMessage(String msg){

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_green,
                (ViewGroup) getActivity().findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.cus_toast);
        text.setText(msg);
        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    private void showErrorMessage(String msg){

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_red,
                (ViewGroup) getActivity().findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.cus_toast);
        text.setText(msg);
        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
