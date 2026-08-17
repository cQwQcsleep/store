package defpackage;

import kotlin.coroutines.Continuation;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class rv9 {
    public static final rv9 a = new rv9();

    public final void a(JSONArray jSONArray) {
        jSONArray.getClass();
        ix9 ix9Var = ix9.a;
        if (ix9Var.t()) {
            ix9Var.m(jSONArray);
        }
    }

    public final Object b(String str, String str2, Continuation continuation) {
        if (StringsKt.startsWith$default(str, "mcp_", false, 2, (Object) null)) {
            return ix9.a.n(str, str2, continuation);
        }
        return "未知 MCP 工具：" + str;
    }
}
