package com.lixm.animationdemo.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chivox.AIConfig;
import com.chivox.core.CoreService;
import com.chivox.core.CoreType;
import com.chivox.core.Engine;
import com.chivox.core.OnCreateProcessListener;
import com.chivox.core.OnLaunchProcessListener;
import com.chivox.cube.output.JsonResult;
import com.chivox.cube.output.RecordFile;
import com.chivox.cube.param.CoreCreateParam;
import com.chivox.cube.param.CoreLaunchParam;
import com.chivox.cube.pattern.Rank;
import com.chivox.cube.util.FileHelper;
import com.chivox.cube.util.constant.ErrorCode;
import com.chivox.media.OnReplayListener;
import com.lixm.animationdemo.R;

import java.io.File;

import static com.lixm.animationdemo.utils.Contants.AppKey;
import static com.lixm.animationdemo.utils.Contants.SecretKey;
import static com.lixm.animationdemo.utils.Contants.UserId;
import static com.lixm.animationdemo.utils.Contants.cloudServer;
import static com.lixm.animationdemo.utils.Contants.connectTimeout;
import static com.lixm.animationdemo.utils.Contants.serverTimeout;


public class RecordSoundActivity extends Activity {
    private Context context;
    String TAG = "yyd";
    String filePath;
    String refText;
    boolean isOnline = true;
    CoreType coretype = CoreType.en_word_score;
    boolean isVadLoaded = false;//是否加载vad资源
    boolean DebugEnable = false;
    private Engine aiengine = null;
    private CoreCreateParam coreCreateParam = null;
    private CoreService coreService = null;
    private RecordFile recfile = null;
    EditText editText;
    Button submitBtn;
    Button stopBtn;
    Button replayBtn;
    Button exitBtn;
    TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_sound);
        context=this;
        initView();
        filePath = Environment.getExternalStorageState() + File.separator + "Demo" + File.separator + "DownloadFile";
        File appDir = new File(filePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        Log.e("yyd", "文件地址：" + filePath);

        EngineInit();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.wordedt);
        submitBtn = (Button) findViewById(R.id.submitbtn);
        stopBtn = (Button) findViewById(R.id.stopbtn);
        replayBtn = (Button) findViewById(R.id.replaybtn);
        exitBtn = (Button) findViewById(R.id.exitbtn);
        resultTv = (TextView) findViewById(R.id.resulttv);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = editText.getText().toString().trim();
                Log.e("yyd", "评测：" + word);
                if (TextUtils.isEmpty(word) || word.equals("")) {
                    Toast.makeText(getApplicationContext(), "write again", Toast.LENGTH_LONG).show();
                    editText.setText("");
                } else {
                    refText = word;
                    EngineStart();
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aiengine.isRunning())
                {
                    Log.w(TAG,"======暂停======");
                    coreService.recordStop(aiengine);
                }
            }
        });

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordPlay();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EngineEnd();
            }
        });
    }

    private void EngineConfig() {
        AIConfig config = AIConfig.getInstance();
        config.setAppKey(AppKey);
        config.setSecretKey(SecretKey);
        config.setUserId(UserId);
        config.setDebugEnable(DebugEnable);
//        config.setVadRes ("mnt/sdcard/vad/ vad.0.12.20160802.bin ");
        config.setDownloadFilePath(filePath);
        File provisionFile = FileHelper.extractProvisionOnce(context, "aiengine_E_20170823143052.provision");
        config.setProvisionFile(provisionFile.getAbsolutePath());
//        config.setResdirectory("mnt/sdcard/Resource");

//        AIConfig config = AIConfig.getInstance();
//        config.setAppKey(AppKey);
//        config.setSecretKey(SecretKey);
//        config.setUserId("android_sdk_demo");
//        config.setDebugEnable(false);
//        config.setImproveScore(false);
//        config.setVadRes(FileHelper.getFilesDir(this).getAbsolutePath() + "/vad/bin/vad.0.12.20160802/vad.0.12.20160802.bin");
//        config.setDownloadFilePath("mnt/sdcard/com.chivox.download");
//        config.setResdirectory(FileHelper.getFilesDir(getBaseContext()).getAbsolutePath() + "/Resource");
//        config.setRecordFilePath(FileHelper.getFilesDir(getBaseContext()).getAbsolutePath() + "/record");
//        File provisionFile = FileHelper.extractProvisionOnce(context, "aiengine.1447050610000018.provision");
////		    	File resourceDir = FileHelper.extractResourceOnce(SelectActivity.this, "aiengine.resource.zip");
//        File resourceDir = FileHelper.extractResourceOnce(context, "Resource.zip");
//        File vadResDir = FileHelper.extractResourceOnce(context, "vad.zip");
//        Log.w(TAG, "vadResDir->" + vadResDir);
//        Log.w(TAG, "resourceDir->" + resourceDir.getAbsolutePath());
//        Log.w(TAG, "filesdir->" + FileHelper.getFilesDir(context));
//        config.setProvisionFile(provisionFile.getAbsolutePath());
////        config.setProvisionFile(ProvisionFile);


    }

    boolean isTextMode = false;

    private void EngineInit() {
        EngineConfig();
        coreCreateParam = new CoreCreateParam(cloudServer, connectTimeout, serverTimeout, isVadLoaded);
        coreService = CoreService.getInstance();
        coreService.initCore(context, coreCreateParam, new OnCreateProcessListener() {
            @Override
            public void onError(int errorCode, ErrorCode.ErrorMsg errorMsg) {
                //如创建引擎过程出现错误则在此接收
                Log.e("yyd", "初始化失败：" + String.valueOf(errorCode));
            }

            @Override
            public void onCompletion(int resultCode, Engine engine) {
                //创建成功，此处返回引擎engine
                Log.e("yyd", "初始化成功");
                aiengine = engine;
            }
        });

//        CoreCreateParam coreCreateParam;
//        if (isOnline) {
//            String cloudServer = "ws://cloud.chivox.com:8080";
//            int connectTimeout = 20;    //20s
//            int serverTimeout = 60;        //60s
//            coreCreateParam = new CoreCreateParam(cloudServer, connectTimeout, serverTimeout, isVadLoaded);
//        } else {
//            List<NativeResource> nativeResources = new ArrayList<NativeResource>();
//            NativeResource nativeResource = null;
//            String resDir = FileHelper.getFilesDir(this).getAbsolutePath();
//            Log.d(TAG, "resDir:" + resDir);
//            switch (coretype.getValue()) {
//                case Core.CORE_EN_WORD_SCORE:
//                    nativeResource = new NativeResource(CoreType.en_word_score);
//                    if (isTextMode) {
//                        nativeResource.setResName("bin2");
//                    }
//                    break;
//
//                case Core.CORE_EN_SENT_SCORE:
//                    nativeResource = new NativeResource(CoreType.en_sent_score);
//                    if (isTextMode) {
//                        nativeResource.setResName("bin2");
//                    }
//                    break;
//
//                case Core.CORE_EN_SENT_REC:
//                    nativeResource = new NativeResource(CoreType.en_sent_rec);
//                    break;
//
//                case Core.CORE_EN_PRED_SCORE:
//                    nativeResource = new NativeResource(CoreType.en_pred_score);
//                    break;
//
//                case Core.CORE_CN_WORD_SCORE:
//                    nativeResource = new NativeResource(CoreType.cn_word_score);
//                    break;
//
//                case Core.CORE_CN_SENT_SCORE:
//                    nativeResource = new NativeResource(CoreType.cn_sent_score);
//                    break;
//
//                case Core.CORE_EN_SENT_SYNTH:
//
//                    break;
//
//                case Core.CORE_CN_SENT_SYNTH:
//
//                    break;
//            }
//            nativeResources.add(nativeResource);
//            coreCreateParam = new CoreCreateParam(nativeResources, isVadLoaded);
//        }
//        String cfgText = null;
//        try {
//            cfgText = coreCreateParam.getCoreCreateParams();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.w(TAG, "new cfgText:" + cfgText);
//        coreService = CoreService.getInstance();
//        coreService.initCore(context, coreCreateParam, new OnCreateProcessListener() {
//
//            @Override
//            public void onError(int errorCode, ErrorMsg errorMsg) {
//                Log.e(TAG, "errorMsg->" + errorMsg);
//            }
//
//            @Override
//            public void onCompletion(int resultCode, Engine engine) {
//                aiengine = engine;
//                Log.w(TAG, "engine->" + engine.getPointer());
//                byte[] data = new byte[1024];
//                AIEngineProxy.aiengineOpt(engine, AIEngineProxy.AIENGINE_OPT_GET_MODULES, data, 1024);
//                Log.e(TAG, "modules->" + new String(data).trim());
//            }
//        });
    }

    private void EngineStart() {
        CoreLaunchParam coreparam = new CoreLaunchParam(isOnline, coretype, refText, isVadLoaded);
        coreparam.setRank(Rank.rank100);
        coreparam.getRequest().setAttachAudioUrl(true);
        coreparam.setClientParamsExtCurSntForEnPredScore(true);
        coreparam.setPrecision(0.5f);
        coreparam.setSoundIntensityEnable(true);
//        CoreService coreSer = CoreService.getInstance();
        try {
            Log.w(TAG, coreparam.getCoreType() + "====" + coreparam.getCoreLaunchParams());
        }catch (Exception e){e.printStackTrace();}
        coreService.recordStart(context, aiengine, coreparam, new OnLaunchProcessListener() {
            @Override
            public void onBeforeLaunch(long l) {
                //录音开始前的回调，在这可以做准备工作
                Log.e("yyd", "最大时长:" + String.valueOf(l));
                Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_LONG).show();
                submitBtn.setText("开始评测");
            }

            @Override
            public void onRealTimeVolume(double v) {
                //实时音量
                Log.e("yyd", "实时音量:" + String.valueOf(v));
            }

            @Override
            public void onAfterLaunch(int i, JsonResult jsonResult, RecordFile recordFile) {
                //返回的评分结果和录音文件
                Log.e("yyd", "评分结果：" + jsonResult.toString());

                recfile = recordFile;
                Message m=Message.obtain();
                m.what=1;
                m.obj=jsonResult.toString();
                handler.sendMessage(m);

            }

            @Override
            public void onError(int i, ErrorCode.ErrorMsg errorMsg) {
                //错误信息
                Log.e("yyd", "错误信息：" + errorMsg.toString());
            }
        });

