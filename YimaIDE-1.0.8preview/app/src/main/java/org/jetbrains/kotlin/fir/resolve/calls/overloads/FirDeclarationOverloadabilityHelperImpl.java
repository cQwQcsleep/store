package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelper;
import org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProvider;
import org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProviderKt;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLogger;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLoggerKt;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponentsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemTransaction;
import org.jetbrains.kotlin.resolve.calls.results.FlatSignature;
import org.jetbrains.kotlin.resolve.calls.results.FlatSignatureComparisonState;
import org.jetbrains.kotlin.resolve.calls.results.FlatSignatureKt;
import org.jetbrains.kotlin.resolve.calls.results.OverloadabilitySpecificityCallbacks;
import org.jetbrains.kotlin.resolve.calls.results.TypeSpecificityComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016J \u0010\r\u001a\u00020\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016J \u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016J0\u0010\u0012\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J,\u0010\u0017\u001a\u00020\t2\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00192\u0010\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0019H\u0002J&\u0010\u001b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00192\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u001d\u001a\u00020\tH\u0002J\u001e\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00192\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002J2\u0010 \u001a\u0004\u0018\u00010\u0016*\u00020\u00142\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00192\u0010\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0019H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/FirDeclarationOverloadabilityHelperImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOverloadabilityHelper;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "isConflicting", Argument.Delimiters.none, "a", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "b", "getContextParameterShadowing", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOverloadabilityHelper$ContextParameterShadowing;", "isExtensionShadowedByMember", "extension", "member", "isShadowingContextParameters", "cs", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeSimpleConstraintSystemImpl;", "state", "Lorg/jetbrains/kotlin/resolve/calls/results/FlatSignatureComparisonState;", "isEquallyOrMoreSpecific", "sigA", "Lorg/jetbrains/kotlin/resolve/calls/results/FlatSignature;", "sigB", "createSignature", "declaration", "ignoreContextParameters", "createSignatureForPossiblyShadowedExtension", "createEmptyConstraintSystem", "signatureComparisonStateIfEquallyOrMoreSpecific", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationOverloadabilityHelperImpl implements FirDeclarationOverloadabilityHelper {
    private final FirSession session;

    public FirDeclarationOverloadabilityHelperImpl(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    private final ConeSimpleConstraintSystemImpl createEmptyConstraintSystem() {
        ConeSimpleConstraintSystemImpl coneSimpleConstraintSystemImpl = new ConeSimpleConstraintSystemImpl(InferenceComponents.createConstraintSystem$default(InferenceComponentsKt.getInferenceComponents(this.session), null, 1, null), this.session);
        FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(this.session);
        if (inferenceLogger != null) {
            inferenceLogger.logStage("Some isEquallyOrMoreSpecific() call", coneSimpleConstraintSystemImpl.getConstraintSystemMarker());
        }
        return coneSimpleConstraintSystemImpl;
    }

    private final FlatSignature<FirCallableSymbol<?>> createSignature(FirCallableSymbol<?> declaration, boolean ignoreContextParameters) {
        boolean z;
        FirFunctionSymbol firFunctionSymbol = declaration instanceof FirFunctionSymbol ? (FirFunctionSymbol) declaration : null;
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol != null ? firFunctionSymbol.getValueParameterSymbols() : null;
        if (valueParameterSymbols == null) {
            valueParameterSymbols = CollectionsKt.emptyList();
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = declaration.getTypeParameterSymbols();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameterSymbols, 10));
        Iterator<T> it = typeParameterSymbols.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterSymbol) it.next()).getLookupTag());
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (!ignoreContextParameters) {
            List list = listCreateListBuilder;
            Iterator<T> it2 = declaration.getContextParameterSymbols().iterator();
            while (it2.hasNext()) {
                list.add(((FirValueParameterSymbol) it2.next()).getResolvedReturnType());
            }
        }
        ConeKotlinType resolvedReceiverType = declaration.getResolvedReceiverType();
        if (resolvedReceiverType != null) {
            listCreateListBuilder.add(resolvedReceiverType);
        }
        List<FirValueParameterSymbol> list2 = valueParameterSymbols;
        List list3 = listCreateListBuilder;
        Iterator<T> it3 = list2.iterator();
        while (it3.hasNext()) {
            list3.add(((FirValueParameterSymbol) it3.next()).getResolvedReturnType());
        }
        Unit unit = Unit.INSTANCE;
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        boolean z2 = declaration.getReceiverParameterSymbol() != null;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it4 = list2.iterator();
            while (it4.hasNext()) {
                if (((FirValueParameterSymbol) it4.next()).isVararg()) {
                    z = true;
                }
            }
            z = false;
        }
        return new FlatSignature<>(declaration, arrayList, listBuild, z2, 0, z, 0, declaration.getRawStatus().isExpect(), declaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic);
    }

    private final FlatSignature<FirCallableSymbol<?>> createSignatureForPossiblyShadowedExtension(FirCallableSymbol<?> declaration) {
        FirFunctionSymbol firFunctionSymbol = declaration instanceof FirFunctionSymbol ? (FirFunctionSymbol) declaration : null;
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol != null ? firFunctionSymbol.getValueParameterSymbols() : null;
        if (valueParameterSymbols == null) {
            valueParameterSymbols = CollectionsKt.emptyList();
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = declaration.getTypeParameterSymbols();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameterSymbols, 10));
        Iterator<T> it = typeParameterSymbols.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterSymbol) it.next()).getLookupTag());
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List<FirValueParameterSymbol> list = valueParameterSymbols;
        List list2 = listCreateListBuilder;
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            list2.add(((FirValueParameterSymbol) it2.next()).getResolvedReturnType());
        }
        Unit unit = Unit.INSTANCE;
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it3 = list.iterator();
            while (it3.hasNext()) {
                if (((FirValueParameterSymbol) it3.next()).isVararg()) {
                    z = true;
                    break;
                }
            }
        }
        return new FlatSignature<>(declaration, arrayList, listBuild, false, 0, z, 0, declaration.getRawStatus().isExpect(), declaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic);
    }

    private final boolean isEquallyOrMoreSpecific(FlatSignature<? extends FirCallableSymbol<?>> sigA, FlatSignature<? extends FirCallableSymbol<?>> sigB) {
        return signatureComparisonStateIfEquallyOrMoreSpecific(createEmptyConstraintSystem(), sigA, sigB) != null;
    }

    private final boolean isShadowingContextParameters(FirCallableSymbol<?> a, FirCallableSymbol<?> b, ConeSimpleConstraintSystemImpl cs, FlatSignatureComparisonState state) {
        List<FirValueParameterSymbol> contextParameterSymbols = a.getContextParameterSymbols();
        if ((contextParameterSymbols instanceof Collection) && contextParameterSymbols.isEmpty()) {
            return true;
        }
        for (FirValueParameterSymbol firValueParameterSymbol : contextParameterSymbols) {
            List<FirValueParameterSymbol> contextParameterSymbols2 = b.getContextParameterSymbols();
            if ((contextParameterSymbols2 instanceof Collection) && contextParameterSymbols2.isEmpty()) {
                return false;
            }
            for (FirValueParameterSymbol firValueParameterSymbol2 : contextParameterSymbols2) {
                ConstraintSystemTransaction constraintSystemTransactionPrepareTransaction = cs.getSystem().prepareTransaction();
                if (state.isLessSpecific(firValueParameterSymbol2.getResolvedReturnType(), firValueParameterSymbol.getResolvedReturnType())) {
                    constraintSystemTransactionPrepareTransaction.rollbackTransaction();
                } else {
                    constraintSystemTransactionPrepareTransaction.closeTransaction();
                }
            }
            return false;
        }
        return true;
    }

    private final FlatSignatureComparisonState signatureComparisonStateIfEquallyOrMoreSpecific(ConeSimpleConstraintSystemImpl coneSimpleConstraintSystemImpl, FlatSignature<? extends FirCallableSymbol<?>> flatSignature, FlatSignature<? extends FirCallableSymbol<?>> flatSignature2) {
        TypeSpecificityComparator typeSpecificityComparator;
        OverloadabilitySpecificityCallbacks overloadabilitySpecificityCallbacks = OverloadabilitySpecificityCallbacks.INSTANCE;
        FirTypeSpecificityComparatorProvider typeSpecificityComparatorProvider = FirTypeSpecificityComparatorProviderKt.getTypeSpecificityComparatorProvider(coneSimpleConstraintSystemImpl.getSession());
        if (typeSpecificityComparatorProvider == null || (typeSpecificityComparator = typeSpecificityComparatorProvider.getTypeSpecificityComparator()) == null) {
            typeSpecificityComparator = TypeSpecificityComparator.NONE.INSTANCE;
        }
        return FlatSignatureKt.signatureComparisonStateIfEquallyOrMoreSpecific$default(coneSimpleConstraintSystemImpl, flatSignature, flatSignature2, overloadabilitySpecificityCallbacks, typeSpecificityComparator, false, 16, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelper
    public FirDeclarationOverloadabilityHelper.ContextParameterShadowing getContextParameterShadowing(FirCallableSymbol<?> a, FirCallableSymbol<?> b) {
        ConeSimpleConstraintSystemImpl coneSimpleConstraintSystemImplCreateEmptyConstraintSystem;
        FlatSignatureComparisonState flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific;
        a.getClass();
        b.getClass();
        FlatSignature<FirCallableSymbol<?>> flatSignatureCreateSignature = createSignature(a, true);
        FlatSignature<FirCallableSymbol<?>> flatSignatureCreateSignature2 = createSignature(b, true);
        ConeSimpleConstraintSystemImpl coneSimpleConstraintSystemImplCreateEmptyConstraintSystem2 = createEmptyConstraintSystem();
        FlatSignatureComparisonState flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific2 = signatureComparisonStateIfEquallyOrMoreSpecific(coneSimpleConstraintSystemImplCreateEmptyConstraintSystem2, flatSignatureCreateSignature, flatSignatureCreateSignature2);
        if (flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific2 != null && (flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific = signatureComparisonStateIfEquallyOrMoreSpecific((coneSimpleConstraintSystemImplCreateEmptyConstraintSystem = createEmptyConstraintSystem()), flatSignatureCreateSignature2, flatSignatureCreateSignature)) != null && a.getContextParameterSymbols().size() * b.getContextParameterSymbols().size() <= 16 && isShadowingContextParameters(b, a, coneSimpleConstraintSystemImplCreateEmptyConstraintSystem2, flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific2)) {
            return isShadowingContextParameters(a, b, coneSimpleConstraintSystemImplCreateEmptyConstraintSystem, flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific) ? FirDeclarationOverloadabilityHelper.ContextParameterShadowing.BothWays : FirDeclarationOverloadabilityHelper.ContextParameterShadowing.Shadowing;
        }
        return FirDeclarationOverloadabilityHelper.ContextParameterShadowing.None;
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelper
    public boolean isConflicting(FirCallableSymbol<?> a, FirCallableSymbol<?> b) {
        a.getClass();
        b.getClass();
        FlatSignature<FirCallableSymbol<?>> flatSignatureCreateSignature = createSignature(a, false);
        FlatSignature<FirCallableSymbol<?>> flatSignatureCreateSignature2 = createSignature(b, false);
        return isEquallyOrMoreSpecific(flatSignatureCreateSignature, flatSignatureCreateSignature2) && isEquallyOrMoreSpecific(flatSignatureCreateSignature2, flatSignatureCreateSignature);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelper
    public boolean isExtensionShadowedByMember(FirCallableSymbol<?> extension, FirCallableSymbol<?> member) {
        extension.getClass();
        member.getClass();
        FlatSignature<FirCallableSymbol<?>> flatSignatureCreateSignatureForPossiblyShadowedExtension = createSignatureForPossiblyShadowedExtension(extension);
        FlatSignature<FirCallableSymbol<?>> flatSignatureCreateSignature = createSignature(member, true);
        ConeSimpleConstraintSystemImpl coneSimpleConstraintSystemImplCreateEmptyConstraintSystem = createEmptyConstraintSystem();
        FlatSignatureComparisonState flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific = signatureComparisonStateIfEquallyOrMoreSpecific(coneSimpleConstraintSystemImplCreateEmptyConstraintSystem, flatSignatureCreateSignatureForPossiblyShadowedExtension, flatSignatureCreateSignature);
        if (flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific != null && extension.getContextParameterSymbols().size() * member.getContextParameterSymbols().size() <= 16) {
            return isShadowingContextParameters(member, extension, coneSimpleConstraintSystemImplCreateEmptyConstraintSystem, flatSignatureComparisonStateSignatureComparisonStateIfEquallyOrMoreSpecific);
        }
        return false;
    }
}
