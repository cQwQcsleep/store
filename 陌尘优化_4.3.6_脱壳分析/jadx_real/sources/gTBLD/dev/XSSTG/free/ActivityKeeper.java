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
        if (intent == null || !intent.getBooleanExtra(l2.decrypt("NU5rn7kAKL8pUmuQoTo8\n", "RiYO89VfWM0=\n"), false)) {
            zContains = c.contains(name);
            if (zContains) {
                try {
                    Intent intent2 = activity.getIntent();
                    StringBuilder sb = new StringBuilder(l2.decrypt("UghTU/bIcBg8bU4vqMgobAklIDrc\n", "sYjDuk1ZlYg=\n"));
                    sb.append(l2.decrypt("RUT/Vc+3PfgmHaYOjA==\n", "T2nfNKzDVI4=\n"));
                    sb.append(name);
                    sb.append(l2.decrypt("crumALFbAC4WrKY=\n", "eJaGYdIvaUE=\n"));
                    sb.append(intent2 != null ? intent2.getAction() : l2.decrypt("nFAKOg==\n", "8iVmVh3sEVE=\n"));
                    sb.append(l2.decrypt("h1KzXOBUehSt\n", "jX+TOIEgGy4=\n"));
                    sb.append(intent2 != null ? intent2.getData() : l2.decrypt("tUqVrQ==\n", "2z/5wRYfoA4=\n"));
                    sb.append(l2.decrypt("cd/4+Kzv6XdB0ujm\n", "e/LYnsCOjgQ=\n"));
                    sb.append(intent2 != null ? Integer.toHexString(intent2.getFlags()) : l2.decrypt("Z8XrEA==\n", "CbCHfE3F1Co=\n"));
                    k2.logToFloatingWindow(sb.toString(), l2.decrypt("z5CK4QrG2Q==\n", "uPH4j2Oovtk=\n"));
                } catch (Throwable unused2) {
                }
            }
        } else {
            k2.logToFloatingWindow(l2.decrypt("kEzGsdI4YthQzB5xWIw0h0KoTGEk\n", "4ySj3b4Yim8=\n") + name, l2.decrypt("r1x/BK0=\n", "yzkdccqFAYo=\n"));
        }
        return zContains;
    }

    public static void b(Activity activity) {
        try {
            activity.finish();
            activity.overridePendingTransition(0, 0);
        } catch (Throwable th) {
            k2.logToFloatingWindow(l2.decrypt("OTkwfqSLx2j74bajcgxbFw==\n", "X1BeF9fj540=\n") + th.getMessage(), l2.decrypt("ZF9Jn8c=\n", "AS078LXwoM4=\n"));
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
                k2.logToFloatingWindow(l2.decrypt("xwqEKSzP5wC7VKJ+WciiZZMM+nAwpJc9xzaaIwDY\n", "LrEVzLxCAo0=\n") + c.size(), l2.decrypt("NTx7fl4=\n", "UVkZCzkf3o4=\n"));
            } catch (Throwable th) {
                k2.logToFloatingWindow(l2.decrypt("QRa82U7r/Ug1eYy8FtuBFgAt9IVWuahp\n", "pJwcMfNWFPM=\n") + th.getMessage(), l2.decrypt("8ANrxkCg2w==\n", "h2IZqCnOvE4=\n"));
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
                    k2.logToFloatingWindow(l2.decrypt("IbvyZZ6u+C1g6skDwLeYXUWipz6P\n", "xAxAgyYrEbQ=\n"), l2.decrypt("H9St4A==\n", "drrLj3MI7NQ=\n"));
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
            k2.logToFloatingWindow(l2.decrypt("cmXcd7XJ+G06OuQ3ycmgBCB4tiK2\n", "lN1ZnixtEew=\n") + th.getMessage(), l2.decrypt("z3PX78c=\n", "qgGlgLVj82c=\n"));
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
                                    k2.k3zLJuvX(activity, l2.decrypt("dcup7JUqUbwBv4SFyAot4DrN8YeOaASd\n", "kFoUCC2HuAc=\n") + activity.getClass().getName());
                                }
                                k2.logToFloatingWindow(l2.decrypt("AG+cjndlUyh8M6f8AmcVQ2Jy6+NNBwothrpOGYKJwsAGaISEW3I=\n", "6dQNa+fotqU=\n") + activity.getClass().getName(), l2.decrypt("hZRnlhxysw==\n", "8vUV+HUc1IU=\n"));
                                ActivityKeeper.b(activity);
                                return;
                            }
                        } catch (Throwable th) {
                            k2.logToFloatingWindow(l2.decrypt("FhVGLsPIMClqSFxttc1/QlwuMX7YoGkmGhZvJO/Nusq83LKqJyA6GHZBa1E=\n", "/67Xy1NF1aQ=\n") + th.getMessage(), l2.decrypt("wb/mAu8=\n", "pM2UbZ3jERQ=\n"));
                        }
                        try {
                            JSONObject jsonResult = s0.getJsonResult();
                            if (Objects.equals(fcRuQsQrcxOAzxwEalcM.WAIT, l2.decrypt("bqy2XazmzHY=\n", "NY/hHOWy7ys=\n")) || jsonResult != null) {
                                k2.logToFloatingWindow(l2.decrypt("nwjc0rfC5s/Zffea58SqqsUO\n", "eZR2NAB5A0U=\n") + activity.getClass().getName(), l2.decrypt("1XuClw==\n", "vBXk+BviYvI=\n"));
                                return;
                            }
                            Set set = ActivityKeeper.e;
                            if (set.contains(activity)) {
                                k2.logToFloatingWindow(l2.decrypt("I78HdoynkjhG7RAT1ZfAWkGoXR+dx8YWJLQu\n", "ywi0njMge78=\n") + activity.getClass().getName(), l2.decrypt("W6ODeA==\n", "Ms3lF51p5i0=\n"));
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
                                    k2.logToFloatingWindow(l2.decrypt("NbkjpjrqyT1w5xDuauyFWGyU\n", "0A6RQI1RLLc=\n") + activity.getClass().getName(), l2.decrypt("VGVDOw==\n", "PQslVNwvE08=\n"));
                                }
                            } catch (Throwable th2) {
                                k2.logToFloatingWindow(l2.decrypt("k44uBlCKY4Lb3ihKP44768Gcel9A\n", "dTmV49oqigM=\n") + th2.getMessage(), l2.decrypt("gIDXjNw=\n", "5fKl464owkQ=\n"));
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
                        k2.logToFloatingWindow(l2.decrypt("P090PMOEAQqur4+wOE2v1UA=\n", "2sb52Uw0QGk=\n") + activity.getClass().getName(), l2.decrypt("9avezQ==\n", "nMW4ogVbqgc=\n"));
                        try {
                            if (ActivityKeeper.a(activity)) {
                                if (s0.isDebug()) {
                                    k2.k3zLJuvX(activity, l2.decrypt("0X5LxnEhWvmlCmavLAEmpZ54E61qYw/Y\n", "NO/2IsmMs0I=\n") + activity.getClass().getName());
                                }
                                k2.logToFloatingWindow(l2.decrypt("YvkUCaPWRaYepS971tQDzQDkY2SZtByj5CzXiUAuzU5k/gwDj8E=\n", "i0KF7DNboCs=\n") + activity.getClass().getName(), l2.decrypt("coqkHirHaw==\n", "BevWcEOpDL4=\n"));
                                activity.getWindow().getDecorView().post(new Runnable() { // from class: gTBLD.dev.XSSTG.free.ActivityKeeper.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ActivityKeeper.b(activity);
                                    }
                                });
                                return;
                            }
                        } catch (Throwable th) {
                            k2.logToFloatingWindow(l2.decrypt("GoESrtOBd11m3AjtpYQ4NlC6Zf7I6S5SFoI7pP+E/b6hX/A+Lml9bHrVP9E=\n", "8zqDS0MMktA=\n") + th.getMessage(), l2.decrypt("1/6LWOA=\n", "soz5N5L4k1U=\n"));
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
                                            k2.logToFloatingWindow(l2.decrypt("eAITKKHQdWVyBAIxsssha1APAy6gmXN5WAUeevfLdHJXCAkm98tkcVYVAmGlzG15SltH\n", "OWFnQde5ARw=\n") + activity.getClass().getName(), l2.decrypt("6Kvq+cg=\n", "jM6IjK/PBg0=\n"));
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
                                k2.logToFloatingWindow(l2.decrypt("m+IS3k9xnv+hrBzeV3XX5e7tEMhKYtf/t6wX2UB7zKu45RbLGTQ=\n", "zoxzvCMUvos=\n") + th2.getMessage(), l2.decrypt("y7Nu9k7deA==\n", "vNIcmCezH6w=\n"));
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
                k2.logToFloatingWindow(l2.decrypt("O1BAnjbUaD6dp6sS0QD51tLVqGilJoKvyrDSROhYmsucu6QSyiLz+/LWpFypBo2i6r7RetVbl+Gc\nu54Y/DQ=\n", "ejM090C9HEc=\n"), l2.decrypt("1T4vTg==\n", "vFBJIf5HENo=\n"));
            } catch (Throwable th) {
                k2.logToFloatingWindow(l2.decrypt("3xbsZnN0Ls6mQNU+EGlhvKU6oRhrEHnZ3AH1a0FdJuaj\n", "OaVEg/X4yVo=\n") + th.getMessage(), l2.decrypt("ETj8tBNVmg==\n", "ZlmO2no7/f4=\n"));
            }
        }
    }

    public static Activity peekTopActivity() {
        return (Activity) ((WeakReference) a.get()).get();
    }
}
