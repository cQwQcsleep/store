package org.jetbrains.kotlin.js.parser.sourcemaps;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B5\b\u0016\u0012*\u0010\u0002\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\b0\u0007\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0004\b\u0005\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\n\u0010\u0010\u001a\u00020\u0004H\u0096\u0080\u0004J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u001f\u0010\u0012\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonObject;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;", "properties", "", "", "<init>", "(Ljava/util/Map;)V", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "getProperties", "()Ljava/util/Map;", "write", "", "writer", "Ljava/io/Writer;", "toString", "component1", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsonObject extends JsonNode {
    private final Map<String, JsonNode> properties;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JsonObject(Pair<String, ? extends JsonNode>... pairArr) {
        this((Map<String, JsonNode>) MapsKt.toMutableMap(MapsKt.toMap(pairArr)));
        pairArr.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JsonObject copy$default(JsonObject jsonObject, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = jsonObject.properties;
        }
        return jsonObject.copy(map);
    }

    public final Map<String, JsonNode> component1() {
        return this.properties;
    }

    public final JsonObject copy(Map<String, JsonNode> properties) {
        properties.getClass();
        return new JsonObject(properties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof JsonObject) && Intrinsics.areEqual(this.properties, ((JsonObject) other).properties);
    }

    public final Map<String, JsonNode> getProperties() {
        return this.properties;
    }

    public int hashCode() {
        return this.properties.hashCode();
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public String toString() {
        return super.toString();
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public void write(Writer writer) throws IOException {
        writer.getClass();
        writer.append('{');
        boolean z = true;
        for (Map.Entry<String, JsonNode> entry : this.properties.entrySet()) {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            if (!z) {
                writer.append(',');
            }
            new JsonString(key).write(writer);
            writer.append(':');
            value.write(writer);
            z = false;
        }
        writer.append('}');
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonObject(Map<String, JsonNode> map) {
        super(null);
        map.getClass();
        this.properties = map;
    }
}
