package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.fir.FirElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0017\u001a\u00020\u0014H\u0096\u0080\u0004J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0001\u0002\u001b\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "getElement", "()Lorg/jetbrains/kotlin/fir/FirElement;", "kind", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "getKind", "()Landroidx/compose/compiler/plugins/kotlin/inference/NodeKind;", "type", "Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "getType", "()Landroidx/compose/compiler/plugins/kotlin/k2/InferenceNodeType;", "referenceContainer", "getReferenceContainer", "()Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "parameterIndex", "", "getParameterIndex", "()I", "hashCode", "equals", "", "other", "Landroidx/compose/compiler/plugins/kotlin/k2/FirElementInferenceNode;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirFunctionInferenceNode;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class FirInferenceNode {
    private final FirElement element;

    private FirInferenceNode(FirElement firElement) {
        this.element = firElement;
    }

    public boolean equals(Object other) {
        return (other instanceof FirInferenceNode) && Intrinsics.areEqual(((FirInferenceNode) other).element, this.element);
    }

    public final FirElement getElement() {
        return this.element;
    }

    public NodeKind getKind() {
        return NodeKind.Expression;
    }

    public int getParameterIndex() {
        return -1;
    }

    public FirInferenceNode getReferenceContainer() {
        return null;
    }

    public abstract InferenceNodeType getType();

    public int hashCode() {
        return this.element.hashCode() * 31;
    }

    public /* synthetic */ FirInferenceNode(FirElement firElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(firElement);
    }
}
