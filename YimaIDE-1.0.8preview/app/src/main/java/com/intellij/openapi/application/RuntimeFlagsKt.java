package com.intellij.openapi.application;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\"\u0013\u0010\u0000\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0000\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0002\"\u0013\u0010\u0006\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0002\"\u0013\u0010\b\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0002\"\u0013\u0010\n\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0002¨\u0006\u000b"}, d2 = {"isMessageBusThrowsWhenDisposed", "", "()Z", "isCoroutineWILEnabled", "isPureSwingEventWilEnabled", "isMessageBusErrorPropagationEnabled", "reportInvokeLaterWithoutModality", "getReportInvokeLaterWithoutModality", "setUserInteractiveQosForEdt", "getSetUserInteractiveQosForEdt", "isRhizomeAdEnabled", "intellij.platform.core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RuntimeFlagsKt {
    private static final boolean isMessageBusThrowsWhenDisposed = Boolean.parseBoolean(System.getProperty("ijpl.message.bus.throws.when.disposed", "true"));
    private static final boolean isCoroutineWILEnabled = Boolean.parseBoolean(System.getProperty("ide.coroutine.write.intent.lock", "true"));
    private static final boolean isPureSwingEventWilEnabled = Boolean.parseBoolean(System.getProperty("ide.pure.swing.events.write.intent.lock", "false"));
    private static final boolean isMessageBusErrorPropagationEnabled = Boolean.parseBoolean(System.getProperty("ijpl.message.bus.rethrows.errors.from.subscribers", "false"));
    private static final boolean reportInvokeLaterWithoutModality = Boolean.parseBoolean(System.getProperty("ijpl.report.invoke.without.modal", "false"));
    private static final boolean setUserInteractiveQosForEdt = Boolean.parseBoolean(System.getProperty("ide.set.qos.for.edt", "true"));
    private static final boolean isRhizomeAdEnabled = Boolean.parseBoolean(System.getProperty("ijpl.rhizome.ad.enabled", "false"));

    public static final boolean isMessageBusErrorPropagationEnabled() {
        return isMessageBusErrorPropagationEnabled;
    }

    public static final boolean isMessageBusThrowsWhenDisposed() {
        return isMessageBusThrowsWhenDisposed;
    }
}
