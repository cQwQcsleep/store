package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import defpackage.f2f;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.extensions.FirExpressionResolutionExtension;
import org.jetbrains.kotlin.fir.extensions.FirExpressionResolutionExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.impl.FirSimpleNamedReference;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitExtensionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCodeFragmentSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a\u001a\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"setIndexedAccessAugmentedAssignSource", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "fakeSourceElementKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "createCopyWithNewSource", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "newSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "addReceiversFromExtensions", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "functionCall", "sessionHolder", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionsResolveTransformerKt {
    public static final void addReceiversFromExtensions(BodyResolveContext bodyResolveContext, FirFunctionCall firFunctionCall, SessionAndScopeSessionHolder sessionAndScopeSessionHolder) {
        bodyResolveContext.getClass();
        firFunctionCall.getClass();
        sessionAndScopeSessionHolder.getClass();
        List<FirExpressionResolutionExtension> expressionResolutionExtensions = FirExpressionResolutionExtensionKt.getExpressionResolutionExtensions(FirExtensionServiceKt.getExtensionService(sessionAndScopeSessionHolder.getSession()));
        if (expressionResolutionExtensions.isEmpty()) {
            expressionResolutionExtensions = null;
        }
        if (expressionResolutionExtensions == null) {
            return;
        }
        FirDeclaration containerIfAny = bodyResolveContext.getContainerIfAny();
        FirBasedSymbol<FirDeclaration> symbol = containerIfAny != null ? containerIfAny.getSymbol() : null;
        if ((symbol instanceof FirCallableSymbol) || (symbol instanceof FirCodeFragmentSymbol)) {
            Iterator<FirExpressionResolutionExtension> it = expressionResolutionExtensions.iterator();
            while (it.hasNext()) {
                Iterator<ImplicitExtensionReceiverValue> it2 = it.next().addNewImplicitReceivers(firFunctionCall, sessionAndScopeSessionHolder, symbol).iterator();
                while (it2.hasNext()) {
                    bodyResolveContext.addReceiver(null, it2.next());
                }
            }
        }
    }

    private static final FirNamedReference createCopyWithNewSource(FirNamedReference firNamedReference, KtSourceElement ktSourceElement) {
        if (firNamedReference instanceof FirResolvedNamedReference) {
            FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
            FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) firNamedReference;
            firResolvedNamedReferenceBuilder.setName(firResolvedNamedReference.getName());
            firResolvedNamedReferenceBuilder.setSource(ktSourceElement);
            firResolvedNamedReferenceBuilder.setResolvedSymbol(firResolvedNamedReference.getResolvedSymbol());
            return firResolvedNamedReferenceBuilder.build();
        }
        if (firNamedReference instanceof FirNamedReferenceWithCandidate) {
            FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = (FirNamedReferenceWithCandidate) firNamedReference;
            return new FirNamedReferenceWithCandidate(ktSourceElement, firNamedReferenceWithCandidate.getName(), firNamedReferenceWithCandidate.getCandidate());
        }
        if (firNamedReference instanceof FirSimpleNamedReference) {
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(ktSourceElement);
            firSimpleNamedReferenceBuilder.setName(((FirSimpleNamedReference) firNamedReference).getName());
            return firSimpleNamedReferenceBuilder.build();
        }
        if (!(firNamedReference instanceof FirErrorNamedReference)) {
            f2f.a("Unexpected type of callee reference: ", UtilsKt.render(firNamedReference));
            return null;
        }
        FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
        firErrorNamedReferenceBuilder.setSource(ktSourceElement);
        FirErrorNamedReference firErrorNamedReference = (FirErrorNamedReference) firNamedReference;
        firErrorNamedReferenceBuilder.setName(firErrorNamedReference.getName());
        firErrorNamedReferenceBuilder.setDiagnostic(firErrorNamedReference.getDiagnostic());
        return firErrorNamedReferenceBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setIndexedAccessAugmentedAssignSource(FirFunctionCall firFunctionCall, KtFakeSourceElementKind ktFakeSourceElementKind) {
        if (FirReferenceUtilsKt.isError(firFunctionCall.getCalleeReference())) {
            return;
        }
        KtSourceElement source = firFunctionCall.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, ktFakeSourceElementKind, null, 2, null) : null;
        firFunctionCall.replaceSource(ktSourceElementFakeElement$default);
        firFunctionCall.replaceCalleeReference(createCopyWithNewSource(firFunctionCall.getCalleeReference(), ktSourceElementFakeElement$default));
    }
}
