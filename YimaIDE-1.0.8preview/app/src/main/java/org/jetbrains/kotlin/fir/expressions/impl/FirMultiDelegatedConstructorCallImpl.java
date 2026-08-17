package org.jetbrains.kotlin.fir.expressions.impl;

import defpackage.ia5;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
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
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a\u0002\b\u0007¢\u0006\u0004\b\u0005\u0010\u0006J5\u0010.\u001a\u00020/\"\u0004\b\u0000\u00100\"\u0004\b\u0001\u001012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H1032\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00105J)\u00106\u001a\u00020\u0000\"\u0004\b\u0000\u001012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H1082\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00109J)\u0010:\u001a\u00020\u0000\"\u0004\b\u0000\u001012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H1082\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00109J)\u0010;\u001a\u00020\u0000\"\u0004\b\u0000\u001012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H1082\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00109J)\u0010<\u001a\u00020\u0000\"\u0004\b\u0000\u001012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H1082\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00109J)\u0010=\u001a\u00020\u0000\"\u0004\b\u0000\u001012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H1082\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00109J)\u0010>\u001a\u00020\u0000\"\u0004\b\u0000\u001012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H1082\u0006\u00104\u001a\u0002H1H\u0016¢\u0006\u0002\u00109J\u0016\u0010?\u001a\u00020/2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0010\u0010A\u001a\u00020/2\u0006\u0010B\u001a\u00020\u000fH\u0016J\u0016\u0010C\u001a\u00020/2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00130\u000bH\u0016J\u0012\u0010E\u001a\u00020/2\b\u0010F\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010G\u001a\u00020/2\u0006\u0010H\u001a\u00020\u001cH\u0016J\u0012\u0010I\u001a\u00020/2\b\u0010J\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010K\u001a\u00020/2\u0006\u0010L\u001a\u00020#H\u0016J\u0016\u0010M\u001a\u00020/2\b\u0010N\u001a\u0004\u0018\u00010'H\u0017b\u0002\b\u0007J\u0016\u0010O\u001a\u00020/2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\tR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0016\u0010&\u001a\u0004\u0018\u00010'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010,R\u0014\u0010-\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010,¨\u0006Q"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirMultiDelegatedConstructorCallImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "delegatedConstructorCalls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "<init>", "(Ljava/util/List;)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getDelegatedConstructorCalls", "()Ljava/util/List;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "getArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "constructedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getConstructedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "dispatchReceiver", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "isThis", Argument.Delimiters.none, "()Z", "isSuper", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirMultiDelegatedConstructorCallImpl;", "transformAnnotations", "transformContextArguments", "transformDispatchReceiver", "transformCalleeReference", "transformDelegatedConstructorCalls", "replaceAnnotations", "newAnnotations", "replaceArgumentList", "newArgumentList", "replaceContextArguments", "newContextArguments", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceConstructedTypeRef", "newConstructedTypeRef", "replaceDispatchReceiver", "newDispatchReceiver", "replaceCalleeReference", "newCalleeReference", "replaceSource", "newSource", "replaceDelegatedConstructorCalls", "newDelegatedConstructorCalls", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMultiDelegatedConstructorCallImpl extends FirMultiDelegatedConstructorCall {
    private final List<FirDelegatedConstructorCall> delegatedConstructorCalls;

    @FirImplementationDetail
    public FirMultiDelegatedConstructorCallImpl(List<FirDelegatedConstructorCall> list) {
        list.getClass();
        this.delegatedConstructorCalls = list;
    }

    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = getDelegatedConstructorCalls().iterator();
        while (it.hasNext()) {
            ((FirDelegatedConstructorCall) it.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getAnnotations();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirCall
    public FirArgumentList getArgumentList() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getArgumentList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirReference getCalleeReference() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getCalleeReference();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getConeTypeOrNull();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public FirTypeRef getConstructedTypeRef() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getConstructedTypeRef();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public List<FirExpression> getContextArguments() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getContextArguments();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall
    public List<FirDelegatedConstructorCall> getDelegatedConstructorCalls() {
        return this.delegatedConstructorCalls;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public FirExpression getDispatchReceiver() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getDispatchReceiver();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getSource();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public boolean isSuper() {
        return !getIsThis();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    /* JADX INFO: renamed from: isThis */
    public boolean getIsThis() {
        return ((FirDelegatedConstructorCall) CollectionsKt.last(getDelegatedConstructorCalls())).getIsThis();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirCall
    public void replaceArgumentList(FirArgumentList newArgumentList) {
        newArgumentList.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        if (Intrinsics.areEqual(newConeTypeOrNull, getConeTypeOrNull())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(FirMultiDelegatedConstructorCallImpl.class.getSimpleName());
        sb.append(".replaceConeTypeOrNull() called with invalid type '");
        sb.append(newConeTypeOrNull);
        ia5.a(sb, "'. Current type is '", getConeTypeOrNull(), 39);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public void replaceConstructedTypeRef(FirTypeRef newConstructedTypeRef) {
        newConstructedTypeRef.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public void replaceContextArguments(List<? extends FirExpression> newContextArguments) {
        newContextArguments.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall
    public void replaceDelegatedConstructorCalls(List<? extends FirDelegatedConstructorCall> newDelegatedConstructorCalls) {
        newDelegatedConstructorCalls.getClass();
        if (getDelegatedConstructorCalls() == newDelegatedConstructorCalls) {
            return;
        }
        getDelegatedConstructorCalls().clear();
        getDelegatedConstructorCalls().addAll(newDelegatedConstructorCalls);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public void replaceDispatchReceiver(FirExpression newDispatchReceiver) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    @FirImplementationDetail
    public void replaceSource(KtSourceElement newSource) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirMultiDelegatedConstructorCallImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformDelegatedConstructorCalls((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirContextArgumentListOwner transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall
    public <D> FirMultiDelegatedConstructorCallImpl transformDelegatedConstructorCalls(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getDelegatedConstructorCalls(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformDispatchReceiver(FirTransformer firTransformer, Object obj) {
        return transformDispatchReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirMultiDelegatedConstructorCallImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirMultiDelegatedConstructorCallImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public <D> FirMultiDelegatedConstructorCallImpl transformContextArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public <D> FirMultiDelegatedConstructorCallImpl transformDispatchReceiver(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirMultiDelegatedConstructorCall transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
    public /* bridge */ /* synthetic */ FirMultiDelegatedConstructorCall transformDispatchReceiver(FirTransformer firTransformer, Object obj) {
        return transformDispatchReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDelegatedConstructorCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirMultiDelegatedConstructorCall transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMultiDelegatedConstructorCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall
    public /* bridge */ /* synthetic */ FirMultiDelegatedConstructorCall transformDelegatedConstructorCalls(FirTransformer firTransformer, Object obj) {
        return transformDelegatedConstructorCalls((FirTransformer<? super Object>) firTransformer, obj);
    }
}
