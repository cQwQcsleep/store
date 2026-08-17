package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class InterpolatedShellItem extends SnippetItem {
    private String shellCode;

    public InterpolatedShellItem(String str, int i) {
        super(i);
        this.shellCode = str;
    }

    @Override // io.github.rosemoe.sora.lang.completion.snippet.SnippetItem
    /* JADX INFO: renamed from: clone */
    public InterpolatedShellItem mo1clone() {
        InterpolatedShellItem interpolatedShellItem = new InterpolatedShellItem(this.shellCode, getStartIndex());
        interpolatedShellItem.setIndex(getStartIndex(), getEndIndex());
        return interpolatedShellItem;
    }

    public String getShellCode() {
        return this.shellCode;
    }

    public void setShellCode(String str) {
        this.shellCode = str;
    }
}
