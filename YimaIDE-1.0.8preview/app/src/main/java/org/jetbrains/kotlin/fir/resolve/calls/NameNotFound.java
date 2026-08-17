package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/NameNotFound;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "getArgument", "()Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "getFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NameNotFound extends ResolutionDiagnostic {
    private final FirNamedArgumentExpression argument;
    private final FirFunction function;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NameNotFound(FirNamedArgumentExpression firNamedArgumentExpression, FirFunction firFunction) {
        super(CandidateApplicability.INAPPLICABLE_ARGUMENTS_MAPPING_ERROR);
        firNamedArgumentExpression.getClass();
        firFunction.getClass();
        this.argument = firNamedArgumentExpression;
        this.function = firFunction;
    }

    public final FirNamedArgumentExpression getArgument() {
        return this.argument;
    }

    public final FirFunction getFunction() {
        return this.function;
    }
}
