package org.jetbrains.kotlin.resolve.scopes;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\"\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/resolve/scopes/LexicalScopeKind;", "", "withLocalDescriptors", "", "<init>", "(Ljava/lang/String;IZ)V", "getWithLocalDescriptors", "()Z", "EMPTY", "THROWING", "CLASS_HEADER", "CLASS_INHERITANCE", "CONSTRUCTOR_HEADER", "CLASS_STATIC_SCOPE", "CLASS_MEMBER_SCOPE", "CLASS_INITIALIZER", "DEFAULT_VALUE", "PROPERTY_HEADER", "PROPERTY_INITIALIZER_OR_DELEGATE", "PROPERTY_ACCESSOR_BODY", "PROPERTY_DELEGATE_METHOD", "FUNCTION_HEADER", "FUNCTION_HEADER_FOR_DESTRUCTURING", "FUNCTION_INNER_SCOPE", "TYPE_ALIAS_HEADER", "CODE_BLOCK", "LEFT_BOOLEAN_EXPRESSION", "RIGHT_BOOLEAN_EXPRESSION", "THEN", "ELSE", "DO_WHILE_BODY", "CATCH", "FOR", "WHILE_BODY", "WHEN", "CALLABLE_REFERENCE", "SYNTHETIC", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum LexicalScopeKind {
    EMPTY(false),
    THROWING(false),
    CLASS_HEADER(false),
    CLASS_INHERITANCE(false),
    CONSTRUCTOR_HEADER(false),
    CLASS_STATIC_SCOPE(false),
    CLASS_MEMBER_SCOPE(false),
    CLASS_INITIALIZER(true),
    DEFAULT_VALUE(true),
    PROPERTY_HEADER(false),
    PROPERTY_INITIALIZER_OR_DELEGATE(true),
    PROPERTY_ACCESSOR_BODY(true),
    PROPERTY_DELEGATE_METHOD(false),
    FUNCTION_HEADER(false),
    FUNCTION_HEADER_FOR_DESTRUCTURING(false),
    FUNCTION_INNER_SCOPE(true),
    TYPE_ALIAS_HEADER(false),
    CODE_BLOCK(true),
    LEFT_BOOLEAN_EXPRESSION(true),
    RIGHT_BOOLEAN_EXPRESSION(true),
    THEN(true),
    ELSE(true),
    DO_WHILE_BODY(true),
    CATCH(true),
    FOR(true),
    WHILE_BODY(true),
    WHEN(true),
    CALLABLE_REFERENCE(false),
    SYNTHETIC(false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean withLocalDescriptors;

    LexicalScopeKind(boolean z) {
        this.withLocalDescriptors = z;
    }

    public static EnumEntries<LexicalScopeKind> getEntries() {
        return $ENTRIES;
    }

    public final boolean getWithLocalDescriptors() {
        return this.withLocalDescriptors;
    }
}
