package org.eclipse.jdt.internal.compiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class DefaultErrorHandlingPolicies {
    public static IErrorHandlingPolicy exitAfterAllProblems() {
        return new IErrorHandlingPolicy() { // from class: org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies.1
            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean ignoreAllErrors() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean proceedOnErrors() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean stopOnFirstError() {
                return false;
            }
        };
    }

    public static IErrorHandlingPolicy exitOnFirstError() {
        return new IErrorHandlingPolicy() { // from class: org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies.2
            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean ignoreAllErrors() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean proceedOnErrors() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean stopOnFirstError() {
                return true;
            }
        };
    }

    public static IErrorHandlingPolicy ignoreAllProblems() {
        return new IErrorHandlingPolicy() { // from class: org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies.5
            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean ignoreAllErrors() {
                return true;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean proceedOnErrors() {
                return true;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean stopOnFirstError() {
                return false;
            }
        };
    }

    public static IErrorHandlingPolicy proceedOnFirstError() {
        return new IErrorHandlingPolicy() { // from class: org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies.3
            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean ignoreAllErrors() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean proceedOnErrors() {
                return true;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean stopOnFirstError() {
                return true;
            }
        };
    }

    public static IErrorHandlingPolicy proceedWithAllProblems() {
        return new IErrorHandlingPolicy() { // from class: org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies.4
            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean ignoreAllErrors() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean proceedOnErrors() {
                return true;
            }

            @Override // org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy
            public boolean stopOnFirstError() {
                return false;
            }
        };
    }
}
