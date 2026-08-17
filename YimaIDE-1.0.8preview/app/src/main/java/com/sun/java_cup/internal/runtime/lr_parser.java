package com.sun.java_cup.internal.runtime;

import com.sun.java_cup.internal.runtime.lr_parser;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class lr_parser {
    public static final int ID_GROUP = 1;
    public static final int ID_OPERATOR = 2;
    public static final int ID_TOTAL_OPERATOR = 3;
    protected static final int _error_sync_size = 3;
    protected boolean _done_parsing;
    private Scanner _scanner;
    protected short[][] action_tab;
    protected Symbol cur_token;
    private int grpCount;
    public int grpLimit;
    private boolean isLiteral;
    private int lastSym;
    protected Symbol[] lookahead;
    protected int lookahead_pos;
    private int opCount;
    public int opLimit;
    private boolean overLimit;
    protected short[][] production_tab;
    protected short[][] reduce_tab;
    protected Stack<Symbol> stack;
    protected int tos;
    private int totalOpCount;
    public int totalOpLimit;

    public lr_parser() {
        this.isLiteral = false;
        this.grpCount = 0;
        this.opCount = 0;
        this.totalOpCount = 0;
        this.overLimit = false;
        this.grpLimit = 0;
        this.opLimit = 0;
        this.totalOpLimit = 0;
        this._done_parsing = false;
        this.stack = new Stack<>();
    }

    public static /* synthetic */ boolean a(int i, int i2) {
        return i2 == i;
    }

    private boolean contains(int[] iArr, final int i) {
        return Arrays.stream(iArr).anyMatch(new IntPredicate() { // from class: fkh
            @Override // java.util.function.IntPredicate
            public final boolean test(int i2) {
                return lr_parser.a(i, i2);
            }
        });
    }

    public static short[][] unpackFromStrings(String[] strArr) {
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        int iCharAt = (sb.charAt(0) << 16) | sb.charAt(1);
        short[][] sArr = new short[iCharAt][];
        int i2 = 2;
        for (int i3 = 0; i3 < iCharAt; i3++) {
            int iCharAt2 = (sb.charAt(i2) << 16) | sb.charAt(i2 + 1);
            i2 += 2;
            sArr[i3] = new short[iCharAt2];
            int i4 = 0;
            while (i4 < iCharAt2) {
                sArr[i3][i4] = (short) (sb.charAt(i2) - 2);
                i4++;
                i2++;
            }
        }
        return sArr;
    }

    public abstract int EOF_sym();

    public abstract short[][] action_table();

    public boolean advance_lookahead() {
        int i = this.lookahead_pos + 1;
        this.lookahead_pos = i;
        return i < error_sync_size();
    }

    public Symbol cur_err_token() {
        return this.lookahead[this.lookahead_pos];
    }

    public void debug_message(String str) {
        System.err.println(str);
    }

    public Symbol debug_parse() throws Exception {
        Stack<Symbol> stack;
        this.production_tab = production_table();
        this.action_tab = action_table();
        this.reduce_tab = reduce_table();
        debug_message("# Initializing parser");
        init_actions();
        user_init();
        this.cur_token = scan();
        debug_message("# Current Symbol is #" + this.cur_token.sym);
        this.stack.removeAllElements();
        this.stack.push(new Symbol(0, start_state()));
        this.tos = 0;
        this._done_parsing = false;
        Symbol symbolPeek = null;
        while (!this._done_parsing) {
            if (this.cur_token.used_by_parser) {
                throw new Error("Symbol recycling detected (fix your scanner).");
            }
            short s = get_action(this.stack.peek().parse_state, this.cur_token.sym);
            if (s > 0) {
                Symbol symbol = this.cur_token;
                symbol.parse_state = s - 1;
                symbol.used_by_parser = true;
                debug_shift(symbol);
                this.stack.push(this.cur_token);
                this.tos++;
                this.cur_token = scan();
                debug_message("# Current token is " + this.cur_token);
            } else if (s < 0) {
                int i = (-s) - 1;
                Symbol symbolDo_action = do_action(i, this, this.stack, this.tos);
                short[] sArr = this.production_tab[i];
                short s2 = sArr[0];
                short s3 = sArr[1];
                debug_reduce(i, s2, s3);
                int i2 = 0;
                while (true) {
                    stack = this.stack;
                    if (i2 >= s3) {
                        break;
                    }
                    stack.pop();
                    this.tos--;
                    i2++;
                }
                short s4 = get_reduce(stack.peek().parse_state, s2);
                debug_message("# Reduce rule: top state " + this.stack.peek().parse_state + ", lhs sym " + ((int) s2) + " -> state " + ((int) s4));
                symbolDo_action.parse_state = s4;
                symbolDo_action.used_by_parser = true;
                this.stack.push(symbolDo_action);
                this.tos = this.tos + 1;
                debug_message("# Goto state #" + ((int) s4));
                symbolPeek = symbolDo_action;
            } else if (s == 0) {
                syntax_error(this.cur_token);
                if (error_recovery(true)) {
                    symbolPeek = this.stack.peek();
                } else {
                    unrecovered_syntax_error(this.cur_token);
                    done_parsing();
                }
            }
        }
        return symbolPeek;
    }

    public void debug_reduce(int i, int i2, int i3) {
        debug_message("# Reduce with prod #" + i + " [NT=" + i2 + ", SZ=" + i3 + "]");
    }

    public void debug_shift(Symbol symbol) {
        debug_message("# Shift under term #" + symbol.sym + " to state #" + symbol.parse_state);
    }

    public void debug_stack() {
        StringBuilder sb = new StringBuilder("## STACK:");
        for (int i = 0; i < this.stack.size(); i++) {
            Symbol symbol = this.stack.get(i);
            sb.append(" <state " + symbol.parse_state + ", sym " + symbol.sym + ">");
            if (i % 3 == 2 || i == this.stack.size() - 1) {
                debug_message(sb.toString());
                sb = new StringBuilder("         ");
            }
        }
    }

    public abstract Symbol do_action(int i, lr_parser lr_parserVar, Stack<Symbol> stack, int i2) throws Exception;

    public void done_parsing() {
        this._done_parsing = true;
    }

    public void dump_stack() {
        if (this.stack == null) {
            debug_message("# Stack dump requested, but stack is null");
            return;
        }
        debug_message("============ Parse Stack Dump ============");
        for (int i = 0; i < this.stack.size(); i++) {
            debug_message("Symbol: " + this.stack.get(i).sym + " State: " + this.stack.get(i).parse_state);
        }
        debug_message("==========================================");
    }

    public boolean error_recovery(boolean z) throws Exception {
        if (z) {
            debug_message("# Attempting error recovery");
        }
        if (!find_recovery_config(z)) {
            if (z) {
                debug_message("# Error recovery fails");
            }
            return false;
        }
        read_lookahead();
        while (true) {
            if (z) {
                debug_message("# Trying to parse ahead");
            }
            if (try_parse_ahead(z)) {
                if (z) {
                    debug_message("# Parse-ahead ok, going back to normal parse");
                }
                parse_lookahead(z);
                return true;
            }
            if (this.lookahead[0].sym == EOF_sym()) {
                if (z) {
                    debug_message("# Error recovery fails at EOF");
                }
                return false;
            }
            if (z) {
                debug_message("# Consuming Symbol #" + cur_err_token().sym);
            }
            restart_lookahead();
        }
    }

    public abstract int error_sym();

    public int error_sync_size() {
        return 3;
    }

    public boolean find_recovery_config(boolean z) {
        if (z) {
            debug_message("# Finding recovery state on stack");
        }
        int i = this.stack.peek().right;
        int i2 = this.stack.peek().left;
        while (!shift_under_error()) {
            if (z) {
                debug_message("# Pop stack by one, state was # " + this.stack.peek().parse_state);
            }
            i2 = this.stack.pop().left;
            this.tos--;
            if (this.stack.empty()) {
                if (!z) {
                    return false;
                }
                debug_message("# No recovery state found on stack");
                return false;
            }
        }
        short s = get_action(this.stack.peek().parse_state, error_sym());
        if (z) {
            debug_message("# Recover state found (#" + this.stack.peek().parse_state + ")");
            StringBuilder sb = new StringBuilder("# Shifting on error to state #");
            sb.append(s + (-1));
            debug_message(sb.toString());
        }
        Symbol symbol = new Symbol(error_sym(), i2, i);
        symbol.parse_state = s - 1;
        symbol.used_by_parser = true;
        this.stack.push(symbol);
        this.tos++;
        return true;
    }

    public int getCount(int i) {
        if (i == 1) {
            return this.grpCount;
        }
        if (i == 2) {
            return this.opCount;
        }
        if (i != 3) {
            return 0;
        }
        return this.totalOpCount;
    }

    public Scanner getScanner() {
        return this._scanner;
    }

    public final short get_action(int i, int i2) {
        short[] sArr = this.action_tab[i];
        int i3 = 0;
        if (sArr.length < 20) {
            for (int i4 = 0; i4 < sArr.length; i4 += 2) {
                int i5 = i4 + 1;
                short s = sArr[i4];
                if (s == i2 || s == -1) {
                    return sArr[i5];
                }
            }
            return (short) 0;
        }
        int length = ((sArr.length - 1) / 2) - 1;
        while (i3 <= length) {
            int i6 = (i3 + length) / 2;
            int i7 = i6 * 2;
            short s2 = sArr[i7];
            if (i2 == s2) {
                return sArr[i7 + 1];
            }
            if (i2 > s2) {
                i3 = i6 + 1;
            } else {
                length = i6 - 1;
            }
        }
        return sArr[sArr.length - 1];
    }

    public final short get_reduce(int i, int i2) {
        short[] sArr = this.reduce_tab[i];
        if (sArr == null) {
            return (short) -1;
        }
        for (int i3 = 0; i3 < sArr.length; i3 += 2) {
            int i4 = i3 + 1;
            short s = sArr[i3];
            if (s == i2 || s == -1) {
                return sArr[i4];
            }
        }
        return (short) -1;
    }

    public abstract void init_actions() throws Exception;

    public boolean isOverLimit() {
        return this.overLimit;
    }

    public Symbol parse() throws Exception {
        Stack<Symbol> stack;
        this.production_tab = production_table();
        this.action_tab = action_table();
        this.reduce_tab = reduce_table();
        init_actions();
        user_init();
        this.isLiteral = false;
        this.overLimit = false;
        this.grpCount = 0;
        this.opCount = 0;
        this.lastSym = -1;
        this.cur_token = scan();
        if (this.overLimit) {
            return null;
        }
        this.stack.removeAllElements();
        this.stack.push(new Symbol(0, start_state()));
        this.tos = 0;
        this._done_parsing = false;
        Symbol symbolPeek = null;
        while (!this._done_parsing) {
            if (this.cur_token.used_by_parser) {
                throw new Error("Symbol recycling detected (fix your scanner).");
            }
            short s = get_action(this.stack.peek().parse_state, this.cur_token.sym);
            if (s > 0) {
                Symbol symbol = this.cur_token;
                symbol.parse_state = s - 1;
                symbol.used_by_parser = true;
                this.stack.push(symbol);
                this.tos++;
                this.cur_token = scan();
            } else if (s < 0) {
                int i = (-s) - 1;
                Symbol symbolDo_action = do_action(i, this, this.stack, this.tos);
                short[] sArr = this.production_tab[i];
                short s2 = sArr[0];
                short s3 = sArr[1];
                int i2 = 0;
                while (true) {
                    stack = this.stack;
                    if (i2 >= s3) {
                        break;
                    }
                    stack.pop();
                    this.tos--;
                    i2++;
                }
                symbolDo_action.parse_state = get_reduce(stack.peek().parse_state, s2);
                symbolDo_action.used_by_parser = true;
                this.stack.push(symbolDo_action);
                this.tos++;
                symbolPeek = symbolDo_action;
            } else if (s == 0) {
                syntax_error(this.cur_token);
                if (error_recovery(false)) {
                    symbolPeek = this.stack.peek();
                } else {
                    unrecovered_syntax_error(this.cur_token);
                    done_parsing();
                }
            }
            if (this.overLimit) {
                return null;
            }
        }
        return symbolPeek;
    }

    public void parse_lookahead(boolean z) throws Exception {
        Stack<Symbol> stack;
        this.lookahead_pos = 0;
        if (z) {
            debug_message("# Reparsing saved input with actions");
            debug_message("# Current Symbol is #" + cur_err_token().sym);
            debug_message("# Current state is #" + this.stack.peek().parse_state);
        }
        Symbol symbol = null;
        while (!this._done_parsing) {
            short s = get_action(this.stack.peek().parse_state, cur_err_token().sym);
            if (s > 0) {
                cur_err_token().parse_state = s - 1;
                cur_err_token().used_by_parser = true;
                if (z) {
                    debug_shift(cur_err_token());
                }
                this.stack.push(cur_err_token());
                this.tos++;
                if (!advance_lookahead()) {
                    if (z) {
                        debug_message("# Completed reparse");
                        return;
                    }
                    return;
                } else if (z) {
                    debug_message("# Current Symbol is #" + cur_err_token().sym);
                }
            } else if (s < 0) {
                int i = (-s) - 1;
                Symbol symbolDo_action = do_action(i, this, this.stack, this.tos);
                short[] sArr = this.production_tab[i];
                short s2 = sArr[0];
                short s3 = sArr[1];
                if (z) {
                    debug_reduce(i, s2, s3);
                }
                int i2 = 0;
                while (true) {
                    stack = this.stack;
                    if (i2 >= s3) {
                        break;
                    }
                    stack.pop();
                    this.tos--;
                    i2++;
                }
                short s4 = get_reduce(stack.peek().parse_state, s2);
                symbolDo_action.parse_state = s4;
                symbolDo_action.used_by_parser = true;
                this.stack.push(symbolDo_action);
                this.tos++;
                if (z) {
                    debug_message("# Goto state #" + ((int) s4));
                }
                symbol = symbolDo_action;
            } else if (s == 0) {
                report_fatal_error("Syntax error", symbol);
                return;
            }
        }
    }

    public abstract short[][] production_table();

    public void read_lookahead() throws Exception {
        this.lookahead = new Symbol[error_sync_size()];
        for (int i = 0; i < error_sync_size(); i++) {
            this.lookahead[i] = this.cur_token;
            this.cur_token = scan();
        }
        this.lookahead_pos = 0;
    }

    public abstract short[][] reduce_table();

    public void report_error(String str, Object obj) {
        System.err.print(str);
        if (!(obj instanceof Symbol)) {
            System.err.println("");
            return;
        }
        Symbol symbol = (Symbol) obj;
        if (symbol.left == -1) {
            System.err.println("");
            return;
        }
        System.err.println(" at character " + symbol.left + " of input");
    }

    public void report_fatal_error(String str, Object obj) throws Exception {
        done_parsing();
        report_error(str, obj);
        throw new Exception("Can't recover from previous error(s)");
    }

    public void restart_lookahead() throws Exception {
        for (int i = 1; i < error_sync_size(); i++) {
            Symbol[] symbolArr = this.lookahead;
            symbolArr[i - 1] = symbolArr[i];
        }
        this.cur_token = scan();
        this.lookahead[error_sync_size() - 1] = this.cur_token;
        this.lookahead_pos = 0;
    }

    public Symbol scan() throws Exception {
        int i;
        int i2;
        Symbol symbolNext_token = getScanner().next_token();
        int i3 = symbolNext_token.sym;
        if (i3 == 7) {
            if (!this.isLiteral) {
                this.grpCount++;
            }
            this.opCount++;
            this.totalOpCount++;
            this.isLiteral = false;
        } else if (contains(sym.OPERATORS, i3)) {
            if (this.lastSym != 14) {
                this.opCount++;
                this.totalOpCount++;
            }
            this.isLiteral = false;
        }
        int i4 = symbolNext_token.sym;
        if (i4 == 26 || i4 == 27) {
            this.isLiteral = true;
        }
        this.lastSym = i4;
        int i5 = this.grpLimit;
        if ((i5 > 0 && this.grpCount > i5) || (((i = this.opLimit) > 0 && this.opCount > i) || ((i2 = this.totalOpLimit) > 0 && this.totalOpCount > i2))) {
            this.overLimit = true;
        }
        return symbolNext_token;
    }

    public void setScanner(Scanner scanner) {
        this._scanner = scanner;
    }

    public boolean shift_under_error() {
        return get_action(this.stack.peek().parse_state, error_sym()) > 0;
    }

    public abstract int start_production();

    public abstract int start_state();

    public void syntax_error(Symbol symbol) {
        report_error("Syntax error", symbol);
    }

    public boolean try_parse_ahead(boolean z) throws Exception {
        virtual_parse_stack virtual_parse_stackVar = new virtual_parse_stack(this.stack);
        while (true) {
            short s = get_action(virtual_parse_stackVar.top(), cur_err_token().sym);
            if (s == 0) {
                return false;
            }
            if (s > 0) {
                int i = s - 1;
                virtual_parse_stackVar.push(i);
                if (z) {
                    debug_message("# Parse-ahead shifts Symbol #" + cur_err_token().sym + " into state #" + i);
                }
                if (!advance_lookahead()) {
                    return true;
                }
            } else {
                int i2 = (-s) - 1;
                if (i2 == start_production()) {
                    if (z) {
                        debug_message("# Parse-ahead accepts");
                    }
                    return true;
                }
                short[] sArr = this.production_tab[i2];
                short s2 = sArr[0];
                short s3 = sArr[1];
                for (int i3 = 0; i3 < s3; i3++) {
                    virtual_parse_stackVar.pop();
                }
                if (z) {
                    debug_message("# Parse-ahead reduces: handle size = " + ((int) s3) + " lhs = #" + ((int) s2) + " from state #" + virtual_parse_stackVar.top());
                }
                virtual_parse_stackVar.push(get_reduce(virtual_parse_stackVar.top(), s2));
                if (z) {
                    debug_message("# Goto state #" + virtual_parse_stackVar.top());
                }
            }
        }
    }

    public void unrecovered_syntax_error(Symbol symbol) throws Exception {
        report_fatal_error("Couldn't repair and continue parse", symbol);
    }

    public void user_init() throws Exception {
    }

    public lr_parser(Scanner scanner) {
        this();
        setScanner(scanner);
    }
}
