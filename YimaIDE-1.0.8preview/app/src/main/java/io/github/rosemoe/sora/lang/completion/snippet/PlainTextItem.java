package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class PlainTextItem extends SnippetItem {
    private String text;

    public PlainTextItem(String str, int i) {
        this(str, i, str.length() + i);
    }

    @Override // io.github.rosemoe.sora.lang.completion.snippet.SnippetItem
    /* JADX INFO: renamed from: clone */
    public PlainTextItem mo1clone() {
        return new PlainTextItem(this.text, getStartIndex(), getEndIndex());
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public PlainTextItem(String str, int i, int i2) {
        setIndex(i, i2);
        this.text = str;
    }
}
