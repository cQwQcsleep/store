package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00140\u00162\u0006\u0010\u0017\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010\u0018J3\u0010\u0019\u001a\u0002H\u001a\"\b\b\u0000\u0010\u001a*\u00020\u001b\"\u0004\b\u0001\u0010\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00140\u001d2\u0006\u0010\u0017\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J)\u0010\"\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00140\u001d2\u0006\u0010\u0017\u001a\u0002H\u0014H&¢\u0006\u0002\u0010#R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\f¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "<init>", "()V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "customRenderer", Argument.Delimiters.none, "getCustomRenderer", "()Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "isMarkedNullable", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", Argument.Delimiters.none, "newAnnotations", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDynamicTypeRef extends FirUnresolvedTypeRef {
    public FirDynamicTypeRef() {
        super((DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitDynamicTypeRef(this, data);
    }

    public abstract List<FirAnnotation> getAnnotations();

    public abstract boolean getCustomRenderer();

    public abstract KtSourceElement getSource();

    public abstract boolean isMarkedNullable();

    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    /* JADX WARN: Multi-variable type inference failed */
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTypeRef firTypeRefTransformDynamicTypeRef = transformer.transformDynamicTypeRef(this, data);
        firTypeRefTransformDynamicTypeRef.getClass();
        return firTypeRefTransformDynamicTypeRef;
    }

    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m699transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public abstract <D> FirDynamicTypeRef m699transformAnnotations(FirTransformer<? super D> transformer, D data);

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirTypeRef m700transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m699transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirUnresolvedTypeRef m701transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m699transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
