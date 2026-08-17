package org.jetbrains.kotlin.analysis.utils.errors;

import defpackage.fs1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\u0010\u0001(\u0000\u001a&\u0010\u0005\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\u0010\u0001(\u0000ò\u0001\u0004\n\u0002H\u0002¨\u0006\u0006"}, d2 = {"requireIsInstance", "", "T", "obj", "", "checkIsInstance", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ChecksKt {
    public static final /* synthetic */ <T> void checkIsInstance(Object obj) {
        obj.getClass();
        Intrinsics.reifiedOperationMarker(3, "T");
        if (obj != null) {
            return;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        Intrinsics.reifiedOperationMarker(4, "T");
        sb.append(Reflection.getOrCreateKotlinClass(Object.class));
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(obj.getClass());
        sb.append(" instead of ");
        sb.append(orCreateKotlinClass);
        sb.append(" for ");
        sb.append(obj);
        throw new IllegalStateException(sb.toString().toString());
    }

    public static final /* synthetic */ <T> void requireIsInstance(Object obj) {
        obj.getClass();
        Intrinsics.reifiedOperationMarker(3, "T");
        if (obj != null) {
            return;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        Intrinsics.reifiedOperationMarker(4, "T");
        sb.append(Reflection.getOrCreateKotlinClass(Object.class));
        fs1.a(sb, " instead of ", Reflection.getOrCreateKotlinClass(obj.getClass()), " for ", obj);
    }
}
