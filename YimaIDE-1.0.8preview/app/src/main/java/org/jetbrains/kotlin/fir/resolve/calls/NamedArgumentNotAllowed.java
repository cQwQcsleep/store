package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.resolve.ForbiddenNamedArgumentsTarget;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/NamedArgumentNotAllowed;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "forbiddenNamedArgumentsTarget", "Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;)V", "getArgument", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getForbiddenNamedArgumentsTarget", "()Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NamedArgumentNotAllowed extends ResolutionDiagnostic {
    private final FirExpression argument;
    private final ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTarget;
    private final FirFunction function;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NamedArgumentNotAllowed(FirExpression firExpression, FirFunction firFunction, ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTarget) {
        super(CandidateApplicability.INAPPLICABLE);
        firExpression.getClass();
        firFunction.getClass();
        forbiddenNamedArgumentsTarget.getClass();
        this.argument = firExpression;
        this.function = firFunction;
        this.forbiddenNamedArgumentsTarget = forbiddenNamedArgumentsTarget;
    }

    public final FirExpression getArgument() {
        return this.argument;
    }

    public final ForbiddenNamedArgumentsTarget getForbiddenNamedArgumentsTarget() {
        return this.forbiddenNamedArgumentsTarget;
    }

    public final FirFunction getFunction() {
        return this.function;
    }
}
