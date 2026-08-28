package rikka.shizuku;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import moe.shizuku.server.IShizukuServiceConnection;
import rikka.shizuku.Shizuku;

/* loaded from: /workspace/unpacked/classes.dex */
class ShizukuServiceConnection extends IShizukuServiceConnection.Stub {
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    private IBinder binder;
    private final ComponentName componentName;
    private final Set<ServiceConnection> connections = new HashSet();
    private boolean dead = false;

    public ShizukuServiceConnection(Shizuku.UserServiceArgs userServiceArgs) {
        this.componentName = userServiceArgs.componentName;
    }

    public void addConnection(ServiceConnection serviceConnection) {
        if (serviceConnection != null) {
            this.connections.add(serviceConnection);
        }
    }

    @Override // moe.shizuku.server.IShizukuServiceConnection
    public void connected(final IBinder iBinder) throws RemoteException {
        MAIN_HANDLER.post(new Runnable() { // from class: rikka.shizuku.ShizukuServiceConnection$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m458lambda$connected$0$rikkashizukuShizukuServiceConnection(iBinder);
            }
        });
        this.binder = iBinder;
        try {
            this.binder.linkToDeath(new IBinder.DeathRecipient() { // from class: rikka.shizuku.ShizukuServiceConnection$$ExternalSyntheticLambda1
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    this.f$0.died();
                }
            }, 0);
        } catch (RemoteException e) {
        }
    }

    @Override // moe.shizuku.server.IShizukuServiceConnection
    public void died() {
        this.binder = null;
        if (this.dead) {
            return;
        }
        this.dead = true;
        MAIN_HANDLER.post(new Runnable() { // from class: rikka.shizuku.ShizukuServiceConnection$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m459lambda$died$1$rikkashizukuShizukuServiceConnection();
            }
        });
    }

    /* renamed from: lambda$connected$0$rikka-shizuku-ShizukuServiceConnection, reason: not valid java name */
    /* synthetic */ void m458lambda$connected$0$rikkashizukuShizukuServiceConnection(IBinder iBinder) {
        Iterator<ServiceConnection> it = this.connections.iterator();
        while (it.hasNext()) {
            it.next().onServiceConnected(this.componentName, iBinder);
        }
    }

    /* renamed from: lambda$died$1$rikka-shizuku-ShizukuServiceConnection, reason: not valid java name */
    /* synthetic */ void m459lambda$died$1$rikkashizukuShizukuServiceConnection() {
        Iterator<ServiceConnection> it = this.connections.iterator();
        while (it.hasNext()) {
            it.next().onServiceDisconnected(this.componentName);
        }
        this.connections.clear();
        ShizukuServiceConnections.remove(this);
    }

    public void removeConnection(ServiceConnection serviceConnection) {
        if (serviceConnection != null) {
            this.connections.remove(serviceConnection);
        }
    }
}
