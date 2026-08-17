package androidx.savedstate.serialization;

import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.SavedStateConfig_androidKt;
import androidx.savedstate.serialization.serializers.SizeFSerializer;
import androidx.savedstate.serialization.serializers.SizeSerializer;
import androidx.savedstate.serialization.serializers.SparseArraySerializer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"getDefaultSerializersModuleOnPlatform", "Lkotlinx/serialization/modules/SerializersModule;", "savedstate_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateConfig_androidKt {
    public static KSerializer a(List list) {
        list.getClass();
        return new SparseArraySerializer((KSerializer) CollectionsKt.first(list));
    }

    public static final SerializersModule getDefaultSerializersModuleOnPlatform() {
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(Size.class), SizeSerializer.INSTANCE);
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(SizeF.class), SizeFSerializer.INSTANCE);
        serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(SparseArray.class), new Function1() { // from class: urc
            public final Object invoke(Object obj) {
                return SavedStateConfig_androidKt.a((List) obj);
            }
        });
        return serializersModuleBuilder.build();
    }
}
