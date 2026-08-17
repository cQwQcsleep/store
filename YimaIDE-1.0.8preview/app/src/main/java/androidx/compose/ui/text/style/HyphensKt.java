package androidx.compose.ui.text.style;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0086\b¢\u0006\u0004\b\b\u0010\t\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"isSpecified", "", "Landroidx/compose/ui/text/style/Hyphens;", "isSpecified--3fSNIE", "(I)Z", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-Kk21toE", "(ILkotlin/jvm/functions/Function0;)I", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class HyphensKt {
    /* JADX INFO: renamed from: isSpecified--3fSNIE, reason: not valid java name */
    public static final boolean m5796isSpecified3fSNIE(int i) {
        return i != 0;
    }

    /* JADX INFO: renamed from: takeOrElse-Kk21toE, reason: not valid java name */
    public static final int m5797takeOrElseKk21toE(int i, Function0<Hyphens> function0) {
        return i != 0 ? i : ((Hyphens) function0.invoke()).m5791unboximpl();
    }
}
