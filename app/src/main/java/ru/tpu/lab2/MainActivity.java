package ru.tpu.lab2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container) ViewsContainer container;
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_rating) EditText etRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideKeyboard();
    }

    @OnClick(R.id.btn_add)
    void onClick(View view) {
        hideKeyboard();
        if (etName.getText().toString().isEmpty() || etRating.getText().toString().isEmpty()) {
            Snackbar.make(view, getResources().getString(R.string.empty_input),
                    Snackbar.LENGTH_LONG).show();
        } else {
            String name = etName.getText().toString();
            Double rating = Double.parseDouble(etRating.getText().toString());
            if (rating > 10.0) {
                rating = 10.0;
            }
            Entry entry = new Entry(name, rating);
            container.addView(entry);

            etName.setText("");
            etRating.setText("");
        }
    }

    private void hideKeyboard() {
        etName.clearFocus();
        etRating.clearFocus();
        InputMethodManager inputManager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        if (focusedView != null && inputManager != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
