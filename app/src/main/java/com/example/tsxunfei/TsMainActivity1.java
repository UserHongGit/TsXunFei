//package com.example.tsxunfei;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.iflytek.cloud.ErrorCode;
//import com.iflytek.cloud.InitListener;
//import com.iflytek.cloud.RecognizerListener;
//import com.iflytek.cloud.RecognizerResult;
//import com.iflytek.cloud.SpeechConstant;
//import com.iflytek.cloud.SpeechError;
//import com.iflytek.cloud.SpeechSynthesizer;
//import com.iflytek.cloud.SpeechUtility;
//import com.iflytek.cloud.SynthesizerListener;
//import com.iflytek.cloud.ui.RecognizerDialog;
//import com.iflytek.cloud.ui.RecognizerDialogListener;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.PrintStream;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//
//public class TsMainActivity1 extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
//
//    private static String TAG = "----------";
//    private HashMap<String, String> mIatResults = new LinkedHashMap();
//
//
//    private EditText longClick;
//    private EditText shortClick;
//
//
//    class MyInitListener implements InitListener {
//        MyInitListener() {
//        }
//
//        public void onInit(int code) {
//            if (code != 0) {
//                TsMainActivity1.this.showTip("初始化失败 ");
//            }
//        }
//    }
//
//    class MySynthesizerListener implements SynthesizerListener {
//        MySynthesizerListener() {
//        }
//
//        public void onSpeakBegin() {
//            TsMainActivity1.this.showTip(" 开始播放 ");
//        }
//
//        public void onSpeakPaused() {
//            TsMainActivity1.this.showTip(" 暂停播放 ");
//        }
//
//        public void onSpeakResumed() {
//            TsMainActivity1.this.showTip(" 继续播放 ");
//        }
//
//        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
//        }
//
//        public void onSpeakProgress(int percent, int beginPos, int endPos) {
//        }
//
//        public void onCompleted(SpeechError error) {
//            if (error == null) {
//                TsMainActivity1.this.showTip("播放完成 ");
//            } else if (error != null) {
//                TsMainActivity1.this.showTip(error.getPlainDescription(true));
//            }
//        }
//
//        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
//        }
//    }
//
//
//    class MyRecognizerDialogListener implements RecognizerDialogListener {
//        MyRecognizerDialogListener() {
//        }
//
//        public void onResult(RecognizerResult results, boolean isLast) {
//            Log.d("Result", results.getResultString());
//            //(1) 解析 json 数据<< 一个一个分析文本 >>
//            String result = results.getResultString();
//            TsMainActivity1.this.showTip(result);
//            System. out.println(" 没有解析的 :" + result);
//            String text = JsonParser.parseIatResult(result);
//            System. out.println(" 解析后的 :" + text);
//            TsMainActivity1.this.shortClick.append(text);
//            String sn = null;
//            try {
//                sn = new JSONObject(results.getResultString()).optString("sn");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            TsMainActivity1.this.mIatResults.put(sn, text);
//            StringBuffer resultBuffer = new StringBuffer();
//            Iterator it = TsMainActivity1.this.mIatResults.keySet().iterator();
//            while (true) {
//                boolean hasNext = it.hasNext();
//                String str = TsMainActivity1.TAG;
//                if (hasNext) {
//                    String key = (String) it.next();
//                    resultBuffer.append((String) TsMainActivity1.this.mIatResults.get(key));
//                    StringBuilder stringBuilder3 = new StringBuilder();
//                    stringBuilder3.append("onResult: ");
//                    stringBuilder3.append((String) TsMainActivity1.this.mIatResults.get(key));
//                    Log.d(str, stringBuilder3.toString());
//                } else {
//                    StringBuilder stringBuilder4 = new StringBuilder();
//                    stringBuilder4.append("onResult: _________");
//                    stringBuilder4.append(resultBuffer.toString());
//                    Log.d(str, stringBuilder4.toString());
//                    TsMainActivity1.this.shortClick.setSelection(TsMainActivity1.this.shortClick.length());
//                    return;
//                }
//            }
//        }
//
//        public void onError(SpeechError speechError) {
//            PrintStream printStream = System.out;
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(speechError.getErrorCode());
//            stringBuilder.append("////");
//            stringBuilder.append(speechError.getErrorDescription());
//            printStream.println(stringBuilder.toString());
//            if (speechError.getErrorCode() == ErrorCode.MSP_ERROR_NO_DATA) {
//                TsMainActivity1.this.showTip("没说话");
//            }
//        }
//    }
//
//    private RecognizerListener mRecoListener = new RecognizerListener() {
//        public void onResult(RecognizerResult results, boolean isLast) {
//            Log.e(TsMainActivity1.TAG, results.getResultString());
//            System.out.println(results.getResultString());
//            TsMainActivity1.this.showTip(results.getResultString());
//        }
//
//        public void onError(SpeechError error) {
//            TsMainActivity1.this.showTip(error.getPlainDescription(true));
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("error.getPlainDescription(true)==");
//            stringBuilder.append(error.getPlainDescription(true));
//            Log.e(TsMainActivity1.TAG, stringBuilder.toString());
//        }
//
//        public void onBeginOfSpeech() {
//            TsMainActivity1.this.showTip(" 开始录音 ");
//        }
//
//        public void onVolumeChanged(int volume, byte[] data) {
//            TsMainActivity1.this.showTip(" 声音改变了 ");
//        }
//
//        public void onEndOfSpeech() {
//            TsMainActivity1.this.showTip(" 结束录音 ");
//        }
//
//        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
//        }
//    };
//
//    private void showTip(String data) {
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        shortClick = (EditText)findViewById(R.id.shortClick);
//        shortClick.setOnLongClickListener(this);
//        longClick = (EditText)findViewById(R.id.longClick);
//        longClick.setOnLongClickListener(this);
//
//        initSpeech();
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.shortClick:
//                startSpeechDialog();
//                break;
//            case R.id.longClick:
//                startSpeechDialog();
//                break;
//        }
//    }
//
//    @Override
//    public boolean onLongClick(View view) {
//        startSpeechDialog();
//        return true;
//    }
//
//    private void initSpeech() {
//        SpeechUtility.createUtility(this, "appid=5cbec4d8");
//    }
//
//
//    private void startSpeechDialog() {
//        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener());
//        System.out.println(mDialog+"///");
////        mDialog.setParameter("language", "zh_cn");
//        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
//        mDialog.setListener(new MyRecognizerDialogListener());
//        mDialog.show();
//        ((TextView) mDialog.getWindow().getDecorView().findViewWithTag("textlink")).setText("");
//    }
//
//
//    private void speekText() {
//        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(this, null);
//        mTts.setParameter(SpeechConstant.VOICE_NAME, "vixyun");
//        mTts.setParameter(SpeechConstant.SPEED, "50");
//        mTts.setParameter(SpeechConstant.VOLUME, "80");
//        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
//        mTts.startSpeaking(this.shortClick.getText().toString(), new MySynthesizerListener());
//    }
//
//}
