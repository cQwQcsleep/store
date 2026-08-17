package org.eclipse.jdt.internal.compiler.parser;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface ConflictedParser {
    boolean atConflictScenario(int i);

    boolean automatonWillShift(int i);

    boolean isParsingJava14();

    boolean isParsingModuleDeclaration();
}
