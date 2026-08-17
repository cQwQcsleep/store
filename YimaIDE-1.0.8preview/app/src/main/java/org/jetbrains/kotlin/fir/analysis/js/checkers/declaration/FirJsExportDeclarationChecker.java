package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructedClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.js.common.IdentifierPolicyKt;
import org.jetbrains.kotlin.name.JsStandardClassIds;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ5\u0010\u0014\u001a\u00020\u000f*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0019H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001aJ3\u0010\u001b\u001a\u00020\u000f*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0019H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001aJ=\u0010\u001c\u001a\u00020\u000f*\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0019H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010 J5\u0010\u001c\u001a\u00020\u000f*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0019H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001aJ-\u0010#\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020$H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010%R\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u001c\u0010\u0011\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R%\u0010!\u001a\u00020\u000f*\u00020\u00158BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010\t\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsExportDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "isInsideInterface", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "isInlineWithReified", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "isExportableReturn", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "currentlyProcessed", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/Set;)Z", "isExportableTypeArguments", "isExportable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "declarationSite", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;Ljava/util/Set;)Z", "isPrimitiveExportableConeKotlinType", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "validateDeclarationOnConsumableName", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsExportDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsExportDeclarationChecker INSTANCE = new FirJsExportDeclarationChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ClassKind.OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Variance.values().length];
            try {
                iArr2[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ProjectionKind.values().length];
            try {
                iArr3[ProjectionKind.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr3[ProjectionKind.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[ProjectionKind.IN.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[ProjectionKind.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private FirJsExportDeclarationChecker() {
        super(MppCheckerKind.Platform);
    }

    private static final void check$checkTypeParameter(CheckerContext checkerContext, FirDeclaration firDeclaration, DiagnosticReporter diagnosticReporter, FirTypeParameterRef firTypeParameterRef) {
        if (firTypeParameterRef instanceof FirConstructedClassTypeParameterRef) {
            return;
        }
        for (FirResolvedTypeRef firResolvedTypeRef : firTypeParameterRef.getSymbol().getResolvedBounds()) {
            CheckerContext checkerContext2 = checkerContext;
            if (!isExportable$default(INSTANCE, checkerContext2, firResolvedTypeRef.getConeType(), checkerContext.getSession(), null, 4, null)) {
                KtSourceElement source = firResolvedTypeRef.getSource();
                if (source == null && (source = firTypeParameterRef.getSource()) == null) {
                    source = ((FirMemberDeclaration) firDeclaration).getSource();
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) FirJsErrors.INSTANCE.getNON_EXPORTABLE_TYPE(), (Object) "upper bound", (Object) firResolvedTypeRef.getConeType(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
            checkerContext = checkerContext2;
        }
    }

    private static final void check$checkValueParameter(CheckerContext checkerContext, FirDeclaration firDeclaration, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
        if (isExportable$default(INSTANCE, checkerContext, coneType, checkerContext.getSession(), null, 4, null)) {
            return;
        }
        KtSourceElement source = firValueParameter.getSource();
        if (source == null) {
            source = ((FirMemberDeclaration) firDeclaration).getSource();
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) FirJsErrors.INSTANCE.getNON_EXPORTABLE_TYPE(), (Object) "parameter", (Object) coneType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private static final void check$reportWrongExportedDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, String str) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirMemberDeclaration) firDeclaration).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_EXPORTED_DECLARATION(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private final boolean isExportable(CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirSession firSession, Set<ConeKotlinType> set) {
        if ((coneKotlinType instanceof ConeErrorType) || !set.add(coneKotlinType)) {
            return true;
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(coneKotlinType, firSession, FirJsExportDeclarationChecker$isExportable$expandedType$1.INSTANCE);
        boolean zIsBasicFunctionType = FunctionalTypeUtilsKt.isBasicFunctionType(coneKotlinTypeFullyExpandedType, firSession);
        boolean zIsExportableTypeArguments = isExportableTypeArguments(checkerContext, coneKotlinTypeFullyExpandedType, firSession, set);
        set.remove(coneKotlinType);
        if (zIsBasicFunctionType || !zIsExportableTypeArguments) {
            return zIsExportableTypeArguments;
        }
        ConeKotlinType coneKotlinTypeWithNullability$default = TypeUtilsKt.withNullability$default(coneKotlinTypeFullyExpandedType, false, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null);
        boolean z = ConeBuiltinTypeUtilsKt.isAny(coneKotlinTypeWithNullability$default) || ConeBuiltinTypeUtilsKt.isNullableAny(coneKotlinTypeWithNullability$default) || (coneKotlinTypeWithNullability$default instanceof ConeDynamicType) || isPrimitiveExportableConeKotlinType(checkerContext, coneKotlinTypeWithNullability$default);
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, coneKotlinTypeFullyExpandedType);
        if (z) {
            return true;
        }
        if (symbol == null || !FirDeclarationUtilKt.isMemberDeclaration(symbol)) {
            return false;
        }
        return ConeBuiltinTypeUtilsKt.isEnum(coneKotlinTypeFullyExpandedType) || FirWebCommonHelpersKt.isEffectivelyExternal(symbol, firSession) || FirJsHelpersKt.isExportedObject(symbol, firSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isExportable$default(FirJsExportDeclarationChecker firJsExportDeclarationChecker, CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirSession firSession, Set set, int i, Object obj) {
        if ((i & 4) != 0) {
            set = new HashSet();
        }
        return firJsExportDeclarationChecker.isExportable(checkerContext, coneKotlinType, firSession, set);
    }

    private final boolean isExportableReturn(CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirSession firSession, Set<ConeKotlinType> set) {
        return ConeBuiltinTypeUtilsKt.isUnit(coneKotlinType) || isExportable(checkerContext, coneKotlinType, firSession, set);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isExportableReturn$default(FirJsExportDeclarationChecker firJsExportDeclarationChecker, CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirSession firSession, Set set, int i, Object obj) {
        if ((i & 4) != 0) {
            set = new HashSet();
        }
        return firJsExportDeclarationChecker.isExportableReturn(checkerContext, coneKotlinType, firSession, set);
    }

    private final boolean isExportableTypeArguments(CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirSession firSession, Set<ConeKotlinType> set) {
        if (coneKotlinType.getTypeArguments().length == 0) {
            return true;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinType);
        if (regularClassSymbol == null) {
            return false;
        }
        int length = coneKotlinType.getTypeArguments().length;
        for (int i = 0; i < length; i++) {
            FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) CollectionsKt.getOrNull(regularClassSymbol.getTypeParameterSymbols(), i);
            if (firTypeParameterSymbol == null || !isExportable(checkerContext, coneKotlinType.getTypeArguments()[i], firSession, firTypeParameterSymbol, set)) {
                return false;
            }
        }
        return true;
    }

    private final boolean isInlineWithReified(FirCallableSymbol<?> firCallableSymbol) {
        if (firCallableSymbol instanceof FirPropertyAccessorSymbol) {
            return isInlineWithReified(((FirPropertyAccessorSymbol) firCallableSymbol).getPropertySymbol());
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = firCallableSymbol.getTypeParameterSymbols();
        if ((typeParameterSymbols instanceof Collection) && typeParameterSymbols.isEmpty()) {
            return false;
        }
        Iterator<T> it = typeParameterSymbols.iterator();
        while (it.hasNext()) {
            if (((FirTypeParameterSymbol) it.next()).isReified()) {
                return true;
            }
        }
        return false;
    }

    private final boolean isInsideInterface(CheckerContext checkerContext) {
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        FirClassSymbol firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
        return firClassSymbol != null && firClassSymbol.getClassKind() == ClassKind.INTERFACE;
    }

    private final boolean isPrimitiveExportableConeKotlinType(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        if ((coneKotlinType instanceof ConeTypeParameterType) || ConeBuiltinTypeUtilsKt.isBoolean(coneKotlinType) || ConeBuiltinTypeUtilsKt.isThrowableOrNullableThrowable(coneKotlinType) || ConeBuiltinTypeUtilsKt.isString(coneKotlinType)) {
            return true;
        }
        return (ConeBuiltinTypeUtilsKt.isPrimitiveNumberOrNullableType(coneKotlinType) && (checkerContext.get$languageVersionSettings().supportsFeature(LanguageFeature.JsAllowLongInExportedDeclarations) || !ConeBuiltinTypeUtilsKt.isLong(coneKotlinType))) || ConeBuiltinTypeUtilsKt.isNothingOrNullableNothing(coneKotlinType) || ConeBuiltinTypeUtilsKt.isPrimitiveArray(coneKotlinType) || ConeBuiltinTypeUtilsKt.isNonPrimitiveArray(coneKotlinType) || ConeBuiltinTypeUtilsKt.isList(coneKotlinType) || ConeBuiltinTypeUtilsKt.isMutableList(coneKotlinType) || ConeBuiltinTypeUtilsKt.isSet(coneKotlinType) || ConeBuiltinTypeUtilsKt.isMutableSet(coneKotlinType) || ConeBuiltinTypeUtilsKt.isMap(coneKotlinType) || ConeBuiltinTypeUtilsKt.isMutableMap(coneKotlinType);
    }

    private final void validateDeclarationOnConsumableName(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirMemberDeclaration firMemberDeclaration) {
        KtSourceElement source;
        if (!FirHelpersKt.isTopLevel(checkerContext) || FirDeclarationUtilKt.getNameOrSpecialName(firMemberDeclaration).isSpecial()) {
            return;
        }
        FirExpression annotationFirstArgument = FirHelpersKt.getAnnotationFirstArgument(firMemberDeclaration.getSymbol(), JsStandardClassIds.Annotations.JsName, checkerContext.getSession());
        if (annotationFirstArgument == null || (source = annotationFirstArgument.getSource()) == null) {
            source = firMemberDeclaration.getSource();
        }
        KtSourceElement ktSourceElement = source;
        FirLiteralExpression firLiteralExpression = annotationFirstArgument instanceof FirLiteralExpression ? (FirLiteralExpression) annotationFirstArgument : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        String strAsString = value instanceof String ? (String) value : null;
        if (strAsString == null) {
            strAsString = FirDeclarationUtilKt.getNameOrSpecialName(firMemberDeclaration).asString();
            strAsString.getClass();
        }
        String str = strAsString;
        if (IdentifierPolicyKt.getSPECIAL_KEYWORDS().contains(str)) {
            return;
        }
        if (IdentifierPolicyKt.getRESERVED_KEYWORDS().contains(str) || !Intrinsics.areEqual(FirJsHelpersKt.sanitizeName(str), str)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getNON_CONSUMABLE_EXPORTED_IDENTIFIER(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:148:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:151:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:154:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:157:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:162:0x0301 A[RETURN] */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        String str;
        String str2;
        ConeKotlinType coneType;
        FirRegularClassSymbol regularClassSymbol;
        String str3;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (FirJsHelpersKt.isExportedObject(checkerContext, firDeclaration.getSymbol()) && (firDeclaration instanceof FirMemberDeclaration)) {
            boolean zHasAnnotation = FirAnnotationUtilsKt.hasAnnotation(firDeclaration, JsStandardClassIds.Annotations.JsName, checkerContext.getSession());
            if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.AllowExpectDeclarationsInJsExport) && ((FirMemberDeclaration) firDeclaration).getStatus().isExpect()) {
                check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "expect");
            }
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            validateDeclarationOnConsumableName(checkerContext, diagnosticReporter, firMemberDeclaration);
            if (firMemberDeclaration instanceof FirFunction) {
                if (firMemberDeclaration.getStatus().isExternal() && FirHelpersKt.isTopLevel(checkerContext)) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "external function");
                    return;
                }
                FirFunction firFunction = (FirFunction) firDeclaration;
                Iterator<FirTypeParameterRef> it = firFunction.getTypeParameters().iterator();
                while (it.hasNext()) {
                    check$checkTypeParameter(checkerContext, firDeclaration, diagnosticReporter, it.next());
                }
                if (isInlineWithReified(firFunction.getSymbol())) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "inline function with reified type parameters");
                    return;
                }
                if (firMemberDeclaration.getStatus().isSuspend() && !checkerContext.get$languageVersionSettings().supportsFeature(LanguageFeature.JsAllowExportingSuspendFunctions)) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "suspend function");
                    return;
                }
                boolean z = firDeclaration instanceof FirConstructor;
                if (z && !((FirConstructor) firDeclaration).getIsPrimary() && !zHasAnnotation) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "secondary constructor without @JsName");
                }
                if (firDeclaration instanceof FirPropertyAccessor) {
                    return;
                }
                Iterator it2 = CollectionsKt.plus(firFunction.getContextParameters(), firFunction.getValueParameters()).iterator();
                while (it2.hasNext()) {
                    check$checkValueParameter(checkerContext, firDeclaration, diagnosticReporter, (FirValueParameter) it2.next());
                }
                ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef());
                if (z || isExportableReturn$default(this, checkerContext, coneType2, checkerContext.getSession(), null, 4, null)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory2) FirJsErrors.INSTANCE.getNON_EXPORTABLE_TYPE(), (Object) "return", (Object) coneType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
            if (firMemberDeclaration instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                KtSourceElement source = firProperty.getSource();
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) {
                    return;
                }
                if (firMemberDeclaration.getStatus().isExternal() && FirHelpersKt.isTopLevel(checkerContext)) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "external property");
                    return;
                }
                if (FirDeclarationUtilKt.isExtension((FirCallableDeclaration) firDeclaration)) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "extension property");
                    return;
                }
                if (!firProperty.getContextParameters().isEmpty()) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "property with context parameters");
                    return;
                }
                FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration);
                FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
                boolean zIsEnumEntries = firClassSymbol != null ? UtilsKt.isEnumEntries(firProperty, (FirClassSymbol<?>) firClassSymbol) : false;
                ConeKotlinType coneType3 = FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef());
                if (zIsEnumEntries || isExportable$default(this, checkerContext, coneType3, checkerContext.getSession(), null, 4, null)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), (KtDiagnosticFactory2) FirJsErrors.INSTANCE.getNON_EXPORTABLE_TYPE(), (Object) "property", (Object) coneType3, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
            if (!(firMemberDeclaration instanceof FirClass)) {
                if (firMemberDeclaration instanceof FirTypeAlias) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "typealias");
                    return;
                } else {
                    if (firMemberDeclaration.getStatus().isExternal()) {
                        check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, "external declaration");
                        return;
                    }
                    return;
                }
            }
            if (firMemberDeclaration.getStatus().isExternal() && FirHelpersKt.isTopLevel(checkerContext)) {
                switch (WhenMappings.$EnumSwitchMapping$0[((FirClass) firDeclaration).getClassKind().ordinal()]) {
                    case 1:
                        str3 = "external class";
                        break;
                    case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                        str3 = null;
                        break;
                    case 3:
                        str3 = "external enum class";
                        break;
                    case 4:
                        str3 = "external enum entry";
                        break;
                    case 5:
                        str3 = "external annotation class";
                        break;
                    case 6:
                        str3 = "external object";
                        break;
                    default:
                        bu8.a();
                        return;
                }
                if (str3 != null) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, str3);
                    return;
                }
            }
            FirClass firClass = (FirClass) firDeclaration;
            Iterator<FirTypeParameterRef> it3 = firClass.getTypeParameters().iterator();
            while (it3.hasNext()) {
                check$checkTypeParameter(checkerContext, firDeclaration, diagnosticReporter, it3.next());
            }
            ClassKind classKind = firClass.getClassKind();
            if (classKind != ClassKind.ANNOTATION_CLASS) {
                if (classKind != ClassKind.CLASS) {
                    if (isInsideInterface(checkerContext)) {
                        if (!firClass.getStatus().isCompanion()) {
                            str = "nested/inner declaration inside exported interface";
                        } else if (FirWebCommonHelpersKt.isEffectivelyExternal(firClass.getSymbol(), checkerContext.getSession())) {
                            str = "external companion object";
                        }
                    }
                    str2 = null;
                } else if (!checkerContext.get$languageVersionSettings().supportsFeature(LanguageFeature.AllowInterfaceNestedClassesInJsExport) && isInsideInterface(checkerContext)) {
                    str = "nested class inside exported interface";
                } else if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.JsAllowExportingValueClasses) && (firClass.getStatus().isInline() || firClass.getStatus().isValue())) {
                    str = "value class";
                } else {
                    str2 = null;
                }
                if (!checkerContext.get$languageVersionSettings().supportsFeature(LanguageFeature.AllowNamedCompanionForJsExport) && isInsideInterface(checkerContext) && firClass.getStatus().isCompanion() && !Intrinsics.areEqual(FirDeclarationUtilKt.getNameOrSpecialName(firMemberDeclaration), SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), FirJsErrors.INSTANCE.getNAMED_COMPANION_IN_EXPORTED_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (str2 != null) {
                    check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, str2);
                }
                if (firClass.getClassKind() == ClassKind.INTERFACE) {
                    for (FirTypeRef firTypeRef : firClass.getSuperTypeRefs()) {
                        coneType = FirTypeUtilsKt.getConeType(firTypeRef);
                        if (isExportable$default(INSTANCE, checkerContext, coneType, checkerContext.getSession(), null, 4, null)) {
                            coneType = null;
                        }
                        if (coneType == null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneType)) != null) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), (KtDiagnosticFactoryForDeprecation1) FirJsErrors.INSTANCE.getEXPOSED_NOT_EXPORTED_SUPER_INTERFACE(), (Object) regularClassSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        }
                    }
                }
            }
            str = "annotation class";
            str2 = str;
            if (!checkerContext.get$languageVersionSettings().supportsFeature(LanguageFeature.AllowNamedCompanionForJsExport)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), FirJsErrors.INSTANCE.getNAMED_COMPANION_IN_EXPORTED_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (str2 != null) {
                check$reportWrongExportedDeclaration(checkerContext, diagnosticReporter, firDeclaration, str2);
            }
            if (firClass.getClassKind() == ClassKind.INTERFACE) {
                while (r8.hasNext()) {
                    coneType = FirTypeUtilsKt.getConeType(firTypeRef);
                    if (isExportable$default(INSTANCE, checkerContext, coneType, checkerContext.getSession(), null, 4, null)) {
                        coneType = null;
                    }
                    if (coneType == null) {
                    }
                }
            }
        }
    }

    private final boolean isExportable(CheckerContext checkerContext, ConeTypeProjection coneTypeProjection, FirSession firSession, FirTypeParameterSymbol firTypeParameterSymbol, Set<ConeKotlinType> set) {
        ConeKotlinType type;
        int i = WhenMappings.$EnumSwitchMapping$2[coneTypeProjection.getKind().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            type = ConeTypeProjectionKt.getType(coneTypeProjection);
        } else if (i == 4) {
            type = WhenMappings.$EnumSwitchMapping$1[firTypeParameterSymbol.getVariance().ordinal()] == 1 ? null : TypeUtilsKt.getProjectionForRawType(firTypeParameterSymbol, firSession, false);
        } else {
            bu8.a();
            return false;
        }
        if (type == null) {
            return false;
        }
        return (firTypeParameterSymbol.getVariance() == Variance.OUT_VARIANCE && ConeBuiltinTypeUtilsKt.isUnit(type)) || isExportable(checkerContext, type, firSession, set);
    }
}
