package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.types.SmartcastStability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B]\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J5\u00106\u001a\u000207\"\u0004\b\u0000\u00108\"\u0004\b\u0001\u001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u0002H90;2\u0006\u0010<\u001a\u0002H9H\u0016¢\u0006\u0002\u0010=J)\u0010>\u001a\u00020\u0000\"\u0004\b\u0000\u001092\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H90@2\u0006\u0010<\u001a\u0002H9H\u0016¢\u0006\u0002\u0010AJ)\u0010B\u001a\u00020\u0000\"\u0004\b\u0000\u001092\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H90@2\u0006\u0010<\u001a\u0002H9H\u0016¢\u0006\u0002\u0010AJ)\u0010C\u001a\u00020\u0000\"\u0004\b\u0000\u001092\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H90@2\u0006\u0010<\u001a\u0002H9H\u0016¢\u0006\u0002\u0010AJ\u0012\u0010D\u001a\u0002072\b\u0010E\u001a\u0004\u0018\u00010\u0003H\u0016J\u0016\u0010F\u001a\u0002072\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00060HH\u0016J\u0010\u0010I\u001a\u0002072\u0006\u0010J\u001a\u00020\bH\u0016R*\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0097\u000er\u0002\b\u001a¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010(\"\u0004\b,\u0010*R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0016\u0010/\u001a\u0004\u0018\u0001008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u0002048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00105¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirSmartCastExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "originalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "upperTypesFromSmartCast", Argument.Delimiters.none, "lowerTypesFromSmartCast", "Lorg/jetbrains/kotlin/fir/DfaType;", "smartcastType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "smartcastTypeWithoutNullableNothing", "smartcastStability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Ljava/util/Collection;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/types/SmartcastStability;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getOriginalExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setOriginalExpression", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getUpperTypesFromSmartCast", "()Ljava/util/Collection;", "getLowerTypesFromSmartCast", "getSmartcastType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setSmartcastType", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getSmartcastTypeWithoutNullableNothing", "setSmartcastTypeWithoutNullableNothing", "getSmartcastStability", "()Lorg/jetbrains/kotlin/types/SmartcastStability;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "isStable", Argument.Delimiters.none, "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirSmartCastExpressionImpl;", "transformAnnotations", "transformOriginalExpression", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceOriginalExpression", "newOriginalExpression", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSmartCastExpressionImpl extends FirSmartCastExpression {
    private List<FirAnnotation> annotations;
    private ConeKotlinType coneTypeOrNull;
    private final Collection<DfaType> lowerTypesFromSmartCast;
    private FirExpression originalExpression;
    private final SmartcastStability smartcastStability;
    private FirTypeRef smartcastType;
    private FirTypeRef smartcastTypeWithoutNullableNothing;
    private final Collection<ConeKotlinType> upperTypesFromSmartCast;

    /* JADX WARN: Multi-variable type inference failed */
    private FirSmartCastExpressionImpl(ConeKotlinType coneKotlinType, List<FirAnnotation> list, FirExpression firExpression, Collection<? extends ConeKotlinType> collection, Collection<? extends DfaType> collection2, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, SmartcastStability smartcastStability) {
        firExpression.getClass();
        collection.getClass();
        collection2.getClass();
        firTypeRef.getClass();
        smartcastStability.getClass();
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.originalExpression = firExpression;
        this.upperTypesFromSmartCast = collection;
        this.lowerTypesFromSmartCast = collection2;
        this.smartcastType = firTypeRef;
        this.smartcastTypeWithoutNullableNothing = firTypeRef2;
        this.smartcastStability = smartcastStability;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m508getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getOriginalExpression().accept(visitor, data);
        getSmartcastType().accept(visitor, data);
        FirTypeRef smartcastTypeWithoutNullableNothing = getSmartcastTypeWithoutNullableNothing();
        if (smartcastTypeWithoutNullableNothing != null) {
            smartcastTypeWithoutNullableNothing.accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m508getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m508getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public Collection<DfaType> getLowerTypesFromSmartCast() {
        return this.lowerTypesFromSmartCast;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public FirExpression getOriginalExpression() {
        return this.originalExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public SmartcastStability getSmartcastStability() {
        return this.smartcastStability;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public FirTypeRef getSmartcastType() {
        return this.smartcastType;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public FirTypeRef getSmartcastTypeWithoutNullableNothing() {
        return this.smartcastTypeWithoutNullableNothing;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        KtSourceElement source = getOriginalExpression().getSource();
        if (source != null) {
            return KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SmartCastExpression.INSTANCE, null, 2, null);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public Collection<ConeKotlinType> getUpperTypesFromSmartCast() {
        return this.upperTypesFromSmartCast;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public boolean isStable() {
        return getSmartcastStability() == SmartcastStability.STABLE_VALUE;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m509setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public void replaceOriginalExpression(FirExpression newOriginalExpression) {
        newOriginalExpression.getClass();
        setOriginalExpression(newOriginalExpression);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m509setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setOriginalExpression(FirExpression firExpression) {
        firExpression.getClass();
        this.originalExpression = firExpression;
    }

    public void setSmartcastType(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.smartcastType = firTypeRef;
    }

    public void setSmartcastTypeWithoutNullableNothing(FirTypeRef firTypeRef) {
        this.smartcastTypeWithoutNullableNothing = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirSmartCastExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m508getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirSmartCastExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformOriginalExpression((FirTransformer) transformer, (Object) data);
        setSmartcastType((FirTypeRef) getSmartcastType().transform(transformer, data));
        FirTypeRef smartcastTypeWithoutNullableNothing = getSmartcastTypeWithoutNullableNothing();
        setSmartcastTypeWithoutNullableNothing(smartcastTypeWithoutNullableNothing != null ? (FirTypeRef) smartcastTypeWithoutNullableNothing.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public <D> FirSmartCastExpressionImpl transformOriginalExpression(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setOriginalExpression((FirExpression) getOriginalExpression().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirSmartCastExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression
    public /* bridge */ /* synthetic */ FirSmartCastExpression transformOriginalExpression(FirTransformer firTransformer, Object obj) {
        return transformOriginalExpression((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirSmartCastExpressionImpl(ConeKotlinType coneKotlinType, List list, FirExpression firExpression, Collection collection, Collection collection2, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, SmartcastStability smartcastStability, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType, list, firExpression, collection, collection2, firTypeRef, firTypeRef2, smartcastStability);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
