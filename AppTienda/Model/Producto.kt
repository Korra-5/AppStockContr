package AppTienda.Model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table
class Producto (
    @Column(nullable = false)
    val categoria: String,

    @Column(nullable = false)
    var nombre: String,

    @Column
    val descripcion: String,

    @Column(nullable = false)
    val precio_sin_iva: Float,

    @Column(nullable = false)
    val precio_con_iva: Float,

    @Column(nullable = false)
    val fecha_alta: Date,

    @Column
    var stock: Int,

    @ManyToOne()
    @JoinColumn(name = "id_proveedor")
    var proveedores: Proveedor,

    @Id
    var id: String
)
{
}