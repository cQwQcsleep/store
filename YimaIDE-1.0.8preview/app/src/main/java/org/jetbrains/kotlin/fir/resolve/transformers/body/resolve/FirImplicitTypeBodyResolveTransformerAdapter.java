package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.AdapterForResolveProcessor;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\u000f\u001a\u0002H\u0010\"\b\b\u0000\u0010\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u0002H\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirImplicitTypeBodyResolveTransformerAdapter;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "implicitBodyResolveComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ImplicitBodyResolveComputationSession;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/ReturnTypeCalculatorWithJump;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirImplicitAwareBodyResolveTransformer;", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "org.jetbrains.kotlin:resolve", "Lorg/jetbrains/kotlin/fir/resolve/transformers/AdapterForResolveProcessor;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@AdapterForResolveProcessor
public final class FirImplicitTypeBodyResolveTransformerAdapter extends FirTransformer<Object> {
    private final ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession;
    private final ReturnTypeCalculatorWithJump returnTypeCalculator;
    private final FirImplicitAwareBodyResolveTransformer transformer;

    public FirImplicitTypeBodyResolveTransformerAdapter(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        scopeSession.getClass();
        ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession = new ImplicitBodyResolveComputationSession();
        this.implicitBodyResolveComputationSession = implicitBodyResolveComputationSession;
        DefaultConstructorMarker defaultConstructorMarker = null;
        ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump = new ReturnTypeCalculatorWithJump(scopeSession, implicitBodyResolveComputationSession, null, null, null, 28, defaultConstructorMarker);
        this.returnTypeCalculator = returnTypeCalculatorWithJump;
        this.transformer = new FirImplicitAwareBodyResolveTransformer(firSession, scopeSession, implicitBodyResolveComputationSession, FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE, true, returnTypeCalculatorWithJump, defaultConstructorMarker, 64, null);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Object data) {
        element.getClass();
        return element;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Object data) {
        file.getClass();
        try {
            return (FirFile) file.transform(this.transformer, ResolutionMode.ContextIndependent.INSTANCE);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }
}
