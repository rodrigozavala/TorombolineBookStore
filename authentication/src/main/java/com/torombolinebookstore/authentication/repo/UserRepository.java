package com.torombolinebookstore.authentication.repo;

import com.torombolinebookstore.authentication.dao.UserDAO;
import com.torombolinebookstore.common_models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;


@Component
public class UserRepository implements CustomCrudInterface<User, Long>{
    @Autowired
    private UserDAO userDAO;

    public Optional<User> findByEmail(String email){
        try{
            return Optional.of(userDAO.findUserByEmail(email));
        }catch (SQLException e){
            // TODO: implement logs correctly
            String message = String.format("An error has occurred while trying to find and user with email={} \n error message = {} \n stacktrace = \n{}",
                    email,
                    e.getMessage(),
                    e.getStackTrace());
            System.out.println(message);
        }
        return Optional.empty();
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteById(Long l) {

    }

    @Override
    public boolean existsById(Long l) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Optional<User> findById(Long l) {
        return Optional.empty();
    }

    @Override
    public <S extends User> S save(S entity) {
        try{
            userDAO.saveUser((User)entity);
        }catch (SQLException e){
            // TODO: implement logs correctly
            String message = String.format("An error has occurred while trying to save and user {}"+
                            ""+" \n error message = {} \n stacktrace = \n{}",
                    ((User)entity).toString(),
                    e.getMessage(),
                    e.getStackTrace());
            System.out.println(message);
        }finally {
            return entity;
        }

    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
}
