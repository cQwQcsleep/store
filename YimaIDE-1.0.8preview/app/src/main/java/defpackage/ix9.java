package defpackage;

import android.content.Context;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ix9 {
    public static final ix9 a = new ix9();
    public static final CoroutineScope b = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
    public static final Mutex c = MutexKt.Mutex$default(false, 1, (Object) null);
    public static final ConcurrentHashMap d = new ConcurrentHashMap();
    public static final ConcurrentHashMap e = new ConcurrentHashMap();
    public static final ConcurrentHashMap f = new ConcurrentHashMap();
    public static final ConcurrentHashMap g = new ConcurrentHashMap();
    public static final MutableStateFlow h;
    public static final StateFlow i;
    public static final int j;

    public static final class a {
        public final tv9 a;
        public final kx9 b;
        public final List c;
        public final String d;
        public final Map e;

        public a(tv9 tv9Var, kx9 kx9Var, List list, String str, Map map) {
            tv9Var.getClass();
            kx9Var.getClass();
            list.getClass();
            str.getClass();
            map.getClass();
            this.a = tv9Var;
            this.b = kx9Var;
            this.c = list;
            this.d = str;
            this.e = map;
        }

        public static /* synthetic */ a b(a aVar, tv9 tv9Var, kx9 kx9Var, List list, String str, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                tv9Var = aVar.a;
            }
            if ((i & 2) != 0) {
                kx9Var = aVar.b;
            }
            if ((i & 4) != 0) {
                list = aVar.c;
            }
            if ((i & 8) != 0) {
                str = aVar.d;
            }
            if ((i & 16) != 0) {
                map = aVar.e;
            }
            Map map2 = map;
            List list2 = list;
            return aVar.a(tv9Var, kx9Var, list2, str, map2);
        }

        public final a a(tv9 tv9Var, kx9 kx9Var, List list, String str, Map map) {
            tv9Var.getClass();
            kx9Var.getClass();
            list.getClass();
            str.getClass();
            map.getClass();
            return new a(tv9Var, kx9Var, list, str, map);
        }

        public final kx9 c() {
            return this.b;
        }

        public final tv9 d() {
            return this.a;
        }

        public final Map e() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c) && Intrinsics.areEqual(this.d, aVar.d) && Intrinsics.areEqual(this.e, aVar.e);
        }

        public final String f() {
            return this.d;
        }

        public final List g() {
            return this.c;
        }

        public int hashCode() {
            return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
        }

        public String toString() {
            return "LiveSession(config=" + this.a + ", client=" + this.b + ", tools=" + this.c + ", serverLabel=" + this.d + ", modelNameToOriginal=" + this.e + ")";
        }
    }

    public static final class b extends SuspendLambda implements Function2 {
        public Object b;
        public Object c;
        public Object d;
        public Object e;
        public Object f;
        public Object g;
        public Object h;
        public int i;
        public int j;
        public final /* synthetic */ String k;
        public final /* synthetic */ String l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.k = str;
            this.l = str2;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new b(this.k, this.l, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:69:0x0199  */
        /* JADX WARN: Code duplicated, block: B:72:0x01a0  */
        /* JADX WARN: Code duplicated, block: B:74:0x01a3  */
        /* JADX WARN: Code duplicated, block: B:77:0x01b3  */
        /* JADX WARN: Code duplicated, block: B:80:0x01e0  */
        /* JADX WARN: Code duplicated, block: B:83:0x01f7 A[Catch: all -> 0x01fc, TryCatch #3 {all -> 0x01fc, blocks: (B:81:0x01e3, B:83:0x01f7, B:87:0x01ff, B:89:0x0205, B:90:0x0236), top: B:105:0x01e3 }] */
        /* JADX WARN: Code duplicated, block: B:86:0x01fe  */
        /* JADX WARN: Code duplicated, block: B:89:0x0205 A[Catch: all -> 0x01fc, TryCatch #3 {all -> 0x01fc, blocks: (B:81:0x01e3, B:83:0x01f7, B:87:0x01ff, B:89:0x0205, B:90:0x0236), top: B:105:0x01e3 }] */
        /* JADX WARN: Code duplicated, block: B:94:0x0255  */
        /* JADX WARN: Instruction removed from duplicated block: B:89:0x0205, please report this as an issue */
        public final Object invokeSuspend(Object obj) {
            Pair pairD;
            a aVarD;
            JSONObject jSONObject;
            Exception e;
            String str;
            int i;
            String message;
            Mutex mutex;
            a aVar;
            String str2;
            Mutex mutex2;
            a aVar2;
            kx9 kx9VarC;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.j;
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    pairD = lx9.a.d(this.k);
                    if (pairD != null) {
                        Collection collectionValues = ix9.d.values();
                        collectionValues.getClass();
                        Collection collection = collectionValues;
                        if (collection.isEmpty()) {
                            i = 0;
                        } else {
                            Iterator it = collection.iterator();
                            i = 0;
                            while (it.hasNext()) {
                                if (Intrinsics.areEqual(((a) it.next()).d().g(), pairD.getFirst()) && (i = i + 1) < 0) {
                                    CollectionsKt.throwCountOverflow();
                                }
                            }
                        }
                        if (i > 1) {
                            return "失败：多个 MCP 服务器工具前缀冲突（" + pairD.getFirst() + "）。请删除重复配置后重连。";
                        }
                    }
                    aVarD = ix9.a.D(this.k, pairD);
                    if (aVarD == null) {
                        if (pairD == null) {
                            return "未知 MCP 工具：" + this.k;
                        }
                        return "失败：MCP 未连接（前缀 " + pairD.getFirst() + "）。请在 AI 面板连接 MCP 服务器。";
                    }
                    String str3 = (String) aVarD.e().get(this.k);
                    if (str3 == null) {
                        str3 = pairD != null ? (String) pairD.getSecond() : null;
                        if (str3 == null) {
                            return "未知 MCP 工具：" + this.k;
                        }
                    }
                    List listG = aVarD.g();
                    if (!(listG instanceof Collection) || !listG.isEmpty()) {
                        Iterator it2 = listG.iterator();
                        while (it2.hasNext()) {
                            if (Intrinsics.areEqual(((mx9) it2.next()).c(), str3)) {
                                JSONObject jSONObjectA = ix9.a.A(this.l);
                                if (jSONObjectA == null) {
                                    return "失败：工具参数不是合法 JSON";
                                }
                                try {
                                    kx9 kx9VarC2 = aVarD.c();
                                    this.b = SpillingKt.nullOutSpilledVariable(pairD);
                                    this.c = aVarD;
                                    this.d = SpillingKt.nullOutSpilledVariable(str3);
                                    this.e = SpillingKt.nullOutSpilledVariable(jSONObjectA);
                                    this.j = 1;
                                    Object objI = kx9VarC2.i(str3, jSONObjectA, this);
                                    if (objI != coroutine_suspended) {
                                        String str4 = str3;
                                        jSONObject = jSONObjectA;
                                        obj = objI;
                                        str = str4;
                                    }
                                } catch (Exception e2) {
                                    String str5 = str3;
                                    jSONObject = jSONObjectA;
                                    e = e2;
                                    str = str5;
                                    message = e.getMessage();
                                    if (message == null) {
                                        message = e.getClass().getSimpleName();
                                    } else {
                                        if (StringsKt.isBlank(message)) {
                                            message = null;
                                        }
                                        if (message == null) {
                                            message = e.getClass().getSimpleName();
                                        }
                                    }
                                    if (ix9.a.x(message)) {
                                        return "失败：".concat(message);
                                    }
                                    mutex = ix9.c;
                                    this.b = SpillingKt.nullOutSpilledVariable(pairD);
                                    this.c = aVarD;
                                    this.d = SpillingKt.nullOutSpilledVariable(str);
                                    this.e = SpillingKt.nullOutSpilledVariable(jSONObject);
                                    this.f = SpillingKt.nullOutSpilledVariable(e);
                                    this.g = message;
                                    this.h = mutex;
                                    this.i = 0;
                                    this.j = 2;
                                    if (mutex.lock((Object) null, this) != coroutine_suspended) {
                                        aVar = aVarD;
                                        str2 = message;
                                        mutex2 = mutex;
                                        aVar2 = (a) ix9.d.get(aVar.d().e());
                                        if (aVar2 != null) {
                                            kx9VarC = aVar2.c();
                                        } else {
                                            kx9VarC = null;
                                        }
                                        if (kx9VarC == aVar.c()) {
                                            ix9.e.put(aVar.d().e(), "连接已断开：" + str2);
                                            ix9 ix9Var = ix9.a;
                                            ix9Var.q(aVar.d().e());
                                            ix9Var.B();
                                        }
                                        Unit unit = Unit.INSTANCE;
                                        mutex2.unlock((Object) null);
                                        return "失败：MCP 连接已断开（" + str2 + "）。请在 AI 面板重新连接。";
                                    }
                                }
                                return coroutine_suspended;
                            }
                        }
                    }
                    return "失败：当前 MCP 会话没有工具 `" + str3 + "`。可断开后重新连接以刷新工具列表。";
                }
                if (i2 != 1) {
                    if (i2 != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    mutex2 = (Mutex) this.h;
                    str2 = (String) this.g;
                    aVar = (a) this.c;
                    ResultKt.throwOnFailure(obj);
                    try {
                        aVar2 = (a) ix9.d.get(aVar.d().e());
                        if (aVar2 != null) {
                            kx9VarC = aVar2.c();
                        } else {
                            kx9VarC = null;
                        }
                        if (kx9VarC == aVar.c()) {
                            ix9.e.put(aVar.d().e(), "连接已断开：" + str2);
                            ix9 ix9Var2 = ix9.a;
                            ix9Var2.q(aVar.d().e());
                            ix9Var2.B();
                        }
                        Unit unit2 = Unit.INSTANCE;
                        mutex2.unlock((Object) null);
                        return "失败：MCP 连接已断开（" + str2 + "）。请在 AI 面板重新连接。";
                    } catch (Throwable th) {
                        mutex2.unlock((Object) null);
                        throw th;
                    }
                }
                jSONObject = (JSONObject) this.e;
                str = (String) this.d;
                aVarD = (a) this.c;
                pairD = (Pair) this.b;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e3) {
                    e = e3;
                    message = e.getMessage();
                    if (message == null) {
                        message = e.getClass().getSimpleName();
                    } else {
                        if (StringsKt.isBlank(message)) {
                            message = null;
                        }
                        if (message == null) {
                            message = e.getClass().getSimpleName();
                        }
                    }
                    if (ix9.a.x(message)) {
                        return "失败：".concat(message);
                    }
                    mutex = ix9.c;
                    this.b = SpillingKt.nullOutSpilledVariable(pairD);
                    this.c = aVarD;
                    this.d = SpillingKt.nullOutSpilledVariable(str);
                    this.e = SpillingKt.nullOutSpilledVariable(jSONObject);
                    this.f = SpillingKt.nullOutSpilledVariable(e);
                    this.g = message;
                    this.h = mutex;
                    this.i = 0;
                    this.j = 2;
                    if (mutex.lock((Object) null, this) != coroutine_suspended) {
                        aVar = aVarD;
                        str2 = message;
                        mutex2 = mutex;
                        aVar2 = (a) ix9.d.get(aVar.d().e());
                        if (aVar2 != null) {
                            kx9VarC = aVar2.c();
                        } else {
                            kx9VarC = null;
                        }
                        if (kx9VarC == aVar.c()) {
                            ix9.e.put(aVar.d().e(), "连接已断开：" + str2);
                            ix9 ix9Var3 = ix9.a;
                            ix9Var3.q(aVar.d().e());
                            ix9Var3.B();
                        }
                        Unit unit3 = Unit.INSTANCE;
                        mutex2.unlock((Object) null);
                        return "失败：MCP 连接已断开（" + str2 + "）。请在 AI 面板重新连接。";
                    }
                    return coroutine_suspended;
                }
                return ix9.a.G(lx9.a.a((JSONObject) obj));
            } catch (CancellationException e4) {
                throw e4;
            }
        }
    }

    public static final class c extends SuspendLambda implements Function2 {
        public Object b;
        public Object c;
        public Object d;
        public Object e;
        public Object f;
        public Object g;
        public Object h;
        public Object i;
        public Object j;
        public Object k;
        public int l;
        public int m;
        public int n;
        public final /* synthetic */ tv9 o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(tv9 tv9Var, Continuation continuation) {
            super(2, continuation);
            this.o = tv9Var;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new c(this.o, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:102:0x02fa A[Catch: all -> 0x0316, TryCatch #3 {all -> 0x0316, blocks: (B:100:0x02ea, B:102:0x02fa, B:104:0x0300, B:106:0x0310, B:110:0x031c, B:112:0x032c, B:114:0x0332, B:115:0x0335), top: B:174:0x02ea }] */
        /* JADX WARN: Code duplicated, block: B:120:0x036c A[Catch: all -> 0x036a, TryCatch #7 {all -> 0x036a, blocks: (B:117:0x034c, B:121:0x0373, B:120:0x036c), top: B:179:0x02f8 }] */
        /* JADX WARN: Code duplicated, block: B:125:0x037d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:127:0x0381 A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:129:0x03b7 A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:143:0x03db  */
        /* JADX WARN: Code duplicated, block: B:172:0x019c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:18:0x008d A[PHI: r0 r6 r7 r8 r9 r12 r13
          0x008d: PHI (r0v68 java.lang.String) = (r0v59 java.lang.String), (r0v74 java.lang.String) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
          0x008d: PHI (r6v17 int) = (r6v15 int), (r6v18 int) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
          0x008d: PHI (r7v15 org.json.JSONObject) = (r7v14 org.json.JSONObject), (r7v21 org.json.JSONObject) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
          0x008d: PHI (r8v16 org.json.JSONObject) = (r8v15 org.json.JSONObject), (r8v21 org.json.JSONObject) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
          0x008d: PHI (r9v9 java.lang.Object) = (r9v8 java.lang.Object), (r9v14 java.lang.Object) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
          0x008d: PHI (r12v6 kx9) = (r12v3 kx9), (r12v8 kx9) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
          0x008d: PHI (r13v8 java.lang.String) = (r13v5 java.lang.String), (r13v10 java.lang.String) binds: [B:90:0x0253, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:60:0x01a6 A[Catch: all -> 0x01b3, TRY_LEAVE, TryCatch #2 {all -> 0x01b3, blocks: (B:58:0x019c, B:60:0x01a6), top: B:172:0x019c }] */
        /* JADX WARN: Code duplicated, block: B:69:0x01dd  */
        /* JADX WARN: Code duplicated, block: B:72:0x01f8 A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, TRY_LEAVE, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:74:0x01ff  */
        /* JADX WARN: Code duplicated, block: B:77:0x0204  */
        /* JADX WARN: Code duplicated, block: B:79:0x0207 A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, TRY_ENTER, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:80:0x020f  */
        /* JADX WARN: Code duplicated, block: B:82:0x0212  */
        /* JADX WARN: Code duplicated, block: B:83:0x0213  */
        /* JADX WARN: Code duplicated, block: B:86:0x021a A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:88:0x0228 A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:91:0x0255  */
        /* JADX WARN: Code duplicated, block: B:95:0x0281 A[Catch: Exception -> 0x006a, CancellationException -> 0x006d, LOOP:0: B:93:0x027b->B:95:0x0281, LOOP_END, TryCatch #4 {CancellationException -> 0x006d, blocks: (B:8:0x005d, B:123:0x0376, B:127:0x0381, B:129:0x03b7, B:130:0x03bd, B:132:0x03c2, B:133:0x03c5, B:16:0x0088, B:92:0x0257, B:93:0x027b, B:95:0x0281, B:96:0x02a7, B:20:0x009c, B:70:0x01de, B:72:0x01f8, B:79:0x0207, B:84:0x0214, B:86:0x021a, B:88:0x0228, B:89:0x0230, B:66:0x01c6), top: B:176:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:99:0x02e2  */
        /* JADX WARN: Instruction removed from duplicated block: B:127:0x0381, please report this as an issue */
        /* JADX WARN: Not initialized variable reg: 12, insn: 0x046e: INVOKE (r12 I:kx9) VIRTUAL call: kx9.j():void A[MD:():void (m)], block:B:162:0x046e */
        public final Object invokeSuspend(Object obj) throws Throwable {
            kx9 kx9VarJ;
            String str;
            Mutex mutex;
            int i;
            tv9 tv9Var;
            AtomicInteger atomicInteger;
            int i2;
            tv9 tv9Var2;
            Mutex mutex2;
            String str2;
            Object objPutIfAbsent;
            Mutex mutex3;
            tv9 tv9Var3;
            Object objPutIfAbsent2;
            a aVar;
            kx9 kx9Var;
            Object objL;
            int i3;
            JSONObject jSONObject;
            JSONObject jSONObjectOptJSONObject;
            StringBuilder sb;
            String strOptString;
            String str3;
            String strOptString2;
            String string;
            Object objM;
            String str4;
            nx9 nx9Var;
            tv9 tv9Var4;
            LinkedHashMap linkedHashMap;
            Ref.BooleanRef booleanRef;
            Mutex mutex4;
            tv9 tv9Var5;
            Ref.BooleanRef booleanRef2;
            Mutex mutex5;
            tv9 tv9Var6;
            Map map;
            String str5;
            kx9 kx9Var2;
            AtomicInteger atomicInteger2;
            StringBuilder sb2;
            kx9 kx9VarC;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object obj2 = null;
            try {
                try {
                    switch (this.n) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            if (this.o.c()) {
                                String string2 = StringsKt.trim(this.o.h()).toString();
                                if (StringsKt.isBlank(string2)) {
                                    ix9.e.put(this.o.e(), "请填写 MCP 地址");
                                    return "请填写 MCP 地址";
                                }
                                ConcurrentHashMap concurrentHashMap = ix9.g;
                                String strE = this.o.e();
                                Object atomicInteger3 = concurrentHashMap.get(strE);
                                if (atomicInteger3 == null && (objPutIfAbsent = concurrentHashMap.putIfAbsent(strE, (atomicInteger3 = new AtomicInteger(0)))) != null) {
                                    atomicInteger3 = objPutIfAbsent;
                                }
                                int iIncrementAndGet = ((AtomicInteger) atomicInteger3).incrementAndGet();
                                Mutex mutex6 = ix9.c;
                                tv9 tv9Var7 = this.o;
                                this.b = string2;
                                this.c = mutex6;
                                this.d = tv9Var7;
                                this.l = iIncrementAndGet;
                                this.m = 0;
                                this.n = 2;
                                if (mutex6.lock((Object) null, this) != coroutine_suspended) {
                                    i2 = iIncrementAndGet;
                                    tv9Var2 = tv9Var7;
                                    mutex2 = mutex6;
                                    str2 = string2;
                                    try {
                                        aVar = (a) ix9.d.get(tv9Var2.e());
                                        if (aVar != null) {
                                            try {
                                                if (!aVar.d().i(tv9Var2)) {
                                                    ix9 ix9Var = ix9.a;
                                                    ix9Var.q(tv9Var2.e());
                                                    ix9Var.B();
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                mutex2.unlock(obj2);
                                                throw th;
                                            }
                                        }
                                        Unit unit = Unit.INSTANCE;
                                        mutex2.unlock((Object) null);
                                        kx9Var = new kx9(str2, this.o.d());
                                        try {
                                            this.b = SpillingKt.nullOutSpilledVariable(str2);
                                            this.c = kx9Var;
                                            this.d = null;
                                            this.l = i2;
                                            this.n = 3;
                                            objL = kx9Var.l(this);
                                            if (objL != coroutine_suspended) {
                                                i3 = i2;
                                                jSONObject = (JSONObject) objL;
                                                jSONObjectOptJSONObject = jSONObject.optJSONObject("serverInfo");
                                                tv9 tv9Var8 = this.o;
                                                sb = new StringBuilder();
                                                sb.append(tv9Var8.f());
                                                if (jSONObjectOptJSONObject != null) {
                                                    strOptString = jSONObjectOptJSONObject.optString("name");
                                                } else {
                                                    strOptString = null;
                                                }
                                                str3 = "";
                                                if (strOptString == null) {
                                                    strOptString = "";
                                                }
                                                if (jSONObjectOptJSONObject != null) {
                                                    strOptString2 = jSONObjectOptJSONObject.optString("version");
                                                } else {
                                                    strOptString2 = null;
                                                }
                                                if (strOptString2 != null) {
                                                    str3 = strOptString2;
                                                }
                                                if (!StringsKt.isBlank(strOptString)) {
                                                    sb.append(" · ");
                                                    sb.append(strOptString);
                                                    if (!StringsKt.isBlank(str3)) {
                                                        sb.append(PsuedoNames.PSEUDONAME_ROOT);
                                                        sb.append(str3);
                                                    }
                                                }
                                                string = sb.toString();
                                                this.b = SpillingKt.nullOutSpilledVariable(str2);
                                                this.c = kx9Var;
                                                this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                                                this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                                                this.f = string;
                                                this.l = i3;
                                                this.n = 4;
                                                objM = kx9Var.m(this);
                                                if (objM != coroutine_suspended) {
                                                    str4 = string;
                                                    nx9Var = (nx9) objM;
                                                    List<mx9> listA = nx9Var.a();
                                                    tv9Var4 = this.o;
                                                    linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listA, 10)), 16));
                                                    for (mx9 mx9Var : listA) {
                                                        Pair pair = TuplesKt.to(lx9.a.b(tv9Var4, mx9Var.c()), mx9Var.c());
                                                        linkedHashMap.put(pair.getFirst(), pair.getSecond());
                                                    }
                                                    booleanRef = new Ref.BooleanRef();
                                                    mutex4 = ix9.c;
                                                    tv9Var5 = this.o;
                                                    this.b = SpillingKt.nullOutSpilledVariable(str2);
                                                    this.c = kx9Var;
                                                    this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                                                    this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                                                    this.f = str4;
                                                    this.g = nx9Var;
                                                    this.h = linkedHashMap;
                                                    this.i = booleanRef;
                                                    this.j = mutex4;
                                                    this.k = tv9Var5;
                                                    this.l = i3;
                                                    this.m = 0;
                                                    this.n = 5;
                                                    if (mutex4.lock((Object) null, this) != coroutine_suspended) {
                                                        booleanRef2 = booleanRef;
                                                        mutex5 = mutex4;
                                                        tv9Var6 = tv9Var5;
                                                        map = linkedHashMap;
                                                        str5 = str4;
                                                        kx9Var2 = kx9Var;
                                                        try {
                                                            atomicInteger2 = (AtomicInteger) ix9.g.get(tv9Var6.e());
                                                            try {
                                                                if (atomicInteger2 == null && atomicInteger2.get() == i3) {
                                                                    Job job = (Job) ix9.f.remove(tv9Var6.e());
                                                                    if (job != null) {
                                                                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                                                                    }
                                                                    a aVar2 = (a) ix9.d.remove(tv9Var6.e());
                                                                    if (aVar2 != null && (kx9VarC = aVar2.c()) != null) {
                                                                        kx9VarC.j();
                                                                    }
                                                                    ix9.d.put(tv9Var6.e(), new a(tv9Var6, kx9Var2, nx9Var.a(), str5, map));
                                                                    ix9.e.remove(tv9Var6.e());
                                                                    ix9 ix9Var2 = ix9.a;
                                                                    ix9Var2.B();
                                                                    ix9Var2.E(tv9Var6.e(), kx9Var2);
                                                                    booleanRef2.element = true;
                                                                } else {
                                                                    kx9Var2.j();
                                                                }
                                                                Unit unit2 = Unit.INSTANCE;
                                                                mutex5.unlock((Object) null);
                                                                if (!booleanRef2.element) {
                                                                    return "已取消";
                                                                }
                                                                sb2 = new StringBuilder();
                                                                sb2.append("已连接：" + str5 + "（" + nx9Var.a().size() + " 个工具）");
                                                                if (nx9Var.b()) {
                                                                    sb2.append("；工具较多，列表可能未完全加载");
                                                                }
                                                                return sb2.toString();
                                                            } catch (Throwable th2) {
                                                                th = th2;
                                                                mutex5.unlock((Object) null);
                                                                throw th;
                                                            }
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                            e = e;
                                            int i4 = i2;
                                            kx9Var.j();
                                            String message = e.getMessage();
                                            if (message == null) {
                                                message = e.getClass().getSimpleName();
                                            } else {
                                                if (StringsKt.isBlank(message)) {
                                                    message = null;
                                                }
                                                if (message == null) {
                                                    message = e.getClass().getSimpleName();
                                                }
                                            }
                                            Mutex mutex7 = ix9.c;
                                            tv9 tv9Var9 = this.o;
                                            this.b = SpillingKt.nullOutSpilledVariable(str2);
                                            this.c = SpillingKt.nullOutSpilledVariable(kx9Var);
                                            this.d = SpillingKt.nullOutSpilledVariable(e);
                                            this.e = message;
                                            this.f = mutex7;
                                            this.g = tv9Var9;
                                            this.h = null;
                                            this.i = null;
                                            this.j = null;
                                            this.k = null;
                                            this.l = i4;
                                            this.m = 0;
                                            this.n = 6;
                                            if (mutex7.lock((Object) null, this) != coroutine_suspended) {
                                                str = message;
                                                mutex = mutex7;
                                                i = i4;
                                                tv9Var = tv9Var9;
                                                try {
                                                    atomicInteger = (AtomicInteger) ix9.g.get(tv9Var.e());
                                                    if (atomicInteger != null) {
                                                        ix9.e.put(tv9Var.e(), str);
                                                        ix9.a.B();
                                                    }
                                                    Unit unit3 = Unit.INSTANCE;
                                                    mutex.unlock((Object) null);
                                                    return "连接失败：" + str;
                                                } catch (Throwable th4) {
                                                    mutex.unlock((Object) null);
                                                    throw th4;
                                                }
                                            }
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        obj2 = null;
                                    }
                                }
                                break;
                            } else {
                                ConcurrentHashMap concurrentHashMap2 = ix9.g;
                                String strE2 = this.o.e();
                                Object atomicInteger4 = concurrentHashMap2.get(strE2);
                                if (atomicInteger4 == null && (objPutIfAbsent2 = concurrentHashMap2.putIfAbsent(strE2, (atomicInteger4 = new AtomicInteger(0)))) != null) {
                                    atomicInteger4 = objPutIfAbsent2;
                                }
                                ((AtomicInteger) atomicInteger4).incrementAndGet();
                                Mutex mutex8 = ix9.c;
                                tv9 tv9Var10 = this.o;
                                this.b = mutex8;
                                this.c = tv9Var10;
                                this.l = 0;
                                this.n = 1;
                                if (mutex8.lock((Object) null, this) != coroutine_suspended) {
                                    mutex3 = mutex8;
                                    tv9Var3 = tv9Var10;
                                    try {
                                        ix9 ix9Var3 = ix9.a;
                                        ix9Var3.q(tv9Var3.e());
                                        ix9Var3.B();
                                        Unit unit4 = Unit.INSTANCE;
                                        return "已禁用，未连接";
                                    } finally {
                                        mutex3.unlock((Object) null);
                                    }
                                }
                            }
                            return coroutine_suspended;
                        case 1:
                            tv9Var3 = (tv9) this.c;
                            mutex3 = (Mutex) this.b;
                            ResultKt.throwOnFailure(obj);
                            ix9 ix9Var4 = ix9.a;
                            ix9Var4.q(tv9Var3.e());
                            ix9Var4.B();
                            Unit unit5 = Unit.INSTANCE;
                            return "已禁用，未连接";
                        case 2:
                            int i5 = this.l;
                            tv9Var2 = (tv9) this.d;
                            Mutex mutex9 = (Mutex) this.c;
                            String str6 = (String) this.b;
                            ResultKt.throwOnFailure(obj);
                            str2 = str6;
                            mutex2 = mutex9;
                            i2 = i5;
                            aVar = (a) ix9.d.get(tv9Var2.e());
                            if (aVar != null) {
                                if (!aVar.d().i(tv9Var2)) {
                                    ix9 ix9Var5 = ix9.a;
                                    ix9Var5.q(tv9Var2.e());
                                    ix9Var5.B();
                                }
                                break;
                            }
                            Unit unit6 = Unit.INSTANCE;
                            mutex2.unlock((Object) null);
                            kx9Var = new kx9(str2, this.o.d());
                            this.b = SpillingKt.nullOutSpilledVariable(str2);
                            this.c = kx9Var;
                            this.d = null;
                            this.l = i2;
                            this.n = 3;
                            objL = kx9Var.l(this);
                            if (objL != coroutine_suspended) {
                                i3 = i2;
                                jSONObject = (JSONObject) objL;
                                jSONObjectOptJSONObject = jSONObject.optJSONObject("serverInfo");
                                tv9 tv9Var11 = this.o;
                                sb = new StringBuilder();
                                sb.append(tv9Var11.f());
                                if (jSONObjectOptJSONObject != null) {
                                    strOptString = jSONObjectOptJSONObject.optString("name");
                                } else {
                                    strOptString = null;
                                }
                                str3 = "";
                                if (strOptString == null) {
                                    strOptString = "";
                                }
                                if (jSONObjectOptJSONObject != null) {
                                    strOptString2 = jSONObjectOptJSONObject.optString("version");
                                } else {
                                    strOptString2 = null;
                                }
                                if (strOptString2 != null) {
                                    str3 = strOptString2;
                                }
                                if (!StringsKt.isBlank(strOptString)) {
                                    sb.append(" · ");
                                    sb.append(strOptString);
                                    if (!StringsKt.isBlank(str3)) {
                                        sb.append(PsuedoNames.PSEUDONAME_ROOT);
                                        sb.append(str3);
                                    }
                                }
                                string = sb.toString();
                                this.b = SpillingKt.nullOutSpilledVariable(str2);
                                this.c = kx9Var;
                                this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                                this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                                this.f = string;
                                this.l = i3;
                                this.n = 4;
                                objM = kx9Var.m(this);
                                if (objM != coroutine_suspended) {
                                    str4 = string;
                                    nx9Var = (nx9) objM;
                                    List<mx9> listA2 = nx9Var.a();
                                    tv9Var4 = this.o;
                                    linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listA2, 10)), 16));
                                    while (r0.hasNext()) {
                                        Pair pair2 = TuplesKt.to(lx9.a.b(tv9Var4, mx9Var.c()), mx9Var.c());
                                        linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
                                    }
                                    booleanRef = new Ref.BooleanRef();
                                    mutex4 = ix9.c;
                                    tv9Var5 = this.o;
                                    this.b = SpillingKt.nullOutSpilledVariable(str2);
                                    this.c = kx9Var;
                                    this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                                    this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                                    this.f = str4;
                                    this.g = nx9Var;
                                    this.h = linkedHashMap;
                                    this.i = booleanRef;
                                    this.j = mutex4;
                                    this.k = tv9Var5;
                                    this.l = i3;
                                    this.m = 0;
                                    this.n = 5;
                                    if (mutex4.lock((Object) null, this) != coroutine_suspended) {
                                        booleanRef2 = booleanRef;
                                        mutex5 = mutex4;
                                        tv9Var6 = tv9Var5;
                                        map = linkedHashMap;
                                        str5 = str4;
                                        kx9Var2 = kx9Var;
                                        atomicInteger2 = (AtomicInteger) ix9.g.get(tv9Var6.e());
                                        if (atomicInteger2 == null) {
                                            kx9Var2.j();
                                        } else {
                                            kx9Var2.j();
                                        }
                                        Unit unit7 = Unit.INSTANCE;
                                        mutex5.unlock((Object) null);
                                        if (!booleanRef2.element) {
                                            return "已取消";
                                        }
                                        sb2 = new StringBuilder();
                                        sb2.append("已连接：" + str5 + "（" + nx9Var.a().size() + " 个工具）");
                                        if (nx9Var.b()) {
                                            sb2.append("；工具较多，列表可能未完全加载");
                                        }
                                        return sb2.toString();
                                    }
                                }
                            }
                            return coroutine_suspended;
                        case 3:
                            i3 = this.l;
                            kx9Var = (kx9) this.c;
                            str2 = (String) this.b;
                            ResultKt.throwOnFailure(obj);
                            objL = obj;
                            jSONObject = (JSONObject) objL;
                            jSONObjectOptJSONObject = jSONObject.optJSONObject("serverInfo");
                            tv9 tv9Var12 = this.o;
                            sb = new StringBuilder();
                            sb.append(tv9Var12.f());
                            if (jSONObjectOptJSONObject != null) {
                                strOptString = jSONObjectOptJSONObject.optString("name");
                            } else {
                                strOptString = null;
                            }
                            str3 = "";
                            if (strOptString == null) {
                                strOptString = "";
                            }
                            if (jSONObjectOptJSONObject != null) {
                                strOptString2 = jSONObjectOptJSONObject.optString("version");
                            } else {
                                strOptString2 = null;
                            }
                            if (strOptString2 != null) {
                                str3 = strOptString2;
                            }
                            if (!StringsKt.isBlank(strOptString)) {
                                sb.append(" · ");
                                sb.append(strOptString);
                                if (!StringsKt.isBlank(str3)) {
                                    sb.append(PsuedoNames.PSEUDONAME_ROOT);
                                    sb.append(str3);
                                }
                            }
                            string = sb.toString();
                            this.b = SpillingKt.nullOutSpilledVariable(str2);
                            this.c = kx9Var;
                            this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                            this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                            this.f = string;
                            this.l = i3;
                            this.n = 4;
                            objM = kx9Var.m(this);
                            if (objM != coroutine_suspended) {
                                str4 = string;
                                nx9Var = (nx9) objM;
                                List<mx9> listA3 = nx9Var.a();
                                tv9Var4 = this.o;
                                linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listA3, 10)), 16));
                                while (r0.hasNext()) {
                                    Pair pair3 = TuplesKt.to(lx9.a.b(tv9Var4, mx9Var.c()), mx9Var.c());
                                    linkedHashMap.put(pair3.getFirst(), pair3.getSecond());
                                }
                                booleanRef = new Ref.BooleanRef();
                                mutex4 = ix9.c;
                                tv9Var5 = this.o;
                                this.b = SpillingKt.nullOutSpilledVariable(str2);
                                this.c = kx9Var;
                                this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                                this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                                this.f = str4;
                                this.g = nx9Var;
                                this.h = linkedHashMap;
                                this.i = booleanRef;
                                this.j = mutex4;
                                this.k = tv9Var5;
                                this.l = i3;
                                this.m = 0;
                                this.n = 5;
                                if (mutex4.lock((Object) null, this) != coroutine_suspended) {
                                    booleanRef2 = booleanRef;
                                    mutex5 = mutex4;
                                    tv9Var6 = tv9Var5;
                                    map = linkedHashMap;
                                    str5 = str4;
                                    kx9Var2 = kx9Var;
                                    atomicInteger2 = (AtomicInteger) ix9.g.get(tv9Var6.e());
                                    if (atomicInteger2 == null) {
                                        kx9Var2.j();
                                    } else {
                                        kx9Var2.j();
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    mutex5.unlock((Object) null);
                                    if (!booleanRef2.element) {
                                        return "已取消";
                                    }
                                    sb2 = new StringBuilder();
                                    sb2.append("已连接：" + str5 + "（" + nx9Var.a().size() + " 个工具）");
                                    if (nx9Var.b()) {
                                        sb2.append("；工具较多，列表可能未完全加载");
                                    }
                                    return sb2.toString();
                                }
                            }
                            return coroutine_suspended;
                        case 4:
                            i3 = this.l;
                            string = (String) this.f;
                            jSONObjectOptJSONObject = (JSONObject) this.e;
                            jSONObject = (JSONObject) this.d;
                            kx9Var = (kx9) this.c;
                            str2 = (String) this.b;
                            ResultKt.throwOnFailure(obj);
                            objM = obj;
                            str4 = string;
                            nx9Var = (nx9) objM;
                            List<mx9> listA4 = nx9Var.a();
                            tv9Var4 = this.o;
                            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listA4, 10)), 16));
                            while (r0.hasNext()) {
                                Pair pair4 = TuplesKt.to(lx9.a.b(tv9Var4, mx9Var.c()), mx9Var.c());
                                linkedHashMap.put(pair4.getFirst(), pair4.getSecond());
                            }
                            booleanRef = new Ref.BooleanRef();
                            mutex4 = ix9.c;
                            tv9Var5 = this.o;
                            this.b = SpillingKt.nullOutSpilledVariable(str2);
                            this.c = kx9Var;
                            this.d = SpillingKt.nullOutSpilledVariable(jSONObject);
                            this.e = SpillingKt.nullOutSpilledVariable(jSONObjectOptJSONObject);
                            this.f = str4;
                            this.g = nx9Var;
                            this.h = linkedHashMap;
                            this.i = booleanRef;
                            this.j = mutex4;
                            this.k = tv9Var5;
                            this.l = i3;
                            this.m = 0;
                            this.n = 5;
                            if (mutex4.lock((Object) null, this) != coroutine_suspended) {
                                booleanRef2 = booleanRef;
                                mutex5 = mutex4;
                                tv9Var6 = tv9Var5;
                                map = linkedHashMap;
                                str5 = str4;
                                kx9Var2 = kx9Var;
                                atomicInteger2 = (AtomicInteger) ix9.g.get(tv9Var6.e());
                                if (atomicInteger2 == null) {
                                    kx9Var2.j();
                                } else {
                                    kx9Var2.j();
                                }
                                Unit unit9 = Unit.INSTANCE;
                                mutex5.unlock((Object) null);
                                if (!booleanRef2.element) {
                                    return "已取消";
                                }
                                sb2 = new StringBuilder();
                                sb2.append("已连接：" + str5 + "（" + nx9Var.a().size() + " 个工具）");
                                if (nx9Var.b()) {
                                    sb2.append("；工具较多，列表可能未完全加载");
                                }
                                return sb2.toString();
                            }
                            return coroutine_suspended;
                        case 5:
                            i3 = this.l;
                            tv9 tv9Var13 = (tv9) this.k;
                            mutex5 = (Mutex) this.j;
                            booleanRef2 = (Ref.BooleanRef) this.i;
                            Map map2 = (Map) this.h;
                            nx9Var = (nx9) this.g;
                            str4 = (String) this.f;
                            kx9Var = (kx9) this.c;
                            ResultKt.throwOnFailure(obj);
                            tv9Var6 = tv9Var13;
                            map = map2;
                            str5 = str4;
                            kx9Var2 = kx9Var;
                            atomicInteger2 = (AtomicInteger) ix9.g.get(tv9Var6.e());
                            if (atomicInteger2 == null) {
                                kx9Var2.j();
                            } else {
                                kx9Var2.j();
                            }
                            Unit unit10 = Unit.INSTANCE;
                            mutex5.unlock((Object) null);
                            if (!booleanRef2.element) {
                                return "已取消";
                            }
                            sb2 = new StringBuilder();
                            sb2.append("已连接：" + str5 + "（" + nx9Var.a().size() + " 个工具）");
                            if (nx9Var.b()) {
                                sb2.append("；工具较多，列表可能未完全加载");
                            }
                            return sb2.toString();
                        case 6:
                            i = this.l;
                            tv9Var = (tv9) this.g;
                            mutex = (Mutex) this.f;
                            str = (String) this.e;
                            ResultKt.throwOnFailure(obj);
                            atomicInteger = (AtomicInteger) ix9.g.get(tv9Var.e());
                            if (atomicInteger != null && atomicInteger.get() == i && ix9.d.get(tv9Var.e()) == null) {
                                ix9.e.put(tv9Var.e(), str);
                                ix9.a.B();
                            }
                            Unit unit11 = Unit.INSTANCE;
                            mutex.unlock((Object) null);
                            return "连接失败：" + str;
                        default:
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                    }
                } catch (CancellationException e2) {
                    kx9VarJ.j();
                    throw e2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
    }

    public static final class d extends SuspendLambda implements Function2 {
        public int b;
        public final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Continuation continuation) {
            super(2, continuation);
            this.c = str;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new d(this.c, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.b;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ix9 ix9Var = ix9.a;
                String str = this.c;
                this.b = 1;
                if (ix9Var.w(str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final class e extends SuspendLambda implements Function2 {
        public Object b;
        public Object c;
        public Object d;
        public Object e;
        public Object f;
        public Object g;
        public Object h;
        public int i;
        public int j;

        public e(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new e(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:29:0x00d3  */
        /* JADX WARN: Code duplicated, block: B:33:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:37:0x012a A[Catch: Exception -> 0x006c, CancellationException -> 0x0293, TRY_LEAVE, TryCatch #0 {Exception -> 0x006c, blocks: (B:34:0x0102, B:35:0x0124, B:37:0x012a, B:11:0x005c), top: B:93:0x005c }] */
        /* JADX WARN: Code duplicated, block: B:45:0x017e  */
        /* JADX WARN: Code duplicated, block: B:48:0x0197 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:49:0x0198 A[Catch: all -> 0x01c5, TryCatch #1 {all -> 0x01c5, blocks: (B:46:0x0182, B:54:0x01c7, B:49:0x0198, B:51:0x01a2), top: B:95:0x0182 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x01c9 -> B:56:0x01d4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x022a -> B:97:0x022d). Please report as a decompilation issue!!! */
        /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) {
            /*
                Method dump skipped, instruction units count: 673
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ix9.e.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final class f extends SuspendLambda implements Function2 {
        public Object b;
        public Object c;
        public int d;
        public int e;
        public final /* synthetic */ String f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, Continuation continuation) {
            super(2, continuation);
            this.f = str;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new f(this.f, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Mutex mutex;
            String str;
            Object objPutIfAbsent;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.e;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ConcurrentHashMap concurrentHashMap = ix9.g;
                String str2 = this.f;
                Object atomicInteger = concurrentHashMap.get(str2);
                if (atomicInteger == null && (objPutIfAbsent = concurrentHashMap.putIfAbsent(str2, (atomicInteger = new AtomicInteger(0)))) != null) {
                    atomicInteger = objPutIfAbsent;
                }
                ((AtomicInteger) atomicInteger).incrementAndGet();
                Mutex mutex2 = ix9.c;
                String str3 = this.f;
                this.b = mutex2;
                this.c = str3;
                this.d = 0;
                this.e = 1;
                if (mutex2.lock((Object) null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutex = mutex2;
                str = str3;
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                str = (String) this.c;
                mutex = (Mutex) this.b;
                ResultKt.throwOnFailure(obj);
            }
            try {
                ix9.e.remove(str);
                ix9 ix9Var = ix9.a;
                ix9Var.q(str);
                ix9Var.B();
                Unit unit = Unit.INSTANCE;
                return Unit.INSTANCE;
            } finally {
                mutex.unlock((Object) null);
            }
        }
    }

    public static final class g extends SuspendLambda implements Function2 {
        public Object b;
        public Object c;
        public Object d;
        public Object e;
        public Object f;
        public Object g;
        public Object h;
        public Object i;
        public int j;
        public int k;
        public int l;
        public int m;
        public final /* synthetic */ Context n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Context context, Continuation continuation) {
            super(2, continuation);
            this.n = context;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new g(this.n, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0081  */
        /* JADX WARN: Code duplicated, block: B:19:0x00bc A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x00bd  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00bd -> B:21:0x00be). Please report as a decompilation issue!!! */
        /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:712)
            	at jadx.core.utils.BlockUtils.isPathExists(BlockUtils.java:845)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.isCandidateForOutBlock(IfRegionMaker.java:303)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.findOutBlock(IfRegionMaker.java:267)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.restructureIf(IfRegionMaker.java:191)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:82)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.m
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L38
                if (r1 != r2) goto L31
                int r1 = r13.k
                int r4 = r13.j
                java.lang.Object r5 = r13.i
                java.util.Collection r5 = (java.util.Collection) r5
                java.lang.Object r6 = r13.h
                tv9 r6 = (tv9) r6
                java.lang.Object r6 = r13.f
                java.util.Iterator r6 = (java.util.Iterator) r6
                java.lang.Object r7 = r13.e
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r13.d
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.lang.Object r9 = r13.c
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.lang.Object r10 = r13.b
                java.util.List r10 = (java.util.List) r10
                kotlin.ResultKt.throwOnFailure(r14)
                goto Lbe
            L31:
                java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r13)
                r13 = 0
                return r13
            L38:
                kotlin.ResultKt.throwOnFailure(r14)
                uv9 r14 = defpackage.uv9.a
                android.content.Context r1 = r13.n
                java.util.List r14 = r14.a(r1)
                java.lang.Iterable r14 = (java.lang.Iterable) r14
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Iterator r14 = r14.iterator()
            L4e:
                boolean r4 = r14.hasNext()
                if (r4 == 0) goto L65
                java.lang.Object r4 = r14.next()
                r5 = r4
                tv9 r5 = (tv9) r5
                boolean r5 = r5.c()
                if (r5 == 0) goto L4e
                r1.add(r4)
                goto L4e
            L65:
                java.util.ArrayList r14 = new java.util.ArrayList
                r4 = 10
                int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r4)
                r14.<init>(r4)
                java.util.Iterator r4 = r1.iterator()
                r5 = r14
                r8 = r1
                r9 = r8
                r10 = r9
                r1 = r3
                r6 = r4
                r4 = r1
            L7b:
                boolean r14 = r6.hasNext()
                if (r14 == 0) goto Lc5
                java.lang.Object r14 = r6.next()
                r7 = r14
                tv9 r7 = (tv9) r7
                ix9 r11 = defpackage.ix9.a
                java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
                r13.b = r12
                java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                r13.c = r12
                java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r13.d = r12
                r13.e = r5
                r13.f = r6
                java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
                r13.g = r14
                java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                r13.h = r14
                r13.i = r5
                r13.j = r4
                r13.k = r1
                r13.l = r3
                r13.m = r2
                java.lang.Object r14 = r11.o(r7, r13)
                if (r14 != r0) goto Lbd
                return r0
            Lbd:
                r7 = r5
            Lbe:
                java.lang.String r14 = (java.lang.String) r14
                r5.add(r14)
                r5 = r7
                goto L7b
            Lc5:
                java.util.List r5 = (java.util.List) r5
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: ix9.g.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final class h extends SuspendLambda implements Function2 {
        public Object b;
        public Object c;
        public Object d;
        public Object e;
        public int f;
        public int g;
        public final /* synthetic */ kx9 h;
        public final /* synthetic */ String i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(kx9 kx9Var, String str, Continuation continuation) {
            super(2, continuation);
            this.h = kx9Var;
            this.i = str;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new h(this.h, this.i, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:26:0x0053  */
        public final Object invokeSuspend(Object obj) {
            String message;
            String str;
            Mutex mutex;
            String str2;
            kx9 kx9Var;
            a aVar;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.g;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    kx9 kx9Var2 = this.h;
                    this.g = 1;
                    obj = kx9Var2.h(this);
                    if (obj == coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    kx9Var = (kx9) this.e;
                    str2 = (String) this.d;
                    mutex = (Mutex) this.c;
                    str = (String) this.b;
                    ResultKt.throwOnFailure(obj);
                }
                try {
                    aVar = (a) ix9.d.get(str2);
                    if (aVar != null && aVar.c() == kx9Var) {
                        ix9.e.put(str2, "连接已断开：" + str);
                        ix9 ix9Var = ix9.a;
                        ix9Var.q(str2);
                        ix9Var.B();
                    }
                    Unit unit = Unit.INSTANCE;
                    return Unit.INSTANCE;
                } finally {
                    mutex.unlock((Object) null);
                }
                message = (String) obj;
            } catch (CancellationException unused) {
                return Unit.INSTANCE;
            } catch (Exception e) {
                message = e.getMessage();
                if (message == null) {
                    message = "连接中断";
                } else {
                    if (StringsKt.isBlank(message)) {
                        message = null;
                    }
                    if (message == null) {
                        message = "连接中断";
                    }
                }
            }
            if (Intrinsics.areEqual(message, "已取消")) {
                return Unit.INSTANCE;
            }
            Mutex mutex2 = ix9.c;
            String str3 = this.i;
            kx9 kx9Var3 = this.h;
            this.b = message;
            this.c = mutex2;
            this.d = str3;
            this.e = kx9Var3;
            this.f = 0;
            this.g = 2;
            if (mutex2.lock((Object) null, this) != coroutine_suspended) {
                str = message;
                mutex = mutex2;
                str2 = str3;
                kx9Var = kx9Var3;
                aVar = (a) ix9.d.get(str2);
                if (aVar != null) {
                    ix9.e.put(str2, "连接已断开：" + str);
                    ix9 ix9Var2 = ix9.a;
                    ix9Var2.q(str2);
                    ix9Var2.B();
                }
                Unit unit2 = Unit.INSTANCE;
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    static {
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        h = MutableStateFlow;
        i = FlowKt.asStateFlow(MutableStateFlow);
        j = 8;
    }

    public final JSONObject A(String str) {
        if (StringsKt.isBlank(str)) {
            str = "{}";
        }
        try {
            return new JSONObject(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void B() {
        h.setValue(Boolean.valueOf(!d.isEmpty()));
    }

    public final Object C(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new g(context, null), continuation);
    }

    public final a D(String str, Pair pair) {
        Object obj = null;
        if (pair != null) {
            Collection collectionValues = d.values();
            collectionValues.getClass();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : collectionValues) {
                if (Intrinsics.areEqual(((a) obj2).d().g(), pair.getFirst())) {
                    arrayList.add(obj2);
                }
            }
            if (arrayList.size() == 1) {
                return (a) CollectionsKt.first(arrayList);
            }
            if (arrayList.size() > 1) {
                return null;
            }
        }
        Collection collectionValues2 = d.values();
        collectionValues2.getClass();
        for (Object obj3 : collectionValues2) {
            if (((a) obj3).e().containsKey(str)) {
                obj = obj3;
                break;
            }
        }
        return (a) obj;
    }

    public final void E(String str, kx9 kx9Var) {
        ConcurrentHashMap concurrentHashMap = f;
        Job job = (Job) concurrentHashMap.remove(str);
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        concurrentHashMap.put(str, BuildersKt.launch$default(b, (CoroutineContext) null, (CoroutineStart) null, new h(kx9Var, str, null), 3, (Object) null));
    }

    public final jx9 F(String str) {
        str.getClass();
        a aVar = (a) d.get(str);
        if (aVar == null) {
            String str2 = (String) e.get(str);
            if (str2 == null) {
                str2 = "";
            }
            return new jx9(str, false, 0, str2, null, 16, null);
        }
        int size = aVar.g().size();
        String strF = aVar.f();
        if (StringsKt.isBlank(strF)) {
            strF = aVar.d().f();
        }
        return new jx9(str, true, size, "", strF);
    }

    public final String G(String str) {
        if (str.length() <= 24000) {
            return str;
        }
        return StringsKt.take(str, 24000) + "\n…（MCP 结果过长，已截断至 24000 字符）";
    }

    public final void m(JSONArray jSONArray) {
        jSONArray.getClass();
        for (Object obj : d.values()) {
            obj.getClass();
            a aVar = (a) obj;
            Iterator it = aVar.g().iterator();
            while (it.hasNext()) {
                jSONArray.put(lx9.a.f(aVar.d(), (mx9) it.next()));
            }
        }
    }

    public final Object n(String str, String str2, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new b(str, str2, null), continuation);
    }

    public final Object o(tv9 tv9Var, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new c(tv9Var, null), continuation);
    }

    public final Object p(String str, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new d(str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void q(String str) {
        kx9 kx9VarC;
        Job job = (Job) f.remove(str);
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        a aVar = (a) d.remove(str);
        if (aVar == null || (kx9VarC = aVar.c()) == null) {
            return;
        }
        kx9VarC.j();
    }

    public final StateFlow r() {
        return i;
    }

    public final boolean s() {
        Collection collectionValues = d.values();
        collectionValues.getClass();
        Collection<a> collection = collectionValues;
        if (collection.isEmpty()) {
            return false;
        }
        for (a aVar : collection) {
            ix9 ix9Var = a;
            aVar.getClass();
            if (ix9Var.z(aVar)) {
                return true;
            }
        }
        return false;
    }

    public final boolean t() {
        return !d.isEmpty();
    }

    public final boolean u(String str, String str2) {
        int i2 = 0;
        while (true) {
            String str3 = str;
            String str4 = str2;
            int iIndexOf$default = StringsKt.indexOf$default(str3, str4, i2, false, 4, (Object) null);
            if (iIndexOf$default < 0) {
                return false;
            }
            boolean z = iIndexOf$default == 0 || !Character.isLetterOrDigit(str3.charAt(iIndexOf$default + (-1)));
            int length = str4.length() + iIndexOf$default;
            boolean z2 = length >= str3.length() || !Character.isLetterOrDigit(str3.charAt(length));
            if (z && z2) {
                return true;
            }
            i2 = iIndexOf$default + 1;
            str = str3;
            str2 = str4;
        }
    }

    public final Object v(Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new e(null), continuation);
    }

    public final Object w(String str, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new f(str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final boolean x(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return StringsKt.contains$default(lowerCase, "mcp http", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "mcp 网络", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "超时", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "timeout", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "failed to connect", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "connection refused", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "connection reset", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "cleartext", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "unable to resolve", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "stream 结束", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "无响应", false, 2, (Object) null);
    }

    public final tv9 y(String str) {
        str.getClass();
        a aVar = (a) d.get(str);
        if (aVar != null) {
            return aVar.d();
        }
        return null;
    }

    public final boolean z(a aVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(aVar.d().f());
        sb.append(' ');
        sb.append(aVar.f());
        for (mx9 mx9Var : aVar.g()) {
            sb.append(' ');
            sb.append(mx9Var.c());
            sb.append(' ');
            sb.append(mx9Var.a());
        }
        String lowerCase = sb.toString().toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return StringsKt.contains$default(lowerCase, "apk", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "smali", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "androidmanifest", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "classes.dex", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "mt manager", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "mt_apk", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "mt-apk", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "逆向", false, 2, (Object) null) || StringsKt.contains$default(lowerCase, "脱壳", false, 2, (Object) null) || u(lowerCase, "dex") || u(lowerCase, "hook") || u(lowerCase, "dalvik");
    }
}
