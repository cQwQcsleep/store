package io.github.rosemoe.sora.widget.snippet.variable;

import io.github.rosemoe.sora.text.TextUtils;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TimeBasedSnippetVariableResolver implements ISnippetVariableResolver {
    private static String getDisplayName(int i, boolean z) {
        Calendar calendar = Calendar.getInstance();
        String displayName = calendar.getDisplayName(i, z ? 1 : 2, Locale.getDefault());
        if (displayName == null && z) {
            displayName = calendar.getDisplayName(i, 2, Locale.getDefault());
        }
        if (displayName == null) {
            displayName = calendar.getDisplayName(i, z ? 1 : 2, Locale.US);
        }
        return displayName == null ? Integer.toString(calendar.get(i)) : displayName;
    }

    public String[] getResolvableNames() {
        return new String[]{"CURRENT_YEAR", "CURRENT_YEAR_SHORT", "CURRENT_MONTH", "CURRENT_DATE", "CURRENT_HOUR", "CURRENT_MINUTE", "CURRENT_SECOND", "CURRENT_DAY_NAME", "CURRENT_DAY_NAME_SHORT", "CURRENT_MONTH_NAME", "CURRENT_MONTH_NAME_SHORT", "CURRENT_SECONDS_UNIX"};
    }

    public String resolve(String str) {
        str.getClass();
        switch (str) {
            case "CURRENT_MONTH":
                return TextUtils.padStart(Integer.toString(Calendar.getInstance().get(2)), '0', 2);
            case "CURRENT_MONTH_NAME_SHORT":
                return getDisplayName(2, true);
            case "CURRENT_MINUTE":
                return TextUtils.padStart(Integer.toString(Calendar.getInstance().get(12)), '0', 2);
            case "CURRENT_SECOND":
                return TextUtils.padStart(Integer.toString(Calendar.getInstance().get(13)), '0', 2);
            case "CURRENT_DAY_NAME":
                return getDisplayName(7, false);
            case "CURRENT_DATE":
                return DateFormat.getDateInstance().format(new Date());
            case "CURRENT_HOUR":
                return TextUtils.padStart(Integer.toString(Calendar.getInstance().get(11)), '0', 2);
            case "CURRENT_YEAR":
                return Integer.toString(Calendar.getInstance().get(1));
            case "CURRENT_DAY_NAME_SHORT":
                return getDisplayName(7, true);
            case "CURRENT_SECONDS_UNIX":
                return Long.toString(Math.round(System.currentTimeMillis() / 1000.0d));
            case "CURRENT_MONTH_NAME":
                return getDisplayName(2, false);
            case "CURRENT_YEAR_SHORT":
                return TextUtils.padStart(Integer.toString(Calendar.getInstance().get(1) % 100), '0', 2);
            default:
                w01.a("Unsupported variable name:".concat(str));
                return null;
        }
    }
}
