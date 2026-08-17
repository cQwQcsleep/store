package org.eclipse.jdt.internal.antadapter;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class AntAdapterMessages {
    private static final String BUNDLE_NAME = "org.eclipse.jdt.internal.antadapter.messages";
    private static ResourceBundle RESOURCE_BUNDLE;

    static {
        try {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
        } catch (MissingResourceException e) {
            System.out.println("Missing resource : " + BUNDLE_NAME.replace('.', '/') + ".properties for locale " + String.valueOf(Locale.getDefault()));
            throw e;
        }
    }

    private AntAdapterMessages() {
    }

    public static String getString(String str, String str2) {
        try {
            return new MessageFormat(RESOURCE_BUNDLE.getString(str)).format(new String[]{str2});
        } catch (MissingResourceException unused) {
            return "!" + str + "!";
        }
    }

    public static String getString(String str) {
        try {
            return RESOURCE_BUNDLE.getString(str);
        } catch (MissingResourceException unused) {
            return "!" + str + "!";
        }
    }
}
