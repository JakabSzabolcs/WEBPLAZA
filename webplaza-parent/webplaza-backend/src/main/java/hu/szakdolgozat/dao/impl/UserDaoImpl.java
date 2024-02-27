package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.UserDao;
import hu.szakdolgozat.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class UserDaoImpl extends JpaCommonEntityDaoImpl<User> implements UserDao {
    @Override
    protected Class<User> getManagedClass() {
        return User.class;
    }

    @Override
    public void add(User entity) {
        entity.setPassword(hashPassword(entity.getPassword()));
        entityManager.persist(entity);
    }

    @Override
    public void update(User entity) {
        if (!entity.getPassword().startsWith("$2a$10$")) {
            /*
             * //$2a$10$ a titkosítási szabály
             * magyarán ha már titkosítva van ne legyen megint
             */
            entity.setPassword(hashPassword(entity.getPassword()));
        }
        entityManager.merge(entity);
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.username = ?1", getManagedClass())
                    .setParameter(1, username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User authenticate(String username, String password) {
        try {
            User user = entityManager.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.username = ?1", getManagedClass())
                    .setParameter(1, username).getSingleResult();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }

        } catch (NoResultException e) {
            return null;
        }
        return null;
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


}

//courier
//$2a$10$BiPJWxRAuOz/8R4JAVi7weEK8Ybm4yuScQdPlVRPloxqQv9ypSwNq
//shop
//$2a$10$Gl7.5P0QtgNXPPXgOZQlMuTlQwUSIEQaYqS1VhwoQd/Ydb2WV.hRO
//customer
//$2a$10$eJiCt1f1zRhh3L6C8obeberAb2xMzC7ThoQM.sW8bSSeuEcfs2cte
//admin
//