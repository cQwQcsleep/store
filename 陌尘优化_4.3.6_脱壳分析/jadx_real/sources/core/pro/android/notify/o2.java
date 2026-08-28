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
                    Intent intent = new Intent(l2.decrypt("uCE3H+nsO/KwIScI6PFxvbo7OgLoqwmVnBg=\n", "2U9TbYaFX9w=\n"), Uri.parse(str));
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                    break;
                } catch (Exception e) {
                    k2.logToFloatingWindow(h.e("tl4GWkd+emboQ2PpHxFnJrFnO5IVUgY/zg==\n", "VMOKeqH36YM=\n", new StringBuilder(), str), l2.decrypt("+5Y9Vt8=\n", "nuRPOa0s704=\n"));
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
                    k2.logToFloatingWindow(h.e("nMI/eOXkUUTC31TpuIhmEJbrFre/9w==\n", "fl+zWANtwqE=\n", new StringBuilder(), str), l2.decrypt("Eod8hWc=\n", "d/UO6hUKkkE=\n"));
                    return;
                }
            default:
                Activity activity3 = Utils.a;
                try {
                    Intent intent3 = new Intent(l2.decrypt("FmAFxKzVdVweYBXTrcg/ExR6CNmtkkc7Mlk=\n", "dw5htsO8EXI=\n"), Uri.parse(str));
                    intent3.setFlags(268435456);
                    context.startActivity(intent3);
                    k2.logToFloatingWindow(l2.decrypt("jbhJhMhZ6bSYoH7yYS2Osf3OVqoOMM7iwb0=\n", "fSfFFOi+aw0=\n") + str, l2.decrypt("ohVP8/+WtQ==\n", "0HA/n5710BQ=\n"));
                    break;
                } catch (Exception e3) {
                    k2.logToFloatingWindow(h.e("v+MFBkPFiVbh/mC1G6qUFrjaOM4R6fUPxw==\n", "XX6JJqVMGrM=\n", new StringBuilder(), str), l2.decrypt("mQpAyhA=\n", "/HgypWLPAFE=\n"));
                    e3.printStackTrace();
                    return;
                }
        }
    }
}
