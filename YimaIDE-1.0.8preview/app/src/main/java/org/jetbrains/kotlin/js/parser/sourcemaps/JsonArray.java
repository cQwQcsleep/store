package org.jetbrains.kotlin.js.parser.sourcemaps;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\"\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonArray;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;", "elements", "", "<init>", "(Ljava/util/List;)V", "", "([Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;)V", "getElements", "()Ljava/util/List;", "write", "", "writer", "Ljava/io/Writer;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsonArray extends JsonNode {
    private final List<JsonNode> elements;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JsonArray(JsonNode... jsonNodeArr) {
        this((List<JsonNode>) ArraysKt.toMutableList(jsonNodeArr));
        jsonNodeArr.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JsonArray copy$default(JsonArray jsonArray, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = jsonArray.elements;
        }
        return jsonArray.copy(list);
    }

    public final List<JsonNode> component1() {
        return this.elements;
    }

    public final JsonArray copy(List<JsonNode> elements) {
        elements.getClass();
        return new JsonArray(elements);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof JsonArray) && Intrinsics.areEqual(this.elements, ((JsonArray) other).elements);
    }

    public final List<JsonNode> getElements() {
        return this.elements;
    }

    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public String toString() {
        return "JsonArray(elements=" + this.elements + ')';
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public void write(Writer writer) throws IOException {
        writer.getClass();
        writer.append('[');
        boolean z = true;
        for (JsonNode jsonNode : this.elements) {
            if (!z) {
                writer.append(',');
            }
            jsonNode.write(writer);
            z = false;
        }
        writer.append(']');
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonArray(List<JsonNode> list) {
        super(null);
        list.getClass();
        this.elements = list;
    }
}
