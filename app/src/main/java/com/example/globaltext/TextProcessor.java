package com.example.globaltext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    private static final Random RANDOM = new Random();
    // 分隔符：标点 \p{P}、符号/emoji \p{S}、空白 \s、组合用变音符 \p{M} 与 ZWJ（\u200D）
    // “喵”只追加到真正的文字片段后，标点/符号/emoji/空格原样保留并作为分句边界。
    private static final Pattern SENTENCE_SPLIT_PATTERN = Pattern.compile("([\\p{P}\\p{S}\\s\\u200D\\p{M}]+)");

    public static String process(String original, CatConfig config) {
        if (original == null || original.trim().isEmpty()) {
            return original;
        }
        String text = original.trim();

        // 内置替换开关（默认开启）：我→本喵、你→主人，先于自定义规则应用
        if (config.enableRuleI) {
            text = text.replace("我", "本喵");
        }
        if (config.enableRuleYou) {
            text = text.replace("你", "主人");
        }

        if (config.rules != null) {
            for (CatConfig.Rule rule : config.rules) {
                if (rule == null || rule.from.isEmpty()) {
                    continue;
                }
                text = text.replace(rule.from, rule.to);
            }
        }

        if (config.enableAppend) {
            text = appendPerSentence(text, config.appendText);
        }

        if (config.enableRandomEmoticon) {
            String emoticon = getRandomEmoticon(config);
            if (emoticon != null && !emoticon.isEmpty()) {
                text = text + " " + emoticon;
            }
        }
        return text;
    }

    private static String appendPerSentence(String text, String suffix) {
        String s = (suffix == null) ? "" : suffix;
        if (s.isEmpty()) {
            return text;
        }
        Matcher matcher = SENTENCE_SPLIT_PATTERN.matcher(text);
        StringBuilder result = new StringBuilder();
        boolean sawText = false;
        int pos = 0;
        while (matcher.find()) {
            String chunk = text.substring(pos, matcher.start());
            result.append(chunk);
            if (!chunk.trim().isEmpty() && !chunk.trim().endsWith(s)) {
                result.append(s);
                sawText = true;
            }
            result.append(matcher.group(1));
            pos = matcher.end();
        }
        if (pos < text.length()) {
            String chunk = text.substring(pos);
            result.append(chunk);
            if (!chunk.trim().isEmpty() && !chunk.trim().endsWith(s)) {
                result.append(s);
                sawText = true;
            }
        }
        return sawText ? result.toString().trim() : text.trim();
    }

    private static String getRandomEmoticon(CatConfig config) {
        String[] emoticons = config.getActiveEmoticons();
        if (emoticons == null || emoticons.length == 0) {
            emoticons = CatConfig.BUILTIN_EMOTICONS;
        }
        return emoticons.length == 0 ? "" : emoticons[RANDOM.nextInt(emoticons.length)];
    }

    public static String process(String original) {
        CatConfig defaults = new CatConfig();
        defaults.enableAppend = true;
        defaults.appendText = "喵";
        defaults.enableRandomEmoticon = true;
        defaults.customEmoticons = new String[0];
        defaults.rules = new ArrayList<>();
        return process(original, defaults);
    }
}