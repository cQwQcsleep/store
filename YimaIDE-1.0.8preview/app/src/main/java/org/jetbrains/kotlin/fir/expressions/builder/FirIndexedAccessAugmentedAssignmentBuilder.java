package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.impl.FirIndexedAccessAugmentedAssignmentImpl;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.impl.FirStubReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010*\u001a\u00020+H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0007\"\u0004\b)\u0010\tÊ\u0001\u0002\b-¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirIndexedAccessAugmentedAssignmentBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "lhsGetCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getLhsGetCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "setLhsGetCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "rhs", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getRhs", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setRhs", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "getOperation", "()Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "setOperation", "(Lorg/jetbrains/kotlin/fir/expressions/FirOperation;)V", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "arrayAccessSource", "getArrayAccessSource", "setArrayAccessSource", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIndexedAccessAugmentedAssignmentBuilder implements FirAnnotationContainerBuilder {
    private KtSourceElement arrayAccessSource;
    public FirFunctionCall lhsGetCall;
    public FirOperation operation;
    public FirExpression rhs;
    private KtSourceElement source;
    private final List<FirAnnotation> annotations = new ArrayList();
    private FirReference calleeReference = FirStubReference.INSTANCE;

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirIndexedAccessAugmentedAssignment build() {
        return new FirIndexedAccessAugmentedAssignmentImpl(this.source, FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getLhsGetCall(), getRhs(), getOperation(), this.calleeReference, this.arrayAccessSource, null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final KtSourceElement getArrayAccessSource() {
        return this.arrayAccessSource;
    }

    public final FirReference getCalleeReference() {
        return this.calleeReference;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirFunctionCall getLhsGetCall() throws UninitializedPropertyAccessException {
        FirFunctionCall firFunctionCall = this.lhsGetCall;
        if (firFunctionCall != null) {
            return firFunctionCall;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lhsGetCall");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirOperation getOperation() throws UninitializedPropertyAccessException {
        FirOperation firOperation = this.operation;
        if (firOperation != null) {
            return firOperation;
        }
        Intrinsics.throwUninitializedPropertyAccessException("operation");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirExpression getRhs() throws UninitializedPropertyAccessException {
        FirExpression firExpression = this.rhs;
        if (firExpression != null) {
            return firExpression;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rhs");
        return null;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final void setArrayAccessSource(KtSourceElement ktSourceElement) {
        this.arrayAccessSource = ktSourceElement;
    }

    public final void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    public final void setLhsGetCall(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        this.lhsGetCall = firFunctionCall;
    }

    public final void setOperation(FirOperation firOperation) {
        firOperation.getClass();
        this.operation = firOperation;
    }

    public final void setRhs(FirExpression firExpression) {
        firExpression.getClass();
        this.rhs = firExpression;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
