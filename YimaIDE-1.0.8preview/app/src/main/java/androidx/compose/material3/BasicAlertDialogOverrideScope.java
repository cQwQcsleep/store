package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.window.DialogProperties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001B>\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\n¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Landroidx/compose/material3/BasicAlertDialogOverrideScope;", "", "onDismissRequest", "Lkotlin/Function0;", "", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "content", "Landroidx/compose/runtime/Composable;", "<init>", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;)V", "getOnDismissRequest", "()Lkotlin/jvm/functions/Function0;", "getModifier", "()Landroidx/compose/ui/Modifier;", "getProperties", "()Landroidx/compose/ui/window/DialogProperties;", "getContent", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BasicAlertDialogOverrideScope {
    public static final int $stable = 0;
    private final Function2<Composer, Integer, Unit> content;
    private final Modifier modifier;
    private final Function0<Unit> onDismissRequest;
    private final DialogProperties properties;

    public /* synthetic */ BasicAlertDialogOverrideScope(Function0 function0, Modifier modifier, DialogProperties dialogProperties, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? Modifier.INSTANCE : modifier, (i & 4) != 0 ? new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null) : dialogProperties, function2);
    }

    public final Function2<Composer, Integer, Unit> getContent() {
        return this.content;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    public final Function0<Unit> getOnDismissRequest() {
        return this.onDismissRequest;
    }

    public final DialogProperties getProperties() {
        return this.properties;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BasicAlertDialogOverrideScope(Function0<Unit> function0, Modifier modifier, DialogProperties dialogProperties, Function2<? super Composer, ? super Integer, Unit> function2) {
        this.onDismissRequest = function0;
        this.modifier = modifier;
        this.properties = dialogProperties;
        this.content = function2;
    }
}
