package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ð\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u0006\u001a\u00028\u00002\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00122\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0013J\u001d\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00152\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00182\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020!2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020$2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020'2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020*2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010+J\u001d\u0010,\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020-2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002002\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00101J\u001d\u00102\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00104J\u001d\u00105\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002062\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00107J\u001d\u00108\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u0002092\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010:J\u001d\u0010;\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020<2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010=J\u001d\u0010>\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020?2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010@J\u001d\u0010A\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020B2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010CJ\u001d\u0010D\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020E2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010FJ\u001d\u0010G\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020H2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010IJ\u001d\u0010J\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020K2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010LJ\u001d\u0010M\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020N2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010OJ\u001d\u0010P\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020Q2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010RJ\u001d\u0010S\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020T2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010UJ\u001d\u0010V\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020W2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010XJ\u001d\u0010Y\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020Z2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010[J\u001d\u0010\\\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020]2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010^J\u001d\u0010_\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020`2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010aJ\u001d\u0010b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020c2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010dJ\u001d\u0010e\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020f2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010gJ\u001d\u0010h\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020i2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010jJ\u001d\u0010k\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020l2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010mJ\u001d\u0010n\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020o2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010pJ\u001d\u0010q\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020r2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010sJ\u001d\u0010t\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020u2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010vJ\u001d\u0010w\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020x2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010yJ\u001d\u0010z\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020{2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010|J\u001d\u0010}\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020~2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u007fJ \u0010\u0080\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0081\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0082\u0001J \u0010\u0083\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0084\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0085\u0001J \u0010\u0086\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0087\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0088\u0001J \u0010\u0089\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u008a\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008b\u0001J \u0010\u008c\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u008d\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008e\u0001J \u0010\u008f\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0090\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0091\u0001J \u0010\u0092\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0093\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0094\u0001J \u0010\u0095\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0096\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0001J \u0010\u0098\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0099\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009a\u0001J \u0010\u009b\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u009c\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009d\u0001J \u0010\u009e\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u009f\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010 \u0001J \u0010¡\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030¢\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010£\u0001J \u0010¤\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030¥\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¦\u0001J \u0010§\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030¨\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010©\u0001J \u0010ª\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030«\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¬\u0001J \u0010\u00ad\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030®\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¯\u0001J \u0010°\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030±\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010²\u0001J \u0010³\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030´\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010µ\u0001J \u0010¶\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030·\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¸\u0001J \u0010¹\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030º\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010»\u0001J \u0010¼\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030½\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¾\u0001J \u0010¿\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030À\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Á\u0001J \u0010Â\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Ã\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ä\u0001J \u0010Å\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Æ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ç\u0001J \u0010È\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030É\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ê\u0001J \u0010Ë\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Ì\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Í\u0001J \u0010Î\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Ï\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ð\u0001J \u0010Ñ\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Ò\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ó\u0001J \u0010Ô\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Õ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ö\u0001J \u0010×\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Ø\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ù\u0001J \u0010Ú\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Û\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ü\u0001J \u0010Ý\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030Þ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ß\u0001J \u0010à\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030á\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010â\u0001J \u0010ã\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ä\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010å\u0001J \u0010æ\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ç\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010è\u0001J \u0010é\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ê\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ë\u0001J \u0010ì\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030í\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010î\u0001J \u0010ï\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ð\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ñ\u0001J \u0010ò\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ó\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ô\u0001J \u0010õ\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ö\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010÷\u0001J \u0010ø\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ù\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ú\u0001J \u0010û\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ü\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ý\u0001J \u0010þ\u0001\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030ÿ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0080\u0002J \u0010\u0081\u0002\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0082\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0083\u0002J \u0010\u0084\u0002\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0085\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0086\u0002J \u0010\u0087\u0002\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u0088\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0089\u0002J \u0010\u008a\u0002\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u008b\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0002J \u0010\u008d\u0002\u001a\u00028\u00002\u0007\u0010\u0007\u001a\u00030\u008e\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008f\u0002¨\u0006\u0090\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "R", "D", Argument.Delimiters.none, "<init>", "()V", "visitNode", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLocalFunctionDeclarationNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnterValueParameterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnterDefaultArgumentsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitExitDefaultArgumentsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitExitValueParameterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitSplitPostponedLambdasNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitPostponedLambdaExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitMergePostponedLambdaExitsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousFunctionCaptureNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousFunctionExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFileEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFileExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousObjectEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousObjectExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLocalClassExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalClassExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalClassExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitScriptEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitScriptExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitCodeFragmentEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitCodeFragmentExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitDelegateExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFieldInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFieldInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitInitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitInitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenBranchConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenBranchConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenBranchResultEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenBranchResultExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenSyntheticElseBranchNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitTryExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitTryMainBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitTryMainBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitCatchClauseEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitCatchClauseExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFinallyBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFinallyBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitTryExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanOperatorEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanOperatorExitLeftOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanOperatorEnterRightOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanOperatorExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitComparisonExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitEqualityOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitJumpNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitLiteralExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitCheckNotNullCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitQualifiedAccessNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedQualifierNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionCallArgumentsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionCallArgumentsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionCallEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionCallExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReferenceNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitGetClassCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitDelegatedConstructorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitStringConcatenationCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitThrowExceptionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitStubNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariableDeclarationEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnterSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitExitSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenSubjectExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitElvisLhsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitElvisLhsIsNotNullNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitElvisRhsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitElvisExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitSmartCastExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;Ljava/lang/Object;)Ljava/lang/Object;", "visitFakeExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ControlFlowGraphVisitor<R, D> {
    public R visitAnonymousFunctionCaptureNode(AnonymousFunctionCaptureNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitAnonymousFunctionExpressionNode(AnonymousFunctionExpressionNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitAnonymousObjectEnterNode(AnonymousObjectEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitAnonymousObjectExpressionExitNode(AnonymousObjectExpressionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitBlockEnterNode(BlockEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitBlockExitNode(BlockExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitBooleanOperatorEnterNode(BooleanOperatorEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitBooleanOperatorEnterRightOperandNode(BooleanOperatorEnterRightOperandNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitBooleanOperatorExitLeftOperandNode(BooleanOperatorExitLeftOperandNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitBooleanOperatorExitNode(BooleanOperatorExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitCallableReferenceNode(CallableReferenceNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitCatchClauseEnterNode(CatchClauseEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitCatchClauseExitNode(CatchClauseExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitCheckNotNullCallNode(CheckNotNullCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitClassEnterNode(ClassEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitClassExitNode(ClassExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitCodeFragmentEnterNode(CodeFragmentEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitCodeFragmentExitNode(CodeFragmentExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitComparisonExpressionNode(ComparisonExpressionNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitDelegateExpressionExitNode(DelegateExpressionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitDelegatedConstructorCallNode(DelegatedConstructorCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitElvisExitNode(ElvisExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitElvisLhsExitNode(ElvisLhsExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitElvisLhsIsNotNullNode(ElvisLhsIsNotNullNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitElvisRhsEnterNode(ElvisRhsEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitEnterDefaultArgumentsNode(EnterDefaultArgumentsNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitEnterSafeCallNode(EnterSafeCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitEnterValueParameterNode(EnterValueParameterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitEqualityOperatorCallNode(EqualityOperatorCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitExitDefaultArgumentsNode(ExitDefaultArgumentsNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitExitSafeCallNode(ExitSafeCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitExitValueParameterNode(ExitValueParameterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFakeExpressionEnterNode(FakeExpressionEnterNode node, D data) {
        node.getClass();
        throw new IllegalStateException("fake expressions should not appear in graphs");
    }

    public R visitFieldInitializerEnterNode(FieldInitializerEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFieldInitializerExitNode(FieldInitializerExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFileEnterNode(FileEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFileExitNode(FileExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFinallyBlockEnterNode(FinallyBlockEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFinallyBlockExitNode(FinallyBlockExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFunctionCallArgumentsEnterNode(FunctionCallArgumentsEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFunctionCallArgumentsExitNode(FunctionCallArgumentsExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFunctionCallEnterNode(FunctionCallEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFunctionCallExitNode(FunctionCallExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFunctionEnterNode(FunctionEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitFunctionExitNode(FunctionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitGetClassCallNode(GetClassCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitInitBlockEnterNode(InitBlockEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitInitBlockExitNode(InitBlockExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitJumpNode(JumpNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLiteralExpressionNode(LiteralExpressionNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLocalClassExitNode(LocalClassExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLocalFunctionDeclarationNode(LocalFunctionDeclarationNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLoopBlockEnterNode(LoopBlockEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLoopBlockExitNode(LoopBlockExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLoopConditionEnterNode(LoopConditionEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLoopConditionExitNode(LoopConditionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLoopEnterNode(LoopEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitLoopExitNode(LoopExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitMergePostponedLambdaExitsNode(MergePostponedLambdaExitsNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public abstract R visitNode(CFGNode<?> node, D data);

    public R visitPostponedLambdaExitNode(PostponedLambdaExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitPropertyInitializerEnterNode(PropertyInitializerEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitPropertyInitializerExitNode(PropertyInitializerExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitQualifiedAccessNode(QualifiedAccessNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitResolvedQualifierNode(ResolvedQualifierNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitScriptEnterNode(ScriptEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitScriptExitNode(ScriptExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitSmartCastExpressionExitNode(SmartCastExpressionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitSplitPostponedLambdasNode(SplitPostponedLambdasNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitStringConcatenationCallNode(StringConcatenationCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitStubNode(StubNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitThrowExceptionNode(ThrowExceptionNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitTryExpressionEnterNode(TryExpressionEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitTryExpressionExitNode(TryExpressionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitTryMainBlockEnterNode(TryMainBlockEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitTryMainBlockExitNode(TryMainBlockExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitTypeOperatorCallNode(TypeOperatorCallNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitVariableAssignmentNode(VariableAssignmentNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitVariableDeclarationEnterNode(VariableDeclarationEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitVariableDeclarationExitNode(VariableDeclarationExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenBranchConditionEnterNode(WhenBranchConditionEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenBranchConditionExitNode(WhenBranchConditionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenBranchResultEnterNode(WhenBranchResultEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenBranchResultExitNode(WhenBranchResultExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenEnterNode(WhenEnterNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenExitNode(WhenExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenSubjectExpressionExitNode(WhenSubjectExpressionExitNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }

    public R visitWhenSyntheticElseBranchNode(WhenSyntheticElseBranchNode node, D data) {
        node.getClass();
        return visitNode(node, data);
    }
}
