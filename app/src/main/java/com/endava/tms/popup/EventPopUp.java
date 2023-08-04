package com.endava.tms.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.endava.tms.R;

public class EventPopUp {

    public void showPopUp(final View view){
        LayoutInflater layoutInflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popUpView = layoutInflater.inflate(R.layout.event_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popUpWindow = new PopupWindow(popUpView, width,height,focusable);
        popUpWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView text = popUpView.findViewById(R.id.t);

        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popUpWindow.dismiss();
                return true;
            }
        });

    }
}
