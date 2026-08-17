package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirDummyCompilerLazyDeclarationResolver;", "Lorg/jetbrains/kotlin/fir/symbols/FirLazyDeclarationResolver;", "<init>", "()V", "startResolvingPhase", Argument.Delimiters.none, "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "finishResolvingPhase", "lazyResolveToPhase", "element", "Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;", "toPhase", "lazyResolveToPhaseWithCallableMembers", "clazz", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "lazyResolveToPhaseRecursively", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDummyCompilerLazyDeclarationResolver extends FirLazyDeclarationResolver {
    public static final FirDummyCompilerLazyDeclarationResolver INSTANCE = new FirDummyCompilerLazyDeclarationResolver();

    private FirDummyCompilerLazyDeclarationResolver() {
    }

    @Override // org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver
    public void finishResolvingPhase(FirResolvePhase phase) {
        phase.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver
    public void lazyResolveToPhase(FirElementWithResolveState element, FirResolvePhase toPhase) {
        element.getClass();
        toPhase.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver
    public void lazyResolveToPhaseRecursively(FirElementWithResolveState element, FirResolvePhase toPhase) {
        element.getClass();
        toPhase.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver
    public void lazyResolveToPhaseWithCallableMembers(FirClass clazz, FirResolvePhase toPhase) {
        clazz.getClass();
        toPhase.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver
    public void startResolvingPhase(FirResolvePhase phase) {
        phase.getClass();
    }
}
