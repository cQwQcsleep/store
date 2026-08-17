package io.github.rosemoe.sora.widget.snippet;

import io.github.rosemoe.sora.lang.completion.snippet.ConditionalFormat;
import io.github.rosemoe.sora.lang.completion.snippet.FormatString;
import io.github.rosemoe.sora.lang.completion.snippet.NextUpperCaseFormat;
import io.github.rosemoe.sora.lang.completion.snippet.NoFormat;
import io.github.rosemoe.sora.lang.completion.snippet.Transform;
import io.github.rosemoe.sora.util.MyCharacter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TransformApplier {
    private static String applyFirstUpperCase(String str, boolean z) {
        if (!z || str == null || str.length() <= 0 || !MyCharacter.isAlpha(str.charAt(0))) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private static CharSequence applySingle(Matcher matcher, List<FormatString> list) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (FormatString formatString : list) {
            if (formatString instanceof NoFormat) {
                sb.append(applyFirstUpperCase(((NoFormat) formatString).getText(), z));
            } else if (formatString instanceof ConditionalFormat) {
                ConditionalFormat conditionalFormat = (ConditionalFormat) formatString;
                String strGroup = matcher.group(conditionalFormat.getGroup());
                if (conditionalFormat.getShorthand() == null) {
                    String ifValue = conditionalFormat.getIfValue() != null ? conditionalFormat.getIfValue() : strGroup;
                    String elseValue = conditionalFormat.getElseValue() != null ? conditionalFormat.getElseValue() : "";
                    if (strGroup == null) {
                        ifValue = elseValue;
                    }
                    sb.append(applyFirstUpperCase(ifValue, z));
                } else if (strGroup != null) {
                    String shorthand = conditionalFormat.getShorthand();
                    shorthand.getClass();
                    if (shorthand.equals("upcase")) {
                        sb.append(applyFirstUpperCase(strGroup.toUpperCase(Locale.ROOT), z));
                    } else if (shorthand.equals("lowcase")) {
                        sb.append(applyFirstUpperCase(strGroup.toLowerCase(Locale.ROOT), z));
                    } else {
                        sb.append(applyFirstUpperCase(strGroup, z));
                    }
                }
            }
            z = formatString instanceof NextUpperCaseFormat;
        }
        return sb;
    }

    public static String doTransform(String str, Transform transform) {
        if (transform == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = transform.regexp.matcher(str);
        int i = transform.globalMode ? Integer.MAX_VALUE : 1;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i && i3 < str.length() && matcher.find(i3)) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            sb.append((CharSequence) str, i3, iStart);
            sb.append(applySingle(matcher, transform.format));
            i2++;
            i3 = iEnd;
        }
        if (i3 < str.length()) {
            sb.append((CharSequence) str, i3, str.length());
        }
        return sb.toString();
    }
}
