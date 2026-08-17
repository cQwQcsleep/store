package androidx.lifecycle.serialization;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.internal.CanonicalName_jvmKt;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.serialization.SavedStateConfiguration;
import androidx.savedstate.serialization.SavedStateDecoderKt;
import androidx.savedstate.serialization.SavedStateEncoderKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00010\u0003B=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tH\u0002J\u001e\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00022\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002J$\u0010\u001a\u001a\u00028\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u00022\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0096\u0002¢\u0006\u0002\u0010\u001bJ,\u0010\u001c\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00022\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u001e"}, d2 = {"Landroidx/lifecycle/serialization/SavedStateHandleDelegate;", "T", "", "Lkotlin/properties/ReadWriteProperty;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "serializer", "Lkotlinx/serialization/KSerializer;", "key", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "init", "Lkotlin/Function0;", "<init>", "(Landroidx/lifecycle/SavedStateHandle;Lkotlinx/serialization/KSerializer;Ljava/lang/String;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;)V", "value", "Ljava/lang/Object;", "loadValue", "(Ljava/lang/String;)Ljava/lang/Object;", "registerSave", "", "createDefaultKey", "thisRef", "property", "Lkotlin/reflect/KProperty;", "getValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class SavedStateHandleDelegate<T> implements ReadWriteProperty<Object, T> {
    private final SavedStateConfiguration configuration;
    private final Function0<T> init;
    private final String key;
    private final SavedStateHandle savedStateHandle;
    private final KSerializer<T> serializer;
    private T value;

    /* JADX WARN: Multi-variable type inference failed */
    public SavedStateHandleDelegate(SavedStateHandle savedStateHandle, KSerializer<T> kSerializer, String str, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0) {
        savedStateHandle.getClass();
        kSerializer.getClass();
        savedStateConfiguration.getClass();
        function0.getClass();
        this.savedStateHandle = savedStateHandle;
        this.serializer = kSerializer;
        this.key = str;
        this.configuration = savedStateConfiguration;
        this.init = function0;
    }

    public static Bundle a(SavedStateHandleDelegate savedStateHandleDelegate) {
        SerializationStrategy serializationStrategy = savedStateHandleDelegate.serializer;
        Object obj = savedStateHandleDelegate.value;
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException("value");
            obj = Unit.INSTANCE;
        }
        return SavedStateEncoderKt.encodeToSavedState(serializationStrategy, obj, savedStateHandleDelegate.configuration);
    }

    private final String createDefaultKey(Object thisRef, KProperty<?> property) {
        String str;
        if (thisRef != null) {
            str = CanonicalName_jvmKt.getCanonicalName(Reflection.getOrCreateKotlinClass(thisRef.getClass())) + '.';
        } else {
            str = "";
        }
        return str + property.getName();
    }

    private final T loadValue(String key) {
        Bundle bundle = (Bundle) this.savedStateHandle.get(key);
        if (bundle != null) {
            return (T) SavedStateDecoderKt.decodeFromSavedState(this.serializer, bundle, this.configuration);
        }
        return null;
    }

    private final void registerSave(String key) {
        this.savedStateHandle.setSavedStateProvider(key, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.serialization.a
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandleDelegate.a(this.a);
            }
        });
    }

    public T getValue(Object thisRef, KProperty<?> property) {
        property.getClass();
        if (this.value == null) {
            String strCreateDefaultKey = this.key;
            if (strCreateDefaultKey == null) {
                strCreateDefaultKey = createDefaultKey(thisRef, property);
            }
            registerSave(strCreateDefaultKey);
            T tLoadValue = loadValue(strCreateDefaultKey);
            if (tLoadValue == null) {
                tLoadValue = (T) this.init.invoke();
            }
            this.value = tLoadValue;
        }
        T t = this.value;
        if (t != null) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("value");
        return (T) Unit.INSTANCE;
    }

    public void setValue(Object thisRef, KProperty<?> property, T value) {
        property.getClass();
        value.getClass();
        if (this.value == null) {
            String strCreateDefaultKey = this.key;
            if (strCreateDefaultKey == null) {
                strCreateDefaultKey = createDefaultKey(thisRef, property);
            }
            registerSave(strCreateDefaultKey);
        }
        this.value = value;
    }
}
