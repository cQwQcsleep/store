package org.jetbrains.kotlin.fir.analysis.js.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JsStandardClassIds;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010\u000b\u001a\u00020\f2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0000R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0015\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"jsModuleKindComponent", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsModuleKind;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getJsModuleKindComponent", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsModuleKind;", "jsModuleKindComponent$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "jsModuleKind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "getJsModuleKind", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/js/config/ModuleKind;", "checkJsModuleUsage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "callee", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "source", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/AbstractKtSourceElement;)V", "org.jetbrains.kotlin:checkers.js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsModuleCheckUtilsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirJsModuleCheckUtilsKt.class, "jsModuleKindComponent", "getJsModuleKindComponent(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsModuleKind;", 1)};
    private static final NullableArrayMapAccessor jsModuleKindComponent$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirJsModuleKind.class));

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModuleKind.values().length];
            try {
                iArr[ModuleKind.UMD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ModuleKind.PLAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0044  */
    public static final void checkJsModuleUsage(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol, AbstractKtSourceElement abstractKtSourceElement) {
        boolean z;
        FirFileSymbol symbol;
        FirFileSymbol symbol2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firBasedSymbol.getClass();
        ModuleKind jsModuleKind = getJsModuleKind(checkerContext.getSession());
        if (jsModuleKind == null) {
            return;
        }
        FirSession session = firBasedSymbol.getModuleData().getSession();
        FirBasedSymbol<?> rootClassLikeSymbolOrSelf = FirJsHelpersKt.getRootClassLikeSymbolOrSelf(firBasedSymbol, session);
        FirFile containingFile = FirJsHelpersKt.getContainingFile(rootClassLikeSymbolOrSelf);
        ClassId classId = JsStandardClassIds.Annotations.JsModule;
        boolean z2 = false;
        if (FirHelpersKt.getAnnotationStringParameter(rootClassLikeSymbolOrSelf, classId, session) != null) {
            z = true;
        } else {
            if (((containingFile == null || (symbol2 = containingFile.getSymbol()) == null) ? null : FirHelpersKt.getAnnotationStringParameter(symbol2, classId, session)) != null) {
                z = true;
            } else {
                z = false;
            }
        }
        ClassId classId2 = JsStandardClassIds.Annotations.JsNonModule;
        if (FirAnnotationUtilsKt.hasAnnotation(rootClassLikeSymbolOrSelf, classId2, session) || (containingFile != null && (symbol = containingFile.getSymbol()) != null && FirAnnotationUtilsKt.hasAnnotation(symbol, classId2, session))) {
            z2 = true;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[jsModuleKind.ordinal()];
        if (i == 1) {
            if ((z2 || !z) && (!z2 || z)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirJsErrors.INSTANCE.getCALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (i != 2) {
            if (z || !z2) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirJsErrors.INSTANCE.getCALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM(), firBasedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (z2 || !z) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirJsErrors.INSTANCE.getCALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM(), firBasedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private static final ModuleKind getJsModuleKind(FirSession firSession) {
        FirJsModuleKind jsModuleKindComponent = getJsModuleKindComponent(firSession);
        if (jsModuleKindComponent != null) {
            return jsModuleKindComponent.getModuleKind();
        }
        return null;
    }

    private static final FirJsModuleKind getJsModuleKindComponent(FirSession firSession) {
        return (FirJsModuleKind) jsModuleKindComponent$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
