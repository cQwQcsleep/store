package io.github.rosemoe.sora.util.regex;

import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RegexBackrefToken {
    private final int group;
    private final boolean isRef;
    private final String text;

    public RegexBackrefToken(boolean z, String str, int i) {
        this.isRef = z;
        this.text = str;
        this.group = i;
    }

    public int getGroup() {
        return this.group;
    }

    public String getReplacementText(Matcher matcher) {
        return isReference() ? matcher.group(getGroup()) : this.text;
    }

    public String getText() {
        return this.text;
    }

    public boolean isReference() {
        return this.isRef;
    }
}
