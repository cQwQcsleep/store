package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NoFormat implements FormatString {
    private String text;

    public NoFormat(String str) {
        setText(str);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
