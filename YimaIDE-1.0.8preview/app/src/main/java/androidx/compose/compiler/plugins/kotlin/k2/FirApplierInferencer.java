package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.inference.ApplierInferencer;
import androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter;
import androidx.compose.compiler.plugins.kotlin.inference.LazyScheme;
import androidx.compose.compiler.plugins.kotlin.inference.LazySchemeStorage;
import androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter;
import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirApplierInferencer;", "", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "infer", "Landroidx/compose/compiler/plugins/kotlin/inference/ApplierInferencer;", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "visitCall", "", "call", "target", "arguments", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirApplierInferencer {
    private final CheckerContext context;
    private final ApplierInferencer<InferenceNodeType, FirInferenceNode> infer;
    private final DiagnosticReporter reporter;
    private final FirSession session;

    public FirApplierInferencer(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        this.context = checkerContext;
        this.reporter = diagnosticReporter;
        this.session = checkerContext.getSession();
        this.infer = new ApplierInferencer<>(new TypeAdapter<InferenceNodeType>() { // from class: androidx.compose.compiler.plugins.kotlin.k2.FirApplierInferencer$infer$1
            @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
            public Scheme declaredSchemaOf(InferenceNodeType type) {
                type.getClass();
                return type.toScheme(this.this$0.context);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
            public void updatedInferredScheme(InferenceNodeType type, Scheme scheme) {
                type.getClass();
                scheme.getClass();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.TypeAdapter
            public Scheme currentInferredSchemeOf(InferenceNodeType type) {
                type.getClass();
                return null;
            }
        }, new NodeAdapter<InferenceNodeType, FirInferenceNode>() { // from class: androidx.compose.compiler.plugins.kotlin.k2.FirApplierInferencer$infer$2
            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public FirInferenceNode containerOf(FirInferenceNode node) {
                node.getClass();
                FirSession session = this.this$0.context.getSession();
                FirApplierInferencer firApplierInferencer = this.this$0;
                for (FirElement parent = ComposableTargetCheckerKt.getParent(session, node.getElement()); parent != null; parent = ComposableTargetCheckerKt.getParent(session, parent)) {
                    if (parent instanceof FirFunction) {
                        return ComposableTargetCheckerKt.inferenceNodeOf(parent, firApplierInferencer.context);
                    }
                }
                return node;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public NodeKind kindOf(FirInferenceNode node) {
                node.getClass();
                return node.getKind();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public FirInferenceNode referencedContainerOf(FirInferenceNode node) {
                node.getClass();
                return node.getReferenceContainer();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public int schemeParameterIndexOf(FirInferenceNode node, FirInferenceNode container) {
                node.getClass();
                container.getClass();
                return node.getParameterIndex();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.NodeAdapter
            public InferenceNodeType typeOf(FirInferenceNode node) {
                node.getClass();
                return node.getType();
            }
        }, new LazySchemeStorage<FirInferenceNode>() { // from class: androidx.compose.compiler.plugins.kotlin.k2.FirApplierInferencer$infer$4
            @Override // androidx.compose.compiler.plugins.kotlin.inference.LazySchemeStorage
            public LazyScheme getLazyScheme(FirInferenceNode node) {
                node.getClass();
                return ComposableTargetCheckerKt.getComposableTargetSessionStorage(this.this$0.session).getLazyScheme(node.getElement());
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.LazySchemeStorage
            public void storeLazyScheme(FirInferenceNode node, LazyScheme value) {
                node.getClass();
                value.getClass();
                ComposableTargetCheckerKt.getComposableTargetSessionStorage(this.this$0.session).storeLazyScheme(node.getElement(), value);
            }
        }, new ErrorReporter<FirInferenceNode>() { // from class: androidx.compose.compiler.plugins.kotlin.k2.FirApplierInferencer$infer$3
            private final String descriptionFrom(String token) {
                FirSession firSession = this.this$0.session;
                FirClassLikeSymbol classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(ClassId.Companion.fromString$default(ClassId.Companion, StringsKt.replace$default(token, '.', '/', false, 4, (Object) null), false, 2, (Object) null));
                Object objAnnotationArgument = classLikeSymbolByClassId != null ? ComposableTargetCheckerKt.annotationArgument(firSession, (FirBasedSymbol<?>) classLikeSymbolByClassId, ComposeClassIds.INSTANCE.getComposableTargetMarker(), ComposeFqNames.INSTANCE.getComposableTargetMarkerDescriptionName()) : null;
                String str = objAnnotationArgument instanceof String ? (String) objAnnotationArgument : null;
                return str == null ? token : str;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter
            public void reportCallError(FirInferenceNode node, String expected, String received) {
                node.getClass();
                expected.getClass();
                received.getClass();
                if (Intrinsics.areEqual(expected, received)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default(this.this$0.context, this.this$0.reporter, node.getElement().getSource(), ComposeErrors.INSTANCE.getCOMPOSE_APPLIER_CALL_MISMATCH(), descriptionFrom(expected), descriptionFrom(received), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter
            public void reportParameterError(FirInferenceNode node, int index, String expected, String received) {
                node.getClass();
                expected.getClass();
                received.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default(this.this$0.context, this.this$0.reporter, node.getElement().getSource(), ComposeErrors.INSTANCE.getCOMPOSE_APPLIER_PARAMETER_MISMATCH(), expected, received, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.inference.ErrorReporter
            public void log(FirInferenceNode node, String message) {
                message.getClass();
            }
        });
    }

    public final void visitCall(FirInferenceNode call, FirInferenceNode target, List<? extends FirInferenceNode> arguments) {
        call.getClass();
        target.getClass();
        arguments.getClass();
        this.infer.visitCall(call, target, arguments);
    }
}
