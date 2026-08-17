package org.jetbrains.kotlin.psi2ir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/psi2ir/Psi2IrConfiguration;", "", "ignoreErrors", "", IrUtilsKt.SKIP_BODIES_ERROR_DESCRIPTION, "<init>", "(ZZ)V", "getIgnoreErrors", "()Z", "getSkipBodies", "generateBodies", "getGenerateBodies", "org.jetbrains.kotlin:ir.psi2ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Psi2IrConfiguration {
    private final boolean ignoreErrors;
    private final boolean skipBodies;

    public /* synthetic */ Psi2IrConfiguration(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getGenerateBodies() {
        return !this.skipBodies;
    }

    public final boolean getIgnoreErrors() {
        return this.ignoreErrors;
    }

    public final boolean getSkipBodies() {
        return this.skipBodies;
    }

    public Psi2IrConfiguration(boolean z, boolean z2) {
        this.ignoreErrors = z;
        this.skipBodies = z2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Psi2IrConfiguration() {
        boolean z = false;
        this(z, z, 3, null);
    }
}
