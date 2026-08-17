package io.github.rosemoe.sora.langs.textmate.utils;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class MatcherUtils {
    public static String replaceAll(CharSequence charSequence, Matcher matcher, Function<MatchResult, String> function) {
        matcher.reset();
        StringBuilder sb = new StringBuilder();
        int iEnd = 0;
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            String strApply = function.apply(matchResult);
            sb.append(charSequence, iEnd, matchResult.start());
            sb.append(strApply);
            iEnd = matchResult.end();
        }
        if (sb.length() == 0) {
            return charSequence.toString();
        }
        sb.append(charSequence, iEnd, charSequence.length());
        return sb.toString();
    }
}
