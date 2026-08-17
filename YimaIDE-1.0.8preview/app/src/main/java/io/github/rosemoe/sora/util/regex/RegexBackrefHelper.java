package io.github.rosemoe.sora.util.regex;

import java.util.List;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RegexBackrefHelper {
    public static String computeReplacement(Matcher matcher, List<RegexBackrefToken> list) {
        StringBuilder sb = new StringBuilder();
        for (RegexBackrefToken regexBackrefToken : list) {
            if (regexBackrefToken.isReference()) {
                String strGroup = matcher.group(regexBackrefToken.getGroup());
                if (strGroup == null) {
                    strGroup = "";
                }
                sb.append(strGroup);
            } else {
                sb.append(regexBackrefToken.getText());
            }
        }
        return sb.toString();
    }

    public static String computeReplacement(Matcher matcher, RegexBackrefGrammar regexBackrefGrammar, String str) {
        return computeReplacement(matcher, new RegexBackrefParser(regexBackrefGrammar).parse(str, matcher.groupCount()));
    }
}
