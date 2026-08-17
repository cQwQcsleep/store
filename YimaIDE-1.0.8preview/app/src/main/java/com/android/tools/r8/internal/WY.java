package com.android.tools.r8.internal;

import com.android.tools.r8.internal.WY;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class WY {
    public Consumer a = new Consumer() { // from class: lff
        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            WY.a((String) obj);
        }
    };
    public final Consumer b = new Consumer() { // from class: mff
        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            this.b.b((String) obj);
        }
    };
    public Consumer c = new Consumer() { // from class: nff
        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            WY.c((String) obj);
        }
    };
    public Runnable d = new Runnable() { // from class: off
        @Override // java.lang.Runnable
        public final void run() {
            WY.a();
        }
    };
    public Runnable e = new Runnable() { // from class: off
        @Override // java.lang.Runnable
        public final void run() {
            WY.a();
        }
    };
    public Runnable f = new Runnable() { // from class: off
        @Override // java.lang.Runnable
        public final void run() {
            WY.a();
        }
    };

    public static WY a(Consumer<String> consumer) {
        final WY wy = new WY();
        wy.a = consumer;
        wy.d = new Runnable() { // from class: pff
            @Override // java.lang.Runnable
            public final void run() {
                this.b.a.accept("-dontshrink");
            }
        };
        wy.e = new Runnable() { // from class: qff
            @Override // java.lang.Runnable
            public final void run() {
                this.b.a.accept("-dontoptimize");
            }
        };
        wy.f = new Runnable() { // from class: rff
            @Override // java.lang.Runnable
            public final void run() {
                this.b.a.accept("-dontobfuscate");
            }
        };
        return wy.b(wy.a);
    }

    public static void c(String str) {
        throw new AssertionError("Unhandled");
    }

    public void d(String str) {
        String strTrim = str.trim();
        if (strTrim.equals("-dontobfuscate")) {
            this.f.run();
            return;
        }
        if (strTrim.equals("-dontoptimize")) {
            this.e.run();
            return;
        }
        if (strTrim.equals("-dontshrink")) {
            this.d.run();
            return;
        }
        if (strTrim.startsWith("-print")) {
            this.c.accept(str);
        } else if (strTrim.startsWith("#")) {
            this.b.accept(str);
        } else {
            this.a.accept(str);
        }
    }

    public final /* synthetic */ void b(String str) {
        this.a.accept(str);
    }

    public WY b(Consumer<String> consumer) {
        this.c = consumer;
        return this;
    }

    public static void a() {
        throw new AssertionError("Unhandled");
    }

    public static void a(String str) {
        throw new AssertionError("Unhandled");
    }
}
