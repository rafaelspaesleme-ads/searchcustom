package br.com.xyz.searchcustom.services;

import br.com.xyz.searchcustom.entities.User;
import br.com.xyz.searchcustom.repositories.UserRepository;
import br.com.xyz.searchcustom.repositories.specifications.SearchCriteria;
import br.com.xyz.searchcustom.repositories.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public List<User> searchPerId(Long id){
        UserSpecification spec = new UserSpecification(new SearchCriteria("id", ":", id));
        return userRepository.findAll(spec);
    }

}
