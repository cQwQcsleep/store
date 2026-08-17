package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.SingleModuleDataProvider;
import org.jetbrains.kotlin.fir.java.FirJavaFacade;
import org.jetbrains.kotlin.fir.java.deserialization.JvmClassFileBasedSymbolProvider;
import org.jetbrains.kotlin.fir.java.deserialization.OptionalAnnotationClassesProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"createSymbolProviders", "Lorg/jetbrains/kotlin/fir/session/FirJvmIncrementalCompilationSymbolProviders;", "Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "projectEnvironment", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmIncrementalCompilationSymbolProvidersKt {
    public static final FirJvmIncrementalCompilationSymbolProviders createSymbolProviders(IncrementalCompilationContext incrementalCompilationContext, FirSession firSession, FirModuleData firModuleData, AbstractProjectEnvironment abstractProjectEnvironment) {
        JvmClassFileBasedSymbolProvider jvmClassFileBasedSymbolProvider;
        OptionalAnnotationClassesProvider optionalAnnotationClassesProvider;
        incrementalCompilationContext.getClass();
        firSession.getClass();
        firModuleData.getClass();
        abstractProjectEnvironment.getClass();
        if (incrementalCompilationContext.getPrecompiledBinariesPackagePartProvider() == null || incrementalCompilationContext.getPrecompiledBinariesFileScope() == null) {
            jvmClassFileBasedSymbolProvider = null;
            optionalAnnotationClassesProvider = null;
        } else {
            SingleModuleDataProvider singleModuleDataProvider = new SingleModuleDataProvider(firModuleData);
            FirKotlinScopeProvider kotlinScopeProvider = FirKotlinScopeProviderKt.getKotlinScopeProvider(firSession);
            PackagePartProvider precompiledBinariesPackagePartProvider = incrementalCompilationContext.getPrecompiledBinariesPackagePartProvider();
            KotlinClassFinder kotlinClassFinder = abstractProjectEnvironment.getKotlinClassFinder(incrementalCompilationContext.getPrecompiledBinariesFileScope());
            FirJavaFacade firJavaFacade = abstractProjectEnvironment.getFirJavaFacade(firSession, firModuleData, incrementalCompilationContext.getPrecompiledBinariesFileScope());
            FirDeclarationOrigin.Precompiled precompiled = FirDeclarationOrigin.Precompiled.INSTANCE;
            jvmClassFileBasedSymbolProvider = new JvmClassFileBasedSymbolProvider(firSession, singleModuleDataProvider, kotlinScopeProvider, precompiledBinariesPackagePartProvider, kotlinClassFinder, firJavaFacade, precompiled);
            optionalAnnotationClassesProvider = new OptionalAnnotationClassesProvider(firSession, singleModuleDataProvider, kotlinScopeProvider, incrementalCompilationContext.getPrecompiledBinariesPackagePartProvider(), precompiled);
        }
        return new FirJvmIncrementalCompilationSymbolProviders(jvmClassFileBasedSymbolProvider, incrementalCompilationContext.getPreviousFirSessionsSymbolProviders(), optionalAnnotationClassesProvider);
    }
}
