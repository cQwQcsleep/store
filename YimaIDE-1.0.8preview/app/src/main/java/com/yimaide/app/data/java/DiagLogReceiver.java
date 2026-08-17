package com.yimaide.app.data.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sun.org.apache.bcel.internal.Const;
import com.yimaide.app.data.java.DiagLogReceiver;
import defpackage.ge7;
import defpackage.jc3;
import defpackage.kb3;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/yimaide/app/data/java/DiagLogReceiver;", "Landroid/content/BroadcastReceiver;", Const.CONSTRUCTOR_NAME, "()V", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "intent", "", "onReceive", "(Landroid/content/Context;Landroid/content/Intent;)V", "Companion", "a", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DiagLogReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.yimaide.app.DIAG_LOG";
    public static final int $stable = 8;

    public static void a(String str, String str2, BroadcastReceiver.PendingResult pendingResult) {
        try {
            ge7.a.P("\n[Diag] 诊断日志回流（" + str + "）：\n" + str2);
            Iterator it = SequencesKt.filter(StringsKt.lineSequence(str2), new Function1() { // from class: ys3
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(DiagLogReceiver.b((String) obj));
                }
            }).iterator();
            while (it.hasNext()) {
                kb3.a.b((String) it.next());
            }
            pendingResult.finish();
        } catch (Throwable th) {
            pendingResult.finish();
            throw th;
        }
    }

    public static boolean b(String str) {
        str.getClass();
        return !StringsKt.isBlank(str);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        context.getClass();
        intent.getClass();
        if (Intrinsics.areEqual(intent.getAction(), ACTION)) {
            String stringExtra = intent.getStringExtra("run_id");
            kb3 kb3Var = kb3.a;
            kb3Var.c(context);
            String strG = kb3Var.g();
            if (strG.length() != 0 && Intrinsics.areEqual(stringExtra, strG)) {
                jc3 jc3Var = jc3.a;
                String stringExtra2 = intent.getStringExtra("lines");
                if (stringExtra2 == null) {
                    return;
                }
                final String strB = jc3Var.b(stringExtra2);
                final String stringExtra3 = intent.getStringExtra("pkg");
                if (stringExtra3 == null) {
                    stringExtra3 = "(unknown)";
                }
                if (StringsKt.isBlank(strB)) {
                    return;
                }
                final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
                jc3Var.c().execute(new Runnable() { // from class: zs3
                    @Override // java.lang.Runnable
                    public final void run() {
                        DiagLogReceiver.a(stringExtra3, strB, pendingResultGoAsync);
                    }
                });
            }
        }
    }
}
