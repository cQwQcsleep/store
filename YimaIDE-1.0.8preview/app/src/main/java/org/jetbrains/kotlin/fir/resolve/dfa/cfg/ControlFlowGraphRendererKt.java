package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImpl;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a \u0010\b\u001a\u00020\u0001*\u00020\t2\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\f\u001a\u00020\u000b*\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\r"}, d2 = {"renderTo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "options", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderOptions;", "renderControlFlowGraphTo", "Lorg/jetbrains/kotlin/fir/FirElement;", "render", Argument.Delimiters.none, "renderControlFlowGraph", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraphRendererKt {
    public static final String render(ControlFlowGraph controlFlowGraph, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions) {
        controlFlowGraph.getClass();
        controlFlowGraphRenderOptions.getClass();
        StringBuilder sb = new StringBuilder();
        renderTo(controlFlowGraph, sb, controlFlowGraphRenderOptions);
        return sb.toString();
    }

    public static /* synthetic */ String render$default(ControlFlowGraph controlFlowGraph, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            controlFlowGraphRenderOptions = new ControlFlowGraphRenderOptions(false, false, null, 7, null);
        }
        return render(controlFlowGraph, controlFlowGraphRenderOptions);
    }

    public static final String renderControlFlowGraph(FirElement firElement, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions) {
        firElement.getClass();
        controlFlowGraphRenderOptions.getClass();
        StringBuilder sb = new StringBuilder();
        renderControlFlowGraphTo(firElement, sb, controlFlowGraphRenderOptions);
        return sb.toString();
    }

    public static /* synthetic */ String renderControlFlowGraph$default(FirElement firElement, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            controlFlowGraphRenderOptions = new ControlFlowGraphRenderOptions(false, false, null, 7, null);
        }
        return renderControlFlowGraph(firElement, controlFlowGraphRenderOptions);
    }

    public static final void renderControlFlowGraphTo(final FirElement firElement, StringBuilder sb, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions) {
        String name;
        firElement.getClass();
        sb.getClass();
        controlFlowGraphRenderOptions.getClass();
        FirFile firFile = firElement instanceof FirFile ? (FirFile) firElement : null;
        if (firFile == null || (name = firFile.getName()) == null) {
            name = Argument.Delimiters.none;
        }
        final ControlFlowGraphRenderer controlFlowGraphRenderer = new ControlFlowGraphRenderer(sb, controlFlowGraphRenderOptions);
        controlFlowGraphRenderer.renderCompleteGraph(name, new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.e
            public final Object invoke() {
                return ControlFlowGraphRendererKt.renderControlFlowGraphTo$lambda$0$0(firElement, controlFlowGraphRenderer);
            }
        });
    }

    public static /* synthetic */ void renderControlFlowGraphTo$default(FirElement firElement, StringBuilder sb, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions, int i, Object obj) {
        if ((i & 2) != 0) {
            controlFlowGraphRenderOptions = new ControlFlowGraphRenderOptions(false, false, null, 7, null);
        }
        renderControlFlowGraphTo(firElement, sb, controlFlowGraphRenderOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderControlFlowGraphTo$lambda$0$0(FirElement firElement, final ControlFlowGraphRenderer controlFlowGraphRenderer) {
        firElement.accept(new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphRendererKt$renderControlFlowGraphTo$1$1$1
            public void visitControlFlowGraphReference(FirControlFlowGraphReference controlFlowGraphReference) {
                ControlFlowGraph controlFlowGraph;
                controlFlowGraphReference.getClass();
                FirControlFlowGraphReferenceImpl firControlFlowGraphReferenceImpl = controlFlowGraphReference instanceof FirControlFlowGraphReferenceImpl ? (FirControlFlowGraphReferenceImpl) controlFlowGraphReference : null;
                if (firControlFlowGraphReferenceImpl == null || (controlFlowGraph = firControlFlowGraphReferenceImpl.getControlFlowGraph()) == null || controlFlowGraph.isSubGraph()) {
                    return;
                }
                controlFlowGraphRenderer.renderPartialGraph(controlFlowGraph);
            }

            public void visitElement(FirElement element) {
                element.getClass();
                element.acceptChildren(this);
            }
        });
        return Unit.INSTANCE;
    }

    public static final void renderTo(final ControlFlowGraph controlFlowGraph, StringBuilder sb, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions) {
        controlFlowGraph.getClass();
        sb.getClass();
        controlFlowGraphRenderOptions.getClass();
        final ControlFlowGraphRenderer controlFlowGraphRenderer = new ControlFlowGraphRenderer(sb, controlFlowGraphRenderOptions);
        controlFlowGraphRenderer.renderCompleteGraph(controlFlowGraph.getName(), new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.f
            public final Object invoke() {
                return ControlFlowGraphRendererKt.renderTo$lambda$0$0(controlFlowGraphRenderer, controlFlowGraph);
            }
        });
    }

    public static /* synthetic */ void renderTo$default(ControlFlowGraph controlFlowGraph, StringBuilder sb, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions, int i, Object obj) {
        if ((i & 2) != 0) {
            controlFlowGraphRenderOptions = new ControlFlowGraphRenderOptions(false, false, null, 7, null);
        }
        renderTo(controlFlowGraph, sb, controlFlowGraphRenderOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderTo$lambda$0$0(ControlFlowGraphRenderer controlFlowGraphRenderer, ControlFlowGraph controlFlowGraph) {
        controlFlowGraphRenderer.renderPartialGraph(controlFlowGraph);
        return Unit.INSTANCE;
    }
}
