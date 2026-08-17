package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ7\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016R\u00020\u0006j\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnNullableExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "computeMissingCases", Argument.Delimiters.none, "c", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Collection;)V", "isNullBranchMissing", "Flags", "ConditionChecker", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class WhenOnNullableExhaustivenessChecker extends WhenExhaustivenessChecker {
    public static final WhenOnNullableExhaustivenessChecker INSTANCE = new WhenOnNullableExhaustivenessChecker();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnNullableExhaustivenessChecker$Flags;", Argument.Delimiters.none, "<init>", "()V", "containsNull", Argument.Delimiters.none, "getContainsNull", "()Z", "setContainsNull", "(Z)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Flags {
        private boolean containsNull;

        public final boolean getContainsNull() {
            return this.containsNull;
        }

        public final void setContainsNull(boolean z) {
            this.containsNull = z;
        }
    }

    private WhenOnNullableExhaustivenessChecker() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public void computeMissingCases(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType, Collection<WhenMissingCase> collection) {
        sessionHolder.getClass();
        firWhenExpression.getClass();
        coneKotlinType.getClass();
        collection.getClass();
        if (isNullBranchMissing(firWhenExpression)) {
            collection.add(WhenMissingCase.NullIsMissing.INSTANCE);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public boolean isApplicable(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType);
    }

    public final boolean isNullBranchMissing(FirWhenExpression whenExpression) {
        whenExpression.getClass();
        Flags flags = new Flags();
        whenExpression.accept(ConditionChecker.INSTANCE, flags);
        return !flags.getContainsNull();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnNullableExhaustivenessChecker$ConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker$AbstractConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnNullableExhaustivenessChecker$Flags;", "<init>", "()V", "visitEqualityOperatorCall", Argument.Delimiters.none, "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "data", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ConditionChecker extends WhenExhaustivenessChecker.AbstractConditionChecker<Flags> {
        public static final ConditionChecker INSTANCE = new ConditionChecker();

        private ConditionChecker() {
        }

        public void visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Flags data) {
            equalityOperatorCall.getClass();
            data.getClass();
            if (ConeBuiltinTypeUtilsKt.isNullableNothing(FirTypeUtilsKt.getResolvedType(equalityOperatorCall.getArgumentList().getArguments().get(1)))) {
                data.setContainsNull(true);
            }
        }

        public void visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, Flags data) {
            typeOperatorCall.getClass();
            data.getClass();
            if (typeOperatorCall.getOperation() == FirOperation.IS && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(FirTypeUtilsKt.getConeType(typeOperatorCall.getConversionTypeRef()))) {
                data.setContainsNull(true);
            }
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitTypeOperatorCall(FirTypeOperatorCall firTypeOperatorCall, Object obj) {
            visitTypeOperatorCall(firTypeOperatorCall, (Flags) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitEqualityOperatorCall(FirEqualityOperatorCall firEqualityOperatorCall, Object obj) {
            visitEqualityOperatorCall(firEqualityOperatorCall, (Flags) obj);
            return Unit.INSTANCE;
        }
    }
}
