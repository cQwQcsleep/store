package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableCollection;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.cfa.FirPropertyInitializationAnalyzerKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.CfgTraverseDirection;
import org.jetbrains.kotlin.fir.analysis.cfa.util.CfgTraverserKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor;
import org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CapturedByValue;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.QualifiedAccessNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableDeclarationExitNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0012\u0013\u0014\u0015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fR\u0018\u0010\r\u001a\u00020\u000e*\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnusedVariableAssignmentChecker;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/AbstractFirPropertyInitializationChecker;", "<init>", "()V", "analyze", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "data", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;)V", "ignoreWarnings", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getIgnoreWarnings", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "AddAllWrites", "FindCapturedWrites", "FindVisibleWrites", "RemoveVisibleWrites", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnusedVariableAssignmentChecker extends AbstractFirPropertyInitializationChecker {
    public static final UnusedVariableAssignmentChecker INSTANCE = new UnusedVariableAssignmentChecker();

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnusedVariableAssignmentChecker$AddAllWrites;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitorVoid;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableAssignmentData;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableAssignmentData;)V", "visitNode", Argument.Delimiters.none, "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AddAllWrites extends ControlFlowGraphVisitorVoid {
        private final VariableAssignmentData data;

        public AddAllWrites(VariableAssignmentData variableAssignmentData) {
            variableAssignmentData.getClass();
            this.data = variableAssignmentData;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitNode(CFGNode<?> node) {
            node.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitVariableAssignmentNode(VariableAssignmentNode node) {
            FirPropertySymbol resolvedPropertySymbol$default;
            node.getClass();
            FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(node.getFir());
            if (calleeReference == null || (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null)) == null || resolvedPropertySymbol$default.getName().isSpecial() || !this.data.getLocalProperties().contains(resolvedPropertySymbol$default)) {
                return;
            }
            this.data.getUnreadWrites().add(node.getFir());
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitVariableDeclarationExitNode(VariableDeclarationExitNode node) {
            node.getClass();
            if (node.getFir().getName().isSpecial()) {
                return;
            }
            ControlFlowGraph controlFlowGraphNearestNonInPlaceGraph = FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(node.getOwner());
            if (node.getFir().getInitializer() != null) {
                this.data.getUnreadWrites().add(node.getFir());
            }
            this.data.getVariableScopes().put(node.getFir().getSymbol(), controlFlowGraphNearestNonInPlaceGraph);
            this.data.getVariablesWithoutReads().put(node.getFir().getSymbol(), node.getFir());
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJx\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b2\"\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b2\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J \u0001\u0010\u0010\u001a:\u0012\u0004\u0012\u00020\u0011\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000f2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0015\u001a\u00020\u00162>\u0010\u0017\u001a:\u0012\u0004\u0012\u00020\u0011\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0012H\u0016J\u0088\u0001\u0010\u0018\u001a:\u0012\u0004\u0012\u00020\u0011\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00122\u0006\u0010\u000e\u001a\u00020\u00192>\u0010\u0017\u001a:\u0012\u0004\u0012\u00020\u0011\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0012H\u0016J\u0088\u0001\u0010\u001a\u001a:\u0012\u0004\u0012\u00020\u0011\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00122\u0006\u0010\u000e\u001a\u00020\u001b2>\u0010\u0017\u001a:\u0012\u0004\u0012\u00020\u0011\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0012H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnusedVariableAssignmentChecker$FindCapturedWrites;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowGraphVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/PropertyAccessType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableWriteData;", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "<init>", "(Ljava/util/Set;)V", "mergeInfo", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "a", "b", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitEdge", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "from", "to", "metadata", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "data", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FindCapturedWrites extends PathAwareControlFlowGraphVisitor<PropertyAccessType, VariableWriteData> {
        private final Set<FirPropertySymbol> properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public FindCapturedWrites(Set<? extends FirPropertySymbol> set) {
            super(CfgTraverseDirection.Backward);
            set.getClass();
            this.properties = set;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
        public PersistentMap<PropertyAccessType, VariableWriteData> mergeInfo(PersistentMap<PropertyAccessType, ? extends VariableWriteData> a, PersistentMap<PropertyAccessType, ? extends VariableWriteData> b, CFGNode<?> node) {
            a.getClass();
            b.getClass();
            node.getClass();
            PersistentMap.Builder builder = a.builder();
            for (Map.Entry entry : b.entrySet()) {
                Object key = entry.getKey();
                Object key2 = entry.getKey();
                Object value = entry.getValue();
                Object obj = a.get(key2);
                if (obj != null) {
                    value = VariableWriteData.m232boximpl(VariableWriteData.m241plusJgvPeUI(((VariableWriteData) obj).getValue(), ((VariableWriteData) value).getValue()));
                }
                builder.put(key, value);
            }
            return builder.build();
        }

        @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> visitEdge(CFGNode<?> from, CFGNode<?> to, Edge metadata, PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, ? extends VariableWriteData>> data) {
            from.getClass();
            to.getClass();
            metadata.getClass();
            data.getClass();
            if (Intrinsics.areEqual(metadata.getLabel(), CapturedByValue.INSTANCE)) {
                return ExtensionsKt.persistentMapOf();
            }
            return from instanceof VariableDeclarationExitNode ? super.visitEdge(from, to, metadata, UnusedVariableAssignmentCheckerKt.remove(data, ((VariableDeclarationExitNode) from).getFir().getSymbol())) : super.visitEdge(from, to, metadata, data);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> visitVariableAssignmentNode(VariableAssignmentNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> data) {
            node.getClass();
            data.getClass();
            FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(node.getFir());
            if (calleeReference != null) {
                FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null);
                if (resolvedPropertySymbol$default != null) {
                    FirPropertySymbol firPropertySymbol = this.properties.contains(resolvedPropertySymbol$default) ? resolvedPropertySymbol$default : null;
                    if (firPropertySymbol != null && !firPropertySymbol.getName().isSpecial()) {
                        return UnusedVariableAssignmentCheckerKt.add(data, PropertyAccessType.Captured, firPropertySymbol, node);
                    }
                }
            }
            return data;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> visitVariableDeclarationExitNode(VariableDeclarationExitNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> data) {
            node.getClass();
            data.getClass();
            return (node.getFir().getName().isSpecial() || node.getFir().getInitializer() == null) ? data : UnusedVariableAssignmentCheckerKt.add(data, PropertyAccessType.Captured, node.getFir().getSymbol(), node);
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001Be\u0012N\u0010\u0004\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012<\u0012:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000e\u0010\u000fJx\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t2\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t2\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0016J \u0001\u0010\u0014\u001a:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\n2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00062\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0017\u001a\u00020\u00182>\u0010\u0019\u001a:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\nH\u0016J\u0088\u0001\u0010\u001a\u001a:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\n2\u0006\u0010\u0013\u001a\u00020\u001b2>\u0010\u0019\u001a:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\nH\u0016J\u0088\u0001\u0010\u001c\u001a:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\n2\u0006\u0010\u0013\u001a\u00020\u001d2>\u0010\u0019\u001a:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\nH\u0016RV\u0010\u0004\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012<\u0012:\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t0\u0007j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnusedVariableAssignmentChecker$FindVisibleWrites;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowGraphVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/PropertyAccessType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableWriteData;", "futureWrites", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "<init>", "(Ljava/util/Map;Ljava/util/Set;)V", "mergeInfo", "a", "b", "node", "visitEdge", "from", "to", "metadata", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "data", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FindVisibleWrites extends PathAwareControlFlowGraphVisitor<PropertyAccessType, VariableWriteData> {
        private final Map<CFGNode<?>, PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>>> futureWrites;
        private final Set<FirPropertySymbol> properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public FindVisibleWrites(Map<CFGNode<?>, ? extends PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>>> map, Set<? extends FirPropertySymbol> set) {
            super(null, 1, null);
            map.getClass();
            set.getClass();
            this.futureWrites = map;
            this.properties = set;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
        public PersistentMap<PropertyAccessType, VariableWriteData> mergeInfo(PersistentMap<PropertyAccessType, ? extends VariableWriteData> a, PersistentMap<PropertyAccessType, ? extends VariableWriteData> b, CFGNode<?> node) {
            a.getClass();
            b.getClass();
            node.getClass();
            PersistentMap.Builder builder = a.builder();
            for (Map.Entry entry : b.entrySet()) {
                Object key = entry.getKey();
                Object key2 = entry.getKey();
                Object value = entry.getValue();
                Object obj = a.get(key2);
                if (obj != null) {
                    value = VariableWriteData.m232boximpl(VariableWriteData.m241plusJgvPeUI(((VariableWriteData) obj).getValue(), ((VariableWriteData) value).getValue()));
                }
                builder.put(key, value);
            }
            return builder.build();
        }

        @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> visitEdge(CFGNode<?> from, CFGNode<?> to, Edge metadata, PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, ? extends VariableWriteData>> data) {
            CFGNode<?> enterNode;
            PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> persistentMap;
            from.getClass();
            to.getClass();
            metadata.getClass();
            data.getClass();
            if (Intrinsics.areEqual(metadata.getLabel(), CapturedByValue.INSTANCE)) {
                return ExtensionsKt.persistentMapOf();
            }
            PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> persistentMapVisitEdge = super.visitEdge(from, to, metadata, data);
            ControlFlowGraph controlFlowGraphNearestNonInPlaceGraph = FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(from.getOwner());
            if (!Intrinsics.areEqual(controlFlowGraphNearestNonInPlaceGraph, FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(to.getOwner()))) {
                PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> persistentMap2 = this.futureWrites.get(from);
                if (persistentMap2 != null) {
                    PersistentMap persistentMapVisitEdge2 = super.visitEdge(from, to, metadata, persistentMap2);
                    PersistentMap.Builder builder = persistentMapVisitEdge.builder();
                    for (Map.Entry entry : persistentMapVisitEdge2.entrySet()) {
                        Object key = entry.getKey();
                        Object key2 = entry.getKey();
                        Object value = entry.getValue();
                        Object obj = persistentMapVisitEdge.get(key2);
                        if (obj != null) {
                            PersistentMap<PropertyAccessType, VariableWriteData> persistentMapMergeInfo = mergeInfo((PersistentMap) obj, (PersistentMap) value, to);
                            if (persistentMapMergeInfo != null) {
                                value = persistentMapMergeInfo;
                            }
                        }
                        builder.put(key, value);
                    }
                    return builder.build();
                }
            } else if (from instanceof CFGNodeWithSubgraphs) {
                for (ControlFlowGraph controlFlowGraph : ((CFGNodeWithSubgraphs) from).getSubGraphs()) {
                    if (!Intrinsics.areEqual(controlFlowGraphNearestNonInPlaceGraph, FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(controlFlowGraph)) && (persistentMap = this.futureWrites.get((enterNode = controlFlowGraph.getEnterNode()))) != null) {
                        PersistentMap persistentMapVisitEdge3 = super.visitEdge(from, enterNode, metadata, persistentMap);
                        PersistentMap.Builder builder2 = persistentMapVisitEdge.builder();
                        for (Map.Entry entry2 : persistentMapVisitEdge3.entrySet()) {
                            Object key3 = entry2.getKey();
                            Object key4 = entry2.getKey();
                            Object value2 = entry2.getValue();
                            Object obj2 = persistentMapVisitEdge.get(key4);
                            if (obj2 != null) {
                                PersistentMap<PropertyAccessType, VariableWriteData> persistentMapMergeInfo2 = mergeInfo((PersistentMap) obj2, (PersistentMap) value2, to);
                                if (persistentMapMergeInfo2 != null) {
                                    value2 = persistentMapMergeInfo2;
                                }
                            }
                            builder2.put(key3, value2);
                        }
                        persistentMapVisitEdge = builder2.build();
                    }
                }
            }
            return persistentMapVisitEdge;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> visitVariableAssignmentNode(VariableAssignmentNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> data) {
            node.getClass();
            data.getClass();
            FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(node.getFir());
            if (calleeReference != null) {
                FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null);
                if (resolvedPropertySymbol$default != null) {
                    FirPropertySymbol firPropertySymbol = this.properties.contains(resolvedPropertySymbol$default) ? resolvedPropertySymbol$default : null;
                    if (firPropertySymbol != null && !firPropertySymbol.getName().isSpecial()) {
                        return UnusedVariableAssignmentCheckerKt.overwrite(data, firPropertySymbol, ExtensionsKt.persistentSetOf(new VariableAssignmentNode[]{node}));
                    }
                }
            }
            return data;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> visitVariableDeclarationExitNode(VariableDeclarationExitNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> data) {
            node.getClass();
            data.getClass();
            if (node.getFir().getName().isSpecial()) {
                return data;
            }
            return UnusedVariableAssignmentCheckerKt.overwrite(UnusedVariableAssignmentCheckerKt.remove(data, node.getFir().getSymbol()), node.getFir().getSymbol(), node.getFir().getInitializer() != null ? ExtensionsKt.persistentSetOf(new VariableDeclarationExitNode[]{node}) : ExtensionsKt.persistentSetOf());
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0016J\u0014\u0010\u000e\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u001c\u0010\u000f\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnusedVariableAssignmentChecker$RemoveVisibleWrites;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitorVoid;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableAssignmentData;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableAssignmentData;)V", "visitNode", Argument.Delimiters.none, "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitQualifiedAccessNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "visitFunctionCallExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "visitAnnotations", "visitQualifiedAccess", "fir", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RemoveVisibleWrites extends ControlFlowGraphVisitorVoid {
        private final VariableAssignmentData data;

        public RemoveVisibleWrites(VariableAssignmentData variableAssignmentData) {
            variableAssignmentData.getClass();
            this.data = variableAssignmentData;
        }

        private final void visitAnnotations(final CFGNode<?> node) {
            List<FirAnnotation> annotations;
            FirElement fir = node.getFir();
            FirAnnotationContainer firAnnotationContainer = fir instanceof FirAnnotationContainer ? (FirAnnotationContainer) fir : null;
            if (firAnnotationContainer == null || (annotations = firAnnotationContainer.getAnnotations()) == null) {
                return;
            }
            Iterator<T> it = annotations.iterator();
            while (it.hasNext()) {
                ((FirAnnotation) it.next()).accept(new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.extra.UnusedVariableAssignmentChecker$RemoveVisibleWrites$visitAnnotations$1$1
                    public void visitElement(FirElement element) {
                        element.getClass();
                        if (element instanceof FirQualifiedAccessExpression) {
                            this.this$0.visitQualifiedAccess(node, (FirQualifiedAccessExpression) element);
                        }
                        element.acceptChildren(this);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void visitQualifiedAccess(CFGNode<?> node, FirQualifiedAccessExpression fir) {
            FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(fir.getCalleeReference(), false, 1, null);
            if (resolvedPropertySymbol$default == null || resolvedPropertySymbol$default.getName().isSpecial()) {
                return;
            }
            this.data.getVariablesWithoutReads().remove(resolvedPropertySymbol$default);
            PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> persistentMap = this.data.getWritesByNode().get(node);
            ImmutableCollection immutableCollectionValues = persistentMap != null ? persistentMap.values() : null;
            if (immutableCollectionValues == null) {
                immutableCollectionValues = CollectionsKt.emptyList();
            }
            Iterator it = immutableCollectionValues.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((PersistentMap) it.next()).values().iterator();
                while (it2.hasNext()) {
                    PersistentSet<CFGNode<?>> persistentSetM238getimpl = VariableWriteData.m238getimpl(((VariableWriteData) it2.next()).getValue(), resolvedPropertySymbol$default);
                    if (persistentSetM238getimpl != null) {
                        Iterator it3 = persistentSetM238getimpl.iterator();
                        while (it3.hasNext()) {
                            TypeIntrinsics.asMutableCollection(this.data.getUnreadWrites()).remove(((CFGNode) it3.next()).getFir());
                        }
                    }
                }
            }
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitFunctionCallExitNode(FunctionCallExitNode node) {
            FirExpression explicitReceiver;
            node.getClass();
            visitAnnotations(node);
            FirFunctionCall fir = node.getFir();
            FirImplicitInvokeCall firImplicitInvokeCall = fir instanceof FirImplicitInvokeCall ? (FirImplicitInvokeCall) fir : null;
            FirExpression firExpressionUnwrapSmartcastExpression = (firImplicitInvokeCall == null || (explicitReceiver = firImplicitInvokeCall.getExplicitReceiver()) == null) ? null : FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver);
            FirQualifiedAccessExpression firQualifiedAccessExpression = firExpressionUnwrapSmartcastExpression instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpressionUnwrapSmartcastExpression : null;
            if (firQualifiedAccessExpression != null) {
                visitQualifiedAccess(node, firQualifiedAccessExpression);
            }
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitNode(CFGNode<?> node) {
            node.getClass();
            visitAnnotations(node);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitQualifiedAccessNode(QualifiedAccessNode node) {
            node.getClass();
            visitAnnotations(node);
            visitQualifiedAccess(node, node.getFir());
        }
    }

    private UnusedVariableAssignmentChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean getIgnoreWarnings(FirPropertySymbol firPropertySymbol) {
        if (Intrinsics.areEqual(firPropertySymbol.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) || firPropertySymbol.getSource() == null) {
            return true;
        }
        KtSourceElement source = firPropertySymbol.getSource();
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredAugmentedAssign) {
            return true;
        }
        KtSourceElement source2 = firPropertySymbol.getSource();
        if (Intrinsics.areEqual(source2 != null ? source2.getElementType() : null, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
            return true;
        }
        KtSourceElement initializerSource = firPropertySymbol.getInitializerSource();
        return Intrinsics.areEqual(initializerSource != null ? initializerSource.getKind() : null, KtFakeSourceElementKind.DesugaredForLoop.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:82:0x0159 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00f4 A[SYNTHETIC] */
    @Override // org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker
    public void analyze(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData) {
        KtDiagnosticFactory0 unused_variable;
        KtDiagnosticFactory0 ktDiagnosticFactory0;
        FirPropertySymbol resolvedPropertySymbol$default;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableInitializationInfoData.getClass();
        Set<FirVariableSymbol<?>> properties = variableInitializationInfoData.getProperties();
        properties.getClass();
        VariableAssignmentData variableAssignmentData = new VariableAssignmentData(properties);
        variableInitializationInfoData.getGraph().traverse(new AddAllWrites(variableAssignmentData));
        if (!variableAssignmentData.getUnreadWrites().isEmpty()) {
            variableAssignmentData.setWritesByNode(CfgTraverserKt.traverseToFixedPoint(variableInitializationInfoData.getGraph(), new FindVisibleWrites(CfgTraverserKt.traverseToFixedPoint(variableInitializationInfoData.getGraph(), new FindCapturedWrites(properties)), properties)));
        }
        variableInitializationInfoData.getGraph().traverse(new RemoveVisibleWrites(variableAssignmentData));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<FirStatement> it = variableAssignmentData.getUnreadWrites().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FirStatement next = it.next();
            if (next instanceof FirVariableAssignment) {
                FirVariableAssignment firVariableAssignment = (FirVariableAssignment) next;
                FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
                if (calleeReference != null && (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null)) != null) {
                    linkedHashSet.add(resolvedPropertySymbol$default);
                    if (!DeclarationAttributesKt.isDelegatedProperty(resolvedPropertySymbol$default)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firVariableAssignment.getLValue().getSource(), FirErrors.INSTANCE.getASSIGNED_VALUE_IS_NEVER_READ(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            } else if (next instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) next;
                if (!variableAssignmentData.getVariablesWithoutReads().containsKey(firProperty.getSymbol()) && !getIgnoreWarnings(firProperty.getSymbol())) {
                    FirExpression initializer = firProperty.getInitializer();
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (initializer != null ? initializer.getSource() : null), FirErrors.INSTANCE.getVARIABLE_INITIALIZER_IS_REDUNDANT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
        for (Map.Entry<FirPropertySymbol, FirProperty> entry : variableAssignmentData.getVariablesWithoutReads().entrySet()) {
            FirPropertySymbol key = entry.getKey();
            FirProperty value = entry.getValue();
            if (!getIgnoreWarnings(key)) {
                FirExpression initializer2 = value.getInitializer();
                FirFunctionCall firFunctionCall = initializer2 instanceof FirFunctionCall ? (FirFunctionCall) initializer2 : null;
                if (firFunctionCall == null || !FirHelpersKt.isIterator(firFunctionCall)) {
                    if (Intrinsics.areEqual(ClassMembersKt.isCatchParameter(value), Boolean.TRUE)) {
                        continue;
                    } else {
                        if (linkedHashSet.contains(key)) {
                            boolean zIsDelegatedProperty = DeclarationAttributesKt.isDelegatedProperty(key);
                            if (!zIsDelegatedProperty) {
                                unused_variable = FirErrors.INSTANCE.getVARIABLE_NEVER_READ();
                            } else {
                                if (!zIsDelegatedProperty) {
                                    bu8.a();
                                    return;
                                }
                                ktDiagnosticFactory0 = null;
                            }
                            if (ktDiagnosticFactory0 != null) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) value.getSource(), ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            }
                        } else {
                            unused_variable = FirErrors.INSTANCE.getUNUSED_VARIABLE();
                        }
                        ktDiagnosticFactory0 = unused_variable;
                        if (ktDiagnosticFactory0 != null) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) value.getSource(), ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }
                }
            }
        }
    }
}
