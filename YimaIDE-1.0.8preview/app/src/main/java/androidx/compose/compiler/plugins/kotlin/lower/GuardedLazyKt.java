package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0086\n¢\u0006\u0002\u0010\u0007\u001a \u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0002\"\u0004\b\u0000\u0010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\n¨\u0006\u000b"}, d2 = {"getValue", "T", "Landroidx/compose/compiler/plugins/kotlin/lower/GuardedLazy;", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/compose/compiler/plugins/kotlin/lower/GuardedLazy;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "guardedLazy", "initializer", "Lkotlin/Function0;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class GuardedLazyKt {
    public static final <T> T getValue(GuardedLazy<? extends T> guardedLazy, Object obj, KProperty<?> kProperty) {
        guardedLazy.getClass();
        kProperty.getClass();
        return guardedLazy.value(kProperty.getName());
    }

    public static final <T> GuardedLazy<T> guardedLazy(Function0<? extends T> function0) {
        function0.getClass();
        return new GuardedLazy<>(function0);
    }
}
