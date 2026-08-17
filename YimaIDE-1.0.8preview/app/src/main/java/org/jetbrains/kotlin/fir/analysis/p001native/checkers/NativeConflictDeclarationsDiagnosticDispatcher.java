package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.PlatformConflictDeclarationsDiagnosticDispatcher;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.name.NativeStandardInteropNames;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JI\u0010\u0004\u001a\u0014\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u0018\u00010\u00052\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00072\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\fH\u0016R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/NativeConflictDeclarationsDiagnosticDispatcher;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/PlatformConflictDeclarationsDiagnosticDispatcher;", "<init>", "()V", "getDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "conflictingDeclaration", "symbols", "Lorg/jetbrains/kotlin/utils/SmartSet;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/utils/SmartSet;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NativeConflictDeclarationsDiagnosticDispatcher implements PlatformConflictDeclarationsDiagnosticDispatcher {
    public static final NativeConflictDeclarationsDiagnosticDispatcher INSTANCE = new NativeConflictDeclarationsDiagnosticDispatcher();

    private NativeConflictDeclarationsDiagnosticDispatcher() {
    }

    /* JADX WARN: Code duplicated, block: B:18:0x003d  */
    /* JADX WARN: Code duplicated, block: B:22:0x0046  */
    /* JADX WARN: Code duplicated, block: B:25:0x0050  */
    /* JADX WARN: Code duplicated, block: B:29:0x0064  */
    /* JADX WARN: Code duplicated, block: B:32:0x006b  */
    /* JADX WARN: Code duplicated, block: B:35:0x0075  */
    /* JADX WARN: Code duplicated, block: B:40:0x0097 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:42:0x0099  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:? A[LOOP:0: B:33:0x006f->B:48:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:50:0x00a0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:? A[LOOP:1: B:23:0x004a->B:51:?, LOOP_END, SYNTHETIC] */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.PlatformConflictDeclarationsDiagnosticDispatcher
    public KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> getDiagnostic(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, SmartSet<FirBasedSymbol<?>> smartSet) {
        FirFunctionSymbol firFunctionSymbol;
        Iterator it;
        FirBasedSymbol firBasedSymbol2;
        Iterator it2;
        FirBasedSymbol firBasedSymbol3;
        checkerContext.getClass();
        firBasedSymbol.getClass();
        smartSet.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ObjCSignatureOverrideAnnotation) && (firBasedSymbol instanceof FirFunctionSymbol)) {
            if (smartSet == null || !smartSet.isEmpty()) {
                Iterator it3 = smartSet.iterator();
                while (it3.hasNext()) {
                    if (!(((FirBasedSymbol) it3.next()) instanceof FirFunctionSymbol)) {
                    }
                }
                firFunctionSymbol = (FirFunctionSymbol) firBasedSymbol;
                if (FirNativeObjcOverrideApplicabilityCheckerKt.isInheritedFromObjc(checkerContext, firFunctionSymbol)) {
                    if (smartSet == null && smartSet.isEmpty()) {
                        if (smartSet != null) {
                            it2 = smartSet.iterator();
                            while (it2.hasNext()) {
                                firBasedSymbol3 = (FirBasedSymbol) it2.next();
                                firBasedSymbol3.getClass();
                                if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                                }
                            }
                        } else {
                            it2 = smartSet.iterator();
                            while (it2.hasNext()) {
                                firBasedSymbol3 = (FirBasedSymbol) it2.next();
                                firBasedSymbol3.getClass();
                                if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                                }
                            }
                        }
                        if (FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, NativeStandardInteropNames.Annotations.INSTANCE.getObjCSignatureOverrideClassId(), checkerContext.getSession())) {
                            return null;
                        }
                        return FirNativeErrors.INSTANCE.getCONFLICTING_OBJC_OVERLOADS();
                    }
                    it = smartSet.iterator();
                    while (it.hasNext()) {
                        firBasedSymbol2 = (FirBasedSymbol) it.next();
                        firBasedSymbol2.getClass();
                        if (!FirNativeObjcOverrideApplicabilityCheckerKt.isInheritedFromObjc(checkerContext, (FirFunctionSymbol) firBasedSymbol2)) {
                        }
                    }
                    if (smartSet != null || !smartSet.isEmpty()) {
                        it2 = smartSet.iterator();
                        while (it2.hasNext()) {
                            firBasedSymbol3 = (FirBasedSymbol) it2.next();
                            firBasedSymbol3.getClass();
                            if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                            }
                        }
                    }
                    if (FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, NativeStandardInteropNames.Annotations.INSTANCE.getObjCSignatureOverrideClassId(), checkerContext.getSession())) {
                        return null;
                    }
                    return FirNativeErrors.INSTANCE.getCONFLICTING_OBJC_OVERLOADS();
                }
            } else {
                firFunctionSymbol = (FirFunctionSymbol) firBasedSymbol;
                if (FirNativeObjcOverrideApplicabilityCheckerKt.isInheritedFromObjc(checkerContext, firFunctionSymbol)) {
                    if (smartSet == null) {
                        it = smartSet.iterator();
                        while (it.hasNext()) {
                            firBasedSymbol2 = (FirBasedSymbol) it.next();
                            firBasedSymbol2.getClass();
                            if (!FirNativeObjcOverrideApplicabilityCheckerKt.isInheritedFromObjc(checkerContext, (FirFunctionSymbol) firBasedSymbol2)) {
                            }
                        }
                        if (smartSet != null) {
                            it2 = smartSet.iterator();
                            while (it2.hasNext()) {
                                firBasedSymbol3 = (FirBasedSymbol) it2.next();
                                firBasedSymbol3.getClass();
                                if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                                }
                            }
                        } else {
                            it2 = smartSet.iterator();
                            while (it2.hasNext()) {
                                firBasedSymbol3 = (FirBasedSymbol) it2.next();
                                firBasedSymbol3.getClass();
                                if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                                }
                            }
                        }
                        if (FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, NativeStandardInteropNames.Annotations.INSTANCE.getObjCSignatureOverrideClassId(), checkerContext.getSession())) {
                            return null;
                        }
                        return FirNativeErrors.INSTANCE.getCONFLICTING_OBJC_OVERLOADS();
                    }
                    it = smartSet.iterator();
                    while (it.hasNext()) {
                        firBasedSymbol2 = (FirBasedSymbol) it.next();
                        firBasedSymbol2.getClass();
                        if (!FirNativeObjcOverrideApplicabilityCheckerKt.isInheritedFromObjc(checkerContext, (FirFunctionSymbol) firBasedSymbol2)) {
                        }
                    }
                    if (smartSet != null) {
                        it2 = smartSet.iterator();
                        while (it2.hasNext()) {
                            firBasedSymbol3 = (FirBasedSymbol) it2.next();
                            firBasedSymbol3.getClass();
                            if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                            }
                        }
                    } else {
                        it2 = smartSet.iterator();
                        while (it2.hasNext()) {
                            firBasedSymbol3 = (FirBasedSymbol) it2.next();
                            firBasedSymbol3.getClass();
                            if (!FirNativeObjcOverrideApplicabilityCheckerKt.hasDifferentParameterNames((FirFunctionSymbol) firBasedSymbol3, firFunctionSymbol)) {
                            }
                        }
                    }
                    if (FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, NativeStandardInteropNames.Annotations.INSTANCE.getObjCSignatureOverrideClassId(), checkerContext.getSession())) {
                        return null;
                    }
                    return FirNativeErrors.INSTANCE.getCONFLICTING_OBJC_OVERLOADS();
                }
            }
        }
        return PlatformConflictDeclarationsDiagnosticDispatcher.DEFAULT.INSTANCE.getDiagnostic(checkerContext, firBasedSymbol, smartSet);
    }
}
