package org.eclipse.tm4e.languageconfiguration.internal.utils;

import io.github.rosemoe.sora.util.Logger;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class RegExpUtils {
    private static final Logger log = Logger.instance(RegExpUtils.class.getName());

    private RegExpUtils() {
    }

    public static Pattern create(String str) {
        try {
            return Pattern.compile(str);
        } catch (Exception e) {
            log.e("Failed to parse pattern: " + str, e);
            return null;
        }
    }

    public static String escapeRegExpCharacters(String str) {
        return str.replaceAll("[\\-\\\\\\{\\}\\*\\+\\?\\|\\^\\$\\.\\[\\]\\(\\)\\#]", "\\\\$0");
    }
}
