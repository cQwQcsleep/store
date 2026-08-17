package androidx.compose.material3;

import androidx.compose.material3.AppBarDslKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"rememberAppBarOverflowState", "Landroidx/compose/material3/AppBarOverflowState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/AppBarOverflowState;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AppBarDslKt {
    public static AppBarOverflowStateImpl a() {
        return new AppBarOverflowStateImpl();
    }

    public static final AppBarOverflowState rememberAppBarOverflowState(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(209837519, i, -1, "androidx.compose.material3.rememberAppBarOverflowState (AppBarDsl.kt:264)");
        }
        Object[] objArr = new Object[0];
        Saver<AppBarOverflowStateImpl, ?> saver = AppBarOverflowStateImpl.INSTANCE.getSaver();
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: xb0
                public final Object invoke() {
                    return AppBarDslKt.a();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        AppBarOverflowStateImpl appBarOverflowStateImpl = (AppBarOverflowStateImpl) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue, composer, AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return appBarOverflowStateImpl;
    }
}
