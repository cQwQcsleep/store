package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ5\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a2\u0006\u0010\u001b\u001a\u0002H\u0018H\u0016¢\u0006\u0002\u0010\u001cJ)\u0010\u001d\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001f2\u0006\u0010\u001b\u001a\u0002H\u0018H\u0016¢\u0006\u0002\u0010 R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0011R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirImportImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "importedFqName", "Lorg/jetbrains/kotlin/name/FqName;", "isAllUnder", Argument.Delimiters.none, "aliasName", "Lorg/jetbrains/kotlin/name/Name;", "aliasSource", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/FqName;ZLorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getImportedFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "()Z", "getAliasName", "()Lorg/jetbrains/kotlin/name/Name;", "getAliasSource", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirImportImpl;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImportImpl extends FirImport {
    private final Name aliasName;
    private final KtSourceElement aliasSource;
    private final FqName importedFqName;
    private final boolean isAllUnder;
    private final KtSourceElement source;

    public FirImportImpl(KtSourceElement ktSourceElement, FqName fqName, boolean z, Name name, KtSourceElement ktSourceElement2) {
        this.source = ktSourceElement;
        this.importedFqName = fqName;
        this.isAllUnder = z;
        this.aliasName = name;
        this.aliasSource = ktSourceElement2;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirImport
    public Name getAliasName() {
        return this.aliasName;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirImport
    public KtSourceElement getAliasSource() {
        return this.aliasSource;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirImport
    public FqName getImportedFqName() {
        return this.importedFqName;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirImport, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirImport
    /* JADX INFO: renamed from: isAllUnder, reason: from getter */
    public boolean getIsAllUnder() {
        return this.isAllUnder;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirImportImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
