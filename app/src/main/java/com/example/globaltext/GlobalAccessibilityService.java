package com.example.globaltext;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    // 上次实际处理文本事件的应用包名，用于识别输入上下文切换（新应用 → 清理流式删除状态）
    private String lastProcessedPkg = "";
    // 流式模式基线：干净原文（不含追加喵/颜文字）与上次写回结果
    private String streamOriginal = "";
    private String streamLastWritten = "";
    // 流式模式：用户删除过追加串的句子集合（这些句子的文字段不再自动追加，删喵不补回）
    private final Set<String> streamNoAppend = new HashSet<>();
    private static final int MAX_TEXT_LEN = 20000;
    // 与 TextProcessor 一致的分句边界：标点/符号/emoji/空白/组合变音符/ZWJ
    private static final Pattern SENTENCE_SPLIT_PATTERN = Pattern.compile("([\\p{P}\\p{S}\\s\\u200D\\p{M}]+)");
    private final Random streamRandom = new Random();
    // 单线程 worker：仅承载纯字符串变换（TextProcessor），规避“无障碍节点必须主线程访问”的限制
    private final ExecutorService transformWorker = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final AtomicLong writeGen = new AtomicLong(0);
    private Runnable voiceTask;
    private int voiceDelayMs = 1500;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent e) {
        String pkg = e.getPackageName() != null ? e.getPackageName().toString() : "";
        if (pkg.isEmpty()) {
            return;
        }
        boolean isQQ = PKG_QQ.equals(pkg) || PKG_QQI.equals(pkg);
        int type = e.getEventType();

        if (type == 32) {
            // 仅在应用切换（包名变化）时重置处理状态；同包窗口变化（输入法候选窗、窗口重绘等）
            // 保留流式删除状态，避免“删喵后被补回”
            if (!pkg.equals(this.lastProcessedPkg)) {
                resetProcessing();
            }
            return;
        }
        // 记录输入上下文包名：供 type32 判断是否发生应用切换
        this.lastProcessedPkg = pkg;

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
                    doProcess(true, true, null);
                }
                src.recycle();
            }
            return;
        }

        if (type == 16) {
            // 性能优化：事件源快速短路——非可编辑源直接忽略；有内容但无中文的不改写（只处理中文）
            AccessibilityNodeInfo s = e.getSource();
            boolean handoff = false;
            if (s != null) {
                boolean editable = s.isEditable();
                CharSequence st = s.getText();
                boolean noChinese = st != null && st.length() > 0 && !hasChinese(st);
                if (!editable || noChinese) {
                    safeRecycle(s);
                    if (noChinese) {
                        return;
                    }
                    if (!editable) {
                        return;
                    }
                    s = null;
                } else {
                    handoff = true; // 可编辑且有中文 → 作为输入框线索，避免 getRoot+递归遍历
                }
            }
            // 语音模式：流式输入时暂停改写，停顿后再一次性改写，避免打断语音
            if (cfg.enableVoice) {
                safeRecycle(s);
                scheduleVoiceRewrite(isQQ);
                return;
            }
            safeRecycle(s);
            String mode = cfg.processingMode != null ? cfg.processingMode : CatConfig.MODE_PUNCTUATION;
            // 流式/实时：每次输入变化都进入处理（流式内部做增量，实时连续改写）
            if (CatConfig.MODE_STREAMING.equals(mode) || CatConfig.MODE_REALTIME.equals(mode)) {
                doProcess(isQQ, false, null);
                return;
            }
            // 标点触发：仅当最后一段以标点结尾时才处理
            AccessibilityNodeInfo root = getRootInActiveWindow();
            if (root == null) {
                return;
            }
            AccessibilityNodeInfo inp = findInput(root, isQQ);
            if (inp == null) {
                safeRecycle(root);
                return;
            }
            CharSequence cs = inp.getText();
            inp.recycle();
            safeRecycle(root);
            if (cs == null || cs.length() == 0) {
                return;
            }
            String last = lastLineOf(cs.toString());
            if (!last.isEmpty() && isPunctuationEnding(last)) {
                Log.d(TAG, "标点触发: " + last);
                doProcess(isQQ, false, null);
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
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 超长文本直接忽略并释放状态，避免异常存储/内存占用
        if (fullText.length() > MAX_TEXT_LEN) {
            resetSegmentState();
            inp.recycle();
            safeRecycle(root);
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
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 前面行被改（末行未变、整段却变化）→ 不进写回
        if (!this.lastFull.isEmpty() && raw.equals(this.lastSegmentWritten) && !fullText.equals(this.lastFull)) {
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 严格增量：持续输入则把新增字符并入原文；上下文变更（非上次结果扩展）则丢弃旧比对数据后重建
        if (!this.lastFull.isEmpty() && fullText.startsWith(this.lastFull)) {
            this.userOriginal = this.userOriginal + fullText.substring(this.lastFull.length());
        } else if (!this.lastFull.isEmpty()) {
            // 本段已改写，但内容从上次改写结果发生了失配（用户改动了已改写内容）：
            // 无法从“已带追加的文本”安全还原原文，放弃改写本段并重置增量状态，防止二次加工堆叠。
            resetSegmentState();
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        } else {
            // 全新输入（本段尚未改写）：正常剥离重建原文
            this.userOriginal = stripLineAppend(fullText, cfg);
            this.lastFull = "";
            this.lastSegmentWritten = "";
        }
        if (this.userOriginal == null || this.userOriginal.trim().isEmpty()) {
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 纯字符串变换放到单线程 worker，写回仍回主线程（accessibility API 限制），
        // 配代次 + 主线程再核对：处理窗口内有新输入则丢弃本次写回，避免覆盖用户输入。
        final String base = this.userOriginal;
        final CatConfig cc = cfg;
        final long gen = this.writeGen.incrementAndGet();
        this.transformWorker.execute(new Runnable() {
            @Override
            public void run() {
                final String target = TextProcessor.process(base.trim(), cc);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 有新输入触发了新的处理，丢弃本次过期结果
                        if (gen != writeGen.get()) {
                            inp.recycle();
                            safeRecycle(root);
                            processing = false;
                            return;
                        }
                        // 变换期间输入框又变了：不覆盖，交给下一个事件
                        CharSequence cNow = inp.getText();
                        if (cNow != null && !fullText.contentEquals(cNow)) {
                            inp.recycle();
                            safeRecycle(root);
                            processing = false;
                            return;
                        }
                        if (target.equals(raw)) {
                            lastFull = fullText;
                            lastSegmentWritten = raw;
                            inp.recycle();
                            safeRecycle(root);
                            processing = false;
                            return;
                        }
                        boolean ok = setText(inp, prefix + target);
                        if (ok) {
                            lastFull = prefix + target;
                            lastSegmentWritten = target;
                            lastWriteTime = System.currentTimeMillis();
                        }
                        inp.recycle();
                        safeRecycle(root);
                        processing = false;
                    }
                });
            }
        });
    }

    /** 流式替换：只维护“干净原文”基线，识别用户删除追加串则保持删除，其余从纯原文增量重写，避免堆叠 */
    private void handleStreamingInput(AccessibilityNodeInfo inp, AccessibilityNodeInfo root, String prefix, String raw, String fullText, CatConfig cfg) {
        long now = System.currentTimeMillis();
        // 回显去重：与上次写回完全一致
        if (this.lastWriteTime > 0 && now - this.lastWriteTime < 600 && fullText.equals(this.streamLastWritten)) {
            this.lastWriteTime = 0L;
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 超长文本直接忽略并释放状态
        if (fullText.length() > MAX_TEXT_LEN) {
            resetSegmentState();
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 首次进入（或状态被重置）：以当前输入剥离后的纯文本为原文基线，直接写回
        if (this.streamLastWritten.isEmpty()) {
            String original = stripFull(fullText, cfg);
            if (original == null || original.trim().isEmpty()) {
                inp.recycle();
                safeRecycle(root);
                this.processing = false;
                return;
            }
            this.streamOriginal = original;
            streamRewrite(inp, root, fullText, cfg, original);
            return;
        }
        // 删除识别：当前文本与上次写回相比，剥离追加串后完全相同 → 用户删了喵/颜文字，保持删除不补回
        if (!fullText.equals(this.streamLastWritten)) {
            String strippedNow = stripFull(fullText, cfg);
            String strippedLast = stripFull(this.streamLastWritten, cfg);
            if (strippedNow.equals(strippedLast)) {
                Log.d(TAG, "流式: 检测到删除追加串，保持删除");
                this.streamOriginal = strippedNow;
                this.streamLastWritten = fullText;
                // 记录当前文本所有句子的“无追加”状态：这些句子后续不再自动追加，删喵不补回
                recordSuspendedFromText(fullText, cfg);
                inp.recycle();
                safeRecycle(root);
                this.processing = false;
                return;
            }
        }
        // 增量：当前文本是上次写回的扩展 → 仅把新增并入原文基线；否则是改动已改写内容 → 剥离当前文本重建原文
        if (fullText.startsWith(this.streamLastWritten)) {
            this.streamOriginal = this.streamOriginal + fullText.substring(this.streamLastWritten.length());
        } else {
            this.streamOriginal = stripFull(fullText, cfg);
        }
        if (this.streamOriginal == null || this.streamOriginal.trim().isEmpty()) {
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        streamRewrite(inp, root, fullText, cfg, this.streamOriginal);
    }

    /** 在 worker 中变换，回主线程核对后写回；流式模式下复用末尾颜文字避免随机闪烁 */
    private void streamRewrite(final AccessibilityNodeInfo inp, final AccessibilityNodeInfo root, final String fullText, final CatConfig cfg, final String original) {
        final CatConfig cc = cfg;
        final String prevWritten = this.streamLastWritten;
        final long gen = this.writeGen.incrementAndGet();
        this.transformWorker.execute(new Runnable() {
            @Override
            public void run() {
                final String target = rebuildStreaming(original, cc, prevWritten);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (gen != writeGen.get()) {
                            inp.recycle();
                            safeRecycle(root);
                            processing = false;
                            return;
                        }
                        CharSequence cNow = inp.getText();
                        if (cNow != null && !fullText.contentEquals(cNow)) {
                            inp.recycle();
                            safeRecycle(root);
                            processing = false;
                            return;
                        }
                        if (target.equals(fullText)) {
                            streamLastWritten = fullText;
                            streamOriginal = original;
                            inp.recycle();
                            safeRecycle(root);
                            processing = false;
                            return;
                        }
                        boolean ok = setText(inp, target);
                        if (ok) {
                            streamLastWritten = target;
                            streamOriginal = original;
                            lastWriteTime = System.currentTimeMillis();
                        }
                        inp.recycle();
                        safeRecycle(root);
                        processing = false;
                    }
                });
            }
        });
    }

    /** 流式加工：只做规则替换 + 逐句追加（跳过用户删除过追加串的句子）+ 末尾颜文字，不改动用户已确认的句子 */
    private String rebuildStreaming(String original, CatConfig cfg, String prevWritten) {
        // 1) 规则替换（我→本喵、你→主人、自定义），暂不追加、不加颜文字
        CatConfig ruleOnly = new CatConfig();
        ruleOnly.enableAppend = false;
        ruleOnly.enableRandomEmoticon = false;
        ruleOnly.enableRuleI = cfg.enableRuleI;
        ruleOnly.enableRuleYou = cfg.enableRuleYou;
        ruleOnly.rules = cfg.rules;
        ruleOnly.appendText = cfg.appendText;
        ruleOnly.customEmoticons = cfg.customEmoticons;
        String text = TextProcessor.process(original, ruleOnly);
        // 2) 逐句追加，跳过用户删除过追加串的句子（删喵不补回）
        text = appendStreamingSkipSuspended(text, cfg);
        // 3) 末尾颜文字（复用上次末尾的，避免写回时随机跳变）
        if (cfg.enableRandomEmoticon) {
            String emote = extractTrailingEmoticon(prevWritten, cfg);
            if (emote == null) {
                String[] emotes = cfg.getActiveEmoticons();
                if (emotes == null || emotes.length == 0) {
                    emotes = CatConfig.BUILTIN_EMOTICONS;
                }
                emote = emotes.length == 0 ? "" : emotes[this.streamRandom.nextInt(emotes.length)];
            }
            if (!emote.isEmpty()) {
                text = text + " " + emote;
            }
        }
        return text;
    }

    /** 逐句追加，但对用户删除过追加串的句子跳过（保持其无追加状态） */
    private String appendStreamingSkipSuspended(String text, CatConfig cfg) {
        String app = (cfg.appendText == null) ? "" : cfg.appendText;
        if (app.isEmpty() || !cfg.enableAppend) {
            return text;
        }
        Matcher matcher = SENTENCE_SPLIT_PATTERN.matcher(text);
        StringBuilder result = new StringBuilder();
        boolean sawText = false;
        int pos = 0;
        while (matcher.find()) {
            String chunk = text.substring(pos, matcher.start());
            result.append(chunk);
            if (!chunk.trim().isEmpty() && !chunk.trim().endsWith(app) && !isSuspended(chunk, cfg)) {
                result.append(app);
                sawText = true;
            }
            result.append(matcher.group(1));
            pos = matcher.end();
        }
        if (pos < text.length()) {
            String chunk = text.substring(pos);
            result.append(chunk);
            if (!chunk.trim().isEmpty() && !chunk.trim().endsWith(app) && !isSuspended(chunk, cfg)) {
                result.append(app);
                sawText = true;
            }
        }
        return sawText ? result.toString().trim() : text.trim();
    }

    /** 判断文字片段是否处于“用户已删除追加串”状态：等于或延展了某条已删除记录 */
    private boolean isSuspended(String chunk, CatConfig cfg) {
        String app = (cfg.appendText == null) ? "" : cfg.appendText;
        String base = chunk.trim();
        if (!app.isEmpty()) {
            while (base.endsWith(app)) {
                base = base.substring(0, base.length() - app.length());
            }
        }
        if (base.isEmpty()) {
            return false;
        }
        for (String t : this.streamNoAppend) {
            if (t == null || t.isEmpty()) {
                continue;
            }
            if (base.equals(t)) {
                return true;
            }
            // 用户在已删喵句子上继续输入（“你好”→“你好呀”），仍视为该句不追加；至少 2 字才前缀匹配，避免单字误伤
            if (t.length() >= 2 && base.startsWith(t)) {
                return true;
            }
        }
        return false;
    }

    /** 记录文本中每个句子的“无追加”状态（用户删除追加串后调用） */
    private void recordSuspendedFromText(String full, CatConfig cfg) {
        String app = (cfg.appendText == null) ? "" : cfg.appendText;
        Matcher matcher = SENTENCE_SPLIT_PATTERN.matcher(full);
        int pos = 0;
        while (matcher.find()) {
            addSuspended(stripSegmentTail(full.substring(pos, matcher.start()), app));
            pos = matcher.end();
        }
        if (pos < full.length()) {
            addSuspended(stripSegmentTail(full.substring(pos), app));
        }
    }

    /** 加入一条“无追加”句子；保留更精确（更长）的项，避免集合无限膨胀 */
    private void addSuspended(String base) {
        String b = base.trim();
        if (b.isEmpty()) {
            return;
        }
        this.streamNoAppend.removeIf(t -> t != null && !t.isEmpty() && b.startsWith(t) && t.length() < b.length());
        for (String t : this.streamNoAppend) {
            if (t != null && t.startsWith(b) && t.length() > b.length()) {
                return; // 已有更精确超集
            }
        }
        this.streamNoAppend.add(b);
    }

    /** 取上次写回结果末尾的活跃颜文字，无则返回 null */
    private static String extractTrailingEmoticon(String text, CatConfig cfg) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        String[] emotes = cfg.getActiveEmoticons();
        if (emotes == null || emotes.length == 0) {
            emotes = CatConfig.BUILTIN_EMOTICONS;
        }
        for (String em : emotes) {
            if (em != null && !em.isEmpty() && text.endsWith(em)) {
                return em;
            }
        }
        return null;
    }

    /** 从整段文本可靠还原原文：先逐句剥离末尾追加串，再剥离颜文字/符号残留 */
    private String stripFull(String full, CatConfig cfg) {
        if (full == null || full.isEmpty()) {
            return "";
        }
        return stripAll(stripAppendPerSentence(full, cfg), cfg).trim();
    }

    /** 逐句剥离每个文字片段末尾的追加串（如“喵”）及紧邻空格 */
    private static String stripAppendPerSentence(String full, CatConfig cfg) {
        String app = (cfg.appendText == null) ? "" : cfg.appendText;
        Matcher matcher = SENTENCE_SPLIT_PATTERN.matcher(full);
        StringBuilder out = new StringBuilder();
        int pos = 0;
        while (matcher.find()) {
            String chunk = full.substring(pos, matcher.start());
            out.append(stripSegmentTail(chunk, app));
            out.append(matcher.group(1));
            pos = matcher.end();
        }
        if (pos < full.length()) {
            out.append(stripSegmentTail(full.substring(pos), app));
        }
        return out.toString();
    }

    /** 剥离一个文字片段末尾的追加串及其前面的空格 */
    private static String stripSegmentTail(String chunk, String app) {
        String s = chunk;
        if (!app.isEmpty()) {
            while (s.endsWith(app)) {
                s = s.substring(0, s.length() - app.length());
            }
        }
        // 追加串后可能跟空格（如“ 喵 ”），一并剥掉结尾空白
        int end = s.length();
        while (end > 0 && Character.isWhitespace(s.charAt(end - 1))) {
            end--;
        }
        return s.substring(0, end);
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
        this.streamOriginal = "";
        this.streamLastWritten = "";
        this.streamNoAppend.clear();
    }

    private void resetProcessing() {
        this.processing = false;
        this.writeGen.incrementAndGet(); // 使在途 worker 写回作废
        synchronized (this) {
            if (this.voiceTask != null) {
                this.mainHandler.removeCallbacks(this.voiceTask);
                this.voiceTask = null;
            }
        }
        this.userOriginal = "";
        this.lastSet = "";
        this.lastWriteTime = 0L;
        this.lastFull = "";
        this.lastSegmentWritten = "";
        this.streamOriginal = "";
        this.streamLastWritten = "";
        this.streamNoAppend.clear();
        this.cachedConfig = CatConfig.load(this);
    }

    /** 语音模式：每次文本变更都重排“停顿改写”，流式吐字期间持续被重置而不会改写，停顿延时后才一次性改写 */
    private void scheduleVoiceRewrite(boolean isQQ) {
        long delay = this.voiceDelayMs;
        CatConfig cfg = this.cachedConfig;
        if (cfg != null) {
            delay = cfg.voiceDelayMs;
        }
        if (delay < 0) {
            delay = 0;
        }
        if (delay > 3000) {
            delay = 3000;
        }
        synchronized (this) {
            if (this.voiceTask != null) {
                this.mainHandler.removeCallbacks(this.voiceTask);
            }
            this.voiceTask = new Runnable() {
                @Override
                public void run() {
                    synchronized (GlobalAccessibilityService.this) {
                        GlobalAccessibilityService.this.voiceTask = null;
                    }
                    doProcess(isQQ, false, null);
                }
            };
            this.mainHandler.postDelayed(this.voiceTask, delay);
        }
    }

    /** 空安全的节点回收 */
    private static void safeRecycle(AccessibilityNodeInfo n) {
        if (n != null) {
            n.recycle();
        }
    }

    /** 若含任一汉字则返回 true；只处理中文 */
    private static boolean hasChinese(CharSequence s) {
        if (s == null) {
            return false;
        }
        String str = s.toString();
        int i = 0;
        int len = str.length();
        while (i < len) {
            int cp = str.codePointAt(i);
            if (Character.UnicodeScript.of(cp) == Character.UnicodeScript.HAN) {
                return true;
            }
            i += Character.charCount(cp);
        }
        return false;
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

    private void doProcess(boolean isQQ, boolean isSendClick, AccessibilityNodeInfo hint) {
        if (this.processing) {
            return;
        }
        this.processing = true;
        AccessibilityNodeInfo root = null;
        AccessibilityNodeInfo inp = hint; // 事件源线索，命中则避免 getRoot+递归遍历
        if (inp == null) {
            root = getRootInActiveWindow();
            if (root == null) {
                this.processing = false;
                return;
            }
            inp = findInput(root, isQQ);
            if (inp == null) {
                safeRecycle(root);
                this.processing = false;
                return;
            }
        }
        CharSequence cs = inp.getText();
        CharSequence hintText = inp.getHintText();
        // 占位提示文本也会被 getText() 返回：text 与 hint 相同 → 输入框实际为空，
        // 视为空输入，不改写也不写回，避免把提示文本填成内容再追加喵（通用修复，避免污染改写状态）。
        boolean emptyByHint = hintText != null && hintText.length() > 0 && cs != null && hintText.toString().equals(cs.toString());
        if (cs == null || cs.length() == 0 || emptyByHint) {
            resetSegmentState();
            this.lastSet = "";
            this.lastWriteTime = 0L;
            inp.recycle();
            safeRecycle(root);
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
            safeRecycle(root);
            this.processing = false;
            return;
        }
        // 只处理中文：当前行无中文字符则不改写并释放状态
        if (!hasChinese(raw)) {
            resetSegmentState();
            this.lastSet = "";
            this.lastWriteTime = 0L;
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        CatConfig cfg = this.cachedConfig;
        if (cfg == null) {
            cfg = CatConfig.load(this);
            this.cachedConfig = cfg;
        }
        // 流式模式：只对新增/修改的片段加喵，已改写且未被用户改动的部分原样保留，
        // 用户删掉追加串则保持删除（不补回），避免整体重转带来的堆叠。
        boolean isStreaming = CatConfig.MODE_STREAMING.equals(cfg.processingMode);
        if (isStreaming && !isSendClick) {
            handleStreamingInput(inp, root, prefix, raw, fullText, cfg);
            return;
        }
        // 实时模式：每输入一字立即连续改写当前段（遇标点/空格/换行/emoji 结算）
        boolean isRealtime = CatConfig.MODE_REALTIME.equals(cfg.processingMode);
        if (isRealtime && !isSendClick) {
            handleContinuousInput(inp, root, prefix, raw, fullText, cfg);
            return;
        }
        // 标点模式：仅在标点事件触发时到达这里，按“整体替换+删除保持”处理
        // 发送兜底：当前行未以标点结尾 → 标点模式不改写，原样发送，避免“不打标点也追加喵”
        if (isSendClick && !isPunctuationEnding(raw)) {
            resetSegmentState();
            this.lastSet = "";
            this.lastWriteTime = 0L;
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        if (!this.lastSet.isEmpty() && !raw.startsWith(this.lastSet) && !this.lastSet.startsWith(raw)) {
            resetSegmentState();
            this.lastSet = "";
            this.lastWriteTime = 0L;
            inp.recycle();
            safeRecycle(root);
            this.processing = false;
            return;
        }
        long now = System.currentTimeMillis();
        long j = this.lastWriteTime;
        if (j > 0 && now - j < 600 && raw.equals(this.lastSet)) {
            Log.d(TAG, "写入回显跳过");
            this.lastWriteTime = 0L;
            inp.recycle();
            safeRecycle(root);
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
            safeRecycle(root);
            this.processing = false;
            return;
        }
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
            safeRecycle(root);
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
            safeRecycle(root);
            this.processing = false;
            return;
        }
        this.lastSet = target;
        inp.recycle();
        safeRecycle(root);
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