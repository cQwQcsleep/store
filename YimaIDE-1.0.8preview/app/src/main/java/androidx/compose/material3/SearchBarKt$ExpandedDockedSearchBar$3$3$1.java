package androidx.compose.material3;

import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.SearchBarKt$ExpandedDockedSearchBar$3$3$1", f = "SearchBar.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SearchBarKt$ExpandedDockedSearchBar$3$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SoftwareKeyboardController $softwareKeyboardController;
    final /* synthetic */ SearchBarState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBarKt$ExpandedDockedSearchBar$3$3$1(SearchBarState searchBarState, SoftwareKeyboardController softwareKeyboardController, Continuation<? super SearchBarKt$ExpandedDockedSearchBar$3$3$1> continuation) {
        super(2, continuation);
        this.$state = searchBarState;
        this.$softwareKeyboardController = softwareKeyboardController;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchBarKt$ExpandedDockedSearchBar$3$3$1(this.$state, this.$softwareKeyboardController, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        SoftwareKeyboardController softwareKeyboardController;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (this.$state.getTargetValue() == SearchBarValue.Collapsed && (softwareKeyboardController = this.$softwareKeyboardController) != null) {
            softwareKeyboardController.hide();
        }
        return Unit.INSTANCE;
    }
}
