package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ConditionalFormat implements FormatString {
    public String elseValue;
    public int group;
    public String ifValue;
    public String shorthand;

    public String getElseValue() {
        return this.elseValue;
    }

    public int getGroup() {
        return this.group;
    }

    public String getIfValue() {
        return this.ifValue;
    }

    public String getShorthand() {
        return this.shorthand;
    }

    public void setElseValue(String str) {
        this.elseValue = str;
    }

    public void setGroup(int i) {
        this.group = i;
    }

    public void setIfValue(String str) {
        this.ifValue = str;
    }

    public void setShorthand(String str) {
        this.shorthand = str;
    }
}
