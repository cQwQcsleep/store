package androidx.activity.compose;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"rememberLauncherForActivityResult", "Landroidx/activity/compose/ManagedActivityResultLauncher;", "I", "O", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "onResult", "Lkotlin/Function1;", "", "(Landroidx/activity/result/contract/ActivityResultContract;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/activity/compose/ManagedActivityResultLauncher;", "activity-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ActivityResultRegistryKt {

    /* JADX INFO: renamed from: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "I", "O", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends Lambda implements Function1<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ ActivityResultRegistry $activityResultRegistry;
        final /* synthetic */ ActivityResultContract<I, O> $contract;
        final /* synthetic */ State<Function1<O, Unit>> $currentOnResult;
        final /* synthetic */ String $key;
        final /* synthetic */ ActivityResultLauncherHolder<I> $realLauncher;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ActivityResultLauncherHolder<I> activityResultLauncherHolder, ActivityResultRegistry activityResultRegistry, String str, ActivityResultContract<I, O> activityResultContract, State<? extends Function1<? super O, Unit>> state) {
            super(1);
            this.$realLauncher = activityResultLauncherHolder;
            this.$activityResultRegistry = activityResultRegistry;
            this.$key = str;
            this.$contract = activityResultContract;
            this.$currentOnResult = state;
        }

        public static void b(State state, Object obj) {
            ((Function1) state.getValue()).invoke(obj);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            ActivityResultLauncherHolder<I> activityResultLauncherHolder = this.$realLauncher;
            ActivityResultRegistry activityResultRegistry = this.$activityResultRegistry;
            String str = this.$key;
            ActivityResultContract<I, O> activityResultContract = this.$contract;
            final State<Function1<O, Unit>> state = this.$currentOnResult;
            activityResultLauncherHolder.setLauncher((ActivityResultLauncher<I>) activityResultRegistry.register(str, activityResultContract, new ActivityResultCallback() { // from class: androidx.activity.compose.a
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    ActivityResultRegistryKt.AnonymousClass1.b(state, obj);
                }
            }));
            final ActivityResultLauncherHolder<I> activityResultLauncherHolder2 = this.$realLauncher;
            return new DisposableEffectResult() { // from class: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$1$invoke$$inlined$onDispose$1
                public void dispose() {
                    activityResultLauncherHolder2.unregister();
                }
            };
        }
    }

    public static final <I, O> ManagedActivityResultLauncher<I, O> rememberLauncherForActivityResult(ActivityResultContract<I, O> activityResultContract, Function1<? super O, Unit> function1, Composer composer, int i) {
        composer.startReplaceableGroup(-1408504823);
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(activityResultContract, composer, 8);
        State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 3) & 14);
        String str = (String) RememberSaveableKt.rememberSaveable(new Object[0], (Saver) null, (String) null, rememberLauncherForActivityResult.key.1.INSTANCE, composer, 3080, 6);
        ActivityResultRegistryOwner current = LocalActivityResultRegistryOwner.INSTANCE.getCurrent(composer, 6);
        if (current == null) {
            k2d.a("No ActivityResultRegistryOwner was provided via LocalActivityResultRegistryOwner");
            return null;
        }
        ActivityResultRegistry activityResultRegistry = current.getActivityResultRegistry();
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new ActivityResultLauncherHolder();
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        ActivityResultLauncherHolder activityResultLauncherHolder = (ActivityResultLauncherHolder) objRememberedValue;
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new ManagedActivityResultLauncher(activityResultLauncherHolder, stateRememberUpdatedState);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        ManagedActivityResultLauncher<I, O> managedActivityResultLauncher = (ManagedActivityResultLauncher) objRememberedValue2;
        EffectsKt.DisposableEffect(activityResultRegistry, str, activityResultContract, new AnonymousClass1(activityResultLauncherHolder, activityResultRegistry, str, activityResultContract, stateRememberUpdatedState2), composer, 520);
        composer.endReplaceableGroup();
        return managedActivityResultLauncher;
    }
}
