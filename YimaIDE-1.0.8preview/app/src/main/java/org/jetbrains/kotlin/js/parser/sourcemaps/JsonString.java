package org.jetbrains.kotlin.js.parser.sourcemaps;

import java.io.IOException;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonString;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "write", "", "writer", "Ljava/io/Writer;", "toString", "component1", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsonString extends JsonNode {
    private final String value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonString(String str) {
        super(null);
        str.getClass();
        this.value = str;
    }

    public static /* synthetic */ JsonString copy$default(JsonString jsonString, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsonString.value;
        }
        return jsonString.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final JsonString copy(String value) {
        value.getClass();
        return new JsonString(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof JsonString) && Intrinsics.areEqual(this.value, ((JsonString) other).value);
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public String toString() {
        return super.toString();
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public void write(Writer writer) throws IOException {
        writer.getClass();
        writer.append('\"');
        String str = this.value;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                writer.append("\\\\");
            } else if (cCharAt == '\"') {
                writer.append("\\\"");
            } else if (cCharAt == '\r') {
                writer.append("\\r");
            } else if (cCharAt == '\n') {
                writer.append("\\n");
            } else if (cCharAt == '\t') {
                writer.append("\\t");
            } else if (cCharAt == '\b') {
                writer.append("\\b");
            } else if (cCharAt == '\f') {
                writer.append("\\f");
            } else if (' ' > cCharAt || cCharAt >= 127) {
                writer.append("\\u");
                int i2 = 16;
                for (int i3 = 0; i3 < 4; i3++) {
                    i2 -= 4;
                    int i4 = (cCharAt >>> i2) & 15;
                    writer.append((char) (i4 < 10 ? i4 + 48 : i4 + 87));
                }
                Unit unit = Unit.INSTANCE;
            } else {
                writer.append(cCharAt);
            }
        }
        writer.append('\"');
    }
}
