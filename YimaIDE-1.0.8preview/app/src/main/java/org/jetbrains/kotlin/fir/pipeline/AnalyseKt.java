package org.jetbrains.kotlin.fir.pipeline;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.diagnostics.impl.PendingDiagnosticsReporterImpl;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.collectors.CliDiagnosticsCollector;
import org.jetbrains.kotlin.fir.analysis.collectors.DiagnosticCollectorComponents;
import org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.analysis.collectors.components.DiagnosticComponentsFactory;
import org.jetbrains.kotlin.fir.analysis.collectors.components.LossDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.analysis.collectors.components.ReportCommitterDiagnosticComponent;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.pipeline.AnalyseKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.FirTotalResolveProcessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001aB\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00030\b*\u00020\u00052\u0006\u0010\n\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f\u001a(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\u00052\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r¨\u0006\u0012"}, d2 = {"runResolution", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/FirSession;", "firFiles", "runCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "scopeSession", Argument.Delimiters.none, "diagnosticsCollector", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "mppCheckerKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "collectLostDiagnosticsOnFile", "file", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnalyseKt {
    public static DiagnosticCollectorComponents a(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter) {
        pendingDiagnosticReporter.getClass();
        return new DiagnosticCollectorComponents(new AbstractDiagnosticCollectorComponent[]{new LossDiagnosticCollectorComponent(firSession, pendingDiagnosticReporter)}, new ReportCommitterDiagnosticComponent(firSession, pendingDiagnosticReporter));
    }

    public static final List<KtDiagnostic> collectLostDiagnosticsOnFile(final FirSession firSession, ScopeSession scopeSession, FirFile firFile, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        List<KtDiagnostic> list;
        firSession.getClass();
        scopeSession.getClass();
        firFile.getClass();
        baseDiagnosticsCollector.getClass();
        try {
            new CliDiagnosticsCollector(firSession, scopeSession, new Function1() { // from class: p30
                public final Object invoke(Object obj) {
                    return AnalyseKt.a(firSession, (PendingDiagnosticReporter) obj);
                }
            }).collectDiagnostics(firFile, new PendingDiagnosticsReporterImpl(baseDiagnosticsCollector));
            Unit unit = Unit.INSTANCE;
            KtSourceFile sourceFile = firFile.getSourceFile();
            return (sourceFile == null || (list = baseDiagnosticsCollector.getDiagnosticsByFile().get(sourceFile)) == null) ? CollectionsKt.emptyList() : list;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
            wq6.a();
            return null;
        }
    }

    public static final Map<FirFile, List<KtDiagnostic>> runCheckers(FirSession firSession, ScopeSession scopeSession, Collection<? extends FirFile> collection, BaseDiagnosticsCollector baseDiagnosticsCollector, MppCheckerKind mppCheckerKind) {
        List<KtDiagnostic> listEmptyList;
        firSession.getClass();
        scopeSession.getClass();
        collection.getClass();
        baseDiagnosticsCollector.getClass();
        mppCheckerKind.getClass();
        CliDiagnosticsCollector cliDiagnosticsCollectorCreate = DiagnosticComponentsFactory.INSTANCE.create(firSession, scopeSession, mppCheckerKind);
        PendingDiagnosticsReporterImpl pendingDiagnosticsReporterImpl = new PendingDiagnosticsReporterImpl(baseDiagnosticsCollector);
        for (FirFile firFile : collection) {
            try {
                cliDiagnosticsCollectorCreate.collectDiagnostics(firFile, pendingDiagnosticsReporterImpl);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
                wq6.a();
                return null;
            }
        }
        cliDiagnosticsCollectorCreate.collectDiagnosticsInSettings(pendingDiagnosticsReporterImpl);
        Collection<? extends FirFile> collection2 = collection;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection2, 10)), 16));
        for (Object obj : collection2) {
            KtSourceFile sourceFile = ((FirFile) obj).getSourceFile();
            if (sourceFile == null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = baseDiagnosticsCollector.getDiagnosticsByFile().get(sourceFile);
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
            }
            linkedHashMap.put(obj, listEmptyList);
        }
        return linkedHashMap;
    }

    public static final Pair<ScopeSession, List<FirFile>> runResolution(FirSession firSession, List<? extends FirFile> list) {
        firSession.getClass();
        list.getClass();
        FirTotalResolveProcessor firTotalResolveProcessor = new FirTotalResolveProcessor(firSession);
        firTotalResolveProcessor.process(list);
        return TuplesKt.to(firTotalResolveProcessor.getScopeSession(), list);
    }
}
