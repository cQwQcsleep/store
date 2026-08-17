package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010$\u001a\u0002H%\"\u0004\b\u0000\u0010%\"\u0004\b\u0001\u0010&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002H&0(2\u0006\u0010)\u001a\u0002H&H\u0016¢\u0006\u0002\u0010*J3\u0010+\u001a\u0002H,\"\b\b\u0000\u0010,*\u00020-\"\u0004\b\u0001\u0010&2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H&0/2\u0006\u0010)\u001a\u0002H&H\u0016¢\u0006\u0002\u00100J\u0016\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u0012\u00104\u001a\u0002022\b\u00105\u001a\u0004\u0018\u00010\u001bH&J)\u00106\u001a\u00020\u0000\"\u0004\b\u0000\u0010&2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H&0/2\u0006\u0010)\u001a\u0002H&H&¢\u0006\u0002\u00107J)\u00108\u001a\u00020\u0000\"\u0004\b\u0000\u0010&2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H&0/2\u0006\u0010)\u001a\u0002H&H&¢\u0006\u0002\u00107R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u0004\u0018\u00010\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0019¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/diagnostics/FirDiagnosticHolder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "customRenderer", Argument.Delimiters.none, "getCustomRenderer", "()Z", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "delegatedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getDelegatedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "partiallyResolvedTypeRef", "getPartiallyResolvedTypeRef", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", Argument.Delimiters.none, "newAnnotations", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "transformPartiallyResolvedTypeRef", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirErrorTypeRef extends FirResolvedTypeRef implements FirDiagnosticHolder {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitErrorTypeRef(this, data);
    }

    public abstract List<FirAnnotation> getAnnotations();

    public abstract ConeKotlinType getConeType();

    public abstract boolean getCustomRenderer();

    public abstract FirTypeRef getDelegatedTypeRef();

    @Override // org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
    public abstract ConeDiagnostic getDiagnostic();

    public abstract FirTypeRef getPartiallyResolvedTypeRef();

    public abstract FirResolvedSymbolOrigin getResolvedSymbolOrigin();

    @Override // org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    public abstract void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTypeRef firTypeRefTransformErrorTypeRef = transformer.transformErrorTypeRef(this, data);
        firTypeRefTransformErrorTypeRef.getClass();
        return firTypeRefTransformErrorTypeRef;
    }

    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m702transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public abstract <D> FirErrorTypeRef m702transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirErrorTypeRef transformPartiallyResolvedTypeRef(FirTransformer<? super D> transformer, D data);

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirResolvedTypeRef m703transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m702transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirTypeRef m704transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m702transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
