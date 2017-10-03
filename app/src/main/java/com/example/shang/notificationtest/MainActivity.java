package com.example.shang.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Second.class);
                // 这里是getActivity（）获得PendingIntent的，也可以getBroadcast（），getService（）
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // 由于每个版本的Notification老是更新，所以导到NotificationCompat，这个是support-v4包的
                Notification notification = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("title")
                        .setContentText("text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher_round) // 这里只是传个id就行了
                        // 这里要传bitmap
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        //PRIORITY_MIN,PRIORITY_LOW,PRIORITY_DEFAULT,PRIORITY_HIGH,PRIORITY_MAX 这5个的优先级从低到高，值为
                        // -2 到 2
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pendingIntent) // 设置点击通知栏的事件
                        .setAutoCancel(true)// 点击通知之后通知图标会消失
//                        // 每个手机的/system/media/audio/ringtones/目录下都有很多的音频文件
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
//                        // 振动，下标为0 表示静止的时长，为1 表示振动的时长，为2 表示静止的时长，以此类推
//                        // 记得在manifest加权限,我的不加也无所谓- -
//                        .setVibrate(new long[]{0,1000,1000,1000})
//                        // 设置led灯，第一个参数是颜色，第二个是亮起的时长，第三个是暗去的时长，下面这个是一闪一闪
//                        .setLights(Color.GREEN,1000,1000)
                        // 如果嫌麻烦，前面三个可以去掉，剩这个默认的
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        // 默认的通知栏内容太长会用省略号，这种是显示所有内容
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("xxxxxxxxxxxx" +
                                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round)
                        ))
                        .build();
                manager.notify(1,notification);
            }
        });
    }
}
