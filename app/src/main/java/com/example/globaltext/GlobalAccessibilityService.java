package com.example.globaltext;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Arrays;
import java.util.Comparator;

public class GlobalAccessibilityService extends AccessibilityService {
    private static final String ID_INPUT = "com.tencent.mobileqq:id/input";
    private static final String ID_SEND = "com.tencent.mobileqq:id/send_btn";
    private static final String PKG_QQ = "com.tencent.mobileqq";
    private static final String PKG_QQI = "com.tencent.mobileqqi";
    private static final String TAG = "GlobalCatSvc";
    private CatConfig cachedConfig;
    private String userOriginal = "";
    private String lastSet = "";
    private boolean processing = false;
    private long lastWriteTime = 0;
    private String lastFull = "";
    private String lastSegmentWritten = "";
    private static final int MAX_TEXT_LEN = 20000;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent e) {
        String pkg = e.getPackageName() != null ? e.getPackageName().toString() : "";
        if (pkg.isEmpty()) {
            return;
        }
        boolean isQQ = PKG_QQ.equals(pkg) || PKG_QQI.equals(pkg);
        int type = e.getEventType();

        if (type == 32) {
            resetProcessing();
            return;
        }

        CatConfig cfg = this.cachedConfig;
        if (cfg == null) {
            cfg = CatConfig.load(this);
            this.cachedConfig = cfg;
        }

        if (!isQQ && !cfg.globalRewrite) {
            // 全局改写关闭时，仅处理 QQ
            return;
        }

        if (type == 1) {
            AccessibilityNodeInfo src = e.getSource();
            if (src != null) {
                if (isQQ && ID_SEND.equals(src.getViewIdResourceName())) {
                    Log.d(TAG, "点击发送，兜底处理");
                    doProcess(true, true);
                }
                src.recycle();
            }
            return;
        }

        if (type == 16) {
            // 连续输入开启时：任意文本变更都实时触发（不再等待末行标点）
            if (cfg.enableContinuous) {
                doProcess(isQQ, false);
                return;
            }
            String mode = cfg.processingMode != null ? cfg.processingMode : CatConfig.MODE_PUNCTUATION;
            if (CatConfig.MODE_REALTIME.equals(mode)) {
                doProcess(isQQ, false);
                return;
            }
            AccessibilityNodeInfo root = getRootInActiveWindow();
            if (root == null) {
                return;
            }
            AccessibilityNodeInfo inp = findInput(root, isQQ);
            if (inp == null) {
                root.recycle();
                return;
            }
            CharSequence cs = inp.getText();
            inp.recycle();
            root.recycle();
            if (cs == null || cs.length() == 0) {
                return;
            }
            String last = lastLineOf(cs.toString());
            if (!last.isEmpty() && isPunctuationEnding(last)) {
                Log.d(TAG, "标点触发: " + last);
                doProcess(isQQ, false);
            }
        }
    }

    /** 连续输入处理：只改写当前（最后）段，遇标点/空格/换行/emoji 由追加逻辑结算；无法定位或前面行被改则不处理 */
    private void handleContinuousInput(AccessibilityNodeInfo inp, AccessibilityNodeInfo root, String prefix, String raw, String fullText, CatConfig cfg) {
        long now = System.currentTimeMillis();
        // 回显去重：与上次写回完全一致
        if (this.lastWriteTime > 0 && now - this.lastWriteTime < 600 && fullText.equals(this.lastFull)) {
            this.lastWriteTime = 0L;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        // 超长文本直接忽略并释放状态，避免异常存储/内存占用
        if (fullText.length() > MAX_TEXT_LEN) {
            resetSegmentState();
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        // 删除优化：末行只是上次结果的严格前缀（用户删了末尾追加内容）→ 接受删除，不补回
        // 增加前缀一致性守卫：必须是 从上次写回结果 减掉一个后缀（lastFull.startsWith(fullText)），
        // 避免过时(跨会话遗留)比对数据在无关/全新内容上误触发。
        boolean fullIsPrefixOfLast = !this.lastFull.isEmpty() && this.lastFull.startsWith(fullText);
        if (!this.lastSegmentWritten.isEmpty() && this.lastSegmentWritten.startsWith(raw) && !raw.equals(this.lastSegmentWritten) && fullIsPrefixOfLast) {
            this.userOriginal = stripLineAppend(raw, cfg);
            this.lastSegmentWritten = raw;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        // 前面行被改（末行未变、整段却变化）→ 不进写回
        if (!this.lastFull.isEmpty() && raw.equals(this.lastSegmentWritten) && !fullText.equals(this.lastFull)) {
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        // 严格增量：持续输入则把新增字符并入原文；上下文变更（非上次结果扩展）则丢弃旧比对数据后重建
        if (!this.lastFull.isEmpty() && fullText.startsWith(this.lastFull)) {
            this.userOriginal = this.userOriginal + fullText.substring(this.lastFull.length());
        } else {
            this.userOriginal = stripLineAppend(fullText, cfg);
            this.lastFull = "";
            this.lastSegmentWritten = "";
        }
        if (this.userOriginal == null || this.userOriginal.trim().isEmpty()) {
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        String target = TextProcessor.process(this.userOriginal.trim(), cfg);
        if (target.equals(raw)) {
            this.lastFull = fullText;
            this.lastSegmentWritten = raw;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        boolean ok = setText(inp, prefix + target);
        if (ok) {
            this.lastFull = prefix + target;
            this.lastSegmentWritten = target;
            this.lastWriteTime = System.currentTimeMillis();
        }
        inp.recycle();
        root.recycle();
        this.processing = false;
    }

    /** 从整段中剥离上一轮追加的句末文本与颜文字，得到干净原文 */
    private String stripLineAppend(String full, CatConfig cfg) {
        String seg = (full == null) ? "" : full;
        String app = (cfg.appendText == null) ? "" : cfg.appendText;
        if (!app.isEmpty() && seg.endsWith(app)) {
            seg = seg.substring(0, seg.length() - app.length());
        }
        return stripAll(seg, cfg).trim();
    }

    /** 释放连续输入状态，避免长期保留大文本 */
    private void resetSegmentState() {
        this.userOriginal = "";
        this.lastFull = "";
        this.lastSegmentWritten = "";
    }

    private void resetProcessing() {
        this.processing = false;
        this.userOriginal = "";
        this.lastSet = "";
        this.lastWriteTime = 0L;
        this.lastFull = "";
        this.lastSegmentWritten = "";
        this.cachedConfig = CatConfig.load(this);
    }

    private boolean isPunctuationEnding(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        char last = s.charAt(s.length() - 1);
        return last == 12290 || last == 65281 || last == '!' || last == 65311 || last == '?' || last == ' ';
    }

    /** 取最后一行（去掉行尾空白），用于多行时只改写当前行 */
    private static String lastLineOf(String full) {
        if (full == null || full.isEmpty()) {
            return "";
        }
        int nl = full.lastIndexOf('\n');
        return (nl >= 0 ? full.substring(nl + 1) : full).trim();
    }

    private void doProcess(boolean isQQ, boolean isSendClick) {
        if (this.processing) {
            return;
        }
        this.processing = true;
        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) {
            this.processing = false;
            return;
        }
        AccessibilityNodeInfo inp = findInput(root, isQQ);
        if (inp == null) {
            root.recycle();
            this.processing = false;
            return;
        }
        CharSequence cs = inp.getText();
        if (cs == null || cs.length() == 0) {
            resetSegmentState();
            this.lastSet = "";
            this.lastWriteTime = 0L;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        String fullText = cs.toString();
        // 多行时只改写当前（最后）一行，前面行原样保留
        String prefix = "";
        String raw = fullText.trim();
        int nl = fullText.lastIndexOf('\n');
        if (nl >= 0) {
            prefix = fullText.substring(0, nl + 1);
            raw = fullText.substring(nl + 1).trim();
        }
        if (raw.isEmpty()) {
            resetSegmentState();
            this.lastSet = "";
            this.lastWriteTime = 0L;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        CatConfig cfg = this.cachedConfig;
        if (cfg == null) {
            cfg = CatConfig.load(this);
            this.cachedConfig = cfg;
        }
        // 连续输入（独立开关，默认开启，优先于标点/实时 radio）
        if (cfg.enableContinuous && !isSendClick) {
            handleContinuousInput(inp, root, prefix, raw, fullText, cfg);
            return;
        }
        long now = System.currentTimeMillis();
        long j = this.lastWriteTime;
        if (j > 0 && now - j < 600 && raw.equals(this.lastSet)) {
            Log.d(TAG, "写入回显跳过");
            this.lastWriteTime = 0L;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        // 删除优化：若当前文本是上次改写结果的严格前缀（用户删除了末尾追加的“喵”/颜文字等改写产物），
        // 则接受删除、不再自动改写补回，直到下一次产生新的文本变动。
        if (!this.lastSet.isEmpty() && this.lastSet.startsWith(raw)) {
            Log.d(TAG, "后缀删除保持: lastSet=" + this.lastSet + " raw=" + raw);
            this.userOriginal = stripAll(raw, cfg);
            this.lastSet = raw;
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        boolean isRealtime = CatConfig.MODE_REALTIME.equals(cfg.processingMode);
        if (!isRealtime && this.lastSet.isEmpty()) {
            this.userOriginal = stripAll(raw, cfg);
            Log.d(TAG, "标点首次剥离: " + this.userOriginal);
        } else if (this.lastSet.isEmpty() || !raw.startsWith(this.lastSet)) {
            this.userOriginal = stripAll(raw, cfg);
            Log.d(TAG, "不匹配剥离: " + this.userOriginal);
        } else {
            String added = raw.substring(this.lastSet.length());
            this.userOriginal += added;
            Log.d(TAG, "前缀增量: +" + added + "  userOriginal=" + this.userOriginal);
        }
        if (this.userOriginal.isEmpty()) {
            Log.d(TAG, "原文为空，跳过");
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        CatConfig effectiveCfg = cfg;
        if (isRealtime && cfg.enableRandomEmoticon && !isSendClick) {
            effectiveCfg = cloneConfigWithoutEmoticon(cfg);
        }
        String target = TextProcessor.process(this.userOriginal, effectiveCfg);
        if (!target.equals(raw)) {
            Log.d(TAG, "写入: raw=" + raw + "  userOriginal=" + this.userOriginal + "  target=" + target);
            boolean ok = setText(inp, prefix + target);
            if (ok) {
                this.lastSet = target;
                this.lastWriteTime = System.currentTimeMillis();
            }
            inp.recycle();
            root.recycle();
            this.processing = false;
            return;
        }
        this.lastSet = target;
        inp.recycle();
        root.recycle();
        this.processing = false;
    }

    /**
     * 定位要处理的输入框。
     * QQ 保留专用控件 id 定位；其它应用优先取活动窗口内获得输入焦点的可编辑节点，
     * 找不到则退回遍历查找第一个可编辑节点。
     */
    private AccessibilityNodeInfo findInput(AccessibilityNodeInfo root, boolean isQQ) {
        if (isQQ) {
            AccessibilityNodeInfo inp = findNodeById(root, ID_INPUT);
            if (inp == null) {
                inp = findEditable(root);
            }
            return inp;
        }
        AccessibilityNodeInfo focused = root.findFocus(AccessibilityNodeInfo.FOCUS_INPUT);
        if (focused != null) {
            if (focused.isEditable()) {
                return focused;
            }
            focused.recycle();
        }
        return findEditable(root);
    }

    private CatConfig cloneConfigWithoutEmoticon(CatConfig src) {
        CatConfig c = new CatConfig();
        c.enableAppend = src.enableAppend;
        c.appendText = src.appendText;
        c.enableRandomEmoticon = false;
        c.processingMode = src.processingMode;
        c.customEmoticons = src.customEmoticons;
        c.rules = src.rules;
        return c;
    }

    private String stripAll(String text, CatConfig cfg) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String result = text;
        String[] emotes = cfg.getActiveEmoticons();
        if (emotes.length == 0) {
            emotes = CatConfig.BUILTIN_EMOTICONS;
        }
        Arrays.sort(emotes, new Comparator() {
            @Override
            public int compare(Object obj, Object obj2) {
                return GlobalAccessibilityService.lambda$stripAll$0((String) obj, (String) obj2);
            }
        });
        for (String em : emotes) {
            if (em == null || em.isEmpty()) {
                continue;
            }
            int idx;
            while ((idx = result.indexOf(em)) >= 0) {
                int st;
                if (idx <= 0 || result.charAt(idx - 1) != ' ') {
                    st = idx;
                } else {
                    st = idx - 1;
                }
                result = result.substring(0, st) + result.substring(idx + em.length());
            }
        }
        return result.replaceAll("\\s*[\\p{S}\\p{So}\\p{Sm}\\p{Sk}\\p{P}]{3,}\\s*", " ").trim();
    }

    static int lambda$stripAll$0(String a, String b) {
        return b.length() - a.length();
    }

    private AccessibilityNodeInfo findNodeById(AccessibilityNodeInfo n, String id) {
        if (n == null || id == null) {
            return null;
        }
        if (id.equals(n.getViewIdResourceName())) {
            return AccessibilityNodeInfo.obtain(n);
        }
        for (int i = 0; i < n.getChildCount(); i++) {
            AccessibilityNodeInfo c = n.getChild(i);
            if (c != null) {
                AccessibilityNodeInfo r = findNodeById(c, id);
                c.recycle();
                if (r != null) {
                    return r;
                }
            }
        }
        return null;
    }

    private AccessibilityNodeInfo findEditable(AccessibilityNodeInfo n) {
        if (n == null) {
            return null;
        }
        if (n.isEditable()) {
            return AccessibilityNodeInfo.obtain(n);
        }
        for (int i = 0; i < n.getChildCount(); i++) {
            AccessibilityNodeInfo c = n.getChild(i);
            if (c != null) {
                AccessibilityNodeInfo r = findEditable(c);
                c.recycle();
                if (r != null) {
                    return r;
                }
            }
        }
        return null;
    }

    private boolean setText(AccessibilityNodeInfo n, String t) {
        if (n == null) {
            return false;
        }
        try {
            Bundle b = new Bundle();
            b.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", t);
            boolean ok = n.performAction(2097152, b);
            if (ok) {
                Bundle a = new Bundle();
                a.putInt("ACTION_ARGUMENT_SELECTION_START_INT", t.length());
                a.putInt("ACTION_ARGUMENT_SELECTION_END_INT", t.length());
                n.performAction(131072, a);
            }
            return ok;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onInterrupt() {
        this.processing = false;
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        AccessibilityServiceInfo i = new AccessibilityServiceInfo();
        i.eventTypes = 49;
        i.feedbackType = 16;
        i.flags = 81;
        i.notificationTimeout = 50L;
        i.packageNames = null;
        setServiceInfo(i);
        this.cachedConfig = CatConfig.load(this);
    }
}