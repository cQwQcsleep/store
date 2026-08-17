package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.FirJvmNamesChecker;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmInvalidAndDangerousCharactersChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmInvalidAndDangerousCharactersChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJvmInvalidAndDangerousCharactersChecker INSTANCE = new FirJvmInvalidAndDangerousCharactersChecker();

    private FirJvmInvalidAndDangerousCharactersChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        KtSourceElement source = firDeclaration.getSource();
        if (firDeclaration instanceof FirRegularClass) {
            FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, ((FirRegularClass) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirNamedFunction) {
            FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, ((FirNamedFunction) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirTypeParameter) {
            FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, ((FirTypeParameter) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirProperty) {
            FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, ((FirProperty) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirTypeAlias) {
            FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, ((FirTypeAlias) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirValueParameter) {
            FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, ((FirValueParameter) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirFile) {
            FirFile firFile = (FirFile) firDeclaration;
            Iterator it = firFile.getPackageDirective().getPackageFqName().pathSegments().iterator();
            while (it.hasNext()) {
                FirJvmNamesChecker.INSTANCE.checkNameAndReport(checkerContext, diagnosticReporter, (Name) it.next(), firFile.getPackageDirective().getSource());
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
