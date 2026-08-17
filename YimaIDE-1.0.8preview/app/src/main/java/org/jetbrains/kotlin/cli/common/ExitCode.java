package org.jetbrains.kotlin.cli.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public enum ExitCode {
    OK(0),
    COMPILATION_ERROR(1),
    INTERNAL_ERROR(2),
    SCRIPT_EXECUTION_ERROR(3),
    OOM_ERROR(137);

    private final int code;

    ExitCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
