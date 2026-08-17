package androidx.compose.runtime.retain;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.retain.RetainedEffectKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a&\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\b\u001a0\u0010\u0002\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\r\u001a:\u0010\u0002\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u000f\u001aD\u0010\u0002\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0011\u001a>\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0013\"\u0004\u0018\u00010\f2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"InternalRetainedEffectScope", "Landroidx/compose/runtime/retain/RetainedEffectScope;", "RetainedEffect", "", "effect", "Lkotlin/Function1;", "Landroidx/compose/runtime/retain/RetainedEffectResult;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "RetainedEffectNoParamError", "", "key1", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "keys", "", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "runtime-retain"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RetainedEffectKt {
    private static final RetainedEffectScope InternalRetainedEffectScope = new RetainedEffectScope();
    private static final String RetainedEffectNoParamError = "RetainedEffect must provide one or more 'key' parameters that define the identity of the RetainedEffect and determine when its previous effect should be disposed and a new effect started for the new key.";

    public static final void RetainedEffect(Object[] objArr, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(712993160, i, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:293)");
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        boolean z = (((i & 112) ^ 48) > 32 && composer.changed(function1)) || (i & 48) == 32;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: xic
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$3$0(function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        RetainKt.retain(RetainedEffectImpl.class.getName().hashCode(), Arrays.copyOf(objArrCopyOf, objArrCopyOf.length), (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$0$0(Function1 function1) {
        return new RetainedEffectImpl(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$1$0(Function1 function1) {
        return new RetainedEffectImpl(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$2$0(Function1 function1) {
        return new RetainedEffectImpl(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedEffectImpl RetainedEffect$lambda$3$0(Function1 function1) {
        return new RetainedEffectImpl(function1);
    }

    public static final void RetainedEffect(Object obj, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(45580315, i, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:138)");
        }
        Object[] objArr = {obj};
        boolean z = (((i & 112) ^ 48) > 32 && composer.changed(function1)) || (i & 48) == 32;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: vic
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$0$0(function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        RetainKt.retain(RetainedEffectImpl.class.getName().hashCode(), Arrays.copyOf(objArr, 1), (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void RetainedEffect(Object obj, Object obj2, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-593547589, i, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:190)");
        }
        Object[] objArr = {obj, obj2};
        boolean z = (((i & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 && composer.changed(function1)) || (i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: uic
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$1$0(function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        RetainKt.retain(RetainedEffectImpl.class.getName().hashCode(), Arrays.copyOf(objArr, 2), (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void RetainedEffect(Object obj, Object obj2, Object obj3, final Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(130977115, i, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:243)");
        }
        Object[] objArr = {obj, obj2, obj3};
        boolean z = (((i & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) > 2048 && composer.changed(function1)) || (i & 3072) == 2048;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: wic
                public final Object invoke() {
                    return RetainedEffectKt.RetainedEffect$lambda$2$0(function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        RetainKt.retain(RetainedEffectImpl.class.getName().hashCode(), Arrays.copyOf(objArr, 3), (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = RetainedEffectNoParamError)
    public static final void RetainedEffect(Function1<? super RetainedEffectScope, ? extends RetainedEffectResult> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(829391227, i, -1, "androidx.compose.runtime.retain.RetainedEffect (RetainedEffect.kt:87)");
        }
        throw new IllegalStateException(RetainedEffectNoParamError);
    }
}
