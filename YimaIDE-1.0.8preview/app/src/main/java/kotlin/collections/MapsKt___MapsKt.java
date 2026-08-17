package kotlin.collections;

import defpackage.a32;
import defpackage.hb9;
import defpackage.z0e;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001an\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fb\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\t\u001ap\u0010\u000e\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fb\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\t\u001a:\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u001a]\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00130\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a\u0082\u0001\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00140\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0015ø\u0001\u0000¢\u0006\u0002\b\u0015\u001az\u0010\u001a\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001\"\u0010\b\u0003\u0010\u001b*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u001c*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u001d\u001a\u0002H\u001b2$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00130\u0007H\u0087\u0088\bb\u0002\b\u001fø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u009c\u0001\u0010\u001a\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001\"\u0010\b\u0003\u0010\u001b*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u001c*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u001d\u001a\u0002H\u001b2$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00140\u0007H\u0087\u0088\bb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b( b\u0002\b\u001fø\u0001\u0000¢\u0006\u0004\b \u0010\u001e\u001aW\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a]\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001az\u0010#\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004\"\u0010\b\u0003\u0010\u001b*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u001c*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u001d\u001a\u0002H\u001b2 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0087\u0088\bb\u0002\b\u001fø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001at\u0010$\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001\"\u0010\b\u0003\u0010\u001b*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u001c*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u001d\u001a\u0002H\u001b2\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\bb\u0002\b\u001fø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001aK\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020&0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a(\u0010(\u001a\u00020&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u001aK\u0010(\u001a\u00020&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020&0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a,\u0010)\u001a\u00020*\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0087\u0088\u0004b\u0002\b\r\u001aK\u0010)\u001a\u00020*\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020&0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001aO\u0010+\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010-\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0\u0007H\u0087\u0088\u0004b\u0002\b.ø\u0001\u0000\u001a\u008c\u0001\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(3b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(2b\u0002\b\rø\u0001\u0000¢\u0006\u0002\b2\u001a{\u00104\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\rø\u0001\u0000\u001aa\u00105\u001a\u000206\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002060\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000\u001aa\u00105\u001a\u000207\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002070\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000\u001av\u00105\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u00108\u001ah\u00109\u001a\u0004\u0018\u000106\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002060\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010:\u001ah\u00109\u001a\u0004\u0018\u000107\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002070\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010;\u001ax\u00109\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u00108\u001a\u0088\u0001\u0010<\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u0010=\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010>j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`?2\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010@\u001a\u008a\u0001\u0010A\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u0010=\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010>j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`?2\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010@\u001a\u008f\u0001\u0010B\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u0010=\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0>j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`?H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(3b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(Cb\u0002\b\r¢\u0006\u0004\bC\u0010D\u001a\u0081\u0001\u0010E\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u0010=\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0>j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`?H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\r¢\u0006\u0002\u0010D\u001a\u008c\u0001\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(3b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(Gb\u0002\b\rø\u0001\u0000¢\u0006\u0002\bG\u001a{\u0010H\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\rø\u0001\u0000\u001aa\u0010I\u001a\u000206\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002060\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000\u001aa\u0010I\u001a\u000207\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002070\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000\u001av\u0010I\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u00108\u001ah\u0010J\u001a\u0004\u0018\u000106\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002060\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010:\u001ah\u0010J\u001a\u0004\u0018\u000107\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002070\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010;\u001ax\u0010J\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u000100*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u00108\u001a\u0088\u0001\u0010K\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u0010=\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010>j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`?2\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010@\u001a\u008a\u0001\u0010L\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u0010=\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010>j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`?2\u001e\u00101\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\u0017b\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010@\u001a\u008f\u0001\u0010M\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u0010=\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0>j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`?H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(3b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(Nb\u0002\b\r¢\u0006\u0004\bN\u0010D\u001a\u0081\u0001\u0010O\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u0010=\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0>j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`?H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016b\u0002\b\r¢\u0006\u0002\u0010D\u001a(\u0010P\u001a\u00020&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u001aK\u0010P\u001a\u00020&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020&0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001ah\u0010Q\u001a\u0002HR\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010R*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005*\u0002HR2\u001e\u0010-\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0\u0007H\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(Tø\u0001\u0000¢\u0006\u0002\u0010S\u001a}\u0010U\u001a\u0002HR\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010R*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005*\u0002HR23\u0010-\u001a/\u0012\u0013\u0012\u00110*¢\u0006\f\bW\u0012\b\b\u0019\u0012\u0004\b\b(X\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0VH\u0087\u0088\u0004b\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0016ø\u0001\u0000¢\u0006\u0002\u0010Y\u001a>\u0010Z\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0\u0013\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0087\u0088\u0004b\u0002\b\r\u001a:\u0010[\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\\"}, d2 = {"firstNotNullOf", "R", "K", "V", "", "", "transform", "Lkotlin/Function1;", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlin/SinceKotlin;", "version", "1.5", "Lkotlin/internal/InlineOnly;", "firstNotNullOfOrNull", "toList", "", "Lkotlin/Pair;", "flatMap", "", "Lkotlin/sequences/Sequence;", "flatMapSequence", "1.4", "Lkotlin/OverloadResolutionByLambdaReturnType;", "Lkotlin/jvm/JvmName;", "name", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "Lkotlin/IgnorableReturnValue;", "flatMapSequenceTo", "map", "mapNotNull", "mapNotNullTo", "mapTo", "all", "", "predicate", "any", "count", "", "forEach", "", "action", "Lkotlin/internal/HidesMembers;", "maxBy", "", "selector", "maxByOrThrow", "1.7", "maxByOrNull", "maxOf", "", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrThrow", "(Ljava/util/Map;Ljava/util/Comparator;)Ljava/util/Map$Entry;", "maxWithOrNull", "minBy", "minByOrThrow", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrThrow", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "1.1", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "asIterable", "asSequence", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/collections/MapsKt")
class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
    public static final <K, V> boolean all(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        map.getClass();
        function1.getClass();
        if (map.isEmpty()) {
            return true;
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            if (!function1.invoke(it2.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        map.getClass();
        function1.getClass();
        if (map.isEmpty()) {
            return false;
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            if (function1.invoke(it2.next()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private static final <K, V> Iterable<Map.Entry<K, V>> asIterable(Map<? extends K, ? extends V> map) {
        map.getClass();
        return map.entrySet();
    }

    public static <K, V> Sequence<Map.Entry<K, V>> asSequence(Map<? extends K, ? extends V> map) {
        map.getClass();
        return CollectionsKt___CollectionsKt.asSequence(map.entrySet());
    }

    public static final <K, V> int count(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        map.getClass();
        function1.getClass();
        int i = 0;
        if (map.isEmpty()) {
            return 0;
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            if (function1.invoke(it2.next()).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    private static final <K, V, R> R firstNotNullOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        R rInvoke;
        map.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        do {
            if (!it2.hasNext()) {
                rInvoke = null;
                break;
            }
            rInvoke = function1.invoke(it2.next());
        } while (rInvoke == null);
        if (rInvoke != null) {
            return rInvoke;
        }
        hb9.a("No element of the map was transformed to a non-null value.");
        return null;
    }

    private static final <K, V, R> R firstNotNullOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            R rInvoke = function1.invoke(it2.next());
            if (rInvoke != null) {
                return rInvoke;
            }
        }
        return null;
    }

    public static final <K, V, R> List<R> flatMap(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> function1) {
        map.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, function1.invoke(it2.next()));
        }
        return arrayList;
    }

    public static final <K, V, R> List<R> flatMapSequence(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> function1) {
        map.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, function1.invoke(it2.next()));
        }
        return arrayList;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C flatMapSequenceTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> function1) {
        map.getClass();
        c.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(c, function1.invoke(it2.next()));
        }
        return c;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> function1) {
        map.getClass();
        c.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(c, function1.invoke(it2.next()));
        }
        return c;
    }

    public static final <K, V> void forEach(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> function1) {
        map.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            function1.invoke(it2.next());
        }
    }

    public static final <K, V, R> List<R> map(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList(map.size());
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            arrayList.add(function1.invoke(it2.next()));
        }
        return arrayList;
    }

    public static final <K, V, R> List<R> mapNotNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            R rInvoke = function1.invoke(it2.next());
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
        }
        return arrayList;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C mapNotNullTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        c.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            R rInvoke = function1.invoke(it2.next());
            if (rInvoke != null) {
                c.add(rInvoke);
            }
        }
        return c;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C mapTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        c.getClass();
        function1.getClass();
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            c.add(function1.invoke(it2.next()));
        }
        return c;
    }

    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxByOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Map.Entry<K, V> entry;
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (it2.hasNext()) {
            Map.Entry<K, V> entry2 = (Object) it2.next();
            if (it2.hasNext()) {
                R rInvoke = function1.invoke(entry2);
                do {
                    Map.Entry<K, V> entry3 = (Object) it2.next();
                    R rInvoke2 = function1.invoke(entry3);
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        entry2 = entry3;
                        rInvoke = rInvoke2;
                    }
                } while (it2.hasNext());
            }
            entry = entry2;
        } else {
            entry = null;
        }
        return entry;
    }

    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxByOrThrow(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        Map.Entry<K, V> entry = (Object) it2.next();
        if (it2.hasNext()) {
            R rInvoke = function1.invoke(entry);
            do {
                Map.Entry<K, V> entry2 = (Object) it2.next();
                R rInvoke2 = function1.invoke(entry2);
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    entry = entry2;
                    rInvoke = rInvoke2;
                }
            } while (it2.hasNext());
        }
        return entry;
    }

    private static final <K, V> double maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return 0.0d;
        }
        double dDoubleValue = function1.invoke((Object) it2.next()).doubleValue();
        while (it2.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, function1.invoke((Object) it2.next()).doubleValue());
        }
        return dDoubleValue;
    }

    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    private static final <K, V> Double m487maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        double dDoubleValue = function1.invoke((Object) it2.next()).doubleValue();
        while (it2.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, function1.invoke((Object) it2.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    private static final <K, V, R> R maxOfWith(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        comparator.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (comparator.compare(rInvoke, rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    private static final <K, V, R> R maxOfWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        comparator.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (comparator.compare(rInvoke, rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    private static final <K, V> Map.Entry<K, V> maxWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        map.getClass();
        comparator.getClass();
        return (Map.Entry) CollectionsKt___CollectionsKt.maxWithOrNull(map.entrySet(), comparator);
    }

    private static final <K, V> Map.Entry<K, V> maxWithOrThrow(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        map.getClass();
        comparator.getClass();
        return (Map.Entry) CollectionsKt___CollectionsKt.maxWithOrThrow(map.entrySet(), comparator);
    }

    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minByOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Map.Entry<K, V> entry;
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (it2.hasNext()) {
            Map.Entry<K, V> entry2 = (Object) it2.next();
            if (it2.hasNext()) {
                R rInvoke = function1.invoke(entry2);
                do {
                    Map.Entry<K, V> entry3 = (Object) it2.next();
                    R rInvoke2 = function1.invoke(entry3);
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        entry2 = entry3;
                        rInvoke = rInvoke2;
                    }
                } while (it2.hasNext());
            }
            entry = entry2;
        } else {
            entry = null;
        }
        return entry;
    }

    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minByOrThrow(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        Map.Entry<K, V> entry = (Object) it2.next();
        if (it2.hasNext()) {
            R rInvoke = function1.invoke(entry);
            do {
                Map.Entry<K, V> entry2 = (Object) it2.next();
                R rInvoke2 = function1.invoke(entry2);
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    entry = entry2;
                    rInvoke = rInvoke2;
                }
            } while (it2.hasNext());
        }
        return entry;
    }

    private static final <K, V> double minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return 0.0d;
        }
        double dDoubleValue = function1.invoke((Object) it2.next()).doubleValue();
        while (it2.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, function1.invoke((Object) it2.next()).doubleValue());
        }
        return dDoubleValue;
    }

    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    private static final <K, V> Double m491minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        double dDoubleValue = function1.invoke((Object) it2.next()).doubleValue();
        while (it2.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, function1.invoke((Object) it2.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    private static final <K, V, R> R minOfWith(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        comparator.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (comparator.compare(rInvoke, rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    private static final <K, V, R> R minOfWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        comparator.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (comparator.compare(rInvoke, rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    private static final <K, V> Map.Entry<K, V> minWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        map.getClass();
        comparator.getClass();
        return (Map.Entry) CollectionsKt___CollectionsKt.minWithOrNull(map.entrySet(), comparator);
    }

    private static final <K, V> Map.Entry<K, V> minWithOrThrow(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        map.getClass();
        comparator.getClass();
        return (Map.Entry) CollectionsKt___CollectionsKt.minWithOrThrow(map.entrySet(), comparator);
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        map.getClass();
        function1.getClass();
        if (map.isEmpty()) {
            return true;
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            if (function1.invoke(it2.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(M m, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> function1) {
        m.getClass();
        function1.getClass();
        Iterator<Map.Entry<K, V>> it2 = m.entrySet().iterator();
        while (it2.hasNext()) {
            function1.invoke(it2.next());
        }
        return m;
    }

    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEachIndexed(M m, Function2<? super Integer, ? super Map.Entry<? extends K, ? extends V>, Unit> function2) {
        m.getClass();
        function2.getClass();
        Iterator<T> it2 = m.entrySet().iterator();
        int i = 0;
        while (it2.hasNext()) {
            a32 a32Var = (Object) it2.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            function2.invoke(Integer.valueOf(i), a32Var);
            i = i2;
        }
        return m;
    }

    public static <K, V> List<Pair<K, V>> toList(Map<? extends K, ? extends V> map) {
        map.getClass();
        if (map.size() == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        Map.Entry<? extends K, ? extends V> next = it2.next();
        if (!it2.hasNext()) {
            return CollectionsKt__CollectionsJVMKt.listOf(new Pair(next.getKey(), next.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new Pair(next.getKey(), next.getValue()));
        do {
            Map.Entry<? extends K, ? extends V> next2 = it2.next();
            arrayList.add(new Pair(next2.getKey(), next2.getValue()));
        } while (it2.hasNext());
        return arrayList;
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> map) {
        map.getClass();
        return !map.isEmpty();
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> map) {
        map.getClass();
        return map.isEmpty();
    }

    private static final <K, V> int count(Map<? extends K, ? extends V> map) {
        map.getClass();
        return map.size();
    }

    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    private static final <K, V> float m485maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return 0.0f;
        }
        float fFloatValue = function1.invoke((Object) it2.next()).floatValue();
        while (it2.hasNext()) {
            fFloatValue = Math.max(fFloatValue, function1.invoke((Object) it2.next()).floatValue());
        }
        return fFloatValue;
    }

    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    private static final <K, V> Float m488maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        float fFloatValue = function1.invoke((Object) it2.next()).floatValue();
        while (it2.hasNext()) {
            fFloatValue = Math.max(fFloatValue, function1.invoke((Object) it2.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    private static final <K, V> float m489minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return 0.0f;
        }
        float fFloatValue = function1.invoke((Object) it2.next()).floatValue();
        while (it2.hasNext()) {
            fFloatValue = Math.min(fFloatValue, function1.invoke((Object) it2.next()).floatValue());
        }
        return fFloatValue;
    }

    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    private static final <K, V> Float m492minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        float fFloatValue = function1.invoke((Object) it2.next()).floatValue();
        while (it2.hasNext()) {
            fFloatValue = Math.min(fFloatValue, function1.invoke((Object) it2.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    private static final <K, V, R extends Comparable<? super R>> R m486maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    private static final <K, V, R extends Comparable<? super R>> R maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    private static final <K, V, R extends Comparable<? super R>> R m490minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    private static final <K, V, R extends Comparable<? super R>> R minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        map.getClass();
        function1.getClass();
        Iterator<T> it2 = map.entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) it2.next());
        while (it2.hasNext()) {
            R rInvoke2 = function1.invoke((Object) it2.next());
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }
}
