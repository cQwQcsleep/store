package org.jetbrains.kotlin.descriptors;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface SourceElement {
    public static final SourceElement NO_SOURCE = new SourceElement() { // from class: org.jetbrains.kotlin.descriptors.SourceElement.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/descriptors/SourceElement$1", "getContainingFile"));
        }

        @Override // org.jetbrains.kotlin.descriptors.SourceElement
        public SourceFile getContainingFile() {
            SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
            if (sourceFile == null) {
                $$$reportNull$$$0(0);
            }
            return sourceFile;
        }

        public String toString() {
            return "NO_SOURCE";
        }
    };

    SourceFile getContainingFile();
}
