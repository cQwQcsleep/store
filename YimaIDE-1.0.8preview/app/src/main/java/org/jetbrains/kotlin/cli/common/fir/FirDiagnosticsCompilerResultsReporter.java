package org.jetbrains.kotlin.cli.common.fir;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.KtInMemoryTextSourceFile;
import org.jetbrains.kotlin.KtIoFileSourceFile;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtVirtualFileSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.fir.FirDiagnosticsCompilerResultsReporter;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer;
import org.jetbrains.kotlin.cli.common.messages.MessageUtil;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticRangeUtils;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithoutSource;
import org.jetbrains.kotlin.diagnostics.KtPsiDiagnostic;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\rJ*\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u001a\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u00130\u0010J(\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005J\"\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/fir/FirDiagnosticsCompilerResultsReporter;", Argument.Delimiters.none, "<init>", "()V", "reportToMessageCollector", Argument.Delimiters.none, "diagnosticsCollector", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "renderDiagnosticName", "throwFirstErrorAsException", "messageRenderer", "Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;", "reportByFile", "report", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", Argument.Delimiters.none, "reportDiagnosticToMessageCollector", "diagnostic", "location", "reporter", "throwErrorDiagnosticAsException", "InFileDiagnosticsComparator", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDiagnosticsCompilerResultsReporter {
    public static final FirDiagnosticsCompilerResultsReporter INSTANCE = new FirDiagnosticsCompilerResultsReporter();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/fir/FirDiagnosticsCompilerResultsReporter$InFileDiagnosticsComparator;", "Ljava/util/Comparator;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "<init>", "()V", "compare", Argument.Delimiters.none, "o1", "o2", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InFileDiagnosticsComparator implements Comparator<KtDiagnostic> {
        public static final InFileDiagnosticsComparator INSTANCE = new InFileDiagnosticsComparator();

        private InFileDiagnosticsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(KtDiagnostic o1, KtDiagnostic o2) {
            o1.getClass();
            o2.getClass();
            TextRange firstRange = o1.getFirstRange();
            TextRange firstRange2 = o2.getFirstRange();
            return !Intrinsics.areEqual(firstRange, firstRange2) ? DiagnosticRangeUtils.TEXT_RANGE_COMPARATOR.compare(firstRange, firstRange2) : o1.getFactory().getName().compareTo(o2.getFactory().getName());
        }
    }

    private FirDiagnosticsCompilerResultsReporter() {
    }

    public static Unit a(MessageCollector messageCollector, boolean z, KtDiagnostic ktDiagnostic, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        ktDiagnostic.getClass();
        INSTANCE.reportDiagnosticToMessageCollector(ktDiagnostic, compilerMessageSourceLocation, messageCollector, z);
        return Unit.INSTANCE;
    }

    public static SequentialCloseablePositionFinder b(KtSourceFile ktSourceFile) {
        if ((ktSourceFile instanceof KtVirtualFileSourceFile) || (ktSourceFile instanceof KtInMemoryTextSourceFile)) {
            return new SequentialCloseablePositionFinder(new InputStreamReader(ktSourceFile.getContentsAsStream(), Charsets.UTF_8));
        }
        if (ktSourceFile instanceof KtIoFileSourceFile) {
            KtIoFileSourceFile ktIoFileSourceFile = (KtIoFileSourceFile) ktSourceFile;
            if (ktIoFileSourceFile.getFile().isFile()) {
                return new SequentialCloseablePositionFinder(new InputStreamReader(ktIoFileSourceFile.getContentsAsStream(), Charsets.UTF_8));
            }
        } else if (!(ktSourceFile instanceof KtPsiSourceFile) && ktSourceFile != null) {
            w04.a("Unexpected source file type: ", ktSourceFile);
            return null;
        }
        return null;
    }

    public static Unit c(MessageRenderer messageRenderer, KtDiagnostic ktDiagnostic, CompilerMessageSourceLocation compilerMessageSourceLocation) {
        ktDiagnostic.getClass();
        INSTANCE.throwErrorDiagnosticAsException(ktDiagnostic, compilerMessageSourceLocation, messageRenderer);
        return Unit.INSTANCE;
    }

    private final void throwErrorDiagnosticAsException(KtDiagnostic diagnostic, CompilerMessageSourceLocation location, MessageRenderer messageRenderer) {
        if (diagnostic.getSeverity() != Severity.ERROR) {
            return;
        }
        String strRender = messageRenderer.render(diagnostic.getSeverity().toCompilerMessageSeverity(), diagnostic.renderMessage(), location);
        throw new IllegalStateException(diagnostic.getFactory().getName() + ": " + strRender);
    }

    public static /* synthetic */ boolean throwFirstErrorAsException$default(FirDiagnosticsCompilerResultsReporter firDiagnosticsCompilerResultsReporter, BaseDiagnosticsCollector baseDiagnosticsCollector, MessageRenderer messageRenderer, int i, Object obj) {
        if ((i & 2) != 0) {
            messageRenderer = MessageRenderer.PLAIN_RELATIVE_PATHS;
            messageRenderer.getClass();
        }
        return firDiagnosticsCompilerResultsReporter.throwFirstErrorAsException(baseDiagnosticsCollector, messageRenderer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean reportByFile(BaseDiagnosticsCollector diagnosticsCollector, Function2<? super KtDiagnostic, ? super CompilerMessageSourceLocation, Unit> report) throws IOException {
        SequentialCloseablePositionFinder sequentialCloseablePositionFinder;
        LinkedHashMap linkedHashMap;
        SequentialCloseablePositionFinder sequentialCloseablePositionFinder2;
        CompilerMessageSourceLocation compilerMessageSourceLocationCreateMessageLocation;
        diagnosticsCollector.getClass();
        report.getClass();
        Iterator<KtSourceFile> it = diagnosticsCollector.getDiagnosticsByFile().keySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            final KtSourceFile next = it.next();
            Lazy lazy = LazyKt.lazy(new Function0() { // from class: s45
                public final Object invoke() {
                    return FirDiagnosticsCompilerResultsReporter.b(next);
                }
            });
            try {
                List<KtDiagnostic> listEmptyList = diagnosticsCollector.getDiagnosticsByFile().get(next);
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                SequentialCloseablePositionFinder sequentialCloseablePositionFinder3 = (SequentialCloseablePositionFinder) lazy.getValue();
                if (sequentialCloseablePositionFinder3 != null) {
                    TreeSet treeSet = new TreeSet();
                    for (KtDiagnostic ktDiagnostic : listEmptyList) {
                        if ((ktDiagnostic instanceof KtDiagnosticWithSource) && !(ktDiagnostic instanceof KtPsiDiagnostic)) {
                            TextRange firstRange = ((KtDiagnosticWithSource) ktDiagnostic).getFirstRange();
                            treeSet.add(Integer.valueOf(firstRange.getStartOffset()));
                            treeSet.add(Integer.valueOf(firstRange.getEndOffset()));
                        }
                    }
                    linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(treeSet, 10)), 16));
                    for (Object obj : treeSet) {
                        linkedHashMap.put(obj, SequentialPositionFinder.findNextPosition$default(sequentialCloseablePositionFinder3, ((Number) obj).intValue(), false, 2, null));
                    }
                } else {
                    linkedHashMap = null;
                }
                for (KtDiagnostic ktDiagnostic2 : CollectionsKt.sortedWith(listEmptyList, InFileDiagnosticsComparator.INSTANCE)) {
                    if (ktDiagnostic2 instanceof KtDiagnosticWithoutSource) {
                        compilerMessageSourceLocationCreateMessageLocation = ((KtDiagnosticWithoutSource) ktDiagnostic2).getLocation();
                    } else {
                        if (!(ktDiagnostic2 instanceof KtDiagnosticWithSource)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        if (((KtDiagnosticWithSource) ktDiagnostic2) instanceof KtPsiDiagnostic) {
                            PsiFile containingFile = ((KtPsiDiagnostic) ktDiagnostic2).getElement().getPsi().getContainingFile();
                            compilerMessageSourceLocationCreateMessageLocation = MessageUtil.psiFileToMessageLocation(containingFile, containingFile.getName(), DiagnosticUtils.getLineAndColumnRange(containingFile, ((KtDiagnosticWithSource) ktDiagnostic2).getTextRanges()));
                        } else if (linkedHashMap != null) {
                            TextRange firstRange2 = ((KtDiagnosticWithSource) ktDiagnostic2).getFirstRange();
                            Object obj2 = linkedHashMap.get(Integer.valueOf(firstRange2.getStartOffset()));
                            obj2.getClass();
                            KtSourceFileDiagnosticPos ktSourceFileDiagnosticPos = (KtSourceFileDiagnosticPos) obj2;
                            Object obj3 = linkedHashMap.get(Integer.valueOf(firstRange2.getEndOffset()));
                            obj3.getClass();
                            KtSourceFileDiagnosticPos ktSourceFileDiagnosticPos2 = (KtSourceFileDiagnosticPos) obj3;
                            compilerMessageSourceLocationCreateMessageLocation = MessageUtil.createMessageLocation(next != null ? next.getPath() : null, ktSourceFileDiagnosticPos.getLineContent(), ktSourceFileDiagnosticPos.getLine(), ktSourceFileDiagnosticPos.getColumn(), ktSourceFileDiagnosticPos2.getLine(), ktSourceFileDiagnosticPos2.getColumn());
                        } else {
                            compilerMessageSourceLocationCreateMessageLocation = null;
                        }
                    }
                    report.invoke(ktDiagnostic2, compilerMessageSourceLocationCreateMessageLocation);
                    z = z || ktDiagnostic2.getSeverity() == Severity.ERROR;
                }
                if (lazy.isInitialized() && (sequentialCloseablePositionFinder2 = (SequentialCloseablePositionFinder) lazy.getValue()) != null) {
                    sequentialCloseablePositionFinder2.close();
                }
            } catch (Throwable th) {
                if (lazy.isInitialized() && (sequentialCloseablePositionFinder = (SequentialCloseablePositionFinder) lazy.getValue()) != null) {
                    sequentialCloseablePositionFinder.close();
                }
                throw th;
            }
        }
        return z;
    }

    public final void reportDiagnosticToMessageCollector(KtDiagnostic diagnostic, CompilerMessageSourceLocation location, MessageCollector reporter, boolean renderDiagnosticName) {
        diagnostic.getClass();
        reporter.getClass();
        CompilerMessageSeverity compilerMessageSeverity = diagnostic.getSeverity().toCompilerMessageSeverity();
        String strRenderMessage = diagnostic.renderMessage();
        if (renderDiagnosticName) {
            strRenderMessage = "[" + diagnostic.getFactoryName() + "] " + strRenderMessage;
        } else if (renderDiagnosticName) {
            bu8.a();
            return;
        }
        reporter.report(compilerMessageSeverity, strRenderMessage, location);
    }

    public final boolean reportToMessageCollector(BaseDiagnosticsCollector diagnosticsCollector, final MessageCollector messageCollector, final boolean renderDiagnosticName) throws IOException {
        boolean z;
        boolean z2;
        diagnosticsCollector.getClass();
        messageCollector.getClass();
        boolean zReportByFile = reportByFile(diagnosticsCollector, new Function2() { // from class: u45
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticsCompilerResultsReporter.a(messageCollector, renderDiagnosticName, (KtDiagnostic) obj, (CompilerMessageSourceLocation) obj2);
            }
        });
        AnalyzerWithCompilerReport.Companion companion = AnalyzerWithCompilerReport.INSTANCE;
        List<KtDiagnostic> diagnostics = diagnosticsCollector.getDiagnostics();
        boolean z3 = true;
        if (!(diagnostics instanceof Collection) || !diagnostics.isEmpty()) {
            Iterator<T> it = diagnostics.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (Intrinsics.areEqual(((KtDiagnostic) it.next()).getFactory(), FirErrors.INSTANCE.getINCOMPATIBLE_CLASS())) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        List<KtDiagnostic> diagnostics2 = diagnosticsCollector.getDiagnostics();
        if (!(diagnostics2 instanceof Collection) || !diagnostics2.isEmpty()) {
            Iterator<T> it2 = diagnostics2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                if (Intrinsics.areEqual(((KtDiagnostic) it2.next()).getFactory(), FirErrors.INSTANCE.getPRE_RELEASE_CLASS())) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
            break;
        }
        List<KtDiagnostic> diagnostics3 = diagnosticsCollector.getDiagnostics();
        if ((diagnostics3 instanceof Collection) && diagnostics3.isEmpty()) {
            z3 = false;
        } else {
            Iterator<T> it3 = diagnostics3.iterator();
            while (it3.hasNext()) {
                if (Intrinsics.areEqual(((KtDiagnostic) it3.next()).getFactory(), FirErrors.INSTANCE.getIR_WITH_UNSTABLE_ABI_COMPILED_CLASS())) {
                }
            }
            z3 = false;
        }
        companion.reportSpecialErrors(z, z2, z3, messageCollector);
        return zReportByFile;
    }

    public final boolean throwFirstErrorAsException(BaseDiagnosticsCollector diagnosticsCollector, final MessageRenderer messageRenderer) {
        diagnosticsCollector.getClass();
        messageRenderer.getClass();
        return reportByFile(diagnosticsCollector, new Function2() { // from class: t45
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticsCompilerResultsReporter.c(messageRenderer, (KtDiagnostic) obj, (CompilerMessageSourceLocation) obj2);
            }
        });
    }
}
