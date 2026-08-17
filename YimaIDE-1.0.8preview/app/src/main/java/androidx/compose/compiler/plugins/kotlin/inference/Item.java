package androidx.compose.compiler.plugins.kotlin.inference;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fH ¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H ¢\u0006\u0002\b\u0015R\u0012\u0010\u0004\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\u0082\u0001\u0002\u0016\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "", "<init>", "()V", "isAnonymous", "", "isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "()Z", "isUnspecified", "isUnspecified$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "toBinding", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "bindings", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "context", "", "toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "serializeTo", "", "writer", "Landroidx/compose/compiler/plugins/kotlin/inference/SchemeStringSerializationWriter;", "serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "Landroidx/compose/compiler/plugins/kotlin/inference/Open;", "Landroidx/compose/compiler/plugins/kotlin/inference/Token;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class Item {
    public /* synthetic */ Item(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin();

    /* JADX INFO: renamed from: isUnspecified$org_jetbrains_kotlin_kotlin_compose_compiler_plugin */
    public boolean getIsUnspecified() {
        return false;
    }

    public abstract void serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(SchemeStringSerializationWriter writer);

    public abstract Binding toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(Bindings bindings, List<Binding> context);

    private Item() {
    }
}
