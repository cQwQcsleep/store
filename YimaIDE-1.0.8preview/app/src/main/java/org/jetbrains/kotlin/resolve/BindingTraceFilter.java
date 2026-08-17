package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/BindingTraceFilter;", "", "ignoreDiagnostics", "", "<init>", "(Z)V", "getIgnoreDiagnostics", "()Z", "includesEverythingIn", "otherFilter", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class BindingTraceFilter {
    private final boolean ignoreDiagnostics;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final BindingTraceFilter ACCEPT_ALL = new BindingTraceFilter(false);
    private static final BindingTraceFilter NO_DIAGNOSTICS = new BindingTraceFilter(true);

    public BindingTraceFilter(boolean z) {
        this.ignoreDiagnostics = z;
    }

    public final boolean getIgnoreDiagnostics() {
        return this.ignoreDiagnostics;
    }

    public final boolean includesEverythingIn(BindingTraceFilter otherFilter) {
        otherFilter.getClass();
        return !this.ignoreDiagnostics || otherFilter.ignoreDiagnostics;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/resolve/BindingTraceFilter$Companion;", "", "<init>", "()V", "ACCEPT_ALL", "Lorg/jetbrains/kotlin/resolve/BindingTraceFilter;", "getACCEPT_ALL", "()Lorg/jetbrains/kotlin/resolve/BindingTraceFilter;", "NO_DIAGNOSTICS", "getNO_DIAGNOSTICS", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BindingTraceFilter getACCEPT_ALL() {
            return BindingTraceFilter.ACCEPT_ALL;
        }

        public final BindingTraceFilter getNO_DIAGNOSTICS() {
            return BindingTraceFilter.NO_DIAGNOSTICS;
        }

        private Companion() {
        }
    }
}
