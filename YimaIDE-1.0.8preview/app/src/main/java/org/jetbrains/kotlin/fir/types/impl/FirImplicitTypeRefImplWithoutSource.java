package org.jetbrains.kotlin.fir.types.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J)\u0010\u0014\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019J5\u0010\u001a\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u001b\"\u0004\b\u0001\u0010\u00152\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u00150\u001d2\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u001eJ)\u0010\u001f\u001a\u00020 \"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010!R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirImplicitTypeRefImplWithoutSource;", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "<init>", "()V", "customRenderer", "", "getCustomRenderer", "()Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "replaceAnnotations", "", "newAnnotations", "transformAnnotations", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "acceptChildren", "R", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirImplicitTypeRefImplWithoutSource extends FirImplicitTypeRef {
    public static final FirImplicitTypeRefImplWithoutSource INSTANCE = new FirImplicitTypeRefImplWithoutSource();

    private FirImplicitTypeRefImplWithoutSource() {
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    public List<FirAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    public boolean getCustomRenderer() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    public KtSourceElement getSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo38transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public <D> FirElement transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public <D> FirImplicitTypeRef mo38transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirTypeRef mo39transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo38transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
