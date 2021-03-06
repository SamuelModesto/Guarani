package com.samuel.authuser.specifications;

import com.samuel.authuser.models.User;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    /**
     * Essas specifications servem para receber os argumentos de filtro,
     * sento path o atributo que se quer filtrar, o Equal serve para filtrar
     * pelo valor exato de um determinado atribuito, o like serve para filtrar
     * uma parte, ou seja, se contém uma parte naquele atributo, então
     * este será filtrado.
     *
     * And serve para combinar esses 3 filtros, sendo que existem outros grupos
     * de specifications que podemos usar.
     *
     * O specification deve ser passado no parametro do endpoint.
     */

    @And({
            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "email", spec = Like.class)
    })
    public interface UserSpec extends Specification<User> {

    }
}
