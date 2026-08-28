package core.pro.android.notify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import gTBLD.dev.XSSTG.free.Utils;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class o2 implements View.OnClickListener {
    public final int a;
    public final Context b;
    public final String c;

    public /* synthetic */ o2(int i, Context context, String str) {
        this.a = i;
        this.c = str;
        this.b = context;
    }

    public /* synthetic */ o2(Context context, String str) {
        this.a = 1;
        this.b = context;
        this.c = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context = this.b;
        String str = this.c;
        switch (this.a) {
            case 0:
                Activity activity = Utils.a;
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                    break;
                } catch (Exception e) {
                    k2.logToFloatingWindow(h.e("tl4GWkd+emboQ2PpHxFnJrFnO5IVUgY/zg==\n", "VMOKeqH36YM=\n", new StringBuilder(), str), "error");
                    return;
                }
            case 1:
                Activity activity2 = Utils.a;
                try {
                    Intent intent2 = new Intent();
                    intent2.setClassName(context, str);
                    intent2.setFlags(268435456);
                    context.startActivity(intent2);
                    break;
                } catch (Exception e2) {
                    k2.logToFloatingWindow(h.e("nMI/eOXkUUTC31TpuIhmEJbrFre/9w==\n", "fl+zWANtwqE=\n", new StringBuilder(), str), "error");
                    return;
                }
            default:
                Activity activity3 = Utils.a;
                try {
                    Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent3.setFlags(268435456);
                    context.startActivity(intent3);
                    k2.logToFloatingWindow("🌐 点击打开链接：" + str, "replace");
                    break;
                } catch (Exception e3) {
                    k2.logToFloatingWindow(h.e("v+MFBkPFiVbh/mC1G6qUFrjaOM4R6fUPxw==\n", "XX6JJqVMGrM=\n", new StringBuilder(), str), "error");
                    e3.printStackTrace();
                    return;
                }
        }
    }
}
