package com.intellij.util.concurrency;

import com.intellij.util.SystemProperties;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/intellij/util/concurrency/Holder;", "", "<init>", "()V", "propagateThreadContext", "", "getPropagateThreadContext", "()Z", "setPropagateThreadContext", "(Z)V", "checkIdeAssertion", "getCheckIdeAssertion", "useImplicitBlockingContext", "getUseImplicitBlockingContext", "setUseImplicitBlockingContext", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class Holder {
    public static final Holder INSTANCE = new Holder();
    private static boolean propagateThreadContext = SystemProperties.getBooleanProperty("ide.propagate.context", true);
    private static final boolean checkIdeAssertion = SystemProperties.getBooleanProperty("ide.check.context.assertion", false);
    private static boolean useImplicitBlockingContext = SystemProperties.getBooleanProperty("ide.enable.implicit.blocking.context", true);

    private Holder() {
    }

    public final boolean getCheckIdeAssertion() {
        return checkIdeAssertion;
    }

    public final boolean getPropagateThreadContext() {
        return propagateThreadContext;
    }

    public final boolean getUseImplicitBlockingContext() {
        return useImplicitBlockingContext;
    }
}
