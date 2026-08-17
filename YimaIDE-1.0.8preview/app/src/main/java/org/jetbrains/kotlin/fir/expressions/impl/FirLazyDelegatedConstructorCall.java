package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u0001B/\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u001a\u0002\b\f¢\u0006\u0004\b\n\u0010\u000bJ5\u00101\u001a\u000202\"\u0004\b\u0000\u00103\"\u0004\b\u0001\u001042\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H4062\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u00108J)\u00109\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H40;2\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u0010<J)\u0010=\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H40;2\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u0010<J)\u0010>\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H40;2\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u0010<J)\u0010?\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H40;2\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u0010<J)\u0010@\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H40;2\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u0010<J\u0016\u0010A\u001a\u0002022\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u0010\u0010C\u001a\u0002022\u0006\u0010D\u001a\u00020#H\u0016J\u0016\u0010E\u001a\u0002022\f\u0010F\u001a\b\u0012\u0004\u0012\u00020'0\u001eH\u0016J\u0012\u0010G\u001a\u0002022\b\u0010H\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010I\u001a\u0002022\u0006\u0010J\u001a\u00020\u0005H\u0016J\u0012\u0010K\u001a\u0002022\b\u0010L\u001a\u0004\u0018\u00010'H\u0016J\u0010\u0010M\u001a\u0002022\u0006\u0010N\u001a\u00020\u0007H\u0016J\u0016\u0010O\u001a\u0002022\b\u0010P\u001a\u0004\u0018\u00010-H\u0017b\u0002\b\fR*\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0097\u000er\u0002\b\u0013¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010!R\u0016\u0010)\u001a\u0004\u0018\u00010'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0016\u0010,\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u001c¨\u0006Q"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirLazyDelegatedConstructorCall;", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "constructedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "isThis", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/references/FirReference;Z)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getConstructedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setConstructedTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "()Z", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "getArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments", "dispatchReceiver", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "isSuper", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirLazyDelegatedConstructorCall;", "transformAnnotations", "transformContextArguments", "transformDispatchReceiver", "transformCalleeReference", "replaceAnnotations", "newAnnotations", "replaceArgumentList", "newArgumentList", "replaceContextArguments", "newContextArguments", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceConstructedTypeRef", "newConstructedTypeRef", "replaceDispatchReceiver", "newDispatchReceiver", "replaceCalleeReference", "newCalleeReference", "replaceSource", "newSource", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyDelegatedConstructorCall extends FirDelegatedConstructorCall {
    private FirReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private FirTypeRef constructedTypeRef;
    private final boolean isThis;

    @FirImplementationDetail
    public FirLazyDelegatedConstructorCall(ConeKotlinType coneKotlinType, FirTypeRef firTypeRef, FirReference firReference, boolean z) {
        firTypeRef.getClass();
        firReference.getClass();
        this.coneTypeOrNull = coneKotlinType;
        this.constructedTypeRef = firTypeRef;
        this.calleeReference = firReference;
        this.isThis = z;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getConstructedTypeRef().accept(visitor, data);
        getCalleeReference().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        throw new IllegalStateException("FirLazyDelegatedConstructorCall should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirCall
    public FirArgumentList getArgumentList() {
        throw new IllegalStateException("FirLazyDelegatedConstructorCall should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public FirTypeRef getConstructedTypeRef() {
        return this.constructedTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public List<FirExpression> getContextArguments() {
        throw new IllegalStateException("FirLazyDelegatedConstructorCall should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public FirExpression getDispatchReceiver() {
        throw new IllegalStateException("FirLazyDelegatedConstructorCall should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        throw new IllegalStateException("FirLazyDelegatedConstructorCall should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public boolean isSuper() {
        return !getIsThis();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    /* JADX INFO: renamed from: isThis, reason: from getter */
    public boolean getIsThis() {
        return this.isThis;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirCall
    public void replaceArgumentList(FirArgumentList newArgumentList) {
        newArgumentList.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public void replaceConstructedTypeRef(FirTypeRef newConstructedTypeRef) {
        newConstructedTypeRef.getClass();
        setConstructedTypeRef(newConstructedTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public void replaceContextArguments(List<? extends FirExpression> newContextArguments) {
        newContextArguments.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public void replaceDispatchReceiver(FirExpression newDispatchReceiver) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    @FirImplementationDetail
    public void replaceSource(KtSourceElement newSource) {
    }

    public void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setConstructedTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.constructedTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirLazyDelegatedConstructorCall transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirLazyDelegatedConstructorCall transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setConstructedTypeRef((FirTypeRef) getConstructedTypeRef().transform(transformer, data));
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirContextArgumentListOwner transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformDispatchReceiver(FirTransformer firTransformer, Object obj) {
        return transformDispatchReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirLazyDelegatedConstructorCall transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public <D> FirLazyDelegatedConstructorCall transformContextArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public <D> FirLazyDelegatedConstructorCall transformDispatchReceiver(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
