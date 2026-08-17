package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.ModifiersCompatibilityUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.resolve.ModifierCheckerHelpersKt;
import org.jetbrains.kotlin.resolve.TargetAllowedPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012JQ\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001dH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001eJY\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0011\u001a\u00020\u00022\f\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001dH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u00020#*\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0016\u0010$\u001a\u00020\u00142\f\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001dH\u0002¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirModifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkModifiers", "list", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", "owner", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkTarget", Argument.Delimiters.none, "modifierSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "modifierToken", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "actualTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "parent", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;Ljava/util/List;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "checkParent", "actualParents", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "firstOrThis", Argument.Delimiters.none, "isFinalExpectClass", "d", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirModifierChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirModifierChecker INSTANCE = new FirModifierChecker();

    private FirModifierChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkModifiers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirModifierList firModifierList, FirDeclaration firDeclaration) {
        FirBasedSymbol<?> firBasedSymbol;
        List<KotlinTarget> enum_entry_list;
        List<? extends KotlinTarget> list;
        FirBasedSymbol<?> firBasedSymbol2;
        List<KotlinTarget> list2;
        if (firModifierList.getModifiers().isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        CheckerContext checkerContext2 = checkerContext;
        FirDeclaration firDeclaration2 = firDeclaration;
        List<? extends KotlinTarget> defaultTargets = FirHelpersKt.getActualTargetList(checkerContext2, firDeclaration2).getDefaultTargets();
        Iterator it = CollectionsKt.asReversed(checkerContext2.getContainingDeclarations()).iterator();
        while (true) {
            firBasedSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirBasedSymbol<?> firBasedSymbol3 = (FirBasedSymbol) it.next();
            if (firBasedSymbol3 == null) {
                firBasedSymbol3 = null;
            }
            if (firBasedSymbol3 != null) {
                if ((!(firBasedSymbol3 instanceof FirConstructorSymbol) || !((FirConstructorSymbol) firBasedSymbol3).isPrimary()) && !(firBasedSymbol3 instanceof FirPropertySymbol)) {
                    KtSourceElement source = firBasedSymbol3.getSource();
                    if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind)) {
                        firBasedSymbol = firBasedSymbol3;
                    }
                }
                if (firBasedSymbol != null) {
                    break;
                }
            }
        }
        FirBasedSymbol<?> firBasedSymbol4 = firBasedSymbol;
        if (firBasedSymbol4 instanceof FirAnonymousObjectSymbol) {
            enum_entry_list = KotlinTarget.INSTANCE.getLOCAL_CLASS_LIST();
        } else if (firBasedSymbol4 instanceof FirClassSymbol) {
            FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol4;
            enum_entry_list = KotlinTarget.INSTANCE.classActualTargets(((FirClassSymbol) firBasedSymbol4).getClassKind(), firClassLikeSymbol.getRawStatus().isInner(), firClassLikeSymbol.getRawStatus().isCompanion(), Intrinsics.areEqual(firClassLikeSymbol.getRawStatus().getVisibility(), Visibilities.Local.INSTANCE) && !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firBasedSymbol4), Boolean.TRUE));
        } else if (firBasedSymbol4 instanceof FirPropertyAccessorSymbol) {
            enum_entry_list = ((FirPropertyAccessorSymbol) firBasedSymbol4).isSetter() ? KotlinTarget.INSTANCE.getPROPERTY_SETTER_LIST() : KotlinTarget.INSTANCE.getPROPERTY_GETTER_LIST();
        } else if (firBasedSymbol4 instanceof FirFunctionSymbol) {
            enum_entry_list = KotlinTarget.INSTANCE.getFUNCTION_LIST();
        } else {
            enum_entry_list = firBasedSymbol4 instanceof FirEnumEntrySymbol ? KotlinTarget.INSTANCE.getENUM_ENTRY_LIST() : KotlinTarget.INSTANCE.getFILE_LIST();
        }
        List<FirModifier<?>> modifiers = firModifierList.getModifiers();
        Iterator<T> it2 = modifiers.iterator();
        int i = 0;
        while (it2.hasNext()) {
            int i2 = i + 1;
            FirModifier firModifier = (FirModifier) it2.next();
            int i3 = 0;
            while (i3 < i) {
                ModifiersCompatibilityUtilsKt.checkCompatibilityType(diagnosticReporter, checkerContext2, modifiers.get(i3), firModifier, hashSet, firDeclaration2);
                i3++;
                checkerContext2 = checkerContext;
                firDeclaration2 = firDeclaration;
            }
            if (hashSet.contains(firModifier)) {
                list = defaultTargets;
                firBasedSymbol2 = firBasedSymbol4;
            } else {
                KtSourceElement source2 = firModifier.getSource();
                KtModifierKeywordToken token = firModifier.getToken();
                firBasedSymbol2 = firBasedSymbol4;
                list = defaultTargets;
                if (checkTarget(checkerContext, diagnosticReporter, source2, token, defaultTargets, firBasedSymbol2)) {
                    list2 = enum_entry_list;
                    if (checkParent(checkerContext, diagnosticReporter, source2, token, list2, firDeclaration, firBasedSymbol2)) {
                        firBasedSymbol2 = firBasedSymbol2;
                    } else {
                        firBasedSymbol2 = firBasedSymbol2;
                        hashSet.add(firModifier);
                    }
                } else {
                    hashSet.add(firModifier);
                }
                checkerContext2 = checkerContext;
                enum_entry_list = list2;
                firBasedSymbol4 = firBasedSymbol2;
                i = i2;
                firDeclaration2 = firDeclaration;
                defaultTargets = list;
            }
            list2 = enum_entry_list;
            checkerContext2 = checkerContext;
            enum_entry_list = list2;
            firBasedSymbol4 = firBasedSymbol2;
            i = i2;
            firDeclaration2 = firDeclaration;
            defaultTargets = list;
        }
    }

    private final boolean checkParent(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, KtModifierKeywordToken ktModifierKeywordToken, List<? extends KotlinTarget> list, FirDeclaration firDeclaration, FirBasedSymbol<?> firBasedSymbol) {
        KtModifierKeywordToken ktModifierKeywordToken2 = ktModifierKeywordToken;
        if (Intrinsics.areEqual(ktModifierKeywordToken2, KtTokens.COMPANION_KEYWORD) && (firDeclaration instanceof FirRegularClass) && list.contains(KotlinTarget.FILE)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_MODIFIER_CONTAINING_DECLARATION(), (Object) ktModifierKeywordToken2, (Object) firstOrThis(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return true;
        }
        Set set = (Set) ModifierCheckerHelpersKt.getDeprecatedParentTargetMap().get(ktModifierKeywordToken2);
        if (set != null) {
            List<? extends KotlinTarget> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    if (set.contains((KotlinTarget) it.next())) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDEPRECATED_MODIFIER_CONTAINING_DECLARATION(), (Object) ktModifierKeywordToken2, (Object) firstOrThis(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        return true;
                    }
                }
            }
        }
        if (Intrinsics.areEqual(ktModifierKeywordToken2, KtTokens.PROTECTED_KEYWORD) && isFinalExpectClass(firBasedSymbol)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_MODIFIER_CONTAINING_DECLARATION(), (Object) ktModifierKeywordToken2, (Object) "final expect class", (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            ktModifierKeywordToken2 = ktModifierKeywordToken2;
        }
        TargetAllowedPredicate targetAllowedPredicate = (TargetAllowedPredicate) ModifierCheckerHelpersKt.getPossibleParentTargetPredicateMap().get(ktModifierKeywordToken2);
        if (targetAllowedPredicate == null) {
            return true;
        }
        List<? extends KotlinTarget> list3 = list;
        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
            Iterator<T> it2 = list3.iterator();
            while (it2.hasNext()) {
                if (targetAllowedPredicate.isAllowed((KotlinTarget) it2.next(), FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()))) {
                    return true;
                }
            }
        }
        if (Intrinsics.areEqual(ktModifierKeywordToken2, KtTokens.INNER_KEYWORD) && (firBasedSymbol instanceof FirScriptSymbol)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getINNER_ON_TOP_LEVEL_SCRIPT_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return false;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_MODIFIER_CONTAINING_DECLARATION(), (Object) ktModifierKeywordToken2, (Object) firstOrThis(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        return false;
    }

    private final boolean checkTarget(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, KtModifierKeywordToken ktModifierKeywordToken, List<? extends KotlinTarget> list, FirBasedSymbol<?> firBasedSymbol) {
        FirErrors firErrors = FirErrors.INSTANCE;
        if (!checkTarget$checkModifier(list, checkerContext, diagnosticReporter, ktSourceElement, ktModifierKeywordToken, firErrors.getWRONG_MODIFIER_TARGET())) {
            return false;
        }
        if ((firBasedSymbol instanceof FirRegularClassSymbol) && Intrinsics.areEqual(ktModifierKeywordToken, KtTokens.EXPECT_KEYWORD)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) firErrors.getWRONG_MODIFIER_TARGET(), (Object) ktModifierKeywordToken, (Object) "nested class", (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return false;
        }
        if (!checkTarget$checkModifier(list, checkerContext, diagnosticReporter, ktSourceElement, ktModifierKeywordToken, firErrors.getDEPRECATED_MODIFIER_FOR_TARGET())) {
            return true;
        }
        checkTarget$checkModifier(list, checkerContext, diagnosticReporter, ktSourceElement, ktModifierKeywordToken, firErrors.getREDUNDANT_MODIFIER_FOR_TARGET());
        return true;
    }

    private static final boolean checkTarget$checkModifier(List<? extends KotlinTarget> list, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, KtModifierKeywordToken ktModifierKeywordToken, KtDiagnosticFactory2<KtModifierKeywordToken, String> ktDiagnosticFactory2) {
        FirErrors firErrors = FirErrors.INSTANCE;
        if (Intrinsics.areEqual(ktDiagnosticFactory2, firErrors.getWRONG_MODIFIER_TARGET())) {
            List<? extends KotlinTarget> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                for (KotlinTarget kotlinTarget : list2) {
                    TargetAllowedPredicate targetAllowedPredicate = (TargetAllowedPredicate) ModifierCheckerHelpersKt.getPossibleTargetPredicateMap().get(ktModifierKeywordToken);
                    if (targetAllowedPredicate != null && targetAllowedPredicate.isAllowed(kotlinTarget, FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()))) {
                        return true;
                    }
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) ktDiagnosticFactory2, (Object) ktModifierKeywordToken, (Object) INSTANCE.firstOrThis(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return false;
        }
        if (Intrinsics.areEqual(ktDiagnosticFactory2, firErrors.getDEPRECATED_MODIFIER_FOR_TARGET())) {
            List<? extends KotlinTarget> list3 = list;
            if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                for (KotlinTarget kotlinTarget2 : list3) {
                    TargetAllowedPredicate targetAllowedPredicate2 = (TargetAllowedPredicate) ModifierCheckerHelpersKt.getDeprecatedTargetPredicateMap().get(ktModifierKeywordToken);
                    if (targetAllowedPredicate2 != null && targetAllowedPredicate2.isAllowed(kotlinTarget2, FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()))) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) ktDiagnosticFactory2, (Object) ktModifierKeywordToken, (Object) INSTANCE.firstOrThis(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        return false;
                    }
                }
            }
            return true;
        }
        List<? extends KotlinTarget> list4 = list;
        if (!(list4 instanceof Collection) || !list4.isEmpty()) {
            for (KotlinTarget kotlinTarget3 : list4) {
                Set setEmptySet = (Set) ModifierCheckerHelpersKt.getRedundantTargetMap().get(ktModifierKeywordToken);
                if (setEmptySet == null) {
                    setEmptySet = SetsKt.emptySet();
                }
                if (setEmptySet.contains(kotlinTarget3)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) ktDiagnosticFactory2, (Object) ktModifierKeywordToken, (Object) INSTANCE.firstOrThis(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    return false;
                }
            }
        }
        return true;
    }

    private final String firstOrThis(List<? extends KotlinTarget> list) {
        String description;
        KotlinTarget kotlinTarget = (KotlinTarget) CollectionsKt.firstOrNull(list);
        return (kotlinTarget == null || (description = kotlinTarget.getDescription()) == null) ? "this" : description;
    }

    private final boolean isFinalExpectClass(FirBasedSymbol<?> d) {
        if (!(d instanceof FirClassSymbol)) {
            return false;
        }
        FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) d;
        return firClassLikeSymbol.getResolvedStatus().getModality() == Modality.FINAL && firClassLikeSymbol.getRawStatus().isExpect();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirModifierList modifierList;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if ((firDeclaration instanceof FirValueParameter) && ((FirValueParameter) firDeclaration).getValueParameterKind() == FirValueParameterKind.ContextParameter) {
            return;
        }
        KtSourceElement source = firDeclaration instanceof FirFile ? ((FirFile) firDeclaration).getPackageDirective().getSource() : firDeclaration.getSource();
        if (source == null || (source.getKind() instanceof KtFakeSourceElementKind) || (modifierList = FirKeywordUtilsKt.getModifierList(source)) == null) {
            return;
        }
        INSTANCE.checkModifiers(checkerContext, diagnosticReporter, modifierList, firDeclaration);
    }
}
