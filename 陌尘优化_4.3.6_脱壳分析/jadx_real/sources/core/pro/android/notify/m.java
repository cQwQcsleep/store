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
                    Intent intent = new Intent(l2.decrypt("PETx800ryVc0ROHkTDaDGD5e/O5MbPswGH0=\n", "XSqVgSJCrXk=\n"), Uri.parse(str));
                    intent.setFlags(268435456);
                    view.getContext().startActivity(intent);
                    break;
                } catch (Throwable th) {
                    k2.logToFloatingWindow(h.e("PUeqVTvDmyhlKLcVYufDU29r1gwd\n", "2845sIdDcrs=\n", new StringBuilder(), str), l2.decrypt("96/hwFc=\n", "kt2TryXueUQ=\n"));
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
                    k2.logToFloatingWindow(h.e("JuBH0DYptSF7jHCEYh33f3zz\n", "wGnUNYqpUpA=\n", new StringBuilder(), str2), l2.decrypt("s6D6N4E=\n", "1tKIWPO8o/w=\n"));
                    return;
                }
            default:
                String str3 = this.b;
                try {
                    Intent intent3 = new Intent(l2.decrypt("Czlss5yFaHEDOXyknZgiPgkjYa6dwloWLwA=\n", "alcIwfPsDF8=\n"), Uri.parse(str3));
                    intent3.setFlags(268435456);
                    view.getContext().startActivity(intent3);
                    break;
                } catch (Throwable th3) {
                    System.out.println(l2.decrypt("+7+F15ewMP3PlIfr2zneJkVHbF9oYbE7BR5IBxNr8o+A\n", "oPvstvvfV7U=\n") + str3);
                    return;
                }
        }
    }
}
