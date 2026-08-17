package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.gestures.ScrollableContainerNode;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aA\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u000b\u001aK\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000e\u001aS\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0011\u001a{\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u0017\u001a\u0085\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0018\u001aq\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u0019\u001a\u008d\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u001a\u001a\u0085\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u001b\u001aA\u0010\u001c\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u001e\b\u0004\u0010\u001d\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020\u00010\u001eH\u0080\b\u001a\f\u0010(\u001a\u00020\u0003*\u00020)H\u0000\u001a\u0010\u0010*\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\f\u0010+\u001a\u00020\u0003*\u00020,H\u0002\u001a\f\u0010-\u001a\u00020\u0003*\u00020,H\u0002\u001a\f\u0010.\u001a\u00020\u0003*\u00020,H\u0002\"\u0018\u0010 \u001a\u00020\u0003*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0018\u0010$\u001a\u00020\u0003*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#\"\u0018\u0010&\u001a\u00020\u0003*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010#¨\u0006/"}, d2 = {"clickable", "Landroidx/compose/ui/Modifier;", "enabled", "", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "clickable-XHw0xAI", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "clickable-oSLSa3U", "indication", "Landroidx/compose/foundation/Indication;", "clickable-O2vRcR0", "combinedClickable", "onLongClickLabel", "onLongClick", "onDoubleClick", "hapticFeedbackEnabled", "combinedClickable-f5TDLPQ", "combinedClickable-hoGz1lA", "combinedClickable-cJG_KMw", "combinedClickable-auXiCPI", "combinedClickable-XVZzFYc", "clickableWithIndicationIfNeeded", "createClickable", "Lkotlin/Function2;", "Landroidx/compose/foundation/IndicationNodeFactory;", "isPress", "Landroidx/compose/ui/input/key/KeyEvent;", "isPress-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isClick", "isClick-ZmokQxo", "isEnter", "isEnter-ZmokQxo", "hasScrollableContainer", "Landroidx/compose/ui/node/TraversableNode;", "unsupportedIndicationExceptionMessage", "changedToUp", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "changedToDownIgnoreConsumed", "isMovingIgnoreConsumed", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ClickableKt {
    /* JADX WARN: Code duplicated, block: B:8:0x0013  */
    public static boolean a(Ref.BooleanRef booleanRef, TraversableNode traversableNode) {
        boolean z;
        if (booleanRef.element) {
            z = true;
        } else {
            traversableNode.getClass();
            if (((ScrollableContainerNode) traversableNode).getEnabled()) {
                z = true;
            } else {
                z = false;
            }
        }
        booleanRef.element = z;
        return !z;
    }

    public static Modifier b(boolean z, String str, Role role, Function0 function0, Modifier modifier, Composer composer, int i) {
        MutableInteractionSource mutableInteractionSource;
        composer.startReplaceGroup(-756081143);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-756081143, i, -1, "androidx.compose.foundation.clickable.<anonymous> (Clickable.kt:141)");
        }
        Indication indication = (Indication) composer.consume(IndicationKt.getLocalIndication());
        if (indication instanceof IndicationNodeFactory) {
            composer.startReplaceGroup(-1604682242);
            composer.endReplaceGroup();
            mutableInteractionSource = null;
        } else {
            composer.startReplaceGroup(-1604549624);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceGroup();
        }
        Modifier modifierM354clickableO2vRcR0 = m354clickableO2vRcR0(Modifier.Companion, mutableInteractionSource, indication, z, str, role, function0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierM354clickableO2vRcR0;
    }

    public static Modifier c(boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, boolean z2, Function0 function2, Modifier modifier, Composer composer, int i) {
        MutableInteractionSource mutableInteractionSource;
        composer.startReplaceGroup(-1534186401);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1534186401, i, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:350)");
        }
        Indication indication = (Indication) composer.consume(IndicationKt.getLocalIndication());
        if (indication instanceof IndicationNodeFactory) {
            composer.startReplaceGroup(2095040488);
            composer.endReplaceGroup();
            mutableInteractionSource = null;
        } else {
            composer.startReplaceGroup(2095173106);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceGroup();
        }
        Modifier modifierM362combinedClickableauXiCPI = m362combinedClickableauXiCPI(Modifier.Companion, mutableInteractionSource, indication, z, str, role, str2, function0, function1, z2, function2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierM362combinedClickableauXiCPI;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedToDownIgnoreConsumed(IndirectPointerInputChange indirectPointerInputChange) {
        return !indirectPointerInputChange.getPreviousPressed() && indirectPointerInputChange.getPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedToUp(IndirectPointerInputChange indirectPointerInputChange) {
        return (indirectPointerInputChange.isConsumed() || !indirectPointerInputChange.getPreviousPressed() || indirectPointerInputChange.getPressed()) ? false : true;
    }

    /* JADX INFO: renamed from: clickable-O2vRcR0, reason: not valid java name */
    public static final Modifier m354clickableO2vRcR0(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z, final String str, final Role role, final Function0<Unit> function0) {
        ClickableElement clickableElementComposed$default;
        if (indication instanceof IndicationNodeFactory) {
            clickableElementComposed$default = new ClickableElement(mutableInteractionSource, (IndicationNodeFactory) indication, false, z, str, role, function0, null);
        } else if (indication == null) {
            clickableElementComposed$default = new ClickableElement(mutableInteractionSource, null, false, z, str, role, function0, null);
        } else if (mutableInteractionSource != null) {
            clickableElementComposed$default = IndicationKt.indication(Modifier.Companion, mutableInteractionSource, indication).then(new ClickableElement(mutableInteractionSource, null, false, z, str, role, function0, null));
        } else {
            clickableElementComposed$default = ComposedModifierKt.composed$default(Modifier.Companion, (Function1) null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
                public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                    composer.startReplaceGroup(-1525724089);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, i, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:634)");
                    }
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
                    Modifier modifierThen = IndicationKt.indication(Modifier.Companion, mutableInteractionSource2, indication).then(new ClickableElement(mutableInteractionSource2, null, false, z, str, role, function0, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    return modifierThen;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            }, 1, (Object) null);
        }
        return modifier.then(clickableElementComposed$default);
    }

    /* JADX INFO: renamed from: clickable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m355clickableO2vRcR0$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return m354clickableO2vRcR0(modifier, mutableInteractionSource, indication, z, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : role, function0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with new overload that only supports IndicationNodeFactory instances inside LocalIndication, and does not use composed")
    /* JADX INFO: renamed from: clickable-XHw0xAI, reason: not valid java name */
    public static final /* synthetic */ Modifier m356clickableXHw0xAI(Modifier modifier, final boolean z, final String str, final Role role, final Function0 function0) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$clickable-XHw0xAI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("clickable");
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", function0);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: p02
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ClickableKt.b(z, str, role, function0, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    /* JADX INFO: renamed from: clickable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m357clickableXHw0xAI$default(Modifier modifier, boolean z, String str, Role role, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m356clickableXHw0xAI(modifier, z, str, role, function0);
    }

    /* JADX INFO: renamed from: clickable-oSLSa3U, reason: not valid java name */
    public static final Modifier m358clickableoSLSa3U(Modifier modifier, boolean z, String str, Role role, MutableInteractionSource mutableInteractionSource, Function0<Unit> function0) {
        return modifier.then(new ClickableElement(mutableInteractionSource, null, true, z, str, role, function0, null));
    }

    /* JADX INFO: renamed from: clickable-oSLSa3U$default, reason: not valid java name */
    public static /* synthetic */ Modifier m359clickableoSLSa3U$default(Modifier modifier, boolean z, String str, Role role, MutableInteractionSource mutableInteractionSource, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m358clickableoSLSa3U(modifier, z, str, role, (i & 8) != 0 ? null : mutableInteractionSource, function0);
    }

    public static final Modifier clickableWithIndicationIfNeeded(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Indication indication, final Function2<? super MutableInteractionSource, ? super IndicationNodeFactory, ? extends Modifier> function2) {
        Modifier modifierThen;
        if (indication instanceof IndicationNodeFactory) {
            modifierThen = (Modifier) function2.invoke(mutableInteractionSource, indication);
        } else if (indication == null) {
            modifierThen = (Modifier) function2.invoke(mutableInteractionSource, (Object) null);
        } else {
            modifierThen = mutableInteractionSource != null ? IndicationKt.indication(Modifier.Companion, mutableInteractionSource, indication).then((Modifier) function2.invoke(mutableInteractionSource, (Object) null)) : ComposedModifierKt.composed$default(Modifier.Companion, (Function1) null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt.clickableWithIndicationIfNeeded.1
                public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                    composer.startReplaceGroup(-1525724089);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, i, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:634)");
                    }
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
                    Modifier modifierThen2 = IndicationKt.indication(Modifier.Companion, mutableInteractionSource2, indication).then((Modifier) function2.invoke(mutableInteractionSource2, (Object) null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    return modifierThen2;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            }, 1, (Object) null);
        }
        return modifier.then(modifierThen);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: combinedClickable-XVZzFYc, reason: not valid java name */
    public static final /* synthetic */ Modifier m360combinedClickableXVZzFYc(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z, final String str, final Role role, final String str2, final Function0 function0, final Function0 function1, final Function0 function2) {
        CombinedClickableElement combinedClickableElementComposed$default;
        if (indication instanceof IndicationNodeFactory) {
            combinedClickableElementComposed$default = new CombinedClickableElement(mutableInteractionSource, (IndicationNodeFactory) indication, false, z, str, role, function2, str2, function0, function1, true, null);
        } else if (indication == null) {
            combinedClickableElementComposed$default = new CombinedClickableElement(mutableInteractionSource, null, false, z, str, role, function2, str2, function0, function1, true, null);
        } else if (mutableInteractionSource != null) {
            combinedClickableElementComposed$default = IndicationKt.indication(Modifier.Companion, mutableInteractionSource, indication).then(new CombinedClickableElement(mutableInteractionSource, null, false, z, str, role, function2, str2, function0, function1, true, null));
        } else {
            combinedClickableElementComposed$default = ComposedModifierKt.composed$default(Modifier.Companion, (Function1) null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-XVZzFYc$$inlined$clickableWithIndicationIfNeeded$1
                public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                    composer.startReplaceGroup(-1525724089);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, i, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:634)");
                    }
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
                    Modifier modifierThen = IndicationKt.indication(Modifier.Companion, mutableInteractionSource2, indication).then(new CombinedClickableElement(mutableInteractionSource2, null, false, z, str, role, function2, str2, function0, function1, true, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    return modifierThen;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            }, 1, (Object) null);
        }
        return modifier.then(combinedClickableElementComposed$default);
    }

    /* JADX INFO: renamed from: combinedClickable-XVZzFYc$default, reason: not valid java name */
    public static /* synthetic */ Modifier m361combinedClickableXVZzFYc$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, Function0 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return m360combinedClickableXVZzFYc(modifier, mutableInteractionSource, indication, z, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : role, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : function0, (i & 128) != 0 ? null : function1, function2);
    }

    /* JADX INFO: renamed from: combinedClickable-auXiCPI, reason: not valid java name */
    public static final Modifier m362combinedClickableauXiCPI(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z, final String str, final Role role, final String str2, final Function0<Unit> function0, final Function0<Unit> function1, final boolean z2, final Function0<Unit> function2) {
        CombinedClickableElement combinedClickableElementComposed$default;
        if (indication instanceof IndicationNodeFactory) {
            combinedClickableElementComposed$default = new CombinedClickableElement(mutableInteractionSource, (IndicationNodeFactory) indication, false, z, str, role, function2, str2, function0, function1, z2, null);
        } else if (indication == null) {
            combinedClickableElementComposed$default = new CombinedClickableElement(mutableInteractionSource, null, false, z, str, role, function2, str2, function0, function1, z2, null);
        } else if (mutableInteractionSource != null) {
            combinedClickableElementComposed$default = IndicationKt.indication(Modifier.Companion, mutableInteractionSource, indication).then(new CombinedClickableElement(mutableInteractionSource, null, false, z, str, role, function2, str2, function0, function1, z2, null));
        } else {
            combinedClickableElementComposed$default = ComposedModifierKt.composed$default(Modifier.Companion, (Function1) null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-auXiCPI$$inlined$clickableWithIndicationIfNeeded$1
                public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                    composer.startReplaceGroup(-1525724089);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, i, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:634)");
                    }
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
                    Modifier modifierThen = IndicationKt.indication(Modifier.Companion, mutableInteractionSource2, indication).then(new CombinedClickableElement(mutableInteractionSource2, null, false, z, str, role, function2, str2, function0, function1, z2, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    return modifierThen;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            }, 1, (Object) null);
        }
        return modifier.then(combinedClickableElementComposed$default);
    }

    /* JADX INFO: renamed from: combinedClickable-auXiCPI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m363combinedClickableauXiCPI$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, boolean z2, Function0 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str = null;
        }
        if ((i & 16) != 0) {
            role = null;
        }
        if ((i & 32) != 0) {
            str2 = null;
        }
        if ((i & 64) != 0) {
            function0 = null;
        }
        if ((i & 128) != 0) {
            function1 = null;
        }
        if ((i & 256) != 0) {
            z2 = true;
        }
        return m362combinedClickableauXiCPI(modifier, mutableInteractionSource, indication, z, str, role, str2, function0, function1, z2, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: combinedClickable-cJG_KMw, reason: not valid java name */
    public static final /* synthetic */ Modifier m364combinedClickablecJG_KMw(Modifier modifier, final boolean z, final String str, final Role role, final String str2, final Function0 function0, final Function0 function1, final Function0 function2) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-cJG_KMw$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("combinedClickable");
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", function2);
                inspectorInfo.getProperties().set("onDoubleClick", function1);
                inspectorInfo.getProperties().set("onLongClick", function0);
                inspectorInfo.getProperties().set("onLongClickLabel", str2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: n02
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ClickableKt.d(z, str, role, str2, function0, function1, function2, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    /* JADX INFO: renamed from: combinedClickable-cJG_KMw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m365combinedClickablecJG_KMw$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, Function0 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        return m364combinedClickablecJG_KMw(modifier, z, str, role, str2, function0, (i & 32) != 0 ? null : function1, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with new overload that only supports IndicationNodeFactory instances inside LocalIndication, and does not use composed")
    /* JADX INFO: renamed from: combinedClickable-f5TDLPQ, reason: not valid java name */
    public static final /* synthetic */ Modifier m366combinedClickablef5TDLPQ(Modifier modifier, final boolean z, final String str, final Role role, final String str2, final Function0 function0, final Function0 function1, final boolean z2, final Function0 function2) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-f5TDLPQ$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("combinedClickable");
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", function2);
                inspectorInfo.getProperties().set("onDoubleClick", function1);
                inspectorInfo.getProperties().set("onLongClick", function0);
                inspectorInfo.getProperties().set("onLongClickLabel", str2);
                inspectorInfo.getProperties().set("hapticFeedbackEnabled", Boolean.valueOf(z2));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: m02
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ClickableKt.c(z, str, role, str2, function0, function1, z2, function2, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    /* JADX INFO: renamed from: combinedClickable-f5TDLPQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m367combinedClickablef5TDLPQ$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, boolean z2, Function0 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        if ((i & 32) != 0) {
            function1 = null;
        }
        if ((i & 64) != 0) {
            z2 = true;
        }
        return m366combinedClickablef5TDLPQ(modifier, z, str, role, str2, function0, function1, z2, function2);
    }

    /* JADX INFO: renamed from: combinedClickable-hoGz1lA, reason: not valid java name */
    public static final Modifier m368combinedClickablehoGz1lA(Modifier modifier, boolean z, String str, Role role, String str2, Function0<Unit> function0, Function0<Unit> function1, boolean z2, MutableInteractionSource mutableInteractionSource, Function0<Unit> function2) {
        return modifier.then(new CombinedClickableElement(mutableInteractionSource, null, true, z, str, role, function2, str2, function0, function1, z2, null));
    }

    /* JADX INFO: renamed from: combinedClickable-hoGz1lA$default, reason: not valid java name */
    public static /* synthetic */ Modifier m369combinedClickablehoGz1lA$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, boolean z2, MutableInteractionSource mutableInteractionSource, Function0 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        if ((i & 32) != 0) {
            function1 = null;
        }
        if ((i & 64) != 0) {
            z2 = true;
        }
        if ((i & 128) != 0) {
            mutableInteractionSource = null;
        }
        return m368combinedClickablehoGz1lA(modifier, z, str, role, str2, function0, function1, z2, mutableInteractionSource, function2);
    }

    public static Modifier d(boolean z, String str, Role role, String str2, Function0 function0, Function0 function1, Function0 function2, Modifier modifier, Composer composer, int i) {
        MutableInteractionSource mutableInteractionSource;
        composer.startReplaceGroup(1969174843);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1969174843, i, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:471)");
        }
        Indication indication = (Indication) composer.consume(IndicationKt.getLocalIndication());
        if (indication instanceof IndicationNodeFactory) {
            composer.startReplaceGroup(-1270399604);
            composer.endReplaceGroup();
            mutableInteractionSource = null;
        } else {
            composer.startReplaceGroup(-1270266986);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceGroup();
        }
        Modifier modifierM362combinedClickableauXiCPI = m362combinedClickableauXiCPI(Modifier.Companion, mutableInteractionSource, indication, z, str, role, str2, function0, function1, true, function2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierM362combinedClickableauXiCPI;
    }

    public static final boolean hasScrollableContainer(TraversableNode traversableNode) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        TraversableNodeKt.traverseAncestors(traversableNode, ScrollableContainerNode.INSTANCE, new Function1() { // from class: o02
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ClickableKt.a(booleanRef, (TraversableNode) obj));
            }
        });
        return booleanRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isClick-ZmokQxo, reason: not valid java name */
    public static final boolean m370isClickZmokQxo(KeyEvent keyEvent) {
        return KeyEventType.equals-impl0(KeyEvent_androidKt.getType-ZmokQxo(keyEvent), KeyEventType.Companion.getKeyUp-CS__XNY()) && m371isEnterZmokQxo(keyEvent);
    }

    /* JADX INFO: renamed from: isEnter-ZmokQxo, reason: not valid java name */
    private static final boolean m371isEnterZmokQxo(KeyEvent keyEvent) {
        long j = KeyEvent_androidKt.getKey-ZmokQxo(keyEvent);
        Key.Companion companion = Key.Companion;
        return Key.equals-impl0(j, companion.getDirectionCenter-EK5gGoQ()) || Key.equals-impl0(j, companion.getEnter-EK5gGoQ()) || Key.equals-impl0(j, companion.getNumPadEnter-EK5gGoQ()) || Key.equals-impl0(j, companion.getSpacebar-EK5gGoQ());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMovingIgnoreConsumed(IndirectPointerInputChange indirectPointerInputChange) {
        return indirectPointerInputChange.getPreviousPressed() && indirectPointerInputChange.getPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPress-ZmokQxo, reason: not valid java name */
    public static final boolean m372isPressZmokQxo(KeyEvent keyEvent) {
        return KeyEventType.equals-impl0(KeyEvent_androidKt.getType-ZmokQxo(keyEvent), KeyEventType.Companion.getKeyDown-CS__XNY()) && m371isEnterZmokQxo(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String unsupportedIndicationExceptionMessage(Indication indication) {
        return "clickable only supports IndicationNodeFactory instances provided to LocalIndication, but Indication was provided instead. Either migrate the Indication implementation to implement IndicationNodeFactory, or use the other clickable overload that takes an Indication parameter, and explicitly pass LocalIndication.current there. The Indication instance provided here was: " + indication;
    }
}
