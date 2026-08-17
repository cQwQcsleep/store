package io.github.rosemoe.sora.langs.textmate.registry.dsl;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0003J\u001f\u0010\u0012\u001a\u00020\u00192\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00190\u001c¢\u0006\u0002\b\u001eJ\u0016\u0010\u001f\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001a\u0010\t\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\u0005R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\u0005R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\u0005R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006!"}, d2 = {"Lio/github/rosemoe/sora/langs/textmate/registry/dsl/LanguageDefinitionBuilder;", "", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "grammar", "getGrammar", "setGrammar", "scopeName", "getScopeName", "setScopeName", "languageConfiguration", "getLanguageConfiguration", "setLanguageConfiguration", "embeddedLanguages", "", "getEmbeddedLanguages", "()Ljava/util/Map;", "setEmbeddedLanguages", "(Ljava/util/Map;)V", "defaultScopeName", "", "prefix", "block", "Lkotlin/Function1;", "Lio/github/rosemoe/sora/langs/textmate/registry/dsl/LanguageEmbeddedLanguagesDefinitionBuilder;", "Lkotlin/ExtensionFunctionType;", "embeddedLanguage", "languageName", "language-textmate_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class LanguageDefinitionBuilder {
    private Map<String, String> embeddedLanguages;
    public String grammar;
    private String languageConfiguration;
    private String name;
    private String scopeName;

    public LanguageDefinitionBuilder(String str) {
        str.getClass();
        this.name = str;
    }

    public static /* synthetic */ void defaultScopeName$default(LanguageDefinitionBuilder languageDefinitionBuilder, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "source";
        }
        languageDefinitionBuilder.defaultScopeName(str);
    }

    public final void defaultScopeName(String prefix) {
        prefix.getClass();
        this.scopeName = prefix + "." + this.name;
    }

    public final void embeddedLanguage(String scopeName, String languageName) {
        scopeName.getClass();
        languageName.getClass();
        Map<String, String> linkedHashMap = this.embeddedLanguages;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
        }
        this.embeddedLanguages = linkedHashMap;
        linkedHashMap.put(scopeName, languageName);
    }

    public final void embeddedLanguages(Function1<? super LanguageEmbeddedLanguagesDefinitionBuilder, Unit> block) {
        block.getClass();
        Map<String, String> linkedHashMap = this.embeddedLanguages;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
        }
        this.embeddedLanguages = linkedHashMap;
        block.invoke(new LanguageEmbeddedLanguagesDefinitionBuilder(linkedHashMap));
    }

    public final Map<String, String> getEmbeddedLanguages() {
        return this.embeddedLanguages;
    }

    public final String getGrammar() {
        String str = this.grammar;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("grammar");
        return null;
    }

    public final String getLanguageConfiguration() {
        return this.languageConfiguration;
    }

    public final String getName() {
        return this.name;
    }

    public final String getScopeName() {
        return this.scopeName;
    }

    public final void setEmbeddedLanguages(Map<String, String> map) {
        this.embeddedLanguages = map;
    }

    public final void setGrammar(String str) {
        str.getClass();
        this.grammar = str;
    }

    public final void setLanguageConfiguration(String str) {
        this.languageConfiguration = str;
    }

    public final void setName(String str) {
        str.getClass();
        this.name = str;
    }

    public final void setScopeName(String str) {
        this.scopeName = str;
    }
}
