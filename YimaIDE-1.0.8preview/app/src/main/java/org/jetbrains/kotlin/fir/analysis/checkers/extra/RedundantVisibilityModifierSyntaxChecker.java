package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import com.intellij.lang.LighterASTNode;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.ExplicitApiMode;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.FirConflictsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.extra.RedundantVisibilityModifierSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirDeclarationSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.psi.KtDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J5\u0010\u0014\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0017JC\u0010\u0014\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0019H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001aJC\u0010\u001b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0019H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001aJ-\u0010!\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u001e*\u00020\u00122\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0002J5\u0010(\u001a\u00020\u001e*\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u00162\f\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0019H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010*J%\u0010\u001c\u001a\u00020\u0016*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010+J,\u0010,\u001a\u00020\u00162\"\u0010-\u001a\u001e\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030/\u0012\u0004\u0012\u0002000.\u0012\u0004\u0012\u00020\u00070.H\u0002J!\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u000203H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00104J!\u00105\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00106J!\u00107\u001a\u00020\u00162\u0006\u00108\u001a\u000209H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010:R\u0018\u0010\u001d\u001a\u00020\u001e*\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010 R\u0018\u0010%\u001a\u00020\u001e*\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/RedundantVisibilityModifierSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "<init>", "()V", "checkPsiOrLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkPropertyAndReport", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkElementAndReport", "defaultVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/descriptors/Visibility;)V", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "checkElementWithImplicitVisibilityAndReport", "implicitVisibility", "isPropertyFromParameter", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/FirElement;)Z", "reportElement", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "canMakeSetterMoreAccessible", "setterImplicitVisibility", "hasSetterWithImplicitVisibility", "getHasSetterWithImplicitVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Z", "isEffectivelyHiddenBy", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/descriptors/Visibility;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "findBiggestVisibility", "processSymbols", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "findPropertyAccessorVisibility", "accessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "findPropertyVisibility", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "findFunctionVisibility", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantVisibilityModifierSyntaxChecker extends FirDeclarationSyntaxChecker<FirDeclaration, KtDeclaration> {
    public static final RedundantVisibilityModifierSyntaxChecker INSTANCE = new RedundantVisibilityModifierSyntaxChecker();

    private RedundantVisibilityModifierSyntaxChecker() {
    }

    public static Unit c(CheckerContext checkerContext, FirProperty firProperty, Function1 function1) {
        function1.getClass();
        FirHelpersKt.processOverriddenPropertiesWithActionSafe(checkerContext, firProperty.getSymbol(), function1);
        return Unit.INSTANCE;
    }

    private final boolean canMakeSetterMoreAccessible(FirProperty firProperty, Visibility visibility) {
        if (firProperty.getStatus().isOverride() && getHasSetterWithImplicitVisibility(firProperty) && visibility != null) {
            return !Intrinsics.areEqual(visibility, firProperty.getStatus().getVisibility());
        }
        return false;
    }

    private final void checkElementAndReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, Visibility visibility) {
        FirBasedSymbol<?> firBasedSymbol;
        Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
        do {
            firBasedSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirBasedSymbol<?> firBasedSymbol2 = (FirBasedSymbol) it.next();
            if (firBasedSymbol2 != null) {
                firBasedSymbol = firBasedSymbol2;
            }
        } while (firBasedSymbol == null);
        checkElementAndReport(checkerContext, diagnosticReporter, firDeclaration, visibility, firBasedSymbol);
    }

    private final void checkElementWithImplicitVisibilityAndReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, Visibility visibility, FirBasedSymbol<?> firBasedSymbol) {
        KtSourceElement source = firDeclaration.getSource();
        if ((!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) || isPropertyFromParameter(firDeclaration)) && (firDeclaration instanceof FirMemberDeclaration)) {
            KtSourceElement source2 = ((FirMemberDeclaration) firDeclaration).getSource();
            Visibility explicitVisibility = source2 != null ? RedundantVisibilityModifierSyntaxCheckerKt.getExplicitVisibility(source2) : null;
            if (isEffectivelyHiddenBy(checkerContext, explicitVisibility, visibility, firBasedSymbol)) {
                reportElement(checkerContext, diagnosticReporter, firDeclaration);
            } else if ((((ExplicitApiMode) checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getExplicitApiMode())) == ExplicitApiMode.DISABLED || !Intrinsics.areEqual(explicitVisibility, Visibilities.Public.INSTANCE)) && Intrinsics.areEqual(explicitVisibility, visibility)) {
                reportElement(checkerContext, diagnosticReporter, firDeclaration);
            }
        }
    }

    private final void checkPropertyAndReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        Visibility visibilityImplicitVisibility;
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter != null) {
            Visibility defaultVisibility = setter.getSymbol().getResolvedStatus().getDefaultVisibility();
            RedundantVisibilityModifierSyntaxChecker redundantVisibilityModifierSyntaxChecker = INSTANCE;
            visibilityImplicitVisibility = redundantVisibilityModifierSyntaxChecker.implicitVisibility(checkerContext, setter, defaultVisibility);
            redundantVisibilityModifierSyntaxChecker.checkElementAndReport(checkerContext, diagnosticReporter, setter, visibilityImplicitVisibility, firProperty.getSymbol());
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
        } else {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            visibilityImplicitVisibility = null;
        }
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter != null) {
            INSTANCE.checkElementAndReport(checkerContext2, diagnosticReporter2, getter, getter.getSymbol().getResolvedStatus().getDefaultVisibility(), firProperty.getSymbol());
        }
        FirBackingField backingField = firProperty.getBackingField();
        if (backingField != null) {
            INSTANCE.checkElementAndReport(checkerContext2, diagnosticReporter2, backingField, backingField.getSymbol().getResolvedStatus().getDefaultVisibility(), firProperty.getSymbol());
        }
        if (canMakeSetterMoreAccessible(firProperty, visibilityImplicitVisibility)) {
            return;
        }
        checkElementAndReport(checkerContext2, diagnosticReporter2, firProperty, firProperty.getSymbol().getResolvedStatus().getDefaultVisibility());
    }

    public static Unit d(CheckerContext checkerContext, FirPropertySymbol firPropertySymbol, final Function1 function1) {
        function1.getClass();
        FirHelpersKt.processOverriddenPropertiesWithActionSafe(checkerContext, firPropertySymbol, new Function1() { // from class: r8c
            public final Object invoke(Object obj) {
                return RedundantVisibilityModifierSyntaxChecker.findPropertyAccessorVisibility$lambda$0$0(function1, (FirPropertySymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit e(CheckerContext checkerContext, FirNamedFunction firNamedFunction, Function1 function1) {
        function1.getClass();
        FirHelpersKt.processOverriddenFunctionsWithActionSafe(checkerContext, firNamedFunction.getSymbol(), function1);
        return Unit.INSTANCE;
    }

    public static ProcessorAction f(Ref.ObjectRef objectRef, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        Integer numCompare = Visibilities.INSTANCE.compare((Visibility) objectRef.element, firCallableSymbol.getResolvedStatus().getVisibility());
        if (numCompare != null && numCompare.intValue() < 0) {
            objectRef.element = firCallableSymbol.getResolvedStatus().getVisibility();
        }
        return ProcessorAction.NEXT;
    }

    private final Visibility findBiggestVisibility(Function1<? super Function1<? super FirCallableSymbol<?>, ? extends ProcessorAction>, Unit> processSymbols) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = Visibilities.Private.INSTANCE;
        processSymbols.invoke(new Function1() { // from class: u8c
            public final Object invoke(Object obj) {
                return RedundantVisibilityModifierSyntaxChecker.f(objectRef, (FirCallableSymbol) obj);
            }
        });
        return (Visibility) objectRef.element;
    }

    private final Visibility findFunctionVisibility(final CheckerContext checkerContext, final FirNamedFunction firNamedFunction) {
        return findBiggestVisibility(new Function1() { // from class: s8c
            public final Object invoke(Object obj) {
                return RedundantVisibilityModifierSyntaxChecker.e(checkerContext, firNamedFunction, (Function1) obj);
            }
        });
    }

    private final Visibility findPropertyAccessorVisibility(final CheckerContext checkerContext, FirPropertyAccessor firPropertyAccessor) {
        final FirPropertySymbol propertySymbol = firPropertyAccessor.getPropertySymbol();
        return findBiggestVisibility(new Function1() { // from class: q8c
            public final Object invoke(Object obj) {
                return RedundantVisibilityModifierSyntaxChecker.d(checkerContext, propertySymbol, (Function1) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ProcessorAction findPropertyAccessorVisibility$lambda$0$0(Function1 function1, FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        FirPropertyAccessorSymbol setterSymbol = firPropertySymbol.getSetterSymbol();
        if (setterSymbol != null) {
            firPropertySymbol = setterSymbol;
        }
        return (ProcessorAction) function1.invoke(firPropertySymbol);
    }

    private final Visibility findPropertyVisibility(final CheckerContext checkerContext, final FirProperty firProperty) {
        return findBiggestVisibility(new Function1() { // from class: t8c
            public final Object invoke(Object obj) {
                return RedundantVisibilityModifierSyntaxChecker.c(checkerContext, firProperty, (Function1) obj);
            }
        });
    }

    private final boolean getHasSetterWithImplicitVisibility(FirProperty firProperty) {
        KtSourceElement source;
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter == null) {
            return false;
        }
        KtSourceElement source2 = firProperty.getSource();
        LighterASTNode lighterASTNode = source2 != null ? source2.getLighterASTNode() : null;
        KtSourceElement source3 = setter.getSource();
        return Intrinsics.areEqual(lighterASTNode, source3 != null ? source3.getLighterASTNode() : null) || (source = setter.getSource()) == null || RedundantVisibilityModifierSyntaxCheckerKt.getExplicitVisibility(source) == null;
    }

    private final Visibility implicitVisibility(CheckerContext checkerContext, FirDeclaration firDeclaration, Visibility visibility) {
        boolean z = firDeclaration instanceof FirPropertyAccessor;
        if (z) {
            FirPropertyAccessor firPropertyAccessor = (FirPropertyAccessor) firDeclaration;
            if (firPropertyAccessor.isSetter() && (CollectionsKt.last(checkerContext.getContainingDeclarations()) instanceof FirClassSymbol) && firPropertyAccessor.getPropertySymbol().getResolvedStatus().isOverride()) {
                return findPropertyAccessorVisibility(checkerContext, firPropertyAccessor);
            }
        }
        if (z) {
            return ((FirPropertyAccessor) firDeclaration).getPropertySymbol().getResolvedStatus().getVisibility();
        }
        if (firDeclaration instanceof FirConstructor) {
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration);
            if (containingClassSymbol instanceof FirRegularClassSymbol) {
                if (containingClassSymbol.getResolvedStatus().getModality() == Modality.SEALED) {
                    return Visibilities.Protected.INSTANCE;
                }
                return ((FirClassSymbol) containingClassSymbol).getClassKind() == ClassKind.ENUM_CLASS ? Visibilities.Private.INSTANCE : visibility;
            }
        } else {
            if ((firDeclaration instanceof FirNamedFunction) && (CollectionsKt.last(checkerContext.getContainingDeclarations()) instanceof FirClassSymbol) && ((FirMemberDeclaration) firDeclaration).getStatus().isOverride()) {
                return findFunctionVisibility(checkerContext, (FirNamedFunction) firDeclaration);
            }
            if ((firDeclaration instanceof FirProperty) && (CollectionsKt.last(checkerContext.getContainingDeclarations()) instanceof FirClassSymbol) && ((FirMemberDeclaration) firDeclaration).getStatus().isOverride()) {
                return findPropertyVisibility(checkerContext, (FirProperty) firDeclaration);
            }
        }
        return visibility;
    }

    private final boolean isEffectivelyHiddenBy(CheckerContext checkerContext, Visibility visibility, Visibility visibility2, FirBasedSymbol<?> firBasedSymbol) {
        EffectiveVisibility effectiveVisibility;
        Visibility visibility3;
        if (visibility != null) {
            Visibilities.Protected r0 = Visibilities.Protected.INSTANCE;
            if (!Intrinsics.areEqual(visibility, r0)) {
                if (firBasedSymbol instanceof FirCallableSymbol) {
                    effectiveVisibility = ((FirCallableSymbol) firBasedSymbol).getResolvedStatus().getEffectiveVisibility();
                } else if (firBasedSymbol instanceof FirClassLikeSymbol) {
                    effectiveVisibility = ((FirClassLikeSymbol) firBasedSymbol).getResolvedStatus().getEffectiveVisibility();
                }
                Visibility visibility4 = effectiveVisibility.toVisibility();
                if (Intrinsics.areEqual(visibility4, Visibilities.Local.INSTANCE) && Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
                    return true;
                }
                if (firBasedSymbol instanceof FirClassLikeSymbol) {
                    ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(checkerContext.getSession());
                    FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
                    visibility3 = visibility;
                    EffectiveVisibility.Permissiveness permissivenessRelation = EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility3, firClassLikeSymbol, false, false, 6, (Object) null).lowerBound(effectiveVisibility, typeContext).relation(EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility2, firClassLikeSymbol, false, false, 6, (Object) null).lowerBound(effectiveVisibility, typeContext), typeContext);
                    if (permissivenessRelation == EffectiveVisibility.Permissiveness.MORE || permissivenessRelation == EffectiveVisibility.Permissiveness.UNKNOWN || (effectiveVisibility.getPrivateApi() && Intrinsics.areEqual(visibility3, Visibilities.Public.INSTANCE) && Intrinsics.areEqual(visibility2.normalize(), r0))) {
                        return false;
                    }
                } else {
                    visibility3 = visibility;
                }
                Integer numCompareTo = visibility3.compareTo(visibility4);
                if (numCompareTo != null && numCompareTo.intValue() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isPropertyFromParameter(FirElement firElement) {
        if (!(firElement instanceof FirProperty)) {
            return false;
        }
        KtSourceElement source = ((FirProperty) firElement).getSource();
        return Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE);
    }

    private final void reportElement(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), FirErrors.INSTANCE.getREDUNDANT_VISIBILITY_MODIFIER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        Visibility default_visibility;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        ktSourceElement.getClass();
        if ((firDeclaration instanceof FirPropertyAccessor) || (firDeclaration instanceof FirValueParameter)) {
            return;
        }
        if (firDeclaration instanceof FirConstructor) {
            KtSourceElement source = ((FirConstructor) firDeclaration).getSource();
            if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
                return;
            }
        }
        if (firDeclaration instanceof FirProperty) {
            checkPropertyAndReport(checkerContext, diagnosticReporter, (FirProperty) firDeclaration);
            return;
        }
        FirResolvedDeclarationStatus resolvedStatus = FirConflictsHelpersKt.getResolvedStatus(firDeclaration.getSymbol());
        if (resolvedStatus == null || (default_visibility = resolvedStatus.getDefaultVisibility()) == null) {
            default_visibility = Visibilities.INSTANCE.getDEFAULT_VISIBILITY();
        }
        checkElementAndReport(checkerContext, diagnosticReporter, firDeclaration, default_visibility);
    }

    private final void checkElementAndReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, Visibility visibility, FirBasedSymbol<?> firBasedSymbol) {
        checkElementWithImplicitVisibilityAndReport(checkerContext, diagnosticReporter, firDeclaration, implicitVisibility(checkerContext, firDeclaration, visibility), firBasedSymbol);
    }
}
