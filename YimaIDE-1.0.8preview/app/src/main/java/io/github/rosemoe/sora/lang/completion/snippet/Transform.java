package io.github.rosemoe.sora.lang.completion.snippet;

import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Transform {
    public List<FormatString> format;
    public boolean globalMode;
    public Pattern regexp;

    public List<FormatString> getFormat() {
        return this.format;
    }

    public Pattern getRegexp() {
        return this.regexp;
    }

    public void setFormat(List<FormatString> list) {
        this.format = list;
    }

    public void setRegexp(Pattern pattern) {
        this.regexp = pattern;
    }
}
