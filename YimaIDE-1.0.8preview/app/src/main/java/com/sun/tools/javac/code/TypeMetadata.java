package com.sun.tools.javac.code;

import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeMetadata {

    public static final class ConstantValue implements TypeMetadata {
        private final Object value;

        public ConstantValue(Object obj) {
            this.value = obj;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof ConstantValue) && Objects.equals(this.value, ((ConstantValue) obj).value);
        }

        public final int hashCode() {
            return Objects.hashCode(this.value);
        }

        public final String toString() {
            return "ConstantValue[value=" + Objects.toString(this.value) + "]";
        }

        public Object value() {
            return this.value;
        }
    }

    public static final class Annotations implements TypeMetadata {
        private final ListBuffer<Attribute.TypeCompound> annotationBuffer;

        public Annotations() {
            this((ListBuffer<Attribute.TypeCompound>) new ListBuffer());
        }

        public ListBuffer<Attribute.TypeCompound> annotationBuffer() {
            return this.annotationBuffer;
        }

        public List<Attribute.TypeCompound> annotations() {
            return this.annotationBuffer.toList();
        }

        public final boolean equals(Object obj) {
            return (obj instanceof Annotations) && Objects.equals(this.annotationBuffer, ((Annotations) obj).annotationBuffer);
        }

        public final int hashCode() {
            return Objects.hashCode(this.annotationBuffer);
        }

        public final String toString() {
            return "Annotations[annotationBuffer=" + Objects.toString(this.annotationBuffer) + "]";
        }

        public Annotations(ListBuffer<Attribute.TypeCompound> listBuffer) {
            this.annotationBuffer = listBuffer;
        }

        public Annotations(List<Attribute.TypeCompound> list) {
            this();
            this.annotationBuffer.appendList(list);
        }
    }
}
