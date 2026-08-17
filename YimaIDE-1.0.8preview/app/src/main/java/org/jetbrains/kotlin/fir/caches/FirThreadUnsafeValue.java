package org.jetbrains.kotlin.fir.caches;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tR\u001b\u0010\u0007\u001a\u00028\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirThreadUnsafeValue;", "V", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "createValue", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "lazyValue", "getLazyValue", "()Ljava/lang/Object;", "lazyValue$delegate", "Lkotlin/Lazy;", "getValue", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirThreadUnsafeValue<V> extends FirLazyValue<V> {

    /* JADX INFO: renamed from: lazyValue$delegate, reason: from kotlin metadata */
    private final Lazy lazyValue;

    public FirThreadUnsafeValue(Function0<? extends V> function0) {
        function0.getClass();
        this.lazyValue = LazyKt.lazy(LazyThreadSafetyMode.NONE, function0);
    }

    private final V getLazyValue() {
        return (V) this.lazyValue.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.caches.FirLazyValue
    public V getValue() {
        return getLazyValue();
    }
}
