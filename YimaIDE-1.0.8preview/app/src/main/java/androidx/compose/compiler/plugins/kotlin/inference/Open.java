package androidx.compose.compiler.plugins.kotlin.inference;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013H\u0010¢\u0006\u0002\b\u0014J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\u0014\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0082\u0004J\n\u0010\u001a\u001a\u00020\u0003H\u0096\u0080\u0004J\u0015\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0010¢\u0006\u0002\b\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006 "}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Open;", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "index", "", "isUnspecified", "", "<init>", "(IZ)V", "getIndex", "()I", "isUnspecified$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "()Z", "isAnonymous", "isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "toBinding", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "bindings", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "context", "", "toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "toString", "", "equals", "other", "", "hashCode", "serializeTo", "", "writer", "Landroidx/compose/compiler/plugins/kotlin/inference/SchemeStringSerializationWriter;", "serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Open extends Item {
    private final int index;
    private final boolean isUnspecified;

    public Open(int i, boolean z) {
        super(null);
        this.index = i;
        this.isUnspecified = z;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Open)) {
            return false;
        }
        int i = ((Open) other).index;
        int i2 = this.index;
        if (i != i2) {
            return i < 0 && i2 < 0;
        }
        return true;
    }

    public final int getIndex() {
        return this.index;
    }

    public int hashCode() {
        int i = this.index;
        if (i < 0) {
            return -31;
        }
        return i * 31;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    public boolean isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin() {
        return this.index < 0;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    /* JADX INFO: renamed from: isUnspecified$org_jetbrains_kotlin_kotlin_compose_compiler_plugin, reason: from getter */
    public boolean getIsUnspecified() {
        return this.isUnspecified;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    public void serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(SchemeStringSerializationWriter writer) {
        writer.getClass();
        writer.writeNumber(this.index);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    public Binding toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(Bindings bindings, List<Binding> context) {
        bindings.getClass();
        context.getClass();
        if (this.index < 0) {
            return bindings.open();
        }
        while (this.index >= context.size()) {
            context.add(bindings.open());
        }
        return context.get(this.index);
    }

    public String toString() {
        int i = this.index;
        return i < 0 ? "_" : String.valueOf(i);
    }

    public /* synthetic */ Open(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z);
    }
}
