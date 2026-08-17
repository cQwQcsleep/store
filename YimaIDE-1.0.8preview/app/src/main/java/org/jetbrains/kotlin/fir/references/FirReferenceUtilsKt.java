package org.jetbrains.kotlin.fir.references;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\t\u001a\t\u0018\u0001H\n¢\u0006\u0002\b\u000b\"\u000e\b\u0000\u0010\n\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\b¢\u0006\u0002\u0010\u000e\u001a\u001a\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001a\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001a\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0015*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001a\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001b*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0016\u0010 \u001a\u0004\u0018\u00010!*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001b\u0010\"\u001a\u00020\r*\u00020\u0002\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\u001a\u001a\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)\"\u001b\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bò\u0001\u0004\n\u00020#¨\u0006*"}, d2 = {"symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "getSymbol", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "resolved", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "getResolved", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "toResolvedSymbol", "T", "Lkotlin/internal/NoInfer;", "discardErrorReference", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/references/FirReference;Z)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "toResolvedBaseSymbol", "toResolvedCallableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "toResolvedTypeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "toResolvedVariableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "toResolvedPropertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "toResolvedValueParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "toResolvedFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "toResolvedNamedFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "toResolvedConstructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "toResolvedEnumEntrySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "isError", "Lorg/jetbrains/kotlin/fir/diagnostics/FirDiagnosticHolder;", "buildErrorNamedReferenceWithNoName", "Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReferenceUtilsKt {
    public static final FirErrorNamedReference buildErrorNamedReferenceWithNoName(ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement) {
        coneDiagnostic.getClass();
        FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
        firErrorNamedReferenceBuilder.setDiagnostic(coneDiagnostic);
        firErrorNamedReferenceBuilder.setSource(ktSourceElement);
        Name nameSpecial = Name.special("<" + coneDiagnostic.getReason() + '>');
        nameSpecial.getClass();
        firErrorNamedReferenceBuilder.setName(nameSpecial);
        return firErrorNamedReferenceBuilder.build();
    }

    public static /* synthetic */ FirErrorNamedReference buildErrorNamedReferenceWithNoName$default(ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 2) != 0) {
            ktSourceElement = null;
        }
        return buildErrorNamedReferenceWithNoName(coneDiagnostic, ktSourceElement);
    }

    public static final FirResolvedNamedReference getResolved(FirReference firReference) {
        firReference.getClass();
        if (firReference instanceof FirResolvedNamedReference) {
            return (FirResolvedNamedReference) firReference;
        }
        return null;
    }

    public static final FirBasedSymbol<?> getSymbol(FirReference firReference) {
        firReference.getClass();
        if (firReference instanceof FirThisReference) {
            return ((FirThisReference) firReference).getBoundSymbol();
        }
        if (firReference instanceof FirResolvedNamedReference) {
            return ((FirResolvedNamedReference) firReference).getResolvedSymbol();
        }
        if (firReference instanceof FirNamedReferenceWithCandidateBase) {
            return ((FirNamedReferenceWithCandidateBase) firReference).getCandidateSymbol();
        }
        return null;
    }

    public static final boolean isError(FirReference firReference) {
        firReference.getClass();
        return (firReference instanceof FirResolvedErrorReference) || (firReference instanceof FirErrorNamedReference);
    }

    public static final FirBasedSymbol<?> toResolvedBaseSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        if (resolvedSymbol == null) {
            return null;
        }
        return resolvedSymbol;
    }

    public static /* synthetic */ FirBasedSymbol toResolvedBaseSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedBaseSymbol(firReference, z);
    }

    public static final FirCallableSymbol<?> toResolvedCallableSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirCallableSymbol) (resolvedSymbol instanceof FirCallableSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirCallableSymbol toResolvedCallableSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedCallableSymbol(firReference, z);
    }

    public static final FirConstructorSymbol toResolvedConstructorSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirConstructorSymbol) (resolvedSymbol instanceof FirConstructorSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirConstructorSymbol toResolvedConstructorSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedConstructorSymbol(firReference, z);
    }

    public static final FirEnumEntrySymbol toResolvedEnumEntrySymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirEnumEntrySymbol) (resolvedSymbol instanceof FirEnumEntrySymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirEnumEntrySymbol toResolvedEnumEntrySymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedEnumEntrySymbol(firReference, z);
    }

    public static final FirFunctionSymbol<?> toResolvedFunctionSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirFunctionSymbol) (resolvedSymbol instanceof FirFunctionSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirFunctionSymbol toResolvedFunctionSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedFunctionSymbol(firReference, z);
    }

    public static final FirNamedFunctionSymbol toResolvedNamedFunctionSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirNamedFunctionSymbol) (resolvedSymbol instanceof FirNamedFunctionSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirNamedFunctionSymbol toResolvedNamedFunctionSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedNamedFunctionSymbol(firReference, z);
    }

    public static final FirPropertySymbol toResolvedPropertySymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirPropertySymbol) (resolvedSymbol instanceof FirPropertySymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirPropertySymbol toResolvedPropertySymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedPropertySymbol(firReference, z);
    }

    public static final /* synthetic */ <T extends FirBasedSymbol<?>> T toResolvedSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        T t = null;
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        if (resolved != null) {
            t = (T) resolved.getResolvedSymbol();
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        return t;
    }

    public static /* synthetic */ FirBasedSymbol toResolvedSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        Intrinsics.reifiedOperationMarker(2, "T");
        return resolvedSymbol;
    }

    public static final FirTypeParameterSymbol toResolvedTypeParameterSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirTypeParameterSymbol) (resolvedSymbol instanceof FirTypeParameterSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirTypeParameterSymbol toResolvedTypeParameterSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedTypeParameterSymbol(firReference, z);
    }

    public static final FirValueParameterSymbol toResolvedValueParameterSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirValueParameterSymbol) (resolvedSymbol instanceof FirValueParameterSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirValueParameterSymbol toResolvedValueParameterSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedValueParameterSymbol(firReference, z);
    }

    public static final FirVariableSymbol<?> toResolvedVariableSymbol(FirReference firReference, boolean z) {
        firReference.getClass();
        if (z && (firReference instanceof FirResolvedErrorReference)) {
            return null;
        }
        FirResolvedNamedReference resolved = getResolved(firReference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        return (FirVariableSymbol) (resolvedSymbol instanceof FirVariableSymbol ? resolvedSymbol : null);
    }

    public static /* synthetic */ FirVariableSymbol toResolvedVariableSymbol$default(FirReference firReference, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toResolvedVariableSymbol(firReference, z);
    }
}
