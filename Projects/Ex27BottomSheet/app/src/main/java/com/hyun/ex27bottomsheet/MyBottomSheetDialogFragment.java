package com.hyun.ex27bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container,false);
    }

   ImageView iv;
   CalendarView cv;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv= view.findViewById(R.id.iv);
        view.findViewById(R.id.btn).setOnClickListener(v-> iv.setImageResource(R.drawable.newyork));

        cv= view.findViewById(R.id.cv);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getActivity(),year+"년" + (month+1) + "월" + dayOfMonth + "일", Toast.LENGTH_LONG).show();
            }
        });

        view.findViewById(R.id.iv_back).setOnClickListener(v -> getDialog().dismiss());


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BottomSheetDialog dialog= new BottomSheetDialog(getActivity());
        dialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        return  dialog;
    }
}
