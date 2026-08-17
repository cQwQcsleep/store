package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty1;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0016\b\u0002\u0010\u0003 \u0001*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u001d\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0014J\u0017\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0014R\u0015\u0010\u0006\u001a\u00020\u0007X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR!\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKProperty1;", "T", "V", "D", "Lkotlin/reflect/KProperty1;", "Lkotlin/reflect/jvm/internal/LazyKProperty;", "name", HttpUrl.FRAGMENT_ENCODE_SET, "computeProperty", "Lkotlin/Function0;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getName", "()Ljava/lang/String;", "getter", "Lkotlin/reflect/KProperty1$Getter;", "getGetter", "()Lkotlin/reflect/KProperty1$Getter;", "get", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", HttpUrl.FRAGMENT_ENCODE_SET, "invoke", "kotlin-reflection"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class LazyKProperty1<T, V, D extends KProperty1<T, ? extends V>> extends LazyKProperty<V, D> implements KProperty1<T, V> {
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyKProperty1(String str, Function0<? extends D> function0) {
        super(function0);
        str.getClass();
        function0.getClass();
        this.name = str;
    }

    public V get(T receiver) {
        return (V) getDelegate().get(receiver);
    }

    public Object getDelegate(T receiver) {
        return getDelegate().getDelegate(receiver);
    }

    public KProperty1.Getter<T, V> getGetter() {
        return getDelegate().getGetter();
    }

    public String getName() {
        return this.name;
    }

    public V invoke(T receiver) {
        return (V) getDelegate().invoke(receiver);
    }
}
