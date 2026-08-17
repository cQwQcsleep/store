package org.jetbrains.kotlin.fir.analysis.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bJ\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\bJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/AbstractDiagnosticCollector;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "createComponents", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lkotlin/jvm/functions/Function1;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getCreateComponents", "()Lkotlin/jvm/functions/Function1;", "collectDiagnosticsInSettings", Argument.Delimiters.none, "reporter", "collectDiagnostics", "firDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "createVisitor", "Lorg/jetbrains/kotlin/fir/analysis/collectors/CheckerRunningDiagnosticCollectorVisitor;", "components", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractDiagnosticCollector implements SessionAndScopeSessionHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SUPPRESS_ALL_ERRORS = "errors";
    public static final String SUPPRESS_ALL_INFOS = "infos";
    public static final String SUPPRESS_ALL_WARNINGS = "warnings";
    private final Function1<PendingDiagnosticReporter, DiagnosticCollectorComponents> createComponents;
    private final ScopeSession scopeSession;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractDiagnosticCollector(FirSession firSession, ScopeSession scopeSession, Function1<? super PendingDiagnosticReporter, DiagnosticCollectorComponents> function1) {
        firSession.getClass();
        scopeSession.getClass();
        function1.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.createComponents = function1;
    }

    public final void collectDiagnostics(FirDeclaration firDeclaration, PendingDiagnosticReporter reporter) {
        firDeclaration.getClass();
        reporter.getClass();
        CheckerRunningDiagnosticCollectorVisitor checkerRunningDiagnosticCollectorVisitorCreateVisitor = createVisitor((DiagnosticCollectorComponents) this.createComponents.invoke(reporter));
        FirLazyDeclarationResolver lazyDeclarationResolver = FirLazyDeclarationResolverKt.getLazyDeclarationResolver(getSession());
        Boolean bool = lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().get();
        lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().set(Boolean.FALSE);
        try {
            firDeclaration.accept(checkerRunningDiagnosticCollectorVisitorCreateVisitor, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().set(bool);
        }
    }

    public final void collectDiagnosticsInSettings(PendingDiagnosticReporter reporter) {
        reporter.getClass();
        createVisitor((DiagnosticCollectorComponents) this.createComponents.invoke(reporter)).checkSettings();
    }

    public abstract CheckerRunningDiagnosticCollectorVisitor createVisitor(DiagnosticCollectorComponents components);

    public final Function1<PendingDiagnosticReporter, DiagnosticCollectorComponents> getCreateComponents() {
        return this.createComponents;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/AbstractDiagnosticCollector$Companion;", Argument.Delimiters.none, "<init>", "()V", "SUPPRESS_ALL_INFOS", Argument.Delimiters.none, "SUPPRESS_ALL_WARNINGS", "SUPPRESS_ALL_ERRORS", "correctDiagnosticCase", "diagnostic", "getDiagnosticsSuppressedForContainer", Argument.Delimiters.none, "annotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String correctDiagnosticCase(String diagnostic) {
            int iHashCode = diagnostic.hashCode();
            if (iHashCode == -1294635157 ? diagnostic.equals(AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS) : !(iHashCode == 100348293 ? !diagnostic.equals(AbstractDiagnosticCollector.SUPPRESS_ALL_INFOS) : !(iHashCode == 498091095 && diagnostic.equals(AbstractDiagnosticCollector.SUPPRESS_ALL_WARNINGS)))) {
                return diagnostic;
            }
            String upperCase = diagnostic.toUpperCase(Locale.ROOT);
            upperCase.getClass();
            return upperCase;
        }

        public final List<String> getDiagnosticsSuppressedForContainer(FirAnnotationContainer annotationContainer) {
            FirExpression firExpressionFindArgumentByName$default;
            List<FirExpression> listUnwrapVarargValue;
            annotationContainer.getClass();
            ArrayList arrayList = null;
            for (FirAnnotation firAnnotation : annotationContainer.getAnnotations()) {
                ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
                ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
                if (coneClassLikeType != null && Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds$Annotations.INSTANCE.getSuppress()) && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getSuppressNames(), false, 2, null)) != null && (listUnwrapVarargValue = FirAnnotationUtilsKt.unwrapVarargValue(firExpressionFindArgumentByName$default)) != null) {
                    for (FirExpression firExpression : listUnwrapVarargValue) {
                        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
                        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
                        String str = value instanceof String ? (String) value : null;
                        if (str != null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(correctDiagnosticCase(str));
                        }
                    }
                }
            }
            return arrayList;
        }

        private Companion() {
        }
    }

    public /* synthetic */ AbstractDiagnosticCollector(FirSession firSession, ScopeSession scopeSession, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, (i & 2) != 0 ? new ScopeSession() : scopeSession, function1);
    }
}
