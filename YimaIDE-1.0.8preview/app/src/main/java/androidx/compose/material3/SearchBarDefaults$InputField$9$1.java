package androidx.compose.material3;

import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.SearchBarDefaults$InputField$9$1", f = "SearchBar.kt", i = {}, l = {1442}, m = "invokeSuspend", n = {}, s = {})
public final class SearchBarDefaults$InputField$9$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ State<Boolean> $focused$delegate;
    final /* synthetic */ SearchBarState $searchBarState;
    final /* synthetic */ TextFieldState $textFieldState;
    int label;

    /* JADX INFO: renamed from: androidx.compose.material3.SearchBarDefaults$InputField$9$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.SearchBarDefaults$InputField$9$1$2", f = "SearchBar.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CharSequence, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineScope $coroutineScope;
        final /* synthetic */ State<Boolean> $focused$delegate;
        final /* synthetic */ Ref.IntRef $prevLength;
        final /* synthetic */ SearchBarState $searchBarState;
        /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.SearchBarDefaults$InputField$9$1$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.SearchBarDefaults$InputField$9$1$2$1", f = "SearchBar.kt", i = {}, l = {1438}, m = "invokeSuspend", n = {}, s = {})
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ SearchBarState $searchBarState;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(SearchBarState searchBarState, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$searchBarState = searchBarState;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$searchBarState, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SearchBarState searchBarState = this.$searchBarState;
                    this.label = 1;
                    if (searchBarState.animateToExpanded(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Ref.IntRef intRef, SearchBarState searchBarState, CoroutineScope coroutineScope, State<Boolean> state, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$prevLength = intRef;
            this.$searchBarState = searchBarState;
            this.$coroutineScope = coroutineScope;
            this.$focused$delegate = state;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$prevLength, this.$searchBarState, this.$coroutineScope, this.$focused$delegate, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(CharSequence charSequence, Continuation<? super Unit> continuation) {
            return create(charSequence, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            int length = ((CharSequence) this.L$0).length();
            if (length > this.$prevLength.element && SearchBarDefaults.InputField$lambda$5(this.$focused$delegate) && !SearchBarKt.isExpanded(this.$searchBarState)) {
                BuildersKt.launch$default(this.$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.$searchBarState, null), 3, (Object) null);
            }
            this.$prevLength.element = length;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBarDefaults$InputField$9$1(SearchBarState searchBarState, TextFieldState textFieldState, CoroutineScope coroutineScope, State<Boolean> state, Continuation<? super SearchBarDefaults$InputField$9$1> continuation) {
        super(2, continuation);
        this.$searchBarState = searchBarState;
        this.$textFieldState = textFieldState;
        this.$coroutineScope = coroutineScope;
        this.$focused$delegate = state;
    }

    public static CharSequence b(TextFieldState textFieldState) {
        return textFieldState.getText();
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchBarDefaults$InputField$9$1(this.$searchBarState, this.$textFieldState, this.$coroutineScope, this.$focused$delegate, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!SearchBarKt.isExpanded(this.$searchBarState)) {
                Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = this.$textFieldState.getText().length();
                final TextFieldState textFieldState = this.$textFieldState;
                Flow flowOnEach = FlowKt.onEach(SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.material3.o3
                    public final Object invoke() {
                        return SearchBarDefaults$InputField$9$1.b(textFieldState);
                    }
                }), new AnonymousClass2(intRef, this.$searchBarState, this.$coroutineScope, this.$focused$delegate, null));
                FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$9$1.3
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((CharSequence) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(CharSequence charSequence, Continuation<? super Unit> continuation) {
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flowOnEach.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
