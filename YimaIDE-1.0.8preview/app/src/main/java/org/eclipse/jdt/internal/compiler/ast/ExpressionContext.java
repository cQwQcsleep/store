package org.eclipse.jdt.internal.compiler.ast;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum ExpressionContext {
    ASSIGNMENT_CONTEXT { // from class: org.eclipse.jdt.internal.compiler.ast.ExpressionContext.1
        @Override // org.eclipse.jdt.internal.compiler.ast.ExpressionContext
        public boolean definesTargetType() {
            return true;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "assignment context";
        }
    },
    INVOCATION_CONTEXT { // from class: org.eclipse.jdt.internal.compiler.ast.ExpressionContext.2
        @Override // org.eclipse.jdt.internal.compiler.ast.ExpressionContext
        public boolean definesTargetType() {
            return true;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "invocation context";
        }
    },
    CASTING_CONTEXT { // from class: org.eclipse.jdt.internal.compiler.ast.ExpressionContext.3
        @Override // org.eclipse.jdt.internal.compiler.ast.ExpressionContext
        public boolean definesTargetType() {
            return false;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "casting context";
        }
    },
    INSTANCEOF_CONTEXT { // from class: org.eclipse.jdt.internal.compiler.ast.ExpressionContext.4
        @Override // org.eclipse.jdt.internal.compiler.ast.ExpressionContext
        public boolean definesTargetType() {
            return false;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "instanceof context";
        }
    },
    VANILLA_CONTEXT { // from class: org.eclipse.jdt.internal.compiler.ast.ExpressionContext.5
        @Override // org.eclipse.jdt.internal.compiler.ast.ExpressionContext
        public boolean definesTargetType() {
            return false;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "vanilla context";
        }
    };

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static ExpressionContext[] valuesCustom() {
        ExpressionContext[] expressionContextArrValuesCustom = values();
        int length = expressionContextArrValuesCustom.length;
        ExpressionContext[] expressionContextArr = new ExpressionContext[length];
        System.arraycopy(expressionContextArrValuesCustom, 0, expressionContextArr, 0, length);
        return expressionContextArr;
    }

    public abstract boolean definesTargetType();
}
