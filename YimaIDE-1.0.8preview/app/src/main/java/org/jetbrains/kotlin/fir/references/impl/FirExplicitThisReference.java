package org.jetbrains.kotlin.fir.references.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0 2\u0006\u0010!\u001a\u0002H\u001eH\u0016¢\u0006\u0002\u0010\"J)\u0010#\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u0006\u0010!\u001a\u0002H\u001eH\u0016¢\u0006\u0002\u0010&J\u0016\u0010'\u001a\u00020\u001c2\f\u0010(\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013H\u0016J\u0012\u0010)\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u0007H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0019X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u001a¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/references/impl/FirExplicitThisReference;", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "labelName", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getLabelName", "()Ljava/lang/String;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "setDiagnostic", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "boundSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "getBoundSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "setBoundSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;)V", "isImplicit", Argument.Delimiters.none, "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/references/impl/FirExplicitThisReference;", "replaceBoundSymbol", "newBoundSymbol", "replaceDiagnostic", "newDiagnostic", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExplicitThisReference extends FirThisReference {
    private FirThisOwnerSymbol<?> boundSymbol;
    private ConeDiagnostic diagnostic;
    private final boolean isImplicit;
    private final String labelName;
    private final KtSourceElement source;

    public FirExplicitThisReference(KtSourceElement ktSourceElement, String str, ConeDiagnostic coneDiagnostic) {
        this.source = ktSourceElement;
        this.labelName = str;
        this.diagnostic = coneDiagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference
    public FirThisOwnerSymbol<?> getBoundSymbol() {
        return this.boundSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference
    public ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference
    public String getLabelName() {
        return this.labelName;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference
    /* JADX INFO: renamed from: isImplicit, reason: from getter */
    public boolean getIsImplicit() {
        return this.isImplicit;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference
    public void replaceBoundSymbol(FirThisOwnerSymbol<?> newBoundSymbol) {
        setBoundSymbol(newBoundSymbol);
    }

    @Override // org.jetbrains.kotlin.fir.references.FirThisReference
    public void replaceDiagnostic(ConeDiagnostic newDiagnostic) {
        setDiagnostic(newDiagnostic);
    }

    public void setBoundSymbol(FirThisOwnerSymbol<?> firThisOwnerSymbol) {
        this.boundSymbol = firThisOwnerSymbol;
    }

    public void setDiagnostic(ConeDiagnostic coneDiagnostic) {
        this.diagnostic = coneDiagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirExplicitThisReference transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
