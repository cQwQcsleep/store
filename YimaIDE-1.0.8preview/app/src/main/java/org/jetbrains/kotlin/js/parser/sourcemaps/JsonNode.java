package org.jetbrains.kotlin.js.parser.sourcemaps;

import java.io.StringWriter;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004\u0082\u0001\u0006\n\u000b\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;", "", "<init>", "()V", "write", "", "writer", "Ljava/io/Writer;", "toString", "", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonArray;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonBoolean;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNull;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNumber;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonObject;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonString;", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JsonNode {
    public /* synthetic */ JsonNode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        write(stringWriter);
        String string = stringWriter.toString();
        string.getClass();
        return string;
    }

    public abstract void write(Writer writer);

    private JsonNode() {
    }
}
