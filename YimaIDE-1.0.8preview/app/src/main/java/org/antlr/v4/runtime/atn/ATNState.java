package org.antlr.v4.runtime.atn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.antlr.v4.runtime.misc.IntervalSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class ATNState {
    public static final int BASIC = 1;
    public static final int BLOCK_END = 8;
    public static final int BLOCK_START = 3;
    public static final int INITIAL_NUM_TRANSITIONS = 4;
    public static final int INVALID_STATE_NUMBER = -1;
    public static final int INVALID_TYPE = 0;
    public static final int LOOP_END = 12;
    public static final int PLUS_BLOCK_START = 4;
    public static final int PLUS_LOOP_BACK = 11;
    public static final int RULE_START = 2;
    public static final int RULE_STOP = 7;
    public static final int STAR_BLOCK_START = 5;
    public static final int STAR_LOOP_BACK = 9;
    public static final int STAR_LOOP_ENTRY = 10;
    public static final int TOKEN_START = 6;
    public static final List<String> serializationNames = Collections.unmodifiableList(Arrays.asList("INVALID", "BASIC", "RULE_START", "BLOCK_START", "PLUS_BLOCK_START", "STAR_BLOCK_START", "TOKEN_START", "RULE_STOP", "BLOCK_END", "STAR_LOOP_BACK", "STAR_LOOP_ENTRY", "PLUS_LOOP_BACK", "LOOP_END"));
    public IntervalSet nextTokenWithinRule;
    public int ruleIndex;
    public ATN atn = null;
    public int stateNumber = -1;
    public boolean epsilonOnlyTransitions = false;
    protected final List<Transition> transitions = new ArrayList(4);

    public void addTransition(int i, Transition transition) {
        if (this.transitions.isEmpty()) {
            this.epsilonOnlyTransitions = transition.isEpsilon();
        } else if (this.epsilonOnlyTransitions != transition.isEpsilon()) {
            System.err.format(Locale.getDefault(), "ATN state %d has both epsilon and non-epsilon transitions.\n", Integer.valueOf(this.stateNumber));
            this.epsilonOnlyTransitions = false;
        }
        for (Transition transition2 : this.transitions) {
            if (transition2.target.stateNumber == transition.target.stateNumber) {
                if (transition2.label() != null && transition.label() != null && transition2.label().equals(transition.label())) {
                    return;
                }
                if (transition2.isEpsilon() && transition.isEpsilon()) {
                    return;
                }
            }
        }
        this.transitions.add(i, transition);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ATNState) && this.stateNumber == ((ATNState) obj).stateNumber;
    }

    public int getNumberOfTransitions() {
        return this.transitions.size();
    }

    public abstract int getStateType();

    public Transition[] getTransitions() {
        return (Transition[]) this.transitions.toArray(new Transition[0]);
    }

    public int hashCode() {
        return this.stateNumber;
    }

    public boolean isNonGreedyExitState() {
        return false;
    }

    public final boolean onlyHasEpsilonTransitions() {
        return this.epsilonOnlyTransitions;
    }

    public Transition removeTransition(int i) {
        return this.transitions.remove(i);
    }

    public void setRuleIndex(int i) {
        this.ruleIndex = i;
    }

    public void setTransition(int i, Transition transition) {
        this.transitions.set(i, transition);
    }

    public String toString() {
        return String.valueOf(this.stateNumber);
    }

    public Transition transition(int i) {
        return this.transitions.get(i);
    }

    public void addTransition(Transition transition) {
        addTransition(this.transitions.size(), transition);
    }
}
