package AppTienda.Model

import jakarta.persistence.*

@Entity
@Table
class Proveedor (
    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,

    @OneToMany(mappedBy = "proveedores")
    val productos: List<Producto> = listOf(),

    @Id
    val id: Long?
)