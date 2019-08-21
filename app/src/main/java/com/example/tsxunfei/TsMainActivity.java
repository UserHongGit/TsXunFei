package com.example.tsxunfei;

import android.app.Activity ;
import android.os.Bundle ;
import android.util.Log ;
import android.view.View ;
import android.widget.Button ;
import android.widget.EditText ;
import android.widget.TextView;
import android.widget.Toast ;

import com.iflytek.cloud.ErrorCode ;
import com.iflytek.cloud.InitListener ;
import com.iflytek.cloud.RecognizerListener ;
import com.iflytek.cloud.RecognizerResult ;
import com.iflytek.cloud.SpeechConstant ;
import com.iflytek.cloud.SpeechError ;
import com.iflytek.cloud.SpeechRecognizer ;
import com.iflytek.cloud.SpeechSynthesizer ;
import com.iflytek.cloud.SpeechUtility ;
import com.iflytek.cloud.SynthesizerListener ;
import com.iflytek.cloud.ui.RecognizerDialog ;
import com.iflytek.cloud.ui.RecognizerDialogListener ;

import org.json.JSONException ;
import org.json.JSONObject ;

import java.util.HashMap ;
import java.util.LinkedHashMap ;

public class TsMainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = TsMainActivity.class.getSimpleName();
    private EditText et_input,et_input2;
    private Button btn_startspeech, btn_startspeektext;

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initSpeech();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        et_input = (EditText) findViewById(R.id.et_input);
        et_input2 = (EditText) findViewById(R.id.et_input2);
        btn_startspeech = (Button) findViewById(R.id.btn_startspeech);
        btn_startspeektext = (Button) findViewById(R.id.btn_startspeektext);
        btn_startspeech.setOnClickListener(this);
        btn_startspeektext.setOnClickListener(this);

        et_input.setOnLongClickListener(this);
        et_input2.setOnLongClickListener(this);
    }

    private void initSpeech() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5cbec4d8");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startspeech: //语音识别（把声音转文字）
//                startSpeechDialog(v);
                break;
            case R.id.btn_startspeektext:// 语音合成（把文字转声音）
//                speekText();
                break;

        }

    }


    @Override
    public boolean onLongClick(View view) {
        EditText ett = (EditText)view;
        Log.i(TAG, "onLongClick: ettt+"+ett.getText());
        MyRecognizerDialogListener.startSpeechDialog(this,ett);
        return true;
    }





//    private void speekText() {
//        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
//        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(this, null);
////2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
////设置发音人（更多在线发音人，用户可参见 附录 13.2
//        mTts.setParameter(SpeechConstant.VOICE_NAME, "vixq"); // 设置发音人
//        mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
//        mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围 0~100
//        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
////设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
////保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
////仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
////3.开始合成
//        mTts.startSpeaking(et_input.getText().toString(), new MySynthesizerListener());
//
//    }



    /**
     * 语音识别
     */
//    private void startSpeech() {
//        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
//        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(this, null); //语音识别器
//        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
//        mIat.setParameter(SpeechConstant.DOMAIN, "iat");// 短信和日常用语： iat (默认)
//        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");// 设置中文
//        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");// 设置普通话
//        //3. 开始听写
//        mIat.startListening(MyRecognizerDialogListener.mRecoListener);
//    }



}