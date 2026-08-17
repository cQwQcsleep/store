package com.intellij.openapi;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface Disposable {

    public interface Default extends Disposable {
        @Override // com.intellij.openapi.Disposable
        default void dispose() {
        }
    }

    public interface Parent extends Disposable {
        void beforeTreeDispose();
    }

    void dispose();
}
