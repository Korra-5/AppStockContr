package AppTienda.Repository

import AppTienda.Model.Producto
import AppTienda.Model.Proveedor
import AppTienda.util.HibernateUtils
import jakarta.persistence.*

class RepositoryProducto {

    private fun getEntityManager() : EntityManager {
        return HibernateUtils.getEntityManager("unidadMySql")
    }

    fun insertarProducto(producto: Producto, proveedor: Proveedor) {
        val em: EntityManager = getEntityManager()
        try {
            em.transaction.begin()

            val proveedorExistente = em.createQuery("SELECT p FROM Proveedor p WHERE p.nombre = :nombre", Proveedor::class.java)
                .setParameter("nombre", proveedor.nombre)
                .resultList
                .firstOrNull()

            val proveedorFinal: Proveedor = if (proveedorExistente != null) {
                proveedorExistente
            } else {
                em.persist(proveedor)
                proveedor
            }

            producto.proveedores = proveedorFinal

            em.persist(producto)

            em.transaction.commit()
            println("Producto añadido correctamente")
        } catch (e: Exception) {
            em.transaction.rollback()
            e.printStackTrace()
            println("Error al añadir el producto: ${e.message}")
        } finally {
            em.close()
        }
    }
    fun eliminarProducto(id: String){
        val em: EntityManager = getEntityManager()
        try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)

            if (producto != null) {
            em.remove(producto)
                em.transaction.commit()
                println("Producto eliminado correctamente")
            }else{
                println("No existe el producto")
            }
        }catch (e: Exception){
            em.transaction.rollback()
            e.printStackTrace()
            println("Error al eliminar el producto: ${e.message}")
        } finally {
            em.close()
        }
    }

    fun modificarNombre(id:String, nombreNuevo: String){
        val em: EntityManager = getEntityManager()
        try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)
            val proveedor = em.find(Proveedor::class.java, producto.proveedores.id)

            if (producto != null) {
                producto.nombre= nombreNuevo
                em.merge(producto)
                println("Producto actualizado correctamente")
                val nuevoId = producto.categoria.take(3) + nombreNuevo.take(3) + producto.proveedores.nombre.take(3)
                em.transaction.commit()

                val NuevoProducto=Producto(producto.categoria, producto.nombre, producto.descripcion, producto.precio_sin_iva, producto.precio_con_iva, producto.fecha_alta, producto.stock, producto.proveedores, nuevoId)
                eliminarProducto(producto.id)
                insertarProducto(NuevoProducto, proveedor =Proveedor(proveedor.nombre, proveedor.direccion, proveedor.productos, proveedor.id))

                println("ID del producto actualizado correctamente")

            }
    }catch (e: Exception){
            em.transaction.rollback()
            e.printStackTrace()
            println("Error al eliminar el producto: ${e.message}")
    }  finally {
        em.close()
    }
    }
    fun modificarStock(id:String,stock:Int){
        val em: EntityManager = getEntityManager()
        val producto=em.find(Producto::class.java, id)
        if(producto==null){
            println("No existe este producto")
        }else {
            em.transaction.begin()
            producto.stock = stock
            em.merge(producto)
            em.transaction.commit()
            println("Stock modificado correctamente")
        }
    }

    fun mostrarProducto(id:String){
        val em: EntityManager = getEntityManager()
        val producto=em.find(Producto::class.java, id)
 if (producto != null) {

        println("Nombre del producto: ${producto.nombre}")
        println("ID del producto: ${producto.id}")
        println("Categoria del producto: ${producto.categoria}")
        println("Descripcion del producto: ${producto.descripcion}")
        println("Stock del producto: ${producto.stock}")
        println("Precio sin IVA del producto: ${producto.precio_sin_iva}")
        println("Precio con IVA del producto: ${producto.precio_con_iva}")
        println("Fecha de alta del producto: ${producto.fecha_alta}")
        println("Proveedor del producto: ${producto.proveedores.nombre}, con ID: ${producto.proveedores.id}")
    }else{
        println("No existe este producto")
    }}

    fun mostrarProductosStock() {
        val em: EntityManager = getEntityManager()
        try {
            val nombre = em.createQuery("SELECT p.nombre FROM Producto p WHERE p.stock > 0", String::class.java)
                .resultList
            println("Productos con stock: $nombre")

        } catch (e: Exception) {
            e.printStackTrace()
            println("Error al obtener productos con stock: ${e.message}")
        } finally {
            em.close()
        }
    }

        fun mostrarProductosSinStock(){
            val em: EntityManager = getEntityManager()
            try {
                val nombre=em.createQuery("SELECT p.nombre FROM Producto p WHERE p.stock = 0", String::class.java)
                    .resultList
                println("Productos sin stock: $nombre")

            } catch (e: Exception) {
                e.printStackTrace()
                println("Error al obtener productos sin stock: ${e.message}")
            } finally {
                em.close()
            }
    }


    }
