package org.jetbrains.kotlin.fir.types.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J)\u0010\u0018\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010\u001dJ5\u0010\u001e\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u001f\"\u0004\b\u0001\u0010\u00192\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u00190!2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010\"J)\u0010#\u001a\u00020$\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010%R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/ResolvedImplicitTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "customRenderer", Argument.Delimiters.none, "getCustomRenderer", "()Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "replaceAnnotations", Argument.Delimiters.none, "newAnnotations", "transformAnnotations", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "acceptChildren", "R", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolvedImplicitTypeRef extends FirImplicitTypeRef {
    private final FirResolvedTypeRef typeRef;

    public ResolvedImplicitTypeRef(FirResolvedTypeRef firResolvedTypeRef) {
        firResolvedTypeRef.getClass();
        this.typeRef = firResolvedTypeRef;
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    public List<FirAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    public boolean getCustomRenderer() {
        return false;
    }

    public KtSourceElement getSource() {
        return null;
    }

    public final FirResolvedTypeRef getTypeRef() {
        return this.typeRef;
    }

    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m707transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <D> FirElement transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public <D> FirImplicitTypeRef m707transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirTypeRef m708transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m707transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
