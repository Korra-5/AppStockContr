package AppTienda.Repository
import AppTienda.Model.Usuario
import AppTienda.util.HibernateUtils
import jakarta.persistence.*

class RepositoryUsuario {
    private fun getEntityManager() : EntityManager {
        return HibernateUtils.getEntityManager("unidadMySql")
    }
fun comprobarUsuario(usuario: String, password: String): Boolean {
    val em:EntityManager = getEntityManager()
val user= em.find(Usuario::class.java,usuario)
    if (user != null) {
        if (user.password == password) {
            println("Bienvenido")
            return true
        } else {
            println("Contraseña incorrecta")
        }
    } else {
        println("Usuario no encontrado")
    }
    return false
}
}