package com.intellij.util.io;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class IntInlineKeyDescriptor extends InlineKeyDescriptor<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.intellij.util.io.InlineKeyDescriptor
    public Integer fromInt(int i) {
        return Integer.valueOf(i);
    }

    @Override // com.intellij.util.io.InlineKeyDescriptor
    public int toInt(Integer num) {
        return num.intValue();
    }
}
