package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0010\b\u0001\u0010\u0002 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u0019\u001a\u00028\u00002\u0016\u0010\u001a\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001c0\u001b\"\u0004\u0018\u00010\u001cH\u0096\u0080\u0004¢\u0006\u0002\u0010\u001dJ%\u0010\u001e\u001a\u00028\u00002\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001fH\u0096\u0080\u0004¢\u0006\u0002\u0010 J\u0014\u00100\u001a\u00020&2\b\u00101\u001a\u0004\u0018\u00010\u001cH\u0096\u0082\u0004J\n\u00102\u001a\u000203H\u0096\u0080\u0004J\n\u00104\u001a\u000205H\u0096\u0080\u0004R\u001b\u0010\b\u001a\u00028\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0012\u001a\u00020\u00138VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0011R\u0017\u0010!\u001a\u0004\u0018\u00010\"8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0015\u0010%\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b%\u0010'R\u0015\u0010(\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b(\u0010'R\u0015\u0010)\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b)\u0010'R\u0015\u0010*\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b*\u0010'R\u0015\u0010+\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b+\u0010'R\u0015\u0010,\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b,\u0010'R\u001b\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b/\u0010\u0011¨\u00066"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKProperty;", "V", "D", "Lkotlin/reflect/KProperty;", "computeProperty", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "delegate", "getDelegate", "()Lkotlin/reflect/KProperty;", "delegate$delegate", "Lkotlin/Lazy;", "parameters", HttpUrl.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KParameter;", "getParameters", "()Ljava/util/List;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "call", "args", HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", HttpUrl.FRAGMENT_ENCODE_SET, "(Ljava/util/Map;)Ljava/lang/Object;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", HttpUrl.FRAGMENT_ENCODE_SET, "()Z", "isOpen", "isAbstract", "isSuspend", "isLateinit", "isConst", "annotations", HttpUrl.FRAGMENT_ENCODE_SET, "getAnnotations", "equals", "other", "hashCode", HttpUrl.FRAGMENT_ENCODE_SET, "toString", HttpUrl.FRAGMENT_ENCODE_SET, "kotlin-reflection"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class LazyKProperty<V, D extends KProperty<? extends V>> implements KProperty<V> {

    /* JADX INFO: renamed from: delegate$delegate, reason: from kotlin metadata */
    private final Lazy delegate;

    public LazyKProperty(Function0<? extends D> function0) {
        function0.getClass();
        this.delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function0);
    }

    public V call(Object... args) {
        args.getClass();
        return (V) getDelegate().call(Arrays.copyOf(args, args.length));
    }

    public V callBy(Map<KParameter, ? extends Object> args) {
        args.getClass();
        return (V) getDelegate().callBy(args);
    }

    public boolean equals(Object other) {
        return Intrinsics.areEqual(getDelegate(), other);
    }

    public List<Annotation> getAnnotations() {
        return getDelegate().getAnnotations();
    }

    public final D getDelegate() {
        return (D) this.delegate.getValue();
    }

    public List<KParameter> getParameters() {
        return getDelegate().getParameters();
    }

    public KType getReturnType() {
        return getDelegate().getReturnType();
    }

    public List<KTypeParameter> getTypeParameters() {
        return getDelegate().getTypeParameters();
    }

    public KVisibility getVisibility() {
        return getDelegate().getVisibility();
    }

    public int hashCode() {
        return getDelegate().hashCode();
    }

    public boolean isAbstract() {
        return getDelegate().isAbstract();
    }

    public boolean isConst() {
        return getDelegate().isConst();
    }

    public boolean isFinal() {
        return getDelegate().isFinal();
    }

    public boolean isLateinit() {
        return getDelegate().isLateinit();
    }

    public boolean isOpen() {
        return getDelegate().isOpen();
    }

    public boolean isSuspend() {
        return getDelegate().isSuspend();
    }

    public String toString() {
        return getDelegate().toString();
    }
}
