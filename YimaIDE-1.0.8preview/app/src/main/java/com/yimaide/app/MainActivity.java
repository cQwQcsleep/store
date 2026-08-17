package com.yimaide.app;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.splashscreen.SplashScreenViewProvider;
import com.yimaide.app.MainActivity;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/yimaide/app/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;

    public static Unit d(final AtomicBoolean atomicBoolean, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-319203828, i, -1, "com.yimaide.app.MainActivity.onCreate.<anonymous> (MainActivity.kt:47)");
            }
            kce.b(false, false, ComposableLambdaKt.rememberComposableLambda(-1421565638, true, new Function2() { // from class: vs9
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.i(atomicBoolean, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(false);
        return Unit.INSTANCE;
    }

    public static void f(final SplashScreenViewProvider splashScreenViewProvider) {
        splashScreenViewProvider.getClass();
        splashScreenViewProvider.getView().animate().alpha(0.0f).setDuration(120L).setInterpolator(new LinearInterpolator()).withEndAction(new Runnable() { // from class: us9
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.j(splashScreenViewProvider);
            }
        }).start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void g(MainActivity mainActivity) {
        sbe sbeVar = sbe.a;
        Context applicationContext = mainActivity.getApplicationContext();
        applicationContext.getClass();
        sbeVar.c(applicationContext);
    }

    public static boolean h(AtomicBoolean atomicBoolean) {
        return atomicBoolean.get();
    }

    public static Unit i(final AtomicBoolean atomicBoolean, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1421565638, i, -1, "com.yimaide.app.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:48)");
            }
            boolean zChangedInstance = composer.changedInstance(atomicBoolean);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ts9
                    public final Object invoke() {
                        return MainActivity.e(atomicBoolean);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            c4g.M((Function0) objRememberedValue, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static void j(SplashScreenViewProvider splashScreenViewProvider) {
        splashScreenViewProvider.remove();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.activity.ComponentActivity
    public void onCreate(Bundle savedInstanceState) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        SplashScreen splashScreenInstallSplashScreen = SplashScreen.Companion.installSplashScreen(this);
        splashScreenInstallSplashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() { // from class: ps9
            public final boolean shouldKeepOnScreen() {
                return MainActivity.h(atomicBoolean);
            }
        });
        splashScreenInstallSplashScreen.setOnExitAnimationListener(new SplashScreen.OnExitAnimationListener() { // from class: qs9
            public final void onSplashScreenExit(SplashScreenViewProvider splashScreenViewProvider) {
                MainActivity.f(splashScreenViewProvider);
            }
        });
        super.onCreate(savedInstanceState);
        SystemBarStyle.Companion companion = SystemBarStyle.INSTANCE;
        EdgeToEdge.enable(this, companion.light(0, 0), companion.light(0, 0));
        kb3 kb3Var = kb3.a;
        Context applicationContext = getApplicationContext();
        applicationContext.getClass();
        kb3Var.c(applicationContext);
        new Thread(new Runnable() { // from class: rs9
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.g(this.b);
            }
        }).start();
        ComponentActivityKt.setContent$default(this, (CompositionContext) null, ComposableLambdaKt.composableLambdaInstance(-319203828, true, new Function2() { // from class: ss9
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.d(atomicBoolean, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, (Object) null);
    }
}
