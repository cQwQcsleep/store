package androidx.compose.compiler.plugins.kotlin.inference;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u0010¢\u0006\u0002\b\u0012J\n\u0010\u0013\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\u0015\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0010¢\u0006\u0002\b\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Token;", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "isAnonymous", "", "isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "()Z", "toBinding", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "bindings", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "context", "", "toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "toString", "equals", "other", "", "hashCode", "", "serializeTo", "", "writer", "Landroidx/compose/compiler/plugins/kotlin/inference/SchemeStringSerializationWriter;", "serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Token extends Item {
    private final String value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Token(String str) {
        super(null);
        str.getClass();
        this.value = str;
    }

    public boolean equals(Object other) {
        return (other instanceof Token) && Intrinsics.areEqual(((Token) other).value, this.value);
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() * 31;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    public boolean isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin() {
        return false;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    public void serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(SchemeStringSerializationWriter writer) {
        writer.getClass();
        writer.writeToken(this.value);
    }

    @Override // androidx.compose.compiler.plugins.kotlin.inference.Item
    public Binding toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(Bindings bindings, List<Binding> context) {
        bindings.getClass();
        context.getClass();
        return bindings.closed(this.value);
    }

    public String toString() {
        return this.value;
    }
}
