package com.intellij.util.io;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\b\u0010\t\u001a\u00020\u0007H\u0007J\u0006\u0010\n\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/intellij/util/io/IOCancellationCallbackHolder;", "", "<init>", "()V", "usedIoCallback", "Lcom/intellij/util/io/IOCancellationCallback;", "setIoCancellationCallback", "", "callback", "checkCancelled", "interactWithUI", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IOCancellationCallbackHolder {
    public static final IOCancellationCallbackHolder INSTANCE = new IOCancellationCallbackHolder();
    private static IOCancellationCallback usedIoCallback = new IOCancellationCallback() { // from class: com.intellij.util.io.IOCancellationCallbackHolder$usedIoCallback$1
        @Override // com.intellij.util.io.IOCancellationCallback
        public void checkCancelled() {
        }

        @Override // com.intellij.util.io.IOCancellationCallback
        public void interactWithUI() {
        }
    };

    private IOCancellationCallbackHolder() {
    }

    @JvmStatic
    public static final void checkCancelled() {
        usedIoCallback.checkCancelled();
    }

    public final void interactWithUI() {
        usedIoCallback.interactWithUI();
    }
}
