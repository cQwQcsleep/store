package org.jetbrains.kotlin.fir.session;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.java.deserialization.JvmClassFileBasedSymbolProvider;
import org.jetbrains.kotlin.fir.java.deserialization.OptionalAnnotationClassesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirJvmIncrementalCompilationSymbolProviders;", Argument.Delimiters.none, "symbolProviderForBinariesFromIncrementalCompilation", "Lorg/jetbrains/kotlin/fir/java/deserialization/JvmClassFileBasedSymbolProvider;", "previousFirSessionsSymbolProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation", "Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/deserialization/JvmClassFileBasedSymbolProvider;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider;)V", "getSymbolProviderForBinariesFromIncrementalCompilation", "()Lorg/jetbrains/kotlin/fir/java/deserialization/JvmClassFileBasedSymbolProvider;", "getPreviousFirSessionsSymbolProviders", "()Ljava/util/Collection;", "getOptionalAnnotationClassesProviderForBinariesFromIncrementalCompilation", "()Lorg/jetbrains/kotlin/fir/java/deserialization/OptionalAnnotationClassesProvider;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirJvmIncrementalCompilationSymbolProviders {
    private final OptionalAnnotationClassesProvider optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation;
    private final Collection<FirSymbolProvider> previousFirSessionsSymbolProviders;
    private final JvmClassFileBasedSymbolProvider symbolProviderForBinariesFromIncrementalCompilation;

    /* JADX WARN: Multi-variable type inference failed */
    public FirJvmIncrementalCompilationSymbolProviders(JvmClassFileBasedSymbolProvider jvmClassFileBasedSymbolProvider, Collection<? extends FirSymbolProvider> collection, OptionalAnnotationClassesProvider optionalAnnotationClassesProvider) {
        collection.getClass();
        this.symbolProviderForBinariesFromIncrementalCompilation = jvmClassFileBasedSymbolProvider;
        this.previousFirSessionsSymbolProviders = collection;
        this.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation = optionalAnnotationClassesProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirJvmIncrementalCompilationSymbolProviders copy$default(FirJvmIncrementalCompilationSymbolProviders firJvmIncrementalCompilationSymbolProviders, JvmClassFileBasedSymbolProvider jvmClassFileBasedSymbolProvider, Collection collection, OptionalAnnotationClassesProvider optionalAnnotationClassesProvider, int i, Object obj) {
        if ((i & 1) != 0) {
            jvmClassFileBasedSymbolProvider = firJvmIncrementalCompilationSymbolProviders.symbolProviderForBinariesFromIncrementalCompilation;
        }
        if ((i & 2) != 0) {
            collection = firJvmIncrementalCompilationSymbolProviders.previousFirSessionsSymbolProviders;
        }
        if ((i & 4) != 0) {
            optionalAnnotationClassesProvider = firJvmIncrementalCompilationSymbolProviders.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation;
        }
        return firJvmIncrementalCompilationSymbolProviders.copy(jvmClassFileBasedSymbolProvider, collection, optionalAnnotationClassesProvider);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final JvmClassFileBasedSymbolProvider getSymbolProviderForBinariesFromIncrementalCompilation() {
        return this.symbolProviderForBinariesFromIncrementalCompilation;
    }

    public final Collection<FirSymbolProvider> component2() {
        return this.previousFirSessionsSymbolProviders;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final OptionalAnnotationClassesProvider getOptionalAnnotationClassesProviderForBinariesFromIncrementalCompilation() {
        return this.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation;
    }

    public final FirJvmIncrementalCompilationSymbolProviders copy(JvmClassFileBasedSymbolProvider symbolProviderForBinariesFromIncrementalCompilation, Collection<? extends FirSymbolProvider> previousFirSessionsSymbolProviders, OptionalAnnotationClassesProvider optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation) {
        previousFirSessionsSymbolProviders.getClass();
        return new FirJvmIncrementalCompilationSymbolProviders(symbolProviderForBinariesFromIncrementalCompilation, previousFirSessionsSymbolProviders, optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirJvmIncrementalCompilationSymbolProviders)) {
            return false;
        }
        FirJvmIncrementalCompilationSymbolProviders firJvmIncrementalCompilationSymbolProviders = (FirJvmIncrementalCompilationSymbolProviders) other;
        return Intrinsics.areEqual(this.symbolProviderForBinariesFromIncrementalCompilation, firJvmIncrementalCompilationSymbolProviders.symbolProviderForBinariesFromIncrementalCompilation) && Intrinsics.areEqual(this.previousFirSessionsSymbolProviders, firJvmIncrementalCompilationSymbolProviders.previousFirSessionsSymbolProviders) && Intrinsics.areEqual(this.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation, firJvmIncrementalCompilationSymbolProviders.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation);
    }

    public final OptionalAnnotationClassesProvider getOptionalAnnotationClassesProviderForBinariesFromIncrementalCompilation() {
        return this.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation;
    }

    public final Collection<FirSymbolProvider> getPreviousFirSessionsSymbolProviders() {
        return this.previousFirSessionsSymbolProviders;
    }

    public final JvmClassFileBasedSymbolProvider getSymbolProviderForBinariesFromIncrementalCompilation() {
        return this.symbolProviderForBinariesFromIncrementalCompilation;
    }

    public int hashCode() {
        JvmClassFileBasedSymbolProvider jvmClassFileBasedSymbolProvider = this.symbolProviderForBinariesFromIncrementalCompilation;
        int iHashCode = (((jvmClassFileBasedSymbolProvider == null ? 0 : jvmClassFileBasedSymbolProvider.hashCode()) * 31) + this.previousFirSessionsSymbolProviders.hashCode()) * 31;
        OptionalAnnotationClassesProvider optionalAnnotationClassesProvider = this.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation;
        return iHashCode + (optionalAnnotationClassesProvider != null ? optionalAnnotationClassesProvider.hashCode() : 0);
    }

    public String toString() {
        return "FirJvmIncrementalCompilationSymbolProviders(symbolProviderForBinariesFromIncrementalCompilation=" + this.symbolProviderForBinariesFromIncrementalCompilation + ", previousFirSessionsSymbolProviders=" + this.previousFirSessionsSymbolProviders + ", optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation=" + this.optionalAnnotationClassesProviderForBinariesFromIncrementalCompilation + ')';
    }
}
