package androidx.compose.compiler.plugins.kotlin.inference;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B!\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0016\u001a\u00020\u0003H\u0096\u0080\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "", "token", "", "observers", "", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "<init>", "(Ljava/lang/String;Ljava/util/Set;)V", "getToken", "()Ljava/lang/String;", "value", "Landroidx/compose/compiler/plugins/kotlin/inference/Value;", "getValue", "()Landroidx/compose/compiler/plugins/kotlin/inference/Value;", "setValue", "(Landroidx/compose/compiler/plugins/kotlin/inference/Value;)V", "next", "getNext", "()Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "setNext", "(Landroidx/compose/compiler/plugins/kotlin/inference/Binding;)V", "toString", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Binding {
    private Binding next;
    private Value value;

    public Binding(String str, Set<Bindings> set) {
        set.getClass();
        this.value = new Value(str, set);
        this.next = this;
    }

    public final Binding getNext() {
        return this.next;
    }

    public final String getToken() {
        return this.value.getToken();
    }

    public final Value getValue() {
        return this.value;
    }

    public final void setNext(Binding binding) {
        binding.getClass();
        this.next = binding;
    }

    public final void setValue(Value value) {
        value.getClass();
        this.value = value;
    }

    public String toString() {
        String token = this.value.getToken();
        if (token != null) {
            return "Binding(token = " + token + ')';
        }
        return "Binding(" + this.value.getIndex() + ')';
    }

    public /* synthetic */ Binding(String str, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, set);
    }
}
