package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010'\"\u0004\b\u0001\u0010(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H(0*2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u0010,J)\u0010-\u001a\u00020\u0000\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00100J\u0012\u00101\u001a\u00020&2\b\u00102\u001a\u0004\u0018\u00010\u001aH\u0016J)\u00103\u001a\u00020\u0001\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00104J)\u00105\u001a\u00020\u0001\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00104J\u0016\u00106\u001a\u00020&2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eH\u0016J)\u00108\u001a\u00020\u0001\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00104J\u0010\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020#H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0017R*\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0097\u000er\u0002\b!¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010$¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirContractCallBlock;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "getCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "setCall", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "statements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getStatements", "()Ljava/util/List;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations-5e3fPpI", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "isUnitCoerced", Argument.Delimiters.none, "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirContractCallBlock;", "replaceConeTypeOrNull", "newConeTypeOrNull", "transformStatements", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "transformOtherChildren", "replaceAnnotations", "newAnnotations", "transformAnnotations", "replaceIsUnitCoerced", "newIsUnitCoerced", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractCallBlock extends FirBlock {
    private List<FirAnnotation> annotations;
    private FirFunctionCall call;
    private ConeKotlinType coneTypeOrNull;

    public FirContractCallBlock(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        this.call = firFunctionCall;
        this.annotations = MutableOrEmptyList.INSTANCE.m213empty5e3fPpI();
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        this.call.accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m414getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m414getAnnotations5e3fPpI() {
        return this.annotations;
    }

    public final FirFunctionCall getCall() {
        return this.call;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        KtSourceElement source = this.call.getSource();
        if (source != null) {
            return KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ContractBlock.INSTANCE, null, 2, null);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock
    public List<FirStatement> getStatements() {
        return CollectionsKt.listOf(this.call);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock
    /* JADX INFO: renamed from: isUnitCoerced */
    public boolean getIsUnitCoerced() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m415setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock
    public void replaceIsUnitCoerced(boolean newIsUnitCoerced) {
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m415setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public final void setCall(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        this.call = firFunctionCall;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirBlock transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m414getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirContractCallBlock transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformStatements(transformer, data);
        transformOtherChildren(transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock
    public <D> FirBlock transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock
    public <D> FirBlock transformStatements(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        this.call = (FirFunctionCall) FirTransformerUtilKt.transformSingle(this.call, transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirBlock, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
