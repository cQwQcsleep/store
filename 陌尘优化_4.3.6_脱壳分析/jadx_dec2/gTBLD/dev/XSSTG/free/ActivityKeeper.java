package gTBLD.dev.XSSTG.free;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import core.pro.android.notify.a;
import core.pro.android.notify.k2;
import core.pro.android.notify.l2;
import core.pro.android.notify.s0;
import core.pro.android.notify.t3;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ActivityKeeper {
    public static final AtomicReference a = new AtomicReference(new WeakReference(null));
    public static volatile boolean b = false;
    public static volatile Set c = Collections.emptySet();
    public static volatile long d = 0;
    public static final Set e = Collections.newSetFromMap(new ConcurrentHashMap());
    public static final ConcurrentHashMap f = new ConcurrentHashMap();

    public static boolean a(Activity activity) {
        Intent intent;
        boolean zContains = false;
        c(activity.getApplication(), false);
        String name = activity.getClass().getName();
        try {
            intent = activity.getIntent();
        } catch (Throwable unused) {
        }
        if (intent == null || !intent.getBooleanExtra("shell_protected", false)) {
            zContains = c.contains(name);
            if (zContains) {
                try {
                    Intent intent2 = activity.getIntent();
                    StringBuilder sb = new StringBuilder("【黑名单命中】");
                    sb.append("\n- activity: ");
                    sb.append(name);
                    sb.append("\n- action: ");
                    sb.append(intent2 != null ? intent2.getAction() : "null");
                    sb.append("\n- data: ");
                    sb.append(intent2 != null ? intent2.getData() : "null");
                    sb.append("\n- flags: 0x");
                    sb.append(intent2 != null ? Integer.toHexString(intent2.getFlags()) : "null");
                    k2.logToFloatingWindow(sb.toString(), "warning");
                } catch (Throwable unused2) {
                }
            }
        } else {
            k2.logToFloatingWindow("shell 跳转放行：" + name, "debug");
        }
        return zContains;
    }

    public static void b(Activity activity) {
        try {
            activity.finish();
            activity.overridePendingTransition(0, 0);
        } catch (Throwable th) {
            k2.logToFloatingWindow("finish 失败：" + th.getMessage(), "error");
        }
    }

    public static void c(Application application, boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (z || jCurrentTimeMillis - d >= 300000) {
            try {
                Collection collectionLoadBlackActivitiesFromFile = HookManager.loadBlackActivitiesFromFile(application.getApplicationContext());
                if (collectionLoadBlackActivitiesFromFile == null) {
                    collectionLoadBlackActivitiesFromFile = new HashSet();
                }
                c = Collections.unmodifiableSet(new HashSet(collectionLoadBlackActivitiesFromFile));
                d = jCurrentTimeMillis;
                k2.logToFloatingWindow("黑名单已加载，数量：" + c.size(), "debug");
            } catch (Throwable th) {
                k2.logToFloatingWindow("加载黑名单失败：" + th.getMessage(), "warning");
            }
        }
    }

    public static void clearAllMasks() {
        View view;
        Set set = e;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new a(0));
            return;
        }
        try {
            Iterator it = set.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                ConcurrentHashMap concurrentHashMap = f;
                if (!zHasNext) {
                    set.clear();
                    concurrentHashMap.clear();
                    k2.logToFloatingWindow("已清除所有遮罩", "info");
                    return;
                } else {
                    Activity activity = (Activity) it.next();
                    if (activity != null && (view = (View) concurrentHashMap.get(activity)) != null) {
                        View decorView = activity.getWindow().getDecorView();
                        if (decorView instanceof ViewGroup) {
                            ((ViewGroup) decorView).removeView(view);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            k2.logToFloatingWindow("清除遮罩失败：" + th.getMessage(), "error");
        }
    }

    public static void init(Application application) {
        if (b) {
            return;
        }
        synchronized (ActivityKeeper.class) {
            if (b) {
                return;
            }
            try {
                c(application, true);
                application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: gTBLD.dev.XSSTG.free.ActivityKeeper.1
                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityCreated(Activity activity, Bundle bundle) {
                        try {
                            if (ActivityKeeper.a(activity)) {
                                if (s0.isDebug()) {
                                    k2.k3zLJuvX(activity, "命中黑名单窗口：" + activity.getClass().getName());
                                }
                                k2.logToFloatingWindow("黑名单窗口拦截（onCreate）：" + activity.getClass().getName(), "warning");
                                ActivityKeeper.b(activity);
                                return;
                            }
                        } catch (Throwable th) {
                            k2.logToFloatingWindow("黑名单拦截检测异常（onCreate）：" + th.getMessage(), "error");
                        }
                        try {
                            JSONObject jsonResult = s0.getJsonResult();
                            if (Objects.equals(fcRuQsQrcxOAzxwEalcM.WAIT, "[#WAIT#]") || jsonResult != null) {
                                k2.logToFloatingWindow("未添加遮罩：" + activity.getClass().getName(), "info");
                                return;
                            }
                            Set set = ActivityKeeper.e;
                            if (set.contains(activity)) {
                                k2.logToFloatingWindow("跳过重复添加遮罩：" + activity.getClass().getName(), "info");
                                return;
                            }
                            try {
                                View decorView = activity.getWindow().getDecorView();
                                if (decorView instanceof ViewGroup) {
                                    View view = new View(activity);
                                    view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                                    view.setBackgroundColor(0);
                                    view.setClickable(true);
                                    ((ViewGroup) decorView).addView(view);
                                    set.add(activity);
                                    ActivityKeeper.f.put(activity, view);
                                    k2.logToFloatingWindow("已添加遮罩：" + activity.getClass().getName(), "info");
                                }
                            } catch (Throwable th2) {
                                k2.logToFloatingWindow("添加遮罩失败：" + th2.getMessage(), "error");
                            }
                        } catch (Throwable unused) {
                        }
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityDestroyed(Activity activity) {
                        AtomicReference atomicReference = ActivityKeeper.a;
                        if (((Activity) ((WeakReference) atomicReference.get()).get()) == activity) {
                            atomicReference.set(new WeakReference(null));
                        }
                        ActivityKeeper.e.remove(activity);
                        ActivityKeeper.f.remove(activity);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPaused(Activity activity) {
                        AtomicReference atomicReference = ActivityKeeper.a;
                        if (((Activity) ((WeakReference) atomicReference.get()).get()) == activity) {
                            atomicReference.set(new WeakReference(null));
                        }
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityResumed(final Activity activity) {
                        ActivityKeeper.a.set(new WeakReference(activity));
                        k2.logToFloatingWindow("前台Activity：" + activity.getClass().getName(), "info");
                        try {
                            if (ActivityKeeper.a(activity)) {
                                if (s0.isDebug()) {
                                    k2.k3zLJuvX(activity, "命中黑名单窗口：" + activity.getClass().getName());
                                }
                                k2.logToFloatingWindow("黑名单窗口拦截（onResume）：" + activity.getClass().getName(), "warning");
                                activity.getWindow().getDecorView().post(new Runnable() { // from class: gTBLD.dev.XSSTG.free.ActivityKeeper.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ActivityKeeper.b(activity);
                                    }
                                });
                                return;
                            }
                        } catch (Throwable th) {
                            k2.logToFloatingWindow("黑名单拦截检测异常（onResume）：" + th.getMessage(), "error");
                        }
                        if (Utils.g(activity)) {
                            try {
                                final View decorView = activity.getWindow().getDecorView();
                                if (decorView == null) {
                                    return;
                                }
                                final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                                final Runnable runnable = new Runnable() { // from class: gTBLD.dev.XSSTG.free.ActivityKeeper.2
                                    @Override // java.lang.Runnable
                                    public void run() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
                                        if (atomicBoolean.compareAndSet(false, true) && Utils.g(activity)) {
                                            k2.logToFloatingWindow("ActivityKeeper window ready; running remote rules: " + activity.getClass().getName(), "debug");
                                            Activity activity2 = activity;
                                            t3.WeOiML3k(activity2, activity2.getApplicationContext());
                                        }
                                    }
                                };
                                decorView.post(new Runnable() { // from class: gTBLD.dev.XSSTG.free.ActivityKeeper.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (Utils.g(activity)) {
                                            if (decorView.hasWindowFocus()) {
                                                runnable.run();
                                                return;
                                            }
                                            final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                                            if (viewTreeObserver.isAlive()) {
                                                viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: gTBLD.dev.XSSTG.free.ActivityKeeper.3.1
                                                    @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
                                                    public void onWindowFocusChanged(boolean z) {
                                                        if (z) {
                                                            if (viewTreeObserver.isAlive()) {
                                                                viewTreeObserver.removeOnWindowFocusChangeListener(this);
                                                            }
                                                            runnable.run();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                });
                            } catch (Throwable th2) {
                                k2.logToFloatingWindow("Unable to obtain activity decor view: " + th2.getMessage(), "warning");
                            }
                        }
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStarted(Activity activity) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStopped(Activity activity) {
                    }
                });
                b = true;
                k2.logToFloatingWindow("Activity生命周期回调注册成功（含黑名单拦截）", "info");
            } catch (Throwable th) {
                k2.logToFloatingWindow("注册生命周期回调失败：" + th.getMessage(), "warning");
            }
        }
    }

    public static Activity peekTopActivity() {
        return (Activity) ((WeakReference) a.get()).get();
    }
}
