package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002 !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J1\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010\u001fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;", Argument.Delimiters.none, "<init>", "()V", "inlinedScopes", Argument.Delimiters.none, "getInlinedScopes", "()I", "setInlinedScopes", "(I)V", "currentCallSiteLineNumber", "getCurrentCallSiteLineNumber", "setCurrentCallSiteLineNumber", "addInlineScopesInfo", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "isRegeneratingAnonymousObject", Argument.Delimiters.none, "addInlineScopesInfoFromScopeNumbers", "addInlineScopesInfoFromIVSuffixes", "addInlineScopesInfoFromIVSuffixesWhenRegeneratingAnonymousObject", "computeSurroundingScopeNumber", "currentNode", "Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator$InlineScopeNode;", "computeNewVariableName", Argument.Delimiters.none, ModuleXmlParser.NAME, "scopeNumber", "callSiteLineNumber", "surroundingScopeNumber", "(Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "InlineScopeNode", "VariableRenamer", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineScopesGenerator {
    private int currentCallSiteLineNumber;
    private int inlinedScopes;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\r\"\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00000\u001d¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b\"\u0010$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator$InlineScopeNode;", Argument.Delimiters.none, "markerVariable", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "scopeNumber", Argument.Delimiters.none, "inlineNesting", "parent", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;IILorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator$InlineScopeNode;)V", "getMarkerVariable", "()Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "getScopeNumber", "()I", "getInlineNesting", "setInlineNesting", "(I)V", "getParent", "()Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator$InlineScopeNode;", "callSiteLineNumber", "getCallSiteLineNumber", "()Ljava/lang/Integer;", "setCallSiteLineNumber", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "surroundingScopeNumber", "getSurroundingScopeNumber", "setSurroundingScopeNumber", "variables", Argument.Delimiters.none, "getVariables", "()Ljava/util/List;", "children", "getChildren", "isRoot", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InlineScopeNode {
        private Integer callSiteLineNumber;
        private int inlineNesting;
        private final LocalVariableNode markerVariable;
        private final InlineScopeNode parent;
        private final int scopeNumber;
        private Integer surroundingScopeNumber;
        private final List<LocalVariableNode> variables = new ArrayList();
        private final List<InlineScopeNode> children = new ArrayList();

        public InlineScopeNode(LocalVariableNode localVariableNode, int i, int i2, InlineScopeNode inlineScopeNode) {
            List<InlineScopeNode> list;
            this.markerVariable = localVariableNode;
            this.scopeNumber = i;
            this.inlineNesting = i2;
            this.parent = inlineScopeNode;
            if (inlineScopeNode == null || (list = inlineScopeNode.children) == null) {
                return;
            }
            list.add(this);
        }

        public final Integer getCallSiteLineNumber() {
            return this.callSiteLineNumber;
        }

        public final List<InlineScopeNode> getChildren() {
            return this.children;
        }

        public final int getInlineNesting() {
            return this.inlineNesting;
        }

        public final LocalVariableNode getMarkerVariable() {
            return this.markerVariable;
        }

        public final InlineScopeNode getParent() {
            return this.parent;
        }

        public final int getScopeNumber() {
            return this.scopeNumber;
        }

        public final Integer getSurroundingScopeNumber() {
            return this.surroundingScopeNumber;
        }

        public final List<LocalVariableNode> getVariables() {
            return this.variables;
        }

        public final boolean isRoot() {
            return this.parent == null;
        }

        public final void setCallSiteLineNumber(Integer num) {
            this.callSiteLineNumber = num;
        }

        public final void setInlineNesting(int i) {
            this.inlineNesting = i;
        }

        public final void setSurroundingScopeNumber(Integer num) {
            this.surroundingScopeNumber = num;
        }
    }

    private final void addInlineScopesInfoFromIVSuffixes(MethodNode node) {
        final Map labelToLineNumberMap = InlineScopesGeneratorKt.getLabelToLineNumberMap(node);
        this.inlinedScopes += new VariableRenamer() { // from class: org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator$addInlineScopesInfoFromIVSuffixes$renamer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public boolean belongsToInlineScope(LocalVariableNode localVariableNode, InlineScopesGenerator.InlineScopeNode inlineScopeNode) {
                localVariableNode.getClass();
                inlineScopeNode.getClass();
                if (inlineScopeNode.isRoot()) {
                    return false;
                }
                String str = localVariableNode.name;
                str.getClass();
                return InlineScopesGeneratorKt.getInlineDepth(str) == inlineScopeNode.getInlineNesting();
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public void computeInlineScopeInfo(InlineScopesGenerator.InlineScopeNode node2) {
                Integer numValueOf;
                node2.getClass();
                LocalVariableNode markerVariable = node2.getMarkerVariable();
                markerVariable.getClass();
                String str = markerVariable.name;
                int inlineNesting = node2.getInlineNesting();
                int scopeNumber = node2.getScopeNumber();
                str.getClass();
                node2.setInlineNesting(InlineScopesGeneratorKt.isInlineLambdaName(str) ? InlineScopesGeneratorKt.getInlineDepth(str) : inlineNesting + 1);
                if (scopeNumber == 1) {
                    numValueOf = Integer.valueOf(this.this$0.getCurrentCallSiteLineNumber());
                } else {
                    Integer num = labelToLineNumberMap.get(markerVariable.start.getLabel());
                    numValueOf = Integer.valueOf(num != null ? num.intValue() : 0);
                }
                node2.setCallSiteLineNumber(numValueOf);
                if (InlineScopesGeneratorKt.isInlineLambdaName(str)) {
                    node2.setSurroundingScopeNumber(Integer.valueOf(this.this$0.computeSurroundingScopeNumber(node2)));
                }
            }
        }.renameVariables(node);
    }

    private final void addInlineScopesInfoFromIVSuffixesWhenRegeneratingAnonymousObject(MethodNode node) {
        final Map labelToLineNumberMap = InlineScopesGeneratorKt.getLabelToLineNumberMap(node);
        new VariableRenamer() { // from class: org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator$addInlineScopesInfoFromIVSuffixesWhenRegeneratingAnonymousObject$renamer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public boolean belongsToInlineScope(LocalVariableNode localVariableNode, InlineScopesGenerator.InlineScopeNode inlineScopeNode) {
                localVariableNode.getClass();
                inlineScopeNode.getClass();
                if (inlineScopeNode.isRoot()) {
                    return false;
                }
                String str = localVariableNode.name;
                str.getClass();
                return InlineScopesGeneratorKt.getInlineDepth(str) == inlineScopeNode.getInlineNesting();
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public void computeInlineScopeInfo(InlineScopesGenerator.InlineScopeNode node2) {
                node2.getClass();
                LocalVariableNode markerVariable = node2.getMarkerVariable();
                markerVariable.getClass();
                int inlineNesting = node2.getInlineNesting();
                String str = markerVariable.name;
                str.getClass();
                node2.setInlineNesting(InlineScopesGeneratorKt.isInlineLambdaName(str) ? InlineScopesGeneratorKt.getInlineDepth(str) : inlineNesting + 1);
                Integer num = labelToLineNumberMap.get(markerVariable.start.getLabel());
                if (num == null) {
                    num = 0;
                }
                node2.setCallSiteLineNumber(num);
                if (InlineScopesGeneratorKt.isInlineLambdaName(str)) {
                    node2.setSurroundingScopeNumber(Integer.valueOf(this.this$0.computeSurroundingScopeNumber(node2)));
                }
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public int inlineNesting() {
                return 0;
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public boolean shouldSkipVariable(LocalVariableNode variable) {
                variable.getClass();
                String str = variable.name;
                str.getClass();
                return !StringsKt.contains$default(str, InlineCodegenUtilsKt.INLINE_FUN_VAR_SUFFIX, false, 2, (Object) null);
            }
        }.renameVariables(node);
    }

    private final void addInlineScopesInfoFromScopeNumbers(MethodNode node) {
        this.inlinedScopes += new VariableRenamer() { // from class: org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator$addInlineScopesInfoFromScopeNumbers$renamer$1
            {
                super();
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public boolean belongsToInlineScope(LocalVariableNode localVariableNode, InlineScopesGenerator.InlineScopeNode inlineScopeNode) {
                localVariableNode.getClass();
                inlineScopeNode.getClass();
                String str = localVariableNode.name;
                str.getClass();
                InlineScopeInfo inlineScopeInfo = InlineScopeUtilsKt.getInlineScopeInfo(str);
                Integer numValueOf = inlineScopeInfo != null ? Integer.valueOf(inlineScopeInfo.getScopeNumber()) : null;
                int inlineNesting = inlineScopeNode.getInlineNesting();
                if (numValueOf != null) {
                    return numValueOf.intValue() == inlineNesting;
                }
                return !inlineScopeNode.isRoot();
            }

            @Override // org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator.VariableRenamer
            public void computeInlineScopeInfo(InlineScopesGenerator.InlineScopeNode node2) {
                Integer numValueOf;
                Integer callSiteLineNumber;
                int iValueOf;
                node2.getClass();
                LocalVariableNode markerVariable = node2.getMarkerVariable();
                markerVariable.getClass();
                String str = markerVariable.name;
                int scopeNumber = node2.getScopeNumber();
                str.getClass();
                InlineScopeInfo inlineScopeInfo = InlineScopeUtilsKt.getInlineScopeInfo(str);
                node2.setInlineNesting(inlineScopeInfo != null ? inlineScopeInfo.getScopeNumber() : 0);
                if (scopeNumber == 1) {
                    numValueOf = Integer.valueOf(this.this$0.getCurrentCallSiteLineNumber());
                } else {
                    numValueOf = Integer.valueOf((inlineScopeInfo == null || (callSiteLineNumber = inlineScopeInfo.getCallSiteLineNumber()) == null) ? 0 : callSiteLineNumber.intValue());
                }
                node2.setCallSiteLineNumber(numValueOf);
                if (InlineScopesGeneratorKt.isInlineLambdaName(str)) {
                    Integer surroundingScopeNumber = inlineScopeInfo != null ? inlineScopeInfo.getSurroundingScopeNumber() : null;
                    if (scopeNumber == 1) {
                        iValueOf = 0;
                    } else {
                        iValueOf = surroundingScopeNumber != null ? Integer.valueOf(surroundingScopeNumber.intValue() + this.this$0.getInlinedScopes() + 1) : -1;
                    }
                    node2.setSurroundingScopeNumber(iValueOf);
                }
            }
        }.renameVariables(node);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String computeNewVariableName(String name, int scopeNumber, Integer callSiteLineNumber, Integer surroundingScopeNumber) {
        String strDropInlineScopeInfo = InlineScopeUtilsKt.dropInlineScopeInfo(StringsKt.replace$default(name, InlineCodegenUtilsKt.INLINE_FUN_VAR_SUFFIX, Argument.Delimiters.none, false, 4, (Object) null));
        StringBuilder sb = new StringBuilder();
        sb.append(strDropInlineScopeInfo);
        sb.append(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR);
        sb.append(scopeNumber);
        if (callSiteLineNumber != null) {
            sb.append(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR);
            sb.append(callSiteLineNumber.intValue());
            if (surroundingScopeNumber != null) {
                sb.append(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR);
                sb.append(surroundingScopeNumber.intValue());
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x0030  */
    /* JADX WARN: Code duplicated, block: B:17:0x0038 A[RETURN] */
    public final int computeSurroundingScopeNumber(InlineScopeNode currentNode) {
        Integer numValueOf;
        int scopeNumber = currentNode.getScopeNumber();
        int inlineNesting = currentNode.getInlineNesting();
        if (scopeNumber == 1) {
            return 0;
        }
        for (InlineScopeNode parent = currentNode.getParent(); parent != null && !parent.isRoot(); parent = parent.getParent()) {
            if (parent.getInlineNesting() == inlineNesting) {
                numValueOf = Integer.valueOf(parent.getScopeNumber());
                if (numValueOf != null) {
                    return numValueOf.intValue() + this.inlinedScopes;
                }
                return 0;
            }
        }
        numValueOf = null;
        if (numValueOf != null) {
            return numValueOf.intValue() + this.inlinedScopes;
        }
        return 0;
    }

    public final void addInlineScopesInfo(MethodNode node, boolean isRegeneratingAnonymousObject) {
        node.getClass();
        List list = node.localVariables;
        if (list == null || !list.isEmpty()) {
            list.getClass();
            List<LocalVariableNode> list2 = list;
            int i = 0;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                int i2 = 0;
                for (LocalVariableNode localVariableNode : list2) {
                    String str = localVariableNode.name;
                    str.getClass();
                    if (JvmAbi.isFakeLocalVariableForInline(str)) {
                        String str2 = localVariableNode.name;
                        str2.getClass();
                        if (!StringsKt.contains$default(str2, InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR, false, 2, (Object) null) && (i2 = i2 + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                i = i2;
            }
            if (isRegeneratingAnonymousObject) {
                if (i > 0) {
                    addInlineScopesInfoFromIVSuffixesWhenRegeneratingAnonymousObject(node);
                }
            } else if (i == 1) {
                addInlineScopesInfoFromScopeNumbers(node);
            } else {
                addInlineScopesInfoFromIVSuffixes(node);
            }
        }
    }

    public final int getCurrentCallSiteLineNumber() {
        return this.currentCallSiteLineNumber;
    }

    public final int getInlinedScopes() {
        return this.inlinedScopes;
    }

    public final void setCurrentCallSiteLineNumber(int i) {
        this.currentCallSiteLineNumber = i;
    }

    public final void setInlinedScopes(int i) {
        this.inlinedScopes = i;
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\b¢\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0014\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J(\u0010\u0014\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\f\u001a\u00020\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016H\u0002¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator$VariableRenamer;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;)V", "computeInlineScopeInfo", Argument.Delimiters.none, "node", "Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator$InlineScopeNode;", "belongsToInlineScope", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "shouldSkipVariable", "variable", "inlineNesting", Argument.Delimiters.none, "renameVariables", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "computeInlineScopesTree", "rootNode", "findClosestSurroundingScope", "labelToIndex", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Label;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public abstract class VariableRenamer {
        public VariableRenamer() {
        }

        private final InlineScopeNode computeInlineScopesTree(MethodNode methodNode) {
            int i = 0;
            InlineScopeNode inlineScopeNode = new InlineScopeNode(null, 0, inlineNesting(), null);
            List list = methodNode.localVariables;
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                final Map<Label, Integer> labelToIndexMap = InlineScopesGeneratorKt.getLabelToIndexMap(methodNode);
                InlineScopeNode inlineScopeNodeFindClosestSurroundingScope = inlineScopeNode;
                for (LocalVariableNode localVariableNode : CollectionsKt.sortedWith(list, new Comparator() { // from class: org.jetbrains.kotlin.codegen.inline.InlineScopesGenerator$VariableRenamer$computeInlineScopesTree$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues((Integer) labelToIndexMap.get(((LocalVariableNode) t).start.getLabel()), (Integer) labelToIndexMap.get(((LocalVariableNode) t2).start.getLabel()));
                    }
                })) {
                    localVariableNode.getClass();
                    inlineScopeNodeFindClosestSurroundingScope = findClosestSurroundingScope(inlineScopeNodeFindClosestSurroundingScope, localVariableNode, labelToIndexMap);
                    String str = localVariableNode.name;
                    str.getClass();
                    if (JvmAbi.isFakeLocalVariableForInline(str)) {
                        i++;
                        InlineScopeNode inlineScopeNode2 = new InlineScopeNode(localVariableNode, i, inlineScopeNodeFindClosestSurroundingScope.getInlineNesting(), inlineScopeNodeFindClosestSurroundingScope);
                        computeInlineScopeInfo(inlineScopeNode2);
                        inlineScopeNode2.getVariables().addAll(arrayList);
                        arrayList.clear();
                        inlineScopeNodeFindClosestSurroundingScope = inlineScopeNode2;
                    } else if (!inlineScopeNodeFindClosestSurroundingScope.isRoot() || !shouldSkipVariable(localVariableNode)) {
                        if (belongsToInlineScope(localVariableNode, inlineScopeNodeFindClosestSurroundingScope)) {
                            inlineScopeNodeFindClosestSurroundingScope.getVariables().add(localVariableNode);
                        } else {
                            arrayList.add(localVariableNode);
                        }
                    }
                }
            }
            return inlineScopeNode;
        }

        private final InlineScopeNode findClosestSurroundingScope(InlineScopeNode inlineScopeNode, LocalVariableNode localVariableNode, Map<Label, Integer> map) {
            while (!inlineScopeNode.isRoot()) {
                LocalVariableNode markerVariable = inlineScopeNode.getMarkerVariable();
                markerVariable.getClass();
                if (findClosestSurroundingScope$contains(markerVariable, map, localVariableNode)) {
                    break;
                }
                inlineScopeNode = inlineScopeNode.getParent();
                inlineScopeNode.getClass();
            }
            return inlineScopeNode;
        }

        private static final boolean findClosestSurroundingScope$contains(LocalVariableNode localVariableNode, Map<Label, Integer> map, LocalVariableNode localVariableNode2) {
            Integer num = map.get(localVariableNode.start.getLabel());
            if (num != null) {
                int iIntValue = num.intValue();
                Integer num2 = map.get(localVariableNode.end.getLabel());
                if (num2 != null) {
                    int iIntValue2 = num2.intValue();
                    Integer num3 = map.get(localVariableNode2.start.getLabel());
                    if (num3 != null) {
                        int iIntValue3 = num3.intValue();
                        Integer num4 = map.get(localVariableNode2.end.getLabel());
                        if (num4 != null) {
                            int iIntValue4 = num4.intValue();
                            if (iIntValue < iIntValue3 && iIntValue2 >= iIntValue4) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        private final int renameVariables(InlineScopeNode rootNode) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(rootNode.getChildren());
            int i = 0;
            while (!arrayList.isEmpty()) {
                InlineScopeNode inlineScopeNode = (InlineScopeNode) CollectionsKt.removeLast(arrayList);
                i++;
                InlineScopesGenerator inlineScopesGenerator = InlineScopesGenerator.this;
                LocalVariableNode markerVariable = inlineScopeNode.getMarkerVariable();
                markerVariable.getClass();
                String str = inlineScopeNode.getMarkerVariable().name;
                str.getClass();
                markerVariable.name = inlineScopesGenerator.computeNewVariableName(str, inlineScopeNode.getScopeNumber() + inlineScopesGenerator.getInlinedScopes(), inlineScopeNode.getCallSiteLineNumber(), inlineScopeNode.getSurroundingScopeNumber());
                for (LocalVariableNode localVariableNode : inlineScopeNode.getVariables()) {
                    InlineScopesGenerator inlineScopesGenerator2 = InlineScopesGenerator.this;
                    String str2 = localVariableNode.name;
                    str2.getClass();
                    localVariableNode.name = inlineScopesGenerator2.computeNewVariableName(str2, inlineScopeNode.getScopeNumber() + InlineScopesGenerator.this.getInlinedScopes(), null, null);
                }
                arrayList.addAll(inlineScopeNode.getChildren());
            }
            return i;
        }

        public abstract boolean belongsToInlineScope(LocalVariableNode localVariableNode, InlineScopeNode inlineScopeNode);

        public abstract void computeInlineScopeInfo(InlineScopeNode node);

        public int inlineNesting() {
            return -1;
        }

        public boolean shouldSkipVariable(LocalVariableNode variable) {
            variable.getClass();
            return false;
        }

        public final int renameVariables(MethodNode methodNode) {
            methodNode.getClass();
            return renameVariables(computeInlineScopesTree(methodNode));
        }
    }
}
