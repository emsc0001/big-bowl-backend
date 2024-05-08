package bigbowl.security_demo.configuration;

import bigbowl.security.entity.Role;
import bigbowl.security.entity.UserWithRoles;
import bigbowl.security.repository.RoleRepository;
import bigbowl.security.repository.UserWithRolesRepository;
import bigbowl.security_demo.entity.SpecialUser;
import bigbowl.security_demo.repository.SpecialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class SetupDevUsers implements ApplicationRunner {

    @Autowired
    SpecialUserRepository specialUserRepository;

    UserWithRolesRepository userWithRolesRepository;
    RoleRepository roleRepository;
    PasswordEncoder pwEncoder;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.roleRepository = roleRepository;
        this.pwEncoder = passwordEncoder;

        passwordUsedByAll = "test12";
    }

    public void run(ApplicationArguments args) {
        setupAllowedRoles();
        setupUserWithRoleUsers();
    }

    private void setupAllowedRoles(){
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
    }


    private void setupUserWithRoleUsers() {
        Role roleUser = roleRepository.findById("USER").orElseThrow(()-> new NoSuchElementException("Role 'user' not found"));
        Role roleAdmin = roleRepository.findById("ADMIN").orElseThrow(()-> new NoSuchElementException("Role 'admin' not found"));
        System.out.println("******************************************************************************");
        System.out.println("********** IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************");
        System.out.println();
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println();
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", pwEncoder.encode(passwordUsedByAll), "user1@admin.dk");
        UserWithRoles user2 = new UserWithRoles("user2", pwEncoder.encode(passwordUsedByAll), "user2@user.dk");
        UserWithRoles user3 = new UserWithRoles("user3", pwEncoder.encode(passwordUsedByAll), "user3@admin.dk");
        UserWithRoles user4 = new UserWithRoles("user4", pwEncoder.encode(passwordUsedByAll), "user4@user.dk");
        UserWithRoles woo1 = new UserWithRoles("Woo", pwEncoder.encode(passwordUsedByAll), "woo@back.dk" );
        user1.addRole(roleUser);
        user1.addRole(roleAdmin);
        user2.addRole(roleUser);
        user3.addRole(roleAdmin);
        user4.addRole(roleUser);
        woo1.addRole(roleAdmin);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        userWithRolesRepository.save(woo1);

        SpecialUser specialUser = new SpecialUser("specialUser",pwEncoder.encode(passwordUsedByAll),"s@a.dk","Anders","Hansen","Lyngby vej 23","2800","Lyngby");
        specialUser.addRole(roleUser);
        specialUserRepository.save(specialUser);

    }
}
