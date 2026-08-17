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
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001BE\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J5\u0010'\u001a\u00020(\"\u0004\b\u0000\u0010)\"\u0004\b\u0001\u0010*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H)\u0012\u0004\u0012\u0002H*0,2\u0006\u0010-\u001a\u0002H*H\u0016¢\u0006\u0002\u0010.J)\u0010/\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H*012\u0006\u0010-\u001a\u0002H*H\u0016¢\u0006\u0002\u00102J)\u00103\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H*012\u0006\u0010-\u001a\u0002H*H\u0016¢\u0006\u0002\u00102J\u0012\u00104\u001a\u00020(2\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u00106\u001a\u00020(2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\b08H\u0016J\u0010\u00109\u001a\u00020(2\u0006\u0010:\u001a\u00020\nH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R*\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016@\u0016X\u0097\u000er\u0002\b\u0019¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirLiteralExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "kind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "value", Argument.Delimiters.none, "prefix", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/types/ConstantValueKind;Ljava/lang/Object;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getKind", "()Lorg/jetbrains/kotlin/types/ConstantValueKind;", "setKind", "(Lorg/jetbrains/kotlin/types/ConstantValueKind;)V", "getValue", "()Ljava/lang/Object;", "getPrefix", "()Ljava/lang/String;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirLiteralExpressionImpl;", "transformAnnotations", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceKind", "newKind", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLiteralExpressionImpl extends FirLiteralExpression {
    private List<FirAnnotation> annotations;
    private ConeKotlinType coneTypeOrNull;
    private ConstantValueKind kind;
    private final String prefix;
    private final KtSourceElement source;
    private final Object value;

    private FirLiteralExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List<FirAnnotation> list, ConstantValueKind constantValueKind, Object obj, String str) {
        constantValueKind.getClass();
        this.source = ktSourceElement;
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.kind = constantValueKind;
        this.value = obj;
        this.prefix = str;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m480getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m480getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m480getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression
    public ConstantValueKind getKind() {
        return this.kind;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression
    public Object getValue() {
        return this.value;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m481setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression
    public void replaceKind(ConstantValueKind newKind) {
        newKind.getClass();
        setKind(newKind);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m481setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setKind(ConstantValueKind constantValueKind) {
        constantValueKind.getClass();
        this.kind = constantValueKind;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirLiteralExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m480getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirLiteralExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirLiteralExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLiteralExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirLiteralExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List list, ConstantValueKind constantValueKind, Object obj, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, coneKotlinType, list, constantValueKind, obj, str);
    }
}
