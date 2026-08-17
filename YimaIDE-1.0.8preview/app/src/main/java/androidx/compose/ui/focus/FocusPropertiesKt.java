package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001H\u0002\u001a#\u0010\u0007\u001a\u00020\b*\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¨\u0006\u000b"}, d2 = {"toUsingEnterExitScope", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/focus/FocusDirection;", "Landroidx/compose/ui/focus/FocusRequester;", "focusProperties", "Landroidx/compose/ui/Modifier;", "scope", "Landroidx/compose/ui/focus/FocusProperties;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FocusPropertiesKt {
    public static final Modifier focusProperties(Modifier modifier, Function1<? super FocusProperties, Unit> function1) {
        return modifier.then(new FocusPropertiesElement(new FocusPropertiesKt$sam$androidx_compose_ui_focus_FocusPropertiesScope$0(function1)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<FocusEnterExitScope, Unit> toUsingEnterExitScope(final Function1<? super FocusDirection, FocusRequester> function1) {
        return new Function1<FocusEnterExitScope, Unit>() { // from class: androidx.compose.ui.focus.FocusPropertiesKt.toUsingEnterExitScope.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public final void invoke(FocusEnterExitScope focusEnterExitScope) {
                FocusRequester focusRequester = (FocusRequester) function1.invoke(FocusDirection.m2756boximpl(focusEnterExitScope.getRequestedFocusDirection()));
                FocusRequester.Companion companion = FocusRequester.INSTANCE;
                if (focusRequester == companion.getCancel()) {
                    focusEnterExitScope.cancelFocusChange();
                } else if (focusRequester != companion.getDefault()) {
                    FocusRequester.m2792requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((FocusEnterExitScope) obj);
                return Unit.INSTANCE;
            }
        };
    }
}
