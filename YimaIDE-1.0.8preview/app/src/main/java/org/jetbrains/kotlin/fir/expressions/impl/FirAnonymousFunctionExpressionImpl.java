package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.RawFirApi;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u001f\u001a\u00020 \"\u0004\b\u0000\u0010!\"\u0004\b\u0001\u0010\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\"0$2\u0006\u0010%\u001a\u0002H\"H\u0016¢\u0006\u0002\u0010&J)\u0010'\u001a\u00020\u0000\"\u0004\b\u0000\u0010\"2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H\"0)2\u0006\u0010%\u001a\u0002H\"H\u0016¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020 2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\u0014\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\u0007H\u0017b\u0002\b/J)\u00100\u001a\u00020\u0000\"\u0004\b\u0000\u0010\"2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H\"0)2\u0006\u0010%\u001a\u0002H\"H\u0016¢\u0006\u0002\u0010*J)\u00101\u001a\u00020\u0000\"\u0004\b\u0000\u0010\"2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H\"0)2\u0006\u0010%\u001a\u0002H\"H\u0016¢\u0006\u0002\u0010*J\u0010\u00102\u001a\u00020 2\u0006\u00103\u001a\u00020\u0005H\u0016J\u0012\u00104\u001a\u00020 2\b\u00105\u001a\u0004\u0018\u00010\u0014H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u0004\u0018\u00010\u00148VX\u0097\u0004r\u0002\b\u0019¢\u0006\f\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirAnonymousFunctionExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "isTrailingLambda", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Z)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getAnonymousFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "setAnonymousFunction", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "()Z", "setTrailingLambda", "(Z)V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirAnonymousFunctionExpressionImpl;", "replaceAnnotations", "newAnnotations", "replaceIsTrailingLambda", "newIsTrailingLambda", "Lorg/jetbrains/kotlin/fir/expressions/RawFirApi;", "transformAnnotations", "transformAnonymousFunction", "replaceAnonymousFunction", "newAnonymousFunction", "replaceConeTypeOrNull", "newConeTypeOrNull", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionExpressionImpl extends FirAnonymousFunctionExpression {
    private FirAnonymousFunction anonymousFunction;
    private boolean isTrailingLambda;
    private final KtSourceElement source;

    public FirAnonymousFunctionExpressionImpl(KtSourceElement ktSourceElement, FirAnonymousFunction firAnonymousFunction, boolean z) {
        firAnonymousFunction.getClass();
        this.source = ktSourceElement;
        this.anonymousFunction = firAnonymousFunction;
        this.isTrailingLambda = z;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getAnonymousFunction().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return getAnonymousFunction().getAnnotations();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
    public FirAnonymousFunction getAnonymousFunction() {
        return this.anonymousFunction;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return FirTypeUtilsKt.getConeTypeOrNull(getAnonymousFunction().getTypeRef());
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
    /* JADX INFO: renamed from: isTrailingLambda, reason: from getter */
    public boolean getIsTrailingLambda() {
        return this.isTrailingLambda;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        getAnonymousFunction().replaceAnnotations(newAnnotations);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
    public void replaceAnonymousFunction(FirAnonymousFunction newAnonymousFunction) {
        newAnonymousFunction.getClass();
        setAnonymousFunction(newAnonymousFunction);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled("anonymousFunction.replaceTypeRef() should be called instead");
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
    @RawFirApi
    public void replaceIsTrailingLambda(boolean newIsTrailingLambda) {
        setTrailingLambda(newIsTrailingLambda);
    }

    public void setAnonymousFunction(FirAnonymousFunction firAnonymousFunction) {
        firAnonymousFunction.getClass();
        this.anonymousFunction = firAnonymousFunction;
    }

    public void setTrailingLambda(boolean z) {
        this.isTrailingLambda = z;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
    public <D> FirAnonymousFunctionExpressionImpl transformAnonymousFunction(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setAnonymousFunction((FirAnonymousFunction) getAnonymousFunction().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirAnonymousFunctionExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return transformAnonymousFunction((FirTransformer) transformer, (Object) data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirAnonymousFunctionExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnonymousFunctionExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
    public /* bridge */ /* synthetic */ FirAnonymousFunctionExpression transformAnonymousFunction(FirTransformer firTransformer, Object obj) {
        return transformAnonymousFunction((FirTransformer<? super Object>) firTransformer, obj);
    }
}
