package com.sun.tools.javac.api;

import java.util.Locale;
import java.util.MissingResourceException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Messages {
    void add(String str) throws MissingResourceException;

    String getLocalizedString(Locale locale, String str, Object... objArr);
}
