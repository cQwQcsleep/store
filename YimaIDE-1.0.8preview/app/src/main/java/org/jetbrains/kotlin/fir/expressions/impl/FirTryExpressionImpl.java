package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001BQ\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0011\u0010\u0012J5\u0010,\u001a\u00020-\"\u0004\b\u0000\u0010.\"\u0004\b\u0001\u0010/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u0002H.\u0012\u0004\u0012\u0002H/012\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00103J)\u00104\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J)\u00108\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J)\u00109\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J)\u0010:\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J)\u0010;\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J)\u0010<\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J)\u0010=\u001a\u00020\u0000\"\u0004\b\u0000\u0010/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H/062\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00107J\u0012\u0010>\u001a\u00020-2\b\u0010?\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010@\u001a\u00020-2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\b0BH\u0016J\u0010\u0010C\u001a\u00020-2\u0006\u0010D\u001a\u00020\nH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R*\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016@\u0016X\u0097\u000er\u0002\b\u001b¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(¨\u0006E"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirTryExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "tryBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "catches", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "finallyBlock", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/fir/references/FirReference;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "getTryBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setTryBlock", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "getCatches", "getFinallyBlock", "setFinallyBlock", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirTryExpressionImpl;", "transformAnnotations", "transformCalleeReference", "transformTryBlock", "transformCatches", "transformFinallyBlock", "transformOtherChildren", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceCalleeReference", "newCalleeReference", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTryExpressionImpl extends FirTryExpression {
    private List<FirAnnotation> annotations;
    private FirReference calleeReference;
    private final List<FirCatch> catches;
    private ConeKotlinType coneTypeOrNull;
    private FirBlock finallyBlock;
    private final KtSourceElement source;
    private FirBlock tryBlock;

    private FirTryExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List<FirAnnotation> list, FirReference firReference, FirBlock firBlock, List<FirCatch> list2, FirBlock firBlock2) {
        firReference.getClass();
        firBlock.getClass();
        list2.getClass();
        this.source = ktSourceElement;
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.calleeReference = firReference;
        this.tryBlock = firBlock;
        this.catches = list2;
        this.finallyBlock = firBlock2;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m528getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getCalleeReference().accept(visitor, data);
        getTryBlock().accept(visitor, data);
        Iterator<T> it2 = getCatches().iterator();
        while (it2.hasNext()) {
            ((FirCatch) it2.next()).accept(visitor, data);
        }
        FirBlock finallyBlock = getFinallyBlock();
        if (finallyBlock != null) {
            finallyBlock.accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m528getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m528getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public List<FirCatch> getCatches() {
        return this.catches;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public FirBlock getFinallyBlock() {
        return this.finallyBlock;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public FirBlock getTryBlock() {
        return this.tryBlock;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m529setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m529setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setFinallyBlock(FirBlock firBlock) {
        this.finallyBlock = firBlock;
    }

    public void setTryBlock(FirBlock firBlock) {
        firBlock.getClass();
        this.tryBlock = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirTryExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m528getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirTryExpressionImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public <D> FirTryExpressionImpl transformCatches(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getCatches(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirTryExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        transformTryBlock((FirTransformer) transformer, (Object) data);
        transformCatches((FirTransformer) transformer, (Object) data);
        transformFinallyBlock((FirTransformer) transformer, (Object) data);
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public <D> FirTryExpressionImpl transformFinallyBlock(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirBlock finallyBlock = getFinallyBlock();
        setFinallyBlock(finallyBlock != null ? (FirBlock) finallyBlock.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public <D> FirTryExpressionImpl transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public <D> FirTryExpressionImpl transformTryBlock(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setTryBlock((FirBlock) getTryBlock().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public /* bridge */ /* synthetic */ FirTryExpression transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public /* bridge */ /* synthetic */ FirTryExpression transformCatches(FirTransformer firTransformer, Object obj) {
        return transformCatches((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirTryExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirTryExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public /* bridge */ /* synthetic */ FirTryExpression transformTryBlock(FirTransformer firTransformer, Object obj) {
        return transformTryBlock((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirTryExpression
    public /* bridge */ /* synthetic */ FirTryExpression transformFinallyBlock(FirTransformer firTransformer, Object obj) {
        return transformFinallyBlock((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirTryExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List list, FirReference firReference, FirBlock firBlock, List list2, FirBlock firBlock2, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, coneKotlinType, list, firReference, firBlock, list2, firBlock2);
    }
}
