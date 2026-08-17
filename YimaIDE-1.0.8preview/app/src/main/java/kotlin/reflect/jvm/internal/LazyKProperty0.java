package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty0;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0010\b\u0001\u0010\u0002 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0011\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0012J\f\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0080\u0004J\u000f\u0010\u0015\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0012R\u0015\u0010\u0005\u001a\u00020\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKProperty0;", "V", "D", "Lkotlin/reflect/KProperty0;", "Lkotlin/reflect/jvm/internal/LazyKProperty;", "name", HttpUrl.FRAGMENT_ENCODE_SET, "computeProperty", "Lkotlin/Function0;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getName", "()Ljava/lang/String;", "getter", "Lkotlin/reflect/KProperty0$Getter;", "getGetter", "()Lkotlin/reflect/KProperty0$Getter;", "get", "()Ljava/lang/Object;", "getDelegate", HttpUrl.FRAGMENT_ENCODE_SET, "invoke", "kotlin-reflection"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class LazyKProperty0<V, D extends KProperty0<? extends V>> extends LazyKProperty<V, D> implements KProperty0<V> {
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyKProperty0(String str, Function0<? extends D> function0) {
        super(function0);
        str.getClass();
        function0.getClass();
        this.name = str;
    }

    public V get() {
        return (V) getDelegate().get();
    }

    public Object getDelegate() {
        return getDelegate().getDelegate();
    }

    public KProperty0.Getter<V> getGetter() {
        return getDelegate().getGetter();
    }

    public String getName() {
        return this.name;
    }

    public V invoke() {
        return (V) getDelegate().invoke();
    }
}
