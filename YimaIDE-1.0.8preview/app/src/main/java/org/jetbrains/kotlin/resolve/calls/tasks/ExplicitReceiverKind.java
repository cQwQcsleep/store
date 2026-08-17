package org.jetbrains.kotlin.resolve.calls.tasks;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ExplicitReceiverKind {
    EXTENSION_RECEIVER,
    DISPATCH_RECEIVER,
    NO_EXPLICIT_RECEIVER,
    BOTH_RECEIVERS;

    public boolean isDispatchReceiver() {
        return this == DISPATCH_RECEIVER || this == BOTH_RECEIVERS;
    }

    public boolean isExtensionReceiver() {
        return this == EXTENSION_RECEIVER || this == BOTH_RECEIVERS;
    }
}
