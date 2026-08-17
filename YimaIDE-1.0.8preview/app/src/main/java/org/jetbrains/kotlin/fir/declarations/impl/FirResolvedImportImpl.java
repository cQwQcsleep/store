package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ5\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010'\"\u0004\b\u0001\u0010(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H(0*2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u0010,J)\u0010-\u001a\u00020\u0000\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00100R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000eR\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0013R\u0016\u0010\u001f\u001a\u0004\u0018\u00010 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0016\u0010#\u001a\u0004\u0018\u00010\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001c¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedImportImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeParentClassName", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirImport;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;)V", "getDelegate", "()Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "setDelegate", "(Lorg/jetbrains/kotlin/fir/declarations/FirImport;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getRelativeParentClassName", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "importedFqName", "getImportedFqName", "isAllUnder", Argument.Delimiters.none, "()Z", "aliasName", "Lorg/jetbrains/kotlin/name/Name;", "getAliasName", "()Lorg/jetbrains/kotlin/name/Name;", "aliasSource", "getAliasSource", "resolvedParentClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getResolvedParentClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "importedName", "getImportedName", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedImportImpl;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedImportImpl extends FirResolvedImport {
    private FirImport delegate;
    private final FqName packageFqName;
    private final FqName relativeParentClassName;

    public FirResolvedImportImpl(FirImport firImport, FqName fqName, FqName fqName2) {
        firImport.getClass();
        fqName.getClass();
        this.delegate = firImport;
        this.packageFqName = fqName;
        this.relativeParentClassName = fqName2;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport, org.jetbrains.kotlin.fir.declarations.FirImport
    public Name getAliasName() {
        return getDelegate().getAliasName();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport, org.jetbrains.kotlin.fir.declarations.FirImport
    public KtSourceElement getAliasSource() {
        return getDelegate().getAliasSource();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport
    public FirImport getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport, org.jetbrains.kotlin.fir.declarations.FirImport
    public FqName getImportedFqName() {
        return getDelegate().getImportedFqName();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport
    public Name getImportedName() {
        FqName importedFqName = getImportedFqName();
        if (importedFqName != null) {
            return importedFqName.shortName();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport
    public FqName getPackageFqName() {
        return this.packageFqName;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport
    public FqName getRelativeParentClassName() {
        return this.relativeParentClassName;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport
    public ClassId getResolvedParentClassId() {
        FqName relativeParentClassName = getRelativeParentClassName();
        if (relativeParentClassName != null) {
            return new ClassId(getPackageFqName(), relativeParentClassName, false);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport, org.jetbrains.kotlin.fir.declarations.FirImport, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return getDelegate().getSource();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedImport, org.jetbrains.kotlin.fir.declarations.FirImport
    /* JADX INFO: renamed from: isAllUnder */
    public boolean getIsAllUnder() {
        return getDelegate().getIsAllUnder();
    }

    public void setDelegate(FirImport firImport) {
        firImport.getClass();
        this.delegate = firImport;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirResolvedImportImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
