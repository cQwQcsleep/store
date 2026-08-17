package com.reandroid.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DiagnosticMessage {

    public enum Type {
        INFO("I"),
        WARN("W"),
        ERROR("E"),
        VERBOSE("V"),
        DEBUG("D");

        String simpleName;

        Type(String str) {
            this.simpleName = str;
        }

        public String getName() {
            return this.simpleName;
        }
    }

    default Origin getSource() {
        return null;
    }

    default String getTag() {
        return null;
    }

    Type type();

    public static class StringMessage implements DiagnosticMessage {
        private final String message;
        private final Origin source;
        private final String tag;
        private final Type type;

        public StringMessage(Type type, Origin origin, String str, String str2) {
            this.type = type;
            this.source = origin;
            this.tag = str;
            this.message = str2;
        }

        public String getMessage() {
            return this.message;
        }

        @Override // com.reandroid.common.DiagnosticMessage
        public Origin getSource() {
            return this.source;
        }

        @Override // com.reandroid.common.DiagnosticMessage
        public String getTag() {
            return this.tag;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(type().getName());
            sb.append(": ");
            String tag = getTag();
            if (tag != null) {
                sb.append(tag);
                sb.append(" : ");
            }
            Origin source = getSource();
            if (source != null) {
                sb.append(source);
                sb.append(", ");
            }
            sb.append(getMessage());
            return sb.toString();
        }

        @Override // com.reandroid.common.DiagnosticMessage
        public Type type() {
            return this.type;
        }

        public StringMessage(Type type, String str, String str2) {
            this(type, null, str, str2);
        }

        public StringMessage(Type type, String str) {
            this(type, null, null, str);
        }
    }
}
