package org.jetbrains.kotlin.fir.session;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", Argument.Delimiters.none, "previousFirSessionsSymbolProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "precompiledBinariesPackagePartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "precompiledBinariesFileScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "<init>", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;)V", "getPreviousFirSessionsSymbolProviders", "()Ljava/util/Collection;", "getPrecompiledBinariesPackagePartProvider", "()Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "getPrecompiledBinariesFileScope", "()Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class IncrementalCompilationContext {
    private final AbstractProjectFileSearchScope precompiledBinariesFileScope;
    private final PackagePartProvider precompiledBinariesPackagePartProvider;
    private final Collection<FirSymbolProvider> previousFirSessionsSymbolProviders;

    /* JADX WARN: Multi-variable type inference failed */
    public IncrementalCompilationContext(Collection<? extends FirSymbolProvider> collection, PackagePartProvider packagePartProvider, AbstractProjectFileSearchScope abstractProjectFileSearchScope) {
        collection.getClass();
        this.previousFirSessionsSymbolProviders = collection;
        this.precompiledBinariesPackagePartProvider = packagePartProvider;
        this.precompiledBinariesFileScope = abstractProjectFileSearchScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IncrementalCompilationContext copy$default(IncrementalCompilationContext incrementalCompilationContext, Collection collection, PackagePartProvider packagePartProvider, AbstractProjectFileSearchScope abstractProjectFileSearchScope, int i, Object obj) {
        if ((i & 1) != 0) {
            collection = incrementalCompilationContext.previousFirSessionsSymbolProviders;
        }
        if ((i & 2) != 0) {
            packagePartProvider = incrementalCompilationContext.precompiledBinariesPackagePartProvider;
        }
        if ((i & 4) != 0) {
            abstractProjectFileSearchScope = incrementalCompilationContext.precompiledBinariesFileScope;
        }
        return incrementalCompilationContext.copy(collection, packagePartProvider, abstractProjectFileSearchScope);
    }

    public final Collection<FirSymbolProvider> component1() {
        return this.previousFirSessionsSymbolProviders;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PackagePartProvider getPrecompiledBinariesPackagePartProvider() {
        return this.precompiledBinariesPackagePartProvider;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final AbstractProjectFileSearchScope getPrecompiledBinariesFileScope() {
        return this.precompiledBinariesFileScope;
    }

    public final IncrementalCompilationContext copy(Collection<? extends FirSymbolProvider> previousFirSessionsSymbolProviders, PackagePartProvider precompiledBinariesPackagePartProvider, AbstractProjectFileSearchScope precompiledBinariesFileScope) {
        previousFirSessionsSymbolProviders.getClass();
        return new IncrementalCompilationContext(previousFirSessionsSymbolProviders, precompiledBinariesPackagePartProvider, precompiledBinariesFileScope);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncrementalCompilationContext)) {
            return false;
        }
        IncrementalCompilationContext incrementalCompilationContext = (IncrementalCompilationContext) other;
        return Intrinsics.areEqual(this.previousFirSessionsSymbolProviders, incrementalCompilationContext.previousFirSessionsSymbolProviders) && Intrinsics.areEqual(this.precompiledBinariesPackagePartProvider, incrementalCompilationContext.precompiledBinariesPackagePartProvider) && Intrinsics.areEqual(this.precompiledBinariesFileScope, incrementalCompilationContext.precompiledBinariesFileScope);
    }

    public final AbstractProjectFileSearchScope getPrecompiledBinariesFileScope() {
        return this.precompiledBinariesFileScope;
    }

    public final PackagePartProvider getPrecompiledBinariesPackagePartProvider() {
        return this.precompiledBinariesPackagePartProvider;
    }

    public final Collection<FirSymbolProvider> getPreviousFirSessionsSymbolProviders() {
        return this.previousFirSessionsSymbolProviders;
    }

    public int hashCode() {
        int iHashCode = this.previousFirSessionsSymbolProviders.hashCode() * 31;
        PackagePartProvider packagePartProvider = this.precompiledBinariesPackagePartProvider;
        int iHashCode2 = (iHashCode + (packagePartProvider == null ? 0 : packagePartProvider.hashCode())) * 31;
        AbstractProjectFileSearchScope abstractProjectFileSearchScope = this.precompiledBinariesFileScope;
        return iHashCode2 + (abstractProjectFileSearchScope != null ? abstractProjectFileSearchScope.hashCode() : 0);
    }

    public String toString() {
        return "IncrementalCompilationContext(previousFirSessionsSymbolProviders=" + this.previousFirSessionsSymbolProviders + ", precompiledBinariesPackagePartProvider=" + this.precompiledBinariesPackagePartProvider + ", precompiledBinariesFileScope=" + this.precompiledBinariesFileScope + ')';
    }
}
