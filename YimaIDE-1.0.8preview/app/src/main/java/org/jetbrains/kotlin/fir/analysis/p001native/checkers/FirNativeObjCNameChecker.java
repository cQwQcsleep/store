package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.CharRange;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J5\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0002H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "validFirstChars", Argument.Delimiters.none, Argument.Delimiters.none, "validChars", "checkObjCName", "objCName", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameUtilities$ObjCName;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameUtilities$ObjCName;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCNameChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirNativeObjCNameChecker INSTANCE = new FirNativeObjCNameChecker();
    private static final Set<Character> validChars;
    private static final Set<Character> validFirstChars;

    static {
        Set<Character> setPlus = SetsKt.plus(SetsKt.plus(CollectionsKt.toSet(new CharRange('A', 'Z')), CollectionsKt.toSet(new CharRange('a', 'z'))), '_');
        validFirstChars = setPlus;
        validChars = SetsKt.plus(setPlus, CollectionsKt.toSet(new CharRange('0', '9')));
    }

    private FirNativeObjCNameChecker() {
        super(MppCheckerKind.Platform);
    }

    private final void checkObjCName(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNativeObjCNameUtilities.ObjCName objCName, FirDeclaration firDeclaration) {
        Character chFirstOrNull;
        String swiftName;
        Set setEmptySet;
        Set setEmptySet2;
        Set set;
        Set set2;
        Character chFirstOrNull2;
        KtSourceElement source = objCName.getAnnotation().getSource();
        Iterator<Map.Entry<Name, FirExpression>> it = objCName.getAnnotation().getArgumentMapping().getMapping().entrySet().iterator();
        while (it.hasNext()) {
            FirExpression value = it.next().getValue();
            if (!(value instanceof FirLiteralExpression)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) value.getSource(), FirNativeErrors.INSTANCE.getNON_LITERAL_OBJC_NAME_ARG(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        if (objCName.getName() == null && objCName.getSwiftName() == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirNativeErrors.INSTANCE.getINVALID_OBJC_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        String name = objCName.getName();
        Character ch = null;
        if (name == null || (chFirstOrNull = StringsKt.firstOrNull(name)) == null || validFirstChars.contains(chFirstOrNull)) {
            chFirstOrNull = null;
        }
        String swiftName2 = objCName.getSwiftName();
        if (swiftName2 != null && (chFirstOrNull2 = StringsKt.firstOrNull(swiftName2)) != null && !validFirstChars.contains(chFirstOrNull2)) {
            ch = chFirstOrNull2;
        }
        Set ofNotNull = SetsKt.setOfNotNull(new Character[]{chFirstOrNull, ch});
        if (!ofNotNull.isEmpty()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getINVALID_OBJC_NAME_FIRST_CHAR(), (Object) CollectionsKt.joinToString$default(ofNotNull, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        String name2 = objCName.getName();
        if ((name2 != null && name2.length() == 0) || ((swiftName = objCName.getSwiftName()) != null && swiftName.length() == 0)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirNativeErrors.INSTANCE.getEMPTY_OBJC_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        String name3 = objCName.getName();
        if (name3 == null || (set2 = StringsKt.toSet(name3)) == null || (setEmptySet = CollectionsKt.subtract(set2, validChars)) == null) {
            setEmptySet = SetsKt.emptySet();
        }
        String swiftName3 = objCName.getSwiftName();
        if (swiftName3 == null || (set = StringsKt.toSet(swiftName3)) == null || (setEmptySet2 = CollectionsKt.subtract(set, validChars)) == null) {
            setEmptySet2 = SetsKt.emptySet();
        }
        if (!SetsKt.plus(setEmptySet, setEmptySet2).isEmpty()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getINVALID_OBJC_NAME_CHARS(), (Object) CollectionsKt.joinToString$default(ofNotNull, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (objCName.getExact() && (!(firDeclaration instanceof FirClass) || ((FirClass) firDeclaration).getClassKind() == ClassKind.ENUM_ENTRY)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirNativeErrors.INSTANCE.getINAPPLICABLE_EXACT_OBJC_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (objCName.getExact() && objCName.getName() == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirNativeErrors.INSTANCE.getMISSING_EXACT_OBJC_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirValueParameter) {
            return;
        }
        List listFilterNotNull = CollectionsKt.filterNotNull(FirNativeObjCNameUtilities.INSTANCE.getObjCNames(firDeclaration.getSymbol(), checkerContext.getSession()));
        if (listFilterNotNull.isEmpty()) {
            return;
        }
        if ((firDeclaration instanceof FirCallableDeclaration) && ((FirMemberDeclaration) firDeclaration).getStatus().isOverride()) {
            Iterator it = listFilterNotNull.iterator();
            while (it.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirNativeObjCNameUtilities.ObjCName) it.next()).getAnnotation().getSource(), FirNativeErrors.INSTANCE.getINAPPLICABLE_OBJC_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        Iterator it2 = listFilterNotNull.iterator();
        while (it2.hasNext()) {
            INSTANCE.checkObjCName(checkerContext, diagnosticReporter, (FirNativeObjCNameUtilities.ObjCName) it2.next(), firDeclaration);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
