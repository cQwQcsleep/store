package com.intellij.openapi.application;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public abstract class AccessToken implements AutoCloseable {
    public static final AccessToken EMPTY_ACCESS_TOKEN = new AccessToken() { // from class: com.intellij.openapi.application.AccessToken.1
        @Override // com.intellij.openapi.application.AccessToken
        public void finish() {
        }
    };

    @Override // java.lang.AutoCloseable
    public final void close() {
        finish();
    }

    public abstract void finish();
}
