# Reglas de Proguard/R8. Vacío por ahora (isMinifyEnabled = false).

# Room 3: que R8 encuentre la implementación generada de la base de datos
-keep class * extends androidx.room3.RoomDatabase { <init>(); }
