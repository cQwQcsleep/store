package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MatchBindingsComputer extends TreeScanner {
    public static final MatchBindings EMPTY = new MatchBindings(List.nil(), List.nil());
    protected static final Context.Key<MatchBindingsComputer> matchBindingsComputerKey = new Context.Key<>();
    private final Log log;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.MatchBindingsComputer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.AND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NOT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BINDINGPATTERN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPETEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PARENS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.RECORDPATTERN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONDEXPR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public MatchBindingsComputer(Context context) {
        this.log = Log.instance(context);
    }

    public static MatchBindingsComputer instance(Context context) {
        MatchBindingsComputer matchBindingsComputer = (MatchBindingsComputer) context.get(matchBindingsComputerKey);
        return matchBindingsComputer == null ? new MatchBindingsComputer(context) : matchBindingsComputer;
    }

    private List<Symbol.BindingSymbol> intersection(JCDiagnostic.DiagnosticPosition diagnosticPosition, List<Symbol.BindingSymbol> list, List<Symbol.BindingSymbol> list2) {
        List<Symbol.BindingSymbol> listNil = List.nil();
        for (Symbol.BindingSymbol bindingSymbol : list) {
            for (Symbol.BindingSymbol bindingSymbol2 : list2) {
                if (bindingSymbol.name == bindingSymbol2.name && (bindingSymbol.flags() & Flags.CLASH) == 0 && (bindingSymbol2.flags() & Flags.CLASH) == 0) {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.MatchBindingExists);
                    bindingSymbol2.flags_field |= Flags.CLASH;
                    listNil = listNil.append(bindingSymbol2);
                }
            }
        }
        return listNil;
    }

    @SafeVarargs
    private final List<Symbol.BindingSymbol> union(JCDiagnostic.DiagnosticPosition diagnosticPosition, List<Symbol.BindingSymbol> list, List<Symbol.BindingSymbol>... listArr) {
        for (List<Symbol.BindingSymbol> list2 : listArr) {
            for (Symbol.BindingSymbol bindingSymbol : list2) {
                for (Symbol.BindingSymbol bindingSymbol2 : list) {
                    if (bindingSymbol2.name == bindingSymbol.name && (bindingSymbol2.flags() & Flags.CLASH) == 0 && (bindingSymbol.flags() & Flags.CLASH) == 0) {
                        this.log.error(diagnosticPosition, CompilerProperties.Errors.MatchBindingExists);
                        bindingSymbol.flags_field |= Flags.CLASH;
                    }
                }
                list = list.append(bindingSymbol);
            }
        }
        return list;
    }

    public MatchBindings andOperation(JCDiagnostic.DiagnosticPosition diagnosticPosition, MatchBindings matchBindings, MatchBindings matchBindings2) {
        return new MatchBindings(union(diagnosticPosition, matchBindings.bindingsWhenTrue, matchBindings2.bindingsWhenTrue), intersection(diagnosticPosition, matchBindings.bindingsWhenFalse, matchBindings2.bindingsWhenFalse));
    }

    public MatchBindings binary(JCTree jCTree, MatchBindings matchBindings, MatchBindings matchBindings2) {
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i != 1) {
            return i != 2 ? EMPTY : new MatchBindings(intersection(jCTree.pos(), matchBindings.bindingsWhenTrue, matchBindings2.bindingsWhenTrue), union(jCTree.pos(), matchBindings.bindingsWhenFalse, matchBindings2.bindingsWhenFalse));
        }
        return andOperation(jCTree.pos(), matchBindings, matchBindings2);
    }

    public MatchBindings caseGuard(JCTree.JCCase jCCase, MatchBindings matchBindings, MatchBindings matchBindings2) {
        return andOperation(jCCase.pos(), matchBindings, matchBindings2);
    }

    public MatchBindings conditional(JCTree jCTree, MatchBindings matchBindings, MatchBindings matchBindings2, MatchBindings matchBindings3) {
        MatchBindings matchBindings4 = EMPTY;
        if (matchBindings == matchBindings4 && matchBindings2 == matchBindings4 && matchBindings3 == matchBindings4) {
            return matchBindings4;
        }
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        List<Symbol.BindingSymbol> listIntersection = intersection(diagnosticPositionPos, matchBindings.bindingsWhenTrue, matchBindings3.bindingsWhenTrue);
        List<Symbol.BindingSymbol> listIntersection2 = intersection(diagnosticPositionPos, matchBindings.bindingsWhenFalse, matchBindings2.bindingsWhenTrue);
        List<Symbol.BindingSymbol> listIntersection3 = intersection(diagnosticPositionPos, matchBindings2.bindingsWhenTrue, matchBindings3.bindingsWhenTrue);
        List<Symbol.BindingSymbol> listIntersection4 = intersection(diagnosticPositionPos, matchBindings.bindingsWhenTrue, matchBindings3.bindingsWhenFalse);
        List<Symbol.BindingSymbol> listIntersection5 = intersection(diagnosticPositionPos, matchBindings.bindingsWhenFalse, matchBindings2.bindingsWhenFalse);
        return new MatchBindings(union(diagnosticPositionPos, listIntersection3, listIntersection, listIntersection2), union(diagnosticPositionPos, intersection(diagnosticPositionPos, matchBindings2.bindingsWhenFalse, matchBindings3.bindingsWhenFalse), listIntersection4, listIntersection5));
    }

    public MatchBindings finishBindings(JCTree jCTree, MatchBindings matchBindings) {
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return matchBindings;
            default:
                return EMPTY;
        }
    }

    public MatchBindings switchCase(JCTree jCTree, MatchBindings matchBindings, MatchBindings matchBindings2) {
        if (matchBindings == null || matchBindings.nullPattern) {
            return matchBindings2;
        }
        return matchBindings2.nullPattern ? matchBindings : new MatchBindings(intersection(jCTree.pos(), matchBindings.bindingsWhenTrue, matchBindings2.bindingsWhenTrue), intersection(jCTree.pos(), matchBindings.bindingsWhenFalse, matchBindings2.bindingsWhenFalse));
    }

    public MatchBindings unary(JCTree jCTree, MatchBindings matchBindings) {
        return (matchBindings == EMPTY || !jCTree.hasTag(JCTree.Tag.NOT)) ? matchBindings : new MatchBindings(matchBindings.bindingsWhenFalse, matchBindings.bindingsWhenTrue);
    }

    public static class MatchBindings {
        public final List<Symbol.BindingSymbol> bindingsWhenFalse;
        public final List<Symbol.BindingSymbol> bindingsWhenTrue;
        public final boolean nullPattern;

        public MatchBindings(List<Symbol.BindingSymbol> list, List<Symbol.BindingSymbol> list2, boolean z) {
            this.bindingsWhenTrue = list;
            this.bindingsWhenFalse = list2;
            this.nullPattern = z;
        }

        public MatchBindings(List<Symbol.BindingSymbol> list, List<Symbol.BindingSymbol> list2) {
            this(list, list2, false);
        }
    }
}
