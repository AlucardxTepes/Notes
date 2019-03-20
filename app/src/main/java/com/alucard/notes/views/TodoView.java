package com.alucard.notes.views;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alucard.notes.R;
import com.alucard.notes.models.Todo;

public class TodoView extends ConstraintLayout {

    private CheckBox completeCheckBox;
    private TextView descriptionView;

    public TodoView(Context context) {
        super(context);
    }

    public TodoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TodoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(Todo todo) {
        completeCheckBox = findViewById(R.id.completeCheckBox);
        descriptionView = findViewById(R.id.descriptionView);

        completeCheckBox.setChecked(todo.isComplete());
        descriptionView.setText(todo.getDescription());

        if (todo.isComplete()) {
            createStrikeThrough(descriptionView);
        }

        setupCheckStateListener();
    }

    public void setupCheckStateListener() {
        completeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                createStrikeThrough(buttonView);
            } else {
                removeStrikeThrough(buttonView);
            }
        });
    }

    private void createStrikeThrough(TextView buttonView) {
        buttonView.setPaintFlags(buttonView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void removeStrikeThrough(TextView buttonView) {
        buttonView.setPaintFlags(buttonView.getPaintFlags() & ~(Paint.STRIKE_THRU_TEXT_FLAG));
    }
}
