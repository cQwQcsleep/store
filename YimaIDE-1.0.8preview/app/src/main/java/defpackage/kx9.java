package defpackage;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.JobKt;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class kx9 {
    public static final a h = new a(null);
    public static final int i = 8;
    public static final MediaType j = MediaType.Companion.get("application/json; charset=utf-8");
    public static final List k = CollectionsKt.listOf(new String[]{"2025-06-18", "2025-03-26", "2024-11-05"});
    public final String a;
    public final Map b;
    public final AtomicLong c;
    public volatile String d;
    public volatile String e;
    public final OkHttpClient f;
    public final OkHttpClient g;

    public static final class b extends ContinuationImpl {
        public Object b;
        public /* synthetic */ Object c;
        public int e;

        public b(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return kx9.this.h(this);
        }
    }

    public static final class c extends ContinuationImpl {
        public Object b;
        public Object c;
        public Object d;
        public Object e;
        public /* synthetic */ Object f;
        public int h;

        public c(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.f = obj;
            this.h |= Integer.MIN_VALUE;
            return kx9.this.l(this);
        }
    }

    public static final class d extends ContinuationImpl {
        public Object b;
        public Object c;
        public Object d;
        public int e;
        public int f;
        public /* synthetic */ Object g;
        public int i;

        public d(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.g = obj;
            this.i |= Integer.MIN_VALUE;
            return kx9.this.m(this);
        }
    }

    public static final class e implements Function1 {
        public final /* synthetic */ Call b;

        public e(Call call) {
            this.b = call;
        }

        public final void a(Throwable th) {
            this.b.cancel();
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            a((Throwable) obj);
            return Unit.INSTANCE;
        }
    }

    public static final class f extends ContinuationImpl {
        public Object b;
        public Object c;
        public Object d;
        public long e;
        public /* synthetic */ Object f;
        public int h;

        public f(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.f = obj;
            this.h |= Integer.MIN_VALUE;
            return kx9.this.r(null, null, this);
        }
    }

    public static final class g implements Function1 {
        public final /* synthetic */ Call b;

        public g(Call call) {
            this.b = call;
        }

        public final void a(Throwable th) {
            this.b.cancel();
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            a((Throwable) obj);
            return Unit.INSTANCE;
        }
    }

    public kx9(String str, Map map) {
        str.getClass();
        map.getClass();
        this.a = str;
        this.b = map;
        this.c = new AtomicLong(1L);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.f = builder.connectTimeout(15L, timeUnit).readTimeout(120L, timeUnit).writeTimeout(30L, timeUnit).build();
        this.g = new OkHttpClient.Builder().connectTimeout(15L, timeUnit).readTimeout(0L, timeUnit).writeTimeout(30L, timeUnit).build();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0063  */
    /* JADX WARN: Code duplicated, block: B:33:0x0074  */
    /* JADX WARN: Code duplicated, block: B:49:0x009d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0051, code lost:
    
        if (r9 == r1) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0081, code lost:
    
        if (r9 == r1) goto L36;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0081 -> B:37:0x0084). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object h(Continuation continuation) {
        b bVar;
        String str;
        String str2;
        if (continuation instanceof b) {
            bVar = (b) continuation;
            int i2 = bVar.e;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                bVar.e = i2 - Integer.MIN_VALUE;
            } else {
                bVar = new b(continuation);
            }
        } else {
            bVar = new b(continuation);
        }
        Object objS = bVar.c;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = bVar.e;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objS);
            bVar.e = 1;
            objS = s(bVar);
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(objS);
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                str2 = (String) bVar.b;
                try {
                    ResultKt.throwOnFailure(objS);
                    str = str2;
                    if (JobKt.isActive(bVar.getContext())) {
                        return "已取消";
                    }
                    bVar.b = SpillingKt.nullOutSpilledVariable(str);
                    bVar.e = 2;
                    if (DelayKt.delay(2000L, bVar) != coroutine_suspended) {
                        str2 = str;
                        bVar.b = SpillingKt.nullOutSpilledVariable(str2);
                        bVar.e = 3;
                        objS = m(bVar);
                    }
                    return coroutine_suspended;
                } catch (CancellationException e2) {
                    throw e2;
                } catch (Exception e3) {
                    String message = e3.getMessage();
                    if (message != null) {
                        String str3 = StringsKt.isBlank(message) ? null : message;
                        if (str3 != null) {
                            return str3;
                        }
                    }
                    return "服务端不可达";
                }
            }
            str2 = (String) bVar.b;
            ResultKt.throwOnFailure(objS);
            bVar.b = SpillingKt.nullOutSpilledVariable(str2);
            bVar.e = 3;
            objS = m(bVar);
        }
        str = (String) objS;
        if (str != null) {
            return str;
        }
        if (JobKt.isActive(bVar.getContext())) {
            return "已取消";
        }
        bVar.b = SpillingKt.nullOutSpilledVariable(str);
        bVar.e = 2;
        if (DelayKt.delay(2000L, bVar) != coroutine_suspended) {
            str2 = str;
            bVar.b = SpillingKt.nullOutSpilledVariable(str2);
            bVar.e = 3;
            objS = m(bVar);
        }
        return coroutine_suspended;
    }

    public final Object i(String str, JSONObject jSONObject, Continuation continuation) {
        return r("tools/call", new JSONObject().put("name", str).put("arguments", jSONObject), continuation);
    }

    public final void j() {
        this.d = null;
        this.e = null;
    }

    public final String k() {
        return this.d;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:26|67|27|(1:29)) */
    /* JADX WARN: Code duplicated, block: B:26:0x0076  */
    /* JADX WARN: Code duplicated, block: B:29:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:33:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:34:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:46:0x0109  */
    /* JADX WARN: Code duplicated, block: B:49:0x0112  */
    /* JADX WARN: Code duplicated, block: B:58:0x012b A[DONT_INVERT, PHI: r0 r11
      0x012b: PHI (r0v6 'e' java.lang.Exception) = (r0v11 'e' java.lang.Exception), (r0v23 'e' java.lang.Exception) binds: [B:25:0x0074, B:36:0x00f3] A[DONT_GENERATE, DONT_INLINE]
      0x012b: PHI (r11v1 org.json.JSONObject) = (r11v2 org.json.JSONObject), (r11v8 org.json.JSONObject) binds: [B:25:0x0074, B:36:0x00f3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:59:0x012d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x0130  */
    /* JADX WARN: Code duplicated, block: B:63:0x0138  */
    /* JADX WARN: Code duplicated, block: B:66:0x0156 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:71:0x00d7 A[EXC_TOP_SPLITTER, PHI: r0 r4 r10 r11
      0x00d7: PHI (r0v17 java.lang.Object) = (r0v13 java.lang.Object), (r0v1 java.lang.Object) binds: [B:28:0x00d3, B:17:0x0053] A[DONT_GENERATE, DONT_INLINE]
      0x00d7: PHI (r4v12 java.lang.String) = (r4v5 java.lang.String), (r4v17 java.lang.String) binds: [B:28:0x00d3, B:17:0x0053] A[DONT_GENERATE, DONT_INLINE]
      0x00d7: PHI (r10v5 java.util.Iterator) = (r10v1 java.util.Iterator), (r10v7 java.util.Iterator) binds: [B:28:0x00d3, B:17:0x0053] A[DONT_GENERATE, DONT_INLINE]
      0x00d7: PHI (r11v6 org.json.JSONObject) = (r11v2 org.json.JSONObject), (r11v10 org.json.JSONObject) binds: [B:28:0x00d3, B:17:0x0053] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fd, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0121, code lost:
    
        throw r0;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00d3 -> B:71:0x00d7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0083 -> B:42:0x00fd). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object l(Continuation continuation) throws Exception {
        Continuation cVar;
        Iterator it;
        Exception e2;
        JSONObject jSONObject;
        String str;
        CancellationException e3;
        JSONObject jSONObject2;
        String str2;
        String str3;
        String message;
        JSONObject jSONObject3;
        String string;
        if (continuation instanceof c) {
            cVar = (c) continuation;
            int i2 = cVar.h;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                cVar.h = i2 - Integer.MIN_VALUE;
            } else {
                cVar = new c(continuation);
            }
        } else {
            cVar = new c(continuation);
        }
        Object objR = cVar.f;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = cVar.h;
        String str4 = null;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objR);
            this.e = null;
            this.d = null;
            it = k.iterator();
            e2 = null;
            jSONObject = null;
            if (it.hasNext()) {
                Object next = it.next();
                next.getClass();
                str2 = (String) next;
                this.d = str4;
                this.e = str4;
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("protocolVersion", str2);
                jSONObject4.put("capabilities", new JSONObject().put("tools", new JSONObject()));
                jSONObject4.put("clientInfo", new JSONObject().put("name", "yima-ide").put("version", "1.0"));
                Unit unit = Unit.INSTANCE;
                cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                cVar.c = jSONObject;
                cVar.d = it;
                cVar.e = str2;
                cVar.h = 1;
                objR = r("initialize", jSONObject4, cVar);
                if (objR == coroutine_suspended) {
                    jSONObject3 = (JSONObject) objR;
                    String strOptString = jSONObject3.optString("protocolVersion", "");
                    strOptString.getClass();
                    string = StringsKt.trim(strOptString).toString();
                    if (!StringsKt.isBlank(string)) {
                        str2 = string;
                    }
                    this.e = str2;
                    jSONObject = jSONObject3;
                    e2 = null;
                    if (jSONObject == null) {
                        if (e2 != null) {
                            throw e2;
                        }
                        throw new sv9("MCP initialize 失败");
                    }
                    jSONObject2 = new JSONObject();
                    cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                    cVar.c = jSONObject;
                    cVar.d = null;
                    cVar.e = null;
                    cVar.h = 2;
                    if (o("notifications/initialized", jSONObject2, cVar) != coroutine_suspended) {
                        return jSONObject;
                    }
                }
            } else {
                if (jSONObject == null) {
                    if (e2 != null) {
                        throw e2;
                    }
                    throw new sv9("MCP initialize 失败");
                }
                jSONObject2 = new JSONObject();
                cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                cVar.c = jSONObject;
                cVar.d = null;
                cVar.e = null;
                cVar.h = 2;
                if (o("notifications/initialized", jSONObject2, cVar) != coroutine_suspended) {
                    return jSONObject;
                }
            }
            return coroutine_suspended;
        }
        if (i3 != 1) {
            if (i3 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            JSONObject jSONObject5 = (JSONObject) cVar.c;
            ResultKt.throwOnFailure(objR);
            return jSONObject5;
        }
        str2 = (String) cVar.e;
        it = (Iterator) cVar.d;
        jSONObject = (JSONObject) cVar.c;
        try {
            try {
                try {
                    ResultKt.throwOnFailure(objR);
                } catch (CancellationException e4) {
                    e3 = e4;
                    str = null;
                    this.d = str;
                    this.e = str;
                    throw e3;
                } catch (Exception e5) {
                    e2 = e5;
                    str3 = null;
                    this.d = str3;
                    this.e = str3;
                    message = e2.getMessage();
                    if (message == null) {
                        message = "";
                    }
                    if (!StringsKt.contains(message, "Protocol-Version", true)) {
                    }
                    str4 = null;
                    if (it.hasNext()) {
                        Object next2 = it.next();
                        next2.getClass();
                        str2 = (String) next2;
                        this.d = str4;
                        this.e = str4;
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("protocolVersion", str2);
                        jSONObject6.put("capabilities", new JSONObject().put("tools", new JSONObject()));
                        jSONObject6.put("clientInfo", new JSONObject().put("name", "yima-ide").put("version", "1.0"));
                        Unit unit2 = Unit.INSTANCE;
                        cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                        cVar.c = jSONObject;
                        cVar.d = it;
                        cVar.e = str2;
                        cVar.h = 1;
                        objR = r("initialize", jSONObject6, cVar);
                        if (objR == coroutine_suspended) {
                        }
                    } else {
                        if (jSONObject == null) {
                            if (e2 != null) {
                                throw e2;
                            }
                            throw new sv9("MCP initialize 失败");
                        }
                        jSONObject2 = new JSONObject();
                        cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                        cVar.c = jSONObject;
                        cVar.d = null;
                        cVar.e = null;
                        cVar.h = 2;
                        if (o("notifications/initialized", jSONObject2, cVar) != coroutine_suspended) {
                            return jSONObject;
                        }
                    }
                    return coroutine_suspended;
                }
            } catch (Exception e6) {
                e2 = e6;
                jSONObject = jSONObject3;
                str3 = null;
                this.d = str3;
                this.e = str3;
                message = e2.getMessage();
                if (message == null) {
                    message = "";
                }
                if (!StringsKt.contains(message, "Protocol-Version", true)) {
                }
                str4 = null;
                if (it.hasNext()) {
                    Object next3 = it.next();
                    next3.getClass();
                    str2 = (String) next3;
                    this.d = str4;
                    this.e = str4;
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("protocolVersion", str2);
                    jSONObject7.put("capabilities", new JSONObject().put("tools", new JSONObject()));
                    jSONObject7.put("clientInfo", new JSONObject().put("name", "yima-ide").put("version", "1.0"));
                    Unit unit3 = Unit.INSTANCE;
                    cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                    cVar.c = jSONObject;
                    cVar.d = it;
                    cVar.e = str2;
                    cVar.h = 1;
                    objR = r("initialize", jSONObject7, cVar);
                    if (objR == coroutine_suspended) {
                        jSONObject3 = (JSONObject) objR;
                        String strOptString2 = jSONObject3.optString("protocolVersion", "");
                        strOptString2.getClass();
                        string = StringsKt.trim(strOptString2).toString();
                        if (!StringsKt.isBlank(string)) {
                            str2 = string;
                        }
                        this.e = str2;
                        jSONObject = jSONObject3;
                        e2 = null;
                        if (jSONObject == null) {
                            if (e2 != null) {
                                throw e2;
                            }
                            throw new sv9("MCP initialize 失败");
                        }
                        jSONObject2 = new JSONObject();
                        cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                        cVar.c = jSONObject;
                        cVar.d = null;
                        cVar.e = null;
                        cVar.h = 2;
                        if (o("notifications/initialized", jSONObject2, cVar) != coroutine_suspended) {
                            return jSONObject;
                        }
                    }
                } else {
                    if (jSONObject == null) {
                        if (e2 != null) {
                            throw e2;
                        }
                        throw new sv9("MCP initialize 失败");
                    }
                    jSONObject2 = new JSONObject();
                    cVar.b = SpillingKt.nullOutSpilledVariable(e2);
                    cVar.c = jSONObject;
                    cVar.d = null;
                    cVar.e = null;
                    cVar.h = 2;
                    if (o("notifications/initialized", jSONObject2, cVar) != coroutine_suspended) {
                        return jSONObject;
                    }
                }
                return coroutine_suspended;
            }
            jSONObject3 = (JSONObject) objR;
            String strOptString3 = jSONObject3.optString("protocolVersion", "");
            strOptString3.getClass();
            string = StringsKt.trim(strOptString3).toString();
            if (!StringsKt.isBlank(string)) {
                str2 = string;
            }
            this.e = str2;
            jSONObject = jSONObject3;
            e2 = null;
            if (jSONObject == null) {
                if (e2 != null) {
                    throw e2;
                }
                throw new sv9("MCP initialize 失败");
            }
            jSONObject2 = new JSONObject();
            cVar.b = SpillingKt.nullOutSpilledVariable(e2);
            cVar.c = jSONObject;
            cVar.d = null;
            cVar.e = null;
            cVar.h = 2;
            if (o("notifications/initialized", jSONObject2, cVar) != coroutine_suspended) {
                return coroutine_suspended;
            }
            return jSONObject;
        } catch (CancellationException e7) {
            e3 = e7;
            str = null;
            this.d = str;
            this.e = str;
            throw e3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bb, code lost:
    
        if (kotlin.text.StringsKt.isBlank(r11) == false) goto L15;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0082 -> B:24:0x0085). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m(Continuation continuation) throws JSONException, sv9 {
        Continuation dVar;
        ArrayList arrayList;
        String strOptString;
        int i2;
        int i3;
        if (continuation instanceof d) {
            dVar = (d) continuation;
            int i4 = dVar.i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                dVar.i = i4 - Integer.MIN_VALUE;
            } else {
                dVar = new d(continuation);
            }
        } else {
            dVar = new d(continuation);
        }
        Object objR = dVar.g;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = dVar.i;
        if (i5 == 0) {
            ResultKt.throwOnFailure(objR);
            arrayList = new ArrayList();
            strOptString = null;
            i2 = 0;
            i3 = 0;
            JobKt.ensureActive(dVar.getContext());
            JSONObject jSONObject = new JSONObject();
            if (strOptString != null && !StringsKt.isBlank(strOptString)) {
                jSONObject.put("cursor", strOptString);
            }
            dVar.b = arrayList;
            dVar.c = SpillingKt.nullOutSpilledVariable(strOptString);
            dVar.d = SpillingKt.nullOutSpilledVariable(jSONObject);
            dVar.e = i3;
            dVar.f = i2;
            dVar.i = 1;
            objR = r("tools/list", jSONObject, dVar);
            if (objR == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i5 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            i2 = dVar.f;
            i3 = dVar.e;
            arrayList = (ArrayList) dVar.b;
            ResultKt.throwOnFailure(objR);
        }
        JSONObject jSONObject2 = (JSONObject) objR;
        arrayList.addAll(lx9.a.e(jSONObject2));
        strOptString = jSONObject2.optString("nextCursor", "");
        strOptString.getClass();
        if (StringsKt.isBlank(strOptString)) {
            strOptString = null;
        }
        i3++;
        if (strOptString != null && !StringsKt.isBlank(strOptString) && i3 >= 20) {
            i2 = 1;
        } else if (strOptString != null) {
        }
        return new nx9(arrayList, i2 != 0);
    }

    public final boolean n(JSONObject jSONObject, long j2) throws JSONException {
        Long longOrNull;
        if (!jSONObject.has("id")) {
            return false;
        }
        Object obj = jSONObject.get("id");
        if (obj instanceof Number) {
            return ((Number) obj).longValue() == j2;
        }
        if (obj instanceof String) {
            return Intrinsics.areEqual(obj, String.valueOf(j2)) || ((longOrNull = StringsKt.toLongOrNull((String) obj)) != null && longOrNull.longValue() == j2);
        }
        return false;
    }

    public final Object o(String str, JSONObject jSONObject, Continuation continuation) throws JSONException {
        JobKt.ensureActive(continuation.getContext());
        JSONObject jSONObjectPut = new JSONObject().put("jsonrpc", "2.0").put(Constants.ATTRNAME_OUTPUT_METHOD, str);
        if (jSONObject != null) {
            jSONObjectPut.put("params", jSONObject);
        }
        jSONObjectPut.getClass();
        Object objQ = q(jSONObjectPut, false, continuation);
        return objQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objQ : Unit.INSTANCE;
    }

    public final JSONObject p(BufferedReader bufferedReader, long j2) throws sv9, IOException {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            int i2 = 0;
            if (line == null) {
                if (sb.length() > 0) {
                    String string = sb.toString();
                    try {
                        Result.Companion companion = Result.Companion;
                        obj = Result.constructor-impl(new JSONObject(string));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (Result.isFailure-impl(obj)) {
                        obj = null;
                    }
                    JSONObject jSONObject = (JSONObject) obj;
                    if (jSONObject != null && n(jSONObject, j2)) {
                        return jSONObject;
                    }
                    try {
                        obj2 = Result.constructor-impl(new JSONArray(string));
                    } catch (Throwable th2) {
                        Result.Companion companion3 = Result.Companion;
                        obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
                    }
                    JSONArray jSONArray = (JSONArray) (Result.isFailure-impl(obj2) ? null : obj2);
                    if (jSONArray != null) {
                        int length = jSONArray.length();
                        while (i2 < length) {
                            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
                            if (jSONObjectOptJSONObject != null && n(jSONObjectOptJSONObject, j2)) {
                                return jSONObjectOptJSONObject;
                            }
                            i2++;
                        }
                    }
                }
                throw new sv9("MCP SSE 流结束但未收到 JSON-RPC 响应");
            }
            if (StringsKt.startsWith$default(line, "data:", false, 2, (Object) null)) {
                String string2 = StringsKt.trim(StringsKt.removePrefix(line, "data:")).toString();
                if (string2.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append('\n');
                    }
                    sb.append(string2);
                }
            } else if (StringsKt.isBlank(line) && sb.length() > 0) {
                String string3 = sb.toString();
                StringsKt.clear(sb);
                try {
                    Result.Companion companion4 = Result.Companion;
                    obj3 = Result.constructor-impl(new JSONObject(string3));
                } catch (Throwable th3) {
                    Result.Companion companion5 = Result.Companion;
                    obj3 = Result.constructor-impl(ResultKt.createFailure(th3));
                }
                if (Result.isFailure-impl(obj3)) {
                    obj3 = null;
                }
                JSONObject jSONObject2 = (JSONObject) obj3;
                if (jSONObject2 != null && n(jSONObject2, j2)) {
                    return jSONObject2;
                }
                try {
                    obj4 = Result.constructor-impl(new JSONArray(string3));
                } catch (Throwable th4) {
                    Result.Companion companion6 = Result.Companion;
                    obj4 = Result.constructor-impl(ResultKt.createFailure(th4));
                }
                JSONArray jSONArray2 = (JSONArray) (Result.isFailure-impl(obj4) ? null : obj4);
                if (jSONArray2 != null) {
                    int length2 = jSONArray2.length();
                    while (i2 < length2) {
                        JSONObject jSONObjectOptJSONObject2 = jSONArray2.optJSONObject(i2);
                        if (jSONObjectOptJSONObject2 != null && n(jSONObjectOptJSONObject2, j2)) {
                            return jSONObjectOptJSONObject2;
                        }
                        i2++;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public final Object q(JSONObject jSONObject, boolean z, Continuation continuation) {
        Object obj;
        JSONObject jSONObject2;
        String string;
        String string2;
        JobKt.ensureActive(continuation.getContext());
        Request.Builder builderHeader = new Request.Builder().url(this.a).header(XIncludeHandler.HTTP_ACCEPT, "application/json, text/event-stream").header("Content-Type", "application/json");
        RequestBody.Companion companion = RequestBody.Companion;
        String string3 = jSONObject.toString();
        string3.getClass();
        Request.Builder builderPost = builderHeader.post(companion.create(string3, j));
        String str = this.e;
        if (str != null) {
            if (StringsKt.isBlank(str)) {
                str = null;
            }
            if (str != null) {
                builderPost.header("MCP-Protocol-Version", str);
            }
        }
        String str2 = this.d;
        if (str2 != null) {
            if (StringsKt.isBlank(str2)) {
                str2 = null;
            }
            if (str2 != null) {
                builderPost.header("Mcp-Session-Id", str2);
            }
        }
        for (Map.Entry entry : this.b.entrySet()) {
            String str3 = (String) entry.getKey();
            String str4 = (String) entry.getValue();
            if (!StringsKt.isBlank(str3) && !StringsKt.isBlank(str4)) {
                builderPost.header(str3, str4);
            }
        }
        Call callNewCall = this.f.newCall(builderPost.build());
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        cancellableContinuationImpl.invokeOnCancellation(new e(callNewCall));
        try {
            Response responseExecute = callNewCall.execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                if (responseBodyBody == null) {
                    if (z) {
                        if (cancellableContinuationImpl.isActive()) {
                            Result.Companion companion2 = Result.Companion;
                            cancellableContinuationImpl.resumeWith(Result.constructor-impl(ResultKt.createFailure(new sv9("MCP HTTP " + responseExecute.code() + "：空响应体"))));
                        }
                    } else if (cancellableContinuationImpl.isActive()) {
                        cancellableContinuationImpl.resumeWith(Result.constructor-impl((Object) null));
                    }
                } else if (responseExecute.isSuccessful()) {
                    String strHeader$default = Response.header$default(responseExecute, "Mcp-Session-Id", (String) null, 2, (Object) null);
                    if (strHeader$default != null && (string2 = StringsKt.trim(strHeader$default).toString()) != null) {
                        if (StringsKt.isBlank(string2)) {
                            string2 = null;
                        }
                        if (string2 != null) {
                            this.d = string2;
                        }
                    }
                    String strHeader$default2 = Response.header$default(responseExecute, "mcp-session-id", (String) null, 2, (Object) null);
                    if (strHeader$default2 != null && (string = StringsKt.trim(strHeader$default2).toString()) != null) {
                        if (StringsKt.isBlank(string)) {
                            string = null;
                        }
                        if (string != null) {
                            this.d = string;
                        }
                    }
                    if (z) {
                        String strHeader$default3 = Response.header$default(responseExecute, "Content-Type", (String) null, 2, (Object) null);
                        if (strHeader$default3 == null) {
                            strHeader$default3 = "";
                        }
                        String lowerCase = strHeader$default3.toLowerCase(Locale.ROOT);
                        lowerCase.getClass();
                        if (StringsKt.contains$default(lowerCase, "text/event-stream", false, 2, (Object) null)) {
                            Reader readerCharStream = responseBodyBody.charStream();
                            jSONObject2 = p(readerCharStream instanceof BufferedReader ? (BufferedReader) readerCharStream : new BufferedReader(readerCharStream, 8192), jSONObject.optLong("id", -1L));
                        } else {
                            String strString = responseBodyBody.string();
                            jSONObject2 = StringsKt.isBlank(strString) ? new JSONObject() : new JSONObject(strString);
                        }
                        if (cancellableContinuationImpl.isActive()) {
                            cancellableContinuationImpl.resumeWith(Result.constructor-impl(jSONObject2));
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.Companion;
                            Result.constructor-impl(responseBodyBody.string());
                        } catch (Throwable th) {
                            Result.Companion companion4 = Result.Companion;
                            Result.constructor-impl(ResultKt.createFailure(th));
                        }
                        if (cancellableContinuationImpl.isActive()) {
                            cancellableContinuationImpl.resumeWith(Result.constructor-impl((Object) null));
                        }
                    }
                } else {
                    try {
                        Result.Companion companion5 = Result.Companion;
                        obj = Result.constructor-impl(responseBodyBody.string());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.Companion;
                        obj = Result.constructor-impl(ResultKt.createFailure(th2));
                    }
                    if (Result.isFailure-impl(obj)) {
                        obj = "";
                    }
                    String str5 = (String) obj;
                    if (cancellableContinuationImpl.isActive()) {
                        int iCode = responseExecute.code();
                        String strTake = StringsKt.take(str5, 300);
                        if (StringsKt.isBlank(strTake)) {
                            strTake = responseExecute.message();
                        }
                        cancellableContinuationImpl.resumeWith(Result.constructor-impl(ResultKt.createFailure(new sv9("MCP HTTP " + iCode + "：" + ((Object) strTake)))));
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(responseExecute, th3);
                    throw th4;
                }
            }
        } catch (InterruptedIOException e2) {
            if (cancellableContinuationImpl.isActive()) {
                String message = e2.getMessage();
                sv9 sv9Var = new sv9("MCP 请求超时或被取消：".concat(message != null ? message : ""));
                Result.Companion companion7 = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.constructor-impl(ResultKt.createFailure(sv9Var)));
            }
        } catch (sv9 e3) {
            if (cancellableContinuationImpl.isActive()) {
                Result.Companion companion8 = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.constructor-impl(ResultKt.createFailure(e3)));
            }
        } catch (Exception e4) {
            if (cancellableContinuationImpl.isActive()) {
                String message2 = e4.getMessage();
                if (message2 == null) {
                    message2 = e4.getClass().getSimpleName();
                }
                sv9 sv9Var2 = new sv9("MCP 网络失败：".concat(message2));
                Result.Companion companion9 = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.constructor-impl(ResultKt.createFailure(sv9Var2)));
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00ad A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x00af  */
    /* JADX WARN: Code duplicated, block: B:36:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object r(String str, JSONObject jSONObject, Continuation continuation) throws JSONException, sv9 {
        f fVar;
        String string;
        String strOptString;
        if (continuation instanceof f) {
            fVar = (f) continuation;
            int i2 = fVar.h;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                fVar.h = i2 - Integer.MIN_VALUE;
            } else {
                fVar = new f(continuation);
            }
        } else {
            fVar = new f(continuation);
        }
        Object objQ = fVar.f;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = fVar.h;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objQ);
            JobKt.ensureActive(fVar.getContext());
            long andIncrement = this.c.getAndIncrement();
            JSONObject jSONObjectPut = new JSONObject().put("jsonrpc", "2.0").put("id", andIncrement).put(Constants.ATTRNAME_OUTPUT_METHOD, str);
            if (jSONObject != null) {
                jSONObjectPut.put("params", jSONObject);
            }
            jSONObjectPut.getClass();
            fVar.b = str;
            fVar.c = SpillingKt.nullOutSpilledVariable(jSONObject);
            fVar.d = SpillingKt.nullOutSpilledVariable(jSONObjectPut);
            fVar.e = andIncrement;
            fVar.h = 1;
            objQ = q(jSONObjectPut, true, fVar);
            if (objQ == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            str = (String) fVar.b;
            ResultKt.throwOnFailure(objQ);
        }
        JSONObject jSONObject2 = (JSONObject) objQ;
        if (jSONObject2 == null) {
            throw new sv9("MCP 无响应：" + str);
        }
        if (!jSONObject2.has("error")) {
            Object objOpt = jSONObject2.opt(Constants.EXSLT_ELEMNAME_FUNCRESULT_STRING);
            if (objOpt instanceof JSONObject) {
                return (JSONObject) objOpt;
            }
            if (objOpt == null) {
                return new JSONObject();
            }
            JSONObject jSONObjectPut2 = new JSONObject().put("value", objOpt);
            jSONObjectPut2.getClass();
            return jSONObjectPut2;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("error");
        if (jSONObjectOptJSONObject == null || (strOptString = jSONObjectOptJSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING)) == null) {
            string = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.toString() : null;
            if (string == null) {
                strOptString = "unknown error";
            } else {
                strOptString = string;
            }
        } else {
            if (StringsKt.isBlank(strOptString)) {
                strOptString = null;
            }
            if (strOptString == null) {
                if (jSONObjectOptJSONObject != null) {
                }
                if (string == null) {
                    strOptString = "unknown error";
                } else {
                    strOptString = string;
                }
            }
        }
        throw new sv9("MCP 错误（" + str + "）：" + strOptString);
    }

    /* JADX WARN: Code duplicated, block: B:112:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:85:0x018b  */
    public final Object s(Continuation continuation) {
        String str;
        String str2 = "连接中断";
        JobKt.ensureActive(continuation.getContext());
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Request.Builder builderHeader = new Request.Builder().url(this.a).get().header(XIncludeHandler.HTTP_ACCEPT, "text/event-stream");
        String str3 = this.e;
        if (str3 != null) {
            if (StringsKt.isBlank(str3)) {
                str3 = null;
            }
            if (str3 != null) {
                builderHeader.header("MCP-Protocol-Version", str3);
            }
        }
        String strK = k();
        if (strK != null) {
            if (StringsKt.isBlank(strK)) {
                strK = null;
            }
            if (strK != null) {
                builderHeader.header("Mcp-Session-Id", strK);
            }
        }
        for (Map.Entry entry : this.b.entrySet()) {
            String str4 = (String) entry.getKey();
            String str5 = (String) entry.getValue();
            if (!StringsKt.isBlank(str4) && !StringsKt.isBlank(str5)) {
                builderHeader.header(str4, str5);
            }
        }
        Call callNewCall = this.g.newCall(builderHeader.build());
        cancellableContinuationImpl.invokeOnCancellation(new g(callNewCall));
        try {
            Response responseExecute = callNewCall.execute();
            try {
                int iCode = responseExecute.code();
                if (iCode == 404 || iCode == 405 || iCode == 501) {
                    if (cancellableContinuationImpl.isActive()) {
                        cancellableContinuationImpl.resumeWith(Result.constructor-impl((Object) null));
                    }
                } else if (responseExecute.isSuccessful()) {
                    String strHeader$default = Response.header$default(responseExecute, "Content-Type", (String) null, 2, (Object) null);
                    if (strHeader$default == null) {
                        strHeader$default = "";
                    }
                    String lowerCase = strHeader$default.toLowerCase(Locale.ROOT);
                    lowerCase.getClass();
                    boolean z = false;
                    if (StringsKt.contains$default(lowerCase, "text/event-stream", false, 2, (Object) null)) {
                        ResponseBody responseBodyBody = responseExecute.body();
                        if (responseBodyBody != null) {
                            try {
                                try {
                                    BufferedSource bufferedSourceSource = responseBodyBody.source();
                                    while (!bufferedSourceSource.exhausted() && cancellableContinuationImpl.isActive()) {
                                        try {
                                            bufferedSourceSource.readUtf8Line();
                                            z = true;
                                        } catch (Throwable th) {
                                            try {
                                                throw th;
                                            } catch (Throwable th2) {
                                                CloseableKt.closeFinally(bufferedSourceSource, th);
                                                throw th2;
                                            }
                                        }
                                    }
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(bufferedSourceSource, (Throwable) null);
                                    if (cancellableContinuationImpl.isActive()) {
                                        if (z) {
                                            cancellableContinuationImpl.resumeWith(Result.constructor-impl("服务端已关闭连接"));
                                        } else {
                                            cancellableContinuationImpl.resumeWith(Result.constructor-impl((Object) null));
                                        }
                                    }
                                } catch (Exception e2) {
                                    if (cancellableContinuationImpl.isActive()) {
                                        String message = e2.getMessage();
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
                                        cancellableContinuationImpl.resumeWith(Result.constructor-impl(message));
                                    }
                                }
                            } catch (CancellationException unused) {
                                if (cancellableContinuationImpl.isActive()) {
                                    cancellableContinuationImpl.resumeWith(Result.constructor-impl("已取消"));
                                }
                            }
                        } else if (cancellableContinuationImpl.isActive()) {
                            cancellableContinuationImpl.resumeWith(Result.constructor-impl((Object) null));
                        }
                    } else if (cancellableContinuationImpl.isActive()) {
                        cancellableContinuationImpl.resumeWith(Result.constructor-impl((Object) null));
                    }
                } else if (cancellableContinuationImpl.isActive()) {
                    Result.Companion companion = Result.Companion;
                    cancellableContinuationImpl.resumeWith(Result.constructor-impl(StringsKt.trim("HTTP " + iCode + " " + responseExecute.message()).toString()));
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(responseExecute, th3);
                    throw th4;
                }
            }
        } catch (InterruptedIOException e3) {
            if (cancellableContinuationImpl.isActive()) {
                String message2 = e3.getMessage();
                if (message2 != null) {
                    str = StringsKt.isBlank(message2) ? null : message2;
                    if (str != null) {
                        str2 = str;
                    }
                }
                cancellableContinuationImpl.resumeWith(Result.constructor-impl(str2));
            }
        } catch (Exception e4) {
            if (cancellableContinuationImpl.isActive()) {
                String message3 = e4.getMessage();
                if (message3 == null) {
                    str = "网络中断";
                } else {
                    str = StringsKt.isBlank(message3) ? null : message3;
                    if (str == null) {
                        str = "网络中断";
                    }
                }
                cancellableContinuationImpl.resumeWith(Result.constructor-impl(str));
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }
}
