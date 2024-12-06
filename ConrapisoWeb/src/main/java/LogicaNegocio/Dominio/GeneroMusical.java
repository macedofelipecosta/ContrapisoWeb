package LogicaNegocio.Dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GeneroMusical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int generoMusical_id;
    private String nombre;

    public GeneroMusical() {
    }
    public GeneroMusical(String nombre) {
        this.nombre = nombre;
    }



    //region Getters
    public int getGeneroMusical_id() {
        return generoMusical_id;
    }

    public String getNombre() {
        return nombre;
    }

    //endregion



}
