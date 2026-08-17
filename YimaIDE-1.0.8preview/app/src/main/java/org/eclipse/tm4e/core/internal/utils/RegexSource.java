package org.eclipse.tm4e.core.internal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.eclipse.tm4e.core.internal.oniguruma.OnigCaptureIndex;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class RegexSource {
    private static final Pattern CAPTURING_REGEX_SOURCE = Pattern.compile("\\$(\\d+)|\\$\\{(\\d+):/(downcase|upcase)\\}");

    private RegexSource() {
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026 A[FALL_THROUGH] */
    /* JADX WARN: Switch 'out' block B:14:0x0026 for B:11:0x001f already processed. Defaulting to fallback option. */
    public static String escapeRegExpCharacters(CharSequence charSequence) {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt != '#' && cCharAt != '$' && cCharAt != '?') {
                switch (cCharAt) {
                    default:
                        switch (cCharAt) {
                            default:
                                switch (cCharAt) {
                                    case TerminalTokens.TokenNameELLIPSIS /* 123 */:
                                    case TerminalTokens.TokenNameelse /* 124 */:
                                    case TerminalTokens.TokenNamerequires /* 125 */:
                                        break;
                                    default:
                                        continue;
                                }
                            case '[':
                            case '\\':
                            case ']':
                            case '^':
                                sb.append('\\');
                                break;
                        }
                    case '(':
                    case ')':
                    case '*':
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                        sb.append('\\');
                        break;
                }
            } else {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static String getReplacement(String str, CharSequence charSequence, OnigCaptureIndex[] onigCaptureIndexArr) {
        int i;
        String strSubstring;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf != -1) {
            i = Integer.parseInt(str.substring(2, iIndexOf));
            strSubstring = str.substring(iIndexOf + 2, str.length() - 1);
        } else {
            i = Integer.parseInt(str.substring(1));
            strSubstring = null;
        }
        OnigCaptureIndex onigCaptureIndex = onigCaptureIndexArr.length > i ? onigCaptureIndexArr[i] : null;
        if (onigCaptureIndex == null) {
            return str;
        }
        CharSequence charSequenceSubSequence = charSequence.subSequence(onigCaptureIndex.start, onigCaptureIndex.end);
        while (charSequenceSubSequence.length() >= 1 && charSequenceSubSequence.charAt(0) == '.') {
            charSequenceSubSequence = charSequenceSubSequence.subSequence(1, charSequenceSubSequence.length());
        }
        if ("downcase".equals(strSubstring)) {
            return charSequenceSubSequence.toString().toLowerCase();
        }
        return "upcase".equals(strSubstring) ? charSequenceSubSequence.toString().toUpperCase() : charSequenceSubSequence.toString();
    }

    public static boolean hasCaptures(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        return CAPTURING_REGEX_SOURCE.matcher(charSequence).find();
    }

    public static String replaceCaptures(CharSequence charSequence, CharSequence charSequence2, OnigCaptureIndex[] onigCaptureIndexArr) {
        Matcher matcher = CAPTURING_REGEX_SOURCE.matcher(charSequence);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, getReplacement(matcher.group(), charSequence2, onigCaptureIndexArr));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
