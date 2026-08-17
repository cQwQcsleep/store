package org.jetbrains.kotlin.fir.references.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.references.FirErrorSuperReference;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ5\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u0018\"\u0004\b\u0001\u0010\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010\u001dJ)\u0010\u001e\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00190 2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0007H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/references/impl/FirErrorSuperReferenceImpl;", "Lorg/jetbrains/kotlin/fir/references/FirErrorSuperReference;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "labelName", Argument.Delimiters.none, "superTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getLabelName", "()Ljava/lang/String;", "getSuperTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setSuperTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/references/impl/FirErrorSuperReferenceImpl;", "replaceSuperTypeRef", "newSuperTypeRef", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorSuperReferenceImpl extends FirErrorSuperReference {
    private final ConeDiagnostic diagnostic;
    private final String labelName;
    private final KtSourceElement source;
    private FirTypeRef superTypeRef;

    public FirErrorSuperReferenceImpl(KtSourceElement ktSourceElement, String str, FirTypeRef firTypeRef, ConeDiagnostic coneDiagnostic) {
        firTypeRef.getClass();
        coneDiagnostic.getClass();
        this.source = ktSourceElement;
        this.labelName = str;
        this.superTypeRef = firTypeRef;
        this.diagnostic = coneDiagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getSuperTypeRef().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.references.FirErrorSuperReference, org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
    public ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirErrorSuperReference, org.jetbrains.kotlin.fir.references.FirSuperReference
    public String getLabelName() {
        return this.labelName;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirErrorSuperReference, org.jetbrains.kotlin.fir.references.FirSuperReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirErrorSuperReference, org.jetbrains.kotlin.fir.references.FirSuperReference
    public FirTypeRef getSuperTypeRef() {
        return this.superTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirErrorSuperReference, org.jetbrains.kotlin.fir.references.FirSuperReference
    public void replaceSuperTypeRef(FirTypeRef newSuperTypeRef) {
        newSuperTypeRef.getClass();
        setSuperTypeRef(newSuperTypeRef);
    }

    public void setSuperTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.superTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirErrorSuperReferenceImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setSuperTypeRef((FirTypeRef) getSuperTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
