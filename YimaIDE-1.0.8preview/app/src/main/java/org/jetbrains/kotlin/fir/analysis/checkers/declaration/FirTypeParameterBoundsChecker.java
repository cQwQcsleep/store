package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirInconsistentTypeParameterHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002#$B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J9\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0004R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014J9\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014J-\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017J9\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014J-\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017J-\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017J-\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017J-\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017J\u001e\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u000e\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u001fH\u0002J-\u0010\"\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0002%&¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "classKinds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "containingDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "checkFinalUpperBounds", "checkExtensionOrContextFunctionTypeBound", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;)V", "checkTypeAliasBound", "checkOnlyOneTypeParameterBound", "checkBoundUniqueness", "checkConflictingBounds", "checkDynamicBounds", "isRelated", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", ModuleXmlParser.TYPE, "checkInconsistentTypeParameterBounds", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirTypeParameterBoundsChecker extends FirDeclarationChecker<FirTypeParameter> {
    private final Set<ClassKind> classKinds;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirTypeParameterBoundsChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firTypeParameter.getClass();
            FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            if (firBasedSymbol != null && FirHelpersKt.isExpect(firBasedSymbol)) {
                check(checkerContext, diagnosticReporter, firTypeParameter, firBasedSymbol);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterBoundsChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirTypeParameterBoundsChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firTypeParameter.getClass();
            FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            if (firBasedSymbol == null || FirHelpersKt.isExpect(firBasedSymbol)) {
                return;
            }
            check(checkerContext, diagnosticReporter, firTypeParameter, firBasedSymbol);
        }
    }

    private FirTypeParameterBoundsChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
        this.classKinds = SetsKt.setOf(new ClassKind[]{ClassKind.CLASS, ClassKind.ENUM_CLASS, ClassKind.OBJECT});
    }

    private final void checkBoundUniqueness(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
        FirRegularClassSymbol regularClassSymbol;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<FirResolvedTypeRef> resolvedBounds = firTypeParameter.getSymbol().getResolvedBounds();
        ArrayList arrayList = new ArrayList();
        for (Object obj : resolvedBounds) {
            if (!(((FirResolvedTypeRef) obj) instanceof FirErrorTypeRef)) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList<FirResolvedTypeRef> arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) obj2;
            ConeKotlinType coneKotlinTypeFullyExpandedClassId = FirHelpersKt.fullyExpandedClassId(firResolvedTypeRef.getConeType(), checkerContext.getSession());
            if (coneKotlinTypeFullyExpandedClassId == null) {
                coneKotlinTypeFullyExpandedClassId = firResolvedTypeRef.getConeType();
            }
            if (hashSet.add(coneKotlinTypeFullyExpandedClassId)) {
                arrayList2.add(obj2);
            }
        }
        boolean zSupportsFeature = FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()).supportsFeature(LanguageFeature.AllowAnyAsAnActualTypeForExpectInterface);
        for (FirResolvedTypeRef firResolvedTypeRef2 : arrayList2) {
            ConeKotlinType coneType = firResolvedTypeRef2.getConeType();
            if (zSupportsFeature) {
                ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneType);
                if (AbbreviatedTypeAttributeKt.getAbbreviatedType(coneKotlinTypeFullyExpandedType) != null && ConeBuiltinTypeUtilsKt.isAnyOrNullableAny(coneKotlinTypeFullyExpandedType)) {
                    coneType = null;
                }
            }
            if (coneType != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneType)) != null && this.classKinds.contains(regularClassSymbol.getClassKind()) && linkedHashSet.add(regularClassSymbol) && linkedHashSet.size() > 1) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedTypeRef2.getSource(), FirErrors.INSTANCE.getONLY_ONE_CLASS_BOUND_ALLOWED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        Iterator it = CollectionsKt.minus(arrayList, arrayList2).iterator();
        while (it.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirResolvedTypeRef) it.next()).getSource(), FirErrors.INSTANCE.getREPEATED_BOUND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkConflictingBounds(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
        if (firTypeParameter.getBounds().size() >= 2) {
            List<FirResolvedTypeRef> resolvedBounds = firTypeParameter.getSymbol().getResolvedBounds();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
            Iterator<T> it = resolvedBounds.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
            }
            if (checkConflictingBounds$anyConflictingTypes(checkerContext, this, arrayList)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeParameter.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONFLICTING_UPPER_BOUNDS(), (Object) firTypeParameter.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private static final boolean checkConflictingBounds$anyConflictingTypes(CheckerContext checkerContext, FirTypeParameterBoundsChecker firTypeParameterBoundsChecker, List<? extends ConeKotlinType> list) {
        List<? extends ConeKotlinType> list2 = list;
        for (ConeKotlinType coneKotlinType : list2) {
            if (!TypeUtilsKt.canHaveSubtypesAccordingToK1(coneKotlinType, checkerContext.getSession())) {
                for (ConeKotlinType coneKotlinType2 : list2) {
                    if (!Intrinsics.areEqual(coneKotlinType, coneKotlinType2) && !firTypeParameterBoundsChecker.isRelated(coneKotlinType, TypeComponentsKt.getTypeContext(checkerContext.getSession()), coneKotlinType2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final void checkDynamicBounds(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        for (FirTypeRef firTypeRef : firTypeParameter.getBounds()) {
            if (FirTypeUtilsKt.getConeType(firTypeRef) instanceof ConeDynamicType) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getDYNAMIC_UPPER_BOUND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }

    private final void checkExtensionOrContextFunctionTypeBound(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        for (FirResolvedTypeRef firResolvedTypeRef : firTypeParameter.getSymbol().getResolvedBounds()) {
            ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, firResolvedTypeRef.getConeType()));
            if (CompilerConeAttributesKt.isExtensionFunctionType(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound) || CompilerConeAttributesKt.getHasContextParameters(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound)) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firResolvedTypeRef.getSource(), FirErrors.INSTANCE.getUPPER_BOUND_IS_EXTENSION_OR_CONTEXT_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }

    private final void checkFinalUpperBounds(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter, FirBasedSymbol<?> firBasedSymbol) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        if ((firBasedSymbol instanceof FirCallableSymbol) && ((FirCallableSymbol) firBasedSymbol).getResolvedStatus().isOverride()) {
            return;
        }
        for (FirResolvedTypeRef firResolvedTypeRef : firTypeParameter.getSymbol().getResolvedBounds()) {
            ConeKotlinType coneType = firResolvedTypeRef.getConeType();
            if ((coneType instanceof ConeDynamicType) || TypeUtilsKt.canHaveSubtypesAccordingToK1(coneType, checkerContext.getSession())) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firResolvedTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getFINAL_UPPER_BOUND(), (Object) firResolvedTypeRef.getConeType(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }

    private final void checkInconsistentTypeParameterBounds(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
        if (firTypeParameter.getBounds().size() <= 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (FirResolvedTypeRef firResolvedTypeRef : firTypeParameter.getSymbol().getResolvedBounds()) {
            FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(firResolvedTypeRef, checkerContext.getSession());
            if (regularClassSymbol != null) {
                if (!linkedHashSet.add(regularClassSymbol)) {
                    return;
                } else {
                    arrayList.add(TuplesKt.to(firResolvedTypeRef, regularClassSymbol));
                }
            }
        }
        FirInconsistentTypeParameterHelpersKt.checkInconsistentTypeParameters(checkerContext, diagnosticReporter, arrayList, firTypeParameter.getSource(), false);
    }

    private final void checkOnlyOneTypeParameterBound(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter) {
        KtSourceElement source;
        List<FirResolvedTypeRef> resolvedBounds = firTypeParameter.getSymbol().getResolvedBounds();
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : resolvedBounds) {
            if (hashSet.add(((FirResolvedTypeRef) obj).getConeType())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((FirResolvedTypeRef) obj2).getConeType() instanceof ConeTypeParameterType) {
                arrayList2.add(obj2);
            } else {
                arrayList3.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        if (list.size() > 1 || (list.size() == 1 && !list2.isEmpty())) {
            SourceNavigator sourceNavigatorForElement = SourceNavigator.INSTANCE.forElement(firTypeParameter);
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : arrayList) {
                if (sourceNavigatorForElement.isInTypeConstraint((FirResolvedTypeRef) obj3)) {
                    arrayList4.add(obj3);
                }
            }
            Set set = CollectionsKt.toSet(arrayList4);
            if (arrayList.size() == 2) {
                FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) CollectionsKt.firstOrNull(list2);
                if (firResolvedTypeRef == null) {
                    firResolvedTypeRef = (FirResolvedTypeRef) CollectionsKt.last(list);
                }
                source = set.contains(firResolvedTypeRef) ? firResolvedTypeRef.getSource() : firTypeParameter.getSource();
            } else {
                source = firTypeParameter.getSource();
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getBOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkTypeAliasBound(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter, FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirTypeAliasSymbol) {
            List<FirTypeRef> bounds = firTypeParameter.getBounds();
            ArrayList arrayList = new ArrayList();
            for (Object obj : bounds) {
                KtSourceElement source = ((FirTypeRef) obj).getSource();
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirTypeRef) it.next()).getSource(), FirErrors.INSTANCE.getBOUND_ON_TYPE_ALIAS_PARAMETER_NOT_ALLOWED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final boolean isRelated(KotlinTypeMarker kotlinTypeMarker, TypeCheckerProviderContext typeCheckerProviderContext, KotlinTypeMarker kotlinTypeMarker2) {
        return TypeUtilsKt.isSubtypeOf(kotlinTypeMarker, typeCheckerProviderContext, kotlinTypeMarker2) || FirHelpersKt.isSupertypeOf(kotlinTypeMarker, typeCheckerProviderContext, kotlinTypeMarker2);
    }

    public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeParameter.getClass();
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            return;
        }
        checkFinalUpperBounds(checkerContext, diagnosticReporter, firTypeParameter, firBasedSymbol);
        checkExtensionOrContextFunctionTypeBound(checkerContext, diagnosticReporter, firTypeParameter);
        FirCallableSymbol firCallableSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
        if (firCallableSymbol == null || !FirHelpersKt.isInlineOnly((FirCallableSymbol<?>) firCallableSymbol, checkerContext.getSession())) {
            checkOnlyOneTypeParameterBound(checkerContext, diagnosticReporter, firTypeParameter);
        }
        checkBoundUniqueness(checkerContext, diagnosticReporter, firTypeParameter);
        checkConflictingBounds(checkerContext, diagnosticReporter, firTypeParameter);
        checkTypeAliasBound(checkerContext, diagnosticReporter, firTypeParameter, firBasedSymbol);
        checkDynamicBounds(checkerContext, diagnosticReporter, firTypeParameter);
        checkInconsistentTypeParameterBounds(checkerContext, diagnosticReporter, firTypeParameter);
    }

    public /* synthetic */ FirTypeParameterBoundsChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }
}
