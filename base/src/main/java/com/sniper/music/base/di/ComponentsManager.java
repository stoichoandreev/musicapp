package com.sniper.music.base.di;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ComponentsManager {

    private static final String KEY_APP = "app";
    private final Map<String, ? super BaseComponent> baseComponents;

    private ComponentsManager() {
        baseComponents = new HashMap<>();
    }

    public static ComponentsManager get() {
        return LazyHolder.INSTANCE;
    }

    public <C extends BaseComponent> void removeBaseComponent(@Nullable String componentKey) {
        if (componentKey != null) {
            baseComponents.remove(componentKey);
        }
    }

    @SuppressWarnings("unchecked")
    public <C extends BaseComponent> C getBaseComponent(@NonNull String componentKey) {
        return (C) baseComponents.get(componentKey);
    }

    public <C extends BaseComponent> void putBaseComponent(@Nullable String componentKey, @Nullable C component) {
        if (componentKey != null && component != null) {
            baseComponents.put(componentKey, component);
        }
    }

    public void removeBaseComponents() {
        baseComponents.clear();
    }

    public ApplicationComponent getAppComponent() {
        return getBaseComponent(KEY_APP);
    }

    public void setAppComponent(@NonNull BaseComponent component) {
        baseComponents.put(KEY_APP, component);
    }

    public synchronized void clear() {
        baseComponents.clear();
    }

    private static class LazyHolder {

        private static final ComponentsManager INSTANCE = new ComponentsManager();
    }
}
