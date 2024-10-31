package AppTienda

import AppTienda.Model.Proveedor
import AppTienda.Repository.RepositoryProducto
import AppTienda.Repository.RepositoryProveedor
import AppTienda.Repository.RepositoryUsuario
import AppTienda.Service.Service


fun main(args: Array<String>) {

    val repositoryPr = RepositoryProducto()
    val service = Service(repositoryPr)
    val repositoryUs=RepositoryUsuario()
    val repositoryPv=RepositoryProveedor()

    while (true) {
        println("Inicie sesión")
        println("Usuario:")
        val user = readLine().toString()
        println("Contraseña")
        val pass = readLine().toString()
        if (repositoryUs.comprobarUsuario(user, pass)==true){
            break
        }

    }
while (true){
    println("¿Que desea hacer?")
    println("1- Dar de alta producto")
    println("2- Dar de baja producto")
    println("3- Modificar nombre de un producto")
    println("4- Modificar stock de un producto")
    println("5- Mostrar producto")
    println("6- Mostrar productos con stock")
    println("7- Mostrar porductos sin stock")
    println("8- Mostrar proveedor de un producto")
    println("9- Mostrar proveedores")

    val opcion= readLine()!!.toInt()

    if (opcion==1){
        println("Inserte nombre del producto")
        val nombre=readLine().toString()
        println("Inserte categoria del producto")
        val categoria=readLine().toString()
        println("Inserte stock del producto")
        val stock=readLine()!!.toInt()
        println("Inserte precio sin iva del producto")
        val precioSinIva= readLine()!!.toFloat()
        println("Inserte descripcion del producto")
        val descripcion=readLine().toString()
        println("Inserte nombre del proveedor")
        val nombreP=readLine().toString()
        println("Inserte direccion del proveedor")
        val direccion=readLine().toString()
        println("Inserte ID del proveedor")
        val id=readLine()!!.toLong()

        val proveedor:Proveedor
        service.comprobarInsercionProducto(categoria, nombre,stock,descripcion,precioSinIva,proveedor= Proveedor(nombreP, direccion, listOf(), id))
    }else if (opcion==2){
        println("Inserta ID del producto que quieres eliminar:")
        val id=readLine().toString()
        repositoryPr.eliminarProducto(id)
    }else if (opcion==3){
        println("Inserta ID del producto que quieres modificar:")
        val id=readLine().toString()
        println("Inserta nuevo nombre:")
        val nuevoNombre=readLine().toString()
if (service.comprobarNombre(nuevoNombre)){
    repositoryPr.modificarNombre(id, nuevoNombre)
}
    }else if (opcion==4){
        println("Inserta ID del producto que quieres modificar:")
        val id=readLine().toString()
        println("Inserta nuevo stock")
        val stock=readLine()!!.toInt()

        repositoryPr.modificarStock(id, stock)
    }else if (opcion==5){
        println("Inserta ID del producto que quieres mostar:")
        val id=readLine().toString()
        repositoryPr.mostrarProducto(id)
    }else if (opcion==6){
        repositoryPr.mostrarProductosStock()
    }else if (opcion==7){
        repositoryPr.mostrarProductosSinStock()
    }else if (opcion==8){
        println("Inserta ID del producto cuyo proveedor quieres ver:")
        val id=readLine().toString()
        repositoryPv.mostrarProveedorProducto(id)
    }else if (opcion==9){
        repositoryPv.mostrarProveedores()
    }
}
}