package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExternalChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH\u0016J)\u0010!\u001a\u00020\u000b*\u00020\u0011H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0018\u001a\u00020\u0005*\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsExternalChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/declaration/FirWebCommonExternalChecker;", "<init>", "()V", "isNativeOrEffectivelyExternal", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reportExternalEnum", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "additionalCheck", "isDefinedExternallyCallableId", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "hasExternalLikeAnnotations", "allowsReporting", "Lorg/jetbrains/kotlin/KtSourceElement;", "getAllowsReporting", "(Lorg/jetbrains/kotlin/KtSourceElement;)Z", "varargElementType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getVarargElementType", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "checkEnumEntry", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsExternalChecker extends FirWebCommonExternalChecker {
    public static final FirJsExternalChecker INSTANCE = new FirJsExternalChecker();

    private FirJsExternalChecker() {
        super(true);
    }

    private static final void additionalCheck$reportOnParametersAndReturnTypesIf(FirDeclaration firDeclaration, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtDiagnosticFactory0 ktDiagnosticFactory0, Function1<? super ConeKotlinType, Boolean> function1) {
        KtSourceElement source;
        if (!(firDeclaration instanceof FirCallableDeclaration) || (firDeclaration instanceof FirDefaultPropertyAccessor) || (firDeclaration instanceof FirDefaultPropertyBackingField)) {
            return;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
        KtSourceElement source2 = firCallableDeclaration.getReturnTypeRef().getSource();
        if (source2 != null && INSTANCE.getAllowsReporting(source2) && (source = firCallableDeclaration.getReturnTypeRef().getSource()) != null) {
            additionalCheck$reportOnParametersAndReturnTypesIf$checkTypeIsNotInlineClass(function1, checkerContext, diagnosticReporter, ktDiagnosticFactory0, FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef()), source);
        }
        if (firDeclaration instanceof FirFunction) {
            for (FirValueParameter firValueParameter : ((FirFunction) firDeclaration).getValueParameters()) {
                KtSourceElement source3 = firValueParameter.getSource();
                if (source3 != null) {
                    FirJsExternalChecker firJsExternalChecker = INSTANCE;
                    if (firJsExternalChecker.getAllowsReporting(source3)) {
                        ConeKotlinType varargElementType = firJsExternalChecker.getVarargElementType(firValueParameter);
                        if (varargElementType == null) {
                            varargElementType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
                        }
                        additionalCheck$reportOnParametersAndReturnTypesIf$checkTypeIsNotInlineClass(function1, checkerContext, diagnosticReporter, ktDiagnosticFactory0, varargElementType, source3);
                    }
                }
            }
        }
    }

    private static final void additionalCheck$reportOnParametersAndReturnTypesIf$checkTypeIsNotInlineClass(Function1<? super ConeKotlinType, Boolean> function1, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtDiagnosticFactory0 ktDiagnosticFactory0, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement) {
        if (((Boolean) function1.invoke(coneKotlinType)).booleanValue()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    public static boolean b(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return FirHelpersKt.isValueClass(coneKotlinType, checkerContext.getSession());
    }

    private final void checkEnumEntry(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirExpression initializer;
        if ((firDeclaration instanceof FirEnumEntry) && (initializer = ((FirEnumEntry) firDeclaration).getInitializer()) != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializer.getSource(), FirJsErrors.INSTANCE.getEXTERNAL_ENUM_ENTRY_WITH_BODY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final boolean getAllowsReporting(KtSourceElement ktSourceElement) {
        return !(ktSourceElement.getKind() instanceof KtFakeSourceElementKind) || Intrinsics.areEqual(ktSourceElement.getKind(), KtFakeSourceElementKind.PropertyFromParameter.INSTANCE);
    }

    private final ConeKotlinType getVarargElementType(FirValueParameter firValueParameter) {
        ConeTypeProjection coneTypeProjection;
        if (firValueParameter.getIsVararg() && (coneTypeProjection = (ConeTypeProjection) ArraysKt.firstOrNull(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()).getTypeArguments())) != null) {
            return ConeTypeProjectionKt.getType(coneTypeProjection);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public void additionalCheck(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        final CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if ((firDeclaration instanceof FirFunction) && ((FirMemberDeclaration) firDeclaration).getStatus().isInline()) {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ((FirFunction) firDeclaration).getSource(), FirWebCommonErrors.INSTANCE.getINLINE_EXTERNAL_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
        }
        additionalCheck$reportOnParametersAndReturnTypesIf(firDeclaration, checkerContext2, diagnosticReporter2, LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.JsAllowValueClassesInExternals) ? FirJsErrors.INSTANCE.getINLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING() : FirJsErrors.INSTANCE.getINLINE_CLASS_IN_EXTERNAL_DECLARATION(), new Function1() { // from class: p95
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirJsExternalChecker.b(checkerContext2, (ConeKotlinType) obj));
            }
        });
        if (LanguageVersionUtilsKt.isDisabled(checkerContext2, LanguageFeature.JsEnableExtensionFunctionInExternals)) {
            additionalCheck$reportOnParametersAndReturnTypesIf(firDeclaration, checkerContext2, diagnosticReporter2, FirJsErrors.INSTANCE.getEXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION(), new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExternalChecker.additionalCheck.2
                public Object get(Object obj) {
                    return Boolean.valueOf(CompilerConeAttributesKt.isExtensionFunctionType((ConeKotlinType) obj));
                }
            });
        }
        checkEnumEntry(checkerContext2, diagnosticReporter2, firDeclaration);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public boolean hasExternalLikeAnnotations(FirDeclaration declaration, FirSession session) {
        declaration.getClass();
        session.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(declaration, JsStandardClassIds.Annotations.JsNative, session);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public boolean isDefinedExternallyCallableId(CallableId callableId) {
        return Intrinsics.areEqual(callableId, JsStandardClassIds.Callables.JsDefinedExternally);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public boolean isNativeOrEffectivelyExternal(FirBasedSymbol<?> symbol, FirSession session) {
        symbol.getClass();
        session.getClass();
        return FirWebCommonHelpersKt.isNativeObject(symbol, session);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public void reportExternalEnum(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), FirJsErrors.INSTANCE.getENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
