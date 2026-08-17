package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.DropUnlessLifecycleKt;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0003¢\u0006\u0002\u0010\b\u001a+\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0007¢\u0006\u0002\u0010\n\u001a+\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0007¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"dropUnlessStateIsAtLeast", "Lkotlin/Function0;", "", "state", "Landroidx/lifecycle/Lifecycle$State;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "block", "(Landroidx/lifecycle/Lifecycle$State;Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function0;", "dropUnlessStarted", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function0;", "dropUnlessResumed", "lifecycle-runtime-compose_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DropUnlessLifecycleKt {
    public static Unit a(LifecycleOwner lifecycleOwner, Lifecycle.State state, Function0 function0) {
        if (lifecycleOwner.getLifecycle().getState().isAtLeast(state)) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    public static final Function0<Unit> dropUnlessResumed(LifecycleOwner lifecycleOwner, Function0<Unit> function0, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            lifecycleOwner = (LifecycleOwner) composer.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
        }
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1331131589, i, -1, "androidx.lifecycle.compose.dropUnlessResumed (DropUnlessLifecycle.kt:99)");
        }
        int i3 = i << 3;
        Function0<Unit> function0DropUnlessStateIsAtLeast = dropUnlessStateIsAtLeast(Lifecycle.State.RESUMED, lifecycleOwner2, function0, composer, (i3 & 112) | 6 | (i3 & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return function0DropUnlessStateIsAtLeast;
    }

    public static final Function0<Unit> dropUnlessStarted(LifecycleOwner lifecycleOwner, Function0<Unit> function0, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            lifecycleOwner = (LifecycleOwner) composer.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
        }
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1207869935, i, -1, "androidx.lifecycle.compose.dropUnlessStarted (DropUnlessLifecycle.kt:77)");
        }
        int i3 = i << 3;
        Function0<Unit> function0DropUnlessStateIsAtLeast = dropUnlessStateIsAtLeast(Lifecycle.State.STARTED, lifecycleOwner2, function0, composer, (i3 & 112) | 6 | (i3 & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return function0DropUnlessStateIsAtLeast;
    }

    private static final Function0<Unit> dropUnlessStateIsAtLeast(final Lifecycle.State state, final LifecycleOwner lifecycleOwner, final Function0<Unit> function0, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            lifecycleOwner = (LifecycleOwner) composer.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2057956404, i, -1, "androidx.lifecycle.compose.dropUnlessStateIsAtLeast (DropUnlessLifecycle.kt:47)");
        }
        if (state == Lifecycle.State.DESTROYED) {
            w01.a("Target state is not allowed to be `Lifecycle.State.DESTROYED` because Compose disposes of the composition before `Lifecycle.Event.ON_DESTROY` observers are invoked.");
            return null;
        }
        boolean zChangedInstance = composer.changedInstance(lifecycleOwner) | ((((i & 14) ^ 6) > 4 && composer.changed(state.ordinal())) || (i & 6) == 4) | ((((i & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 && composer.changed(function0)) || (i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: py3
                public final Object invoke() {
                    return DropUnlessLifecycleKt.a(lifecycleOwner, state, function0);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function0<Unit> function1 = (Function0) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return function1;
    }
}
