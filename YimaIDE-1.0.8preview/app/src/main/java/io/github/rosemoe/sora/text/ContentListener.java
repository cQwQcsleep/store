package io.github.rosemoe.sora.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ContentListener {
    void afterDelete(Content content, int i, int i2, int i3, int i4, CharSequence charSequence);

    void afterInsert(Content content, int i, int i2, int i3, int i4, CharSequence charSequence);

    default void beforeModification(Content content) {
    }

    void beforeReplace(Content content);
}
