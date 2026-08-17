package javax.annotation.processing;

import defpackage.x0e;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Completions {

    public static class SimpleCompletion implements Completion {
        private String message;
        private String value;

        public SimpleCompletion(String str, String str2) {
            if (str == null || str2 == null) {
                x0e.a("Null completion strings not accepted.");
                throw null;
            }
            this.value = str;
            this.message = str2;
        }

        @Override // javax.annotation.processing.Completion
        public String getMessage() {
            return this.message;
        }

        @Override // javax.annotation.processing.Completion
        public String getValue() {
            return this.value;
        }

        public String toString() {
            return "[\"" + this.value + "\", \"" + this.message + "\"]";
        }
    }

    private Completions() {
    }

    public static Completion of(String str) {
        return new SimpleCompletion(str, "");
    }

    public static Completion of(String str, String str2) {
        return new SimpleCompletion(str, str2);
    }
}
