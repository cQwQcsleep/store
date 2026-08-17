package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ7\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&R\u00020\u0006j\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013\u0082\u0001\u0006\u0015\u0016\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker;", Argument.Delimiters.none, "<init>", "()V", "isApplicable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "computeMissingCases", Argument.Delimiters.none, "c", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Collection;)V", "AbstractConditionChecker", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnBooleanExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnEnumExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnNothingExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnNullableExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnSealedClassExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenSelfTypeExhaustivenessChecker;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
abstract class WhenExhaustivenessChecker {
    public /* synthetic */ WhenExhaustivenessChecker(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void computeMissingCases(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType, Collection<WhenMissingCase> collection);

    public abstract boolean isApplicable(SessionHolder sessionHolder, ConeKotlinType coneKotlinType);

    private WhenExhaustivenessChecker() {
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b$\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker$AbstractConditionChecker;", "D", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", Argument.Delimiters.none, "<init>", "()V", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)V", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Ljava/lang/Object;)V", "visitWhenBranch", "whenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;Ljava/lang/Object;)V", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;Ljava/lang/Object;)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class AbstractConditionChecker<D> extends FirVisitor<Unit, D> {
        /* JADX INFO: renamed from: visitBooleanOperatorExpression, reason: avoid collision after fix types in other method */
        public void visitBooleanOperatorExpression2(FirBooleanOperatorExpression booleanOperatorExpression, D data) {
            booleanOperatorExpression.getClass();
            data.getClass();
            if (booleanOperatorExpression.getKind() == LogicOperationKind.OR) {
                booleanOperatorExpression.acceptChildren(this, data);
            }
        }

        /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
        public void visitElement2(FirElement element, D data) {
            element.getClass();
            data.getClass();
        }

        /* JADX INFO: renamed from: visitWhenBranch, reason: avoid collision after fix types in other method */
        public void visitWhenBranch2(FirWhenBranch whenBranch, D data) {
            whenBranch.getClass();
            data.getClass();
            if (whenBranch.getHasGuard()) {
                return;
            }
            whenBranch.getCondition().accept(this, data);
        }

        /* JADX INFO: renamed from: visitWhenExpression, reason: avoid collision after fix types in other method */
        public void visitWhenExpression2(FirWhenExpression whenExpression, D data) {
            whenExpression.getClass();
            data.getClass();
            Iterator<T> it = whenExpression.getBranches().iterator();
            while (it.hasNext()) {
                ((FirWhenBranch) it.next()).accept(this, data);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, Object obj) {
            visitElement2(firElement, obj);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitBooleanOperatorExpression(FirBooleanOperatorExpression firBooleanOperatorExpression, Object obj) {
            visitBooleanOperatorExpression2(firBooleanOperatorExpression, obj);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitWhenBranch(FirWhenBranch firWhenBranch, Object obj) {
            visitWhenBranch2(firWhenBranch, obj);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitWhenExpression(FirWhenExpression firWhenExpression, Object obj) {
            visitWhenExpression2(firWhenExpression, obj);
            return Unit.INSTANCE;
        }
    }
}
