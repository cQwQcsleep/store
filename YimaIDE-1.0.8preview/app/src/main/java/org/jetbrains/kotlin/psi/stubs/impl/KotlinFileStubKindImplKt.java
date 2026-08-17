package org.jetbrains.kotlin.psi.stubs.impl;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty1;
import org.jetbrains.kotlin.psi.stubs.KotlinFileStubKind;
import org.jetbrains.kotlin.psi.stubs.impl.KotlinFileStubKindImplKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001aG\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00022*\u0010\u0004\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u00060\u0005\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"toStringGenerator", "", "T", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind;", "property", "", "Lkotlin/reflect/KProperty1;", "", "(Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind;[Lkotlin/reflect/KProperty1;)Ljava/lang/String;", "org.jetbrains.kotlin:psi-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KotlinFileStubKindImplKt {
    public static CharSequence a(KotlinFileStubKind kotlinFileStubKind, KProperty1 kProperty1) {
        kProperty1.getClass();
        return kProperty1.getName() + '=' + kProperty1.get(kotlinFileStubKind);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends KotlinFileStubKind> String toStringGenerator(final T t, KProperty1<T, ? extends Object>... kProperty1Arr) {
        return ArraysKt.joinToString$default(kProperty1Arr, ", ", Reflection.getOrCreateKotlinClass(t.getClass()).getSimpleName() + '[', "]", 0, (CharSequence) null, new Function1() { // from class: vb8
            public final Object invoke(Object obj) {
                return KotlinFileStubKindImplKt.a(t, (KProperty1) obj);
            }
        }, 24, (Object) null);
    }
}
