package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001aP\u0010\u0000\u001a\u00020\u00012\u0014\b\u0006\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\u0010\b\u0006\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00062\u0010\b\u0006\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00062\u0006\u0010\b\u001a\u00020\tH\u0081\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"rememberClipboardEventsHandler", "", "onPaste", "Lkotlin/Function1;", "Landroidx/compose/ui/text/AnnotatedString;", "onCopy", "Lkotlin/Function0;", "onCut", "isEnabled", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ClipboardEventsHandler_jvmKt {
    public static final void rememberClipboardEventsHandler(Function1<? super AnnotatedString, Unit> function1, Function0<AnnotatedString> function0, Function0<AnnotatedString> function2, boolean z, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            AnonymousClass2 anonymousClass2 = new Function0() { // from class: androidx.compose.foundation.text.ClipboardEventsHandler_jvmKt.rememberClipboardEventsHandler.2
                public final Void invoke() {
                    return null;
                }
            };
        }
        if ((i2 & 4) != 0) {
            AnonymousClass3 anonymousClass3 = AnonymousClass3.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.ClipboardEventsHandler_jvmKt$rememberClipboardEventsHandler$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass3 implements Function0 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public final Void invoke() {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.ClipboardEventsHandler_jvmKt$rememberClipboardEventsHandler$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function1<AnnotatedString, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnnotatedString) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(AnnotatedString annotatedString) {
        }
    }
}
