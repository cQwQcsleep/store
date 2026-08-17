package io.github.rosemoe.sora.langs.textmate.registry;

import android.util.Pair;
import io.github.rosemoe.sora.langs.textmate.registry.dsl.LanguageDefinitionListBuilder;
import io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.langs.textmate.registry.reader.LanguageDefinitionReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.tm4e.core.grammar.IGrammar;
import org.eclipse.tm4e.core.registry.Registry;
import org.eclipse.tm4e.languageconfiguration.internal.model.LanguageConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class GrammarRegistry {
    private static GrammarRegistry instance;
    private GrammarRegistry parent;
    private Registry registry = new Registry();
    private final Map<String, LanguageConfiguration> languageConfigurationMap = new LinkedHashMap();
    private final Map<String, Integer> scopeName2GrammarId = new LinkedHashMap();
    private final Map<String, String> grammarFileName2ScopeName = new LinkedHashMap();
    private final Map<String, GrammarDefinition> scopeName2GrammarDefinition = new LinkedHashMap();

    public GrammarRegistry(GrammarRegistry grammarRegistry) {
        this.parent = grammarRegistry;
    }

    private synchronized IGrammar doLoadGrammar(GrammarDefinition grammarDefinition) {
        IGrammar iGrammarAddGrammar;
        InputStream inputStreamTryGetInputStream;
        try {
            String languageConfiguration = grammarDefinition.getLanguageConfiguration();
            if (languageConfiguration != null && (inputStreamTryGetInputStream = FileProviderRegistry.getInstance().tryGetInputStream(languageConfiguration)) != null) {
                this.languageConfigurationMap.put(grammarDefinition.getScopeName(), LanguageConfiguration.load(new InputStreamReader(inputStreamTryGetInputStream)));
            }
            boolean zIsEmpty = grammarDefinition.getEmbeddedLanguages().isEmpty();
            Registry registry = this.registry;
            iGrammarAddGrammar = !zIsEmpty ? registry.addGrammar(grammarDefinition.getGrammar()) : registry.addGrammar(grammarDefinition.getGrammar(), (List) null, Integer.valueOf(getOrPullGrammarId(grammarDefinition.getScopeName())), findGrammarIds(grammarDefinition.getEmbeddedLanguages()));
            if (grammarDefinition.getScopeName() != null && !iGrammarAddGrammar.getScopeName().equals(grammarDefinition.getScopeName())) {
                throw new IllegalStateException(String.format("The scope name loaded by the grammar file does not match the declared scope name, it should be %s instead of %s", iGrammarAddGrammar.getScopeName(), grammarDefinition.getScopeName()));
            }
        } catch (Throwable th) {
            throw th;
        }
        return iGrammarAddGrammar;
    }

    private synchronized Map<String, Integer> findGrammarIds(Map<String, String> map) {
        HashMap map2;
        map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map2.put(entry.getKey(), Integer.valueOf(getOrPullGrammarId(getGrammarScopeName(entry.getValue()))));
        }
        return map2;
    }

    private String getGrammarScopeName(String str) {
        String str2;
        return (this.scopeName2GrammarDefinition.containsKey(str) || (str2 = this.grammarFileName2ScopeName.get(str)) == null) ? str : str2;
    }

    public static synchronized GrammarRegistry getInstance() {
        try {
            if (instance == null) {
                GrammarRegistry grammarRegistry = new GrammarRegistry();
                instance = grammarRegistry;
                grammarRegistry.initThemeListener();
            }
        } catch (Throwable th) {
            throw th;
        }
        return instance;
    }

    private synchronized int getOrPullGrammarId(String str) {
        Integer numValueOf;
        try {
            numValueOf = this.scopeName2GrammarId.get(str);
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(this.scopeName2GrammarId.size() + 2);
            }
            this.scopeName2GrammarId.put(str, numValueOf);
        } catch (Throwable th) {
            throw th;
        }
        return numValueOf.intValue();
    }

    private void initThemeListener() {
        ThemeRegistry themeRegistry = ThemeRegistry.getInstance();
        ThemeRegistry.ThemeChangeListener themeChangeListener = new ThemeRegistry.ThemeChangeListener() { // from class: g06
            @Override // io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry.ThemeChangeListener
            public final void onChangeTheme(ThemeModel themeModel) {
                this.a.lambda$initThemeListener$0(themeModel);
            }
        };
        if (themeRegistry.hasListener(themeChangeListener)) {
            return;
        }
        themeRegistry.addListener(themeChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initThemeListener$0(ThemeModel themeModel) {
        try {
            setTheme(themeModel);
        } catch (Exception e) {
            rc6.a(e);
        }
    }

    private void prepareLoadGrammars(List<GrammarDefinition> list) {
        Iterator<GrammarDefinition> it2 = list.iterator();
        while (it2.hasNext()) {
            getOrPullGrammarId(it2.next().getScopeName());
        }
    }

    public synchronized void dispose(boolean z) {
        if (this.registry == null) {
            return;
        }
        this.registry = null;
        this.grammarFileName2ScopeName.clear();
        this.languageConfigurationMap.clear();
        this.scopeName2GrammarId.clear();
        this.scopeName2GrammarDefinition.clear();
        GrammarRegistry grammarRegistry = this.parent;
        if (grammarRegistry != null && z) {
            grammarRegistry.dispose(true);
        }
    }

    public IGrammar findGrammar(String str, boolean z) {
        GrammarRegistry grammarRegistry;
        IGrammar iGrammarGrammarForScopeName = this.registry.grammarForScopeName(str);
        if (iGrammarGrammarForScopeName != null) {
            return iGrammarGrammarForScopeName;
        }
        if (z && (grammarRegistry = this.parent) != null) {
            return grammarRegistry.findGrammar(str, true);
        }
        return null;
    }

    public LanguageConfiguration findLanguageConfiguration(String str, boolean z) {
        GrammarRegistry grammarRegistry;
        LanguageConfiguration languageConfiguration = this.languageConfigurationMap.get(str);
        if (languageConfiguration != null) {
            return languageConfiguration;
        }
        if (z && (grammarRegistry = this.parent) != null) {
            return grammarRegistry.findLanguageConfiguration(str, true);
        }
        return null;
    }

    @Deprecated
    public synchronized void languageConfigurationToGrammar(LanguageConfiguration languageConfiguration, IGrammar iGrammar) {
        this.languageConfigurationMap.put(iGrammar.getScopeName(), languageConfiguration);
    }

    public synchronized IGrammar loadGrammar(GrammarDefinition grammarDefinition) {
        String name = grammarDefinition.getName();
        if (this.grammarFileName2ScopeName.containsKey(name) && grammarDefinition.getScopeName() != null) {
            return this.registry.grammarForScopeName(grammarDefinition.getScopeName());
        }
        IGrammar iGrammarDoLoadGrammar = doLoadGrammar(grammarDefinition);
        if (grammarDefinition.getScopeName() != null) {
            this.grammarFileName2ScopeName.put(name, grammarDefinition.getScopeName());
            this.scopeName2GrammarDefinition.put(iGrammarDoLoadGrammar.getScopeName(), grammarDefinition);
        }
        return iGrammarDoLoadGrammar;
    }

    public List<IGrammar> loadGrammars(List<GrammarDefinition> list) {
        prepareLoadGrammars(list);
        return (List) list.stream().map(new Function() { // from class: h06
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.loadGrammar((GrammarDefinition) obj);
            }
        }).collect(Collectors.toList());
    }

    public Pair<IGrammar, LanguageConfiguration> loadLanguageAndLanguageConfiguration(GrammarDefinition grammarDefinition) {
        IGrammar iGrammarLoadGrammar = loadGrammar(grammarDefinition);
        return Pair.create(iGrammarLoadGrammar, findLanguageConfiguration(iGrammarLoadGrammar.getScopeName(), false));
    }

    public synchronized void setTheme(ThemeModel themeModel) throws Exception {
        try {
            if (!themeModel.isLoaded()) {
                themeModel.load(this.registry.getColorMap());
            }
            this.registry.setTheme(themeModel.getTheme());
        } catch (Throwable th) {
            throw th;
        }
    }

    public IGrammar findGrammar(String str) {
        return findGrammar(str, true);
    }

    public LanguageConfiguration findLanguageConfiguration(String str) {
        return findLanguageConfiguration(str, true);
    }

    public List<IGrammar> loadGrammars(LanguageDefinitionListBuilder languageDefinitionListBuilder) {
        return loadGrammars(languageDefinitionListBuilder.build());
    }

    public List<IGrammar> loadGrammars(String str) {
        return loadGrammars(LanguageDefinitionReader.read(str));
    }

    private GrammarRegistry() {
    }

    public void dispose() {
        dispose(false);
    }
}
