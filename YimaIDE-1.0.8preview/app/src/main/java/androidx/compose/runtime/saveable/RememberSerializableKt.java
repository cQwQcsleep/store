package androidx.compose.runtime.saveable;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.saveable.serialization.SerializableSaverKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.SavedStateConfiguration;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aL\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\b\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00010\bH\u0087\b¢\u0006\u0002\u0010\t\u001aU\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00010\bH\u0007¢\u0006\u0002\u0010\f\u001aX\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\r\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\b\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\r0\bH\u0087\b¢\u0006\u0002\u0010\u000e\u001aa\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\r\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\r0\bH\u0007¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"rememberSerializable", "T", "", "inputs", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "init", "Lkotlin/Function0;", "([Ljava/lang/Object;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "serializer", "Lkotlinx/serialization/KSerializer;", "([Ljava/lang/Object;Lkotlinx/serialization/KSerializer;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "Landroidx/compose/runtime/MutableState;", "([Ljava/lang/Object;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "stateSerializer", "([Ljava/lang/Object;Lkotlinx/serialization/KSerializer;Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "runtime-saveable"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RememberSerializableKt {
    public static final <T> MutableState<T> rememberSerializable(Object[] objArr, KSerializer<T> kSerializer, SavedStateConfiguration savedStateConfiguration, Function0<? extends MutableState<T>> function0, Composer composer, int i, int i2) {
        if ((i2 & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1248826527, i, -1, "androidx.compose.runtime.saveable.rememberSerializable (RememberSerializable.kt:163)");
        }
        MutableState<T> mutableState = (MutableState) RememberSaveableKt.m2563rememberSaveable(Arrays.copyOf(objArr, objArr.length), RememberSaveableKt.mutableStateSaver(SerializableSaverKt.serializableSaver(kSerializer, savedStateConfiguration)), (String) null, (Function0) function0, composer, (i & V4Signature.MAX_SIGNING_INFOS_SIZE) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }

    /* JADX INFO: renamed from: rememberSerializable, reason: collision with other method in class */
    public static final <T> T m2566rememberSerializable(Object[] objArr, KSerializer<T> kSerializer, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0, Composer composer, int i, int i2) {
        if ((i2 & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1261607160, i, -1, "androidx.compose.runtime.saveable.rememberSerializable (RememberSerializable.kt:93)");
        }
        T t = (T) RememberSaveableKt.m2563rememberSaveable(Arrays.copyOf(objArr, objArr.length), SerializableSaverKt.serializableSaver(kSerializer, savedStateConfiguration), (String) null, (Function0) function0, composer, (i & V4Signature.MAX_SIGNING_INFOS_SIZE) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t;
    }

    public static final /* synthetic */ <T> MutableState<T> rememberSerializable(Object[] objArr, SavedStateConfiguration savedStateConfiguration, Function0<? extends MutableState<T>> function0, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        SavedStateConfiguration savedStateConfiguration2 = savedStateConfiguration;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        SerializersModule serializersModule = savedStateConfiguration2.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return rememberSerializable(objArrCopyOf, SerializersKt.serializer(serializersModule, (KType) null), savedStateConfiguration2, (Function0) function0, composer, (i << 3) & 8064, 0);
    }

    /* JADX INFO: renamed from: rememberSerializable, reason: collision with other method in class */
    public static final /* synthetic */ <T> T m2565rememberSerializable(Object[] objArr, SavedStateConfiguration savedStateConfiguration, Function0<? extends T> function0, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        SavedStateConfiguration savedStateConfiguration2 = savedStateConfiguration;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        SerializersModule serializersModule = savedStateConfiguration2.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return (T) m2566rememberSerializable(objArrCopyOf, SerializersKt.serializer(serializersModule, (KType) null), savedStateConfiguration2, (Function0) function0, composer, (i << 3) & 8064, 0);
    }
}
