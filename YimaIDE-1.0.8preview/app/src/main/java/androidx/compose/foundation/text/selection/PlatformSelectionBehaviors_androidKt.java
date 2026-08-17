package androidx.compose.foundation.text.selection;

import android.content.Context;
import androidx.compose.foundation.text.contextmenu.ProcessText_androidKt;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.foundation.text.selection.SelectedTextType;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.intl.LocaleList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0001¢\u0006\u0002\u0010\u0017\u001a#\u0010\u0018\u001a\u00020\u0019*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0004\b\u001f\u0010 \u001aX\u0010!\u001a\u00020\"*\u00020#2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010&\u001a\u0004\u0018\u00010\r2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\"0(¢\u0006\u0002\b)H\u0000¢\u0006\u0002\b*\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"F\u0010\b\u001a$\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\t8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006+"}, d2 = {"LocalTextClassifierCoroutineContext", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lkotlin/coroutines/CoroutineContext;", "getLocalTextClassifierCoroutineContext", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "TEXT_CLASSIFIER_INITIALIZATION_TIMEOUT_MILLIS", "", "TEXT_CLASSIFICATION_TIMEOUT_MILLIS", "PlatformSelectionBehaviorsFactory", "Lkotlin/Function4;", "Landroid/content/Context;", "Landroidx/compose/foundation/text/selection/SelectedTextType;", "Landroidx/compose/ui/text/intl/LocaleList;", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "getPlatformSelectionBehaviorsFactory$annotations", "()V", "getPlatformSelectionBehaviorsFactory", "()Lkotlin/jvm/functions/Function4;", "setPlatformSelectionBehaviorsFactory", "(Lkotlin/jvm/functions/Function4;)V", "rememberPlatformSelectionBehaviors", "selectedTextType", "localeList", "(Landroidx/compose/foundation/text/selection/SelectedTextType;Landroidx/compose/ui/text/intl/LocaleList;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "canReuse", "", "Landroidx/compose/foundation/text/selection/TextClassificationResult;", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "canReuse-h5sm0ck", "(Landroidx/compose/foundation/text/selection/TextClassificationResult;Ljava/lang/CharSequence;J)Z", "addPlatformTextContextMenuItems", "", "Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "context", "editable", "platformSelectionBehaviors", "child", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "addPlatformTextContextMenuItems-71BSaZU", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PlatformSelectionBehaviors_androidKt {
    private static final ProvidableCompositionLocal<CoroutineContext> LocalTextClassifierCoroutineContext = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: k2b
        public final Object invoke() {
            return PlatformSelectionBehaviors_androidKt.a();
        }
    });
    private static Function4<? super CoroutineContext, ? super Context, ? super SelectedTextType, ? super LocaleList, ? extends PlatformSelectionBehaviors> PlatformSelectionBehaviorsFactory = new Function4() { // from class: l2b
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return PlatformSelectionBehaviors_androidKt.b((CoroutineContext) obj, (Context) obj2, (SelectedTextType) obj3, (LocaleList) obj4);
        }
    };
    private static final long TEXT_CLASSIFICATION_TIMEOUT_MILLIS = 200;
    private static final long TEXT_CLASSIFIER_INITIALIZATION_TIMEOUT_MILLIS = 300;

    public static CoroutineContext a() {
        return Dispatchers.getIO();
    }

    /* JADX INFO: renamed from: addPlatformTextContextMenuItems-71BSaZU, reason: not valid java name */
    public static final void m1757addPlatformTextContextMenuItems71BSaZU(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, boolean z, CharSequence charSequence, TextRange textRange, PlatformSelectionBehaviors platformSelectionBehaviors, Function1<? super TextContextMenuBuilderScope, Unit> function1) {
        if (charSequence != null && textRange != null && platformSelectionBehaviors != null && (platformSelectionBehaviors instanceof PlatformSelectionBehaviorsImpl)) {
            ((PlatformSelectionBehaviorsImpl) platformSelectionBehaviors).m1754addSmartSelectionTextContextMenuItemsYmzfRxQ$foundation(textContextMenuBuilderScope, charSequence, textRange.unbox-impl(), function1);
            ProcessText_androidKt.m1451addProcessedTextContextMenuItemsUAq72N0(textContextMenuBuilderScope, context, z, charSequence, textRange.unbox-impl());
            return;
        }
        function1.invoke(textContextMenuBuilderScope);
        if (charSequence == null || textRange == null) {
            return;
        }
        ProcessText_androidKt.m1451addProcessedTextContextMenuItemsUAq72N0(textContextMenuBuilderScope, context, z, charSequence, textRange.unbox-impl());
    }

    public static PlatformSelectionBehaviorsImpl b(CoroutineContext coroutineContext, Context context, SelectedTextType selectedTextType, LocaleList localeList) {
        return new PlatformSelectionBehaviorsImpl(coroutineContext, context, selectedTextType, localeList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: canReuse-h5sm0ck, reason: not valid java name */
    public static final boolean m1758canReuseh5sm0ck(TextClassificationResult textClassificationResult, CharSequence charSequence, long j) {
        return TextRange.equals-impl0(j, textClassificationResult.m1806getSelectiond9O1mEE()) && Intrinsics.areEqual(charSequence, textClassificationResult.getText());
    }

    public static final ProvidableCompositionLocal<CoroutineContext> getLocalTextClassifierCoroutineContext() {
        return LocalTextClassifierCoroutineContext;
    }

    public static final Function4<CoroutineContext, Context, SelectedTextType, LocaleList, PlatformSelectionBehaviors> getPlatformSelectionBehaviorsFactory() {
        return PlatformSelectionBehaviorsFactory;
    }

    public static /* synthetic */ void getPlatformSelectionBehaviorsFactory$annotations() {
    }

    public static final PlatformSelectionBehaviors rememberPlatformSelectionBehaviors(SelectedTextType selectedTextType, LocaleList localeList, Composer composer, int i) {
        composer.startReplaceGroup(430530635);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(430530635, i, -1, "androidx.compose.foundation.text.selection.rememberPlatformSelectionBehaviors (PlatformSelectionBehaviors.android.kt:95)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        CoroutineContext coroutineContext = (CoroutineContext) composer.consume(LocalTextClassifierCoroutineContext);
        boolean zChanged = composer.changed(coroutineContext) | composer.changed(context) | ((((i & 14) ^ 6) > 4 && composer.changed(selectedTextType.ordinal())) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer.changed(localeList)) || (i & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = (PlatformSelectionBehaviors) PlatformSelectionBehaviorsFactory.invoke(coroutineContext, context, selectedTextType, localeList);
            composer.updateRememberedValue(objRememberedValue);
        }
        PlatformSelectionBehaviors platformSelectionBehaviors = (PlatformSelectionBehaviors) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return platformSelectionBehaviors;
    }

    public static final void setPlatformSelectionBehaviorsFactory(Function4<? super CoroutineContext, ? super Context, ? super SelectedTextType, ? super LocaleList, ? extends PlatformSelectionBehaviors> function4) {
        PlatformSelectionBehaviorsFactory = function4;
    }
}
