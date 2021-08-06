package com.inretailapp.ui.components

/**
 * El proposito de esta interface es para enviarse como parámetro a otras pantallas
 * y así mismo, agregar semántica y legibilidad al desarrollo de las funciones del código fuente
 * Finalmente, el uso correcto que se debe de dar a esta interface entre dos objetos (A y B) es el siguiente
 * Objeto A: Implementa esta interfaz y la guarda en una variable (ej: var listener = obj: AppListener<Model>{...}
 * Y se envía como parámetro de clase al "Objeto B"
 * Objeto B: Recibe esta variable y ejecuta sus funciones implementadas en el objeto A (ej: listener.onChangeTab(0))
 * **/
interface AppListener<M: Any> {
    fun onPressDeleteButton(model: M, action: (() -> Unit)? = null) {}
    fun onPressEditButton(model: M) {}
    fun onSelectItemView(model: M) {}
    fun onChangeTab(tabNumber: Int) {}
}

