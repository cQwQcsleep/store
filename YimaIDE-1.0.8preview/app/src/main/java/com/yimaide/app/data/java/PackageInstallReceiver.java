package com.yimaide.app.data.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sun.org.apache.bcel.internal.Const;
import defpackage.ge7;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/yimaide/app/data/java/PackageInstallReceiver;", "Landroid/content/BroadcastReceiver;", Const.CONSTRUCTOR_NAME, "()V", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "intent", "", "onReceive", "(Landroid/content/Context;Landroid/content/Intent;)V", "Companion", "a", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PackageInstallReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.yimaide.app.INSTALL_STATUS";
    public static final int $stable = 8;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        context.getClass();
        intent.getClass();
        if (Intrinsics.areEqual(intent.getAction(), ACTION)) {
            int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", Integer.MIN_VALUE);
            String stringExtra = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
            String stringExtra2 = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
            if (intExtra == -1) {
                Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
                if (intent2 == null) {
                    ge7.a.P("[Install] Missing confirmation intent\n");
                    return;
                } else {
                    intent2.addFlags(268435456);
                    context.startActivity(intent2);
                    return;
                }
            }
            if (intExtra != 0) {
                ge7 ge7Var = ge7.a;
                if (stringExtra == null) {
                    stringExtra = "unknown";
                }
                ge7Var.P("[Install] Failed (status=" + intExtra + "): " + stringExtra + "\n");
                return;
            }
            ge7 ge7Var2 = ge7.a;
            if (stringExtra2 != null) {
                str = " (" + stringExtra2 + ")";
            } else {
                str = "";
            }
            ge7Var2.P("[Install] Installed successfully" + str + "\n");
        }
    }
}
