package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/InapplicableWrongReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "actualType", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getActualType", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InapplicableWrongReceiver extends ResolutionDiagnostic {
    private final ConeKotlinType actualType;
    private final ConeKotlinType expectedType;

    public /* synthetic */ InapplicableWrongReceiver(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : coneKotlinType, (i & 2) != 0 ? null : coneKotlinType2);
    }

    public final ConeKotlinType getActualType() {
        return this.actualType;
    }

    public final ConeKotlinType getExpectedType() {
        return this.expectedType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public InapplicableWrongReceiver() {
        ConeKotlinType coneKotlinType = null;
        this(coneKotlinType, coneKotlinType, 3, coneKotlinType);
    }

    public InapplicableWrongReceiver(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        super(CandidateApplicability.INAPPLICABLE_WRONG_RECEIVER);
        this.expectedType = coneKotlinType;
        this.actualType = coneKotlinType2;
    }
}
