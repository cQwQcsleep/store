package androidx.compose.runtime.saveable;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.CharsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u001aa\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0007¢\u0006\u0002\u0010\u000b\u001a=\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0007¢\u0006\u0002\u0010\f\u001aS\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0007¢\u0006\u0002\u0010\r\u001a[\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e\"\u0004\b\u0000\u0010\u00012\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e0\nH\u0007¢\u0006\u0002\u0010\u0010\u001ag\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e\"\u0004\b\u0000\u0010\u00012\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0004\"\u0004\u0018\u00010\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e0\nH\u0007¢\u0006\u0002\u0010\u0011\u001a>\u0010\u0012\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000e0\u0006\"\u0004\b\u0000\u0010\u00012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006H\u0000\u001a\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0002\u001a\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0002H\u0000\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"rememberSaveable", "T", "", "inputs", "", "saver", "Landroidx/compose/runtime/saveable/Saver;", "key", "", "init", "Lkotlin/Function0;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "Landroidx/compose/runtime/MutableState;", "stateSaver", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/MutableState;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "mutableStateSaver", "inner", "requireCanBeSaved", "", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "value", "generateCannotBeSavedErrorMessage", "MaxSupportedRadix", "", "runtime-saveable"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RememberSaveableKt {
    private static final int MaxSupportedRadix = 36;

    public static final String generateCannotBeSavedErrorMessage(Object obj) {
        return obj + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it to rememberSaveable().";
    }

    public static final <T> Saver<MutableState<T>, MutableState<Object>> mutableStateSaver(final Saver<T, ? extends Object> saver) {
        saver.getClass();
        return SaverKt.Saver(new Function2() { // from class: dcc
            public final Object invoke(Object obj, Object obj2) {
                return RememberSaveableKt.mutableStateSaver$lambda$0$0(saver, (SaverScope) obj, (MutableState) obj2);
            }
        }, new Function1() { // from class: ecc
            public final Object invoke(Object obj) {
                return RememberSaveableKt.mutableStateSaver$lambda$0$1(saver, (MutableState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState mutableStateSaver$lambda$0$0(Saver saver, SaverScope saverScope, MutableState mutableState) {
        if (!(mutableState instanceof SnapshotMutableState)) {
            w01.a("If you use a custom MutableState implementation you have to write a custom Saver and pass it as a saver param to rememberSaveable()");
            return null;
        }
        SnapshotMutableState snapshotMutableState = (SnapshotMutableState) mutableState;
        Object objSave = saver.save(saverScope, snapshotMutableState.getValue());
        if (objSave == null) {
            return null;
        }
        SnapshotMutationPolicy policy = snapshotMutableState.getPolicy();
        policy.getClass();
        return SnapshotStateKt.mutableStateOf(objSave, policy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState mutableStateSaver$lambda$0$1(Saver saver, MutableState mutableState) {
        Object objRestore = null;
        if (!(mutableState instanceof SnapshotMutableState)) {
            w01.a("Failed requirement.");
            return null;
        }
        SnapshotMutableState snapshotMutableState = (SnapshotMutableState) mutableState;
        if (snapshotMutableState.getValue() != 0) {
            T value = snapshotMutableState.getValue();
            value.getClass();
            objRestore = saver.restore(value);
        }
        SnapshotMutationPolicy policy = snapshotMutableState.getPolicy();
        policy.getClass();
        MutableState mutableStateMutableStateOf = SnapshotStateKt.mutableStateOf(objRestore, policy);
        mutableStateMutableStateOf.getClass();
        return mutableStateMutableStateOf;
    }

    @Deprecated(message = " 'rememberSaveable' with a custom 'key' is no longer supported. It bypasses positional scoping, leading to state bugs and inconsistent behavior (e.g., unintentional state sharing or loss, issues in nested LazyLayouts). Please remove the 'key' parameter to use positional scoping for consistent, locally-scoped state. See https://r.android.com/3610053 for details.")
    /* JADX INFO: renamed from: rememberSaveable, reason: collision with other method in class */
    public static final <T> T m2563rememberSaveable(Object[] objArr, Saver<T, ? extends Object> saver, String str, Function0<? extends T> function0, Composer composer, int i, int i2) {
        Object[] objArr2;
        final T t;
        Object objConsumeRestored;
        if ((i2 & 2) != 0) {
            saver = SaverKt.autoSaver();
        }
        final Saver<T, ? extends Object> saver2 = saver;
        int i3 = i2 & 4;
        Object objInvoke = null;
        if (i3 != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(441892779, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:79)");
        }
        long currentCompositeKeyHashCode = ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0);
        if (str == null || str.length() == 0) {
            str = Long.toString(currentCompositeKeyHashCode, CharsKt.checkRadix(MaxSupportedRadix));
            str.getClass();
        }
        final String str2 = str;
        saver2.getClass();
        final SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) composer.consume(SaveableStateRegistryKt.getLocalSaveableStateRegistry());
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            if (saveableStateRegistry != null && (objConsumeRestored = saveableStateRegistry.consumeRestored(str2)) != null) {
                objInvoke = saver2.restore(objConsumeRestored);
            }
            if (objInvoke == null) {
                objInvoke = function0.invoke();
            }
            objArr2 = objArr;
            Object saveableHolder = new SaveableHolder(saver2, saveableStateRegistry, str2, objInvoke, objArr2);
            composer.updateRememberedValue(saveableHolder);
            objRememberedValue = saveableHolder;
        } else {
            objArr2 = objArr;
        }
        final SaveableHolder saveableHolder2 = (SaveableHolder) objRememberedValue;
        Object valueIfInputsDidntChange = saveableHolder2.getValueIfInputsDidntChange(objArr2);
        if (valueIfInputsDidntChange == null) {
            valueIfInputsDidntChange = function0.invoke();
        }
        boolean zChangedInstance = composer.changedInstance(saveableHolder2) | ((((i & 112) ^ 48) > 32 && composer.changedInstance(saver2)) || (i & 48) == 32) | composer.changedInstance(saveableStateRegistry) | composer.changed(str2) | composer.changedInstance(valueIfInputsDidntChange) | composer.changedInstance(objArr2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            final Object[] objArr3 = objArr2;
            t = (T) valueIfInputsDidntChange;
            Object obj = new Function0() { // from class: androidx.compose.runtime.saveable.a
                public final Object invoke() {
                    return RememberSaveableKt.rememberSaveable$lambda$1$0(saveableHolder2, saver2, saveableStateRegistry, str2, t, objArr3);
                }
            };
            composer.updateRememberedValue(obj);
            objRememberedValue2 = obj;
        } else {
            t = (T) valueIfInputsDidntChange;
        }
        EffectsKt.SideEffect((Function0) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberSaveable$lambda$1$0(SaveableHolder saveableHolder, Saver saver, SaveableStateRegistry saveableStateRegistry, String str, Object obj, Object[] objArr) {
        saveableHolder.update(saver, saveableStateRegistry, str, obj, objArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requireCanBeSaved(SaveableStateRegistry saveableStateRegistry, Object obj) {
        String strGenerateCannotBeSavedErrorMessage;
        if (obj == null || saveableStateRegistry.canBeSaved(obj)) {
            return;
        }
        if (obj instanceof SnapshotMutableState) {
            SnapshotMutableState snapshotMutableState = (SnapshotMutableState) obj;
            if (snapshotMutableState.getPolicy() == SnapshotStateKt.neverEqualPolicy() || snapshotMutableState.getPolicy() == SnapshotStateKt.structuralEqualityPolicy() || snapshotMutableState.getPolicy() == SnapshotStateKt.referentialEqualityPolicy()) {
                strGenerateCannotBeSavedErrorMessage = "MutableState containing " + snapshotMutableState.getValue() + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it as a stateSaver parameter to rememberSaveable().";
            } else {
                strGenerateCannotBeSavedErrorMessage = "If you use a custom SnapshotMutationPolicy for your MutableState you have to write a custom Saver";
            }
        } else {
            strGenerateCannotBeSavedErrorMessage = generateCannotBeSavedErrorMessage(obj);
        }
        throw new IllegalArgumentException(strGenerateCannotBeSavedErrorMessage);
    }

    public static final <T> T rememberSaveable(Object[] objArr, Function0<? extends T> function0, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1564532345, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:135)");
        }
        T t = (T) m2563rememberSaveable(Arrays.copyOf(objArr, objArr.length), SaverKt.autoSaver(), (String) null, (Function0) function0, composer, ((i << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t;
    }

    /* JADX INFO: renamed from: rememberSaveable, reason: collision with other method in class */
    public static final <T> T m2564rememberSaveable(Object[] objArr, Saver<T, ? extends Object> saver, Function0<? extends T> function0, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(674689872, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:180)");
        }
        T t = (T) m2563rememberSaveable(Arrays.copyOf(objArr, objArr.length), (Saver) saver, (String) null, (Function0) function0, composer, (i & 112) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP | ((i << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t;
    }

    public static final <T> MutableState<T> rememberSaveable(Object[] objArr, Saver<T, ? extends Object> saver, Function0<? extends MutableState<T>> function0, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-746165481, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:208)");
        }
        MutableState<T> mutableState = (MutableState) m2563rememberSaveable(Arrays.copyOf(objArr, objArr.length), mutableStateSaver(saver), (String) null, (Function0) function0, composer, ((i << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }

    @Deprecated(message = " 'rememberSaveable' with a custom 'key' is no longer supported. It bypasses positional scoping, leading to state bugs and inconsistent behavior (e.g., unintentional state sharing or loss, issues in nested LazyLayouts). Please remove the 'key' parameter to use positional scoping for consistent, locally-scoped state. See https://r.android.com/3610053 for details.")
    public static final <T> MutableState<T> rememberSaveable(Object[] objArr, Saver<T, ? extends Object> saver, String str, Function0<? extends MutableState<T>> function0, Composer composer, int i, int i2) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        String str2 = str;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-202053668, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:248)");
        }
        MutableState<T> mutableState = (MutableState) m2563rememberSaveable(Arrays.copyOf(objArr, objArr.length), mutableStateSaver(saver), str2, (Function0) function0, composer, i & 8064, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }
}
