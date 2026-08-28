package core.pro.android.notify;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

/* loaded from: /workspace/unpacked/classes2.dex */
public class r extends ContextWrapper {
    public r(Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SharedPreferences getSharedPreferences(String str, int i) {
        String str2 = "ۦۨۛ۫ۜۡۘ۫ۙۥۘۧۤۢۡۢۛۛۖۛۘۙۦۘۛۢۛ۬ۛۙۧۧ۠ۥۚۤۤۜۘ";
        while (true) {
            switch ((((str2.hashCode() ^ 880) ^ 853) ^ 588) ^ (-995867985)) {
                case -644855193:
                    return new s(super.getSharedPreferences(str, i), str, i);
                case -441320104:
                    str2 = "ۤ۠ۦۘۖۢۙۡۙ۠ۨۘۙۚ۠ۦۡۚۧۥۚۘ۬ۢۥ۫۟ۥۨۨۘ";
                    break;
                case 1757629977:
                    str2 = "۬ۗۘۘۢ۠ۡۘۥۗ۫ۢۢۤۦۤۜ۫ۚۘۨۚ۟۬ۜۘۘۥۘ۫ۜ";
                    break;
                case 2055568077:
                    str2 = "ۧۥۧۘۚ۟۬۫ۘۨۛۦۛۚۤۗۢۨۤۤۦۤۨۘۨۗ۟ۦۘۜ۬ۧۖۗۦۢۧۗ۟ۦۥۖۘۨ";
                    break;
            }
        }
    }
}
