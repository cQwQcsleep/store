package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirValueClassDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.declarations.ValueClassesUtilsKt;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitAnyTypeRef;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \"2\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0003 !\"B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0016\u0010\u0015\u001a\u00020\u0011*\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0002J\u0014\u0010\u0017\u001a\u00020\u0011*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\f\u0010\u001b\u001a\u00020\u0011*\u00020\u001cH\u0002J\u0014\u0010\u001d\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\f\u0010\u001e\u001a\u00020\u0011*\u00020\u001fH\u0002\u0082\u0001\u0002#$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "isRelatedToParameter", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "parameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "isNotFinalReadOnly", "primaryConstructorProperty", "isInapplicableParameterType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isGenericArrayOfTypeParameter", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isSubtypeOfCloneable", "isCloneableId", "Lorg/jetbrains/kotlin/name/ClassId;", "Regular", "ForExpectClass", "Companion", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirValueClassDeclarationChecker extends FirDeclarationChecker<FirRegularClass> {
    private static final Set<String> boxAndUnboxNames = SetsKt.setOf(new String[]{"box", "unbox"});
    private static final Set<String> equalsAndHashCodeNames = SetsKt.setOf(new String[]{"equals", "hashCode"});
    private static final FqName javaLangFqName = new FqName("java.lang");
    private static final FqName cloneableFqName = new FqName("Cloneable");

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirValueClassDeclarationChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirValueClassDeclarationChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firRegularClass.getClass();
            if (firRegularClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firRegularClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueClassDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirValueClassDeclarationChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirValueClassDeclarationChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firRegularClass.getClass();
            if (firRegularClass.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firRegularClass);
        }
    }

    public /* synthetic */ FirValueClassDeclarationChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    public static Unit b(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueClassDeclarationChecker firValueClassDeclarationChecker, Ref.ObjectRef objectRef, HashMap map, FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirRegularClassSymbol) {
            if (((FirClassLikeSymbol) firBasedSymbol).getRawStatus().isInner()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirRegularClassSymbol) firBasedSymbol).getSource(), FirErrors.INSTANCE.getINNER_CLASS_INSIDE_VALUE_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        } else if (firBasedSymbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firBasedSymbol;
            if (firValueClassDeclarationChecker.isRelatedToParameter(firPropertySymbol, (FirValueParameterSymbol) ((Map) objectRef.element).get(firPropertySymbol.getName()))) {
                map.put(firPropertySymbol.getName(), firBasedSymbol);
            } else if (firPropertySymbol.getDelegate() != null) {
                FirExpression delegate = firPropertySymbol.getDelegate();
                delegate.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) delegate.getSource(), FirErrors.INSTANCE.getDELEGATED_PROPERTY_INSIDE_VALUE_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (DeclarationAttributesKt.getHasBackingField(firPropertySymbol)) {
                KtSourceElement source = firPropertySymbol.getSource();
                if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertySymbol.getSource(), FirErrors.INSTANCE.getPROPERTY_WITH_BACKING_FIELD_INSIDE_VALUE_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit check$lambda$4$0(CheckerContext checkerContext, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (!(firBasedSymbol instanceof FirNamedFunctionSymbol)) {
            return Unit.INSTANCE;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firBasedSymbol;
        if (org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isEquals(firNamedFunctionSymbol, checkerContext.getSession())) {
            objectRef.element = firBasedSymbol;
        }
        if (ValueClassesUtilsKt.isTypedEqualsInValueClass(firNamedFunctionSymbol, checkerContext.getSession())) {
            objectRef2.element = firBasedSymbol;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit d(FirRegularClass firRegularClass, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, String str, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        FirClassLikeSymbol<?> containingClassSymbol;
        firNamedFunctionSymbol.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return null;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) symbol;
        if (firNamedFunctionSymbol2.getResolvedStatus().getModality() != Modality.ABSTRACT && (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firNamedFunctionSymbol2)) != null) {
            if (Intrinsics.areEqual(containingClassSymbol, firRegularClass.getSymbol())) {
                KtSourceElement source = firNamedFunctionSymbol2.getSource();
                if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getRESERVED_MEMBER_INSIDE_VALUE_CLASS(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            } else if (FirHelpersKt.getClassKind(containingClassSymbol) == ClassKind.INTERFACE) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getRESERVED_MEMBER_FROM_INTERFACE_INSIDE_VALUE_CLASS(), (Object) containingClassSymbol.getName().asString(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final boolean isCloneableId(ClassId classId) {
        if (Intrinsics.areEqual(classId.getRelativeClassName(), cloneableFqName)) {
            return Intrinsics.areEqual(classId.getPackageFqName(), StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE()) || Intrinsics.areEqual(classId.getPackageFqName(), javaLangFqName);
        }
        return false;
    }

    private final boolean isInapplicableParameterType(FirTypeRef firTypeRef, FirSession firSession) {
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firTypeRef), firSession, (Function1) null, 2, (Object) null);
        return ConeBuiltinTypeUtilsKt.isUnit(coneKotlinTypeFullyExpandedType$default) || ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeFullyExpandedType$default);
    }

    private final boolean isNotFinalReadOnly(FirValueParameterSymbol firValueParameterSymbol, FirPropertySymbol firPropertySymbol) {
        if (firPropertySymbol == null) {
            return true;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.OPEN_KEYWORD;
        ktModifierKeywordToken.getClass();
        return firValueParameterSymbol.isVararg() || !firPropertySymbol.isVal() || FirKeywordUtilsKt.hasModifier(firValueParameterSymbol, ktModifierKeywordToken);
    }

    private final boolean isRelatedToParameter(FirPropertySymbol firPropertySymbol, FirValueParameterSymbol firValueParameterSymbol) {
        if (!Intrinsics.areEqual(firPropertySymbol.getName(), firValueParameterSymbol != null ? firValueParameterSymbol.getName() : null)) {
            return false;
        }
        KtSourceElement source = firPropertySymbol.getSource();
        return (source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind;
    }

    private final boolean isSubtypeOfCloneable(FirRegularClass firRegularClass, FirSession firSession) {
        if (isCloneableId(FirDeclarationUtilKt.getClassId(firRegularClass))) {
            return true;
        }
        List listLookupSuperTypes$default = SupertypeUtilsKt.lookupSuperTypes$default(firRegularClass, true, true, firSession, false, null, 32, null);
        if ((listLookupSuperTypes$default instanceof Collection) && listLookupSuperTypes$default.isEmpty()) {
            return false;
        }
        Iterator it = listLookupSuperTypes$default.iterator();
        while (it.hasNext()) {
            if (isCloneableId(TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) it.next(), firSession, (Function1) null, 2, (Object) null).getLookupTag().getClassId())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirRegularClass firRegularClass) {
        CheckerContext checkerContext2;
        CheckerContext checkerContext3;
        FirExpression resolvedDefaultValue;
        KtSourceElement source;
        FirRegularClassSymbol regularClassSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isInlineOrValueClass(firRegularClass.getSymbol())) {
            if (firRegularClass.getStatus().isInner() || firRegularClass.getIsLocal()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_NOT_TOP_LEVEL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firRegularClass.getStatus().getModality() != Modality.FINAL) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_NOT_FINAL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                checkerContext2 = checkerContext;
            } else {
                checkerContext2 = checkerContext;
            }
            if (!firRegularClass.getContextParameters().isEmpty() && LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.ContextReceivers)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_CANNOT_HAVE_CONTEXT_RECEIVERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            for (FirTypeRef firTypeRef : firRegularClass.getSuperTypeRefs()) {
                if (!(firTypeRef instanceof FirImplicitAnyTypeRef) && !(firTypeRef instanceof FirErrorTypeRef) && ((regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(firTypeRef, checkerContext.getSession())) == null || regularClassSymbol.getClassKind() != ClassKind.INTERFACE)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_CANNOT_EXTEND_CLASSES(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            if (isSubtypeOfCloneable(firRegularClass, checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_CANNOT_BE_CLONEABLE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                checkerContext3 = checkerContext;
            } else {
                checkerContext3 = checkerContext;
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = MapsKt.emptyMap();
            final HashMap map = new HashMap();
            Set setEmptySet = SetsKt.emptySet();
            boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext3, LanguageFeature.CustomEqualsInValueClasses);
            KtSourceElementKind kind = null;
            Set set = setEmptySet;
            FirConstructorSymbol firConstructorSymbol = null;
            for (FirConstructorSymbol firConstructorSymbol2 : org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.constructors(firRegularClass, checkerContext3.getSession())) {
                if (firConstructorSymbol2.isPrimary()) {
                    List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbol2.getValueParameterSymbols();
                    LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(valueParameterSymbols, 10)), 16));
                    for (Object obj : valueParameterSymbols) {
                        linkedHashMap.put(((FirValueParameterSymbol) obj).getName(), obj);
                    }
                    objectRef.element = linkedHashMap;
                    firConstructorSymbol = firConstructorSymbol2;
                    set = CollectionsKt.toSet(linkedHashMap.values());
                } else if (firConstructorSymbol2.getHasBody() && !checkerContext3.get$languageVersionSettings().supportsFeature(LanguageFeature.ValueClassesSecondaryConstructorWithBody)) {
                    KtSourceElement bodySource = firConstructorSymbol2.getBodySource();
                    bodySource.getClass();
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext3, diagnosticReporter, (AbstractKtSourceElement) bodySource, FirErrors.INSTANCE.getSECONDARY_CONSTRUCTOR_WITH_BODY_INSIDE_VALUE_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                checkerContext3 = checkerContext;
            }
            org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclarations$default(firRegularClass, checkerContext.getSession(), (FirResolvePhase) null, new Function1() { // from class: lg5
                public final Object invoke(Object obj2) {
                    return FirValueClassDeclarationChecker.b(checkerContext, diagnosticReporter, this, objectRef, map, (FirBasedSymbol) obj2);
                }
            }, 2, (Object) null);
            for (FirDeclaration firDeclaration : firRegularClass.getDeclarations()) {
                if ((firDeclaration instanceof FirField) && (firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                    FirField firField = (FirField) firDeclaration;
                    FirExpression initializer = firField.getInitializer();
                    FirCallableSymbol<?> resolvedCallableSymbol = initializer != null ? ReferenceUtilsKt.toResolvedCallableSymbol(initializer, checkerContext.getSession()) : null;
                    if (resolvedCallableSymbol == null || !CollectionsKt.contains(set, resolvedCallableSymbol)) {
                        FirResolvedTypeRef returnTypeRef = firField.getReturnTypeRef();
                        returnTypeRef.getClass();
                        FirTypeRef delegatedTypeRef = returnTypeRef.getDelegatedTypeRef();
                        KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, delegatedTypeRef != null ? delegatedTypeRef.getSource() : null, FirErrors.INSTANCE.getVALUE_CLASS_CANNOT_IMPLEMENT_INTERFACE_BY_DELEGATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
            Set<String> setPlus = SetsKt.plus(boxAndUnboxNames, zIsEnabled ? SetsKt.emptySet() : equalsAndHashCodeNames);
            FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClass);
            for (final String str : setPlus) {
                Name nameIdentifier = Name.identifier(str);
                nameIdentifier.getClass();
                firTypeScopeUnsubstitutedScope.processFunctionsByName(nameIdentifier, new Function1() { // from class: mg5
                    public final Object invoke(Object obj2) {
                        return FirValueClassDeclarationChecker.d(firRegularClass, checkerContext, diagnosticReporter, str, (FirNamedFunctionSymbol) obj2);
                    }
                });
            }
            if (firConstructorSymbol != null && (source = firConstructorSymbol.getSource()) != null) {
                kind = source.getKind();
            }
            if (!(kind instanceof KtRealSourceElementKind)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getABSENCE_OF_PRIMARY_CONSTRUCTOR_FOR_VALUE_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            boolean zIsEnabled2 = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.JvmInlineMultiFieldValueClasses);
            Object obj2 = objectRef.element;
            if (zIsEnabled2) {
                if (((Map) obj2).isEmpty()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firConstructorSymbol.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_EMPTY_CONSTRUCTOR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            } else if (((Map) obj2).size() != 1) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firConstructorSymbol.getSource(), FirErrors.INSTANCE.getINLINE_CLASS_CONSTRUCTOR_WRONG_PARAMETERS_SIZE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            for (Map.Entry entry : ((Map) objectRef.element).entrySet()) {
                Name name = (Name) entry.getKey();
                FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) entry.getValue();
                FirResolvedTypeRef resolvedReturnTypeRef = firValueParameterSymbol.getResolvedReturnTypeRef();
                if (isNotFinalReadOnly(firValueParameterSymbol, (FirPropertySymbol) map.get(name))) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_CONSTRUCTOR_NOT_FINAL_READ_ONLY_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (isInapplicableParameterType(resolvedReturnTypeRef, checkerContext.getSession())) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getVALUE_CLASS_HAS_INAPPLICABLE_PARAMETER_TYPE(), (Object) resolvedReturnTypeRef.getConeType(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else if (FirHelpersKt.isRecursiveValueClassType(resolvedReturnTypeRef.getConeType(), checkerContext.getSession())) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef.getSource(), FirErrors.INSTANCE.getVALUE_CLASS_CANNOT_BE_RECURSIVE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (FirValueClassRepresentationKt.getMultiFieldValueClassRepresentation(firRegularClass) != null && (resolvedDefaultValue = firValueParameterSymbol.getResolvedDefaultValue()) != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedDefaultValue.getSource(), FirErrors.INSTANCE.getMULTI_FIELD_VALUE_CLASS_PRIMARY_CONSTRUCTOR_DEFAULT_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            if (zIsEnabled) {
                final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclarations$default(firRegularClass, checkerContext.getSession(), (FirResolvePhase) null, new Function1() { // from class: ng5
                    public final Object invoke(Object obj3) {
                        return FirValueClassDeclarationChecker.check$lambda$4$0(checkerContext, objectRef2, objectRef3, (FirBasedSymbol) obj3);
                    }
                }, 2, (Object) null);
                Pair pair = TuplesKt.to(objectRef2.element, objectRef3.element);
                FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) pair.component1();
                FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) pair.component2();
                if (firNamedFunctionSymbol2 != null) {
                    if (!firNamedFunctionSymbol2.getTypeParameterSymbols().isEmpty()) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol2.getSource(), FirErrors.INSTANCE.getTYPE_PARAMETERS_NOT_ALLOWED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                    FirResolvedTypeRef resolvedReturnTypeRef2 = ((FirValueParameterSymbol) CollectionsKt.single(firNamedFunctionSymbol2.getValueParameterSymbols())).getResolvedReturnTypeRef();
                    for (ConeTypeProjection coneTypeProjection : resolvedReturnTypeRef2.getConeType().getTypeArguments()) {
                        if (!ConeTypeProjectionKt.isStarProjection(coneTypeProjection)) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) resolvedReturnTypeRef2.getSource(), FirErrors.INSTANCE.getTYPE_ARGUMENT_ON_TYPED_VALUE_CLASS_EQUALS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            break;
                        }
                    }
                }
                if (firNamedFunctionSymbol == null || firNamedFunctionSymbol2 != null) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINEFFICIENT_EQUALS_OVERRIDING_IN_VALUE_CLASS(), (Object) ConeTypeUtilsKt.replaceArgumentsWithStarProjections(ScopeUtilsKt.defaultType(firRegularClass)), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private FirValueClassDeclarationChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
