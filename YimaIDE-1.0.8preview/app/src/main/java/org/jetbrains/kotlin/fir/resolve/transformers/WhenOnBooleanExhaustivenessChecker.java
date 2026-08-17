package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J7\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016R\u00020\u0006j\u0006\u0010\u0012\u001a\u00020\u0006¢\u0006\u0002\u0010\u0018¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnBooleanExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "recordValue", Argument.Delimiters.none, "value", Argument.Delimiters.none, "data", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnBooleanExhaustivenessChecker$Flags;", "computeMissingCases", "c", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Collection;)V", "Flags", "ConditionChecker", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class WhenOnBooleanExhaustivenessChecker extends WhenExhaustivenessChecker {
    public static final WhenOnBooleanExhaustivenessChecker INSTANCE = new WhenOnBooleanExhaustivenessChecker();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnBooleanExhaustivenessChecker$Flags;", Argument.Delimiters.none, "<init>", "()V", "containsTrue", Argument.Delimiters.none, "getContainsTrue", "()Z", "setContainsTrue", "(Z)V", "containsFalse", "getContainsFalse", "setContainsFalse", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Flags {
        private boolean containsFalse;
        private boolean containsTrue;

        public final boolean getContainsFalse() {
            return this.containsFalse;
        }

        public final boolean getContainsTrue() {
            return this.containsTrue;
        }

        public final void setContainsFalse(boolean z) {
            this.containsFalse = z;
        }

        public final void setContainsTrue(boolean z) {
            this.containsTrue = z;
        }
    }

    private WhenOnBooleanExhaustivenessChecker() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordValue(Object value, Flags data) {
        if (Intrinsics.areEqual(value, Boolean.TRUE)) {
            data.setContainsTrue(true);
        } else if (Intrinsics.areEqual(value, Boolean.FALSE)) {
            data.setContainsFalse(true);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public void computeMissingCases(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType, Collection<WhenMissingCase> collection) {
        Collection<DfaType> lowerTypesFromSmartCast;
        sessionHolder.getClass();
        firWhenExpression.getClass();
        coneKotlinType.getClass();
        collection.getClass();
        if (LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.ImprovedExhaustivenessChecksIn21) && WhenSelfTypeExhaustivenessChecker.INSTANCE.isExhaustiveThroughSelfTypeCheck(sessionHolder, firWhenExpression, coneKotlinType)) {
            return;
        }
        Flags flags = new Flags();
        if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(sessionHolder.getSession()).supportsFeature(LanguageFeature.DataFlowBasedExhaustiveness)) {
            FirVariable subjectVariable = firWhenExpression.getSubjectVariable();
            FirExpression initializer = subjectVariable != null ? subjectVariable.getInitializer() : null;
            FirSmartCastExpression firSmartCastExpression = initializer instanceof FirSmartCastExpression ? (FirSmartCastExpression) initializer : null;
            if (firSmartCastExpression != null && (lowerTypesFromSmartCast = firSmartCastExpression.getLowerTypesFromSmartCast()) != null) {
                ArrayList<Boolean> arrayList = new ArrayList();
                for (DfaType dfaType : lowerTypesFromSmartCast) {
                    DfaType.BooleanLiteral booleanLiteral = dfaType instanceof DfaType.BooleanLiteral ? (DfaType.BooleanLiteral) dfaType : null;
                    Boolean boolValueOf = booleanLiteral != null ? Boolean.valueOf(booleanLiteral.getValue()) : null;
                    if (boolValueOf != null) {
                        arrayList.add(boolValueOf);
                    }
                }
                for (Boolean bool : arrayList) {
                    bool.booleanValue();
                    INSTANCE.recordValue(bool, flags);
                }
            }
        }
        firWhenExpression.accept(ConditionChecker.INSTANCE, flags);
        if (!flags.getContainsTrue()) {
            collection.add(WhenMissingCase.BooleanIsMissing.TrueIsMissing.INSTANCE);
        }
        if (flags.getContainsFalse()) {
            return;
        }
        collection.add(WhenMissingCase.BooleanIsMissing.FalseIsMissing.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public boolean isApplicable(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), StandardClassIds.INSTANCE.getBoolean());
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnBooleanExhaustivenessChecker$ConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker$AbstractConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnBooleanExhaustivenessChecker$Flags;", "<init>", "()V", "visitEqualityOperatorCall", Argument.Delimiters.none, "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "data", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ConditionChecker extends WhenExhaustivenessChecker.AbstractConditionChecker<Flags> {
        public static final ConditionChecker INSTANCE = new ConditionChecker();

        private ConditionChecker() {
        }

        public void visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Flags data) {
            equalityOperatorCall.getClass();
            data.getClass();
            FirOperation operation = equalityOperatorCall.getOperation();
            if (operation == FirOperation.EQ || operation == FirOperation.IDENTITY) {
                FirExpression firExpression = equalityOperatorCall.getArgumentList().getArguments().get(1);
                if (firExpression instanceof FirLiteralExpression) {
                    WhenOnBooleanExhaustivenessChecker.INSTANCE.recordValue(((FirLiteralExpression) firExpression).getValue(), data);
                }
            }
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitEqualityOperatorCall(FirEqualityOperatorCall firEqualityOperatorCall, Object obj) {
            visitEqualityOperatorCall(firEqualityOperatorCall, (Flags) obj);
            return Unit.INSTANCE;
        }
    }
}
