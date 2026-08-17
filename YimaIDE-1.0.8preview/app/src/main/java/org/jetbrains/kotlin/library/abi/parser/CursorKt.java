package org.jetbrains.kotlin.library.abi.parser;

import kotlin.Metadata;
import kotlin.text.Regex;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"validIdentifierRegex", "Lkotlin/text/Regex;", "validIdentifierWithDotRegex", "getValidIdentifierWithDotRegex", "()Lkotlin/text/Regex;", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CursorKt {
    private static final Regex validIdentifierRegex = new Regex("^((=(?!\\s?\\.\\.\\.)|[^.;\\[\\]/<>:\\\\(){}?=,&])+)");
    private static final Regex validIdentifierWithDotRegex = new Regex("^((?!\\.\\.\\.)(=(?!\\s?\\.\\.\\.)|[^;\\[\\]/<>:\\\\(){}?=,&]))+");

    public static final Regex getValidIdentifierWithDotRegex() {
        return validIdentifierWithDotRegex;
    }
}
