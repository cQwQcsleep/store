package com.sun.tools.javac.comp;

import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WarningAnalyzer {
    protected static final Context.Key<WarningAnalyzer> contextKey = new Context.Key<>();
    private final Log log;
    private final ThisEscapeAnalyzer thisEscapeAnalyzer;

    public WarningAnalyzer(Context context) {
        context.put(contextKey, this);
        this.log = Log.instance(context);
        this.thisEscapeAnalyzer = ThisEscapeAnalyzer.instance(context);
    }

    public static WarningAnalyzer instance(Context context) {
        WarningAnalyzer warningAnalyzer = (WarningAnalyzer) context.get(contextKey);
        return warningAnalyzer == null ? new WarningAnalyzer(context) : warningAnalyzer;
    }

    public void analyzeTree(Env<AttrContext> env) {
        this.thisEscapeAnalyzer.analyzeTree(env);
    }
}
