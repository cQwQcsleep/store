package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/graphics/Interpolatable;", "", "lerp", "other", "t", "", "Companion", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface Interpolatable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/graphics/Interpolatable$Companion;", "", "<init>", "()V", "lerp", "a", "b", "t", "", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0030 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:19:0x0031 A[RETURN] */
        public final Object lerp(Object a, Object b, float t) {
            if (Intrinsics.areEqual(a, b)) {
                if (t < 0.5f) {
                    return a;
                }
                return b;
            }
            Object objLerp = a instanceof Interpolatable ? ((Interpolatable) a).lerp(b, t) : null;
            if (objLerp == null && (b instanceof Interpolatable)) {
                objLerp = ((Interpolatable) b).lerp(a, 1.0f - t);
            }
            if (objLerp != null) {
                return objLerp;
            }
            if (t < 0.5f) {
                return a;
            }
            return b;
        }
    }

    Object lerp(Object other, float t);
}
