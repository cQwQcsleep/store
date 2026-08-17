package androidx.savedstate.serialization;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001a;\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\n2\u0006\u0010\u0005\u001a\u0002H\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"encodeToSavedState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "T", "", "value", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "(Ljava/lang/Object;Landroidx/savedstate/serialization/SavedStateConfiguration;)Landroid/os/Bundle;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;Landroidx/savedstate/serialization/SavedStateConfiguration;)Landroid/os/Bundle;", "savedstate_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateEncoderKt {
    public static final <T> Bundle encodeToSavedState(SerializationStrategy<? super T> serializationStrategy, T t, SavedStateConfiguration savedStateConfiguration) {
        Pair[] pairArr;
        serializationStrategy.getClass();
        t.getClass();
        savedStateConfiguration.getClass();
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry : mapEmptyMap.entrySet()) {
                arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m6396constructorimpl(bundleBundleOf);
        new SavedStateEncoder(bundleBundleOf, savedStateConfiguration).encodeSerializableValue(serializationStrategy, t);
        return bundleBundleOf;
    }

    public static /* synthetic */ Bundle encodeToSavedState$default(Object obj, SavedStateConfiguration savedStateConfiguration, int i, Object obj2) {
        if ((i & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        obj.getClass();
        savedStateConfiguration.getClass();
        SerializersModule serializersModule = savedStateConfiguration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return encodeToSavedState(SerializersKt.serializer(serializersModule, (KType) null), obj, savedStateConfiguration);
    }

    public static /* synthetic */ Bundle encodeToSavedState$default(SerializationStrategy serializationStrategy, Object obj, SavedStateConfiguration savedStateConfiguration, int i, Object obj2) {
        if ((i & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        return encodeToSavedState(serializationStrategy, obj, savedStateConfiguration);
    }

    public static final /* synthetic */ <T> Bundle encodeToSavedState(T t, SavedStateConfiguration savedStateConfiguration) {
        t.getClass();
        savedStateConfiguration.getClass();
        SerializersModule serializersModule = savedStateConfiguration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return encodeToSavedState(SerializersKt.serializer(serializersModule, (KType) null), t, savedStateConfiguration);
    }

    public static final <T> Bundle encodeToSavedState(SerializationStrategy<? super T> serializationStrategy, T t) {
        serializationStrategy.getClass();
        t.getClass();
        return encodeToSavedState$default(serializationStrategy, t, null, 4, null);
    }
}
