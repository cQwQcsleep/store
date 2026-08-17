package androidx.lifecycle.serialization;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.lifecycle.SavedStateHandle;
import androidx.savedstate.serialization.SavedStateConfiguration;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aP\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u0002*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\nH\u0086\bø\u0001\u0000\u001aT\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0002*\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00030\f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"saved", "Lkotlin/properties/ReadWriteProperty;", "", "T", "Landroidx/lifecycle/SavedStateHandle;", "key", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "init", "Lkotlin/Function0;", "serializer", "Lkotlinx/serialization/KSerializer;", "lifecycle-viewmodel-savedstate_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateHandleDelegateKt {
    public static final /* synthetic */ <T> ReadWriteProperty<Object, T> saved(SavedStateHandle savedStateHandle, String str, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0) {
        savedStateHandle.getClass();
        savedStateConfiguration.getClass();
        function0.getClass();
        SerializersModule serializersModule = savedStateConfiguration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return saved(savedStateHandle, SerializersKt.serializer(serializersModule, (KType) null), str, savedStateConfiguration, function0);
    }

    public static /* synthetic */ ReadWriteProperty saved$default(SavedStateHandle savedStateHandle, String str, SavedStateConfiguration savedStateConfiguration, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        savedStateHandle.getClass();
        savedStateConfiguration.getClass();
        function0.getClass();
        SerializersModule serializersModule = savedStateConfiguration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return saved(savedStateHandle, SerializersKt.serializer(serializersModule, (KType) null), str, savedStateConfiguration, function0);
    }

    public static final <T> ReadWriteProperty<Object, T> saved(SavedStateHandle savedStateHandle, KSerializer<T> kSerializer, String str, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0) {
        savedStateHandle.getClass();
        kSerializer.getClass();
        savedStateConfiguration.getClass();
        function0.getClass();
        return new SavedStateHandleDelegate(savedStateHandle, kSerializer, str, savedStateConfiguration, function0);
    }

    public static /* synthetic */ ReadWriteProperty saved$default(SavedStateHandle savedStateHandle, KSerializer kSerializer, String str, SavedStateConfiguration savedStateConfiguration, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        return saved(savedStateHandle, kSerializer, str, savedStateConfiguration, function0);
    }
}
