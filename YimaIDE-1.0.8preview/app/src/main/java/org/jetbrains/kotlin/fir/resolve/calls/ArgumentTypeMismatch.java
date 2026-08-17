package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ArgumentTypeMismatch;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "actualType", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isMismatchDueToNullability", Argument.Delimiters.none, "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "systemHadContradiction", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;ZLorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Z)V", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getActualType", "getArgument", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "()Z", "getAnonymousFunctionIfReturnExpression", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getSystemHadContradiction", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentTypeMismatch extends ResolutionDiagnostic {
    private final ConeKotlinType actualType;
    private final FirAnonymousFunction anonymousFunctionIfReturnExpression;
    private final FirExpression argument;
    private final ConeKotlinType expectedType;
    private final boolean isMismatchDueToNullability;
    private final boolean systemHadContradiction;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArgumentTypeMismatch(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirExpression firExpression, boolean z, FirAnonymousFunction firAnonymousFunction, boolean z2) {
        super(z ? CandidateApplicability.UNSAFE_CALL : CandidateApplicability.INAPPLICABLE);
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firExpression.getClass();
        this.expectedType = coneKotlinType;
        this.actualType = coneKotlinType2;
        this.argument = firExpression;
        this.isMismatchDueToNullability = z;
        this.anonymousFunctionIfReturnExpression = firAnonymousFunction;
        this.systemHadContradiction = z2;
    }

    public final ConeKotlinType getActualType() {
        return this.actualType;
    }

    public final FirAnonymousFunction getAnonymousFunctionIfReturnExpression() {
        return this.anonymousFunctionIfReturnExpression;
    }

    public final FirExpression getArgument() {
        return this.argument;
    }

    public final ConeKotlinType getExpectedType() {
        return this.expectedType;
    }

    public final boolean getSystemHadContradiction() {
        return this.systemHadContradiction;
    }

    /* JADX INFO: renamed from: isMismatchDueToNullability, reason: from getter */
    public final boolean getIsMismatchDueToNullability() {
        return this.isMismatchDueToNullability;
    }

    public /* synthetic */ ArgumentTypeMismatch(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirExpression firExpression, boolean z, FirAnonymousFunction firAnonymousFunction, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType, coneKotlinType2, firExpression, z, (i & 16) != 0 ? null : firAnonymousFunction, (i & 32) != 0 ? false : z2);
    }
}
