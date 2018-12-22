package com.test.app.dao.ventas;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

import com.test.app.model.Login;
import com.test.app.services.LoginService;

@Service
public class LoginDAO implements LoginService {

	//@PersistenceContext(unitName = "compras")
    @PersistenceContext(unitName = "source")
    private EntityManager manager;

    @Override
    public Login acceso(String usuario, String password) {
        Login login = new Login();
        try {
            StoredProcedureQuery query = manager.createStoredProcedureQuery("HR.LOGIN");
            query.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("P_CONTRASENIA", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_RESULTSET", void.class, ParameterMode.REF_CURSOR);
            query.setParameter("P_USUARIO", usuario);
            query.setParameter("P_CONTRASENIA", password);
            query.execute();
            manager.close();
        } catch (PersistenceException pe) {
            System.out.println("Error en LoginServiceDao {acceso}: " + pe.getMessage());
        }
        return login;
    }
}