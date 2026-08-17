package com.sun.tools.javac.api;

import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Formattable {
    String getKind();

    String toString(Locale locale, Messages messages);

    public static class LocalizedString implements Formattable {
        String key;

        public LocalizedString(String str) {
            this.key = str;
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String getKind() {
            return "LocalizedString";
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String toString(Locale locale, Messages messages) {
            return messages.getLocalizedString(locale, this.key, new Object[0]);
        }

        public String toString() {
            return this.key;
        }
    }
}
