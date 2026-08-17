package org.jetbrains.kotlin.fir.expressions.impl;

import defpackage.ia5;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ5\u0010!\u001a\u00020\"\"\u0004\b\u0000\u0010#\"\u0004\b\u0001\u0010$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0&2\u0006\u0010'\u001a\u0002H$H\u0016¢\u0006\u0002\u0010(J)\u0010)\u001a\u00020\u0000\"\u0004\b\u0000\u0010$2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H$0+2\u0006\u0010'\u001a\u0002H$H\u0016¢\u0006\u0002\u0010,J)\u0010-\u001a\u00020\u0000\"\u0004\b\u0000\u0010$2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H$0+2\u0006\u0010'\u001a\u0002H$H\u0016¢\u0006\u0002\u0010,J\u0012\u0010.\u001a\u00020\"2\b\u0010/\u001a\u0004\u0018\u00010\u001bH\u0016J\u0016\u00100\u001a\u00020\"2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000602H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0019R\u0014\u0010\u000b\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0019¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirSpreadArgumentExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isNamed", Argument.Delimiters.none, "isFakeSpread", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;ZZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setExpression", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "()Z", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isSpread", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirSpreadArgumentExpressionImpl;", "transformAnnotations", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSpreadArgumentExpressionImpl extends FirSpreadArgumentExpression {
    private List<FirAnnotation> annotations;
    private FirExpression expression;
    private final boolean isFakeSpread;
    private final boolean isNamed;
    private final KtSourceElement source;

    private FirSpreadArgumentExpressionImpl(KtSourceElement ktSourceElement, List<FirAnnotation> list, FirExpression firExpression, boolean z, boolean z2) {
        firExpression.getClass();
        this.source = ktSourceElement;
        this.annotations = list;
        this.expression = firExpression;
        this.isNamed = z;
        this.isFakeSpread = z2;
    }

    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m510getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getExpression().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m510getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m510getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return getExpression().getConeTypeOrNull();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression
    public FirExpression getExpression() {
        return this.expression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression
    /* JADX INFO: renamed from: isFakeSpread, reason: from getter */
    public boolean getIsFakeSpread() {
        return this.isFakeSpread;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression
    /* JADX INFO: renamed from: isNamed, reason: from getter */
    public boolean getIsNamed() {
        return this.isNamed;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression
    /* JADX INFO: renamed from: isSpread */
    public boolean getIsSpread() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m511setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        if (Intrinsics.areEqual(newConeTypeOrNull, getConeTypeOrNull())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(FirSpreadArgumentExpressionImpl.class.getSimpleName());
        sb.append(".replaceConeTypeOrNull() called with invalid type '");
        sb.append(newConeTypeOrNull);
        ia5.a(sb, "'. Current type is '", getConeTypeOrNull(), 39);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m511setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setExpression(FirExpression firExpression) {
        firExpression.getClass();
        this.expression = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirSpreadArgumentExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m510getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirSpreadArgumentExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        setExpression((FirExpression) getExpression().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirSpreadArgumentExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirWrappedArgumentExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirWrappedExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression, org.jetbrains.kotlin.fir.expressions.FirWrappedExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirSpreadArgumentExpressionImpl(KtSourceElement ktSourceElement, List list, FirExpression firExpression, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, list, firExpression, z, z2);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
