package androidx.lifecycle;

import android.app.Application;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import defpackage.g8c;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u001aI\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0000¢\u0006\u0002\u0010\n\u001a6\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\fH\u0000\"\u0018\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"newInstance", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "constructor", "Ljava/lang/reflect/Constructor;", "params", "", "", "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Landroidx/lifecycle/ViewModel;", "ANDROID_VIEWMODEL_SIGNATURE", "", "VIEWMODEL_SIGNATURE", "findMatchingConstructor", "signature", "lifecycle-viewmodel-savedstate_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateViewModelFactory_androidKt {
    private static final List<Class<?>> ANDROID_VIEWMODEL_SIGNATURE = CollectionsKt.listOf(new Class[]{Application.class, SavedStateHandle.class});
    private static final List<Class<?>> VIEWMODEL_SIGNATURE = CollectionsKt.listOf(SavedStateHandle.class);

    public static final <T> Constructor<T> findMatchingConstructor(Class<T> cls, List<? extends Class<?>> list) {
        cls.getClass();
        list.getClass();
        Iterator it = ArrayIteratorKt.iterator(cls.getConstructors());
        while (it.hasNext()) {
            Constructor<T> constructor = (Constructor) it.next();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            parameterTypes.getClass();
            List list2 = ArraysKt.toList(parameterTypes);
            if (Intrinsics.areEqual(list, list2)) {
                return constructor;
            }
            if (list.size() == list2.size() && list2.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final <T extends ViewModel> T newInstance(Class<T> cls, Constructor<T> constructor, Object... objArr) {
        cls.getClass();
        constructor.getClass();
        objArr.getClass();
        try {
            return constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            og0.a("Failed to access ", cls, e);
            return null;
        } catch (InstantiationException e2) {
            g8c.a("A ", cls, " cannot be instantiated.", e2);
            return null;
        } catch (InvocationTargetException e3) {
            g3c.a("An exception happened in constructor of " + cls, e3.getCause());
            return null;
        }
    }
}
