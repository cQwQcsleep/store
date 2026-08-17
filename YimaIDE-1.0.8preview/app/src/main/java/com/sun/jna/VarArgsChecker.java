package com.sun.jna;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class VarArgsChecker {

    public static final class NoVarArgsChecker extends VarArgsChecker {
        private NoVarArgsChecker() {
            super();
        }

        @Override // com.sun.jna.VarArgsChecker
        public int fixedArgs(Method method) {
            return 0;
        }

        @Override // com.sun.jna.VarArgsChecker
        public boolean isVarArgs(Method method) {
            return false;
        }
    }

    public static final class RealVarArgsChecker extends VarArgsChecker {
        private RealVarArgsChecker() {
            super();
        }

        @Override // com.sun.jna.VarArgsChecker
        public int fixedArgs(Method method) {
            if (method.isVarArgs()) {
                return method.getParameterTypes().length - 1;
            }
            return 0;
        }

        @Override // com.sun.jna.VarArgsChecker
        public boolean isVarArgs(Method method) {
            return method.isVarArgs();
        }
    }

    public static VarArgsChecker create() {
        try {
            return Method.class.getMethod("isVarArgs", null) != null ? new RealVarArgsChecker() : new NoVarArgsChecker();
        } catch (NoSuchMethodException unused) {
            return new NoVarArgsChecker();
        } catch (SecurityException unused2) {
            return new NoVarArgsChecker();
        }
    }

    public abstract int fixedArgs(Method method);

    public abstract boolean isVarArgs(Method method);

    private VarArgsChecker() {
    }
}
