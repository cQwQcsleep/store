package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirPropertyHidesJavaFieldChecker;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirPropertyHidesJavaFieldChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyHidesJavaFieldChecker extends FirDeclarationChecker<FirClass> {
    public static final FirPropertyHidesJavaFieldChecker INSTANCE = new FirPropertyHidesJavaFieldChecker();

    private FirPropertyHidesJavaFieldChecker() {
        super(MppCheckerKind.Platform);
    }

    public static Unit c(FirClass firClass, final CheckerContext checkerContext, FirTypeScope firTypeScope, final DiagnosticReporter diagnosticReporter, final FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if ((firVariableSymbol instanceof FirPropertySymbol) && Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol(firVariableSymbol), firClass.getSymbol())) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firVariableSymbol;
            if (Intrinsics.areEqual(firPropertySymbol.getOrigin(), FirDeclarationOrigin.Source.INSTANCE) && firPropertySymbol.getReceiverParameterSymbol() == null && !FirCallableSymbolKt.getHasContextParameters(firVariableSymbol) && !DeprecationUtilsKt.isDeprecationLevelHidden(firVariableSymbol, checkerContext.getSession())) {
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                firTypeScope.processPropertiesByName(firPropertySymbol.getName(), new Function1() { // from class: ac5
                    public final Object invoke(Object obj) {
                        return FirPropertyHidesJavaFieldChecker.check$lambda$0$0(booleanRef, firVariableSymbol, checkerContext, diagnosticReporter, (FirVariableSymbol) obj);
                    }
                });
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:22:0x003d  */
    /* JADX WARN: Code duplicated, block: B:24:0x004d  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit check$lambda$0$0(Ref.BooleanRef booleanRef, FirVariableSymbol firVariableSymbol, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableSymbol firVariableSymbol2) throws KotlinIllegalArgumentExceptionWithAttachments {
        Visibility visibility;
        Visibilities.Private r1;
        Visibility visibility2;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        firVariableSymbol2.getClass();
        if (!booleanRef.element && (firVariableSymbol2 instanceof FirFieldSymbol)) {
            FirDeclarationOrigin origin = firVariableSymbol2.getOrigin();
            if ((origin instanceof FirDeclarationOrigin.Java) || Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                visibility = firVariableSymbol2.getResolvedStatus().getVisibility();
                r1 = Visibilities.Private.INSTANCE;
                if (!Intrinsics.areEqual(visibility, r1)) {
                    visibility2 = firVariableSymbol.getResolvedStatus().getVisibility();
                    if ((Intrinsics.areEqual(visibility2, r1) && !Intrinsics.areEqual(visibility2, Visibilities.PrivateToThis.INSTANCE)) || FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession()).isVisibleForOverriding((FirCallableDeclaration) ((FirPropertySymbol) firVariableSymbol).getFir(), (FirCallableDeclaration) ((FirFieldSymbol) firVariableSymbol2).getFir())) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirPropertySymbol) firVariableSymbol).getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getPROPERTY_HIDES_JAVA_FIELD(), (Object) firVariableSymbol2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        booleanRef.element = true;
                    }
                }
            } else {
                D fir = firVariableSymbol2.getFir();
                FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                if (firCallableDeclaration != null && (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) != null && (original = importedFromObjectOrStaticData.getOriginal()) != null && DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                    visibility = firVariableSymbol2.getResolvedStatus().getVisibility();
                    r1 = Visibilities.Private.INSTANCE;
                    if (!Intrinsics.areEqual(visibility, r1)) {
                        visibility2 = firVariableSymbol.getResolvedStatus().getVisibility();
                        return Intrinsics.areEqual(visibility2, r1) ? Unit.INSTANCE : Unit.INSTANCE;
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirPropertySymbol) firVariableSymbol).getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getPROPERTY_HIDES_JAVA_FIELD(), (Object) firVariableSymbol2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        booleanRef.element = true;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        final FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass);
        FirContainingNamesAwareScopeKt.processAllProperties(firTypeScopeUnsubstitutedScope, new Function1() { // from class: zb5
            public final Object invoke(Object obj) {
                return FirPropertyHidesJavaFieldChecker.c(firClass, checkerContext, firTypeScopeUnsubstitutedScope, diagnosticReporter, (FirVariableSymbol) obj);
            }
        });
    }
}
