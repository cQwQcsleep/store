package org.jetbrains.kotlin.resolve.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB\u001b\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability;", "", "canBeConst", "", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "<init>", "(ZLorg/jetbrains/kotlin/diagnostics/Diagnostic;)V", "getCanBeConst", "()Z", "getDiagnostic", "()Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "Applicable", "NonApplicable", "Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability$Applicable;", "Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability$NonApplicable;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class ConstApplicability {
    private final boolean canBeConst;
    private final Diagnostic diagnostic;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability$Applicable;", "Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability;", "<init>", "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Applicable extends ConstApplicability {
        public static final Applicable INSTANCE = new Applicable();

        /* JADX WARN: Illegal instructions before constructor call */
        private Applicable() {
            Diagnostic diagnostic = null;
            super(true, diagnostic, diagnostic);
        }
    }

    private ConstApplicability(boolean z, Diagnostic diagnostic) {
        this.canBeConst = z;
        this.diagnostic = diagnostic;
    }

    public final boolean getCanBeConst() {
        return this.canBeConst;
    }

    public final Diagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability$NonApplicable;", "Lorg/jetbrains/kotlin/resolve/checkers/ConstApplicability;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/Diagnostic;)V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class NonApplicable extends ConstApplicability {
        public /* synthetic */ NonApplicable(Diagnostic diagnostic, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : diagnostic);
        }

        public NonApplicable(Diagnostic diagnostic) {
            super(false, diagnostic, null);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public NonApplicable() {
            Diagnostic diagnostic = null;
            this(diagnostic, 1, diagnostic);
        }
    }

    public /* synthetic */ ConstApplicability(boolean z, Diagnostic diagnostic, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, diagnostic);
    }
}
