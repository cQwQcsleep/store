package org.jetbrains.kotlin.utils;

import java.lang.reflect.Field;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001¨\u0006\u0004"}, d2 = {"getSafe", "", "Ljava/lang/reflect/Field;", "obj", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ReflectionUtilKt {
    public static final Object getSafe(Field field, Object obj) {
        field.getClass();
        try {
            boolean zIsAccessible = field.isAccessible();
            try {
                field.setAccessible(true);
                return field.get(obj);
            } finally {
                field.setAccessible(zIsAccessible);
            }
        } catch (Throwable unused) {
            return null;
        }
    }
}
