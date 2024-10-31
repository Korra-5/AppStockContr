package AppTienda.Model

import jakarta.persistence.*

@Entity
@Table
class Usuario (

    @Column (nullable=false)
    val password:String,

    @Id
    val nombreUsuario: String,
) {
}