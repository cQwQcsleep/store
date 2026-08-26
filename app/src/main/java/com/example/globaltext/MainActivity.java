package com.example.globaltext;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private CheckBox cbAppend;
    private CheckBox cbEmoticon;
    private CheckBox cbGlobal;
    private CheckBox cbRuleI;
    private CheckBox cbRuleYou;
    private CheckBox cbAutoHide;
    private CheckBox cbVoice;
    private EditText etVoiceDelay;
    private static final int DEFAULT_VOICE_DELAY = 1500;
    private CatConfig config;
    private EditText etAppendText;
    private EditText etCustomEmoticons;
    private EditText etRules;
    private CheckBox rbPunctuation;
    private CheckBox rbRealtime;
    private CheckBox rbStreaming;
    private TextView statusText;
    private Button toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.config = CatConfig.load(this);
        } catch (Exception e) {
            this.config = new CatConfig();
        }
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(40, 40, 40, 80);
        root.setBackgroundColor(Color.parseColor("#FFF8E1"));
        root.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));

        TextView title = new TextView(this);
        title.setText("QQ文本改写助手");
        title.setTextSize(24.0f);
        title.setTextColor(Color.rgb(230, 81, 0));
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setGravity(17);
        title.setPadding(0, 40, 0, 8);
        root.addView(title);
        TextView subtitle = new TextView(this);
        subtitle.setText("控制面板 · 所有规则均可自定义");
        subtitle.setTextSize(14.0f);
        subtitle.setTextColor(Color.rgb(141, 110, 99));
        subtitle.setGravity(17);
        subtitle.setPadding(0, 0, 0, 24);
        root.addView(subtitle);

        this.statusText = new TextView(this);
        this.statusText.setTextSize(16.0f);
        this.statusText.setGravity(17);
        this.statusText.setPadding(24, 18, 24, 18);
        this.statusText.setBackgroundColor(-1);
        this.statusText.setTextColor(Color.rgb(51, 51, 51));
        root.addView(this.statusText);
        this.toggleButton = new Button(this);
        this.toggleButton.setTextSize(16.0f);
        this.toggleButton.setTextColor(-1);
        this.toggleButton.setPadding(32, 16, 32, 16);
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(-1, -2);
        btnLp.setMargins(0, 16, 0, 0);
        this.toggleButton.setLayoutParams(btnLp);
        this.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.openAccessibilitySettings();
            }
        });
        root.addView(this.toggleButton);
        root.addView(divider());

        TextView modeTitle = new TextView(this);
        modeTitle.setText("处理模式");
        modeTitle.setTextSize(18.0f);
        modeTitle.setTextColor(Color.rgb(93, 64, 55));
        modeTitle.setTypeface(Typeface.DEFAULT_BOLD);
        modeTitle.setPadding(0, 16, 0, 12);
        root.addView(modeTitle);
        LinearLayout modeRow = new LinearLayout(this);
        modeRow.setOrientation(0);
        modeRow.setPadding(0, 8, 0, 8);
        this.rbPunctuation = new CheckBox(this);
        this.rbPunctuation.setText("标点触发  ");
        this.rbPunctuation.setTextSize(16.0f);
        this.rbPunctuation.setTextColor(Color.rgb(51, 51, 51));
        this.rbPunctuation.setChecked(CatConfig.MODE_PUNCTUATION.equals(this.config.processingMode));
        this.rbPunctuation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.onPunctuationChecked(buttonView, isChecked);
            }
        });
        modeRow.addView(this.rbPunctuation);
        this.rbRealtime = new CheckBox(this);
        this.rbRealtime.setText("实时处理");
        this.rbRealtime.setTextSize(16.0f);
        this.rbRealtime.setTextColor(Color.rgb(51, 51, 51));
        this.rbRealtime.setChecked(CatConfig.MODE_REALTIME.equals(this.config.processingMode));
        this.rbRealtime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.onRealtimeChecked(buttonView, isChecked);
            }
        });
        modeRow.addView(this.rbRealtime);
        this.rbStreaming = new CheckBox(this);
        this.rbStreaming.setText("流式处理");
        this.rbStreaming.setTextSize(16.0f);
        this.rbStreaming.setTextColor(Color.rgb(51, 51, 51));
        this.rbStreaming.setChecked(CatConfig.MODE_STREAMING.equals(this.config.processingMode));
        this.rbStreaming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.onStreamingChecked(buttonView, isChecked);
            }
        });
        modeRow.addView(this.rbStreaming);
        root.addView(modeRow);
        TextView modeHint = new TextView(this);
        modeHint.setText("标点触发：打字时只在标点处立即处理\n实时处理：每输入一个字立即处理（体验可能较快）");
        modeHint.setTextSize(11.0f);
        modeHint.setTextColor(Color.rgb(161, 136, 127));
        modeHint.setPadding(0, 0, 0, 16);
        root.addView(modeHint);

        TextView funcTitle = new TextView(this);
        funcTitle.setText("功能开关");
        funcTitle.setTextSize(18.0f);
        funcTitle.setTextColor(Color.rgb(93, 64, 55));
        funcTitle.setTypeface(Typeface.DEFAULT_BOLD);
        funcTitle.setPadding(0, 16, 0, 8);
        root.addView(funcTitle);
        this.cbAppend = addCheckbox(root, "断句追加", "在句号、叹号等标点分句后追加文本", this.config.enableAppend);
        this.etAppendText = new EditText(this);
        this.etAppendText.setInputType(131073);
        this.etAppendText.setBackgroundColor(-1);
        this.etAppendText.setPadding(16, 12, 16, 12);
        this.etAppendText.setHint("追加内容（默认：喵）");
        this.etAppendText.setText(this.config.appendText != null ? this.config.appendText : "喵");
        LinearLayout.LayoutParams etLp1 = new LinearLayout.LayoutParams(-1, -2);
        etLp1.setMargins(0, 0, 0, 4);
        this.etAppendText.setLayoutParams(etLp1);
        root.addView(this.etAppendText);
        // 语音模式：开关（统一样式）+ 改写延时输入框（默认不可改，开启语音模式后可改）+ 重置
        this.cbVoice = addCheckbox(root, "语音模式", "增加改写延迟，防止改写打断语音输入", this.config.enableVoice);
        this.cbVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (MainActivity.this.etVoiceDelay != null) {
                    MainActivity.this.etVoiceDelay.setEnabled(isChecked);
                }
            }
        });
        // 改写延迟输入框：白框与断句追加一致，重置置于框内右端（灰、非加粗）
        this.etVoiceDelay = new EditText(this);
        this.etVoiceDelay.setInputType(2); // TYPE_CLASS_NUMBER
        this.etVoiceDelay.setBackgroundColor(-1);
        this.etVoiceDelay.setPadding(16, 12, 72, 12);
        android.text.SpannableString delayHint = new android.text.SpannableString("延迟范围0-3000ms，默认1500ms");
        delayHint.setSpan(new android.text.style.AbsoluteSizeSpan(12, true), 0, delayHint.length(), 0);
        this.etVoiceDelay.setHint(delayHint);
        this.etVoiceDelay.setText(String.valueOf(this.config.voiceDelayMs));
        this.etVoiceDelay.setEnabled(this.config.enableVoice); // 默认不可修改，开启语音模式可修改
        // 范围限制：超过 3000 立即回填 3000；空值由保存逻辑取默认
        this.etVoiceDelay.addTextChangedListener(new android.text.TextWatcher() {
            private boolean self = false;
            @Override
            public void beforeTextChanged(CharSequence s, int a, int b, int c) {
            }
            @Override
            public void onTextChanged(CharSequence s, int a, int b, int c) {
            }
            @Override
            public void afterTextChanged(android.text.Editable e) {
                if (this.self) {
                    return;
                }
                String raw = e.toString();
                if (raw.isEmpty()) {
                    return;
                }
                try {
                    int v = Integer.parseInt(raw);
                    if (v > 3000) {
                        this.self = true;
                        e.replace(0, e.length(), "3000");
                        android.text.Selection.setSelection(e, e.length());
                        this.self = false;
                    }
                } catch (Exception ignore) {
                }
            }
        });
        android.widget.FrameLayout voiceBox = new android.widget.FrameLayout(this);
        LinearLayout.LayoutParams vBoxLp = new LinearLayout.LayoutParams(-1, -2);
        vBoxLp.setMargins(0, 0, 0, 4);
        voiceBox.setLayoutParams(vBoxLp);
        this.etVoiceDelay.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -2));
        voiceBox.addView(this.etVoiceDelay);
        TextView voiceReset = new TextView(this);
        voiceReset.setText("重置");
        voiceReset.setTextSize(12.0f);
        voiceReset.setTextColor(Color.rgb(136, 136, 136));
        voiceReset.setGravity(17);
        voiceReset.setPadding(0, 0, 12, 0);
        voiceReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.etVoiceDelay.setText(String.valueOf(DEFAULT_VOICE_DELAY));
            }
        });
        voiceBox.addView(voiceReset, new android.widget.FrameLayout.LayoutParams(-2, -2, 21)); // RIGHT|CENTER_VERTICAL
        root.addView(voiceBox);
        this.cbEmoticon = addCheckbox(root, "句末颜文字", "在消息末尾附加随机颜文字", this.config.enableRandomEmoticon);
        this.cbGlobal = addCheckbox(root, "全局改写（所有应用）", "开启后对任意应用的输入框生效；关闭时仅处理 QQ", this.config.globalRewrite);
        this.cbRuleI = addCheckbox(root, "替换：我→本喵", "把输入中的“我”替换为“本喵”", this.config.enableRuleI);
        this.cbRuleYou = addCheckbox(root, "替换：你→主人", "把输入中的“你”替换为“主人”", this.config.enableRuleYou);
        this.cbAutoHide = addCheckbox(root, "后台自动隐藏", "失去前台焦点时关闭并移出最近任务，不在后台出现", this.config.enableAutoHide);

        TextView ruleTitle = new TextView(this);
        ruleTitle.setText("文本替换规则");
        ruleTitle.setTextSize(18.0f);
        ruleTitle.setTextColor(Color.rgb(93, 64, 55));
        ruleTitle.setTypeface(Typeface.DEFAULT_BOLD);
        ruleTitle.setPadding(0, 16, 0, 8);
        root.addView(ruleTitle);
        TextView ruleHint = new TextView(this);
        ruleHint.setText("每行一条，按顺序应用。格式：原词=替换词（也支持 ＝ 全角等号 / →）\n例：我=本喵 / 你＝主人 / 也支持数字等任意文本");
        ruleHint.setTextSize(12.0f);
        ruleHint.setTextColor(Color.rgb(141, 110, 99));
        ruleHint.setPadding(0, 0, 0, 12);
        root.addView(ruleHint);
        this.etRules = new EditText(this);
        this.etRules.setInputType(131073);
        this.etRules.setLines(6);
        this.etRules.setMinLines(6);
        this.etRules.setBackgroundColor(-1);
        this.etRules.setPadding(16, 12, 16, 12);
        this.etRules.setText(CatConfig.rulesToString(this.config.rules));
        root.addView(this.etRules);

        TextView emojiTitle = new TextView(this);
        emojiTitle.setText("自定义颜文字");
        emojiTitle.setTextSize(18.0f);
        emojiTitle.setTextColor(Color.rgb(93, 64, 55));
        emojiTitle.setTypeface(Typeface.DEFAULT_BOLD);
        emojiTitle.setPadding(0, 16, 0, 8);
        root.addView(emojiTitle);
        TextView emojiHint = new TextView(this);
        emojiHint.setText("每行一个颜文字，留空则使用内置库");
        emojiHint.setTextSize(12.0f);
        emojiHint.setTextColor(Color.rgb(141, 110, 99));
        emojiHint.setPadding(0, 0, 0, 12);
        root.addView(emojiHint);
        this.etCustomEmoticons = new EditText(this);
        this.etCustomEmoticons.setInputType(131073);
        this.etCustomEmoticons.setLines(4);
        this.etCustomEmoticons.setMinLines(4);
        this.etCustomEmoticons.setBackgroundColor(-1);
        this.etCustomEmoticons.setPadding(16, 12, 16, 12);
        this.etCustomEmoticons.setHint("例如: (=^w^=) 等");
        this.etCustomEmoticons.setText(joinLines(this.config.customEmoticons));
        root.addView(this.etCustomEmoticons);

        Button saveBtn = new Button(this);
        saveBtn.setText("保存设置");
        saveBtn.setTextSize(16.0f);
        saveBtn.setTextColor(-1);
        saveBtn.setBackgroundColor(Color.rgb(255, 111, 0));
        saveBtn.setPadding(40, 16, 40, 16);
        LinearLayout.LayoutParams saveLp = new LinearLayout.LayoutParams(-1, -2);
        saveLp.setMargins(0, 16, 0, 0);
        saveBtn.setLayoutParams(saveLp);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.saveConfig();
            }
        });
        root.addView(saveBtn);
        Button testBtn = new Button(this);
        testBtn.setText("测试当前配置");
        testBtn.setTextSize(14.0f);
        testBtn.setTextColor(Color.rgb(255, 111, 0));
        testBtn.setBackgroundColor(-1);
        testBtn.setPadding(40, 14, 40, 14);
        LinearLayout.LayoutParams testLp = new LinearLayout.LayoutParams(-1, -2);
        testLp.setMargins(0, 12, 0, 0);
        testBtn.setLayoutParams(testLp);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.showTestDialog();
            }
        });
        root.addView(testBtn);
        TextView hint = new TextView(this);
        hint.setText("提示：修改设置后请点击保存，服务下次触发时自动加载");
        hint.setTextSize(11.0f);
        hint.setTextColor(Color.rgb(161, 136, 127));
        hint.setGravity(17);
        hint.setPadding(16, 36, 16, 8);
        root.addView(hint);

        scrollView.addView(root);
        setContentView(scrollView);
    }

    void onPunctuationChecked(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            this.rbRealtime.setChecked(false);
            this.rbStreaming.setChecked(false);
        }
    }

    void onRealtimeChecked(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            this.rbPunctuation.setChecked(false);
            this.rbStreaming.setChecked(false);
        }
    }

    void onStreamingChecked(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            this.rbPunctuation.setChecked(false);
            this.rbRealtime.setChecked(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateServiceStatus();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus || this.config == null || !this.config.enableAutoHide) {
            return;
        }
        // 失去前台焦点（按 Home/切应用/锁屏）且开启了自动隐藏时，关闭活动并移出最近任务，
        // 使其不出现在后台（最近任务）列表中
        if (!isFinishing() && !isChangingConfigurations()) {
            finishAndRemoveTask();
        }
    }

    private void updateServiceStatus() {
        if (this.statusText == null || this.toggleButton == null) {
            return;
        }
        boolean enabled = isAccessibilityServiceEnabled();
        if (enabled) {
            this.statusText.setText("服务状态：已开启");
            this.statusText.setTextColor(Color.rgb(46, 125, 50));
            this.toggleButton.setText("服务已开启");
            this.toggleButton.setEnabled(false);
            this.toggleButton.setBackgroundColor(Color.rgb(165, 214, 167));
            return;
        }
        this.statusText.setText("服务状态：未开启");
        this.statusText.setTextColor(Color.rgb(198, 40, 40));
        this.toggleButton.setText("前往开启无障碍服务");
        this.toggleButton.setEnabled(true);
        this.toggleButton.setBackgroundColor(Color.rgb(255, 111, 0));
    }

    private boolean isAccessibilityServiceEnabled() {
        try {
            AccessibilityManager am = (AccessibilityManager) getSystemService("accessibility");
            if (am == null) {
                return false;
            }
            List<AccessibilityServiceInfo> services = am.getEnabledAccessibilityServiceList(-1);
            for (AccessibilityServiceInfo info : services) {
                if (info.getResolveInfo() != null && info.getResolveInfo().serviceInfo != null && getPackageName().equals(info.getResolveInfo().serviceInfo.packageName)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void openAccessibilitySettings() {
        try {
            Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
            intent.setFlags(268435456);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "无法打开设置", 0).show();
        }
    }

    private CheckBox addCheckbox(LinearLayout linearLayout, String title, String desc, boolean checked) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        row.setPadding(0, 8, 0, 8);
        row.setGravity(16);
        CheckBox cb = new CheckBox(this);
        cb.setChecked(checked);
        row.addView(cb, new LinearLayout.LayoutParams(-2, -2));
        LinearLayout textCol = new LinearLayout(this);
        textCol.setOrientation(1);
        textCol.setPadding(12, 0, 0, 0);
        TextView tvTitle = new TextView(this);
        tvTitle.setText(title);
        tvTitle.setTextSize(16.0f);
        tvTitle.setTextColor(Color.rgb(51, 51, 51));
        tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        textCol.addView(tvTitle);
        TextView tvDesc = new TextView(this);
        tvDesc.setText(desc);
        tvDesc.setTextSize(12.0f);
        tvDesc.setTextColor(Color.rgb(136, 136, 136));
        textCol.addView(tvDesc);
        row.addView(textCol, new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout.addView(row);
        return cb;
    }

    private View divider() {
        View v = new View(this);
        v.setBackgroundColor(Color.rgb(221, 221, 221));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, 2);
        lp.setMargins(0, 24, 0, 8);
        v.setLayoutParams(lp);
        return v;
    }

    private String joinLines(String[] arr) {
        if (arr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            if (s == null) {
                continue;
            }
            String t = s.trim();
            if (t.isEmpty()) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(t);
        }
        return sb.toString();
    }

    public void saveConfig() {
        try {
            this.config.enableAppend = this.cbAppend.isChecked();
            String append = this.etAppendText.getText().toString().trim();
            this.config.appendText = append.isEmpty() ? "喵" : append;
            this.config.enableRandomEmoticon = this.cbEmoticon.isChecked();
            this.config.globalRewrite = this.cbGlobal.isChecked();
            this.config.enableRuleI = this.cbRuleI.isChecked();
            this.config.enableRuleYou = this.cbRuleYou.isChecked();
            this.config.enableAutoHide = this.cbAutoHide.isChecked();
            this.config.enableVoice = this.cbVoice.isChecked();
            try {
                int delay = Integer.parseInt(this.etVoiceDelay.getText().toString().trim());
                this.config.voiceDelayMs = Math.max(0, Math.min(3000, delay));
            } catch (Exception ignore) {
                // 非法输入保持原值或默认 1500
                this.config.voiceDelayMs = DEFAULT_VOICE_DELAY;
            }
            String mode;
            if (this.rbStreaming.isChecked()) {
                mode = CatConfig.MODE_STREAMING;
            } else if (this.rbRealtime.isChecked()) {
                mode = CatConfig.MODE_REALTIME;
            } else {
                mode = CatConfig.MODE_PUNCTUATION;
            }
            this.config.processingMode = mode;

            ArrayList<CatConfig.Rule> rules = new ArrayList<>();
            String rulesText = this.etRules.getText() == null ? "" : this.etRules.getText().toString();
            for (String line : rulesText.split("\n")) {
                CatConfig.Rule r = CatConfig.parseRule(line);
                if (r != null) {
                    rules.add(r);
                }
            }
            this.config.rules = rules;

            ArrayList<String> list = new ArrayList<>();
            String customText = this.etCustomEmoticons.getText() == null ? "" : this.etCustomEmoticons.getText().toString().trim();
            if (!customText.isEmpty()) {
                for (String raw : customText.split("\n")) {
                    String t = raw.trim();
                    if (!t.isEmpty()) {
                        list.add(t);
                    }
                }
            }
            this.config.customEmoticons = list.toArray(new String[0]);
            this.config.save(this);
            Toast.makeText(this, "设置已保存", 0).show();
        } catch (Exception e) {
            Toast.makeText(this, "保存失败: " + e.getMessage(), 0).show();
        }
    }

    public void showTestDialog() {
        try {
            saveConfig();
            CatConfig testCfg = CatConfig.load(this);
            String sample = "今天我很好，你准备好了吗？我们去公园玩吧";
            String processed = TextProcessor.process(sample, testCfg);
            String msg = "断句追加：" + yn(testCfg.enableAppend) + "（" + (testCfg.appendText == null ? "" : testCfg.appendText) + "）"
                    + "\n句末颜文字：" + yn(testCfg.enableRandomEmoticon)
                    + "\n替换规则：" + testCfg.rules.size() + " 条"
                    + "\n自定义颜文字：" + (testCfg.customEmoticons.length > 0 ? testCfg.customEmoticons.length + "个" : "使用内置")
                    + "\n\n原始：\n" + sample
                    + "\n\n处理后：\n" + processed;
            new AlertDialog.Builder(this).setTitle("预览").setMessage(msg).setPositiveButton("好的", (DialogInterface.OnClickListener) null).show();
        } catch (Exception e) {
            Toast.makeText(this, "测试失败: " + e.getMessage(), 0).show();
        }
    }

    private String yn(boolean b) {
        return b ? "开" : "关";
    }
}