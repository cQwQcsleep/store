package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class PlaceholderItem extends SnippetItem {
    private PlaceholderDefinition definition;
    private String text;

    private PlaceholderItem(PlaceholderDefinition placeholderDefinition, String str, int i, int i2) {
        setIndex(i, i2);
        this.text = str;
        this.definition = placeholderDefinition;
    }

    @Override // io.github.rosemoe.sora.lang.completion.snippet.SnippetItem
    /* JADX INFO: renamed from: clone */
    public PlaceholderItem mo1clone() {
        return new PlaceholderItem(this.definition, this.text, getStartIndex(), getEndIndex());
    }

    public PlaceholderDefinition getDefinition() {
        return this.definition;
    }

    public void setDefinition(PlaceholderDefinition placeholderDefinition) {
        this.definition = placeholderDefinition;
    }

    public PlaceholderItem(PlaceholderDefinition placeholderDefinition, int i) {
        setIndex(i, i);
        this.definition = placeholderDefinition;
    }
}
