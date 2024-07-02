package com.hyun.ex41broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


// 안드로이드의 4대 컴포넌트는 Manifast에 등록해야함
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "방송을 수신했습니다.", Toast.LENGTH_SHORT).show();
    }
}
