package org.eclipse.jdt.internal.compiler.lookup;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
interface ParameterNonNullDefaultProvider {
    public static final ParameterNonNullDefaultProvider FALSE_PROVIDER = new ParameterNonNullDefaultProvider() { // from class: org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider.1
        @Override // org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider
        public boolean hasAnyNonNullDefault() {
            return false;
        }

        @Override // org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider
        public boolean hasNonNullDefaultForParam(int i) {
            return false;
        }
    };
    public static final ParameterNonNullDefaultProvider TRUE_PROVIDER = new ParameterNonNullDefaultProvider() { // from class: org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider.2
        @Override // org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider
        public boolean hasAnyNonNullDefault() {
            return true;
        }

        @Override // org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider
        public boolean hasNonNullDefaultForParam(int i) {
            return true;
        }
    };

    public static class MixedProvider implements ParameterNonNullDefaultProvider {
        private final boolean[] result;

        public MixedProvider(boolean[] zArr) {
            this.result = zArr;
        }

        @Override // org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider
        public boolean hasAnyNonNullDefault() {
            return true;
        }

        @Override // org.eclipse.jdt.internal.compiler.lookup.ParameterNonNullDefaultProvider
        public boolean hasNonNullDefaultForParam(int i) {
            return this.result[i];
        }
    }

    boolean hasAnyNonNullDefault();

    boolean hasNonNullDefaultForParam(int i);
}
