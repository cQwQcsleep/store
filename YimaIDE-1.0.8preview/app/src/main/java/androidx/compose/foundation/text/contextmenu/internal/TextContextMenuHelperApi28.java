package androidx.compose.foundation.text.contextmenu.internal;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.textclassifier.TextClassification;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuTextClassificationItem;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0003¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/internal/TextContextMenuHelperApi28;", "", "<init>", "()V", "textClassificationItem", "", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "context", "Landroid/content/Context;", "component", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuTextClassificationItem;", "IconBox", "icon", "Landroid/graphics/drawable/Icon;", "(Landroid/graphics/drawable/Icon;Landroidx/compose/runtime/Composer;I)V", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TextContextMenuHelperApi28 {
    public static final TextContextMenuHelperApi28 INSTANCE = new TextContextMenuHelperApi28();

    private TextContextMenuHelperApi28() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void IconBox(final Icon icon, Composer composer, final int i) {
        int i2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function2 function2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2116504409);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(icon) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2116504409, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:267)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            boolean zChanged = composerStartRestartGroup.changed(icon) | composerStartRestartGroup.changed(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = icon.loadDrawable(context);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Drawable drawable = (Drawable) objRememberedValue;
            if (drawable == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                } else {
                    function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.d
                        public final Object invoke(Object obj, Object obj2) {
                            return TextContextMenuHelperApi28.d(this.b, icon, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    };
                }
            } else {
                IconBox(drawable, composerStartRestartGroup, i2 & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
        composerStartRestartGroup.skipToGroupEnd();
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.e
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.h(this.b, icon, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox$lambda$3$0(Drawable drawable, DrawScope drawScope) {
        Canvas canvas = drawScope.getDrawContext().getCanvas();
        drawable.setBounds(0, 0, (int) Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)), (int) Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)));
        drawable.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas));
        return Unit.INSTANCE;
    }

    public static Unit a(TextContextMenuHelperApi28 textContextMenuHelperApi28, Drawable drawable, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(drawable, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static String b(TextClassification textClassification, Composer composer, int i) {
        composer.startReplaceGroup(950061013);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(950061013, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:246)");
        }
        String strValueOf = String.valueOf(textClassification.getLabel());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return strValueOf;
    }

    public static Unit c(RemoteAction remoteAction) throws PendingIntent.CanceledException {
        TextClassificationHelperApi28.INSTANCE.sendPendingIntent(remoteAction.getActionIntent());
        return Unit.INSTANCE;
    }

    public static Unit d(TextContextMenuHelperApi28 textContextMenuHelperApi28, Icon icon, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(icon, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit f(Context context, TextClassification textClassification) throws PendingIntent.CanceledException {
        TextClassificationHelperApi28.INSTANCE.sendLegacyIntent(context, textClassification);
        return Unit.INSTANCE;
    }

    public static String g(RemoteAction remoteAction, Composer composer, int i) {
        composer.startReplaceGroup(-1376593684);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1376593684, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:254)");
        }
        String string = remoteAction.getTitle().toString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return string;
    }

    public static Unit h(TextContextMenuHelperApi28 textContextMenuHelperApi28, Icon icon, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(icon, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public final void textClassificationItem(ContextMenuScope contextMenuScope, final Context context, TextContextMenuTextClassificationItem textContextMenuTextClassificationItem) {
        if (context == null) {
            return;
        }
        int index = textContextMenuTextClassificationItem.getIndex();
        final TextClassification textClassification = textContextMenuTextClassificationItem.getTextClassification();
        if (index < 0) {
            Function2 function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.f
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.b(textClassification, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            final Drawable icon = textClassification.getIcon();
            ContextMenuScope.item$default(contextMenuScope, function2, null, false, icon != null ? ComposableLambdaKt.composableLambdaInstance(-1123224187, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$2$1
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    m1457invokeek8zF_U(((Color) obj).unbox-impl(), (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-ek8zF_U, reason: not valid java name */
                public final void m1457invokeek8zF_U(long j, Composer composer, int i) {
                    if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1123224187, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous>.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:247)");
                    }
                    TextContextMenuHelperApi28.INSTANCE.IconBox(icon, composer, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }) : null, new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.g
                public final Object invoke() {
                    return TextContextMenuHelperApi28.f(context, textClassification);
                }
            }, 6, null);
        } else {
            final RemoteAction remoteAction = textClassification.getActions().get(index);
            ContextMenuScope.item$default(contextMenuScope, new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.h
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.g(remoteAction, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, null, false, ((index == 0) || remoteAction.shouldShowIcon()) ? ComposableLambdaKt.composableLambdaInstance(-1261173016, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.5
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    m1458invokeek8zF_U(((Color) obj).unbox-impl(), (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-ek8zF_U, reason: not valid java name */
                public final void m1458invokeek8zF_U(long j, Composer composer, int i) {
                    if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1261173016, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:257)");
                    }
                    TextContextMenuHelperApi28.INSTANCE.IconBox(remoteAction.getIcon(), composer, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }) : null, new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.i
                public final Object invoke() {
                    return TextContextMenuHelperApi28.c(remoteAction);
                }
            }, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void IconBox(final Drawable drawable, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(257732500);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(drawable) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(257732500, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:274)");
            }
            Modifier modifierM983size3ABfNKs = SizeKt.m983size3ABfNKs(Modifier.Companion, ContextMenuSpec.INSTANCE.m459getIconSizeD9Ej5fM());
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(drawable);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.foundation.text.contextmenu.internal.j
                    public final Object invoke(Object obj) {
                        return TextContextMenuHelperApi28.IconBox$lambda$3$0(drawable, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            BoxKt.Box(DrawModifierKt.drawBehind(modifierM983size3ABfNKs, (Function1) objRememberedValue), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.k
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.a(this.b, drawable, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
