package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuComponent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final /* synthetic */ class TextContextMenuModifierKt$collectTextContextMenuData$1$1 extends FunctionReferenceImpl implements Function1<Function1<? super TextContextMenuComponent, ? extends Boolean>, Unit> {
    public TextContextMenuModifierKt$collectTextContextMenuData$1$1(Object obj) {
        super(1, obj, TextContextMenuBuilderScope.class, "addFilter", "addFilter$foundation(Lkotlin/jvm/functions/Function1;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Function1<? super TextContextMenuComponent, Boolean>) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Function1<? super TextContextMenuComponent, Boolean> function1) {
        ((TextContextMenuBuilderScope) ((CallableReference) this).receiver).addFilter$foundation(function1);
    }
}
