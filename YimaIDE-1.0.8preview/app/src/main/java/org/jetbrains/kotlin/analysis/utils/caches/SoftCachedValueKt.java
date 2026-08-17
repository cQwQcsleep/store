package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0086\n¢\u0006\u0002\u0010\u0007\u001aI\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0002\"\u0004\b\u0000\u0010\u00012\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\f\"\u00020\u00042\u000e\b\u0004\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000eH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"getValue", "T", "Lcom/intellij/psi/util/CachedValue;", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Lcom/intellij/psi/util/CachedValue;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "softCachedValue", "project", "Lcom/intellij/openapi/project/Project;", "dependencies", "", "createValue", "Lkotlin/Function0;", "(Lcom/intellij/openapi/project/Project;[Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Lcom/intellij/psi/util/CachedValue;", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SoftCachedValueKt {
    public static final <T> T getValue(com.intellij.psi.util.CachedValue<T> cachedValue, Object obj, KProperty<?> kProperty) {
        cachedValue.getClass();
        kProperty.getClass();
        return (T) cachedValue.getValue();
    }

    public static final <T> com.intellij.psi.util.CachedValue<T> softCachedValue(Project project, final Object[] objArr, final Function0<? extends T> function0) {
        project.getClass();
        objArr.getClass();
        function0.getClass();
        com.intellij.psi.util.CachedValue<T> cachedValueCreateCachedValue = CachedValuesManager.getManager(project).createCachedValue(new CachedValueProvider() { // from class: org.jetbrains.kotlin.analysis.utils.caches.SoftCachedValueKt.softCachedValue.1
            public final CachedValueProvider.Result<T> compute() {
                return new CachedValueProvider.Result<>(function0.invoke(), new Object[]{objArr});
            }
        });
        cachedValueCreateCachedValue.getClass();
        return cachedValueCreateCachedValue;
    }
}
