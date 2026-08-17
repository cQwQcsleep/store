package org.jetbrains.kotlin.parsing;

import com.intellij.lang.WhitespacesAndCommentsBinder;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0015\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0002¢\u0006\u0002\n\u0000\"\u0015\u0010\u0003\u001a\u00020\u00018\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0002¢\u0006\u0002\n\u0000\"\u0015\u0010\u0004\u001a\u00020\u00018\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0002¢\u0006\u0002\n\u0000\"\u0015\u0010\u0005\u001a\u00020\u00018\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0002¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"PRECEDING_ALL_COMMENTS_BINDER", "Lcom/intellij/lang/WhitespacesAndCommentsBinder;", "Lkotlin/jvm/JvmField;", "TRAILING_ALL_COMMENTS_BINDER", "PRECEDING_ALL_BINDER", "TRAILING_ALL_BINDER", "org.jetbrains.kotlin:parser"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KotlinWhitespaceAndCommentsBindersKt {
    public static final WhitespacesAndCommentsBinder PRECEDING_ALL_COMMENTS_BINDER = new AllCommentsBinder(false);
    public static final WhitespacesAndCommentsBinder TRAILING_ALL_COMMENTS_BINDER = new AllCommentsBinder(true);
    public static final WhitespacesAndCommentsBinder PRECEDING_ALL_BINDER = new BindAll(false);
    public static final WhitespacesAndCommentsBinder TRAILING_ALL_BINDER = new BindAll(true);
}
