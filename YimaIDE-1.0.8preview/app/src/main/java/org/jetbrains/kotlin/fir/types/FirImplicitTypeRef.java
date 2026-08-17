package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00152\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u0017J3\u0010\u0018\u001a\u0002H\u0019\"\b\b\u0000\u0010\u0019*\u00020\u001a\"\u0004\b\u0001\u0010\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001c2\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\tH&J)\u0010!\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001c2\u0006\u0010\u0016\u001a\u0002H\u0013H&¢\u0006\u0002\u0010\"R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "customRenderer", "", "getCustomRenderer", "()Z", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", "", "newAnnotations", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class FirImplicitTypeRef extends FirTypeRef {
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return (R) visitor.visitImplicitTypeRef(this, data);
    }

    public abstract List<FirAnnotation> getAnnotations();

    public abstract boolean getCustomRenderer();

    public abstract KtSourceElement getSource();

    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTypeRef firTypeRefTransformImplicitTypeRef = transformer.transformImplicitTypeRef(this, data);
        firTypeRefTransformImplicitTypeRef.getClass();
        return firTypeRefTransformImplicitTypeRef;
    }

    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo38transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public abstract <D> FirImplicitTypeRef mo38transformAnnotations(FirTransformer<? super D> transformer, D data);

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirTypeRef mo39transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo38transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
