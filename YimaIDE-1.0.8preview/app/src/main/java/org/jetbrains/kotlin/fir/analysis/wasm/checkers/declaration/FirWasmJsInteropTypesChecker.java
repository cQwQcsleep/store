package org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.wasm.FirWasmErrors;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.FirWasmJsCodeHelpersKt;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.FirWasmJsExportHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.name.WasmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/declaration/FirWasmJsInteropTypesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "Position", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmJsInteropTypesChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirWasmJsInteropTypesChecker INSTANCE = new FirWasmJsInteropTypesChecker();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/declaration/FirWasmJsInteropTypesChecker$Position;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "TYPE_PARAMETER_UPPER_BOUND", "PROPERTY_TYPE", "VALUE_PARAMETER_TYPE", "VARARG_VALUE_PARAMETER_TYPE", "RETURN_TYPE", "FUNCTION_TYPE_PARAMETER_TYPE", "FUNCTION_TYPE_RETURN_TYPE", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Position {
        TYPE_PARAMETER_UPPER_BOUND("upper bound of JS interop type parameter"),
        PROPERTY_TYPE("type of JS interop property"),
        VALUE_PARAMETER_TYPE("value parameter type of JS interop function"),
        VARARG_VALUE_PARAMETER_TYPE("value parameter type of JS interop function"),
        RETURN_TYPE("return type of JS interop function"),
        FUNCTION_TYPE_PARAMETER_TYPE("parameter type of JS interop function type"),
        FUNCTION_TYPE_RETURN_TYPE("return type of JS interop function type");

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String description;

        Position(String str) {
            this.description = str;
        }

        public static EnumEntries<Position> getEntries() {
            return $ENTRIES;
        }

        public final String getDescription() {
            return this.description;
        }
    }

    private FirWasmJsInteropTypesChecker() {
        super(MppCheckerKind.Platform);
    }

    private static final void check$checkSupportInJsInterop(FirTypeRef firTypeRef, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirSession firSession, Position position, KtSourceElement ktSourceElement) {
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firTypeRef);
        if (position == Position.VARARG_VALUE_PARAMETER_TYPE) {
            coneType = ArrayUtilsKt.varargElementType(coneType);
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneType);
        if (!check$isSupportedInJsInterop(coneKotlinTypeFullyExpandedType, firSession, checkerContext, position)) {
            KtSourceElement source = firTypeRef.getSource();
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (source == null ? ktSourceElement : source), (KtDiagnosticFactory2) FirWasmErrors.INSTANCE.getWRONG_JS_INTEROP_TYPE(), (Object) coneKotlinTypeFullyExpandedType, (Object) position.getDescription(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        FirTypeRef delegatedTypeRef = firResolvedTypeRef != null ? firResolvedTypeRef.getDelegatedTypeRef() : null;
        FirFunctionTypeRef firFunctionTypeRef = delegatedTypeRef instanceof FirFunctionTypeRef ? (FirFunctionTypeRef) delegatedTypeRef : null;
        if (firFunctionTypeRef == null) {
            return;
        }
        List parameters = firFunctionTypeRef.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirFunctionTypeParameter) it.next()).getReturnTypeRef());
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            check$checkSupportInJsInterop((FirTypeRef) it2.next(), checkerContext, diagnosticReporter, firSession, Position.FUNCTION_TYPE_PARAMETER_TYPE, firFunctionTypeRef.getSource());
        }
        check$checkSupportInJsInterop(firFunctionTypeRef.getReturnTypeRef(), checkerContext, diagnosticReporter, firSession, Position.FUNCTION_TYPE_RETURN_TYPE, firFunctionTypeRef.getSource());
    }

    private static final boolean check$isExternalJsInteropDeclaration(FirDeclaration firDeclaration, FirSession firSession) {
        return FirWebCommonHelpersKt.isEffectivelyExternal(firDeclaration.getSymbol(), firSession) && !FirAnnotationUtilsKt.hasAnnotation(firDeclaration.getAnnotations(), WasmStandardClassIds.Annotations.WasmImport, firSession);
    }

    private static final boolean check$isJsCodeDeclaration(FirDeclaration firDeclaration) {
        if (firDeclaration instanceof FirNamedFunction) {
            return FirWasmJsCodeHelpersKt.hasValidJsCodeBody((FirNamedFunction) firDeclaration);
        }
        if (firDeclaration instanceof FirProperty) {
            return FirWasmJsCodeHelpersKt.hasValidJsCodeBody((FirProperty) firDeclaration);
        }
        return false;
    }

    private static final boolean check$isSupportedInJsInterop(ConeKotlinType coneKotlinType, FirSession firSession, CheckerContext checkerContext, Position position) {
        if (ConeBuiltinTypeUtilsKt.isUnit(coneKotlinType) || ConeBuiltinTypeUtilsKt.isNothing(coneKotlinType)) {
            return position == Position.RETURN_TYPE || position == Position.FUNCTION_TYPE_RETURN_TYPE;
        }
        if (ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneKotlinType) || ConeBuiltinTypeUtilsKt.isUnsignedTypeOrNullableUnsignedType(coneKotlinType) || ConeBuiltinTypeUtilsKt.isString(coneKotlinType) || ConeBuiltinTypeUtilsKt.isNullableString(coneKotlinType) || (coneKotlinType instanceof ConeTypeParameterType) || FunctionalTypeUtilsKt.isBasicFunctionType(coneKotlinType, firSession)) {
            return true;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinType);
        if (regularClassSymbol == null) {
            return false;
        }
        if (regularClassSymbol.getRawStatus().isExpect()) {
            return true;
        }
        return FirWebCommonHelpersKt.isEffectivelyExternal(regularClassSymbol, firSession);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0058  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        FirSession session = checkerContext.getSession();
        if (check$isExternalJsInteropDeclaration(firDeclaration, session) || check$isJsCodeDeclaration(firDeclaration) || FirWasmJsExportHelpersKt.isJsExportedDeclaration(firDeclaration, session)) {
            if ((firDeclaration instanceof FirFunction) && FirWasmJsExportHelpersKt.isJsExportedDeclaration(firDeclaration, session) && LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextParameters)) {
                FirFunction firFunction = (FirFunction) firDeclaration;
                if (firFunction.getContextParameters().isEmpty()) {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                } else {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirWasmErrors.INSTANCE.getEXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                }
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
            KtSourceElement source = firDeclaration.getSource();
            if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
                return;
            }
            if (firDeclaration instanceof FirTypeParameterRefsOwner) {
                for (FirTypeParameterRef firTypeParameterRef : ((FirTypeParameterRefsOwner) firDeclaration).getTypeParameters()) {
                    Iterator<FirResolvedTypeRef> it = firTypeParameterRef.getSymbol().getResolvedBounds().iterator();
                    while (it.hasNext()) {
                        check$checkSupportInJsInterop(it.next(), checkerContext2, diagnosticReporter2, session, Position.TYPE_PARAMETER_UPPER_BOUND, firTypeParameterRef.getSource());
                    }
                }
            }
            if (firDeclaration instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                check$checkSupportInJsInterop(firProperty.getReturnTypeRef(), checkerContext2, diagnosticReporter2, session, Position.PROPERTY_TYPE, firProperty.getSource());
            } else if (firDeclaration instanceof FirFunction) {
                FirFunction firFunction2 = (FirFunction) firDeclaration;
                for (FirValueParameter firValueParameter : firFunction2.getValueParameters()) {
                    check$checkSupportInJsInterop(firValueParameter.getReturnTypeRef(), checkerContext2, diagnosticReporter2, session, firValueParameter.getIsVararg() ? Position.VARARG_VALUE_PARAMETER_TYPE : Position.VALUE_PARAMETER_TYPE, firValueParameter.getSource());
                }
                check$checkSupportInJsInterop(firFunction2.getReturnTypeRef(), checkerContext2, diagnosticReporter2, session, Position.RETURN_TYPE, firFunction2.getSource());
            }
        }
    }
}
