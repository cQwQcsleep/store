package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/UnstableSmartCast;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "targetType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isCastToNotNull", Argument.Delimiters.none, "isImplicitInvokeReceiver", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ZZ)V", "getArgument", "()Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "getTargetType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "()Z", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnstableSmartCast extends ResolutionDiagnostic {
    private final FirSmartCastExpression argument;
    private final boolean isCastToNotNull;
    private final boolean isImplicitInvokeReceiver;
    private final ConeKotlinType targetType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnstableSmartCast(FirSmartCastExpression firSmartCastExpression, ConeKotlinType coneKotlinType, boolean z, boolean z2) {
        super(CandidateApplicability.UNSTABLE_SMARTCAST);
        firSmartCastExpression.getClass();
        coneKotlinType.getClass();
        this.argument = firSmartCastExpression;
        this.targetType = coneKotlinType;
        this.isCastToNotNull = z;
        this.isImplicitInvokeReceiver = z2;
    }

    public final FirSmartCastExpression getArgument() {
        return this.argument;
    }

    public final ConeKotlinType getTargetType() {
        return this.targetType;
    }

    /* JADX INFO: renamed from: isCastToNotNull, reason: from getter */
    public final boolean getIsCastToNotNull() {
        return this.isCastToNotNull;
    }

    /* JADX INFO: renamed from: isImplicitInvokeReceiver, reason: from getter */
    public final boolean getIsImplicitInvokeReceiver() {
        return this.isImplicitInvokeReceiver;
    }
}
