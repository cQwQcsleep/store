package androidx.compose.foundation.text;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.compose.foundation.text.BasicSecureTextField_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.os.HandlerCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\r\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004\u001a\b\u0010\u0010\u001a\u00020\u0011H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"0\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012²\u0006\n\u0010\u0013\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"TAG", "", "platformAllowsRevealLastTyped", "", "(Landroidx/compose/runtime/Composer;I)Z", "DefaultContentResolverForSecureTextField", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroidx/compose/foundation/text/ContentResolverForSecureTextField;", "contentResolverForSecureTextField", "getContentResolverForSecureTextField$annotations", "()V", "getContentResolverForSecureTextField", "()Lkotlin/jvm/functions/Function1;", "setContentResolverForSecureTextField", "(Lkotlin/jvm/functions/Function1;)V", "resetContentResolverForSecureTextField", "", "foundation", "state"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BasicSecureTextField_androidKt {
    private static final Function1<Context, ContentResolverForSecureTextField> DefaultContentResolverForSecureTextField;
    private static final String TAG = "BasicSecureTextField";
    private static Function1<? super Context, ? extends ContentResolverForSecureTextField> contentResolverForSecureTextField;

    static {
        Function1<Context, ContentResolverForSecureTextField> function1 = new Function1() { // from class: iq0
            public final Object invoke(Object obj) {
                return BasicSecureTextField_androidKt.b((Context) obj);
            }
        };
        DefaultContentResolverForSecureTextField = function1;
        contentResolverForSecureTextField = function1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.text.BasicSecureTextField_androidKt$DefaultContentResolverForSecureTextField$1$1] */
    public static BasicSecureTextField_androidKt$DefaultContentResolverForSecureTextField$1$1 b(Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        return new ContentResolverForSecureTextField() { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$DefaultContentResolverForSecureTextField$1$1
            @Override // androidx.compose.foundation.text.ContentResolverForSecureTextField
            public boolean getShowPassword() {
                try {
                    return Settings.System.getInt(contentResolver, "show_password") > 0;
                } catch (Exception e) {
                    Log.w("BasicSecureTextField", "Failed to fetch show password setting, using value: true", e);
                    return true;
                }
            }

            @Override // androidx.compose.foundation.text.ContentResolverForSecureTextField
            public void registerContentObserver(Uri uri, boolean notifyForDescendants, ContentObserver observer) {
                contentResolver.registerContentObserver(uri, notifyForDescendants, observer);
            }

            @Override // androidx.compose.foundation.text.ContentResolverForSecureTextField
            public void unregisterContentObserver(ContentObserver observer) {
                contentResolver.unregisterContentObserver(observer);
            }
        };
    }

    public static final Function1<Context, ContentResolverForSecureTextField> getContentResolverForSecureTextField() {
        return contentResolverForSecureTextField;
    }

    public static /* synthetic */ void getContentResolverForSecureTextField$annotations() {
    }

    public static final boolean platformAllowsRevealLastTyped(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1015272320, i, -1, "androidx.compose.foundation.text.platformAllowsRevealLastTyped (BasicSecureTextField.android.kt:38)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        Function1<? super Context, ? extends ContentResolverForSecureTextField> function1 = contentResolverForSecureTextField;
        boolean zChanged = composer.changed(function1) | composer.changed(context);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = (ContentResolverForSecureTextField) contentResolverForSecureTextField.invoke(context);
            composer.updateRememberedValue(objRememberedValue);
        }
        final ContentResolverForSecureTextField contentResolverForSecureTextField2 = (ContentResolverForSecureTextField) objRememberedValue;
        boolean zChanged2 = composer.changed(contentResolverForSecureTextField2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(contentResolverForSecureTextField2.getShowPassword()), (SnapshotMutationPolicy) null, 2, (Object) null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        final MutableState mutableState = (MutableState) objRememberedValue2;
        boolean zChanged3 = composer.changed(contentResolverForSecureTextField2);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
            final Handler handlerCreateAsync = HandlerCompat.createAsync(Looper.getMainLooper());
            objRememberedValue3 = new ContentObserver(handlerCreateAsync) { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1
                @Override // android.database.ContentObserver
                public void onChange(boolean selfChange) {
                    BasicSecureTextField_androidKt.platformAllowsRevealLastTyped$lambda$3(mutableState, contentResolverForSecureTextField2.getShowPassword());
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        final BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1 basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1 = (BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1) objRememberedValue3;
        boolean zChangedInstance = composer.changedInstance(contentResolverForSecureTextField2) | composer.changedInstance(basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == Composer.Companion.getEmpty()) {
            objRememberedValue4 = new Function1() { // from class: hq0
                public final Object invoke(Object obj) {
                    return BasicSecureTextField_androidKt.platformAllowsRevealLastTyped$lambda$5$0(contentResolverForSecureTextField2, basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        EffectsKt.DisposableEffect(contentResolverForSecureTextField2, basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1, (Function1) objRememberedValue4, composer, 0);
        boolean zPlatformAllowsRevealLastTyped$lambda$2 = platformAllowsRevealLastTyped$lambda$2(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zPlatformAllowsRevealLastTyped$lambda$2;
    }

    private static final boolean platformAllowsRevealLastTyped$lambda$2(MutableState<Boolean> mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void platformAllowsRevealLastTyped$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult platformAllowsRevealLastTyped$lambda$5$0(final ContentResolverForSecureTextField contentResolverForSecureTextField2, final BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1 basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1, DisposableEffectScope disposableEffectScope) {
        contentResolverForSecureTextField2.registerContentObserver(Settings.System.getUriFor("show_password"), false, basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1);
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$lambda$5$0$$inlined$onDispose$1
            public void dispose() {
                contentResolverForSecureTextField2.unregisterContentObserver(basicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1);
            }
        };
    }

    public static final void resetContentResolverForSecureTextField() {
        contentResolverForSecureTextField = DefaultContentResolverForSecureTextField;
    }

    public static final void setContentResolverForSecureTextField(Function1<? super Context, ? extends ContentResolverForSecureTextField> function1) {
        contentResolverForSecureTextField = function1;
    }
}
