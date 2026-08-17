package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirBadInheritedJavaSignaturesChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBadInheritedJavaSignaturesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBadInheritedJavaSignaturesChecker extends FirDeclarationChecker<FirClass> {
    public static final FirBadInheritedJavaSignaturesChecker INSTANCE = new FirBadInheritedJavaSignaturesChecker();

    private FirBadInheritedJavaSignaturesChecker() {
        super(MppCheckerKind.Platform);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:40:0x008e A[EDGE_INSN: B:40:0x008e->B:41:0x008f BREAK  A[LOOP:0: B:35:0x0074->B:60:?]] */
    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirCallableSymbol firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        boolean z;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        firCallableSymbol.getClass();
        FirDeclarationOrigin origin = firCallableSymbol.getOrigin();
        boolean z2 = true;
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firCallableSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return Unit.INSTANCE;
            }
        }
        boolean zContains = ConeTypeUtilsKt.contains(firCallableSymbol.getResolvedReturnType(), FirBadInheritedJavaSignaturesChecker$check$1$hasBadReturnType$1.INSTANCE);
        ConeKotlinType resolvedReceiverType = firCallableSymbol.getResolvedReceiverType();
        boolean z3 = resolvedReceiverType != null && ConeTypeUtilsKt.contains(resolvedReceiverType, FirBadInheritedJavaSignaturesChecker$check$1$hasBadReceiverType$1.INSTANCE);
        if (!(firCallableSymbol instanceof FirFunctionSymbol)) {
            z = false;
            break;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = ((FirFunctionSymbol) firCallableSymbol).getValueParameterSymbols();
        if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
            Iterator<T> it = valueParameterSymbols.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (ConeTypeUtilsKt.contains(((FirValueParameterSymbol) it.next()).getResolvedReturnType(), FirBadInheritedJavaSignaturesChecker$check$1$hasBadValueParameter$1$1.INSTANCE)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        List<FirValueParameterSymbol> contextParameterSymbols = firCallableSymbol.getContextParameterSymbols();
        if (!(contextParameterSymbols instanceof Collection) || !contextParameterSymbols.isEmpty()) {
            Iterator<T> it2 = contextParameterSymbols.iterator();
            do {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
            } while (!ConeTypeUtilsKt.contains(((FirValueParameterSymbol) it2.next()).getResolvedReturnType(), FirBadInheritedJavaSignaturesChecker$check$1$hasBadContextParameter$1$1.INSTANCE));
        } else {
            z2 = false;
            break;
        }
        if (zContains || z3 || z || z2) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_INHERITANCE_FROM_JAVA_MEMBER_REFERENCING_KOTLIN_FUNCTION(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean check$containsFunctionN(ConeKotlinType coneKotlinType) {
        return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), StandardClassIds$Annotations.INSTANCE.getFunctionN());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        FirContainingNamesAwareScopeKt.processAllCallables(FirHelpersKt.unsubstitutedScope(checkerContext, firClass), new Function1() { // from class: ly4
            public final Object invoke(Object obj) {
                return FirBadInheritedJavaSignaturesChecker.b(checkerContext, diagnosticReporter, firClass, (FirCallableSymbol) obj);
            }
        });
    }
}
