package core.pro.android.notify;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class m implements View.OnClickListener {
    public final int a;
    public final String b;

    public /* synthetic */ m(String str, int i) {
        this.a = i;
        this.b = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                String str = this.b;
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.setFlags(268435456);
                    view.getContext().startActivity(intent);
                    break;
                } catch (Throwable th) {
                    k2.logToFloatingWindow(h.e("PUeqVTvDmyhlKLcVYufDU29r1gwd\n", "2845sIdDcrs=\n", new StringBuilder(), str), "error");
                    return;
                }
            case 1:
                String str2 = this.b;
                try {
                    Intent intent2 = new Intent();
                    intent2.setClassName(view.getContext(), str2);
                    intent2.setFlags(268435456);
                    view.getContext().startActivity(intent2);
                    break;
                } catch (Throwable th2) {
                    k2.logToFloatingWindow(h.e("JuBH0DYptSF7jHCEYh33f3zz\n", "wGnUNYqpUpA=\n", new StringBuilder(), str2), "error");
                    return;
                }
            default:
                String str3 = this.b;
                try {
                    Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(str3));
                    intent3.setFlags(268435456);
                    view.getContext().startActivity(intent3);
                    break;
                } catch (Throwable th3) {
                    System.out.println("[DialogHook] 打开链接失败: " + str3);
                    return;
                }
        }
    }
}
