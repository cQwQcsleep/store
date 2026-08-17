package org.jetbrains.kotlin.fir.analysis.collectors.components;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckersDiagnosticComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckersDiagnosticComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckersDiagnosticComponent;
import org.jetbrains.kotlin.fir.analysis.collectors.CliDiagnosticsCollector;
import org.jetbrains.kotlin.fir.analysis.collectors.DiagnosticCollectorComponents;
import org.jetbrains.kotlin.fir.analysis.collectors.components.DiagnosticComponentsFactory;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/DiagnosticComponentsFactory;", Argument.Delimiters.none, "<init>", "()V", "createAllDiagnosticComponents", "Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/analysis/collectors/CliDiagnosticsCollector;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticComponentsFactory {
    public static final DiagnosticComponentsFactory INSTANCE = new DiagnosticComponentsFactory();

    private DiagnosticComponentsFactory() {
    }

    public static DiagnosticCollectorComponents a(FirSession firSession, MppCheckerKind mppCheckerKind, PendingDiagnosticReporter pendingDiagnosticReporter) {
        pendingDiagnosticReporter.getClass();
        return INSTANCE.createAllDiagnosticComponents(firSession, pendingDiagnosticReporter, mppCheckerKind);
    }

    private final DiagnosticCollectorComponents createAllDiagnosticComponents(FirSession session, PendingDiagnosticReporter reporter, MppCheckerKind mppKind) {
        FirSession firSession;
        PendingDiagnosticReporter pendingDiagnosticReporter;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(new DeclarationCheckersDiagnosticComponent(session, reporter, mppKind));
        listCreateListBuilder.add(new ExpressionCheckersDiagnosticComponent(session, reporter, mppKind));
        listCreateListBuilder.add(new TypeCheckersDiagnosticComponent(session, reporter, mppKind));
        listCreateListBuilder.add(new ControlFlowAnalysisDiagnosticComponent(session, reporter, mppKind));
        if (mppKind == MppCheckerKind.Common) {
            listCreateListBuilder.add(new ErrorNodeDiagnosticCollectorComponent(session, reporter));
            firSession = session;
            pendingDiagnosticReporter = reporter;
            listCreateListBuilder.add(new LanguageVersionSettingsDiagnosticComponent(firSession, pendingDiagnosticReporter, null, 4, null));
        } else {
            firSession = session;
            pendingDiagnosticReporter = reporter;
        }
        return new DiagnosticCollectorComponents((AbstractDiagnosticCollectorComponent[]) CollectionsKt.build(listCreateListBuilder).toArray(new AbstractDiagnosticCollectorComponent[0]), new ReportCommitterDiagnosticComponent(firSession, pendingDiagnosticReporter));
    }

    public final CliDiagnosticsCollector create(final FirSession session, ScopeSession scopeSession, final MppCheckerKind mppKind) {
        session.getClass();
        scopeSession.getClass();
        mppKind.getClass();
        return new CliDiagnosticsCollector(session, scopeSession, new Function1() { // from class: at3
            public final Object invoke(Object obj) {
                return DiagnosticComponentsFactory.a(session, mppKind, (PendingDiagnosticReporter) obj);
            }
        });
    }
}
