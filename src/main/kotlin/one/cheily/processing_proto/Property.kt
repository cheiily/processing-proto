package one.cheily.processing_proto

import java.io.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

object Properties {
    private val map = mutableMapOf<String, MutableMap<String, Serializable>>()

    operator fun get(key: String): MutableMap<String, Serializable> {
        return map.getOrPut(key) { mutableMapOf() }
    }

    operator fun get(clazz: KClass<*>): MutableMap<String, Serializable> {
        return get(clazz.java.canonicalName!!)
    }
}

class Property<T : Serializable>(val key: String, value: T, val func : () -> Unit) {
    init {
        Properties[func::class.java.nestHost.canonicalName!!][key] = value
        Sketch.Config.properties[key] = value
    }
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        @Suppress("UNCHECKED_CAST")
        return Properties[func::class.java.nestHost.canonicalName!!][key] as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        Properties[func::class.java.nestHost.canonicalName!!][key] = value
    }
}