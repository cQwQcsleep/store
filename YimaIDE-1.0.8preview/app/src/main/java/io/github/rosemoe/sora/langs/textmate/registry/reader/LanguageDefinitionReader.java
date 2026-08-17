package io.github.rosemoe.sora.langs.textmate.registry.reader;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.DefaultGrammarDefinition;
import io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition;
import io.github.rosemoe.sora.langs.textmate.registry.reader.LanguageDefinitionReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.tm4e.core.registry.IGrammarSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class LanguageDefinitionReader {

    public static class LanguageDefinitionList {

        @SerializedName("languages")
        private List<GrammarDefinition> grammarDefinition;

        public LanguageDefinitionList(List<GrammarDefinition> list) {
            this.grammarDefinition = list;
        }

        public List<GrammarDefinition> getLanguageDefinition() {
            return this.grammarDefinition;
        }

        public void setLanguageDefinition(List<GrammarDefinition> list) {
            this.grammarDefinition = list;
        }
    }

    public static /* synthetic */ GrammarDefinition a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String asString = asJsonObject.get("grammar").getAsString();
        String asString2 = asJsonObject.get("name").getAsString();
        String asString3 = asJsonObject.get("scopeName").getAsString();
        JsonElement jsonElement2 = asJsonObject.get("embeddedLanguages");
        JsonObject asJsonObject2 = (jsonElement2 == null || !jsonElement2.isJsonObject()) ? null : jsonElement2.getAsJsonObject();
        JsonElement jsonElement3 = asJsonObject.get("languageConfiguration");
        String asString4 = (jsonElement3 == null || jsonElement3.isJsonNull()) ? null : jsonElement3.getAsString();
        InputStream inputStreamTryGetInputStream = FileProviderRegistry.getInstance().tryGetInputStream(asString);
        if (inputStreamTryGetInputStream == null) {
            w01.a("grammar file can not be opened");
            return null;
        }
        DefaultGrammarDefinition defaultGrammarDefinitionWithLanguageConfiguration = DefaultGrammarDefinition.withLanguageConfiguration(IGrammarSource.fromInputStream(inputStreamTryGetInputStream, asString, Charset.defaultCharset()), asString4, asString2, asString3);
        if (asJsonObject2 == null) {
            return defaultGrammarDefinitionWithLanguageConfiguration;
        }
        HashMap map = new HashMap();
        for (Map.Entry entry : asJsonObject2.entrySet()) {
            JsonElement jsonElement4 = (JsonElement) entry.getValue();
            if (!jsonElement4.isJsonNull()) {
                map.put((String) entry.getKey(), jsonElement4.getAsString());
            }
        }
        return defaultGrammarDefinitionWithLanguageConfiguration.withEmbeddedLanguages(map);
    }

    private static List<GrammarDefinition> read(BufferedReader bufferedReader) {
        return ((LanguageDefinitionList) new GsonBuilder().registerTypeAdapter(GrammarDefinition.class, new JsonDeserializer() { // from class: mo8
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LanguageDefinitionReader.a(jsonElement, type, jsonDeserializationContext);
            }
        }).create().fromJson(bufferedReader, LanguageDefinitionList.class)).grammarDefinition;
    }

    public static List<GrammarDefinition> read(String str) {
        InputStream inputStreamTryGetInputStream = FileProviderRegistry.getInstance().tryGetInputStream(str);
        if (inputStreamTryGetInputStream == null) {
            return Collections.EMPTY_LIST;
        }
        return read(new BufferedReader(new InputStreamReader(inputStreamTryGetInputStream)));
    }
}
