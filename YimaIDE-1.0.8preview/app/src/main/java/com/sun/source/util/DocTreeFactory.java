package com.sun.source.util;

import com.sun.source.doctree.AttributeTree;
import com.sun.source.doctree.AuthorTree;
import com.sun.source.doctree.CommentTree;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocRootTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.DocTypeTree;
import com.sun.source.doctree.EndElementTree;
import com.sun.source.doctree.EntityTree;
import com.sun.source.doctree.ErroneousTree;
import com.sun.source.doctree.EscapeTree;
import com.sun.source.doctree.HiddenTree;
import com.sun.source.doctree.IdentifierTree;
import com.sun.source.doctree.IndexTree;
import com.sun.source.doctree.InheritDocTree;
import com.sun.source.doctree.LinkTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.ParamTree;
import com.sun.source.doctree.ProvidesTree;
import com.sun.source.doctree.RawTextTree;
import com.sun.source.doctree.ReferenceTree;
import com.sun.source.doctree.ReturnTree;
import com.sun.source.doctree.SeeTree;
import com.sun.source.doctree.SerialDataTree;
import com.sun.source.doctree.SerialFieldTree;
import com.sun.source.doctree.SerialTree;
import com.sun.source.doctree.SinceTree;
import com.sun.source.doctree.SnippetTree;
import com.sun.source.doctree.SpecTree;
import com.sun.source.doctree.StartElementTree;
import com.sun.source.doctree.SummaryTree;
import com.sun.source.doctree.SystemPropertyTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.ThrowsTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.doctree.UnknownInlineTagTree;
import com.sun.source.doctree.UsesTree;
import com.sun.source.doctree.ValueTree;
import com.sun.source.doctree.VersionTree;
import defpackage.a9g;
import java.util.List;
import javax.lang.model.element.Name;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocTreeFactory {
    DocTreeFactory at(int i);

    List<DocTree> getFirstSentence(List<? extends DocTree> list);

    AttributeTree newAttributeTree(Name name, AttributeTree.ValueKind valueKind, List<? extends DocTree> list);

    AuthorTree newAuthorTree(List<? extends DocTree> list);

    LiteralTree newCodeTree(TextTree textTree);

    CommentTree newCommentTree(String str);

    DeprecatedTree newDeprecatedTree(List<? extends DocTree> list);

    DocCommentTree newDocCommentTree(List<? extends DocTree> list, List<? extends DocTree> list2);

    DocCommentTree newDocCommentTree(List<? extends DocTree> list, List<? extends DocTree> list2, List<? extends DocTree> list3, List<? extends DocTree> list4);

    DocRootTree newDocRootTree();

    DocTypeTree newDocTypeTree(String str);

    EndElementTree newEndElementTree(Name name);

    EntityTree newEntityTree(Name name);

    ErroneousTree newErroneousTree(String str, Diagnostic<JavaFileObject> diagnostic);

    EscapeTree newEscapeTree(char c);

    ThrowsTree newExceptionTree(ReferenceTree referenceTree, List<? extends DocTree> list);

    HiddenTree newHiddenTree(List<? extends DocTree> list);

    IdentifierTree newIdentifierTree(Name name);

    IndexTree newIndexTree(DocTree docTree, List<? extends DocTree> list);

    InheritDocTree newInheritDocTree();

    default InheritDocTree newInheritDocTree(ReferenceTree referenceTree) {
        throw new UnsupportedOperationException();
    }

    LinkTree newLinkPlainTree(ReferenceTree referenceTree, List<? extends DocTree> list);

    LinkTree newLinkTree(ReferenceTree referenceTree, List<? extends DocTree> list);

    LiteralTree newLiteralTree(TextTree textTree);

    ParamTree newParamTree(boolean z, IdentifierTree identifierTree, List<? extends DocTree> list);

    ProvidesTree newProvidesTree(ReferenceTree referenceTree, List<? extends DocTree> list);

    RawTextTree newRawTextTree(DocTree.Kind kind, String str);

    ReferenceTree newReferenceTree(String str);

    ReturnTree newReturnTree(List<? extends DocTree> list);

    default ReturnTree newReturnTree(boolean z, List<? extends DocTree> list) {
        if (!z) {
            return newReturnTree(list);
        }
        a9g.a();
        return null;
    }

    SeeTree newSeeTree(List<? extends DocTree> list);

    SerialDataTree newSerialDataTree(List<? extends DocTree> list);

    SerialFieldTree newSerialFieldTree(IdentifierTree identifierTree, ReferenceTree referenceTree, List<? extends DocTree> list);

    SerialTree newSerialTree(List<? extends DocTree> list);

    SinceTree newSinceTree(List<? extends DocTree> list);

    SnippetTree newSnippetTree(List<? extends DocTree> list, TextTree textTree);

    SpecTree newSpecTree(TextTree textTree, List<? extends DocTree> list);

    StartElementTree newStartElementTree(Name name, List<? extends DocTree> list, boolean z);

    default SummaryTree newSummaryTree(List<? extends DocTree> list) {
        throw new UnsupportedOperationException("not implemented");
    }

    SystemPropertyTree newSystemPropertyTree(Name name);

    TextTree newTextTree(String str);

    ThrowsTree newThrowsTree(ReferenceTree referenceTree, List<? extends DocTree> list);

    UnknownBlockTagTree newUnknownBlockTagTree(Name name, List<? extends DocTree> list);

    UnknownInlineTagTree newUnknownInlineTagTree(Name name, List<? extends DocTree> list);

    UsesTree newUsesTree(ReferenceTree referenceTree, List<? extends DocTree> list);

    ValueTree newValueTree(ReferenceTree referenceTree);

    default ValueTree newValueTree(TextTree textTree, ReferenceTree referenceTree) {
        return newValueTree(referenceTree);
    }

    VersionTree newVersionTree(List<? extends DocTree> list);
}
