package androidx.savedstate.serialization;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J!\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u001e\u001a\u00020\rH\u0002¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\u0016H\u0016J\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\u001aH\u0016J\u0010\u0010&\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u00101\u001a\u00020\u001dH\u0016J\u0016\u00102\u001a\u00020\u001d2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u001a03H\u0002J\u0016\u00104\u001a\u00020\u001d2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r03H\u0002J\u0010\u00105\u001a\u00020\u001d2\u0006\u0010\f\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020\u001d2\u0006\u0010\f\u001a\u000208H\u0002J\u0010\u00109\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020<H\u0002J\u0010\u0010=\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020@H\u0002J\u001b\u0010A\u001a\u00020\u001d2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0BH\u0002¢\u0006\u0002\u0010CJ\u0010\u0010D\u001a\u00020E2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J)\u0010F\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u0002¢\u0006\u0002\u0010GJ)\u0010H\u001a\u00020\u001d\"\u0004\b\u0000\u0010I2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002HI0K2\u0006\u0010\f\u001a\u0002HIH\u0016¢\u0006\u0002\u0010LJ)\u0010M\u001a\u00020\u0016\"\u0004\b\u0000\u0010I2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002HI0K2\u0006\u0010\f\u001a\u0002HIH\u0002¢\u0006\u0002\u0010NR\u001a\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006O"}, d2 = {"Landroidx/savedstate/serialization/SavedStateEncoder;", "Lkotlinx/serialization/encoding/AbstractEncoder;", "savedState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "<init>", "(Landroid/os/Bundle;Landroidx/savedstate/serialization/SavedStateConfiguration;)V", "getSavedState$savedstate_release", "()Landroid/os/Bundle;", "Landroid/os/Bundle;", "value", "", "key", "getKey$savedstate_release", "()Ljava/lang/String;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "shouldEncodeElementDefault", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "index", "", "encodeElement", "checkDiscriminatorCollisions", "", "elementName", "(Landroid/os/Bundle;Ljava/lang/String;)V", "encodeBoolean", "encodeByte", "", "encodeShort", "", "encodeInt", "encodeLong", "", "encodeFloat", "", "encodeDouble", "", "encodeChar", "", "encodeString", "encodeEnum", "enumDescriptor", "encodeNull", "encodeIntList", "", "encodeStringList", "encodeBooleanArray", "", "encodeCharArray", "", "encodeDoubleArray", "", "encodeFloatArray", "", "encodeIntArray", "", "encodeLongArray", "", "encodeStringArray", "", "([Ljava/lang/String;)V", "beginStructure", "Lkotlinx/serialization/encoding/CompositeEncoder;", "putClassDiscriminatorIfRequired", "(Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlinx/serialization/descriptors/SerialDescriptor;Landroid/os/Bundle;)V", "encodeSerializableValue", "T", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeFormatSpecificTypes", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Z", "savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateEncoder extends AbstractEncoder {
    private final SavedStateConfiguration configuration;
    private String key;
    private final Bundle savedState;
    private final SerializersModule serializersModule;

    public SavedStateEncoder(Bundle bundle, SavedStateConfiguration savedStateConfiguration) {
        bundle.getClass();
        savedStateConfiguration.getClass();
        this.savedState = bundle;
        this.configuration = savedStateConfiguration;
        this.key = "";
        this.serializersModule = savedStateConfiguration.getSerializersModule();
    }

    private final void checkDiscriminatorCollisions(Bundle savedState, String elementName) {
        if (this.configuration.getClassDiscriminatorMode() == 1) {
            boolean zM6311containsimpl = SavedStateReader.m6311containsimpl(SavedStateReader.m6310constructorimpl(savedState), ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
            boolean zAreEqual = Intrinsics.areEqual(elementName, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
            if (zM6311containsimpl && zAreEqual) {
                zba.a("SavedStateEncoder for ", SavedStateReader.m6381getStringimpl(SavedStateReader.m6310constructorimpl(savedState), ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), " has property '", elementName, "' that conflicts with the class discriminator. You can rename a property with @SerialName annotation.");
            }
        }
    }

    private final void encodeBooleanArray(boolean[] value) {
        SavedStateWriter.m6403putBooleanArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeCharArray(char[] value) {
        SavedStateWriter.m6405putCharArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeDoubleArray(double[] value) {
        SavedStateWriter.m6410putDoubleArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeFloatArray(float[] value) {
        SavedStateWriter.m6412putFloatArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> boolean encodeFormatSpecificTypes(SerializationStrategy<? super T> serializer, T value) {
        if (SavedStateEncoder_androidKt.encodeFormatSpecificTypesOnPlatform(this, serializer, value)) {
            return true;
        }
        SerialDescriptor descriptor = serializer.getDescriptor();
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getIntListDescriptor())) {
            value.getClass();
            encodeIntList((List) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getStringListDescriptor())) {
            value.getClass();
            encodeStringList((List) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getBooleanArrayDescriptor())) {
            value.getClass();
            encodeBooleanArray((boolean[]) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getCharArrayDescriptor())) {
            value.getClass();
            encodeCharArray((char[]) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getDoubleArrayDescriptor())) {
            value.getClass();
            encodeDoubleArray((double[]) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getFloatArrayDescriptor())) {
            value.getClass();
            encodeFloatArray((float[]) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getIntArrayDescriptor())) {
            value.getClass();
            encodeIntArray((int[]) value);
            return true;
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getLongArrayDescriptor())) {
            value.getClass();
            encodeLongArray((long[]) value);
            return true;
        }
        if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getStringArrayDescriptor())) {
            return false;
        }
        value.getClass();
        encodeStringArray((String[]) value);
        return true;
    }

    private final void encodeIntArray(int[] value) {
        SavedStateWriter.m6414putIntArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeIntList(List<Integer> value) {
        SavedStateWriter.m6415putIntListimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeLongArray(long[] value) {
        SavedStateWriter.m6418putLongArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeStringArray(String[] value) {
        SavedStateWriter.m6430putStringArrayimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void encodeStringList(List<String> value) {
        SavedStateWriter.m6431putStringListimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    private final void putClassDiscriminatorIfRequired(SavedStateConfiguration configuration, SerialDescriptor descriptor, Bundle savedState) {
        if (configuration.getClassDiscriminatorMode() == 1 && !SavedStateReader.m6311containsimpl(SavedStateReader.m6310constructorimpl(savedState), ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)) {
            if (Intrinsics.areEqual(descriptor.getKind(), StructureKind.CLASS.INSTANCE) || Intrinsics.areEqual(descriptor.getKind(), StructureKind.OBJECT.INSTANCE)) {
                SavedStateWriter.m6429putStringimpl(SavedStateWriter.m6396constructorimpl(savedState), ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, descriptor.getSerialName());
            }
        }
    }

    public CompositeEncoder beginStructure(SerialDescriptor descriptor) {
        Pair[] pairArr;
        descriptor.getClass();
        if (Intrinsics.areEqual(this.key, "")) {
            putClassDiscriminatorIfRequired(this.configuration, descriptor, this.savedState);
            return this;
        }
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
        SavedStateWriter.m6423putSavedStateimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, bundleBundleOf);
        putClassDiscriminatorIfRequired(this.configuration, descriptor, bundleBundleOf);
        return new SavedStateEncoder(bundleBundleOf, this.configuration);
    }

    public void encodeBoolean(boolean value) {
        SavedStateWriter.m6402putBooleanimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeByte(byte value) {
        SavedStateWriter.m6413putIntimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeChar(char value) {
        SavedStateWriter.m6404putCharimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeDouble(double value) {
        SavedStateWriter.m6409putDoubleimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public boolean encodeElement(SerialDescriptor descriptor, int index) {
        descriptor.getClass();
        String elementName = descriptor.getElementName(index);
        this.key = elementName;
        checkDiscriminatorCollisions(this.savedState, elementName);
        return true;
    }

    public void encodeEnum(SerialDescriptor enumDescriptor, int index) {
        enumDescriptor.getClass();
        SavedStateWriter.m6413putIntimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, index);
    }

    public void encodeFloat(float value) {
        SavedStateWriter.m6411putFloatimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeInt(int value) {
        SavedStateWriter.m6413putIntimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeLong(long value) {
        SavedStateWriter.m6417putLongimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeNull() {
        SavedStateWriter.m6419putNullimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key);
    }

    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializer, T value) {
        serializer.getClass();
        if (encodeFormatSpecificTypes(serializer, value)) {
            return;
        }
        super.encodeSerializableValue(serializer, value);
    }

    public void encodeShort(short value) {
        SavedStateWriter.m6413putIntimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    public void encodeString(String value) {
        value.getClass();
        SavedStateWriter.m6429putStringimpl(SavedStateWriter.m6396constructorimpl(this.savedState), this.key, value);
    }

    /* JADX INFO: renamed from: getKey$savedstate_release, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: getSavedState$savedstate_release, reason: from getter */
    public final Bundle getSavedState() {
        return this.savedState;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public boolean shouldEncodeElementDefault(SerialDescriptor descriptor, int index) {
        descriptor.getClass();
        return this.configuration.getEncodeDefaults();
    }
}
