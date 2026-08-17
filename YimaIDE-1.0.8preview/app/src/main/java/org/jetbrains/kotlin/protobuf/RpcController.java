package org.jetbrains.kotlin.protobuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface RpcController {
    String errorText();

    boolean failed();

    boolean isCanceled();

    void notifyOnCancel(RpcCallback<Object> rpcCallback);

    void reset();

    void setFailed(String str);

    void startCancel();
}
