package org.jetbrains.kotlin.fir.expressions.impl;

import defpackage.ia5;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u001a\"\u0004\b\u0001\u0010\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001b0\u001d2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J)\u0010$\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J)\u0010%\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J)\u0010&\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J\u0012\u0010'\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\tH\u0016J\u0016\u0010)\u001a\u00020\u00192\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u0016H\u0016R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirLazyBlockImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "statements", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getStatements", "isUnitCoerced", Argument.Delimiters.none, "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirLazyBlockImpl;", "transformAnnotations", "transformStatements", "transformOtherChildren", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceIsUnitCoerced", "newIsUnitCoerced", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyBlockImpl extends FirLazyBlock {
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        throw new IllegalStateException("FirLazyBlock should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        throw new IllegalStateException("FirLazyBlock should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        throw new IllegalStateException("FirLazyBlock should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public List<FirStatement> getStatements() {
        throw new IllegalStateException("FirLazyBlock should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    /* JADX INFO: renamed from: isUnitCoerced */
    public boolean getIsUnitCoerced() {
        throw new IllegalStateException("FirLazyBlock should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        if (Intrinsics.areEqual(newConeTypeOrNull, getConeTypeOrNull())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(FirLazyBlockImpl.class.getSimpleName());
        sb.append(".replaceConeTypeOrNull() called with invalid type '");
        sb.append(newConeTypeOrNull);
        ia5.a(sb, "'. Current type is '", getConeTypeOrNull(), 39);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public void replaceIsUnitCoerced(boolean newIsUnitCoerced) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirLazyBlockImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public /* bridge */ /* synthetic */ FirBlock transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public /* bridge */ /* synthetic */ FirBlock transformStatements(FirTransformer firTransformer, Object obj) {
        return transformStatements((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirLazyBlockImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public <D> FirLazyBlockImpl transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public <D> FirLazyBlockImpl transformStatements(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirBlock transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public /* bridge */ /* synthetic */ FirLazyBlock transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock
    public /* bridge */ /* synthetic */ FirLazyBlock transformStatements(FirTransformer firTransformer, Object obj) {
        return transformStatements((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirLazyBlock transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLazyBlock, org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
