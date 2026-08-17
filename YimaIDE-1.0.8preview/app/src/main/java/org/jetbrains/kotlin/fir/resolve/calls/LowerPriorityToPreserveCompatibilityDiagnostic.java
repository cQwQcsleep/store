package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/LowerPriorityToPreserveCompatibilityDiagnostic;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "<init>", "()V", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LowerPriorityToPreserveCompatibilityDiagnostic extends ResolutionDiagnostic {
    public static final LowerPriorityToPreserveCompatibilityDiagnostic INSTANCE = new LowerPriorityToPreserveCompatibilityDiagnostic();

    private LowerPriorityToPreserveCompatibilityDiagnostic() {
        super(CandidateApplicability.RESOLVED_NEED_PRESERVE_COMPATIBILITY);
    }
}
