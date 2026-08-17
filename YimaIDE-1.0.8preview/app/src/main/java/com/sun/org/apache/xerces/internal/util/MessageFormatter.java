package com.sun.org.apache.xerces.internal.util;

import java.util.Locale;
import java.util.MissingResourceException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MessageFormatter {
    String formatMessage(Locale locale, String str, Object[] objArr) throws MissingResourceException;
}
