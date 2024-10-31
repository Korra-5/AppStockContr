package AppTienda.Service

import AppTienda.Repository.RepositoryProducto
import AppTienda.Model.Producto
import AppTienda.Model.Proveedor
import java.util.*

class Service (private val repository: RepositoryProducto) {

    fun comprobarInsercionProducto(categoria: String, nombreProd: String, stock: Int, descripcion: String, precio: Float,proveedor: Proveedor) {

            val precioIva: Float = precio * 1.21f
            val fecha: Date = Date()


            val producto = Producto(
                categoria = categoria,
                nombre = nombreProd,
                descripcion = descripcion,
                precio_sin_iva = precio,
                precio_con_iva = precioIva,
                fecha_alta = fecha,
                stock = stock,
                proveedores = proveedor,
                id = categoria.take(3) + nombreProd.take(3) + proveedor.nombre.take(3)
            )
            if (comprobarProducto(producto) && comprobarProveedor(proveedor))
            {
                repository.insertarProducto(producto, proveedor)
            }
    }



    fun comprobarProveedor(proveedor: Proveedor):Boolean {
        if (proveedor.nombre.length>50||proveedor.direccion==""||proveedor.direccion==""){
            println()
            println("Algo ha fallado, aqui algunas especificaciones:")
            println()
            println("El nombre del proveedor no puede exceder los 50 caracteres")
            println("La direcion no puede ser nula")
            println("El nombre del proveedor no puede ser nulo")
            println()

            return false

        }else{return true}

    }

    fun comprobarProducto(producto: Producto):Boolean {
        if(producto.nombre.length>50||producto.nombre==""||producto.categoria.length>50||producto.categoria==""||producto.precio_sin_iva==null){
            println()
            println("Algo ha fallado, aqui algunas especificaciones:")
            println()
            println("El nombre del producto no puede exceder los 50 caracteres")
            println("El nombre del prodcuto no puede ser nulo")
            println("La categoria no puede exceder los 50 caracteres")
            println("La categoria no puede ser nula")
            println("El precio no puede ser nulo")
            println()

            return false

        }else{
            return true
        }
    }
fun comprobarNombre(nombre:String):Boolean{
    if(nombre.length>50||nombre==""){
        println("Intentalo de nuevo")
        println("El nombre no puede ser nulo ni superior a 50 caracteres")
        return false
    }else{return true}
}
}