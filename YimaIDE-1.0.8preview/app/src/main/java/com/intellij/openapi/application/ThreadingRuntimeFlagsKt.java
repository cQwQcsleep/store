package com.intellij.openapi.application;

import com.intellij.psi.impl.source.tree.ChildRole;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\"\u0013\u0010\u0000\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0000\u0010\u0002\"\u0016\u0010\u0003\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u00018G¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0002\"\u0013\u0010\u0007\u001a\u00020\b8G¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"isLockStoredInContext", "", "()Z", "useBackgroundWriteAction", "getUseBackgroundWriteAction", "reportInvalidActionChains", "getReportInvalidActionChains", "readLockCompensationTimeout", "", "getReadLockCompensationTimeout", "()I", "intellij.platform.core"}, k = 2, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public final class ThreadingRuntimeFlagsKt {
    private static final int readLockCompensationTimeout;
    private static final boolean isLockStoredInContext = Boolean.parseBoolean(System.getProperty("ide.store.lock.in.context", "true"));
    private static final boolean useBackgroundWriteAction = Boolean.parseBoolean(System.getProperty("idea.background.write.action.enabled", "false"));
    private static final boolean reportInvalidActionChains = Boolean.parseBoolean(System.getProperty("ijpl.report.invalid.action.chains", "false"));

    static {
        int i;
        try {
            String property = System.getProperty("ide.read.lock.compensation.timeout.ms", "250");
            property.getClass();
            i = Integer.parseInt(property);
        } catch (NumberFormatException unused) {
            i = -1;
        }
        readLockCompensationTimeout = i;
    }

    public static final boolean isLockStoredInContext() {
        return isLockStoredInContext;
    }
}
