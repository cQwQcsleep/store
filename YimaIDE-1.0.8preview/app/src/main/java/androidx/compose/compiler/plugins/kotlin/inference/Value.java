package androidx.compose.compiler.plugins.kotlin.inference;

import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014¨\u0006\u0019"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Value;", "", "token", "", "observers", "", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "<init>", "(Ljava/lang/String;Ljava/util/Set;)V", "getToken", "()Ljava/lang/String;", "setToken", "(Ljava/lang/String;)V", "getObservers", "()Ljava/util/Set;", "setObservers", "(Ljava/util/Set;)V", "size", "", "getSize", "()I", "setSize", "(I)V", "index", "getIndex", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Value {
    private final int index;
    private Set<Bindings> observers;
    private int size;
    private String token;

    public Value(String str, Set<Bindings> set) {
        set.getClass();
        this.token = str;
        this.observers = set;
        this.size = 1;
        int i = BindingsKt.valueIndex;
        BindingsKt.valueIndex = i + 1;
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Set<Bindings> getObservers() {
        return this.observers;
    }

    public final int getSize() {
        return this.size;
    }

    public final String getToken() {
        return this.token;
    }

    public final void setObservers(Set<Bindings> set) {
        set.getClass();
        this.observers = set;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public final void setToken(String str) {
        this.token = str;
    }
}
