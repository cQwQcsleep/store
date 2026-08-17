package com.sun.org.apache.xpath.internal.compiler;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Token {
    static final char AT = '@';
    static final String ATTR = "attribute";
    static final char COLON = ':';
    static final char COMMA = ',';
    static final String DDOT = "..";
    static final char DOLLAR = '$';
    static final char DOT = '.';
    static final String DOT_STR = ".";
    static final char DQ = '\"';
    static final char EM = '!';
    static final char EQ = '=';
    static final char GT = '>';
    static final char LBRACK = '[';
    static final char LPAREN = '(';
    static final char LT = '<';
    static final char MINUS = '-';
    static final char PLUS = '+';
    static final char RBRACK = ']';
    static final char RPAREN = ')';
    static final char SLASH = '/';
    static final char SQ = '\'';
    static final char STAR = '*';
    static final char US = '_';
    static final char VBAR = '|';
    static final String OR = "or";
    static final String AND = "and";
    static final String DIV = "div";
    static final String MOD = "mod";
    static final String QUO = "quo";
    static final String DCOLON = "::";
    static final String CHILD = "child";
    static final String[] OPERATORS = {OR, AND, DIV, MOD, QUO, "..", DCOLON, "attribute", CHILD};

    private Token() {
    }

    public static boolean contains(String str) {
        Stream stream = Arrays.stream(OPERATORS);
        Objects.requireNonNull(str);
        return stream.anyMatch(new zih(str));
    }
}
