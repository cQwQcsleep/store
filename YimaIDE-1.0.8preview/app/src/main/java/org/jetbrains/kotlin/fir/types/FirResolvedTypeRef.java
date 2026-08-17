package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.mpp.TypeRefMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e\"\u0004\b\u0001\u0010\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u001f0!2\u0006\u0010\"\u001a\u0002H\u001fH\u0016¢\u0006\u0002\u0010#J3\u0010$\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020&\"\u0004\b\u0001\u0010\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u001f0(2\u0006\u0010\"\u001a\u0002H\u001fH\u0016¢\u0006\u0002\u0010)J\u0016\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u0012\u0010-\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010\u001aH&J)\u0010/\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u001f0(2\u0006\u0010\"\u001a\u0002H\u001fH&¢\u0006\u0002\u00100R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/mpp/TypeRefMarker;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "customRenderer", "", "getCustomRenderer", "()Z", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "delegatedTypeRef", "getDelegatedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", "", "newAnnotations", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class FirResolvedTypeRef extends FirTypeRef implements TypeRefMarker {
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return (R) visitor.visitResolvedTypeRef(this, data);
    }

    public abstract List<FirAnnotation> getAnnotations();

    /* JADX INFO: renamed from: getConeType */
    public abstract ConeKotlinType mo64getConeType();

    public abstract boolean getCustomRenderer();

    public abstract FirTypeRef getDelegatedTypeRef();

    public abstract FirResolvedSymbolOrigin getResolvedSymbolOrigin();

    public abstract KtSourceElement getSource();

    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    public abstract void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin);

    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTypeRef firTypeRefTransformResolvedTypeRef = transformer.transformResolvedTypeRef(this, data);
        firTypeRefTransformResolvedTypeRef.getClass();
        return firTypeRefTransformResolvedTypeRef;
    }

    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo40transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public abstract <D> FirResolvedTypeRef mo40transformAnnotations(FirTransformer<? super D> transformer, D data);

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirTypeRef mo41transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo40transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
