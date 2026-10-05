package org.ucb.encuentra.di

import org.koin.core.module.Module

/** Cada plataforma sabe dónde guardar el archivo de la base de datos (Context en Android, Documents en iOS). */
expect val platformModule: Module
