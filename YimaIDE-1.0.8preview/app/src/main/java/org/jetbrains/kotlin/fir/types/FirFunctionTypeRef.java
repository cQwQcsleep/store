package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u001e\u001a\u0002H\u001f\"\u0004\b\u0000\u0010\u001f\"\u0004\b\u0001\u0010 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H 0\"2\u0006\u0010#\u001a\u0002H H\u0016¢\u0006\u0002\u0010$J3\u0010%\u001a\u0002H&\"\b\b\u0000\u0010&*\u00020'\"\u0004\b\u0001\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H\u0016¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J)\u0010.\u001a\u00020\u0000\"\u0004\b\u0000\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H&¢\u0006\u0002\u0010/R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\bR\u0012\u0010\u0019\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015R\u0012\u0010\u001b\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\fR\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\b¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "<init>", "()V", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "customRenderer", "", "getCustomRenderer", "()Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "isMarkedNullable", "receiverTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReceiverTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "parameters", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "getParameters", "returnTypeRef", "getReturnTypeRef", "isSuspend", "contextParameterTypeRefs", "getContextParameterTypeRefs", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", "", "newAnnotations", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class FirFunctionTypeRef extends FirUnresolvedTypeRef {
    public FirFunctionTypeRef() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return (R) visitor.visitFunctionTypeRef(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public abstract List<FirAnnotation> getAnnotations();

    public abstract List<FirTypeRef> getContextParameterTypeRefs();

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public abstract boolean getCustomRenderer();

    public abstract List<FirFunctionTypeParameter> getParameters();

    public abstract FirTypeRef getReceiverTypeRef();

    public abstract FirTypeRef getReturnTypeRef();

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public abstract boolean isMarkedNullable();

    public abstract boolean isSuspend();

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTypeRef firTypeRefTransformFunctionTypeRef = transformer.transformFunctionTypeRef(this, data);
        firTypeRefTransformFunctionTypeRef.getClass();
        return firTypeRefTransformFunctionTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public abstract <D> FirFunctionTypeRef mo42transformAnnotations(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirTypeRef mo42transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirUnresolvedTypeRef mo37transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
