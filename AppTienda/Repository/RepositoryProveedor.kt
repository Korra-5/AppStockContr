package AppTienda.Repository

import AppTienda.Model.Producto
import AppTienda.util.HibernateUtils
import jakarta.persistence.EntityManager

class RepositoryProveedor {

    private fun getEntityManager() : EntityManager {
        return HibernateUtils.getEntityManager("unidadMySql")
    }

    fun mostrarProveedorProducto(id:String){
        val em: EntityManager = getEntityManager()

        val producto = em.find(Producto::class.java, id)

        if (producto == null) {
            println("Este producto no existe")
        }else {

            println("Proveedor del producto ${producto.nombre}:")
            println(producto.proveedores.nombre)
            println("Con direccion: ${producto.proveedores.direccion}")
            println("Y ID: ${producto.proveedores.id}")
        }
    }

    fun mostrarProveedores(){
        val em: EntityManager = getEntityManager()
        try {
            val nombre=em.createQuery("SELECT p.nombre FROM Proveedor p", String::class.java)
                .resultList
            println("Lista de proveedores: $nombre")

        } catch (e: Exception) {
            e.printStackTrace()
            println("Error al obtener productos sin stock: ${e.message}")
        } finally {
            em.close()
        }
    }
}