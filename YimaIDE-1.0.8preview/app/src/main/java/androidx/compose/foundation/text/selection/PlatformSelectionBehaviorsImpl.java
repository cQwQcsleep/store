package androidx.compose.foundation.text.selection;

import android.app.RemoteAction;
import android.content.Context;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope_androidKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.Mutex$DefaultImpls;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\"\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0096@¢\u0006\u0004\b\"\u0010#J \u0010$\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0082@¢\u0006\u0004\b&\u0010#J*\u0010'\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)H\u0096@¢\u0006\u0004\b*\u0010+J \u0010,\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0096@¢\u0006\u0004\b-\u0010#J(\u0010.\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b0\u00101J<\u00104\u001a\u00020%*\u0002052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020%07¢\u0006\u0002\b8H\u0000¢\u0006\u0004\b9\u0010:J\u001f\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e¢\u0006\u0004\b=\u0010>J?\u0010?\u001a\u0004\u0018\u0001H@\"\u0004\b\u0000\u0010@2'\u0010A\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H@0C\u0012\u0006\u0012\u0004\u0018\u0001030B¢\u0006\u0002\b8H\u0082@¢\u0006\u0002\u0010DR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviorsImpl;", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "context", "Landroid/content/Context;", "selectedTextType", "Landroidx/compose/foundation/text/selection/SelectedTextType;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Landroid/content/Context;Landroidx/compose/foundation/text/selection/SelectedTextType;Landroidx/compose/ui/text/intl/LocaleList;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "textClassificationSession", "Landroid/view/textclassifier/TextClassifier;", "<set-?>", "Landroidx/compose/foundation/text/selection/TextClassificationResult;", "textClassificationResult", "getTextClassificationResult", "()Landroidx/compose/foundation/text/selection/TextClassificationResult;", "setTextClassificationResult", "(Landroidx/compose/foundation/text/selection/TextClassificationResult;)V", "textClassificationResult$delegate", "Landroidx/compose/runtime/MutableState;", "androidLocalList", "Landroid/os/LocaleList;", "getAndroidLocalList", "()Landroid/os/LocaleList;", "suggestSelectionForLongPressOrDoubleClick", "Landroidx/compose/ui/text/TextRange;", "text", "", "selection", "suggestSelectionForLongPressOrDoubleClick-pYaCw-w", "(Ljava/lang/CharSequence;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShowContextMenuOrSelectionToolbar", "", "onShowContextMenuOrSelectionToolbar-Sb-Bc2M", "onShowContextMenu", "secondaryClickLocation", "Landroidx/compose/ui/geometry/Offset;", "onShowContextMenu-_2OEclM", "(Ljava/lang/CharSequence;JLandroidx/compose/ui/geometry/Offset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShowSelectionToolbar", "onShowSelectionToolbar-Sb-Bc2M", "classifyText", "textClassifier", "classifyText-M8tDOmk", "(Ljava/lang/CharSequence;JLandroid/view/textclassifier/TextClassifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AssistantItemKey", "", "addSmartSelectionTextContextMenuItems", "Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "child", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "addSmartSelectionTextContextMenuItems-YmzfRxQ$foundation", "(Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;Ljava/lang/CharSequence;JLkotlin/jvm/functions/Function1;)V", "tryGetTextClassification", "Landroid/view/textclassifier/TextClassification;", "tryGetTextClassification-FDrldGo", "(Ljava/lang/CharSequence;J)Landroid/view/textclassifier/TextClassification;", "requireTextClassificationSession", "T", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PlatformSelectionBehaviorsImpl implements PlatformSelectionBehaviors {
    public static final int $stable = 8;
    private final Context context;
    private final CoroutineContext coroutineContext;
    private final LocaleList localeList;
    private final SelectedTextType selectedTextType;
    private TextClassifier textClassificationSession;
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);

    /* JADX INFO: renamed from: textClassificationResult$delegate, reason: from kotlin metadata */
    private final MutableState textClassificationResult = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
    private final Object AssistantItemKey = new Object();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2", f = "PlatformSelectionBehaviors.android.kt", i = {0, 1}, l = {369, 273, 282}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$0"}, v = 1)
    public static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<TextClassifier, Continuation<? super T>, Object> $block;
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$1", f = "PlatformSelectionBehaviors.android.kt", i = {}, l = {283}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
            final /* synthetic */ Function2<TextClassifier, Continuation<? super T>, Object> $block;
            final /* synthetic */ TextClassifier $textClassificationSession;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(TextClassifier textClassifier, Function2<? super TextClassifier, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$textClassificationSession = textClassifier;
                this.$block = function2;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$textClassificationSession, this.$block, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i != 0) {
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                        return obj;
                    }
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                TextClassifier textClassifier = this.$textClassificationSession;
                if (textClassifier == null) {
                    return null;
                }
                Function2<TextClassifier, Continuation<? super T>, Object> function2 = this.$block;
                this.label = 1;
                Object objInvoke = function2.invoke(textClassifier, this);
                return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function2<? super TextClassifier, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlatformSelectionBehaviorsImpl.this.new AnonymousClass2(this.$block, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:36:0x0090 A[RETURN] */
        public final Object invokeSuspend(Object obj) throws Throwable {
            Mutex mutex;
            PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl;
            Mutex mutex2;
            TextClassifier textClassifier;
            Object objWithTimeoutOrNull;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    mutex = PlatformSelectionBehaviorsImpl.this.mutex;
                    platformSelectionBehaviorsImpl = PlatformSelectionBehaviorsImpl.this;
                    this.L$0 = mutex;
                    this.L$1 = platformSelectionBehaviorsImpl;
                    this.label = 1;
                    if (mutex.lock((Object) null, this) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            ResultKt.throwOnFailure(obj);
                            return obj;
                        }
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    mutex2 = (Mutex) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        textClassifier = (TextClassifier) obj;
                        mutex = mutex2;
                        mutex.unlock((Object) null);
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(textClassifier, this.$block, null);
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(200L, anonymousClass1, this);
                        if (objWithTimeoutOrNull == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return objWithTimeoutOrNull;
                    } catch (Throwable th) {
                        th = th;
                        mutex2.unlock((Object) null);
                        throw th;
                    }
                }
                platformSelectionBehaviorsImpl = (PlatformSelectionBehaviorsImpl) this.L$1;
                Mutex mutex3 = (Mutex) this.L$0;
                ResultKt.throwOnFailure(obj);
                mutex = mutex3;
                textClassifier = platformSelectionBehaviorsImpl.textClassificationSession;
                if (textClassifier == null || textClassifier.isDestroyed()) {
                    PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1 platformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1 = new PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1(platformSelectionBehaviorsImpl, null);
                    this.L$0 = mutex;
                    this.L$1 = null;
                    this.label = 2;
                    Object objWithTimeoutOrNull2 = TimeoutKt.withTimeoutOrNull(300L, platformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1, this);
                    if (objWithTimeoutOrNull2 != coroutine_suspended) {
                        mutex2 = mutex;
                        obj = objWithTimeoutOrNull2;
                        textClassifier = (TextClassifier) obj;
                        mutex = mutex2;
                        mutex.unlock((Object) null);
                        AnonymousClass1 anonymousClass2 = new AnonymousClass1(textClassifier, this.$block, null);
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(200L, anonymousClass2, this);
                        if (objWithTimeoutOrNull == coroutine_suspended) {
                            return objWithTimeoutOrNull;
                        }
                    }
                } else {
                    mutex.unlock((Object) null);
                    AnonymousClass1 anonymousClass3 = new AnonymousClass1(textClassifier, this.$block, null);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 3;
                    objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(200L, anonymousClass3, this);
                    if (objWithTimeoutOrNull == coroutine_suspended) {
                        return objWithTimeoutOrNull;
                    }
                }
                return coroutine_suspended;
            } catch (Throwable th2) {
                th = th2;
                mutex2 = mutex;
                mutex2.unlock((Object) null);
                throw th;
            }
        }
    }

    public PlatformSelectionBehaviorsImpl(CoroutineContext coroutineContext, Context context, SelectedTextType selectedTextType, LocaleList localeList) {
        this.coroutineContext = coroutineContext;
        this.context = context;
        this.selectedTextType = selectedTextType;
        this.localeList = localeList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX INFO: renamed from: classifyText-M8tDOmk, reason: not valid java name */
    public final Object m1752classifyTextM8tDOmk(CharSequence charSequence, long j, TextClassifier textClassifier, Continuation<? super Unit> continuation) {
        PlatformSelectionBehaviorsImpl$classifyText$1 platformSelectionBehaviorsImpl$classifyText$1;
        Mutex mutex;
        TextClassifier textClassifier2;
        long j2;
        CharSequence charSequence2;
        TextClassification textClassificationClassifyText;
        Mutex mutex2;
        long j3;
        CharSequence charSequence3;
        if (continuation instanceof PlatformSelectionBehaviorsImpl$classifyText$1) {
            platformSelectionBehaviorsImpl$classifyText$1 = (PlatformSelectionBehaviorsImpl$classifyText$1) continuation;
            int i = platformSelectionBehaviorsImpl$classifyText$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                platformSelectionBehaviorsImpl$classifyText$1.label = i - Integer.MIN_VALUE;
            } else {
                platformSelectionBehaviorsImpl$classifyText$1 = new PlatformSelectionBehaviorsImpl$classifyText$1(this, continuation);
            }
        } else {
            platformSelectionBehaviorsImpl$classifyText$1 = new PlatformSelectionBehaviorsImpl$classifyText$1(this, continuation);
        }
        Object obj = platformSelectionBehaviorsImpl$classifyText$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = platformSelectionBehaviorsImpl$classifyText$1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                mutex = this.mutex;
                platformSelectionBehaviorsImpl$classifyText$1.L$0 = charSequence;
                platformSelectionBehaviorsImpl$classifyText$1.L$1 = textClassifier;
                platformSelectionBehaviorsImpl$classifyText$1.L$2 = mutex;
                platformSelectionBehaviorsImpl$classifyText$1.J$0 = j;
                platformSelectionBehaviorsImpl$classifyText$1.label = 1;
                if (mutex.lock((Object) null, platformSelectionBehaviorsImpl$classifyText$1) != coroutine_suspended) {
                    textClassifier2 = textClassifier;
                    j2 = j;
                    charSequence2 = charSequence;
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                j2 = platformSelectionBehaviorsImpl$classifyText$1.J$0;
                mutex = (Mutex) platformSelectionBehaviorsImpl$classifyText$1.L$2;
                textClassifier2 = (TextClassifier) platformSelectionBehaviorsImpl$classifyText$1.L$1;
                charSequence2 = (CharSequence) platformSelectionBehaviorsImpl$classifyText$1.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                long j4 = platformSelectionBehaviorsImpl$classifyText$1.J$0;
                mutex2 = (Mutex) platformSelectionBehaviorsImpl$classifyText$1.L$2;
                textClassificationClassifyText = (TextClassification) platformSelectionBehaviorsImpl$classifyText$1.L$1;
                CharSequence charSequence4 = (CharSequence) platformSelectionBehaviorsImpl$classifyText$1.L$0;
                ResultKt.throwOnFailure(obj);
                charSequence3 = charSequence4;
                j3 = j4;
            }
            try {
                setTextClassificationResult(new TextClassificationResult(charSequence3, j3, textClassificationClassifyText, null));
                Unit unit = Unit.INSTANCE;
                return Unit.INSTANCE;
            } finally {
                mutex2.unlock((Object) null);
            }
            TextClassificationResult textClassificationResult = getTextClassificationResult();
            if (textClassificationResult != null && PlatformSelectionBehaviors_androidKt.m1758canReuseh5sm0ck(textClassificationResult, charSequence2, j2)) {
                Unit unit2 = Unit.INSTANCE;
                mutex.unlock((Object) null);
                return unit2;
            }
            Unit unit3 = Unit.INSTANCE;
            mutex.unlock((Object) null);
            textClassificationClassifyText = textClassifier2.classifyText(new TextClassification.Request.Builder(charSequence2, TextRange.getMin-impl(j2), TextRange.getMax-impl(j2)).setDefaultLocales(getAndroidLocalList()).build());
            Mutex mutex3 = this.mutex;
            platformSelectionBehaviorsImpl$classifyText$1.L$0 = charSequence2;
            platformSelectionBehaviorsImpl$classifyText$1.L$1 = textClassificationClassifyText;
            platformSelectionBehaviorsImpl$classifyText$1.L$2 = mutex3;
            platformSelectionBehaviorsImpl$classifyText$1.J$0 = j2;
            platformSelectionBehaviorsImpl$classifyText$1.label = 2;
            if (mutex3.lock((Object) null, platformSelectionBehaviorsImpl$classifyText$1) != coroutine_suspended) {
                mutex2 = mutex3;
                j3 = j2;
                charSequence3 = charSequence2;
                setTextClassificationResult(new TextClassificationResult(charSequence3, j3, textClassificationClassifyText, null));
                Unit unit4 = Unit.INSTANCE;
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        } catch (Throwable th) {
            mutex.unlock((Object) null);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final android.os.LocaleList getAndroidLocalList() {
        android.os.LocaleList androidLocaleList;
        LocaleList localeList = this.localeList;
        return (localeList == null || (androidLocaleList = TextClassifierHelperMethods.INSTANCE.toAndroidLocaleList(localeList)) == null) ? new android.os.LocaleList(Locale.Companion.getCurrent().getPlatformLocale()) : androidLocaleList;
    }

    private final TextClassificationResult getTextClassificationResult() {
        return (TextClassificationResult) this.textClassificationResult.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onShowContextMenuOrSelectionToolbar-Sb-Bc2M, reason: not valid java name */
    public final Object m1753onShowContextMenuOrSelectionToolbarSbBc2M(CharSequence charSequence, long j, Continuation<? super Unit> continuation) {
        return (charSequence.length() == 0 || TextRange.getCollapsed-impl(j)) ? Unit.INSTANCE : requireTextClassificationSession(new PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2(this, charSequence, j, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object requireTextClassificationSession(Function2<? super TextClassifier, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return BuildersKt.withContext(this.coroutineContext, new AnonymousClass2(function2, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextClassificationResult(TextClassificationResult textClassificationResult) {
        this.textClassificationResult.setValue(textClassificationResult);
    }

    /* JADX INFO: renamed from: addSmartSelectionTextContextMenuItems-YmzfRxQ$foundation, reason: not valid java name */
    public final void m1754addSmartSelectionTextContextMenuItemsYmzfRxQ$foundation(TextContextMenuBuilderScope textContextMenuBuilderScope, CharSequence charSequence, long j, Function1<? super TextContextMenuBuilderScope, Unit> function1) {
        TextClassification textClassificationM1755tryGetTextClassificationFDrldGo = m1755tryGetTextClassificationFDrldGo(charSequence, j);
        if (textClassificationM1755tryGetTextClassificationFDrldGo == null) {
            function1.invoke(textContextMenuBuilderScope);
            return;
        }
        if (!textClassificationM1755tryGetTextClassificationFDrldGo.getActions().isEmpty()) {
            TextContextMenuBuilderScope_androidKt.textClassificationItem(textContextMenuBuilderScope, this.AssistantItemKey, textClassificationM1755tryGetTextClassificationFDrldGo, 0);
        } else if (TextClassifierHelperMethods.INSTANCE.hasLegacyAssistItem$foundation(textClassificationM1755tryGetTextClassificationFDrldGo)) {
            TextContextMenuBuilderScope_androidKt.textClassificationItem(textContextMenuBuilderScope, this.AssistantItemKey, textClassificationM1755tryGetTextClassificationFDrldGo, -1);
        }
        function1.invoke(textContextMenuBuilderScope);
        List<RemoteAction> actions = textClassificationM1755tryGetTextClassificationFDrldGo.getActions();
        int size = actions.size();
        for (int i = 0; i < size; i++) {
            actions.get(i);
            if (i > 0) {
                TextContextMenuBuilderScope_androidKt.textClassificationItem(textContextMenuBuilderScope, this.AssistantItemKey, textClassificationM1755tryGetTextClassificationFDrldGo, i);
            }
        }
    }

    @Override // androidx.compose.foundation.text.selection.PlatformSelectionBehaviors
    /* JADX INFO: renamed from: onShowContextMenu-_2OEclM */
    public Object mo1747onShowContextMenu_2OEclM(CharSequence charSequence, long j, Offset offset, Continuation<? super Unit> continuation) {
        Object objM1753onShowContextMenuOrSelectionToolbarSbBc2M = m1753onShowContextMenuOrSelectionToolbarSbBc2M(charSequence, j, continuation);
        return objM1753onShowContextMenuOrSelectionToolbarSbBc2M == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM1753onShowContextMenuOrSelectionToolbarSbBc2M : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.text.selection.PlatformSelectionBehaviors
    /* JADX INFO: renamed from: onShowSelectionToolbar-Sb-Bc2M */
    public Object mo1748onShowSelectionToolbarSbBc2M(CharSequence charSequence, long j, Continuation<? super Unit> continuation) {
        Object objM1753onShowContextMenuOrSelectionToolbarSbBc2M = m1753onShowContextMenuOrSelectionToolbarSbBc2M(charSequence, j, continuation);
        return objM1753onShowContextMenuOrSelectionToolbarSbBc2M == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM1753onShowContextMenuOrSelectionToolbarSbBc2M : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.text.selection.PlatformSelectionBehaviors
    /* JADX INFO: renamed from: suggestSelectionForLongPressOrDoubleClick-pYaCw-w */
    public Object mo1749suggestSelectionForLongPressOrDoubleClickpYaCww(CharSequence charSequence, long j, Continuation<? super TextRange> continuation) {
        if (charSequence.length() == 0 || TextRange.getCollapsed-impl(j)) {
            return null;
        }
        return requireTextClassificationSession(new PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(charSequence, j, this, null), continuation);
    }

    /* JADX INFO: renamed from: tryGetTextClassification-FDrldGo, reason: not valid java name */
    public final TextClassification m1755tryGetTextClassificationFDrldGo(CharSequence text, long selection) {
        if (!Mutex$DefaultImpls.tryLock$default(this.mutex, null, 1, null)) {
            return null;
        }
        TextClassificationResult textClassificationResult = getTextClassificationResult();
        TextClassification textClassification = (textClassificationResult == null || !PlatformSelectionBehaviors_androidKt.m1758canReuseh5sm0ck(textClassificationResult, text, selection)) ? null : textClassificationResult.getTextClassification();
        Mutex$DefaultImpls.unlock$default(this.mutex, null, 1, null);
        return textClassification;
    }
}
