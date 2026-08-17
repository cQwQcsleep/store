package androidx.compose.compiler.plugins.kotlin.inference;

import androidx.compose.compiler.plugins.kotlin.inference.Bindings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tJ\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0002J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\fH\u0002J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u001a\u001a\u00020\u0007H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "", "<init>", "()V", "listeners", "", "Lkotlin/Function0;", "", "open", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "closed", "target", "", "onChange", "callback", "unify", "", "a", "b", "unifyValues", "value", "Landroidx/compose/compiler/plugins/kotlin/inference/Value;", "bind", "binding", "token", "bindingValueChanged", "changed", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Bindings {
    private final List<Function0<Unit>> listeners = new ArrayList();

    public static Unit a(Bindings bindings, Function0 function0) {
        bindings.listeners.remove(function0);
        return Unit.INSTANCE;
    }

    private final boolean bind(Binding a, Binding b) {
        Value value = a.getValue();
        Value value2 = b.getValue();
        if (Intrinsics.areEqual(value, value2)) {
            return true;
        }
        int size = value.getSize();
        int size2 = value2.getSize();
        Set<Bindings> setPlus = SetsKt.plus(value.getObservers(), value2.getObservers());
        if (size > size2) {
            value.setSize(value.getSize() + size2);
            value.setObservers(setPlus);
            unifyValues(b, value);
        } else {
            value2.setSize(value2.getSize() + size);
            value2.setObservers(setPlus);
            unifyValues(a, value2);
        }
        Binding next = a.getNext();
        a.setNext(b.getNext());
        b.setNext(next);
        bindingValueChanged(a.getValue());
        return true;
    }

    private final void bindingValueChanged(Value value) {
        Iterator<Bindings> it = value.getObservers().iterator();
        while (it.hasNext()) {
            it.next().changed();
        }
    }

    private final void changed() {
        if (this.listeners.isEmpty()) {
            return;
        }
        Iterator it = CollectionsKt.toMutableList(this.listeners).iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }

    private final void unifyValues(Binding b, Value value) {
        b.setValue(value);
        for (Binding next = b.getNext(); !Intrinsics.areEqual(next, b); next = next.getNext()) {
            next.setValue(value);
        }
    }

    public final Binding closed(String target) {
        target.getClass();
        return new Binding(target, SetsKt.emptySet());
    }

    public final Function0<Unit> onChange(final Function0<Unit> callback) {
        callback.getClass();
        this.listeners.add(callback);
        return new Function0() { // from class: mv0
            public final Object invoke() {
                return Bindings.a(this.b, callback);
            }
        };
    }

    public final Binding open() {
        return new Binding(null, SetsKt.setOf(this), 1, null);
    }

    public final boolean unify(Binding a, Binding b) {
        a.getClass();
        b.getClass();
        String token = a.getValue().getToken();
        String token2 = b.getValue().getToken();
        if (token != null && token2 == null) {
            return bind(b, token);
        }
        if (token != null || token2 == null) {
            return (token == null || token2 == null) ? bind(a, b) : Intrinsics.areEqual(token, token2);
        }
        return bind(a, token2);
    }

    private final boolean bind(Binding binding, String token) {
        Value value = binding.getValue();
        value.setToken(token);
        bindingValueChanged(value);
        value.setObservers(SetsKt.emptySet());
        return true;
    }
}
