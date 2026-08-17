package androidx.compose.runtime.saveable.serialization;

import android.os.Bundle;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.saveable.serialization.SerializableSaverKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.SavedStateConfiguration;
import androidx.savedstate.serialization.SavedStateDecoderKt;
import androidx.savedstate.serialization.SavedStateEncoderKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0080\b\u001a:\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\n"}, d2 = {"serializableSaver", "Landroidx/compose/runtime/saveable/Saver;", "Serializable", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "serializer", "Lkotlinx/serialization/KSerializer;", "runtime-saveable"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SerializableSaverKt {
    public static Object a(KSerializer kSerializer, SavedStateConfiguration savedStateConfiguration, Bundle bundle) {
        return SavedStateDecoderKt.decodeFromSavedState((DeserializationStrategy) kSerializer, bundle, savedStateConfiguration);
    }

    public static Bundle b(KSerializer kSerializer, SavedStateConfiguration savedStateConfiguration, SaverScope saverScope, Object obj) {
        return SavedStateEncoderKt.encodeToSavedState((SerializationStrategy) kSerializer, obj, savedStateConfiguration);
    }

    public static final /* synthetic */ <Serializable> Saver<Serializable, Bundle> serializableSaver(SavedStateConfiguration savedStateConfiguration) {
        SerializersModule serializersModule = savedStateConfiguration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "Serializable");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return serializableSaver(SerializersKt.serializer(serializersModule, (KType) null), savedStateConfiguration);
    }

    public static /* synthetic */ Saver serializableSaver$default(SavedStateConfiguration savedStateConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        SerializersModule serializersModule = savedStateConfiguration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "Serializable");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return serializableSaver(SerializersKt.serializer(serializersModule, (KType) null), savedStateConfiguration);
    }

    public static final <Serializable> Saver<Serializable, Bundle> serializableSaver(final KSerializer<Serializable> kSerializer, final SavedStateConfiguration savedStateConfiguration) {
        return SaverKt.Saver(new Function2() { // from class: e7d
            public final Object invoke(Object obj, Object obj2) {
                return SerializableSaverKt.b(kSerializer, savedStateConfiguration, (SaverScope) obj, obj2);
            }
        }, new Function1() { // from class: f7d
            public final Object invoke(Object obj) {
                return SerializableSaverKt.a(kSerializer, savedStateConfiguration, (Bundle) obj);
            }
        });
    }

    public static /* synthetic */ Saver serializableSaver$default(KSerializer kSerializer, SavedStateConfiguration savedStateConfiguration, int i, Object obj) {
        if ((i & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        return serializableSaver(kSerializer, savedStateConfiguration);
    }
}
