package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class VariableItem extends SnippetItem implements PlaceHolderElement {
    private String defaultValue;
    private String name;
    private Transform transform;

    public VariableItem(int i, String str, String str2, Transform transform) {
        super(i);
        this.name = str;
        this.defaultValue = str2;
        this.transform = transform;
    }

    @Override // io.github.rosemoe.sora.lang.completion.snippet.SnippetItem
    /* JADX INFO: renamed from: clone */
    public VariableItem mo1clone() {
        VariableItem variableItem = new VariableItem(getStartIndex(), this.name, this.defaultValue);
        variableItem.setIndex(getStartIndex(), getEndIndex());
        return variableItem;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getName() {
        return this.name;
    }

    public Transform getTransform() {
        return this.transform;
    }

    public void setDefaultValue(String str) {
        this.defaultValue = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public VariableItem(int i, String str, String str2) {
        this(i, str, str2, null);
    }
}
