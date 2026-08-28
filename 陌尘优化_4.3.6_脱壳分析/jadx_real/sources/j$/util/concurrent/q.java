package j$.util.concurrent;

import java.util.concurrent.locks.LockSupport;

/* loaded from: /workspace/unpacked/classes3.dex */
final class q extends l {
    private static final j$.sun.misc.a h;
    private static final long i;
    r e;
    volatile r f;
    volatile Thread g;
    volatile int lockState;

    static int i(Object obj, Object obj2) {
        int iCompareTo;
        return (obj == null || obj2 == null || (iCompareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) == 0) ? System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1 : iCompareTo;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004b A[PHI: r7
      0x004b: PHI (r7v3 java.lang.Class<?>) = (r7v2 java.lang.Class<?>), (r7v4 java.lang.Class<?>) binds: [B:24:0x0049, B:16:0x0033] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    q(r rVar) {
        int i2;
        super(-2, null, null);
        this.f = rVar;
        r rVar2 = null;
        while (rVar != null) {
            r rVar3 = (r) rVar.d;
            rVar.g = null;
            rVar.f = null;
            if (rVar2 == null) {
                rVar.e = null;
                rVar.i = false;
            } else {
                Object obj = rVar.b;
                int i3 = rVar.a;
                r rVar4 = rVar2;
                Class<?> clsC = null;
                while (true) {
                    Object obj2 = rVar4.b;
                    int i4 = rVar4.a;
                    if (i4 > i3) {
                        i2 = -1;
                    } else if (i4 < i3) {
                        i2 = 1;
                    } else if (clsC != null || (clsC = ConcurrentHashMap.c(obj)) != null) {
                        int i5 = ConcurrentHashMap.g;
                        int iCompareTo = (obj2 == null || obj2.getClass() != clsC) ? 0 : ((Comparable) obj).compareTo(obj2);
                        i2 = iCompareTo == 0 ? i(obj, obj2) : iCompareTo;
                    }
                    r rVar5 = i2 <= 0 ? rVar4.f : rVar4.g;
                    if (rVar5 == null) {
                        break;
                    } else {
                        rVar4 = rVar5;
                    }
                }
                rVar.e = rVar4;
                if (i2 <= 0) {
                    rVar4.f = rVar;
                } else {
                    rVar4.g = rVar;
                }
                rVar = c(rVar2, rVar);
            }
            rVar2 = rVar;
            rVar = rVar3;
        }
        this.e = rVar2;
    }

    private final void d() {
        if (h.c(this, i, 0, 1)) {
            return;
        }
        boolean z = false;
        while (true) {
            int i2 = this.lockState;
            if ((i2 & (-3)) == 0) {
                if (h.c(this, i, i2, 1)) {
                    break;
                }
            } else if ((i2 & 2) == 0) {
                if (h.c(this, i, i2, i2 | 2)) {
                    this.g = Thread.currentThread();
                    z = true;
                }
            } else if (z) {
                LockSupport.park(this);
            }
        }
        if (z) {
            this.g = null;
        }
    }

    @Override // j$.util.concurrent.l
    final l a(Object obj, int i2) {
        Object obj2;
        Thread thread;
        Thread thread2;
        r rVarB = null;
        if (obj != null) {
            l lVar = this.f;
            while (lVar != null) {
                int i3 = this.lockState;
                if ((i3 & 3) != 0) {
                    if (lVar.a == i2 && ((obj2 = lVar.b) == obj || (obj2 != null && obj.equals(obj2)))) {
                        return lVar;
                    }
                    lVar = lVar.d;
                } else {
                    j$.sun.misc.a aVar = h;
                    long j = i;
                    if (aVar.c(this, j, i3, i3 + 4)) {
                        try {
                            r rVar = this.e;
                            if (rVar != null) {
                                rVarB = rVar.b(i2, obj, null);
                            }
                            if (aVar.f(this, j) == 6 && (thread2 = this.g) != null) {
                                LockSupport.unpark(thread2);
                            }
                            return rVarB;
                        } catch (Throwable th) {
                            if (h.f(this, i) == 6 && (thread = this.g) != null) {
                                LockSupport.unpark(thread);
                            }
                            throw th;
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0070, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b3, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bb, code lost:
    
        return r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a A[PHI: r2
      0x005a: PHI (r2v5 java.lang.Class<?>) = (r2v4 java.lang.Class<?>), (r2v6 java.lang.Class<?>) binds: [B:26:0x0058, B:18:0x0042] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final r e(int i2, Object obj, Object obj2) {
        int i3;
        r rVar;
        r rVar2 = this.e;
        Class<?> clsC = null;
        boolean z = false;
        while (true) {
            if (rVar2 == null) {
                r rVar3 = new r(i2, obj, obj2, null, null);
                this.e = rVar3;
                this.f = rVar3;
                break;
            }
            int i4 = rVar2.a;
            if (i4 > i2) {
                i3 = -1;
            } else if (i4 < i2) {
                i3 = 1;
            } else {
                Object obj3 = rVar2.b;
                if (obj3 == obj || (obj3 != null && obj.equals(obj3))) {
                    break;
                }
                if (clsC != null || (clsC = ConcurrentHashMap.c(obj)) != null) {
                    int i5 = ConcurrentHashMap.g;
                    int iCompareTo = (obj3 == null || obj3.getClass() != clsC) ? 0 : ((Comparable) obj).compareTo(obj3);
                    if (iCompareTo == 0) {
                        if (!z) {
                            r rVar4 = rVar2.f;
                            if ((rVar4 != null && (r3 = rVar4.b(i2, obj, clsC)) != null) || ((rVar = rVar2.g) != null && (r3 = rVar.b(i2, obj, clsC)) != null)) {
                                break;
                            }
                            z = true;
                        }
                        i3 = i(obj, obj3);
                    } else {
                        i3 = iCompareTo;
                    }
                }
            }
            r rVar5 = i3 <= 0 ? rVar2.f : rVar2.g;
            if (rVar5 == null) {
                r rVar6 = this.f;
                r rVar7 = new r(i2, obj, obj2, rVar6, rVar2);
                this.f = rVar7;
                if (rVar6 != null) {
                    rVar6.h = rVar7;
                }
                if (i3 <= 0) {
                    rVar2.f = rVar7;
                } else {
                    rVar2.g = rVar7;
                }
                if (!rVar2.i) {
                    rVar7.i = true;
                } else {
                    d();
                    try {
                        this.e = c(this.e, rVar7);
                    } finally {
                        this.lockState = 0;
                    }
                }
            } else {
                rVar2 = rVar5;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x008e A[PHI: r0
      0x008e: PHI (r0v4 j$.util.concurrent.r) = (r0v3 j$.util.concurrent.r), (r0v12 j$.util.concurrent.r) binds: [B:55:0x008a, B:51:0x0083] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final boolean f(r rVar) {
        r rVar2;
        r rVar3;
        r rVar4 = (r) rVar.d;
        r rVar5 = rVar.h;
        if (rVar5 == null) {
            this.f = rVar4;
        } else {
            rVar5.d = rVar4;
        }
        if (rVar4 != null) {
            rVar4.h = rVar5;
        }
        if (this.f == null) {
            this.e = null;
            return true;
        }
        r rVarB = this.e;
        if (rVarB == null || rVarB.g == null || (rVar2 = rVarB.f) == null || rVar2.f == null) {
            return true;
        }
        d();
        try {
            r rVar6 = rVar.f;
            r rVar7 = rVar.g;
            if (rVar6 != null && rVar7 != null) {
                r rVar8 = rVar7;
                while (true) {
                    r rVar9 = rVar8.f;
                    if (rVar9 == null) {
                        break;
                    }
                    rVar8 = rVar9;
                }
                boolean z = rVar8.i;
                rVar8.i = rVar.i;
                rVar.i = z;
                r rVar10 = rVar8.g;
                r rVar11 = rVar.e;
                if (rVar8 == rVar7) {
                    rVar.e = rVar8;
                    rVar8.g = rVar;
                } else {
                    r rVar12 = rVar8.e;
                    rVar.e = rVar12;
                    if (rVar12 != null) {
                        if (rVar8 == rVar12.f) {
                            rVar12.f = rVar;
                        } else {
                            rVar12.g = rVar;
                        }
                    }
                    rVar8.g = rVar7;
                    rVar7.e = rVar8;
                }
                rVar.f = null;
                rVar.g = rVar10;
                if (rVar10 != null) {
                    rVar10.e = rVar;
                }
                rVar8.f = rVar6;
                rVar6.e = rVar8;
                rVar8.e = rVar11;
                if (rVar11 == null) {
                    rVarB = rVar8;
                } else if (rVar == rVar11.f) {
                    rVar11.f = rVar8;
                } else {
                    rVar11.g = rVar8;
                }
                if (rVar10 != null) {
                    rVar6 = rVar10;
                }
            } else if (rVar6 == null) {
                rVar6 = rVar7 != null ? rVar7 : rVar;
            }
            if (rVar6 != rVar) {
                r rVar13 = rVar.e;
                rVar6.e = rVar13;
                if (rVar13 == null) {
                    rVarB = rVar6;
                } else if (rVar == rVar13.f) {
                    rVar13.f = rVar6;
                } else {
                    rVar13.g = rVar6;
                }
                rVar.e = null;
                rVar.g = null;
                rVar.f = null;
            }
            if (!rVar.i) {
                rVarB = b(rVarB, rVar6);
            }
            this.e = rVarB;
            if (rVar == rVar6 && (rVar3 = rVar.e) != null) {
                if (rVar == rVar3.f) {
                    rVar3.f = null;
                } else if (rVar == rVar3.g) {
                    rVar3.g = null;
                }
                rVar.e = null;
            }
            this.lockState = 0;
            return false;
        } catch (Throwable th) {
            this.lockState = 0;
            throw th;
        }
    }

    static r g(r rVar, r rVar2) {
        r rVar3;
        if (rVar2 != null && (rVar3 = rVar2.g) != null) {
            r rVar4 = rVar3.f;
            rVar2.g = rVar4;
            if (rVar4 != null) {
                rVar4.e = rVar2;
            }
            r rVar5 = rVar2.e;
            rVar3.e = rVar5;
            if (rVar5 == null) {
                rVar3.i = false;
                rVar = rVar3;
            } else if (rVar5.f == rVar2) {
                rVar5.f = rVar3;
            } else {
                rVar5.g = rVar3;
            }
            rVar3.f = rVar2;
            rVar2.e = rVar3;
        }
        return rVar;
    }

    static r h(r rVar, r rVar2) {
        r rVar3;
        if (rVar2 != null && (rVar3 = rVar2.f) != null) {
            r rVar4 = rVar3.g;
            rVar2.f = rVar4;
            if (rVar4 != null) {
                rVar4.e = rVar2;
            }
            r rVar5 = rVar2.e;
            rVar3.e = rVar5;
            if (rVar5 == null) {
                rVar3.i = false;
                rVar = rVar3;
            } else if (rVar5.g == rVar2) {
                rVar5.g = rVar3;
            } else {
                rVar5.f = rVar3;
            }
            rVar3.g = rVar2;
            rVar2.e = rVar3;
        }
        return rVar;
    }

    static r c(r rVar, r rVar2) {
        r rVar3;
        rVar2.i = true;
        while (true) {
            r rVar4 = rVar2.e;
            if (rVar4 == null) {
                rVar2.i = false;
                return rVar2;
            }
            if (!rVar4.i || (rVar3 = rVar4.e) == null) {
                break;
            }
            r rVar5 = rVar3.f;
            if (rVar4 == rVar5) {
                r rVar6 = rVar3.g;
                if (rVar6 != null && rVar6.i) {
                    rVar6.i = false;
                    rVar4.i = false;
                    rVar3.i = true;
                    rVar2 = rVar3;
                } else {
                    if (rVar2 == rVar4.g) {
                        rVar = g(rVar, rVar4);
                        r rVar7 = rVar4.e;
                        rVar3 = rVar7 == null ? null : rVar7.e;
                        rVar4 = rVar7;
                        rVar2 = rVar4;
                    }
                    if (rVar4 != null) {
                        rVar4.i = false;
                        if (rVar3 != null) {
                            rVar3.i = true;
                            rVar = h(rVar, rVar3);
                        }
                    }
                }
            } else if (rVar5 != null && rVar5.i) {
                rVar5.i = false;
                rVar4.i = false;
                rVar3.i = true;
                rVar2 = rVar3;
            } else {
                if (rVar2 == rVar4.f) {
                    rVar = h(rVar, rVar4);
                    r rVar8 = rVar4.e;
                    rVar3 = rVar8 == null ? null : rVar8.e;
                    rVar4 = rVar8;
                    rVar2 = rVar4;
                }
                if (rVar4 != null) {
                    rVar4.i = false;
                    if (rVar3 != null) {
                        rVar3.i = true;
                        rVar = g(rVar, rVar3);
                    }
                }
            }
        }
        return rVar;
    }

    static r b(r rVar, r rVar2) {
        while (rVar2 != null && rVar2 != rVar) {
            r rVar3 = rVar2.e;
            if (rVar3 == null) {
                rVar2.i = false;
                return rVar2;
            }
            if (rVar2.i) {
                rVar2.i = false;
                return rVar;
            }
            r rVar4 = rVar3.f;
            if (rVar4 == rVar2) {
                r rVar5 = rVar3.g;
                if (rVar5 != null && rVar5.i) {
                    rVar5.i = false;
                    rVar3.i = true;
                    rVar = g(rVar, rVar3);
                    rVar3 = rVar2.e;
                    rVar5 = rVar3 == null ? null : rVar3.g;
                }
                if (rVar5 != null) {
                    r rVar6 = rVar5.f;
                    r rVar7 = rVar5.g;
                    if ((rVar7 == null || !rVar7.i) && (rVar6 == null || !rVar6.i)) {
                        rVar5.i = true;
                    } else {
                        if (rVar7 == null || !rVar7.i) {
                            if (rVar6 != null) {
                                rVar6.i = false;
                            }
                            rVar5.i = true;
                            rVar = h(rVar, rVar5);
                            rVar3 = rVar2.e;
                            rVar5 = rVar3 != null ? rVar3.g : null;
                        }
                        if (rVar5 != null) {
                            rVar5.i = rVar3 == null ? false : rVar3.i;
                            r rVar8 = rVar5.g;
                            if (rVar8 != null) {
                                rVar8.i = false;
                            }
                        }
                        if (rVar3 != null) {
                            rVar3.i = false;
                            rVar = g(rVar, rVar3);
                        }
                        rVar2 = rVar;
                    }
                }
                rVar2 = rVar3;
            } else {
                if (rVar4 != null && rVar4.i) {
                    rVar4.i = false;
                    rVar3.i = true;
                    rVar = h(rVar, rVar3);
                    rVar3 = rVar2.e;
                    rVar4 = rVar3 == null ? null : rVar3.f;
                }
                if (rVar4 != null) {
                    r rVar9 = rVar4.f;
                    r rVar10 = rVar4.g;
                    if ((rVar9 == null || !rVar9.i) && (rVar10 == null || !rVar10.i)) {
                        rVar4.i = true;
                    } else {
                        if (rVar9 == null || !rVar9.i) {
                            if (rVar10 != null) {
                                rVar10.i = false;
                            }
                            rVar4.i = true;
                            rVar = g(rVar, rVar4);
                            rVar3 = rVar2.e;
                            rVar4 = rVar3 != null ? rVar3.f : null;
                        }
                        if (rVar4 != null) {
                            rVar4.i = rVar3 == null ? false : rVar3.i;
                            r rVar11 = rVar4.f;
                            if (rVar11 != null) {
                                rVar11.i = false;
                            }
                        }
                        if (rVar3 != null) {
                            rVar3.i = false;
                            rVar = h(rVar, rVar3);
                        }
                        rVar2 = rVar;
                    }
                }
                rVar2 = rVar3;
            }
        }
        return rVar;
    }

    static {
        j$.sun.misc.a aVarH = j$.sun.misc.a.h();
        h = aVarH;
        i = aVarH.j(q.class, "lockState");
    }
}
