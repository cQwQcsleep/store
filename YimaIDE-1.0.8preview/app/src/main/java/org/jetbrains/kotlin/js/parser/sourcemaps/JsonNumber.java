package org.jetbrains.kotlin.js.parser.sourcemaps;

import java.io.IOException;
import java.io.Writer;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNumber;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonNode;", "value", "", "<init>", "(D)V", "getValue", "()D", "write", "", "writer", "Ljava/io/Writer;", "toString", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsonNumber extends JsonNode {
    private final double value;

    public JsonNumber(double d) {
        super(null);
        this.value = d;
    }

    public static /* synthetic */ JsonNumber copy$default(JsonNumber jsonNumber, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            d = jsonNumber.value;
        }
        return jsonNumber.copy(d);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getValue() {
        return this.value;
    }

    public final JsonNumber copy(double value) {
        return new JsonNumber(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof JsonNumber) && Double.compare(this.value, ((JsonNumber) other).value) == 0;
    }

    public final double getValue() {
        return this.value;
    }

    public int hashCode() {
        return Double.hashCode(this.value);
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public String toString() {
        return super.toString();
    }

    @Override // org.jetbrains.kotlin.js.parser.sourcemaps.JsonNode
    public void write(Writer writer) throws IOException {
        writer.getClass();
        double d = this.value;
        if (((long) d) == d) {
            writer.append((CharSequence) String.valueOf((long) d));
        } else {
            writer.append((CharSequence) String.valueOf(d));
        }
    }
}
