package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExceptionLabel extends Label {
    @Override // com.reandroid.dex.ins.Label
    default int compareLabelName(Label label) {
        return 0;
    }

    ExceptionHandler getHandler();

    @Override // com.reandroid.dex.ins.ExtraLine
    default boolean isRemoved() {
        return getHandler().isRemoved();
    }
}