//        CoreLaunchParam coreParam = new CoreLaunchParam(isOnline, coretype, refText, isVadLoaded);
//
//        coreParam.setRank(Rank.rank100);
//        coreParam.getRequest().setAttachAudioUrl(true);
//        coreParam.setClientParamsExtCurSntForEnPredScore(true);
//        coreParam.setPrecision(0.5f);
//        coreParam.setSoundIntensityEnable(true);
//        try {
//            Log.w(TAG, "cfgText " + coreParam.getCoreLaunchParams());
//            Log.w(TAG, coreParam.getCoreType() + "====" + coreParam.getCoreLaunchParams());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.e(TAG, "==开始录制=====Running：" + aiengine.isRunning() + "=====Online：" + aiengine.isOnline());
//        coreService.recordStart(this, aiengine, coreParam, new OnLaunchProcessListener() {
//
//            @Override
//            public void onError(int errorCode,ErrorMsg errorMsg) {
//                Log.e(TAG, "errorMsg->" + errorMsg);
////                isRecording = false;
////                record();
//            }
//
//            @Override
//            public void onRealTimeVolume(final double volume) {
//                Log.i(TAG, "volume->" + volume);
////                runOnUiThread(new Runnable() {
////
////                    @Override
////                    public void run() {
////                        voiceLineView.setVolume((int) volume);
////                    }
////                });
//            }
//
//            @Override
//            public void onBeforeLaunch(long duration) {
//                Log.i(TAG, "duration->" + duration);
//            }
//
//            @Override
//            public void onAfterLaunch(final int resultCode, final JsonResult jsonResult,
//                                      RecordFile recordFile) {
//                //resultCode 1:错误  2:vad 3:sound 4:segment 5:evaluate
//                Log.e(TAG, "resultCode->" + resultCode + " jsonResult->" + jsonResult + " recordFile->" + recordFile);
////                runOnUiThread(new Runnable() {
////
////                    @Override
////                    public void run()
////                    {
////                        if(5 == resultCode){
////
////                        }
////                        tvResult.setText(new SonantPostProcess().process(jsonResult.getJsonText()));
////                    }
////                });
//                recfile = recordFile;
//            }
//        });
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    resultTv.setText(msg.obj.toString());
                    editText.setText("");
                    submitBtn.setText("确定");
                    break;
            }
        }
    };

    private void RecordPlay() {
        if (recfile != null) {
            File audioFile = recfile.getRecordFile();
            coreService.replayStart(getApplicationContext(), audioFile, new OnReplayListener() {
                @Override
                public void onBeforeReplay(long l) {

                }

                @Override
                public void onAfterReplay(int i) {

                }

                @Override
                public void onError(int i, ErrorCode.ErrorMsg errorMsg) {
                    Log.e("yyd", "错误信息：" + errorMsg.toString());
                }
            });
        } else {
            Toast.makeText(this, "文件为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void EngineEnd() {
        aiengine.destory();
        finish();
    }

    /**
     * 判断是是否有录音权限
     */
    public static void isRecordUseable(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "start", Toast.LENGTH_LONG).show();
        }
    }
}