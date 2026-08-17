package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeRefSource;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOuterClassArgumentsRequiredChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "checkOuterClassArgumentsRequired", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOuterClassArgumentsRequiredChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirOuterClassArgumentsRequiredChecker INSTANCE = new FirOuterClassArgumentsRequiredChecker();

    private FirOuterClassArgumentsRequiredChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkOuterClassArgumentsRequired(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef, FirRegularClass firRegularClass) {
        if (!(firTypeRef instanceof FirResolvedTypeRef) || (firTypeRef instanceof FirErrorTypeRef)) {
            return;
        }
        FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) firTypeRef;
        ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(firResolvedTypeRef.getConeType());
        FirUserTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
        if ((delegatedTypeRef instanceof FirUserTypeRef) && (abbreviatedTypeOrSelf instanceof ConeClassLikeType)) {
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, ((ConeClassLikeType) abbreviatedTypeOrSelf).getLookupTag());
            if (symbol instanceof FirRegularClassSymbol) {
                ConeTypeProjection[] typeProjections = org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.toTypeProjections(delegatedTypeRef.getQualifier());
                List<FirTypeParameterSymbol> typeParameterSymbols = ((FirRegularClassSymbol) symbol).getTypeParameterSymbols();
                int size = typeParameterSymbols.size();
                for (int length = typeProjections.length; length < size; length++) {
                    FirTypeParameterSymbol firTypeParameterSymbol = typeParameterSymbols.get(length);
                    if (!org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.isValidTypeParameterFromOuterDeclaration(firTypeParameterSymbol, firRegularClass, checkerContext.getSession())) {
                        FirBasedSymbol<?> containingDeclarationSymbol = firTypeParameterSymbol.getContainingDeclarationSymbol();
                        FirRegularClassSymbol firRegularClassSymbol = containingDeclarationSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingDeclarationSymbol : null;
                        if (firRegularClassSymbol == null) {
                            break;
                        }
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOUTER_CLASS_ARGUMENTS_REQUIRED(), (Object) firRegularClassSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        break;
                    }
                }
            }
        }
        List<FirTypeRefSource> listExtractArgumentsTypeRefAndSource = FirHelpersKt.extractArgumentsTypeRefAndSource(firTypeRef);
        if (listExtractArgumentsTypeRefAndSource == null) {
            return;
        }
        Iterator<FirTypeRefSource> it = listExtractArgumentsTypeRefAndSource.iterator();
        while (it.hasNext()) {
            FirTypeRef typeRef = it.next().getTypeRef();
            if (typeRef != null) {
                INSTANCE.checkOuterClassArgumentsRequired(checkerContext, diagnosticReporter, typeRef, firRegularClass);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        Iterator<FirTypeRef> it = firRegularClass.getSuperTypeRefs().iterator();
        while (it.hasNext()) {
            checkOuterClassArgumentsRequired(checkerContext, diagnosticReporter, it.next(), firRegularClass);
        }
    }
}
