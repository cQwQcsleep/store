package androidx.compose.foundation.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B9\u0012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\f\u0010\u0010\u001a\u00020\u0006*\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002R\u001f\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/layout/SystemInsetsPaddingModifierElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/SystemInsetsPaddingModifierNode;", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "insetsGetter", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "Landroidx/compose/foundation/layout/WindowInsets;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "create", "update", "node", "inspectableProperties", "hashCode", "", "equals", "", "other", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SystemInsetsPaddingModifierElement extends ModifierNodeElement<SystemInsetsPaddingModifierNode> {
    private final Function1<WindowInsetsHolder, WindowInsets> insetsGetter;
    private final Function1<InspectorInfo, Unit> inspectorInfo;

    /* JADX WARN: Multi-variable type inference failed */
    public SystemInsetsPaddingModifierElement(Function1<? super InspectorInfo, Unit> function1, Function1<? super WindowInsetsHolder, ? extends WindowInsets> function2) {
        this.inspectorInfo = function1;
        this.insetsGetter = function2;
    }

    /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
    public SystemInsetsPaddingModifierNode m1002create() {
        return new SystemInsetsPaddingModifierNode(this.insetsGetter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SystemInsetsPaddingModifierElement) && this.insetsGetter == ((SystemInsetsPaddingModifierElement) other).insetsGetter;
    }

    public int hashCode() {
        return this.insetsGetter.hashCode();
    }

    public void inspectableProperties(InspectorInfo inspectorInfo) {
        this.inspectorInfo.invoke(inspectorInfo);
    }

    public void update(SystemInsetsPaddingModifierNode node) {
        node.update(this.insetsGetter);
    }
}
