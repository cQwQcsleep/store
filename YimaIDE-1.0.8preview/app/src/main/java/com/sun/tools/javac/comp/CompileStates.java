package com.sun.tools.javac.comp;

import com.sun.tools.javac.util.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CompileStates extends HashMap<Env<AttrContext>, CompileState> {
    protected static final Context.Key<CompileStates> compileStatesKey = new Context.Key<>();
    private static final long serialVersionUID = 1812267524140424433L;
    protected transient Context context;

    public enum CompileState {
        INIT(0),
        PARSE(1),
        ENTER(2),
        PROCESS(3),
        ATTR(4),
        FLOW(5),
        WARN(6),
        TRANSTYPES(7),
        TRANSPATTERNS(8),
        LOWER(9),
        UNLAMBDA(10),
        GENERATE(11);

        private final int value;

        CompileState(int i) {
            this.value = i;
        }

        public static CompileState max(CompileState compileState, CompileState compileState2) {
            return compileState.value > compileState2.value ? compileState : compileState2;
        }

        public boolean isAfter(CompileState compileState) {
            return this.value > compileState.value;
        }
    }

    public CompileStates(Context context) {
        this.context = context;
        context.put(compileStatesKey, this);
    }

    public static CompileStates instance(Context context) {
        CompileStates compileStates = (CompileStates) context.get(compileStatesKey);
        return compileStates == null ? new CompileStates(context) : compileStates;
    }

    public boolean isDone(Env<AttrContext> env, CompileState compileState) {
        CompileState compileState2 = get(env);
        return (compileState2 == null || compileState.isAfter(compileState2)) ? false : true;
    }
}
