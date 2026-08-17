package io.github.rosemoe.sora.langs.textmate.registry.model;

import io.github.rosemoe.sora.langs.textmate.utils.StringUtils;
import java.util.Collections;
import java.util.Map;
import org.eclipse.tm4e.core.registry.IGrammarSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class DefaultGrammarDefinition implements GrammarDefinition {
    private Map<String, String> embeddedLanguages;
    private IGrammarSource grammarSource;
    private String languageConfigurationPath;
    private String name;
    private String scopeName;

    private DefaultGrammarDefinition(String str, String str2, IGrammarSource iGrammarSource, String str3) {
        this.embeddedLanguages = null;
        this.name = str;
        this.scopeName = str2;
        this.grammarSource = iGrammarSource;
        this.languageConfigurationPath = str3;
    }

    public static DefaultGrammarDefinition withGrammarSource(IGrammarSource iGrammarSource) {
        String fileNameWithoutExtension = StringUtils.getFileNameWithoutExtension(iGrammarSource.getFilePath());
        return withGrammarSource(iGrammarSource, fileNameWithoutExtension, "source." + fileNameWithoutExtension);
    }

    public static DefaultGrammarDefinition withLanguageConfiguration(IGrammarSource iGrammarSource, String str) {
        String fileNameWithoutExtension = StringUtils.getFileNameWithoutExtension(iGrammarSource.getFilePath());
        return withLanguageConfiguration(iGrammarSource, str, fileNameWithoutExtension, "source." + fileNameWithoutExtension);
    }

    @Override // io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition
    public Map<String, String> getEmbeddedLanguages() {
        Map<String, String> map = this.embeddedLanguages;
        return map == null ? Collections.EMPTY_MAP : map;
    }

    @Override // io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition
    public IGrammarSource getGrammar() {
        return this.grammarSource;
    }

    @Override // io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition
    public String getLanguageConfiguration() {
        return this.languageConfigurationPath;
    }

    @Override // io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition
    public String getName() {
        return this.name;
    }

    @Override // io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition
    public String getScopeName() {
        return this.scopeName;
    }

    public GrammarDefinition withEmbeddedLanguages(Map<String, String> map) {
        return map == null ? this : new DefaultGrammarDefinition(this.name, this.scopeName, this.grammarSource, this.languageConfigurationPath, map);
    }

    private DefaultGrammarDefinition(String str, String str2, IGrammarSource iGrammarSource, String str3, Map<String, String> map) {
        this(str, str2, iGrammarSource, str3);
        this.embeddedLanguages = map;
    }

    public static DefaultGrammarDefinition withGrammarSource(IGrammarSource iGrammarSource, String str, String str2) {
        return new DefaultGrammarDefinition(str, str2, iGrammarSource, null);
    }

    public static DefaultGrammarDefinition withLanguageConfiguration(IGrammarSource iGrammarSource, String str, String str2, String str3) {
        return new DefaultGrammarDefinition(str2, str3, iGrammarSource, str);
    }
}
