package org.jetbrains.kotlin.cli.common.messages;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiFormatUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.analyzer.AbstractAnalyzerWithCompilerReport;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.diagnostics.Errors;
import org.jetbrains.kotlin.diagnostics.GenericDiagnostics;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SimpleDiagnostic;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirPlatformIncompatibilityDiagnosticRendererKt;
import org.jetbrains.kotlin.fir.builder.FirSyntaxErrors;
import org.jetbrains.kotlin.load.java.components.TraceBasedErrorReporter;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.AnalyzingUtils;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.CompilerEnvironment;
import org.jetbrains.kotlin.resolve.DescriptorToSourceUtils;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.TargetEnvironment;
import org.jetbrains.kotlin.resolve.checkers.OptInUsageChecker;
import org.jetbrains.kotlin.resolve.diagnostics.Diagnostics;
import org.jetbrains.kotlin.resolve.jvm.JvmBindingContextSlices;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\u00020\u0001:\u0003\u001e\u001f B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0016\u0010\u0015\u001a\u00020\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J$\u0010\u001b\u001a\u00020\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/AnalyzerWithCompilerReport;", "Lorg/jetbrains/kotlin/analyzer/AbstractAnalyzerWithCompilerReport;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "targetEnvironment", "Lorg/jetbrains/kotlin/resolve/TargetEnvironment;", "getTargetEnvironment", "()Lorg/jetbrains/kotlin/resolve/TargetEnvironment;", "analysisResult", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "getAnalysisResult", "()Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "setAnalysisResult", "(Lorg/jetbrains/kotlin/analyzer/AnalysisResult;)V", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "reportIncompleteHierarchies", Argument.Delimiters.none, "reportAlternativeSignatureErrors", "reportSyntaxErrors", "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "hasErrors", Argument.Delimiters.none, "analyzeAndReport", "analyze", "Lkotlin/Function0;", "SyntaxErrorReport", "MyDiagnostic", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnalyzerWithCompilerReport implements AbstractAnalyzerWithCompilerReport {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DiagnosticFactory0<PsiErrorElement> SYNTAX_ERROR_FACTORY;
    public AnalysisResult analysisResult;
    private final CompilerConfiguration configuration;
    private final MessageCollector messageCollector;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/AnalyzerWithCompilerReport$MyDiagnostic;", "E", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/diagnostics/SimpleDiagnostic;", "psiElement", "factory", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0;", "message", Argument.Delimiters.none, "<init>", "(Lcom/intellij/psi/PsiElement;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "isValid", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MyDiagnostic<E extends PsiElement> extends SimpleDiagnostic<E> {
        private final boolean isValid;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyDiagnostic(E e, DiagnosticFactory0<E> diagnosticFactory0, String str) {
            super(e, diagnosticFactory0, Severity.ERROR);
            e.getClass();
            diagnosticFactory0.getClass();
            str.getClass();
            this.message = str;
            this.isValid = true;
        }

        public final String getMessage() {
            return this.message;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
        /* JADX INFO: renamed from: isValid, reason: from getter */
        public boolean getIsValid() {
            return this.isValid;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/AnalyzerWithCompilerReport$SyntaxErrorReport;", Argument.Delimiters.none, "isHasErrors", Argument.Delimiters.none, "isAllErrorsAtEof", "<init>", "(ZZ)V", "()Z", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SyntaxErrorReport {
        private final boolean isAllErrorsAtEof;
        private final boolean isHasErrors;

        public SyntaxErrorReport(boolean z, boolean z2) {
            this.isHasErrors = z;
            this.isAllErrorsAtEof = z2;
        }

        /* JADX INFO: renamed from: isAllErrorsAtEof, reason: from getter */
        public final boolean getIsAllErrorsAtEof() {
            return this.isAllErrorsAtEof;
        }

        /* JADX INFO: renamed from: isHasErrors, reason: from getter */
        public final boolean getIsHasErrors() {
            return this.isHasErrors;
        }
    }

    static {
        DiagnosticFactory0<PsiErrorElement> diagnosticFactory0Create = DiagnosticFactory0.create(Severity.ERROR);
        diagnosticFactory0Create.getClass();
        SYNTAX_ERROR_FACTORY = diagnosticFactory0Create;
    }

    public AnalyzerWithCompilerReport(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        this.configuration = compilerConfiguration;
        this.messageCollector = CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration);
    }

    public static Unit a(AnalyzerWithCompilerReport analyzerWithCompilerReport, String str) {
        str.getClass();
        MessageCollector.report$default(analyzerWithCompilerReport.messageCollector, CompilerMessageSeverity.WARNING, str, null, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit b(AnalyzerWithCompilerReport analyzerWithCompilerReport, String str) {
        str.getClass();
        MessageCollector.report$default(analyzerWithCompilerReport.messageCollector, CompilerMessageSeverity.ERROR, str, null, 4, null);
        return Unit.INSTANCE;
    }

    private final void reportAlternativeSignatureErrors() {
        BindingContext bindingContext = getAnalysisResult().getBindingContext();
        Collection<DeclarationDescriptor> keys = bindingContext.getKeys(JvmBindingContextSlices.LOAD_FROM_JAVA_SIGNATURE_ERRORS);
        keys.getClass();
        if (keys.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder("The following Java entities have annotations with wrong Kotlin signatures:\n");
        for (DeclarationDescriptor declarationDescriptor : keys) {
            declarationDescriptor.getClass();
            PsiModifierListOwner psiModifierListOwnerDescriptorToDeclaration = DescriptorToSourceUtils.descriptorToDeclaration(declarationDescriptor);
            List<String> list = (List) bindingContext.get(JvmBindingContextSlices.LOAD_FROM_JAVA_SIGNATURE_ERRORS, declarationDescriptor);
            if (list != null) {
                list.isEmpty();
            }
            psiModifierListOwnerDescriptorToDeclaration.getClass();
            sb.append(PsiFormatUtil.getExternalName(psiModifierListOwnerDescriptorToDeclaration));
            sb.append(":\n");
            list.getClass();
            for (String str : list) {
                sb.append(FirPlatformIncompatibilityDiagnosticRendererKt.INDENTATION_UNIT);
                sb.append(str);
                sb.append("\n");
            }
        }
        MessageCollector.report$default(this.messageCollector, CompilerMessageSeverity.ERROR, sb.toString(), null, 4, null);
    }

    private final void reportIncompleteHierarchies() {
        BindingContext bindingContext = getAnalysisResult().getBindingContext();
        Collection<ClassDescriptor> keys = bindingContext.getKeys(TraceBasedErrorReporter.INCOMPLETE_HIERARCHY);
        keys.getClass();
        if (keys.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder("Supertypes of the following classes cannot be resolved. Please make sure you have the required dependencies in the classpath:\n");
        for (ClassDescriptor classDescriptor : keys) {
            String strAsString = DescriptorUtils.getFqName(classDescriptor).asString();
            List list = (List) bindingContext.get(TraceBasedErrorReporter.INCOMPLETE_HIERARCHY, classDescriptor);
            if (list != null) {
                list.isEmpty();
            }
            sb.append("    class ");
            sb.append(strAsString);
            sb.append(", unresolved supertypes: ");
            list.getClass();
            sb.append(CollectionsKt.joinToString$default(list, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
            sb.append("\n");
        }
        MessageCollector.report$default(this.messageCollector, CompilerMessageSeverity.ERROR, sb.toString(), null, 4, null);
    }

    private final void reportSyntaxErrors(Collection<? extends KtFile> files) {
        Iterator<? extends KtFile> it = files.iterator();
        while (it.hasNext()) {
            INSTANCE.reportSyntaxErrors((PsiElement) it.next(), this.messageCollector);
        }
    }

    public void analyzeAndReport(Collection<? extends KtFile> files, Function0<? extends AnalysisResult> analyze) {
        files.getClass();
        analyze.getClass();
        setAnalysisResult((AnalysisResult) analyze.invoke());
        if (!getAnalysisResult().isError()) {
            OptInUsageChecker.Companion.checkCompilerArguments(getAnalysisResult().getModuleDescriptor(), CommonConfigurationKeysKt.getLanguageVersionSettings(this.configuration), new Function1() { // from class: t30
                public final Object invoke(Object obj) {
                    return AnalyzerWithCompilerReport.b(this.b, (String) obj);
                }
            }, new Function1() { // from class: u30
                public final Object invoke(Object obj) {
                    return AnalyzerWithCompilerReport.a(this.b, (String) obj);
                }
            });
        }
        reportSyntaxErrors(files);
        Companion companion = INSTANCE;
        Diagnostics diagnostics = getAnalysisResult().getBindingContext().getDiagnostics();
        diagnostics.getClass();
        companion.reportDiagnostics((GenericDiagnostics<?>) diagnostics, this.messageCollector, CLIConfigurationKeysKt.getRenderDiagnosticInternalName(this.configuration));
        reportIncompleteHierarchies();
        reportAlternativeSignatureErrors();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public AnalysisResult getAnalysisResult() throws UninitializedPropertyAccessException {
        AnalysisResult analysisResult = this.analysisResult;
        if (analysisResult != null) {
            return analysisResult;
        }
        Intrinsics.throwUninitializedPropertyAccessException("analysisResult");
        return null;
    }

    public TargetEnvironment getTargetEnvironment() {
        return CompilerEnvironment.INSTANCE;
    }

    public boolean hasErrors() {
        return CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.checkHasErrors(this.configuration);
    }

    public void setAnalysisResult(AnalysisResult analysisResult) {
        analysisResult.getClass();
        this.analysisResult = analysisResult;
    }

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002J\"\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tJ\"\u0010\u000f\u001a\u00020\t2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tJ&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J9\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2'\u0010!\u001a#\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110#¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00170\"H\u0002J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0013\u001a\u00020\u0014R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/AnalyzerWithCompilerReport$Companion;", Argument.Delimiters.none, "<init>", "()V", "SYNTAX_ERROR_FACTORY", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0;", "Lcom/intellij/psi/PsiErrorElement;", "kotlin.jvm.PlatformType", "reportDiagnostic", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "reporter", "Lorg/jetbrains/kotlin/cli/common/messages/DiagnosticMessageReporter;", "renderDiagnosticName", "reportDiagnostics", "unsortedDiagnostics", "Lorg/jetbrains/kotlin/diagnostics/GenericDiagnostics;", "diagnostics", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "renderInternalDiagnosticName", "reportSpecialErrors", Argument.Delimiters.none, "hasIncompatibleClasses", "hasPrereleaseClasses", "hasUnstableClasses", "reportSyntaxErrors", "Lorg/jetbrains/kotlin/cli/common/messages/AnalyzerWithCompilerReport$SyntaxErrorReport;", "file", "Lcom/intellij/psi/PsiElement;", "diagnosticCollector", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "createAndReportSyntaxError", "Lkotlin/Function2;", Argument.Delimiters.none, "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "message", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static Unit a(BaseDiagnosticsCollector baseDiagnosticsCollector, final PsiElement psiElement, PsiErrorElement psiErrorElement, String str) {
            psiErrorElement.getClass();
            str.getClass();
            baseDiagnosticsCollector.report(FirSyntaxErrors.INSTANCE.getSYNTAX().on(new KtRealPsiSourceElement(psiErrorElement), str, null, DiagnosticContext.Default.INSTANCE), new DiagnosticContext() { // from class: org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport$Companion$reportSyntaxErrors$2$context$1
                @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
                public KtSourceFile getContainingFile() {
                    PsiFile containingFile = psiElement.getContainingFile();
                    containingFile.getClass();
                    return new KtPsiSourceFile(containingFile);
                }

                @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext, org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
                /* JADX INFO: renamed from: getLanguageVersionSettings */
                public LanguageVersionSettings get$languageVersionSettings() {
                    return LanguageVersionSettingsImpl.DEFAULT;
                }

                @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
                public boolean isDiagnosticSuppressed(KtDiagnostic diagnostic) {
                    diagnostic.getClass();
                    return false;
                }
            });
            return Unit.INSTANCE;
        }

        public static Unit b(DiagnosticMessageReporter diagnosticMessageReporter, PsiErrorElement psiErrorElement, String str) {
            psiErrorElement.getClass();
            str.getClass();
            AnalyzerWithCompilerReport.INSTANCE.reportDiagnostic(new MyDiagnostic(psiErrorElement, AnalyzerWithCompilerReport.SYNTAX_ERROR_FACTORY, str), diagnosticMessageReporter, false);
            return Unit.INSTANCE;
        }

        private final boolean reportDiagnostic(Diagnostic diagnostic, DiagnosticMessageReporter reporter, boolean renderDiagnosticName) {
            String strRender;
            if (!diagnostic.getIsValid()) {
                return false;
            }
            MyDiagnostic myDiagnostic = diagnostic instanceof MyDiagnostic ? (MyDiagnostic) diagnostic : null;
            if (myDiagnostic == null || (strRender = myDiagnostic.getMessage()) == null) {
                strRender = DefaultErrorMessages.render(diagnostic);
                strRender.getClass();
            }
            if (renderDiagnosticName) {
                strRender = "[" + diagnostic.getFactoryName() + "] " + strRender;
            } else if (renderDiagnosticName) {
                bu8.a();
                return false;
            }
            reporter.report(diagnostic, diagnostic.getPsiFile(), strRender);
            return diagnostic.getSeverity() == Severity.ERROR;
        }

        private final SyntaxErrorReport reportSyntaxErrors(PsiElement file, final Function2<? super PsiErrorElement, ? super String, Unit> createAndReportSyntaxError) {
            PsiElementVisitor psiElementVisitor = new AnalyzingUtils.PsiErrorElementVisitor() { // from class: org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport$Companion$reportSyntaxErrors$ErrorReportingVisitor
                private boolean allErrorsAtEof = true;
                private boolean hasErrors;

                private final boolean isAtEof(PsiElement psiElement) {
                    do {
                        psiElement = psiElement.getNextSibling();
                        if (psiElement == null) {
                            return true;
                        }
                        if (!(psiElement instanceof PsiWhiteSpace)) {
                            return false;
                        }
                    } while (psiElement instanceof PsiComment);
                    return false;
                }

                public final boolean getAllErrorsAtEof() {
                    return this.allErrorsAtEof;
                }

                public final boolean getHasErrors() {
                    return this.hasErrors;
                }

                public final void setAllErrorsAtEof(boolean z) {
                    this.allErrorsAtEof = z;
                }

                public final void setHasErrors(boolean z) {
                    this.hasErrors = z;
                }

                public void visitErrorElement(PsiErrorElement element) {
                    element.getClass();
                    String errorDescription = element.getErrorDescription();
                    errorDescription.getClass();
                    if (this.allErrorsAtEof && !isAtEof(element)) {
                        this.allErrorsAtEof = false;
                    }
                    this.hasErrors = true;
                    Function2<PsiErrorElement, String, Unit> function2 = createAndReportSyntaxError;
                    if (StringUtil.isEmpty(errorDescription)) {
                        errorDescription = "Syntax error";
                    }
                    function2.invoke(element, errorDescription);
                }
            };
            file.accept(psiElementVisitor);
            return new SyntaxErrorReport(psiElementVisitor.getHasErrors(), psiElementVisitor.getAllErrorsAtEof());
        }

        public final boolean reportDiagnostics(GenericDiagnostics<?> diagnostics, MessageCollector messageCollector, boolean renderInternalDiagnosticName) {
            boolean z;
            boolean z2;
            diagnostics.getClass();
            messageCollector.getClass();
            boolean zReportDiagnostics = reportDiagnostics(diagnostics, new DefaultDiagnosticReporter(messageCollector), renderInternalDiagnosticName);
            Companion companion = AnalyzerWithCompilerReport.INSTANCE;
            boolean z3 = diagnostics instanceof Collection;
            boolean z4 = true;
            if (!z3 || !((Collection) diagnostics).isEmpty()) {
                Iterator<?> it = diagnostics.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (Intrinsics.areEqual(((UnboundDiagnostic) it.next()).getFactory(), Errors.INCOMPATIBLE_CLASS)) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            if (!z3 || !((Collection) diagnostics).isEmpty()) {
                Iterator<?> it2 = diagnostics.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (Intrinsics.areEqual(((UnboundDiagnostic) it2.next()).getFactory(), Errors.PRE_RELEASE_CLASS)) {
                        z2 = true;
                        break;
                    }
                }
            } else {
                z2 = false;
                break;
            }
            if (z3 && ((Collection) diagnostics).isEmpty()) {
                z4 = false;
            } else {
                Iterator<?> it3 = diagnostics.iterator();
                while (it3.hasNext()) {
                    if (Intrinsics.areEqual(((UnboundDiagnostic) it3.next()).getFactory(), Errors.IR_WITH_UNSTABLE_ABI_COMPILED_CLASS)) {
                    }
                }
                z4 = false;
            }
            companion.reportSpecialErrors(z, z2, z4, messageCollector);
            return zReportDiagnostics;
        }

        public final void reportSpecialErrors(boolean hasIncompatibleClasses, boolean hasPrereleaseClasses, boolean hasUnstableClasses, MessageCollector messageCollector) {
            messageCollector.getClass();
            if (hasIncompatibleClasses) {
                MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Incompatible classes were found in dependencies. Remove them from the classpath or use '-Xskip-metadata-version-check' to suppress errors", null, 4, null);
            }
            if (hasPrereleaseClasses) {
                MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Pre-release declarations were found in dependencies. Please exclude the dependencies with such declarations and recompile with a release compiler, or use '-Xskip-prerelease-check' to suppress errors. Note that in the latter case the compiled declarations will also be marked as pre-release.", null, 4, null);
            }
            if (hasUnstableClasses) {
                MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Classes compiled by an unstable version of the Kotlin compiler were found in dependencies. Remove them from the classpath or use '-Xallow-unstable-dependencies' to suppress errors", null, 4, null);
            }
        }

        private Companion() {
        }

        public final SyntaxErrorReport reportSyntaxErrors(final PsiElement file, final BaseDiagnosticsCollector diagnosticCollector) {
            file.getClass();
            diagnosticCollector.getClass();
            return reportSyntaxErrors(file, new Function2() { // from class: v30
                public final Object invoke(Object obj, Object obj2) {
                    return AnalyzerWithCompilerReport.Companion.a(diagnosticCollector, file, (PsiErrorElement) obj, (String) obj2);
                }
            });
        }

        public final SyntaxErrorReport reportSyntaxErrors(PsiElement file, final DiagnosticMessageReporter reporter) {
            file.getClass();
            reporter.getClass();
            return reportSyntaxErrors(file, new Function2() { // from class: w30
                public final Object invoke(Object obj, Object obj2) {
                    return AnalyzerWithCompilerReport.Companion.b(reporter, (PsiErrorElement) obj, (String) obj2);
                }
            });
        }

        public final SyntaxErrorReport reportSyntaxErrors(PsiElement file, MessageCollector messageCollector) {
            file.getClass();
            messageCollector.getClass();
            return reportSyntaxErrors(file, new DefaultDiagnosticReporter(messageCollector));
        }

        public final boolean reportDiagnostics(GenericDiagnostics<?> unsortedDiagnostics, DiagnosticMessageReporter reporter, boolean renderDiagnosticName) {
            unsortedDiagnostics.getClass();
            reporter.getClass();
            Iterable iterableAll = unsortedDiagnostics.all();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterableAll) {
                if (obj instanceof Diagnostic) {
                    arrayList.add(obj);
                }
            }
            List<Diagnostic> listSortedDiagnostics = DiagnosticUtils.sortedDiagnostics(arrayList);
            listSortedDiagnostics.getClass();
            boolean zReportDiagnostic = false;
            for (Diagnostic diagnostic : listSortedDiagnostics) {
                diagnostic.getClass();
                zReportDiagnostic |= reportDiagnostic(diagnostic, reporter, renderDiagnosticName);
            }
            return zReportDiagnostic;
        }
    }
}
