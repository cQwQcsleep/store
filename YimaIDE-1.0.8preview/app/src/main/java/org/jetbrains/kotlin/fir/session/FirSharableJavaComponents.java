package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.java.enhancement.FirEnhancedSymbolsStorage;
import org.jetbrains.kotlin.fir.java.scopes.FirRenamedForOverrideSymbolsStorage;
import org.jetbrains.kotlin.fir.scopes.jvm.JvmMappedScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirSharableJavaComponents;", Argument.Delimiters.none, "enhancementStorage", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage;", "mappedStorage", "Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage;", "renamedFunctionsStorage", "Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage;Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage;Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage;)V", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "getEnhancementStorage", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage;", "getMappedStorage", "()Lorg/jetbrains/kotlin/fir/scopes/jvm/JvmMappedScope$FirMappedSymbolStorage;", "getRenamedFunctionsStorage", "()Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSharableJavaComponents {
    private final FirEnhancedSymbolsStorage enhancementStorage;
    private final JvmMappedScope.FirMappedSymbolStorage mappedStorage;
    private final FirRenamedForOverrideSymbolsStorage renamedFunctionsStorage;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirSharableJavaComponents(FirCachesFactory firCachesFactory) {
        this(new FirEnhancedSymbolsStorage(firCachesFactory), new JvmMappedScope.FirMappedSymbolStorage(firCachesFactory), new FirRenamedForOverrideSymbolsStorage(firCachesFactory));
        firCachesFactory.getClass();
    }

    public final FirEnhancedSymbolsStorage getEnhancementStorage() {
        return this.enhancementStorage;
    }

    public final JvmMappedScope.FirMappedSymbolStorage getMappedStorage() {
        return this.mappedStorage;
    }

    public final FirRenamedForOverrideSymbolsStorage getRenamedFunctionsStorage() {
        return this.renamedFunctionsStorage;
    }

    public FirSharableJavaComponents(FirEnhancedSymbolsStorage firEnhancedSymbolsStorage, JvmMappedScope.FirMappedSymbolStorage firMappedSymbolStorage, FirRenamedForOverrideSymbolsStorage firRenamedForOverrideSymbolsStorage) {
        firEnhancedSymbolsStorage.getClass();
        firMappedSymbolStorage.getClass();
        firRenamedForOverrideSymbolsStorage.getClass();
        this.enhancementStorage = firEnhancedSymbolsStorage;
        this.mappedStorage = firMappedSymbolStorage;
        this.renamedFunctionsStorage = firRenamedForOverrideSymbolsStorage;
    }
}
