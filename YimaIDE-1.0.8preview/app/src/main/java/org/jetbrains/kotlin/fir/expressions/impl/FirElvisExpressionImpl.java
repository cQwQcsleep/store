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
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ5\u0010(\u001a\u00020)\"\u0004\b\u0000\u0010*\"\u0004\b\u0001\u0010+2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H+0-2\u0006\u0010.\u001a\u0002H+H\u0016¢\u0006\u0002\u0010/J)\u00100\u001a\u00020\u0000\"\u0004\b\u0000\u0010+2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H+022\u0006\u0010.\u001a\u0002H+H\u0016¢\u0006\u0002\u00103J)\u00104\u001a\u00020\u0000\"\u0004\b\u0000\u0010+2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H+022\u0006\u0010.\u001a\u0002H+H\u0016¢\u0006\u0002\u00103J)\u00105\u001a\u00020\u0000\"\u0004\b\u0000\u0010+2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H+022\u0006\u0010.\u001a\u0002H+H\u0016¢\u0006\u0002\u00103J)\u00106\u001a\u00020\u0000\"\u0004\b\u0000\u0010+2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H+022\u0006\u0010.\u001a\u0002H+H\u0016¢\u0006\u0002\u00103J)\u00107\u001a\u00020\u0000\"\u0004\b\u0000\u0010+2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H+022\u0006\u0010.\u001a\u0002H+H\u0016¢\u0006\u0002\u00103J\u0012\u00108\u001a\u00020)2\b\u00109\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010:\u001a\u00020)2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\b0<H\u0016J\u0010\u0010=\u001a\u00020)2\u0006\u0010>\u001a\u00020\nH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R*\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016@\u0016X\u0097\u000er\u0002\b\u0018¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010\r\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010#\"\u0004\b'\u0010%¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirElvisExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "lhs", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "rhs", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/fir/references/FirReference;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "getLhs", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setLhs", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getRhs", "setRhs", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirElvisExpressionImpl;", "transformAnnotations", "transformCalleeReference", "transformLhs", "transformRhs", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceCalleeReference", "newCalleeReference", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirElvisExpressionImpl extends FirElvisExpression {
    private List<FirAnnotation> annotations;
    private FirReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private FirExpression lhs;
    private FirExpression rhs;
    private final KtSourceElement source;

    private FirElvisExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List<FirAnnotation> list, FirReference firReference, FirExpression firExpression, FirExpression firExpression2) {
        firReference.getClass();
        firExpression.getClass();
        firExpression2.getClass();
        this.source = ktSourceElement;
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.calleeReference = firReference;
        this.lhs = firExpression;
        this.rhs = firExpression2;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m426getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getCalleeReference().accept(visitor, data);
        getLhs().accept(visitor, data);
        getRhs().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m426getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m426getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression
    public FirExpression getLhs() {
        return this.lhs;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression
    public FirExpression getRhs() {
        return this.rhs;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m427setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m427setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setLhs(FirExpression firExpression) {
        firExpression.getClass();
        this.lhs = firExpression;
    }

    public void setRhs(FirExpression firExpression) {
        firExpression.getClass();
        this.rhs = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirElvisExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m426getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirElvisExpressionImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirElvisExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        transformLhs((FirTransformer) transformer, (Object) data);
        transformRhs((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression
    public <D> FirElvisExpressionImpl transformLhs(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setLhs((FirExpression) getLhs().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression
    public <D> FirElvisExpressionImpl transformRhs(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setRhs((FirExpression) getRhs().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirElvisExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression
    public /* bridge */ /* synthetic */ FirElvisExpression transformLhs(FirTransformer firTransformer, Object obj) {
        return transformLhs((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression
    public /* bridge */ /* synthetic */ FirElvisExpression transformRhs(FirTransformer firTransformer, Object obj) {
        return transformRhs((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirElvisExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirElvisExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirElvisExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List list, FirReference firReference, FirExpression firExpression, FirExpression firExpression2, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, coneKotlinType, list, firReference, firExpression, firExpression2);
    }
}
