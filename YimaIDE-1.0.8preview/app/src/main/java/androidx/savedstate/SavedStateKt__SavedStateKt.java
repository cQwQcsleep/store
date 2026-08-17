package androidx.savedstate;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001a8\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"read", "T", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "block", "Lkotlin/Function1;", "Landroidx/savedstate/SavedStateReader;", "Lkotlin/ExtensionFunctionType;", "(Landroid/os/Bundle;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "write", "Landroidx/savedstate/SavedStateWriter;", "savedstate_release"}, k = 5, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK, xs = "androidx/savedstate/SavedStateKt")
final /* synthetic */ class SavedStateKt__SavedStateKt {
    public static final <T> T read(Bundle bundle, Function1<? super SavedStateReader, ? extends T> function1) {
        bundle.getClass();
        function1.getClass();
        return (T) function1.invoke(SavedStateReader.m6309boximpl(SavedStateReader.m6310constructorimpl(bundle)));
    }

    public static final <T> T write(Bundle bundle, Function1<? super SavedStateWriter, ? extends T> function1) {
        bundle.getClass();
        function1.getClass();
        return (T) function1.invoke(SavedStateWriter.m6394boximpl(SavedStateWriter.m6396constructorimpl(bundle)));
    }
}
