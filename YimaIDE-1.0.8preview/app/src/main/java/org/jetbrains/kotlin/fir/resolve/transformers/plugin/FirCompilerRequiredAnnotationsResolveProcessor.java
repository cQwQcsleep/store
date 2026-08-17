package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.transformers.FirGlobalResolveProcessor;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirCompilerRequiredAnnotationsResolveProcessor;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirGlobalResolveProcessor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "process", Argument.Delimiters.none, "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "beforePhase", "afterPhase", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompilerRequiredAnnotationsResolveProcessor extends FirGlobalResolveProcessor {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirCompilerRequiredAnnotationsResolveProcessor(FirSession firSession, ScopeSession scopeSession) {
        super(firSession, scopeSession, FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS);
        firSession.getClass();
        scopeSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirResolveProcessor
    public void afterPhase() {
        super.afterPhase();
        FirSwitchableExtensionDeclarationsSymbolProvider generatedDeclarationsSymbolProvider = FirSwitchableExtensionDeclarationsSymbolProviderKt.getGeneratedDeclarationsSymbolProvider(getSession());
        if (generatedDeclarationsSymbolProvider != null) {
            generatedDeclarationsSymbolProvider.enable();
        }
        FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(getSession());
        if (generatedDeclarationsSymbolProvider == null || !(symbolProvider instanceof FirCachingCompositeSymbolProvider)) {
            return;
        }
        getSession().register(Reflection.getOrCreateKotlinClass(FirSymbolProvider.class), ((FirCachingCompositeSymbolProvider) symbolProvider).createCopyWithCleanCaches());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirResolveProcessor
    public void beforePhase() {
        super.beforePhase();
        FirSwitchableExtensionDeclarationsSymbolProvider generatedDeclarationsSymbolProvider = FirSwitchableExtensionDeclarationsSymbolProviderKt.getGeneratedDeclarationsSymbolProvider(getSession());
        if (generatedDeclarationsSymbolProvider != null) {
            generatedDeclarationsSymbolProvider.disable();
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirGlobalResolveProcessor
    public void process(Collection<? extends FirFile> files) {
        files.getClass();
        FirCompilerRequiredAnnotationsResolveTransformer firCompilerRequiredAnnotationsResolveTransformer = new FirCompilerRequiredAnnotationsResolveTransformer(getSession(), getScopeSession(), new CompilerRequiredAnnotationsComputationSession());
        for (FirFile firFile : files) {
            try {
                FirTransformerUtilKt.transformSingle(firFile, firCompilerRequiredAnnotationsResolveTransformer, null);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
                wq6.a();
                return;
            }
        }
    }
}
