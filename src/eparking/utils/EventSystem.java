package eparking.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventSystem {

    // Lista de listeners para cuando se debe refrescar el dashboard
    private static List<Consumer<Void>> dashboardRefreshListeners = new ArrayList<>();

    // Método para suscribirse al evento
    public static void onDashboardRefresh(Consumer<Void> listener) {
        dashboardRefreshListeners.add(listener);
    }

    // Método para disparar el evento (notificar a todos los suscritos)
    public static void triggerDashboardRefresh() {
        dashboardRefreshListeners.forEach(listener -> listener.accept(null));
    }
}
