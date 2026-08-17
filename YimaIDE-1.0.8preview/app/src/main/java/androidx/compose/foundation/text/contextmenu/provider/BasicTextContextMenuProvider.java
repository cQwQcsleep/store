package androidx.compose.foundation.text.contextmenu.provider;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001:\u0001!B_\u0012V\u0010\u0002\u001aR\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0003¢\u0006\u0002\b\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u001dJ\u001b\u0010\u001e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\rR`\u0010\u0002\u001aR\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0003¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u0007\u001a\b\u0018\u00010\u0015R\u00020\u00002\f\u0010\u0014\u001a\b\u0018\u00010\u0015R\u00020\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider;", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuProvider;", "contextMenuBlock", "Lkotlin/Function3;", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;", "Lkotlin/ParameterName;", "name", "session", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "dataProvider", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "anchorLayoutCoordinates", "", "Landroidx/compose/runtime/Composable;", "<init>", "(Lkotlin/jvm/functions/Function5;)V", "Lkotlin/jvm/functions/Function5;", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "<set-?>", "Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider$SessionImpl;", "getSession", "()Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider$SessionImpl;", "setSession", "(Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider$SessionImpl;)V", "session$delegate", "Landroidx/compose/runtime/MutableState;", "showTextContextMenu", "(Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ContextMenu", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "cancel", "SessionImpl", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BasicTextContextMenuProvider implements TextContextMenuProvider {
    public static final int $stable = 0;
    private final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> contextMenuBlock;
    private final MutatorMutex mutatorMutex = new MutatorMutex();

    /* JADX INFO: renamed from: session$delegate, reason: from kotlin metadata */
    private final MutableState session = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\nH\u0016J\u000e\u0010\f\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider$SessionImpl;", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;", "dataProvider", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "<init>", "(Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider;Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;)V", "getDataProvider", "()Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "channel", "Lkotlinx/coroutines/channels/Channel;", "", "close", "awaitClose", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class SessionImpl implements TextContextMenuSession {
        private final Channel<Unit> channel = ChannelKt.Channel$default(0, (BufferOverflow) null, (Function1) null, 7, (Object) null);
        private final TextContextMenuDataProvider dataProvider;

        public SessionImpl(TextContextMenuDataProvider textContextMenuDataProvider) {
            this.dataProvider = textContextMenuDataProvider;
        }

        public final Object awaitClose(Continuation<? super Unit> continuation) {
            Object objReceive = this.channel.receive(continuation);
            return objReceive == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objReceive : Unit.INSTANCE;
        }

        @Override // androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession
        public void close() {
            this.channel.trySend-JP2dKIU(Unit.INSTANCE);
        }

        public final TextContextMenuDataProvider getDataProvider() {
            return this.dataProvider;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProvider$showTextContextMenu$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProvider$showTextContextMenu$2", f = "BasicTextContextMenuProvider.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ SessionImpl $localSession;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SessionImpl sessionImpl, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$localSession = sessionImpl;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return BasicTextContextMenuProvider.this.new AnonymousClass2(this.$localSession, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    BasicTextContextMenuProvider.this.setSession(this.$localSession);
                    SessionImpl sessionImpl = this.$localSession;
                    this.label = 1;
                    if (sessionImpl.awaitClose(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                BasicTextContextMenuProvider.this.setSession(null);
                this = Unit.INSTANCE;
                return this;
            } catch (Throwable th) {
                BasicTextContextMenuProvider.this.setSession(null);
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BasicTextContextMenuProvider(Function5<? super TextContextMenuSession, ? super TextContextMenuDataProvider, ? super Function0<? extends LayoutCoordinates>, ? super Composer, ? super Integer, Unit> function5) {
        this.contextMenuBlock = function5;
    }

    public static Unit a(BasicTextContextMenuProvider basicTextContextMenuProvider, Function0 function0, int i, Composer composer, int i2) {
        basicTextContextMenuProvider.ContextMenu(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(BasicTextContextMenuProvider basicTextContextMenuProvider, Function0 function0, int i, Composer composer, int i2) {
        basicTextContextMenuProvider.ContextMenu(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private final SessionImpl getSession() {
        return (SessionImpl) this.session.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSession(SessionImpl sessionImpl) {
        this.session.setValue(sessionImpl);
    }

    public final void ContextMenu(final Function0<? extends LayoutCoordinates> function0, Composer composer, final int i) {
        int i2;
        final Function0<? extends LayoutCoordinates> function1;
        Composer composerStartRestartGroup = composer.startRestartGroup(723898654);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(723898654, i2, -1, "androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProvider.ContextMenu (BasicTextContextMenuProvider.kt:137)");
            }
            SessionImpl session = getSession();
            if (session == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jq0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTextContextMenuProvider.b(this.b, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            function1 = function0;
            this.contextMenuBlock.invoke(session, session.getDataProvider(), function1, composerStartRestartGroup, Integer.valueOf((i2 << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function1 = function0;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: kq0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTextContextMenuProvider.a(this.b, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final void cancel() {
        SessionImpl session = getSession();
        if (session != null) {
            session.close();
        }
    }

    @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider
    public Object showTextContextMenu(TextContextMenuDataProvider textContextMenuDataProvider, Continuation<? super Unit> continuation) {
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass2(new SessionImpl(textContextMenuDataProvider), null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }
}
