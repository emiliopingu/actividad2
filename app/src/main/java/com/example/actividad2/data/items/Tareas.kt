package com.example.actividad2.data.items

class Tareas {
    var id: Int = 0
    private var nombreTarea: String = ""
    var lugarTarea: String = ""
    var usuarioTarea: String = ""
    var descripcion: String = ""
    var fecducidad: String = ""

    constructor(
        nombreTarea: String,
        lugarTarea: String,
        usuarioTarea: String,
        descripcion: String,
        fecducidad: String
    ) {
        this.nombreTarea = nombreTarea
        this.lugarTarea = lugarTarea
        this.usuarioTarea = usuarioTarea
        this.descripcion = descripcion
        this.fecducidad = fecducidad
    }

    constructor() {

    }
}


