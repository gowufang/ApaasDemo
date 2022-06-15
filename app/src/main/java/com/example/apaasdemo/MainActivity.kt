package com.example.apaasdemo

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.apaasdemo.R
import com.example.apaasdemo.rtmtoken.RtmTokenBuilder
import io.agora.agoraeducore.core.internal.framework.proxy.RoomType
import io.agora.agoraeducore.core.internal.launch.*
import io.agora.classroom.sdk.AgoraClassSdkConfig
import io.agora.classroom.sdk.AgoraClassroomSDK


class MainActivity : AppCompatActivity() {
    var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_start_class_room).setOnClickListener {
            showLoadingDialog()
            startClassRoom()
        }
    }

    var roomName = "myArtRoomClass"

    /**
     * 这里所有的参数需要从自己的服务器获取，这是只是为了测试
     */
    fun startClassRoom() {
        val userUuid = "xiaoming2"
        /*填入你的App ID*/
        val appId = "47b7535dcb9a4bb4aa592115266eae98"
        val appCert = "6d9a4e2ca6ee4a33a2c57364ed712dac"
        /*填入你的RTM Token*/
        val rtmToken = RtmTokenBuilder().buildToken(appId, appCert, userUuid, RtmTokenBuilder.Role.Rtm_User, 0)

        val config = AgoraEduLaunchConfig(
            "xiaoming",
            userUuid,               // userUuid 最后一位数：2表示学生
            roomName,
            roomName + "4", // roomUuid最后一位数：0 一对一 2大班课 4小班课
            AgoraEduRoleType.AgoraEduRoleTypeStudent.value,   // 角色：1:老师角色 2:学生角色
            RoomType.LARGE_CLASS.value,  // 房间：0 一对一 2大班课 4小班课
            rtmToken,
            null,     // 上课开始时间
            1800L,     // 课程时长
            AgoraEduRegion.cn, // 区域
            null,
            null,
            AgoraEduStreamState(videoState = 1, audioState = 1), //用户上台默认是否发流 1:是 0:否
            AgoraEduLatencyLevel.AgoraEduLatencyLevelUltraLow,   //默认延时等级
            null,
            null
        )

        config.appId = appId
        AgoraClassroomSDK.setConfig(AgoraClassSdkConfig(appId))
        AgoraClassroomSDK.launch(this, config, AgoraEduLaunchCallback { event ->
            Log.e("agora", ":launch-课堂状态:" + event.name)
            dismissLoadingDialog()
        })
    }


    fun showLoadingDialog() {
        alertDialog = AlertDialog.Builder(this).create()
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable())
        alertDialog?.setCancelable(false)
        alertDialog?.setOnKeyListener { dialog, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK
        }
        alertDialog?.show()
        alertDialog?.setContentView(R.layout.loading_alert)
        alertDialog?.setCanceledOnTouchOutside(false)
    }

    fun dismissLoadingDialog() {
        runOnUiThread {
            if (null != alertDialog && alertDialog?.isShowing == true) {
                alertDialog?.dismiss()
            }
        }
    }
}