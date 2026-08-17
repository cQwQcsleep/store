package androidx.savedstate.serialization.serializers;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\b"}, d2 = {"encoderErrorMessage", "", "serialName", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "decoderErrorMessage", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "savedstate_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BuiltInSerializerKt {
    public static final String decoderErrorMessage(String str, Decoder decoder) {
        str.getClass();
        decoder.getClass();
        return "Cannot deserialize " + str + " with '" + Reflection.getOrCreateKotlinClass(decoder.getClass()).getSimpleName() + "'. This serializer can only be used with SavedStateDecoder. Use 'decodeFromSavedState' instead.";
    }

    public static final String encoderErrorMessage(String str, Encoder encoder) {
        str.getClass();
        encoder.getClass();
        return "Cannot serialize " + str + " with '" + Reflection.getOrCreateKotlinClass(encoder.getClass()).getSimpleName() + "'. This serializer can only be used with SavedStateEncoder. Use 'encodeToSavedState' instead.";
    }
}
