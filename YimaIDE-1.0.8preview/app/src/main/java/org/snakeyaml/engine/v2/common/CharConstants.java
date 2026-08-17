package org.snakeyaml.engine.v2.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class CharConstants {
    private static final int ASCII_SIZE = 128;
    public static final Map<Character, Integer> ESCAPE_CODES;
    public static final Map<Character, String> ESCAPE_REPLACEMENTS;
    private static final String FULL_LINEBR_S = "\r\n";
    boolean[] contains;
    private static final String LINEBR_S = "\n";
    public static final CharConstants LINEBR = new CharConstants(LINEBR_S);
    private static final String NULL_OR_LINEBR_S = "\u0000\r\n";
    public static final CharConstants NULL_OR_LINEBR = new CharConstants(NULL_OR_LINEBR_S);
    private static final String NULL_BL_LINEBR_S = " \u0000\r\n";
    public static final CharConstants NULL_BL_LINEBR = new CharConstants(NULL_BL_LINEBR_S);
    private static final String NULL_BL_T_LINEBR_S = "\t \u0000\r\n";
    public static final CharConstants NULL_BL_T_LINEBR = new CharConstants(NULL_BL_T_LINEBR_S);
    private static final String NULL_BL_T_S = "\u0000 \t";
    public static final CharConstants NULL_BL_T = new CharConstants(NULL_BL_T_S);
    public static final CharConstants URI_CHARS_FOR_TAG_PREFIX = new CharConstants("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_-;/?:@&=+$_.!~*'()%,[]");
    private static final String URI_CHARS_SUFFIX_S = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_-;/?:@&=+$_.!~*'()%";
    public static final CharConstants URI_CHARS_FOR_TAG_SUFFIX = new CharConstants(URI_CHARS_SUFFIX_S);
    private static final String ALPHA_S = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    public static final CharConstants ALPHA = new CharConstants(ALPHA_S);

    static {
        HashMap map = new HashMap();
        map.put('0', "\u0000");
        map.put('a', "\u0007");
        map.put('b', "\b");
        map.put('t', "\t");
        map.put('n', LINEBR_S);
        map.put('v', "\u000b");
        map.put('f', "\f");
        map.put('r', "\r");
        map.put('e', "\u001b");
        map.put(' ', " ");
        map.put('\"', "\"");
        map.put('/', "/");
        map.put('\\', "\\");
        map.put('N', "\u0085");
        map.put('_', " ");
        ESCAPE_REPLACEMENTS = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put('x', 2);
        map2.put('u', 4);
        map2.put('U', 8);
        ESCAPE_CODES = Collections.unmodifiableMap(map2);
    }

    private CharConstants(String str) {
        boolean[] zArr = new boolean[128];
        this.contains = zArr;
        Arrays.fill(zArr, false);
        for (int i = 0; i < str.length(); i++) {
            this.contains[str.codePointAt(i)] = true;
        }
    }

    public static String escapeChar(String str) {
        for (Character ch : ESCAPE_REPLACEMENTS.keySet()) {
            String str2 = ESCAPE_REPLACEMENTS.get(ch);
            if (!" ".equals(str2) && !"/".equals(str2) && !"\"".equals(str2) && str2.equals(str)) {
                return "\\" + ch;
            }
        }
        return str;
    }

    public boolean has(int i, String str) {
        return has(i) || str.indexOf(i) != -1;
    }

    public boolean hasNo(int i) {
        return !has(i);
    }

    public boolean hasNo(int i, String str) {
        return !has(i, str);
    }

    public boolean has(int i) {
        return i < 128 && this.contains[i];
    }
}
