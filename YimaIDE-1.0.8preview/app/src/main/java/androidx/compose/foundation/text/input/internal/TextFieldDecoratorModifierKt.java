package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.content.MediaType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"MediaTypesText", "", "Landroidx/compose/foundation/content/MediaType;", "getMediaTypesText$annotations", "()V", "MediaTypesAll", "getMediaTypesAll$annotations", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldDecoratorModifierKt {
    private static final Set<MediaType> MediaTypesAll;
    private static final Set<MediaType> MediaTypesText;

    static {
        MediaType.Companion companion = MediaType.INSTANCE;
        MediaTypesText = SetsKt.setOf(companion.getText());
        MediaTypesAll = SetsKt.setOf(companion.getAll());
    }

    private static /* synthetic */ void getMediaTypesAll$annotations() {
    }

    private static /* synthetic */ void getMediaTypesText$annotations() {
    }
}
