package com.sun.tools.javac.parser;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.source.tree.CaseTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.ModuleTree;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.file.PathFileObject;
import com.sun.tools.javac.parser.JavacParser;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.DocCommentTable;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.IntHashTable;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import defpackage.s22;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.lang.model.SourceVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacParser implements Parser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final int DIAMOND = 16;
    protected static final int EXPR = 1;
    protected static final int NOLAMBDA = 32;
    protected static final int NOPARAMS = 4;
    private static final int RECOVERY_THRESHOLD = 50;
    protected static final int TYPE = 2;
    protected static final int TYPEARG = 8;
    private static final JCDiagnostic.Fragment[][] decisionTable;
    private static final int infixPrecedenceLevels = 10;
    protected TreeMaker F;
    protected Predicate<Tokens.TokenKind> LAX_IDENTIFIER;
    protected Lexer S;
    boolean allowRecords;
    boolean allowSealedTypes;
    boolean allowStringFolding;
    boolean allowThisIdent;
    boolean allowYieldStatement;
    private int count;
    protected Map<Tokens.Comment, List<Tokens.Comment>> danglingComments;
    private final DocCommentTable docComments;
    protected final AbstractEndPosTable endPosTable;
    private int errorPos;
    private JCTree.JCErroneous errorTree;
    boolean keepDocComments;
    boolean keepLineMap;
    protected int lastmode;
    private Log log;
    protected int mode;
    private Names names;
    ArrayList<JCTree.JCExpression[]> odStackSupply;
    ArrayList<Tokens.Token[]> opStackSupply;
    private final boolean parseModuleInfo;
    private boolean permitTypeAnnotationsPushBack;
    private Preview preview;
    JCTree.JCVariableDecl receiverParam;
    private Source source;
    protected Tokens.Token token;
    private List<JCTree.JCAnnotation> typeAnnotationsPushedBack;
    private JCDiagnostic.Error unexpectedTopLevelDefinitionStartError;

    /* JADX INFO: renamed from: com.sun.tools.javac.parser.JavacParser$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.REFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SELECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CLASSDEF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.VARDEF.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IDENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPEARRAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MODULEDEF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PACKAGEDEF.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.METHODDEF.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr2 = new int[Tokens.TokenKind.values().length];
            $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind = iArr2;
            try {
                iArr2[Tokens.TokenKind.SEMI.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PUBLIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.FINAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ABSTRACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.MONKEYS_AT.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.EOF.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CLASS.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.INTERFACE.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.IMPORT.ordinal()] = 10;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LBRACE.ordinal()] = 11;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.RBRACE.ordinal()] = 12;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PRIVATE.ordinal()] = 13;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PROTECTED.ordinal()] = 14;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.STATIC.ordinal()] = 15;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.TRANSIENT.ordinal()] = 16;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.NATIVE.ordinal()] = 17;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.VOLATILE.ordinal()] = 18;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SYNCHRONIZED.ordinal()] = 19;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.STRICTFP.ordinal()] = 20;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LT.ordinal()] = 21;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BYTE.ordinal()] = 22;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SHORT.ordinal()] = 23;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CHAR.ordinal()] = 24;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.INT.ordinal()] = 25;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LONG.ordinal()] = 26;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.FLOAT.ordinal()] = 27;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.DOUBLE.ordinal()] = 28;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BOOLEAN.ordinal()] = 29;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.VOID.ordinal()] = 30;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.UNDERSCORE.ordinal()] = 31;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.IDENTIFIER.ordinal()] = 32;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CASE.ordinal()] = 33;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.DEFAULT.ordinal()] = 34;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.IF.ordinal()] = 35;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.FOR.ordinal()] = 36;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.WHILE.ordinal()] = 37;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.DO.ordinal()] = 38;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.TRY.ordinal()] = 39;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SWITCH.ordinal()] = 40;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.RETURN.ordinal()] = 41;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.THROW.ordinal()] = 42;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BREAK.ordinal()] = 43;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CONTINUE.ordinal()] = 44;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ELSE.ordinal()] = 45;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.FINALLY.ordinal()] = 46;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CATCH.ordinal()] = 47;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.THIS.ordinal()] = 48;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SUPER.ordinal()] = 49;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.NEW.ordinal()] = 50;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ASSERT.ordinal()] = 51;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.INTLITERAL.ordinal()] = 52;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LONGLITERAL.ordinal()] = 53;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.FLOATLITERAL.ordinal()] = 54;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.DOUBLELITERAL.ordinal()] = 55;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CHARLITERAL.ordinal()] = 56;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.STRINGLITERAL.ordinal()] = 57;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.TRUE.ordinal()] = 58;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.FALSE.ordinal()] = 59;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.NULL.ordinal()] = 60;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.EQ.ordinal()] = 61;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PLUSEQ.ordinal()] = 62;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SUBEQ.ordinal()] = 63;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.STAREQ.ordinal()] = 64;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SLASHEQ.ordinal()] = 65;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PERCENTEQ.ordinal()] = 66;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.AMPEQ.ordinal()] = 67;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BAREQ.ordinal()] = 68;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CARETEQ.ordinal()] = 69;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LTLTEQ.ordinal()] = 70;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.GTGTEQ.ordinal()] = 71;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.GTGTGTEQ.ordinal()] = 72;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LBRACKET.ordinal()] = 73;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LPAREN.ordinal()] = 74;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.DOT.ordinal()] = 75;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ELLIPSIS.ordinal()] = 76;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.QUES.ordinal()] = 77;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PLUSPLUS.ordinal()] = 78;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SUBSUB.ordinal()] = 79;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BANG.ordinal()] = 80;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.TILDE.ordinal()] = 81;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PLUS.ordinal()] = 82;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SUB.ordinal()] = 83;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ARROW.ordinal()] = 84;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.RPAREN.ordinal()] = 85;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.EXTENDS.ordinal()] = 86;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.RBRACKET.ordinal()] = 87;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.COMMA.ordinal()] = 88;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.GTGTGT.ordinal()] = 89;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.GTGT.ordinal()] = 90;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.GT.ordinal()] = 91;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.STRINGFRAGMENT.ordinal()] = 92;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.AMP.ordinal()] = 93;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.GTEQ.ordinal()] = 94;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.ERROR.ordinal()] = 95;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BARBAR.ordinal()] = 96;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.AMPAMP.ordinal()] = 97;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BAR.ordinal()] = 98;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.CARET.ordinal()] = 99;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.EQEQ.ordinal()] = 100;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.BANGEQ.ordinal()] = 101;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LTEQ.ordinal()] = 102;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.LTLT.ordinal()] = 103;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.STAR.ordinal()] = 104;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.SLASH.ordinal()] = 105;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.PERCENT.ordinal()] = 106;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[Tokens.TokenKind.INSTANCEOF.ordinal()] = 107;
            } catch (NoSuchFieldError unused116) {
            }
        }
    }

    public static abstract class AbstractEndPosTable implements EndPosTable {
        public int errorEndPos = -1;

        @Override // com.sun.tools.javac.tree.EndPosTable
        public void setErrorEndPos(int i) {
            if (i > this.errorEndPos) {
                this.errorEndPos = i;
            }
        }
    }

    public enum BasicErrorRecoveryAction implements ErrorRecoveryAction {
        BLOCK_STMT { // from class: com.sun.tools.javac.parser.JavacParser.BasicErrorRecoveryAction.1
            @Override // com.sun.tools.javac.parser.JavacParser.ErrorRecoveryAction
            public JCTree doRecover(JavacParser javacParser) {
                return javacParser.parseStatementAsBlock();
            }
        },
        CATCH_CLAUSE { // from class: com.sun.tools.javac.parser.JavacParser.BasicErrorRecoveryAction.2
            @Override // com.sun.tools.javac.parser.JavacParser.ErrorRecoveryAction
            public JCTree doRecover(JavacParser javacParser) {
                return javacParser.catchClause();
            }
        }
    }

    public enum EnumeratorEstimate {
        ENUMERATOR,
        MEMBER,
        UNKNOWN
    }

    public interface ErrorRecoveryAction {
        JCTree doRecover(JavacParser javacParser);
    }

    public class LambdaClassifier {
        JCDiagnostic.Fragment diagFragment;
        LambdaParameterKind kind;

        public LambdaClassifier() {
        }

        private void reduce(LambdaParameterKind lambdaParameterKind) {
            LambdaParameterKind lambdaParameterKind2;
            LambdaParameterKind lambdaParameterKind3 = this.kind;
            if (lambdaParameterKind3 == null) {
                this.kind = lambdaParameterKind;
                return;
            }
            if (lambdaParameterKind3 == lambdaParameterKind || lambdaParameterKind3 == (lambdaParameterKind2 = LambdaParameterKind.ERROR)) {
                return;
            }
            this.kind = lambdaParameterKind2;
            int i = lambdaParameterKind3.index;
            LambdaParameterKind lambdaParameterKind4 = LambdaParameterKind.VAR;
            this.diagFragment = (Source.Feature.VAR_SYNTAX_IMPLICIT_LAMBDAS.allowedInSource(JavacParser.this.source) || !(i == lambdaParameterKind4.index || lambdaParameterKind.index == lambdaParameterKind4.index)) ? JavacParser.decisionTable[lambdaParameterKind3.index][lambdaParameterKind.index] : null;
        }

        public void addParameter(JCTree.JCVariableDecl jCVariableDecl) {
            Assert.check(jCVariableDecl.vartype != null);
            if (jCVariableDecl.name == JavacParser.this.names.error) {
                reduce(LambdaParameterKind.IMPLICIT);
            } else if (JavacParser.this.restrictedTypeName(jCVariableDecl.vartype, false) != null) {
                reduce(LambdaParameterKind.VAR);
            } else {
                reduce(LambdaParameterKind.EXPLICIT);
            }
        }

        public LambdaParameterKind result() {
            return this.kind;
        }
    }

    public enum LambdaParameterKind {
        VAR(0),
        EXPLICIT(1),
        IMPLICIT(2),
        ERROR(-1);

        private final int index;

        LambdaParameterKind(int i) {
            this.index = i;
        }
    }

    public static class MinimalEndPosTable extends SimpleEndPosTable {
        @Override // com.sun.tools.javac.parser.JavacParser.SimpleEndPosTable, com.sun.tools.javac.tree.EndPosTable
        public <T extends JCTree> T storeEnd(T t, int i) {
            int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[t.getTag().ordinal()];
            return (i2 == 3 || i2 == 4 || i2 == 7 || i2 == 8 || i2 == 9) ? (T) super.storeEnd(t, i) : t;
        }
    }

    public enum ParensResult {
        CAST,
        EXPLICIT_LAMBDA,
        IMPLICIT_LAMBDA,
        PARENS
    }

    public enum PatternResult {
        EXPRESSION,
        PATTERN
    }

    public static class SimpleEndPosTable extends AbstractEndPosTable {
        private final IntHashTable endPosMap = new IntHashTable();

        @Override // com.sun.tools.javac.tree.EndPosTable
        public int getEndPos(JCTree jCTree) {
            int i = this.endPosMap.get(jCTree);
            if (i == -1) {
                return -1;
            }
            return i;
        }

        @Override // com.sun.tools.javac.tree.EndPosTable
        public int replaceTree(JCTree jCTree, JCTree jCTree2) {
            int iRemove = this.endPosMap.remove(jCTree);
            if (iRemove != -1 && jCTree2 != null) {
                storeEnd(jCTree2, iRemove);
            }
            return iRemove;
        }

        @Override // com.sun.tools.javac.tree.EndPosTable
        public <T extends JCTree> T storeEnd(T t, int i) {
            this.endPosMap.put(t, Math.max(i, this.errorEndPos));
            return t;
        }
    }

    static {
        JCDiagnostic.Fragment fragment = CompilerProperties.Fragments.VarAndExplicitNotAllowed;
        JCDiagnostic.Fragment fragment2 = CompilerProperties.Fragments.VarAndImplicitNotAllowed;
        JCDiagnostic.Fragment fragment3 = CompilerProperties.Fragments.ImplicitAndExplicitNotAllowed;
        decisionTable = new JCDiagnostic.Fragment[][]{new JCDiagnostic.Fragment[]{null, fragment, fragment2}, new JCDiagnostic.Fragment[]{fragment, null, fragment3}, new JCDiagnostic.Fragment[]{fragment2, fragment3, null}};
    }

    public JavacParser(ParserFactory parserFactory, Lexer lexer, boolean z, boolean z2, boolean z3, boolean z4) {
        this.danglingComments = new HashMap();
        this.typeAnnotationsPushedBack = List.nil();
        this.permitTypeAnnotationsPushBack = false;
        this.mode = 0;
        this.lastmode = 0;
        this.errorPos = -1;
        this.count = 0;
        this.odStackSupply = new ArrayList<>();
        this.opStackSupply = new ArrayList<>();
        this.LAX_IDENTIFIER = new Predicate() { // from class: dn7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return JavacParser.a((Tokens.TokenKind) obj);
            }
        };
        this.S = lexer;
        nextToken();
        this.F = parserFactory.F;
        this.log = parserFactory.log;
        this.names = parserFactory.names;
        this.source = parserFactory.source;
        this.preview = parserFactory.preview;
        this.allowStringFolding = parserFactory.options.getBoolean("allowStringFolding", true);
        this.keepDocComments = z;
        this.parseModuleInfo = z4;
        this.docComments = newDocCommentTable(z, parserFactory);
        this.keepLineMap = z2;
        this.errorTree = this.F.Erroneous();
        this.endPosTable = newEndPosTable(z3);
        this.allowYieldStatement = Source.Feature.SWITCH_EXPRESSION.allowedInSource(this.source);
        this.allowRecords = Source.Feature.RECORDS.allowedInSource(this.source);
        this.allowSealedTypes = Source.Feature.SEALED_CLASSES.allowedInSource(this.source);
        updateUnexpectedTopLevelDefinitionStartError(false);
    }

    public static /* synthetic */ boolean a(Tokens.TokenKind tokenKind) {
        return tokenKind == Tokens.TokenKind.IDENTIFIER || tokenKind == Tokens.TokenKind.UNDERSCORE || tokenKind == Tokens.TokenKind.ASSERT || tokenKind == Tokens.TokenKind.ENUM;
    }

    private boolean allowedAfterSealedOrNonSealed(Tokens.Token token, boolean z, boolean z2) {
        if (z) {
            int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[token.kind.ordinal()];
            if (i != 3 && i != 4) {
                if (i != 5) {
                    if (i != 7 && i != 8 && i != 9 && i != 20) {
                        return false;
                    }
                } else if (this.S.token(2).kind == Tokens.TokenKind.INTERFACE && !z2) {
                    return false;
                }
            }
            return true;
        }
        int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[token.kind.ordinal()];
        if (i2 != 2 && i2 != 3 && i2 != 4) {
            if (i2 != 5) {
                if (i2 != 7 && i2 != 8 && i2 != 9 && i2 != 20) {
                    if (i2 == 32) {
                        return isNonSealedIdentifier(token, z2 ? 3 : 1) || token.name() == this.names.sealed;
                    }
                    switch (i2) {
                        case 13:
                        case 14:
                        case 15:
                            break;
                        default:
                            return false;
                    }
                }
            } else if (this.S.token(2).kind == Tokens.TokenKind.INTERFACE && !z2) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ String b(JCTree.JCLiteral jCLiteral) {
        return (String) jCLiteral.getValue();
    }

    private JCTree.JCExpression bracketsOpt(JCTree.JCExpression jCExpression, List<JCTree.JCAnnotation> list) {
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
        Tokens.Token token = this.token;
        if (token.kind == Tokens.TokenKind.LBRACKET) {
            int i = token.pos;
            nextToken();
            jCExpression = bracketsOptCont(jCExpression, i, listTypeAnnotationsOpt);
        } else if (!listTypeAnnotationsOpt.isEmpty()) {
            if (!this.permitTypeAnnotationsPushBack) {
                return illegal(listTypeAnnotationsOpt.head.pos);
            }
            this.typeAnnotationsPushedBack = listTypeAnnotationsOpt;
        }
        return !list.isEmpty() ? (JCTree.JCExpression) toP(this.F.at(this.token.pos).AnnotatedType(list, jCExpression)) : jCExpression;
    }

    private JCTree.JCExpression bracketsOptCont(JCTree.JCExpression jCExpression, int i, List<JCTree.JCAnnotation> list) {
        accept(Tokens.TokenKind.RBRACKET);
        JCTree.JCExpression jCExpression2 = (JCTree.JCExpression) toP(this.F.at(i).TypeArray(bracketsOpt(jCExpression)));
        return list.nonEmpty() ? (JCTree.JCExpression) toP(this.F.at(i).AnnotatedType(list, jCExpression2)) : jCExpression2;
    }

    private List<JCTree> constructImplicitClass(List<JCTree> list, int i) {
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        for (JCTree jCTree : list) {
            if (jCTree.hasTag(JCTree.Tag.PACKAGEDEF)) {
                this.log.error(jCTree.pos(), CompilerProperties.Errors.ImplicitClassShouldNotHavePackageDeclaration);
            } else if (jCTree.hasTag(JCTree.Tag.IMPORT) || jCTree.hasTag(JCTree.Tag.MODULEIMPORT)) {
                listBuffer.append(jCTree);
            } else if (!jCTree.hasTag(JCTree.Tag.SKIP)) {
                listBuffer2.append(jCTree);
            }
        }
        int startPos = getStartPos((JCTree) listBuffer2.first());
        String simpleName = PathFileObject.getSimpleName(this.log.currentSourceFile());
        if (simpleName.endsWith(".java")) {
            simpleName = simpleName.substring(0, simpleName.length() - 5);
        }
        if (!SourceVersion.isIdentifier(simpleName) || SourceVersion.isKeyword(simpleName)) {
            this.log.error(startPos, CompilerProperties.Errors.BadFileName(simpleName));
        }
        JCTree.JCClassDecl jCClassDeclClassDef = this.F.at(startPos).ClassDef(this.F.at(-1).Modifiers(524304L, List.nil()), this.names.fromString(simpleName), List.nil(), null, List.nil(), List.nil(), listBuffer2.toList());
        storeEnd(jCClassDeclClassDef, i);
        listBuffer.append(jCClassDeclClassDef);
        return listBuffer.toList();
    }

    private List<JCTree> constructorOrMethodOrFieldDeclaration(JCTree.JCModifiers jCModifiers, Name name, boolean z, boolean z2, Tokens.Comment comment) {
        JCTree.JCExpression jCExpressionUnannotatedType;
        List<? extends JCTree> listOf;
        int i = this.token.pos;
        List<JCTree.JCTypeParameter> listTypeParametersOpt = typeParametersOpt();
        if (listTypeParametersOpt.nonEmpty() && jCModifiers.pos == -1) {
            jCModifiers.pos = i;
            storeEnd(jCModifiers, i);
        }
        List<JCTree.JCAnnotation> listAnnotationsOpt = annotationsOpt(JCTree.Tag.ANNOTATION);
        if (listAnnotationsOpt.nonEmpty()) {
            List<JCTree.JCAnnotation> listAppendList = jCModifiers.annotations.appendList(listAnnotationsOpt);
            jCModifiers.annotations = listAppendList;
            if (jCModifiers.pos == -1) {
                jCModifiers.pos = listAppendList.head.pos;
            }
        }
        Tokens.Token token = this.token;
        int i2 = token.pos;
        boolean z3 = token.kind == Tokens.TokenKind.VOID;
        if (z3) {
            jCExpressionUnannotatedType = (JCTree.JCExpression) to(this.F.at(i2).TypeIdent(TypeTag.VOID));
            nextToken();
        } else {
            jCExpressionUnannotatedType = unannotatedType(false);
        }
        Tokens.TokenKind tokenKind = this.token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.LPAREN;
        if (((tokenKind == tokenKind2 && !z) || (z2 && tokenKind == Tokens.TokenKind.LBRACE)) && jCExpressionUnannotatedType.hasTag(JCTree.Tag.IDENT)) {
            if (z || token.name() != name) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i2, CompilerProperties.Errors.InvalidMethDeclRetTypeReq);
            } else if (listAnnotationsOpt.nonEmpty()) {
                illegal(listAnnotationsOpt.head.pos);
            }
            if (z2 && this.token.kind == Tokens.TokenKind.LBRACE) {
                jCModifiers.flags |= 2251799813685248L;
            }
            return List.of(methodDeclaratorRest(i2, jCModifiers, null, this.names.init, listTypeParametersOpt, z, true, z2, comment));
        }
        if (z2 && jCExpressionUnannotatedType.hasTag(JCTree.Tag.IDENT)) {
            Tokens.Token token2 = this.token;
            if (token2.kind == Tokens.TokenKind.THROWS) {
                Log log = this.log;
                JCDiagnostic.DiagnosticFlag diagnosticFlag = JCDiagnostic.DiagnosticFlag.SYNTAX;
                int i3 = token2.pos;
                JCDiagnostic.Fragment fragment = CompilerProperties.Fragments.Compact;
                log.error(diagnosticFlag, i3, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(fragment, name, CompilerProperties.Fragments.ThrowsClauseNotAllowedForCanonicalConstructor(fragment)));
                skip(false, true, false, false);
                return List.of(methodDeclaratorRest(i2, jCModifiers, null, this.names.init, listTypeParametersOpt, z, true, z2, comment));
            }
        }
        int i4 = this.token.pos;
        Name nameIdent = ident();
        if (this.token.kind == tokenKind2) {
            return List.of(methodDeclaratorRest(i4, jCModifiers, jCExpressionUnannotatedType, nameIdent, listTypeParametersOpt, z, z3, false, comment));
        }
        JCTree.JCExpression jCExpression = jCExpressionUnannotatedType;
        if (z3 || !listTypeParametersOpt.isEmpty()) {
            int i5 = this.token.pos;
            if (z3 || listTypeParametersOpt.nonEmpty()) {
                JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) toP(this.F.at(i5).MethodDef(jCModifiers, nameIdent, jCExpression, listTypeParametersOpt, List.nil(), List.nil(), null, null));
                attach(jCMethodDecl, comment);
                listOf = List.of(jCMethodDecl);
            } else {
                listOf = List.nil();
            }
            return List.of(syntaxError(this.token.pos, listOf, CompilerProperties.Errors.Expected(tokenKind2)));
        }
        if (z2 && (!z2 || (jCModifiers.flags & 8) == 0)) {
            variableDeclaratorsRest(i4, jCModifiers, jCExpression, nameIdent, z, comment, new ListBuffer(), false).toList();
            accept(Tokens.TokenKind.SEMI);
            return List.of(syntaxError(i4, null, CompilerProperties.Errors.RecordCannotDeclareInstanceFields));
        }
        List<JCTree> list = variableDeclaratorsRest(i4, jCModifiers, jCExpression, nameIdent, z, comment, new ListBuffer(), false).toList();
        accept(Tokens.TokenKind.SEMI);
        storeEnd(list.last(), this.S.prevToken().endPos);
        return list;
    }

    private JCTree.JCStatement doRecover(int i, ErrorRecoveryAction errorRecoveryAction, JCDiagnostic.Error error) {
        int iErrPos = this.S.errPos();
        JCTree jCTreeDoRecover = errorRecoveryAction.doRecover(this);
        this.S.errPos(iErrPos);
        return (JCTree.JCStatement) toP(this.F.Exec(syntaxError(i, List.of(jCTreeDoRecover), error)));
    }

    public static /* synthetic */ boolean e(Tokens.TokenKind tokenKind) {
        return tokenKind == Tokens.TokenKind.RPAREN || tokenKind == Tokens.TokenKind.COMMA;
    }

    public static int earlier(int i, int i2) {
        return (i != -1 && (i2 == -1 || i < i2)) ? i : i2;
    }

    private EnumeratorEstimate estimateEnumeratorOrMember(Name name) {
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        if ((tokenKind == Tokens.TokenKind.IDENTIFIER || tokenKind == Tokens.TokenKind.UNDERSCORE) && token.name() != name && (!this.allowRecords || !isRecordStart())) {
            int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.S.token(1).kind.ordinal()];
            if (i == 1 || i == 11 || i == 74 || i == 88) {
                return EnumeratorEstimate.ENUMERATOR;
            }
        }
        int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()];
        if (i2 != 5 && i2 != 21 && i2 != 31) {
            if (i2 != 32) {
                return EnumeratorEstimate.MEMBER;
            }
            if (this.allowRecords && isRecordStart()) {
                return EnumeratorEstimate.MEMBER;
            }
        }
        return EnumeratorEstimate.UNKNOWN;
    }

    private boolean foldIfNeeded(JCTree.JCExpression jCExpression, ListBuffer<JCTree.JCLiteral> listBuffer, ListBuffer<JCTree.JCExpression> listBuffer2, boolean z) {
        JCTree.JCLiteral jCLiteralStringLiteral = stringLiteral(jCExpression);
        if (jCLiteralStringLiteral != null) {
            listBuffer.prepend(jCLiteralStringLiteral);
            return z && merge(listBuffer, listBuffer2);
        }
        boolean zMerge = merge(listBuffer, listBuffer2);
        listBuffer.clear();
        listBuffer2.prepend(jCExpression);
        return zMerge;
    }

    public static /* synthetic */ boolean i(JCTree.JCStatement jCStatement, JCTree.JCStatement jCStatement2) {
        return jCStatement2.hasTag(JCTree.Tag.VARDEF) || jCStatement2.hasTag(JCTree.Tag.CLASSDEF) || jCStatement2.hasTag(JCTree.Tag.BLOCK) || jCStatement2 == jCStatement;
    }

    private void ignoreDanglingComments() {
        this.S.getDocComments().clear();
    }

    private JCTree.JCExpression insertAnnotationsToMostInner(JCTree.JCExpression jCExpression, List<JCTree.JCAnnotation> list, boolean z) {
        JCTree.Tag tag;
        JCTree.Tag tag2;
        int endPos = getEndPos(jCExpression);
        JCTree.JCArrayTypeTree jCArrayTypeTree = null;
        JCTree.JCExpression jCExpression2 = jCExpression;
        while (TreeInfo.typeIn(jCExpression2).hasTag(JCTree.Tag.TYPEARRAY)) {
            jCArrayTypeTree = (JCTree.JCArrayTypeTree) TreeInfo.typeIn(jCExpression2);
            jCExpression2 = jCArrayTypeTree.elemtype;
        }
        if (z) {
            jCExpression2 = (JCTree.JCExpression) to(this.F.at(this.token.pos).TypeArray(jCExpression2));
            endPos = getEndPos(jCExpression2);
        }
        if (list.nonEmpty()) {
            JCTree.JCExpression expression = jCExpression2;
            JCTree.JCExpression jCExpression3 = expression;
            while (true) {
                JCTree.JCExpression jCExpressionTypeIn = TreeInfo.typeIn(expression);
                tag = JCTree.Tag.SELECT;
                if (!jCExpressionTypeIn.hasTag(tag)) {
                    JCTree.JCExpression jCExpressionTypeIn2 = TreeInfo.typeIn(expression);
                    tag2 = JCTree.Tag.TYPEAPPLY;
                    if (!jCExpressionTypeIn2.hasTag(tag2)) {
                        break;
                    }
                }
                while (TreeInfo.typeIn(expression).hasTag(JCTree.Tag.SELECT)) {
                    jCExpression3 = expression;
                    expression = ((JCTree.JCFieldAccess) TreeInfo.typeIn(expression)).getExpression();
                }
                while (TreeInfo.typeIn(expression).hasTag(JCTree.Tag.TYPEAPPLY)) {
                    jCExpression3 = expression;
                    expression = ((JCTree.JCTypeApply) TreeInfo.typeIn(expression)).clazz;
                }
            }
            JCTree.JCAnnotatedType jCAnnotatedTypeAnnotatedType = this.F.at(list.head.pos).AnnotatedType(list, expression);
            if (TreeInfo.typeIn(jCExpression3).hasTag(tag2)) {
                ((JCTree.JCTypeApply) TreeInfo.typeIn(jCExpression3)).clazz = jCAnnotatedTypeAnnotatedType;
            } else if (TreeInfo.typeIn(jCExpression3).hasTag(tag)) {
                ((JCTree.JCFieldAccess) TreeInfo.typeIn(jCExpression3)).selected = jCAnnotatedTypeAnnotatedType;
            } else {
                jCExpression2 = jCAnnotatedTypeAnnotatedType;
            }
        }
        if (jCArrayTypeTree == null) {
            return jCExpression2;
        }
        jCArrayTypeTree.elemtype = jCExpression2;
        return (JCTree.JCExpression) storeEnd(jCExpression, endPos);
    }

    private boolean isDeclaration(boolean z) {
        Tokens.TokenKind tokenKind = this.token.kind;
        if (tokenKind == Tokens.TokenKind.CLASS || tokenKind == Tokens.TokenKind.INTERFACE || tokenKind == Tokens.TokenKind.ENUM) {
            return true;
        }
        return isRecordStart() && z;
    }

    private boolean isDefiniteStatementStartToken() {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()];
        if (i == 51) {
            return true;
        }
        switch (i) {
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                return true;
            default:
                return false;
        }
    }

    public static /* synthetic */ boolean j(Tokens.TokenKind tokenKind) {
        return tokenKind == Tokens.TokenKind.LPAREN;
    }

    public static /* synthetic */ JCTree.JCAnnotation l(JavacParser javacParser, JCTree.JCAnnotation jCAnnotation) {
        JCTree.JCAnnotation jCAnnotationTypeAnnotation = javacParser.F.at(jCAnnotation.pos).TypeAnnotation(jCAnnotation.annotationType, jCAnnotation.args);
        javacParser.endPosTable.replaceTree(jCAnnotation, jCAnnotationTypeAnnotation);
        return jCAnnotationTypeAnnotation;
    }

    private List<JCTree.JCStatement> localVariableDeclarations(JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, Tokens.Comment comment) {
        if (comment != null) {
            saveDanglingDocComments(comment);
        }
        ListBuffer listBufferVariableDeclarators = variableDeclarators(jCModifiers, jCExpression, new ListBuffer(), true);
        accept(Tokens.TokenKind.SEMI);
        storeEnd((JCTree.JCStatement) listBufferVariableDeclarators.last(), this.S.prevToken().endPos);
        return listBufferVariableDeclarators.toList();
    }

    public static /* synthetic */ boolean n(Tokens.TokenKind tokenKind) {
        return tokenKind == Tokens.TokenKind.ARROW || tokenKind == Tokens.TokenKind.COMMA;
    }

    private JCTree.JCExpression[] newOdStack() {
        if (this.odStackSupply.isEmpty()) {
            return new JCTree.JCExpression[11];
        }
        ArrayList<JCTree.JCExpression[]> arrayList = this.odStackSupply;
        return arrayList.remove(arrayList.size() - 1);
    }

    private Tokens.Token[] newOpStack() {
        if (this.opStackSupply.isEmpty()) {
            return new Tokens.Token[11];
        }
        ArrayList<Tokens.Token[]> arrayList = this.opStackSupply;
        return arrayList.remove(arrayList.size() - 1);
    }

    private boolean openingBraceMissing(boolean z) {
        skip(false, true, !z, !z);
        Tokens.TokenKind tokenKind = this.token.kind;
        if (tokenKind == Tokens.TokenKind.LBRACE) {
            return true;
        }
        if (z) {
            return false;
        }
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[tokenKind.ordinal()];
        if (i != 12) {
            switch (i) {
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    return true;
                default:
                    JCTree.JCBlock jCBlockBlock = new VirtualParser(this).block();
                    if (jCBlockBlock.stats.isEmpty()) {
                        return false;
                    }
                    final JCTree.JCStatement jCStatementLast = jCBlockBlock.stats.last();
                    return (jCBlockBlock.stats.stream().allMatch(new Predicate() { // from class: xm7
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return JavacParser.i(jCStatementLast, (JCTree.JCStatement) obj);
                        }
                    }) && (jCStatementLast instanceof JCTree.JCExpressionStatement) && ((JCTree.JCExpressionStatement) jCStatementLast).expr.hasTag(JCTree.Tag.ERRONEOUS)) ? false : true;
            }
        }
        VirtualParser.VirtualScanner virtualScanner = new VirtualParser.VirtualScanner(this.S);
        virtualScanner.nextToken();
        int i2 = 1;
        while (virtualScanner.token().kind != Tokens.TokenKind.EOF) {
            int i3 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[virtualScanner.token().kind.ordinal()];
            if (i3 == 11) {
                i2++;
            } else if (i3 == 12) {
                i2--;
            }
            virtualScanner.nextToken();
        }
        return i2 == 0;
    }

    public static JCTree.Tag optag(Tokens.TokenKind tokenKind) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[tokenKind.ordinal()];
        if (i == 21) {
            return JCTree.Tag.LT;
        }
        if (i == 82) {
            return JCTree.Tag.PLUS;
        }
        if (i == 83) {
            return JCTree.Tag.MINUS;
        }
        if (i == 93) {
            return JCTree.Tag.BITAND;
        }
        if (i == 94) {
            return JCTree.Tag.GE;
        }
        switch (i) {
            case 62:
                return JCTree.Tag.PLUS_ASG;
            case 63:
                return JCTree.Tag.MINUS_ASG;
            case 64:
                return JCTree.Tag.MUL_ASG;
            case 65:
                return JCTree.Tag.DIV_ASG;
            case 66:
                return JCTree.Tag.MOD_ASG;
            case 67:
                return JCTree.Tag.BITAND_ASG;
            case 68:
                return JCTree.Tag.BITOR_ASG;
            case 69:
                return JCTree.Tag.BITXOR_ASG;
            case 70:
                return JCTree.Tag.SL_ASG;
            case 71:
                return JCTree.Tag.SR_ASG;
            case 72:
                return JCTree.Tag.USR_ASG;
            default:
                switch (i) {
                    case 89:
                        return JCTree.Tag.USR;
                    case 90:
                        return JCTree.Tag.SR;
                    case 91:
                        return JCTree.Tag.GT;
                    default:
                        switch (i) {
                            case 96:
                                return JCTree.Tag.OR;
                            case 97:
                                return JCTree.Tag.AND;
                            case 98:
                                return JCTree.Tag.BITOR;
                            case 99:
                                return JCTree.Tag.BITXOR;
                            case 100:
                                return JCTree.Tag.EQ;
                            case 101:
                                return JCTree.Tag.NE;
                            case 102:
                                return JCTree.Tag.LE;
                            case 103:
                                return JCTree.Tag.SL;
                            case 104:
                                return JCTree.Tag.MUL;
                            case 105:
                                return JCTree.Tag.DIV;
                            case 106:
                                return JCTree.Tag.MOD;
                            case 107:
                                return JCTree.Tag.TYPETEST;
                            default:
                                return JCTree.Tag.NO_TAG;
                        }
                }
        }
    }

    private JCTree.JCCaseLabel parseCaseLabel(boolean z) {
        Tokens.Token token = this.token;
        int i = token.pos;
        if (token.kind == Tokens.TokenKind.DEFAULT) {
            checkSourceLevel(i, Source.Feature.PATTERN_SWITCH);
            if (!z) {
                reportSyntaxError(new JCDiagnostic.SimpleDiagnosticPosition(this.token.pos), CompilerProperties.Errors.DefaultLabelNotAllowed);
            }
            nextToken();
            return (JCTree.JCCaseLabel) toP(this.F.at(i).DefaultCaseLabel());
        }
        JCTree.JCModifiers jCModifiersOptFinal = optFinal(0L);
        if (jCModifiersOptFinal.flags == 0 && !jCModifiersOptFinal.annotations.nonEmpty() && analyzePattern(0) != PatternResult.PATTERN) {
            return (JCTree.JCCaseLabel) toP(this.F.at(i).ConstantCaseLabel(term(33)));
        }
        checkSourceLevel(this.token.pos, Source.Feature.PATTERN_SWITCH);
        return (JCTree.JCCaseLabel) toP(this.F.at(i).PatternCaseLabel(parsePattern(i, jCModifiersOptFinal, null, false, true)));
    }

    private JCTree.JCExpression parseGuard(JCTree.JCCaseLabel jCCaseLabel) {
        Tokens.Token token = this.token;
        if (token.kind != Tokens.TokenKind.IDENTIFIER || token.name() != this.names.when) {
            return null;
        }
        int i = this.token.pos;
        nextToken();
        JCTree.JCExpression jCExpressionTerm = term(33);
        return !(jCCaseLabel instanceof JCTree.JCPatternCaseLabel) ? syntaxError(i, List.of(jCExpressionTerm), CompilerProperties.Errors.GuardNotAllowed) : jCExpressionTerm;
    }

    public static int prec(Tokens.TokenKind tokenKind) {
        JCTree.Tag tagOptag = optag(tokenKind);
        if (tagOptag != JCTree.Tag.NO_TAG) {
            return TreeInfo.opPrec(tagOptag);
        }
        return -1;
    }

    private void reportSyntaxError(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Error error, boolean z) {
        int preferredPosition = diagnosticPosition.getPreferredPosition();
        if (preferredPosition > this.S.errPos() || preferredPosition == -1) {
            if (this.token.kind != Tokens.TokenKind.EOF || z) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, diagnosticPosition, error);
            } else {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, diagnosticPosition, CompilerProperties.Errors.PrematureEof);
            }
        }
        this.S.errPos(preferredPosition);
        Tokens.Token token = this.token;
        int i = token.pos;
        if (i != this.errorPos || token.kind == Tokens.TokenKind.EOF) {
            this.count = 0;
            this.errorPos = i;
        } else {
            int i2 = this.count;
            this.count = i2 + 1;
            Assert.check(i2 < 50);
        }
    }

    private void saveDanglingDocComments(Tokens.Comment comment) {
        Queue<Tokens.Comment> docComments = this.S.getDocComments();
        int size = docComments.size();
        if (size != 0) {
            if (size == 1 && docComments.peek() == comment) {
                docComments.remove();
                return;
            }
            ListBuffer listBuffer = new ListBuffer();
            while (!docComments.isEmpty()) {
                Tokens.Comment commentRemove = docComments.remove();
                if (commentRemove != comment) {
                    listBuffer.add(commentRemove);
                }
            }
            this.danglingComments.put(comment, listBuffer.toList());
        }
    }

    private boolean shebang(Tokens.Comment comment, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        DiagnosticSource diagnosticSourceCurrentSource = this.log.currentSource();
        return comment.getStyle() == Tokens.Comment.CommentStyle.JAVADOC_LINE && comment.getPos().getStartPosition() == 0 && diagnosticSourceCurrentSource.getLineNumber(diagnosticPosition.getEndPosition(diagnosticSourceCurrentSource.getEndPosTable())) == 1;
    }

    private int skipAnnotation(int i) {
        int i2 = i + 1;
        while (peekToken(i2, Tokens.TokenKind.DOT)) {
            i2 += 2;
        }
        if (peekToken(i2, Tokens.TokenKind.LPAREN)) {
            i2++;
            int i3 = 0;
            while (true) {
                int i4 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.S.token(i2).kind.ordinal()];
                if (i4 == 6) {
                    break;
                }
                if (i4 == 74) {
                    i3++;
                } else if (i4 == 85 && (i3 = i3 - 1) == 0) {
                    break;
                }
                i2++;
            }
        }
        return i2;
    }

    private JCTree.JCLiteral stringLiteral(JCTree jCTree) {
        if (!jCTree.hasTag(JCTree.Tag.LITERAL)) {
            return null;
        }
        JCTree.JCLiteral jCLiteral = (JCTree.JCLiteral) jCTree;
        if (jCLiteral.typetag == TypeTag.CLASS) {
            return jCLiteral;
        }
        return null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private List<JCTree.JCCase> switchExpressionStatementGroup() {
        List<JCTree.JCStatement> listOf;
        CaseTree.CaseKind caseKind;
        JCTree jCTree;
        ListBuffer listBuffer = new ListBuffer();
        int i = this.token.pos;
        ListBuffer listBuffer2 = new ListBuffer();
        if (this.token.kind == Tokens.TokenKind.DEFAULT) {
            nextToken();
            listBuffer2.append((JCTree.JCCaseLabel) toP(this.F.at(i).DefaultCaseLabel()));
        } else {
            accept(Tokens.TokenKind.CASE);
            boolean zIsNullCaseLabel = false;
            while (true) {
                JCTree.JCCaseLabel caseLabel = parseCaseLabel(zIsNullCaseLabel);
                listBuffer2.append(caseLabel);
                if (this.token.kind != Tokens.TokenKind.COMMA) {
                    break;
                }
                checkSourceLevel(Source.Feature.SWITCH_MULTIPLE_CASE_LABELS);
                nextToken();
                zIsNullCaseLabel = TreeInfo.isNullCaseLabel(caseLabel);
            }
        }
        JCTree.JCExpression guard = parseGuard((JCTree.JCCaseLabel) listBuffer2.last());
        if (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()] != 84) {
            accept(Tokens.TokenKind.COLON, new Function() { // from class: an7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CompilerProperties.Errors.Expected2(Tokens.TokenKind.COLON, Tokens.TokenKind.ARROW);
                }
            });
            listOf = blockStatements();
            caseKind = JCTree.JCCase.STATEMENT;
            jCTree = null;
        } else {
            checkSourceLevel(Source.Feature.SWITCH_RULE);
            nextToken();
            Tokens.TokenKind tokenKind = this.token.kind;
            if (tokenKind == Tokens.TokenKind.THROW || tokenKind == Tokens.TokenKind.LBRACE) {
                listOf = List.of(parseStatement());
                JCTree.JCStatement jCStatement = listOf.head;
                caseKind = JCTree.JCCase.RULE;
                jCTree = jCStatement;
            } else {
                JCTree.JCExpression expression = parseExpression();
                listOf = List.of((JCTree.JCStatement) to(this.F.at(expression).Yield(expression)));
                caseKind = JCTree.JCCase.RULE;
                accept(Tokens.TokenKind.SEMI);
                jCTree = expression;
            }
        }
        JCTree jCTree2 = jCTree;
        listBuffer.append((JCTree.JCCase) toP(this.F.at(i).Case(caseKind, listBuffer2.toList(), guard, listOf, jCTree2)));
        return listBuffer.toList();
    }

    private JCTree.JCErroneous syntaxError(int i, List<? extends JCTree> list, JCDiagnostic.Error error, boolean z) {
        JCTree jCTreeLast;
        setErrorEndPos(i);
        JCTree.JCErroneous jCErroneousErroneous = this.F.at(i).Erroneous(list);
        reportSyntaxError(jCErroneousErroneous, error, z);
        if (list != null && (jCTreeLast = list.last()) != null) {
            storeEnd(jCTreeLast, i);
        }
        return (JCTree.JCErroneous) toP(jCErroneousErroneous);
    }

    private List<JCTree> topLevelMethodOrFieldDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) throws AssertionError {
        JCTree.JCExpression jCExpressionUnannotatedType;
        JavacParser javacParser;
        Tokens.TokenKind tokenKind;
        Tokens.Token token = this.token;
        int i = token.pos;
        if (comment == null) {
            comment = token.docComment();
        }
        Tokens.Comment comment2 = comment;
        List<JCTree.JCTypeParameter> listTypeParametersOpt = typeParametersOpt();
        if (listTypeParametersOpt.nonEmpty() && jCModifiers.pos == -1) {
            jCModifiers.pos = i;
            storeEnd(jCModifiers, i);
        }
        List<JCTree.JCAnnotation> listAnnotationsOpt = annotationsOpt(JCTree.Tag.ANNOTATION);
        if (listAnnotationsOpt.nonEmpty()) {
            List<JCTree.JCAnnotation> listAppendList = jCModifiers.annotations.appendList(listAnnotationsOpt);
            jCModifiers.annotations = listAppendList;
            if (jCModifiers.pos == -1) {
                jCModifiers.pos = listAppendList.head.pos;
            }
        }
        Tokens.Token token2 = this.token;
        int i2 = token2.pos;
        boolean z = token2.kind == Tokens.TokenKind.VOID;
        if (z) {
            jCExpressionUnannotatedType = (JCTree.JCExpression) to(this.F.at(i2).TypeIdent(TypeTag.VOID));
            nextToken();
        } else {
            jCExpressionUnannotatedType = unannotatedType(false);
        }
        JCTree.JCExpression jCExpression = jCExpressionUnannotatedType;
        Tokens.Token token3 = this.token;
        Tokens.TokenKind tokenKind2 = token3.kind;
        if (tokenKind2 == Tokens.TokenKind.IDENTIFIER) {
            int i3 = token3.pos;
            Name nameIdent = ident();
            if (this.token.kind == Tokens.TokenKind.LPAREN) {
                return List.of(methodDeclaratorRest(i3, jCModifiers, jCExpression, nameIdent, listTypeParametersOpt, false, z, false, comment2));
            }
            javacParser = this;
            if (!z && listTypeParametersOpt.isEmpty() && ((tokenKind = javacParser.token.kind) == Tokens.TokenKind.EQ || tokenKind == Tokens.TokenKind.SEMI || tokenKind == Tokens.TokenKind.COMMA)) {
                List<JCTree> list = javacParser.variableDeclaratorsRest(i3, jCModifiers, jCExpression, nameIdent, false, comment2, new ListBuffer(), false).toList();
                javacParser.accept(Tokens.TokenKind.SEMI);
                javacParser.storeEnd(list.last(), javacParser.S.prevToken().endPos);
                return list;
            }
        } else {
            javacParser = this;
            if (tokenKind2 == Tokens.TokenKind.LPAREN && jCExpression.hasTag(JCTree.Tag.IDENT)) {
                javacParser.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i2, CompilerProperties.Errors.InvalidMethDeclRetTypeReq);
                return List.of(javacParser.methodDeclaratorRest(i2, jCModifiers, null, javacParser.names.init, listTypeParametersOpt, false, true, false, comment2));
            }
        }
        return List.of(javacParser.F.Erroneous());
    }

    public static TypeTag typetag(Tokens.TokenKind tokenKind) {
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[tokenKind.ordinal()]) {
            case 22:
                return TypeTag.BYTE;
            case 23:
                return TypeTag.SHORT;
            case 24:
                return TypeTag.CHAR;
            case 25:
                return TypeTag.INT;
            case 26:
                return TypeTag.LONG;
            case 27:
                return TypeTag.FLOAT;
            case 28:
                return TypeTag.DOUBLE;
            case 29:
                return TypeTag.BOOLEAN;
            default:
                return TypeTag.NONE;
        }
    }

    public static JCTree.Tag unoptag(Tokens.TokenKind tokenKind) {
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[tokenKind.ordinal()]) {
            case 78:
                return JCTree.Tag.PREINC;
            case 79:
                return JCTree.Tag.PREDEC;
            case 80:
                return JCTree.Tag.NOT;
            case 81:
                return JCTree.Tag.COMPL;
            case 82:
                return JCTree.Tag.POS;
            case 83:
                return JCTree.Tag.NEG;
            default:
                return JCTree.Tag.NO_TAG;
        }
    }

    private void updateUnexpectedTopLevelDefinitionStartError(boolean z) {
        if (this.parseModuleInfo) {
            this.unexpectedTopLevelDefinitionStartError = CompilerProperties.Errors.ExpectedModuleOrOpen;
            return;
        }
        if (Source.Feature.IMPLICIT_CLASSES.allowedInSource(this.source) && !z) {
            this.unexpectedTopLevelDefinitionStartError = CompilerProperties.Errors.ClassMethodOrFieldExpected;
        } else if (this.allowRecords) {
            this.unexpectedTopLevelDefinitionStartError = CompilerProperties.Errors.Expected4(Tokens.TokenKind.CLASS, Tokens.TokenKind.INTERFACE, Tokens.TokenKind.ENUM, PsiKeyword.RECORD);
        } else {
            this.unexpectedTopLevelDefinitionStartError = CompilerProperties.Errors.Expected3(Tokens.TokenKind.CLASS, Tokens.TokenKind.INTERFACE, Tokens.TokenKind.ENUM);
        }
    }

    public void accept(Tokens.TokenKind tokenKind, Function<Tokens.TokenKind, JCDiagnostic.Error> function) {
        Tokens.Token token = this.token;
        if (token.kind == tokenKind) {
            nextToken();
        } else {
            setErrorEndPos(token.pos);
            reportSyntaxError(this.S.prevToken().endPos, function.apply(tokenKind));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:104:0x011f  */
    /* JADX WARN: Code duplicated, block: B:108:0x012a  */
    /* JADX WARN: Code duplicated, block: B:112:0x013c  */
    /* JADX WARN: Code duplicated, block: B:114:0x013f  */
    /* JADX WARN: Code duplicated, block: B:116:0x0142  */
    /* JADX WARN: Code duplicated, block: B:117:0x0144  */
    /* JADX WARN: Code duplicated, block: B:129:0x007c A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:134:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:143:0x0127 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x0134 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:150:0x0156 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:27:0x0048  */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:44:0x007f A[DONT_INVERT] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1093)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public com.sun.tools.javac.parser.JavacParser.ParensResult analyzeParens() {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.javac.parser.JavacParser.analyzeParens():com.sun.tools.javac.parser.JavacParser$ParensResult");
    }

    /* JADX WARN: Code duplicated, block: B:130:0x0119 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:81:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:87:0x0103 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x0105 A[ADDED_TO_REGION] */
    public PatternResult analyzePattern(int i) {
        PatternResult patternResult = PatternResult.EXPRESSION;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.S.token(i).kind.ordinal()];
            if (i4 != 3) {
                if (i4 == 5) {
                    i = skipAnnotation(i);
                } else if (i4 == 9) {
                    if (i3 == 0 && peekToken(i, this.LAX_IDENTIFIER)) {
                        if (i2 == 0) {
                            return PatternResult.PATTERN;
                        }
                        patternResult = PatternResult.PATTERN;
                        continue;
                    } else {
                        if (i3 != 0 && i2 == 0 && peekToken(i, new Predicate() { // from class: pm7
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return JavacParser.n((Tokens.TokenKind) obj);
                            }
                        })) {
                            return PatternResult.EXPRESSION;
                        }
                    }
                } else if (i4 == 49) {
                    continue;
                } else if (i4 == 51) {
                    if (i3 == 0) {
                        if (i3 != 0) {
                            continue;
                        }
                    } else if (i3 != 0) {
                        continue;
                    }
                } else if (i4 != 77) {
                    switch (i4) {
                        case 21:
                            i3++;
                            continue;
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 32:
                            if (i3 == 0) {
                                if (i3 != 0) {
                                }
                            } else if (i3 != 0) {
                            }
                            break;
                        case 31:
                            if (i3 == 0 && peekToken(i, new Predicate() { // from class: vm7
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return JavacParser.e((Tokens.TokenKind) obj);
                                }
                            })) {
                                return PatternResult.PATTERN;
                            }
                            if (i3 != 0) {
                                continue;
                            } else if (!peekToken(i, this.LAX_IDENTIFIER)) {
                                continue;
                            } else {
                                if (i2 == 0) {
                                    return PatternResult.PATTERN;
                                }
                                patternResult = PatternResult.PATTERN;
                            }
                            break;
                        default:
                            switch (i4) {
                                case 73:
                                    Tokens.TokenKind tokenKind = Tokens.TokenKind.RBRACKET;
                                    if (peekToken(i, tokenKind, this.LAX_IDENTIFIER)) {
                                        return PatternResult.PATTERN;
                                    }
                                    if (peekToken(i, tokenKind)) {
                                        i++;
                                    }
                                    break;
                                case 74:
                                    if (this.S.token(i + 1).kind == Tokens.TokenKind.RPAREN) {
                                        return (i2 == 0 || this.S.token(i + 2).kind != Tokens.TokenKind.ARROW) ? PatternResult.PATTERN : PatternResult.EXPRESSION;
                                    }
                                    i2++;
                                    continue;
                                case 75:
                                    continue;
                                default:
                                    switch (i4) {
                                        case 84:
                                            if (i2 > 0) {
                                                return PatternResult.EXPRESSION;
                                            }
                                            break;
                                        case 85:
                                            i2--;
                                            if (i2 != 0) {
                                                continue;
                                            } else {
                                                if (i3 == 0 && peekToken(i, Tokens.TokenKind.IDENTIFIER) && this.S.token(i + 1).name() == this.names.when) {
                                                    return PatternResult.PATTERN;
                                                }
                                            }
                                            break;
                                        case 86:
                                            continue;
                                        default:
                                            switch (i4) {
                                                case 88:
                                                    break;
                                                case 89:
                                                    i3--;
                                                case 90:
                                                    i3--;
                                                case 91:
                                                    i3--;
                                                    if (i3 == 0 && !peekToken(i, Tokens.TokenKind.DOT)) {
                                                        return (peekToken(i, this.LAX_IDENTIFIER) || peekToken(i, new Predicate() { // from class: wm7
                                                            @Override // java.util.function.Predicate
                                                            public final boolean test(Object obj) {
                                                                return JavacParser.j((Tokens.TokenKind) obj);
                                                            }
                                                        })) ? PatternResult.PATTERN : PatternResult.EXPRESSION;
                                                    }
                                                    if (i3 < 0) {
                                                        return PatternResult.EXPRESSION;
                                                    }
                                                    break;
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    continue;
                }
                i++;
            } else if (i2 > 0) {
                return PatternResult.PATTERN;
            }
        }
        return patternResult;
    }

    public JCTree.JCAnnotation annotation(int i, JCTree.Tag tag) {
        JCTree.JCAnnotation jCAnnotationTypeAnnotation;
        JCTree.JCExpression jCExpressionQualident = qualident(false);
        List<JCTree.JCExpression> listAnnotationFieldValuesOpt = annotationFieldValuesOpt();
        if (tag == JCTree.Tag.ANNOTATION) {
            jCAnnotationTypeAnnotation = this.F.at(i).Annotation(jCExpressionQualident, listAnnotationFieldValuesOpt);
        } else {
            if (tag != JCTree.Tag.TYPE_ANNOTATION) {
                s22.a("Unhandled annotation kind: ", tag);
                return null;
            }
            jCAnnotationTypeAnnotation = this.F.at(i).TypeAnnotation(jCExpressionQualident, listAnnotationFieldValuesOpt);
        }
        return (JCTree.JCAnnotation) toP(jCAnnotationTypeAnnotation);
    }

    public JCTree.JCExpression annotationFieldValue() {
        if (!this.LAX_IDENTIFIER.test(this.token.kind)) {
            return annotationValue();
        }
        selectExprMode();
        JCTree.JCExpression jCExpressionTerm1 = term1();
        if (jCExpressionTerm1.hasTag(JCTree.Tag.IDENT)) {
            Tokens.Token token = this.token;
            Tokens.TokenKind tokenKind = token.kind;
            Tokens.TokenKind tokenKind2 = Tokens.TokenKind.EQ;
            if (tokenKind == tokenKind2) {
                int i = token.pos;
                accept(tokenKind2);
                return (JCTree.JCExpression) toP(this.F.at(i).Assign(jCExpressionTerm1, annotationValue()));
            }
        }
        return jCExpressionTerm1;
    }

    public List<JCTree.JCExpression> annotationFieldValues() {
        accept(Tokens.TokenKind.LPAREN);
        ListBuffer listBuffer = new ListBuffer();
        if (this.token.kind != Tokens.TokenKind.RPAREN) {
            listBuffer.append(annotationFieldValue());
            while (this.token.kind == Tokens.TokenKind.COMMA) {
                nextToken();
                listBuffer.append(annotationFieldValue());
            }
        }
        accept(Tokens.TokenKind.RPAREN);
        return listBuffer.toList();
    }

    public List<JCTree.JCExpression> annotationFieldValuesOpt() {
        return this.token.kind == Tokens.TokenKind.LPAREN ? annotationFieldValues() : List.nil();
    }

    public JCTree.JCExpression annotationValue() {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()];
        if (i == 5) {
            int i2 = this.token.pos;
            nextToken();
            return annotation(i2, JCTree.Tag.ANNOTATION);
        }
        if (i != 11) {
            selectExprMode();
            return term1();
        }
        int i3 = this.token.pos;
        accept(Tokens.TokenKind.LBRACE);
        ListBuffer listBuffer = new ListBuffer();
        Tokens.TokenKind tokenKind = this.token.kind;
        if (tokenKind == Tokens.TokenKind.COMMA) {
            nextToken();
        } else if (tokenKind != Tokens.TokenKind.RBRACE) {
            listBuffer.append(annotationValue());
            while (this.token.kind == Tokens.TokenKind.COMMA) {
                nextToken();
                if (this.token.kind == Tokens.TokenKind.RBRACE) {
                    break;
                }
                listBuffer.append(annotationValue());
            }
        }
        accept(Tokens.TokenKind.RBRACE, new Function() { // from class: cn7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CompilerProperties.Errors.AnnotationMissingElementValue;
            }
        });
        return (JCTree.JCExpression) toP(this.F.at(i3).NewArray(null, List.nil(), listBuffer.toList()));
    }

    public List<JCTree.JCAnnotation> annotationsOpt(JCTree.Tag tag) {
        if (this.token.kind != Tokens.TokenKind.MONKEYS_AT) {
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        int i = this.mode;
        while (true) {
            Tokens.Token token = this.token;
            if (token.kind != Tokens.TokenKind.MONKEYS_AT) {
                setLastMode(this.mode);
                setMode(i);
                return listBuffer.toList();
            }
            int i2 = token.pos;
            nextToken();
            listBuffer.append(annotation(i2, tag));
        }
    }

    public List<JCTree.JCExpression> arguments() {
        ListBuffer listBuffer = new ListBuffer();
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.LPAREN;
        if (tokenKind == tokenKind2) {
            nextToken();
            if (this.token.kind != Tokens.TokenKind.RPAREN) {
                listBuffer.append(parseExpression());
                while (this.token.kind == Tokens.TokenKind.COMMA) {
                    nextToken();
                    listBuffer.append(parseExpression());
                }
            }
            accept(Tokens.TokenKind.RPAREN, new Function() { // from class: um7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CompilerProperties.Errors.Expected2(Tokens.TokenKind.RPAREN, Tokens.TokenKind.COMMA);
                }
            });
        } else {
            syntaxError(token.pos, CompilerProperties.Errors.Expected(tokenKind2));
        }
        return listBuffer.toList();
    }

    public JCTree.JCExpression argumentsOpt(List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression) {
        if (!(isMode(1) && this.token.kind == Tokens.TokenKind.LPAREN) && list == null) {
            return jCExpression;
        }
        selectExprMode();
        return arguments(list, jCExpression);
    }

    public JCTree.JCExpression arrayCreatorRest(int i, JCTree.JCExpression jCExpression) {
        Tokens.Token token;
        Tokens.TokenKind tokenKind;
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
        accept(Tokens.TokenKind.LBRACKET);
        Tokens.TokenKind tokenKind2 = this.token.kind;
        Tokens.TokenKind tokenKind3 = Tokens.TokenKind.RBRACKET;
        if (tokenKind2 == tokenKind3) {
            accept(tokenKind3);
            JCTree.JCExpression jCExpressionBracketsOpt = bracketsOpt(jCExpression, listTypeAnnotationsOpt);
            if (this.token.kind != Tokens.TokenKind.LBRACE) {
                return syntaxError(this.token.pos, List.of((JCTree.JCExpression) toP(this.F.at(i).NewArray(jCExpressionBracketsOpt, List.nil(), null))), CompilerProperties.Errors.ArrayDimensionMissing);
            }
            JCTree.JCNewArray jCNewArray = (JCTree.JCNewArray) arrayInitializer(i, jCExpressionBracketsOpt);
            if (listTypeAnnotationsOpt.nonEmpty()) {
                JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) jCExpressionBracketsOpt;
                jCNewArray.annotations = jCAnnotatedType.annotations;
                jCNewArray.elemtype = jCAnnotatedType.underlyingType;
            }
            return jCNewArray;
        }
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        listBuffer2.append(listTypeAnnotationsOpt);
        listBuffer.append(parseExpression());
        accept(tokenKind3);
        while (true) {
            token = this.token;
            tokenKind = token.kind;
            if (tokenKind != Tokens.TokenKind.LBRACKET && tokenKind != Tokens.TokenKind.MONKEYS_AT) {
                break;
            }
            List<JCTree.JCAnnotation> listTypeAnnotationsOpt2 = typeAnnotationsOpt();
            int i2 = this.token.pos;
            nextToken();
            Tokens.TokenKind tokenKind4 = this.token.kind;
            Tokens.TokenKind tokenKind5 = Tokens.TokenKind.RBRACKET;
            if (tokenKind4 == tokenKind5) {
                jCExpression = bracketsOptCont(jCExpression, i2, listTypeAnnotationsOpt2);
            } else {
                listBuffer2.append(listTypeAnnotationsOpt2);
                listBuffer.append(parseExpression());
                accept(tokenKind5);
            }
        }
        int i3 = token.pos;
        List<JCTree.JCExpression> listArrayInitializerElements = tokenKind == Tokens.TokenKind.LBRACE ? arrayInitializerElements(i, jCExpression) : null;
        JCTree.JCNewArray jCNewArray2 = (JCTree.JCNewArray) toP(this.F.at(i).NewArray(jCExpression, listBuffer.toList(), listArrayInitializerElements));
        jCNewArray2.dimAnnotations = listBuffer2.toList();
        return listArrayInitializerElements != null ? syntaxError(i3, List.of(jCNewArray2), CompilerProperties.Errors.IllegalArrayCreationBothDimensionAndInitialization) : jCNewArray2;
    }

    public JCTree.JCExpression arrayInitializer(int i, JCTree.JCExpression jCExpression) {
        return (JCTree.JCExpression) toP(this.F.at(i).NewArray(jCExpression, List.nil(), arrayInitializerElements(i, jCExpression)));
    }

    public List<JCTree.JCExpression> arrayInitializerElements(int i, JCTree.JCExpression jCExpression) {
        accept(Tokens.TokenKind.LBRACE);
        ListBuffer listBuffer = new ListBuffer();
        Tokens.TokenKind tokenKind = this.token.kind;
        if (tokenKind == Tokens.TokenKind.COMMA) {
            nextToken();
        } else if (tokenKind != Tokens.TokenKind.RBRACE) {
            listBuffer.append(variableInitializer());
            while (this.token.kind == Tokens.TokenKind.COMMA) {
                nextToken();
                if (this.token.kind == Tokens.TokenKind.RBRACE) {
                    break;
                }
                listBuffer.append(variableInitializer());
            }
        }
        accept(Tokens.TokenKind.RBRACE);
        return listBuffer.toList();
    }

    public <T extends JCTree> T attach(T t, Tokens.Comment comment) {
        if (this.keepDocComments && comment != null) {
            this.docComments.putComment(t, comment);
        }
        reportDanglingComments(t, comment);
        return t;
    }

    public JCTree.JCPrimitiveTypeTree basicType() {
        JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree = (JCTree.JCPrimitiveTypeTree) to(this.F.at(this.token.pos).TypeIdent(typetag(this.token.kind)));
        nextToken();
        return jCPrimitiveTypeTree;
    }

    public JCTree.JCBlock block(int i, long j) {
        accept(Tokens.TokenKind.LBRACE);
        ignoreDanglingComments();
        JCTree.JCBlock jCBlockBlock = this.F.at(i).Block(j, blockStatements());
        while (true) {
            Tokens.Token token = this.token;
            Tokens.TokenKind tokenKind = token.kind;
            if (tokenKind != Tokens.TokenKind.CASE && tokenKind != Tokens.TokenKind.DEFAULT) {
                jCBlockBlock.bracePos = token.pos;
                accept(Tokens.TokenKind.RBRACE);
                return (JCTree.JCBlock) toP(jCBlockBlock);
            }
            syntaxError(token.pos, CompilerProperties.Errors.Orphaned(tokenKind));
            switchBlockStatementGroups();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:65:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ca A[FALL_THROUGH] */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0073, code lost:
    
        if (r11.S.token(1).kind != com.sun.tools.javac.parser.Tokens.TokenKind.SEMI) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0080, code lost:
    
        if (r11.S.token(2).kind != com.sun.tools.javac.parser.Tokens.TokenKind.SEMI) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00c8, code lost:
    
        if (r9 == com.sun.tools.javac.parser.Tokens.TokenKind.ARROW) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<JCTree.JCStatement> blockStatement() {
        Tokens.Token token = this.token;
        int i = token.pos;
        int[] iArr = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind;
        int i2 = iArr[token.kind.ordinal()];
        if (i2 != 1 && i2 != 51 && i2 != 11) {
            if (i2 != 12) {
                if (i2 != 19) {
                    if (i2 != 20) {
                        switch (i2) {
                            case 3:
                            case 5:
                                Tokens.Comment commentDocComment = this.token.docComment();
                                JCTree.JCModifiers jCModifiersModifiersOpt = modifiersOpt();
                                return isDeclaration() ? List.of(classOrRecordOrInterfaceOrEnumDeclaration(jCModifiersModifiersOpt, commentDocComment)) : localVariableDeclarations(jCModifiersModifiersOpt, parseType(true), commentDocComment);
                            case 4:
                                break;
                            case 6:
                                break;
                            case 7:
                            case 8:
                                return List.of(classOrRecordOrInterfaceOrEnumDeclaration(modifiersOpt(), this.token.docComment()));
                            case 9:
                                if (!this.allowRecords) {
                                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.LocalEnum);
                                }
                                return List.of(classOrRecordOrInterfaceOrEnumDeclaration(modifiersOpt(), this.token.docComment()));
                            default:
                                switch (i2) {
                                    case 32:
                                        if (this.token.name() == this.names.yield && this.allowYieldStatement) {
                                            int i3 = iArr[this.S.token(1).kind.ordinal()];
                                            if (i3 != 1 && i3 != 40) {
                                                int i4 = 2;
                                                if (i3 == 74) {
                                                    boolean z = false;
                                                    boolean z2 = false;
                                                    int i5 = 1;
                                                    while (true) {
                                                        Tokens.TokenKind tokenKind = this.S.token(i4).kind;
                                                        if (tokenKind != Tokens.TokenKind.EOF && i5 != 0) {
                                                            int i6 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[tokenKind.ordinal()];
                                                            if (i6 == 21) {
                                                                z2 = true;
                                                            } else if (i6 == 74) {
                                                                i5++;
                                                            } else if (i6 == 85) {
                                                                i5--;
                                                            } else if (i6 != 88) {
                                                                if (i6 == 91) {
                                                                    z2 = false;
                                                                }
                                                            } else if (i5 == 1 && !z2) {
                                                                z = true;
                                                            }
                                                            i4++;
                                                        }
                                                    }
                                                    if (!z) {
                                                        if (i4 == 3) {
                                                        }
                                                    }
                                                    break;
                                                } else if (i3 != 92) {
                                                    switch (i3) {
                                                        default:
                                                            switch (i3) {
                                                                default:
                                                                    switch (i3) {
                                                                        default:
                                                                            switch (i3) {
                                                                                case 78:
                                                                                case 79:
                                                                                    break;
                                                                                case 80:
                                                                                case 81:
                                                                                    break;
                                                                            }
                                                                        case 52:
                                                                        case 53:
                                                                        case 54:
                                                                        case 55:
                                                                        case 56:
                                                                        case 57:
                                                                        case 58:
                                                                        case 59:
                                                                        case 60:
                                                                            nextToken();
                                                                            JCTree.JCExpression jCExpressionTerm = term(1);
                                                                            accept(Tokens.TokenKind.SEMI);
                                                                            return List.of((JCTree.JCStatement) toP(this.F.at(i).Yield(jCExpressionTerm)));
                                                                    }
                                                                case 48:
                                                                case 49:
                                                                case 50:
                                                                    nextToken();
                                                                    JCTree.JCExpression jCExpressionTerm2 = term(1);
                                                                    accept(Tokens.TokenKind.SEMI);
                                                                    return List.of((JCTree.JCStatement) toP(this.F.at(i).Yield(jCExpressionTerm2)));
                                                            }
                                                        case 22:
                                                        case 23:
                                                        case 24:
                                                        case 25:
                                                        case 26:
                                                        case 27:
                                                        case 28:
                                                        case 29:
                                                        case 30:
                                                        case 31:
                                                        case 32:
                                                            nextToken();
                                                            JCTree.JCExpression jCExpressionTerm3 = term(1);
                                                            accept(Tokens.TokenKind.SEMI);
                                                            return List.of((JCTree.JCStatement) toP(this.F.at(i).Yield(jCExpressionTerm3)));
                                                    }
                                                }
                                            }
                                            nextToken();
                                            JCTree.JCExpression jCExpressionTerm4 = term(1);
                                            accept(Tokens.TokenKind.SEMI);
                                            return List.of((JCTree.JCStatement) toP(this.F.at(i).Yield(jCExpressionTerm4)));
                                        }
                                        if (isNonSealedClassStart(true)) {
                                            this.log.error(this.token.pos, CompilerProperties.Errors.SealedOrNonSealedLocalClassesNotAllowed);
                                            nextToken();
                                            nextToken();
                                            nextToken();
                                            return List.of(classOrRecordOrInterfaceOrEnumDeclaration(modifiersOpt(), this.token.docComment()));
                                        }
                                        if (isSealedClassStart(true)) {
                                            checkSourceLevel(Source.Feature.SEALED_CLASSES);
                                            this.log.error(this.token.pos, CompilerProperties.Errors.SealedOrNonSealedLocalClassesNotAllowed);
                                            nextToken();
                                            return List.of(classOrRecordOrInterfaceOrEnumDeclaration(modifiersOpt(), this.token.docComment()));
                                        }
                                        break;
                                }
                                Tokens.Comment commentDocComment2 = this.token.docComment();
                                if (isRecordStart() && this.allowRecords) {
                                    return List.of(recordDeclaration(this.F.at(i).Modifiers(0L), commentDocComment2));
                                }
                                Tokens.Token token2 = this.token;
                                JCTree.JCExpression jCExpressionTerm5 = term(3);
                                if (this.token.kind == Tokens.TokenKind.COLON && jCExpressionTerm5.hasTag(JCTree.Tag.IDENT)) {
                                    nextToken();
                                    return List.of(this.F.at(i).Labelled(token2.name(), parseStatementAsBlock()));
                                }
                                if (!wasTypeMode() || !this.LAX_IDENTIFIER.test(this.token.kind)) {
                                    JCTree.JCExpression jCExpressionCheckExprStat = checkExprStat(jCExpressionTerm5);
                                    accept(Tokens.TokenKind.SEMI);
                                    return List.of((JCTree.JCExpressionStatement) toP(this.F.at(i).Exec(jCExpressionCheckExprStat)));
                                }
                                int i7 = this.token.pos;
                                JCTree.JCModifiers jCModifiersModifiers = this.F.at(-1).Modifiers(0L);
                                this.F.at(i7);
                                return localVariableDeclarations(jCModifiersModifiers, jCExpressionTerm5, commentDocComment2);
                        }
                    }
                    return List.of(classOrRecordOrInterfaceOrEnumDeclaration(modifiersOpt(), this.token.docComment()));
                }
            }
            return List.nil();
        }
        return List.of(parseSimpleStatement());
    }

    public List<JCTree.JCStatement> blockStatements() {
        int i;
        ListBuffer listBuffer = new ListBuffer();
        int i2 = -1;
        while (true) {
            List<JCTree.JCStatement> listBlockStatement = blockStatement();
            ignoreDanglingComments();
            if (!listBlockStatement.isEmpty() && (i = this.token.pos) != i2) {
                if (i <= this.endPosTable.errorEndPos) {
                    skip(false, true, true, true);
                    i2 = this.token.pos;
                }
                listBuffer.addAll(listBlockStatement);
            }
            return listBuffer.toList();
        }
    }

    public JCTree.JCExpression bracketsSuffix(JCTree.JCExpression jCExpression) {
        Name name;
        if (!isMode(1) || this.token.kind != Tokens.TokenKind.DOT) {
            boolean zIsMode = isMode(2);
            Tokens.Token token = this.token;
            if (zIsMode) {
                if (token.kind != Tokens.TokenKind.COLCOL) {
                    selectTypeMode();
                    return jCExpression;
                }
            } else if (token.kind != Tokens.TokenKind.COLCOL) {
                syntaxError(token.pos, CompilerProperties.Errors.DotClassExpected);
            }
            return jCExpression;
        }
        selectExprMode();
        int i = this.token.pos;
        nextToken();
        accept(Tokens.TokenKind.CLASS);
        Tokens.Token token2 = this.token;
        if (token2.pos == this.endPosTable.errorEndPos) {
            if (this.LAX_IDENTIFIER.test(token2.kind)) {
                name = this.token.name();
                nextToken();
            } else {
                name = this.names.error;
            }
            return this.F.at(i).Erroneous(List.of(toP(this.F.at(i).Select(jCExpression, name))));
        }
        JCTree.Tag tag = jCExpression.getTag();
        if ((tag == JCTree.Tag.TYPEARRAY && TreeInfo.containsTypeAnnotation(jCExpression)) || tag == JCTree.Tag.ANNOTATED_TYPE) {
            syntaxError(this.token.pos, CompilerProperties.Errors.NoAnnotationsOnDotClass);
        }
        return (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpression, this.names._class));
    }

    public JCTree.JCCatch catchClause() {
        int i = this.token.pos;
        accept(Tokens.TokenKind.CATCH);
        accept(Tokens.TokenKind.LPAREN);
        JCTree.JCModifiers jCModifiersOptFinal = optFinal(8589934592L);
        List<JCTree.JCExpression> listCatchTypes = catchTypes();
        JCTree.JCVariableDecl jCVariableDeclVariableDeclaratorId = variableDeclaratorId(jCModifiersOptFinal, listCatchTypes.size() > 1 ? (JCTree.JCExpression) toP(this.F.at(listCatchTypes.head.getStartPosition()).TypeUnion(listCatchTypes)) : listCatchTypes.head, true, false, false);
        accept(Tokens.TokenKind.RPAREN);
        return this.F.at(i).Catch(jCVariableDeclVariableDeclaratorId, block());
    }

    public List<JCTree.JCExpression> catchTypes() {
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.add(parseType());
        while (this.token.kind == Tokens.TokenKind.BAR) {
            nextToken();
            listBuffer.add(parseType());
        }
        return listBuffer.toList();
    }

    public JCTree.JCExpression checkExprStat(JCTree.JCExpression jCExpression) {
        if (TreeInfo.isExpressionStatement(jCExpression)) {
            return jCExpression;
        }
        JCTree.JCErroneous jCErroneousErroneous = this.F.at(jCExpression.pos).Erroneous(List.of(jCExpression));
        this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, jCErroneousErroneous, CompilerProperties.Errors.NotStmt);
        return jCErroneousErroneous;
    }

    public void checkNoMods(int i, long j) {
        if (j != 0) {
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i, CompilerProperties.Errors.ModNotAllowedHere(Flags.asFlagSet(j & (-j))));
        }
    }

    public void checkSourceLevel(int i, Source.Feature feature) {
        if (this.preview.isPreview(feature) && !this.preview.isEnabled()) {
            this.log.error(i, this.preview.disabledError(feature));
        } else if (!feature.allowedInSource(this.source)) {
            this.log.error(i, feature.error(this.source.name));
        } else if (this.preview.isPreview(feature)) {
            this.preview.warnPreview(i, feature);
        }
    }

    public JCTree.JCNewClass classCreatorRest(int i, JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression2) {
        JCTree.JCClassDecl jCClassDecl;
        List<JCTree.JCExpression> listArguments = arguments();
        if (this.token.kind == Tokens.TokenKind.LBRACE) {
            ignoreDanglingComments();
            int i2 = this.token.pos;
            List<JCTree> listClassInterfaceOrRecordBody = classInterfaceOrRecordBody(this.names.empty, false, false);
            jCClassDecl = (JCTree.JCClassDecl) toP(this.F.at(i2).AnonymousClassDef(this.F.at(-1).Modifiers(0L), listClassInterfaceOrRecordBody));
        } else {
            jCClassDecl = null;
        }
        return (JCTree.JCNewClass) toP(this.F.at(i).NewClass(jCExpression, list, jCExpression2, listArguments, jCClassDecl));
    }

    public JCTree.JCClassDecl classDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) {
        JCTree.JCExpression type;
        int i = this.token.pos;
        accept(Tokens.TokenKind.CLASS);
        Name nameTypeName = typeName();
        List<JCTree.JCTypeParameter> listTypeParametersOpt = typeParametersOpt();
        if (this.token.kind == Tokens.TokenKind.EXTENDS) {
            nextToken();
            type = parseType();
        } else {
            type = null;
        }
        JCTree.JCExpression jCExpression = type;
        List<JCTree.JCExpression> listNil = List.nil();
        if (this.token.kind == Tokens.TokenKind.IMPLEMENTS) {
            nextToken();
            listNil = typeList();
        }
        List<JCTree.JCExpression> list = listNil;
        List<JCTree.JCExpression> listPermitsClause = permitsClause(jCModifiers, "class");
        saveDanglingDocComments(comment);
        return (JCTree.JCClassDecl) attach((JCTree.JCClassDecl) toP(this.F.at(i).ClassDef(jCModifiers, nameTypeName, listTypeParametersOpt, jCExpression, list, listPermitsClause, classInterfaceOrRecordBody(nameTypeName, false, false))), comment);
    }

    public List<JCTree> classInterfaceOrRecordBody(Name name, boolean z, boolean z2) {
        Tokens.TokenKind tokenKind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.LBRACE;
        accept(tokenKind2);
        if (this.token.pos <= this.endPosTable.errorEndPos) {
            skip(false, true, false, false);
            if (this.token.kind != tokenKind2) {
                return List.nil();
            }
            nextToken();
        }
        ListBuffer listBuffer = new ListBuffer();
        while (true) {
            Tokens.TokenKind tokenKind3 = this.token.kind;
            tokenKind = Tokens.TokenKind.RBRACE;
            if (tokenKind3 == tokenKind || tokenKind3 == Tokens.TokenKind.EOF) {
                break;
            }
            listBuffer.appendList(classOrInterfaceOrRecordBodyDeclaration(null, name, z, z2));
            if (this.token.pos <= this.endPosTable.errorEndPos) {
                skip(false, true, true, false);
            }
        }
        accept(tokenKind);
        return listBuffer.toList();
    }

    public List<JCTree> classOrInterfaceOrRecordBodyDeclaration(JCTree.JCModifiers jCModifiers, Name name, boolean z, boolean z2) {
        Tokens.Token token = this.token;
        if (token.kind == Tokens.TokenKind.SEMI) {
            nextToken();
            return List.nil();
        }
        Tokens.Comment commentDocComment = token.docComment();
        int i = this.token.pos;
        JCTree.JCModifiers jCModifiersModifiersOpt = modifiersOpt(jCModifiers);
        if (isDeclaration()) {
            return List.of(classOrRecordOrInterfaceOrEnumDeclaration(jCModifiersModifiersOpt, commentDocComment));
        }
        if (this.token.kind != Tokens.TokenKind.LBRACE || (jCModifiersModifiersOpt.flags & 4087) != 0 || !jCModifiersModifiersOpt.annotations.isEmpty()) {
            return isDefiniteStatementStartToken() ? List.of(syntaxError(this.token.pos, blockStatement(), CompilerProperties.Errors.StatementNotExpected)) : constructorOrMethodOrFieldDeclaration(jCModifiersModifiersOpt, name, z, z2, commentDocComment);
        }
        if (z) {
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.InitializerNotAllowed);
        } else if (z2 && (jCModifiersModifiersOpt.flags & 8) == 0) {
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.InstanceInitializerNotAllowedInRecords);
        }
        ignoreDanglingComments();
        return List.of(block(i, jCModifiersModifiersOpt.flags));
    }

    public JCTree.JCStatement classOrRecordOrInterfaceOrEnumDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) {
        List<? extends JCTree> listOf;
        if (this.token.kind == Tokens.TokenKind.CLASS) {
            return classDeclaration(jCModifiers, comment);
        }
        if (isRecordStart()) {
            return recordDeclaration(jCModifiers, comment);
        }
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        if (tokenKind == Tokens.TokenKind.INTERFACE) {
            return interfaceDeclaration(jCModifiers, comment);
        }
        if (tokenKind == Tokens.TokenKind.ENUM) {
            return enumDeclaration(jCModifiers, comment);
        }
        int i = token.pos;
        if (this.LAX_IDENTIFIER.test(tokenKind)) {
            listOf = List.of((JCTree) jCModifiers, toP(this.F.at(i).Ident(ident())));
            setErrorEndPos(this.token.pos);
        } else {
            listOf = List.of(jCModifiers);
        }
        return (JCTree.JCStatement) toP(this.F.Exec(syntaxError(i, listOf, this.unexpectedTopLevelDefinitionStartError)));
    }

    /* JADX WARN: Code duplicated, block: B:55:0x0165  */
    public JCTree.JCExpression creator(int i, List<JCTree.JCExpression> list) {
        int i2;
        boolean zIsMode;
        int i3;
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()]) {
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                if (list == null) {
                    return listTypeAnnotationsOpt.isEmpty() ? arrayCreatorRest(i, basicType()) : arrayCreatorRest(i, (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt.head.pos).AnnotatedType(listTypeAnnotationsOpt, basicType())));
                }
                break;
        }
        JCTree.JCExpression jCExpressionQualident = qualident(true);
        int i4 = this.mode;
        selectTypeMode();
        Tokens.Token token = this.token;
        if (token.kind == Tokens.TokenKind.LT) {
            i2 = token.pos;
            jCExpressionQualident = typeArguments(jCExpressionQualident, true);
            zIsMode = isMode(16);
        } else {
            i2 = -1;
            zIsMode = false;
        }
        while (this.token.kind == Tokens.TokenKind.DOT) {
            if (zIsMode) {
                illegal();
            }
            int i5 = this.token.pos;
            nextToken();
            List<JCTree.JCAnnotation> listTypeAnnotationsOpt2 = typeAnnotationsOpt();
            jCExpressionQualident = (JCTree.JCExpression) toP(this.F.at(i5).Select(jCExpressionQualident, ident()));
            if (listTypeAnnotationsOpt2 != null && listTypeAnnotationsOpt2.nonEmpty()) {
                jCExpressionQualident = (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt2.head.pos).AnnotatedType(listTypeAnnotationsOpt2, jCExpressionQualident));
            }
            Tokens.Token token2 = this.token;
            if (token2.kind == Tokens.TokenKind.LT) {
                i2 = token2.pos;
                jCExpressionQualident = typeArguments(jCExpressionQualident, true);
                zIsMode = isMode(16);
            }
        }
        setMode(i4);
        Tokens.Token token3 = this.token;
        Tokens.TokenKind tokenKind = token3.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.LBRACKET;
        if (tokenKind != tokenKind2 && tokenKind != Tokens.TokenKind.MONKEYS_AT) {
            Tokens.TokenKind tokenKind3 = Tokens.TokenKind.LPAREN;
            if (tokenKind == tokenKind3) {
                if (listTypeAnnotationsOpt.nonEmpty()) {
                    jCExpressionQualident = insertAnnotationsToMostInner(jCExpressionQualident, listTypeAnnotationsOpt, false);
                }
                return classCreatorRest(i, null, list, jCExpressionQualident);
            }
            setErrorEndPos(token3.pos);
            reportSyntaxError(this.token.pos, CompilerProperties.Errors.Expected2(tokenKind3, tokenKind2));
            return (JCTree.JCExpression) toP(this.F.at(i).Erroneous(List.of((JCTree.JCExpression) toP(this.F.at(i).NewClass(null, list, jCExpressionQualident, List.nil(), null)))));
        }
        if (listTypeAnnotationsOpt.nonEmpty()) {
            jCExpressionQualident = insertAnnotationsToMostInner(jCExpressionQualident, listTypeAnnotationsOpt, false);
        }
        JCTree.JCExpression jCExpressionArrayCreatorRest = arrayCreatorRest(i, jCExpressionQualident);
        if (zIsMode) {
            reportSyntaxError(i2, CompilerProperties.Errors.CannotCreateArrayWithDiamond);
            return (JCTree.JCExpression) toP(this.F.at(i).Erroneous(List.of(jCExpressionArrayCreatorRest)));
        }
        if (list == null) {
            return jCExpressionArrayCreatorRest;
        }
        if (list.isEmpty()) {
            i3 = i;
        } else {
            JCTree.JCExpression jCExpression = list.head;
            if (jCExpression.pos != -1) {
                i3 = jCExpression.pos;
            } else {
                i3 = i;
            }
        }
        setErrorEndPos(this.S.prevToken().endPos);
        JCTree.JCErroneous jCErroneousErroneous = this.F.at(i3).Erroneous(list.prepend(jCExpressionArrayCreatorRest));
        reportSyntaxError(jCErroneousErroneous, CompilerProperties.Errors.CannotCreateArrayWithTypeArguments);
        return (JCTree.JCExpression) toP(jCErroneousErroneous);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003d  */
    public List<JCTree> enumBody(Name name) {
        boolean z;
        boolean z2;
        boolean z3;
        Tokens.TokenKind tokenKind;
        Tokens.TokenKind tokenKind2;
        accept(Tokens.TokenKind.LBRACE);
        ListBuffer listBuffer = new ListBuffer();
        if (this.token.kind == Tokens.TokenKind.COMMA) {
            nextToken();
            Tokens.TokenKind tokenKind3 = this.token.kind;
            Tokens.TokenKind tokenKind4 = Tokens.TokenKind.SEMI;
            if (tokenKind3 == tokenKind4) {
                nextToken();
                z = true;
                z2 = false;
                z3 = z2;
            } else {
                Tokens.TokenKind tokenKind5 = Tokens.TokenKind.RBRACE;
                if (tokenKind3 != tokenKind5) {
                    reportSyntaxError(this.S.prevToken().endPos, CompilerProperties.Errors.Expected2(tokenKind5, tokenKind4));
                    z3 = true;
                    z = false;
                    z2 = false;
                } else {
                    z = false;
                    z2 = false;
                    z3 = z2;
                }
            }
        } else {
            z = false;
            z2 = false;
            z3 = z2;
        }
        while (true) {
            Tokens.TokenKind tokenKind6 = this.token.kind;
            tokenKind = Tokens.TokenKind.RBRACE;
            if (tokenKind6 == tokenKind || tokenKind6 == (tokenKind2 = Tokens.TokenKind.EOF)) {
                break;
            }
            Tokens.TokenKind tokenKind7 = Tokens.TokenKind.SEMI;
            if (tokenKind6 == tokenKind7) {
                accept(tokenKind7);
                Tokens.TokenKind tokenKind8 = this.token.kind;
                if (tokenKind8 == tokenKind || tokenKind8 == tokenKind2) {
                    break;
                }
                z = true;
            }
            EnumeratorEstimate enumeratorEstimateEstimateEnumeratorOrMember = estimateEnumeratorOrMember(name);
            if (enumeratorEstimateEstimateEnumeratorOrMember == EnumeratorEstimate.UNKNOWN) {
                enumeratorEstimateEstimateEnumeratorOrMember = z ? EnumeratorEstimate.MEMBER : EnumeratorEstimate.ENUMERATOR;
            }
            if (enumeratorEstimateEstimateEnumeratorOrMember == EnumeratorEstimate.ENUMERATOR) {
                if (z && !z2) {
                    reportSyntaxError(this.token.pos, CompilerProperties.Errors.EnumConstantNotExpected);
                    z2 = true;
                }
                listBuffer.append(enumeratorDeclaration(name));
                Tokens.Token token = this.token;
                int i = token.pos;
                if (i <= this.endPosTable.errorEndPos) {
                    skip(false, true, true, false);
                } else {
                    Tokens.TokenKind tokenKind9 = token.kind;
                    if (tokenKind9 != tokenKind && tokenKind9 != tokenKind7 && tokenKind9 != tokenKind2) {
                        Tokens.TokenKind tokenKind10 = Tokens.TokenKind.COMMA;
                        if (tokenKind9 == tokenKind10) {
                            nextToken();
                        } else {
                            setErrorEndPos(i);
                            reportSyntaxError(this.S.prevToken().endPos, CompilerProperties.Errors.Expected3(tokenKind10, tokenKind, tokenKind7));
                            z3 = true;
                        }
                    }
                }
            } else {
                if (!z && !z2 && !z3) {
                    reportSyntaxError(this.token.pos, CompilerProperties.Errors.EnumConstantExpected);
                    z2 = true;
                }
                listBuffer.appendList(classOrInterfaceOrRecordBodyDeclaration(null, name, false, false));
                if (this.token.pos <= this.endPosTable.errorEndPos) {
                    skip(false, true, true, false);
                }
            }
            z3 = false;
        }
        accept(tokenKind);
        return listBuffer.toList();
    }

    public JCTree.JCClassDecl enumDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) {
        int i = this.token.pos;
        accept(Tokens.TokenKind.ENUM);
        Name nameTypeName = typeName();
        int i2 = this.token.pos;
        List<JCTree.JCTypeParameter> listTypeParametersOpt = typeParametersOpt(true);
        if (listTypeParametersOpt == null || !listTypeParametersOpt.isEmpty()) {
            if (listTypeParametersOpt != null) {
                i2 = listTypeParametersOpt.head.pos;
            }
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i2, CompilerProperties.Errors.EnumCantBeGeneric);
        }
        List<JCTree.JCExpression> listNil = List.nil();
        if (this.token.kind == Tokens.TokenKind.IMPLEMENTS) {
            nextToken();
            listNil = typeList();
        }
        List<JCTree.JCExpression> list = listNil;
        saveDanglingDocComments(comment);
        List<JCTree> listEnumBody = enumBody(nameTypeName);
        jCModifiers.flags |= 16384;
        return (JCTree.JCClassDecl) attach((JCTree.JCClassDecl) toP(this.F.at(i).ClassDef(jCModifiers, nameTypeName, List.nil(), null, list, listEnumBody)), comment);
    }

    public JCTree enumeratorDeclaration(Name name) {
        JCTree.JCClassDecl jCClassDecl;
        Tokens.Comment commentDocComment = this.token.docComment();
        int i = this.token.deprecatedFlag() ? 147481 : 16409;
        int i2 = this.token.pos;
        List<JCTree.JCAnnotation> listAnnotationsOpt = annotationsOpt(JCTree.Tag.ANNOTATION);
        JCTree.JCModifiers jCModifiersModifiers = this.F.at(listAnnotationsOpt.isEmpty() ? -1 : i2).Modifiers(i, listAnnotationsOpt);
        List<JCTree.JCExpression> listTypeArgumentsOpt = typeArgumentsOpt();
        int i3 = this.token.pos;
        Name nameIdent = ident();
        int i4 = this.token.pos;
        saveDanglingDocComments(commentDocComment);
        List<JCTree.JCExpression> listArguments = this.token.kind == Tokens.TokenKind.LPAREN ? arguments() : List.nil();
        if (this.token.kind == Tokens.TokenKind.LBRACE) {
            jCClassDecl = (JCTree.JCClassDecl) toP(this.F.at(i3).AnonymousClassDef(this.F.at(-1).Modifiers(16384L), classInterfaceOrRecordBody(this.names.empty, false, false)));
        } else {
            jCClassDecl = null;
        }
        JCTree.JCClassDecl jCClassDecl2 = jCClassDecl;
        if (listArguments.isEmpty() && jCClassDecl2 == null) {
            i4 = i3;
        }
        JCTree.JCNewClass jCNewClassNewClass = this.F.at(i4).NewClass(null, listTypeArgumentsOpt, this.F.at(i3).Ident(name), listArguments, jCClassDecl2);
        if (i4 != i3) {
            storeEnd(jCNewClassNewClass, this.S.prevToken().endPos);
        }
        return attach(toP(this.F.at(i2).VarDef(jCModifiersModifiers, nameIdent, this.F.at(i3).Ident(name), jCNewClassNewClass)), commentDocComment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JCTree.JCExpression foldStrings(JCTree.JCExpression jCExpression) {
        if (this.allowStringFolding) {
            ListBuffer<JCTree.JCExpression> listBuffer = new ListBuffer<>();
            ListBuffer<JCTree.JCLiteral> listBuffer2 = new ListBuffer<>();
            JCTree.JCExpression jCExpression2 = jCExpression;
            boolean zFoldIfNeeded = false;
            while (jCExpression2.hasTag(JCTree.Tag.PLUS)) {
                JCTree.JCBinary jCBinary = (JCTree.JCBinary) jCExpression2;
                zFoldIfNeeded |= foldIfNeeded(jCBinary.rhs, listBuffer2, listBuffer, false);
                jCExpression2 = jCBinary.lhs;
            }
            if (foldIfNeeded(jCExpression2, listBuffer2, listBuffer, true) | zFoldIfNeeded) {
                List<JCTree.JCExpression> list = listBuffer.toList();
                JCTree.JCExpression jCExpressionBinary = (JCTree.JCExpression) list.head;
                for (A a : list.tail) {
                    jCExpressionBinary = this.F.at(a.getStartPosition()).Binary(optag(Tokens.TokenKind.PLUS), jCExpressionBinary, a);
                    storeEnd(jCExpressionBinary, getEndPos(a));
                }
                return jCExpressionBinary;
            }
        }
        return jCExpression;
    }

    public List<JCTree.JCStatement> forInit() {
        ListBuffer listBuffer = new ListBuffer();
        Tokens.Token token = this.token;
        int i = token.pos;
        Tokens.TokenKind tokenKind = token.kind;
        if (tokenKind == Tokens.TokenKind.FINAL || tokenKind == Tokens.TokenKind.MONKEYS_AT) {
            return variableDeclarators(optFinal(0L), parseType(true), listBuffer, true).toList();
        }
        JCTree.JCExpression jCExpressionTerm = term(3);
        if (wasTypeMode() && this.LAX_IDENTIFIER.test(this.token.kind)) {
            return variableDeclarators(modifiersOpt(), jCExpressionTerm, listBuffer, true).toList();
        }
        if (!wasTypeMode() || this.token.kind != Tokens.TokenKind.COLON) {
            return moreStatementExpressions(i, jCExpressionTerm, listBuffer).toList();
        }
        this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i, CompilerProperties.Errors.BadInitializer("for-loop"));
        return List.of(this.F.at(i).VarDef(modifiersOpt(), this.names.error, jCExpressionTerm, null));
    }

    public List<JCTree.JCExpressionStatement> forUpdate() {
        return moreStatementExpressions(this.token.pos, parseExpression(), new ListBuffer()).toList();
    }

    public JCTree.JCVariableDecl formalParameter(boolean z, boolean z2) {
        JCTree.JCModifiers jCModifiersOptFinal = !z2 ? optFinal(8589934592L) : modifiersOpt();
        if (z2 && jCModifiersOptFinal.flags != 0) {
            this.log.error(jCModifiersOptFinal.pos, CompilerProperties.Errors.RecordCantDeclareFieldModifiers);
        }
        if (z2) {
            jCModifiersOptFinal.flags |= 2305843009230471186L;
        }
        this.permitTypeAnnotationsPushBack = true;
        JCTree.JCExpression type = parseType(z);
        this.permitTypeAnnotationsPushBack = false;
        Tokens.TokenKind tokenKind = this.token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.ELLIPSIS;
        List<JCTree.JCAnnotation> list = this.typeAnnotationsPushedBack;
        if (tokenKind == tokenKind2) {
            this.typeAnnotationsPushedBack = List.nil();
            jCModifiersOptFinal.flags |= Flags.VARARGS;
            type = insertAnnotationsToMostInner(type, list, true);
            nextToken();
        } else {
            if (list.nonEmpty()) {
                reportSyntaxError(this.typeAnnotationsPushedBack.head.pos, CompilerProperties.Errors.IllegalStartOfType);
            }
            this.typeAnnotationsPushedBack = List.nil();
        }
        return variableDeclaratorId(jCModifiersOptFinal, type, false, z, z2);
    }

    public List<JCTree.JCVariableDecl> formalParameters(boolean z, boolean z2) {
        ListBuffer listBuffer = new ListBuffer();
        accept(Tokens.TokenKind.LPAREN);
        if (this.token.kind != Tokens.TokenKind.RPAREN) {
            this.allowThisIdent = (z || z2) ? false : true;
            JCTree.JCVariableDecl jCVariableDeclFormalParameter = formalParameter(z, z2);
            if (jCVariableDeclFormalParameter.nameexpr != null) {
                this.receiverParam = jCVariableDeclFormalParameter;
            } else {
                listBuffer.append(jCVariableDeclFormalParameter);
            }
            this.allowThisIdent = false;
            while (this.token.kind == Tokens.TokenKind.COMMA) {
                if ((jCVariableDeclFormalParameter.mods.flags & Flags.VARARGS) != 0) {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, jCVariableDeclFormalParameter, CompilerProperties.Errors.VarargsMustBeLast);
                }
                nextToken();
                jCVariableDeclFormalParameter = formalParameter(z, z2);
                listBuffer.append(jCVariableDeclFormalParameter);
            }
        }
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.RPAREN;
        if (tokenKind == tokenKind2) {
            nextToken();
        } else {
            setErrorEndPos(token.pos);
            reportSyntaxError(this.S.prevToken().endPos, CompilerProperties.Errors.Expected3(Tokens.TokenKind.COMMA, tokenKind2, Tokens.TokenKind.LBRACKET));
        }
        return listBuffer.toList();
    }

    public int getEndPos(JCTree jCTree) {
        return this.endPosTable.getEndPos(jCTree);
    }

    public int getStartPos(JCTree jCTree) {
        return TreeInfo.getStartPos(jCTree);
    }

    public Name ident(boolean z, boolean z2) {
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.IDENTIFIER;
        if (tokenKind == tokenKind2) {
            Name name = token.name();
            nextToken();
            return name;
        }
        if (tokenKind == Tokens.TokenKind.ASSERT) {
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, token.pos, CompilerProperties.Errors.AssertAsIdentifier);
            nextToken();
            return this.names.error;
        }
        if (tokenKind == Tokens.TokenKind.ENUM) {
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, token.pos, CompilerProperties.Errors.EnumAsIdentifier);
            nextToken();
            return this.names.error;
        }
        if (tokenKind == Tokens.TokenKind.THIS) {
            if (this.allowThisIdent) {
                Name name2 = token.name();
                nextToken();
                return name2;
            }
            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, token.pos, CompilerProperties.Errors.ThisAsIdentifier);
            nextToken();
            return this.names.error;
        }
        if (tokenKind != Tokens.TokenKind.UNDERSCORE) {
            accept(tokenKind2);
            if (!z || this.token.kind != Tokens.TokenKind.CLASS) {
                return this.names.error;
            }
            nextToken();
            return this.names._class;
        }
        if (Source.Feature.UNDERSCORE_IDENTIFIER.allowedInSource(this.source)) {
            this.log.warning(this.token.pos, CompilerProperties.Warnings.UnderscoreAsIdentifier);
        } else if (z2) {
            checkSourceLevel(Source.Feature.UNNAMED_VARIABLES);
            if (peekToken(Tokens.TokenKind.LBRACKET)) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.UseOfUnderscoreNotAllowedWithBrackets);
            }
        } else {
            boolean zAllowedInSource = Source.Feature.UNNAMED_VARIABLES.allowedInSource(this.source);
            Log log = this.log;
            if (zAllowedInSource) {
                log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.UseOfUnderscoreNotAllowedNonVariable);
            } else {
                log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.UnderscoreAsIdentifier);
            }
        }
        Name name3 = this.token.name();
        nextToken();
        return name3;
    }

    public Name identOrUnderscore() {
        return ident(false, true);
    }

    public JCTree.JCExpression illegal(int i) {
        setErrorEndPos(i);
        return isMode(1) ? syntaxError(i, CompilerProperties.Errors.IllegalStartOfExpr) : syntaxError(i, CompilerProperties.Errors.IllegalStartOfType);
    }

    public JCTree.JCVariableDecl implicitParameter() {
        return variableDeclaratorId(this.F.at(this.token.pos).Modifiers(8589934592L), null, false, true, false);
    }

    public List<JCTree.JCVariableDecl> implicitParameters(boolean z) {
        if (z) {
            accept(Tokens.TokenKind.LPAREN);
        }
        ListBuffer listBuffer = new ListBuffer();
        Tokens.TokenKind tokenKind = this.token.kind;
        if (tokenKind != Tokens.TokenKind.RPAREN && tokenKind != Tokens.TokenKind.ARROW) {
            listBuffer.append(implicitParameter());
            while (this.token.kind == Tokens.TokenKind.COMMA) {
                nextToken();
                listBuffer.append(implicitParameter());
            }
        }
        if (z) {
            accept(Tokens.TokenKind.RPAREN);
        }
        return listBuffer.toList();
    }

    public JCTree importDeclaration() {
        boolean z;
        Tokens.TokenKind tokenKind;
        int i = this.token.pos;
        nextToken();
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind2 = token.kind;
        if (tokenKind2 == Tokens.TokenKind.STATIC) {
            nextToken();
            z = true;
        } else {
            Tokens.TokenKind tokenKind3 = Tokens.TokenKind.IDENTIFIER;
            if (tokenKind2 == tokenKind3 && token.name() == this.names.module && peekToken(tokenKind3)) {
                checkSourceLevel(Source.Feature.MODULE_IMPORTS);
                nextToken();
                JCTree.JCExpression jCExpressionQualident = qualident(false);
                accept(Tokens.TokenKind.SEMI);
                return toP(this.F.at(i).ModuleImport(jCExpressionQualident));
            }
            z = false;
        }
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) toP(this.F.at(this.token.pos).Ident(ident()));
        do {
            int i2 = this.token.pos;
            tokenKind = Tokens.TokenKind.DOT;
            accept(tokenKind);
            Tokens.TokenKind tokenKind4 = this.token.kind;
            Tokens.TokenKind tokenKind5 = Tokens.TokenKind.STAR;
            TreeMaker treeMaker = this.F;
            if (tokenKind4 == tokenKind5) {
                jCExpression = (JCTree.JCExpression) to(treeMaker.at(i2).Select(jCExpression, this.names.asterisk));
                nextToken();
                break;
            }
            jCExpression = (JCTree.JCExpression) toP(treeMaker.at(i2).Select(jCExpression, ident()));
        } while (this.token.kind == tokenKind);
        accept(Tokens.TokenKind.SEMI);
        return toP(this.F.at(i).Import((JCTree.JCFieldAccess) jCExpression, z));
    }

    public JCTree.JCExpression innerCreator(int i, List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression) {
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
        JCTree.JCExpression jCExpressionTypeArguments = (JCTree.JCExpression) toP(this.F.at(this.token.pos).Ident(ident()));
        if (listTypeAnnotationsOpt.nonEmpty()) {
            jCExpressionTypeArguments = (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt.head.pos).AnnotatedType(listTypeAnnotationsOpt, jCExpressionTypeArguments));
        }
        if (this.token.kind == Tokens.TokenKind.LT) {
            int i2 = this.mode;
            jCExpressionTypeArguments = typeArguments(jCExpressionTypeArguments, true);
            setMode(i2);
        }
        return classCreatorRest(i, jCExpression, list, jCExpressionTypeArguments);
    }

    public JCTree.JCClassDecl interfaceDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) {
        int i = this.token.pos;
        accept(Tokens.TokenKind.INTERFACE);
        Name nameTypeName = typeName();
        List<JCTree.JCTypeParameter> listTypeParametersOpt = typeParametersOpt();
        List<JCTree.JCExpression> listNil = List.nil();
        if (this.token.kind == Tokens.TokenKind.EXTENDS) {
            nextToken();
            listNil = typeList();
        }
        List<JCTree.JCExpression> list = listNil;
        List<JCTree.JCExpression> listPermitsClause = permitsClause(jCModifiers, PsiKeyword.INTERFACE);
        saveDanglingDocComments(comment);
        return (JCTree.JCClassDecl) attach((JCTree.JCClassDecl) toP(this.F.at(i).ClassDef(jCModifiers, nameTypeName, listTypeParametersOpt, null, list, listPermitsClause, classInterfaceOrRecordBody(nameTypeName, true, false))), comment);
    }

    public boolean isInvalidUnqualifiedMethodIdentifier(int i, Name name) {
        if (name != this.names.yield) {
            return false;
        }
        if (this.allowYieldStatement) {
            return true;
        }
        this.log.warning(i, CompilerProperties.Warnings.InvalidYield);
        return false;
    }

    public boolean isMode(int i) {
        return (this.mode & i) != 0;
    }

    public boolean isNonSealedClassStart(boolean z) {
        if (isNonSealedIdentifier(this.token, 0)) {
            return allowedAfterSealedOrNonSealed(this.S.token(3), z, true);
        }
        return false;
    }

    public boolean isNonSealedIdentifier(Tokens.Token token, int i) {
        if (token.name() != this.names.non || !peekToken(i, Tokens.TokenKind.SUB, Tokens.TokenKind.IDENTIFIER)) {
            return false;
        }
        Tokens.Token token2 = this.S.token(i + 1);
        Tokens.Token token3 = this.S.token(i + 2);
        if (token.endPos != token2.pos || token2.endPos != token3.pos || token3.name() != this.names.sealed) {
            return false;
        }
        checkSourceLevel(Source.Feature.SEALED_CLASSES);
        return true;
    }

    public boolean isRecordStart() {
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.IDENTIFIER;
        if (tokenKind != tokenKind2 || token.name() != this.names.record || !peekToken(tokenKind2)) {
            return false;
        }
        checkSourceLevel(Source.Feature.RECORDS);
        return true;
    }

    public boolean isSealedClassStart(boolean z) {
        if (this.token.name() != this.names.sealed || !allowedAfterSealedOrNonSealed(this.S.token(1), z, false)) {
            return false;
        }
        checkSourceLevel(Source.Feature.SEALED_CLASSES);
        return true;
    }

    public boolean isUnboundMemberRef() {
        Tokens.Token token = this.S.token(0);
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[token.kind.ordinal()];
            if (i3 != 5 && i3 != 49 && i3 != 77 && i3 != 31 && i3 != 32) {
                switch (i3) {
                    case 21:
                        i++;
                        break;
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                        continue;
                    default:
                        switch (i3) {
                            case 73:
                            case 75:
                                continue;
                            case 74:
                                int i4 = 0;
                                while (true) {
                                    int i5 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.S.token(i2).kind.ordinal()];
                                    if (i5 == 6) {
                                        return false;
                                    }
                                    if (i5 == 74) {
                                        i4++;
                                    } else if (i5 == 85 && (i4 = i4 - 1) == 0) {
                                    }
                                    i2++;
                                    break;
                                }
                                break;
                            default:
                                switch (i3) {
                                    case 86:
                                    case 87:
                                    case 88:
                                        break;
                                    case 89:
                                        i--;
                                    case 90:
                                        i--;
                                    case 91:
                                        i--;
                                        if (i == 0) {
                                            Tokens.TokenKind tokenKind = this.S.token(i2 + 1).kind;
                                            return tokenKind == Tokens.TokenKind.DOT || tokenKind == Tokens.TokenKind.LBRACKET || tokenKind == Tokens.TokenKind.COLCOL;
                                        }
                                        break;
                                    default:
                                        return false;
                                }
                                break;
                        }
                        break;
                }
            }
            i2++;
            token = this.S.token(i2);
        }
    }

    public boolean isZero(String str) {
        char c;
        char[] charArray = str.toCharArray();
        int i = (charArray.length <= 1 || Character.toLowerCase(charArray[1]) != 'x') ? 10 : 16;
        int i2 = i == 16 ? 2 : 0;
        while (i2 < charArray.length && ((c = charArray[i2]) == '0' || c == '.')) {
            i2++;
        }
        return i2 >= charArray.length || Character.digit(charArray[i2], i) <= 0;
    }

    public JCTree.JCExpression lambdaExpression(List<JCTree.JCVariableDecl> list, int i) {
        return (JCTree.JCExpression) toP(this.F.at(i).Lambda(list, parseExpression()));
    }

    public JCTree.JCExpression lambdaExpressionOrStatement(boolean z, boolean z2, int i) {
        Name nameRestrictedTypeName;
        List<JCTree.JCVariableDecl> listFormalParameters = z2 ? formalParameters(true, false) : implicitParameters(z);
        if (z2) {
            LambdaClassifier lambdaClassifier = new LambdaClassifier();
            for (JCTree.JCVariableDecl jCVariableDecl : listFormalParameters) {
                JCTree.JCExpression jCExpression = jCVariableDecl.vartype;
                if (jCExpression != null && (nameRestrictedTypeName = restrictedTypeName(jCExpression, false)) != null && jCVariableDecl.vartype.hasTag(JCTree.Tag.TYPEARRAY)) {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, jCVariableDecl.pos, Source.Feature.VAR_SYNTAX_IMPLICIT_LAMBDAS.allowedInSource(this.source) ? CompilerProperties.Errors.RestrictedTypeNotAllowedArray(nameRestrictedTypeName) : CompilerProperties.Errors.RestrictedTypeNotAllowedHere(nameRestrictedTypeName));
                }
                lambdaClassifier.addParameter(jCVariableDecl);
                if (lambdaClassifier.result() == LambdaParameterKind.ERROR) {
                    break;
                }
            }
            JCDiagnostic.Fragment fragment = lambdaClassifier.diagFragment;
            if (fragment != null) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i, CompilerProperties.Errors.InvalidLambdaParameterDeclaration(fragment));
            }
            for (JCTree.JCVariableDecl jCVariableDecl2 : listFormalParameters) {
                JCTree.JCExpression jCExpression2 = jCVariableDecl2.vartype;
                if (jCExpression2 != null && restrictedTypeName(jCExpression2, true) != null) {
                    checkSourceLevel(jCVariableDecl2.pos, Source.Feature.VAR_SYNTAX_IMPLICIT_LAMBDAS);
                    jCVariableDecl2.declKind = JCTree.JCVariableDecl.DeclKind.VAR;
                    jCVariableDecl2.typePos = TreeInfo.getStartPos(jCVariableDecl2.vartype);
                    jCVariableDecl2.vartype = null;
                }
            }
        }
        return lambdaExpressionOrStatementRest(listFormalParameters, i);
    }

    public JCTree.JCExpression lambdaExpressionOrStatementRest(List<JCTree.JCVariableDecl> list, int i) {
        accept(Tokens.TokenKind.ARROW);
        Tokens.Token token = this.token;
        return token.kind == Tokens.TokenKind.LBRACE ? lambdaStatement(list, i, token.pos) : lambdaExpression(list, i);
    }

    public JCTree.JCExpression lambdaStatement(List<JCTree.JCVariableDecl> list, int i, int i2) {
        return (JCTree.JCExpression) toP(this.F.at(i).Lambda(list, block(i2, 0L)));
    }

    public JCTree.JCExpression literal(Name name, int i) {
        Float fValueOf;
        Double dValueOf;
        JCTree.JCExpression jCExpressionLiteral = this.errorTree;
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()]) {
            case 52:
                try {
                    jCExpressionLiteral = this.F.at(i).Literal(TypeTag.INT, Integer.valueOf(Convert.string2int(strval(name), this.token.radix())));
                } catch (NumberFormatException unused) {
                    reportIntegralLiteralError(name, i);
                }
                break;
            case 53:
                try {
                    jCExpressionLiteral = this.F.at(i).Literal(TypeTag.LONG, Long.valueOf(Convert.string2long(strval(name), this.token.radix())));
                } catch (NumberFormatException unused2) {
                    reportIntegralLiteralError(name, i);
                }
                break;
            case 54:
                int iRadix = this.token.radix();
                Tokens.Token token = this.token;
                String strStringVal = iRadix == 16 ? "0x" + token.stringVal() : token.stringVal();
                try {
                    fValueOf = Float.valueOf(strStringVal);
                } catch (NumberFormatException unused3) {
                    fValueOf = Float.valueOf(Float.NaN);
                }
                if (fValueOf.floatValue() == 0.0f && !isZero(strStringVal)) {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.FpNumberTooSmall);
                } else if (fValueOf.floatValue() != Float.POSITIVE_INFINITY) {
                    jCExpressionLiteral = this.F.at(i).Literal(TypeTag.FLOAT, fValueOf);
                } else {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.FpNumberTooLarge);
                }
                break;
            case 55:
                int iRadix2 = this.token.radix();
                Tokens.Token token2 = this.token;
                String strStringVal2 = iRadix2 == 16 ? "0x" + token2.stringVal() : token2.stringVal();
                try {
                    dValueOf = Double.valueOf(strStringVal2);
                } catch (NumberFormatException unused4) {
                    dValueOf = Double.valueOf(Double.NaN);
                }
                if (dValueOf.doubleValue() == XPath.MATCH_SCORE_QNAME && !isZero(strStringVal2)) {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.FpNumberTooSmall);
                } else if (dValueOf.doubleValue() != Double.POSITIVE_INFINITY) {
                    jCExpressionLiteral = this.F.at(i).Literal(TypeTag.DOUBLE, dValueOf);
                } else {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.FpNumberTooLarge);
                }
                break;
            case 56:
                jCExpressionLiteral = this.F.at(i).Literal(TypeTag.CHAR, Integer.valueOf(this.token.stringVal().charAt(0)));
                break;
            case 57:
                jCExpressionLiteral = this.F.at(i).Literal(TypeTag.CLASS, this.token.stringVal());
                break;
            case 58:
            case 59:
                jCExpressionLiteral = this.F.at(i).Literal(TypeTag.BOOLEAN, Integer.valueOf(this.token.kind == Tokens.TokenKind.TRUE ? 1 : 0));
                break;
            case 60:
                jCExpressionLiteral = this.F.at(i).Literal(TypeTag.BOT, null);
                break;
            default:
                Assert.error();
                break;
        }
        if (jCExpressionLiteral == this.errorTree) {
            jCExpressionLiteral = this.F.at(i).Erroneous();
        }
        storeEnd(jCExpressionLiteral, this.token.endPos);
        nextToken();
        return jCExpressionLiteral;
    }

    public JCTree.JCExpression memberReferenceSuffix(int i, JCTree.JCExpression jCExpression) {
        MemberReferenceTree.ReferenceMode referenceMode;
        Name nameIdent;
        selectExprMode();
        List<JCTree.JCExpression> listTypeArguments = this.token.kind == Tokens.TokenKind.LT ? typeArguments(false) : null;
        if (this.token.kind == Tokens.TokenKind.NEW) {
            referenceMode = MemberReferenceTree.ReferenceMode.NEW;
            nameIdent = this.names.init;
            nextToken();
        } else {
            referenceMode = MemberReferenceTree.ReferenceMode.INVOKE;
            nameIdent = ident();
        }
        return (JCTree.JCExpression) toP(this.F.at(jCExpression.getStartPosition()).Reference(referenceMode, nameIdent, jCExpression, listTypeArguments));
    }

    public boolean merge(ListBuffer<JCTree.JCLiteral> listBuffer, ListBuffer<JCTree.JCExpression> listBuffer2) {
        if (listBuffer.isEmpty()) {
            return false;
        }
        if (listBuffer.size() == 1) {
            listBuffer2.prepend(listBuffer.first());
            return false;
        }
        JCTree.JCLiteral jCLiteralLiteral = this.F.at(listBuffer.first().getStartPosition()).Literal(TypeTag.CLASS, listBuffer.stream().map(new Function() { // from class: qm7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return JavacParser.b((JCTree.JCLiteral) obj);
            }
        }).collect(Collectors.joining()));
        storeEnd(jCLiteralLiteral, listBuffer.last().getEndPosition(this.endPosTable));
        listBuffer2.prepend(jCLiteralLiteral);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0072 A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:8:0x0017, B:10:0x0024, B:12:0x002c, B:29:0x0067, B:31:0x0072, B:42:0x00ab, B:32:0x0079, B:34:0x007d, B:36:0x0095, B:38:0x009f, B:40:0x00a5, B:35:0x008a, B:19:0x003d, B:23:0x004f, B:25:0x0056, B:27:0x005e), top: B:47:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0079 A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:8:0x0017, B:10:0x0024, B:12:0x002c, B:29:0x0067, B:31:0x0072, B:42:0x00ab, B:32:0x0079, B:34:0x007d, B:36:0x0095, B:38:0x009f, B:40:0x00a5, B:35:0x008a, B:19:0x003d, B:23:0x004f, B:25:0x0056, B:27:0x005e), top: B:47:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x007d A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:8:0x0017, B:10:0x0024, B:12:0x002c, B:29:0x0067, B:31:0x0072, B:42:0x00ab, B:32:0x0079, B:34:0x007d, B:36:0x0095, B:38:0x009f, B:40:0x00a5, B:35:0x008a, B:19:0x003d, B:23:0x004f, B:25:0x0056, B:27:0x005e), top: B:47:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x008a A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:8:0x0017, B:10:0x0024, B:12:0x002c, B:29:0x0067, B:31:0x0072, B:42:0x00ab, B:32:0x0079, B:34:0x007d, B:36:0x0095, B:38:0x009f, B:40:0x00a5, B:35:0x008a, B:19:0x003d, B:23:0x004f, B:25:0x0056, B:27:0x005e), top: B:47:0x0017 }] */
    public JCTree methodDeclaratorRest(int i, JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, Name name, List<JCTree.JCTypeParameter> list, boolean z, boolean z2, boolean z3, Tokens.Comment comment) {
        Name name2;
        List<JCTree.JCExpression> list2;
        JCTree.JCExpression jCExpression2;
        Tokens.TokenKind tokenKind;
        Tokens.TokenKind tokenKind2;
        JCTree.JCExpression jCExpressionAnnotationValue;
        JCTree.JCBlock jCBlockBlock;
        JCTree.JCExpression jCExpression3;
        if (z && (jCModifiers.flags & 2) != 0) {
            checkSourceLevel(Source.Feature.PRIVATE_INTERFACE_METHODS);
        }
        JCTree.JCVariableDecl jCVariableDecl = this.receiverParam;
        JCTree.JCBlock jCBlockBlock2 = null;
        try {
            this.receiverParam = null;
            List<JCTree.JCVariableDecl> listNil = List.nil();
            List<JCTree.JCExpression> listNil2 = List.nil();
            boolean z4 = false;
            if (z3) {
                name2 = name;
                if (name2 == this.names.init && this.token.kind != Tokens.TokenKind.LPAREN) {
                    list2 = listNil2;
                    jCExpression2 = jCExpression;
                }
                saveDanglingDocComments(comment);
                tokenKind = this.token.kind;
                if (tokenKind == Tokens.TokenKind.LBRACE) {
                    jCExpression3 = null;
                    jCBlockBlock = block();
                } else {
                    tokenKind2 = Tokens.TokenKind.DEFAULT;
                    if (tokenKind == tokenKind2) {
                        accept(tokenKind2);
                        jCExpressionAnnotationValue = annotationValue();
                        accept(Tokens.TokenKind.SEMI);
                    } else {
                        accept(Tokens.TokenKind.SEMI, new Function() { // from class: tm7
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return CompilerProperties.Errors.Expected2(Tokens.TokenKind.LBRACE, Tokens.TokenKind.SEMI);
                            }
                        });
                        jCExpressionAnnotationValue = null;
                    }
                    if (this.token.pos <= this.endPosTable.errorEndPos && openingBraceMissing(z4)) {
                        jCBlockBlock2 = block();
                    }
                    jCBlockBlock = jCBlockBlock2;
                    jCExpression3 = jCExpressionAnnotationValue;
                }
                return attach((JCTree.JCMethodDecl) toP(this.F.at(i).MethodDef(jCModifiers, name2, jCExpression2, list, this.receiverParam, listNil, list2, jCBlockBlock, jCExpression3)), comment);
            }
            name2 = name;
            listNil = formalParameters();
            z4 = this.token.pos == this.endPosTable.errorEndPos;
            JCTree.JCExpression jCExpressionBracketsOpt = !z2 ? bracketsOpt(jCExpression) : jCExpression;
            if (this.token.kind == Tokens.TokenKind.THROWS) {
                nextToken();
                listNil2 = qualidentList(true);
            }
            list2 = listNil2;
            jCExpression2 = jCExpressionBracketsOpt;
            saveDanglingDocComments(comment);
            tokenKind = this.token.kind;
            if (tokenKind == Tokens.TokenKind.LBRACE) {
                jCExpression3 = null;
                jCBlockBlock = block();
            } else {
                tokenKind2 = Tokens.TokenKind.DEFAULT;
                if (tokenKind == tokenKind2) {
                    accept(tokenKind2);
                    jCExpressionAnnotationValue = annotationValue();
                    accept(Tokens.TokenKind.SEMI);
                } else {
                    accept(Tokens.TokenKind.SEMI, new Function() { // from class: tm7
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return CompilerProperties.Errors.Expected2(Tokens.TokenKind.LBRACE, Tokens.TokenKind.SEMI);
                        }
                    });
                    jCExpressionAnnotationValue = null;
                }
                if (this.token.pos <= this.endPosTable.errorEndPos) {
                    jCBlockBlock2 = block();
                }
                jCBlockBlock = jCBlockBlock2;
                jCExpression3 = jCExpressionAnnotationValue;
            }
            return attach((JCTree.JCMethodDecl) toP(this.F.at(i).MethodDef(jCModifiers, name2, jCExpression2, list, this.receiverParam, listNil, list2, jCBlockBlock, jCExpression3)), comment);
        } finally {
            this.receiverParam = jCVariableDecl;
        }
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00d5  */
    public JCTree.JCModifiers modifiersOpt(JCTree.JCModifiers jCModifiers) {
        long j;
        int i;
        int[] iArr;
        long j2;
        JCTree.JCModifiers jCModifiersModifiers;
        long j3;
        ListBuffer listBuffer = new ListBuffer();
        if (jCModifiers == null) {
            i = this.token.pos;
            j = 0;
        } else {
            j = jCModifiers.flags;
            listBuffer.appendList(jCModifiers.annotations);
            i = jCModifiers.pos;
        }
        if (this.token.deprecatedFlag()) {
            j |= Flags.BODY_ONLY_FINALIZE;
        }
        while (true) {
            iArr = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind;
            int i2 = iArr[this.token.kind.ordinal()];
            if (i2 == 2) {
                j2 = 1;
            } else if (i2 == 3) {
                j2 = 16;
            } else if (i2 == 4) {
                j2 = 1024;
            } else if (i2 == 5) {
                j2 = 8192;
            } else if (i2 != 32) {
                if (i2 == 34) {
                    j2 = Flags.DEFAULT;
                } else if (i2 != 95) {
                    switch (i2) {
                        case 13:
                            j2 = 2;
                            break;
                        case 14:
                            j2 = 4;
                            break;
                        case 15:
                            j2 = 8;
                            break;
                        case 16:
                            j2 = 128;
                            break;
                        case 17:
                            j2 = 256;
                            break;
                        case 18:
                            j2 = 64;
                            break;
                        case 19:
                            j2 = 32;
                            break;
                        case 20:
                            j2 = 2048;
                            break;
                    }
                } else {
                    nextToken();
                    j2 = 0;
                }
            } else if (isNonSealedClassStart(false)) {
                nextToken();
                nextToken();
                j2 = Long.MIN_VALUE;
            } else if (isSealedClassStart(false)) {
                checkSourceLevel(Source.Feature.SEALED_CLASSES);
                j2 = Flags.SEALED;
            }
            if ((j & j2) != 0) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.RepeatedModifier);
            }
            int i3 = this.token.pos;
            nextToken();
            if (j2 != 8192 || this.token.kind == Tokens.TokenKind.INTERFACE) {
                j |= j2;
            } else {
                JCTree.JCAnnotation jCAnnotationAnnotation = annotation(i3, JCTree.Tag.ANNOTATION);
                if (j == 0 && listBuffer.isEmpty()) {
                    i = jCAnnotationAnnotation.pos;
                }
                listBuffer.append(jCAnnotationAnnotation);
                j |= 0;
            }
        }
        int i4 = iArr[this.token.kind.ordinal()];
        if (i4 != 8) {
            j3 = i4 == 9 ? 16384L : 512L;
            if (((-9223081765785031169L) & j) == 0 && listBuffer.isEmpty()) {
                i = -1;
            }
            jCModifiersModifiers = this.F.at(i).Modifiers(j, listBuffer.toList());
            if (i != -1) {
                storeEnd(jCModifiersModifiers, this.S.prevToken().endPos);
            }
            return jCModifiersModifiers;
        }
        j |= j3;
        if (((-9223081765785031169L) & j) == 0) {
            i = -1;
        }
        jCModifiersModifiers = this.F.at(i).Modifiers(j, listBuffer.toList());
        if (i != -1) {
            storeEnd(jCModifiersModifiers, this.S.prevToken().endPos);
        }
        return jCModifiersModifiers;
    }

    public JCTree.JCModuleDecl moduleDecl(JCTree.JCModifiers jCModifiers, ModuleTree.ModuleKind moduleKind, Tokens.Comment comment) {
        int i = this.token.pos;
        checkSourceLevel(Source.Feature.MODULES);
        nextToken();
        JCTree.JCExpression jCExpressionQualident = qualident(false);
        accept(Tokens.TokenKind.LBRACE);
        List<JCTree.JCDirective> listModuleDirectiveList = moduleDirectiveList();
        accept(Tokens.TokenKind.RBRACE);
        int i2 = this.S.prevToken().endPos;
        accept(Tokens.TokenKind.EOF);
        JCTree.JCModuleDecl jCModuleDeclModuleDef = this.F.at(i).ModuleDef(jCModifiers, moduleKind, jCExpressionQualident, listModuleDirectiveList);
        storeEnd(jCModuleDeclModuleDef, i2);
        return (JCTree.JCModuleDecl) attach(jCModuleDeclModuleDef, comment);
    }

    public List<JCTree.JCDirective> moduleDirectiveList() {
        Tokens.TokenKind tokenKind;
        List<JCTree.JCExpression> listQualidentList;
        List<JCTree.JCExpression> listNil;
        ListBuffer listBuffer = new ListBuffer();
        while (true) {
            Tokens.Token token = this.token;
            Tokens.TokenKind tokenKind2 = token.kind;
            Tokens.TokenKind tokenKind3 = Tokens.TokenKind.IDENTIFIER;
            if (tokenKind2 != tokenKind3) {
                break;
            }
            int i = token.pos;
            if (token.name() != this.names.requires) {
                if (this.token.name() != this.names.exports && this.token.name() != this.names.opens) {
                    if (this.token.name() != this.names.provides) {
                        if (this.token.name() != this.names.uses) {
                            setErrorEndPos(i);
                            reportSyntaxError(i, CompilerProperties.Errors.InvalidModuleDirective);
                            break;
                        }
                        nextToken();
                        JCTree.JCExpression jCExpressionQualident = qualident(false);
                        accept(Tokens.TokenKind.SEMI);
                        listBuffer.append((JCTree.JCDirective) toP(this.F.at(i).Uses(jCExpressionQualident)));
                    } else {
                        nextToken();
                        JCTree.JCExpression jCExpressionQualident2 = qualident(false);
                        Tokens.Token token2 = this.token;
                        if (token2.kind == tokenKind3 && token2.name() == this.names.with) {
                            nextToken();
                            listNil = qualidentList(false);
                        } else {
                            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.ExpectedStr("'" + this.names.with + "'"));
                            listNil = List.nil();
                        }
                        accept(Tokens.TokenKind.SEMI);
                        listBuffer.append((JCTree.JCDirective) toP(this.F.at(i).Provides(jCExpressionQualident2, listNil)));
                    }
                } else {
                    boolean z = this.token.name() == this.names.exports;
                    nextToken();
                    JCTree.JCExpression jCExpressionQualident3 = qualident(false);
                    Tokens.Token token3 = this.token;
                    if (token3.kind == tokenKind3 && token3.name() == this.names.to) {
                        nextToken();
                        listQualidentList = qualidentList(false);
                    } else {
                        listQualidentList = null;
                    }
                    accept(Tokens.TokenKind.SEMI);
                    TreeMaker treeMaker = this.F;
                    listBuffer.append((JCTree.JCDirective) toP(z ? treeMaker.at(i).Exports(jCExpressionQualident3, listQualidentList) : treeMaker.at(i).Opens(jCExpressionQualident3, listQualidentList)));
                }
            } else {
                nextToken();
                boolean z2 = false;
                boolean z3 = false;
                while (true) {
                    int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()];
                    if (i2 == 15) {
                        if (z2) {
                            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.RepeatedModifier);
                        }
                        z2 = true;
                    } else {
                        if (i2 != 32 || this.token.name() != this.names.transitive || (tokenKind = this.S.token(1).kind) == Tokens.TokenKind.SEMI || tokenKind == Tokens.TokenKind.DOT) {
                            break;
                        }
                        if (z3) {
                            this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.RepeatedModifier);
                        }
                        z3 = true;
                    }
                    nextToken();
                }
                JCTree.JCExpression jCExpressionQualident4 = qualident(false);
                accept(Tokens.TokenKind.SEMI);
                listBuffer.append((JCTree.JCDirective) toP(this.F.at(i).Requires(z3, z2, jCExpressionQualident4)));
            }
        }
        return listBuffer.toList();
    }

    public <T extends ListBuffer<? super JCTree.JCExpressionStatement>> T moreStatementExpressions(int i, JCTree.JCExpression jCExpression, T t) {
        t.append(toP(this.F.at(i).Exec(checkExprStat(jCExpression))));
        while (this.token.kind == Tokens.TokenKind.COMMA) {
            nextToken();
            t.append(toP(this.F.at(this.token.pos).Exec(checkExprStat(parseExpression()))));
        }
        return t;
    }

    public DocCommentTable newDocCommentTable(boolean z, ParserFactory parserFactory) {
        if (z) {
            return new LazyDocCommentTable(parserFactory);
        }
        return null;
    }

    public AbstractEndPosTable newEndPosTable(boolean z) {
        return z ? new SimpleEndPosTable() : new MinimalEndPosTable();
    }

    public void nextToken() {
        this.S.nextToken();
        this.token = this.S.token();
    }

    public JCTree.JCModifiers optFinal(long j) {
        JCTree.JCModifiers jCModifiersModifiersOpt = modifiersOpt();
        checkNoMods(jCModifiersModifiersOpt.flags & (-131089));
        jCModifiersModifiersOpt.flags |= j;
        return jCModifiersModifiersOpt;
    }

    public JCTree.JCExpression parExpression() {
        int i = this.token.pos;
        accept(Tokens.TokenKind.LPAREN);
        JCTree.JCExpression expression = parseExpression();
        accept(Tokens.TokenKind.RPAREN);
        return (JCTree.JCExpression) toP(this.F.at(i).Parens(expression));
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0083  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:23:0x0088
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:590)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:82)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // com.sun.tools.javac.parser.Parser
    public com.sun.tools.javac.tree.JCTree.JCCompilationUnit parseCompilationUnit() {
        /*
            Method dump skipped, instruction units count: 596
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.javac.parser.JavacParser.parseCompilationUnit():com.sun.tools.javac.tree.JCTree$JCCompilationUnit");
    }

    @Override // com.sun.tools.javac.parser.Parser
    public JCTree.JCExpression parseExpression() {
        return term(1);
    }

    public JCTree.JCExpression parseIntersectionType(int i, JCTree.JCExpression jCExpression) {
        List listOf = List.of(jCExpression);
        while (true) {
            Tokens.TokenKind tokenKind = this.token.kind;
            Tokens.TokenKind tokenKind2 = Tokens.TokenKind.AMP;
            if (tokenKind != tokenKind2) {
                break;
            }
            accept(tokenKind2);
            listOf = listOf.prepend(parseType());
        }
        return listOf.length() > 1 ? (JCTree.JCExpression) toP(this.F.at(i).TypeIntersection(listOf.reverse())) : jCExpression;
    }

    public JCTree.JCPattern parsePattern(int i, JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, boolean z, boolean z2) {
        int i2;
        JavacParser javacParser;
        int i3;
        if (jCModifiers == null) {
            jCModifiers = optFinal(0L);
        }
        JCTree.JCModifiers jCModifiers2 = jCModifiers;
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        if (tokenKind == Tokens.TokenKind.UNDERSCORE && jCExpression == null) {
            nextToken();
            checkSourceLevel(Source.Feature.UNNAMED_VARIABLES);
            return (JCTree.JCPattern) toP(this.F.at(this.token.pos).AnyPattern());
        }
        if (jCExpression == null) {
            boolean z3 = tokenKind == Tokens.TokenKind.IDENTIFIER && token.name() == this.names.var;
            jCExpression = unannotatedType(z, 34);
            if (z3) {
                i3 = jCExpression.pos;
                jCExpression = null;
            } else {
                i3 = -1;
            }
            i2 = i3;
        } else {
            i2 = -1;
        }
        JCTree.JCExpression jCExpression2 = jCExpression;
        Tokens.Token token2 = this.token;
        if (token2.kind != Tokens.TokenKind.LPAREN) {
            int i4 = token2.pos;
            Name nameIdentOrUnderscore = identOrUnderscore();
            if (Source.Feature.UNNAMED_VARIABLES.allowedInSource(this.source)) {
                Names names = this.names;
                if (nameIdentOrUnderscore == names.underscore) {
                    nameIdentOrUnderscore = names.empty;
                }
            }
            JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) toP(this.F.at(i4).VarDef(jCModifiers2, nameIdentOrUnderscore, jCExpression2, null, i2 != -1 ? JCTree.JCVariableDecl.DeclKind.VAR : JCTree.JCVariableDecl.DeclKind.EXPLICIT, i2));
            if (jCExpression2 == null && jCVariableDecl.name == this.names.underscore && !z) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i4, CompilerProperties.Errors.UseOfUnderscoreNotAllowed);
            }
            return (JCTree.JCPattern) toP(this.F.at(i).BindingPattern(jCVariableDecl));
        }
        checkSourceLevel(Source.Feature.RECORD_PATTERNS);
        ListBuffer listBuffer = new ListBuffer();
        if (peekToken(Tokens.TokenKind.RPAREN)) {
            javacParser = this;
            javacParser.nextToken();
        } else {
            while (true) {
                this.nextToken();
                JavacParser javacParser2 = this;
                javacParser = javacParser2;
                listBuffer.append(javacParser2.parsePattern(this.token.pos, null, null, true, false));
                if (javacParser.token.kind != Tokens.TokenKind.COMMA) {
                    break;
                }
                this = javacParser;
            }
        }
        javacParser.accept(Tokens.TokenKind.RPAREN);
        JCTree.JCPattern jCPattern = (JCTree.JCPattern) javacParser.toP(javacParser.F.at(i).RecordPattern(jCExpression2, listBuffer.toList()));
        if (jCModifiers2.annotations.nonEmpty()) {
            javacParser.log.error(jCModifiers2.annotations.head.pos(), CompilerProperties.Errors.RecordPatternsAnnotationsNotAllowed);
        }
        javacParser.checkNoMods(i, jCModifiers2.flags & 16);
        new TreeScanner() { // from class: com.sun.tools.javac.parser.JavacParser.1
            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
                JavacParser.this.log.error(jCAnnotatedType.pos(), CompilerProperties.Errors.RecordPatternsAnnotationsNotAllowed);
            }
        }.scan(jCExpression2);
        return jCPattern;
    }

    public JCTree.JCStatement parseSimpleStatement() {
        ignoreDanglingComments();
        Tokens.Token token = this.token;
        int i = token.pos;
        int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[token.kind.ordinal()];
        if (i2 == 1) {
            nextToken();
            return (JCTree.JCStatement) toP(this.F.at(i).Skip());
        }
        if (i2 == 11) {
            return block();
        }
        if (i2 == 19) {
            nextToken();
            return this.F.at(i).Synchronized(parExpression(), block());
        }
        JCTree.JCExpression expression = null;
        jCBlockBlock = null;
        jCBlockBlock = null;
        JCTree.JCBlock jCBlockBlock = null;
        JCTree.JCStatement statementAsBlock = null;
        if (i2 == 51) {
            nextToken();
            JCTree.JCExpression expression2 = parseExpression();
            if (this.token.kind == Tokens.TokenKind.COLON) {
                nextToken();
                expression = parseExpression();
            }
            accept(Tokens.TokenKind.SEMI);
            return (JCTree.JCAssert) toP(this.F.at(i).Assert(expression2, expression));
        }
        switch (i2) {
            case 35:
                nextToken();
                JCTree.JCExpression jCExpressionParExpression = parExpression();
                JCTree.JCStatement statementAsBlock2 = parseStatementAsBlock();
                if (this.token.kind == Tokens.TokenKind.ELSE) {
                    nextToken();
                    statementAsBlock = parseStatementAsBlock();
                }
                return this.F.at(i).If(jCExpressionParExpression, statementAsBlock2, statementAsBlock);
            case 36:
                nextToken();
                accept(Tokens.TokenKind.LPAREN);
                Tokens.TokenKind tokenKind = this.token.kind;
                Tokens.TokenKind tokenKind2 = Tokens.TokenKind.SEMI;
                List<JCTree.JCStatement> listNil = tokenKind == tokenKind2 ? List.nil() : forInit();
                if (listNil.length() == 1 && listNil.head.hasTag(JCTree.Tag.VARDEF)) {
                    JCTree.JCStatement jCStatement = listNil.head;
                    if (((JCTree.JCVariableDecl) jCStatement).init == null) {
                        Tokens.TokenKind tokenKind3 = this.token.kind;
                        Tokens.TokenKind tokenKind4 = Tokens.TokenKind.COLON;
                        if (tokenKind3 == tokenKind4) {
                            accept(tokenKind4);
                            JCTree.JCExpression expression3 = parseExpression();
                            accept(Tokens.TokenKind.RPAREN);
                            return this.F.at(i).ForeachLoop((JCTree.JCVariableDecl) jCStatement, expression3, parseStatementAsBlock());
                        }
                    }
                }
                accept(tokenKind2);
                JCTree.JCExpression expression4 = this.token.kind != tokenKind2 ? parseExpression() : null;
                accept(tokenKind2);
                Tokens.TokenKind tokenKind5 = this.token.kind;
                Tokens.TokenKind tokenKind6 = Tokens.TokenKind.RPAREN;
                List<JCTree.JCExpressionStatement> listNil2 = tokenKind5 == tokenKind6 ? List.nil() : forUpdate();
                accept(tokenKind6);
                return this.F.at(i).ForLoop(listNil, expression4, listNil2, parseStatementAsBlock());
            case 37:
                nextToken();
                return this.F.at(i).WhileLoop(parExpression(), parseStatementAsBlock());
            case 38:
                nextToken();
                JCTree.JCStatement statementAsBlock3 = parseStatementAsBlock();
                accept(Tokens.TokenKind.WHILE);
                JCTree.JCExpression jCExpressionParExpression2 = parExpression();
                accept(Tokens.TokenKind.SEMI);
                return (JCTree.JCDoWhileLoop) toP(this.F.at(i).DoLoop(statementAsBlock3, jCExpressionParExpression2));
            case 39:
                nextToken();
                List<JCTree> listNil3 = List.nil();
                if (this.token.kind == Tokens.TokenKind.LPAREN) {
                    nextToken();
                    listNil3 = resources();
                    accept(Tokens.TokenKind.RPAREN);
                }
                JCTree.JCBlock jCBlockBlock2 = block();
                ListBuffer listBuffer = new ListBuffer();
                Tokens.TokenKind tokenKind7 = this.token.kind;
                if (tokenKind7 == Tokens.TokenKind.CATCH || tokenKind7 == Tokens.TokenKind.FINALLY) {
                    while (true) {
                        Tokens.TokenKind tokenKind8 = this.token.kind;
                        if (tokenKind8 == Tokens.TokenKind.CATCH) {
                            listBuffer.append(catchClause());
                        } else if (tokenKind8 == Tokens.TokenKind.FINALLY) {
                            nextToken();
                            jCBlockBlock = block();
                        }
                    }
                } else if (listNil3.isEmpty()) {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i, CompilerProperties.Errors.TryWithoutCatchFinallyOrResourceDecls);
                }
                return this.F.at(i).Try(listNil3, jCBlockBlock2, listBuffer.toList(), jCBlockBlock);
            case 40:
                nextToken();
                JCTree.JCExpression jCExpressionParExpression3 = parExpression();
                accept(Tokens.TokenKind.LBRACE);
                JCTree.JCSwitch jCSwitch = (JCTree.JCSwitch) to(this.F.at(i).Switch(jCExpressionParExpression3, switchBlockStatementGroups()));
                jCSwitch.bracePos = this.token.endPos;
                accept(Tokens.TokenKind.RBRACE);
                return jCSwitch;
            case 41:
                nextToken();
                Tokens.TokenKind tokenKind9 = this.token.kind;
                Tokens.TokenKind tokenKind10 = Tokens.TokenKind.SEMI;
                JCTree.JCExpression expression5 = tokenKind9 != tokenKind10 ? parseExpression() : null;
                accept(tokenKind10);
                return (JCTree.JCReturn) toP(this.F.at(i).Return(expression5));
            case 42:
                nextToken();
                JCTree.JCExpression expression6 = parseExpression();
                accept(Tokens.TokenKind.SEMI);
                return (JCTree.JCThrow) toP(this.F.at(i).Throw(expression6));
            case 43:
                nextToken();
                Name nameIdent = this.LAX_IDENTIFIER.test(this.token.kind) ? ident() : null;
                accept(Tokens.TokenKind.SEMI);
                return (JCTree.JCBreak) toP(this.F.at(i).Break(nameIdent));
            case 44:
                nextToken();
                Name nameIdent2 = this.LAX_IDENTIFIER.test(this.token.kind) ? ident() : null;
                accept(Tokens.TokenKind.SEMI);
                return (JCTree.JCContinue) toP(this.F.at(i).Continue(nameIdent2));
            case 45:
                int i3 = this.token.pos;
                nextToken();
                return doRecover(i3, BasicErrorRecoveryAction.BLOCK_STMT, CompilerProperties.Errors.ElseWithoutIf);
            case 46:
                int i4 = this.token.pos;
                nextToken();
                return doRecover(i4, BasicErrorRecoveryAction.BLOCK_STMT, CompilerProperties.Errors.FinallyWithoutTry);
            case 47:
                return doRecover(this.token.pos, BasicErrorRecoveryAction.CATCH_CLAUSE, CompilerProperties.Errors.CatchWithoutTry);
            default:
                Assert.error();
                return null;
        }
    }

    @Override // com.sun.tools.javac.parser.Parser
    public JCTree.JCStatement parseStatement() {
        return parseStatementAsBlock();
    }

    public JCTree.JCStatement parseStatementAsBlock() {
        JCDiagnostic.Error error;
        int i = this.token.pos;
        List<JCTree.JCStatement> listBlockStatement = blockStatement();
        if (listBlockStatement.isEmpty()) {
            return (JCTree.JCStatement) toP(this.F.at(i).Exec(syntaxError(i, CompilerProperties.Errors.IllegalStartOfStmt)));
        }
        JCTree.JCStatement jCStatement = listBlockStatement.head;
        int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCStatement.getTag().ordinal()];
        if (i2 != 3) {
            error = i2 != 4 ? null : CompilerProperties.Errors.VariableNotAllowed;
        } else {
            error = CompilerProperties.Errors.ClassNotAllowed;
        }
        if (error == null) {
            return jCStatement;
        }
        this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, jCStatement, error);
        return (JCTree.JCStatement) toP(this.F.at(i).Exec(this.F.at(jCStatement.pos).Erroneous(List.of(this.F.at(jCStatement.pos).Block(0L, listBlockStatement)))));
    }

    public JCTree.JCExpression parseType(boolean z, List<JCTree.JCAnnotation> list) {
        JCTree.JCExpression jCExpressionUnannotatedType = unannotatedType(z);
        return list.nonEmpty() ? insertAnnotationsToMostInner(jCExpressionUnannotatedType, list, false) : jCExpressionUnannotatedType;
    }

    public boolean peekToken(int i, Predicate<Tokens.TokenKind> predicate, Predicate<Tokens.TokenKind> predicate2, Predicate<Tokens.TokenKind> predicate3) {
        return predicate.test(this.S.token(i + 1).kind) && predicate2.test(this.S.token(i + 2).kind) && predicate3.test(this.S.token(i + 3).kind);
    }

    public List<JCTree.JCExpression> permitsClause(JCTree.JCModifiers jCModifiers, String str) {
        if (this.allowSealedTypes) {
            Tokens.Token token = this.token;
            if (token.kind == Tokens.TokenKind.IDENTIFIER && token.name() == this.names.permits) {
                checkSourceLevel(Source.Feature.SEALED_CLASSES);
                if ((jCModifiers.flags & Flags.SEALED) == 0) {
                    this.log.error(this.token.pos, CompilerProperties.Errors.InvalidPermitsClause(CompilerProperties.Fragments.ClassIsNotSealed(str)));
                }
                nextToken();
                return qualidentList(false);
            }
        }
        return List.nil();
    }

    public JCTree.JCExpression qualident(boolean z) {
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) toP(this.F.at(this.token.pos).Ident(ident()));
        while (true) {
            Tokens.Token token = this.token;
            if (token.kind != Tokens.TokenKind.DOT) {
                return jCExpression;
            }
            int i = token.pos;
            nextToken();
            List<JCTree.JCAnnotation> listTypeAnnotationsOpt = z ? typeAnnotationsOpt() : null;
            jCExpression = (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpression, ident()));
            if (listTypeAnnotationsOpt != null && listTypeAnnotationsOpt.nonEmpty()) {
                jCExpression = (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt.head.pos).AnnotatedType(listTypeAnnotationsOpt, jCExpression));
            }
        }
    }

    public List<JCTree.JCExpression> qualidentList(boolean z) {
        ListBuffer listBuffer = new ListBuffer();
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = z ? typeAnnotationsOpt() : List.nil();
        JCTree.JCExpression jCExpressionQualident = qualident(z);
        if (listTypeAnnotationsOpt.isEmpty()) {
            listBuffer.append(jCExpressionQualident);
        } else {
            listBuffer.append(insertAnnotationsToMostInner(jCExpressionQualident, listTypeAnnotationsOpt, false));
        }
        while (this.token.kind == Tokens.TokenKind.COMMA) {
            nextToken();
            List<JCTree.JCAnnotation> listTypeAnnotationsOpt2 = z ? typeAnnotationsOpt() : List.nil();
            JCTree.JCExpression jCExpressionQualident2 = qualident(z);
            if (listTypeAnnotationsOpt2.isEmpty()) {
                listBuffer.append(jCExpressionQualident2);
            } else {
                listBuffer.append(insertAnnotationsToMostInner(jCExpressionQualident2, listTypeAnnotationsOpt2, false));
            }
        }
        return listBuffer.toList();
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00ef  */
    public JCTree.JCClassDecl recordDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) {
        Name name;
        List<JCTree.JCTypeParameter> list;
        List<JCTree> list2;
        boolean z;
        int i = this.token.pos;
        nextToken();
        jCModifiers.flags |= Flags.RECORD;
        Name nameTypeName = typeName();
        List<JCTree.JCTypeParameter> listTypeParametersOpt = typeParametersOpt();
        boolean z2 = true;
        List<JCTree.JCVariableDecl> listFormalParameters = formalParameters(false, true);
        List<JCTree.JCExpression> listNil = List.nil();
        if (this.token.kind == Tokens.TokenKind.IMPLEMENTS) {
            nextToken();
            listNil = typeList();
        }
        saveDanglingDocComments(comment);
        List<JCTree> listClassInterfaceOrRecordBody = classInterfaceOrRecordBody(nameTypeName, false, true);
        ArrayList arrayList = new ArrayList();
        Iterator<JCTree.JCVariableDecl> it = listFormalParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        for (JCTree jCTree : listClassInterfaceOrRecordBody) {
            if (jCTree.hasTag(JCTree.Tag.METHODDEF)) {
                JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                if (jCMethodDecl.name == this.names.init && jCMethodDecl.params.isEmpty() && (jCMethodDecl.mods.flags & 2251799813685248L) != 0) {
                    ListBuffer listBuffer = new ListBuffer();
                    for (JCTree.JCVariableDecl jCVariableDecl : listFormalParameters) {
                        boolean z3 = z2;
                        TreeMaker treeMakerAt = this.F.at(jCVariableDecl);
                        TreeMaker treeMaker = this.F;
                        Name name2 = nameTypeName;
                        JCTree.JCModifiers jCModifiers2 = jCVariableDecl.mods;
                        listBuffer.add(treeMakerAt.VarDef(treeMaker.Modifiers((jCModifiers2.flags & Flags.VARARGS) | 8606744576L, jCModifiers2.annotations), jCVariableDecl.name, jCVariableDecl.vartype, null));
                        z2 = z3;
                        nameTypeName = name2;
                        listTypeParametersOpt = listTypeParametersOpt;
                        listClassInterfaceOrRecordBody = listClassInterfaceOrRecordBody;
                    }
                    name = nameTypeName;
                    list = listTypeParametersOpt;
                    list2 = listClassInterfaceOrRecordBody;
                    z = z2;
                    jCMethodDecl.params = listBuffer.toList();
                } else {
                    name = nameTypeName;
                    list = listTypeParametersOpt;
                    list2 = listClassInterfaceOrRecordBody;
                    z = z2;
                }
            } else {
                name = nameTypeName;
                list = listTypeParametersOpt;
                list2 = listClassInterfaceOrRecordBody;
                z = z2;
            }
            z2 = z;
            nameTypeName = name;
            listTypeParametersOpt = list;
            listClassInterfaceOrRecordBody = list2;
        }
        Name name3 = nameTypeName;
        List<JCTree.JCTypeParameter> list3 = listTypeParametersOpt;
        List<JCTree> listPrepend = listClassInterfaceOrRecordBody;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            listPrepend = listPrepend.prepend((JCTree.JCVariableDecl) arrayList.get(size));
        }
        return (JCTree.JCClassDecl) attach((JCTree.JCClassDecl) toP(this.F.at(i).ClassDef(jCModifiers, name3, list3, null, listNil, listPrepend)), comment);
    }

    public void reportDanglingComments(final JCTree jCTree, Tokens.Comment comment) {
        List<Tokens.Comment> listRemove = this.danglingComments.remove(comment);
        if (listRemove != null) {
            listRemove.forEach(new Consumer() { // from class: rm7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.reportDanglingDocComment(jCTree, (Tokens.Comment) obj);
                }
            });
        }
    }

    public void reportDanglingDocComment(JCTree jCTree, Tokens.Comment comment) {
        JCDiagnostic.DiagnosticPosition pos = comment.getPos();
        if (pos == null || shebang(comment, pos)) {
            return;
        }
        this.S.lintWarning(pos.withLintPosition(jCTree.getStartPosition()), CompilerProperties.LintWarnings.DanglingDocComment);
    }

    public void reportIntegralLiteralError(Name name, int i) {
        int iRadix = this.token.radix();
        if (iRadix == 2 || iRadix == 8) {
            char[] charArray = strval(name).toCharArray();
            for (int i2 = 0; i2 < charArray.length; i2++) {
                if (Character.digit(charArray[i2], iRadix) == -1) {
                    this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos + i2, iRadix == 2 ? CompilerProperties.Errors.IllegalDigitInBinaryLiteral : CompilerProperties.Errors.IllegalDigitInOctalLiteral);
                    return;
                }
            }
        }
        this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, this.token.pos, CompilerProperties.Errors.IntNumberTooLarge(strval(name)));
    }

    public JCTree resource() {
        Tokens.TokenKind tokenKind = this.token.kind;
        if (tokenKind == Tokens.TokenKind.FINAL || tokenKind == Tokens.TokenKind.MONKEYS_AT) {
            return variableDeclaratorRest(this.token.pos, optFinal(0L), parseType(true), identOrUnderscore(), true, null, true, false);
        }
        JCTree.JCExpression jCExpressionTerm = term(3);
        if (wasTypeMode() && this.LAX_IDENTIFIER.test(this.token.kind)) {
            return variableDeclaratorRest(this.token.pos, this.F.Modifiers(0L), jCExpressionTerm, identOrUnderscore(), true, null, true, false);
        }
        checkSourceLevel(Source.Feature.EFFECTIVELY_FINAL_VARIABLES_IN_TRY_WITH_RESOURCES);
        if (!jCExpressionTerm.hasTag(JCTree.Tag.IDENT) && !jCExpressionTerm.hasTag(JCTree.Tag.SELECT)) {
            this.log.error(jCExpressionTerm.pos(), CompilerProperties.Errors.TryWithResourcesExprNeedsVar);
        }
        return jCExpressionTerm;
    }

    public List<JCTree> resources() {
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.append(resource());
        while (this.token.kind == Tokens.TokenKind.SEMI) {
            storeEnd((JCTree) listBuffer.last(), this.token.endPos);
            int i = this.token.pos;
            nextToken();
            if (this.token.kind == Tokens.TokenKind.RPAREN) {
                break;
            }
            listBuffer.append(resource());
        }
        return listBuffer.toList();
    }

    public Name restrictedTypeName(JCTree.JCExpression jCExpression, boolean z) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()];
        if (i != 5) {
            if (i != 6) {
                return null;
            }
            return restrictedTypeName(((JCTree.JCArrayTypeTree) jCExpression).elemtype, z);
        }
        JCTree.JCIdent jCIdent = (JCTree.JCIdent) jCExpression;
        if (restrictedTypeNameStartingAtSource(jCIdent.name, jCExpression.pos, z) != null) {
            return jCIdent.name;
        }
        return null;
    }

    public Source restrictedTypeNameStartingAtSource(Name name, int i, boolean z) {
        if (name == this.names.var) {
            if (Source.Feature.LOCAL_VARIABLE_TYPE_INFERENCE.allowedInSource(this.source)) {
                return Source.JDK10;
            }
            if (z) {
                this.log.warning(i, CompilerProperties.Warnings.RestrictedTypeNotAllowed(name, Source.JDK10));
            }
        }
        if (name == this.names.yield) {
            if (this.allowYieldStatement) {
                return Source.JDK14;
            }
            if (z) {
                this.log.warning(i, CompilerProperties.Warnings.RestrictedTypeNotAllowed(name, Source.JDK14));
            }
        }
        if (name == this.names.record) {
            if (this.allowRecords) {
                return Source.JDK14;
            }
            if (z) {
                this.log.warning(i, CompilerProperties.Warnings.RestrictedTypeNotAllowedPreview(name, Source.JDK14));
            }
        }
        if (name == this.names.sealed) {
            if (this.allowSealedTypes) {
                return Source.JDK15;
            }
            if (z) {
                this.log.warning(i, CompilerProperties.Warnings.RestrictedTypeNotAllowedPreview(name, Source.JDK15));
            }
        }
        if (name != this.names.permits) {
            return null;
        }
        if (this.allowSealedTypes) {
            return Source.JDK15;
        }
        if (!z) {
            return null;
        }
        this.log.warning(i, CompilerProperties.Warnings.RestrictedTypeNotAllowedPreview(name, Source.JDK15));
        return null;
    }

    public void selectExprMode() {
        setMode((this.mode & 32) | 1);
    }

    public void selectTypeMode() {
        setMode((this.mode & 32) | 2);
    }

    public void setErrorEndPos(int i) {
        this.endPosTable.setErrorEndPos(i);
    }

    public void setLastMode(int i) {
        this.lastmode = i;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void skip(boolean z, boolean z2, boolean z3, boolean z4) {
        while (true) {
            switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()]) {
                case 1:
                    nextToken();
                    return;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    return;
                case 10:
                    if (z) {
                        return;
                    }
                    break;
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                    if (z2) {
                        return;
                    }
                    break;
                case 31:
                case 32:
                    if (z3) {
                        return;
                    }
                    break;
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    if (z4) {
                        return;
                    }
                    break;
                case 51:
                    if (z4) {
                        return;
                    }
                    break;
            }
            nextToken();
        }
    }

    public <T extends JCTree> T storeEnd(T t, int i) {
        return (T) this.endPosTable.storeEnd(t, i);
    }

    public String strval(Name name) {
        String strStringVal = this.token.stringVal();
        if (name.length() == 0) {
            return strStringVal;
        }
        return name + strStringVal;
    }

    public JCTree.JCExpression superSuffix(List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression) {
        nextToken();
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        if (tokenKind == Tokens.TokenKind.LPAREN || list != null) {
            return arguments(list, jCExpression);
        }
        if (tokenKind == Tokens.TokenKind.COLCOL) {
            return list != null ? illegal() : memberReferenceSuffix(jCExpression);
        }
        int i = token.pos;
        accept(Tokens.TokenKind.DOT);
        return argumentsOpt(this.token.kind == Tokens.TokenKind.LT ? typeArguments(false) : null, (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpression, ident())));
    }

    public List<JCTree.JCCase> switchBlockStatementGroup() {
        CaseTree.CaseKind caseKind;
        JCTree.JCStatement jCStatement;
        List<JCTree.JCStatement> listBlockStatements;
        CaseTree.CaseKind caseKind2;
        JCTree.JCStatement jCStatement2;
        List<JCTree.JCStatement> listBlockStatements2;
        int i = this.token.pos;
        ListBuffer listBuffer = new ListBuffer();
        int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()];
        if (i2 != 33) {
            if (i2 != 34) {
                x01.a("should not reach here");
                return null;
            }
            nextToken();
            JCTree.JCCaseLabel jCCaseLabel = (JCTree.JCCaseLabel) toP(this.F.at(i).DefaultCaseLabel());
            JCTree.JCExpression guard = parseGuard(jCCaseLabel);
            Tokens.TokenKind tokenKind = this.token.kind;
            Tokens.TokenKind tokenKind2 = Tokens.TokenKind.ARROW;
            if (tokenKind == tokenKind2) {
                checkSourceLevel(Source.Feature.SWITCH_RULE);
                accept(tokenKind2);
                caseKind2 = JCTree.JCCase.RULE;
                JCTree.JCStatement statementAsBlock = parseStatementAsBlock();
                if (!statementAsBlock.hasTag(JCTree.Tag.EXEC) && !statementAsBlock.hasTag(JCTree.Tag.BLOCK) && !statementAsBlock.hasTag(JCTree.Tag.THROW)) {
                    this.log.error(statementAsBlock.pos(), CompilerProperties.Errors.SwitchCaseUnexpectedStatement);
                }
                List<JCTree.JCStatement> listOf = List.of(statementAsBlock);
                listBlockStatements2 = listOf;
                jCStatement2 = listOf.head;
            } else {
                accept(Tokens.TokenKind.COLON, new Function() { // from class: zm7
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return CompilerProperties.Errors.Expected2(Tokens.TokenKind.COLON, Tokens.TokenKind.ARROW);
                    }
                });
                caseKind2 = JCTree.JCCase.STATEMENT;
                jCStatement2 = null;
                listBlockStatements2 = blockStatements();
            }
            JCTree.JCCase jCCaseCase = this.F.at(i).Case(caseKind2, List.of(jCCaseLabel), guard, listBlockStatements2, jCStatement2);
            if (listBlockStatements2.isEmpty()) {
                storeEnd(jCCaseCase, this.S.prevToken().endPos);
            }
            return listBuffer.append(jCCaseCase).toList();
        }
        nextToken();
        ListBuffer listBuffer2 = new ListBuffer();
        boolean zIsNullCaseLabel = false;
        while (true) {
            JCTree.JCCaseLabel caseLabel = parseCaseLabel(zIsNullCaseLabel);
            listBuffer2.append(caseLabel);
            if (this.token.kind != Tokens.TokenKind.COMMA) {
                break;
            }
            nextToken();
            checkSourceLevel(Source.Feature.SWITCH_MULTIPLE_CASE_LABELS);
            zIsNullCaseLabel = TreeInfo.isNullCaseLabel(caseLabel);
        }
        JCTree.JCExpression guard2 = parseGuard((JCTree.JCCaseLabel) listBuffer2.last());
        Tokens.TokenKind tokenKind3 = this.token.kind;
        Tokens.TokenKind tokenKind4 = Tokens.TokenKind.ARROW;
        if (tokenKind3 == tokenKind4) {
            checkSourceLevel(Source.Feature.SWITCH_RULE);
            accept(tokenKind4);
            caseKind = JCTree.JCCase.RULE;
            JCTree.JCStatement statementAsBlock2 = parseStatementAsBlock();
            if (!statementAsBlock2.hasTag(JCTree.Tag.EXEC) && !statementAsBlock2.hasTag(JCTree.Tag.BLOCK) && !statementAsBlock2.hasTag(JCTree.Tag.THROW)) {
                this.log.error(statementAsBlock2.pos(), CompilerProperties.Errors.SwitchCaseUnexpectedStatement);
            }
            List<JCTree.JCStatement> listOf2 = List.of(statementAsBlock2);
            listBlockStatements = listOf2;
            jCStatement = listOf2.head;
        } else {
            accept(Tokens.TokenKind.COLON, new Function() { // from class: ym7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CompilerProperties.Errors.Expected2(Tokens.TokenKind.COLON, Tokens.TokenKind.ARROW);
                }
            });
            caseKind = JCTree.JCCase.STATEMENT;
            jCStatement = null;
            listBlockStatements = blockStatements();
        }
        JCTree.JCCase jCCaseCase2 = this.F.at(i).Case(caseKind, listBuffer2.toList(), guard2, listBlockStatements, jCStatement);
        if (listBlockStatements.isEmpty()) {
            storeEnd(jCCaseCase2, this.S.prevToken().endPos);
        }
        return listBuffer.append(jCCaseCase2).toList();
    }

    public List<JCTree.JCCase> switchBlockStatementGroups() {
        ListBuffer listBuffer = new ListBuffer();
        while (true) {
            Tokens.Token token = this.token;
            int i = token.pos;
            int i2 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[token.kind.ordinal()];
            if (i2 == 6 || i2 == 12) {
                break;
            }
            if (i2 == 33 || i2 == 34) {
                listBuffer.appendList(switchBlockStatementGroup());
            } else {
                nextToken();
                syntaxError(i, CompilerProperties.Errors.Expected3(Tokens.TokenKind.CASE, Tokens.TokenKind.DEFAULT, Tokens.TokenKind.RBRACE));
            }
        }
        return listBuffer.toList();
    }

    public JCTree.JCExpression term() {
        Tokens.TokenKind tokenKind;
        JCTree.JCExpression jCExpressionTerm1 = term1();
        return (!isMode(1) || ((tokenKind = this.token.kind) != Tokens.TokenKind.EQ && (Tokens.TokenKind.PLUSEQ.compareTo(tokenKind) > 0 || this.token.kind.compareTo(Tokens.TokenKind.GTGTGTEQ) > 0))) ? jCExpressionTerm1 : termRest(jCExpressionTerm1);
    }

    public JCTree.JCExpression term1() {
        JCTree.JCExpression jCExpressionTerm2 = term2();
        if (!isMode(1) || this.token.kind != Tokens.TokenKind.QUES) {
            return jCExpressionTerm2;
        }
        selectExprMode();
        return term1Rest(jCExpressionTerm2);
    }

    public JCTree.JCExpression term1Rest(JCTree.JCExpression jCExpression) {
        Tokens.Token token = this.token;
        if (token.kind != Tokens.TokenKind.QUES) {
            return jCExpression;
        }
        int i = token.pos;
        nextToken();
        JCTree.JCExpression jCExpressionTerm = term();
        accept(Tokens.TokenKind.COLON);
        return this.F.at(i).Conditional(jCExpression, jCExpressionTerm, term1());
    }

    public JCTree.JCExpression term2() {
        JCTree.JCExpression jCExpressionTerm3 = term3();
        if (!isMode(1) || prec(this.token.kind) < 4) {
            return jCExpressionTerm3;
        }
        selectExprMode();
        return term2Rest(jCExpressionTerm3, 4);
    }

    public JCTree.JCExpression term2Rest(JCTree.JCExpression jCExpression, int i) {
        JCTree jCTreeInsertAnnotationsToMostInner;
        final JavacParser javacParser = this;
        JCTree.JCExpression[] jCExpressionArrNewOdStack = javacParser.newOdStack();
        Tokens.Token[] tokenArrNewOpStack = javacParser.newOpStack();
        jCExpressionArrNewOdStack[0] = jCExpression;
        int i2 = javacParser.token.pos;
        Tokens.Token token = Tokens.DUMMY;
        int i3 = 0;
        while (prec(javacParser.token.kind) >= i) {
            tokenArrNewOpStack[i3] = token;
            Tokens.Token token2 = javacParser.token;
            if (token2.kind == Tokens.TokenKind.INSTANCEOF) {
                int i4 = token2.pos;
                javacParser.nextToken();
                Tokens.Token token3 = javacParser.token;
                Tokens.TokenKind tokenKind = token3.kind;
                Tokens.TokenKind tokenKind2 = Tokens.TokenKind.LPAREN;
                if (tokenKind == tokenKind2) {
                    javacParser.checkSourceLevel(token3.pos, Source.Feature.PATTERN_SWITCH);
                    jCTreeInsertAnnotationsToMostInner = javacParser.parsePattern(javacParser.token.pos, null, null, false, false);
                } else {
                    int i5 = token3.pos;
                    JCTree.JCModifiers jCModifiersOptFinal = javacParser.optFinal(0L);
                    int i6 = javacParser.token.pos;
                    JCTree.JCExpression jCExpressionUnannotatedType = javacParser.unannotatedType(false);
                    Tokens.Token token4 = javacParser.token;
                    Tokens.TokenKind tokenKind3 = token4.kind;
                    if (tokenKind3 == Tokens.TokenKind.IDENTIFIER) {
                        javacParser.checkSourceLevel(token4.pos, Source.Feature.PATTERN_MATCHING_IN_INSTANCEOF);
                        jCTreeInsertAnnotationsToMostInner = javacParser.parsePattern(i5, jCModifiersOptFinal, jCExpressionUnannotatedType, false, false);
                        javacParser = this;
                    } else if (tokenKind3 == tokenKind2) {
                        javacParser = this;
                        jCTreeInsertAnnotationsToMostInner = javacParser.parsePattern(i5, jCModifiersOptFinal, jCExpressionUnannotatedType, false, false);
                    } else {
                        javacParser = this;
                        if (tokenKind3 == Tokens.TokenKind.UNDERSCORE) {
                            javacParser.checkSourceLevel(token4.pos, Source.Feature.UNNAMED_VARIABLES);
                            jCTreeInsertAnnotationsToMostInner = javacParser.parsePattern(i5, jCModifiersOptFinal, jCExpressionUnannotatedType, false, false);
                        } else {
                            javacParser.checkNoMods(i6, jCModifiersOptFinal.flags & (-131073));
                            jCTreeInsertAnnotationsToMostInner = jCModifiersOptFinal.annotations.nonEmpty() ? javacParser.insertAnnotationsToMostInner(jCExpressionUnannotatedType, jCModifiersOptFinal.annotations.map(new Function() { // from class: bn7
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return JavacParser.l(this.b, (JCTree.JCAnnotation) obj);
                                }
                            }), false) : jCExpressionUnannotatedType;
                        }
                    }
                }
                jCExpressionArrNewOdStack[i3] = javacParser.F.at(i4).TypeTest(jCExpressionArrNewOdStack[i3], jCTreeInsertAnnotationsToMostInner);
            } else {
                javacParser.nextToken();
                i3++;
                jCExpressionArrNewOdStack[i3] = javacParser.term3();
                token = token2;
            }
            while (i3 > 0 && prec(token.kind) >= prec(javacParser.token.kind)) {
                int i7 = i3 - 1;
                jCExpressionArrNewOdStack[i7] = javacParser.F.at(token.pos).Binary(optag(token.kind), jCExpressionArrNewOdStack[i7], jCExpressionArrNewOdStack[i3]);
                i3--;
                token = tokenArrNewOpStack[i3];
            }
        }
        Assert.check(i3 == 0);
        JCTree.JCExpression jCExpressionFoldStrings = jCExpressionArrNewOdStack[0];
        if (jCExpressionFoldStrings.hasTag(JCTree.Tag.PLUS)) {
            jCExpressionFoldStrings = javacParser.foldStrings(jCExpressionFoldStrings);
        }
        javacParser.odStackSupply.add(jCExpressionArrNewOdStack);
        javacParser.opStackSupply.add(tokenArrNewOpStack);
        return jCExpressionFoldStrings;
    }

    /* JADX WARN: Code duplicated, block: B:120:0x02a5 A[DONT_INVERT, FALL_THROUGH] */
    /* JADX WARN: Code duplicated, block: B:121:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:123:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:130:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:133:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:141:0x0315  */
    /* JADX WARN: Code duplicated, block: B:145:0x031e  */
    /* JADX WARN: Code duplicated, block: B:146:0x0322  */
    /* JADX WARN: Code duplicated, block: B:148:0x0328  */
    /* JADX WARN: Code duplicated, block: B:149:0x0333  */
    /* JADX WARN: Code duplicated, block: B:151:0x033e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:156:0x0359  */
    /* JADX WARN: Code duplicated, block: B:158:0x0366  */
    /* JADX WARN: Code duplicated, block: B:161:0x036d  */
    /* JADX WARN: Code duplicated, block: B:163:0x0372  */
    /* JADX WARN: Code duplicated, block: B:165:0x0384  */
    /* JADX WARN: Code duplicated, block: B:170:0x03ad  */
    /* JADX WARN: Code duplicated, block: B:172:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:175:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:177:0x03d6  */
    /* JADX WARN: Code duplicated, block: B:180:0x03f7  */
    /* JADX WARN: Code duplicated, block: B:183:0x0404  */
    /* JADX WARN: Code duplicated, block: B:186:0x0423  */
    /* JADX WARN: Code duplicated, block: B:193:0x0437  */
    /* JADX WARN: Code duplicated, block: B:199:0x045c  */
    /* JADX WARN: Code duplicated, block: B:201:0x0469  */
    /* JADX WARN: Code duplicated, block: B:204:0x0480  */
    /* JADX WARN: Code duplicated, block: B:206:0x049d  */
    /* JADX WARN: Code duplicated, block: B:208:0x04b3  */
    /* JADX WARN: Code duplicated, block: B:210:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:212:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:217:0x04eb  */
    /* JADX WARN: Code duplicated, block: B:222:0x050e A[LOOP:2: B:220:0x0506->B:222:0x050e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:226:0x053a A[LOOP:3: B:224:0x0532->B:226:0x053a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:229:0x0569  */
    /* JADX WARN: Code duplicated, block: B:233:0x0577  */
    /* JADX WARN: Code duplicated, block: B:269:0x04e5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:270:0x031a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:271:0x0456 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:272:0x0475 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:273:0x0575 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:275:0x03cf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:276:0x036b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:277:0x038f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:278:0x03ab A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:281:0x0453 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    public JCTree.JCExpression term3() {
        JCTree.JCExpression jCExpressionInsertAnnotationsToMostInner;
        JCTree.JCExpression jCExpressionIllegal;
        int i;
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt;
        int[] iArr;
        int i2;
        ListBuffer listBuffer;
        JCTree.JCExpression jCExpressionTypeArgumentsOpt;
        JCTree.JCExpression jCExpressionBracketsOpt;
        Tokens.TokenKind tokenKind;
        Tokens.TokenKind tokenKind2;
        JCTree.JCExpression jCExpression;
        List<JCTree.JCExpression> listTypeArgumentsOpt;
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt2;
        Tokens.Token token;
        int i3;
        Tokens.TokenKind tokenKind3;
        Tokens.Token token2;
        Tokens.TokenKind tokenKind4;
        int i4 = this.token.pos;
        int i5 = this.mode;
        List<JCTree.JCExpression> listTypeArgumentsOpt2 = typeArgumentsOpt(1);
        int i6 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()];
        if (i6 == 5) {
            List<JCTree.JCAnnotation> listTypeAnnotationsOpt3 = typeAnnotationsOpt();
            if (listTypeAnnotationsOpt3.isEmpty()) {
                x01.a("Expected type annotations, but found none!");
                return null;
            }
            JCTree.JCExpression jCExpressionTerm3 = term3();
            if (isMode(2)) {
                jCExpressionInsertAnnotationsToMostInner = insertAnnotationsToMostInner(jCExpressionTerm3, listTypeAnnotationsOpt3, false);
            } else {
                int i7 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpressionTerm3.getTag().ordinal()];
                if (i7 != 1) {
                    if (i7 != 2) {
                        return illegal(listTypeAnnotationsOpt3.head.pos);
                    }
                    if (((JCTree.JCFieldAccess) jCExpressionTerm3).name != this.names._class) {
                        return illegal();
                    }
                    this.log.error(this.token.pos, CompilerProperties.Errors.NoAnnotationsOnDotClass);
                    return jCExpressionTerm3;
                }
                JCTree.JCMemberReference jCMemberReference = (JCTree.JCMemberReference) jCExpressionTerm3;
                if (TreeInfo.isType(jCMemberReference.expr, this.names)) {
                    jCMemberReference.expr = insertAnnotationsToMostInner(jCMemberReference.expr, listTypeAnnotationsOpt3, false);
                } else {
                    jCMemberReference.expr = syntaxError(getStartPos(jCMemberReference.expr), List.of((JCTree.JCAnnotatedType) toP(this.F.at(i4).AnnotatedType(listTypeAnnotationsOpt3, jCMemberReference.expr))), CompilerProperties.Errors.IllegalStartOfType);
                }
                jCMemberReference.pos = getStartPos(jCMemberReference.expr);
                jCExpressionInsertAnnotationsToMostInner = jCMemberReference;
            }
        } else if (i6 == 9) {
            if (listTypeArgumentsOpt2 != null) {
                return illegal();
            }
            if (isMode(1) || isMode(32) || !peekToken(Tokens.TokenKind.ARROW)) {
                jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(this.token.pos).Ident(ident()));
                while (true) {
                    i = this.token.pos;
                    listTypeAnnotationsOpt = typeAnnotationsOpt();
                    if (listTypeAnnotationsOpt.isEmpty() && (tokenKind3 = this.token.kind) != Tokens.TokenKind.LBRACKET && tokenKind3 != Tokens.TokenKind.ELLIPSIS) {
                        return illegal(listTypeAnnotationsOpt.head.pos);
                    }
                    iArr = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind;
                    i2 = iArr[this.token.kind.ordinal()];
                    if (i2 != 21) {
                        switch (i2) {
                            case 73:
                                nextToken();
                                tokenKind = this.token.kind;
                                tokenKind2 = Tokens.TokenKind.RBRACKET;
                                if (tokenKind != tokenKind2) {
                                    if (isMode(1)) {
                                        selectExprMode();
                                        JCTree.JCExpression jCExpressionTerm = term();
                                        if (!listTypeAnnotationsOpt.isEmpty()) {
                                            jCExpressionIllegal = illegal(listTypeAnnotationsOpt.head.pos);
                                        }
                                        jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Indexed(jCExpressionIllegal, jCExpressionTerm));
                                    }
                                    accept(tokenKind2);
                                } else {
                                    nextToken();
                                    jCExpression = (JCTree.JCExpression) toP(this.F.at(i).TypeArray(bracketsOpt(jCExpressionIllegal)));
                                    if (listTypeAnnotationsOpt.nonEmpty()) {
                                        jCExpression = (JCTree.JCExpression) toP(this.F.at(i).AnnotatedType(listTypeAnnotationsOpt, jCExpression));
                                    }
                                    jCExpressionIllegal = bracketsSuffix(jCExpression);
                                }
                                break;
                            case 74:
                                if (isMode(1)) {
                                    selectExprMode();
                                    jCExpressionIllegal = arguments(listTypeArgumentsOpt2, jCExpressionIllegal);
                                    if (!listTypeAnnotationsOpt.isEmpty()) {
                                        jCExpressionIllegal = illegal(listTypeAnnotationsOpt.head.pos);
                                    }
                                    listTypeArgumentsOpt2 = null;
                                }
                                break;
                            case 75:
                                nextToken();
                                if (this.token.kind != Tokens.TokenKind.IDENTIFIER && listTypeArgumentsOpt2 != null) {
                                    return illegal();
                                }
                                int i8 = this.mode;
                                setMode(i8 & (-5));
                                listTypeArgumentsOpt = typeArgumentsOpt(1);
                                setMode(i8);
                                if (isMode(1)) {
                                    i3 = iArr[this.token.kind.ordinal()];
                                    if (i3 != 7) {
                                        switch (i3) {
                                            case 48:
                                                if (listTypeArgumentsOpt != null) {
                                                    return illegal();
                                                }
                                                selectExprMode();
                                                jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._this));
                                                nextToken();
                                                break;
                                            case 49:
                                                selectExprMode();
                                                jCExpressionIllegal = superSuffix(listTypeArgumentsOpt, (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._super)));
                                                listTypeArgumentsOpt2 = null;
                                                break;
                                            case 50:
                                                if (listTypeArgumentsOpt != null) {
                                                    return illegal();
                                                }
                                                selectExprMode();
                                                int i9 = this.token.pos;
                                                nextToken();
                                                if (this.token.kind == Tokens.TokenKind.LT) {
                                                    listTypeArgumentsOpt = typeArguments(false);
                                                }
                                                jCExpressionIllegal = innerCreator(i9, listTypeArgumentsOpt, jCExpressionIllegal);
                                                listTypeArgumentsOpt2 = null;
                                                break;
                                        }
                                    } else {
                                        if (listTypeArgumentsOpt != null) {
                                            return illegal();
                                        }
                                        selectExprMode();
                                        jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._class));
                                        nextToken();
                                    }
                                    listTypeArgumentsOpt2 = listTypeArgumentsOpt;
                                }
                                if (isMode(2) || this.token.kind != Tokens.TokenKind.MONKEYS_AT) {
                                    listTypeAnnotationsOpt2 = null;
                                } else {
                                    listTypeAnnotationsOpt2 = typeAnnotationsOpt();
                                }
                                jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpressionIllegal, ident()));
                                token = this.token;
                                if (token.pos > this.endPosTable.errorEndPos && token.kind == Tokens.TokenKind.MONKEYS_AT) {
                                    if (listTypeArgumentsOpt != null) {
                                        illegal();
                                    }
                                    return (JCTree.JCExpression) toP(jCExpressionIllegal);
                                }
                                if (listTypeAnnotationsOpt2 == null && listTypeAnnotationsOpt2.nonEmpty()) {
                                    jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt2.head.pos).AnnotatedType(listTypeAnnotationsOpt2, jCExpressionIllegal));
                                }
                                listTypeArgumentsOpt2 = listTypeArgumentsOpt;
                                break;
                                break;
                            case 76:
                                if (this.permitTypeAnnotationsPushBack) {
                                    this.typeAnnotationsPushedBack = listTypeAnnotationsOpt;
                                } else if (listTypeAnnotationsOpt.nonEmpty()) {
                                    illegal(listTypeAnnotationsOpt.head.pos);
                                }
                                break;
                            default:
                                break;
                        }
                    } else if (!isMode(2) && isUnboundMemberRef()) {
                        int i10 = this.token.pos;
                        accept(Tokens.TokenKind.LT);
                        listBuffer = new ListBuffer();
                        listBuffer.append(typeArgument());
                        while (this.token.kind == Tokens.TokenKind.COMMA) {
                            nextToken();
                            listBuffer.append(typeArgument());
                        }
                        accept(Tokens.TokenKind.GT);
                        jCExpressionTypeArgumentsOpt = (JCTree.JCExpression) toP(this.F.at(i10).TypeApply(jCExpressionIllegal, listBuffer.toList()));
                        while (this.token.kind == Tokens.TokenKind.DOT) {
                            nextToken();
                            selectTypeMode();
                            jCExpressionTypeArgumentsOpt = typeArgumentsOpt((JCTree.JCExpression) toP(this.F.at(this.token.pos).Select(jCExpressionTypeArgumentsOpt, ident())));
                        }
                        jCExpressionBracketsOpt = bracketsOpt(jCExpressionTypeArgumentsOpt);
                        if (this.token.kind != Tokens.TokenKind.COLCOL) {
                            jCExpressionBracketsOpt = illegal();
                        }
                        selectExprMode();
                        return term3Rest(jCExpressionBracketsOpt, listTypeArgumentsOpt2);
                    }
                }
            } else {
                jCExpressionIllegal = lambdaExpressionOrStatement(false, false, i4);
            }
            if (listTypeArgumentsOpt2 != null) {
                illegal();
            }
            jCExpressionInsertAnnotationsToMostInner = typeArgumentsOpt(jCExpressionIllegal);
        } else {
            if (i6 == 40) {
                checkSourceLevel(Source.Feature.SWITCH_EXPRESSION);
                this.allowYieldStatement = true;
                int i11 = this.token.pos;
                nextToken();
                JCTree.JCExpression jCExpressionParExpression = parExpression();
                accept(Tokens.TokenKind.LBRACE);
                ListBuffer listBuffer2 = new ListBuffer();
                while (true) {
                    Tokens.Token token3 = this.token;
                    int i12 = token3.pos;
                    int i13 = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[token3.kind.ordinal()];
                    if (i13 == 6 || i13 == 12) {
                        break;
                    }
                    if (i13 == 33 || i13 == 34) {
                        listBuffer2.appendList(switchExpressionStatementGroup());
                    } else {
                        nextToken();
                        syntaxError(i12, CompilerProperties.Errors.Expected3(Tokens.TokenKind.CASE, Tokens.TokenKind.DEFAULT, Tokens.TokenKind.RBRACE));
                    }
                }
                JCTree.JCSwitchExpression jCSwitchExpression = (JCTree.JCSwitchExpression) to(this.F.at(i11).SwitchExpression(jCExpressionParExpression, listBuffer2.toList()));
                jCSwitchExpression.bracePos = this.token.pos;
                accept(Tokens.TokenKind.RBRACE);
                return jCSwitchExpression;
            }
            if (i6 != 74) {
                switch (i6) {
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                        if (listTypeArgumentsOpt2 != null) {
                            illegal();
                        }
                        jCExpressionInsertAnnotationsToMostInner = bracketsSuffix(bracketsOpt(basicType()));
                        break;
                    case 30:
                        if (listTypeArgumentsOpt2 != null) {
                            illegal();
                        }
                        if (!isMode(1)) {
                            JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree = (JCTree.JCPrimitiveTypeTree) to(this.F.at(i4).TypeIdent(TypeTag.VOID));
                            nextToken();
                            return jCPrimitiveTypeTree;
                        }
                        nextToken();
                        if (this.token.kind != Tokens.TokenKind.DOT) {
                            return illegal(i4);
                        }
                        jCExpressionInsertAnnotationsToMostInner = bracketsSuffix((JCTree.JCPrimitiveTypeTree) toP(this.F.at(i4).TypeIdent(TypeTag.VOID)));
                        break;
                        break;
                    default:
                        switch (i6) {
                            case 48:
                                if (!isMode(1)) {
                                    return illegal();
                                }
                                selectExprMode();
                                JCTree.JCExpression jCExpression2 = (JCTree.JCExpression) to(this.F.at(i4).Ident(this.names._this));
                                nextToken();
                                jCExpressionInsertAnnotationsToMostInner = listTypeArgumentsOpt2 == null ? argumentsOpt(null, jCExpression2) : arguments(listTypeArgumentsOpt2, jCExpression2);
                                listTypeArgumentsOpt2 = null;
                                break;
                                break;
                            case 49:
                                if (!isMode(1)) {
                                    return illegal();
                                }
                                selectExprMode();
                                jCExpressionInsertAnnotationsToMostInner = superSuffix(listTypeArgumentsOpt2, (JCTree.JCExpression) to(this.F.at(i4).Ident(this.names._super)));
                                listTypeArgumentsOpt2 = null;
                                break;
                                break;
                            case 50:
                                if (listTypeArgumentsOpt2 == null && isMode(1)) {
                                    selectExprMode();
                                    nextToken();
                                    if (this.token.kind == Tokens.TokenKind.LT) {
                                        listTypeArgumentsOpt2 = typeArguments(false);
                                    }
                                    jCExpressionInsertAnnotationsToMostInner = creator(i4, listTypeArgumentsOpt2);
                                    listTypeArgumentsOpt2 = null;
                                    break;
                                }
                                return illegal();
                            case 51:
                                break;
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                            case 60:
                                if (listTypeArgumentsOpt2 != null || !isMode(1)) {
                                    return illegal();
                                }
                                selectExprMode();
                                jCExpressionInsertAnnotationsToMostInner = literal(this.names.empty);
                                break;
                                break;
                            default:
                                switch (i6) {
                                    case 77:
                                        if (!isMode(2) || !isMode(8) || isMode(4)) {
                                            return illegal();
                                        }
                                        selectTypeMode();
                                        return typeArgument();
                                    case 78:
                                    case 79:
                                    case 80:
                                    case 81:
                                    case 82:
                                    case 83:
                                        if (listTypeArgumentsOpt2 != null || !isMode(1)) {
                                            return illegal();
                                        }
                                        Tokens.TokenKind tokenKind5 = this.token.kind;
                                        nextToken();
                                        selectExprMode();
                                        if (tokenKind5 != Tokens.TokenKind.SUB || (((tokenKind4 = (token2 = this.token).kind) != Tokens.TokenKind.INTLITERAL && tokenKind4 != Tokens.TokenKind.LONGLITERAL) || token2.radix() != 10)) {
                                            return this.F.at(i4).Unary(unoptag(tokenKind5), term3());
                                        }
                                        selectExprMode();
                                        jCExpressionInsertAnnotationsToMostInner = literal(this.names.hyphen, i4);
                                        break;
                                        break;
                                    default:
                                        return (listTypeArgumentsOpt2 == null || (i5 & 2) == 0) ? illegal() : this.F.at(i4).TypeApply(this.F.Erroneous(), listTypeArgumentsOpt2);
                                }
                                break;
                        }
                    case 31:
                    case 32:
                        if (listTypeArgumentsOpt2 != null) {
                            return illegal();
                        }
                        if (isMode(1)) {
                            jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(this.token.pos).Ident(ident()));
                            while (true) {
                                i = this.token.pos;
                                listTypeAnnotationsOpt = typeAnnotationsOpt();
                                if (listTypeAnnotationsOpt.isEmpty()) {
                                }
                                iArr = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind;
                                i2 = iArr[this.token.kind.ordinal()];
                                if (i2 != 21) {
                                    switch (i2) {
                                        case 73:
                                            nextToken();
                                            tokenKind = this.token.kind;
                                            tokenKind2 = Tokens.TokenKind.RBRACKET;
                                            if (tokenKind != tokenKind2) {
                                                if (isMode(1)) {
                                                    selectExprMode();
                                                    JCTree.JCExpression jCExpressionTerm2 = term();
                                                    if (!listTypeAnnotationsOpt.isEmpty()) {
                                                        jCExpressionIllegal = illegal(listTypeAnnotationsOpt.head.pos);
                                                    }
                                                    jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Indexed(jCExpressionIllegal, jCExpressionTerm2));
                                                }
                                                accept(tokenKind2);
                                            } else {
                                                nextToken();
                                                jCExpression = (JCTree.JCExpression) toP(this.F.at(i).TypeArray(bracketsOpt(jCExpressionIllegal)));
                                                if (listTypeAnnotationsOpt.nonEmpty()) {
                                                    jCExpression = (JCTree.JCExpression) toP(this.F.at(i).AnnotatedType(listTypeAnnotationsOpt, jCExpression));
                                                }
                                                jCExpressionIllegal = bracketsSuffix(jCExpression);
                                            }
                                            break;
                                        case 74:
                                            if (isMode(1)) {
                                                selectExprMode();
                                                jCExpressionIllegal = arguments(listTypeArgumentsOpt2, jCExpressionIllegal);
                                                if (!listTypeAnnotationsOpt.isEmpty()) {
                                                    jCExpressionIllegal = illegal(listTypeAnnotationsOpt.head.pos);
                                                }
                                                listTypeArgumentsOpt2 = null;
                                            }
                                            break;
                                        case 75:
                                            nextToken();
                                            if (this.token.kind != Tokens.TokenKind.IDENTIFIER) {
                                            }
                                            int i14 = this.mode;
                                            setMode(i14 & (-5));
                                            listTypeArgumentsOpt = typeArgumentsOpt(1);
                                            setMode(i14);
                                            if (isMode(1)) {
                                                i3 = iArr[this.token.kind.ordinal()];
                                                if (i3 != 7) {
                                                    switch (i3) {
                                                        case 48:
                                                            if (listTypeArgumentsOpt != null) {
                                                                return illegal();
                                                            }
                                                            selectExprMode();
                                                            jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._this));
                                                            nextToken();
                                                            break;
                                                        case 49:
                                                            selectExprMode();
                                                            jCExpressionIllegal = superSuffix(listTypeArgumentsOpt, (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._super)));
                                                            listTypeArgumentsOpt2 = null;
                                                            break;
                                                        case 50:
                                                            if (listTypeArgumentsOpt != null) {
                                                                return illegal();
                                                            }
                                                            selectExprMode();
                                                            int i15 = this.token.pos;
                                                            nextToken();
                                                            if (this.token.kind == Tokens.TokenKind.LT) {
                                                                listTypeArgumentsOpt = typeArguments(false);
                                                            }
                                                            jCExpressionIllegal = innerCreator(i15, listTypeArgumentsOpt, jCExpressionIllegal);
                                                            listTypeArgumentsOpt2 = null;
                                                            break;
                                                    }
                                                } else {
                                                    if (listTypeArgumentsOpt != null) {
                                                        return illegal();
                                                    }
                                                    selectExprMode();
                                                    jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._class));
                                                    nextToken();
                                                }
                                                listTypeArgumentsOpt2 = listTypeArgumentsOpt;
                                            }
                                            if (isMode(2)) {
                                                listTypeAnnotationsOpt2 = null;
                                            } else {
                                                listTypeAnnotationsOpt2 = null;
                                            }
                                            jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpressionIllegal, ident()));
                                            token = this.token;
                                            if (token.pos > this.endPosTable.errorEndPos) {
                                            }
                                            if (listTypeAnnotationsOpt2 == null) {
                                            }
                                            listTypeArgumentsOpt2 = listTypeArgumentsOpt;
                                            break;
                                        case 76:
                                            if (this.permitTypeAnnotationsPushBack) {
                                                this.typeAnnotationsPushedBack = listTypeAnnotationsOpt;
                                            } else if (listTypeAnnotationsOpt.nonEmpty()) {
                                                illegal(listTypeAnnotationsOpt.head.pos);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                } else if (!isMode(2)) {
                                    int i16 = this.token.pos;
                                    accept(Tokens.TokenKind.LT);
                                    listBuffer = new ListBuffer();
                                    listBuffer.append(typeArgument());
                                    while (this.token.kind == Tokens.TokenKind.COMMA) {
                                        nextToken();
                                        listBuffer.append(typeArgument());
                                    }
                                    accept(Tokens.TokenKind.GT);
                                    jCExpressionTypeArgumentsOpt = (JCTree.JCExpression) toP(this.F.at(i16).TypeApply(jCExpressionIllegal, listBuffer.toList()));
                                    while (this.token.kind == Tokens.TokenKind.DOT) {
                                        nextToken();
                                        selectTypeMode();
                                        jCExpressionTypeArgumentsOpt = typeArgumentsOpt((JCTree.JCExpression) toP(this.F.at(this.token.pos).Select(jCExpressionTypeArgumentsOpt, ident())));
                                    }
                                    jCExpressionBracketsOpt = bracketsOpt(jCExpressionTypeArgumentsOpt);
                                    if (this.token.kind != Tokens.TokenKind.COLCOL) {
                                        jCExpressionBracketsOpt = illegal();
                                    }
                                    selectExprMode();
                                    return term3Rest(jCExpressionBracketsOpt, listTypeArgumentsOpt2);
                                }
                            }
                        } else {
                            jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(this.token.pos).Ident(ident()));
                            while (true) {
                                i = this.token.pos;
                                listTypeAnnotationsOpt = typeAnnotationsOpt();
                                if (listTypeAnnotationsOpt.isEmpty()) {
                                }
                                iArr = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind;
                                i2 = iArr[this.token.kind.ordinal()];
                                if (i2 != 21) {
                                    switch (i2) {
                                        case 73:
                                            nextToken();
                                            tokenKind = this.token.kind;
                                            tokenKind2 = Tokens.TokenKind.RBRACKET;
                                            if (tokenKind != tokenKind2) {
                                                if (isMode(1)) {
                                                    selectExprMode();
                                                    JCTree.JCExpression jCExpressionTerm4 = term();
                                                    if (!listTypeAnnotationsOpt.isEmpty()) {
                                                        jCExpressionIllegal = illegal(listTypeAnnotationsOpt.head.pos);
                                                    }
                                                    jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Indexed(jCExpressionIllegal, jCExpressionTerm4));
                                                }
                                                accept(tokenKind2);
                                            } else {
                                                nextToken();
                                                jCExpression = (JCTree.JCExpression) toP(this.F.at(i).TypeArray(bracketsOpt(jCExpressionIllegal)));
                                                if (listTypeAnnotationsOpt.nonEmpty()) {
                                                    jCExpression = (JCTree.JCExpression) toP(this.F.at(i).AnnotatedType(listTypeAnnotationsOpt, jCExpression));
                                                }
                                                jCExpressionIllegal = bracketsSuffix(jCExpression);
                                            }
                                            break;
                                        case 74:
                                            if (isMode(1)) {
                                                selectExprMode();
                                                jCExpressionIllegal = arguments(listTypeArgumentsOpt2, jCExpressionIllegal);
                                                if (!listTypeAnnotationsOpt.isEmpty()) {
                                                    jCExpressionIllegal = illegal(listTypeAnnotationsOpt.head.pos);
                                                }
                                                listTypeArgumentsOpt2 = null;
                                            }
                                            break;
                                        case 75:
                                            nextToken();
                                            if (this.token.kind != Tokens.TokenKind.IDENTIFIER) {
                                            }
                                            int i17 = this.mode;
                                            setMode(i17 & (-5));
                                            listTypeArgumentsOpt = typeArgumentsOpt(1);
                                            setMode(i17);
                                            if (isMode(1)) {
                                                i3 = iArr[this.token.kind.ordinal()];
                                                if (i3 != 7) {
                                                    switch (i3) {
                                                        case 48:
                                                            if (listTypeArgumentsOpt != null) {
                                                                return illegal();
                                                            }
                                                            selectExprMode();
                                                            jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._this));
                                                            nextToken();
                                                            break;
                                                        case 49:
                                                            selectExprMode();
                                                            jCExpressionIllegal = superSuffix(listTypeArgumentsOpt, (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._super)));
                                                            listTypeArgumentsOpt2 = null;
                                                            break;
                                                        case 50:
                                                            if (listTypeArgumentsOpt != null) {
                                                                return illegal();
                                                            }
                                                            selectExprMode();
                                                            int i18 = this.token.pos;
                                                            nextToken();
                                                            if (this.token.kind == Tokens.TokenKind.LT) {
                                                                listTypeArgumentsOpt = typeArguments(false);
                                                            }
                                                            jCExpressionIllegal = innerCreator(i18, listTypeArgumentsOpt, jCExpressionIllegal);
                                                            listTypeArgumentsOpt2 = null;
                                                            break;
                                                    }
                                                } else {
                                                    if (listTypeArgumentsOpt != null) {
                                                        return illegal();
                                                    }
                                                    selectExprMode();
                                                    jCExpressionIllegal = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpressionIllegal, this.names._class));
                                                    nextToken();
                                                }
                                                listTypeArgumentsOpt2 = listTypeArgumentsOpt;
                                            }
                                            if (isMode(2)) {
                                                listTypeAnnotationsOpt2 = null;
                                            } else {
                                                listTypeAnnotationsOpt2 = null;
                                            }
                                            jCExpressionIllegal = (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpressionIllegal, ident()));
                                            token = this.token;
                                            if (token.pos > this.endPosTable.errorEndPos) {
                                            }
                                            if (listTypeAnnotationsOpt2 == null) {
                                            }
                                            listTypeArgumentsOpt2 = listTypeArgumentsOpt;
                                            break;
                                        case 76:
                                            if (this.permitTypeAnnotationsPushBack) {
                                                this.typeAnnotationsPushedBack = listTypeAnnotationsOpt;
                                            } else if (listTypeAnnotationsOpt.nonEmpty()) {
                                                illegal(listTypeAnnotationsOpt.head.pos);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                } else if (!isMode(2)) {
                                    int i19 = this.token.pos;
                                    accept(Tokens.TokenKind.LT);
                                    listBuffer = new ListBuffer();
                                    listBuffer.append(typeArgument());
                                    while (this.token.kind == Tokens.TokenKind.COMMA) {
                                        nextToken();
                                        listBuffer.append(typeArgument());
                                    }
                                    accept(Tokens.TokenKind.GT);
                                    jCExpressionTypeArgumentsOpt = (JCTree.JCExpression) toP(this.F.at(i19).TypeApply(jCExpressionIllegal, listBuffer.toList()));
                                    while (this.token.kind == Tokens.TokenKind.DOT) {
                                        nextToken();
                                        selectTypeMode();
                                        jCExpressionTypeArgumentsOpt = typeArgumentsOpt((JCTree.JCExpression) toP(this.F.at(this.token.pos).Select(jCExpressionTypeArgumentsOpt, ident())));
                                    }
                                    jCExpressionBracketsOpt = bracketsOpt(jCExpressionTypeArgumentsOpt);
                                    if (this.token.kind != Tokens.TokenKind.COLCOL) {
                                        jCExpressionBracketsOpt = illegal();
                                    }
                                    selectExprMode();
                                    return term3Rest(jCExpressionBracketsOpt, listTypeArgumentsOpt2);
                                }
                            }
                        }
                        if (listTypeArgumentsOpt2 != null) {
                            illegal();
                        }
                        jCExpressionInsertAnnotationsToMostInner = typeArgumentsOpt(jCExpressionIllegal);
                        break;
                }
            } else {
                if (listTypeArgumentsOpt2 != null || !isMode(1)) {
                    return illegal();
                }
                ParensResult parensResultAnalyzeParens = analyzeParens();
                int iOrdinal = parensResultAnalyzeParens.ordinal();
                if (iOrdinal == 0) {
                    accept(Tokens.TokenKind.LPAREN);
                    selectTypeMode();
                    JCTree.JCExpression intersectionType = parseIntersectionType(i4, parseType());
                    accept(Tokens.TokenKind.RPAREN);
                    selectExprMode();
                    return this.F.at(i4).TypeCast(intersectionType, term3());
                }
                if (iOrdinal == 1 || iOrdinal == 2) {
                    jCExpressionInsertAnnotationsToMostInner = lambdaExpressionOrStatement(true, parensResultAnalyzeParens == ParensResult.EXPLICIT_LAMBDA, i4);
                } else {
                    accept(Tokens.TokenKind.LPAREN);
                    selectExprMode();
                    JCTree.JCExpression jCExpressionTermRest = termRest(term1Rest(term2Rest(term3(), 4)));
                    accept(Tokens.TokenKind.RPAREN);
                    jCExpressionInsertAnnotationsToMostInner = (JCTree.JCExpression) toP(this.F.at(i4).Parens(jCExpressionTermRest));
                }
            }
        }
        return term3Rest(jCExpressionInsertAnnotationsToMostInner, listTypeArgumentsOpt2);
    }

    /* JADX WARN: Code duplicated, block: B:82:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:83:0x01bd  */
    public JCTree.JCExpression term3Rest(JCTree.JCExpression jCExpression, List<JCTree.JCExpression> list) {
        Tokens.TokenKind tokenKind;
        Tokens.TokenKind tokenKind2;
        JCTree.Tag tag;
        if (list != null) {
            illegal();
        }
        while (true) {
            int i = this.token.pos;
            List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
            Tokens.TokenKind tokenKind3 = this.token.kind;
            if (tokenKind3 == Tokens.TokenKind.LBRACKET) {
                nextToken();
                if (isMode(2)) {
                    int i2 = this.mode;
                    selectTypeMode();
                    if (this.token.kind == Tokens.TokenKind.RBRACKET) {
                        nextToken();
                        jCExpression = (JCTree.JCExpression) toP(this.F.at(i).TypeArray(bracketsOpt(jCExpression)));
                        if (this.token.kind != Tokens.TokenKind.COLCOL) {
                            return listTypeAnnotationsOpt.nonEmpty() ? (JCTree.JCExpression) toP(this.F.at(i).AnnotatedType(listTypeAnnotationsOpt, jCExpression)) : jCExpression;
                        }
                        selectExprMode();
                    } else {
                        setMode(i2);
                    }
                }
                if (isMode(1)) {
                    selectExprMode();
                    jCExpression = (JCTree.JCExpression) to(this.F.at(i).Indexed(jCExpression, term()));
                }
                accept(Tokens.TokenKind.RBRACKET);
            } else {
                if (tokenKind3 != Tokens.TokenKind.DOT) {
                    if (isMode(1)) {
                        Tokens.TokenKind tokenKind4 = this.token.kind;
                        Tokens.TokenKind tokenKind5 = Tokens.TokenKind.COLCOL;
                        if (tokenKind4 == tokenKind5) {
                            selectExprMode();
                            if (list != null) {
                                return illegal();
                            }
                            accept(tokenKind5);
                            jCExpression = memberReferenceSuffix(i, jCExpression);
                        }
                    }
                    if (!listTypeAnnotationsOpt.isEmpty()) {
                        if (!this.permitTypeAnnotationsPushBack) {
                            return illegal(listTypeAnnotationsOpt.head.pos);
                        }
                        this.typeAnnotationsPushedBack = listTypeAnnotationsOpt;
                    }
                    while (true) {
                        tokenKind = this.token.kind;
                        tokenKind2 = Tokens.TokenKind.PLUSPLUS;
                        if (tokenKind == tokenKind2) {
                            selectExprMode();
                            TreeMaker treeMakerAt = this.F.at(this.token.pos);
                            if (this.token.kind == tokenKind2) {
                                tag = JCTree.Tag.POSTINC;
                            } else {
                                tag = JCTree.Tag.POSTDEC;
                            }
                            jCExpression = (JCTree.JCExpression) to(treeMakerAt.Unary(tag, jCExpression));
                            nextToken();
                        } else {
                            selectExprMode();
                            TreeMaker treeMakerAt2 = this.F.at(this.token.pos);
                            if (this.token.kind == tokenKind2) {
                                tag = JCTree.Tag.POSTINC;
                            } else {
                                tag = JCTree.Tag.POSTDEC;
                            }
                            jCExpression = (JCTree.JCExpression) to(treeMakerAt2.Unary(tag, jCExpression));
                            nextToken();
                        }
                    }
                    return (JCTree.JCExpression) toP(jCExpression);
                }
                nextToken();
                List<JCTree.JCExpression> listTypeArgumentsOpt = typeArgumentsOpt(1);
                if (this.token.kind == Tokens.TokenKind.SUPER && isMode(1)) {
                    selectExprMode();
                    JCTree.JCExpression jCExpression2 = (JCTree.JCExpression) to(this.F.at(i).Select(jCExpression, this.names._super));
                    nextToken();
                    jCExpression = arguments(listTypeArgumentsOpt, jCExpression2);
                } else if (this.token.kind != Tokens.TokenKind.NEW || !isMode(1)) {
                    List<JCTree.JCAnnotation> listTypeAnnotationsOpt2 = (isMode(2) && this.token.kind == Tokens.TokenKind.MONKEYS_AT) ? typeAnnotationsOpt() : null;
                    jCExpression = (JCTree.JCExpression) toP(this.F.at(i).Select(jCExpression, ident(true)));
                    Tokens.Token token = this.token;
                    if (token.pos <= this.endPosTable.errorEndPos && token.kind == Tokens.TokenKind.MONKEYS_AT) {
                        while (true) {
                            tokenKind = this.token.kind;
                            tokenKind2 = Tokens.TokenKind.PLUSPLUS;
                            if ((tokenKind == tokenKind2 && tokenKind != Tokens.TokenKind.SUBSUB) || !isMode(1)) {
                                break;
                            }
                            selectExprMode();
                            TreeMaker treeMakerAt3 = this.F.at(this.token.pos);
                            if (this.token.kind == tokenKind2) {
                                tag = JCTree.Tag.POSTINC;
                            } else {
                                tag = JCTree.Tag.POSTDEC;
                            }
                            jCExpression = (JCTree.JCExpression) to(treeMakerAt3.Unary(tag, jCExpression));
                            nextToken();
                        }
                        return (JCTree.JCExpression) toP(jCExpression);
                    }
                    if (listTypeAnnotationsOpt2 != null && listTypeAnnotationsOpt2.nonEmpty()) {
                        jCExpression = (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt2.head.pos).AnnotatedType(listTypeAnnotationsOpt2, jCExpression));
                    }
                    jCExpression = argumentsOpt(listTypeArgumentsOpt, typeArgumentsOpt(jCExpression));
                } else {
                    if (listTypeArgumentsOpt != null) {
                        return illegal();
                    }
                    selectExprMode();
                    int i3 = this.token.pos;
                    nextToken();
                    if (this.token.kind == Tokens.TokenKind.LT) {
                        listTypeArgumentsOpt = typeArguments(false);
                    }
                    jCExpression = innerCreator(i3, listTypeArgumentsOpt, jCExpression);
                }
                list = null;
            }
        }
    }

    public JCTree.JCExpression termRest(JCTree.JCExpression jCExpression) {
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[this.token.kind.ordinal()]) {
            case 61:
                int i = this.token.pos;
                nextToken();
                selectExprMode();
                return (JCTree.JCExpression) toP(this.F.at(i).Assign(jCExpression, term()));
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
                Tokens.Token token = this.token;
                int i2 = token.pos;
                Tokens.TokenKind tokenKind = token.kind;
                nextToken();
                selectExprMode();
                return this.F.at(i2).Assignop(optag(tokenKind), jCExpression, term());
            default:
                return jCExpression;
        }
    }

    public <T extends JCTree> T to(T t) {
        return (T) storeEnd(t, this.token.endPos);
    }

    public <T extends JCTree> T toP(T t) {
        return (T) storeEnd(t, this.S.prevToken().endPos);
    }

    public Tokens.Token token() {
        return this.token;
    }

    public List<JCTree.JCAnnotation> typeAnnotationsOpt() {
        return annotationsOpt(JCTree.Tag.TYPE_ANNOTATION);
    }

    public JCTree.JCExpression typeArgument() {
        JCTree.JCExpression jCExpressionErroneous;
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
        Tokens.Token token = this.token;
        if (token.kind != Tokens.TokenKind.QUES) {
            return parseType(false, listTypeAnnotationsOpt);
        }
        int i = token.pos;
        nextToken();
        Tokens.TokenKind tokenKind = this.token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.EXTENDS;
        if (tokenKind == tokenKind2) {
            JCTree.TypeBoundKind typeBoundKind = (JCTree.TypeBoundKind) to(this.F.at(i).TypeBoundKind(BoundKind.EXTENDS));
            nextToken();
            jCExpressionErroneous = this.F.at(i).Wildcard(typeBoundKind, parseType());
        } else {
            Tokens.TokenKind tokenKind3 = Tokens.TokenKind.SUPER;
            if (tokenKind == tokenKind3) {
                JCTree.TypeBoundKind typeBoundKind2 = (JCTree.TypeBoundKind) to(this.F.at(i).TypeBoundKind(BoundKind.SUPER));
                nextToken();
                jCExpressionErroneous = this.F.at(i).Wildcard(typeBoundKind2, parseType());
            } else {
                boolean zTest = this.LAX_IDENTIFIER.test(tokenKind);
                TreeMaker treeMaker = this.F;
                if (zTest) {
                    jCExpressionErroneous = this.F.at(i).Erroneous(List.of((JCTree.JCIdent) toP(this.F.at(i).Wildcard(treeMaker.at(-1).TypeBoundKind(BoundKind.UNBOUND), null)), (JCTree.JCIdent) toP(this.F.at(this.token.pos).Ident(ident()))));
                    reportSyntaxError(jCExpressionErroneous, CompilerProperties.Errors.Expected3(Tokens.TokenKind.GT, tokenKind2, tokenKind3));
                } else {
                    jCExpressionErroneous = (JCTree.JCExpression) toP(this.F.at(i).Wildcard((JCTree.TypeBoundKind) toP(treeMaker.at(i).TypeBoundKind(BoundKind.UNBOUND)), null));
                }
            }
        }
        return !listTypeAnnotationsOpt.isEmpty() ? (JCTree.JCExpression) toP(this.F.at(listTypeAnnotationsOpt.head.pos).AnnotatedType(listTypeAnnotationsOpt, jCExpressionErroneous)) : jCExpressionErroneous;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0087  */
    public List<JCTree.JCExpression> typeArguments(boolean z) {
        Tokens.TokenKind tokenKind;
        Tokens.TokenKind tokenKind2;
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind3 = token.kind;
        Tokens.TokenKind tokenKind4 = Tokens.TokenKind.LT;
        if (tokenKind3 != tokenKind4) {
            return List.of(syntaxError(token.pos, CompilerProperties.Errors.Expected(tokenKind4)));
        }
        nextToken();
        if (this.token.kind == Tokens.TokenKind.GT && z) {
            setMode(this.mode | 16);
            nextToken();
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.append(!isMode(1) ? typeArgument() : parseType());
        while (true) {
            tokenKind = this.token.kind;
            tokenKind2 = Tokens.TokenKind.COMMA;
            if (tokenKind != tokenKind2) {
                break;
            }
            nextToken();
            listBuffer.append(!isMode(1) ? typeArgument() : parseType());
        }
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$parser$Tokens$TokenKind[tokenKind.ordinal()];
        if (i != 71 && i != 72 && i != 94) {
            switch (i) {
                case 89:
                case 90:
                    this.token = this.S.split();
                    break;
                case 91:
                    nextToken();
                    break;
                default:
                    listBuffer.append(syntaxError(this.token.pos, CompilerProperties.Errors.Expected2(Tokens.TokenKind.GT, tokenKind2)));
                    break;
            }
        } else {
            this.token = this.S.split();
        }
        return listBuffer.toList();
    }

    public List<JCTree.JCExpression> typeArgumentsOpt(int i) {
        if (this.token.kind != Tokens.TokenKind.LT) {
            return null;
        }
        if (!isMode(i) || isMode(4)) {
            illegal();
        }
        setMode(i);
        return typeArguments(false);
    }

    public JCTree typeDeclaration(JCTree.JCModifiers jCModifiers, Tokens.Comment comment) {
        Tokens.Token token = this.token;
        int i = token.pos;
        if (jCModifiers != null || token.kind != Tokens.TokenKind.SEMI) {
            return classOrRecordOrInterfaceOrEnumDeclaration(modifiersOpt(jCModifiers), comment);
        }
        nextToken();
        return toP(this.F.at(i).Skip());
    }

    public List<JCTree.JCExpression> typeList() {
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.append(parseType());
        while (this.token.kind == Tokens.TokenKind.COMMA) {
            nextToken();
            listBuffer.append(parseType());
        }
        return listBuffer.toList();
    }

    public Name typeName() {
        int i = this.token.pos;
        Name nameIdent = ident();
        Source sourceRestrictedTypeNameStartingAtSource = restrictedTypeNameStartingAtSource(nameIdent, i, true);
        if (sourceRestrictedTypeNameStartingAtSource != null) {
            reportSyntaxError(i, CompilerProperties.Errors.RestrictedTypeNotAllowed(nameIdent, sourceRestrictedTypeNameStartingAtSource));
        }
        return nameIdent;
    }

    public JCTree.JCTypeParameter typeParameter() {
        int i = this.token.pos;
        List<JCTree.JCAnnotation> listTypeAnnotationsOpt = typeAnnotationsOpt();
        Name nameTypeName = typeName();
        ListBuffer listBuffer = new ListBuffer();
        if (this.token.kind == Tokens.TokenKind.EXTENDS) {
            nextToken();
            listBuffer.append(parseType());
            while (this.token.kind == Tokens.TokenKind.AMP) {
                nextToken();
                listBuffer.append(parseType());
            }
        }
        return (JCTree.JCTypeParameter) toP(this.F.at(i).TypeParameter(nameTypeName, listBuffer.toList(), listTypeAnnotationsOpt));
    }

    public List<JCTree.JCTypeParameter> typeParametersOpt(boolean z) {
        if (this.token.kind != Tokens.TokenKind.LT) {
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        nextToken();
        if (z) {
            Tokens.TokenKind tokenKind = this.token.kind;
            Tokens.TokenKind tokenKind2 = Tokens.TokenKind.GT;
            if (tokenKind == tokenKind2) {
                accept(tokenKind2);
                return null;
            }
        }
        listBuffer.append(typeParameter());
        while (this.token.kind == Tokens.TokenKind.COMMA) {
            nextToken();
            listBuffer.append(typeParameter());
        }
        accept(Tokens.TokenKind.GT);
        return listBuffer.toList();
    }

    public JCTree.JCExpression unannotatedType(boolean z, int i) {
        JCTree.JCExpression jCExpressionTerm = term(i);
        Name nameRestrictedTypeName = restrictedTypeName(jCExpressionTerm, !z);
        if (nameRestrictedTypeName != null && (!z || nameRestrictedTypeName != this.names.var)) {
            syntaxError(jCExpressionTerm.pos, CompilerProperties.Errors.RestrictedTypeNotAllowedHere(nameRestrictedTypeName));
        }
        return (this.lastmode & 2) == 0 ? this.F.Erroneous(List.of(jCExpressionTerm)) : jCExpressionTerm;
    }

    public JCTree.JCVariableDecl variableDeclarator(JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, boolean z, Tokens.Comment comment, boolean z2) {
        return variableDeclaratorRest(this.token.pos, jCModifiers, jCExpression, identOrUnderscore(), z, comment, z2, true);
    }

    public JCTree.JCVariableDecl variableDeclaratorId(JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, boolean z, boolean z2, boolean z3) {
        Name name;
        Name name2;
        JCTree.JCVariableDecl.DeclKind declKind;
        JCTree.JCExpression jCExpression2 = jCExpression;
        Tokens.Token token = this.token;
        int i = token.pos;
        if (this.allowThisIdent || !z2 || this.LAX_IDENTIFIER.test(token.kind) || jCModifiers.flags != 8589934592L || jCModifiers.annotations.nonEmpty()) {
            Tokens.Token token2 = this.token;
            JCTree.JCExpression jCExpressionQualident = (token2.kind == Tokens.TokenKind.UNDERSCORE && (z || z2)) ? (JCTree.JCExpression) toP(this.F.at(token2.pos).Ident(identOrUnderscore())) : qualident(false);
            if (jCExpressionQualident.hasTag(JCTree.Tag.IDENT) && (name2 = ((JCTree.JCIdent) jCExpressionQualident).name) != this.names._this) {
                name = name2;
            } else {
                if (!z2 || jCExpression2 != null) {
                    if (this.allowThisIdent) {
                        if ((Flags.VARARGS & jCModifiers.flags) != 0) {
                            this.log.error(this.token.pos, CompilerProperties.Errors.VarargsAndReceiver);
                        }
                        Tokens.Token token3 = this.token;
                        if (token3.kind == Tokens.TokenKind.LBRACKET) {
                            this.log.error(token3.pos, CompilerProperties.Errors.ArrayAndReceiver);
                        }
                        if (jCExpressionQualident.hasTag(JCTree.Tag.SELECT) && ((JCTree.JCFieldAccess) jCExpressionQualident).name != this.names._this) {
                            this.log.error(this.token.pos, CompilerProperties.Errors.WrongReceiver);
                        }
                    }
                    return (JCTree.JCVariableDecl) toP(this.F.at(i).ReceiverVarDef(jCModifiers, jCExpressionQualident, jCExpression2));
                }
                Name name3 = this.names.empty;
                reportSyntaxError(i, CompilerProperties.Errors.Expected(Tokens.TokenKind.IDENTIFIER));
                JCTree.JCExpression jCExpression3 = jCExpressionQualident;
                name = name3;
                jCExpression2 = jCExpression3;
            }
        } else {
            name = this.names.error;
        }
        if ((Flags.VARARGS & jCModifiers.flags) != 0) {
            Tokens.Token token4 = this.token;
            if (token4.kind == Tokens.TokenKind.LBRACKET) {
                this.log.error(token4.pos, CompilerProperties.Errors.VarargsAndOldArraySyntax);
            }
        }
        if (z3) {
            Tokens.Token token5 = this.token;
            if (token5.kind == Tokens.TokenKind.LBRACKET) {
                this.log.error(token5.pos, CompilerProperties.Errors.RecordComponentAndOldArraySyntax);
            }
        }
        JCTree.JCExpression jCExpressionBracketsOpt = bracketsOpt(jCExpression2);
        if (Source.Feature.UNNAMED_VARIABLES.allowedInSource(this.source)) {
            Names names = this.names;
            if (name == names.underscore) {
                name = names.empty;
            }
        }
        if (jCExpressionBracketsOpt != null && jCExpressionBracketsOpt.hasTag(JCTree.Tag.IDENT) && ((JCTree.JCIdent) jCExpressionBracketsOpt).name == this.names.var) {
            declKind = JCTree.JCVariableDecl.DeclKind.VAR;
        } else {
            declKind = jCExpressionBracketsOpt != null ? JCTree.JCVariableDecl.DeclKind.EXPLICIT : JCTree.JCVariableDecl.DeclKind.IMPLICIT;
        }
        return (JCTree.JCVariableDecl) toP(this.F.at(i).VarDef(jCModifiers, name, jCExpressionBracketsOpt, null, declKind, jCExpressionBracketsOpt != null ? jCExpressionBracketsOpt.pos : i));
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00bd  */
    public JCTree.JCVariableDecl variableDeclaratorRest(int i, JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, Name name, boolean z, Tokens.Comment comment, boolean z2, boolean z3) {
        Name name2;
        JCTree.JCExpression jCExpressionVariableInitializer;
        int i2;
        JCTree.JCExpression jCExpressionBracketsOpt = bracketsOpt(jCExpression);
        Source.Feature feature = Source.Feature.UNNAMED_VARIABLES;
        if (feature.allowedInSource(this.source) && name == this.names.underscore) {
            if (!z2) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, i, CompilerProperties.Errors.UseOfUnderscoreNotAllowed);
            }
            name2 = this.names.empty;
        } else {
            name2 = name;
        }
        saveDanglingDocComments(comment);
        Tokens.Token token = this.token;
        Tokens.TokenKind tokenKind = token.kind;
        Tokens.TokenKind tokenKind2 = Tokens.TokenKind.EQ;
        JCTree.JCExpression jCExpression2 = null;
        if (tokenKind == tokenKind2) {
            nextToken();
            jCExpressionVariableInitializer = variableInitializer();
        } else {
            if (z) {
                syntaxError(token.pos, CompilerProperties.Errors.Expected(tokenKind2));
            }
            jCExpressionVariableInitializer = null;
        }
        if (feature.allowedInSource(this.source) && name2 == this.names.empty && z2 && jCExpressionVariableInitializer == null) {
            Tokens.Token token2 = this.token;
            if (token2.kind != Tokens.TokenKind.COLON) {
                syntaxError(token2.pos, CompilerProperties.Errors.Expected(tokenKind2));
            }
        }
        boolean z4 = true;
        JCTree jCTreeInnermostType = TreeInfo.innermostType(jCExpressionBracketsOpt, true);
        if (jCTreeInnermostType.hasTag(JCTree.Tag.IDENT)) {
            Name name3 = ((JCTree.JCIdent) jCTreeInnermostType).name;
            if (restrictedTypeNameStartingAtSource(name3, i, !z3 && z2) == null) {
                jCExpression2 = jCExpressionBracketsOpt;
                i2 = -1;
                z4 = false;
            } else {
                if (name3 != this.names.var) {
                    reportSyntaxError(jCTreeInnermostType.pos, CompilerProperties.Errors.RestrictedTypeNotAllowedHere(name3));
                } else if (!jCExpressionBracketsOpt.hasTag(JCTree.Tag.TYPEARRAY) || z3) {
                    int i3 = jCTreeInnermostType.pos;
                    if (z3) {
                        reportSyntaxError(i3, CompilerProperties.Errors.RestrictedTypeNotAllowedCompound(name3));
                    }
                    i2 = i3;
                } else {
                    reportSyntaxError(jCTreeInnermostType.pos, CompilerProperties.Errors.RestrictedTypeNotAllowedArray(name3));
                }
                jCExpression2 = jCExpressionBracketsOpt;
                i2 = -1;
                z4 = false;
            }
        } else {
            jCExpression2 = jCExpressionBracketsOpt;
            i2 = -1;
            z4 = false;
        }
        return (JCTree.JCVariableDecl) attach((JCTree.JCVariableDecl) toP(this.F.at(i).VarDef(jCModifiers, name2, jCExpression2, jCExpressionVariableInitializer, z4 ? JCTree.JCVariableDecl.DeclKind.VAR : JCTree.JCVariableDecl.DeclKind.EXPLICIT, i2)), comment);
    }

    public <T extends ListBuffer<? super JCTree.JCVariableDecl>> T variableDeclarators(JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, T t, boolean z) {
        return (T) variableDeclaratorsRest(this.token.pos, jCModifiers, jCExpression, identOrUnderscore(), false, null, t, z);
    }

    public <T extends ListBuffer<? super JCTree.JCVariableDecl>> T variableDeclaratorsRest(int i, JCTree.JCModifiers jCModifiers, JCTree.JCExpression jCExpression, Name name, boolean z, Tokens.Comment comment, T t, boolean z2) {
        t.append(variableDeclaratorRest(i, jCModifiers, jCExpression, name, z, comment, z2, false));
        while (this.token.kind == Tokens.TokenKind.COMMA) {
            storeEnd((JCTree) t.last(), this.token.endPos);
            nextToken();
            t.append(variableDeclarator(jCModifiers, jCExpression, z, comment, z2));
        }
        return t;
    }

    public JCTree.JCExpression variableInitializer() {
        Tokens.Token token = this.token;
        return token.kind == Tokens.TokenKind.LBRACE ? arrayInitializer(token.pos, null) : parseExpression();
    }

    public boolean wasTypeMode() {
        return (this.lastmode & 2) != 0;
    }

    public JCTree.JCExpression parseType(boolean z) {
        return parseType(z, typeAnnotationsOpt());
    }

    @Override // com.sun.tools.javac.parser.Parser
    public JCTree.JCExpression parseType() {
        return parseType(false);
    }

    public void checkNoMods(long j) {
        checkNoMods(this.token.pos, j);
    }

    public JCTree.JCExpression illegal() {
        return illegal(this.token.pos);
    }

    public boolean isDeclaration() {
        return isDeclaration(this.allowRecords);
    }

    public void accept(Tokens.TokenKind tokenKind) {
        accept(tokenKind, new Function() { // from class: sm7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CompilerProperties.Errors.Expected((Tokens.TokenKind) obj);
            }
        });
    }

    public List<JCTree.JCExpression> typeArgumentsOpt() {
        return typeArgumentsOpt(2);
    }

    public JCTree.JCErroneous syntaxError(int i, List<? extends JCTree> list, JCDiagnostic.Error error) {
        return syntaxError(i, list, error, false);
    }

    public JCTree.JCExpression typeArgumentsOpt(JCTree.JCExpression jCExpression) {
        if (this.token.kind != Tokens.TokenKind.LT || !isMode(2) || isMode(4)) {
            return jCExpression;
        }
        selectTypeMode();
        return typeArguments(jCExpression, false);
    }

    public JCTree.JCErroneous syntaxError(int i, JCDiagnostic.Error error) {
        return syntaxError(i, List.nil(), error);
    }

    public JCTree.JCExpression term(int i) {
        int i2 = this.mode;
        setMode(i);
        JCTree.JCExpression jCExpressionTerm = term();
        setLastMode(this.mode);
        setMode(i2);
        return jCExpressionTerm;
    }

    public JCTree.JCExpression unannotatedType(boolean z) {
        return unannotatedType(z, 2);
    }

    public boolean peekToken(int i, Predicate<Tokens.TokenKind> predicate) {
        return predicate.test(this.S.token(i + 1).kind);
    }

    public boolean peekToken(Predicate<Tokens.TokenKind> predicate, Predicate<Tokens.TokenKind> predicate2) {
        return peekToken(0, predicate, predicate2);
    }

    public boolean peekToken(int i, Predicate<Tokens.TokenKind> predicate, Predicate<Tokens.TokenKind> predicate2) {
        return predicate.test(this.S.token(i + 1).kind) && predicate2.test(this.S.token(i + 2).kind);
    }

    public boolean peekToken(Predicate<Tokens.TokenKind> predicate, Predicate<Tokens.TokenKind> predicate2, Predicate<Tokens.TokenKind> predicate3) {
        return peekToken(0, predicate, predicate2, predicate3);
    }

    public boolean peekToken(Predicate<Tokens.TokenKind> predicate) {
        return peekToken(0, predicate);
    }

    public boolean peekToken(Predicate<Tokens.TokenKind>... predicateArr) {
        return peekToken(0, predicateArr);
    }

    public boolean peekToken(int i, Predicate<Tokens.TokenKind>... predicateArr) {
        for (Predicate<Tokens.TokenKind> predicate : predicateArr) {
            i++;
            if (!predicate.test(this.S.token(i).kind)) {
                return false;
            }
        }
        return true;
    }

    public JCTree.JCExpression memberReferenceSuffix(JCTree.JCExpression jCExpression) {
        int i = this.token.pos;
        accept(Tokens.TokenKind.COLCOL);
        return memberReferenceSuffix(i, jCExpression);
    }

    public JCTree.JCBlock block() {
        return block(this.token.pos, 0L);
    }

    public void checkSourceLevel(Source.Feature feature) {
        checkSourceLevel(this.token.pos, feature);
    }

    public List<JCTree.JCTypeParameter> typeParametersOpt() {
        return typeParametersOpt(false);
    }

    private JCTree.JCExpression bracketsOpt(JCTree.JCExpression jCExpression) {
        return bracketsOpt(jCExpression, List.nil());
    }

    public JCTree.JCExpression arguments(List<JCTree.JCExpression> list, JCTree.JCExpression jCExpression) {
        int i = this.token.pos;
        Cloneable cloneableApply = this.F.at(i).Apply(list, jCExpression, arguments());
        if (jCExpression.hasTag(JCTree.Tag.IDENT)) {
            JCTree.JCIdent jCIdent = (JCTree.JCIdent) jCExpression;
            if (isInvalidUnqualifiedMethodIdentifier(jCIdent.pos, jCIdent.name)) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SYNTAX, jCExpression, CompilerProperties.Errors.InvalidYield);
                cloneableApply = this.F.Erroneous(List.of(cloneableApply));
            }
        }
        return (JCTree.JCExpression) toP(cloneableApply);
    }

    public void reportSyntaxError(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Error error) {
        reportSyntaxError(diagnosticPosition, error, false);
    }

    public void reportSyntaxError(int i, JCDiagnostic.Error error) {
        reportSyntaxError(new JCDiagnostic.SimpleDiagnosticPosition(i), error);
    }

    public List<JCTree.JCVariableDecl> formalParameters() {
        return formalParameters(false, false);
    }

    public JavacParser(ParserFactory parserFactory, Lexer lexer, boolean z, boolean z2, boolean z3) {
        this(parserFactory, lexer, z, z2, z3, false);
    }

    public JavacParser(JavacParser javacParser, Lexer lexer) {
        this.danglingComments = new HashMap();
        this.typeAnnotationsPushedBack = List.nil();
        this.permitTypeAnnotationsPushBack = false;
        this.mode = 0;
        this.lastmode = 0;
        this.errorPos = -1;
        this.count = 0;
        this.odStackSupply = new ArrayList<>();
        this.opStackSupply = new ArrayList<>();
        this.LAX_IDENTIFIER = new Predicate() { // from class: dn7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return JavacParser.a((Tokens.TokenKind) obj);
            }
        };
        this.S = lexer;
        this.token = javacParser.token;
        TreeMaker treeMaker = javacParser.F;
        this.F = treeMaker;
        this.log = javacParser.log;
        this.names = javacParser.names;
        this.source = javacParser.source;
        this.preview = javacParser.preview;
        this.allowStringFolding = javacParser.allowStringFolding;
        this.keepDocComments = javacParser.keepDocComments;
        this.parseModuleInfo = false;
        this.docComments = javacParser.docComments;
        this.errorTree = treeMaker.Erroneous();
        this.endPosTable = newEndPosTable(false);
        this.allowYieldStatement = Source.Feature.SWITCH_EXPRESSION.allowedInSource(this.source);
        this.allowRecords = Source.Feature.RECORDS.allowedInSource(this.source);
        this.allowSealedTypes = Source.Feature.SEALED_CLASSES.allowedInSource(this.source);
        updateUnexpectedTopLevelDefinitionStartError(false);
    }

    public JCTree.JCTypeApply typeArguments(JCTree.JCExpression jCExpression, boolean z) {
        int i = this.token.pos;
        return (JCTree.JCTypeApply) toP(this.F.at(i).TypeApply(jCExpression, typeArguments(z)));
    }

    public Name ident(boolean z) {
        return ident(z, false);
    }

    public Name ident() {
        return ident(false);
    }

    public JCTree.JCModifiers modifiersOpt() {
        return modifiersOpt(null);
    }

    public JCTree.JCExpression literal(Name name) {
        return literal(name, this.token.pos);
    }
}
