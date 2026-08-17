package org.jetbrains.kotlin.idea;

import com.intellij.lang.Language;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KotlinLanguage extends Language {
    public static final KotlinLanguage INSTANCE = new KotlinLanguage();
    public static final String NAME = "Kotlin";

    private KotlinLanguage() {
        super("kotlin");
    }

    public String getDisplayName() {
        return NAME;
    }

    public boolean isCaseSensitive() {
        return true;
    }
}
