package io.github.rosemoe.sora.util.regex;

import kotlin.text.Typography;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RegexBackrefGrammar {
    public static final RegexBackrefGrammar DEFAULT = new RegexBackrefGrammar(Typography.dollar, '\\');
    public final char backrefStartChar;
    public final char escapeChar;

    public RegexBackrefGrammar(char c, char c2) {
        this.backrefStartChar = c;
        this.escapeChar = c2;
    }
}
