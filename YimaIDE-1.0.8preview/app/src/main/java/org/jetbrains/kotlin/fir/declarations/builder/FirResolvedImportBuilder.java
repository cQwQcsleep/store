package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedImportImpl;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fÊ\u0001\u0002\b\u0016¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirResolvedImportBuilder;", Argument.Delimiters.none, "<init>", "()V", "delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "getDelegate", "()Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "setDelegate", "(Lorg/jetbrains/kotlin/fir/declarations/FirImport;)V", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "setPackageFqName", "(Lorg/jetbrains/kotlin/name/FqName;)V", "relativeParentClassName", "getRelativeParentClassName", "setRelativeParentClassName", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedImportBuilder {
    public FirImport delegate;
    public FqName packageFqName;
    private FqName relativeParentClassName;

    public final FirResolvedImport build() {
        return new FirResolvedImportImpl(getDelegate(), getPackageFqName(), this.relativeParentClassName);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirImport getDelegate() throws UninitializedPropertyAccessException {
        FirImport firImport = this.delegate;
        if (firImport != null) {
            return firImport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("delegate");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FqName getPackageFqName() throws UninitializedPropertyAccessException {
        FqName fqName = this.packageFqName;
        if (fqName != null) {
            return fqName;
        }
        Intrinsics.throwUninitializedPropertyAccessException("packageFqName");
        return null;
    }

    public final FqName getRelativeParentClassName() {
        return this.relativeParentClassName;
    }

    public final void setDelegate(FirImport firImport) {
        firImport.getClass();
        this.delegate = firImport;
    }

    public final void setPackageFqName(FqName fqName) {
        fqName.getClass();
        this.packageFqName = fqName;
    }

    public final void setRelativeParentClassName(FqName fqName) {
        this.relativeParentClassName = fqName;
    }
}
